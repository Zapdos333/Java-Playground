package com.Ace009.nonLibrary.school;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.Ace009.library.Range;

/**
 * A class for working with {@link AminoAcid amino acids} and their corresponing RNA {@link #CODES codons}
 * @author Ace009
 */
public class RNATranslation {
	/**
	 * a Map linking 3 {@link Nucleobase} with the {@link AminoAcid}
	 */
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
	/**
	 * an enum type for the 4(5) Nucleobases
	 */
	public static enum Nucleobase {
		/** Uracil/Thymine */
		U("Uracil","Thymine"),
		/** Cytosine */
		C("Cytosine","Cytosine"),
		/** Adenine */
		A("Adenine","Adenine"),
		/** Guanine */
		G("Guanine","Guanine");
		/** first letter of {@link #nameR RNA-name} */
		private final char codeR;
		/** first Letter of {@link #nameD DNA-name} */
		private final char codeD;
		/** name in RNA */
		private final String nameR;
		/** name in DNA */
		private final String nameD;
		/** constructor defining the processing of the given constants
		 * @param nameR name in RNA
		 * @param nameD name in DNA
		 */
		private Nucleobase(String nameR, String nameD) {
			this.codeR = nameR.toCharArray()[0]; this.codeD = nameD.toCharArray()[0];
			this.nameR = nameR; this.nameD=nameD;
		}
		/** a 2d-matrix defining the pairing of the nucleobases */
		private static final Nucleobase[][] pairs = new Nucleobase[][]{
			{Nucleobase.G,Nucleobase.C},{Nucleobase.C,Nucleobase.G},{Nucleobase.U,Nucleobase.A},{Nucleobase.A,Nucleobase.U}
		};
		/** @return the base corresponding to {@code this} according to {@link #pairs} */
		public Nucleobase getPair() { return Stream.of(pairs).filter(e->e[0]==this).findFirst().get()[1]; }
		/** @return the RNA key */
		public char RC() { return this.codeR; }
		/** @return the DNA key */
		public char DC() { return this.codeD; }
		/** @return the RNA name */
		public String RN() { return this.nameR; }
		/** @return the DNA name */
		public String DN() { return this.nameD; }
	}
	/**
	 * an enum type for all proteinogenic amino acids
	 * <p> also contains a STOP for codon interpretation
	 */
	public static enum AminoAcid {
		/** stop codon */
		STOP(' ',"","stopping..."),
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
		/** constructor defining the processing of the given constants
		 * @param code one-letter-code of the AminoAcid
		 * @param shortCode 3-letter-code of the AminoAcid
		 * @param name full name of the AminoAcid
		 */
		private AminoAcid(char code, String shortCode, String name){
			this.shortCode = shortCode; this.name = name; this.code = code;
		}
		/** Amino acids internal storage for their 3-letter-code */
		private final String shortCode;
		/** Amino acids internal storage for their full names */
		private final String name;
		/** Amino acids internal storage for their 1-letter-code */
		private final char code;
		/** @return the 3-letter-code */
		public String SC(){ return shortCode; }
		/** @return the name */
		public String N(){ return name; }
		/** @return the 1-letter-code */
		public char C(){ return code; }
	}
	/**
	 * gives a {@code List} of {@code Nucleobase} triplets that match the given {@code AminoAcid},
	 * according to {@link #CODES}
	 * @param in the given {@code AminoAcid}
	 * @return a {@code List} of all matching {@code Nucleobase} triplets
	 */
	@SuppressWarnings("unused") // temp until used
	private static List<Nucleobase[]> getTriplets(AminoAcid in) {
		return CODES.entrySet().stream().filter(e->e.getValue()==in).map(e->e.getKey()).toList();
	}
	/**
	 * decodes the given {@code Nucleobase} triplet, according to {@link #CODES},
	 * returns the corresponding {@code AminoAcid} and throws {@code IllegalArgumentException}
	 * if the {@code Nucleobase} array is not a triplet ({@code in.length!=3})
	 * @param in the given {@code Nucleobase} triplet to decode
	 * @return the corresponding {@code AminoAcid}
	 * @throws IllegalArgumentException thrown if {@code in.length!=3}
	 */
	private static AminoAcid decodeTriplet(Nucleobase[] in) throws IllegalArgumentException {
		if (in.length==3) {
			AminoAcid t_ = CODES.get(in);
			if (t_==null) throw new NullPointerException("no corresponding AminoAcid found");
			return t_;
		}
		else {throw new IllegalArgumentException("no Triplet provided");}
	}
	/**
	 * decodes an array of {@code Nucleobase} by seperating them into triplets,
	 * and parsing them with {@link #decode(Nucleobase[][])}
	 * @param in the array of {@code Nucleobase}
	 * @return a List of {@code AminoAcid}
	 */
	public static List<AminoAcid> decode(Nucleobase[] in) {
		assert in.length%3==0;
		List<Nucleobase> i_ = Stream.of(in).toList();
		Nucleobase[][] t_ = new Nucleobase[3][in.length/3];
		for (int i : Range.arrayRange(t_.length)) {
			t_[i]=i_.subList(i*3, (i+1)*3).toArray(Nucleobase[]::new);
		}
		return decode(t_);
	}
	/**
	 * decodes an array of {@code Nucleobase} triplets by taking them,
	 * and parsing them with {@link #decodeTriplet(Nucleobase[])}
	 * @param in the array of {@code Nucleobase}
	 * @return a List of {@code AminoAcid}
	 */
	public static List<AminoAcid> decode(Nucleobase[][] in) {
		assert Stream.of(in).allMatch(e->e.length==3);
		List<AminoAcid> out = new ArrayList<>();
		for (Nucleobase[] t : in) {
			out.add(decodeTriplet(t));
		}
		return out;
	}
}
