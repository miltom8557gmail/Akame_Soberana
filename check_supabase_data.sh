#!/bin/bash
S_KEY="sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"
URL="https://vzoqzjpvswpwyixqsqnz.supabase.co/rest/v1/"

echo "⚡ Solicitando Metadados do Supabase..."
curl -s -X GET "$URL" \
-H "apikey: $S_KEY" \
-H "Authorization: Bearer $S_KEY" | jq .
