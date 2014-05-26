polexchange
===========

Efforts to build an exchange platform. Asset pairs form markets, similar to Forex/ Stock exchanges. Exposes a web API to take orders and offer market snapshots. 'Matchers' take orders and strike trades, each tasked with its own market. RabbitMQ used for internal communication.

Each market features an [Order book](https://en.wikipedia.org/wiki/Order_book_(trading)) to record buyer/seller interest of participants. An order specifies the market it pertains to, whether it is a buy or sell order, quantity and offered price.

Incoming orders are processed sequentially and when prices overlap and an agreement between two parties can be struck a trade is executed.

### Features

* Takes orders
* Persists orders and informs matcher
* Matcher receives ordesr and executes trades
* Updates open orders and persists executed trades

### To-do

* User registration/authentication
* Attribute order submissions to the authenticated user
* Order cancellation requests (if possible)
* User asset balances
* Internal API for for user asset deposits/withdrawals (integration with external systems like fiat banking, Bitcoin)
* Real-time websocket stream of newly posted orders and executed trades
* Web front-end

### Specification

[Specification](https://github.com/Pucilowski/polexchange/blob/master/doc/spec.md) begins to outline the structure and inner workings and intended integration interfaces.

### Instructions

If you wish to witness some operation out of it

```
# system dependencies
sudo apt-get install maven
sudo apt-get install rabbitmq-server
# grab project
git clone https://github.com/Pucilowski/polexchange.git
cd polexchange
mvn clean install
```

#### Run

* from `main` module run `com.pucilowski.exchange.main.util.PopulateSchema`, kill when populated
* from `matcher` module run `com.pucilowski.exchange.matcher.Matcher`
* from `main` module run `com.pucilowski.exchange.main.util.Cli`
	* type following into standard input to submit orders
		* `bid <price> <volume>`
		* `ask <price> <volume>`
* for results
    * api:
        * `http://localhost:8080/api/markets/`
        * `http://localhost:8080/api/markets/ltc/btc/bids/`
        * `http://localhost:8080/api/markets/ltc/btc/asks/`
        * `http://localhost:8080/api/markets/ltc/btc/history/`
    * database entries:
        * store.script in working dir
