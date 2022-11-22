/* SOFTWARE: IDEA INTERFACE© */
/* THIS SOFTWARE CAN BE USED TO REGISTER NEW IDEAS INTO THE FILE: "appSynthesis.txt" */

/* POSSIBLE IMPROVEMENTS:   -> ALLOW DELETION OF CERTAIN ELEMENTS
                            -> ALLOW MODIFICATION OF SPECIFIC ELEMENTS
                            -> ADD \  */

import java.util.*;
import java.io.*;
import java.lang.String;
import java.lang.System;

public class Ideas{

    //VARIABLES AND CONSTANTS START
    static int maxIdeas = 500;

    private static final String STRING = ". ";
    public static final String GREENBG = "\u001B[42m";
    public static final String REDBG = "\u001B[41m";
    public static final String BLACKBG = "\u001B[40m";
    public static final String BLUEFT = "\u001B[34m";
    public static final String BLACKFT = "\u001B[30m";
    public static final String REDFT = "\u001B[31m";

    public static final String RESET = "\u001B[0m";

    String type;
    String title;
    String description;
    int lvl;

    static Ideas newItems[] = new Ideas[maxIdeas];
    static Ideas newESs[] = new Ideas[maxIdeas];
    static Ideas newApps[] = new Ideas[maxIdeas];
    static String newtItems[] = new String[maxIdeas];
    static String newtESs[] = new String[maxIdeas];
    static String newtApps[] = new String[maxIdeas];

    static Ideas Items[] = new Ideas[maxIdeas];
    static Ideas ESs[] = new Ideas[maxIdeas];
    static Ideas Apps[] = new Ideas[maxIdeas];
    static String tItems[] = new String[maxIdeas];
    static String tESs[] = new String[maxIdeas];
    static String tApps[] = new String[maxIdeas];

    static String Sheet[][] = new String[maxIdeas + 1][4];
    //VARIABLES AND CONSTANTS END

    //ACCESSING THE FILE METHOD START
    public static String[] fileAccess() throws FileNotFoundException{
        File myObj = new File("appSynthesis.txt");

        Scanner scf = new Scanner(myObj);

        String content = "";
        while(scf.hasNextLine()){
            content += scf.nextLine() + "\n";
        }
        scf.close();

        String contentList[] = content.split("\n");

        return contentList;
    }
    //ACCESSING THE FILE METHOD END

    //CREATING ELEMENTS START
    public void createIdea(){
        System.out.print("\nWelcome to Idea Interface. To create a new idea, press enter: ");
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
        String entry = sc.useDelimiter("\r\n").nextLine();
        if(entry == ""){
            System.out.println("\nPlease try again.\n");
            createIdea();
        }
        typeOfIdea();
    }

    public void typeOfIdea(){
        System.out.println("\n\nYou're in. Now please enter the type of project you wish to pursue (type either \"ES\" for embedded system or \"App\"):");
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
        String type = sc.nextLine();
        this.type = type;
        this.title();
    }

    public void title(){
        System.out.println("\nNow, please enter a title for your project:");
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
        String title = sc.nextLine();
        this.title = title;
        appendString(newtItems, this.title);
        System.out.println(this.type);
        if(this.type.equals("ES")){
            newES();
        }
        else if(this.type.equals("App")){
            newApp();
        }
        else{
            System.out.println("\nError, please try again with the type of idea:");
            typeOfIdea();
        }
    }

    public void newES(){
        System.out.println("Now, describe the project (its purpose, its functions, etc.):");
        Scanner sc1 = new Scanner(System.in).useDelimiter("\r\n");
        String description = sc1.nextLine();
        this.description = description;
        System.out.print("According to you, what would be the amplitude of the project on a scale from 1 to 10?\t");
        Scanner sc2 = new Scanner(System.in).useDelimiter("\r\n");
        int lvl = sc2.nextInt();
        lvl = (lvl <= 10) ? lvl : 10;
        lvl = (lvl >= 1) ? lvl : 1;
        this.lvl = lvl;
    }

    public void newApp(){
        System.out.println("Now, describe the project (its purpose, interfaces, functions, etc.):");
        Scanner sc1 = new Scanner(System.in).useDelimiter("\r\n");
        String description = sc1.nextLine();
        this.description = description;
        System.out.print("According to you, what would be the amplitude of the project on a scale from 1 to 10?\t");
        Scanner sc2 = new Scanner(System.in).useDelimiter("\r\n");
        int lvl = sc2.nextInt();
        lvl = (lvl <= 10) ? lvl : 10;
        lvl = (lvl >= 1) ? lvl : 1;
        this.lvl = lvl;
    }
    //CREATING ELEMENTS METHODS END

