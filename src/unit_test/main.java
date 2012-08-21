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
    range_stream.word_len=4;
    
    while(!range_stream.empty()){
      word_range dbg= range_stream.get_next_range();
      if(dbg.get_sum_text()< (txt_iter_set.size()*0.0003)) continue;
      if(dbg.get_variance_rate().getKey()> 0.3) continue;
      if(dbg.get_variance_rate().getValue()> 0.3) continue;
      //float core_rate= (dbg.get_sum_text()*2.0f)/(range_stream.sum_str(dbg.get_text().substring(0,1))+range_stream.sum_str(dbg.get_text().substring(1,2)));
      double p_rand= 1.0f* range_stream.sum_str(dbg.get_text().substring(1,2))/txt_iter_set.size() *
          range_stream.sum_str(dbg.get_text().substring(0,1))/txt_iter_set.size();
      double core_rate= 1.0*dbg.get_sum_text()/txt_iter_set.size() / p_rand;
      if(core_rate< 20) continue;
      System.out.println(dbg.get_text()+
            "["+dbg.get_sum_text()+": "+
           +core_rate+
        "] ["+dbg.get_variance_rate()+"]");
    }
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String s_file=null;
    try {
      String filename= "E:\\myhome\\res\\law_doc\\°¸Àý¿â_ÖÐ¹ú·¨ÔºÍø.txt";
//      String filename= "E:\\myhome\\res\\cmfu_doc\\1689081.txt";
      
      FileInputStream fin= new FileInputStream(filename);
      //byte[] buff= new byte[(int) new File(filename).length()]; 
      byte[] buff= new byte[10000000]; 
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
