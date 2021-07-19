/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispostivos;

import org.json.JSONObject;

/**
 *
 * @author erik0
 */
public class ControladorDispositivosJson {
    private GerenciadorDispositivos gerenciadorDispositivos;
    
    public ControladorDispositivosJson(GerenciadorDispositivos gerenciadorDispositivos) {
        this.gerenciadorDispositivos = gerenciadorDispositivos;
    }
    
    public String executar(String jsonParaProcessar) {
        JSONObject json = new JSONObject(jsonParaProcessar);
        
        if (!json.has("command"))
            return pegarJsonCampoErro("command");
        
        String comando = json.getString("command");
        
        switch (comando) {
            case "get":
                return executarComandoGet(json);
                
            case "set":
                return executarComandoSet(json);
                
            default:
                return pegarJsonNaoValido("Comando", comando);
        }
    }
    
    private String executarComandoGet(JSONObject json) {
        if (!json.has("locate"))
            return pegarJsonCampoErro("locate");
        
        String locate = json.getString("locate");
        
        DispositivoBase dis = gerenciadorDispositivos.get(locate);
        
        if (dis == null)
            return pegarJsonNaoValido("locate", locate);
        else
            return pegarJsonRetorno(dis);
    }
    
    private String executarComandoSet(JSONObject json) {
        if (!json.has("locate"))
            return pegarJsonCampoErro("locate");
        
        if (!json.has("value"))
            return pegarJsonCampoErro("value");
        
        String locate = json.getString("locate");
        String value = json.getString("value");
        
        if (!valueEhValido(value))
            return pegarJsonNaoValido("value", value);
        
        TipoStatus novoStatus = pegarStatus(value);
        
        DispositivoBase dis = gerenciadorDispositivos.set(locate, novoStatus);
        
        if (dis == null)
            return pegarJsonNaoValido("locate", locate);
        else
            return pegarJsonRetorno(dis);
    }
                
    private String pegarJsonErro(String motivo) {
        JSONObject jsonErro = new JSONObject();
        
        jsonErro.put("erro", motivo);
        
        return jsonErro.toString();
    }
    
    private String pegarJsonCampoErro(String nomeCampo) {    
        return pegarJsonErro(
                String.format("Campo \"%s\" não encontrado", 
                        nomeCampo)
        );
    }
    
    private String pegarJsonNaoValido(String substantivo, String objeto) {
        return pegarJsonErro(
                String.format("%s \"%s\" não é valido", 
                substantivo, objeto)
        );
    }
    
    private String pegarJsonRetorno(DispositivoBase dispositivo) {
        JSONObject json = new JSONObject();
        
        json.put("locate", dispositivo.local);
        json.put("status", dispositivo.status.toString());
        
        return json.toString();
    }
   
    private boolean valueEhValido(String value) {
        return (value.equals("on") || value.equals("off"));
    }
    
    private TipoStatus pegarStatus(String value) {
        if (value.equals("on"))
            return TipoStatus.on;
        else
            return TipoStatus.off;
    }
}
