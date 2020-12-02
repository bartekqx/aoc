package day02;

class CurrentPasswordPolicy implements PasswordPolicy {

    private final char letter;
    private final int position1;
    private final int position2;

    public CurrentPasswordPolicy(char letter, int position1, int position2) {
        this.letter = letter;
        this.position1 = position1;
        this.position2 = position2;
    }

    @Override
    public boolean isValid(String password) {
            return (password.charAt(position1) == letter && password.charAt(position2) != letter) ||
                    (password.charAt(position1) != letter && (password.charAt(position2) == letter));
    }
}
