# SpringBootAIAgent
Java SpringBoot web service that shows how to connect to multiple AI model providers. 
Currently it connects to both OpenAi and GoogleGenAi.

## Build
Web Service can be build via Maven.
```
mvn clean package
```

## Clean logs
This Web Service creates logs to show how long each call takes.  You can clean all logs in the "logs" directory via maven
```
mvn clean
```

## Running 
To run the web servicve local you can run via maven or ide.
```
mvn spring-boot:run
```
## GET and Post methods
http
GET /hello/<name>
GET /openAiChat
POST /askOpenAI
GET /googleAiChat
POST/askGoogleAi

## You will need to bring your own api keys.
### Set system environment variables for your keys.
### Set "OPEN_AI_API_KEY" envirnmnet variable to your api key.
### Set "GOOGLE_GENAI_API_KEY" environment variable to your api key.

Play away.
