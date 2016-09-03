/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui;


import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.PreserveOnRefresh;
import org.fatal1t.finbe.ui.views.RegistrationView;
import org.fatal1t.finbe.ui.views.SimpleLoginView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fatal1t
 */
@SpringUI
@PreserveOnRefresh
public class AppUI extends UI {
    @Autowired
    private SpringViewProvider viewProvider;
    
    
    
     @Override
    protected void init(VaadinRequest request) {

         //
         // Create a new instance of the navigator. The navigator will attach
         // itself automatically to this view.
         //
         Navigator navigator = new Navigator(this, this);
         navigator.addProvider(viewProvider);
        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("token") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;
                boolean isRegistrationView = event.getNewView() instanceof RegistrationView;

                if (!isLoggedIn && !isLoginView && !isRegistrationView) {  
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }
                else if(!isLoggedIn && isRegistrationView)
                {
                    // Access to registration
                    return true;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
        getNavigator().navigateTo(SimpleLoginView.NAME);
    }
    
    @Subscribe
    private void onChange()
    {
        
    }
    
}
