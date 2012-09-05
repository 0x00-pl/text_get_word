package result_diff;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class result_diff {
  ArrayList<Map<String,String>> a;
  ArrayList<Map<String,String>> b;
  public result_diff(ArrayList<Map<String,String>> o1, ArrayList<Map<String,String>> o2){
    a=o1;
    b=o2;
  }
  
  Map<String,Number> get_map(ArrayList<Map<String,String>> obj){
    Map<String,Number> ret= new HashMap<>();
    for(ListIterator<Map<String,String>> i=a.listIterator(); i.hasNext();){
      Map<String,String> ety= i.next();
      String k= ety.get("name");
      Number v= Integer.parseInt(ety.get("sum"));
      ret.put(k,v);
    }
    return ret;
  }
  
  public Map<String,Number> get_diff(){
    Map<String,Number> ret= new HashMap<String,Number>();
    Map<String,Number> mapa= get_map(a);
    Map<String,Number> mapb= get_map(b);

    Set<String> kset= new HashSet<>(mapa.keySet());
    kset.addAll(mapb.keySet());
    
    for(Iterator<String> it=kset.iterator(); it.hasNext(); ){
      String k= it.next();
      int na=0;
      int nb=0;
      if(mapa.containsKey(k)) na= mapa.get(k).intValue();
      if(mapb.containsKey(k)) nb= mapb.get(k).intValue();
      
      ret.put(k, Math.abs(na-nb));
    }
    
    return ret;
  }
  
}
