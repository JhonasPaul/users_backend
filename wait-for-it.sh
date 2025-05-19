#!/bin/bash
# wait-for-it.sh

host="$1"
port="$2"
shift 2
# Espera hasta que el puerto del host esté disponible
until nc -z "$host" "$port"; do
  echo "Esperando $host:$port para estar disponible..."
  sleep 2
done
echo "$host:$port está disponible"
exec "$@"