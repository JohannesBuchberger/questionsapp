package eit11c.bs1.questionsapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Question extends AbstractPojo implements Parcelable {
    private String category, type, difficulty, question, correct_answer;
    private String[] incorrect_answers;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correct_answer = correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrect_answers;
    }

    public void setIncorrectAnswers(String[] incorrectAnswers) {
        this.incorrect_answers = incorrectAnswers;
    }

    // parcel
    protected Question(Parcel in) {
        category = in.readString();
        type = in.readString();
        difficulty = in.readString();
        question = in.readString();
        correct_answer = in.readString();
        incorrect_answers = in.createStringArray();
    }

    // parcel
    @Override
    public int describeContents() {
        return hashCode();
    }

    // parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(type);
        dest.writeString(difficulty);
        dest.writeString(question);
        dest.writeString(correct_answer);
        dest.writeStringArray(getIncorrectAnswers());
    }

    // parcel
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
