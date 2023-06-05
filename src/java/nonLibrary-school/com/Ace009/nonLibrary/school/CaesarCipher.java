package com.Ace009.nonLibrary.school;

//import com.Ace009.library.Range;
//import com.Ace009.library.Args;
import com.Ace009.library.CClass.CArray;

public class CaesarCipher {
	/**a {@code char[]} containing the latin alphabet */
	static final char[] ALPHABET = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	/**securing the alphabet */
	static {
		if(ALPHABET.length!=26) {throw new SecurityException("altered constant field");}
	}
	/**this instances warped {@link #alphabet} constant */
	private final char[] cipher = new char[ALPHABET.length];
	/**
	 * 
	 * @param code
	 */
	public CaesarCipher(final byte code) {
		byte i=0;
		while (cipher[25]=='\u0000') {
			cipher[i+code]=ALPHABET[i];
			i++;
		}
		i=0;
		while (i<code) {
			cipher[i]=ALPHABET[i];
			i++;
		}
	}
	public CaesarCipher (char code) {
		this((byte)CArray.indexOf(CArray.asObjectArray(ALPHABET), code));
	}
}
