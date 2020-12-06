# roche-products

roche-products api was created for managing products. REST API end-points; 
- Creating a product 
- List all products 
- Get one product 
- Update existing product
- Delete a product (soft delete)

## Technology stack
* The project uses `OpenJDK 11`, you have to install it first on your local machine if you want to develop the API.
* The API is implemented via `Spring Boot` - `2.3.5.RELEASE`.
* Using `spring-data-jpa`. 
* `Postgres` is used for persisting data.
* `Lombok` has been used to remedy some boiler plate coding
* The project build tool is `Gradle`. 

## Running locally
* Clone project to local machine and open it in your favorite editor, and Run main spring-boot class `RocheApplication`
OR
* Install `Docker`. locally and Once you have it, execute the following commands in the project root folder:
    - `./gradlew clean && ./gradlew build --info`
    - `docker build -t roche-products:latest .`
    - `docker-compose up`

* Docker will start containers for Roche-products and DB. 
The API port exposed to 8080 of your localhost.

##  Api documentation 
* Swagger documentation can be found here:
- `localhost:8080/swagger-ui.html`

## Testing
* API endpoints integration/component tests are inside project.

## Info
* App info via standard actuator here: 
- `http://localhost:8080/actuator`


