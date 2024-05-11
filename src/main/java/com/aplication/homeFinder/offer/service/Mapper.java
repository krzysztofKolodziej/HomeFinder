package com.aplication.homeFinder.offer.service;

import com.aplication.homeFinder.offer.model.Offer;
import com.aplication.homeFinder.offer.model.OfferDetails;
import com.aplication.homeFinder.offer.service.dto.OfferDetailsDto;
import com.aplication.homeFinder.offer.service.dto.OfferDto;
import com.aplication.homeFinder.offer.service.dto.OfferRequest;

public class Mapper {
    public Offer mapOffer(OfferRequest offerRequest, Long id) {
            OfferDto offerDto = offerRequest.getOfferDto();
            OfferDetailsDto offerDetailsDto = offerRequest.getOfferDetailsDto();
            if (offerDetailsDto == null && offerDto == null) {
                return null;
            }
            Offer offer = Offer.builder()
                    .id(id)
                    .kindOfProperty(offerDto.getKindOfProperty())
                    .price(offerDto.getPrice())
                    .title(offerDto.getTitle())
                    .location(offerDto.getLocation())
                    .numberOfRooms(offerDto.getNumberOfRooms())
                    .area(offerDto.getArea())
                    .pricePerMeter(offerDto.getPricePerMeter())
                    .floor(offerDto.getFloor())
                    .description(offerDto.getDescription())
                    .build();
            offer.addDetails(mapOfferDetails(offerDetailsDto,id));
            return offer;
        }

        private OfferDetails mapOfferDetails(OfferDetailsDto offerDetailsDto, Long id) {
            OfferDetails.AdditionalInformation additionalInformation = new OfferDetails.AdditionalInformation();
            additionalInformation.setMarket(offerDetailsDto.getMarket());
            additionalInformation.setAnnouncerType(offerDetailsDto.getAnnouncerType());
            additionalInformation.setYearOfConstruction(offerDetailsDto.getYearOfConstruction());
            additionalInformation.setBuildingType(offerDetailsDto.getBuildingType());
            additionalInformation.setMedia(offerDetailsDto.getMedia());
            additionalInformation.setEquipment(offerDetailsDto.getEquipment());

            OfferDetails offerDetails = new OfferDetails();
            offerDetails.setId(id);
            offerDetails.setRent(offerDetailsDto.getRent());
            offerDetails.setOwnerShipForm(offerDetailsDto.getOwnershipForm());
            offerDetails.setFinishLevel(offerDetailsDto.getFinishLevel());
            offerDetails.setParkingPlace(offerDetailsDto.getParkingPlace());
            offerDetails.setHeating(offerDetailsDto.getHeating());
            offerDetails.setContactDetails(offerDetailsDto.getContactDetails());
            offerDetails.setAdditionalInformation(additionalInformation);

            return offerDetails;
        }

        public OfferDto mapOfferDto(Offer offer) {
            return OfferDto.builder()
                    .kindOfProperty(offer.getKindOfProperty())
                    .price(offer.getPrice())
                    .title(offer.getTitle())
                    .location(offer.getLocation())
                    .numberOfRooms(offer.getNumberOfRooms())
                    .area(offer.getArea())
                    .pricePerMeter(offer.getPricePerMeter())
                    .floor(offer.getFloor())
                    .description(offer.getDescription())
                    .build();
        }

        public OfferDto mapOfferWithDetailsDto(Offer offer, OfferDetails offerDetails) {
            return OfferDto.builder()
                    .kindOfProperty(offer.getKindOfProperty())
                    .price(offer.getPrice())
                    .title(offer.getTitle())
                    .location(offer.getLocation())
                    .numberOfRooms(offer.getNumberOfRooms())
                    .area(offer.getArea())
                    .pricePerMeter(offer.getPricePerMeter())
                    .floor(offer.getFloor())
                    .description(offer.getDescription())
                    .offerDetailsDto(mapOfferDetailsDto(offerDetails))
                    .build();
        }

        private OfferDetailsDto mapOfferDetailsDto(OfferDetails offerDetails) {
            return OfferDetailsDto.builder()
                    .rent(offerDetails.getRent())
                    .ownershipForm(offerDetails.getOwnerShipForm())
                    .finishLevel(offerDetails.getFinishLevel())
                    .parkingPlace(offerDetails.getParkingPlace())
                    .heating(offerDetails.getHeating())
                    .contactDetails(offerDetails.getContactDetails())
                    .market(offerDetails.getAdditionalInformation().getMarket())
                    .announcerType(offerDetails.getAdditionalInformation().getAnnouncerType())
                    .yearOfConstruction(offerDetails.getAdditionalInformation().getYearOfConstruction())
                    .buildingType(offerDetails.getAdditionalInformation().getBuildingType())
                    .media(offerDetails.getAdditionalInformation().getMedia())
                    .equipment(offerDetails.getAdditionalInformation().getEquipment())
                    .build();
        }

}