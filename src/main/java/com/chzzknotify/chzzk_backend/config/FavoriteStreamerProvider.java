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
                new Streamer("a7e175625fdea5a7d98428302b7aa57f", "탬탬버린"),
                new Streamer("17f0cfcba4ff608de5eabb5110d134d0", "김뿡"),
                new Streamer("d6fc3283fe0938bca8d65093e4c2bb94", "실프"),
                new Streamer("0b33823ac81de48d5b78a38cdbc0ab94", "울프"),
                new Streamer("a41ae3dc06b2620ea68897f2bea9d67d", "유잼 호랑이 9884025(김뚜띠 부계정)"),
                new Streamer("2eee29ce69664154d8bc478825941259", "김뚜띠"),
                new Streamer("458f6ec20b034f49e0fc6d03921646d2", "서새봄"),
                new Streamer("80b36a0ae8e887e893ce0014dbfece4a", "나나양")
//                new Streamer("다른ID", "또다른 스트리머")
        );
    }
}
