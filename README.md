# cleo-primer

The cleo-primer package provides a basic RESTful implementation of partial, out-of-order and real-time typeahead services.
It is built on [Cleo](http://sna-projects.com/cleo/), an open source package from LinkedIn.

### Homepage:

Find out more about Cleo at [http://sna-projects.com/cleo](http://sna-projects.com/cleo).

### License:

Apache Public License (APL) 2.0

### Artifacts:

cleo-primer.war

### Maven:

groupId: com.sna-projects.cleo

artifactId: cleo-primer

version: 1.0

### Build the war

    mvn clean package

### Launch WebApp

Launch the cleo-primer web application using the next command from the
main folder:

    MAVEN_OPTS="-Xms1g -Xmx1g" mvn jetty:run -Dcleo.instance.name=Company -Dcleo.instance.type=cleo.primer.GenericTypeaheadInstance -Dcleo.instance.conf=src/main/resources/config/generic-typeahead

You can customize you web application by choosing different values for parameters
<code>cleo.instance.name</code>, <code>cleo.instance.type</code> and <code>cleo.instance.conf</code>. Depending on the size
of your data sets, you may need to specify a different JVM heap size.

### Post a list of new elements

    ./scripts/post-element-list.sh dat/nasdaq-company-list.xml

### Post a new element

    ./scripts/post-element.sh dat/nasdaq-google.xml dat/nasdaq-intel.xml

### Search

Visit the URL below to try out cleo-primer.
 
    http://localhost:8080/cleo-primer

    http://localhost:8080/cleo-primer/rest/elements/search?query=goo

### Eclipse:

Set up Eclipse by executing the command below:

    mvn eclipse:eclipse

Inside Eclipse, select Preferences > Java > Build Path > Classpath Variables. Define a new classpath variable M2_REPO and assign maven repository.

For more information, check out http://maven.apache.org/guides/mini/guide-ide-eclipse.html

### Contribute

For help please see the [discussion group](http://groups.google.com/group/cleo-typeahead).  Bugs and feature requests can be filed [here](https://github.com/linkedin/cleo/issues).
