package se.sprinto.hakan.demospringsecurity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.sprinto.hakan.demospringmessenger.model.Email;
import se.sprinto.hakan.demospringmessenger.service.MessagingService;

import java.io.IOException;

@Component
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {
    private final MessagingService messagingService;
    private final RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler;

    public OttSuccessHandler(MessagingService messagingService, RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler) {
        this.messagingService = messagingService;
        this.redirectOneTimeTokenGenerationSuccessHandler = redirectOneTimeTokenGenerationSuccessHandler;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {
        String link = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue())
                .toUriString();
        Email email = new Email();
        email.setRecipient(request.getParameter("username"));
        email.setMessage(link);
        email.setSubject("One time token link");
        messagingService.sendMessage(email);
        redirectOneTimeTokenGenerationSuccessHandler.handle(request, response, oneTimeToken);

    }
}
