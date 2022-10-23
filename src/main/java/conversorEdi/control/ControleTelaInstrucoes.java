package conversorEdi.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import conversorEdi.view.TelaInstrucoes;

public class ControleTelaInstrucoes implements ActionListener{

	private TelaInstrucoes tela;
	
	public ControleTelaInstrucoes(TelaInstrucoes tela) {
		super();
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ControleTelaMenu.mostrarTelaMenu();
		tela.dispose();
	}
}
