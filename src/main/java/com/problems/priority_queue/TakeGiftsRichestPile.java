package com.problems.priority_queue;

import com.framework.*;

import java.util.List;
import java.util.Map;

@ExcludeFromRegistry
public class TakeGiftsRichestPile implements Problem<TakeGiftsRichestPile.TakeGiftsRichestPileInput, Integer> {

    @Override
    public String getID() {
        return "2558";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Source getSource() {
        return null;
    }

    @Override
    public String getExternalLink() {
        return "";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of();
    }

    @Override
    public Map<String, Solution<TakeGiftsRichestPileInput, Integer>> getSolutions() {
        return Map.of();
    }

    @Override
    public List<TestCase<TakeGiftsRichestPileInput, Integer>> getTestCases() {
        return List.of();
    }

    public record TakeGiftsRichestPileInput (int [] gifts, int k){}

}
