polexchange
===========

A work in progress trading platform. The goal is to create a platform and let users trade various currency pairs by
posting bid/ask offers to an orderbook, similar to forex markets and stock exchanges.

The Matcher, responsible for determining when a trade can be struck is a discrete component. Multiple matchers are ran, each one given responsibility of its own market. Communication between the matcher and world-API is done through RabbitMQ.

### instructions

in order to get some operation out of it

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
