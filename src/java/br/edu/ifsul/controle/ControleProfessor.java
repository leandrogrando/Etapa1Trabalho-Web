package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleProfessor")
@SessionScoped
public class ControleProfessor implements Serializable {

    private ProfessorDAO dao;
    private Professor objeto;
    private EspecialidadeDAO daoEspecialidade;
    
    public ControleProfessor(){
        dao = new ProfessorDAO();
        daoEspecialidade = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/professor/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Professor();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if (dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ProfessorDAO getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
