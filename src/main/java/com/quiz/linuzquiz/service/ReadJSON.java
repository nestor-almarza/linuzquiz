package com.quiz.linuzquiz.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.quiz.linuzquiz.model.Choice;
import com.quiz.linuzquiz.model.Choices;
import com.quiz.linuzquiz.model.Slide;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ReadJSON {
    @SuppressWarnings("unchecked")
    public  List<Slide> reading() {

        JSONParser parser = new JSONParser();
        List<Slide> slides = null;
        try {
// take json file and creates an object with it.
            JSONArray obj =
                    (JSONArray) parser.parse(new FileReader("src/main/resources/com/quiz/linuzquiz/batteries/battery.json"));

// with this big obj we chop it into smaller ones Slide( String, Choices(List<Choice(String, Boolean)>) );
            slides = (List<Slide>) obj.stream()
                    .map(data -> parseData((JSONObject) data))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return slides;
    }

    /**
     *
     * Composition of Objects;
     * battery.json :breaks down to: ["title", "choices"]
     *  "choices" : breaks down to: [ "result", "response"]
     *
     *  Slide( title, Choices )
     *  Choices(List<Choice>)
     *  Choice(Boolean result, String response)
     */


    private  Slide parseData(JSONObject data)
    {
        String title = (String)data.get("title");
        JSONArray choiceList = (JSONArray) data.get("choices");
       // gets choices, in other words, the options
        List<Choice> options = (List<Choice>) choiceList.stream()
                .map( choice -> parseChoice((JSONObject) choice))
                .collect(Collectors.toList());
        // create Choices
        Choices choices = new Choices();
        choices.setChoices(options);
        // create and assemble Slide with Title and Choices
        Slide slide = new Slide();
        slide.setTitle( title);
        slide.setChoices(choices);
        return slide;
    }


    private Choice parseChoice(JSONObject choice) {
        Choice option =  new Choice();
        Boolean result = (Boolean)choice.get("result");
        String response = (String)choice.get("response");

        option.setResult(result);
        option.setResponse(response);

        return option;
    }
}


