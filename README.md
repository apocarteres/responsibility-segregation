## What is it?

It is a tiny PoC on how to segregate resource management from business logic. It is a quite common problem when we
do HTTP requests, we need to spawn HTTP client along with executor service and probably something else, but eventually we
can't afford to forget to release those resources otherwise customer very likely end up with dead process in production. 

Things get complicated when we have to deal with several resource pools, such as: 
- db connections
- thread pools
- sockets
- file descriptors 

Java offers some techniques like ``try-with-resources`` and we still can leverage it, but on the other hand:
- it reduces business code readability and hence maintainability
- no guarantee developer didn't forget to release a resource   
- time-consuming team discussions are required to refresh in mind lifecycle of the resource on code review and still 
no guarantee resource is properly managed

## What is proposed? 

The proposal in PoC is to follow [RAII](https://en.wikipedia.org/wiki/Resource_acquisition_is_initialization) idiom.
In the other words we need a guarantee that allocated resources will be released despite on how they were used, 
whether exceptions occurred or not and other factors. 

PoC introduces a term of "context". A "context" is a very limited scope of execution which provides access to some resources.
The following rules are applied on the context:
- particular resources can't exist outside of context
- context holds uniq ownership of allocated resources
- context has responsibility to release those resources  

## Questions?
Always welcome! Just file a new issue for this repo with what you have. The good issue should raise a question 
or counter-example when contextual resource usage won't work or will be inefficient / unsafe / etc.

## How to run?

- make sure you are using JDK11 and ```mvn``` is in your ```PATH```
- build the project with: ```mvn clean package```
- run app ```/java -cp ./target/resource-segregation-1.0-SNAPSHOT.jar com.github.apocarteres.rs.ApplicationLoader```
