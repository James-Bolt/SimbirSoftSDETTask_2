package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilitiesResponse {

    @JsonProperty("ability")
    private Ability ability;
    @JsonProperty("is_hidden")
    private boolean isHidden;
    @JsonProperty("slot")
    private int slot;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ability {
        private String name;
        private String url;
    }
}