docker-build: maven-build
	docker build . -t bankslip-api:latest

docker-run: docker-build
	docker run -p 8090:8080 bankslip-api:latest

maven-build:
	mvn clean install -Dmaven.test.skip=false
