package br.jotas.sc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private static PrincipalUI instancia;

	// Implementação de SINGLETON
	public static PrincipalUI obterInstancia() {
		if (instancia == null) {
			instancia = new PrincipalUI();
		}
		return instancia;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = obterInstancia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		setTitle("Jota\u00B4s Locadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmMovimento = new JMenu("Movimento");
		menuBar.add(jmMovimento);
		
		JMenuItem jmiLocacao = new JMenuItem("Loca\u00E7\u00E3o");
		jmMovimento.add(jmiLocacao);
		
		JMenuItem jmiDevolucao = new JMenuItem("Devolu\u00E7\u00E3o");
		jmMovimento.add(jmiDevolucao);
		
		JMenu jmCadastro = new JMenu("Cadastro");
		menuBar.add(jmCadastro);
		
		JMenuItem jmiCliente = new JMenuItem("Cliente");
		jmiCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteUI cadcliUI = new CadastroClienteUI();
				cadcliUI.setFocusable(true);
				cadcliUI.moveToFront();
				cadcliUI.requestFocus();
				getContentPane().add(cadcliUI, 0);
				try {
					cadcliUI.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cadcliUI.setVisible(true);
			}
		});
		jmCadastro.add(jmiCliente);
		
		JMenuItem jmiFilme = new JMenuItem("Filme");
		jmiFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFilmeUI cadprodUI = new CadastroFilmeUI();
				cadprodUI.setFocusable(true);
				cadprodUI.requestFocus();
				cadprodUI.setVisible(true);
				getContentPane().add(cadprodUI);
			}
		});
		jmCadastro.add(jmiFilme);
		
		JMenu jmConsulta = new JMenu("Consulta");
		menuBar.add(jmConsulta);
		
		JMenuItem jmiClienteConsulta = new JMenuItem("Cliente");
		jmiClienteConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaClienteUI conCli = new ConsultaClienteUI();
				conCli.setVisible(true);

				PrincipalUI.obterInstancia().getContentPane().add(conCli);
			}
		});
		jmConsulta.add(jmiClienteConsulta);
		
		JMenuItem jmiExemplar = new JMenuItem("Exemplar");
		jmiExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaExemplarUI conExe = new ConsultaExemplarUI();
				conExe.setVisible(true);

				PrincipalUI.obterInstancia().getContentPane().add(conExe);
			}
		});
		jmConsulta.add(jmiExemplar);
		
		JMenuItem jmiFilmeConsulta = new JMenuItem("Filme");
		jmiFilmeConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaFilmeUI conFil = new ConsultaFilmeUI();
				conFil.setVisible(true);

				PrincipalUI.obterInstancia().getContentPane().add(conFil);
			}
		});
		jmConsulta.add(jmiFilmeConsulta);
		
		JMenu jmRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(jmRelatrio);
		
		JMenuItem jmiAtraso = new JMenuItem("Atraso");
		jmRelatrio.add(jmiAtraso);
		
		JMenuItem jmiClienteRelatorio = new JMenuItem("Cliente");
		jmRelatrio.add(jmiClienteRelatorio);
		
		JMenuItem jmiFilmeRelatorio = new JMenuItem("Filme");
		jmRelatrio.add(jmiFilmeRelatorio);
		
		JMenu jmSair = new JMenu("Sair");
		jmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sair telaSair = new Sair();
				telaSair.setVisible(true);
				telaSair.requestFocus();
				getContentPane().add(telaSair);
			}
		});
		menuBar.add(jmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 252, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
