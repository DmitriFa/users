package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private int page;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUsers(@RequestParam(defaultValue = "1") int page) {
        List<User> users = userService.allUsers(page);
        int usersCount = userService.usersCount();
        int pagesCount = (usersCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("page", page);
        modelAndView.addObject("usersList", users);
        modelAndView.addObject("usersCount", usersCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkTitle(user.getName())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            userService.add(user);
        } else {
            modelAndView.addObject("message","part with title \"" + user.getName() + "\" already exists");
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkTitle(user.getName()) || userService.getById(user.getId()).getName().equals(user.getName())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            userService.edit(user);
        } else {
            modelAndView.addObject("message","part with title \"" + user.getName() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" +  + user.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id)  {
        ModelAndView modelAndView = new ModelAndView();
        int usersCount = userService.usersCount();
        int page = ((usersCount - 1) % 10 == 0 && usersCount > 10 && this.page == (usersCount + 9)/10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }
}
