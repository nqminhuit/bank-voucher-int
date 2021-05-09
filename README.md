# Diagram
![flow diagram svg](flow-diagram.svg "flow diagram")

## Flow request for new voucher code (starts from 0-8):
(0): client requests to get new voucher code

(1): Gateway pushes a message to kafka server

(1.1): message pushed success

(1.2): response to client that message is being processed within 30s

(2): voucher integration service receives message request

(3): send api request to Voucher Provider Server (VPS) to get voucher code.

(4): VPS response with a message: "the request is being processed within 30 seconds".

(4.1): VPS response with the voucher code within 30s.

(4.2): VPS response with the voucher code after 30s.

(5.1): push a message that it has the voucher code and that it should be sent back via web.

(5.2): push a message that it has the voucher code and that it should be sent back via SMS.

(6): Gateway receives CODE then (7) send it to web/SMS

(8): in the meantime, voucher service receives CODE then persist it to DB

## Flow customer requests to get all purchased code by phone number (starts from 10-15):
(10): client request to get all purchased code by phone number

(11): Gateway delegates the request to voucher-service

(12): voucher-service handle request then response result to Gateway

(13): Gateway response result to Client


# Implementation
Kafka topics:
- request-code
- receive-code

## Gateway:
Acts as a backend frontline, receives all requests from Client and delegates request to coresponding service.

Has:
- 1 kafka **producer** to **request-code** topic
- 1 kafka **consumer** to **receive-code** topic

## Voucher integration service:
Subscribes to request-code topic, requests to 3rd party Voucher Provider Server, handle voucher code response from VPS then publish to receive-code topic.

Has:
- 1 kafka **consumer** to **request-code** topic
- 1 kafka **producer** to **receive-code** topic

## Voucher service:
Subscribes to receive-code topic, persists voucher code to DB when receives code; handle request to get all purchased code by phone number.

Has:
- 1 kafka **consumer** to **receive-code** topic

# Development
start zookeeper and kafka servers:
```bash
$ docker-compose up -d
```

# API testing
