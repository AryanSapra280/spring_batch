package com.ev.itemProcessors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer,Long> {
    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("Item processed and added 20 in it");
        return Long.valueOf(item+20);
    }
}
