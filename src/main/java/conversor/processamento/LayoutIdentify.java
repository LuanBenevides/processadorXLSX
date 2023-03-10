package conversor.processamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import conversor.processamento.rede.ProcessaRedeEEFI;

public class LayoutIdentify {
	
	public void identify(File arquivo) throws IOException {
		
		File arquivoEmCatalogacao = new File(arquivo.getPath());
		
		try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoEmCatalogacao))) {
			
			String linha = leitura.readLine();
			
			System.out.println(linha);
			
			boolean contemRedeCredito = linha.contains("EEVC" );
			boolean contemRedeDebito = linha.contains("EEVD" );
			boolean contemRedeFinanceiro = linha.contains("EEFI");
			
			
			if(contemRedeCredito == true) {
				System.out.println("Layout do tipo Rede crédito!");
			}else if(contemRedeDebito == true){
				System.out.println("Layout do tipo Rede débito");
			}else if(contemRedeFinanceiro == true) {
				System.out.println("Layout do tipo Rede financeiro");
				
				ProcessaRedeEEFI eefi = new ProcessaRedeEEFI();
				
				eefi.gerarPlanilhaRedeEEFI(arquivo);
				
				System.out.println("Arquivos gerados com sucesso!");
	
			}
			
			
			//gerarPlanilhaRedeEEFI
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
}
