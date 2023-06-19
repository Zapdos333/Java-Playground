package com.Ace009.nonLibrary.school;

import java.util.HashMap;
import java.util.Map;

public class RNATranslation {
	public static final Map<Gen[], AminoAcid> CODES = new HashMap<>(){
		
	};
	public static enum Gen {
		T, U, C, A, G;
		static final Gen[][] DNApairs = new Gen[][]{
			{Gen.G,Gen.C},{Gen.C,Gen.G},{Gen.T,Gen.A},{Gen.A,Gen.T}
		};
		static final Gen[][] RNApairs = new Gen[][]{
			{Gen.G,Gen.C},{Gen.C,Gen.G},{Gen.U,Gen.A},{Gen.A,Gen.U}
		}; 
	}
	public static enum AminoAcid {
		STOP(' ',"","stopping..."),START(' ',"","starting..."),
		// Start and stop only for codes Map
		F('F',"Phe","Phenylalanine"),L('L',"Leu","Leucine"),
		I('I',"Ile","Isoleucin"),M('M',"Met","Methionine"),
		V('V',"Val","Valine"),S('S',"Ser","Serine"),
		P('P',"Pro","Proline"),T('T',"Thr","Threonine"),
		A('A',"Ala","Alanine"),Y('Y',"Tyr","Tyrosine"),
		H('H',"His","Histidine"),Q('Q',"Gln","Glutamine"),
		N('N',"Asn","Aparagine"),K('K',"Lys","Lysine"),
		D('D',"Asp","Aspartic Acid"),E('E',"Glu","Glutamic Acid"),
        C('C',"Cys","Cysteine"),W('W',"Trp","Tryptophan"),
        R('R',"Arg","Arginine"),G('G',"Gly","Glycine");
		AminoAcid(char code, String shortCode, String name){
			this.shortCode = shortCode; this.name = name; this.code = code;
		}
		private final String shortCode; private final String name; private final char code;
		public String SC(){ return shortCode; }
		public String N(){ return name; }
		public char C(){ return code; }
		static final byte amount = 20; // twenty proteinogenic amino-acids
	}
}
