package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

/**
 * Data access for sessions.
 *
 */
public interface PresSessionDAO {

    PresSession create(PresSession s) throws WebException;

    PresSession update(PresSession s);

    void delete(PresSession s);

    void open(PresSession s);

    void close(PresSession s);

    Participation participate(PresSession s, User u) throws UserNotFoundException;

    Participation quit(PresSession s, User u) throws UserNotFoundException;

    PresSession read(PresSession s);

    PresSession getByCode(String code);

    List<PresSession> list(Presentation p) throws WebException;

}
