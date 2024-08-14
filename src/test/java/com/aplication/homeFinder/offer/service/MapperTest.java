package com.aplication.homeFinder.offer.service;

import com.aplication.homeFinder.offer.model.ClientMessage;
import com.aplication.homeFinder.offer.model.Offer;
import com.aplication.homeFinder.offer.model.OfferDetails;
import com.aplication.homeFinder.offer.service.dto.ClientMessageDto;
import com.aplication.homeFinder.offer.service.dto.OfferDetailsDto;
import com.aplication.homeFinder.offer.service.dto.OfferDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MapperTest {
    @InjectMocks
    Mapper mapper;

    final double MID_RATE = 4.2d;

    @Test
    void shouldMapOfferDtoToEditToOffer() {
        //given
        OfferDetailsDto.AdditionalInformation additionalInformationDtoTest = getAdditionalInformationDto();
        OfferDetailsDto offerDetailsDtoTest = getOfferDetailsDto(additionalInformationDtoTest);
        OfferDto offerDtoTest = getOfferDto(offerDetailsDtoTest);
        Offer offerTest = getOfferNewEmpty();

        //when
        Offer offer = mapper.mapOfferEdit(offerTest, offerDtoTest);

        //then
        assertThat(offer.getKindOfProperty()).isEqualTo(offerDtoTest.getKindOfProperty());
        assertThat(offer.getPrice()).isEqualTo(offerDtoTest.getPrice());
        assertThat(offer.getTitle()).isEqualTo(offerDtoTest.getTitle());
        assertThat(offer.getCity()).isEqualTo(offerDtoTest.getCity());
        assertThat(offer.getStreet()).isEqualTo(offerDtoTest.getStreet());
        assertThat(offer.getNumberOfRooms()).isEqualTo(offerDtoTest.getNumberOfRooms());
        assertThat(offer.getArea()).isEqualTo(offerDtoTest.getArea());
        assertThat(offer.getPricePerMeter()).isEqualTo(offerDtoTest.getPricePerMeter());
        assertThat(offer.getFloor()).isEqualTo(offerDtoTest.getFloor());
        assertThat(offer.getDescription()).isEqualTo(offerDtoTest.getDescription());

        OfferDetails offerDetails = offer.getOfferDetails();
        OfferDetailsDto offerDetailsDto = offerDtoTest.getOfferDetailsDto();
        assertThat(offerDetails.getOwnershipForm()).isEqualTo(offerDetailsDto.getOwnershipForm());
        assertThat(offerDetails.getRent()).isEqualTo(offerDetailsDto.getRent());
        assertThat(offerDetails.getFinishLevel()).isEqualTo(offerDetailsDto.getFinishLevel());
        assertThat(offerDetails.getParkingPlace()).isEqualTo(offerDetailsDto.getParkingPlace());
        assertThat(offerDetails.getHeating()).isEqualTo(offerDetailsDto.getHeating());
        assertThat(offerDetails.getContactDetails()).isEqualTo(offerDetailsDto.getContactDetails());

        OfferDetails.AdditionalInformation additionalInformation = offerDetails.getAdditionalInformation();
        OfferDetailsDto.AdditionalInformation additionalInformationDto = offerDetailsDto.getAdditionalInformationDto();
        assertThat(additionalInformation.getMarket()).isEqualTo(additionalInformationDto.getMarket());
        assertThat(additionalInformation.getAnnouncerType()).isEqualTo(additionalInformationDto.getAnnouncerType());
        assertThat(additionalInformation.getYearOfConstruction()).isEqualTo(additionalInformationDto.getYearOfConstruction());
        assertThat(additionalInformation.getBuildingType()).isEqualTo(additionalInformationDto.getBuildingType());
        assertThat(additionalInformation.getMedia()).isEqualTo(additionalInformationDto.getMedia());
        assertThat(additionalInformation.getEquipment()).isEqualTo(additionalInformationDto.getEquipment());
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDoesNotExist() {
        //given
        OfferDto offerDtoTest = getOfferDtoNewEmpty();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(null, offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDtoDoesNotExist() {
        //given
        Offer offerTest = getOfferNewEmpty();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(offerTest, null))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDetailsDtoDoesNotExist() {
        //given
        Offer offerTest = getOfferNewEmpty();
        OfferDto offerDtoTest = new OfferDto();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(offerTest, offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDetailsDoesNotExist() {
        //given
        OfferDto offerDtoTest = getOfferDtoNewEmpty();
        Offer offerTest = new Offer();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(offerTest, offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfAdditionalInformationDoesNotExist() {
        //given
        OfferDto offerDtoTest = getOfferDtoNewEmpty();
        Offer offerTest = new Offer();
        OfferDetails offerDetailsTest = new OfferDetails();
        offerTest.setOfferDetails(offerDetailsTest);

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(offerTest, offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfAdditionalInformationDtoDoesNotExist() {
        //given
        OfferDto offerDtoTest = new OfferDto();
        OfferDetailsDto offerDetailsDtoTest = new OfferDetailsDto();
        offerDtoTest.setOfferDetailsDto(offerDetailsDtoTest);
        Offer offerTest = getOfferNewEmpty();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferEdit(offerTest, offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void validMapOfferEditIsNotNull() {
        //given
        OfferDto offerDtoTest = getOfferDtoNewEmpty();
        Offer offerTest = getOfferNewEmpty();

        //when & then
        Offer offer = mapper.mapOfferEdit(offerTest, offerDtoTest);

        assertThat(offer).isNotNull();
    }

    @Test
    void shouldMapOfferDtoToOffer() {
        //given
        OfferDetailsDto.AdditionalInformation additionalInformationDtoTest = getAdditionalInformationDto();
        OfferDetailsDto offerDetailsDtoTest = getOfferDetailsDto(additionalInformationDtoTest);
        OfferDto offerDtoTest = getOfferDto(offerDetailsDtoTest);

        //when
        Offer offer = mapper.mapOffer(offerDtoTest);

        //then
        assertThat(offer.getKindOfProperty()).isEqualTo(offerDtoTest.getKindOfProperty());
        assertThat(offer.getPrice()).isEqualTo(offerDtoTest.getPrice());
        assertThat(offer.getTitle()).isEqualTo(offerDtoTest.getTitle());
        assertThat(offer.getCity()).isEqualTo(offerDtoTest.getCity());
        assertThat(offer.getStreet()).isEqualTo(offerDtoTest.getStreet());
        assertThat(offer.getNumberOfRooms()).isEqualTo(offerDtoTest.getNumberOfRooms());
        assertThat(offer.getArea()).isEqualTo(offerDtoTest.getArea());
        assertThat(offer.getPricePerMeter()).isEqualTo(offerDtoTest.getPricePerMeter());
        assertThat(offer.getFloor()).isEqualTo(offerDtoTest.getFloor());
        assertThat(offer.getDescription()).isEqualTo(offerDtoTest.getDescription());

        OfferDetails offerDetails = offer.getOfferDetails();
        OfferDetailsDto offerDetailsDto = offerDtoTest.getOfferDetailsDto();
        assertThat(offerDetails.getOwnershipForm()).isEqualTo(offerDetailsDto.getOwnershipForm());
        assertThat(offerDetails.getRent()).isEqualTo(offerDetailsDto.getRent());
        assertThat(offerDetails.getFinishLevel()).isEqualTo(offerDetailsDto.getFinishLevel());
        assertThat(offerDetails.getParkingPlace()).isEqualTo(offerDetailsDto.getParkingPlace());
        assertThat(offerDetails.getHeating()).isEqualTo(offerDetailsDto.getHeating());
        assertThat(offerDetails.getContactDetails()).isEqualTo(offerDetailsDto.getContactDetails());

        OfferDetails.AdditionalInformation additionalInformation = offerDetails.getAdditionalInformation();
        OfferDetailsDto.AdditionalInformation additionalInformationDto = offerDetailsDto.getAdditionalInformationDto();
        assertThat(additionalInformation.getMarket()).isEqualTo(additionalInformationDto.getMarket());
        assertThat(additionalInformation.getAnnouncerType()).isEqualTo(additionalInformationDto.getAnnouncerType());
        assertThat(additionalInformation.getYearOfConstruction()).isEqualTo(additionalInformationDto.getYearOfConstruction());
        assertThat(additionalInformation.getBuildingType()).isEqualTo(additionalInformationDto.getBuildingType());
        assertThat(additionalInformation.getMedia()).isEqualTo(additionalInformationDto.getMedia());
        assertThat(additionalInformation.getEquipment()).isEqualTo(additionalInformationDto.getEquipment());
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDtoDoesNotExistMapOffer() {
        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOffer(null))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDetailsDtoDoesNotExistMapOffer() {
        //given
        OfferDto offerDtoTest = new OfferDto();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOffer(offerDtoTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldMapOfferToOfferDto() {
        //given
        Offer offerTest = getOffer();

        //when
        OfferDto offerDto = mapper.mapOfferDto(offerTest, MID_RATE);

        //then
        assertThat(offerDto.getId()).isEqualTo(offerTest.getId());
        assertThat(offerDto.getKindOfProperty()).isEqualTo(offerTest.getKindOfProperty());
        assertThat(offerDto.getPrice()).isEqualTo(offerTest.getPrice() / MID_RATE);
        assertThat(offerDto.getTitle()).isEqualTo(offerTest.getTitle());
        assertThat(offerDto.getCity()).isEqualTo(offerTest.getCity());
        assertThat(offerDto.getNumberOfRooms()).isEqualTo(offerTest.getNumberOfRooms());
        assertThat(offerDto.getArea()).isEqualTo(offerTest.getArea());
        assertThat(offerDto.getPricePerMeter()).isEqualTo(offerTest.getPricePerMeter() / MID_RATE);
        assertThat(offerDto.getFloor()).isEqualTo(offerTest.getFloor());
        assertThat(offerDto.getDescription()).isEqualTo(offerTest.getDescription());
    }

    @Test
    void shouldThrowResponseStatusExceptionIfOfferDoesNotExistMapOfferDto() {
        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferDto(null, MID_RATE))
                .withMessage("404 NOT_FOUND \"Offer not found\"");
    }

    @Test
    void shouldReturnPriceOrPricePerMeterWhenMidRateIsEqualsZero() {
        //given
        Offer offerTest = getOfferNewEmpty();

        //when
        OfferDto offerDto = mapper.mapOfferDto(offerTest, 0.0d);

        //then
        assertThat(offerDto.getPrice()).isEqualTo(offerTest.getPrice());
        assertThat(offerDto.getPricePerMeter()).isEqualTo(offerTest.getPricePerMeter());
    }

    @Test
    void shouldMapOfferWithDetailsToOfferWithDetailsDto() {
        //given
        OfferDetails.AdditionalInformation additionalInformationTest = getAdditionalInformation();
        OfferDetails offerDetailsTest = getOfferDetails(additionalInformationTest);
        Offer offerTest = getOffer();
        offerTest.setOfferDetails(offerDetailsTest);

        //when
        OfferDto offerDto = mapper.mapOfferWithDetailsDto(offerTest);

        //then
        assertThat(offerDto.getId()).isEqualTo(offerTest.getId());
        assertThat(offerDto.getKindOfProperty()).isEqualTo(offerTest.getKindOfProperty());
        assertThat(offerDto.getPrice()).isEqualTo(offerTest.getPrice());
        assertThat(offerDto.getTitle()).isEqualTo(offerTest.getTitle());
        assertThat(offerDto.getCity()).isEqualTo(offerTest.getCity());
        assertThat(offerDto.getStreet()).isEqualTo(offerTest.getStreet());
        assertThat(offerDto.getNumberOfRooms()).isEqualTo(offerTest.getNumberOfRooms());
        assertThat(offerDto.getArea()).isEqualTo(offerTest.getArea());
        assertThat(offerDto.getPricePerMeter()).isEqualTo(offerTest.getPricePerMeter());
        assertThat(offerDto.getFloor()).isEqualTo(offerTest.getFloor());
        assertThat(offerDto.getDescription()).isEqualTo(offerTest.getDescription());

        OfferDetailsDto offerDetailsDto = offerDto.getOfferDetailsDto();
        assertThat(offerDetailsDto.getRent()).isEqualTo(offerDetailsTest.getRent());
        assertThat(offerDetailsDto.getOwnershipForm()).isEqualTo(offerDetailsTest.getOwnershipForm());
        assertThat(offerDetailsDto.getFinishLevel()).isEqualTo(offerDetailsTest.getFinishLevel());
        assertThat(offerDetailsDto.getParkingPlace()).isEqualTo(offerDetailsTest.getParkingPlace());
        assertThat(offerDetailsDto.getHeating()).isEqualTo(offerDetailsTest.getHeating());
        assertThat(offerDetailsDto.getContactDetails()).isEqualTo(offerDetailsTest.getContactDetails());

        OfferDetailsDto.AdditionalInformation additionalInformationDto = offerDetailsDto.getAdditionalInformationDto();
        assertThat(additionalInformationDto.getMarket()).isEqualTo(additionalInformationTest.getMarket());
        assertThat(additionalInformationDto.getAnnouncerType()).isEqualTo(additionalInformationTest.getAnnouncerType());
        assertThat(additionalInformationDto.getYearOfConstruction()).isEqualTo(additionalInformationTest.getYearOfConstruction());
        assertThat(additionalInformationDto.getMedia()).isEqualTo(additionalInformationTest.getMedia());
        assertThat(additionalInformationDto.getBuildingType()).isEqualTo(additionalInformationTest.getBuildingType());
        assertThat(additionalInformationDto.getEquipment()).isEqualTo(additionalInformationTest.getEquipment());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAdditionalInformationDoesNotExists() {
        //given
        Offer offerTest = new Offer();
        OfferDetails offerDetails = new OfferDetails();
        offerTest.setOfferDetails(offerDetails);

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferWithDetailsDto(offerTest))
                .withMessage("404 NOT_FOUND \"AdditionalInformation not found\"");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenOfferDoesNotExists() {
        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferWithDetailsDto(null))
                .withMessage("404 NOT_FOUND \"Offer not found\"");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenOfferDetailsDoesNotExists() {
        //given
        Offer offerTest = new Offer();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapOfferWithDetailsDto(offerTest))
                .withMessage("404 NOT_FOUND \"OfferDetails not found\"");
    }

    @Test
    void shouldMapClientMessageDtoToClientMessage() {
        //given
        ClientMessageDto clientMessageDtoTest = new ClientMessageDto();
        Offer offerTest = getOfferNewEmpty();
        offerTest.setId(1L);
        clientMessageDtoTest.setName("Karol");
        clientMessageDtoTest.setEmail("karol@gmail.com");
        clientMessageDtoTest.setPhoneNumber("444333111");
        clientMessageDtoTest.setMessage("Prosze o kontakt w sprawie oferty");
        clientMessageDtoTest.setIdOffer(offerTest.getId());

        //when
        ClientMessage clientMessage = mapper.mapClientMessage(clientMessageDtoTest, offerTest);

        //then
        assertThat(clientMessage.getName()).isEqualTo(clientMessageDtoTest.getName());
        assertThat(clientMessage.getEmail()).isEqualTo(clientMessageDtoTest.getEmail());
        assertThat(clientMessage.getPhoneNumber()).isEqualTo(clientMessageDtoTest.getPhoneNumber());
        assertThat(clientMessage.getMessage()).isEqualTo(clientMessageDtoTest.getMessage());
        assertThat(clientMessage.getOffer()).isEqualTo(offerTest);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenClientMessageDtoDoesNotExists() {
        //given
        Offer offerTest = getOfferNewEmpty();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapClientMessage(null, offerTest))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenOfferDoesNotExistsMapClientMessage() {
        //given
        ClientMessageDto clientMessageDto = new ClientMessageDto();

        //when & then
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mapper.mapClientMessage(clientMessageDto, null))
                .withMessage("404 NOT_FOUND \"Not found\"");
    }


    private static OfferDto getOfferDto(OfferDetailsDto offerDetailsDtoTest) {
        OfferDto offerDtoTest = new OfferDto();
        offerDtoTest.setOfferDetailsDto(offerDetailsDtoTest);
        offerDtoTest.setKindOfProperty(Offer.KindOfProperty.MIESZKANIE);
        offerDtoTest.setPrice(600000d);
        offerDtoTest.setTitle("Sprzedam");
        offerDtoTest.setCity("Wroclaw");
        offerDtoTest.setNumberOfRooms(4);
        offerDtoTest.setArea(50d);
        offerDtoTest.setPricePerMeter(12000d);
        offerDtoTest.setFloor(5);
        return offerDtoTest;
    }

    private static OfferDetailsDto getOfferDetailsDto(OfferDetailsDto.AdditionalInformation additionalInformationDtoTest) {
        OfferDetailsDto offerDetailsDtoTest = new OfferDetailsDto();
        offerDetailsDtoTest.setAdditionalInformationDto(additionalInformationDtoTest);
        offerDetailsDtoTest.setOwnershipForm(OfferDetails.OwnershipForm.PELNA_WLASNOSC);
        offerDetailsDtoTest.setRent(450d);
        offerDetailsDtoTest.setFinishLevel(OfferDetails.FinishLevel.DO_ZAMIESZKANIA);
        offerDetailsDtoTest.setParkingPlace(OfferDetails.ParkingPlace.MIEJSCE_NAZIEMNE);
        offerDetailsDtoTest.setHeating(OfferDetails.Heating.ELEKTRYCZNE);
        offerDetailsDtoTest.setContactDetails("900300100");
        return offerDetailsDtoTest;
    }

    private static OfferDetailsDto.AdditionalInformation getAdditionalInformationDto() {
        OfferDetailsDto.AdditionalInformation additionalInformationDtoTest = new OfferDetailsDto.AdditionalInformation();
        additionalInformationDtoTest.setMarket(OfferDetails.Market.PIERWOTNY);
        additionalInformationDtoTest.setAnnouncerType(OfferDetails.AnnouncerType.BIURO_NIERUCHOMOSCI);
        additionalInformationDtoTest.setYearOfConstruction(2019);
        additionalInformationDtoTest.setBuildingType(OfferDetails.BuildingType.APARTAMENTOWIEC);
        additionalInformationDtoTest.setMedia("internet");
        additionalInformationDtoTest.setEquipment("pralka, piekarnik");
        return additionalInformationDtoTest;
    }

    private static Offer getOffer() {
        Offer offerTest = new Offer();
        offerTest.setId(1L);
        offerTest.setKindOfProperty(Offer.KindOfProperty.MIESZKANIE);
        offerTest.setPrice(500000d);
        offerTest.setTitle("Spzedam mieszaknie");
        offerTest.setCity("Wroclaw");
        offerTest.setNumberOfRooms(3);
        offerTest.setArea(50d);
        offerTest.setPricePerMeter(10000d);
        offerTest.setFloor(3);
        offerTest.setDescription("Sprzedam mieszkanie w spokojnej okolicy");
        return offerTest;
    }

    private static OfferDetails getOfferDetails(OfferDetails.AdditionalInformation additionalInformationTest) {
        OfferDetails offerDetailsTest = new OfferDetails();
        offerDetailsTest.setRent(400D);
        offerDetailsTest.setOwnershipForm(OfferDetails.OwnershipForm.PELNA_WLASNOSC);
        offerDetailsTest.setFinishLevel(OfferDetails.FinishLevel.DO_ZAMIESZKANIA);
        offerDetailsTest.setParkingPlace(OfferDetails.ParkingPlace.MIEJSCE_NAZIEMNE);
        offerDetailsTest.setHeating(OfferDetails.Heating.ELEKTRYCZNE);
        offerDetailsTest.setContactDetails("333444555");
        offerDetailsTest.setAdditionalInformation(additionalInformationTest);
        return offerDetailsTest;
    }

    private static OfferDetails.AdditionalInformation getAdditionalInformation() {
        OfferDetails.AdditionalInformation additionalInformationTest = new OfferDetails.AdditionalInformation();
        additionalInformationTest.setMarket(OfferDetails.Market.PIERWOTNY);
        additionalInformationTest.setAnnouncerType(OfferDetails.AnnouncerType.BIURO_NIERUCHOMOSCI);
        additionalInformationTest.setYearOfConstruction(2010);
        additionalInformationTest.setMedia("internet");
        additionalInformationTest.setBuildingType(OfferDetails.BuildingType.APARTAMENTOWIEC);
        additionalInformationTest.setEquipment("pralka, lodowka");
        return additionalInformationTest;
    }

    private static Offer getOfferNewEmpty() {
        Offer offerTest = new Offer();
        OfferDetails.AdditionalInformation additionalInformationTest = new OfferDetails.AdditionalInformation();
        OfferDetails offerDetailsTest = new OfferDetails();
        offerDetailsTest.setAdditionalInformation(additionalInformationTest);
        offerTest.setOfferDetails(offerDetailsTest);
        return offerTest;
    }

    private static OfferDto getOfferDtoNewEmpty() {
        OfferDto offerDtoTest = new OfferDto();
        OfferDetailsDto.AdditionalInformation additionalInformationDtoTest = new OfferDetailsDto.AdditionalInformation();
        OfferDetailsDto offerDetailsDtoTest = new OfferDetailsDto();
        offerDetailsDtoTest.setAdditionalInformationDto(additionalInformationDtoTest);
        offerDtoTest.setOfferDetailsDto(offerDetailsDtoTest);
        return offerDtoTest;
    }
}