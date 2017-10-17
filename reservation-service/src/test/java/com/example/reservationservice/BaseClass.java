package com.example.reservationservice;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
public class BaseClass {

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationRestController reservationRestController;

    @Before
    public void before() throws Exception {

        List<Reservation> reservations = Arrays.asList(new Reservation(1L, "Jane"),
                new Reservation(2L, "Bob"));

        // turn the mock into a stub w/ Mockito
        Mockito.when(this.reservationRepository.findAll()).thenReturn(reservations);

        RestAssuredMockMvc.standaloneSetup(this.reservationRestController);
    }
}
