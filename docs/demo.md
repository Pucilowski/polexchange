* install rabbitmq-server
* From main module run com.pucilowski.exchange.main.util.PopulateSchema, kill when populated
* From main module run Run com.pucilowski.exchange.main.util.Cli
* Open the following in browser:
	* http://localhost:8080/api/markets/ltc/btc/bids/
	* http://localhost:8080/api/markets/ltc/btc/asks/
	* http://localhost:8080/api/markets/ltc/btc/history/
* From matcher module run com.pucilowski.exchange.matcher.Matcher
* Into Cli instance type:
	* bid <price> <volume>
	* ask <price> <volume>	