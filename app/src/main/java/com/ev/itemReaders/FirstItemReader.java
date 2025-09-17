package com.ev.itemReaders;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {
    List<Integer> list = Arrays.asList(1,2,3,4,5,6);
    int count = 0;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //never use a for loop, because Spring Batch will call this method one-by-one until it gets NULLg
        if(count<list.size()) {
            return (list.get(count++));
        }
        return null;
    }
}
