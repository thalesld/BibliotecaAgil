
package bibliotecaagil;

import java.util.Scanner;
/**
 *
 * @author Thales
 */
public class Funcionalidades {
    Menu m = new Menu();
    Scanner entrada = new Scanner(System.in);
    Scanner entrada2 = new Scanner(System.in);
    String txt = "livros.txt";
    
    public void mostrarLivros(){   
        String arquivo = Arquivo.lerArquivo(txt);
        int tamanho = Arquivo.getQtdLinhas()*6;
        if(tamanho != 0){
            System.out.println("\n-----CATÁLOGO DE LIVROS-----");  
            for(int x=0; x<tamanho; x+=6){
                String a1 = arquivo.split(";")[x];
                String a2 = arquivo.split(";")[x+1];
                String a3 = arquivo.split(";")[x+2];
                String a4 = arquivo.split(";")[x+3];
                String a5 = arquivo.split(";")[x+4];
                String a6 = arquivo.split(";")[x+5];
                System.out.println("Código: "+a1);
                System.out.println("Titulo: "+a2);
                System.out.println("Autor: "+a3);
                System.out.println("Ano: "+a4);
                System.out.println("Status: "+a5);
                if(a6.length() <= 4){System.out.println("Emprestado para: ");
                }else{System.out.println("Emprestado para: "+a6);}
                System.out.println("-------------\n");
            }
        }else{
            System.out.println("\nRETORNANDO ao Menu principal...\n");
        }
        m.mostrarMenu();
    }
    
    public void retirarLivro(){
        String arquivo = Arquivo.lerArquivo(txt);
        
        int linha = m.digitarCodigo();
        int posicao = linha*6;
        String cod = ":"+linha+":"; //Caractere para diferenciar os status no arquivo
        
        String a1 = arquivo.split(";")[posicao-1];
        String a2 = arquivo.split(";")[posicao-2];
        if(a2.equals("Disponível"+cod)){
            System.out.print("Digite o NOME do retirante: ");
            String nome = entrada2.nextLine();
            Arquivo.substituirPalavra(txt, a1, nome+cod);
            Arquivo.substituirPalavra(txt, a2, "Indisponível"+cod);
            System.out.println("\nLivro RETIRADO com sucesso!");
        }else{
            System.out.println("\nLivro está com "+a1+", retirada INDISPONÍVEL!");
        }
        System.out.println("\nRETORNANDO ao Menu principal...\n");
        m.mostrarMenu();
        
    }
    
    public void devolverLivro(){
        String arquivo = Arquivo.lerArquivo(txt);
        
        int linha = m.digitarCodigo();
        int posicao = linha*6;
        String cod = ":"+linha+":";
        
        String a1 = arquivo.split(";")[posicao-1];
        String a2 = arquivo.split(";")[posicao-2];
        String a3 = arquivo.split(";")[posicao-5];
        if(a2.equals("Indisponível"+cod)){
            System.out.println("Confirma a devolução do livro '"+a3+"' retirado por "+a1+"?");
            System.out.print("1- CONFIRMAR  2- CANCELAR: ");
            int x = entrada.nextInt();
            switch(x){
                case 1:
                    Arquivo.substituirPalavra(txt, a1, " "+cod);
                    Arquivo.substituirPalavra(txt, a2, "Disponível"+cod);
                    System.out.println("\nLivro DEVOLVIDO com sucesso!");
                    break;
                case 2:
                    System.out.println("Operação CANCELADA!");
                    break;
            }
        }else if(a2.equals("Disponível"+cod)){
            System.out.println("\nNão é possível realizar a devolução.\nLivro ja está na biblioteca!");
        }else{
            System.out.println("Livro não encontrado!");
        }
         System.out.println("\nRETORNANDO ao menu principal...\n");
        m.mostrarMenu();
    }
    
    public void doarLivro(){
        Arquivo.lerArquivo(txt);
        String cadastro[] = new String[6];
        
        System.out.println("\n*** Nova Doação para Biblioteca ***");
        System.out.println("Cadastre as informações do livro:");
        cadastro[0] = Integer.toString(Arquivo.getQtdLinhas()+1);
        System.out.print("Titulo: ");
        cadastro[1] = entrada.nextLine();
        System.out.print("Autor: ");
        cadastro[2] = entrada.nextLine();
        System.out.print("Ano: ");
        cadastro[3] = entrada.nextLine();
        cadastro[4] = "Disponível"+":"+cadastro[0]+":";
        cadastro[5] = " "+":"+cadastro[0]+":";
        
        String livro = cadastro[0]+";"+cadastro[1]+";"+cadastro[2]+";"+cadastro[3]+";"+cadastro[4]+";"+cadastro[5]+";";
        Arquivo.atualizarArquivo(txt, livro);
        System.out.println("\n** Livro Cadastrado com Sucesso! **\n");
        System.out.println("\nRETORNANDO ao menu principal...\n");
        m.mostrarMenu();
    }
}