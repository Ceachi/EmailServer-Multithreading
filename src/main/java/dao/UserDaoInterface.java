/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;

/**
 *
 * @author CeachiBogdan
 */
public interface UserDaoInterface<T> {
    public List<T> findAll();
    public T findById(int id);
    public T findByEmail(String email);
    public void update(T entity);
    public void insertUsers(List<T> users);
    public void insert(User user);
}
