#!/bin/bash

CLASSPATH=./target/ammogen-1.7.0-SNAPSHOT.jar:/home/phreed/.m2/repository/org/antlr/stringtemplate/4.0.2/stringtemplate-4.0.2.jar:/home/phreed/.m2/repository/org/antlr/antlr-runtime/3.3/antlr-runtime-3.3.jar:/usr/share/java/xercesImpl.jar:/usr/share/java/xml-apis.jar

java -cp ${CLASSPATH} \
   edu.vu.isis.ammo.generator.AmmoGenerator \
.  contract=/opt/ammo-bug/Android/Medevac/medevaclib/src/main/resources/contract/medevac.xml \
   skeleton=true

