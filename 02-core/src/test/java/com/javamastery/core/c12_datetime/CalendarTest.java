package com.javamastery.core.c12_datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

    @Test
    void age() {
        assertEquals(30, Calendar.age(LocalDate.of(1996, 10, 13), LocalDate.of(2026, 10, 13)));
        assertEquals(29, Calendar.age(LocalDate.of(1996, 10, 14), LocalDate.of(2026, 10, 13)));
        assertEquals(1, Calendar.age(LocalDate.of(2024, 2, 29), LocalDate.of(2025, 3, 1)));
    }

    @Test
    void businessDays() {
        LocalDate mon = LocalDate.of(2026, 10, 12);
        assertEquals(5, Calendar.businessDays(mon, mon.plusDays(7), Set.of()));
        assertEquals(4, Calendar.businessDays(mon, mon.plusDays(7), Set.of(mon.plusDays(2))));
        assertEquals(0, Calendar.businessDays(mon.plusDays(5), mon.plusDays(7), Set.of()));
        assertEquals(0, Calendar.businessDays(mon, mon, Set.of()));
    }

    @Test
    void addBusinessDays() {
        LocalDate fri = LocalDate.of(2026, 10, 16);
        assertEquals(LocalDate.of(2026, 10, 19), Calendar.addBusinessDays(fri, 1, Set.of()));
        assertEquals(LocalDate.of(2026, 10, 20), Calendar.addBusinessDays(fri, 1, Set.of(LocalDate.of(2026, 10, 19))));
        assertEquals(fri, Calendar.addBusinessDays(fri, 0, Set.of()));
        assertEquals(LocalDate.of(2026, 10, 30), Calendar.addBusinessDays(fri, 10, Set.of()));
    }

    @Test
    void timeZones() {
        // летом Таллин UTC+3, Нью-Йорк UTC-4
        assertEquals(LocalDateTime.of(2026, 7, 1, 8, 0),
                Calendar.meetingTimeIn(LocalDateTime.of(2026, 7, 1, 15, 0), ZoneId.of("America/New_York")));
        // в декабре Таллин UTC+2, Нью-Йорк UTC-5
        assertEquals(LocalDateTime.of(2026, 12, 1, 8, 0),
                Calendar.meetingTimeIn(LocalDateTime.of(2026, 12, 1, 15, 0), ZoneId.of("America/New_York")));
        // Токио: летнего времени нет
        assertEquals(LocalDateTime.of(2026, 1, 1, 0, 30),
                Calendar.meetingTimeIn(LocalDateTime.of(2025, 12, 31, 17, 30), ZoneId.of("Asia/Tokyo")));
    }

    @Test
    void parseDuration() {
        assertEquals(Duration.ofMinutes(90), Calendar.parseDuration("1h30m"));
        assertEquals(Duration.ofMinutes(45), Calendar.parseDuration("45m"));
        assertEquals(Duration.ofHours(2), Calendar.parseDuration("2h"));
        assertEquals(Duration.ofSeconds(3930), Calendar.parseDuration("1h5m30s"));
        assertThrows(IllegalArgumentException.class, () -> Calendar.parseDuration("1x"));
        assertThrows(IllegalArgumentException.class, () -> Calendar.parseDuration("30m1h"));
        assertThrows(IllegalArgumentException.class, () -> Calendar.parseDuration(""));
    }

    @Test
    void lastFriday() {
        assertEquals(LocalDate.of(2026, 10, 30), Calendar.lastWeekdayOfMonth(YearMonth.of(2026, 10), DayOfWeek.FRIDAY));
        assertEquals(LocalDate.of(2026, 2, 28), Calendar.lastWeekdayOfMonth(YearMonth.of(2026, 2), DayOfWeek.SATURDAY));
    }

    @Test
    void overlaps() {
        Instant t = Instant.parse("2026-10-13T10:00:00Z");
        assertTrue(Calendar.overlaps(t, t.plusSeconds(3600), t.plusSeconds(1800), t.plusSeconds(7200)));
        assertFalse(Calendar.overlaps(t, t.plusSeconds(3600), t.plusSeconds(3600), t.plusSeconds(7200)));
    }
}
