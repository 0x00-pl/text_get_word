package get_word;

import java.util.ArrayList;

public class Tget_word {
  public static int intersection_len(String s1, String s2){
    int len=0;
    while(s1.charAt(len)==s2.charAt(len) && len+1<s1.length() && len+1<s2.length()) 
      len++;
    return len;
  }
  public static ArrayList<String> get_word(ArrayList<String> sorted_text, int times_over){
    int strlen= sorted_text.get(0).length();
    int[] begat= new int[strlen];
    for(int i=1; i<strlen; i++){
      begat[i]= 1;
    }
    ArrayList<String> ret= new ArrayList<>();
    int last_match_len=0;
    String last_str= sorted_text.get(0);
    for(int i=1; i<=sorted_text.size(); i++){      
      String new_str;
      int new_match_len;
      if(i==sorted_text.size()){
        new_str= "";
        new_match_len= 0;
      }else{
        new_str= sorted_text.get(i);
        new_match_len= intersection_len(last_str, new_str);
      }
      
      //pop word
      while(new_match_len< last_match_len){
        //flip
        if(times_over< begat[last_match_len-1]){
            ret.add(last_str.substring(0,last_match_len));
        }
        last_match_len--;
      }
      //update begat
      for(int j=0; j<new_match_len; j++){
        begat[j]++;
      }
      for(int j=new_match_len; j<strlen; j++){
        begat[j]=1;
      }
      //update last_len
      last_match_len= new_match_len;
      last_str= new_str;
    }
    return ret;
  }
}
