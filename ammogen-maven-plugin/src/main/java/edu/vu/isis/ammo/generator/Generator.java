package edu.vu.isis.ammo.generator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The actual generator code.
 */
public class Generator {
	final private Logger logger;

	public Generator(final Logger logger) {
		this.logger = logger;
	}

	/**
	 * Decide which template group is being used.
	 */
	public enum TemplateGroup {
		CONTENT_PROVIDER(1, "content-provider-schema",
				"/templates/java/ammo_content_provider.stg"), GATEWAY_PLUGIN(2,
				"gateway-plugin", "/templates/cpp/gateway_plugin.stg"), PLUGIN_SERIALIZATION(
				3, "gateway-plugin-serialization",
				"/templates/cpp/gateway_plugin_serialization.stg");

		public final int nominal;
		public final String alias;
		public final String path;

		private static final Map<String, TemplateGroup> byName = new HashMap<String, TemplateGroup>();
		static {
			for (TemplateGroup item : TemplateGroup.values()) {
				TemplateGroup.byName.put(item.alias, item);
			}
		}

		private TemplateGroup(int nominal, String alias, String path) {
			this.nominal = nominal;
			this.alias = alias;
			this.path = path;
		}

		public static boolean hasByName(String templateAlias) {
			return TemplateGroup.byName.containsKey(templateAlias);
		}

		public static TemplateGroup getByName(String templateAlias) {
			return TemplateGroup.byName.get(templateAlias);
		}

		/**
		 * 
		 * @return a comma separated list of the keys
		 */
		public static String getKeys() {
			final StringBuilder sb = new StringBuilder();
			boolean isFirst = true;
			for (TemplateGroup item : TemplateGroup.values()) {
				if (isFirst) {
					isFirst = false;
				} else {
					sb.append(",");
				}
				sb.append(item.alias);
			}
			return sb.toString();
		}

	}

	public static final String namespaceURI = "http://ammo.isis.vanderbilt.edu/content_provider";

	/**
	 * The configuration options.
	 */
	private boolean isBase = true;
	private boolean isSkeleton = false;
	private boolean isSchema = true;
	private boolean isProvider = true;
	private boolean isSerializer = false;
	private boolean makeMockProvider = false;

	public void requestMockProvider(boolean val) {
		this.makeMockProvider = val;
	}

	public void requestBase(final boolean val) {
		this.isBase = val;
	}

	/**
	 * @deprecated
	 * @param val
	 */
	public void setSkeleton(String val) {
		if ("both".equalsIgnoreCase(val)) {
			this.isSkeleton = false;
			this.isBase = true;
		}
		this.isSkeleton = Boolean.parseBoolean(val);
	}

	public void requestSkeleton(final boolean val) {
		this.isSkeleton = val;
	}

	public void setSchema(String val) {
		this.isSchema = Boolean.parseBoolean(val);
	}

	public void requestProviderSchema(final boolean val) {
		this.isSchema = val;
	}

	public void setProvider(String val) {
		this.isProvider = Boolean.parseBoolean(val);
	}

	public void requestProviderImpl(boolean val) {
		this.isProvider = val;
	}

	public void setSerializer(String val) {
		this.isSerializer = Boolean.parseBoolean(val);
	}

	public void requestSerializer(final boolean val) {
		this.isSerializer = val;
	}

	private boolean isSponsored = true;
	private File outputPath = new File("gen");
	private String templateGroup = null;

	private File templateFile = null;

	private File contractFile = new File("contract.xml");
	private String suffix = ".java";

	public void setSponsored(String val) {
		this.isSponsored = Boolean.parseBoolean(val);
	}

	public void setSponsored(final Boolean val) {
		this.isSponsored = val;
	}

	public void setOutputDir(final File val) {
		this.outputPath = val;
	}

	public void setTemplate(String val) {
		this.templateGroup = val;
	}

	public void setTemplateFile(final File val) {
		this.templateFile = val;
	}

	public void setContractPath(final File contract) {
		this.contractFile = contract;
	}

	public void setSuffix(String val) {
		this.suffix = val;
	}

