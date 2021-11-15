import com.getir.readingisgoodapp.ReadingIsGoodAppApplication;
import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.model.UpdateBookStockRequest;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.service.impl.BookServiceImpl;
import com.getir.readingisgoodapp.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ReadingIsGoodAppApplication.class)
@AutoConfigureDataMongo
public class BookTest {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void saveBook() throws Exception {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        Book book = new Book("Patasana","dk212","ahmet Umit",100,"100");
        Assertions.assertEquals(book,bookRepository.save(book));
    }

    @Test
    public void updateStock() throws Exception{
        Book book = bookRepository.findAll().get(0);
        int amountOfSum = 10;
        Assertions.assertEquals(book.getStock()+10,bookService.updateBookStock(new UpdateBookStockRequest(10,book.getId())));
    }
}
