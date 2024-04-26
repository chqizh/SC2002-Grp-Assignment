<b> This document assumes your are in the SC2002-Grp-Assignment directory. </b>

## To Generate Javadocs:
```
javadoc -sourcepath src -subpackages Accounts:App:Branch:Customer:Database:DataPersistence:Menu -d docs
```
Javadocs have already been generated and are located in the docs folder.

## To Compile to Class Files:
```
javac -d bin $(find . -name '*.java')
```
Pre-compiled binaries are located in the bin folder.

## To Run Class Files
```
cd bin
java FOMSApp
```
FOMSApp.class is located in the bin folder.

## To Compile to Jar:
```
jar cvfm bin/FOMS.jar bin/META-INF/MANIFEST.MF -C bin/ .
```
Pre-compiled binaries are located in the bin folder.

## To Run FOMS.jar:
```
cd bin
java -jar FOMS.jar
```
FOMS.jar is located in the bin folder.
