package ait.cochort49.shop_g49_1.exceprionHandling;

import java.util.Objects;



public class Response {
    private String message;

    public Response(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response: message - " + message;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Response response)) return false;

        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
