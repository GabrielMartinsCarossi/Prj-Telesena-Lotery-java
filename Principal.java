public class Principal{
    public static void main(String[] args){
        ControleTeleSena CT = new ControleTeleSena();
        CT.criaPessoas();
        CT.criaTelesenas();
        CT.vendeTeleSenas();
        CT.sorteio();
        CT.imprimeInfo();
    }
}