package open.digytal.webapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import open.digytal.model.entity.EntidadeConta;
import open.digytal.model.enums.Roles;
import open.digytal.service.CadastroService;
import open.digytal.webapi.secutiry.JwtSession;

@RestController
@RequestMapping("/cadastros/contas")
public class ContaResource {
	@Autowired
	private CadastroService service;
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "nome", value = "Nome",defaultValue="", required = false, dataType = "string")
	  })
	@PreAuthorize(Roles.PRE_USER_ADMIN)
	@GetMapping(value= {"","/{nome}"})
	public List<EntidadeConta> todas(@PathVariable(name ="nome",required = false) String nome){
		return service.listarContas (JwtSession.getLogin(),nome);
	}
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "Id",defaultValue="", required = true, dataType = "int")
	  })
	@PreAuthorize(Roles.PRE_USER_ADMIN)
	@GetMapping(value= {"/{id}"})
	public List<EntidadeConta> todas(@PathVariable(name ="id") Integer id){
		return service.listarContas(id);
	}
	@PreAuthorize(Roles.PRE_USER_ADMIN)
	@GetMapping(value="/correntepoupanca")
	public List<EntidadeConta> contasCorrentePoupanca(){
		return service.listarCorrentesPoupanca(JwtSession.getLogin());
	}
	@PreAuthorize(Roles.PRE_USER_ADMIN)
	@GetMapping(value="/cartaocredito")
	public List<EntidadeConta> contasCartaoCredito(){
		return service.listarCartoesCredito(JwtSession.getLogin());
	}
	@PostMapping
    public void incluir(@RequestBody EntidadeConta entidade){
        service.salvarConta(entidade);
    }
	
	
}
