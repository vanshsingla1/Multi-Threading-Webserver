# Multithreaded TCP Web Server

A concurrent TCP server built in Java using `ServerSocket`, `Socket`, and `ExecutorService`. The server uses a fixed-size thread pool to handle multiple client connections efficiently without creating a new thread for every request.

## Features

* Concurrent client handling using thread pools
* TCP socket-based communication
* Fixed-size thread pool with `ExecutorService`
* Efficient thread reuse and task scheduling
* Load tested using Apache JMeter
* Connection logging and request processing

## Technologies Used

* Java
* Java Networking (`ServerSocket`, `Socket`)
* Java Concurrency (`ExecutorService`, Thread Pools)
* Apache JMeter

## Project Structure

```text
src/
 ├── Server.java
 ├── Client.java
 └── ThreadPool/
      └── Server.java
```

## How It Works

1. The server listens for incoming client connections on a specified port.
2. When a client connects, the request is submitted to a fixed-size thread pool.
3. An available worker thread processes the request.
4. The response is sent back to the client.
5. The thread returns to the pool and becomes available for future requests.

## Running the Project

### Start the Server

```bash
javac Server.java
java Server
```

### Start the Client

```bash
javac Client.java
java Client
```

## Load Testing

Apache JMeter was used to simulate multiple concurrent users and evaluate server performance under load. The thread-pool implementation demonstrated efficient handling of concurrent connections while avoiding excessive thread creation overhead.

## Key Concepts Demonstrated

* TCP/IP Networking
* Socket Programming
* Multithreading
* Thread Pools
* ExecutorService
* Client-Server Architecture
* Concurrent Request Handling
* Performance Testing

## Future Improvements

* HTTP request parsing
* GET and POST request support
* Request logging
* Graceful server shutdown
* Static file serving
* Monitoring and performance metrics

## Author

Vansh Singla
