package personal.project.web.repository;

import org.springframework.stereotype.Repository;
import personal.project.domain.dto.SearchCondition;
import personal.project.domain.dto.get.ReturnProjectDto;
import java.util.List;

@Repository
public interface SearchRepository {

    List<ReturnProjectDto> searchByCondition(SearchCondition condition);
}
