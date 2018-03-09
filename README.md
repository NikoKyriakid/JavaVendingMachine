
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
The Vending Machine App has the following actions

1. **Set Quantity**
    - You will have to choose the slot number
    - and then the integer number of the amount
2. **Set Price**
    - You will have to choose the slot number
    - and then the floating number of the price
3. **Buy Product**
    - You will have to choose the slot number of the product
    - Add coins by typing a comma separated list of floating numbers
        like `1,1,0.5,0.2`
    - Number of error can occur here: No enough money provided, Coin type not supported, inability of the machine to provide the change to the buyer   
4. **Add Coins**
    - Add coins by typing a comma separated list of floating numbers like `1,1,0.5,0.2`
5. **Print Slots**
    - Display the Slots of the machine along with the prices and the quantities
6. **Print Change**
    - Prints the coins currently in the Vending Machine
7. **Populate Slots**
    - Populates with random values the quantities and the prices)*
8. **Populate Coins**
    - Adds random coins to the Vending Machine
9. **Reset**
0. **Exit**

## Built With

* [Ant](http://ant.apache.org/) - Java based build tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Java IDE


## Authors

* **Niko Kyriakidis** - *Initial work* - [NikoKyriakid](https://github.com/NikoKyriakid)

## License

This project is licensed under the MIT License
