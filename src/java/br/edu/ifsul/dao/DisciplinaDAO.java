package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Disciplina;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class DisciplinaDAO<TIPO> extends DAOGenerico<Disciplina> implements Serializable {
    
    public DisciplinaDAO(){
        super();
        classePersistente = Disciplina.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
