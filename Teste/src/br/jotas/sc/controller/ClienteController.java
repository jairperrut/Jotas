package br.jotas.sc.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.jotas.sc.dao.ClienteDAO;
import br.jotas.sc.model.Cliente;

public class ClienteController {

	public void salvarCliente(Cliente cliente) throws NullPointerException, ParseException, Exception {
		validaDados(cliente);
		ClienteDAO dao = new ClienteDAO();
		if(cliente.getId()!=0){
			dao.editarCliente(cliente);
		}else{
			dao.salvarCliente(cliente);			
		}
	}

	public Cliente obterCliente(int id){
		ClienteDAO dao = new ClienteDAO();
		return dao.obterCliente(id);
	}
	public ArrayList<Cliente> listarClientes() {
		ClienteDAO dao = new ClienteDAO();		
		return dao.listarClientes();
	}	

	public void excluirCliente(int id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.excluirCliente(id);
	}

	public void validaDados(Cliente cliente) throws NullPointerException, Exception {
		if (cliente.getCpf().isEmpty()) {
			throw new NullPointerException("Campo CPF obrigatório!");
		}
		else if (cliente.getTelefone().isEmpty()) {
			throw new NullPointerException("Campo Telefone obrigatório!");
		}		
		else if (cliente.getNome().isEmpty() || cliente.getNome().equals("")) {
			throw new NullPointerException("Campo Nome obrigatório!");
		}
		else if (cliente.getDataNascimento() == null || cliente.getDataNascimento().equals("")){
			throw new NullPointerException("Campo Data Nascimento obrigatório!");
		}
		else if (cliente.getCpf() != null && cliente.getCpf().length() < 11 ) {
			throw new Exception("CPF inválido");
		}
		else if (cliente.getTelefone() == null){
			throw new Exception("Campo Telefone obrigatório!");
		}
	}
}
