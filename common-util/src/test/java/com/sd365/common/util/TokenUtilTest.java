package com.sd365.common.util;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class TokenUtilTest {
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();

    @Test
    public void mainTest(){
        String jsonToken = "{\"sda\":\"sda\"}.{\"234\":\"ww\"}.{\"sff\":\"www\"}";
        final String s = encryptionToken(jsonToken);
        System.out.println("加密后的token: "+s);
    }
    public String encryptionToken(String jsonToken) {
        final String[] split = jsonToken.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for(String s :split){
            final String tmp = encoder64(s);
            stringBuilder.append(tmp).append(".");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public void decryptToken(String token)  {
        final String[] split = token.split("\\.");
        for(String s:split){
            final String s1 = decoder64(s);
            System.out.println(s1);
        }
    }

    private String  decoder64(String baseString){
        final byte[] textByte = baseString.getBytes(StandardCharsets.UTF_8);
        return new String(decoder.decode(textByte), StandardCharsets.UTF_8);
    }

    private String  encoder64(String baseString){
        return encoder.encodeToString(baseString.getBytes(StandardCharsets.UTF_8));
    }

}
