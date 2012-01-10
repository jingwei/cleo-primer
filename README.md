What is cleo-primer?
=======================

The cleo-primer package provides a basic RESTful implementation of partial, out-of-order and real-time typeahead services.
It is built on Cleo, an open source package from LinkedIn.

### Homepage:

Find out more about Cleo at: http://sna-projects.com/cleo

### License:

Apache Public License (APL) 2.0

### Artifacts:

1. cleo-primer.jar <-- core library

### Maven:

groupId: com.sna-projects.cleo

artifactId: cleo-primer

version: 1.0

### Launch

You can launch the cleo-primer web application using the next command from the
main folder:

  $ MAVEN_OPTS="-Xms1g -Xmx1g" mvn jetty:run \
    -Dcleo.instance.name=Company \
    -Dcleo.instance.type=cleo.primer.GenericTypeaheadInstance \
    -Dcleo.instance.conf=src/main/resources/config/generic-typeahead

You can customize you web application by choosing different values for parameters
cleo.instance.name, cleo.instance.type and cleo.instance.conf.

### Scripts:



### Eclipse:

Set up Eclipse for Cleo by executing the command below:

mvn eclipse:eclipse

Inside Eclipse, select Preferences > Java > Build Path > Classpath Variables. Define a new classpath variable M2_REPO and assign maven repository.

For more information, check out http://maven.apache.org/guides/mini/guide-ide-eclipse.html

