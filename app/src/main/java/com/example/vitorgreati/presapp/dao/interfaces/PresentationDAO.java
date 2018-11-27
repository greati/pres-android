package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

/**
 * Data access for presentations.
 *
 */
public interface PresentationDAO {

    Presentation create(Presentation p) throws WebException;

    Presentation update(Presentation p);

    void delete(Presentation p);

    Presentation read(Presentation p) throws WebException;

    List<Presentation> list(User u) throws WebException;

}
