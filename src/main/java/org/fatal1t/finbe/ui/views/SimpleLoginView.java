/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.fatal1t.finbe.services.AuthenticationService;
import org.fatal1t.finbe.services.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */
@SpringView(name = SimpleLoginView.NAME)
@UIScope
@Theme("Valo")
public class SimpleLoginView extends CustomComponent implements View {

    public static final String NAME = "login";

    private final TextField user;
    private final LoginService loginService;
    private final AuthenticationService authenticationService;

    private final PasswordField password;
    
    private final HorizontalLayout buttons;
    private final Button loginButton;
    private final Button registerButton;
    @Autowired
    public SimpleLoginView(LoginService service, AuthenticationService authenticationService1) {
        setSizeFull();

        this.loginService = service;
        this.authenticationService = authenticationService1;
        // Create login & registration button and add them to separate component
        loginButton = new Button("Login");       
        registerButton = new Button("Register");
        buttons = new HorizontalLayout(loginButton, registerButton);
        // Create the user input field
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username (eg. joe@email.com)");
        user.setValue("test");
        //user.addValidator(new EmailValidator(
        //        "Username must be an email address"));
        //user.setInvalidAllowed(false);

        // Create the password input field
        password = new PasswordField("Password:");
        password.setWidth("300px");
        //password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("123456");
        password.setNullRepresentation("");
        
        //add listeners and shortcuts
        this.loginButton.addClickListener(e -> this.loginButtonClick(e));
        this.loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        this.registerButton.addClickListener( e -> {
            System.out.println("Pozadavek na registraci");
            this.getUI().getNavigator().navigateTo(RegistrationView.NAME);
            
        });

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, buttons);
        fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, true));
        fields.setSizeUndefined();

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        
        setCompositionRoot(viewLayout);

    }

    @Override
    public void enter(ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user.focus();
    }

    // Validator for validating the passwords
    private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value != null
                    && (value.length() < 8 || !value.matches(".*\\d.*"))) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    
    public void loginButtonClick(ClickEvent event) {

        //
        // Validate the fields using the navigator. By using validors for the
        // fields we reduce the amount of queries we have to use to the database
        // for wrongly entered passwords
        //
        if (!user.isValid() || !password.isValid()) {
            return;
        }
        
        String username = user.getValue();
        String password = this.password.getValue();

        //
        // Validate username and password with database here. For examples sake
        // I use a dummy username and password.
        //
        boolean isValid = username.equals("test@test.com")
                && password.equals("passw0rd");
        String token = this.loginService.login(username, password);
        if (token != null) {

            // Store the current user in the service session
            getSession().setAttribute("user", username);
            getSession().setAttribute("token", token); 
            getSession().setAttribute("userId",  this.authenticationService.checkUserToken(token));
            // Navigate to main view
            getUI().getNavigator().navigateTo(Dashboard.NAME);//

        } else {

            // Wrong password clear the password field and refocuses it
            this.password.setValue(null);
            this.password.focus();

        }
    }
}