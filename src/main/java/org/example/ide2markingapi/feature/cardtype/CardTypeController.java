package org.example.ide2markingapi.feature.cardtype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ide2markingapi.feature.cardtype.dto.CardTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/card-types")
@RequiredArgsConstructor
@Slf4j
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @GetMapping

    public List<CardTypeResponse> findCardTypes(){

        List<CardTypeResponse> cardTypeResponses = cardTypeService.findAll();
        return cardTypeResponses;
    }

    @GetMapping("/{name}")
    public CardTypeResponse findCardTypeByName(@PathVariable String name){
        return cardTypeService.findByName(name);
    }

}