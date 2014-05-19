polexchange
===========

an attempt at a trading platform

### instructions

to make it ever so slightly operational:

#### dependencies

```
sudo apt-get install maven
sudo apt-get install rabbitmq-server
```

#### run things

* `mvn clean install` to satisfy dependencies
* from `main` module run `com.pucilowski.exchange.main.util.PopulateSchema`, kill when populated
* from `main` module run `com.pucilowski.exchange.main.util.Cli`
* from `matcher` module run `com.pucilowski.exchange.matcher.Matcher`
* type following into Cli instance to submit orders
	* `bid <price> <volume>`
	* `ask <price> <volume>`
* for results
    * api:
        * http://localhost:8080/api/markets/
        * http://localhost:8080/api/markets/ltc/btc/bids/
        * http://localhost:8080/api/markets/ltc/btc/asks/
        * http://localhost:8080/api/markets/ltc/btc/history/
    * database entries:
        * store.script in working dir