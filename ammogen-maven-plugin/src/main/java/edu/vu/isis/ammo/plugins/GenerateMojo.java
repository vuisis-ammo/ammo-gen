package edu.vu.isis.ammo.plugins;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import edu.vu.isis.ammo.generator.Generator;
import edu.vu.isis.ammo.generator.GeneratorException;

/**
 * Goal which generates code for Ammo enabled applications.
 * 
 * @goal generate
 * 
 * @phase generate-sources
 */
public class GenerateMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException {
		final Generator generator = new Generator(new MavenLoggerImpl(this.getLog(), "ammogen"));
		generator.setSponsored(this.mkdir);
		generator.setSuffix(this.suffix);
		generator.setTemplate(this.template);
		generator.setTemplateFile(this.templateFile);
		
		generator.setContractPath(this.contract);
		generator.setOutputDir(this.outputPath);
		
		generator.requestProviderImpl(this.provider);
		generator.requestProviderSchema(this.schema);
		generator.requestSerializer(this.serializer);
		
		generator.requestSkeleton(this.skeleton);
		generator.requestBase(this.base);

		generator.requestMockProvider(this.makeMockProvider);
		try {
			generator.build();
		} catch (GeneratorException ex) {
			this.getLog().error("build failed", ex);
		}
		return;
	}

	public static final String namespaceURI = "http://ammo.isis.vanderbilt.edu/content_provider";

	/**
	 * skeleton : has the skeleton target been requested?
	 * <p>
	 * The skeleton is a class file which inherits from the 
	 * corresponding schema or provider base.
	 * It is expected that the skeleton will be modified by the developer.
	 * 
	 * @parameter expression="${generate.skeleton}" default-value="false"
	 */
	private boolean skeleton = false;
	/**
	 * base : has the base target been requested?
	 * <p>
	 * The base is a class file which provides an abstract class
	 * from the 
	 * corresponding schema or provider inherit.
	 * The skeleton inherits from the base.
	 * 
	 * @parameter expression="${generate.base}" default-value="true"
	 */
	private boolean base = true;
	/**
	 * schema : has the schema target been requested?
	 * <p>
	 * The schema provides the data structures and constants used
	 * by on consumer of the content providers services.
	 * 
	 * @parameter expression="${generate.schema}" default-value="true"
	 */
	private boolean schema = true;
	/**
	 * provider : has the provider target been requested?
	 * <p>
	 * The provider is the content provider implementation.
	 * 
	 * @parameter expression="${generate.provider}" default-value="true"
	 */
	private boolean provider = true;
	/**
	 * serializer : has the serializer service been requested?
	 * <p>
	 * The serializer provides a service with is used by ammo to
	 * communicate efficiently with the content provider.
	 * It can also be used to bypass the content provider completely.
	 * 
	 * @parameter expression="${generate.serializer}" default-value="false"
	 */
	private boolean serializer = false;
	/**
	 * mockProvider : has the mock content provider been requested?
	 * <p>
	 * The mock provider is to assist in testing.  It is very similar to 
	 * the provider, sharing much code, but it inherits from MockContentProvider
	 * rather than ContentProvider.
	 * 
	 * @parameter expression="${generate.mockProvider}" default-value="false"
	 */
	private boolean makeMockProvider = false;

	/**
	 * mkdir : should the java package directory path be constructed?
	 * <p>
	 * Java classes are generally placed in a chain of directories 
	 * which mimic the java package to which the class belongs.
	 * 
	 * @parameter expression="${generate.mkdir}" default-value="false"
	 */
	private boolean mkdir = true;

	/**
	 * outputPath : the base path for the generated java source files.
	 * <p>
	 * 
	 * 
	 * @parameter expression="${generate.outputPath}" 
	 *    default-value="${basedir}/target/generated-sources/ammogen"
	 */
	private File outputPath = new File("gen");
	/**
	 * template : what is the name of template to be used to generate the output.
	 * <p>
	 * This will typically be the name of one of the included string-template group files.
	 * 
	 * @parameter expression="${generate.template}" default-value="content-provider-schema"
	 */
	private String template = null;
	/**
	 * templateFile : what is the file containing the string-template to be used to generate the output.
	 * <p>
	 * If provided this trumps the template parameter.  It must point to the specific string-template group file.
	 * 
	 * @parameter expression="${generate.template}" default-value=""
	 */
	private File templateFile = null;
	/**
	 * contract : what is the file expressing the data contract.
	 * <p>
	 * This file provides the file name and its path.
	 * 
	 * @parameter expression="${generate.contract}"
	 *            default-value="contract.xml""
	 */
	private File contract = new File("contract.xml");
	/**
	 * suffix : an alternate suffix for the generated files.
	 * <p>
	 * Normally the file suffix is determined by the template selected.
	 * There are cases where some other suffix is useful.
	 * This suffix will be provided to the file name generating template.
	 * 
	 * @parameter expression="${generate.suffix}" default-value=".java"
	 */
	private String suffix = ".java";

}
