package com.chzzknotify.chzzk_backend.service;

import com.chzzknotify.chzzk_backend.model.LiveInfo ;
import com.chzzknotify.chzzk_backend.model.Streamer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChzzkService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${chzzk.client-id}")
    private String clientId;

    public List<LiveInfo> getLiveStreamers(List<Streamer> streamers) {
        List<LiveInfo> liveList = new ArrayList<>();

        for (Streamer streamer : streamers) {
            try {
                String url = "https://api.chzzk.naver.com/service/v3.2/channels/" + streamer.getStreamerId() + "/live-detail";

                HttpHeaders headers = new HttpHeaders();
                headers.set("Client-Id", clientId);
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    String body = response.getBody();
//                    System.out.println("▶ 응답 원문: " + body);

                    JsonNode root = objectMapper.readTree(body);
                    JsonNode content = root.path("content");

                    if (content.isMissingNode() || content.isNull()) {
                        System.out.println("⚠️ 'content' 필드가 없습니다: " + streamer.getStreamerName());
                        continue;
                    }

                    String status = content.path("status").asText();
//                    System.out.println("▶ 스트리머: " + streamer.getStreamerName() + ", 상태: " + status);

                    if ("OPEN".equals(status)) {
                        String rawThumbnail = content.path("liveImageUrl").asText();
                        String thumbnail = rawThumbnail.replace("{type}", "480");
                        String link = "https://chzzk.naver.com/live/" + streamer.getStreamerId();

                        String liveTitle = content.path("liveTitle").asText();
                        String category = content.path("liveCategoryValue").asText();
                        int concurrentUserCount = content.path("concurrentUserCount").asInt();


                        List<String> tags = new ArrayList<>();
                        if (content.has("tags") && content.get("tags").isArray()) {
                            for (JsonNode tag : content.get("tags")) {
                                tags.add(tag.asText());
                            }
                        }

                        liveList.add(new LiveInfo(
                                streamer.getStreamerId(),
                                streamer.getStreamerName(),
                                thumbnail,
                                link,
                                liveTitle,
                                tags,
                                category,
                                concurrentUserCount,
                        true
                        ));
                    }
                }

            } catch (Exception e) {
                System.out.println("❗ 방송 상태 확인 중 오류 발생: " + streamer.getStreamerName() + " (" + streamer.getStreamerId() + ")");
                e.printStackTrace(); // 디버깅 시 반드시 필요
            }
        }

        return liveList;
    }
}
