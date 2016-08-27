/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.fatal1t.finbe.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */
@SpringView(name = RegistrationView.NAME)
@UIScope
@Theme("Valo")
public class RegistrationView extends CustomComponent implements View {
    public static final String NAME = "Registration";
    
    private final RegistrationService regService;
    private final TextField username = new TextField("Username");
    private final TextField email = new TextField("Email");    
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField passwordRepeat = new PasswordField("Password again");
    private final Button register = new Button("Register");
    private final HorizontalLayout buttons;
    private final FormLayout formLayout;
    private final VerticalLayout mainLayout;
    
    @Autowired
    public RegistrationView(RegistrationService service)
    {
        this.regService = service;
        setSizeFull();
        
        //add buttons to separate component, initializing form
        this.buttons = new HorizontalLayout(register);
        this.formLayout = new FormLayout(this.username, this.email, this.password, this.passwordRepeat, this.buttons);
        this.formLayout.setMargin(new MarginInfo(true, true, true, true));
        this.formLayout.setSpacing(true);
        this.formLayout.setCaption("Here you can register for this service usage");
        this.formLayout.setSizeUndefined();

        
        //hack form central alignment
        this.mainLayout = new VerticalLayout(this.formLayout);
        this.mainLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        mainLayout.setSizeFull();

        setCompositionRoot(this.mainLayout);
        
    }
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.username.focus();        
    }
    
}
