package xyz.zhiwei.spring_life.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.zhiwei.spring_life.lua.LuaScriptManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhiweicoding.xyz
 * @date 24/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelTestController {
    //资源名称
    public static final String RESOURCE_NAME = "userList";

    @RequestMapping("/list")
    public List<String> getUserList() {
        List<String> userList = null;
        Entry entry = null;
        try {
            // 被保护的业务逻辑
            entry = SphU.entry(RESOURCE_NAME);
            userList = Collections.singletonList("zhiwei");
        } catch (BlockException e) {
            // 资源访问阻止，被限流或被降级
            return Collections.singletonList("xianliu");
        } catch (Exception e) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        } finally {
            // 务必保证 exit，务必保证每个 entry 与 exit 配对
            if (entry != null) {
                entry.exit();
            }
        }
        return userList;
    }

    @RequestMapping("/get/{id}")
    public String queryUserById(@PathVariable("id") String id) {
        if (SphO.entry(RESOURCE_NAME)) {
            try {
                //被保护的逻辑
                //模拟数据库查询数据
                return "zhiwei";
            } finally {
                //关闭资源
                SphO.exit();
            }
        } else {
            //资源访问阻止，被限流或被降级
            return "Resource is Block!!!";
        }
    }

    @RequestMapping("/by/{name}")
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "queryUserByUserNameBlock")
    public String queryByName(@PathVariable("name") String name) {
        return "zhiwei";
    }

    @RequestMapping("/fallback")
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "queryUserByUserNameBlock", fallback = "queryUserByUserNameFallBack")
    public String fallback(@RequestParam("name") String name) {
        if (name == null || "".equals(name)) {
            //抛出异常
            throw new RuntimeException("fallback() command failed, userName is null");
        }
        return "zhiwei";
    }

    //注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数
    //否则会报错，FlowException: null
    public String queryUserByUserNameBlock(String name, BlockException e) {
        //打印异常
        e.printStackTrace();
        return "Resource is Block!!!";
    }

    public String queryUserByUserNameFallBack(String name, Throwable ex) {
        //打印异常
        ex.printStackTrace();
        return "Resource is FallBack!!!";
    }

}
