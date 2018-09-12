package com.wallxu.common.utils;

import com.wallxu.common.constant.CommonConstant;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * Created by shupinyougou on 2017/1/19.
 */
public class MD5Util {


    public static void main(String[] args) {
        System.out.println(getDynamicSalt());
        System.out.println(md5LoginPassword("15230631228"));
        System.out.println(md5("d3b1294a61a07da9b49b6e22b2cbd7f9","1a2b3c4d"));
    }

    /**
     * 加密处理前端登录输入的密码
     * @param loginPassword
     * @return
     */
    public final static String md5LoginPassword(String loginPassword) {
        String common_salt = CommonConstant.PASSWORD_SALT;
        return md5(loginPassword, common_salt);
    }


    public final static String md5(String content, String salt) {
        String mixContent = "" + salt.charAt(0) + salt.charAt(2) + content + salt.charAt(5) + salt.charAt(4);
        return DigestUtils.md5Hex(mixContent);
    }

    public static String getDynamicSalt() {
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rs = "";
        char[] rands = new char[8];
        for (int i = 0; i < rands.length; i++) {
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
        }
        for (int i = 0; i < rands.length; i++) {
            rs += rands[i];
        }
        return rs;
    }

    public final static String md5(String content) {
        //用于加密的字符
        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = content.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

}
