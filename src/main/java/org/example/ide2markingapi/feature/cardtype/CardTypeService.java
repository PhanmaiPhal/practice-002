package org.example.ide2markingapi.feature.cardtype;


import org.example.ide2markingapi.feature.cardtype.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {

    List<CardTypeResponse> findAll();

    CardTypeResponse findByName(String name);

}
