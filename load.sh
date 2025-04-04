#!/bin/bash

counter=1  # Начальное значение счетчика

while true; do
  # Отправляем текущее значение счетчика
  curl -s "http://localhost:8090/receive/$counter"
  echo ""
  # Увеличиваем счетчик на 1
  ((counter++))

  # Ждем 1 секунду
  sleep 1
done
