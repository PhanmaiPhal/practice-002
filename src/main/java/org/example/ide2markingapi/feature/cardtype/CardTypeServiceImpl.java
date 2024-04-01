package org.example.ide2markingapi.feature.cardtype;

import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.domain.Card;
import org.example.ide2markingapi.domain.CardType;
import org.example.ide2markingapi.feature.cardtype.dto.CardTypeResponse;
import org.example.ide2markingapi.mapper.CardTypeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Override
    public List<CardTypeResponse> findAll() {

        List<CardType> cardTypes = cardTypeRepository.findAll();

        return cardTypeMapper.toListCardTypeResponse(cardTypes);
    }

    @Override
    public CardTypeResponse findByName(String name) {

        CardType cardType = cardTypeRepository.findByNameIgnoreCase(name)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "CardType does not exist!"
                ));

        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
