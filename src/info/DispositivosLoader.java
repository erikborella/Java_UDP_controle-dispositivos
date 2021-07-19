/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import dispostivos.DispositivoBase;


/**
 *
 * @author erik0
 */
public class DispositivosLoader {
    private static String[] localDispositivos = {
        "luz_guarita",
        "ar_guarita",
        "luz_estacionamento",
        "luz_galpao_externo",
        "luz_galpao_interno",
        "luz_escritorios",
        "ar_escritorios",
        "luz_sala_reunioes",
        "ar_sala_reunioes"
    };

    public static DispositivoBase[] carregarDispositivos() {
        DispositivoBase[] dispositivos = new DispositivoBase[localDispositivos.length];
        
        for (int i = 0; i < localDispositivos.length; i++) {
            dispositivos[i] = new DispositivoBase(localDispositivos[i]);
        }
        
        return dispositivos;
    }
    
    public static String[] getLocaisDisponiveis() {
        return localDispositivos;
    }
}
