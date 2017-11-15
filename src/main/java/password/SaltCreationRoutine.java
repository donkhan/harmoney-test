package password;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SaltCreationRoutine {
	
	public static void genKey(String saltFile) throws NoSuchAlgorithmException, IOException{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256); 
		SecretKey secretKey = keyGen.generateKey();
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/conf/cherries/" + saltFile);
		fos.write(secretKey.getEncoded());
		fos.close();
	}
	
	public static void main(String args[]) throws NoSuchAlgorithmException, IOException{
		genKey("TEST.salt");
	}
	
}
