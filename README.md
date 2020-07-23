# template-cucumber5-serenity-spring

template with the integration of cucumber5 serenity and spring

#### Run the tests
`
mvn clean verify -P local 
`

#### Run the tests by tags

`
mvn clean verify -P local -Dcucumber.options="--tags @Tag"