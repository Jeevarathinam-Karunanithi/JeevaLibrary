package com.example.appengine.java8;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Test
{
    Calculator c = new Calculator();
    @Test
    public void testAdd(){
         assertEquals(5,c.addnum(2,3));
        }
}