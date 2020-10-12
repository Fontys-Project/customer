default: compile build

compile:
	mvn clean install package

build:
	docker-compose build

test:
	mvn test

run:
	docker-compose up -d

stop:
	docker-compose down