package ch.asu.chaosdb.core.abstracts.filter;

import java.util.HashMap;
import java.util.Map;

// <formField, filterValue>
public class DynamicFilter extends HashMap<String, String> {
  // It is build like this:
  //  'fieldName' + Optional:one-of-these:['Like', 'Before', 'After', 'In']

  // 'Like':   Filters a String with %search%
  // 'Before': Filters Dates before search
  // 'After':  Filters Dates after search
  // 'In':     Filters Values in Array of search.forEach

  public static DynamicFilter from(Map<String, String> map) {
    DynamicFilter filter = new DynamicFilter();
    filter.putAll(map);
    return filter;
  }
}
