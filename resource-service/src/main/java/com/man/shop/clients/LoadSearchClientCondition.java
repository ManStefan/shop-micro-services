package com.man.shop.clients;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import static com.man.shop.utils.PlatformConstants.*;

/**
 * Created by smanolache on 4/9/2017.
 */
public class LoadSearchClientCondition extends AllNestedConditions {

    public LoadSearchClientCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = Environments.Constants.DEV_VALUE)
    static class EnvIsDev{
    }

    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = Environments.Constants.PROD_VALUE)
    static class EnvIsProd{
    }

    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = Environments.Constants.PP_VALUE)
    static class EnvIsPreProd{

    }
}
