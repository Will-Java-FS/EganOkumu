package com.revature.Project0.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Project0.models.Cat;
import com.revature.Project0.services.CatServiceImpl;
import com.revature.Project0.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
@CrossOrigin
public class CatController {

    CatServiceImpl cs;
    UserServiceImpl us;
    ObjectMapper mp;
    @Autowired //Constructor Injection
    public CatController(CatServiceImpl cs, UserServiceImpl us, ObjectMapper mp) {
        this.cs = cs;
        this.us = us;
        this.mp = mp;
    }

    @GetMapping()
    public ResponseEntity<String> getAllCats() throws JsonProcessingException {
        List<Cat> catList = cs.getAllCats();
        String jsonResponse = mp.writeValueAsString(catList);
        return ResponseEntity.status(200).body(jsonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCat(@PathVariable Integer id) throws JsonProcessingException {
        Cat cat = cs.getCat(id);
        String jsonResponse = cat != null ? mp.writeValueAsString(cat) : "A cat of that ID does not exist!";
        return ResponseEntity.status(200).body(jsonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCat(@PathVariable("id") Integer id){
        if(cs.getCat(id) != null){
            Integer delete = cs.deleteCat(id);
            return delete == 1
                    ? ResponseEntity.status(200).body("Delete Successful")
                    : ResponseEntity.status(500).body("Delete FAILED");
        }
        else {
            return ResponseEntity.status(404).body("Cat does not exist!");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCat(@PathVariable("id") Integer id, @RequestBody Cat newCat){
        if(cs.idExists(id) == 0)
            return ResponseEntity.status(404).body("Cat " + id + " doesn't exist!");
        else {
            Integer update = cs.updateCat(id,newCat);
            return update == 1
                    ? ResponseEntity.status(200).body("Update Successful")
                    : ResponseEntity.status(500).body("Update FAILED");
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> addCat(@PathVariable int userId, @RequestBody Cat a) throws JsonProcessingException {
        // todo: check that userId is valid user
        if(us.idExists(userId) == 0)
            return ResponseEntity.status(404).body("Cat owner " + userId + " doesn't exist!");
        else{
            a = cs.addCat(userId, a);
            String jsonResponse = mp.writeValueAsString(a);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }

    }


}
