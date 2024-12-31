package xyz.zhiwei.spring_life;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import xyz.zhiwei.spring_life.controller.SentinelTestController;

import java.util.ArrayList;
import java.util.List;

/**
 * java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
 */
@SpringBootApplication()
@MapperScan(basePackages = "xyz.zhiwei.spring_life.dao")
@EnableTransactionManagement
@EntityScan(value = {"xyz.zhiwei.spring_life.bean"})
public class SpringLifeApplication {

    public static void main(String[] args) {
        System.out.println("开始执行spring boot");
        ConfigurableApplicationContext run = SpringApplication.run(SpringLifeApplication.class, args);
        BeanLifeComponent bean = run.getBean(BeanLifeComponent.class);
        bean.use();
//        System.out.println("开始关闭spring boot");
//        run.close();
//        System.out.println("关闭了spring boot");

        initFlowRules();
//        while (true) {
//            Entry entry = null;
//            try {
//                entry = SphU.entry("HelloWorld");
//                /*您的业务逻辑 - 开始*/
//                System.out.println("hello world");
//                /*您的业务逻辑 - 结束*/
//            } catch (BlockException e1) {
//                /*流控逻辑处理 - 开始*/
//                System.out.println("block!");
//                /*流控逻辑处理 - 结束*/
//            } finally {
//                if (entry != null) {
//                    entry.exit();
//                }
//            }
//
//        }

    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(200);
        rules.add(rule);

        FlowRule rule2 = new FlowRule(SentinelTestController.RESOURCE_NAME);
        // set limit qps to 2
        rule2.setCount(2);
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setLimitApp("default");
        rules.add(rule2);
        FlowRuleManager.loadRules(rules);
    }
}
