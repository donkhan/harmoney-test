package password;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kkhan on 13/05/17.
 */
public class PasswordEncryptionRoutine {
    static String encrypt(String password){
	String key = "Bar12345Bar12345"; 
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            String encryptedPassWord = new String(cipher.doFinal(password.getBytes()));
            return  encryptedPassWord;
        }catch(NoSuchAlgorithmException nae){
            nae.printStackTrace();
        }catch(NoSuchPaddingException npe){
            npe.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]){
        if(args.length == 0){
            System.err.println("Usage java password.PasswordEncryptionRoutine Password");
        }
        System.out.println(encrypt(args[0]));
    }
}
