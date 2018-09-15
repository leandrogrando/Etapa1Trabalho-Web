package br.edu.ifsul.dao;
import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class InstituicaoDAO implements Serializable {

    private String mensagem = "";
    private EntityManager em;
    
    public InstituicaoDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Instituicao> getLista(){
        return em.createQuery("from Instituicao order by nome").getResultList();
    }
    
    public boolean salvar(Instituicao obj){
        try {
            em.getTransaction().begin();
            if (obj.getId() == null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Instituicao obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover: " + Util.getMensagemErro(e);
            return false;
        }
    }    
    
    public Instituicao localizar(Object id){
        return em.find(Instituicao.class, id);
    }
    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
