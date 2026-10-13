package com.javamastery.core.c12_datetime;

import java.time.*;
import java.util.Set;

/**
 * C12 · java.time: LocalDate, LocalDateTime, ZonedDateTime, Duration, Period, DayOfWeek.
 * (java.util.Date / Calendar не используем — это legacy.)
 */
public final class Calendar {
    private Calendar() {}

    /** Полных лет на дату today. */
    public static int age(LocalDate birthday, LocalDate today) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Количество рабочих дней (пн-пт, не в holidays) в интервале [from, to) . */
    public static int businessDays(LocalDate from, LocalDate to, Set<LocalDate> holidays) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Прибавить n рабочих дней (пропуская выходные и праздники). n &gt;= 0. addBusinessDays(пятница, 1) → понедельник. */
    public static LocalDate addBusinessDays(LocalDate start, int n, Set<LocalDate> holidays) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Митинг назначен в Таллине на local время. Какое будет местное время в зоне targetZone?
     * Учитывай летнее/зимнее время (зону бери из ZoneId, а не фиксированное смещение!).
     */
    public static LocalDateTime meetingTimeIn(LocalDateTime tallinnTime, ZoneId targetZone) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Разобрать длительность вида "1h30m", "45m", "2h", "1h5m30s" → Duration. Неверный формат → IllegalArgumentException. */
    public static Duration parseDuration(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Последний день месяца, приходящийся на заданный день недели (например, последняя пятница). TemporalAdjusters. */
    public static LocalDate lastWeekdayOfMonth(YearMonth month, DayOfWeek dow) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Пересекаются ли два интервала времени [s1, e1) и [s2, e2) (Instant). */
    public static boolean overlaps(Instant s1, Instant e1, Instant s2, Instant e2) {
        throw new UnsupportedOperationException("TODO");
    }
}
