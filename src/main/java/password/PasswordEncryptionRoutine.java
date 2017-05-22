package password;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/**
 * Created by kkhan on 13/05/17.
 * 
 */


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptionRoutine {
	
	public static void encrypt(String password,String fileName) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Key skeySpec = new SecretKeySpec(readKey(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
        byte[] encrypted  = cipher.doFinal(password.getBytes());
        fileName = System.getProperty("user.dir") + "/conf/cherries/" + fileName;
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(encrypted);
        fos.close();
    }
	
	private static byte[] readKey() throws IOException{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/conf/cherries/salt");
        byte raw[] = new byte[16];
        fis.read(raw);
        fis.close();
        return raw;
	}
	
    static String decrypt(String fileName) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        fileName = System.getProperty("user.dir") + "/conf/cherries/" + fileName;
        FileInputStream fis = new FileInputStream(fileName);
        List<Integer> list = new ArrayList<Integer>();
        int v = -1;
        while((v = fis.read()) != -1){
        	list.add(v);
        }
        byte[] c = new byte[list.size()];
        for(int i =0;i<list.size();i++){
        	c[i] = list.get(i).byteValue();
        }
        fis.close();
        Key key = new SecretKeySpec(readKey(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] ivByte = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParamsSpec);
        byte[] original = cipher.doFinal(c);
        String decryptedPassword = new String(original);
        return decryptedPassword;
    }

    
    private static void createKeys() throws FileNotFoundException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
    	 String fileName = System.getProperty("user.dir") + "/conf/cherries/cherries.conf";
         Properties properties = new Properties();
         properties.load(new FileInputStream(fileName));
         Iterator<Object> keys = properties.keySet().iterator();
         while(keys.hasNext()){
        	 String key = (String)keys.next();
        	 String password = properties.getProperty(key);
        	 encrypt(password,key);
        	 System.out.println(key + " " + decrypt(key));
         }
    }
    
    public static void main(String args[]) throws Exception{
    	SaltCreationRoutine.genKey();
    	createKeys();
    	System.out.println("Copy all the files under " + System.getProperty("user.dir") + "/conf/cherries/" + " to mapi/conf/cherries and restart MAPI");
    }
}
