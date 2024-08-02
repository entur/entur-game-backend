# Norgestur-backend
Welcome to the Norgestur project! This is the 3rd version of the game, developed by summer interns in 2024. This game aims to provide an engaging and fun way to explore public transportation in Norway. Below you will find information on how to set up, run, and contribute to the project. This is the backend of our project and works together with [entur-game-frontend](https://github.com/entur/entur-game-frontend).

## Developers
### 2024 Summer Team
- [Magnus](https://github.com/Magnus-Farstad)
- [Marianne](https://github.com/maribsta)
- [Oscar](https://github.com/oscarahalvorsen)
- [Selma](https://github.com/selmagudmundsen)
- [Tomas](https://github.com/tomaswedege)

### Previous Developers
- [Artur](https://github.com/apkrauze)
- [Kenneth](https://github.com/kennetng)
- [Daniel](https://github.com/Daniel-Jansson)


## Application scripts

### Docker
To run this project you need do have docker and run the script in the script tag.
Note that if changes are made to the script below for either database name or postgres_password you also need to enter application-local and change the values for the respective fields.
```
docker run --name norgestur-db -p 5432:5432 -e POSTGRES_DB=norgestur-db -e POSTGRES_PASSWORD=entur123 -d postgres:15.2-alpine
```

### Flyway
The application used flyway to initiate the postgres patches. To ensure that patches are run, remember to write them in chronological order for version and two '_' before you add the name of the file, I.E:
- V1.0__this_is_my_first_patch.sql
- V1.1__this_is_my_second_patch.sql
