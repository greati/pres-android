package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.User;

import java.util.List;

/**
 * Data access for questions.
 *
 */
public interface ChoicesQuestionDAO {

    ChoicesQuestion create(ChoicesQuestion q);

    ChoicesQuestion update(ChoicesQuestion q);

    void delete(ChoicesQuestion q);

    void open(User u, ChoicesQuestion q);

    void close(User u, ChoicesQuestion q);

    List<ChoicesQuestion> list(PresSession s);

    ChoicesQuestion read(ChoicesQuestion q);

    ChoicesAnswer answer(User u, ChoicesAnswer answer);
}
