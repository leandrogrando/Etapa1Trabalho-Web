package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleInstituicao")
@ViewScoped
public class ControleInstituicao implements Serializable {

    private InstituicaoDAO<Instituicao> dao;
    private Instituicao objeto;
    private InstituicaoDAO<Instituicao> daoInstituicao;

    public ControleInstituicao(){
        dao = new InstituicaoDAO<>();
        daoInstituicao = new InstituicaoDAO<>();
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Instituicao();       
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }    
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));            
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



    public void setDao(InstituicaoDAO dao) {
        this.dao = dao;
    }
    
    public InstituicaoDAO<Instituicao> getDao() {
        return dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

}
