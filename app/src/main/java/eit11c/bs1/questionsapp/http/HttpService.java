package eit11c.bs1.questionsapp.http;

public class HttpService {
    private String _urlBase = "https://opentdb.com/";

    public void getCategories() {
        new GetCategoriesService().execute(_urlBase + "api_category.php");
    }

    public void getQuiz(String difficulty, int category) {
        StringBuilder url = new StringBuilder();
        url.append(_urlBase + "api.php?");
        url.append("amount=10");
        url.append("&category=" + category);
        url.append("&difficulty=" + difficulty);
        url.append("&type=multiple");
        new GetQuizService().execute(url.toString());
    }
}
