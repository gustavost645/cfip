package open.digytal.webapi.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import open.digytal.model.Login;
import open.digytal.model.Sessao;
import open.digytal.model.Usuario;
import open.digytal.webapi.secutiry.JwtTokenProvider;

@RestController
@RequestMapping("/login")
public class LoginResource {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> sigin(@RequestBody Login login) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getUsername(),
                		login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Usuario usuario = new Usuario();
        usuario.setLogin(login.getUsername());
        Sessao sessao = new Sessao();
        sessao.setUsuario(usuario);
        sessao.setToken(token);
        sessao.setInicio(new Date());
        return ResponseEntity.ok(sessao);
    }
    @GetMapping("")
    public void listar(Search search) {
    	search.getF().forEach(f->{System.out.println(f);});
    }
}
