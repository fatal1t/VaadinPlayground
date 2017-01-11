/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.views;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.fatal1t.finbe.controllers.entities.Category;
import org.fatal1t.finbe.controllers.entities.CategoryRepository;
import org.fatal1t.finbe.controllers.entities.UserDataRepository;
import org.fatal1t.finbe.services.ItemsService;
import org.fatal1t.finbe.services.entities.UserItem;
import org.fatal1t.finbe.ui.components.CategoryForm;
import org.fatal1t.finbe.ui.components.ItemForm;
import org.fatal1t.finbe.ui.components.Menu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */

@SpringView(name = ItemsView.NAME)
@UIScope
public class ItemsView extends CustomComponent implements CustomView {
    public final static String NAME = "Items";
     
    private final ItemForm itemsForm;  
    private final CategoryForm catgoryForm;
    private final Menu menu;
    private final ItemsService itemsService;
    private final CategoryRepository categoryRepository;
    
    
    private final Grid itemGrid;
    private final Grid catGrid;

    
    @Autowired
    public ItemsView(ItemForm itemForm, Menu menu, ItemsService itemsService, CategoryForm catForm, CategoryRepository repository )
    {
        //Autowired dependences
        this.itemsForm = itemForm;
        this.catgoryForm = catForm;
        this.categoryRepository = repository;
                
        this.menu = menu;
        this.itemsService = itemsService;
        
        this.itemGrid = new Grid();
        this.itemGrid.setColumns("itemName","itemPrice", "currency", "category", "purDate");
        this.itemGrid.setWidth(700, Unit.PIXELS);

        this.catGrid = new Grid();
        this.catGrid.setColumns("catName", "createDate");
        this.catGrid.setWidth(700, Unit.PIXELS);
        
        HorizontalLayout itemsLayout = new HorizontalLayout(this.itemGrid, this.itemsForm);
        HorizontalLayout catsLayout = new HorizontalLayout(this.catGrid, this.catgoryForm);
        setCompositionRoot(new VerticalLayout(menu, itemsLayout, catsLayout));
        itemGrid.addSelectionListener(e -> itemForm.setItem((UserItem) itemGrid.getSelectedRow()));
        catGrid.addSelectionListener(e -> catForm.setCategory((Category) catGrid.getSelectedRow()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setItems();
        this.itemsForm.setView(this);
        this.itemsForm.setItem(new UserItem());
        
        this.catgoryForm.setView(this);
        this.catgoryForm.setCategory(new Category());
    }

    @Override
    public void setItems() {
        Long userid = (Long) getUI().getSession().getAttribute("userId");
        this.itemGrid.setContainerDataSource( new BeanItemContainer(UserItem.class, this.itemsService.getAllItems(userid)));
        this.catGrid.setContainerDataSource(new BeanItemContainer(Category.class, this.categoryRepository.findByIdUser(userid)));
    }
    
}
