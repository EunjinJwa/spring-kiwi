### Database Setting
mariadb - docker container 사용

```
# mariadb image download
docker pull mariadb

# docker mariadb 컨테이너 생성 및 실행
docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mariadb

# docker mariadb 컨테이너 접속
docker exec -it mariadb /bin/bash

# database 생성
mysql -u root -p
create database kiwi
```

### 참고 
docker container 명령어
```
docker ps -a 
docker stop [container]
docker start [container]
```

### Spring actuator
기본 endpoint : /actuator
참고문서 : https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html