/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.components;

import org.fatal1t.finbe.ui.views.DashboardView;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
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
import javax.annotation.PostConstruct;
import org.fatal1t.finbe.controllers.entities.CategoryRepository;
import org.fatal1t.finbe.controllers.entities.CurrencyRepository;
import org.fatal1t.finbe.services.ItemsService;
import org.fatal1t.finbe.services.entities.UserItem;
import org.fatal1t.finbe.ui.views.CustomView;
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
    private final CategoryRepository categoryRepository;
    private UserItem item;
    private final TextField itemName = new TextField("Item Name");
    private final TextField itemPrice = new TextField("Item Price");
    private final ComboBox currency = new ComboBox("Currency");
    private final TextField itemLink = new TextField("link");
    private final CheckBox isNeeded = new CheckBox("Is needed");
    private final TextField itemDesc = new TextField("Item Description");
    private final DateField purDate = new DateField("Purchase date");
    private final ComboBox category = new ComboBox("Category");
    private final Button save = new Button("Save");
    private final Button newItem = new Button("New");
    private final Button delete = new Button("Delete");
    
    private final HorizontalLayout buttons;
    private CustomView baseView;
    private Long userId;
    
    @Autowired
    public ItemForm(ItemsService service, CurrencyRepository currencyRepository, CategoryRepository categoryRepository1)
    {
        this.buttons = new HorizontalLayout(save, newItem, delete);
        this.currRepository = currencyRepository;
        this.categoryRepository = categoryRepository1;
        this.currRepository.findAll().forEach( curr -> {
            this.currency.addItem(curr.getIsoCode());                   
        }
        );
        this.purDate.setDateFormat("dd-MM-yyyy");
        this.itemService = service;
        
        
        addComponents(itemName, itemPrice, currency, category, itemLink, isNeeded, itemDesc, purDate, buttons); 
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.addClickListener(e -> {
            System.out.println("Neco se ulozilo " + item.getItemName());
            itemService.setItem(item, userId);
            this.baseView.setItems();
        });       
        newItem.addClickListener(e -> {
            UserItem it = new UserItem();
            setItem(it);
            this.baseView.setItems();
            //this.clear();
        });
        delete.addClickListener(e -> {
            this.itemService.delete(item, userId);
            setItem(new UserItem());
            this.baseView.setItems();
        });
        //setItem(new UserItem());
    }
    
    public void setItem(UserItem selectedItem)
    {
        System.out.println("Nastavuju novou polozku pro " + getUI().getSession().getAttribute("userId"));
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
        this.categoryRepository.findByIdUser(userId).forEach(e -> {
            this.category.addItem(e.getCatName());
        });
        BeanFieldGroup.bindFieldsUnbuffered(item, this);
            
    }
    
    public void setView(CustomView d)
    {
        this.baseView = d;
    }
}
    