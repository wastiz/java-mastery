# Spring · Library: Spring Data JPA + Hibernate + транзакции (темы S04, S05)

Схема БД уже задана миграцией Flyway (`src/main/resources/db/migration/V1__init.sql`),
а Hibernate запущен с `ddl-auto: validate` — то есть **твои сущности обязаны точно совпасть со схемой**,
иначе приложение даже не стартует. Так и бывает на реальных проектах.

## Что сделать

1. **Маппинг сущностей** (`domain`): `@Entity`, `@Id` + `@GeneratedValue(strategy = IDENTITY)`, связи:
   * `Author 1 — N Book` двунаправленная: `Book.author` — `@ManyToOne(fetch = LAZY)` + `@JoinColumn(name = "author_id")`,
     `Author.books` — `@OneToMany(mappedBy = "author", cascade = ALL, orphanRemoval = true)`;
   * `Book N — N Tag` через таблицу `book_tag`;
   * `Book.year` хранится в колонке `published_year`;
   * `Book.version` — оптимистическая блокировка (`@Version`);
   * `Loan` → `Book` и `Member` (`@ManyToOne(fetch = LAZY)`).
2. **Репозитории** (`repo`): методы объявлены как `default`-заглушки — замени их настоящими
   derived-запросами, `@Query` (JPQL / native), проекцией, `@Modifying`, `@EntityGraph` / `join fetch`.
3. **Сервисы** (`service`):
   * `CatalogService.authorSummaries()` — без проблемы N+1 (тест считает SQL-запросы!);
   * `LoanService` — правила выдачи книг и транзакции (см. javadoc), в том числе `REQUIRES_NEW` для аудита.

Запуск тестов: `mvn -f 06-spring/data test`. SQL видно в логе (`show-sql`).
