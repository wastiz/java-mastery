# Spring · Quest Board — финальный проект ⚔️ (тема S11)

RPG-трекер задач: герои берут квесты, выполняют их, получают опыт и золото, растут в уровнях,
открывают достижения и соревнуются в таблице лидеров.

Здесь почти ничего не дано: есть только `QuestBoardApplication` и `application.yml`.
**Архитектуру (сущности, репозитории, сервисы, контроллеры, обработку ошибок) ты проектируешь сам.**
Тесты — «чёрный ящик»: они ходят только в HTTP API, поэтому внутри можно делать как угодно.

Требования к реализации (их не проверить тестами, но их проверит ревьюер на собесе 😉):
слои controller → service → repository, DTO вместо сущностей в API, `@Transactional` на сервисах,
ошибки в формате `ProblemDetail`, события вместо прямых вызовов для достижений, кэш для лидерборда.

## Время

Приложение должно брать текущее время только из бина `java.time.Clock` (объяви его сам).
Тесты подкладывают свой `@Primary`-бин `Clock` и «перематывают» время.

## Герои

`POST /api/heroes` `{"name": "Aragorn", "heroClass": "WARRIOR"}` → `201`
`{"id": 1, "name": "Aragorn", "heroClass": "WARRIOR", "xp": 0, "level": 1, "gold": 0}`

* `heroClass`: `WARRIOR`, `MAGE`, `ROGUE`; имя 3..20 символов (`400`), уникально (`409`);
* уровень: `level = 1 + floor(sqrt(xp / 100))` → 0 xp = 1 ур., 100 = 2, 400 = 3, 900 = 4;
* `GET /api/heroes/{id}` → `200` / `404`.

## Квесты

`POST /api/quests` `{"title": "Slay the dragon", "difficulty": "HARD", "deadline": "2026-10-20T12:00:00Z"}` → `201`
`{"id", "title", "difficulty", "status": "OPEN", "rewardXp", "rewardGold", "deadline", "heroId": null}`

| difficulty | rewardXp | rewardGold |
|---|---|---|
| EASY | 50 | 10 |
| MEDIUM | 120 | 25 |
| HARD | 300 | 60 |
| LEGENDARY | 1000 | 250 |

* `title` не пустой (`400`); `deadline` необязателен, но если задан — не в прошлом (`400`);
* `GET /api/quests?status=OPEN` → список (по id); без `status` — все.

### Жизненный цикл: `OPEN → IN_PROGRESS → COMPLETED | FAILED`, `OPEN/IN_PROGRESS → EXPIRED`

* `POST /api/quests/{id}/accept` `{"heroId": 1}` → `200`, `status: IN_PROGRESS`, `heroId: 1`.
  `409`, если квест не `OPEN`; у героя уже 2 квеста в работе; `LEGENDARY` требует уровень ≥ 3. `404`, если нет квеста/героя.
* `POST /api/quests/{id}/complete` → `200`. Квест должен быть `IN_PROGRESS` (иначе `409`).
  Если дедлайн уже прошёл → `status: FAILED`, награды нет. Иначе `COMPLETED`, герою начисляются награды:
  `MAGE` получает +10% опыта, `ROGUE` +20% золота (дробная часть отбрасывается).
* `POST /api/quests/{id}/abandon` → `200`, квест снова `OPEN` без героя, герой теряет 10 золота (не ниже 0). Только из `IN_PROGRESS` (`409`).
* `POST /api/admin/expire-quests` → `200 {"expired": N}` — все `OPEN`/`IN_PROGRESS` квесты с прошедшим дедлайном становятся `EXPIRED`.
  Та же логика должна запускаться по расписанию раз в сутки (`@Scheduled`).

## Таблица лидеров

`GET /api/leaderboard?limit=10` → `[{"rank": 1, "heroId": 3, "name": "Gandalf", "level": 4, "xp": 1100}, ...]`
— по убыванию xp, при равенстве по имени. Результат кэшируется (`@Cacheable`) и должен сбрасываться, когда меняется опыт.

## Достижения

`GET /api/heroes/{id}/achievements` → `["FIRST_QUEST", "RICH", ...]` в порядке получения, без повторов:

* `FIRST_QUEST` — первый выполненный квест;
* `HARD_WORKER` — 3 выполненных квеста;
* `LEGEND` — выполнен `LEGENDARY` квест;
* `RICH` — после выполнения квеста у героя ≥ 100 золота.

Если за одно выполнение получено несколько — порядок как в списке выше.
Реализуй через доменное событие `QuestCompletedEvent` и слушатель (`@EventListener` или `@TransactionalEventListener`).

Запуск тестов: `mvn -f 06-spring/questboard test`. Запуск приложения: `mvn -f 06-spring/questboard spring-boot:run`.
