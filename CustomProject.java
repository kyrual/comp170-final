import java.util.Scanner;
import java.util.Random;
/**/
public class CustomProject {
    public static void main(String[] args) {
        int fight = 0;
        int chance = 0;
        int path = 0;
        int j = 0;
        Scanner scan = new Scanner(System.in);


        System.out.println("Good morning, adventurer! What is your name? ");
        String name = scan.nextLine();
        System.out.println();

        System.out.println("Hello " + name + "!");
        System.out.println("Are you ready to start?");
        yesNo(name);
        intro(name);

        System.out.println("Before you rush out the door, don't forget to equip yourself!");
        System.out.println("Taking a sword will give you a better chance at winning battles, and a good luck charm... gives you luck.");
        System.out.println("a. Sword (+2 fighting)    b. Lucky Charm (+1 chance)    c. I'm in a rush!");

        //placing this loop here so that all methods can pull chance and fighting stats from the main method
        while (j < 1) {
            System.out.println("Do you want to take a sword or a lucky charm?");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("a")) {
                System.out.println();
                System.out.println("+2 has been added to your fighting stats.");
                System.out.println();
                fight += 2;
                j++;
            } else if (choice.equals("b")) {
                System.out.println();
                System.out.println("+1 has been added to chance. Interesting choice!");
                System.out.println();
                chance += 1;
                j++;
            }
            else if (choice.equals("c")) {
                System.out.println();
                System.out.println("Good luck, " + name + "!");
                System.out.println();
                j++;
            }
            else {
                System.out.println("Please type \"a\" or \"b\" or \"c\"");
                System.out.println();
            }
        }

        System.out.println("The journey will take 5 days to complete.");
        System.out.println("Let's get going!");
        System.out.println();


