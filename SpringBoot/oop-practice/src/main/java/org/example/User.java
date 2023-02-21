package org.example;

public class User {

    private String password;

    // 비밀번호 초기화 메소드
    public void initPassword(){
        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        String randomPassword = randomPasswordGenerator.generatePassword();

        // 비밀번호가 8자 이상 12자 이하일 때만 랜덤 패스워드 초기화 가능
        if(randomPassword.length() >= 8 && randomPassword.length() <= 12){
            this.password = randomPassword;
        }


    }


}
