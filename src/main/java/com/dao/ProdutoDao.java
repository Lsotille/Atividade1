package com.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {this.em.merge(produto);}

	public void remover(Produto produto) {produto = em.merge(produto);this.em.remove(produto);}

	public Produto buscarPorId(Long id) {return em.find(Produto.class, id);}

	public List<Produto> paginacao(int pages,int results) {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).setFirstResult((pages-1)*results).setMaxResults(results).getResultList();
	}

}
