/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.util.List;

/**
 *
 * @author fatal1t
 */
public interface ExpenseRepository {
    public List<Expense> findByUserId(Long userId);
    public List<Expense> findByUserIdAndExpCat(Long userId, Long expCat);
    
}
