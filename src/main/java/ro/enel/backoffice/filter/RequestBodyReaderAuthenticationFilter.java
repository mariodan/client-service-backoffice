package ro.enel.backoffice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.enel.backoffice.dto.AuthRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RequestBodyReaderAuthenticationFilter() {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestBody;
        try {
            requestBody = IOUtils.toString(request.getReader());
            AuthRequest authRequest = objectMapper.readValue(requestBody, AuthRequest.class);

            UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password);

            setDetails(request, token);

            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }
}
