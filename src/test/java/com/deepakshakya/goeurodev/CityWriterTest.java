package com.deepakshakya.goeurodev;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.util.Assert;

public class CityWriterTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private String header = "id,name,type,latitude,longitude";

    private String tempFilePath;

    private List<String> writtenLines = new ArrayList<>();

    private CityWriter writer;

    private BufferedWriter bufferedWriter;

    @Before
    public void setUp() throws Exception {

        tempFilePath = folder.newFile().getPath();

        bufferedWriter = new BufferedWriter(new FileWriter(tempFilePath));
        bufferedWriter.write(header + "\n");

        writer = new CityWriter();
        writer.setWriter(bufferedWriter);
    }

    @After
    public void tearDown() throws Exception {
        bufferedWriter.close();
    }

    private void readLinesInTempFile() throws IOException {

        Assert.notNull(writtenLines, "Test setup incorrect. Please initialize it in @Before method");

        BufferedReader reader = new BufferedReader(new FileReader(tempFilePath));

        String line;

        while ((line = reader.readLine()) != null) {
            writtenLines.add(line);
        }

        reader.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteNull() throws IOException {
        writer.write(null);
    }

    @Test(expected = IOException.class)
    public void testWriteException() throws IOException {

        bufferedWriter = mock(BufferedWriter.class);
        writer.setWriter(bufferedWriter);

        doThrow(IOException.class).when(bufferedWriter).write(anyString());

        writer.write(new CityBuilder().buildList(1));
    }

    @Test
    public void testWriteBlank() throws IOException {
        writer.write(new CityBuilder().buildList(0));

        readLinesInTempFile();

        assertNotNull(writtenLines);
        assertThat(writtenLines, hasSize(1));
        assertThat(writtenLines, hasItem(header));
    }

    @Test
    public void testWriteSingle() throws IOException {

        writer.write(new CityBuilder().buildList(1));

        readLinesInTempFile();

        assertNotNull(writtenLines);
        assertThat(writtenLines, hasSize(2));
        assertThat(writtenLines, hasItem(header));
        assertThat(writtenLines, hasItem("1,CITY1,CITY_TYPE1,1.0,1.0"));
    }

    @Test
    public void testWriteMultiple() throws IOException {

        int count = 10;

        writer.write(new CityBuilder().buildList(count));

        readLinesInTempFile();

        assertNotNull(writtenLines);
        assertThat(writtenLines, hasSize(count + 1));
        assertThat(writtenLines, hasItem(header));

        for (int i = 1; i <= count; i++) {
            assertThat(writtenLines, hasItem(i + ",CITY" + i + ",CITY_TYPE" + i + "," + i + ".0," + i + ".0"));
        }
    }
}
