package unit_test;

import get_word.Tget_word;

import java.util.ArrayList;

import sort_text.Tsort_text;

public class main {
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String str1="³ÔÆÏÌÑ²»ÍÂÆÏÌÑÆ¤1234567890";
    String str2="chiputaobutuputaopi123456";
    
    ArrayList<String> temp1= Tsort_text.sort_text(str1, 10);
    ArrayList<String> temp2= Tget_word.get_word(temp1, 1);
    
    for(int i=0; i< temp2.size(); i++){
      System.out.println(temp2.get(i));
    }
  }
  
}
