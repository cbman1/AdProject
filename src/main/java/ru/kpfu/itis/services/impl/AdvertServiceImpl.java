package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.AdvertDao;
import ru.kpfu.itis.dao.EditAdvertDao;
import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.AdvertRepository;
import ru.kpfu.itis.security.validators.AdvertValidator;
import ru.kpfu.itis.services.AdvertService;

import java.util.List;

public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertValidator advertValidator;

    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
        this.advertValidator = new AdvertValidator();
    }

    @Override
    public void addAdvert(AdvertDao advertDao, User user) {
        Advert advert = Advert.builder()
                .name(advertDao.getName())
                .description(advertDao.getDescription())
                .price(advertDao.getPrice())
                .authorId(user.getId())
                .category(advertDao.getCategory())
                .city(user.getCity())
                .address(user.getAddress())
                .build();

        advertValidator.validateAdvert(advert);
        advertRepository.addAdvert(advert);
    }

    @Override
    public void soldAdvert(Advert advert) {
        advertRepository.addSoldAdvert(advert);
    }

    @Override
    public void addAdvertToFavourite(User user, Advert advert) {
        advertRepository.addAdvertToFavourite(user, advert);
    }

    @Override
    public List<Advert> findAllActiveAdverts() {
        return advertRepository.findAllAdvert();
    }

    @Override
    public List<Advert> getFavouriteAdvertsUser(User user) {
        return advertRepository.getAllFavouriteAdvertsUser(user);
    }

    @Override
    public void removeAdvertFromFavourite(Advert advert, User user) {
        advertRepository.removeAdvertFromFavourite(advert, user);
    }

    @Override
    public List<Advert> getAllSoldAdvertsUser(User user) {
        return advertRepository.getAllSoldAdvertsUser(user);
    }

    @Override
    public Advert getAdvertById(Long id) {
        return advertRepository.getAdvertById(id);
    }

    @Override
    public List<Advert> getAllActiveAdvertsUser(User user) {
        return advertRepository.getAllActiveAdvertsUser(user);
    }

    @Override
    public List<Advert> getAllAdvertsByCity(String city) {
        return advertRepository.findAdvertByCity(city);
    }

    @Override
    public List<Advert> getAllAdvertsBySearchInput(String searchInput) {
        return advertRepository.findAdvertByRequest(searchInput);
    }

    @Override
    public Integer getCountFavouriteAdvert(Advert advert) {
        return advertRepository.getCountFavouriteAdvert(advert);
    }

    @Override
    public void updateInformationAdvert(Long id, EditAdvertDao editAdvertDao) {
        Advert advert = advertRepository.getAdvertById(id);
        advert.setName(editAdvertDao.getName());
        advert.setDescription(editAdvertDao.getDescription());
        advert.setPrice(editAdvertDao.getPrice());
        advertValidator.validateAdvert(advert);
        advertRepository.updateAdvertInformation(advert);
    }

    @Override
    public void returnInSellSoldAdvert(Advert advert) {
        advertRepository.updateSoldItem(advert);
    }
}
