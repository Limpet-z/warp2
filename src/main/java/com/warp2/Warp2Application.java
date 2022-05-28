package com.warp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Warp2Application {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Warp2Application.class);

        app.setBanner((environment, sourceClass, out) -> out.print(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |   _____      | || |     _____    | || | ____    ____ | || |   ______     | || |  _________   | || |  _________   | |\n" +
                "| |  |_   _|     | || |    |_   _|   | || ||_   \\  /   _|| || |  |_   __ \\   | || | |_   ___  |  | || | |  _   _  |  | |\n" +
                "| |    | |       | || |      | |     | || |  |   \\/   |  | || |    | |__) |  | || |   | |_  \\_|  | || | |_/ | | \\_|  | |\n" +
                "| |    | |   _   | || |      | |     | || |  | |\\  /| |  | || |    |  ___/   | || |   |  _|  _   | || |     | |      | |\n" +
                "| |   _| |__/ |  | || |     _| |_    | || | _| |_\\/_| |_ | || |   _| |_      | || |  _| |___/ |  | || |    _| |_     | |\n" +
                "| |  |________|  | || |    |_____|   | || ||_____||_____|| || |  |_____|     | || | |_________|  | || |   |_____|    | |\n" +
                "| |              | || |              | || |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        "));

        app.run(args);
    }

}