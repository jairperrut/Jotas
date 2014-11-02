package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.jotas.sc.dao.ClienteDAO;
import br.jotas.sc.model.Cliente;

public class ClienteController {

	public void salvarCliente(Cliente cliente) throws NullPointerException, Exception {
		validaDados(cliente);
		ClienteDAO dao = new ClienteDAO();
		dao.salvarCliente(cliente);
	}

	public ArrayList<Cliente> listarCliente() throws SQLException {
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
		if (cliente.getNome().isEmpty() || cliente.getNome().equals("")) {
			throw new NullPointerException("Campo Nome obrigatório!");
		}
		if (cliente.getCpf().length() < 11) {
			throw new Exception("CPF inválido");
		}
	}
}
