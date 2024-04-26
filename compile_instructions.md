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
 cvfm bin/FOMS.jar bin/META-INF/MANIFEST.MF -C bin/ .
```

## To Run FOMS.jar:
```
cd bin
java -jar FOMS.jar
```
