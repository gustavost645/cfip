package open.digytal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import open.digytal.model.Conta;
import open.digytal.model.Lancamento;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
	@Query("SELECT e FROM Conta e ORDER BY e.nome")
	public List<Conta> listar();
	
	@Query("SELECT e FROM Conta e WHERE e.nome = :nome ORDER BY e.nome")
	public List<Conta> listar(@Param("nome")String nome);
	
	
	@Query("SELECT e FROM Lancamento e WHERE e.conta= :conta AND e.data>= :data ORDER BY e.data")
	public List<Lancamento> extrato(@Param("conta") Conta conta, @Param("data")Date dataIncio);
	
}

