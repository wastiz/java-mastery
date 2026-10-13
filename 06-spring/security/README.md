# Spring · Notes API: Spring Security + JWT (тема S06)

Stateless REST API заметок с аутентификацией по JWT (HS256, общий секрет из `app.jwt.secret`).
Пользователи уже заведены в `UserAccountRepository`: `alice / alice-pass` (USER) и `admin / admin-pass` (USER, ADMIN).

## Что сделать

1. `security/SecurityConfig` — цепочка фильтров, `PasswordEncoder` (BCrypt), `JwtEncoder`, `JwtDecoder`,
   конвертер ролей из claim `roles` в `ROLE_*`.
2. `security/TokenService` — выпуск токенов.
3. **С нуля** в пакете `web`: `AuthController` и `NoteController` по спецификации ниже.

## Правила доступа

* `POST /auth/login`, `GET /actuator/health` — без аутентификации;
* `/api/admin/**` — только роль `ADMIN` (иначе `403`);
* всё остальное — только с валидным `Authorization: Bearer <token>` (иначе `401`);
* сессий нет (`STATELESS`), CSRF выключен (почему для stateless API это нормально — см. теорию).

## Эндпоинты

| Метод | URL | Тело | Ответ |
|---|---|---|---|
| POST | `/auth/login` | `{"username": "alice", "password": "alice-pass"}` | `200 {"token": "...", "expiresIn": 3600}`; неверные данные → `401` |
| GET | `/api/me` | — | `200 {"username": "alice", "roles": ["USER"]}` |
| GET | `/api/notes` | — | `200` — список заметок **текущего** пользователя |
| POST | `/api/notes` | `{"text": "..."}` | `201` + `Note` |
| DELETE | `/api/notes/{id}` | — | `204`; чужая заметка → `403` (кроме ADMIN — ему можно всё); нет такой → `404` |
| GET | `/api/admin/stats` | — | `200 {"users": 2, "notes": N}` |

## JWT

* `sub` — имя пользователя, `roles` — список ролей без префикса (`["USER", "ADMIN"]`),
  `iss` — `java-mastery`, `iat`/`exp` — время выпуска и истечения (`app.jwt.ttl`, по умолчанию 1 час);
* подпись HS256 секретом из `app.jwt.secret` (base64).

Запуск тестов: `mvn -f 06-spring/security test`.
