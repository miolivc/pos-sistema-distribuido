
package br.edu.ifpb.pos.entidade;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "mensagem")
@XmlType(propOrder = {"para", "de", "criadoEm", "corpo"})
public class Mensagem {
    
    private String de;
    private String para;
    private String criadoEm;
    private String corpo;

    public Mensagem(String de, String para, LocalDateTime criadoEm, String corpo) {
        this.de = de;
        this.para = para;
        this.criadoEm = criadoEm.toString();
        this.corpo = corpo;
    }

    public Mensagem() {
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(String criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
    
    
    
}
