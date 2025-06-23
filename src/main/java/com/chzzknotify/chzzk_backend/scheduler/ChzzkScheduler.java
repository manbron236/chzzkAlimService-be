package com.chzzknotify.chzzk_backend.scheduler;

import com.chzzknotify.chzzk_backend.config.FavoriteStreamerProvider;
import com.chzzknotify.chzzk_backend.model.LiveInfo;
import com.chzzknotify.chzzk_backend.model.Streamer;
import com.chzzknotify.chzzk_backend.service.ChzzkService;
import com.chzzknotify.chzzk_backend.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChzzkScheduler {

    private final ChzzkService chzzkService;
    private final DiscordService discordService;
    private final FavoriteStreamerProvider streamerProvider;

    @Scheduled(fixedRate = 60000)
    public void checkStreamersLiveStatus() {
        List<Streamer> favoriteStreamers = streamerProvider.getFavoriteStreamers();
        List<LiveInfo> liveList = chzzkService.getLiveStreamers(favoriteStreamers);

        for (LiveInfo liveInfo : liveList) {
            discordService.sendLiveAlert(liveInfo);
        }
    }

}
