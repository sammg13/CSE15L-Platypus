import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        System.out.println(markdown.length());
        while(currentIndex < markdown.length()) {
            System.out.println(currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            System.out.println(openParen);
            System.out.println(closeParen);
            System.out.println(nextOpenBracket);
            System.out.println(nextCloseBracket);
            if(openParen == -1 || closeParen == -1 || nextOpenBracket == - 1 || nextCloseBracket == -1){
                return toReturn;
            }
            if(nextOpenBracket > 0 && !markdown.substring(nextOpenBracket-1,nextOpenBracket).equals("!")){
                if(openParen - nextCloseBracket <= 1){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }
            currentIndex = closeParen + 1; 
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
// javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java
// java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest