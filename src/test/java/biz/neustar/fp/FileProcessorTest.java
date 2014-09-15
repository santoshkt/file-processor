package biz.neustar.fp;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileProcessorTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private static final StringBuilder sb = new StringBuilder();

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
		FileProcessor.main(new String[] { "src/test/resources/inputData.txt" });
		assertEquals(sb.toString(), out.toString());
	}
	
	@Test
	public void testFileProcessorAgain() {
		FileProcessor.main(new String[] { "src/test/resources/inputData.txt" });
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

}
