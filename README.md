Resource Management Service
Multithreading Project implements a service provider that can provide any number of servers that have 100 Giga of memory (as a maximum)
from the server pool which persisted in Aerospike DB via RESTful API which built using spring boot.
Two threads were implemented to simulate the activation of the created servers and the allocation of the server that makes it possible 
to server simultaneous requests.
