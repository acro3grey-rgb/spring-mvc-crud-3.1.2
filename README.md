# Spring Boot MVC CRUD Application

CRUD-приложение на Spring Boot MVC с использованием Spring Data JPA, Spring Security, Liquibase, Thymeleaf и MySQL.

## Что есть в проекте

- управление пользователями через `/admin`
- страница текущего пользователя на `/user`
- роли `ROLE_ADMIN` и `ROLE_USER`
- авторизация через Spring Security
- схема и стартовые данные через Liquibase
- MySQL в Docker через `docker-compose.yml`

## Стек

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Liquibase
- Thymeleaf
- MySQL
- Docker Compose
- Maven
- Lombok

## Как запустить MySQL в Docker

Сначала подготовь локальный env-файл:

```text
copy .env.example .env
```

```text
docker compose up -d mysql
```

Контейнер поднимет:

- базу `testdb`
- пользователя `root`
- пароль `root`
- порт `3308`

## Как запустить приложение

Приложение по умолчанию ожидает:

```text
DB_URL=jdbc:mysql://localhost:3308/testdb?serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=root
```

Если переменные окружения не заданы, будут использованы эти же значения по умолчанию.

Запуск:

```text
mvn spring-boot:run
```

После старта открыть:

```text
http://localhost:8080/user
```

Для входа можно использовать:

```text
admin / admin
user / user
```

## Быстрая проверка после запуска

1. Подними базу: `docker compose up -d mysql`
2. Убедись, что контейнер жив: `docker compose ps`
3. Запусти приложение: `mvn spring-boot:run`
4. Проверь в логах, что Liquibase выполнил changelog без ошибок
5. Открой `http://localhost:8080/login`
6. Войди под `admin / admin`
7. Проверь, что в БД появились таблицы `users`, `roles`, `users_roles`, `databasechangelog`, `databasechangeloglock`

## Как теперь инициализируется БД

Liquibase запускается при старте приложения и применяет changelog-файлы из:

```text
src/main/resources/db/changelog
```

Что делает Liquibase в этом проекте:

- создаёт таблицы `users`, `roles`, `users_roles`
- добавляет внешние ключи
- заполняет стартовые роли и пользователей
- хранит историю применённых миграций в служебных таблицах Liquibase

Служебные таблицы Liquibase:

- `databasechangelog` хранит историю выполненных `changeSet`
- `databasechangeloglock` защищает от одновременного запуска миграций

Важно: `spring.jpa.hibernate.ddl-auto=validate`, поэтому Hibernate больше не создаёт таблицы сам, а только проверяет, что схема соответствует сущностям.

## Что нужно установить локально

- Docker Desktop
- Java 17
- Maven

Maven Wrapper (`mvnw`) пока не добавлен, потому что в текущем окружении отсутствует команда `mvn`, а штатная генерация wrapper идёт через Maven.

Автор: https://github.com/acro3grey-rgb
