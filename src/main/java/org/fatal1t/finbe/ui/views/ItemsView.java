/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.fatal1t.finbe.ui.components.ItemForm;
import org.fatal1t.finbe.ui.components.Menu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */

@SpringView(name = ItemsView.NAME)
@UIScope
public class ItemsView extends CustomComponent implements View{
    public final static String NAME = "Items";
     
    private final ItemForm itemsForm;   
    private final Menu menu;
    
    @Autowired
    public ItemsView(ItemForm itemForm, Menu menu)
    {
        this.itemsForm = itemForm;
        this.menu = menu;
        setCompositionRoot(new HorizontalLayout(menu, itemForm));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
