package org.wonderbeat.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wonderbeat.home.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
class HomeController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index").addObject("users", userService.usersStatuses());
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid UserCommand command) {
        userService.createUser(command.getUserId());
        return "redirect:/";
    }

    @RequestMapping(value = "/user/block", method = RequestMethod.POST)
    public String blockUser(@ModelAttribute @Valid UserCommand command) {
        userService.blockUser(command.getUserId());
        return "redirect:/";
    }

    void setUserService(UserService userService) {
        this.userService = userService;
    }
}
