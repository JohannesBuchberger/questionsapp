package eit11c.bs1.questionsapp.pojos;

public class Categories extends AbstractPojo {
    private Category[] trivia_categories;

    public Category[] getCategories() {

        return trivia_categories;
    }

    public void setCategories(Category[] categories) {
        this.trivia_categories = categories;
    }

    public class Category extends AbstractPojo {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
