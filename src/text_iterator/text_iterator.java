package text_iterator;

import java.util.Comparator;

public class text_iterator {
  String s;
  int pos;
  public text_iterator(String s, int pos){
    this.s=s;
    this.pos=pos;
  }
  public static class cmp implements Comparator<text_iterator>{
    @Override
    public int compare(text_iterator o1, text_iterator o2) {
      return o1.s.substring(o1.pos).compareTo(o2.s.substring(o2.pos));
    }
    
  }
}
