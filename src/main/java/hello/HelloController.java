package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Welcome to Gregg's Points Bank";
  }

  @RequestMapping("/members/{partner}")
  public MemberList getmembers(@PathVariable String partner) {//Rest Endpoint
    MemberList ml = new MemberList();
    return ml;
  }

  @RequestMapping("/seedmembers")
  public MemberList seedmembers() {//Rest Endpoint
    MemberList ml = new MemberList();
    return ml;
  }
}
