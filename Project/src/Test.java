import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;

public class Test {
    public static void main(String args[]) throws IOException {





    }
    static double compare(String word, String pro)
    {
        System.out.println("comparing " + word + " to " + pro);
        int i=0;
        int j=0;
        int startI=0;
        int startJ=0;
        int count=0;
        boolean found=false;
        while(i<word.length() && j<pro.length())
        {
            if(pro.charAt(j) == '-') // or just remove in parse
            {
                j++;
            }
            if(word.charAt(i) == pro.charAt(j))
            {
                count++;
                if(found)
                {
                    Rule rule = new Rule(word.substring(startI,i),pro.substring(startJ,j));
                    System.out.println(word.substring(startI,i));
                    System.out.println(pro.substring(startJ,j));
                }
                i++;
                j++;
                found=false;
            }
            else if(!found)
            {
                found=true;
                startI=i;
                startJ=j;

                j++;

                if(i==word.length() || j==pro.length())
                {
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pro.substring(startJ,pro.length()));
                }
            }
            else //if(found)
            {
                if(pro.charAt(j)>'z' || pro.charAt(j) < 'a')
                {
                    j++;
                }
                else
                {
                    i++;
                }
                if(i==word.length() || j==pro.length())
                {
                    System.out.println(word.substring(startI,word.length()));
                    System.out.println(pro.substring(startJ,pro.length()));
                }
            }
        }
        System.out.println("amount same:" + count);
        return (word.length()-count)/word.length();

    }
}
        /*try {
            String word = "apple";
            URL url = new URL("http://www.dictionaryapi.com/api/v1/references/collegiate/xml/"
                    + word + "?key=782cdb78-2970-44f3-a699-e4c511831106");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(br);
            Element classElement = document.getRootElement();
            List<Element> entryList = classElement.getChildren("entry");
            List<Element> pronunciation = entryList.get(0).getChildren("pr");
            System.out.println(pronunciation.get(0).getValue());
            List<Element> def = entryList.get(0).getChildren("def");
            List<Element> defintion = def.get(0).getChildren("dt");
            System.out.println(defintion.get(0).getValue());

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
        /*
        ArrayList<Double> testList= new ArrayList<>(Arrays.asList(.5,1.0,1.5,1.25,1.75,2.0));
        final SecureRandom generator = new SecureRandom();
        double randomNum=generator.nextDouble()*8;
        System.out.println(randomNum);
        for (int i =0; i<testList.size();i++){
            //Word currWord = wordsInPool.get(i);
            randomNum-=testList.get(i);
            System.out.println(randomNum);
            if (randomNum<=0){
                System.out.println("Index is:"+i);
            }
        }

    }
    */


