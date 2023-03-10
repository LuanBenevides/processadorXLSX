package conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import conversor.rede.models.eefi.Registro045;

public class MontarPlanilha045 {

public void criarArquivo045(final String nomeArquivo, final List<Registro045> registros45) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 045");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho045(planilha, numeroDaLinha++);
			
			for(Registro045 registro045: registros45) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula045(linha, 0, registro045.tipoRegistro);
                adicionarCelula045(linha, 1, registro045.numeroPv);
                adicionarCelula045(linha, 2, registro045.numeroOrdemDebito);
                adicionarCelula045(linha, 3, registro045.dataOrdemDebito);
                adicionarCelula045(linha, 4, registro045.valorOrdemDebito);
                adicionarCelula045(linha, 5, registro045.motivoAjuste);
                adicionarCelula045(linha, 6, registro045.motivoAjusteString);
                adicionarCelula045(linha, 7, registro045.numCartao);
                adicionarCelula045(linha, 8, registro045.numNsu);
                adicionarCelula045(linha, 9, registro045.dataCvOriginalTransacao);
                adicionarCelula045(linha, 10, registro045.numAutorizacao);
                adicionarCelula045(linha, 11, registro045.valorTransacaoOriginal);
                adicionarCelula045(linha, 12, registro045.numRvOriginal);
                adicionarCelula045(linha, 13, registro045.dataRvOriginal);
                adicionarCelula045(linha, 14, registro045.numPvOriginal);
                adicionarCelula045(linha, 15, registro045.numReferenciaCartaFax);
                adicionarCelula045(linha, 16, registro045.dataCarta);
                adicionarCelula045(linha, 17, registro045.numProcessoChargeback);
                adicionarCelula045(linha, 18, registro045.mesReferencia);
                adicionarCelula045(linha, 19, registro045.valorLiquidado);
                adicionarCelula045(linha, 20, registro045.dataLiquidacao);
                adicionarCelula045(linha, 21, registro045.numProcessoRetencao);
                adicionarCelula045(linha, 22, registro045.meioCompensacao);
                adicionarCelula045(linha, 23, registro045.meioCompensacaoString);
                adicionarCelula045(linha, 24, registro045.bandeira);
            }
            workbook.write(outputStream);
		}catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + nomeArquivo);
        }
       System.out.println("Arquivo gerado com sucesso!");
	}
	private void adicionarCabecalho045(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha++);
        adicionarCelula045(linha, 0, "Tipo do registro");
        adicionarCelula045(linha, 1, "N??mero do PV");
        adicionarCelula045(linha, 2, "N??mero da ordem de d??bito");
        adicionarCelula045(linha, 3, "Data da ordem de d??bito");
        adicionarCelula045(linha, 4, "Valor da ordem de d??bito");
        adicionarCelula045(linha, 5, "Motivo do ajuste (Tabela III)");
        adicionarCelula045(linha, 6, "Motivo do ajuste (String)");
        adicionarCelula045(linha, 7, "N??mero do cart??o");
        adicionarCelula045(linha, 8, "N??mero do NSU");
        adicionarCelula045(linha, 9, "Data do CV original da transa????o");
        adicionarCelula045(linha, 10, "N??mero da autoriza????o");
        adicionarCelula045(linha, 11, "Valor da transa????o original");
        adicionarCelula045(linha, 12, "N??mero do RV original");
        adicionarCelula045(linha, 13, "Data do RV original");
        adicionarCelula045(linha, 14, "N??mero do PV original");
        adicionarCelula045(linha, 15, "N??mero de refer??ncia da carta/fax");
        adicionarCelula045(linha, 16, "Data da carta");
        adicionarCelula045(linha, 17, "N??mero do processo de chargeback");
        adicionarCelula045(linha, 18, "M??s de refer??ncia");
        adicionarCelula045(linha, 19, "Valor Liquidado");
        adicionarCelula045(linha, 20, "Data da Liquida????o");  
        adicionarCelula045(linha, 22, "N??mero do processo de reten????o");
        adicionarCelula045(linha, 23, "Meio de compensa????o");
        adicionarCelula045(linha, 24, "Meio de compensa????o (String)");
        adicionarCelula045(linha, 25, "Identifica a Bandeira");
    }
	private void adicionarCelula045(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
