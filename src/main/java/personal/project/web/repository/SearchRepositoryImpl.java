package personal.project.web.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.project.domain.dto.SearchCondition;
import personal.project.domain.dto.get.QReturnProjectDto;
import personal.project.domain.dto.get.ReturnProjectDto;

import java.util.List;

import static personal.project.domain.entity.QProject.project;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReturnProjectDto> searchByCondition(SearchCondition condition) {
        return queryFactory.select(
                new QReturnProjectDto(project)).from(project)
                .where(
                        combineMethod(condition.query, condition.sort)
                ).fetch();
    }

    /** Where 다중 파라미터 사용
     * 조립이 가능하고 재사용성이 있다는 장점이 있다.
     * */
    private BooleanExpression queryContain(String query) {
        return query != null ? project.title.contains(query) : null;
    }

    private BooleanExpression sortGoe(Integer sort) {
        return sort != null ? project.likeCount.goe(sort) : null;
    }

    private Predicate combineMethod(String query, Integer sort) {
        if (query == null && sort == null) {
            return null;
        } else if(query == null && sort != null) {
            return sortGoe(sort);
        } else if(query != null && sort == null) {
            return queryContain(query);
        } else {
            return queryContain(query).and(sortGoe(sort));
        }
    }
}
