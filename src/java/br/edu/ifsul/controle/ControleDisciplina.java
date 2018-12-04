package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
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
@ManagedBean(name = "controleDisciplina")
@ViewScoped
public class ControleDisciplina implements Serializable {

    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;
    private CursoDAO<Curso> daoCurso;
    private Aluno aluno;
    private AlunoDAO<Aluno> daoAluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public CursoDAO<Curso> getDaoCurso() {
        return daoCurso;
    }

    public void setDaoCurso(CursoDAO<Curso> daoCurso) {
        this.daoCurso = daoCurso;
    }

    public ControleDisciplina(){
        dao = new DisciplinaDAO<>();
        daoCurso = new CursoDAO<>();
        daoAluno = new AlunoDAO<>();
    }
    
    public String listar(){
        return "/privado/disciplina/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Disciplina();       
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



    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the dao
     */
    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }
    
    public void adicionarAluno(){
        if (aluno != null){
            if(!objeto.getInscritosDisciplina().contains(aluno)){
                objeto.getInscritosDisciplina().add(aluno);
                Util.mensagemInformacao("Aluno adicionado com sucesso!");
            } else {
                Util.mensagemErro("Aluno já existe na lista!");
            }
        }
    }
    
    public void removerDesejo(int index){        
        objeto.getInscritosDisciplina().remove(index);
        Util.mensagemInformacao("Aluno removido com sucesso!");
    }

}
