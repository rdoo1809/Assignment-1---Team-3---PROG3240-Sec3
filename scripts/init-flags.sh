#!/bin/bash

ADMIN_TOKEN="default:development.unleash-insecure-admin-api-token"
UNLEASH_URL="http://localhost:4242/api/admin/projects/default/features"

echo "Waiting for Unleash server to start"
sleep 20

echo "Creating feature flags"

curl -s -X POST $UNLEASH_URL \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "premium-pricing",
    "description": "Enable premium pricing discounts",
    "type": "release"
  }'

curl -s -X POST $UNLEASH_URL \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "order-notifications",
    "description": "Enable order notifications",
    "type": "release"
  }'

curl -s -X POST $UNLEASH_URL \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "bulk-order-discount",
    "description": "Enable bulk order discount",
    "type": "release"
  }'

echo "Feature flags successfully created"