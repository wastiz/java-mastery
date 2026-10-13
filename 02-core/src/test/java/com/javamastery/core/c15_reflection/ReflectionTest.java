package com.javamastery.core.c15_reflection;

import com.javamastery.core.c15_reflection.MiniContainer.*;
import com.javamastery.core.c15_reflection.Validation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    static class BaseEntity {
        @Min(1)
        private long id;
    }

    static class SignUp extends BaseEntity {
        static final String IGNORED = "";
        @NotBlank @Size(min = 3, max = 16)
        private String login;
        @Email
        private String email;
        @Min(18) @Max(130)
        private Integer age;
        @Size(max = 2)
        private List<String> tags = new ArrayList<>();

        SignUp(long id, String login, String email, Integer age, List<String> tags) {
            ((BaseEntity) this).id = id;
            this.login = login;
            this.email = email;
            this.age = age;
            this.tags = tags;
        }
    }

    @Test
    void valid() {
        assertEquals(List.of(), Validation.validate(new SignUp(5, "valeri", "v@corp.ee", 30, List.of("java"))));
        assertEquals(List.of(), Validation.validate(new SignUp(5, "valeri", null, null, null)));
    }

    @Test
    void allViolationsInOrder() {
        List<String> errors = Validation.validate(new SignUp(0, "  ", "bad@", 15, List.of("a", "b", "c")));
        assertEquals(List.of(
                "id: must be >= 1",
                "login: must not be blank",
                "login: size must be between 3 and 16",
                "email: must be a valid email",
                "age: must be >= 18",
                "tags: size must be between 0 and 2"), errors);
    }

    @Test
    void emails() {
        for (String bad : new String[]{"a@b", "@b.com", "a@@b.com", "a@b.", "a@.com", "plain"}) {
            assertEquals(List.of("email: must be a valid email"),
                    Validation.validate(new SignUp(1, "valeri", bad, 20, List.of())), bad);
        }
    }

    // ===== DI container =====

    interface Clock { long now(); }

    static class FixedClock implements Clock {
        public long now() { return 42; }
    }

    interface Repo { String find(); }

    static class SqlRepo implements Repo {
        final Clock clock;
        boolean initialized;

        SqlRepo(Clock clock) { this.clock = clock; }

        @PostConstruct
        void init() { initialized = true; }

        public String find() { return "row@" + clock.now(); }
    }

    static class Service {
        final Repo repo;
        final Clock clock;

        Service() { this(null, null); }

        @Inject
        Service(Repo repo, Clock clock) {
            this.repo = repo;
            this.clock = clock;
        }
    }

    @Prototype
    static class RequestContext {}

    static class A { A(B b) {} }

    static class B { B(A a) {} }

    @Test
    void injectsDependencies() {
        MiniContainer c = new MiniContainer().register(FixedClock.class).register(SqlRepo.class).register(Service.class);
        Service s = c.get(Service.class);
        assertEquals("row@42", s.repo.find());
        assertSame(s.clock, ((SqlRepo) s.repo).clock, "синглтоны должны переиспользоваться");
        assertSame(s, c.get(Service.class));
        assertTrue(((SqlRepo) c.get(Repo.class)).initialized, "@PostConstruct не вызван");
    }

    @Test
    void prototypes() {
        MiniContainer c = new MiniContainer().register(RequestContext.class);
        assertNotSame(c.get(RequestContext.class), c.get(RequestContext.class));
    }

    @Test
    void errors() {
        MiniContainer c = new MiniContainer().register(A.class).register(B.class);
        var ex = assertThrows(IllegalStateException.class, () -> c.get(A.class));
        assertTrue(ex.getMessage().contains("Circular"), ex.getMessage());
        MiniContainer none = new MiniContainer();
        assertThrows(IllegalStateException.class, () -> none.get(Clock.class));
        MiniContainer two = new MiniContainer().register(FixedClock.class).register(FixedClock2.class);
        assertThrows(IllegalStateException.class, () -> two.get(Clock.class));
    }

    static class FixedClock2 implements Clock {
        public long now() { return 7; }
    }
}
