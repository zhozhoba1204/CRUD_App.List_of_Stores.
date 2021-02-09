package ru.blinov.polo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.blinov.polo.dao.StoreDAO;
import ru.blinov.polo.models.Store;

import javax.validation.Valid;

@Controller
@RequestMapping("/allstores")
public class StoreController {

    private final StoreDAO storeDAO;

    @Autowired
    public StoreController(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("stores", storeDAO.index());
        return "allstores/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("store",storeDAO.show(id));
        return "allstores/show";
    }

    @GetMapping("/new")
    public String newStore(@ModelAttribute("store") Store store){
        return "allstores/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("store") @Valid Store store,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "allstores/new";

        storeDAO.save(store);
        return "redirect:/allstores";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("store",storeDAO.show(id));
        return "allstores/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("store") @Valid Store store,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "allstores/edit";

        storeDAO.update(id, store);
        return "redirect:/allstores";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        storeDAO.delete(id);
        return "redirect:/allstores";
    }



}
