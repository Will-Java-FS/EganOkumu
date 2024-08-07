package com.revature.Project0.services;

import com.revature.Project0.models.User;
import com.revature.Project0.repositories.CatRepo;
import com.revature.Project0.models.Cat;
import com.revature.Project0.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService
{

    @Autowired
    CatRepo cr;

    @Autowired
    UserRepo ur;

    public CatServiceImpl(CatRepo cr, UserRepo ur){
        this.cr = cr;
        this.ur = ur;
    }

    @Override
    public Cat getCat(Integer id) {
        return cr.findById(id).orElse(null);
    }

    @Override
    public List<Cat> getAllCats() {
        return cr.findAll();
    }

    @Override
    public Integer updateCat(Integer catId, Cat newCat) {
        Optional<Cat> optionalCat = cr.findById(catId);
        if(optionalCat.isPresent() && (newCat.getColor().length() <= 255 && !newCat.getColor().isEmpty()) && (newCat.getName().length() <= 255 && !newCat.getName().isEmpty())){
            Cat updatedCat = optionalCat.get();
            updatedCat.setColor(newCat.getColor());
            updatedCat.setName(newCat.getName());
            cr.save(updatedCat);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteCat(Integer catId){
        if(getCat(catId) != null){
            cr.deleteById(catId);
            return 1;
        }
        else
            return null;
    }

    @Override
    public Cat addCat(int userId, Cat c) {
        if(ur.idExists(userId) == 1 && c.getColor().length() <= 255 && !c.getColor().isEmpty() && c.getName().length() <= 255 && !c.getName().isEmpty()){
            User u = ur.findById(userId).get();
            c.setOwner(u);
            return cr.save(c);
        }
        else
            return null;

    }
    public Integer idExists(Integer user_id){
        return cr.idExists(user_id);
    }

}