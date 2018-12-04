package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class AlunoDAO<TIPO> extends DAOGenerico<Aluno> implements Serializable {
    
    public AlunoDAO(){
        super();
        classePersistente = Aluno.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
