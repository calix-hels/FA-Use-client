HOME=$(PWD)
JC=$(JAVA_HOME)/bin/javac
JAR=$(JAVA_HOME)/bin/jar
OUTPUT=$(HOME)/out
JOPTS= -classpath $(CLASSPATH) -d $(OUTPUT) 

JRE_LIB=$(JAVA_HOME)/jre
WEBCONTENT=WebContent
WEB_APP_LIB=WebContent/WEB-INF/lib
COMDIR=com/calix/compass/fa/usage/v1/soap
SRCDIR=./src/$(COMDIR)
LIBDIR=lib

MANIFEST=$(HOME)/src/META-INF/MANIFEST.MF
CLASSDIR=$(HOME)/$(WEBCONTENT)/classes

APPJAR= $(HOME)/$(LIBDIR)/GetUse.jar
FILE_LIST= $(HOME)/$(LIBDIR)/jar_contents

CLASSPATH=$(WEB_APP_LIB)/axis.jar:$(WEB_APP_LIB)/commons-discovery-0.2.jar:$(WEB_APP_LIB)/commons-logging.jar:$(WEB_APP_LIB)/jaxrpc.jar:$(WEB_APP_LIB)/postgresql-8.3-603.jdbc3.jar:$(WEB_APP_LIB)/saaj.jar:$(WEB_APP_LIB)/wsdl4j.jar:$(WEB_APP_LIB)/commons-net-1.4.1.jar:$(WEB_APP_LIB)/commons-cli-1.2.jar

jars = $(shell find $(HOME) -name \*.jar)

default : extract
	if [ ! -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(JC) $(JOPTS) $(SRCDIR)/data/*.java $(SRCDIR)/*.java
	${RM} $(APPJAR)
	(cd $(OUTPUT); $(JAR) cfm $(APPJAR) $(MANIFEST) .) 
	
jars = $(shell ls $(PWD)/$(WEB_APP_LIB)/*.jar) 
extract:
	if [ ! -d $(OUTPUT) ]; then mkdir $(OUTPUT); fi
	@ echo extracting ...
	cd $(OUTPUT); $(foreach j,$(jars),$(JAR) xf $j com javax org ;) 
	cp $(HOME)/src/client-config.wsdd $(OUTPUT)
	@ echo 
	@ echo done

clean:
	rm -rf $(OUTPUT) $(LIBDIR)