    //MAIN START
    public static void main(String argv[]) throws IOException{
        int i = 0;
        int j = 0;
        int k = 0;

        String contentList[] = fileAccess();

        writeLists(contentList);

        while(j < maxIdeas){
            System.out.print("\n\nHello. Do you wish to create a new project (1 + enter)? Or manage your projects (0 + enter)? ");
            Scanner sce = new Scanner(System.in).useDelimiter("\r\n");
            System.out.println("Proceed...");
            int enter = sce.nextInt();
            if(enter == 1){
                Ideas idea = new Ideas();

                idea.createIdea();

                if(idea.type.equals("ES")){
                    newESs[i] = idea;
                    newtESs[i] = idea.title;
                    i++;
                }
                else if(idea.type.equals("App")){
                    newApps[j] = idea;
                    newtApps[j] = idea.title;
                    j++;
                }
                newItems[k] = idea;
                newtItems[k] = idea.title;

                k++;
            }
            else if(enter == 0){
            getType();
            }
            System.out.println("Get out (1 or 0)?");
            Scanner scout = new Scanner(System.in);
            int out = scout.nextInt();
            if(out == 1){
                break;
            }
        }

        updateContentList(contentList);

        int indexN = indexNull(contentList);

        String SheetString = "";

        for(int s = 0; s < indexN; s++){
            SheetString += contentList[s].toString() + "\n";
        }

        List2D(Sheet, newItems);

        for(int n = 0; n < Sheet.length - 1; n++){
            SheetString += Arrays.toString(Sheet[n]) + "\n";
        }

        FileWriter file = new FileWriter("appSynthesis.txt");
        
        file.write(SheetString);

        file.close();
    }
    //MAIN END

    //GETTING ELEMENTS METHODS START
    public static void getType(){
        System.out.println("Welcome to Idea Interface.\nPlease select a type of idea to manage by entering its number and pressing enter:\n");
        System.out.println("1. ES (Embedded System)");
        System.out.println("2. App");
        System.out.println("3. Show All");
        System.out.print("\n\tNumber: ");
        Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
        int type = sc.nextInt();
        switch(type){
            case 1:
                getES();
                break;
            case 2:
                getApp();
                break;
            case 3:
                getAll();
                break;
            default:
                System.out.println("Error: Can't find type " + type + ". Please try again.");
                getType();
        }
    }

    public static void getES(){
        if(ESs[0] == null){
            System.out.println("\nNo embedded systems registered yet.");
        }
        else{
            System.out.println("\n\n"+ BLUEFT + REDBG + "Please select the number associated with the project in question:" + RESET + "\n");
            for(Ideas item: ESs){
                if(item != null){
                    System.out.println(indexOfIdeas(ESs, item) + 1 + STRING + item.title);
                }
            }
            System.out.print("\n\tNumber: ");
            Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
            int nItem = sc.nextInt();
            if(nItem < ESs.length && nItem >= 0){
                Ideas item = ESs[nItem - 1];
                System.out.println(GREENBG + BLACKFT + "\n\n\tTitle of the project: " + item.title + "\n");
                System.out.println("\tThe project is: an " + item.type + "\n");
                System.out.println("\tThe project has an amplitude of level: " + item.lvl + "\n");
                System.out.println("\tThe description of the ES: " + item.description + "\n\n" + RESET);
                System.out.println(REDFT + "END.\n" + RESET);
            }
        }
    }

    public static void getApp(){
        if(Apps[0] == null){
            System.out.println("\nNo apps registered yet.");
        }
        else{
            System.out.println("\n\n"+ BLUEFT + REDBG + "Please select the number associated with the project in question:" + RESET + "\n");
            for(Ideas item: Apps){
                if(item != null){
                    System.out.println(indexOfIdeas(Apps, item) + 1 + STRING +  item.title);
                }
            }
            System.out.print("\n\tNumber: ");
            Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
            int nItem = sc.nextInt();
            if(nItem < Apps.length && nItem >= 0){
                Ideas item = Apps[nItem - 1];
                System.out.println(GREENBG + BLACKFT + "\n\n\tTitle of the project: " + item.title + "\n");
                System.out.println("\tThe project is: an " + item.type + "\n");
                System.out.println("\tThe project has an amplitude of level: " + item.lvl + "\n");
                System.out.println("\tThe description of the app: " + item.description + "\n\n" + RESET);
                System.out.println(REDFT + "END.\n" + RESET);
            }
        }
    }

