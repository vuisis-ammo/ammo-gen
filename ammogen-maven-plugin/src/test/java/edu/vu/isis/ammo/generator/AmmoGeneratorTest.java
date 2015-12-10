package edu.vu.isis.ammo.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junitx.framework.FileAssert;

/**
 * Unit test for simple AmmoGenerator.
 */
public class AmmoGeneratorTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AmmoGeneratorTest(String testName) {
		super(testName);
	}

	/**
	 * lifted from guavac
	 */
	private static final int TEMP_DIR_ATTEMPTS = 10000;

	public static File createTempDir(final String prefix) {
		final File baseDir = new File(System.getProperty("java.io.tmpdir"));
		final String baseName = prefix + System.currentTimeMillis() + "-";

		for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
			File tempDir = new File(baseDir, baseName + counter);
			if (tempDir.mkdir()) {
				return tempDir;
			}
		}
		throw new IllegalStateException("Failed to create directory within "
				+ TEMP_DIR_ATTEMPTS + " attempts (tried " + baseName + "0 to "
				+ baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AmmoGeneratorTest.class);
	}

	/**
	 * Empty Test
	 */
	public void testNoArgs() {
		// AmmoGenerator.main(null);
		assertTrue(true);
	}

	/**
	 * simple Test
	 * 
	 * @throws IOException
	 */
	public void testBasicContentProvider() throws IOException {
		final File outputDir = createTempDir("ammogen-bcp");
		final File contractfile = new File(this.getClass()
				.getResource("/samples/incident.xml").getFile());

		AmmoGenerator
				.main(new String[] { "skeleton=false", "schema=true",
						"provider=true", "mkdir=false", "output=" + outputDir,
						"template=content-provider-schema",
						"contract=" + contractfile });
		final File expectedProviderFile = new File(this.getClass()
				.getResource("/results/bcp/IncidentProviderBase.java")
				.getFile());
		final File actualProviderFile = new File(outputDir,
				"IncidentProviderBase.java");
		FileAssert.assertBinaryEquals(expectedProviderFile, actualProviderFile);

		final File expectedSchemaFile = new File(this.getClass()
				.getResource("/results/bcp/IncidentSchemaBase.java").getFile());
		final File actualSchemaFile = new File(outputDir,
				"IncidentSchemaBase.java");
		FileAssert.assertBinaryEquals(expectedSchemaFile, actualSchemaFile);
	}

	/**
	 * simple Test
	 * 
	 * @throws IOException
	 */
	public void testBasicGatewayPlugin() throws IOException {
		final File outputDir = createTempDir("ammogen-bgp");
		final URL url = this.getClass().getResource("/samples/incident.xml");
		final File contractfile = new File(url.getFile());

		AmmoGenerator.main(new String[] { "skeleton=false", "schema=false",
				"provider=false", "mkdir=false", "output=" + outputDir,
				"template=gateway-plugin", "contract=" + contractfile });
		assertTrue(true);
	}

	/**
	 * simple Test
	 * 
	 * @throws IOException
	 */
	public void testBasicGatewayPluginSerialization() throws IOException {
		final File outputDir = createTempDir("ammogen-bgps");
		final URL url = this.getClass().getResource("/samples/incident.xml");
		final File contractfile = new File(url.getFile());

		AmmoGenerator.main(new String[] { "skeleton=false", "schema=false",
				"provider=false", "mkdir=false", "output=" + outputDir,
				"template=gateway-plugin-serialization",
				"contract=" + contractfile });
		assertTrue(true);
	}
}
