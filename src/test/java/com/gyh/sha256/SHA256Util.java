package com.gyh.sha256;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author gyh
 * @Date 2021/1/28 14:17
 */
public class SHA256Util {

    public static final String ALGORITHM_RSA = "RSA";


    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    public static void main(String[] args)  throws Exception {

        String client_id = "ykqy";
        String client_secret = "4ee73cc437ac4dcb8a45";
        String username = "ykqy";
        String pwd = "123qwe";
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVZzoBatliUEoi9ZOHMQRxNBJDsFEDpuCOjMIKHWyUZj6bB6kgwivPEtKQY0mP/88ObERpla33ubB+Enp4PjZoI8ay7F6IbmJ7GhGCZy1hqgRAsB1DYjsL+fFyhZyhCVChgkMlFwt253t8EeFo6HKteo8jaVUOShbdMLRhXFQJWQIDAQAB";

        String sign = SHA256Util.getSHA256(client_id + client_secret + username + pwd + pubKey);
        String  aaa = URLEncoder.encode(pubEncrypt(pubKey, pwd), "utf-8");

        String clientSecret = URLEncoder.encode(pubEncrypt(pubKey, client_secret), "utf-8");

        System.out.println("生成sign：" + sign);
        System.out.println("密码加密：" + aaa);
        System.out.println("clientSecret：" + clientSecret);
    }


    public static String pubEncrypt(String pubKey, String src) throws Exception {
        int MAX_ENCRYPT_BLOCK = 117;
        String target = null;
        ByteArrayOutputStream out = null;
        try {
            Key key = getPublicKey(pubKey);

            Cipher cipher = Cipher.getInstance(SHA256Util.ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // encodeResult = cipher.doFinal(src.getBytes());
            byte[] data = src.getBytes();
            int inputLen = data.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }

            target = encryptBASE64(out.toByteArray());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("message" + e.getMessage());
        }finally{
            if(out != null){
                out.close();
            }
        }
        return target;
    }

    public static Key getPublicKey(String pubKey) throws Exception {
        Key key = null;

        try {
            byte[] keyBytes = decryptBASE64(pubKey);
            KeyFactory keyFactory = KeyFactory.getInstance(SHA256Util.ALGORITHM_RSA);

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            key = keyFactory.generatePublic(x509KeySpec);

        } catch (Exception e) {
            throw new Exception("��Ч����Կ  " + e.getMessage());
        }

        return key;
    }

    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static byte[] decryptBASE64(String key) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
