package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoMain {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.sunil.sixtcarandroid.db.model");
        addKickStarter(schema);
        new DaoGenerator().generateAll(schema, "../SixtCarAndroid/app/src/main/java/");
    }

    private static void addKickStarter(Schema schema) {

              /*  "id": "WMWSW31030T222518",
                "modelIdentifier": "mini",
                "modelName": "MINI",
                "name": "Vanessa",
                "make": "BMW",
                "group": "MINI",
                "color": "midnight_black",
                "series": "MINI",
                "fuelType": "D",
                "fuelLevel": 0.7,
                "transmission": "M",
                "licensePlate": "M-VO0259",
                "latitude": 48.134557,
                "longitude": 11.576921,
                "innerCleanliness": "REGULAR",
                "carImageUrl": "https://de.drive-now.com/static/drivenow/img/cars/mini.png"*/

        Entity car = schema.addEntity("Car");
        car.addIdProperty();
        car.addStringProperty("car_id").notNull();
        car.addStringProperty("modelIdentifier");
        car.addStringProperty("modelName");
        car.addStringProperty("name");
        car.addStringProperty("make");
        car.addStringProperty("group");
        car.addStringProperty("series");
        car.addStringProperty("color");
        car.addStringProperty("fuelType");
        car.addDoubleProperty("fuelLevel");
        car.addStringProperty("transmission");
        car.addStringProperty("licensePlate");
        car.addDoubleProperty("latitude");
        car.addDoubleProperty("longitude");
        car.addStringProperty("innerCleanliness");
        car.addStringProperty("carImageUrl");

    }
}
