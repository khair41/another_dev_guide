## graphql
  - dataloader (n+1 query problem)
  - can secure access to resource level
    - check permissions field by field

## rpc
  - best-used-for/efficient service to service communication
  - almost as if we were calling methods but through a network
  - direct function call using binary formats (protocol buffers)
  - faster because there is less data to send
   


## Follow ups
- pagination
    - include page, limit (GET /events?page=1&limit=10)
    - it is possible that there are multiple inserts happening between gets when using pagination will return unorder/repeated elements. for this case we use a cursor that tells whats the last id that we fetched (GET /events?cursor=100&limit=10)
- security
  - non formal syntax to use 
    ```
        @auth:admin
        POST /events
    ```
    - use JWT
      - encodes user data an permissions into a signed token
  - session token
    - a random token which is used to find user info server side

