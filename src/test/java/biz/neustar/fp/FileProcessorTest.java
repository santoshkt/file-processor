package biz.neustar.fp;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileProcessorTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private static final StringBuilder sb = new StringBuilder();
	private static final String inputFile = "tempFile" + Math.random();

	@BeforeClass
	public static void buildInputFile() {

		try {

			File file = new File(inputFile);

			// If file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("PERSON Bob Jones\n");
			bw.write("PLACE Washington\n");
			bw.write("PERSON Mary\n");
			bw.write("COMPUTER Mac\n");
			bw.write("PERSON Bob Jones\n");
			bw.write("OTHER Tree\n");
			bw.write("ANIMAL Dog\n");
			bw.write("PLACE Texas\n");
			bw.write("FOOD Steak\n");
			bw.write("ANIMAL Cat\n");
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public static void buildOutput() {

		sb.append("CATEGORY COUNT\r\n");
		sb.append("PERSON 2\r\n");
		sb.append("PLACE 2\r\n");
		sb.append("ANIMAL 2\r\n");
		sb.append("COMPUTER 1\r\n");
		sb.append("OTHER 1\r\n");
		sb.append("PERSON Bob Jones\r\n");
		sb.append("PLACE Washington\r\n");
		sb.append("PERSON Mary\r\n");
		sb.append("COMPUTER Mac\r\n");
		sb.append("OTHER Tree\r\n");
		sb.append("ANIMAL Dog\r\n");
		sb.append("PLACE Texas\r\n");
		sb.append("ANIMAL Cat\r\n");
	}

	@Before
	public void setSystemIO() {
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(err));
	}

	@Test
	public void testFileProcessor() {
		FileProcessor.main(new String[] { inputFile });
		assertEquals(sb.toString(), out.toString());
	}

	@Test
	public void testFileProcessorAgain() {
		FileProcessor.main(new String[] { inputFile });
		assertEquals(sb.toString(), out.toString());
	}

	@Test
	public void testFileProcessorNoFileName() {
		FileProcessor.main(new String[] {});
		assertEquals(
				"Usage: java biz.neustar.fp.FileProcessor <input_file_path>\r\n",
				out.toString());
	}

	@Test
	public void testFileProcessorInvalidFileName() {
		FileProcessor.main(new String[] { "invalid" });
		assertEquals(
				"Error: Input file does not exist or the path is incorrect.\r\n",
				out.toString());
	}
	
	@After
	public void clearSystemIO() {
		System.setOut(null);
		System.setErr(null);
	}

	@AfterClass
	public static void deleteInputFile() {

		try {
			File file = new File(inputFile);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
