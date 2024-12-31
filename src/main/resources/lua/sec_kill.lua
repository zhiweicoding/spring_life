-- Lua script for reducing inventory during a lottery draw
-- Keys and arguments:
-- KEYS[1] - Inventory key (e.g., "lottery_inventory")
-- KEYS[2] - User participation record key (e.g., "lottery_users")
-- ARGV[1] - User ID

local inventoryKey = KEYS[1]
local userKey = KEYS[2]
local userId = ARGV[1]

-- Check inventory availability
local stock = tonumber(redis.call("GET", inventoryKey))
if not stock or stock <= 0 then
    return -1  -- Inventory insufficient
end

-- Check if user already participated
if redis.call("SISMEMBER", userKey, userId) == 1 then
    return -2  -- User already participated
end

-- Reduce inventory
redis.call("DECR", inventoryKey)

-- Record user participation
redis.call("SADD", userKey, userId)

return 1  -- Success
