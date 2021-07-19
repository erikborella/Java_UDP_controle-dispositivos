/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispostivos;

/**
 *
 * @author erik0
 */
public enum TipoStatus {
    on(true), 
    off(false);
    
    private final boolean booleanStatus;
    
    private TipoStatus(boolean status) {
        this.booleanStatus = status;
    }
    
    public boolean getValorBooleano() {
        return this.booleanStatus;
    }
}
