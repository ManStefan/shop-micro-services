package com.man.shop.test;

import com.man.shop.ResourceServiceApplication;
import com.man.shop.model.CategoryOfProduct;
import com.man.shop.repositories.CategoryOfProductRepository;
import com.man.shop.rest.entites.RestCategoryOfProduct;
import com.man.shop.service.CategoryOfProductService;
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by smanolache on 3/30/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResourceServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles(PlatformConstants.Environments.Constants.TEST_VALUE)
public class TestInsert extends BaseResourceTest{

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private CategoryOfProductRepository categoryOfProductRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void emptyTest() throws Exception {
        RestCategoryOfProduct restCategoryOfProduct = new RestCategoryOfProduct();
        restCategoryOfProduct.setName("First");
        restCategoryOfProduct.setLevel(1);
        restCategoryOfProduct.setDescription("Desc");

        mockMvc.perform(post("/category_of_product")
                .content(this.json(restCategoryOfProduct))
                .contentType(contentType))
                .andExpect(status().isCreated());

        assertEquals("First", categoryOfProductRepository.findAll().iterator().next().getName());
    }

    @Override
    protected void doBeforeLogic() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Override
    protected void doAfterLogic() {

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
