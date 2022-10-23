package rede.financeiros.testes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conversorEdi.control.montadores.MontarPlanilha034;
import conversorEdi.control.montadores.MontarPlanilha035;
import conversorEdi.control.montadores.MontarPlanilha036;
import conversorEdi.control.montadores.MontarPlanilha046;
import conversorEdi.control.montadores.MontarPlanilha053;
import conversorEdi.control.montadores.MontarPlanilha047;
import conversorEdi.model.Registro034;
import conversorEdi.model.Registro035;
import conversorEdi.model.Registro036;
import conversorEdi.model.Registro046;
import conversorEdi.model.Registro047;
import conversorEdi.model.Registro053;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ReadArquivoTesteInterface{

	public static void main(String[] args) throws IOException {
		
			JFrame mainFrame = new JFrame();
		
			mainFrame.setSize(400, 100);
			mainFrame.setTitle("Interpretador de arquivos EDI");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLocationRelativeTo(null);
			
			JPanel painel = new JPanel();
			JLabel retorno = new JLabel("Arquivos XLSX gerados no diretório...");
			
			try {
				JFileChooser escolha = new JFileChooser();
				escolha.setFileSelectionMode(JFileChooser.FILES_ONLY);
				escolha.showOpenDialog(mainFrame);
				
				File arquivo = escolha.getSelectedFile();
				inicializar(arquivo);
			} catch (Exception e) {
				e.getStackTrace();
			}
			
			painel.add(painel);
			painel.add(retorno);
			mainFrame.add(painel);
	}	
	public static void inicializar(File arquivoRecebido) throws IOException {
		String path = arquivoRecebido.getPath();
		
		File arquivo = new File(path);
		Scanner entrada = new Scanner(arquivo);
		
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
		linhaLeitura.skip(arquivo.length());
		int qtdLinhas = linhaLeitura.getLineNumber();
		
		List<Registro034> registros034 = new ArrayList<>();
		List<Registro035> registros035 = new ArrayList<>();
		List<Registro036> registros036 = new ArrayList<>();
		List<Registro053> registros053 = new ArrayList<>();
		List<Registro046> registros046 = new ArrayList<>();
		List<Registro047> registros047 = new ArrayList<>();
		
		String[] registro = new String[qtdLinhas + 1];
		String[] identifySubregistro = new String[qtdLinhas + 1];
		
		//Arrays para os subregistros;
		String[] subRegistro034 = new String[19];
		String[] subRegistro035 = new String[34];
		String[] subRegistro036 = new String[19];
		String[] subRegistro053 = new String[11];
		String[] subRegistro046 = new String[22];
		String[] subRegistro047 = new String[22];
		
		
		try (BufferedReader arq = new BufferedReader(new FileReader(arquivo))){
			
			//Condição para leitura do arquivo até que não hajam linhas
			boolean condit = entrada.hasNext();
			int controle = 0;
			/*int reg34Cont = 0;
			int reg46Cont = 0;
			int reg47Cont = 0;*/

			//Loop inicial de leitura do arquivo
			while(condit == true && controle < qtdLinhas) {
			
			registro[controle] = arq.readLine();
			
			
			//Identificação do tipo de registro;
			identifySubregistro[0] = registro[controle].substring(0, 3); //Tipo fixo		
				
			if(identifySubregistro[0].equals("034")){
				//reg34Cont += 1;
				
				Registro034 regis34 = new Registro034();
				
				subRegistro034[0] = identifySubregistro[0];
				regis34.tipo_registro = subRegistro034[0];
				
				subRegistro034[1] = registro[controle].substring(3, 12);
				regis34.num_pv_centralizador = subRegistro034[1];
				
				subRegistro034[2] = registro[controle].substring(12, 23);
				regis34.num_documento = subRegistro034[2];
				
				subRegistro034[3] = registro[controle].substring(23, 31);
				regis34.data_lancamento = subRegistro034[3];
				
				subRegistro034[4] = registro[controle].substring(31, 46);
				regis34.valor_lancamento = subRegistro034[4];
				
				subRegistro034[5] = registro[controle].substring(46, 47);
				regis34.credito = subRegistro034[5];
				
				subRegistro034[6] = registro[controle].substring(47, 50);
				regis34.banco = subRegistro034[6];
				
				subRegistro034[7] = registro[controle].substring(50, 56);
				regis34.agencia = subRegistro034[7];
				
				subRegistro034[8] = registro[controle].substring(56, 67);
				regis34.conta = subRegistro034[8];
				
				subRegistro034[9] = registro[controle].substring(67, 75);
				regis34.data_movimento = subRegistro034[9];
				
				subRegistro034[10] = registro[controle].substring(75, 84);
				regis34.num_rv = subRegistro034[10];
				
				subRegistro034[11] = registro[controle].substring(84, 92);
				regis34.data_rv = subRegistro034[11];
				
				subRegistro034[12] = registro[controle].substring(92, 93);
				regis34.bandeira = subRegistro034[12];
				
				subRegistro034[13] = registro[controle].substring(93, 94);
				regis34.tipo_de_transacao = subRegistro034[13];
				
				subRegistro034[14] = registro[controle].substring(94, 109);
				regis34.valor_bruto_rv = subRegistro034[14];
				
				subRegistro034[15] = registro[controle].substring(109, 124);
				regis34.valor_taxa_desconto = subRegistro034[15];
				
				subRegistro034[16] = registro[controle].substring(124, 129);
				regis34.num_parcel_total = subRegistro034[16];
				
				subRegistro034[17] = registro[controle].substring(129, 131);
				regis34.status_credito = subRegistro034[17];
				
				subRegistro034[18] = registro[controle].substring(131, 140);
				regis34.num_pv_original = subRegistro034[18];
				
				registros034.add(regis34);
				//System.out.println("Registro do tipo 034 inserido!");
				
			}else if(identifySubregistro[0].equals("035")) {
				
				Registro035 regis35 = new Registro035();
				
				subRegistro035[0] = identifySubregistro[0];
				regis35.tipoRegistro = subRegistro035[0];
				
				subRegistro035[1] = registro[controle].substring(3, 12);
				regis35.numPvAjustado = subRegistro035[1];
				
				subRegistro035[2] = registro[controle].substring(12, 21);
				regis35.numRvAjustado = subRegistro035[2];
				
				subRegistro035[3] = registro[controle].substring(21, 29);
				regis35.dataAjuste = subRegistro035[3];
				
				subRegistro035[4] = registro[controle].substring(29, 44);
				regis35.valorAjuste = subRegistro035[4];
				
				subRegistro035[5] = registro[controle].substring(44, 45);
				regis35.debito = subRegistro035[5];
				
				subRegistro035[6] = registro[controle].substring(45, 47);
				regis35.motivoAjuste = subRegistro035[6];
				
				subRegistro035[7] = registro[controle].substring(47, 75);
				regis35.motivoAjusteTxt = subRegistro035[7];
				
				subRegistro035[8] = registro[controle].substring(75, 91);
				regis35.numCartao = subRegistro035[8];
				
				subRegistro035[9] = registro[controle].substring(91, 99);
				regis35.dataTransacaoCv = subRegistro035[9];
				
				subRegistro035[10] = registro[controle].substring(99, 108);
				regis35.numRvOriginal = subRegistro035[10];
				
				subRegistro035[11] = registro[controle].substring(108, 123);
				regis35.numRefCartaFax = subRegistro035[11];
				
				subRegistro035[12] = registro[controle].substring(123, 131);
				regis35.dataCarta = subRegistro035[12];
				
				subRegistro035[13] = registro[controle].substring(131, 137);
				regis35.mesRef = subRegistro035[13];
				
				subRegistro035[14] = registro[controle].substring(137, 146);
				regis35.numPvOriginal = subRegistro035[14];
				
				subRegistro035[15] = registro[controle].substring(146, 154);
				regis35.dataRvOriginal = subRegistro035[15];
				
				subRegistro035[16] = registro[controle].substring(154, 169);
				regis35.valorTransacao = subRegistro035[16];
				
				subRegistro035[17] = registro[controle].substring(169, 170);
				regis35.desagendamentoOuNet = subRegistro035[17];
				
				subRegistro035[18] = registro[controle].substring(170, 178);
				regis35.dataCredito = subRegistro035[18];
				
				subRegistro035[19] = registro[controle].substring(178, 193);
				regis35.novoValorParcela = subRegistro035[19];
				
				subRegistro035[20] = registro[controle].substring(193, 208);
				regis35.valorOriginalParcela = subRegistro035[20];
				
				subRegistro035[21] = registro[controle].substring(208, 223);
				regis35.valorBrutoRvOriginal = subRegistro035[21];
				
				subRegistro035[22] = registro[controle].substring(223, 238);
				regis35.valorCancelamentoSolicitado = subRegistro035[22];
				
				subRegistro035[23] = registro[controle].substring(238, 250);
				regis35.numNsu = subRegistro035[23];
				
				subRegistro035[24] = registro[controle].substring(250, 256);
				regis35.numAutorizacao = subRegistro035[24];
				
				subRegistro035[25] = registro[controle].substring(256, 257);
				regis35.tipoDebito = subRegistro035[25];
				
				subRegistro035[26] = registro[controle].substring(257, 268);
				regis35.numOrdemDebito = subRegistro035[26];
						
				subRegistro035[27] = registro[controle].substring(268, 283);
				regis35.valorDebitoTotal = subRegistro035[27];
				
				subRegistro035[28] = registro[controle].substring(283 ,298);
				regis35.valorPedente = subRegistro035[28];
				
				subRegistro035[29] = registro[controle].substring(298, 299);
				regis35.bandeiraRvOrigem = subRegistro035[29];
				
				subRegistro035[30] = registro[controle].substring(299, 300);
				regis35.bandeiraRvAjustado = subRegistro035[30];
				
				subRegistro035[31] = registro[controle].substring(300, 302);
				regis35.numParcelaRvAjustado = subRegistro035[31];
				
				subRegistro035[32] = registro[controle].substring(302, 304);
				regis35.numParcelaRvOriginal = subRegistro035[32];
				
				subRegistro035[33] = registro[controle].substring(304, 312);
				regis35.dataRvAjustado = subRegistro035[33];
				
				registros035.add(regis35);
				
			}else if(identifySubregistro[0].equals("053")) {
				Registro053 regis53 = new Registro053();
				
				subRegistro053[0] = identifySubregistro[0];
				regis53.tipoRegistro = subRegistro053[0];
				
				subRegistro053[1] = registro[controle].substring(3, 19);
				regis53.numCartao = subRegistro053[1];
				
				subRegistro053[2] =  registro[controle].substring(19, 27);
				regis53.dataTransacaoCV = subRegistro053[2];
				
				subRegistro053[3] = registro[controle].substring(27, 36);
				regis53.numRvOriginal = subRegistro053[3];
				
				subRegistro053[4] = registro[controle].substring(36, 45);
				regis53.numPvOriginal = subRegistro053[4];
				
				subRegistro053[5] = registro[controle].substring(45, 60);
				regis53.valorTransacao = subRegistro053[5];
				
				subRegistro053[6] = registro[controle].substring(60, 72);
				regis53.numNsu = subRegistro053[6];
				
				subRegistro053[7] = registro[controle].substring(72, 78);
				regis53.numAutorizacao = subRegistro053[7];
				
				subRegistro053[8] = registro[controle].substring(78, 98);
				regis53.tId = subRegistro053[8];
				
				subRegistro053[9] = registro[controle].substring(98, 128);
				regis53.numPedido = subRegistro053[9];
				
				registros053.add(regis53);
				
			}else if(identifySubregistro[0].equals("036")) { 
				Registro036 regis36 = new Registro036();
				
				subRegistro036[0] = identifySubregistro[0];
				regis36.tipoRegistro = subRegistro046[0];
				
				subRegistro036[1] = registro[controle].substring(3, 12);
				regis36.numPV = subRegistro046[0];
				
				subRegistro036[2] = registro[controle].substring(12, 23);
				regis36.numDocumento = subRegistro046[0];
				
				subRegistro036[3] = registro[controle].substring(23, 31);
				regis36.dataLancamento = subRegistro046[0];
				
				subRegistro036[4] = registro[controle].substring(31, 46);
				regis36.valorLancamento = subRegistro046[0];
				
				subRegistro036[5] = registro[controle].substring(46, 47);
				regis36.credito = subRegistro046[0];
				
				subRegistro036[6] = registro[controle].substring(47, 50);
				regis36.banco = subRegistro046[0];
				
				subRegistro036[7] = registro[controle].substring(50, 56);
				regis36.agencia = subRegistro046[0];
				
				subRegistro036[8] = registro[controle].substring(56, 67);
				regis36.conta = subRegistro046[0];
				
				subRegistro036[9] = registro[controle].substring(67, 76);
				regis36.numRvCorresp = subRegistro046[0];
				
				subRegistro036[10] = registro[controle].substring(76, 84);
				regis36.dataRvCorresp = subRegistro046[0];
				
				subRegistro036[11] = registro[controle].substring(84, 99);
				regis36.valorCreditoOriginal = subRegistro046[0];
				
				subRegistro036[12] = registro[controle].substring(99, 107);
				regis36.dataVencimentoOriginal = subRegistro046[0];
				
				subRegistro036[13] = registro[controle].substring(107, 112);
				regis36.numParcelaTotal = subRegistro046[0];
				
				subRegistro036[14] = registro[controle].substring(112, 127);
				regis36.valorBruto = subRegistro046[0];
				
				subRegistro036[15] = registro[controle].substring(127, 142);
				regis36.valorTaxaDesconto = subRegistro046[0];
				
				subRegistro036[16] = registro[controle].substring(142, 151);
				regis36.numPvOriginal = subRegistro046[0];
				
				subRegistro036[17] = registro[controle].substring(151, 152);
				regis36.bandeira = subRegistro046[0];
				
				registros036.add(regis36);
				
			}else if(identifySubregistro[0].equals("046")) {
				//reg46Cont += 1;
					
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
				//System.out.println("Registro do tipo 046 inserido!");
				
			} else if(identifySubregistro[0].equals("047")) {
				//reg47Cont += 1;
				
				Registro047 regis47 = new Registro047();
				
				subRegistro047[0] = identifySubregistro[0];
				regis47.tipo_registro = subRegistro047[0];
				//EC
				subRegistro047[1] = registro[controle].substring(3, 12);
				regis47.num_EC = subRegistro047[1];
				
				//OC REF NUMBER
				subRegistro047[2] = registro[controle].substring(12, 23);
				regis47.num_OC_ref = subRegistro047[2];
				
				//VALUE OC REF
				subRegistro047[3] = registro[controle].substring(23, 40);
				regis47.valor_OC_ref =  subRegistro047[3];
				
				//DATA OC REF
				subRegistro047[4] = registro[controle].substring(40, 48);
				regis47.data_OC_ref = subRegistro047[4];
					
				//EC ORIGINAL NUMBER
				subRegistro047[5] = registro[controle].substring(48, 57);
				regis47.num_EC_orig_venda = subRegistro047[5];
					
				//NUMBER OF RV
				subRegistro047[6] = registro[controle].substring(57, 66);
				regis47.num_rv = subRegistro047[6];
					
				//DATE OF RV
				subRegistro047[7] = registro[controle].substring(66, 74);
				regis47.data_venda_RV = subRegistro047[7];
					
				//TRANSACTION TYPE (TABLE ONE)
				subRegistro047[8] = registro[controle].substring(74, 75);
				regis47.transact_parcel_tipo = subRegistro047[8];
				
				//BAND. CODE
				subRegistro047[9] = registro[controle].substring(75, 76);
				regis47.code_band  = subRegistro047[9];
					
				//1 SESSION 2 GRAVAM
				subRegistro047[10] = registro[controle].substring(76, 77);
				regis47.negociacao_tipo = subRegistro047[10];
					
				//CONTRATO NUMBER
				subRegistro047[11] = registro[controle].substring(77, 94);
				regis47.contrato_number = subRegistro047[11];
					
				//CNPJ PARCEIRO
				subRegistro047[12] = registro[controle].substring(94, 109);
				regis47.cnpj_parceiro = subRegistro047[12];
					
				//Num do doc oc, gerado na negociacao
				subRegistro047[13] = registro[controle].substring(109, 120);
				regis47.num_doc_OC_neg = subRegistro047[13];
					
				//Valor negociado
				subRegistro047[14] = registro[controle].substring(120, 137);
				regis47.valor_negoc = subRegistro047[14];
					
				//Data da Negociação
				subRegistro047[15] = registro[controle].substring(137, 145);
				regis47.data_negociacao = subRegistro047[15];

				//Data de Liquidação
				subRegistro047[16] = registro[controle].substring(145, 153);
				regis47.data_liquidacao = subRegistro047[16];
					
				//Banco
				subRegistro047[17] = registro[controle].substring(153, 156);
				regis47.bank_number = subRegistro047[17];
					
				//Agencia
				subRegistro047[18] = registro[controle].substring(156, 162);
				regis47.bank_branch_number = subRegistro047[18];
					
				//Conta
				subRegistro047[19] = registro[controle].substring(162, 173);
				regis47.bank_account_number = subRegistro047[19];
					
				//Status do crédito - Tabela II
				subRegistro047[20] = registro[controle].substring(173, 175);
				regis47.status_credito = subRegistro047[20];
					
				//parcela
				subRegistro047[21] = registro[controle].substring(175, 177);
				regis47.parcela_numero = subRegistro047[21];
				
				registros047.add(regis47);
				//System.out.println("Registro do tipo 047 inserido!");
			}
				controle++;
				
				linhaLeitura.close();
			}
			
			MontarPlanilha034 start034 = new MontarPlanilha034();
			MontarPlanilha035 start035 = new MontarPlanilha035();
			MontarPlanilha036 start036 = new MontarPlanilha036();
			MontarPlanilha053 start053 = new MontarPlanilha053();
			MontarPlanilha046 start046 = new MontarPlanilha046();
			MontarPlanilha047 start047 = new MontarPlanilha047();
			
			start034.criarArquivo034("034_Ordem de Crédito.xlsx", registros034);
			start035.criarArquivo035("035_Ajustes Net e Desagendamento.xlsx", registros035);
			start036.criarArquivo036("036_Antecipações.xlsx", registros036);
			start053.criarArquivo053("053_Ajustes Net e Desagendamentos (E-Commerce).xlsx", registros053);
			start046.criarArquivo046("046_Transações Negociadas e Liquidadas(vendas crédito).xlsx", registros046);
			start047.criarArquivo047("047_Transações em negociação(vendas crédito).xlsx", registros047);
			System.exit(0);
		}entrada.close();
	}
}
