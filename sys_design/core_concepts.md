# Core Concepts

## Scaling
- Horizontal
  - More machines
  - Usually requires a Load Balancer
  - When scaling, we are forced to handle distribution of work, data, and state
  - Consider how to distribute work. Modern system uses Consistent Hashing
- Vertical
  - More resources

### Work Distribution
- For Horizontal Scaling
- How to get the work to the right machine
- Often use Load Balancers with different strategies
  - Least Connections
  - Utilization based
  - Round Robin (usually enough)
  - Queuing System for async jobs
- Keep load on the system as even as possible

### Data distribution
You'll also need to consider how to distribute data across the system.
- For some systems, this implies keeping data in-memory on the node that's processing the request.
- More frequently, this implies keeping data in a database that's shared across all nodes. 
- Look for ways to partition your data such that a single node can access the data it needs without needing to talk to another node. 
- If you do need to talk to other nodes (a concept known as "fan-out"), keep the number small. 
- A common antipattern is to have requests which fan out to many different nodes and then the results are all gathered together. 
  - This "scatter gather" pattern can be problematic because it can lead to a lot of network traffic, is sensitive to failures in each connection, and suffers from tail latency issues if the final result is dependent on every response.
- If your system design problem involves geography, there's a good chance you have the option to partition by some sort of REGION_ID. 
- For many systems that involve physical locations, this is a great way to scale because for many problems a given user will only be concerned with data in or around a particular location (e.g. a user in the US doesn't need to know about data in Europe). 
- Inherently, horizontal scaling on data introduces synchronization challenges. 
  - You're either reading and writing data to a shared database which is a network hop away (≈ 1-10ms, ideally) or you're keeping multiple redundant copies across each of your servers. 
  - This means race conditions and consistency challenges! Most database systems are built to resolve some of these problems directly (e.g. by using transactions). 
  - In other cases, you may need to use a Distributed Lock. Regardless, you'll need to be prepared to discuss how you're going to keep your data Consistent.

## CAP Theorem
- Consistency
- Availability (default)
- Partition Tolerance
- You can choose up to 2 of the options to build a system
- In practice Network partitions are unavoidable this means choosing between consistency and availability
- Availability should be default choice.
- Strong consistency is needed in systems where reading stale/obsolete data is unacceptable

- Choosing consistency means that all nodes will see the same data at the same time
  - When a write operation occurs all subsequent reads will return that value 
  - In network partitions some nodes may become unavailable to maintain consistency
  
- Adopting availability means that every request will receive a response even on network partitions
  - Tradeoff is that different nodes may temporarily have different versions of the data leading to inconsistency. Note that eventually the system should sync

- Examples strong consistency
  - Inventory management: stock levels need to be precisely tracked to avoid overselling
  - Booking systems for limited resources: to prevent double-booking
  - Banking systems where the balance of an account mus be consistent across all nodes to prevent fraud

## Locking
- The process of ensuring that only one client can access a shared resource at a time i.e. a shared counter for inventory.
- Locks can happen at every scale, OS kernel, locks in app, locks in DB, distributed locks
- Important to enforce correctness of our system but a hit on performance

### Granularity
- We want to lock as little as possible i.e. when updating a user profile, we want to lock only the user, not the user table

### Duration of the lock
- We want to lock for as short time as possible. Lock only for the time of the update, not for the entire request

### Lock Bypass
- Employ an optimistic concurrency control strategy. We assume that we can do the work without locking and then check if we were right. in short, perform a compare and swap operation (expand)


## Indexing
- Generally we systems can tolerate slow writes but not slow reads.
- The process of creating data structure that makes read faster.
- Most basic indexing is a hashtable
- Another indexing technique is order the indexes and do a binary search, so the search is in log(n) time

### Indexing in DB
- Most DB allow to create indexes on any column or group of columns.
- DynamoDB allow you to create many secondary indexes
- Redis needs additional steps to design and implement index strategies

#### Geospatial indexes
- used to index location data. i.e. find the closest restaurant or the nearest gas station

#### Vector databases
- used to index high-dimensional data. i.e. find similar images or similar documents

#### Full text indexes
- used for things like search for documents or search for tweets

#### Specialized indexes
- Elastic Search (elaborate)
- Change Data Capture (CDC, elaborate)

## Communication Protocols

### Internal Application protocols
- HTTPS for APIs with simple request and response
  - Each request is stateless so system can scale horizontally with a Load Balancer
  - Make sure that service is not assuming dependencies on the state of the client i.e.sessions
  
- Long Polling, the client makes a request to the server and the server holds the request open until it has new data to send to the client
  - Once the data is sent, the client makes another request and the process repeats, no extra infra needed

- Websockets are used for bidirectional communication between the client and the server
  - challenging for load balancers and firewalls
  - a common pattern is to use a message broker to handle the communication between 
  - this helps to not have long connections to every service in backend

- SSE (Single Sent Events), great way to send updates from the server to the client
  - similar to long polling
  - more efficient for unidirectional communication, server to client
  - SSE allows the server to push updates to the client whenever data is available without the client having to make repeated request
  - achieved via a single long-loved http connection
  - easier to implement and integrate into existing HTTP infrastructure like load balancers and firewalls

- gRPC
  - (elaborate)

## Security

### Authentication / Authorization
- Delegate this work to either an API Gateway or a dedicated service like AuthO

### Encryption
- include data in transit (via protocol encryption) and the data at rest (storage encryption)
- https is the ssl/tls that encrypts data in transit and is the standard for web traffic
- for specific gRPC it supports ssl/tls out of the box
- be sure to use database that supports encryption or encrypt the data before storing it
- for sensitive data is a common pattern that end-user control the keys

### Data protection
- ensure the data is protected from unauthorized access, use, or disclosure
- PI data can be exposed through an endpoint, having some sort of rate limiting or request throttling is a good idea

## Monitoring

### Infrastructure Monitoring
- Health and performance of infrastructure 
  - CPU Usage, memory, disk, network
  - Tools: Datadog or New Relic

### Service-Level Monitoring
- Request latency, error rates and throughput

### Application-Level Monitoring
- number of users, number of active sessions, number of active connections
- could also include key business metrics for the business

## Consistent Hashing

- Arranges data and machines in a circular space called hash ring allowing to add/remove machines with minimal data distributions

