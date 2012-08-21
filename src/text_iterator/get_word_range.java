package text_iterator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class get_word_range {
  ArrayList<text_iterator> sorted_list;
  int index = 0;
  int word_len = 2;
  
  public get_word_range(ArrayList<text_iterator> sorted_list) {
    this.sorted_list = sorted_list;
  }
  
  public static class word_range {
    public int world_len= Integer.MIN_VALUE;
    public int text_iterator_beg;
    public int text_iterator_end;
    public ArrayList<text_iterator> sorted_list;
    
    public word_range(ArrayList<text_iterator> sorted_list){
      this.sorted_list= sorted_list;
    }
    
    public text_iterator beg() {
      return sorted_list.get(text_iterator_beg);
    }
    
    public text_iterator end() {
      return sorted_list.get(text_iterator_end);
    }
    
    public String get_text() {
      text_iterator temp_beg=  beg();
      return temp_beg.s.substring(temp_beg.pos, temp_beg.pos+world_len);
    }
    
    public Map.Entry<Float,Float> get_variance_rate(){
      float sqsum_l=0;
      char last_l='~';
      int lastn_l=0;
      
      float sqsum_r=0;
      char last_r='~';
      int lastn_r=0;
      
      for(int i= text_iterator_beg; i< text_iterator_end; i++){
        text_iterator cur= sorted_list.get(i);
        // l
        {
          if (last_l == cur.s.charAt(cur.pos + world_len)) {
            lastn_l++;
          } else {
            sqsum_l += lastn_l * lastn_l;
            last_l = cur.s.charAt(cur.pos + world_len);
            lastn_l = 1;
          }
        }
        
        // r
        {
          if (last_r == cur.s.charAt(cur.pos - 1)) {
            lastn_r++;
          } else {
            sqsum_r += lastn_r * lastn_r;
            last_r = cur.s.charAt(cur.pos - 1);
            lastn_r = 1;
          }
        }
      }
      sqsum_l+= lastn_l*lastn_l;
      sqsum_r+= lastn_r*lastn_r;
      
      
      float sum_sq= text_iterator_end-text_iterator_beg;
      sum_sq*= sum_sq;
      
      return new AbstractMap.SimpleEntry<Float,Float>(sqsum_l/sum_sq, sqsum_r/sum_sq);
    }
 
    public int get_sum_text(){
      return text_iterator_end- text_iterator_beg;
    }
  }
  
  int get_first_unmatch(String cmp_obj) {
    for (int i = index; i < sorted_list.size(); i++) {
      text_iterator iter = sorted_list.get(i);
      if (!iter.s.startsWith(cmp_obj, iter.pos)) {
        return i;
      }
    }
    return sorted_list.size();
  }
  
  public word_range get_next_range() {
    word_range ret = new word_range(sorted_list);
    ret.text_iterator_beg = index;
    
    while (index < sorted_list.size()) {
      // unitl
      String cur_word = ret.beg().s.substring(ret.beg().pos,
          Math.min(ret.beg().s.length(), ret.beg().pos + word_len));
      int range_end = get_first_unmatch(cur_word);
      //useless
//      if (!(range_end < sorted_list.size()))
//        return null;
      index = range_end;
      ret.text_iterator_end = range_end;
      ret.world_len= word_len;
      return ret;
    }
    return null;
  }
  
  public boolean empty() {
    return !(index < sorted_list.size());
  }
}
