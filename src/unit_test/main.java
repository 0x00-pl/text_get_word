package unit_test;

import get_word.Tget_word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sort_text.Tsort_text;
import text_iterator.get_word_range;
import text_iterator.get_word_range.word_range;
import text_iterator.text_iterator;

public class main {
  
  static void f(String text){
    ArrayList<text_iterator> txt_iter_set = new ArrayList<>();
    for(int i=1; i<text.length()-3; i++){
      txt_iter_set.add(new text_iterator(text, i));
    }
  
    Collections.sort(txt_iter_set, new text_iterator.cmp());
    
    get_word_range range_stream= new get_word_range(txt_iter_set);
    while(!range_stream.empty()){
      word_range dbg= range_stream.get_next_range();
      if(dbg.get_sum_text()>1000 && dbg.get_variance_rate().getKey()<0.1 && dbg.get_variance_rate().getValue()<0.1)
        System.out.println(dbg.get_text()+"["+dbg.get_sum_text()+"] ["+dbg.get_variance_rate()+"]");
    }
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String s_file=null;
    try {
      FileInputStream fin= new FileInputStream("E:\\myhome\\res\\law_doc\\°¸Àý¿â_ÖÐ¹ú·¨ÔºÍø.txt");
      //byte[] buff= new byte[(int) new File("E:\\myhome\\res\\law_doc\\33k.txt").length()]; 
      byte[] buff= new byte[3000000]; 
      fin.read(buff);
      s_file= new String(buff, Charset.forName("utf8"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //f("^³ÔÆÏÌÑ²»ÍÂÆÏÌÑÆ¤²»³ÔÆÏÌÑµ½ÍÂÆÏÌÑÆ¤$");
    f(s_file);
    
    if (false) {
      String str1 = "³ÔÆÏÌÑ²»ÍÂÆÏÌÑÆ¤1234567890";
      String str2 = "chiputaobutuputaopi123456";
      
      ArrayList<String> temp1 = Tsort_text.sort_text(str1, 10);
      ArrayList<String> temp2 = Tget_word.get_word(temp1, 1);
      
      for (int i = 0; i < temp2.size(); i++) {
        System.out.println(temp2.get(i));
      }
    }
  }

}
