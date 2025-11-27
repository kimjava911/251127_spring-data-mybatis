package kr.java.mybatis.service;

import kr.java.mybatis.domain.UserInfo;
import kr.java.mybatis.domain.UserLogin;
import kr.java.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final로 지정된 필드들을 갖고 있는 생성자
// -> 생성자 주입을 위한 final에 대한 생성자를 알아서 만들어주기 때문에 편리
public class UserService {
    private final UserMapper userMapper;

    // 메서드의 범위에서 auto_commit을 껐다켜주겠다
    @Transactional
    public void register(
            String username, String password,
            String nickname, String email) {
        // 1. UserLogin
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userMapper.insertUserLogin(userLogin);
        System.out.println(userLogin.getId()); // useGeneratedKeys="true"
        // 2. UserInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(userLogin.getId());
        userInfo.setNickname(nickname);
        userInfo.setEmail(email);
        userMapper.insertUserInfo(userInfo);

        // 예외 발생 -> 두 INSERT 모두 롤백
    }

    @Transactional(readOnly = true)
    public UserInfo login(String username, String password) {
        UserLogin userLogin = userMapper.findLoginByUsername(username);

        if (userLogin == null
                || !userLogin.getPassword().equals(password)) {
            return null;
        }

        return userMapper.findUserInfoByLoginId(userLogin.getId());
    }
}
