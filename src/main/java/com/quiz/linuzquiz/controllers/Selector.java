package com.quiz.linuzquiz.controllers;

import com.quiz.linuzquiz.model.Choice;
import com.quiz.linuzquiz.model.Slide;

import java.util.List;
import java.util.stream.Collectors;

public class Selector {
    private Slide current;
    private List<Slide> battery;
    private final Integer numberOfQuestions;

    public Selector(List<Slide> battery) {
        super();
        this.current = battery.get(0);
        this.battery = battery;
        this.numberOfQuestions = battery.size() - 1;
    }

    protected Slide next() {
        int position = this.battery.indexOf(current);
        if (position == numberOfQuestions)
            this.current = battery.get(0);
        if (position < numberOfQuestions)
            this.current = battery.get((position + 1));
        return this.current;
    }

    protected Slide previous() {
        int position = this.battery.indexOf(current);
        if (position == 0)
            this.current = battery.get(this.numberOfQuestions);
        if (position != 0)
            this.current = battery.get((position - 1));
        return this.current;
    }

    protected Slide getSlide(){ return this.current ;}

    protected void current() {
        System.out.println(this.current.getTitle());
    }
  // gets the Choice objects that have been clicked
    protected Boolean checkAllSlidesHaveAnswer() {
        Boolean clickedAllChoices =
                this.battery.
                        stream()
                        .flatMap(slide -> slide.getChoices().getChoices().stream()
                                .filter(Choice::getClicked)
                        ).toList().size() == this.battery.size();
        return clickedAllChoices;
    }

    public List<Slide> getBattery() {
        return battery;
    }
}
