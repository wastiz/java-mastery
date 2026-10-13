package com.javamastery.core.c16_patterns;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class PatternsTest {

    @Test
    void builder() {
        HttpRequest r = HttpRequest.builder("https://api.example.com/users")
                .method("post").header("Accept", "json").header("accept", "xml")
                .query("page", "2").query("size", "10").body("{}").timeoutMs(5000).build();
        assertEquals("POST", r.method());
        assertEquals(List.of("json", "xml"), r.header("ACCEPT"));
        assertEquals(List.of(), r.header("X-Missing"));
        assertEquals("https://api.example.com/users?page=2&size=10", r.fullUrl());
        assertEquals(5000, r.timeoutMs());
        assertEquals("{}", r.body());
    }

    @Test
    void builderDefaultsAndValidation() {
        HttpRequest r = HttpRequest.builder("http://x.ee").build();
        assertEquals("GET", r.method());
        assertEquals(30000, r.timeoutMs());
        assertNull(r.body());
        assertEquals("http://x.ee", r.fullUrl());
        assertThrows(IllegalArgumentException.class, () -> HttpRequest.builder("ftp://x"));
        assertThrows(IllegalStateException.class, () -> HttpRequest.builder("http://x").body("b").build());
        assertThrows(IllegalArgumentException.class, () -> HttpRequest.builder("http://x").timeoutMs(0));
    }

    @Test
    void builtRequestIsImmutable() {
        HttpRequest.Builder b = HttpRequest.builder("http://x").header("A", "1");
        HttpRequest r = b.build();
        b.header("A", "2").query("q", "1");
        assertEquals(List.of("1"), r.header("A"));
        assertEquals("http://x", r.fullUrl());
        assertThrows(UnsupportedOperationException.class, () -> r.header("A").add("hack"));
    }

    sealed interface Event permits UserEvent, SystemEvent {}

    record UserEvent(String user) implements Event {}

    record SystemEvent(String msg) implements Event {}

    @Test
    void eventBus() {
        EventBus bus = new EventBus();
        List<String> log = new ArrayList<>();
        bus.subscribe(UserEvent.class, e -> log.add("user:" + e.user()));
        EventBus.Subscription all = bus.subscribe(Event.class, e -> log.add("any:" + e));
        bus.subscribe(Object.class, e -> { throw new IllegalStateException("broken handler"); });
        bus.subscribe(SystemEvent.class, e -> log.add("sys:" + e.msg()));

        assertEquals(3, bus.publish(new UserEvent("ann")));
        assertEquals(List.of("user:ann", "any:UserEvent[user=ann]"), log);
        assertEquals(1, bus.errors().size());

        all.cancel();
        log.clear();
        assertEquals(2, bus.publish(new SystemEvent("boot")));
        assertEquals(List.of("sys:boot"), log);
        assertEquals(1, bus.publish("just a string"));
    }

    static class FakeRepo implements Repositories.UserRepository {
        final Map<Long, String> data = new HashMap<>(Map.of(1L, "Ann", 2L, "Bob"));
        final AtomicInteger finds = new AtomicInteger();

        public Optional<String> findName(long id) {
            finds.incrementAndGet();
            if (id < 0) throw new IllegalArgumentException("negative id");
            return Optional.ofNullable(data.get(id));
        }

        public void rename(long id, String newName) {
            data.put(id, newName);
        }
    }

    @Test
    void cachingDecorator() {
        FakeRepo fake = new FakeRepo();
        var repo = Repositories.caching(fake, 2);
        assertEquals(Optional.of("Ann"), repo.findName(1));
        assertEquals(Optional.of("Ann"), repo.findName(1));
        assertEquals(Optional.empty(), repo.findName(9));
        assertEquals(Optional.empty(), repo.findName(9));
        assertEquals(2, fake.finds.get());
        repo.rename(1, "Anna");
        assertEquals(Optional.of("Anna"), repo.findName(1));
        assertEquals(3, fake.finds.get());
        repo.findName(2);       // кэш: 9, 1 → добавили 2 → вытеснили 9
        repo.findName(9);
        assertEquals(5, fake.finds.get());
    }

    @Test
    void loggingDecorator() {
        List<String> log = new ArrayList<>();
        var repo = Repositories.logging(new FakeRepo(), log);
        repo.findName(2);
        repo.rename(2, "Bobby");
        assertThrows(IllegalArgumentException.class, () -> repo.findName(-1));
        assertEquals(List.of("findName(2)", "-> Optional[Bob]", "rename(2, Bobby)", "-> ok", "findName(-1)", "!! negative id"), log);
    }

    @Test
    void decoratorsCompose() {
        List<String> log = new ArrayList<>();
        FakeRepo fake = new FakeRepo();
        var repo = Repositories.logging(Repositories.caching(fake, 10), log);
        repo.findName(1);
        repo.findName(1);
        assertEquals(4, log.size());
        assertEquals(1, fake.finds.get());
    }

    @Test
    void orderStates() {
        var o = new OrderState.Order();
        o.moveTo(OrderState.Status.PAID);
        o.moveTo(OrderState.Status.SHIPPED);
        o.moveTo(OrderState.Status.DELIVERED);
        var ex = assertThrows(IllegalStateException.class, () -> o.moveTo(OrderState.Status.CANCELLED));
        assertEquals("Cannot go from DELIVERED to CANCELLED", ex.getMessage());

        var o2 = new OrderState.Order();
        o2.moveTo(OrderState.Status.CANCELLED);
        assertFalse(o2.refundRequired());
        assertThrows(IllegalStateException.class, () -> o2.moveTo(OrderState.Status.PAID));

        var o3 = new OrderState.Order();
        assertThrows(IllegalStateException.class, () -> o3.moveTo(OrderState.Status.SHIPPED));
        o3.moveTo(OrderState.Status.PAID);
        o3.moveTo(OrderState.Status.CANCELLED);
        assertTrue(o3.refundRequired());
    }
}
