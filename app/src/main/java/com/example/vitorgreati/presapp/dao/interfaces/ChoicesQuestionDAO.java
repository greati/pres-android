package com.example.vitorgreati.presapp.dao.interfaces;

import com.example.vitorgreati.presapp.exception.WebException;
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

    ChoicesQuestion create(ChoicesQuestion q) throws WebException;

    ChoicesQuestion update(ChoicesQuestion q);

    void delete(ChoicesQuestion q);

    Boolean open(User u, ChoicesQuestion q) throws WebException;

    Boolean close(User u, ChoicesQuestion q) throws WebException;

    List<ChoicesQuestion> list(PresSession s) throws WebException;

    ChoicesQuestion read(ChoicesQuestion q);

    ChoicesAnswer answer(User u, ChoicesAnswer answer) throws WebException;
}
