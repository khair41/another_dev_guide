- identify the db to use
Default to choose PostgreSQL

 
 
## Relational Database
  - create relations via Primary Key, Foreign Key

## Document Database
  - store data as json with flexible schemas 
  - main reason to use is when supporting flexible schema

## Key-Value Store
- simply store values and retrieve a key
- extremely fast
- but cant do complex queries
- like a giant hashmap

## Graph Database
- normally not used
- try to avoid

# Schema Design
- Data Volume
  - where can data live
  - single db v distributed

- Access patterns
  - drives indexes
  - structure

- Consistency requirements 
  - ACID vs eventual consistency

- Denormalize data can live temporarily in cache then convert to accurate model (eventual consistency)

- Indexing for Access Patterns
  - B-Tree


# Scaling and Sharding
- Choose the right partition strategy


# Wrapping up
- Choose a DB
- Outline columns
- Add primary and foreign keys
- Determine what indexes I need
- Determine whether to denormalize
- Consider if sharding is needed, if yes, chose a partition key

(All in context of access pattern, data volume and consistency requirements)

