package project3;

public class Main {
	private static byte[] seq1 = new byte[14];
	private static byte[] seq2 = new byte[16];
	private static byte[] seq3 = new byte[18];

	private static byte[] curKey1 = new byte[14];
	private static byte[] maxKey1 = new byte[14];
	private static double max1 = 0;

	private static byte[] curKey2 = new byte[16];
	private static byte[] maxKey2 = new byte[16];
	private static double max2 = 0;

	private static byte[] curKey3 = new byte[18];
	private static byte[] maxKey3 = new byte[18];
	private static double max3 = 0;

	private static byte[] c;

	public static void main(String[] args) {
		String s = "1001111100111010111110110001100101001000100" + "111010101010100011111100010011011000000010"
				+ "011010011110010010010010101010001110100111" + "001101111111000110110101010000000101101100"
				+ "011001101000100111000100";
		int len = s.length();
		c = new byte[len];

		for (int i = 0; i < len; i++)
			c[i] = (byte) (s.charAt(i) - '0');

		generateKey1(13);
		System.out.println(max1 + 0.5);
		for (int i = 0; i < maxKey1.length; i++)
			System.out.print(maxKey1[i] + " ");

		System.out.println();
		generateKey2(15);
		System.out.println(max2 + 0.5);
		for (int i = 0; i < maxKey2.length; i++)
			System.out.print(maxKey2[i] + " ");

		System.out.println();
		generateKey3(17);
		System.out.println(max3 + 0.5);
		for (int i = 0; i < maxKey3.length; i++)
			System.out.print(maxKey3[i] + " ");

		byte[] t = generateSeq(maxKey1, maxKey2, maxKey3, len);
		System.out.println("\n");
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] - t[i] + " ");
		}
	}

	public static void generateKey1(int i) {
		if (i == 0) {
			byte[] seq = generateSeq1(curKey1, c.length);
			double dist = hammingDist(c, seq);
			double d = Math.abs(0.5 - dist);

			if (max1 <= d) {
				max1 = d;
				for (int j = 0; j < curKey1.length; j++)
					maxKey1[j] = curKey1[j];
			}
		} else {
			curKey1[i] = 0;
			generateKey1(i - 1);
			curKey1[i] = 1;
			generateKey1(i - 1);
		}
	}

	public static void generateKey2(int i) {
		if (i == 0) {
			byte[] seq = generateSeq2(curKey2, c.length);
			double dist = hammingDist(c, seq);
			double d = Math.abs(0.5 - dist);

			if (max2 < d) {
				max2 = d;
				for (int j = 0; j < curKey2.length; j++)
					maxKey2[j] = curKey2[j];
			}
		} else {
			curKey2[i] = 0;
			generateKey2(i - 1);
			curKey2[i] = 1;
			generateKey2(i - 1);
		}
	}

	public static void generateKey3(int i) {
		if (i == 0) {
			byte[] seq = generateSeq3(curKey3, c.length);
			double dist = hammingDist(c, seq);
			double d = Math.abs(0.5 - dist);

			if (max3 < d) {
				max3 = d;
				for (int j = 0; j < curKey3.length; j++)
					maxKey3[j] = curKey3[j];
			}
		} else {
			curKey3[i] = 0;
			generateKey3(i - 1);
			curKey3[i] = 1;
			generateKey3(i - 1);
		}
	}

	public static byte[] generateSeq1(byte[] key, int len) {
		byte[] seq = new byte[len];

		for (int i = 0; i < key.length; i++)
			seq1[i] = key[i];

		for (int i = 0; i < len; i++)
			seq[i] = next1();
		return seq;
	}

	public static byte[] generateSeq2(byte[] key, int len) {
		byte[] seq = new byte[len];

		for (int i = 0; i < key.length; i++)
			seq2[i] = key[i];

		for (int i = 0; i < len; i++)
			seq[i] = next2();
		return seq;
	}

	public static byte[] generateSeq3(byte[] key, int len) {
		byte[] seq = new byte[len];

		for (int i = 0; i < key.length; i++)
			seq3[i] = key[i];

		for (int i = 0; i < len; i++)
			seq[i] = next3();
		return seq;
	}

	public static byte[] generateSeq(byte[] key1, byte[] key2, byte[] key3, int len) {

		for (int i = 0; i < key1.length; i++)
			seq1[i] = key1[i];

		for (int i = 0; i < key2.length; i++)
			seq2[i] = key2[i];

		for (int i = 0; i < key3.length; i++)
			seq3[i] = key3[i];

		byte[] seq = new byte[len];
		for (int i = 0; i < len; i++) {
			int next = next1() + next2() + next3();
			if (next > 1)
				seq[i] = 1;
			else
				seq[i] = 0;
		}
		return seq;
	}

	public static double hammingDist(byte[] fst, byte[] snd) {
		int dist = 0;
		for (int i = 0; i < fst.length; i++)
			if (fst[i] != snd[i])
				dist++;
		return 1 - ((double) dist) / fst.length;
	}

	public static byte next1() {
		byte temp = (byte) ((seq1[14 - 1] + seq1[14 - 2] + seq1[14 - 4] + seq1[14 - 6] + seq1[14 - 7] + seq1[14 - 10]
				+ seq1[14 - 11] + seq1[14 - 13]) % 2);
		for (int i = 1; i < seq1.length; i++)
			seq1[i - 1] = seq1[i];
		seq1[13] = temp;
		return seq1[0];

	}

	public static byte next2() {
		byte temp = (byte) ((seq2[16 - 2] + seq2[16 - 4] + seq2[16 - 6] + seq2[16 - 7] + seq2[16 - 10] + seq2[16 - 11]
				+ seq2[16 - 13] + seq2[16 - 15]) % 2);
		for (int i = 1; i < seq2.length; i++)
			seq2[i - 1] = seq2[i];
		seq2[15] = temp;
		return seq2[0];
	}

	public static byte next3() {
		byte temp = (byte) ((seq3[18 - 2] + seq3[18 - 4] + seq3[18 - 5] + seq3[18 - 8] + seq3[18 - 10] + seq3[18 - 13]
				+ seq3[18 - 16] + seq3[18 - 17]) % 2);
		for (int i = 1; i < seq3.length; i++)
			seq3[i - 1] = seq3[i];
		seq3[17] = temp;
		return seq3[0];
	}
}