	/**
	 * convert the string to snake case
	 * 
	 * @param name
	 * @return
	 */
	static public String snake_case(final String name) {
		final StringBuilder sb = new StringBuilder();
		for (String seg : name.split(" ")) {
			sb.append(seg.toLowerCase());
			sb.append('_');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * convert the string to camel case
	 * 
	 * @param name
	 * @return
	 */
	static public String camelCase(final String name) {
		if (name.length() < 1)
			return "";
		final StringBuilder sb = new StringBuilder();
		final String[] sl = name.split(" ");
		if (sl.length < 1)
			return "";
		sb.append(sl[0].toLowerCase());
		for (int ix = 1; ix < sl.length; ++ix) {
			if (sl[ix].length() < 1)
				continue;
			sb.append(sl[ix].substring(0, 1).toUpperCase());
			if (sl[ix].length() > 0)
				sb.append(sl[ix].substring(1).toLowerCase());
		}
		return sb.toString();
	}

	/**
	 * Used for multiple presentation types.
	 */
	static class Name {
		private final String norm;
		private final String camel;
		private final String snake;

		public String getNorm() {
			return norm;
		}

		public String getCamel() {
			return camel;
		}

		public String getSnake() {
			return snake;
		}

		private Name(final String norm, final String camel, final String snake) {
			this.norm = norm;
			this.camel = camel;
			this.snake = snake;
		}

		public Name(final String name) {
			this(name, camelCase(name), snake_case(name));
		}

		public Name(final NodeList seq) {
			this(seq.item(0).getTextContent());
		}

		private static String capitalize(String str) {
			if (str.length() < 1)
				return "";
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}

		@Override
		public String toString() {
			return this.norm;
		}
		
		public String getCobra() {
			return snake.toUpperCase();
		}

		public String getBactrian() {
			return capitalize(camel);
		}
	}

	/**
	 * The root object
	 */
	static class Contract {
		final private Name name;
		final private String sponsor;
		final private List<Relation> relations;

		public Name getName() {
			return name;
		}

		public String getSponsor() {
			return sponsor;
		}

		public File getSponsorPath(final File basedir) {
			File wip = basedir.getAbsoluteFile();
			for (String dir : this.sponsor.split("\\.")) {
				wip = new File(wip, dir);
			}
			wip = new File(wip, "provider");
			return wip;
		}

		public List<Relation> getRelations() {
			return relations;
		}

		private Contract(final Name name, final String sponsor,
				List<Relation> relations) {
			this.name = name;
			this.sponsor = sponsor;
			this.relations = relations;
		}

		static public Contract newInstance(final Element xml) {
			String sponsor = "";
			final NodeList sl = xml.getElementsByTagName("sponsor");
			for (int ix = 0; ix < sl.getLength();) {
				Element el = (Element) sl.item(ix);
				sponsor = el.getAttribute("name");
				break;
			}

			final List<Relation> relation_set = new ArrayList<Relation>();
			final NodeList nl = xml.getElementsByTagName("relation");
			for (int ix = 0; ix < nl.getLength(); ++ix) {
				final Relation relation = Relation.newInstance((Element) nl
						.item(ix));
				relation_set.add(relation);
			}

			return new Contract(extract_name(xml, "name"), sponsor,
					relation_set);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<provider name=\"").append(name.norm).append("\" ")
					.append(" sponsor=\"").append(sponsor).append("\">\n");
			for (Relation relation : this.relations) {
				sb.append(relation.toString());
			}
			sb.append("\n</provider>");
			return sb.toString();
		}
	}

	/**
	 * Collect table information.
	 */
	static class Relation {
		final private Name name;
		final private RMode mode;
		final private List<Field> fields;
		final private List<FieldRef> keycols;

		public Name getName() {
			return name;
		}

		public RMode getMode() {
			return mode;
		}

		public List<Field> getFields() {
			return fields;
		}

		public List<FieldRef> getKeycols() {
			return keycols;
		}

		private Relation(final Name name, final RMode mode,
				final List<Field> fields, final List<FieldRef> keycols) {
			this.name = name;
			this.mode = mode;
			this.fields = fields;
			this.keycols = keycols;
		}

		static public Relation newInstance(final Element xml) {
			final List<Field> field_set = new ArrayList<Field>();
			final NodeList nl = xml.getElementsByTagName("field");
			for (int ix = 0; ix < nl.getLength(); ++ix) {
				field_set.add(Field.newInstance((Element) nl.item(ix)));
			}

			// process the key columns
			final List<FieldRef> keycol_set = new ArrayList<FieldRef>();
			final NodeList kl = xml.getElementsByTagName("key");
			for (int ix = 0; ix < kl.getLength(); ++ix) {
				NodeList rl = xml.getElementsByTagName("ref");
				for (int jx = 0; jx < rl.getLength(); ++jx) {
					keycol_set.add(FieldRef.newInstance((Element) rl.item(jx)));
				}
			}

			RMode mode = null;
			final NodeList ml = xml.getElementsByTagName("mode");
			for (int ix = 0; ix < ml.getLength();) {
				mode = RMode.newInstance((Element) ml.item(ix));
				break;
			}

			return new Relation(extract_name(xml, "name"), mode, field_set,
					keycol_set);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("<relation name=").append(name.norm).append(">\n");
			for (Field field : this.fields) {
				sb.append(field.toString());
			}
			for (FieldRef ref : this.keycols) {
				sb.append(ref.toString());
			}
			sb.append("\n</relation>");
			return sb.toString();
		}
	}

	static class RMode {
		private final Name name;
		private final String dtype;
		private final String description;

		public Name getName() {
			return name;
		}

		public String getDtype() {
			return dtype;
		}

		public String getDescription() {
			return description;
		}

		private RMode(final Name name, final String dtype,
				final String description) {
			this.name = name;
			this.dtype = dtype;
			this.description = description.trim();
		}

		static public RMode newInstance(Element xml) {
			final String type = xml.getAttribute("type");
			return new RMode(extract_name(xml, "name"), type,
					xml.getTextContent());
		}

		@Override
		public String toString() {
			return new StringBuilder().append("<mode name='").append(name.norm)
					.append("' type='").append(dtype).append("'>")
					.append(description).append("</field>").toString();
		}
	}

	static public Name extract_name(final Element xml, final String attr) {
		return new Name(xml.getAttribute(attr));
	}

	static public RMode extract_mode(final Element xml) {
		return null;
	}

	/**
	 * A column in a table (relation).
	 */
	static class Field {
		private final Name name;
		private final String dtype;
		private final String initial;
		private final String description;
		private final List<Enumeration> enums;

		public Name getName() {
			return name;
		}

		public String getDtype() {
			return dtype;
		}

		public String getDefault() {
			return initial;
		}

		public String getDescription() {
			return description;
		}

		public List<Enumeration> getEnums() {
			return enums;
		}

		private Field(final Name name, final String dtype,
				final String initial, final String description,
				final List<Enumeration> enum_set) {
			this.name = name;
			this.dtype = dtype;
			this.initial = initial;
			this.description = description.trim();
			this.enums = enum_set;
		}

		static public Field newInstance(final Element xml) {
			final String initial = xml.getAttribute("default");

			final List<Enumeration> enum_set = new ArrayList<Enumeration>();
			final NodeList enum_list = xml.getElementsByTagName("enum");
			for (int ix = 0; ix < enum_list.getLength(); ++ix) {
				enum_set.add(Enumeration.newInstance((Element) enum_list
						.item(ix)));
			}

			final String type = xml.getAttribute("type");
			return new Field(extract_name(xml, "name"), type, initial,
					xml.getTextContent(), enum_set);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("<field name='").append(name.norm).append("' type='")
					.append("'>");
			sb.append(description);
			for (Enumeration en : this.enums) {
				sb.append(en.toString());
			}
			sb.append("</field>\n");
			return sb.toString();
		}
	}

	static class FieldRef {
		private final Name name;

		public Name getName() {
			return name;
		}

		private FieldRef(final Name name) {
			this.name = name;
		}

		static public FieldRef newInstance(final Element xml) {
			return new FieldRef(extract_name(xml, "field"));
		}

		@Override
		public String toString() {
			return new StringBuilder().append("<ref field='").append(name.norm)
					.append("' />").toString();
		}
	}

	/**
	 * A column in a table (relation).
	 */
	static class Enumeration {
		private final Name key;
		private final int ordinal;

		public Name getKey() {
			return key;
		}

		public String getOrdinal() {
			return Integer.toString(ordinal);
		}

		private Enumeration(final Name key, final int ordinal) {
			this.key = key;
			this.ordinal = ordinal;
		}

		static public Enumeration newInstance(final Element xml) {
			Name key = new Name(xml.getAttribute("key"));
			int ordinal = Integer.parseInt(xml.getAttribute("value"));
			return new Enumeration(key, ordinal);
		}

		@Override
		public String toString() {
			return new StringBuilder().append("<enum key=\"").append(key)
					.append('"').append(' ').append("value=\"").append(ordinal)
					.append("\"/>\n").toString();
		}
	}

	/**
	 * 
	 * @param contract
	 * @param outputDir
	 * @param stFile
	 * @param stg
	 * @return
	 */
	private boolean serializeTemplate(final Contract contract,
			final File outputDir, final STGroup stg, final ST stFile,
			final ST stTemplate, final String typeString) {
		final Name type = new Name(typeString);
		addSingle(stFile, "type", type);
		final File outputFile = new File(outputDir, stFile.render());
		logger.info("file=<{}> : name:<{}> type:<{}> mode:<{}> ", outputFile,
				stFile.getAttribute("name"), type, stFile.getAttribute("mode"));

		addSingle(stTemplate, "type", type);
		final ST stTop = stg.getInstanceOf(stTemplate.render());
		if (stTop == null) {
			logger.warn("no {}", stTemplate.render());
			return false;
		}
		stTop.add("cp", contract);

		return serializeTemplateToFile(stTop, outputFile);
	}
	
	private ST addSingle(final ST template, final String key, final Object value) {
	template.remove(key);
	return template.add(key, value);
	}

	/**
	 * 
	 * @param stTop
	 * @param outputPath
	 * @return
	 */
	private boolean serializeTemplateToFile(final ST stTop,
			final File outputPath) {
		// st.inspect();
		BufferedOutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(outputPath));
			os.write(stTop.render().getBytes());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		return true;
	}

