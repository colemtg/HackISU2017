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

}
