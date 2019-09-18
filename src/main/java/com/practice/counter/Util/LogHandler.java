package com.practice.counter.Util;

import com.practice.counter.model.LogModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LogHandler {

    public List<LogModel> mapLogFileToModel(String fileName) {
        List<String> list;
        Matcher matcher;
        List<LogModel> logList = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {

            list = fileStream.collect(Collectors.toList());
            for (String line : list) {
                matcher = LogModel.getPatternFullLog().matcher(line);
                logList.add(LogModel.buildLogModel(matcher));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        serializeLogList(logList, "serializedLog.txt");
        return logList;
    }


    public void serializeLogList(List<LogModel> log, String filename) {
        try ( FileOutputStream file = new FileOutputStream(filename);
              ObjectOutputStream out = new ObjectOutputStream(file)) {

            out.writeObject(log);

            System.out.println("Object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerializeLogList(String filename) {
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)) {

            List<LogModel> deSerializedLogList = (List<LogModel>) in.readObject();

            System.out.println("Object has been deserialized ");
        }

        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
