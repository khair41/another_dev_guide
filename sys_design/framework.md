# Framework

## Requirements
- Should usually take 5 min. Separate in:
  - Functional Requirements
    - Does the System needs to do X?
    - What would happen if Y?
  - Non functional Requirements
    - The system should be able to...
    - The system should be
    - Challenging when not familiar with domain but can fall into this categories
      - CAP Theorem (prioritize consistency or availability)
        - Just can use 2 out of 3
          - Consistency
          - Availability
          - Partition Tolerance
        
      - Environment Constraints (mobile/web)
      - Scalability (seasonal or time frame peaks)
      - Latency (how quickly should we respond)
      - Durability (how important is that the data is not lost, banking vs social)
      - Fault Tolerance (how well handle failures i.e. redundancy, failover and recovery)
      - Compliance (legal or regulatory reqs)
  - Capacity estimation
    - https://www.hellointerview.com/blog/mastering-estimation

## Core Entities 
- Take around 2 min
  - Who are the actors in the system? Are they overlapping?
  - What are the nouns or resources necessary for the Functional Requirements?

## API or Interface
- Define contract between system and users
- Mapped directly the functional requirements already defined

Options:
- REST (default choice)
    - GET POST PUT DELETE to perform CRUD operations on resources
- GraphQL
  - Allows client to specify exactly what data they want to receive
  - Helpful to avoid over/under fetching communication 
  - Use for internal APIs when performance is critical
- RPC 
  - Action-oriented protocol (like gRPC) that's faster than REST for service-to-service communication.
  - Use for internal APIs when performance is critical.
  
```
POST /v1/tweets
body: {
    "text": string
}

GET /v1/tweets/{tweetId} -> Tweet

POST /v1/users/{userId}/follows

GET /v1/feed -> Tweet[]
```

## Data Flow (optional)
- Use this step when there is a long set of steps to accomplish certain action
- Helpful to describe the high level sequence of actions or processes that system performs on the inputs or process
- For a web crawler, this might look like:
  1. Fetch seed URLs
  2. Parse HTML
  3. Extract URLs
  4. Store data
  5. Repeat

## High-level design



## Deep Dives


