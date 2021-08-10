public class Pessoa{
    //Atributos
    private String nome;
    private TeleSena[] teleSenas;
    private double valorPremio;
    
    //Métodos
    
    /**
     * Método construtor
     */
    public Pessoa(String nome, int qntdTeleSenas){
        teleSenas= new TeleSena[qntdTeleSenas];    
        this.nome=nome;
        valorPremio=0;
    }
    
    //GET
    
    public String getNome(){
        return nome;
    }
    
    public TeleSena[] getTeleSenas(){
        return teleSenas;
    }
    
    public double getValorPremio(){
        return valorPremio;
    }
    
    //SET 
    
    public void setNome(String nome){
        this.nome=nome;
    }
    
    public void setTeleSenas(TeleSena[] teleSenas){
        this.teleSenas= teleSenas;
    }
    
    public void setValorPremio(double valorPremio){
        this.valorPremio= valorPremio;
    }
    
    public String toString(){
        return " Nome: "+nome+" | Prêmio: "+valorPremio;
    }
    
    /**
     * Método que insere uma TeleSena no array 
     */
    public boolean insereTeleSena(TeleSena ts){
        for (int i=0; i<teleSenas.length; i++){
            if (teleSenas[i] == null){
                teleSenas[i]= ts;
                return true;
            }
        }
        return false;
    }
}