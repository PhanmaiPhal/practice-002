package org.example.ide2markingapi.mapper;


import org.example.ide2markingapi.domain.CardType;
import org.example.ide2markingapi.feature.cardtype.dto.CardTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {


    CardType fromCardTypeResponse(CardTypeResponse cardTypeResponse);

    CardTypeResponse toCardTypeResponse(CardType cardType);

    List<CardTypeResponse> toListCardTypeResponse(List<CardType> cardTypes);
}
