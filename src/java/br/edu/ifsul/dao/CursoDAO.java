package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - CamPpus Passo Fundo
 */
public class CursoDAO<TIPO> extends DAOGenerico<Curso> implements Serializable {
    
    public CursoDAO(){
        super();
        classePersistente = Curso.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
