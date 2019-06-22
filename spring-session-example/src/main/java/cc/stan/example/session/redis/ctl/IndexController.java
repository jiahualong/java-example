package cc.stan.example.session.redis.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class IndexController {

    @GetMapping("/set/session")
    public void setSession(HttpSession session) {
        session.setAttribute("name", "yars");
    }

    @GetMapping("/get/session")
    public void getSession(HttpSession session) {
        System.out.println(session.getAttribute("name"));
    }

    @GetMapping("/get/session2")
    public void getSession2() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(req.getSession().getAttribute("name"));
    }


}
