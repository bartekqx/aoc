package day02;

class Password {

    private final String password;
    private final PasswordPolicy passwordPolicy;

    public Password(String password, PasswordPolicy passwordPolicy) {
        this.password = password;
        this.passwordPolicy = passwordPolicy;
    }

    public boolean isValid() {
        return passwordPolicy.isValid(password);
    }
}
