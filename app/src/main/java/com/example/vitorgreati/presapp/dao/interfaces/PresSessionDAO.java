package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.UnauthorizedOperationException;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.Date;
import java.util.List;

/**
 * Data access for sessions.
 *
 */
public interface PresSessionDAO {

    PresSession create(PresSession s) throws WebException;

    PresSession update(PresSession s);

    void delete(PresSession s);

    void open(PresSession s, User u, Date openingDate) throws WebException, UnauthorizedOperationException;

    void close(PresSession s, User u, Date closingDate) throws WebException, UnauthorizedOperationException;

    Participation participate(String code, User u) throws UserNotFoundException, WebException;

    Participation quit(String code, User u) throws UserNotFoundException, WebException;

    PresSession read(PresSession s);

    PresSession getByCode(String code);

    List<PresSession> list(Presentation p) throws WebException;

}
