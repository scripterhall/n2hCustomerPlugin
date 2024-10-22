package ing.n2h.validator_annotations.n2hDTO;

import java.util.Map;

public class N2hApiError {

    private String message;
    private Map<String, String> errors;

    public N2hApiError(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    // Getters et setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
