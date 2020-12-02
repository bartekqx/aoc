package day02;

class OldPasswordPolicy implements PasswordPolicy {

    private final char letter;
    private final int minTimes;
    private final int maxTimes;

    public OldPasswordPolicy(char letter, int minTimes, int maxTimes) {
        this.letter = letter;
        this.minTimes = minTimes;
        this.maxTimes = maxTimes;
    }

    @Override
    public boolean isValid(String password) {
        int letterCnt = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) ==letter ) {
                letterCnt++;
            }
        }

        return letterCnt >= minTimes && letterCnt <= maxTimes;
    }
}

