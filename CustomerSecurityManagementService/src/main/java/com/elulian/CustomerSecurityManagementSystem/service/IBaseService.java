package com.elulian.CustomerSecurityManagementSystem.service;

import java.io.Serializable;
import java.util.List;

import com.elulian.CustomerSecurityManagementSystem.dao.IBaseDAO;
import com.elulian.CustomerSecurityManagementSystem.exception.DataMissingException;
import com.elulian.CustomerSecurityManagementSystem.exception.ExistsException;

/**
 * Base service that talks to BaseDao to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 *
 * @author 
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface IBaseService<T, ID extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> findAll();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T findById(ID id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(ID id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the updated object
     * 
     */
    T save(T object); // throws ExistsException, DataMissingException;

    /**
     * Generic method to delete an object
     * @param object the object to remove
     *
     */
    void remove(T object);// throws NotExistsException;

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     * 
     */
    void remove(ID id); // throws NotExistsException;

    /**
     * Generic method to search for an object.
     * @param searchTerm the search term
     * @param clazz type of class to search for.
     * @return a list of matched objects
     */
    List<T> search(String searchTerm);
    
    long getTotalCount();
    
    /**
     * for mock test only, application flow just need spring
     * injection
     * @param dao
     */
    void setDAO(IBaseDAO<T, ID> dao);
  
}
