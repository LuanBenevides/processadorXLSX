package conversorEdi.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import conversorEdi.view.TelaUpload;

public class ControleTelaUpload implements ActionListener{

	private TelaUpload tela;
	
	public ControleTelaUpload(TelaUpload tela) {
		super();
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ControleTelaMenu.mostrarTelaMenu();
		tela.dispose();
	}

}
