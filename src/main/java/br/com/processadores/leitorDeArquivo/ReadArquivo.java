package br.com.processadores.leitorDeArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conversorEdi.control.montadores.MontarPlanilha046;
import conversorEdi.model.Registro046;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ReadArquivo {

	public static void main(String[] args) throws IOException{
		
		//Arquivo base para a leitura é passado como parâmetro por instância da classe file
		File arquivo = new File("C:\\Users\\luan_\\OneDrive\\Documentos\\ambiente-dev\\catalogador\\01848_0027808831_20220903_000422_RF_MAO67954325_GRUPOLINHARES.txt");
		Scanner entrada = new Scanner(arquivo);
		
		/*Método para obter a quantidade de linhas
			Utilizado para o controle no loop de leitura do arquivo
		*/
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
		linhaLeitura.skip(arquivo.length());
		int qtdLinhas = linhaLeitura.getLineNumber();
		
		//Armazenamento dos registros 046
		List<Registro046> registros046 = new ArrayList<>();
		
		String[] registro = new String[qtdLinhas + 1];
		String[] identifySubregistro = new String[qtdLinhas + 1];
		
		//Arrays para os subregistros;
		String[] subRegistro046 = new String[22];
		try (BufferedReader arq = new BufferedReader(new FileReader(arquivo))){
			
			//Condição para leitura do arquivo até que não hajam linhas
			boolean condit = entrada.hasNext();
			int controle = 0;
			int reg46Cont = 0;

			
			
			//Loop inicial de leitura do arquivo
			while(condit == true && controle < qtdLinhas) {
			
			registro[controle] = arq.readLine();
			
			
			//Identificação do tipo de registro;
			identifySubregistro[0] = registro[controle].substring(0, 3); //Tipo fixo		
				
			if(identifySubregistro[0].equals("046")) {
				reg46Cont += 1;
					
				Registro046 regis46 = new Registro046();
				
				subRegistro046[0] = identifySubregistro[0];
				regis46.tipo_registro = subRegistro046[0];
				//EC
				subRegistro046[1] = registro[controle].substring(3, 12);
				regis46.num_EC = subRegistro046[1];
				
				//OC REF NUMBER
				subRegistro046[2] = registro[controle].substring(12, 23);
				regis46.num_OC_ref = subRegistro046[2];
				
				//VALUE OC REF
				subRegistro046[3] = registro[controle].substring(23, 40);
				regis46.valor_OC_ref =  subRegistro046[3];
				
				//DATA OC REF
				subRegistro046[4] = registro[controle].substring(40, 48);
				regis46.data_OC_ref = subRegistro046[4];
					
				//EC ORIGINAL NUMBER
				subRegistro046[5] = registro[controle].substring(48, 57);
				regis46.num_EC_orig_venda = subRegistro046[5];
					
				//NUMBER OF RV
				subRegistro046[6] = registro[controle].substring(57, 66);
				regis46.num_rv = subRegistro046[6];
					
				//DATE OF RV
				subRegistro046[7] = registro[controle].substring(66, 74);
				regis46.data_venda_RV = subRegistro046[7];
					
				//TRANSACTION TYPE (TABLE ONE)
				subRegistro046[8] = registro[controle].substring(74, 75);
				regis46.transact_parcel_tipo = subRegistro046[8];
				
				//BAND. CODE
				subRegistro046[9] = registro[controle].substring(75, 76);
				regis46.code_band  = subRegistro046[9];
					
				//1 SESSION 2 GRAVAM
				subRegistro046[10] = registro[controle].substring(76, 77);
				regis46.negociacao_tipo = subRegistro046[10];
					
				//CONTRATO NUMBER
				subRegistro046[11] = registro[controle].substring(77, 94);
				regis46.contrato_number = subRegistro046[11];
					
				//CNPJ PARCEIRO
				subRegistro046[12] = registro[controle].substring(94, 109);
				regis46.cnpj_parceiro = subRegistro046[12];
					
				//Num do doc oc, gerado na negociacao
				subRegistro046[13] = registro[controle].substring(109, 120);
				regis46.num_doc_OC_neg = subRegistro046[13];
					
				//Valor negociado
				subRegistro046[14] = registro[controle].substring(120, 137);
				regis46.valor_negoc = subRegistro046[14];
					
				//Data da Negociação
				subRegistro046[15] = registro[controle].substring(137, 145);
				regis46.data_negociacao = subRegistro046[15];

				//Data de Liquidação
				subRegistro046[16] = registro[controle].substring(145, 153);
				regis46.data_liquidacao = subRegistro046[16];
					
				//Banco
				subRegistro046[17] = registro[controle].substring(153, 156);
				regis46.bank_number = subRegistro046[17];
					
				//Agencia
				subRegistro046[18] = registro[controle].substring(156, 162);
				regis46.bank_branch_number = subRegistro046[18];
					
				//Conta
				subRegistro046[19] = registro[controle].substring(162, 173);
				regis46.bank_account_number = subRegistro046[19];
					
				//Status do crédito - Tabela II
				subRegistro046[20] = registro[controle].substring(173, 175);
				regis46.status_credito = subRegistro046[20];
					
				//parcela
				subRegistro046[21] = registro[controle].substring(175, 177);
				regis46.parcela_numero = subRegistro046[21];
			
				registros046.add(regis46);
				System.out.println("Registro do tipo 046 inserido!");
			} else if(identifySubregistro[0].equals("047")){
				
			}
				controle++;
				
				linhaLeitura.close();
			}
			
			System.out.println(reg46Cont + " registros 046 identificados e gravados no arquivo...");
			
			MontarPlanilha046 start = new MontarPlanilha046();
				
			start.criarArquivo046("Novo arquivo.xlsx", registros046);
			
		}entrada.close();			
	}
}
