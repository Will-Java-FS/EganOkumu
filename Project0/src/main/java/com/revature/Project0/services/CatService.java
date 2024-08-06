package com.revature.Project0.services;


import com.revature.Project0.models.Cat;

import java.util.List;

public interface CatService
{
    public Cat addCat(int userId, Cat cat);
    public Cat getCat(Integer id);
    public List<Cat> getAllCats();
    public Integer updateCat(Integer catId, Cat newCat);
    public Integer deleteCat(Integer catId);
}
