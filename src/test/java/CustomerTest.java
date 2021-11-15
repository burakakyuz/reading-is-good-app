import com.getir.readingisgoodapp.ReadingIsGoodAppApplication;
import com.getir.readingisgoodapp.domain.Customer;

import com.getir.readingisgoodapp.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= ReadingIsGoodAppApplication.class)
@AutoConfigureDataMongo
public class CustomerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CustomerServiceImpl customerService;


    @Test
    public void saveCustomer()  {
        Customer customer = new Customer("burak","akyuz","deneme@gmail.com");
        Assertions.assertEquals(customer,customerService.newCustomer(customer));
    }

}