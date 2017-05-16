package password;

import java.io.FileOutputStream;

/**
 * Created by kkhan on 13/05/17.
 * 
 */


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptionRoutine {

    public static byte[] encrypt(String value) {
        byte[] encrypted = null;
        try {
            byte[] raw = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
            Key skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
            encrypted  = cipher.doFinal(value.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encrypted;
    }

    
    public static void main(String args[]) throws Exception{
    	if(args.length < 2){
    		System.err.println("Usage password.PasswordEncryptionRoutine Password filename");
    		return;
    	}
        byte b[] = encrypt(args[0]);
        String fileName = System.getProperty("user.dir") + "/" + args[1];
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(b);
        fos.close();
        System.out.println("Password is stored in " + fileName + " . Copy it to conf/cherries folder in MAPI and restart it");

    }
}
