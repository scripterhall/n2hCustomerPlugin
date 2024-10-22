package ing.n2h.validator_annotations.n2hValidators;

import java.lang.reflect.Field;
import java.util.Date;

import ing.n2h.validator_annotations.n2hBuisnessAnnotations.N2hCustomer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class N2hCustomerValidator implements ConstraintValidator<N2hCustomer, Object> {

    private boolean validateName;
    private boolean validateEmail;
    private boolean validatePassword;
    private boolean validatePhone;
    private boolean validateAddress;
    private boolean validateFirstName;
    private boolean validateLastName;
    private boolean validateGender;
    private boolean validateBirthDate;
    private boolean validateCIN;

    @Override
    public void initialize(N2hCustomer customer) {
        this.validateName = customer.name();
        this.validateEmail = customer.email();
        this.validatePassword = customer.password();
        this.validatePhone = customer.phone();
        this.validateAddress = customer.address();

        this.validateFirstName = customer.firstName();
        this.validateLastName = customer.lastName();
        this.validateGender = customer.gender();
        this.validateBirthDate = customer.birthDate();
        this.validateCIN = customer.CIN();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            context.buildConstraintViolationWithTemplate("Customer cannot be null")
                    .addConstraintViolation();
            return false;
        }

        Field[] fields = value.getClass().getDeclaredFields();
        boolean isValid = true;

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                // Validation pour "name"
                if ("name".equals(field.getName()) && validateName) {
                    String name = (String) field.get(value);
                    if (!isNameValid(name)) {
                        context.buildConstraintViolationWithTemplate("Name must not be empty")
                                .addPropertyNode("name")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "email"
                if ("email".equals(field.getName()) && validateEmail) {
                    String email = (String) field.get(value);
                    if (!isEmailValid(email)) {
                        context.buildConstraintViolationWithTemplate("Invalid email format")
                                .addPropertyNode("email")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "password"
                if ("password".equals(field.getName()) && validatePassword) {
                    String password = (String) field.get(value);
                    if (password == null || password.length() < 8) {
                        context.buildConstraintViolationWithTemplate("Password must be at least 8 characters long")
                                .addPropertyNode("password")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "phone"
                if ("phone".equals(field.getName()) && validatePhone) {
                    String phone = (String) field.get(value);
                    if (!isPhoneValid(phone)) {
                        context.buildConstraintViolationWithTemplate("Phone number must be exactly 8 digits")
                                .addPropertyNode("phone")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "address"
                if ("address".equals(field.getName()) && validateAddress) {
                    String address = (String) field.get(value);
                    if (!isAddressValid(address)) {
                        context.buildConstraintViolationWithTemplate("Address must not be empty")
                                .addPropertyNode("address")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "firstName"
                if ("firstName".equals(field.getName()) && validateFirstName) {
                    String firstName = (String) field.get(value);
                    if (!isNameValid(firstName)) {
                        context.buildConstraintViolationWithTemplate("First name must not be empty")
                                .addPropertyNode("firstName")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "lastName"
                if ("lastName".equals(field.getName()) && validateLastName) {
                    String lastName = (String) field.get(value);
                    if (!isNameValid(lastName)) {
                        context.buildConstraintViolationWithTemplate("Last name must not be empty")
                                .addPropertyNode("lastName")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "gender"
                if ("gender".equals(field.getName()) && validateGender) {
                    String gender = (String) field.get(value);
                    if (!isGenderValid(gender)) {
                        context.buildConstraintViolationWithTemplate("Invalid gender")
                                .addPropertyNode("gender")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "birthDate"
                if ("birthDate".equals(field.getName()) && validateBirthDate) {
                    Date birthDate = (Date) field.get(value);
                    if (!isBirthDateValid(birthDate)) {
                        context.buildConstraintViolationWithTemplate("Invalid birth date")
                                .addPropertyNode("birthDate")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

                // Validation pour "CIN"
                if ("CIN".equals(field.getName()) && validateCIN) {
                    String cin = (String) field.get(value);
                    if (!isCINValid(cin)) {
                        context.buildConstraintViolationWithTemplate("Invalid CIN number")
                                .addPropertyNode("CIN")
                                .addConstraintViolation();
                        isValid = false;
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@") && (email.lastIndexOf(".") > email.indexOf("@"));
    }

    private boolean isPhoneValid(String phone) {
        return phone != null && phone.length() == 8 && phone.matches("\\d+"); // Numéro composé uniquement de chiffres
    }

    private boolean isAddressValid(String address) {
        return address != null && !address.trim().isEmpty();
    }

    private boolean isGenderValid(String gender) {
        return "homme".equalsIgnoreCase(gender) || "femme".equalsIgnoreCase(gender)
                || "autres".equalsIgnoreCase(gender);
    }

    private boolean isBirthDateValid(Date birthDate) {
        return birthDate != null && birthDate.before(new Date()); // Date de naissance doit être avant aujourd'hui
    }

    private boolean isCINValid(String cin) {
        return cin != null && cin.matches("\\d+"); // CIN composé uniquement de chiffres
    }

}
