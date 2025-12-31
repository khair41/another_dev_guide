# Databases

## Relational databases
- Often use for transactional data i.e. records, order records
- composed by rows and columns
- SQL Joins: a way to combine data from multiple tables
### Indexes: storing data for faster queries
  - usually implemented using a B-Tree or a HashTable 
### RDBMS transactions: a way of grouping multiple operations together into a single atomic operation
  - i.e. a users and posts table, we might want to create a new user and a new post for that user at the same time
  - either both operations succeed or both fail
### most common relational databases
  - Postgres (expand)
  - MySQL (expand)

## NoSQL databases
### strong candidates for situations like:
- Flexible data models:
  - data model is evolving or you need to store different types of data structures without a flexible schema
- scalability:
  - your application needs to scale horizontally to accommodate large amounts of data or high user loads
- handling big data and realtime web apps
  - application dealing with large volumes of data, unstructured data, or real-time data processing and analytics

### Things to know:
#### Data models:
- Key-value
  - Fast access
  - Simple model

- document
  - flexible
  - schema-less

- column-family
  - scalable
  - high performance for write

- graph format
  - relationships
  - efficient retrieval

#### Consistency Models:
- offer various consistency models from strong to eventual
  - strong consistency ensures all nodes in the system have the same data at the same time
  - eventually consistency ensures that all nodes will eventually have the same data

#### Indexing:
- Most common types of indexes are B-Tree and Hash Tables indexes

#### Scalability:
- Scale horizontally by using consistent hashing and/or sharding to distribute across many servers

### Most common NoSQL databases
- dynamodb (expand)
- cassandra (expand)
- mongodb (expand)

## Blob storage
- Use when storing large blobs like images, videos or other files
- Expensive to store large blobs in a traditional database
- Blob storage often work with CDNs
- Upload a file/blob to blob storage which will act as your origin and then use a CDN to cache the file/blob in edge locations around the world
### Things to know
  - Durable
  - Scalability
  - Cost Effective
  - Secure
  - Upload/Download directly from client
  - Chunking

## Search Optimized Database
- Inverted Indexes
- Tokenization
- Stemming
- Fuzzy search
- Scaling

## API Gateway
- Sits in front of the system
- Responsible for routing incoming requests to the respective backend service
- Handles:
  - authentication
  - rate limiting
  - logging

## Load Balancer
- Helps distribute traffic when facing horizontal scaling
- Always used when there are different machines that handling the same request
- L4 LB for persistent connections like websockets
- L7 LB offers flexibility in routing traffic while minimizing the connection load downstream

## Queue
- Serves as buffer for busy traffic
- A source sends a message to a queue, forgets about it, on the other end a pool of workers process the messages 
- Function is to smooth out the load on the system
- If a spike of 1000 requests come but only can handle 200 requests per second, 800 request will wait in queue
- Queue also decouple from producer and consumer

### Common use cases 
- Buffer for Bursty Traffic
- Distribute rok across a system

### Things to know
- Message Ordering
- Retry Mechanism
- Dead Letter Queues
- Scaling with Partitions
- Backpressure

## Streams / Event Sourcing
- Can retain data for a configurable period of time
- Used when processing vast amounts of data in real-time

### Use when
- Need to process large amounts of data in real-time
- Support complex scenarios like event sourcing
- Support multiple consumer reading from the same stream

### Thinks to know
- Scaling and partitioning 
- Multiple Consumer groups
- Replication
- Windowing

## Distributed Lock
- When we need a way to lock a resource
- Traditional DB with ACID properties use transaction locks to keep data consistent, this works at DB level but not designed for longer-term locking
- Perfect for situations where you need to lock something across different systems or processes for a reasonable periods of time
- Uses a distributed key-value store to store a lock and then use the atomicity of the key-value store

### Common Examples
- E-Commerce checkout system: for high demand items
- Ride-Sharing Matchmaking
- Distributed Cron Jobs: ensures a task is executed by only one server at a time
- Online Auction Bidding System: for the last seconds of the bidding 

### Things to know
- Locking Mechanism
- Lock Expiry
- Locking Granularity
- Deadlocks

## Distributed Cache
- Commonly used to lower system latency
- Its a server or group of servers that stores data in memory
- They store data that is expensive to compute or retrieve from DB

### Use when
- Save Aggregate Metrics
- Reduce Number of DB Queries
- Speed Up Expensive Queries

### Things to know
- Eviction Policy
  - LRU
  - FIFO
  - LFU
- Cache invalidation strategy
- Cache with strategy

## CDN
- Often used to deliver static content
- Also used to deliver dynamic content like API responses
- They work by caching content on  servers that are close to the user
- When the content is not on the server 
- Contains eviction policies like setting a TTL