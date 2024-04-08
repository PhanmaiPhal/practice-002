package org.example.ide2markingapi.base;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedError<T> {

    //Request Entity Too Large , Bed Request ,......
    //7003
    private String code;
    //Detail error for user
    private T description;
}
