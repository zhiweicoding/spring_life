package xyz.zhiwei.spring_life.lua;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiweicoding.xyz
 * @date 24/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@Slf4j
@Component
public class LuaScriptManager {

    @Autowired
    private RedissonClient redissonClient;

    private final Map<String, String> scriptCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        //获取resources/lua下的所有lua文件，然后加载到redis中
        try {
            Path luaPath = Paths.get("src/main/resources/lua");
            Files.list(luaPath)
                    .filter(path -> path.toString().endsWith(".lua"))
                    .forEach(path -> {
                        try {
                            String fileName = path.getFileName().toString();
                            fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                            loadScript(fileName);
                        } catch (Exception e) {
                            log.error("加载lua脚本失败,path:{}", path);
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            log.error("加载lua脚本失败", e);
        }
    }

    // 加载 Lua 脚本
    public String loadScript(String scriptName) throws Exception {
        if (scriptCache.containsKey(scriptName)) {
            return scriptCache.get(scriptName);
        }

        // 读取脚本文件
        String scriptPath = "src/main/resources/lua/" + scriptName;
        String script = new String(Files.readAllBytes(Paths.get(scriptPath)));

        // 加载到 Redis 并缓存 SHA1
        String sha1 = redissonClient.getScript().scriptLoad(script);
        log.info("Loaded Lua script {} with SHA1 {}", scriptName, sha1);
        scriptCache.put(scriptName, sha1);

        return sha1;
    }

    public String getSha1(String scriptName) {
        return scriptCache.get(scriptName);
    }
}