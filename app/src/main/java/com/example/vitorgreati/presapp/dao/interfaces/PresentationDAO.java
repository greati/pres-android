package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

/**
 * Data access for presentations.
 *
 */
public interface PresentationDAO {

    void create(Presentation p);

    void update(Presentation p);

    void delete(Presentation p);

    Presentation read(Presentation p);

    List<Presentation> list(User u);

}
