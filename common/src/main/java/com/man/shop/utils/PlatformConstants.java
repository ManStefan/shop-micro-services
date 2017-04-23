package com.man.shop.utils;

/**
 * Created by smanolache on 4/22/2017.
 */
public class PlatformConstants {

    public enum Environments {
        TEST(Constants.TEST_VALUE),
        DEV(Constants.DEV_VALUE),
        PP(Constants.PP_VALUE),
        PROD(Constants.PROD_VALUE);

        private String name;

        Environments(String name){
            if(!name.equals(this.name()))
                throw new IllegalArgumentException();
            this.name = name;
        }

        public String getName(){
            return this.name;
        }


        public static class Constants{
            public static final String TEST_VALUE = "test";
            public static final String DEV_VALUE = "dev";
            public static final String PP_VALUE = "pp";
            public static final String PROD_VALUE = "prod";
        }
    }
}
