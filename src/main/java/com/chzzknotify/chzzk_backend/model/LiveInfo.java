package com.chzzknotify.chzzk_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveInfo {
    private String streamerId;
    private String streamerName;
    private String thumbnailUrl;
    private String liveUrl;
    private boolean isLive;
}
