
package bibliotecaagil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Thales
 */
public class Arquivo {
    static int qtdLinhas;
    
    public static String lerArquivo(String diretorio){
        String conteudo = "";
        int numLinhas = 0;
        try {
            FileReader arquivo = new FileReader(diretorio);
            BufferedReader leitura = new BufferedReader(arquivo);
            String linha="";
            try {
                linha = leitura.readLine();
                while(linha!=null){
                    numLinhas++;
                    conteudo += linha+"";
                    linha = leitura.readLine();
                }
                arquivo.close();
                setQtdLinhas(numLinhas);
                return conteudo;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return "";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    
    public static boolean atualizarArquivo(String diretorio,String conteudo){
        try {
            FileWriter arquivo = new FileWriter(diretorio, true);
            PrintWriter gravacao = new PrintWriter(arquivo);
            gravacao.println(conteudo);
            gravacao.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static void substituirPalavra(String diretorio, String substituido, String substituto) {
        try {
            Path caminho = Paths.get(diretorio);
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(caminho), charset);
            content = content.replaceAll(substituido, substituto);
            Files.write(caminho, content.getBytes(charset));
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

    public static int getQtdLinhas(){
        return qtdLinhas;
    }

    public static void setQtdLinhas(int qtdLinhas){
        Arquivo.qtdLinhas = qtdLinhas;
    }
    
}