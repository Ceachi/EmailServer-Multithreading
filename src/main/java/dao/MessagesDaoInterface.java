/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Id;

/**
 *
 * @author CeachiBogdan
 */
public interface MessagesDaoInterface<T> {
    public List<T> findAll();
    public T findById(Id id);
    public void update(T entity);
    public void insertMessages(List<T> messages);
    public void insert(T message);
}
