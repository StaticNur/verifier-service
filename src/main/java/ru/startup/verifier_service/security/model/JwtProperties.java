package ru.startup.verifier_service.security.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.startup.verifier_service.util.YamlPropertiesUtilFactory;

/**
 * Represents properties used for JWT token generation and validation.
 * <p>
 * This class contains the secret key used for token signing, as well as the expiration
 * times for access and refresh tokens.
 * </p>
 *
 * @since 1.0
 */
@Component
@Data
@PropertySource(value = "classpath:application.yml", factory = YamlPropertiesUtilFactory.class)
public class JwtProperties {
    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${spring.jwt.access}")
    private Long access;
    @Value("${spring.jwt.refresh}")
    private Long refresh;
}
