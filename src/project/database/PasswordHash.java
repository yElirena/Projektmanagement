package project.database;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHash 
{
	private static SecureRandom random = new SecureRandom();
	private static String password;
	private static String newPassword;
	
	
	public static String generatePassword(String password) 
	{
		try 
		{
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			
			Base64.Encoder enc = Base64.getEncoder();
			newPassword = enc.encodeToString(hash);
			
			
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
		{
			e.printStackTrace();
		}
		return newPassword;
	}

}
