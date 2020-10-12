# Customer

The Customer Service os part of Warehouse Management System (WMS+) which adds customer registration & management 
related functionalities.

## Building

The below commands can be used to build the software.
Using Make:
```bash
make
```

Or directly using Maven:
```bash
mvn clean package install
```

## Usage

To start the service, either start everything through Docker compose using Make:
```bash
make run
```

Or directly through Docker compose:
```bash
docker-compose up -d --build
```

Or if you run the dependencies (such as the database) elsewhere or through a different method, then you can start 
the application directly with:
```basl
java -jar ./api/target/api-0.1.0-INDEV.jar
```