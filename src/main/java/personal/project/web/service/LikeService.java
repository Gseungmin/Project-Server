package personal.project.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.domain.entity.Likes;
import personal.project.web.repository.LikeRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    /**좋아요 조회*/
    public Optional<Likes> findLikes(Long memberId, Long projectId) {
        return likeRepository.findByMemIdAndProId(memberId, projectId);
    }

    /**좋아요 저장*/
    public void save(Likes likes) {
        likeRepository.save(likes);
    }
}



