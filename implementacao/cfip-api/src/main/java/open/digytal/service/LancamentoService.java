package open.digytal.service;

import java.util.Date;
import java.util.List;

import open.digytal.model.entity.EntidadeLancamento;
import open.digytal.model.entity.EntidadeParcela;

public interface LancamentoService {
	 List<EntidadeLancamento> extrato(Integer contaId, Date dataInicio);
	 EntidadeLancamento incluir(EntidadeLancamento entidade);
	 List<EntidadeLancamento> listarLancamentos(String login, Date inicio,Date fim, Integer conta, Integer natureza);
	 List<EntidadeLancamento> listarPrevisoes(String login, Date inicio,Date fim, Integer conta, Integer natureza) ;
	 List<EntidadeParcela> listarParcelas(String login, Date inicio, Date fim, Integer conta,Integer natureza);
	 List<EntidadeParcela> listarFaturas(String login, Date inicio, Date fim, Integer conta,Integer natureza);
}