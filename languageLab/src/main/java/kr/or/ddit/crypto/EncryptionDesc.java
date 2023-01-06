package kr.or.ddit.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * encode / decode
 * 	encoding(부호화) : 전송이나 저장을 위해 매체(media)가 이해할 수 있는 방식으로 데이터의 표현 방식을 바꾸는 작업.
 * 					 ex) Base64, URLEncoding(percent encoding), 
 * encrypt / decrypt
 *  encryption(암호화) : 권한(key) 없는 사용자가 snipping 하거나 spooping 하는 걸 막기 위해 데이터 표현을 바꾸는 작업.(즉 막아야하는 것)
 *  - 단방향 암호화(hash 함수) : 암호화 된 이후 평문 복원이 불가능한 방식(암호화는 가능한데 복호화는 안됨. 비밀번호 암호화에 활용).
 *  				: 다양한 길이의 입력 데이터에 만들어지는 결과 데이터가 길이가 동일한 경우.
 *  		ex) SHA-512(숫자는 암호문의 길이를 의미한다)
 *   			해시의 단점이 있다 - 
 *  - 양방향 암호화 : 암호문에서 원래 평문으로 복화하가 가능한 방식
 *  	- 대칭키 방식 : 하나의 비밀키로 암호화와 복호화에 모두 사용(ebook)., 하나의 키로 만들기때문에 키가 노출될 수 있다. 이 방식을 ->비대칭키로 해결할 수 있다.
 *  		ex) AES(128, 256)
 *  	- 비대칭키 방식 : 공개키와 개인키, 한쌍의 키로 암호화와 복호화에 다른 키를 사용하는 방식(전자서명).
 *  		ex) RSA(2048)
 *  
 *   //CBC 방식은 사이퍼의 바인드 방식이 chain이라는 뜻. 앞에 연산이 없으면 
 *     첫번째 연산에서 초기화 블럭이 필요하다. 초기화 블럭을 임의로 만들어서 다음 블럭화 익스클루시브 연산이 되어야한다. 
 *  	
 */
public class EncryptionDesc {
	public static void main(String[] args) throws Exception {
		//비대칭키 방식
		
		String plain = "java";
//		encryptAESTest(plain);
		
		//한쌍의 키를 먼저 만든다
		KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
		pairGen.initialize(2048);
		KeyPair keyPair = pairGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded); //인코딩을 해놨기때문에 디코딩을 해야한다
		
		byte[] decoded = Base64.getDecoder().decode(encoded); //== encrypted
		
		//개인키로 암호화했으면 공개키로 복호화해야한다
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decryted = cipher.doFinal(decoded); //얘는 위에 input하고 같다
		System.out.println(new String(decryted));
	}
	
	private static void encryptAESTest(String plain) throws Exception {
		String ivValueText = "초기화벡터";
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest(ivValueText.getBytes());
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256); //길이가 128짜ffl, 256은 키 사이즈가 오류나는게 우리가 갖고 있는 JDK버전이 허용을 못해서 그렇다.
		SecretKey key = keyGen.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec); //ENCRYPT_MODE가 암호화를 한다는 뜻이다
		
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		//인코딩은 무조건 Base64
		//부호화
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		//주민번호를 입력 후 암호화시켜서 저장한 상황
		byte[] decoded = Base64.getDecoder().decode(encoded);
		
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec); //대칭키에서 핵심은 암호화 키의 복호화모드의 키와 같다는 게 핵심이다.
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String(decrypted));
	}
	
	private static String encrptSha512(String plain) { //단방향 암호화
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encrypted = md.digest(input);
//			System.out.println(encrypted.length); //*8
			String encoded = Base64.getEncoder().encodeToString(encrypted);
//			System.out.println(encoded);
			return encoded;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public static void encodeTest() throws UnsupportedEncodingException {
		//암호화 설명 예시
		String plain = "원문데이터";
		String base64Encoded = Base64.getEncoder().encodeToString(plain.getBytes());
		System.out.println(base64Encoded);
		System.out.println(new String(Base64.getDecoder().decode(base64Encoded)));
		String urlEncoded = URLEncoder.encode(plain, "UTF-8");
		System.out.println(urlEncoded);
		System.out.println(URLDecoder.decode(urlEncoded, "UTF-8"));
	}
}
