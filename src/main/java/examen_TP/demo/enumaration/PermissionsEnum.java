package examen_TP.demo.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionsEnum {
    PRINCIPAL_READ("principal:read");
    @Getter
    private final String permission;
}