	/**
	 * The main worker which generates the source files from the contract.
	 * 
	 * @return did the build work?
	 * @throws GeneratorException
	 */
	public boolean build() throws GeneratorException {
		STGroup.trackCreationEvents = true;

		final STGroup stg;
		final TemplateGroup templateGroup;

		if ((this.templateFile == null || !this.templateFile.isFile())
				&& this.templateGroup == null) {
			// grab the default templateGroupFile

			templateGroup = TemplateGroup.CONTENT_PROVIDER;
			final URL url = this.getClass().getResource(
					TemplateGroup.CONTENT_PROVIDER.path);
			stg = new STGroupFile(url, "US-ASCII", '<', '>');
			logger.debug("default template group");
		} else {
			logger.debug("template file: {}", this.templateGroup);

			if (this.templateFile != null && this.templateFile.exists()) {
				try {
					logger.debug("template group: {}",
							templateFile.getCanonicalPath());
				} catch (IOException e1) {
					logger.error("could not open template group {}",
							templateFile, e1);
					return false;
				}
				try {
					stg = new STGroupFile(templateFile.getCanonicalPath());

					final ST foo = stg.getInstanceOf("groupType");
					final String templateGroupName = foo.render();
					logger.debug("template group name: {}", templateGroupName);
					templateGroup = TemplateGroup.getByName(templateGroupName);
					if (templateGroup == null) {
						return false;
					}
				} catch (FileNotFoundException ex) {
					logger.error("template group file not found {}",
							this.templateFile, ex);
					return false;
				} catch (IOException ex) {
					logger.error(
							"problem reading template group file not found {}",
							this.templateFile, ex);
					return false;
				}
			} else if (TemplateGroup.hasByName(this.templateGroup)) {
				templateGroup = TemplateGroup.getByName(this.templateGroup);
				logger.debug("template group=<{}>", templateGroup);
				final URL url = this.getClass().getResource(templateGroup.path);
				if (url == null) {
					logger.debug("no template url found for {}",
							templateGroup.path);
					return false;
				}
				logger.info("template group: {} url={}", templateGroup.path,
						url.toExternalForm());
				stg = new STGroupFile(url, "US-ASCII", '<', '>');
			} else {
				logger.error("template group: <{} | {}> does not exist",
						this.templateGroup, this.templateFile);
				return false;
			}
		}

		final File contractPath = this.contractFile;
		try {
			if (this.contractFile == null) {
				logger.warn("no contract specified");
				return false;
			}
			if (!this.contractFile.exists()) {
				logger.warn("invalid contract specified {}", this.contractFile);
				return false;
			}
			logger.debug("contract: {}", contractPath.getCanonicalPath());
		} catch (IOException e1) {
			logger.error("bad path for contract {}", this.contractFile);
			return false;
		}
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final Document contractXml;
		try {
			final DocumentBuilder db = dbf.newDocumentBuilder();
			contractXml = db.parse(contractPath);
			logger.info("Namespace: {}", contractXml.getNamespaceURI());

		} catch (ParserConfigurationException pce) {
			logger.error("could not parse configuration", pce);
			return false;
		} catch (SAXException se) {
			logger.error("could not parse via sax", se);
			return false;
		} catch (IOException ioe) {
			logger.error("could not open configuration", ioe);
			return false;
		}

		/**
		 * Build from templates based on contract
		 */
		final Element de = contractXml.getDocumentElement();
		final Contract contract = Contract.newInstance(de);
		logger.trace("contract {}", contract);

		final File outputDir;

		switch (templateGroup) {
		case CONTENT_PROVIDER: {
			final ST stFile = stg.getInstanceOf("fileNameTemplate");
			if (stFile == null) {
				logger.warn("no fileNameTemplate : cannot make file");
				return false;
			}
			stFile.add("name", contract.name);
			stFile.add("suffix", this.suffix);

			if (isSponsored) {
				outputDir = contract.getSponsorPath(this.outputPath);
			} else {
				outputDir = this.outputPath;
			}
			outputDir.mkdirs();
			try {
				logger.debug("output directory {}",
						outputDir.getCanonicalPath());
			} catch (IOException ex) {
				logger.warn("problem with output directory {}", outputDir);
				return false;
			}

			final ST stTemplate = stg.getInstanceOf("topTemplateTemplate");
			if (stTemplate == null) {
				logger.warn("no topTemplateTemplate");
				return false;
			}

			if (this.isSkeleton) {
				final Name mode = new Name("skel");
				addSingle(stFile, "mode", mode);
				addSingle(stTemplate, "mode", mode);

				if (this.isSchema) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "schema");
				}
				if (this.isProvider) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "provider");
				}
				if (this.isSerializer) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "service");
				}
				if (this.makeMockProvider) {
					logger.info("mock skel : not relevant");
				}
			}
			if (this.isBase) {
				final Name mode = new Name("base");
				addSingle(stFile, "mode", mode);
				addSingle(stTemplate, "mode", mode);

				if (this.isSchema) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "schema");
				}
				if (this.isProvider) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "provider");
				}
				if (this.isSerializer) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "service");
				}
				if (this.makeMockProvider) {
					serializeTemplate(contract, outputDir, stg, stFile,
							stTemplate, "mock");
				}
			}
		}
			break;
		case GATEWAY_PLUGIN: {
			outputDir = this.outputPath;
			try {
				logger.info("Output Directory: {}",
						outputDir.getCanonicalPath());
			} catch (IOException ex) {
				throw new GeneratorException(ex);
			}

			final ST mainSource = stg.getInstanceOf("topPluginMainTemplate");
			mainSource.add("cp", contract);
			final File ms = new File(outputDir, "main.cpp");
			logger.info(" main: {} ", ms);
			serializeTemplateToFile(mainSource, ms);

			final ST configMgrHeader = stg
					.getInstanceOf("topPluginCfgMgrHeaderTemplate");
			configMgrHeader.add("cp", contract);
			final File ch = new File(outputDir, contract.name.getBactrian()
					+ "ConfigManager.h");
			logger.info(" cp: {} ", ch);
			serializeTemplateToFile(configMgrHeader, ch);

			final ST configMgrSource = stg
					.getInstanceOf("topPluginCfgMgrSourceTemplate");
			configMgrSource.add("cp", contract);
			final File cs = new File(outputDir, contract.name.getBactrian()
					+ "ConfigManager.cpp");
			logger.info(" cs: {} ", cs);
			serializeTemplateToFile(configMgrSource, cs);

			final ST configFileJson = stg
					.getInstanceOf("topPluginConfigJsonTemplate");
			configFileJson.add("cp", contract);
			final File cj = new File(outputDir, contract.name.getBactrian()
					+ "PluginConfig.json");
			logger.info(" cj: {} ", cj);
			serializeTemplateToFile(configFileJson, cj);

			final ST receiverHeader = stg
					.getInstanceOf("topReceiverHeaderTemplate");
			receiverHeader.add("cp", contract);
			final File rh = new File(outputDir, contract.name.getBactrian()
					+ "Receiver.h");
			logger.info(" rh: {} ", rh);
			serializeTemplateToFile(receiverHeader, rh);

			final ST receiverSource = stg
					.getInstanceOf("topReceiverSourceTemplate");
			receiverSource.add("cp", contract);
			final File rs = new File(outputDir, contract.name.getBactrian()
					+ "Receiver.cpp");
			logger.info(" rs: {} ", rs);
			serializeTemplateToFile(receiverSource, rs);

			final ST pushHandlerHeader = stg
					.getInstanceOf("topPushHandlerHeaderTemplate");
			pushHandlerHeader.add("cp", contract);
			final File ph = new File(outputDir, contract.name.getBactrian()
					+ "PushHandler.h");
			logger.info(" ph: {} ", ph);
			serializeTemplateToFile(pushHandlerHeader, ph);

			final ST pushHandlerSource = stg
					.getInstanceOf("topPushHandlerSourceTemplate");
			pushHandlerSource.add("cp", contract);
			final File ps = new File(outputDir, contract.name.getBactrian()
					+ "PushHandler.cpp");
			logger.info(" ps: {} ", ps);
			serializeTemplateToFile(pushHandlerSource, ps);

			final ST queryHandlerHeader = stg
					.getInstanceOf("topQueryHandlerHeaderTemplate");
			queryHandlerHeader.add("cp", contract);
			final File qh = new File(outputDir, contract.name.getBactrian()
					+ "QueryHandler.h");
			logger.info(" qh: {} ", qh);
			serializeTemplateToFile(queryHandlerHeader, qh);

			final ST queryHandlerSource = stg
					.getInstanceOf("topQueryHandlerSourceTemplate");
			queryHandlerSource.add("cp", contract);
			final File qs = new File(outputDir, contract.name.getBactrian()
					+ "QueryHandler.cpp");
			logger.info(" qs: {} ", qs);
			serializeTemplateToFile(queryHandlerSource, qs);

			final ST buildFileMpc = stg.getInstanceOf("topMPCTemplate");
			buildFileMpc.add("cp", contract);
			final File bf = new File(outputDir, contract.name.getBactrian()
					+ "GatewayPlugin.mpc");
			logger.info(" bf: {} ", bf);
			serializeTemplateToFile(buildFileMpc, bf);
		}
			break;
		case PLUGIN_SERIALIZATION: {
			outputDir = this.outputPath;
			try {
				logger.info(" Output Directory: {}",
						outputDir.getCanonicalPath());
			} catch (IOException ex) {
				throw new GeneratorException(ex);
			}

			final ST serialHeader = stg
					.getInstanceOf("topPluginSerializationDeclTemplate");
			serialHeader.add("cp", contract);
			final File sh = new File(outputDir, contract.name.getBactrian()
					+ "Serialization.h");
			logger.info(" sh: {} stg: {}", sh, this.templateGroup);
			serializeTemplateToFile(serialHeader, sh);

			ST serialSource = stg
					.getInstanceOf("topPluginSerializationDefnTemplate");
			serialSource.add("cp", contract);
			File ss = new File(outputDir, contract.name.getBactrian()
					+ "Serialization.cpp");
			logger.info(" ss: {} stg: {}", ss, this.templateGroup);
			serializeTemplateToFile(serialSource, ss);

			ST schemaProto = stg.getInstanceOf("topProtoTemplate");
			schemaProto.add("cp", contract);
			File sp = new File(outputDir, contract.name.getBactrian()
					+ ".proto");
			logger.info(" sp: {} ", sp);
			serializeTemplateToFile(schemaProto, sp);

			ST serialMPC = stg
					.getInstanceOf("topPluginSerializationMPCTemplate");
			serialMPC.add("cp", contract);
			File sm = new File(outputDir, contract.name.getBactrian()
					+ "Serialization.mpc");
			logger.info(" sm: {} ", sm);
			serializeTemplateToFile(serialMPC, sm);

			ST serialExHeader = stg
					.getInstanceOf("topPluginSerializationExportHdrTemplate");
			serialExHeader.add("cp", contract);
			File se = new File(outputDir, contract.name.getBactrian()
					+ "_Export.h");
			logger.info(" se: {} ", se);
			serializeTemplateToFile(serialExHeader, se);
		}
			break;
		default:
			logger.info("Unknown template type");
			return false;
		}
		return true;
	}

}
