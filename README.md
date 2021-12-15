# **Cryptocurrency Tracker**
It is an application for tracking cryptocurrencies data.

### **Technologies**
- Java 8
- Spring Boot Framework
- H2 DB
- Docker
- Junit
- Mockito
- Swagger UI

### **Features**

- Coincap public api(api.coincap.io/) is used to track any cryptocurrency.
- As a cryptocurrency, only FUN token is chosen. The main reason to choose is that The FUN Token is an asset developed specifically for the online gambling and gaming industry.
- Coincap Api is called once in 10 seconds and it is stored to H2 DB.
- By requesting "/cryptocurrencies" Api, the last 10 entries can be seen.


### **Run Application**

Please run the docker commands below:
```
1. cd {project-path}
2. docker build -t task .
3. docker run -p 8080:8080 task
```
After executing the commands, cryptocurrency tracker application and H2 DB get ready to use.

### **API Documentation**

For API documentation, please see  at http://localhost:8080/swagger-ui.html

Path: /cryptocurrencies

Response:

```json
[
    {
        "createdDate": "2021-04-23 16:48:08",
        "value": {
            "id": "funfair",
            "type": "token",
            "rank": 101,
            "symbol": "FUN",
            "marketCapUsd": 287744511,
            "priceUsd": 0.027939,
            "changePercent24Hr": -17.42,
            "explorer": "https://etherscan.io/token/FunFair"
        }
    },
    {
        "createdDate": "2021-04-23 16:47:58",
        "value": {
            "id": "funfair",
            "type": "token",
            "rank": 101,
            "symbol": "FUN",
            "marketCapUsd": 287744511,
            "priceUsd": 0.027939,
            "changePercent24Hr": -17.42,
            "explorer": "https://etherscan.io/token/FunFair"
        }
    }
]
```

