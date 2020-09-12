package com.connect_us.backend.service.mail;

import com.connect_us.backend.domain.account.MailDto;
import com.connect_us.backend.service.account.impl.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final AccountServiceImp accountServiceImp;
    private final PasswordEncoder passwordEncoder;

    //첨부파일 없이 이메일 보내기
    public void sendEmail(MailDto dto) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(dto.getEmail());
        mail.setSubject(dto.getTitle());
        mail.setText(dto.getMessage());
        javaMailSender.send(mail);
    }
    /** 가입 인증 **/
    public MailDto createValidationEmail(String email, Long id, String name){
        String tmpKey = getRandomPassword();
        String title = name+"님의 가입을 축하합니다.";
        String message = "안녕하세요. "+name+"님의 가입을 축하합니다. 아래의 이메일 인증 확인을 클릭해 인증을 완료해주세요"
                        +"'http://localhost:8080/v1/email/userVerification?id="
                        + id
                        +"&key="
                        +tmpKey
                        +"'";
        MailDto dto =new MailDto(email,title,message);
        return dto;
    }
    /** 비밀번호 변경 안내 **/
    public MailDto createPasswordChangeEmail(String email, String name){
        String tmpPassword=getRandomPassword();
        String title = name+"님의 쇼핑몰 임시 비밀번호 안내 이메일 입니다.";
        String message = "안녕하세요. 임시 비밀번호 안내 관련 이메일 입니다. 임시 비밀번호는 "+tmpPassword+" 입니다.";
        MailDto dto = new MailDto(email,title,message);
        updatePassword(tmpPassword);
        return dto;
    }

    public void updatePassword(String password){
        String pw = passwordEncoder.encode(password);
        accountServiceImp.updatePassword(pw); //현재 로그인 된 사용자의 pw를 업데이트
    }

    public String getRandomPassword(){
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
