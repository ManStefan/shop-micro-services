package com.man.shop.test.filters;


import com.google.gson.reflect.TypeToken;
import com.man.shop.SearchServiceApplication;
import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.test.BaseSearchTest;
import com.man.shop.utils.JsonUtils;
import com.man.shop.utils.PlatformConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by smanolache on 2/16/2017.
 */

//docker run -d -P -v //D/conf:/myconfig -p 8983:8983 solr solr-create -c mycore -d /myconfig
//docker exec -t -i 472b /bin/bash
    // docker-machine ssh default
    //docker inspect 18d5



@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles(PlatformConstants.Environments.Constants.TEST_VALUE)
public class FilterTest extends BaseSearchTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<RestProduct> restProducts;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Override
    public void doBeforeLogic() throws Exception {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(delete("/product"));

        Type listType = new TypeToken<ArrayList<RestProduct>>(){}.getType();
        restProducts = JsonUtils.fromJsonFile(new File("src/test/resources/products.json"), listType);

        for (RestProduct product : restProducts) {
            mockMvc.perform(post("/product")
                    .content(this.json(product))
                    .contentType(contentType))
                    .andExpect(status().isCreated());
        }
    }

    @Override
    protected void doAfterLogic() {

    }

    @Test
    public void testFilterAllProdSelectedByAttributes() throws Exception {
        RestProductFilterRequest filterRequest = JsonUtils.fromJsonFile(new File("src/test/resources/filter_select_all_prods.json"), RestProductFilterRequest.class);

        mockMvc.perform(post("/product/filter")
                .content(this.json(filterRequest))
                .contentType(contentType))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.products", hasSize(2)))
                .andExpect(jsonPath("$.products[0]", is(1)))
                .andExpect(jsonPath("$.products[1]", is(2)))
                .andExpect(jsonPath("$.facetedAttributes.1.1", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.1.2", is(2)))
                .andExpect(jsonPath("$.facetedAttributes.1.3", is(2)))
                .andExpect(jsonPath("$.facetedAttributes.1.5", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.2.4", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.3.6", is(1)));
    }

    @Test
    public void testFilterFirstProdByAttributes() throws Exception {
        RestProductFilterRequest filterRequest = JsonUtils.fromJsonFile(new File("src/test/resources/filter_first_prod.json"), RestProductFilterRequest.class);

        mockMvc.perform(post("/product/filter")
                .content(this.json(filterRequest))
                .contentType(contentType))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.products", hasSize(1)))
                .andExpect(jsonPath("$.products[0]", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.1.1", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.1.2", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.1.3", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.1.5").doesNotExist())
                .andExpect(jsonPath("$.facetedAttributes.2.4", is(1)))
                .andExpect(jsonPath("$.facetedAttributes.3.6").doesNotExist());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
