package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dictionary {

    private Map <String, String> dictionary;
    private List <String> list;

    public Dictionary (){
        this.dictionary = new HashMap <> ();
        this.list = new ArrayList <> ();
    }

    public String get (String word) {
        return this.dictionary.get(word);
    }

    public void add (String word, String trans) {
        if (!(this.dictionary.containsKey(word))){
            this.list.add(word);
            this.dictionary.putIfAbsent(word, trans);
        }
    }

    public String getRandomWord () {
        Random random = new Random();
        if (list.isEmpty()){
            return "";
        }
        return this.list.get(random.nextInt(this.list.size()));
    }
}
