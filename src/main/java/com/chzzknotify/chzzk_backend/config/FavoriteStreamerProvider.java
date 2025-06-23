package com.chzzknotify.chzzk_backend.config;

import com.chzzknotify.chzzk_backend.model.Streamer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FavoriteStreamerProvider {

    public List<Streamer> getFavoriteStreamers() {
        return Arrays.asList(
                new Streamer("2086f44c7b09a17cef6786f21389db3b", "지누"),
                new Streamer("a7e175625fdea5a7d98428302b7aa57f", "탬탬버린")
//                new Streamer("다른ID", "또다른 스트리머")
        );
    }
}
