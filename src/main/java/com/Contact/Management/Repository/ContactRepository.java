package com.Contact.Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Contact.Management.Models.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

    //pagination

    @Query("from Contact as c where c.user.id =:userId")

    //user id
    public List <Contact>findContactByUser(@Param("userId") int userId);

    
} 