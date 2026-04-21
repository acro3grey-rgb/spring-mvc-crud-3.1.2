# Spring Boot MVC CRUD Application 3.1.1

CRUD-приложение на Spring Boot MVC с использованием Hibernate (JPA), Thymeleaf и MySQL.

Реализовано:
- просмотр пользователей
- добавление
- редактирование
- удаление

Технологии:
Java 17, Spring Boot, Spring MVC, Hibernate (EntityManager), MySQL, Thymeleaf, Maven.

Конфигурация выполнена через Spring Boot auto-configuration и `application.properties`.
Приложение запускается со встроенным web-сервером.

Запуск:

```text
mvn spring-boot:run
```

После запуска открыть:

```text
http://localhost:8080/users
```

Перед запуском должна быть доступна MySQL-база `testdb`.
При необходимости параметры подключения можно задать через переменные окружения:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Автор: https://github.com/acro3grey-rgb
