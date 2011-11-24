package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {
	
	private final static SecureRandom random = new SecureRandom();
	private final static BigInteger one = new BigInteger("1");
	
	private BigInteger modulus;
	
	private BigInteger publicKey;
	private BigInteger privateKey;
	
	public RSA(){
		BigInteger p = BigInteger.probablePrime(5, new Random());
		BigInteger q = BigInteger.probablePrime(5, new Random());
		BigInteger phi = (p.add(one)).multiply(q.subtract(one));
		
		this.modulus = p.multiply(q);
		this.publicKey = new BigInteger("65537");
		this.privateKey = publicKey.modInverse(phi);
		
	}
	
	
	public BigInteger encrypt(BigInteger message){
		return message.modPow(publicKey, modulus);
	}
	
	public BigInteger decrypt(BigInteger encrypted){
		return encrypted.modPow(privateKey, modulus);
	}
	
	public String encrypt(String message){
		return this.encrypt(new BigInteger(message)).toString();
	}
	
	public String decrypt(String message){
		return this.decrypt(new BigInteger(message)).toString();
	}
	
	public BigInteger getModulus(){
		return this.modulus;
	}
	
	public String getPublicKey(){
		return this.publicKey.toString() + this.modulus.toString();
	}
	
	@Override
	public String toString(){
		return 	"Public:\t" + this.publicKey + 
				"Private:\t" + this.privateKey + 
				"Modulus:\t" + this.modulus;
	}
}
