package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface MainConfig extends Config {
    String baseUri();
}
