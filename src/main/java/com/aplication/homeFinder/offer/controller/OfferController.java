package com.aplication.homeFinder.offer.controller;

import com.aplication.homeFinder.ErrorRespond;
import com.aplication.homeFinder.offer.service.FilteringSchema;
import com.aplication.homeFinder.offer.service.dto.ClientMessageDto;
import com.aplication.homeFinder.offer.service.dto.OfferDto;
import com.aplication.homeFinder.offer.service.OfferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/api/offers/{currency}/all")
    public ResponseEntity<?> findAllOffer(@ModelAttribute FilteringSchema filteringSchema, @PathVariable String currency) {
        try {
            List<OfferDto> offers = offerService.findOffers(filteringSchema, currency);
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<?> findOfferWithDetails(@PathVariable @Valid Long id) {
        try {
            OfferDto offerDto = offerService.findOfferWithDetails(id);
            return ResponseEntity.status(HttpStatus.OK).body(offerDto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> safeOffer(@RequestBody @Valid OfferDto offerDto) {
        try {
            OfferDto offerDtoMap = offerService.saveOffer(offerDto);
            return ResponseEntity.status(HttpStatus.OK).body(offerDtoMap);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<?> updateOffer(@RequestBody @Valid OfferDto offerDto, @PathVariable Long id) {
        try {
            OfferDto offerDtoMap = offerService.updateOffer(offerDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(offerDtoMap);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable @Valid Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("offers/{id}/message")
    public ResponseEntity<?> saveMessage(@RequestBody @Valid ClientMessageDto clientMessageDto, @PathVariable Long id) {
        try {
            offerService.addMessage(clientMessageDto, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(new ErrorRespond(e.getStatusCode(), e.getMessage()), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
