/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.components;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.fatal1t.finbe.ui.views.Dashboard;
import org.fatal1t.finbe.ui.views.ItemsView;

/**
 *
 * @author fatal1t
 */
@SpringComponent
@UIScope
public class Menu extends VerticalLayout {
    private final MenuBar mainMenu = new MenuBar();
    
    public Menu()
    {
        MenuBar.Command itemsCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(ItemsView.NAME);
            }
        };
        MenuBar.Command dashboardCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(Dashboard.NAME);
            }
        };
        
        mainMenu.addItem("Desired Items", itemsCommand);
        mainMenu.addItem("Dashboard", dashboardCommand);
        mainMenu.setVisible(true);        
              
        addComponent(mainMenu);
        this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        this.setStyleName(ValoTheme.MENU_PART);
        //this.setWidth(1920, Unit.PIXELS);
    }
    
}
