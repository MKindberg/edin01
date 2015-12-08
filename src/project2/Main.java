package project2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		byte[] b5 = new byte[4];
		byte[] b2 = new byte[4];
		byte t5 = 0;
		byte t2 = 0;
		byte[] res = new byte[10003];
		for (int i = 0; i < res.length; i++) {
			t5 = next5(b5);
			t2 = next2(b2);
			res[i] = (byte) (t2 + t5 * 2);
		}

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("out.txt"));

			for (int i = 0; i < res.length; i++) {
				pw.print(res[i]);
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static byte next2(byte[] b) {
		byte temp;
		if (b[3] == 0 && b[2] == 0 && b[1] == 0 && b[0] == 0)
			temp = 1;
		else if (b[3] == 0 && b[2] == 0 && b[1] == 0)
			temp = 0;
		else
			temp = (byte) ((b[0] + b[3]) % 2);
		for (int j = 1; j < 4; j++)
			b[j - 1] = b[j];
		b[3] = temp;
		return b[0];
	}

	public static byte next5(byte[] b) {
		byte temp;

		if (b[3] == b[1] && b[3] == b[2] && b[0] == 0 && b[3] == 0)
			temp = 3;
		else if (b[3] == b[1] && b[3] == b[2] && b[3] == 0 && b[0] == 1)
			temp = 0;
		else
			temp = (byte) ((3 * b[0] + 3 * b[2] + 4 * b[3]) % 5);
		for (int j = 1; j < 4; j++)
			b[j - 1] = b[j];
		b[3] = temp;
		return b[0];
	}

	public static String toString(byte[] b) {
		String s = "";
		for (int i = 0; i < b.length; i++)
			s += b[i];
		return s;
	}
}
