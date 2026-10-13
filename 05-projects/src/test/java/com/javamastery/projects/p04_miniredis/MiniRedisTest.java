package com.javamastery.projects.p04_miniredis;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class MiniRedisTest {

    final AtomicLong now = new AtomicLong(1_000_000);
    final MiniRedis r = new MiniRedis(now::get);

    @Test
    void setGetDel() {
        assertEquals("OK", r.execute("SET name Valeri"));
        assertEquals("Valeri", r.execute("get name"));
        assertEquals("(nil)", r.execute("GET nope"));
        assertEquals("OK", r.execute("SET greeting \"hello world\""));
        assertEquals("hello world", r.execute("GET greeting"));
        assertEquals("2", r.execute("DEL name greeting nope"));
        assertEquals("(nil)", r.execute("GET name"));
    }

    @Test
    void errors() {
        assertEquals("ERR unknown command 'FLY'", r.execute("FLY away"));
        assertEquals("ERR wrong number of arguments", r.execute("GET"));
        assertEquals("ERR wrong number of arguments", r.execute("SET a"));
    }

    @Test
    void incr() {
        assertEquals("1", r.execute("INCR visits"));
        assertEquals("2", r.execute("INCR visits"));
        r.execute("SET n 41");
        assertEquals("42", r.execute("INCR n"));
        r.execute("SET s abc");
        assertEquals("ERR not an integer", r.execute("INCR s"));
    }

    @Test
    void ttl() {
        r.execute("SET session abc EX 10");
        assertEquals("10", r.execute("TTL session"));
        now.addAndGet(3_500);
        assertEquals("7", r.execute("TTL session"));
        now.addAndGet(6_500);
        assertEquals("(nil)", r.execute("GET session"));
        assertEquals("-2", r.execute("TTL session"));
        r.execute("SET perm x");
        assertEquals("-1", r.execute("TTL perm"));
        assertEquals("1", r.execute("EXPIRE perm 5"));
        assertEquals("0", r.execute("EXPIRE ghost 5"));
        now.addAndGet(5_000);
        assertEquals("(nil)", r.execute("GET perm"));
    }

    @Test
    void setRemovesOldTtl() {
        r.execute("SET k v EX 1");
        r.execute("SET k v2");
        now.addAndGet(5_000);
        assertEquals("v2", r.execute("GET k"));
    }

    @Test
    void keys() {
        r.execute("SET user:1 a");
        r.execute("SET user:2 b");
        r.execute("SET user:10 c EX 1");
        r.execute("SET order:1 d");
        assertEquals("user:1,user:10,user:2", r.execute("KEYS user:*"));
        assertEquals("user:1,user:2", r.execute("KEYS user:?"));
        now.addAndGet(2_000);
        assertEquals("user:1,user:2", r.execute("KEYS user:*"));
        assertEquals("", r.execute("KEYS nothing*"));
        assertEquals("order:1,user:1,user:2", r.execute("KEYS *"));
    }

    @Test
    void lists() {
        assertEquals("3", r.execute("LPUSH queue a b c"));
        assertEquals("c,b,a", r.execute("LRANGE queue 0 -1"));
        assertEquals("b,a", r.execute("LRANGE queue -2 -1"));
        assertEquals("a", r.execute("RPOP queue"));
        assertEquals("b", r.execute("RPOP queue"));
        assertEquals("c", r.execute("RPOP queue"));
        assertEquals("(nil)", r.execute("RPOP queue"));
        r.execute("LPUSH l x");
        assertEquals("ERR wrong type", r.execute("GET l"));
        assertEquals("", r.execute("LRANGE missing 0 -1"));
    }

    @Test
    void threadSafety() throws Exception {
        MiniRedis real = new MiniRedis();
        Thread[] ts = new Thread[8];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < 5_000; j++) real.execute("INCR counter");
            });
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        assertEquals("40000", real.execute("GET counter"));
    }
}
