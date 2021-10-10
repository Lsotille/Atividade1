package com.testes;


import java.util.Scanner;

import javax.persistence.EntityManager;

import com.dao.ProdutoDao;
import com.modelo.Produto;
import com.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		int x;
		do {
			System.out.println("1:Cadastro Instantaneo;2:Atualizar o Produto de ID 1; 3:Deletar o Produto de ID 2; 4:Simular Paginacao; 0:sair");
			x = input.nextInt();
			switch (x) {
				case 1:
					cadastrarProduto();
					cadastrarProduto();
					cadastrarProduto();
					break;
				case 2:
					atualizarProduto();
					break;
				case 3:
					deletarProduto();
					break;
				case 4:
					System.out.println("Digite o numero de Paginas e o Numero de Resultados desejados");
					int pages = input.nextInt();
					int results = input.nextInt();
					simulacao(pages,results);
					break;
			}
		}while (x!=0);
	}
	private static void atualizarProduto() {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p = produtoDao.buscarPorId(1l);
		em.getTransaction().begin();
		produtoDao.atualizar(p);
		p.setNome("imdeR imoaiX");
		em.getTransaction().commit();
		em.close();
	}

	private static void deletarProduto() {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p = produtoDao.buscarPorId(2l);
		em.getTransaction().begin();
		produtoDao.remover(p);
		em.getTransaction().commit();
		em.close();
	}

	private static void cadastrarProduto() {
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", 0.5);
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		em.getTransaction().begin();
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

	private static void simulacao(int pages ,int results ){
		for (int i = 0; i < 20 ; i++) {
			cadastrarProduto();
		}
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		em.getTransaction().begin();
		produtoDao.paginacao(pages,results);
		em.close();
	}
}
