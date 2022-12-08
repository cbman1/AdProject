package ru.kpfu.itis.repositories;

import ru.kpfu.itis.dao.AdvertDao;
import ru.kpfu.itis.dao.EditAdvertDao;
import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;

import java.util.List;

public interface AdvertRepository {
    void addAdvert(Advert advert);
    void addSoldAdvert(Advert advert);
    List<Advert> findAllAdvert();
    List<Advert> getAllActiveAdvertsUser(User user);
    Advert getAdvertById(Long id);
    List<Advert> getAllFavouriteAdvertsUser(User user);
    List<Advert> getAllSoldAdvertsUser(User user);
    void removeAdvertFromFavourite(Advert advert, User user);
    void addAdvertToFavourite(User user, Advert advert);
    List<Advert> findAdvertByRequest(String searchInput);
    List<Advert> findAdvertByCity(String city);
    Integer getCountFavouriteAdvert(Advert advert);
    void updateAdvertInformation(Advert advert);
    void updateSoldItem(Advert advert);
}
