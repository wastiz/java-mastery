package com.javamastery.core.c13_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IoTest {

    @TempDir
    Path tmp;

    private Path sampleLog() throws IOException {
        Path log = tmp.resolve("app.log");
        Files.write(log, List.of(
                "2026-10-13T14:00:01 INFO [web] Started",
                "2026-10-13T14:00:02 ERROR [db] Connection refused",
                "garbage line",
                "2026-10-13T14:00:03 WARN [web] Slow request [GET /api]",
                "2026-10-13T14:00:04 ERROR [db] Timeout",
                "2026-10-13T14:00:05 ERROR [auth] Bad token",
                "2026-10-13T14:00:06 FATAL [db] not a level",
                "2026-10-13T14:00:07 DEBUG [cache] miss"));
        return log;
    }

    @Test
    void parseLine() {
        var e = LogAnalyzer.parseLine("2026-10-13T14:00:03 WARN [web] Slow request [GET /api]");
        assertEquals(new LogAnalyzer.Entry("2026-10-13T14:00:03", "WARN", "web", "Slow request [GET /api]"), e);
        assertNull(LogAnalyzer.parseLine("nope"));
    }

    @Test
    void countByLevel() throws IOException {
        assertEquals(Map.of("DEBUG", 1L, "ERROR", 3L, "INFO", 1L, "WARN", 1L), LogAnalyzer.countByLevel(sampleLog()));
    }

    @Test
    void extractErrors() throws IOException {
        Path out = tmp.resolve("errors.log");
        Files.writeString(out, "old content that must disappear\n");
        assertEquals(3, LogAnalyzer.extractErrors(sampleLog(), out));
        List<String> lines = Files.readAllLines(out);
        assertEquals(3, lines.size());
        assertEquals("2026-10-13T14:00:02 ERROR [db] Connection refused", lines.get(0));
    }

    @Test
    void noisiest() throws IOException {
        assertEquals(List.of("db", "auth"), LogAnalyzer.noisiestComponents(sampleLog(), 2));
    }

    @Test
    void totalSize() throws IOException {
        Path sub = Files.createDirectories(tmp.resolve("a/b"));
        Files.write(tmp.resolve("x.log"), new byte[100]);
        Files.write(sub.resolve("y.log"), new byte[23]);
        Files.write(sub.resolve("z.txt"), new byte[1000]);
        assertEquals(123, LogAnalyzer.totalLogSize(tmp));
    }

    @Test
    void csvRead() throws IOException {
        String csv = "name,quote,n\r\n\"Doe, John\",\"He said \"\"hi\"\"\",1\n\"multi\nline\",,3\n";
        List<List<String>> rows = Csv.read(new StringReader(csv));
        assertEquals(3, rows.size());
        assertEquals(List.of("name", "quote", "n"), rows.get(0));
        assertEquals(List.of("Doe, John", "He said \"hi\"", "1"), rows.get(1));
        assertEquals(List.of("multi\nline", "", "3"), rows.get(2));
        assertEquals(List.of(List.of("a", "b")), Csv.read(new StringReader("a,b")));
    }

    @Test
    void csvRoundTrip() throws IOException {
        List<List<String>> rows = List.of(List.of("plain", "with,comma", "with \"quote\"", "line\nbreak", ""));
        StringWriter w = new StringWriter();
        Csv.write(rows, w);
        assertEquals("plain,\"with,comma\",\"with \"\"quote\"\"\",\"line\nbreak\",\n", w.toString());
        assertEquals(rows, Csv.read(new StringReader(w.toString())));
    }
}
