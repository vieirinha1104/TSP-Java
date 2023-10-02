Nome do programa: Drone++

Objetivo do nosso programa: Mostrar atraves de uma interface de usuario um algoritmo classico de grafos do problema do caixeiro viajante.

Algoritmo do Caixeiro Viajante: Dado um plano cartesiano xy, em que um caixeiro viajante esta inicialmente no ponto (0,0) do plano, esse caixeiro quer passar por N pontos especificos dos plano (X1,Y1) , ... , (Xn,Yn) e então retornar ao ponto de origem de tal forma que o caixero viajante percorra a distancia minima possivel passando por todos os N pontos exatamente uma vez.

Utilidade para o algoritmo: Esse algoritmo pode ser usado para implementar a inteligencia artificial de um Drone. 

Instrucoes de como usar o nosso programa:
1-) Janela inicial: 
Botao 1-) Get input: Abre uma segunda janela para inserir a entrada e receber a saida do programa
Botao 2-) Guide: Imprime na tela instrucoes de como usar o programa e suas utilidades atraves de uma mensagem de aviso na tela
2-) Janela entrada/saida:
Botao 1-) "Enter input: N X1 Y1 X2 Y2 ... XN YN": Trata-se de uma caixa de texto para colocar a entrada do algoritmo, apague o texto inicial e coloque a entrada no formato indicado, onde N -> Numero de pontos que o drone deve passar, Xi -> Coordenada X do ponto i, Yi -> Coordenada Y do ponto i, todas as variaveis devem ser separadas por espaco
Botao 2-) Click Here: Imprime na tela as saidas do algoritmo do programa atraves de duas mensagens de aviso na tela, a primeira retornando o calculo da distancia que sera percorrida pelo drone e a segunda retornando a ordem dos pontos que o drone ira visitar


Classes utilizadas a partir de biblitecas: import javax.swing.*(JFrame,JButton);import java.awt.Font(JTextField);import java.awt.event.ActionEvent(ActionEvent);import java.awt.event.ActionListener;(implements ActionListener)
1-) JFrame:
Objetos: jf(tela inicial),jf2(tela de entrada/saida do programa)
Metodos: setVisible,setSize,setDefaultCloseOperation,setLocationRelativeTo,setLayout,add
2-) JButton: 
Objetos: jb1(Botao superior da tela inicial),jb2(Botao inferior da tela inicial),jb4(Botao inferior da tela de entrada/saida)
Metodos: setBounds,setFont,addAcitionListener
3-) JTextField
Objetos: input_text(Caixa de texto da tela de entrada/saida)
Metodos:setBounds,setFont,getText
4-) ActionEvent
Objetos: e

Classes utilizadas de nossa autoria:
1-) public class App: função main do nosso programa, não recebe argumentos, nela roda nossa interface atraves da classe ScreenUI
2-) public class ScreenUI
construtor:ScreenUI() - construtor default
metodos:public static double dist,public void outputMessage,public void actionPerformed,public void inputUI