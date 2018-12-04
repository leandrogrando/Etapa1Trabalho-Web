package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class InstituicaoDAO<TIPO> extends DAOGenerico<Instituicao> implements Serializable {
    
    public InstituicaoDAO(){
        super();
        classePersistente = Instituicao.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
