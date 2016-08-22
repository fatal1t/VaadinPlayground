/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.fatal1t.finbe.controllers.entities.CurrencyRepository;
import org.fatal1t.finbe.services.ItemsService;
import org.fatal1t.finbe.services.entities.UserItem;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */
@SpringComponent
@UIScope
public class ItemForm extends FormLayout {
    
    private final ItemsService itemService;
    private final CurrencyRepository currRepository;
    private UserItem item;
    private final TextField itemName = new TextField("Item Name");
    private final TextField itemPrice = new TextField("Item Price");
    private final ComboBox currency = new ComboBox("Currency");
    private final TextField itemLink = new TextField("link");
    private final CheckBox isNeeded = new CheckBox("Is needed");
    private final TextField itemDesc = new TextField("Item Description");
    private final DateField purDate = new DateField("Purchase date");
    private final TextField category = new TextField("Category");
    private final Button save = new Button("Save");
    private final Button newItem = new Button("New");
    private final Button delete = new Button("Delete");
    private final Button newCategory = new Button("New Category");
    private final HorizontalLayout buttons;
    private Dashboard dashboard;
    private Long userId;
    
    @Autowired
    public ItemForm(ItemsService service, CurrencyRepository currencyRepository)
    {
        this.buttons = new HorizontalLayout(save, newItem, delete);
        this.currRepository = currencyRepository;
        this.currRepository.findAll().forEach( curr -> {
            this.currency.addItem(curr.getIsoCode());                   
        }
        );
        this.category.setValue("default");
        this.purDate.setDateFormat("dd-MM-yyyy");
        this.itemService = service;
        
        
        addComponents(itemName, itemPrice, currency, category, itemLink, isNeeded, itemDesc, purDate, buttons); 
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.addClickListener(e -> {
            System.err.println("Neco se ulozilo " + item.getItemName());
            itemService.setItem(item, userId);
            this.dashboard.setItems();
        });       
        newItem.addClickListener(e -> {
            UserItem it = new UserItem();
            setItem(it);
            this.dashboard.setItems();
            //this.clear();
        });
        delete.addClickListener(e -> {
            this.itemService.delete(item, userId);
            setItem(new UserItem());
            this.dashboard.setItems();
        });
        //setItem(new UserItem());
    }
    
    public void setItem(UserItem selectedItem)
    {
        userId = (Long) getSession().getAttribute("userId");
        if(selectedItem == null)
        {
            return;     
        }
        System.err.println("Selecting item with id " + selectedItem.getId());
        boolean isPersisted = this.itemService.getItemById(userId, selectedItem.getId()) != null;
        if(isPersisted)
        {
            System.err.println("Item is found");
            this.item = this.itemService.getItemById(userId, selectedItem.getId());
        }
        else
        {
            System.err.println("Item is not found");
            this.item = selectedItem;
        }
        BeanFieldGroup.bindFieldsUnbuffered(item, this);
        //this.dashboard.setItems();
    }
    
    public void setDashboard(Dashboard d)
    {
        this.dashboard = d;
    }
}
    