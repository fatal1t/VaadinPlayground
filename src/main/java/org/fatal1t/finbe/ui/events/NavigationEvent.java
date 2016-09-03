/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.ui.events;

import com.google.common.eventbus.EventBus;
import java.util.EventObject;

/**
 *
 * @author fatal1t
 */
public class NavigationEvent extends EventObject{
    private final String target;
    
    public NavigationEvent(String target, Object source)
    {
        super(source);
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
    
}
