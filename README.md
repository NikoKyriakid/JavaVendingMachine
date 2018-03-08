
# JavaVendingMachine

This is a JAVA Application that simulates the functionality of a Vending Machine.

## Getting Started

1. Install Ant [Apache Ant](https://ant.apache.org/manual/index.html)
    - Make sure you have a Java environment installed. [Download](https://java.com/en/download/)
    - Download Ant. See [Binary Distribution](http://ant.apache.org/bindownload.cgi) for details.
    - Uncompress the downloaded file into a directory.
    - Set environmental variables `JAVA_HOME` to your Java environment, `ANT_HOME` to the directory you uncompressed Ant to, and add `${ANT_HOME}/bin` (Unix) or `%ANT_HOME%/bin` (Windows) to your `PATH`.

### Installing And Running

```bash
# 1. Git clone the repo
git clone https://github.com/NikoKyriakid/JavaVendingMachine.git
# 2. Enter the folder
cd JavaVendingMachine
# 3. Run ant, which will build and display instructions to run the app
ant
# 4. The instruction will hold two ways of running the application
# 4.1 Run the script by (run is a script created by ant builder)
sh run
# 4.2 Or run the jar file by
java -jar <currentPath>/dist/VendingMachine.jar
```

### Functionality



## Built With

* [Ant](http://ant.apache.org/) - Java based build tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Java IDE


## Authors

* **Niko Kyriakidis** - *Initial work* - [NikoKyriakid](https://github.com/NikoKyriakid)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
