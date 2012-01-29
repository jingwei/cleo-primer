# How To Rest

### GET - Search

    curl -v -X GET -H "Accept: application/json" http://localhost:8080/cleo-primer/rest/elements/search?query=goo
    
    curl -v -X GET -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements/search?query=goo
    
### GET - Retrieve an element

    curl -v -X GET -H "Accept: application/json" http://localhost:8080/cleo-primer/rest/elements/1063
    
    curl -v -X GET -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements/1063
    
### GET - Retrieve all elements

    curl -v -X GET -H "Accept: application/json" http://localhost:8080/cleo-primer/rest/elements
    
    curl -v -X GET -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements
    
### GET - Retrieve a range of elements

    curl -v -X GET -H "Accept: application/json" http://localhost:8080/cleo-primer/rest/elements/1..100
    
    curl -v -X GET -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements/1..100
    
### DELETE - Remove an element

    curl -v -X DELETE http://localhost:8080/cleo-primer/rest/elements/1

### PUT - Update an element

    curl -v -X PUT -H "Content-type: application/xml" -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements/1063 -d '
    <element>
      <id>1063</id>
      <name>Google Inc. (GOOG)</name>
      <title>Technology - Computer Software: Programming Data Processing</title>
      <url>http://www.nasdaq.com/symbol/goog</url>
      <score>159350</score>
      <term>google</term>
      <term>inc.</term>
      <term>goog</term>
    </element>
    '

### POST - Add a new element

    curl -v -X POST -H "Content-type: application/xml" -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements/_ -d '
    <element>
      <id>1063</id>
      <name>Google Inc. (GOOG)</name>
      <title>Technology - Computer Software: Programming Data Processing</title>
      <url>http://www.nasdaq.com/symbol/goog</url>
      <score>159350</score>
      <term>google</term>
      <term>inc.</term>
      <term>goog</term>
    </element>
    '

### POST - Add a list of new elements

    curl -v -X POST -H "Content-type: application/xml" -H "Accept: application/xml" http://localhost:8080/cleo-primer/rest/elements -d '
    <element-list>
      <element>
        <id>1063</id>
        <name>Google Inc. (GOOG)</name>
        <title>Technology - Computer Software: Programming Data Processing</title>
        <url>http://www.nasdaq.com/symbol/goog</url>
        <score>159350</score>
        <term>google</term>
        <term>inc.</term>
        <term>goog</term>
      </element>
      <element>
        <id>1253</id>
        <name>Intel Corporation (INTC)</name>
        <title>Technology - Semiconductors</title>
        <url>http://www.nasdaq.com/symbol/intc</url>
        <score>129668</score>
        <term>intel</term>
        <term>corporation</term>
        <term>intc</term>
      </element>
    </element-list>
    '

### POST - Flush elements

    curl -v -X POST http://localhost:8080/cleo-primer/rest/elements/flush
