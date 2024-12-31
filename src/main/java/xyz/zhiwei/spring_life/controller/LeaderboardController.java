package xyz.zhiwei.spring_life.controller;

import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zhiwei.spring_life.LeaderboardService;

import java.util.List;
import java.util.Set;

/**
 * @author zhiweicoding.xyz
 * @date 29/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    /**
     * 添加或更新用户分数
     */
    @PostMapping("/update")
    public String updateUserScore(@RequestParam String userId, @RequestParam double score) {
        leaderboardService.addOrUpdateUserScore(userId, score);
        return "用户分数已更新";
    }

    /**
     * 获取用户排名
     */
    @GetMapping("/rank/{userId}")
    public String getUserRank(@PathVariable String userId) {
        Integer rank = leaderboardService.getUserRank(userId);
        if (rank != null) {
            return "用户 " + userId + " 的排名是第 " + rank + " 名";
        } else {
            return "用户未上榜";
        }
    }

    /**
     * 获取排行榜前 N 名
     */
    @GetMapping("/top/{n}")
    public String getTopN(@PathVariable int n) {
        List<ScoredEntry<String>> topN = leaderboardService.getTopN(n);
        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (ScoredEntry<String> entry : topN) {
            sb.append(rank++)
                    .append(". 用户ID: ")
                    .append(entry.getValue())
                    .append(", 分数: ")
                    .append(entry.getScore())
                    .append("<br>");
        }
        return sb.toString();
    }

    /**
     * 获取指定范围内的用户
     */
    @GetMapping("/range")
    public String getUsersInRange(@RequestParam int start, @RequestParam int end) {
        List<ScoredEntry<String>> range = leaderboardService.getUsersInRange(start, end);
        StringBuilder sb = new StringBuilder();
        int rank = start;
        for (ScoredEntry<String> entry : range) {
            sb.append(rank++)
                    .append(". 用户ID: ")
                    .append(entry.getValue())
                    .append(", 分数: ")
                    .append(entry.getScore())
                    .append("<br>");
        }
        return sb.toString();
    }
}