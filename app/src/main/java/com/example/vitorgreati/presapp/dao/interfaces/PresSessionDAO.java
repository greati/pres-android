package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

/**
 * Data access for sessions.
 *
 */
public interface PresSessionDAO {

    void create(PresSession s);

    void update(PresSession s);

    void delete(PresSession s);

    void open(PresSession s);

    void close(PresSession s);

    void participate(PresSession s, User u) throws UserNotFoundException;

    void quit(PresSession s, User u) throws UserNotFoundException;

    PresSession get(String code);

    List<PresSession> list(Presentation p);

}
