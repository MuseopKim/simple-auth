package com.simpleauth.session;

import com.simpleauth.dto.login.LoginAccount;
import com.simpleauth.entity.Account;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter
@Component
public class HttpSessionManager {

    private static final String LOGIN_ACCOUNT = "loginAccount";

    public void login(HttpServletRequest request, Account account) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(60 * 30);
        session.setAttribute(LOGIN_ACCOUNT, LoginAccount.from(account));
    }

    public boolean isNotLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null;
    }

    public LoginAccount getLoginAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (LoginAccount) session.getAttribute(LOGIN_ACCOUNT);
    }
}
