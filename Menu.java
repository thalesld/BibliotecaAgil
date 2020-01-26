
package bibliotecaagil;

import java.util.Scanner;
/**
 *
 * @author Thales
 */
public class Menu {
    Scanner entrada = new Scanner(System.in);
    String txt = "livros.txt";
    
    public void mostrarMenu(){
        Funcionalidades f = new Funcionalidades();
        System.out.println("O que deseja fazer?");
        System.out.println("1- Mostrar Catálogo de Livros");
        System.out.println("2- Retirar um livro");
        System.out.println("3- Devolver um Livro");
        System.out.println("4- Doar um Livro");
        System.out.println("5- Sair do Sistema");
        System.out.print("\nDigite uma opcão: ");
        int opcao = entrada.nextInt();
        switch(opcao){
            case 1:
                f.mostrarLivros();
                break;
            case 2:
                f.retirarLivro();
                break;
            case 3:
                f.devolverLivro();
                break;
            case 4:
                f.doarLivro();
                break;
            case 5:
                System.out.println("SAINDO do programa...");
                break;
            default:
                System.out.println("\nOpção INVÁLIDA!");
                System.out.println("\nRETORNANDO ao Menu principal...\n");
                mostrarMenu();
        }
    }
    
    public int digitarCodigo(){
        Arquivo.lerArquivo(txt);
        System.out.print("Digite o CÓDIGO do livro: ");
        int cod = entrada.nextInt();
        while(cod > Arquivo.getQtdLinhas()){
            System.out.println("Código INVÁLIDO!");
            System.out.print("Digite um código VÁLIDO:");
            cod = entrada.nextInt();
        }
        return cod;
    }
}
