package com.multicampus.matchcode.model.request.hyem;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.TeamMemberDTO;


public interface TeamMemberInfo {
    TeamMemberDTO getTeamMemberDTO();
    MemberDTO getMemberDTO();
}
