This document assumes your are in the SC2002-Grp-Assignment directory.

## To Generate Javadocs:
```
javadoc -sourcepath src -subpackages Accounts:App:Branch:Customer:Database:DataPersistence:Menu -d docs
```

## To Compile to Class Files:
```
javac -d bin $(find . -name '*.java')
```

## To Run Class Files
```
cd bin
java FOMSApp
```

## To Compile to Jar:
```
jar cvfm bin/FOMS.jar bin/META-INF/MANIFEST.MF -C bin/ .
```

## To Run FOMS.jar:
```
cd bin
java -jar FOMS.jar
```
