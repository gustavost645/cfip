package open.digytal.repository.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

public class RepositorioVoImpl implements RepositorioVo {
	@PersistenceContext
	private EntityManager em;
	private Class classe;
	@Override
	public List listar(Class classe) {
		this.classe=classe;
		TypedQuery<Tuple> query = em.createQuery("SELECT e.id, e.conta.nome, e.natureza.nome, e.descricao, e.valor FROM EntidadeLancamento e", Tuple.class);
		List<Tuple> typles = query.getResultList();
		List lista = new ArrayList();
		typles.forEach(tuple -> {
			lista.add(vo(tuple));
		});
		return lista;
	}

	private Object vo(Tuple tuple) {
		try {
			Object vo = classe.newInstance();
			Field[]fields=classe.getDeclaredFields();
			for(int x=0; x<tuple.getElements().size();x++) {
				Field f=fields[x];
				f.setAccessible(true);
				f.set(vo, tuple.get(x, f.getType()));
			}
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}