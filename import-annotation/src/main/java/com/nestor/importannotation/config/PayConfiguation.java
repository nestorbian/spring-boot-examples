package com.nestor.importannotation.config;

import com.nestor.importannotation.service.PayService;
import org.springframework.context.annotation.Bean;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/10
 */
public class PayConfiguation {

    @Bean
    public PayService payService() {
        return new PayService();
    }

}
