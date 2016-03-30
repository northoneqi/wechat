package com.qishi.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;



public class EncryptUtil {

	public static final String HashType_MD5 = "MD5";
	public static final String HashType_SHA1 = "SHA1";
	public static final String HashType_SHA256 = "SHA-256";
	public static final String HashType_SHA384 = "SHA-384";
	public static final String HashType_SHA512 = "SHA-512";	

	public static String getHashString(String str, String hashType)
			throws Exception {
		String result = null;
		
		if( StringUtil.isNotEmpty( str ) && StringUtil.isNotEmpty( hashType ) ){
		
			MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder( hashType );  
	          
	        result = mdpe.encodePassword( str, null );
        
		}
        return result;
	}
	
	public static String getHashString(String str, String salt, String hashType)
			throws Exception {
		String result = null;
		
		if( StringUtil.isNotEmpty( str ) && StringUtil.isNotEmpty( hashType ) ){
		
			MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder( hashType );  
	          
	        result = mdpe.encodePassword( str, salt );
        
		}
        return result;
	}
	
	public static String getHashString(String str, String hashType, boolean useBase64Encode )
			throws Exception {
		String result = null;
		
		if( StringUtil.isNotEmpty( str ) && StringUtil.isNotEmpty( hashType ) ){
		
			MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder( hashType );  
	        mdpe.setEncodeHashAsBase64( useBase64Encode );
	        result = mdpe.encodePassword( str, null );
        
		}
        return result;
	}
	
	public static String getHashString(String str, String salt, String hashType, boolean useBase64Encode )
			throws Exception {
		String result = null;
		
		if( StringUtil.isNotEmpty( str ) && StringUtil.isNotEmpty( hashType ) ){
		
			MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder( hashType );  
	        mdpe.setEncodeHashAsBase64( useBase64Encode );
	        result = mdpe.encodePassword( str, salt );
        
		}
        return result;
	}

	public static void main(String[] args) throws Exception {
		String str = "123456";
		String salt = "admin";
		
		System.out.println( "默认加密" );
		System.out.println( "---------------------------------------------------------" );
		System.out.println( str + "(MD5)    =>" + getHashString( str, HashType_MD5 ) );
		System.out.println( str + "(SHA1)   =>" + getHashString( str, HashType_SHA1 ) );
		System.out.println( str + "(SHA256) =>" + getHashString( str, HashType_SHA256 ) );
		System.out.println( str + "(SHA384) =>" + getHashString( str, HashType_SHA384 ) );
		System.out.println( str + "(SHA512) =>" + getHashString( str, HashType_SHA512 ) );
		
		System.out.println();
		System.out.println( "默认加密并用Base64编码" );
		System.out.println( "---------------------------------------------------------" );
		System.out.println( str + "(MD5)    =>" + getHashString( str, HashType_MD5, true ) );
		System.out.println( str + "(SHA1)   =>" + getHashString( str, HashType_SHA1, true ) );
		System.out.println( str + "(SHA256) =>" + getHashString( str, HashType_SHA256, true ) );
		System.out.println( str + "(SHA384) =>" + getHashString( str, HashType_SHA384, true ) );
		System.out.println( str + "(SHA512) =>" + getHashString( str, HashType_SHA512, true ) );
		
		System.out.println();
		System.out.println( "带盐加密(" + salt + ")" );
		System.out.println( "---------------------------------------------------------" );
		System.out.println( str + "(MD5)    =>" + getHashString( str, salt, HashType_MD5 ) );
		System.out.println( str + "(SHA1)   =>" + getHashString( str, salt, HashType_SHA1 ) );
		System.out.println( str + "(SHA256) =>" + getHashString( str, salt, HashType_SHA256 ) );
		System.out.println( str + "(SHA384) =>" + getHashString( str, salt, HashType_SHA384 ) );
		System.out.println( str + "(SHA512) =>" + getHashString( str, salt, HashType_SHA512 ) );
		
		System.out.println();
		System.out.println( "带盐加密(" + salt + ")并用Base64编码" );
		System.out.println( "---------------------------------------------------------" );
		System.out.println( str + "(MD5)    =>" + getHashString( str, salt, HashType_MD5, true ) );
		System.out.println( str + "(SHA1)   =>" + getHashString( str, salt, HashType_SHA1, true ) );
		System.out.println( str + "(SHA256) =>" + getHashString( str, salt, HashType_SHA256, true ) );
		System.out.println( str + "(SHA384) =>" + getHashString( str, salt, HashType_SHA384, true ) );
		System.out.println( str + "(SHA512) =>" + getHashString( str, salt, HashType_SHA512, true ) );
	}
}
