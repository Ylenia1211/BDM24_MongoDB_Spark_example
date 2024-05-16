# MongoDB and mongo-express Docker Compose Setup

This repository contains a Docker Compose configuration to quickly set up a MongoDB instance and mongo-express, a web-based MongoDB admin interface. 

## Prerequisites

Make sure you have Docker and Docker Compose installed on your system. If not, you can install them from the official Docker website:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Usage

To use this setup, follow these steps:

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/Ylenia1211/BDM24_spark_example.git
    ```

2. Navigate to the cloned repository directory:

    ```bash
    cd your-repository
    ```

3. Start the services using Docker Compose:

    ```bash
    docker-compose up 
    ```

The services will now start in detached mode, and you can access mongo-express at `http://localhost:8081` in your web browser. Use `root` as the username and `example` as the password to log in.

## Configuration

The `docker-compose.yml` file defines two services:

- **mongo**: MongoDB instance with the specified root credentials.
- **mongo-express**: Web-based MongoDB admin interface connected to the MongoDB instance.

## Credentials

- **Username**: root
- **Password**: example

You can change the credentials by modifying the `.env` file and restarting the services.

## Ports

- **mongo-express**: Accessible at `http://localhost:8081`. You can change the host port in the `docker-compose.yml` file if needed.

## Notes

- This setup is intended for development and testing purposes. Make sure to secure your MongoDB instance appropriately before deploying it in a production environment.
- Ensure that the provided credentials are strong and not used in production environments without proper security measures.



## Notes to use **src/main/java/MongoCRUDExample.java**
# MongoDB CRUD Operations Example

This Java program demonstrates basic CRUD (Create, Read, Update, Delete) operations using the MongoDB Java driver.

Make sure you have the MongoDB Java driver added to your project dependencies.

- Make sure your MongoDB instance is running and accessible.
- Customize the connection string (`uri`) according to your MongoDB setup.

  # Spark MongoDB Connector Example

This Java program demonstrates how to use the Apache Spark MongoDB Connector to interact with MongoDB data in a Spark application.

## Prerequisites

Ensure you have Apache Spark installed and configured on your system. Also, make sure you have the MongoDB Java driver and the Spark MongoDB Connector added to your project dependencies.

## Usage

1. Import necessary libraries:

    ```java
    import org.apache.spark.api.java.JavaRDD;
    import org.apache.spark.api.java.JavaSparkContext;
    import org.apache.spark.sql.SparkSession;
    import org.bson.Document;
    import com.mongodb.spark.MongoSpark;
    ```

2. Define the main class `SparkMongoDBExample` and its `main` method.

3. Inside the `main` method, create a Spark session:

    ```java
    SparkSession spark = SparkSession.builder()
        .appName("MongoDBSparkConnectorExample")
        .master("local[*]")  // Set the master URL
        .config("spark.mongodb.input.uri","mongodb://root:example@localhost:27017/db_spark.collection1?authSource=admin")
        .config("spark.mongodb.output.uri", "mongodb://root:example@localhost:27017/db_spark.collection1?authSource=admin")
        .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.12:10.0.0")
        .getOrCreate();
    ```

    - Customize the connection URI according to your MongoDB setup.

4. Create a Spark context:

    ```java
    JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
    ```

5. Read data from MongoDB collection:

    ```java
    JavaRDD<Document> rdd = MongoSpark.load(jsc);
    ```

6. Perform operations on the data, for example, displaying the first 10 documents and counting the total number of documents:

    ```java
    rdd.take(10).forEach(System.out::println);
    long count = rdd.count();
    System.out.println("Number of documents in the collection: " + count);
    ```

7. Close the Spark session and context:

    ```java
    jsc.close();
    spark.stop();
    ```

## Notes

- Ensure your MongoDB instance is running and accessible.
- Customize the connection URI (`spark.mongodb.input.uri` and `spark.mongodb.output.uri`) according to your MongoDB setup.
- Replace `db_spark` with the name of your database and `collection1` with the name of your collection.
- This example assumes a local Spark setup (`master("local[*]")`). Adjust the master URL accordingly for a cluster setup.
