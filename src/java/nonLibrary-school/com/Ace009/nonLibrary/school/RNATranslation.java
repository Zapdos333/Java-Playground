package com.Ace009.nonLibrary.school;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * A class for working with {@link AminoAcid amino acids} and their corresponing RNA {@link #CODES codons}
 * @author Ace009
 */
public class RNATranslation {
	public static final Map<Nucleobase[], AminoAcid> CODES = new HashMap<>(){
		{
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.U,Nucleobase.U},AminoAcid.F);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.U,Nucleobase.C},AminoAcid.F);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.U,Nucleobase.A},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.U,Nucleobase.G},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.C,Nucleobase.U},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.C,Nucleobase.C},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.C,Nucleobase.A},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.C,Nucleobase.G},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.A,Nucleobase.U},AminoAcid.Y);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.A,Nucleobase.C},AminoAcid.Y);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.A,Nucleobase.A},AminoAcid.STOP);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.A,Nucleobase.G},AminoAcid.STOP);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.G,Nucleobase.U},AminoAcid.C);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.G,Nucleobase.C},AminoAcid.C);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.G,Nucleobase.A},AminoAcid.STOP);
			put(new Nucleobase[]{Nucleobase.U,Nucleobase.G,Nucleobase.G},AminoAcid.W);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.U,Nucleobase.U},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.U,Nucleobase.C},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.U,Nucleobase.A},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.U,Nucleobase.G},AminoAcid.L);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.C,Nucleobase.U},AminoAcid.P);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.C,Nucleobase.C},AminoAcid.P);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.C,Nucleobase.A},AminoAcid.P);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.C,Nucleobase.G},AminoAcid.P);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.A,Nucleobase.U},AminoAcid.H);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.A,Nucleobase.C},AminoAcid.H);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.A,Nucleobase.A},AminoAcid.Q);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.A,Nucleobase.G},AminoAcid.Q);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.G,Nucleobase.U},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.G,Nucleobase.C},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.G,Nucleobase.A},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.C,Nucleobase.G,Nucleobase.G},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.U,Nucleobase.U},AminoAcid.I);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.U,Nucleobase.C},AminoAcid.I);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.U,Nucleobase.A},AminoAcid.I);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.U,Nucleobase.G},AminoAcid.M); //start
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.C,Nucleobase.U},AminoAcid.T);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.C,Nucleobase.C},AminoAcid.T);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.C,Nucleobase.A},AminoAcid.T);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.C,Nucleobase.G},AminoAcid.T);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.A,Nucleobase.U},AminoAcid.N);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.A,Nucleobase.C},AminoAcid.N);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.A,Nucleobase.A},AminoAcid.K);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.A,Nucleobase.G},AminoAcid.K);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.G,Nucleobase.U},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.G,Nucleobase.C},AminoAcid.S);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.G,Nucleobase.A},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.A,Nucleobase.G,Nucleobase.G},AminoAcid.R);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.U,Nucleobase.U},AminoAcid.V);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.U,Nucleobase.C},AminoAcid.V);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.U,Nucleobase.A},AminoAcid.V);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.U,Nucleobase.G},AminoAcid.V);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.C,Nucleobase.U},AminoAcid.A);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.C,Nucleobase.C},AminoAcid.A);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.C,Nucleobase.A},AminoAcid.A);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.C,Nucleobase.G},AminoAcid.A);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.A,Nucleobase.U},AminoAcid.D);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.A,Nucleobase.C},AminoAcid.D);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.A,Nucleobase.A},AminoAcid.E);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.A,Nucleobase.G},AminoAcid.E);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.G,Nucleobase.U},AminoAcid.G);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.G,Nucleobase.C},AminoAcid.G);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.G,Nucleobase.A},AminoAcid.G);
			put(new Nucleobase[]{Nucleobase.G,Nucleobase.G,Nucleobase.G},AminoAcid.G);
		}
	};
	public static enum Nucleobase {
		/** Uracil/Thymine */
		U('U','T'),
		/** Cytosine */
		C('C','C'),
		/** Adenine */
		A('A','A'),
		/** Guanine */
		G('G','G');
		/** RNA-code */
		private final char codeR;
		/** DNA-code */
		private final char codeD;
		/** constructor defining the processing of the given constants */
		private Nucleobase(char codeR, char codeD) { this.codeR = codeR; this.codeD = codeD; }
		/** a 2d-matrix defining the pairing of the nucleobases */
		private static final Nucleobase[][] pairs = new Nucleobase[][]{
			{Nucleobase.G,Nucleobase.C},{Nucleobase.C,Nucleobase.G},{Nucleobase.U,Nucleobase.A},{Nucleobase.A,Nucleobase.U}
		};
		/** returns the base corresponding to {@code this} according to {@link #pairs} */
		public Nucleobase getPair() { return Stream.of(pairs).filter(e->e[0]==this).findFirst().get()[1]; }
		/** outputs the RNA key */
		public char RNAcode() { return this.codeR; }
		/** outputs the DNA key */
		public char DNAcode() { return this.codeD; }
	}
	/**
	 * an enum type for all proteinogenic amino acids
	 * <p> also contains a STOP and START for codon interpretation
	 */
	public static enum AminoAcid {
		/** stop codon */
		STOP(' ',"","stopping..."),
		/** start codon */
		START(' ',"","starting..."),
		/** Phenylalanine, Phe, F */
		F('F',"Phe","Phenylalanine"),
		/** Leucine, Leu, L */
		L('L',"Leu","Leucine"),
		/** Isoleucin, Ile, I */
		I('I',"Ile","Isoleucin"),
		/** Methionine, Met, M */
		M('M',"Met","Methionine"),
		/** Valine, Val, V */
		V('V',"Val","Valine"),
		/** Serine, Ser, S */
		S('S',"Ser","Serine"),
		/** Proline, Pro, P */
		P('P',"Pro","Proline"),
		/** Threonine, Thr, T */
		T('T',"Thr","Threonine"),
		/** Alanine, Ala, A */
		A('A',"Ala","Alanine"),
		/** Tyrosine, Tyr, Y */
		Y('Y',"Tyr","Tyrosine"),
		/** Histidine, His, H */
		H('H',"His","Histidine"),
		/** Glutamine, Gln, Q */
		Q('Q',"Gln","Glutamine"),
		/** Aparagine, Asn, N */
		N('N',"Asn","Aparagine"),
		/** Lysine, Lys, K */
		K('K',"Lys","Lysine"),
		/** Aspartic Acid, Asp, D */
		D('D',"Asp","Aspartic-Acid"),
		/** Glutamic Acid, Glu, E */
		E('E',"Glu","Glutamic-Acid"),
		/** Cysteine, Cys, C */
        C('C',"Cys","Cysteine"),
		/** Tryptophan, Trp, W */
		W('W',"Trp","Tryptophan"),
		/** Arginine, Arg, R */
        R('R',"Arg","Arginine"),
		/** Glycine, Gly, G */
		G('G',"Gly","Glycine");
		/** constructor defining the processing of the given constants */
		private AminoAcid(char code, String shortCode, String name){
			this.shortCode = shortCode; this.name = name; this.code = code;
		}
		/** Amino acids internal storage for their 3-letter-code */
		private final String shortCode;
		/** Amino acids internal storage for their full names */
		private final String name;
		/** Amino acids internal storage for their 1-letter-code */
		private final char code;
		/** outputs the 3-letter-code */
		public String SC(){ return shortCode; }
		/** outputs the name */
		public String N(){ return name; }
		/** outputs the 1-letter-code */
		public char C(){ return code; }
	}
}
