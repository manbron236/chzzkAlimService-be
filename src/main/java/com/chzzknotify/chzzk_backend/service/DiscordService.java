package com.chzzknotify.chzzk_backend.service;

import com.chzzknotify.chzzk_backend.model.LiveInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DiscordService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${discord.webhook.url}")
    private String webhookUrl;

    private final Set<String> alreadyNotified = new HashSet<>();

    public void sendLiveAlert(LiveInfo liveInfo) {
        if (alreadyNotified.contains(liveInfo.getStreamerId())) {
            return; // 중복 전송 방지
        }

        String content = "🎥 " + liveInfo.getStreamerName() + "님이 방송을 시작했습니다!\n" + liveInfo.getLiveUrl();

        try {
            // JSON 직렬화로 Escape 문제 방지
            Map<String, String> body = Map.of("content", content);
            String payload = objectMapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                alreadyNotified.add(liveInfo.getStreamerId());
            }
        } catch (Exception e) {
            System.out.println("🚨 디스코드 알림 전송 실패: " + e.getMessage());
        }
    }

    public Set<String> getAlreadyNotified() {
        return alreadyNotified;
    }

    public void resetNotifiedList() {
        alreadyNotified.clear();
    }
}
