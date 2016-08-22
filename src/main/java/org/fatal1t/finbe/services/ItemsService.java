/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.fatal1t.finbe.controllers.entities.CategoryRepository;
import org.fatal1t.finbe.controllers.entities.CurrencyRepository;
import org.fatal1t.finbe.controllers.entities.Item;
import org.fatal1t.finbe.controllers.entities.ItemRespository;
import org.fatal1t.finbe.services.entities.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class ItemsService{
    @Autowired
    private ItemRespository itemRepository;
    @Autowired
    private CategoryRepository catRepository;
    @Autowired
    private CurrencyRepository currRepository;
    
    public synchronized List<UserItem> getAllItems(Long userId)
    {
        List<Item> items = itemRepository.findByIdUser(userId);
        System.out.println("I have found something " + items.size());
        return convertToUserItems(items); 
    }
    
    public synchronized UserItem getItemById(Long userId, Long idItem)
    {
        Item i = itemRepository.findByIdItemAndIdUser(idItem, userId);
        if(i != null)
        {
            System.out.println("found :" + i.toString());
            return this.convertToUserItem(i);
        }
        else 
            return null;
    }
    
    public synchronized void delete(UserItem i, Long userId)
    {
        this.itemRepository.delete(i.getId());
    }
    
    public synchronized void setItem(UserItem i, Long userId)
    {           
       itemRepository.save(convertToItem(i, userId));        
    }
    ///Scenarios
    // - nova polozka / konvertovat z UserItem na 
    // - uprava stavajici 
    // - 
    private UserItem convertToUserItem(Item item)
    {
        System.out.println("Converted item with id = " +item.getId());
        String category = null;
        String currency = null;
        if(item.getIdCat() != null )
        {
            category = this.catRepository.findById(item.getIdCat()).getCatName();
        }
        if(item.getIdCurrency() != null)
        {
            currency = this.currRepository.findById(item.getIdCurrency()).getIsoCode();
        }        
        UserItem newItem = new UserItem(item.getId(), category, item.getItemPrice(), currency, item.getItemName()
                , item.getItemLink(), item.getItemDesc(), item.getIsNeeded(), item.getItemPurDate());
        return newItem;
    }
    private Item convertToItem(UserItem i, Long userId)
    {
        Long cat = 1L;
        if(i.getCategory() != null)
        {
            cat = this.catRepository.findByIdUserAndCatName(1L, i.getCategory()).getId();
        }
        Long currency = this.currRepository.findByIsoCode(i.getCurrency()).getId();
        Item item = null;
        Date date = i.getPurDate() == null ? i.getPurDate() : Date.from(Instant.EPOCH);
        if(i.getId() != -1)
        {
             item = new Item(i.getId(),cat, userId, i.getItemName(), i.getItemPrice(), currency, i.getItemDesc(), i.isIsNeeded(), i.getItemLink(), Timestamp.from(date.toInstant()));
        }
        else 
        {
            
            item = new Item(cat, userId, i.getItemName(), i.getItemPrice(), currency, i.getItemDesc(), i.isIsNeeded(), i.getItemLink(), Timestamp.from(date.toInstant()));
        }
        return item;
    }
    
    private List<UserItem> convertToUserItems(List<Item> items)
    {
        ArrayList<UserItem> newItems  = new ArrayList<>();
        items.forEach(e -> {
            newItems.add(convertToUserItem(e));
        });
        return newItems;
    }
}
