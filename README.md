# ☕ Java Mastery

Практическая часть курса «Java и Spring Boot до уровня Middle».
Теория, порядок прохождения и трекер прогресса — в Notion: **NOTION_URL**

Здесь только задачи: в каждом классе есть сигнатуры и подробное описание в javadoc,
а вместо реализации — `throw new UnsupportedOperationException("TODO")`.
Тесты уже написаны. Твоя задача — сделать их зелёными. **Без ИИ** — как в универе 🙂

## Требования

* JDK 21 (`java -version`)
* Maven 3.9+ (`mvn -version`)
* IntelliJ IDEA (Community хватит) — открой корневой `pom.xml` как проект

## Как работать

```bash
./check.sh b05        # тесты одной темы (B05 — строки)
./check.sh c          # все темы модуля 02-core
./check.sh rest       # Spring-модуль (core | rest | data | security | questboard)
./check.sh b05 -v     # подробный вывод Maven
```

Или прямо из IDEA — зелёная стрелка рядом с тестом. Без скрипта:
`mvn -pl 01-basics test -Dtest='**/b05_strings/*Test'`.

Рекомендованный цикл для каждой темы:

1. Прочитать теорию в Notion (20–30 минут).
2. Открыть класс задачи, прочитать javadoc, **не подглядывая в тесты** попробовать решить.
3. Запустить тесты, разобраться с красными.
4. Ответить на вопросы с собеседования из Notion вслух (да, вслух).
5. Закоммитить: `git commit -am "B05 strings"` — история коммитов станет твоим дневником прогресса.

## Структура

| Модуль | Темы в Notion | Что внутри |
|---|---|---|
| `01-basics` | B01–B14 | синтаксис, ООП, строки, enum, records, sealed, исключения, immutability |
| `02-core` | C01–C17 | generics, коллекции изнутри (свои ArrayList / HashMap / LRU), Stream API, IO, GC, reflection, паттерны |
| `03-concurrency` | K01–K07 | потоки, synchronized/volatile, deadlock, executors, CompletableFuture, locks, виртуальные потоки |
| `04-algorithms` | A01–A07 | задачи в стиле LeetCode с номерами оригиналов |
| `05-projects` | P01–P05 | мини-проекты: Сапёр, «Жизнь», Змейка, Mini-Redis, JSON-парсер |
| `06-spring/core` | S01, S08 | IoC/DI, жизненный цикл бинов, события, свой BeanPostProcessor |
| `06-spring/rest` | S02, S03 | REST API, DTO, валидация, ProblemDetail |
| `06-spring/data` | S04, S05 | JPA-маппинг под готовую схему Flyway, запросы, N+1, транзакции, optimistic lock |
| `06-spring/security` | S06 | Spring Security + JWT |
| `06-spring/questboard` | S11 | финальный проект — архитектуру проектируешь сам |

Пакеты названы по кодам тем: `b05_strings`, `c05_hashmap`, `k04_completable`...

## Правила честной игры

* Не открывай тест, пока не написал первую версию решения — тест подскажет слишком много.
* Можно: документация Java/Spring, книги, свой старый код. Нельзя: ИИ и готовые решения.
* Застрял больше чем на 30 минут — открой «Подсказки» в Notion, они раскрываются по одной.
* Некоторые тесты проверяют сложность (таймаутами) — решение «в лоб» может не пройти, это нормально.
* В мини-проектах после зелёных тестов запусти `*Console`-классы и поиграй.
