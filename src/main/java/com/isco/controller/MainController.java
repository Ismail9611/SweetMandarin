package com.isco.controller;


import com.isco.model.Note;
import com.isco.model.User;
import com.isco.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private NoteService noteService;

    @Autowired
    public MainController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping({"/", "/index"})
    public String index(@RequestParam(defaultValue = "0", name = "currPage") int currPage,
                        @RequestParam(defaultValue = "3", name = "perPage") int perPage,
                        @RequestParam(defaultValue = "", name = "searchQuery") String searchQuery,
                        Model model) {

        model.addAttribute("currPage", currPage);

        if (!searchQuery.isEmpty()){
            Page<Note> notePage = noteService.findPaginatedSearch(currPage, perPage, searchQuery);
            model.addAttribute("notePage", notePage);
            return "index";
        }

        Page<Note> notePage = noteService.findPaginated(currPage, perPage);
        model.addAttribute("notePage", notePage);

        return "index";
    }



    @RequestMapping("/account")
    public String account(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "account";
    }



    private boolean isAjax(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader);
    }


}
