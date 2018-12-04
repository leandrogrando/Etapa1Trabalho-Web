package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {

    private CursoDAO<Curso> dao;
    private Curso objeto;
    private InstituicaoDAO<Instituicao> daoInstituicao;
    private Disciplina disciplina;
    private DisciplinaDAO<Disciplina> daoDisciplina;
    private Boolean novaDisciplina;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }

    public Boolean getNovaDisciplina() {
        return novaDisciplina;
    }

    public void setNovaDisciplina(Boolean novaDisciplina) {
        this.novaDisciplina = novaDisciplina;
    }

    public ControleCurso(){
        dao = new CursoDAO<>();
        daoInstituicao = new InstituicaoDAO<>();
        daoDisciplina = new DisciplinaDAO<>();
    }
    
    public void novaDisciplina(){
        disciplina = new Disciplina();
        novaDisciplina = true;
    }
    
    public void editarDisciplina(int index){
        disciplina = objeto.getDisciplinas().get(index);
        novaDisciplina = false;
    }
    
    public void salvarDisciplina(){
        if (novaDisciplina){
            objeto.adicionarDisciplina(disciplina);
        }
        Util.mensagemInformacao("Disciplina atualizada com sucesso!");
    }
    
    public void removerDisciplina(int index){
        objeto.removerDisciplina(index);
        Util.mensagemInformacao("Disciplina removida com sucesso!");
    }
    
    public String listar(){
        return "/privado/curso/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Curso();       
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = getDao().persist(objeto);
        } else {
            persistiu = getDao().merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());            
        } else {
            Util.mensagemErro(getDao().getMensagem());            
        }
    }    
    
    public void editar(Integer id){
        try {
            objeto = getDao().localizar(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        objeto = getDao().localizar(id);
        if (getDao().remover(objeto)){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public void imprimeRelatorio(Integer id) {
        objeto = dao.localizar(id);
        List<Curso> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCursos", parametros, lista);
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    /**
     * @return the dao
     */
    public CursoDAO<Curso> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

}
