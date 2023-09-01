package com.multicampus.matchcode.service.hyuk;

import com.multicampus.matchcode.model.request.hyuk.Match;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.request.hyuk.MatchData;
import com.multicampus.matchcode.repository.MatchRepository;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MatchService {

    //matchRepository 객체 생성
    private MatchRepository matchRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수

    // Entity -> DTO로 변환
    private MatchDTO convertEntityToDTO(Match match) {
        return MatchDTO.builder()
                       .id(match.getId())
                       .mapId(match.getMapId())
                       .sportsId(match.getSportsId())
                       .matchDate(match.getMatchDate())
                       .createdDate(match.getCreatedDate())
                       .expireDate(match.getExpireDate())
                       .restrictionMinRate(match.getRestrictionMinRate())
                       .restrictionMaxRate(match.getRestrictionMaxRate())
                       .status(match.getStatus())
                       .build();
    }

    @Transactional
    public ArrayList<MatchDTO> getMatchlist(Integer pageNum) {
        Page<MatchDTO> page = matchRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<MatchDTO> matchEntities = page.getContent();
        ArrayList<MatchDTO> matchDTOs = new ArrayList<>();
        matchDTOs.addAll(matchEntities);

        return matchDTOs;
    }

    @Transactional
    public MatchDTO getPost(Long id) {
        // Optional : NPE(NullPointerException) 방지
        Optional<MatchDTO> matchWrapper = matchRepository.findById(id);

        return matchWrapper.get();
    }

    @Transactional
    public Long savePost(MatchData data) {
        MatchDTO matchDTO = MatchDTO.builder()
                                    .mapId(data.getMapId())
                                    .sportsId(data.getSportsId())
                                    .createdDate(new Timestamp(System.currentTimeMillis()))
                                    .build();
        return matchRepository.save(matchDTO)
                              .getId();
    }

    public Long updatePost(long id, MatchData data) {
        MatchDTO matchDTO = MatchDTO.builder()
                                    .id(id)
                                    .mapId(data.getMapId())
                                    .sportsId(data.getSportsId())
                                    .createdDate(new Timestamp(System.currentTimeMillis()))
                                    .build();
        return matchRepository.save(matchDTO)
                              .getId();
    }

    @Transactional
    public void deletePost(Long id) {
        matchRepository.deleteById(id);
    }

    // 검색 API
/*    @Transactional
    public List<MatchDTO> searchPosts(String keyword) {
        List<Match> matchEntities = matchRepository.findByMapIdContaining(keyword);
        List<MatchDTO> matchDTOList = new ArrayList<>();

        if (matchEntities.isEmpty()) return matchDTOList;

        for (Match match : matchEntities) {
            matchDTOList.add(this.convertEntityToDTO(match));
        }

        return matchDTOList;
    }*/

    // 페이징
    @Transactional
    public Long getMatchCount() {
        return matchRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double matchTotalCount = Double.valueOf(this.getMatchCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((matchTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                                   ? curPageNum + BLOCK_PAGE_NUM_COUNT
                                   : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3)
                     ? 1
                     : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    @Transactional
    public List<MatchDTO> getMatchlistByRegionAndSports(Integer pageNum, long region, long sports) {
        Page<MatchDTO> page = matchRepository.findByMapIdAndSportsId(region, sports,
                PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate"))
        );
        return page.getContent();
    }

    @Transactional
    public Integer[] getPageListByRegionAndSports(Integer pageNum, long region, long sports) {
        Double matchTotalCount = Double.valueOf(matchRepository.countByMapIdAndSportsId(region, sports));
        return calculatePageList(pageNum, matchTotalCount);
    }

    private Integer[] calculatePageList(Integer pageNum, Double totalCount) {
        int totalLastPageNum = (int) (Math.ceil(totalCount / PAGE_POST_COUNT));
        int blockLastPageNum = (totalLastPageNum > pageNum + BLOCK_PAGE_NUM_COUNT)
                               ? pageNum + BLOCK_PAGE_NUM_COUNT
                               : totalLastPageNum;

        int curPageNum = (pageNum <= 3)
                         ? 1
                         : pageNum - 2;

        Integer[] pageList = new Integer[blockLastPageNum - curPageNum + 1];
        for (int i = curPageNum, idx = 0; i <= blockLastPageNum; i++, idx++) {
            pageList[idx] = i;
        }

        return pageList;
    }
}
