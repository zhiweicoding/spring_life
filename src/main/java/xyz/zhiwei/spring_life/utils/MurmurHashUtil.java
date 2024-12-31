package xyz.zhiwei.spring_life.utils;

import com.google.common.hash.Hashing;


/**
 * @author zhiweicoding.xyz
 * @date 28/12/2024
 * @email diaozhiwei2k@gmail.com
 */
public class MurmurHashUtil {

    // 这里以 MurmurHash3 32-bit 版本做演示
    public static long hash32(String data) {
        int seed = 0x1234ABCD;
        return Hashing.murmur3_32(seed).hashUnencodedChars(data).padToLong();
    }

    // 将32位整型转换为 62 进制字符串
    public static String toBase62String(long hashValue) {
        // 考虑到 hashValue 可能为负数，可以做一下取绝对值处理
        long num = hashValue & 0x00000000ffffffffL;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int remainder = (int) (num % 62);
            sb.append(chars.charAt(remainder));
            num = num / 62;
        }
        return sb.reverse().toString();
    }

}
