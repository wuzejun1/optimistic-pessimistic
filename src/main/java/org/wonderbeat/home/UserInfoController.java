package org.wonderbeat.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wonderbeat.home.service.UserService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/test")
public class UserInfoController {

    @Inject
    UserService userService;

    @RequestMapping("/user/{id}/balance")
    @ResponseBody
    public String userBalance(@PathVariable @NotNull Integer id) {
        return String.valueOf(userService.userBalance(id));
    }
}
