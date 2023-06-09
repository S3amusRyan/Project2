package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public final class AuthenticationUtils {

    /**
     * Returns SHA-256 hash of the string
     *
     * @param password: string to be hashed
     * @return SHA-256 encoded string
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String encodeSHA256(String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest);

    }

}
