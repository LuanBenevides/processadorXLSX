package conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import conversor.rede.models.eefi.Registro046;

public class MontarPlanilha046 {
	
	public void criarArquivo046(final String nomeArquivo, final List<Registro046> registros46) {
			
			System.out.println("Gerando arquivo: " + nomeArquivo);
			
			try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
				var planilha = workbook.createSheet("Layout Rede - Registros 046");
				
				int numeroDaLinha = 0;
				
				adicionarCabecalho046(planilha, numeroDaLinha++);
				
				for(Registro046 registro046: registros46) {
					
	                var linha = planilha.createRow(numeroDaLinha++);
	                adicionarCelula046(linha, 0, registro046.tipo_registro);
	                adicionarCelula046(linha, 1, registro046.num_EC);
	                adicionarCelula046(linha, 2, registro046.num_OC_ref);
	                adicionarCelula046(linha, 3, registro046.valor_OC_ref);
	                adicionarCelula046(linha, 4, registro046.data_OC_ref);
	                adicionarCelula046(linha, 5, registro046.num_EC_orig_venda);
	                adicionarCelula046(linha, 6, registro046.num_rv);
	                adicionarCelula046(linha, 7, registro046.data_venda_RV);
	                adicionarCelula046(linha, 8, registro046.transact_parcel_tipo);
	                adicionarCelula046(linha, 9, registro046.code_band);
	                adicionarCelula046(linha, 10, registro046.negociacao_tipo);
	                adicionarCelula046(linha, 11, registro046.contrato_number);
	                adicionarCelula046(linha, 12, registro046.cnpj_parceiro);
	                adicionarCelula046(linha, 13, registro046.num_doc_OC_neg);
	                adicionarCelula046(linha, 14, registro046.valor_negoc);
	                adicionarCelula046(linha, 15, registro046.data_negociacao);
	                adicionarCelula046(linha, 16, registro046.data_liquidacao);
	                adicionarCelula046(linha, 17, registro046.bank_number);
	                adicionarCelula046(linha, 18, registro046.bank_branch_number);
	                adicionarCelula046(linha, 19, registro046.bank_account_number);
	                adicionarCelula046(linha, 20, registro046.status_credito);
	                adicionarCelula046(linha, 21, registro046.parcela_numero);
	                
	            }
	            workbook.write(outputStream);
			}catch (FileNotFoundException e) {
	            System.out.println("Arquivo nao encontrado: " + nomeArquivo);
	        } catch (IOException e) {
	            System.out.println("Erro ao processar o arquivo: " + nomeArquivo);
	        }
	       System.out.println("Arquivo gerado com sucesso!");
		}
	
	private void adicionarCabecalho046(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha++);
        adicionarCelula046(linha, 0, "Tipo do registro");
        adicionarCelula046(linha, 1, "N??mero do Estabelecimento");
        adicionarCelula046(linha, 2, "N??mero do Documento OC ??? Refer??ncia");
        adicionarCelula046(linha, 3, "Valor da OC ??? Refer??ncia");
        adicionarCelula046(linha, 4, "Data do lan??amento OC");
        adicionarCelula046(linha, 5, "N??mero do estabelecimento original da venda");
        adicionarCelula046(linha, 6, "N??mero do Resumo de Venda");
        adicionarCelula046(linha, 7, "Data da Venda do RV");
        adicionarCelula046(linha, 8, "Identifica transa????es ?? vista, parceladas etc.");
        adicionarCelula046(linha, 9, "C??digo da Bandeira");
        adicionarCelula046(linha, 10, "Tipo da Negocia????o - Cess??o (1) e ou Gravame (2)");
        adicionarCelula046(linha, 11, "N??mero do Contrato de Negocia????o");
        adicionarCelula046(linha, 12, "N??mero do CNPJ Parceiro");
        adicionarCelula046(linha, 13, "N??mero do Documento OC gerado na negocia????o");
        adicionarCelula046(linha, 14, "Valor Negociado");
        adicionarCelula046(linha, 15, "Data da Negocia????o");
        adicionarCelula046(linha, 16, "Data da liquida????o");
        adicionarCelula046(linha, 17, "Banco");
        adicionarCelula046(linha, 18, "Ag??ncia");
        adicionarCelula046(linha, 19, "Conta");
        adicionarCelula046(linha, 20, "Status do cr??dito ");
        adicionarCelula046(linha, 21, "Parcela");        
    }
	
	 private void adicionarCelula046(Row linha, int coluna, String valor) {
	        Cell cell = linha.createCell(coluna);
	        cell.setCellValue(valor);
	    }
}

