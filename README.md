
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

## Functionality
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

## Performance

I did not create a class for the coins and just used a type double to represent it, *2 -> 2£, 1 -> 1£, 0.50 -> 50p, 0.20 -> 20p, 0.10 -> 10p*.
A class for the inventory of the change could be used as well, to create a more OOP design.
However, a TreeMap<Double, Integer> was easier to implement for a simple and lighter solution. The Double for the type of coin and Integer for the amount of coins.

I also didn't use BigDecimal for the operations between doubles as doubles give different results eg `0.6/0.2` gives `2.99999999` instead of `3`.
Which was causing problems to the calculations. This problem was solved using `Math.round(value* 100.0) / 100.0;`

Some extra for loops were implemented for better readability, eg. the method that checks the types of the coins could at the same time return the sum instead of void. 
However, I separated those code blocks and that way I had to do two loops instead of one.

#### Strategy that was chosen for providing the “best” set of coins
This was done in the method `static int bestSetOfCoins()` of `VendingMachineImpl` class.
Here I applied operations between `List` objects and `TreeMap` objects mainly as no class was created to represent the coins and any other inventory.

The logic goes this way:

1. Calculate how many times the maximum coin fits in the remaining value that is to be returned to the user
2. Calculate the minimum X of the times and the amount of the coins for that type
3. Try to take use the X coins of the type
    1. Calculate remaining `remaining = total - X*coinValue`
    2. Save the X in a Map (so when it's finished we will know how many of each coins we used)
    3. If remaining is 0 then `return 1`, which will state a successful set of coins is found.
    4. Grab the next lower in value coin
    5. As long as there is a remainder and the next coin call `bestSetOfCoins` (recursive)
        with the new remainder and the directly next lower coin. Which takes us to **Step 1** again.
        This way we are using dynamic programming to solve a complex problem by breaking it down into a collection of simpler subproblems,
        solving each of those subproblems just once, and storing their solutions.
    6. If `bestSetOfCoins` comes successful start returning in order to stop recursion.
    7. If `bestSetOfCoins` comes unsuccessful decrease the X by one and go to `Step 3.1`. This will try to create different combination.
    
Please bare in mind this algorithm always tries to use the most of the big-in-value coins and if it fails to find a 
combination it decreases by one the times it used the big-in-value coins. This way it will easily generate 2.10
when there are no 10p coins. Cause it will try one 2£ coin and realise it cannot solve it then it will try with one
less ie zero times of 2£ coins. But in the next loop it will try with 2 of 1£ which will give the same unsuccessful result.
Then in the next loop it will decrese the usage of 1£ by one and use only 1 time. So in the end it will end up with:
    ```
    1 time of 1£
    1 times of 50p
    3 times of 20p
    ```       
        
 
## Built With

* [Ant](http://ant.apache.org/) - Java based build tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Java IDE


## Authors

* **Niko Kyriakidis** - *Initial work* - [NikoKyriakid](https://github.com/NikoKyriakid)

## License

This project is licensed under the MIT License
