/*Copyright (C) 2010-2012 Institute for Software Integrated Systems (ISIS)
This software was developed by the Institute for Software Integrated
Systems (ISIS) at Vanderbilt University, Tennessee, USA for the 
Transformative Apps program under DARPA, Contract # HR011-10-C-0175.
The United States Government has unlimited rights to this software. 
The US government has the right to use, modify, reproduce, release, 
perform, display, or disclose computer software or computer software 
documentation in whole or in part, in any manner and for any 
purpose whatsoever, and to have or authorize others to do so.
 */

package edu.vu.isis.ammo.generator;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AmmoGenerator {

	static final private Logger logger = LoggerFactory.getLogger("ammo.generator");
	
    /**
     * The help information.
     */
    static private void usage() {
        System.out
                .println(new StringBuilder()
                        .append("Arguments \n")
                        .append("  template   : (optional) Template group file <ammo_content_provider.stg>  \n")
                        .append("             : or a key {")
                        .append(TemplateGroup.getKeys())
                        .append("}\n")
                        .append("  contract   : Domain specific language file <incident.xml>  \n")
                        .append("  output     : Output directory <gen> \n")
                        .append("  skeleton   : Should the skeleton files be generated? {true,false,both} \n")
                        .append("  schema     : Should the schema files be generated?\n")
                        .append("  provider   : Should the provider files be generated?\n")
                        .append("  serializer : Should the serializer files be generated?\n")
                        .append("  mkdir      : Should the package specific directory be created?\n")
                        .append("sample: \n")
                        .append("AmmoGenerator ")
                        .append("skeleton=false ")
                        .append("schema=false ")
                        .append("provider=false ")
                        .append("mkdir=true ")
                        .append("template=content-provider-schema ")
                        .append("contract=/home/me/contract.xml ")
                        .append("output=/tmp/  ")
                        .append('\n')
                        .toString());
    }

    /**
     * Decide which template group is being used.
     */
    public enum TemplateGroup {
        CONTENT_PROVIDER(1, "content-provider-schema", "/templates/java/ammo_content_provider.stg"),
        GATEWAY_PLUGIN(2, "gateway-plugin", "/templates/cpp/gateway_plugin.stg"),
        PLUGIN_SERIALIZATION(3, "gateway-plugin-serialization",
                "/templates/cpp/gateway_plugin_serialization.stg");

        public final int nominal;
        public final String alias;
        public final String path;

        private static final Map<String, TemplateGroup> byName = new HashMap<String, TemplateGroup>();
        static {
            for (TemplateGroup item : TemplateGroup.values()) {
            	logger.trace("template group item {} {}", item.alias, item);
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
     * The command line interpreter, see the usage method for details.
     */
    static private boolean keyMatch(String full, String given) {
        if (full.length() < given.length())
            return false;
        if (full.length() == given.length()) {
            return full.equalsIgnoreCase(given);
        }
        return full.startsWith(given);
    }

    /**
     * Determines what the command line properties are and calls build.
     * 
     * @param args
     */
    static public void main(String[] args) {
        final Date now = new Date();
        final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);

        System.out.println(df.format(now));
        final File cwd = new File(".");

        final Generator that = new Generator(AmmoGenerator.logger);

        for (String arg : args) {
            final String[] param = arg.split("=", 2);
            if (param.length < 1)
                continue;
            if (param[0].length() < 1)
                continue;
            final String key = param[0].toLowerCase();

            if (keyMatch("skeleton", key)) {
                that.setSkeleton(param[1]);
                continue;
            }
            if (keyMatch("schema", key)) {
                that.setSchema(param[1]);
                continue;
            }
            if (keyMatch("provider", key)) {
                that.setProvider(param[1]);
                continue;
            }
            if (keyMatch("serializer", key)) {
                that.setSerializer(param[1]);
                continue;
            }
            if (keyMatch("mkdir", key)) {
                that.setSponsored(param[1]);
                continue;
            }
            if (keyMatch("template", key)) {
                that.setTemplate(param[1]);
                continue;
            }
            if (keyMatch("contract", key)) {
            	final File contractPath = new File(param[1]);
                that.setContractPath(contractPath);
                continue;
            }
            if (keyMatch("output", key)) {
            	final File outputDir = new File(param[1]);
                that.setOutputDir(outputDir);
                continue;
            }
            if (keyMatch("suffix", key)) {
                that.setSuffix(param[1]);
                continue;
            }
        }

        try {
            System.out.println("Current dir : " + cwd.getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
            usage();
            System.exit(-1);
        }
        try {
			if (that.build())
			    return;
		} catch (GeneratorException ex) {
			 ex.printStackTrace();
	            usage();
	            System.exit(-1);
		}

        usage();
        System.exit(-1);
    }

}
