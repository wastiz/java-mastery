package com.javamastery.spring.core;

import com.javamastery.spring.core.aop.CallRegistry;
import com.javamastery.spring.core.aop.PriceCalculator;
import com.javamastery.spring.core.aop.SimplePriceCalculator;
import com.javamastery.spring.core.events.LoyaltyListener;
import com.javamastery.spring.core.events.OrderService;
import com.javamastery.spring.core.lifecycle.CacheWarmer;
import com.javamastery.spring.core.lifecycle.ReportService;
import com.javamastery.spring.core.lifecycle.RequestContext;
import com.javamastery.spring.core.messaging.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;
import java.time.Clock;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpringCoreTest {

    AnnotationConfigApplicationContext ctx;

    @BeforeEach
    void start() {
        CacheWarmer.EVENTS.clear();
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterEach
    void stop() {
        if (ctx.isActive()) ctx.close();
    }

    @Test
    void clockBean() {
        assertThat(ctx.getBean(Clock.class).getZone().getId()).isIn("Z", "UTC");
    }

    @Test
    void sendersAreBeansAndPropertyInjected() {
        assertThat(ctx.getBeansOfType(MessageSender.class)).hasSize(2);
        EmailSender email = ctx.getBean(EmailSender.class);
        assertThat(email.send("ann@x.ee", "hi")).isEqualTo("email to ann@x.ee from Java Mastery Bot: hi");
    }

    @Test
    void notificationService() {
        NotificationService s = ctx.getBean(NotificationService.class);
        assertThat(s.channels()).containsExactly("email", "sms");
        assertThat(s.notify("sms", "+372", "  Your code is 123456, valid 5 min  ")).isEqualTo("sms to +372: Your code is 123456,");
        assertThat(s.notifyHtml("ann@x.ee", "<b>bold</b>")).isEqualTo("email to ann@x.ee from Java Mastery Bot: <p>&lt;b&gt;bold&lt;/b&gt;</p>");
        assertThatThrownBy(() -> s.notify("pigeon", "x", "y")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void primaryAndQualifier() {
        assertThat(ctx.getBean(TextFormatter.class)).isInstanceOf(PlainFormatter.class);
        assertThat(ctx.getBean("htmlFormatter")).isInstanceOf(HtmlFormatter.class);
    }

    @Test
    void lifecycle() {
        CacheWarmer w = ctx.getBean(CacheWarmer.class);
        assertThat(w.isWarm()).isTrue();
        assertThat(w.cache()).containsExactly("a", "b", "c");
        ctx.close();
        assertThat(CacheWarmer.EVENTS).containsExactly("constructor", "postConstruct", "preDestroy");
    }

    @Test
    void scopes() {
        assertThat(ctx.getBean(RequestContext.class).id()).isNotEqualTo(ctx.getBean(RequestContext.class).id());
        assertThat(ctx.getBean(ReportService.class)).isSameAs(ctx.getBean(ReportService.class));
        ReportService reports = ctx.getBean(ReportService.class);
        String r1 = reports.generate(), r2 = reports.generate();
        assertThat(r1).startsWith("report ").contains(" at ");
        assertThat(r1.split(" ")[1]).isNotEqualTo(r2.split(" ")[1]);
    }

    @Test
    void events() {
        OrderService orders = ctx.getBean(OrderService.class);
        assertThat(orders.placeOrder("ann", 2599)).isEqualTo(1);
        assertThat(orders.placeOrder("ann", 1000)).isEqualTo(2);
        orders.placeOrder("bob", 999);
        LoyaltyListener loyalty = ctx.getBean(LoyaltyListener.class);
        assertThat(loyalty.pointsOf("ann")).isEqualTo(3);
        assertThat(loyalty.pointsOf("bob")).isZero();
    }

    @Test
    void beanPostProcessorProxy() {
        PriceCalculator calc = ctx.getBean(PriceCalculator.class);
        assertThat(Proxy.isProxyClass(calc.getClass())).as("бин должен быть JDK-прокси").isTrue();
        assertThat(calc.price("BOOK", 4)).isEqualTo(1000);
        assertThat(ctx.getBean(CallRegistry.class).calls()).containsExactly("SimplePriceCalculator.price");
        // а по классу реализации бин больше не найти — почему? (см. теорию: JDK proxy vs CGLIB)
        assertThat(ctx.getBeanNamesForType(SimplePriceCalculator.class)).isEmpty();
        assertThat(ctx.getBeansOfType(CallRegistry.class).values().stream().map(Object::getClass).toList())
                .isEqualTo(List.of(CallRegistry.class));
    }
}
