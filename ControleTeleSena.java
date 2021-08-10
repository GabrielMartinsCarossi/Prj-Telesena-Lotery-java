public class ControleTeleSena{
    //Arrays
    private Pessoa[] pessoas;
    private Pessoa[] ganhadores;
    private TeleSena[] telesenas;
    private int[] nrosSorteados;
    //Variáveis
    private int qntdTSVendidas = 0, contNrosSorteados=0, nroDeVencedores=0;
    private double valorVendas; 
    //Constantes:
    
    private static final int NRO_PESSOAS=20; //Número de pessoas participantes
    private static final int NRO_TELESENAS=300;//Número de telesenas a venda
    private static final int NRO_MAX_SORTEIO=60;// Sorteio de 1 até N
    private static final int NRO_MAX_TS_PESSOA=15;//Número máximo de TeleSenas por pessoa
    private static final int NROS_CONJUNTO=25;//Quantidade de números por conjunto
    private static final int SLEEP=25;//O tempo entre a impressão de cada nro do sorteio
    private static final double LUCRO= 0.2;//lucro do Silvio Santos
    private static final double VALOR_VENDA_TS=10.0;//Valor de cada telesena
    private static final String TAB= "         ";//Tabulação da impressão;
    
    //MÉTODOS
    /**
     * Método construtor
     */
    public ControleTeleSena(){
        pessoas= new Pessoa[NRO_PESSOAS];
        ganhadores=new Pessoa[NRO_PESSOAS];
        telesenas= new TeleSena[NRO_TELESENAS];
        nrosSorteados= new int[NRO_MAX_SORTEIO];
    }
    
    /**
     * Método para vender as TeleSenas
     */
    public void vendeTeleSenas(){
        //Vende a qntd de TeleSenas que cada pessoa quer 
        for (int i=0; i<pessoas.length; i++){
            int qntTS= pessoas[i].getTeleSenas().length;  
            qntdTSVendidas += qntTS;
            for (int cont=0; cont<qntTS; cont++){
                pessoas[i].insereTeleSena(getTelesena());
                valorVendas += pessoas[i].getTeleSenas()[cont].getValorVenda();
            }
        }
    }
    
    /**
     * Método para criar as TeleSenas
     */
    public void criaTelesenas(){
        for (int i=0; i<telesenas.length; i++){
            telesenas[i]= new TeleSena(VALOR_VENDA_TS,NRO_MAX_SORTEIO);
        }
    }
    
    /**
     * Método que retorna uma TeleSena do array 
     */
    private TeleSena getTelesena(){
        TeleSena TS = new TeleSena(VALOR_VENDA_TS, NRO_MAX_SORTEIO);
        for (int i=0; i<telesenas.length; i++){
            if (telesenas[i] != null){
                TS = telesenas[i];
                telesenas[i]=null;
                return TS;
            }
        }
        return TS;
    }
    
    /**
     * Método para criar as pessoas
     */
    public void criaPessoas(){
        String[] nomes = {"José","Maria","João","Ana","Antônio","Júlia", "Mateus","Antonia","Carlos","Adriana","Paulo", "Juliana", "Pedro","Márcia","Lucas","Fernanda","Luiz", "Marcos", "Aline",
        "Sandra","Gabriel","Camila","Rafael","Amanda","Daniel","Bruna","Marcelo","Jéssica", "Eduardo", "Felipe"};
        String[] sobrenomes = {"Almeida","Alves","Andrade","Barbosa","Barros","Batista","Borges","Campos","Cardoso","Carvalho","Castro","Costa","Dias","Duarte","Duarte","Freitas","Ferreira","Garcia",
        "Gomes","Gonçalves","Lima","Lopes","Machado","Marques","Martins", "Medeiro", "Melo","Mendes","Miranda","Monteiro","Moraes","Moreira","Moura","Nascimento","Nunes","Oliveira","Pereira","Ramos", 
        "Reis", "Ribeiro", "Rocha", "Santana", "Santos", "Silva", "Soares", "Souza", "Teixeira","Vieira"}; 
        //Criar as pessoas
        for (int i=0; i<NRO_PESSOAS; i++){ 
            //Gera nro aleatório de Tele Senas de 1 até o máximo (15)
            int qntdTeleSenas = (int)(Math.random()* NRO_MAX_TS_PESSOA + 1);
            //Cria nome aleatório
            String nome = nomes[(int) (Math.random()* 29)] + " " +
                          sobrenomes[(int) (Math.random()* 40)];
            //Cria pessoa
            pessoas[i]= new Pessoa(nome, qntdTeleSenas);
        }
    }
    
    /**
     * Método para realizar o sorteio
     */
    public void sorteio(){
        //OBS 1: O método sorteio() sorteia um número por vez, armazenado na variável nro.
        //OBS 2: O método sorteio() chama o método insereNumero() e procuraNumero() após o sorteio de cada número.
        int nroSorteado=0;
        contNrosSorteados= 0;
        //Sorteia os primeiros 25 números
        for (int i=0; i<NROS_CONJUNTO; i++){
            
            do{
                nroSorteado= sorteiaNumero();
            }while(verificaNroRepetido(nroSorteado));  
            insereNumeroSorteado(nroSorteado);
            procuraNumero(nroSorteado);//procura o número nas teleSenas
            contNrosSorteados++;
        }
        //Se não tiver ganhador continua sorteando números até alguém ganhar
        while(!(confereGanhadores())){
            nroSorteado=0;
            do{
                nroSorteado = sorteiaNumero();
            }while(verificaNroRepetido(nroSorteado));
            insereNumeroSorteado(nroSorteado);
            procuraNumero(nroSorteado);//procura o número nas teleSenas
            contNrosSorteados++;
        }
    }
    
    /**
     * Método que sorteia um número de 1 até o máximo (60)
     */
    private int sorteiaNumero(){
        return (int) (Math.random()* NRO_MAX_SORTEIO + 1);
    }
    
    /**
     * Método que verifica um número repetido
     */
    private boolean verificaNroRepetido(int nro){
        for (int i=0; i<nrosSorteados.length; i++){
            if (nrosSorteados[i] == nro){
                return true;
            }
        }    
        return false;
    }
    
    /**
     * Método que insere o número sorteado no array
     */
    private void insereNumeroSorteado(int nro){
        for (int i=0; i<nrosSorteados.length; i++){
            if (nrosSorteados[i] == 0){
                nrosSorteados[i] = nro;    
                break;
            }
        }
    }
    
    /**
     * Método que procura um número(parâmetro) nas TeleSenas de todas as pessoas
     */
    private void procuraNumero(int nro){
        //Ao encontrar o número em um dos conjuntos, um acerto é incrementado.
        //PESSOAS
        for(int i=0; i<NRO_PESSOAS; i++){
            TeleSena[] arrayTS= pessoas[i].getTeleSenas();
            //TELESENA(S)
            for (int cont=0; cont<arrayTS.length; cont++){
                if (arrayTS[cont].encontraNumeroConj1(nro))
                    arrayTS[cont].addAcertosConjunto1();
                if (arrayTS[cont].encontraNumeroConj2(nro))
                    arrayTS[cont].addAcertosConjunto2();    
            }  
        }
    }
    
    /**
     * Método que confere o(s) ganhador(es)
     */
    private boolean confereGanhadores(){
        //Se os acertos forem iguais a qntd de números no conjunto (NROS_CONJUNTO),aquela pessoa ganhou
        boolean ganhador = false;
        //PESSOAS
        for(int i=0; i<NRO_PESSOAS; i++){
            TeleSena[] arrayTS= pessoas[i].getTeleSenas();
            //TELESENA(S)
            for (int cont=0; cont<arrayTS.length; cont++){
                //Confere os acertos- 25/conjunto
                if (arrayTS[cont].getAcertosConjunto1() == NROS_CONJUNTO || 
                    arrayTS[cont].getAcertosConjunto2() == NROS_CONJUNTO){
                    insereGanhador(pessoas[i]);
                    nroDeVencedores++;
                    ganhador=true;
                }
            }  
        }
        return ganhador;    
    }
    
    /**
     * Método que insere uma Pessoa ganhadora no array
     */
    private void insereGanhador(Pessoa p){
        for (int i=0; i<ganhadores.length; i++){
            if (ganhadores[i] == null){
                ganhadores[i]= p;
                break;
            }
        }
    }
    
    //MÉTODOS PARA IMPRESSÃO DE INFORMAÇÕES:
    
    /**
     * Método que imprime todas as informações
     */
    public void imprimeInfo(){
        System.out.print('\u000C');//limpa o terminal
        Figura.imprimeFigura();//imprime a logo da teleSena
        imprimeNrosSorteados();//imprime os números sorteados
        imprimeGanhadores();//imprime os ganahdores
        imprimeDadosEstatisticos();//imprime informações
        System.out.println("\n");
    }
    
    /*
     * Método auxiliar do imprimeInfo()
     */
    public void imprimeNrosSorteados(){
        //Números sorteados na Tele Sena
        System.out.println("                    ◊ NÚMEROS SORTEADOS ◊  ");
        System.out.println(TAB+ "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.print(TAB+"  ");
        for (int i=0; i<nrosSorteados.length; i++){
            if (nrosSorteados[i] != 0){
                try{
                    Thread.sleep(SLEEP);
                }catch(Exception e){}
                if (nrosSorteados[i] <10) 
                    System.out.print("0"+nrosSorteados[i]+"  "); 
                else
                    System.out.print(nrosSorteados[i]+"  ");    
                if ((i+1)%10==0 && i != (nrosSorteados.length-1)){
                    System.out.print("\n"+TAB+"  ");
                }
            }
        }
        System.out.println();
        System.out.println(TAB+"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        //Quantidade de nros sorteados na Tele Sena:
         System.out.println("              Quantidade de números sorteados: " + contNrosSorteados+"\n");
    }
    
    /*
     * Método auxiliar do imprimeInfo() 
     */
    public void imprimeGanhadores(){
        String[] nros = {"⓪","⓵","⓶","⓷","⓸","⓹","⓺","⓻","⓼","⓽","⓾"};
        System.out.println("                       ◊ GANHADORES ◊");
        //Quantidade de ganhadores
        System.out.println("\n"+TAB+"Número de ganhadores: " + nroDeVencedores);    
        //Nome de cada um dos ganhadores e respectivo prêmio
        double premio = valorVendas * (1-LUCRO)/nroDeVencedores;//calcula o prêmio individual
        for (int i=0; i<ganhadores.length; i++){
            if (ganhadores[i] != null){
                System.out.println();
                System.out.print(TAB+nros[i+1]+" "+ganhadores[i].getNome());
                System.out.printf(": R$ %.2f", premio);
            }
        }
        System.out.println("\n\n                 »»-------[̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅]------««");
    }
    
    /*
     * Método auxiliar do imprimeInfo() 
     */
    public void imprimeDadosEstatisticos(){
        System.out.println("\n"+TAB+"Informações:");
        System.out.println(TAB+"Quantidade de TeleSenas vendidas: "+ qntdTSVendidas);//Quantidade de TeleSenas vendidas
        System.out.printf(TAB+"Valor total das TeleSenas vendidas: R$ %.2f", valorVendas);//Valor total das TeleSenas vendidas
        System.out.printf("\n"+TAB+"Valor total sorteado: R$ %.2f", valorVendas * (1-LUCRO));//Valor total sorteado
        System.out.printf("\n"+TAB+"Lucro: R$ %.2f", valorVendas * LUCRO ); //Lucro obtido 
        System.out.println("\n\n                 »»-------[̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅]------««");
    }
}