package com.example.personal_api.repository;

import com.example.personal_api.dto.AlunoListaResponse;
import com.example.personal_api.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("""
                select new com.example.personal_api.dto.AlunoListaResponse(
                    a.id,
                    a.nome,
                    a.objetivo,
                    a.pesoAtual,
                    max(av.dataAvaliacao),
                    0
                )
                from Aluno a
                left join Avaliacao av on av.aluno.id = a.id
                where a.personal.id = :personalId
                  and (
                    :search is null
                    or :search = ''
                    or lower(a.nome) like lower(concat('%', :search, '%'))
                  )
                group by a.id, a.nome, a.objetivo, a.pesoAtual
                order by
                   case
                       when max(av.dataAvaliacao) is null then 1
                       else 0
                   end,
                   max(av.dataAvaliacao) asc
                
            """)
    Page<AlunoListaResponse> listarAlunosComUltimaAvaliacao(
            @Param("personalId") Long personalId,
            Pageable pageable,
            @Param("search") String search
    );

}
