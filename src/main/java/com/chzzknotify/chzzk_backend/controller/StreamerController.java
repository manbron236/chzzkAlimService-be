package com.chzzknotify.chzzk_backend.controller;

import com.chzzknotify.chzzk_backend.config.FavoriteStreamerProvider;
import com.chzzknotify.chzzk_backend.model.LiveInfo;
import com.chzzknotify.chzzk_backend.model.Streamer;
import com.chzzknotify.chzzk_backend.service.ChzzkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/streamers")
@RequiredArgsConstructor

public class StreamerController {
    private final ChzzkService chzzkService;
    private final FavoriteStreamerProvider streamerProvider;

    /**
     * 방송 중인 스트리머 리스트 반환 API
     */
    @GetMapping("/live")
    public List<LiveInfo> getLiveStreamers() {
        return chzzkService.getLiveStreamers(streamerProvider.getFavoriteStreamers());
    }
}
