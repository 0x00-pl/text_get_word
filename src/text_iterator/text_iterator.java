package text_iterator;

import java.util.Comparator;

public class text_iterator 
//implements Comparable<String>
{
  String s;
  int pos;
  
  public text_iterator(String s, int pos) {
    this.s = s;
    this.pos = pos;
  }
  
  public String toString(){
    return s.substring(pos);
  }
  
  public String get_line(){
    int beg= pos;
    while(beg>0 && s.charAt(beg)!='\n') 
      beg--;
    beg++;
    String temp= s.substring(beg);
    return temp.substring(0, temp.indexOf('\n'));
  }
  
  public static class cmp implements Comparator<text_iterator> {
    @Override
    public int compare(text_iterator o1, text_iterator o2) {
      return o1.s.substring(o1.pos).compareTo(o2.s.substring(o2.pos));
    }
  }
  
//  public static class start_with implements Comparator<text_iterator> {
//    @Override
//    public int compare(text_iterator o1, text_iterator o2) {
//      int len = o2.pos;
//      return o1.s.substring(o1.pos, o1.pos+len).compareTo(o2.s);
//    }
//  }

  public static class start_with_str{
    String val;
    public start_with_str(String val){ this.val= val; }
    public boolean equals(Object arg0){
      text_iterator temp= (text_iterator) arg0;
      return  temp.s.startsWith(val, temp.pos);
    }
  }
  
//  @Override
//  public int compareTo(String arg0) {
//    return s.startsWith(arg0, pos)? 0: s.substring(pos).compareTo(arg0);
//  }
}
