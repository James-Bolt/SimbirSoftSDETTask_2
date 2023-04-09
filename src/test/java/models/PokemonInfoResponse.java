package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import lombok.Data;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonInfoResponse {
    private List<Ability> abilities;
    private int weight;

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ability {
        private AbilityDetails ability;
        @JsonProperty("is_hidden")
        private boolean hidden;
        private int slot;

        @Data
        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class AbilityDetails {
            private String name;
            private String url;
        }
    }
}