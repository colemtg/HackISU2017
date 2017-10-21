/*import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class drive {
    public static void main(String[] args) throws IOException, JDOMException {
        FileReader file = new FileReader("1-1000.txt");
        BufferedReader bufferedReader = new BufferedReader(file);
        String word;
        String pro="";
        String def="";
        ArrayList<Word> words = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter("words-and-pros.txt"));
        while ((word = bufferedReader.readLine())!=null)
        {
           // System.out.println(word);
            URL url = new URL("http://www.dictionaryapi.com/api/v1/references/collegiate/xml/"
                    + word + "?key=782cdb78-2970-44f3-a699-e4c511831106");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(br);
            Element classElement = document.getRootElement();
            List<Element> entryList = classElement.getChildren("entry");
            for(int i=0; i<entryList.size(); i++)
            {
                if(entryList.get(i).getChildren("pr").size()!=0)
                {
                    pro = entryList.get(i).getChildren("pr").get(0).getValue();
                    if(pro.charAt(0) == 'ˈ')
                    {
                        pro = pro.substring(1,pro.length());
                    }
                    if(pro.length()>3*word.length())
                    {
                        pro=word;
                    }
                    for(int j=0; j<pro.length(); j++)
                    {
                        if(pro.charAt(j)=='-')
                        {
                            pro = pro.substring(0,j) + pro.substring(j+1,pro.length());
                        }
                        else if(pro.charAt(j)=='͟')
                        {
                            pro = pro.substring(0,j) + pro.substring(j+1,pro.length());
                        }
                        else if(pro.charAt(j)==',')
                        {
                            pro = pro.substring(0,j);
                            j=pro.length();
                        }
                    }
                    i=entryList.size();
                    //System.out.println(pro);
                }
                writer.append(word);
                writer.append(" ");
                writer.append(pro);
                writer.newLine();
            }
            /*
            for(int i=0; i<entryList.size(); i++)
            {
                if(entryList.get(i).getChildren("def").size()!=0)
                {
                   def = entryList.get(i).getChildren("def").
                            get(0).getChildren("dt").get(0).getValue();
                    i=entryList.size();
                    if(def.charAt(0)==':')
                    {
                        def = def.substring(1,def.length());
                    }
                    System.out.println(def);
                }
            }

            //words.add(new Word(word,pro,def));
        }
        writer.close();

    }
}
*/