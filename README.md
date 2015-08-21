optimistic-pessimistic
======================

Spring based Web Application.  
Users could transfer money from account to account.  
Goal - keep DB consistent.

This example contains 2 possible solutions:
* Optimistic concurency control
* Isolation and PESSIMISTIC_WRITE locking

[![Build Status](https://travis-ci.org/WonderBeat/optimistic-pessimistic.svg?branch=master)](https://travis-ci.org/WonderBeat/optimistic-pessimistic)

Suggestion
=======================

* Pessimistic locking: A user who reads a record, with the intention of updating it, places an exclusive lock on the record to prevent other users from manipulating it. This means no one else can manipulate that record until the user releases the lock. The downside is that users can be locked out for a very long time, thereby slowing the overall system response and causing frustration.
* Where to use pessimistic locking: This is mainly used in environments where data-contention (the degree of users request to the database system at any one time) is heavy; where the cost of protecting data through locks is less than the cost of rolling back transactions if concurrency conflicts occur. Pessimistic concurrency is best implemented when lock times will be short, as in programmatic processing of records. Pessimistic concurrency requires a persistent connection to the database and is not a scalable option when users are interacting with data, because records might be locked for relatively large periods of time. **It is not appropriate for use in web application development.**