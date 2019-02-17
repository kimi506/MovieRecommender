package students.com.movierecommender.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class ResponseMessage {
    @SerializedName(value = "text")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
