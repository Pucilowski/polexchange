API
---

RESTful HTTP API

* `/api/markets/`
    * `GET` returns tradable currency pair markets
    * `<base>/<counter>/`
        * `GET bids/` returns bid orders
        * `GET asks/` returns ask orders
        * `GET history/` return trade history
* `/api/user/`        
    * `orders/`
        * `GET` returns user's orders
        * `POST` requests order to be submitted
        * `DELETE <id>/` requests order to be cancelled
    * `trades/`
        * `GET` returns user's trade history
            
market activity websocket endpoints

* `/ws`
    * `/orders`
        * `/opened`
        * `/closed`
    * `/trades`

Entities
--------

### market

* base (what is being traded)
* counter (what it is being traded in terms of)

### order

* id
* user
* market
* side (buy / sell)
* price
* quantity
* remaining
* status
    * opening, open, cancelling, cancelled, fulfilled
* opened (when)
* closed (when cancelled or fulfilled)

should there also be an open order entity?

### trade

* id
* market
* side (market buy / sell)
* bid order
* ask order
* price
* quantity
* executed (when)

Processes
---------

### lifecycle of an order

* user sends order submission request to restful controller
* atomic (@Transactional)
    * order is persisted as a pending order
    * order is pushed to broker queue corresponding to the currency pair
* matcher
    * pulls new orders, stores in memory
    * upon a match a trade is struck and pushed to broker
* atomic (@Transactional)
    * trade is pulled from broker
    * trade is persisted
    * relevant pair of orders is updated
        * remaining volume to be traded is updated
        * if order is complete then status is changed to closed
        * order is persisted
    
### cancellation of an order

* user sends order cancellation request to restful controller
* atomic (@Transactional)
    * order status is changed to pending cancellation and persisted
    * order cancellation is pushed to broker queue corresponding to the currency pair
* matcher
    * pulls cancellation request
    * if possible removes corresponding order from memory
    * pushes cancellation confirmation to broker
* atomic (@Transactional)
    * pulls order cancellation request
    * relevant order's status is set to cancelled and persisted