        pathManager(name, chance, fight);
        fin(name);
        readCreds(name);

    }
    /*
    *@param name - user's selected name
    *@param chance - user's chance stats
    *@param fighting - user's fighting stats
    *
    *       TODO: a method that manages the storyline and selects which event the user will stumble into next
    *        also tells the user how many days are left in their jouney
     */
    public static void pathManager(String name, int chance, int fight) {
        int path = 0;
        Random rand = new Random();
        int rng = 0;

        for(path = 0; path<=5; path++){
            rng = (rand.nextInt(1, 9))+chance;
            //System.out.println("event: " + rng);
            if(rng == 1){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event1(path);
            }
            else if(rng == 2){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event2(path, chance, fight);
            }
            else if(rng == 3){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event3(path, chance, fight);
            }
            else if(rng == 4 || rng == 5){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event4(path, chance);
            }
            else if(rng == 6){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event5(path, chance, fight);
            }
            else if(rng == 8){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event6(name, path, chance, fight);
            }
            else if(rng == 9 || rng == 7){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event7(path, chance);
            }
            else if(rng > 10){
                System.out.println("⛏ Day left in journey: " + (5-path));
                path = event8(path);
            }
        }
    }
    /*
    *for 0 to 4
    *   rand 0-9 + chance
    *       if rand = 1
    *           event 1
    *       else if rand = 2
    *           event 2
    *       else if rand = 3
    *           event 3
    *       ...
    *       else if rand = n
    *           event n
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event1(int path){
        Random rand = new Random();
        int rng = 0;

        System.out.println("A heavy rain has flooded the road in front of you.");
        rng = rand.nextInt(1,2);
        if(path<=0){
            System.out.println("You'll need to wait for the rain to stop.");
            System.out.println();
            path = 0;
            return path;
        }

        else{
            if((path-rng)<=0){
                System.out.println("The heavy flood sweeps you back towards the inn.");
                System.out.println();
                path = 0;
                path--;
                return path;
            }
            else {
                System.out.println("The flood washes you backwards. It'll take " + rng + " more days to reach your destination.");
                System.out.println();

                path -= (rng + 1);
                return path;
            }
        }
    }
    /*
     *print flood event
     *      if path = 0
     *          print wait at the inn
     *          reset path
     *      else
     *          if (path - rng) <= 0
     *              print user back to inn
     *              reset path
     *          else
     *              print it'll take rng days more
     *              path = path - rng
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event2(int path, int chance, int fight){
        Scanner scan = new Scanner(System.in);

        int i = 0;

        System.out.println("A giant lays on the road ahead.");
        System.out.println("He seems to be sleeping, so it might be possible to sneak past him.");

        while(i<1) {
            System.out.println("What do you want to do?");
            System.out.println("a. Fight    b. Sneak away");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("a")) {
                path = optFight(path, fight, 7);
                i++;
            }
            else if(choice.equals("b")){
                path = sneakAway(path, chance, 6);
                i++;
            }
            else {
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }

        return path;
    }
    /*
     *print mob fight
     *print choice
     *
     *while i<1
     *      if choice = a
     *          path = optFight();
     *          end loop;
     *      else if choice = b
     *          path = sneakAway();
     *          end loop
     *      else
     *          print choose an option
     *
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event3(int path, int chance, int fight){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int rng = 0;
        int i = 0;

        System.out.println("A mob of goblins are camped ahead of you.");
        System.out.println("One mistake could set you back.");

        while(i<1) {
            System.out.println("What do you want to do?");

            System.out.println("a. Fight    b. Sneak away");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("a")) {
                path = optFight(path, fight, 5);
                i++;
            }
            else if(choice.equals("b")){
                path = sneakAway(path, chance, 5);
                i++;
            }
            else {
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }

        return path;
    }
    /*
     *print mob fight
     *print choice
     *
     *while i<1
     *      if choice = a
     *          path = optFight();
     *          end loop;
     *      else if choice = b
     *          path = sneakAway();
     *          end loop
     *      else
     *          print choose an option
     *
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event4(int path, int chance) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int rng = 0;
        int i = 0;

        System.out.println("You come across an old, wooden bridge that crosses over turbulent waters.");

        while (i < 1){
            System.out.println("What do you want to do?");
            System.out.println("a. Cross    b. Walk around");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("a")) {
                System.out.println("You put a foot down on the bridge...");
                System.out.println("It creaks and shakes...");
                System.out.println("...");

                rng = (rand.nextInt(1, 10)) + chance;
                if (rng < 5 && path <= 2) {
                    System.out.println("You take another step and the bridge collapses!");
                    System.out.println("You fall into turbulent waters and are swept downstream to the inn.");
                    System.out.println();

                    path = 0;
                    path--;
                } else if (rng < 5 && path > 2) {
                    System.out.println("You take another step and the bridge collapses!");
                    System.out.println("You fall into turbulent waters and are swept downstream.");
                    System.out.println("It'll take an additional 2 days to reach the destination.");
                    System.out.println();

                    path -= 3;
                } else if (rng >= 5) {
                    System.out.println("You take another step half-expecting the bridge to collapse.");
                    System.out.println("When nothing happens, you sprint the remaining distance.");
                    System.out.println();
                    return path;
                }
                i++;
            } else if (choice.equals("b")) {
                System.out.println("You chose to walk around the body of water, adding an additional day to your travel time.");
                System.out.println();
                path -= 2;
                i++;
            } else {
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }
        return path;
    }
    /*print bridge crossing
     *print choice
     *
     * while i<1
     *     if choice = a
     *          if rng <5
     *              bridge collapses
     *          else if rng >= 5
     *              cross bridge
     *      else if choice = b
     *          walk around
     *      else
     *          choose an option
     *
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event5(int path, int chance, int fight){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int rng = 0;
        int i = 0;

        System.out.println("A mob of slimes are camped in front of you.");
        System.out.println("Although they are weak, they can do a bit of damage in numbers.");

        while(i<1) {
            System.out.println("What do you want to do?");

            System.out.println("a. Fight    b. Sneak away");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("a")) {
                path = optFight(path, fight, 3);
                i++;
            }
            else if(choice.equals("b")){
                path = sneakAway(path, chance, 3);
                i++;
            }
            else {
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }

        return path;
    }
    /*
     *print mob fight
     *print choice
     *
     *while i<1
     *      if choice = a
     *          path = optFight();
     *          end loop;
     *      else if choice = b
     *          path = sneakAway();
     *          end loop
     *      else
     *          print choose an option
     *
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event6(String name, int path, int chance, int fight){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int i=0;
        int rng = 0;

        System.out.println("You meet an old woman laying unconscious on the road.");

        while(i<1){
            System.out.println("What will you do?");
            System.out.println("a. Help her    b. Keep moving");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if(choice.equals("a")){
                System.out.println("The old woman rises and thanks you for your help and disappears in a cloud of smoke.");
                if(fight == 2 && chance == 1){
                    System.out.println("You're a kind and blessed person, " + name);
                    i++;
                }
                else if(chance == 1){
                    System.out.println("A sword lies in her place (+2 fighting).");
                    System.out.println();
                    fight += 2;
                    i++;
                }
                else if(fight == 2){
                    System.out.println("You feel a rush of luck (+1 chance).");
                    System.out.println();
                    chance += 1;
                    i++;
                }
                else{
                    System.out.println("You feel a rush of luck (+1 chance).");
                    System.out.println();
                    chance += 1;
                    i++;
                }
            }
            else if(choice.equals("b")){
                System.out.println("\"Your lack of compassion will haunt you.\"");
                System.out.println("You turn around to see the old woman disappear into a cloud of smoke.");
                System.out.println("When you look back, you seem to be standing at the entrance of the inn");
                System.out.println();

                path=0;
                path--;
                return path;
            }
            else{
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }

        return path;
    }
    /*
     *print old woman event
     * print choice
     *
     * while i<1
     *      if choice = a
     *          help woman
     *          gain stat
     *      if choice = b
     *          walk away
     *          reset map to inn
     *
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event7(int path, int chance){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int rng = 0;
        int i=0;

        System.out.println("You stand in front of a forked road.");
        System.out.println("Do you want to take the path to the left or right?");

        if(chance==1){
            System.out.println("(For some reason, you're drawn to the path on the left...)");
        }

        while(i<1){
            System.out.println("a. left    b. right");
            String choice = scan.nextLine();

            choice = choice.toLowerCase();
            if(choice.equals("a")){
                System.out.println("You take the path on the left.");
                System.out.println("...");
                //makes sure you take credit for this event
                path += 1;
                event8(path);
                i++;
            }
            else if(choice.equals("b")){
                System.out.println("You take the path on the right.");
                System.out.println("It was a dead end! Looks like you'll have to head back.");
                System.out.println();
                path -= 1;
                i++;
            }
            else{
                System.out.println("Please choose \"a\" or \"b\"");
            }
        }

        return path;
    }
    /*
     *print forked road event
     *print choice event
     *
     * while i<1
     *      if choice = a
     *          take the left path
     *      else if choice = b
     *          take the right path
     *      else
     *          choose an option
     */

    /*
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: write an event that the user could potentially encounter
     *
     */
    public static int event8(int path){
        System.out.println("Wow! What incredible luck!");
        System.out.println("You found a hidden path through the forest.");
        System.out.println("This will decrease our travel time by a day!");
        System.out.println();
        path -=1;
        return path;
    }
    /*
     *print easy path
     *
     */

    /*
     *@param fight - int fighting stats that the user has
     *@param x - int rng set for the specific monster user will need to beat
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: calculate the chances of the user's ability to beat a mob fight
     */
    public static int optFight(int path, int fight, int x){
        Random rand = new Random();
        int rng = (rand.nextInt(0, 10))+fight;

        System.out.println("You've chosen to fight.");
        System.out.println("Be careful!");
        System.out.println("...");

        if(rng<x) {
            if(path<2){
                System.out.println("Oh no! You put up a good fight.");
                System.out.println("The beast(s) chase you backwards to the inn.");
                System.out.println();

                path =0;
                path--;
                }
            else if (path >=3) {
                System.out.println("Oh no! You put up a good fight.");
                System.out.println("The beast(s) chase you backwards. It'll take an additional two days of travel.");
                System.out.println();

                path -= 3;
            }
        }
        else if(rng < 10){
            System.out.println("You won! Good job.");
            System.out.println("Let's continue the journey.");
            System.out.println();

            return path;
        }
        else if(rng >= 10){
            System.out.println("You landed such an incredible blow! Your opponent(s) immediately fled, clearing a pathway for you.");
            System.out.println("It looks like this shortcut will take a day off your travel.");
            System.out.println();

            path+=1;
        }
        return path;
    }
    /*
     *print fight scene
     *      if rng < x
     *          you lose
     *      else if rng >= x && rng < 10
     *          you won
     *      else if rng >= 10
     *          you won and move forward a step
     */

    /*
     *@param chance - int chance stats that the user has
     *@param x - int rng set for the specific monster user will need to beat
     *@return int path - tracks amount of steps user needs to finish the game
     *
     *      TODO: calculate the chances of the user's ability to sneak away from a mob
     *
     */
    public static int sneakAway(int path, int chance, int x){
        Random rand = new Random();
        int rng = (rand.nextInt(1, 10))+chance;

        if(rng<x && path <= 1) {
            System.out.println("While sneaking away, you are hit with a sudden blow that knocks you unconscious.");
            System.out.println("You wake up in front of the inn.");
            System.out.println();
            path = 0;
            path --;
        }
        else if(rng<x && path>1){
            System.out.println("While sneaking away, you are hit with a sudden blow that knocks you unconscious.");
            System.out.println("You wake up in a new location that has added an additional day to your travels.");
            System.out.println();
            path -= 2;
        }
        else if(rng>=x){
            System.out.println("You managed to get away and continue on your adventure.");
            System.out.println();
            return path;
        }
        return path;
    }
    /*
     *if rng < x
     *      fail to sneak away
     * else if rng >= x
     *      you managed to get away
     */

    /*
     *@param name - user's selected name
     *
     *      TODO: displays the games intro and removes crowding from main
     *
     */
    public static void intro(String name){
        System.out.println("You wake up to the sunlight peeking through the blinds of the accommodating hotel room you stayed in last night.");
        System.out.println("Blinking awake, you wonder what adventures you should pursue today.");
        System.out.println("...");
        System.out.println("A folded paper slides beneath the door.");
        System.out.println("You rush to open the door, but there was no one to be found.");
        System.out.println("...");
        System.out.println("You unfold the mysterious paper to reveal an old wrinkled map with coordinates.");
        System.out.println("Although there is no information on where these coordinates lead, you are filled with curiosity.");
        System.out.println();
        System.out.println("Do you want to go on this adventure, " + name + "?");
        yesNo(name);
    }
    /*
     *@param name - user's selected name
     *
     *      TODO: allows the user to select yes or no
     *
     */
    public static void yesNo (String name) {
        Scanner scan = new Scanner(System.in);
        int i = 0;

        while (i < 1) {
            System.out.println("  Yes    No");
            String response = scan.nextLine();

            response = response.toLowerCase();

            if (response.equals("no")) {
                endCred1(name);
            } else if (response.equals("yes")) {
                System.out.println();
                System.out.println("Great! Let's get started, " + name + ".");
                System.out.println();
                i++;
            } else {
                System.out.println("That's not an option!");
                System.out.println();
            }
        }
    }
    /*
    *
    * while i < 1
    *   print "yes no"
    *   user response
    *
    *       if user response = "yes"
    *           exit loop
    *       else if user response = "no"
    *           endCred1();
    *       else
    *           loop
    *
     */

    /*
     *@param name - user's selected name
     *
     *      TODO: display the final destination of game and removes crowding from main
     *
     */
    public static void fin(String name) {
        System.out.println("You look at your map to double check the location of the coordinates.");
        System.out.println("Yes, this seems to be the right place.");
        System.out.println("A treasure chest lays in front of you.");
        System.out.println("When you open the chest, a folded piece of paper lays at the very bottom.");
        System.out.println();
        System.out.println("\"Hello " + name + ",\"");
        System.out.println("\"The real treasure was the friends we made along the way.\"");
        System.out.println("...");
        System.out.println("What?");
    }
    /*
     *@param name - user's selected name
     *
     *        TODO: display end credits and removes crowding from main
     *         However, endCred1 has an exit it is only launched when the user chooses not to play the game.
     */
    public static void endCred1 (String name) {
        System.out.println();
        System.out.println("       End Credits      ");
        System.out.println("Director       Rue Yin Hu");
        System.out.println("Design         Rue Yin Hu");
        System.out.println("Development    Rue Yin Hu");
        System.out.println();
        System.out.println("Adventurer     not " + name);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Thank you for giving my game a try.");
        System.exit(0);
    }
    public static void readCreds (String name) {
        System.out.println();
        System.out.println("       End Credits      ");
        System.out.println("Director       Rue Yin Hu");
        System.out.println("Design         Rue Yin Hu");
        System.out.println("Development    Rue Yin Hu");
        System.out.println();
        System.out.println("Adventurer    " + name);
        System.out.println();
        System.out.println();
        System.out.println("Thank you for playing my game!");
    }
}

