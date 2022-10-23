package br.com.processadores.leitorDeArquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CapturaArquivo {

	//Localiza um arquivo na memória e realiza a leitura, linha a linha;
	public static void main(String[] args) throws FileNotFoundException {
		
		//Recebe um path como parâmetro e armazena o arquivo na variável
		File arquivo = new File("C:\\Users\\luan_\\OneDrive\\Documentos\\ambiente-dev\\catalogador\\01848_0027808831_20220903_000422_RF_MAO67954325_GRUPOLINHARES.txt");
		
		Scanner entrada = new Scanner(arquivo);
		
		while(entrada.hasNext()) {
			System.out.println(entrada.nextLine());
		}
		entrada.close();
	}
}
