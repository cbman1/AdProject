package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.AdvertDao;
import ru.kpfu.itis.dao.EditAdvertDao;
import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;

import java.util.List;

public interface AdvertService {
    void addAdvert(AdvertDao advertDao, User user);
    void soldAdvert(Advert advert);
    void addAdvertToFavourite(User user, Advert advert);
    void removeAdvertFromFavourite(Advert advert, User user);
    List<Advert> findAllActiveAdverts();
    List<Advert> getFavouriteAdvertsUser(User user);
    List<Advert> getAllActiveAdvertsUser(User user);
    List<Advert> getAllSoldAdvertsUser(User user);
    List<Advert> getAllAdvertsByCity(String city);
    List<Advert> getAllAdvertsBySearchInput(String searchInput);
    Integer getCountFavouriteAdvert(Advert advert);
    Advert getAdvertById(Long id);
    void updateInformationAdvert(Long id, EditAdvertDao editAdvertDao);
    void returnInSellSoldAdvert(Advert advert);
}
