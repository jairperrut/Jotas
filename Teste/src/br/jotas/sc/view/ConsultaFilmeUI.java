package br.jotas.sc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.jotas.sc.controller.FilmeController;
import br.jotas.sc.model.Filme;
import br.jotas.sc.util.ConsultaFilmeTableModel;

public class ConsultaFilmeUI extends JInternalFrame {
	private JTextField jtfTitulo;
	public JTable jtListaFilme;
	private static ConsultaFilmeUI instancia;
	
	public static ConsultaFilmeUI obterInstancia() {
		if (instancia == null) {
			instancia = new ConsultaFilmeUI();
		}
		return instancia;
	}
	
	public ConsultaFilmeUI() {
		setClosable(true);
		setTitle("Consulta Filme");
		setBounds(100, 100, 450, 365);
		
		JLabel jlTitulo = new JLabel("T\u00EDtulo");
		
		jtfTitulo = new JTextField();
		jtfTitulo.setColumns(10);
		
		JButton jbProcurar = new JButton("Procurar");
		
		JScrollPane jspConsultaFilme = new JScrollPane();
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton jbEditar = new JButton("Editar");
		jbEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filme f = (Filme) new ConsultaFilmeTableModel(new FilmeController().listarFilme()).get(jtListaFilme.getSelectedRow());
				
				CadastroFilmeUI cadFilEdit = new CadastroFilmeUI(f);
				cadFilEdit.setFocusable(true);
				cadFilEdit.moveToFront();
				cadFilEdit.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(cadFilEdit, 0);
				cadFilEdit.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jspConsultaFilme, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlTitulo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfTitulo, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
						.addComponent(jbProcurar, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(jbEditar)
							.addGap(18)
							.addComponent(jbCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlTitulo)
						.addComponent(jtfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbProcurar)
					.addGap(8)
					.addComponent(jspConsultaFilme, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancelar)
						.addComponent(jbEditar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		if (jtListaFilme == null) {
			jtListaFilme = new JTable();
			jtListaFilme.setModel(new ConsultaFilmeTableModel(new FilmeController().listarFilme()));
			jtListaFilme.getColumnModel().getColumn(0).setPreferredWidth(200);
			jtListaFilme.getColumnModel().getColumn(1).setPreferredWidth(100);
		}
		jspConsultaFilme.setViewportView(jtListaFilme);
		getContentPane().setLayout(groupLayout);

	}
}
