import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateBig {

	public static void main(String[] args) {
		
		checkUsage(args);

		int N = Integer.parseInt(args[0]);
		String mtxFileGuts = "%%MatrixMarket matrix coordinate real general\n";
		mtxFileGuts += "% Generated automatically using java CreateBig "+N+"\n";
		mtxFileGuts += "" + N + " " + N + " " + (N * N - N) + "\n";
		Random rand = new Random();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i!=j) {
					mtxFileGuts += "" + i + "  " + j + "  " + rand.nextFloat()+ "\n";
				}
			}
		}

		writeToOutfile("big" + N + ".mtx", mtxFileGuts);
	}


	public static void writeToOutfile(String filename, String contents) {
		try {
			FileWriter outfile = new FileWriter(filename);
			outfile.write(contents);
			outfile.close();
		} catch (IOException e) {
			System.out.println("ERROR: IOException");
			System.exit(1);
		}
	}

	private static void checkUsage(String[] args) {
		if (args.length < 1) {
			System.err.println("usage: java CreateBig N");
			System.exit(1);
		}
	}
}
