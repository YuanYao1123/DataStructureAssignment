import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
//        initFileDatabase();
        while (true) {
            System.out.println("----------Welcome to the student information system---------");
            System.out.println("Choose the purpose:");
            System.out.println("1: Use");
            System.out.println("2: Compare and Test the performance of different implementation");
            System.out.println("3: Exit");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i == 1) {
                startSys();
            } else if (i == 2) {
                compare();
            } else if (i == 3) {
                break;
            }
        }
    }
    public static void startSys() {
        Container<Student> container = null;
        // load the data from file
        ArrayList<Student> studentList = loadFileAsList();
        Container<Student> containerArray = new MyArrayList<>();
        Container<Student> containerLinkedList = new DoublyLinkedList<>();
        Container<Student> containerBST = new BinarySearchTree<>();
        add(containerArray, studentList);
        add(containerLinkedList, studentList);
        add(containerBST, studentList);
        //User Interface
        while (true) {
            System.out.println("Please select the system you wanna use:");
            System.out.println("1: System1: Array based system");
            System.out.println("2: System2: Doubly Linked List based system");
            System.out.println("3: System3: Binary Search Tree based system");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            int flag = 1;
            switch (i) {
                case 1:
                    container = containerArray;
                    break;
                case 2:
                    container = containerLinkedList;
                    break;
                case 3:
                    container = containerBST;
                    break;
                default:
                    System.out.println("Please type the number between 1-3");
                    flag = 0;
            }
            while (true) {
                if (flag == 1) {
                    System.out.println("please select the function you wanna use!");
                    System.out.println("1: Add a student");
                    System.out.println("2: Delete a student by name");
                    System.out.println("3: Search a student by name");
                    System.out.println("4: Print all students' information(just show the last 100 data as a demo)");
                    System.out.println("5: Sort students by student Name");
                    System.out.println("6: Exit");
                    int selection = sc.nextInt();
                    if (selection == 1) {
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }
                        System.out.println("Please input the student information");
                        System.out.println("Name: ");
                        String name = sc.nextLine();
                        System.out.println("Age: ");
                        int age = sc.nextInt();
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }
                        System.out.println("Major: ");
                        String major = sc.nextLine();
                        System.out.println("Country: ");
                        String country = sc.nextLine();
                        Student student = new Student(name, age, major, country);
                        if (container.addElement(student)) {
                            System.out.println("Adding succeeded!");
                            container.print();
                        }
                    } else if (selection == 2) {
                        System.out.println("Please input the student name you wanna delete");
                        System.out.println("Name: ");
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }
                        String name = sc.nextLine();
                        container.removeBy(new Student(name, 0, null, null));
                        System.out.println("Deleting succeeded!");
                        container.print();
                    } else if (selection == 3) {
                        System.out.println("Please input the student name you wanna search");
                        System.out.println("Name: ");
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }
                        String name = sc.nextLine();
                        Student student = container.searchBy(new Student(name, 0, null, null));
                        System.out.println(student);
                    } else if (selection == 4) {
                        ArrayList<Student> students = container.print();
                        for (int j = students.size()-99; j <= students.size()-1; j++) {
                            System.out.println(students.get(j));
                        }
                    } else if (selection == 5) {
                        System.out.println("It's sorting now, please wait a moment.");
                        container.sort();
                    } else if (selection == 6) {
                        break;
                    }
                }
            }
        }
    }


    public static void compare(){
        ArrayList<Student> studentList = loadFileAsList();
        Container<Student> containerArray=new MyArrayList<>();
        Container<Student> containerLinkedList=new DoublyLinkedList<>();
        Container<Student> containerBST=new BinarySearchTree<>();
        while (true) {
            System.out.println("Welcome to the Compare and Test System");
            System.out.println("Please select the function you want to compare");
            System.out.println("1: Batch Add 50000 students information(recommend doing first)");
            System.out.println("2: Sort students by student Name");
            System.out.println("3: Delete a student by name");
            System.out.println("4: Search a student by name");
            System.out.println("5: Print all students' information");
            System.out.println("6: Exit");
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();
            if (selection == 1) {
                System.out.print("Array:  ");
                testAdd(containerArray, studentList);
                System.out.print("Doubly Linked List: ");
                testAdd(containerLinkedList, studentList);
                System.out.print("Binary Search Tree: ");
                testAdd(containerBST, studentList);
                System.out.println();
            } else if (selection == 2) {
                System.out.println("Sort:--------------");
                System.out.print("Array(Quick Sort):  ");
                testSort(containerArray);
                System.out.print("Doubly Linked List(Selection Sort):  ");
                testSort(containerLinkedList);
                System.out.print("Binary Search Tree: ");
                testSort(containerBST);
                System.out.println();
            } else if (selection == 3) {
                Student stu = new Student();
                stu.setStuName("Carmen");
                System.out.print("Array:  ");
                testDelete(containerArray, stu);
                System.out.print("Doubly Linked List: ");
                testDelete(containerLinkedList, stu);
                System.out.print("Binary Search Tree: ");
                testDelete(containerBST, stu);
                System.out.println();
            } else if (selection == 4) {
                Student stu2 = new Student();
                stu2.setStuName("Samson");
                System.out.print("Array:  ");
                testSearch(containerArray, stu2);
                System.out.print("Doubly Linked List: ");
                testSearch(containerLinkedList, stu2);
                System.out.print("Binary Search Tree: ");
                testSearch(containerBST, stu2);
                System.out.println();
            } else if (selection == 5) {
                System.out.print("Array:  ");
                testPrint(containerArray);
                System.out.print("Doubly Linked List: ");
                testPrint(containerLinkedList);
                System.out.print("Binary Search Tree: ");
                testPrint(containerBST);
                System.out.println();
            } else if (selection==6) {
                break;
            }
        }
    }

    public static void add(Container<Student> container,ArrayList<Student> addedData){
        for (Student addedDatum : addedData) {
            container.addElement(addedDatum);
        }
    }
    public static void testAdd(Container<Student> container,ArrayList<Student> addedData){
        long start = System.currentTimeMillis();
        for (Student addedDatum : addedData) {
            container.addElement(addedDatum);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spend:"+(end-start));
    }

    public static void testDelete(Container<Student> container,Student stu){
        long start = System.currentTimeMillis();
        container.removeBy(stu);
        long end = System.currentTimeMillis();
        System.out.println("Time spend:"+(end-start));
    }

    public static void testSearch(Container<Student> container,Student stu){
        long start = System.currentTimeMillis();
        container.searchBy(stu);
        long end = System.currentTimeMillis();
        System.out.println("Time spend:"+(end-start));
    }

    public static void testPrint(Container<Student> container){
        long start = System.currentTimeMillis();
        container.print();
        long end = System.currentTimeMillis();
        System.out.println("Time spend:"+(end-start));
    }

    public static void testSort(Container<Student> container){
        long start = System.currentTimeMillis();
        container.sort();
        long end = System.currentTimeMillis();
        System.out.println("Time spend:"+(end-start));
    }


    public static ArrayList<Student> loadFileAsList(){
        File file=new File("student.txt");
        Scanner sc=null;
        try {
            sc=new Scanner(file);
            ArrayList<Student> studentList=new ArrayList<>();
            while (sc.hasNext()){
                Student student=new Student();
                String s = sc.nextLine();
                String[] arrSplit = s.split(":");  //Hayes:27:Personal and Culinary Services:Malaysia
                student.setStuName(arrSplit[0]);
                student.setAge(Integer.parseInt(arrSplit[1]));
                student.setMajor(arrSplit[2]);
                student.setCountry(arrSplit[3]);

                studentList.add(student);
            }
            return studentList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void initFile() {
        String[] arrName=new String[]{"Olivia","Emma","Charlotte","Amelia","Ava","Sophia","Isabella","Mia","Evelyn","Harper","Luna","Camila","Gianna","Elizabeth","Eleanor","Ella","Abigail","Sofia","Avery","Scarlett","Emily","Aria","Penelope","Chloe","Layla","Mila","Nora","Hazel","Madison","Ellie","Lily","Nova","Isla","Grace","Violet","Aurora","Riley","Zoey","Willow","Emilia","Stella","Zoe","Victoria","Hannah","Addison","Leah","Lucy","Eliana","Ivy","Everly","Lillian","Paisley","Elena","Naomi","Maya","Natalie","Kinsley","Delilah","Claire","Audrey","Aaliyah","Ruby","Brooklyn","Alice","Aubrey","Autumn","Leilani","Savannah","Valentina","Kennedy","Madelyn","Josephine","Bella","Skylar","Genesis","Sophie","Hailey","Sadie","Natalia","Quinn","Caroline","Allison","Gabriella","Anna","Serenity","Nevaeh","Cora","Ariana","Emery","Lydia","Jade","Sarah","Eva","Adeline","Madeline","Piper","Rylee","Athena","Peyton","Everleigh","Vivian","Clara","Raelynn","Liliana","Samantha","Maria","Iris","Ayla","Eloise","Lyla","Eliza","Hadley","Melody","Julia","Parker","Rose","Isabelle","Brielle","Adalynn","Arya","Eden","Remi","Mackenzie","Maeve","Margaret","Reagan","Charlie","Alaia","Melanie","Josie","Elliana","Cecilia","Mary","Daisy","Alina","Lucia","Ximena","Juniper","Kaylee","Magnolia","Summer","Adalyn","Sloane","Amara","Arianna","Isabel","Reese","Emersyn","Sienna","Kehlani","River","Freya","Valerie","Blakely","Genevieve","Esther","Valeria","Katherine","Kylie","Norah","Amaya","Bailey","Ember","Ryleigh","Georgia","Catalina","Emerson","Alexandra","Faith","Jasmine","Ariella","Ashley","Andrea","Millie","June","Khloe","Callie","Juliette","Sage","Ada","Anastasia","Olive","Alani","Brianna","Rosalie","Molly","Brynlee","Amy","Ruth","Aubree","Gemma","Taylor","Oakley","Margot","Arabella","Sara","Journee","Harmony","Blake","Alaina","Aspen","Noelle","Selena","Oaklynn","Morgan","Londyn","Zuri","Aliyah","Jordyn","Juliana","Finley","Presley","Zara","Leila","Marley","Sawyer","Amira","Lilly","London","Kimberly","Elsie","Ariel","Lila","Alana","Diana","Kamila","Nyla","Vera","Hope","Annie","Kaia","Myla","Alyssa","Angela","Ana","Lennon","Evangeline","Harlow","Rachel","Gracie","Rowan","Laila","Elise","Sutton","Lilah","Adelyn","Phoebe","Octavia","Sydney","Mariana","Wren","Lainey","Vanessa","Teagan","Kayla","Malia","Elaina","Saylor","Brooke","Lola","Miriam","Alayna","Adelaide","Daniela","Jane","Payton","Journey","Lilith","Delaney","Dakota","Mya","Charlee","Alivia","Annabelle","Kailani","Lucille","Trinity","Gia","Tatum","Raegan","Camille","Kaylani","Kali","Stevie","Maggie","Haven","Tessa","Daphne","Adaline","Hayden","Joanna","Jocelyn","Lena","Evie","Juliet","Fiona","Cataleya","Angelina","Leia","Paige","Julianna","Milani","Talia","Rebecca","Kendall","Harley","Lia","Phoenix","Dahlia","Logan","Camilla","Thea","Jayla","Brooklynn","Blair","Vivienne","Hallie","Madilyn","Mckenna","Evelynn","Ophelia","Celeste","Alayah","Winter","Catherine","Collins","Nina","Briella","Palmer","Noa","Mckenzie","Kiara","Amari","Adriana","Gracelynn","Lauren","Cali","Kalani","Aniyah","Nicole","Alexis","Mariah","Gabriela","Wynter","Amina","Ariyah","Adelynn","Remington","Reign","Alaya","Dream","Alexandria","Willa","Avianna","Makayla","Gracelyn","Elle","Amiyah","Arielle","Elianna","Giselle","Brynn","Ainsley","Aitana","Charli","Demi","Makenna","Rosemary","Danna","Izabella","Lilliana","Melissa","Samara","Lana","Mabel","Everlee","Fatima","Leighton","Esme","Raelyn","Madeleine","Nayeli","Camryn","Kira","Annalise","Selah","Serena","Royalty","Rylie","Celine","Laura","Brinley","Frances","Michelle","Heidi","Rory","Sabrina","Destiny","Gwendolyn","Alessandra","Poppy","Amora","Nylah","Luciana","Maisie","Miracle","Joy","Liana","Raven","Shiloh","Allie","Daleyza","Kate","Lyric","Alicia","Lexi","Addilyn","Anaya","Malani","Paislee","Elisa","Kayleigh","Azalea","Francesca","Jordan","Regina","Viviana","Aylin","Skye","Daniella","Makenzie","Veronica","Legacy","Maia","Ariah","Alessia","Carmen","Astrid","Maren","Helen","Felicity","Alexa","Danielle","Lorelei","Paris","Adelina","Bianca","Gabrielle","Jazlyn","Scarlet","Bristol","Navy","Esmeralda","Colette","Stephanie","Jolene","Marlee","Sarai","Hattie","Nadia","Rosie","Kamryn","Kenzie","Alora","Holly","Matilda","Sylvia","Cameron","Armani","Emelia","Keira","Braelynn","Jacqueline","Alison","Amanda","Cassidy","Emory","Ari","Haisley","Jimena","Jessica","Elaine","Dorothy","Mira","Eve","Oaklee","Averie","Charleigh","Lyra","Madelynn","Angel","Edith","Jennifer","Raya","Ryan","Heaven","Kyla","Wrenley","Meadow","Carter","Kora","Saige","Kinley","Maci","Mae","Salem","Aisha","Adley","Liam","Noah","Oliver","Elijah","James","William","Benjamin","Lucas","Henry","Theodore","Jack","Levi","Alexander","Jackson","Mateo","Daniel","Michael","Mason","Sebastian","Ethan","Logan","Owen","Samuel","Jacob","Asher","Aiden","John","Joseph","Wyatt","David","Leo","Luke","Julian","Hudson","Grayson","Matthew","Ezra","Gabriel","Carter","Isaac","Jayden","Luca","Anthony","Dylan","Lincoln","Thomas","Maverick","Elias","Josiah","Charles","Caleb","Christopher","Ezekiel","Miles","Jaxon","Isaiah","Andrew","Joshua","Nathan","Nolan","Adrian","Cameron","Santiago","Eli","Aaron","Ryan","Angel","Cooper","Waylon","Easton","Kai","Christian","Landon","Colton","Roman","Axel","Brooks","Jonathan","Robert","Jameson","Ian","Everett","Greyson","Wesley","Jeremiah","Hunter","Leonardo","Jordan","Jose","Bennett","Silas","Nicholas","Parker","Beau","Weston","Austin","Connor","Carson","Dominic","Xavier","Jaxson","Jace","Emmett","Adam","Declan","Rowan","Micah","Kayden","Gael","River","Ryder","Kingston","Damian","Sawyer","Luka","Evan","Vincent","Legend","Myles","Harrison","August","Bryson","Amir","Giovanni","Chase","Diego","Milo","Jasper","Walker","Jason","Brayden","Cole","Nathaniel","George","Lorenzo","Zion","Luis","Archer","Enzo","Jonah","Thiago","Theo","Ayden","Zachary","Calvin","Braxton","Ashton","Rhett","Atlas","Jude","Bentley","Carlos","Ryker","Adriel","Arthur","Ace","Tyler","Jayce","Max","Elliot","Graham","Kaiden","Maxwell","Juan","Dean","Matteo","Malachi","Ivan","Elliott","Jesus","Emiliano","Messiah","Gavin","Maddox","Camden","Hayden","Leon","Antonio","Justin","Tucker","Brandon","Kevin","Judah","Finn","King","Brody","Xander","Nicolas","Charlie","Arlo","Emmanuel","Barrett","Felix","Alex","Miguel","Abel","Alan","Beckett","Amari","Karter","Timothy","Abraham","Jesse","Zayden","Blake","Alejandro","Dawson","Tristan","Victor","Avery","Joel","Grant","Eric","Patrick","Peter","Richard","Edward","Andres","Emilio","Colt","Knox","Beckham","Adonis","Kyrie","Matias","Oscar","Lukas","Marcus","Hayes","Caden","Remington","Griffin","Nash","Israel","Steven","Holden","Rafael","Zane","Jeremy","Kash","Preston","Kyler","Jax","Jett","Kaleb","Riley","Simon","Phoenix","Javier","Bryce","Louis","Mark","Cash","Lennox","Paxton","Malakai","Paul","Kenneth","Nico","Kaden","Lane","Kairo","Maximus","Omar","Finley","Atticus","Crew","Brantley","Colin","Dallas","Walter","Brady","Callum","Ronan","Hendrix","Jorge","Tobias","Clayton","Emerson","Damien","Zayn","Malcolm","Kayson","Bodhi","Bryan","Aidan","Cohen","Brian","Cayden","Andre","Niko","Maximiliano","Zander","Khalil","Rory","Francisco","Cruz","Kobe","Reid","Daxton","Derek","Martin","Jensen","Karson","Tate","Muhammad","Jaden","Joaquin","Josue","Gideon","Dante","Cody","Bradley","Orion","Spencer","Angelo","Erick","Jaylen","Julius","Manuel","Ellis","Colson","Cairo","Gunner","Wade","Chance","Odin","Anderson","Kane","Raymond","Cristian","Aziel","Prince","Ezequiel","Jake","Otto","Eduardo","Rylan","Ali","Cade","Stephen","Ari","Kameron","Dakota","Warren","Ricardo","Killian","Mario","Romeo","Cyrus","Ismael","Russell","Tyson","Edwin","Desmond","Nasir","Remy","Tanner","Fernando","Hector","Titus","Lawson","Sean","Kyle","Elian","Corbin","Bowen","Wilder","Armani","Royal","Stetson","Briggs","Sullivan","Leonel","Callan","Finnegan","Jay","Zayne","Marshall","Kade","Travis","Sterling","Raiden","Sergio","Tatum","Cesar","Zyaire","Milan","Devin","Gianni","Kamari","Royce","Malik","Jared","Franklin","Clark","Noel","Marco","Archie","Apollo","Pablo","Garrett","Oakley","Memphis","Quinn","Onyx","Alijah","Baylor","Edgar","Nehemiah","Winston","Major","Rhys","Forrest","Jaiden","Reed","Santino","Troy","Caiden","Harvey","Collin","Solomon","Donovan","Damon","Jeffrey","Kason","Sage","Grady","Kendrick","Leland","Luciano","Pedro","Hank","Hugo","Esteban","Johnny","Kashton","Ronin","Ford","Mathias","Porter","Erik","Johnathan","Frank","Tripp","Casey","Fabian","Leonidas","Baker","Matthias","Philip","Jayceon","Kian","Saint","Ibrahim","Jaxton","Augustus","Callen","Trevor","Ruben","Adan","Conor","Dax","Braylen","Kaison","Francis","Kyson","Andy","Lucca","Mack","Peyton","Alexis","Deacon","Kasen","Kamden","Frederick","Princeton","Braylon","Wells","Nikolai","Iker","Bo","Dominick","Moshe","Cassius","Gregory","Lewis","Kieran","Isaias","Seth","Marcos","Omari","Shane","Keegan","Jase","Asa","Sonny","Uriel","Pierce","Jasiah","Eden","Rocco","Banks","Cannon","Denver","Zaiden","Roberto","Shawn","Drew","Emanuel","Kolton","Ayaan","Ares","Conner","Jalen","Alonzo","Enrique","Dalton","Moses","Koda","Bodie","Jamison","Phillip","Zaire","Jonas","Kylo","Moises","Shepherd","Allen","Kenzo","Mohamed","Keanu","Dexter","Conrad","Bruce","Sylas","Soren","Raphael","Rowen","Gunnar","Sutton","Quentin","Jaziel","Emmitt","Makai","Koa","Maximilian","Brixton","Dariel","Zachariah","Roy","Armando","Corey","Saul","Izaiah","Danny","Davis","Ridge","Yusuf","Ariel","Valentino","Jayson","Ronald","Albert","Gerardo","Ryland","Dorian","Drake","Gage","Rodrigo","Hezekiah","Kylan","Boone","Ledger","Santana","Jamari","Jamir","Lawrence","Reece","Kaysen","Shiloh","Arjun","Marcelo","Abram","Benson","Huxley","Nikolas","Zain","Kohen","Samson","Miller","Donald","Finnley","Kannon","Lucian","Watson","Keith","Westin","Tadeo","Sincere","Boston","Axton","Amos","Chandler","Leandro","Raul","Scott","Reign","Alessandro","Camilo","Derrick","Morgan","Julio","Clay","Edison","Jaime","Augustine","Julien","Zeke","Marvin","Bellamy","Landen","Dustin","Jamie","Krew","Kyree","Colter","Johan","Houston","Layton","Quincy","Case","Atreus","Legacy","Ocean","Ozzy","Briar","Wilson","Forest","Grey","Joziah","Salem","Neil","Remi","Bridger","Harry","Jefferson","Lachlan","Nelson","Casen","Salvador","Magnus","Tommy","Marcellus","Maximo","Jerry","Clyde","Aron","Keaton","Eliam","Lian","Trace","Douglas","Junior","Titan","Cullen","Cillian","Musa","Mylo","Hugh","Tomas","Vincenzo","Westley","Langston","Byron","Kiaan","Loyal","Orlando","Kyro","Amias","Amiri","Jimmy","Vicente","Khari","Brendan","Rey","Ben","Emery","Zyair","Bjorn","Evander","Ramon","Alvin","Ricky","Jagger","Brock","Dakari","Eddie","Blaze","Gatlin","Alonso","Curtis","Kylian","Nathanael","Devon","Wayne","Zakai","Mathew","Rome","Riggs","Aryan","Alfred","Ernesto","Kyng","Everest","Gary","Leroy","Yahir","Braden","Kelvin","Kristian","Adler","Avyaan","Brayan","Jones","Truett","Aries","Joey","Randy","Jaxx","Jesiah","Jovanni","Azriel","Brecken","Harley","Zechariah","Gordon","Jakai","Carl","Graysen","Kylen","Ayan","Branson","Crosby","Dominik","Jabari","Jaxtyn","Kristopher","Ulises","Zyon","Fox","Howard","Salvatore","Turner","Vance","Harlem","Jair","Jakobe","Jeremias","Osiris","Azael","Bowie","Canaan","Elon","Granger","Karsyn","Zavier","Cain","Dangelo","Heath","Yisroel","Gian","Shepard","Harold","Kamdyn","Rene","Rodney","Yaakov","Adrien","Kartier","Cassian","Coleson","Ahmir","Darian","Genesis","Kalel","Agustin","Wylder","Yadiel","Ephraim","Kody","Neo","Ignacio","Osman","Aldo","Abdullah","Cory","Blaine","Dimitri","Khai","Landry","Palmer","Benedict","Leif","Koen","Maxton","Mordechai","Zev","Atharv","Bishop","Blaise","DavianOlivia","Emma","Charlotte","Amelia","Ava","Sophia","Isabella","Mia","Evelyn","Harper","Luna","Camila","Gianna","Elizabeth","Eleanor","Ella","Abigail","Sofia","Avery","Scarlett","Emily","Aria","Penelope","Chloe","Layla","Mila","Nora","Hazel","Madison","Ellie","Lily","Nova","Isla","Grace","Violet","Aurora","Riley","Zoey","Willow","Emilia","Stella","Zoe","Victoria","Hannah","Addison","Leah","Lucy","Eliana","Ivy","Everly","Lillian","Paisley","Elena","Naomi","Maya","Natalie","Kinsley","Delilah","Claire","Audrey","Aaliyah","Ruby","Brooklyn","Alice","Aubrey","Autumn","Leilani","Savannah","Valentina","Kennedy","Madelyn","Josephine","Bella","Skylar","Genesis","Sophie","Hailey","Sadie","Natalia","Quinn","Caroline","Allison","Gabriella","Anna","Serenity","Nevaeh","Cora","Ariana","Emery","Lydia","Jade","Sarah","Eva","Adeline","Madeline","Piper","Rylee","Athena","Peyton","Everleigh","Vivian","Clara","Raelynn","Liliana","Samantha","Maria","Iris","Ayla","Eloise","Lyla","Eliza","Hadley","Melody","Julia","Parker","Rose","Isabelle","Brielle","Adalynn","Arya","Eden","Remi","Mackenzie","Maeve","Margaret","Reagan","Charlie","Alaia","Melanie","Josie","Elliana","Cecilia","Mary","Daisy","Alina","Lucia","Ximena","Juniper","Kaylee","Magnolia","Summer","Adalyn","Sloane","Amara","Arianna","Isabel","Reese","Emersyn","Sienna","Kehlani","River","Freya","Valerie","Blakely","Genevieve","Esther","Valeria","Katherine","Kylie","Norah","Amaya","Bailey","Ember","Ryleigh","Georgia","Catalina","Emerson","Alexandra","Faith","Jasmine","Ariella","Ashley","Andrea","Millie","June","Khloe","Callie","Juliette","Sage","Ada","Anastasia","Olive","Alani","Brianna","Rosalie","Molly","Brynlee","Amy","Ruth","Aubree","Gemma","Taylor","Oakley","Margot","Arabella","Sara","Journee","Harmony","Blake","Alaina","Aspen","Noelle","Selena","Oaklynn","Morgan","Londyn","Zuri","Aliyah","Jordyn","Juliana","Finley","Presley","Zara","Leila","Marley","Sawyer","Amira","Lilly","London","Kimberly","Elsie","Ariel","Lila","Alana","Diana","Kamila","Nyla","Vera","Hope","Annie","Kaia","Myla","Alyssa","Angela","Ana","Lennon","Evangeline","Harlow","Rachel","Gracie","Rowan","Laila","Elise","Sutton","Lilah","Adelyn","Phoebe","Octavia","Sydney","Mariana","Wren","Lainey","Vanessa","Teagan","Kayla","Malia","Elaina","Saylor","Brooke","Lola","Miriam","Alayna","Adelaide","Daniela","Jane","Payton","Journey","Lilith","Delaney","Dakota","Mya","Charlee","Alivia","Annabelle","Kailani","Lucille","Trinity","Gia","Tatum","Raegan","Camille","Kaylani","Kali","Stevie","Maggie","Haven","Tessa","Daphne","Adaline","Hayden","Joanna","Jocelyn","Lena","Evie","Juliet","Fiona","Cataleya","Angelina","Leia","Paige","Julianna","Milani","Talia","Rebecca","Kendall","Harley","Lia","Phoenix","Dahlia","Logan","Camilla","Thea","Jayla","Brooklynn","Blair","Vivienne","Hallie","Madilyn","Mckenna","Evelynn","Ophelia","Celeste","Alayah","Winter","Catherine","Collins","Nina","Briella","Palmer","Noa","Mckenzie","Kiara","Amari","Adriana","Gracelynn","Lauren","Cali","Kalani","Aniyah","Nicole","Alexis","Mariah","Gabriela","Wynter","Amina","Ariyah","Adelynn","Remington","Reign","Alaya","Dream","Alexandria","Willa","Avianna","Makayla","Gracelyn","Elle","Amiyah","Arielle","Elianna","Giselle","Brynn","Ainsley","Aitana","Charli","Demi","Makenna","Rosemary","Danna","Izabella","Lilliana","Melissa","Samara","Lana","Mabel","Everlee","Fatima","Leighton","Esme","Raelyn","Madeleine","Nayeli","Camryn","Kira","Annalise","Selah","Serena","Royalty","Rylie","Celine","Laura","Brinley","Frances","Michelle","Heidi","Rory","Sabrina","Destiny","Gwendolyn","Alessandra","Poppy","Amora","Nylah","Luciana","Maisie","Miracle","Joy","Liana","Raven","Shiloh","Allie","Daleyza","Kate","Lyric","Alicia","Lexi","Addilyn","Anaya","Malani","Paislee","Elisa","Kayleigh","Azalea","Francesca","Jordan","Regina","Viviana","Aylin","Skye","Daniella","Makenzie","Veronica","Legacy","Maia","Ariah","Alessia","Carmen","Astrid","Maren","Helen","Felicity","Alexa","Danielle","Lorelei","Paris","Adelina","Bianca","Gabrielle","Jazlyn","Scarlet","Bristol","Navy","Esmeralda","Colette","Stephanie","Jolene","Marlee","Sarai","Hattie","Nadia","Rosie","Kamryn","Kenzie","Alora","Holly","Matilda","Sylvia","Cameron","Armani","Emelia","Keira","Braelynn","Jacqueline","Alison","Amanda","Cassidy","Emory","Ari","Haisley","Jimena","Jessica","Elaine","Dorothy","Mira","Eve","Oaklee","Averie","Charleigh","Lyra","Madelynn","Angel","Edith","Jennifer","Raya","Ryan","Heaven","Kyla","Wrenley","Meadow","Carter","Kora","Saige","Kinley","Maci","Mae","Salem","Aisha","Adley","Liam","Noah","Oliver","Elijah","James","William","Benjamin","Lucas","Henry","Theodore","Jack","Levi","Alexander","Jackson","Mateo","Daniel","Michael","Mason","Sebastian","Ethan","Logan","Owen","Samuel","Jacob","Asher","Aiden","John","Joseph","Wyatt","David","Leo","Luke","Julian","Hudson","Grayson","Matthew","Ezra","Gabriel","Carter","Isaac","Jayden","Luca","Anthony","Dylan","Lincoln","Thomas","Maverick","Elias","Josiah","Charles","Caleb","Christopher","Ezekiel","Miles","Jaxon","Isaiah","Andrew","Joshua","Nathan","Nolan","Adrian","Cameron","Santiago","Eli","Aaron","Ryan","Angel","Cooper","Waylon","Easton","Kai","Christian","Landon","Colton","Roman","Axel","Brooks","Jonathan","Robert","Jameson","Ian","Everett","Greyson","Wesley","Jeremiah","Hunter","Leonardo","Jordan","Jose","Bennett","Silas","Nicholas","Parker","Beau","Weston","Austin","Connor","Carson","Dominic","Xavier","Jaxson","Jace","Emmett","Adam","Declan","Rowan","Micah","Kayden","Gael","River","Ryder","Kingston","Damian","Sawyer","Luka","Evan","Vincent","Legend","Myles","Harrison","August","Bryson","Amir","Giovanni","Chase","Diego","Milo","Jasper","Walker","Jason","Brayden","Cole","Nathaniel","George","Lorenzo","Zion","Luis","Archer","Enzo","Jonah","Thiago","Theo","Ayden","Zachary","Calvin","Braxton","Ashton","Rhett","Atlas","Jude","Bentley","Carlos","Ryker","Adriel","Arthur","Ace","Tyler","Jayce","Max","Elliot","Graham","Kaiden","Maxwell","Juan","Dean","Matteo","Malachi","Ivan","Elliott","Jesus","Emiliano","Messiah","Gavin","Maddox","Camden","Hayden","Leon","Antonio","Justin","Tucker","Brandon","Kevin","Judah","Finn","King","Brody","Xander","Nicolas","Charlie","Arlo","Emmanuel","Barrett","Felix","Alex","Miguel","Abel","Alan","Beckett","Amari","Karter","Timothy","Abraham","Jesse","Zayden","Blake","Alejandro","Dawson","Tristan","Victor","Avery","Joel","Grant","Eric","Patrick","Peter","Richard","Edward","Andres","Emilio","Colt","Knox","Beckham","Adonis","Kyrie","Matias","Oscar","Lukas","Marcus","Hayes","Caden","Remington","Griffin","Nash","Israel","Steven","Holden","Rafael","Zane","Jeremy","Kash","Preston","Kyler","Jax","Jett","Kaleb","Riley","Simon","Phoenix","Javier","Bryce","Louis","Mark","Cash","Lennox","Paxton","Malakai","Paul","Kenneth","Nico","Kaden","Lane","Kairo","Maximus","Omar","Finley","Atticus","Crew","Brantley","Colin","Dallas","Walter","Brady","Callum","Ronan","Hendrix","Jorge","Tobias","Clayton","Emerson","Damien","Zayn","Malcolm","Kayson","Bodhi","Bryan","Aidan","Cohen","Brian","Cayden","Andre","Niko","Maximiliano","Zander","Khalil","Rory","Francisco","Cruz","Kobe","Reid","Daxton","Derek","Martin","Jensen","Karson","Tate","Muhammad","Jaden","Joaquin","Josue","Gideon","Dante","Cody","Bradley","Orion","Spencer","Angelo","Erick","Jaylen","Julius","Manuel","Ellis","Colson","Cairo","Gunner","Wade","Chance","Odin","Anderson","Kane","Raymond","Cristian","Aziel","Prince","Ezequiel","Jake","Otto","Eduardo","Rylan","Ali","Cade","Stephen","Ari","Kameron","Dakota","Warren","Ricardo","Killian","Mario","Romeo","Cyrus","Ismael","Russell","Tyson","Edwin","Desmond","Nasir","Remy","Tanner","Fernando","Hector","Titus","Lawson","Sean","Kyle","Elian","Corbin","Bowen","Wilder","Armani","Royal","Stetson","Briggs","Sullivan","Leonel","Callan","Finnegan","Jay","Zayne","Marshall","Kade","Travis","Sterling","Raiden","Sergio","Tatum","Cesar","Zyaire","Milan","Devin","Gianni","Kamari","Royce","Malik","Jared","Franklin","Clark","Noel","Marco","Archie","Apollo","Pablo","Garrett","Oakley","Memphis","Quinn","Onyx","Alijah","Baylor","Edgar","Nehemiah","Winston","Major","Rhys","Forrest","Jaiden","Reed","Santino","Troy","Caiden","Harvey","Collin","Solomon","Donovan","Damon","Jeffrey","Kason","Sage","Grady","Kendrick","Leland","Luciano","Pedro","Hank","Hugo","Esteban","Johnny","Kashton","Ronin","Ford","Mathias","Porter","Erik","Johnathan","Frank","Tripp","Casey","Fabian","Leonidas","Baker","Matthias","Philip","Jayceon","Kian","Saint","Ibrahim","Jaxton","Augustus","Callen","Trevor","Ruben","Adan","Conor","Dax","Braylen","Kaison","Francis","Kyson","Andy","Lucca","Mack","Peyton","Alexis","Deacon","Kasen","Kamden","Frederick","Princeton","Braylon","Wells","Nikolai","Iker","Bo","Dominick","Moshe","Cassius","Gregory","Lewis","Kieran","Isaias","Seth","Marcos","Omari","Shane","Keegan","Jase","Asa","Sonny","Uriel","Pierce","Jasiah","Eden","Rocco","Banks","Cannon","Denver","Zaiden","Roberto","Shawn","Drew","Emanuel","Kolton","Ayaan","Ares","Conner","Jalen","Alonzo","Enrique","Dalton","Moses","Koda","Bodie","Jamison","Phillip","Zaire","Jonas","Kylo","Moises","Shepherd","Allen","Kenzo","Mohamed","Keanu","Dexter","Conrad","Bruce","Sylas","Soren","Raphael","Rowen","Gunnar","Sutton","Quentin","Jaziel","Emmitt","Makai","Koa","Maximilian","Brixton","Dariel","Zachariah","Roy","Armando","Corey","Saul","Izaiah","Danny","Davis","Ridge","Yusuf","Ariel","Valentino","Jayson","Ronald","Albert","Gerardo","Ryland","Dorian","Drake","Gage","Rodrigo","Hezekiah","Kylan","Boone","Ledger","Santana","Jamari","Jamir","Lawrence","Reece","Kaysen","Shiloh","Arjun","Marcelo","Abram","Benson","Huxley","Nikolas","Zain","Kohen","Samson","Miller","Donald","Finnley","Kannon","Lucian","Watson","Keith","Westin","Tadeo","Sincere","Boston","Axton","Amos","Chandler","Leandro","Raul","Scott","Reign","Alessandro","Camilo","Derrick","Morgan","Julio","Clay","Edison","Jaime","Augustine","Julien","Zeke","Marvin","Bellamy","Landen","Dustin","Jamie","Krew","Kyree","Colter","Johan","Houston","Layton","Quincy","Case","Atreus","Cayson","Aarav","Darius","Harlan","Justice","Abdiel","Layne","Raylan","Arturo","Taylor","Anakin","Ander","Hamza","Otis","Azariah","Leonard","Colby","Duke","Flynn","Trey","Gustavo","Fletcher","Issac","Sam","Trenton","Callahan","Chris","Mohammad","Rayan","Lionel","Bruno","Jaxxon","Zaid","Brycen","Roland","Dillon","Lennon","Ambrose","Rio","Mac","Ahmed","Samir","Yosef","Tru","Creed","Tony","Alden","Aden","Alec","Carmelo","Dario","Marcel","Roger","Ty","Ahmad","Emir","Landyn","Skyler","Mohammed","Dennis","Kareem","Nixon","Rex","Uriah","Lee","Louie","Rayden","Reese","Alberto","Cason","Quinton","Kingsley","Chaim","Alfredo","Mauricio","Caspian","Legacy","Ocean","Ozzy","Briar","Wilson","Forest","Grey","Joziah","Salem","Neil","Remi","Bridger","Harry","Jefferson","Lachlan","Nelson","Casen","Salvador","Magnus","Tommy","Marcellus","Maximo","Jerry","Clyde","Aron","Keaton","Eliam","Lian","Trace","Douglas","Junior","Titan","Cullen","Cillian","Musa","Mylo","Hugh","Tomas","Vincenzo","Westley","Langston","Byron","Kiaan","Loyal","Orlando","Kyro","Amias","Amiri","Jimmy","Vicente","Khari","Brendan","Rey","Ben","Emery","Zyair","Bjorn","Evander","Ramon","Alvin","Ricky","Jagger","Brock","Dakari","Eddie","Blaze","Gatlin","Alonso","Curtis","Kylian","Nathanael","Devon","Wayne","Zakai","Mathew","Rome","Riggs","Aryan","Avi","Hassan","Lochlan","Stanley","Dash","Kaiser","Benicio","Bryant","Talon","Rohan","Wesson","Joe","Noe","Melvin","Vihaan","Zayd","Darren","Enoch","Mitchell","Jedidiah","Brodie","Castiel","Ira","Lance","Guillermo","Thatcher","Ermias","Misael","Jakari","Emory","Mccoy","Rudy","Thaddeus","Valentin","Yehuda","Bode","Madden","Kase","Bear","Boden","Jiraiya","Maurice","Alvaro","Ameer","Demetrius","Eliseo","Kabir","Kellan","Allan","Azrael","Calum","Niklaus","Ray","Damari","Elio","Jon","Leighton","Axl","Dane","Eithan","Eugene","Kenji","Jakob","Colten","Eliel","Nova","Santos","Zahir","Idris","Ishaan","Kole","Korbin","Seven","Alaric","Kellen","Bronson","Franco","Wes","Larry","Mekhi","Jamal","Dilan","Elisha","Brennan","Kace","Van","Felipe","Fisher","Cal","Dior","Judson","Alfonso","Deandre","Rocky","Henrik","Reuben","Anders","Arian","Damir","Jacoby","Khalid","Kye","Mustafa","Jadiel","Stefan","Yousef","Aydin","Jericho","Robin","Wallace","Alistair","Davion","Alfred","Ernesto","Kyng","Everest","Gary","Leroy","Yahir","Braden","Kelvin","Kristian","Adler","Avyaan","Brayan","Jones","Truett","Aries","Joey","Randy","Jaxx","Jesiah","Jovanni","Azriel","Brecken","Harley","Zechariah","Gordon","Jakai","Carl","Graysen","Kylen","Ayan","Branson","Crosby","Dominik","Jabari","Jaxtyn","Kristopher","Ulises","Zyon","Fox","Howard","Salvatore","Turner","Vance","Harlem","Jair","Jakobe","Jeremias","Osiris","Azael","Bowie","Canaan","Elon","Granger","Karsyn","Zavier","Cain","Dangelo","Heath","Yisroel","Gian","Shepard","Harold","Kamdyn","Rene","Rodney","Yaakov","Adrien","Kartier","Cassian","Coleson","Ahmir","Darian","Genesis","Kalel","Agustin","Wylder","Yadiel","Ephraim","Kody","Neo","Ignacio","Osman","Aldo","Abdullah","Cory","Blaine","Dimitri","Khai","Landry","Palmer","Benedict","Leif","Koen","Maxton","Mordechai","Zev","Atharv","Bishop","Blaise","Davian"};
        String[] arrCountry=new String[]{"Malaysia","China","Australia","Afghanistan","Albania","Austria","India","Indonesia","Philippines","Turkey","Vietnam","Myanmar","North Korea"};
        String[] arrMajor=new String[]{"Agriculture","Area"," Ethnic","Aviation","Biological and Biomedical Sciences","Business Management","Computer Science","Construction Trades","Education","Engineering","English Language and Literature","Family and Consumer Sciences","History","Human Services","Legal Professions and Studies","Library Science","Mathematics and Statistics","Natural Resources and Conservation","Personal and Culinary Services","Philosophy and Religious Studies","Physical Sciences","Precision Production","Psychology","Science Technologies","Social Sciences","Theology and Religious Vocations","Visual and Performing Arts"};
        PrintStream ps=null;
        try {
            File file=new File("student.txt");
            ps=new PrintStream(file);
            Random random=new Random();
            for (int i = 0; i < 50000; i++) {
                ps.println(arrName[random.nextInt(arrName.length)]+":"+(int)(17+Math.random()*14)+":"+arrMajor[random.nextInt(arrMajor.length)]+":"+arrCountry[random.nextInt(arrCountry.length)]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
