package eit11c.bs1.questionsapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz extends AbstractPojo implements Parcelable {
    private Question[] results;

    public Question[] getResults() {
        return results;
    }

    public void setResults(Question[] results) {
        this.results = results;
    }

    // parcel
    protected Quiz(Parcel in) {
        results = in.createTypedArray(Question.CREATOR);
    }

    // parcel
    @Override
    public int describeContents() {
        return hashCode();
    }

    // parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(results, flags);
    }

    // parcel
    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}