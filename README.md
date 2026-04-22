# Spring Boot MVC CRUD Application 3.1.2

Учебное CRUD-приложение на Spring Boot MVC с добавленной авторизацией и разграничением доступа через Spring Security.

## Что реализовано

- CRUD пользователей доступен только администратору по URL `/admin`.
- Обычный пользователь имеет доступ только к своей странице `/user`.
- Администратор также может открыть `/user`.
- Пользователь может иметь несколько ролей.
- `User` реализует `UserDetails`.
- `Role` реализует `GrantedAuthority`.
- Авторизация выполняется через пользователей из базы данных, а не через `inMemoryAuthentication`.
- После успешного входа:
  - `admin` перенаправляется на `/admin`;
  - `user` перенаправляется на `/user`.
- Logout доступен со страниц через Thymeleaf-форму.

## Стартовые пользователи

При запуске приложения создаются роли `ROLE_ADMIN` и `ROLE_USER`, а также тестовые пользователи, если их нет в базе:

| Login | Password | Roles |
| --- | --- | --- |
| `admin` | `admin` | `ROLE_ADMIN`, `ROLE_USER` |
| `user` | `user` | `ROLE_USER` |

Если удалить `admin` или `user` через админку, они восстановятся после перезапуска приложения.

## Основные URL

| URL | Доступ |
| --- | --- |
| `/login` | страница входа |
| `/admin` | только `ROLE_ADMIN` |
| `/user` | `ROLE_USER` и `ROLE_ADMIN` |
| `/logout` | выход из аккаунта |

## Технологии

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL
- Maven

## Настройка базы данных

Перед запуском должна быть доступна MySQL-база `testdb`.

```sql
CREATE DATABASE IF NOT EXISTS testdb;
```

Параметры подключения задаются в `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
```

Пароль от MySQL лучше передавать через переменную окружения:

```powershell
$env:DB_PASSWORD="your_mysql_password"
```

## Запуск

```powershell
mvn spring-boot:run
```

Если Maven доступен только из IntelliJ IDEA, можно запускать главный класс:

```text
com.serg.SpringMvcCrudApplication
```

После запуска открыть:

```text
http://localhost:8080
```

## Проверка

1. Войти как `admin / admin`.
2. Убедиться, что открылся `/admin`.
3. Проверить CRUD пользователей.
4. Выйти через `Logout`.
5. Войти как `user / user`.
6. Убедиться, что открылся `/user`.
7. Попробовать открыть `/admin` под обычным пользователем: должен быть отказ доступа.

Автор: https://github.com/acro3grey-rgb
