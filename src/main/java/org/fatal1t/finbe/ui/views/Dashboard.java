/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.fatal1t.finbe.controllers.entities.Category;
import org.fatal1t.finbe.controllers.entities.CategoryRepository;
import org.fatal1t.finbe.services.AuthenticationService;
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
@SpringView(name = Dashboard.NAME)
@UIScope
@Theme("Valo")
public class Dashboard extends CustomComponent implements View {

    public final static String NAME = "Dashboard";
    private final ItemsService service;
    private final CategoryRepository categoryRepository;
    private final AuthenticationService authenticationService;
    private final ItemForm itemForm;
    private final CategoryForm catForm;
    
    private final Grid itemGrid;
    private final Grid categoryGrid;
    private final HorizontalLayout itemLayout;
    private final HorizontalLayout categoryLayout;
    private final VerticalLayout mainLayout;
    private final Menu menu;
    private Long userId;
    
    
    @Autowired
    public Dashboard(ItemsService service, ItemForm form, CategoryRepository repo, AuthenticationService authService, CategoryForm catForm, Menu menu) {
        // autowired dependecies
        this.categoryRepository = repo;
        this.itemForm = form;        
        this.authenticationService = authService;
        this.catForm = catForm;
        this.menu = menu; 
       
        this.itemGrid = new Grid();
        this.categoryGrid = new Grid();
        this.itemLayout = new HorizontalLayout(this.itemGrid, this.itemForm);
        this.categoryLayout = new HorizontalLayout(this.categoryGrid, this.catForm);
        itemLayout.setMargin(true);
        itemLayout.setSpacing(true);    
        itemGrid.setHeight(500, Unit.PIXELS);
        itemGrid.setWidth(500, Unit.PIXELS);
        itemGrid.setColumns("itemName", "itemPrice");
        categoryGrid.setColumns("catName");
        categoryGrid.setHeight(100, Unit.PIXELS);
        categoryGrid.setWidth(300, Unit.PIXELS);
        categoryLayout.setMargin(true);
        categoryLayout.setSpacing(true);
        
        //categoryGrid.setVisible(false);
        this.mainLayout = new VerticalLayout(this.menu, itemLayout, categoryLayout );
        
        mainLayout.setComponentAlignment(itemLayout, Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(categoryLayout, Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
        
        setCompositionRoot(mainLayout);
        this.service = service;
        itemGrid.addSelectionListener(e -> form.setItem((UserItem) itemGrid.getSelectedRow()));
    }   
        
    public void setItems()
    {
        this.itemGrid.setContainerDataSource(new BeanItemContainer(UserItem.class, service.getAllItems(userId)));
        this.categoryGrid.setContainerDataSource(new BeanItemContainer(Category.class, categoryRepository.findByIdUser(userId)));
        
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.itemForm.setDashboard(this);        
        String username = String.valueOf(getSession().getAttribute("user"));
        String token = String.valueOf(getSession().getAttribute("token"));
        System.out.println("loggeg user "+ getSession().getAttribute("userId")+" with token " +token);
        this.userId = (Long) getSession().getAttribute("userId");
        setItems();
        this.itemForm.setItem(new UserItem());
    }
    
}
