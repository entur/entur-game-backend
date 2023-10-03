# Norgestur-backend
This backend was developed from scratch during a hackathon by [@Daniel](https://github.com/Daniel-Jansson), [@Kenneth](https://github.com/kennetng) and [@Artur](https://github.com/apkrauze).

This project will receive a players game stats from [entur-game-frontend](https://github.com/entur/entur-game-frontend).
To run this project you need do have docker and run the script in the script tag.

## Application scripts

### Docker
Note that if changes are made to the script below for either database name or postgres_password you also need to enter application-local and change the values for the respective fields.
```
docker run --name norgestur-db -p 5432:5432 -e POSTGRES_DB=norgestur-db -e POSTGRES_PASSWORD=entur123 -d postgres:15.2-alpine
```

### Flyway
The application used flyway to initiate the postgres patches. To ensure that patches are run, remember to write them in chronological order for version and two '_' before you add the name of the file, I.E:
- V1.0__this_is_my_first_patch.sql
- V1.1__this_is_my_second_patch.sql
