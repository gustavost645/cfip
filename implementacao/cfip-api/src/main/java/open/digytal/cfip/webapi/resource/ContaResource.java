package open.digytal.cfip.webapi.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import open.digytal.model.Conta;
import open.digytal.model.Lancamento;
import open.digytal.repository.ContaRepository;
import open.digytal.util.Formatador;

@RestController
@RequestMapping("/contas")
public class ContaResource {
	@Autowired
	private ContaRepository repository;
	@GetMapping
	public List<Conta> listarTodas(){
		return repository.listarTodas();
	}
	@GetMapping(value="/naocredito")
	public List<Conta> listarContas(){
		return repository.listarContas();
	}
	@GetMapping(value="/credito")
	public List<Conta> listarCartaoCredito(){
		return repository.listarCartoesCredito();
	}
	@GetMapping(value="/extrato/{id}/{dataInicio}")
	public List<Lancamento> extrato(@PathVariable("id") Integer id, @PathVariable("dataInicio") @DateTimeFormat(pattern = Formatador.DATA_API) Date dataInicio){
		return repository.extrato(id, dataInicio);
	}
}
