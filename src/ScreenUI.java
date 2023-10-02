import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class ScreenUI implements ActionListener {
    //Objeto input_text da classe JTextField, trata-se da caixa de texto da nossa tela de entrada/saida
    JTextField input_text;
    //Construtor da nossa classe ScreenUI, nao recebe parametros, ao inicializar o construtor do objeto a interface ja eh inicializada
    public ScreenUI(){
    //Objeto jf da classe JFrame: usado para criar nossa tela inicial
    JFrame jf = new JFrame("Drone++");
    //Metodos do objeto da classe JFrame:
    //setVisible: default 'false', chamanado esse metodo com argumento 'true' conseguimos abrir a primeira tela do nosso programa
    jf.setVisible(true);
    //setSize: ajusta o tamanho da tela
    jf.setSize(1280,720);
    //setDefaultCloseOperation: encerra o programa ao fechar a tela inicial
    jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
    //setlocationRelativeTo:Ao receber o argumento 'null' centraliza a janela do programa com a tela do computador
    jf.setLocationRelativeTo(null);
    //setLayout: ao receber o argumento 'null' ele deixa o layout da tela livre para ser editado
    jf.setLayout(null);
    //Objetos jb1 e jb2 da classe JButton, para criar os butoes do programa
    //jb1 representa o primeiro botao da tela inicial do nosso programa, jb2 representa o segundo botao da tela inicial
    //Inicializamos os construtores dos objetos com o texto indicativos de cada um dos botoes
    JButton jb1= new JButton("SET INPUT");
    JButton jb2= new JButton("GUIDE");
    //setBounds: posiciona o botao na tela, recebe quatro entradas, a primeira a posicao x da ponta superior esquerda do botao, a segunda a posicao y do mesmo ponto, e entao a largura e altura do botao
    jb1.setBounds(452, 249, 375, 91);
    //setFont: edita a fonte do texto de cada botao, recebe como argumento o nome da fonte, o tipo da fonte, e o tamanho da fonte
    jb1.setFont(new Font("Impact",Font.PLAIN,40));
    jb2.setBounds(452, 380, 375, 91);
    jb2.setFont(new Font("Impact",Font.PLAIN,40));
    //add: metodo do objeto jf da classe JFrame, adiciona o objeto jb da classe JButton na tela
    jf.add(jb1);
    jf.add(jb2);
    //addActionListener: adiciona uma acao ao clicar no botao, receber como patrametro o nome do metodo que o ActionListener vai chamar quando for clicado no botao
    jb1.addActionListener(this::inputUI);
    jb2.addActionListener(this);
    }
    //Metodo que sera invocado pelo ActionListener ao clicar no botao jb1, vao inicializar a tela de entrada e saida
    public void inputUI(ActionEvent actionEvent){
        JFrame jf2 = new JFrame("Drone++");
        jf2.setVisible(true);
        jf2.setSize(1280,720);
        jf2.setLocationRelativeTo(null);
        jf2.setLayout(null);
        input_text = new JTextField("ENTER INPUT: N X1 Y1 X2 Y2 ... XN YN");
        JButton jb4= new JButton("CLICK HERE");
        input_text.setBounds(452, 249, 375, 91);
        input_text.setFont(new Font("Impact",Font.PLAIN,28));
        jb4.setBounds(452, 380, 375, 91);
        jb4.setFont(new Font("Impact",Font.PLAIN,40));
        jf2.add(input_text);
        jf2.add(jb4);
        jb4.addActionListener(this::outputMessage);

    }
    //Metodo que calcula a distancia entre dois pontos:(X1,Y1) e (X2,Y2) no plano cartesiano
    //Recebe como parametros 4 variaveis do tipo double x1,y1,x2,y2 e retorna uma variavel double que representa a distancia entre os dois pontos
    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    //Metodo que sera invocado pelo ActionListener ao clicar no botao jb4
    //Esse metodo vai rodar o nosso algoritmo do Travel Salesman Problem(TSP), o algoritmo do TSP recebe como entrada a string do input_text e gera uma string para nossa saida, e entao a saida eh printada como uma mensagem na tela
    public void outputMessage(ActionEvent actionEvent) {
        //Metodo getText do objeto input_text da classe JTextField, retorna a string digitada na caixa de texto, nao recebe argumentos
        String str = new String(input_text.getText());
        String[] args=str.split("\\s+");
        int n = Integer.parseInt(args[0]);
        double x[] = new double[n], y[] = new double[n];
        for(int i = 1; i < 2 * n + 1; i += 2) {
            x[(i - 1) / 2] = Double.parseDouble(args[i]);
            y[(i - 1) / 2] = Double.parseDouble(args[i + 1]);
        }
        double dp[][] = new double[n][(1 << n)];
        int prv[][] = new int[n][(1 << n)];
        for(int i = 0; i < n; i++) {
            dp[i][(1 << i)] = dist(0, 0, x[i], y[i]);
            prv[i][(1 << i)] = -1;
        }
        for(int mask = 1; mask < (1 << n); mask++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == j) continue;
                    if(((mask >> i) & 1) == 1 && ((mask >> j) & 1) == 0) {
                        if(dp[j][(mask | (1 << j))] == 0 || dp[j][(mask | (1 << j))] > dp[i][mask] + dist(x[i], y[i], x[j], y[j])) {
                            dp[j][(mask | (1 << j))] = dp[i][mask] + dist(x[i], y[i], x[j], y[j]);
                            prv[j][(mask | (1 << j))] = i;
                        }
                    }
                }
            }
        }
        double tmp = Double.MAX_VALUE;
        int id = -1;
        for(int i = 0; i < n; i++) {
            if(dp[i][(1 << n) - 1] + dist(0, 0, x[i], y[i]) < tmp) {
                tmp = dp[i][(1 << n) - 1] + dist(0, 0, x[i], y[i]);
                id = i;
            }
        }
        ArrayList<Integer> path = new ArrayList<Integer>();
        int mask = (1 << n) - 1;
        path.add(-1);
        while(id != -1) {
            path.add(id);
            int nid = prv[id][mask];
            mask ^= (1 << id);
            id = nid;
        }
        path.add(-1);
        String output1 = new String(tmp + " is the total distance.");
        String output2 = new String("The order of the points:\n");
        for(int i = 0; i < path.size(); i++) {
            if(path.get(i) == -1) {
               output2+=" (0,0)\n";
            } 
            else {
                output2+=" " + "("+x[path.get(i)]+ "," + y[path.get(i)]+")\n";
            }
        }
        JOptionPane.showMessageDialog(null,output1);
        JOptionPane.showMessageDialog(null,output2);
    }
    //Metodo que sera invocado pelo ActionListener ao clicar no botao jb2, apenas gera uma mensagem na tela de guia para o usuario usar a inteface
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"Please go to 'Set Input' and Enter the coordinates that the drone should reach.\nThen, our app will give you the shortest path passing through all the points");
    }
    
}
