/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

/**
 *
 * @author asmateus
 */
public class DescriptionStrings {
    
    private final static String path = "file://" + System.getProperty("user.dir") + "/data/";
    
    public static String getLevelUPString() {
        String lvlup = ""
            + "<html>"
            + "<head>"
            + "<title></title>"
            + "<meta content=\"\">"
            + "<style>"
            + "body{color:white;}"
            + "h1{color:yellow;text-align:center;}"
            + "h3{color:yellow;}"
            + "div{text-align:center;}"
            + "p{color:white;}"
                + "span{font-weight:bold;}"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<h1>LEVEL UP QUESTION</h1>\n"
            + "</body>"
            + "</html>";
        return lvlup;
    }
    
    public static String getDummyString() {
        String dummy = ""
            + "<html>"
            + "<head>"
            + "<title></title>"
            + "<meta content=\"\">"
            + "<style>"
            + "body{color:white;}"
            + "h1{color:yellow;text-align:center;}"
            + "h2{color:white;text-align:center;}"
            + "div{text-align:center;}"
            + "p{color:white;}"
                + "span{font-weight:bold;}"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<h1>MERGE SORT</h1>"
            + "<h2>GAME</h2>"
            + "<h2>Press I to start level</h2>"
            + "</body>"
            + "</html>";
        return dummy;
    }
    
    public static String getDescriptionLVL1(String pth) {
        String lvl1 = ""
            + "<html>"
            + "<head>"
            + "<title></title>"
            + "<meta content=\"\">"
            + "<style>"
            + "body{color:white;}"
            + "h1{color:yellow;text-align:center;}"
            + "h3{color:yellow;}"
            + "div{text-align:center;}"
            + "p{color:white;}"
                + "span{font-weight:bold;}"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<h1>LEVEL 1</h1>\n"
            + "<div>"
            + "<img src=\""+path+pth+"2.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"J.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"1.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"5.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"Q.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"2.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"3.png\" alt=\"ERROR\">"
            + "<img src=\""+path+pth+"8.png\" alt=\"ERROR\">"
            
            + "</div>"
            + "<h3>Description of MergeSort algorithm</h3>"
            + "<p>"
            + "Mergesort is a divide and conquer algorithm that was invented by John von Neumann in 1945. <br>"
            + "Conceptually, a merge sort works as follows: <br>"
            + "1. Divide the unsorted list into n sublists, each containing 1 element (a list of 1 element is considered sorted).<br>"
            + "2. Repeatedly merge sublists to produce new sorted sublists until there is only 1 sublist remaining. This will be the sorted list.<br>"
            + "</p>"
            + "<p>"
            + "The division step works as follows:<br>"
            + "1.1. we split the list in 2,<br>"
            + "1.2. look at the left sublist, if it has more than 1 member, store the right part in a stack and repeat from 1.1 with left part until false,<br>"
            + "1.3. repeat from 1.1. with the values of the stack.</p>"
            + "<p>"
            + "We will call a <span> div-iteration</span> each time the algorithm reaches 1.1 in the division step. Answer the following questions with"
            + " the current information."
            + "</p>"
            + "</body>"
            + "</html>";
        
        return lvl1;
    }
}