    public static void getAll(){
        if(Items[0] == null){
            System.out.println("\nNo items registered yet.");
        }
        else{
            System.out.println("\n\n"+ BLUEFT + REDBG + "Please select the number associated with the project in question:" + RESET + "\n");
            for(Ideas item: Items){
                if(item != null){
                    System.out.println(indexOfIdeas(Items, item) + 1 + STRING + item.title);
                }
            }
            System.out.print("\n\tNumber: ");
            Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
            int nItem = sc.nextInt();
            if(nItem < Items.length && nItem >= 0){
                Ideas item = Items[nItem - 1];
                System.out.println(GREENBG + BLACKFT + "\n\n\tTitle of the project: " + item.title + "\n");
                System.out.println("\tThe project is: an " + item.type + "\n");
                System.out.println("\tThe project has an amplitude of level: " + item.lvl + "\n");
                System.out.println("\tThe description of the project: " + item.description + "\n\n" + RESET);
                System.out.println(REDFT + "END.\n" + RESET);
            }
        }
    }
    //GETTING ELEMENTS METHODS END

    //LIST AND INDEX MANIPULATION METHODS START
    public Ideas[] appendIdeas(Ideas L[], Ideas i){
        Ideas arrNew[] = new Ideas[L.length + 1];
        int n;
        for(n = 0; n < L.length; n++){
            arrNew[n] = L[n];
        }
        arrNew[n] = i;
        return arrNew;
    }
    public String[] appendString(String L[], String s){
        String arrNew[] = new String[L.length + 1];
        int n;
        for(n = 0; n < L.length; n++){
            arrNew[n] = L[n];
        }
        arrNew[n] = s;
        return arrNew;
    }

    public static int indexOfIdeas(Ideas L[], Ideas e){
        int index = L.length - 1;
        int i = 0;
        while(i < L.length){
            if(L[i] == e){
                index = i;
                break;
            }
            i++;
        }
        return index;
    }
    public static int indexOfString(String L[], String e){
        int index = -1;
        int i = 0;
        while(i < L.length){
            if(L[i] == e){
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    public static int indexNull(String[] L){
        int index = 0;
        while(!L[index].equals("[null, null, null, null]")){
            index++;
        }
        return index;
    }

    public static void List2D(String[][] L, Ideas[] L1){
        for(int i = 0; i < L1.length; i++){
            if(L1[i] != null){
                L[i][0] = L1[i].title;
                L[i][1] = L1[i].type;
                L[i][2] = L1[i].description;
                L[i][3] = "" + L1[i].lvl;
            }
        }
    }
    //LIST AND INDEX MANIPULATION METHODS END

    public static void writeLists(String[] contentList){
        String contentArray[][] = new String[maxIdeas][4];
        int i = 0;
        int j = 0;
        for(int k = 0; k < Items.length - 1; k++){
            contentArray[k] = contentList[k+1].split(",");
            Ideas idea = new Ideas();

            if(!Arrays.toString(contentArray[k]).equals("[[null,  null,  null,  null]]")){

                idea.title = contentArray[k][0].substring(1);
                idea.type = contentArray[k][1];
                idea.description = contentArray[k][2];
                idea.lvl = Integer.parseInt(contentArray[k][3].substring(1, contentArray[k][3].length() - 1));

                if(idea.type.equals(" ES")){
                    ESs[i] = idea;
                    tESs[i] = idea.title;
                    i++;
                }
                else if(idea.type.equals(" App")){
                    Apps[j] = idea;
                    tApps[j] = idea.title;
                    j++;
                }
                Items[k] = idea;
                tItems[k] = idea.title;
            }
        }
    }

    public static void updateContentList(String[] contentList){
        for(int i = 0; i < Items.length; i++){
            if(Items[i] != null){
                contentList[i+1] = "[" + Items[i].title + "," + Items[i].type + "," + Items[i].description + ", " + Items[i].lvl + "]";
            }
        }
    }
}

/* SOFTWARE CREATED BY TOM HALL
THE POINT OF THIS SOFTWARE IS NOT TO BE A FULLY USABLE SOFTWARE. IT IS SIMPLY AN EXERCISE IN JAVA. */
