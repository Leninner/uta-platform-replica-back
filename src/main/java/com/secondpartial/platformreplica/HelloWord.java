package com.secondpartial.platformreplica;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
  // in this case, the return type is a map in a get request
  @RequestMapping("/")
  public Map<String, String> sayHello() {
    HashMap<String, String> map = new HashMap<>();
    map.put("key", "value");
    map.put("foo", "bar");
    map.put("aa", "bb");
    return map; 
  }
}
