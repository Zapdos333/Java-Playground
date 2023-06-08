package com.Ace009.nonLibrary.school;

import com.Ace009.library.Range;
import com.Ace009.library.CClass.CArray;

/**
 * a class to create and use Caesar Ciphers
 * <p>
 * has methods for {@link #encode(char[]) encoding} and {@link #decode(char[]) decoding}
 * accepting both {@code String} and {@code char[]}
 * <p>
 * also has a method for {@link #crack(String) cracking} a {@code String} encoded with a Caesar Cipher,
 * simply gives an array of all possible decodings
 * @author Ace009
 */
public class CaesarCipher {
	/**a {@code char[]} containing the latin alphabet */
	static final char[] ALPHABET = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	/**securing the alphabet */
	static {
		if(ALPHABET.length!=26) {throw new SecurityException("altered constant field");}
	}
	/**
	 * a method that gets all possible decodings of {@code input}
	 * @param input the {@code String} to attempt decode
	 * @return {@code String[]} containing all possible decodings of {@code input}
	 */
	public static String[] crack (String input) {
		input = input.toLowerCase();
		String[] output = new String[ALPHABET.length];
		for (int i : Range.arrayRange(ALPHABET.length)) {
			CaesarCipher check = new CaesarCipher((byte)i);
			output[i] = check.decode(input);
		}
		return output;
	}
	/**this instances shifted {@link #alphabet} constant */
	private final char[] cipher = new char[ALPHABET.length];
	/**
	 * Standard Constructor,
	 * {@code this.cipher} is {@code ALPHABET} shifted by {@code code} entries
	 * @param code {@code byte} between 0 and 25, inclusive
	 * @see #ALPHABET
	 * @see #cipher
	 */
	public CaesarCipher(final byte code) {
		byte i=0;
		while (cipher[25]=='\u0000') {
			cipher[i+code]=ALPHABET[i];
			i++;
		}
		i=0;
		while (i<code) {
			cipher[i]=ALPHABET[i+ALPHABET.length-code];
			i++;
		}
	}
	/**
	 * Character implementation of the default Constructor,
	 * runs {@code CaesarCipher(byte)} with {@code code}s index of {@code ALPHABET},
	 * {@code (byte)code} defaults to {@code 0}
	 * @param code {@code char} the key Character
	 * @see #CaesarCipher(byte)
	 * @see #ALPHABET
	 */
	public CaesarCipher (char code) {
		this((byte)Math.max( CArray.indexOf(CArray.asObjectArray(ALPHABET), Character.toLowerCase(code)),0));
	}
	/**
	 * encodes {@code input} using the {@code char} from {@code cipher}
	 * as replacement for the {@code char} in {@code ALPHABET}
	 * @param input {@code char[]} input to encode
	 * @return {@code char[]} encoded
	 * @see #cipher
	 */
	public char[] encode(char[] input) {
		input = new String(input).toLowerCase().toCharArray();
		char[] output = new char[input.length];
		for (int i : Range.arrayRange(output.length)) {
			output[i]=cipher[Math.max( CArray.indexOf(CArray.asObjectArray(ALPHABET), input[i]),0)];
		}
		assert output[output.length-1]!='\u0000';
		return output;
	}
	/**
	 * default implementation of {@code .encode(char[])}
	 * with a {@code String} instead of {@code char[]}
	 * @param input {@code String} input to encode
	 * @return {@code String} encoded
	 * @see #encode(char[])
	 */
	public String encode(String input) {
		return new String(this.encode(input.toLowerCase().toCharArray()));
	}
	/**
	 * decodes {@code input} using the {@code char} from {@code ALPHABET}
	 * as replacement for the {@code char} in {@code cipher}
	 * @param input {@code char[]} input to decode
	 * @return {@code char[]} decoded
	 * @see #cipher
	 */
	public char[] decode(char[] input) {
		input = new String(input).toLowerCase().toCharArray();
		char[] output = new char[input.length];
		for (int i : Range.arrayRange(output.length)) {
            output[i]=ALPHABET[Math.max( CArray.indexOf(CArray.asObjectArray(cipher), input[i]),0)];
        }
		assert output[output.length-1]!='\u0000';
		return output;
	}
	/**
	 * default implementation of {@code .decode(char[])}
	 * with a {@code String} instead of {@code char[]}
	 * @param input {@code String} input to decode
	 * @return {@code String} decoded
	 * @see #decode(char[])
	 */
	public String decode(String input) {
		return new String(this.decode(input.toLowerCase().toCharArray()));
	}
	/*public String getKey() {
		return CArray.asList(CArray.asObjectArray(cipher)).toString();
	}*/
}
