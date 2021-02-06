package eit11c.bs1.questionsapp.http;

import eit11c.bs1.questionsapp.pojos.Categories;

public class GetCategoriesService extends AbstractGetService<Categories> {

    @Override
    protected Class<Categories> getRelevantClass(){
        return Categories.class;
    }
}