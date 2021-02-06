package eit11c.bs1.questionsapp.http;

import eit11c.bs1.questionsapp.pojos.Quiz;

public class GetQuizService extends AbstractGetService<Quiz> {
    @Override
    protected Class<Quiz> getRelevantClass() {
        return Quiz.class;
    }
}
