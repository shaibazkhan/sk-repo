Since there is no "transfer money" verb, I've define a "transaction" resource that can be acted upon with CRUD. Idea is client will create
an *empty* transaction and then *update* transaction with all data, implicitly committing it.

Following needs to be added:
* Check must be added to ensure if transaction has been *PUT* before server responds with error otherwise *OK*.
* Funds transfer should happen within a `Transaction` so that state of transactions and accounts can be updated in a atomic way. 
  Currently everything is in-memory.
* More rigorous testing is required to check whether transfer-of-funds between accounts can handle load.
* Sending notifications in a separate thread so that call to send notification is not blocked.
* Probably taking out `transferFundsBetweenAccounts` test from `AccountsServiceTest` and adding it to separate stress test so that unit tests are quicker.