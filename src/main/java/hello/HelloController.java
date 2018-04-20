package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Welcome to Gregg's Points Bank";
  }

  //get organizations
  @RequestMapping(value = "/organizations", method = RequestMethod.GET)
  public List<String> getOrganizations() {
    List<String> list = OrganizationDAO.getAll();
    System.out.println("\n****LOG****\n");
    System.out.println(list);
    return list;
  }

  //append a new organization to the organization list
  @RequestMapping(value = "/organizations/{organization}", method = RequestMethod.POST)
  public void insertOrganization(@PathVariable String organization) {
    OrganizationDAO.insert(organization);
    return;
  }

  @RequestMapping(value = "/organizations", method = RequestMethod.PUT)
  public String notSupported() {
    return "Put method is not supported";
  }

  @RequestMapping(value = "/{partner}/members", method = RequestMethod.GET)
  public MemberList getmembers(@PathVariable String partner) {
    MemberList ml = new MemberList(partner);
    return ml;
  }

  @RequestMapping(value = "/{partner}/members", method = RequestMethod.DELETE)
  public String deletePartner(@PathVariable String partner) {
    PartnerList pl = new PartnerList();
    pl.deletePartner(partner);
    return "redirect:/items";
  }

  @RequestMapping("/seedmembers")
  public MemberList seedmembers() {//Rest Endpoint
    MemberList ml = new MemberList();
    return ml;
  }
}
