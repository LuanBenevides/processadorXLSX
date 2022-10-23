package conversorEdi.view;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import conversorEdi.control.ControleTelaUpload;
import rede.financeiros.testes.ReadArquivoTeste;

public class TelaUpload extends TelaBase{

	private static final long serialVersionUID = 1L;

	private ControleTelaUpload controle;
	
	private JFileChooser arquivo;
	private JFrame frame;
	
	public TelaUpload() throws IOException {
		super();
		
		frame = new JFrame();
		frame.setTitle("Interpretador de arquivos EDI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ReadArquivoTeste readOperation = new ReadArquivoTeste();
		controle = new ControleTelaUpload(this);
		
		arquivo = new JFileChooser();
		arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		arquivo.showOpenDialog(frame);
		File operatedFile = arquivo.getSelectedFile();
		readOperation.generatePlanilhas(operatedFile);
		
		add(frame);
		frame.add(arquivo);
		
		setVisible(true);
			
	}

	public ControleTelaUpload getControle() {
		return controle;
	}

	public JFileChooser getArquivo() {
		return arquivo;
	}
	
}
