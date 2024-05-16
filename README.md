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
