# Countries interview
___

### Run (with Maven)

1. Clone repo from this page (to the place where you want to store it)
```bash
git clone https://github.com/patrykkondrat/countries-interview.git
```

2. If you don't have maven just install it.
We can run this application using maven, so it is necessary to install it beforehand.

```bash
mvn clean install
mvn spring-boot:run
```

3. After running - to open the frontend site, you need to move to this page:
```
http://localhost:8080/static/index.html
```

___
### Run (with Docker)

1. Clone repo from this page (to the place where you want to store it)
```bash
git clone https://github.com/patrykkondrat/countries-interview.git
```

2. If you don't have docker, just install it.
_You need to install maven to get an executable jar file_
```bash
mvn clean install
```
3. Build a docker image
```bash
docker build . -t countries
```
4. Run
```bash
docker run -p 8080:8080 countries
```
5. After running - to open the frontend site, you need to move to this page:
```
http://localhost:8080/static/index.html
```