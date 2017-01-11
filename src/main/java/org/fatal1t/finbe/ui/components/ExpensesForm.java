/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.components;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 *
 * @author fatal1t
 */
@SpringComponent
@UIScope
public class ExpensesForm extends FormLayout {
    private final TextField expName = new TextField("Expense name");
    private final ComboBox expCat = new ComboBox("Expense category");
    private final TextField expPrice = new TextField("Expense price");
    private final ComboBox expCurrency = new ComboBox("Expense Currency");
    private final TextField expDesc = new TextField("Expense description");
    
}
