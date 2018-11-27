package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.User;

/**
 * Data access for questions.
 *
 */
public interface ChoicesQuestionDAO {

    void create(ChoicesQuestion q);

    void update(ChoicesQuestion q);

    void delete(ChoicesQuestion q);

    void list(PresSession s);

    void get(ChoicesQuestion q);

    void open(User u, ChoicesQuestion q);

    void close(User u, ChoicesQuestion q);

    void answer(User u, ChoicesAnswer answer);
}
