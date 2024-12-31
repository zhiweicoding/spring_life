package xyz.zhiwei.spring_life.utils;

import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhiweicoding.xyz
 * @date 28/12/2024
 * @email diaozhiwei2k@gmail.com
 */
class MurmurHashUtilTest {

    @Test
    void hash32() {
        long intUrl = MurmurHashUtil.hash32("https://zhiwei.plus/archives/ef15f154-5d13-4d0e-b2a4-2b241b76f521");

        System.out.println(intUrl);
    }

    @Test
    void toBase62String() {
        String base62String = MurmurHashUtil.toBase62String(3800304482L);
        System.out.println(base62String);
    }
}