package unit_test;

import get_word.Tget_word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import result_diff.result_diff;
import text_iterator.get_word_range;
import text_iterator.get_word_range.word_range;
import text_iterator.text_iterator;

public class main {
  static boolean filp_no_cn_char(String text){
    String bad_char=" ×ü¡¡\n\r\t&@_/~¡«-¡ª#¡­:;¡¿[]{}()£¨£©£º£»£¡'¡¤¡¢£¬£¬¡£.£®,£¿¡°¡±$! abcdefjhigklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for(int i=0; i<bad_char.length(); i++)
      if(text.indexOf(bad_char.charAt(i))!=-1)
        return false;
    return true;
  } 

  static ArrayList<Map<String,String>> f(String text){
    ArrayList<text_iterator> txt_iter_set = new ArrayList<>();
    for(int i=1; i<text.length()-10; i++){
      txt_iter_set.add(new text_iterator(text, i));
    }
  
    Collections.sort(txt_iter_set, new text_iterator.cmp());
    
    get_word_range range_stream= new get_word_range(txt_iter_set);
    range_stream.word_len= 2;
    double frenpce_rate= Math.pow(1.0/10, range_stream.word_len*2);
    
    ArrayList<Map<String,String>> ret= new ArrayList<Map<String,String>>();
    while(!range_stream.empty()){
      word_range dbg= range_stream.get_next_range();
      String txt= dbg.get_text();

//      if(txt.startsWith("µöÓãµº")){
//        Map<String,String> temp = new HashMap<String,String>();
//        temp.put("name", dbg.get_text());
//        temp.put("sum", "" + dbg.get_sum_text());
//        Entry<Float,Float> v = dbg.get_variance_rate();
//        temp.put("l_cariance", v.getKey().toString());
//        temp.put("r_cariance", v.getValue().toString());
//        System.out.println(temp);
//        int i=0;
//      }
      double min_sum= txt_iter_set.size()*frenpce_rate;
      if(dbg.get_sum_text()< min_sum) continue;
      if(!filp_no_cn_char(txt)) continue;
      System.out.print("["+txt+"]: ");
      System.out.print(dbg.get_sum_text());
      //if(dbg.get_variance_rate().getKey()> 0.8) continue;
      //if(dbg.get_variance_rate().getValue()> 0.7) continue;
      //float core_rate= (dbg.get_sum_text()*2.0f)/(range_stream.sum_str(dbg.get_text().substring(0,1))+range_stream.sum_str(dbg.get_text().substring(1,2)));
      //double p_rand= 1.0f* range_stream.sum_str(dbg.get_text().substring(1,2))/txt_iter_set.size() *
      //    range_stream.sum_str(dbg.get_text().substring(0,1))/txt_iter_set.size();
      double core_rate= 0;
      //core_rate= 1.0*dbg.get_sum_text()/txt_iter_set.size() / p_rand;
      //if(core_rate< 20) continue;
      {
        // System.out.println(dbg.get_text()+
        // "["+dbg.get_sum_text()+": "+
        // +core_rate+
        // "] ["+dbg.get_variance_rate()+"]");
        Map<String,String> temp = new HashMap<String,String>();
        temp.put("name", dbg.get_text());
        temp.put("sum", "" + dbg.get_sum_text());
        Entry<Float,Float> v = dbg.get_variance_rate();
        temp.put("l_cariance", v.getKey().toString());
        temp.put("r_cariance", v.getValue().toString());
        ret.add(temp);
        System.out.println(temp+"core_rate="+core_rate);
      }
    }
//    word_range showi= range_stream.get("µöÓãµº");
//    for(int i= showi.text_iterator_beg; i!= showi.text_iterator_end; i++){
//      System.out.print(txt_iter_set.get(i).get_line());
//    }
    return ret;
  }
  
  
  static void show_result(ArrayList<Map<String,String>> data){
    Collections.sort(data, new Comparator<Map<String,String>>() {
      @Override
      public int compare(Map<String,String> o1, Map<String,String> o2) {
        return o2.get("sum").compareTo(o1.get("sum"));
      }
    });

    for(int i=0; i<data.size(); i++){
//      System.out.println(data.get(i));
      Map<String,String> iter= data.get(i);
      System.out.print(iter.get("name")+",");
      System.out.print(iter.get("sum")+",");
      System.out.print(iter.get("l_cariance")+",");
      System.out.println(iter.get("r_cariance"));
      
    }
  }
  
  public static ArrayList<Map<String,String>> f_file(String filename){
    String s_file=null;
    try {
      FileInputStream fin= new FileInputStream(filename);
      long file_len= new File(filename).length();
      byte[] buff= new byte[(int) Math.min(file_len, 1e8)]; 
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
    return f(s_file);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {

    //ArrayList<Map<String,String>> result1= f_file("E:\\myhome\\res\\weibo\\result\\weibo_at_ 2012-08-28.1346126146.264.txt");
    ArrayList<Map<String,String>> result1= f_file("E:\\myhome\\res\\weibo\\result\\weibo_at_ 2012-09-03.1346636609.317.txt");
    ArrayList<Map<String,String>> result2= f_file("E:\\myhome\\res\\weibo\\result\\weibo_at_ 2012-09-10.1347241586.344.txt");
    result_diff diff= new result_diff(result1, result2);
    Object diffmsg= diff.get_diff();
    convert_python.from_map tmp = null;
    String diffmsg_py_fmt= tmp.format_kv((Map<Object,Object>)diffmsg);
    System.out.println(diffmsg_py_fmt);
    //show_result(result1);
    int i=0;
    int j=i;
  }

}
