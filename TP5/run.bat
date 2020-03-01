java -jar C:\webservices\spring-boot-client-mvc\target\spring-boot-client-mvc-0.0.1-SNAPSHOT.war --server.port=9191

rem pour configurer démarrer Tomcat au port 9090
rem java -jar C:\webservices\resttp1\target\rest-0.0.1-SNAPSHOT.jar --server.port=9090

rem pour lancer l'application avec un fichier de configuration externe.
remjava -jar -Dspring.config.location=C:\webservices\application-prod.properties C:\webservices\resttp1\target\rest-0.0.1-SNAPSHOT.jar


rem Spring Boot Active Profile (flag : --spring.profiles.active):

rem pour lancer l'application avec le profile prod
rem java -jar C:\webservices\resttp1\target\rest-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

rem pour lancer l'application avec le profile dev
rem java -jar C:\webservices\resttp1\target\rest-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev