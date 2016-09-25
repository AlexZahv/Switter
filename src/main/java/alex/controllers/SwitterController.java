package alex.controllers;

import alex.users.Account;
import alex.users.Swit;
import alex.users.service.SwitService;
import alex.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@MultipartConfig
@Controller
public class SwitterController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;
    @Autowired
    private SwitService switService;

    @RequestMapping(method = RequestMethod.GET, value = {"/"})
    public String getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        if ((login != null) && (password != null)) {
            if (userService.authentificateUser(login, password))
                return "redirect:/main";
        }
        return "authorization";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authorization")
    public String getAuthorizationPage() {
        return "authorization";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authorization")
    public String authorizeUser(@ModelAttribute Account account, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        if (userService.authentificateUser(account.getLogin(), account.getPassword())) {
            request.getSession().setAttribute("login", account.getLogin());
            request.getSession().setAttribute("password", account.getPassword());
            return "redirect:/main";
        } else return "authorization";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/main")
    public String getMainPage(@ModelAttribute Account account, HttpServletRequest request, Locale locale) {
        if (request.getSession().getAttribute("login") == null)
            return "redirect:/authorization";
        List<Swit> switList = switService.getLastSwits(0);
        for (Swit swit : switList) {
            String messageBody = swit.getMessageBody();
            messageBody = "<p>" + messageBody.replace("\n", "</p><p>") + "</p>";
            swit.setMessageBody(messageBody);
        }
        request.setAttribute("swits", switList);

        return "main";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute Account account, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "registration";
        if (userService.registerUser(account)) {
            request.getSession().setAttribute("login", account.getLogin());
            request.getSession().setAttribute("password", account.getPassword());
            return "redirect:/main";
        } else return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String getRegistrationPage(HttpServletRequest request, HttpServletResponse response) {
        return "registration";
    }

    @RequestMapping(value = "personal_page", method = RequestMethod.GET)
    public String getPersonalPage(HttpServletResponse response, HttpServletRequest request, @RequestParam(defaultValue = "") String login) {
        Account account;
        if (login.equals("")) {
            HttpSession session = request.getSession();
            login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            account = userService.getUser(login, password);
        } else {
            account = userService.getUser(login);
            request.setAttribute("login", login);
        }
        if (account == null)
            return "redirect:/authorization";
        request.setAttribute("account", account);
        request.setAttribute("swits", switService.getSwitsPerUser(login, 0));
        request.setAttribute("subscribed", account.getFollowers());
        request.setAttribute("followers", account.getBuddies());
        return "personal_page";
    }

    @RequestMapping(value = "/logout")
    public String logoutFromAccount(HttpServletResponse response, HttpServletRequest request) {
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("password");
        return "authorization";
    }

    @RequestMapping(value = "publishing", method = RequestMethod.POST)
    public String addNewSwit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        if (login != null && password != null) {
            Swit swit = new Swit();
            Account account;
            if ((account = userService.getUser(login, password)) != null) {
                swit.setMessageBody(request.getParameter("messageBody"));
                swit.setDate(new Date());
                swit.setAuthor(account);
                switService.saveSwit(swit);
            }
        }
        return "redirect:/main";
    }

    @RequestMapping(method = RequestMethod.GET, value = "ajax")
    public
    @ResponseBody
    String testAjax() {
        return "<b>it is wrong answer</b>";
    }

    @RequestMapping(method = RequestMethod.GET, value = "friends")
    public String getFriends(@RequestParam(defaultValue = "") String login, HttpServletRequest request, HttpServletResponse response) {
        Account account;
        if (login.equals(""))
            account = userService.getUser((String) request.getSession().getAttribute("login"));
        else
            account = userService.getUser(login);
        request.setAttribute("persons", account.getFollowers());
        request.setAttribute("title", "Followers");
        return "friends";
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscribed")
    public String getSubscribed(@RequestParam(defaultValue = "") String login, HttpServletRequest request, HttpServletResponse response) {
        Account account;
        if (login.equals(""))
            account = userService.getUser((String) request.getSession().getAttribute("login"));
        else
            account = userService.getUser(login);
        request.setAttribute("persons", account.getBuddies());
        request.setAttribute("title", "Subscribed");
        return "friends";
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {// имена параметров (тут - "file") - из формы JSP.
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        Account account;
        if ((account = userService.getUser(login, password)) != null) {
            account.setPhoto(file.getBytes());
            userService.updateUser(account);
            return  "redirect:/personal_page";
        }
        return "personal_page";
    }


    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public String addNewFriend(
            HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String newFriendlogin) {
        String userLogin = (String) request.getSession().getAttribute("login");
        String userPassword = (String) request.getSession().getAttribute("password");
        if ((userLogin != null) && (userPassword != null) && (!newFriendlogin.equals(""))) {
            userService.addNewFriend(newFriendlogin, userLogin, userPassword);
        }
        request.setAttribute("account", userService.getUser(newFriendlogin));
        request.setAttribute("swits", switService.getSwitsPerUser(newFriendlogin, 0));
        request.setAttribute("subscribeds", userService.getUser(newFriendlogin).getFollowers());
        request.setAttribute("followers", userService.getUser(newFriendlogin).getBuddies());
        return "personal_page";

    }

    @RequestMapping(value = "imageController/{userLogin}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable String userLogin) {
        Account account = userService.getUser(userLogin);
        return account.getPhoto();
    }
}