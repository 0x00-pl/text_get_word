package sort_text;

import java.util.ArrayList;
import java.util.Collections;

public class Tsort_text {
  public static ArrayList<String> sort_text(String text, int len){
    ArrayList<String> ret= new ArrayList<String>(text.length());
    for(int i=0; i< text.length()-len; i++){
      ret.add(text.substring(i, i+len));
    }
    Collections.sort(ret);
    return ret;
  }
}
