package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.AuthenticationException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.User;

/**
 * Data access for users.
 *
 * @author vitorgreati
 */
public interface UserDAO {

    User create(User u) throws WebException;

    User authenticate(String email, String password) throws AuthenticationException, WebException;

}
