package com.alexandreazevedo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandreazevedo.cursomc.domain.Categoria;
import com.alexandreazevedo.cursomc.domain.Cidade;
import com.alexandreazevedo.cursomc.domain.Cliente;
import com.alexandreazevedo.cursomc.domain.Endereco;
import com.alexandreazevedo.cursomc.domain.Estado;
import com.alexandreazevedo.cursomc.domain.Produto;
import com.alexandreazevedo.cursomc.domain.enums.TipoCliente;
import com.alexandreazevedo.cursomc.repositories.CategoriaRepository;
import com.alexandreazevedo.cursomc.repositories.CidadeRepository;
import com.alexandreazevedo.cursomc.repositories.ClienteRepository;
import com.alexandreazevedo.cursomc.repositories.EnderecoRepository;
import com.alexandreazevedo.cursomc.repositories.EstadoRepository;
import com.alexandreazevedo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepository;	
	@Autowired
	ProdutoRepository produtoRepository;	
	@Autowired
	EstadoRepository estadoRepository;	
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	
		Estado sp = new Estado(null, "São Paulo");
		Estado mg = new Estado(null, "Minas Gerais");
		
		Cidade uberlandia = new Cidade(null, "Uberlândia", mg);
		Cidade saopaulo = new Cidade(null, "São Paulo", sp);
		Cidade campinas = new Cidade(null, "Campinas", sp);
		
		mg.getCidades().addAll(Arrays.asList(uberlandia));
		sp.getCidades().addAll(Arrays.asList(saopaulo, campinas));
		
		estadoRepository.saveAll(Arrays.asList(sp, mg));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, saopaulo, campinas));
	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto", "Jardim", "38220834", cli1, uberlandia);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, saopaulo);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
	
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
