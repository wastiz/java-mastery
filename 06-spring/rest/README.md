# Spring · Bookstore REST API (темы S02, S03)

Сервис уже запускается (`mvn spring-boot:run`, порт 8080), но в нём нет ни одного эндпоинта.
Твоя задача — реализовать `BookService`, добавить валидацию в `CreateBookRequest`
и **с нуля** написать два класса в пакете `com.javamastery.spring.rest.web`:

* `BookController` — REST-контроллер;
* `GlobalExceptionHandler` — `@RestControllerAdvice`, который превращает исключения в `ProblemDetail` (RFC 9457).

## Спецификация API

| Метод | URL | Тело | Ответ |
|---|---|---|---|
| GET | `/api/books?author=&page=0&size=20&sort=title` | — | `200` + `PageResponse<BookResponse>` |
| GET | `/api/books/{id}` | — | `200` + `BookResponse`, `404` если нет |
| POST | `/api/books` | `CreateBookRequest` | `201` + `Location: /api/books/{id}` + `BookResponse` |
| PUT | `/api/books/{id}` | `CreateBookRequest` | `200` + `BookResponse` |
| PATCH | `/api/books/{id}/stock` | `{"delta": -2}` | `200` + `BookResponse` |
| DELETE | `/api/books/{id}` | — | `204` |

* `author` — фильтр «содержит», без учёта регистра; `sort` — `title` (без учёта регистра), `year` или `price`, при равенстве — по id.
* `page < 0`, `size` вне 1..100 или неизвестный `sort` → `400`.

## Ошибки — всегда `application/problem+json`

| Ситуация | status | title |
|---|---|---|
| книга не найдена | 404 | `Book not found` |
| ISBN уже занят другой книгой | 409 | `Duplicate ISBN` |
| остаток уйдёт в минус | 409 | `Out of stock` |
| неверные параметры (`IllegalArgumentException`) | 400 | `Bad request` |
| не прошла валидация тела | 400 | `Validation failed` + поле `errors`: `{"title": "title is required", ...}` |

Сообщения валидации (тесты проверяют их дословно):

* `isbn` — ровно 13 цифр: `isbn must be 13 digits` (и для пустого значения тоже);
* `title` — не пустой: `title is required`;
* `author` — не пустой: `author is required`;
* `year` — от 1450 до 2100: `year must be between 1450 and 2100`;
* `price` — не null: `price is required`; не меньше 0: `price must be >= 0`;
* `stock` — не меньше 0: `stock must be >= 0`.

Если у поля несколько нарушений — в `errors` достаточно любого одного.

Запуск тестов: `mvn -f 06-spring/rest test`.
