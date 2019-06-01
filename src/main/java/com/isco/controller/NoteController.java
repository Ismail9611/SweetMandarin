package com.isco.controller;

import com.isco.model.Note;
import com.isco.model.NoteInfo;
import com.isco.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/note")
public class NoteController {


    private NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }


    @GetMapping("/{id}")
    public String index(@PathVariable(name = "id") Note note,
                        Model model){
        NoteInfo noteInfo = note.getNoteInfo();

        model.addAttribute("noteInfo", noteInfo);
        model.addAttribute("note", note);
        return "note";
    }

    @GetMapping(value = "/update_view_count")
    @ResponseBody
    public Map<String, Boolean> updateViewsCount(@RequestParam(name = "id") Long id,
                                                 HttpServletRequest request){
        Map<String, Boolean> response = new HashMap<>();
        if (isAjax(request)){
            Note note = noteService.findNoteById(id);
            long count = note.getNoteInfo().getViewsCount();
            count++;
            note.getNoteInfo().setViewsCount(count);
            noteService.saveNote(note);
            response.put("success", true);
        } else {
            response.put("success", false);
        }
        return response;
    }

    private boolean isAjax(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader);
    }



}
