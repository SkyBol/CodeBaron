package ch.asu.chaosdb.core.entries.storage.helpers;

import com.google.common.hash.Hashing;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class EnDeCrypter {
    private final Cipher cipher;
    private final SecretKeySpec secretKeySpec;

    public EnDeCrypter(String password, String salt, String pepper) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        byte[] hashedPassword = Hashing.sha256().hashString(String.format("%s--%s$$%s", password, salt, pepper), Charset.defaultCharset()).asBytes();

        this.secretKeySpec = new SecretKeySpec(hashedPassword, "AES");
    }

    public byte[] encrypt(byte[] fileInput) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return crypt(fileInput, Cipher.ENCRYPT_MODE);
    }

    public byte[] decrypt(byte[] fileInput) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return crypt(fileInput, Cipher.DECRYPT_MODE);
    }

    private byte[] crypt(byte[] fileInput, int optmode) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        cipher.init(optmode, this.secretKeySpec);
        return cipher.doFinal(fileInput);
    }
}
