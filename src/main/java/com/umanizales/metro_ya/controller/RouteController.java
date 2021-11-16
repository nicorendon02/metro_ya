package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.controller.dto.BoyWithParentDTO;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.User;
import com.umanizales.metro_ya.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "ntree")
@Validated
@CrossOrigin("http://localhost:8080")
public class RouteController {
    @Autowired
    private RouteService nTreeService;

    @PostMapping("/{parentIdentification}")
    public @ResponseBody
    ResponseEntity<?> addBoy(@Valid @RequestBody User boy, @PathVariable int parentIdentification)
            throws DataNotFoundException, NTreeException {
        return nTreeService.addBoy(boy,parentIdentification);
    }

    @PostMapping("/fill")
    public @ResponseBody
    ResponseEntity<?> fillTree(BoyWithParentDTO data)
            throws DataNotFoundException, NTreeException {
        return nTreeService.addBoy(data.getBoy(),data.getParentId());
    }

    @GetMapping("/list")
    public @ResponseBody
    ResponseEntity<?> listBoys() throws NTreeException {
        return nTreeService.listBoys();
    }
}