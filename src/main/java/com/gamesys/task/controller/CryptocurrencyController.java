package com.gamesys.task.controller;

import com.gamesys.task.integration.model.Cryptocurrency;
import com.gamesys.task.service.CryptocurrencyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@RequestMapping(path="/cryptocurrencies")
@Controller
public class CryptocurrencyController {

    private final CryptocurrencyServiceImpl cryptocurrencyService;

    @GetMapping(path ="")
    public ResponseEntity<List<Cryptocurrency>> getCryptocurrencies() {
        return new ResponseEntity<List<Cryptocurrency>>(cryptocurrencyService.getCryptocurrencies(), HttpStatus.OK);
    }

}
