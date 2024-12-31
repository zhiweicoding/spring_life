package xyz.zhiwei.spring_life;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author zhiweicoding.xyz
 * @date 29/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@Service
public class LeaderboardService {

    private static final String LEADERBOARD_KEY = "game_leaderboard";

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 添加或更新用户分数
     *
     * @param userId 用户ID
     * @param score  用户分数
     */
    public void addOrUpdateUserScore(String userId, double score) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(LEADERBOARD_KEY);
        sortedSet.add(score, userId);
    }

    /**
     * 获取用户排名（从高到低）
     *
     * @param userId 用户ID
     * @return 用户排名（1 表示第一名），如果用户不存在则返回 null
     */
    public Integer getUserRank(String userId) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(LEADERBOARD_KEY);
        Integer rank = sortedSet.revRank(userId);
        return rank != null ? rank + 1 : null; // 转换为从1开始的排名
    }

    /**
     * 获取排行榜前 N 名
     *
     * @param topN 前N名
     * @return 前N名用户及其分数
     */
    public List<ScoredEntry<String>> getTopN(int topN) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(LEADERBOARD_KEY);
        Collection<ScoredEntry<String>> scoredEntries = sortedSet.entryRangeReversed(0, topN - 1);
        return scoredEntries.stream().toList();
    }

    /**
     * 获取指定范围内的用户
     *
     * @param start 起始排名（从1开始）
     * @param end   结束排名（从1开始）
     * @return 指定范围内的用户及其分数
     */
    public List<ScoredEntry<String>> getUsersInRange(int start, int end) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(LEADERBOARD_KEY);
        return sortedSet.entryRangeReversed(start - 1, end - 1).stream().toList();
    }
}
