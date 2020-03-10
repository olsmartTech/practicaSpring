package com.nucleoti.searching.core.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;

    private String name;

    private String phoneNumber;

    private String preferredEmail;

    private String pictureUrl;

    private boolean enabled;

    private boolean validated;

    private List<String> roles;

}
