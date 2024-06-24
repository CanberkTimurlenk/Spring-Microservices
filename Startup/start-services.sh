#!/bin/bash

ACTION=$1

if [ "$ACTION" == "up" ]; then
    docker compose -f ../OrderService/docker-compose.yaml -p test-orderservices up -d
    docker compose -f ../ProductService/docker-compose.yaml -p test-productservices up -d
    docker compose -f ../InventoryService/docker-compose.yaml -p test-inventoryservices up -d
    docker compose -f ../ShipmentService/docker-compose.yaml -p test-shipmentservices up -d
    echo "All services are up and running."
elif [ "$ACTION" == "down" ]; then
    docker compose -f ../OrderService/docker-compose.yaml -p test-orderservices down
    docker compose -f ../ProductService/docker-compose.yaml -p test-productservices down
    docker compose -f ../InventoryService/docker-compose.yaml -p test-inventoryservices down
    docker compose -f ../ShipmentService/docker-compose.yaml -p test-shipmentservices down
    echo "All services have been stopped."
else
    echo "Usage: $0 {up|down}"
fi
