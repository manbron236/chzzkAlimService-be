package com.chzzknotify.chzzk_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveInfo {
    private String streamerId;
    private String streamerName;
    private String thumbnailUrl;
    private String liveUrl;
    private String liveTitle;
    private List<String> tags;
    private String category;
    private boolean isLive;
}
