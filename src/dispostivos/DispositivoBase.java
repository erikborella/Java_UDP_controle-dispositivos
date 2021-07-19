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
public class DispositivoBase {    
    public String local;
    public TipoStatus status;
    
    public DispositivoBase(String local) {
        this.local = local;
        this.status = TipoStatus.off;
    }

    @Override
    public String toString() {
        return "DispositivoBase{" + "local=" + local + ", status=" + status + '}';
    }
    
    
}
