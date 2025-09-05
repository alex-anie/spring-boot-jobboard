package com.alexanie.jobboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoSpyTest {

    @Spy
    ArrayList<String> spyList = new ArrayList<>();

    @Spy
    ArrayList<Integer> ages = new ArrayList<>();

    @Test
    @DisplayName("Add Strings to ArrayList")
    void testing_spies(){
        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertThat(spyList.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("Stubbing a Spy :: Ages")
    void subbing_ages(){

            ages.add(20);
            ages.add(30);
            ages.add(40);

            int value = ages.get(1);
            System.out.println(value);

            doReturn(12).when(ages).get(1);
            System.out.println(ages.get(1));

            verify(ages, times(2)).get(1);

            assertThat(value).isEqualTo(30);
            assertThat(ages.size()).isEqualTo(3);

    }

    @Nested
    @DisplayName("Mock vs Spy in Mockito")
    class MockVsSpyInMockito {

        @Mock
        ArrayList<String> mockNames = new ArrayList<>();

        @Spy
        ArrayList<String> spyNames = new ArrayList<>();

        @Test
        @DisplayName("Mock in Mockito")
        void mockTest(){
            /*
            When you annotate with @Mock, Mockito replaces names with a mock object,
            not your real ArrayList.
            That mock does not have the real behavior of ArrayList.
            Calls like names.add("John") don’t actually add anything.
            By default, mocks return default values (0 for ints, null for objects,
            false for booleans, etc.).
            So when you do names.size(), it doesn’t call the real ArrayList.size()—instead
            it returns the default 0.

             when(names.size()).thenReturn(4);

             */

              when(mockNames.get(1)).thenReturn("The value is jane");
              when(mockNames.get(2)).thenReturn("John");

              assertThat(mockNames.size()).isEqualTo(4);
              assertThat(mockNames.get(1)).isEqualTo("The value is jane");
              assertThat(mockNames.get(2)).isEqualTo("John");
        }

        @Test
        @DisplayName("Spy in Mockito")
        void spyTest(){

            spyNames.add("Alex");
            spyNames.add("Jessica");
            spyNames.add("Solomon");

            assertThat(spyNames.get(2)).isEqualTo("Solomon");
        }

    }
    

}
