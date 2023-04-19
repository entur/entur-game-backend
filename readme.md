# Norgestur-backend
Backend for Norgestur

## Database startup
```
docker run --name norgestur-db -p 5432:5432 -e POSTGRES_DB=norgestur-db -e POSTGRES_PASSWORD=entur123 -d postgres:15.2-alpine
```
