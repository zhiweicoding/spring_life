package xyz.zhiwei.spring_life.controller;

import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhiwei.spring_life.lua.LuaScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhiweicoding.xyz
 * @date 24/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@RestController
public class RedisTestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private LuaScriptManager luaScriptManager;

    @GetMapping("/test-redis")
    public String testRedis() {
        // 设置一个键值对
        stringRedisTemplate.opsForValue().set("testKey", "Hello, Redis!");

        // 获取该键的值
        String value = stringRedisTemplate.opsForValue().get("testKey");

        // 返回结果
        return value != null ? "Redis is working: " + value : "Failed to connect to Redis";
    }

    @GetMapping("/lua")
    public String lua(@RequestParam String inventoryKey,
                      @RequestParam String userKey,
                      @RequestParam String userId) {
        try {
            Long result = executeSecKillScript(luaScriptManager.getSha1("sec_kill.lua"), inventoryKey, userKey, userId);
            return switch (result.intValue()) {
                case -1 -> "Inventory insufficient";
                case -2 -> "User already participated";
                case 1 -> "Success: Inventory reduced and user recorded";
                default -> "Unexpected result: " + result;
            };
        } catch (Exception e) {
            return "Error executing Lua script: " + e.getMessage();
        }
    }

    private Long executeSecKillScript(String secKillScriptSha, String inventoryKey, String userKey, String userId) {
        try {
            return redissonClient.getScript().evalSha(
                    RScript.Mode.READ_WRITE,
                    secKillScriptSha,
                    RScript.ReturnType.INTEGER,
                    Arrays.asList(inventoryKey, userKey),
                    userId
            );
        } catch (Exception e) {
            throw new RuntimeException("Error executing Lua script", e);
        }
    }
}
