public class TeleSena{
    //Atributos
    private double valorVenda;
    private int nros_conjunto;
    private int nro_max_sorteio;
    private int[] conjunto1;
    private int[] conjunto2; 
    private int acertosConjunto1;
    private int acertosConjunto2;
    
    //Métodos 
    /**
     * Método construtor
     */
    public TeleSena(double valorVenda, int nro_max_sorteio){
        this.valorVenda= valorVenda;
        this.nros_conjunto= 25;// 25 números por conjunto
        this.nro_max_sorteio= nro_max_sorteio;
        conjunto1= preencheConjunto();//Cria o conjunto 1
        conjunto2= preencheConjunto();//Cria o conjunto 2
        acertosConjunto1=0;
        acertosConjunto2=0;
    }
    
    // GET
    public double getValorVenda(){
        return valorVenda;
    }
    
    public int[] getConjunto1(){
        return conjunto1;
    }
    
    public int[] getConjunto2(){
        return conjunto2;
    }
    
    public int getAcertosConjunto1(){
        return acertosConjunto1;
    }
    
    public int getAcertosConjunto2(){
        return acertosConjunto2;
    }
    
    //SET
    public void setValorVenda(double valorVenda){
        this.valorVenda=valorVenda;
    }
    
    public String toString(){
        return "Valor:"+valorVenda+ getConjuntos(); 
    }
    
    /**
     * Incrementa os acertos do conjunto 1
     */
    public void addAcertosConjunto1(){
        acertosConjunto1++;
    }
    
    /**
     * Incrementa os acertos do conjunto 2
     */
    public void addAcertosConjunto2(){
        acertosConjunto2++;
    }
    
    /**
     * Método que retorna um array de 25 números aleatórios de 1-60
     */
    public int[] preencheConjunto(){
        int[] aux = new int[nros_conjunto];
        for (int i=0; i<nros_conjunto; i++){
            if (i==0){
                aux[i]= (int) (Math.random()* nro_max_sorteio + 1);
            }
            else{
                boolean nroRepetido=true;
                while (nroRepetido){
                    aux[i]= (int) (Math.random()* nro_max_sorteio + 1);
                    nroRepetido=false;
                    for (int cont=0 ; cont<i; cont++){
                        if (aux[i] == aux[cont]){
                            nroRepetido=true;  
                        }
                    }
                }
            }
        }    
        
        return aux;
    }
    
    /**
     * Método que encontra um número(parâmetro) no conjunto 1
     */
    public boolean encontraNumeroConj1(int nro){
        for (int i=0; i<conjunto1.length; i++){
            if (conjunto1[i] == nro){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método que encontra um número(parâmetro) no conjunto 2
     */
    public boolean encontraNumeroConj2(int nro){
        for (int i=0; i<conjunto2.length; i++){
            if (conjunto2[i] == nro){
                return true;
            }
        }
        return false;    
    }
    
    /*
     * Método auxiliar que retorna os conjuntos da teleSena
     */
    public String getConjuntos(){
        String retorno = "\nConjunto 1: ";
        for (int i=0; i<nros_conjunto; i++){
            if (conjunto1[i] <10) retorno += "0";
            retorno += conjunto1[i]+", ";    
        }
        retorno += "\nConjunto 2: ";
        for (int i=0; i<nros_conjunto; i++){
            if (conjunto2[i] <10) retorno += "0";
            retorno += conjunto2[i]+", ";    
        }
        return retorno;    
    }
}
