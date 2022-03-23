package com.sd365.common.util;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oxx
 * @Date 2020-12-18
 */
public class TokenUtil {
    final static Base64.Decoder DECODER = Base64.getDecoder();
    final static Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * @param jsonToken:String
     * @return 返回已经经过加密的了token字符串
     */
    public static String encoderToken(String jsonToken) {
        final String[] split = jsonToken.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            final String tmp = encoder64(s);
            stringBuilder.append(tmp).append(".");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * @param token:String
     * @return 返回token：head,payload, Signature的集合对象
     */
    public static List<String> decryptToken(String token) {
        final String[] split = token.split("\\.");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            final String s1 = decoder64(s);
            list.add(s1);
        }
        return list;
    }

    private static String decoder64(String baseString) {
        final byte[] textByte = baseString.getBytes(StandardCharsets.UTF_8);
        return new String(DECODER.decode(textByte), StandardCharsets.UTF_8);
    }

    private static String encoder64(String baseString) {
        return ENCODER.encodeToString(baseString.getBytes(StandardCharsets.UTF_8));
    }

    private TokenUtil() {
    }

//    /**
//     *
//     * @param token
//     * @return 解析token成为map
//     */
//    public static Map<String, String> analysisToken(String token) {
//        Map<String, String> map = new HashMap<>();
//        final Pattern p = Pattern.compile("(?<=\").*?(?=\")");
//        final List<String> list = decryptToken(token);
//        for (String s : list) {
//            final String[] split = s.split(",");
//            for (String s1 : split) {
//                final String[] split1 = s1.split(":");
//                String key="", value = "";
//                Matcher matcher1=p.matcher(split1[0]);
//                while(matcher1.find()) {
//                    key = matcher1.group();
//                }
//                Matcher matcher2=p.matcher(split1[1]);
//                while(matcher2.find()) {
//                    value= matcher2.group();
//                }
//                map.put(key, value);
//            }
//        }
//        return map;
//    }
}
