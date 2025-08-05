# Ludito Test Project

Документация API: автоматически генерируется через Swagger по адресу `/swagger-ui.html`.

## Быстрый старт через Docker Compose

### Предварительные требования

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Шаги запуска

1. Клонируйте репозиторий:
   ```sh
   git clone https://github.com/KhodjaevMardon/ludito-test-project.git
   cd ludito-test-project
   ```

2. Соберите и запустите сервисы:
   ```sh
   docker-compose up --build
   ```

3. После запуска:
    - API будет доступен на [http://localhost:8888](http://localhost:8888)
    - Swagger UI: [http://localhost:8888/swagger-ui.html](http://localhost:8888/swagger-ui.html)
    - База данных PostgreSQL: `localhost:5432`, пользователь/пароль: `postgres`

### Остановка сервисов

```sh
docker-compose down
```

## Переменные окружения

- `DB_HOST=db`
- `DB_PORT=5432`
- `DB_NAME=postgres`
- `DB_USER=postgres`
- `DB_PASSWORD=postgres`

---

## Контакты

- Автор: Mardon Khodjaev
- Email: mkhodjaev02@gmail.com
- Telegram: [@mardonkhodjaev](https://t.me/mardonkhodjaev)

---