package ru.kpfu.itis.security.validators;

import ru.kpfu.itis.models.Advert;

public class AdvertValidator {
    public void validateAdvert(Advert advert) {
        if (advert.getName() == null || advert.getName().length() <= 6 || advert.getName().length() >= 50) {
            throw new IllegalArgumentException("Name length must be less than 50 and greater than 6.");
        }
        if (advert.getDescription() == null || advert.getDescription().length() <= 6 || advert.getDescription().length() >= 500) {
            throw new IllegalArgumentException("Description length must be less than 500 and greater than 6.");
        }
        if (advert.getPrice() == null) {
            throw new IllegalArgumentException("Advert price must be not null");
        }
    }
}
