package jfs.backend.Modules;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponce {

    private String token;
    private String username;
}
