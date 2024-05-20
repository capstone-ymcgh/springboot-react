package com.capstone.capstone_project.dto.request.recipeboard;


/*
 * 검색 요청에 대한 DTO , 검색 조건이 늘어날 수도 있기 때문에 객체로 생성.
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecSearchData {

    String title;
    String content;
    String writerName;

    @Builder
    public RecSearchData(String title, String content, String writerName) {
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }

    public static RecSearchData createdSearchData(String title, String content, String writerName) {
        return RecSearchData.builder()
                .title(title)
                .content(content)
                .writerName(writerName)
                .build();
    }

}
