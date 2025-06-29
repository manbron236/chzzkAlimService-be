package com.chzzknotify.chzzk_backend.scheduler;

import com.chzzknotify.chzzk_backend.config.FavoriteStreamerProvider;
import com.chzzknotify.chzzk_backend.model.LiveInfo;
import com.chzzknotify.chzzk_backend.model.Streamer;
import com.chzzknotify.chzzk_backend.service.DiscordService;
import com.chzzknotify.chzzk_backend.service.ChzzkService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LiveCheckerService {

    private final ChzzkService chzzkService;
    private final DiscordService discordService;
    private final FavoriteStreamerProvider favoriteStreamerProvider;

    @Scheduled(fixedDelay = 60000) // 60초마다 검사
    public void checkLiveStatus() {
        List<Streamer> streamers = favoriteStreamerProvider.getFavoriteStreamers();
        List<LiveInfo> liveList = chzzkService.getLiveStreamers(streamers);

        for (LiveInfo liveInfo : liveList) {
            discordService.sendLiveAlert(liveInfo);
        }
    }
}

