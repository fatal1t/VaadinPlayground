/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.fatal1t.finbe.controllers.entities.Category;
import org.fatal1t.finbe.controllers.entities.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */
@SpringComponent
@UIScope
public class CategoryForm extends FormLayout {
    private final TextField catName = new TextField("Category name");
    private Category category;
    private final CategoryRepository repository;
    private final Label errorLabel = new Label();
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button newCat = new Button("New");
    private final HorizontalLayout buttons;
    private Long userId;
    
    @Autowired
    public CategoryForm(CategoryRepository repository)
    {
        this.repository = repository;
        this.errorLabel.setVisible(false);
        this.buttons = new HorizontalLayout(this.save, this.delete, this.newCat);
        this.addComponents(this.catName, this.errorLabel, this.buttons);
        this.save.addClickListener(e -> {
            repository.save(this.category);
        });
        
        this.delete.addClickListener(e -> {
            try
            {
                this.repository.delete(this.category.getId());
            }
            catch(Exception ex)
            {
                //padne to validaci v DB pro primarnimu klici pokud bude takova hodnota existovat v jine tabulce 
                this.errorLabel.setValue("Neco se posralo, zkus predelat kategorie");
            }
        });
        
    }
    
    public void setCategory(Category category)
    {
        /// pridat kontrolu na persistenci 
        userId = (Long) getSession().getAttribute("userId");
        BeanFieldGroup.bindFieldsUnbuffered(category, this);
        this.category = category;
    }
    
}