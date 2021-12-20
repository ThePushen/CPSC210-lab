package model.quiz;

import model.question.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnlimitedTriesQuizTest extends QuizTest{

    @BeforeEach
    void runBefore() {
        QuestionList questionList = generateQuestionList();
        quiz = new UnlimitedTriesQuiz(questionList);
    }

    @Test
    void testConstructor() {
        super.testConstructor();
        assertEquals(6, quiz.getMaxMark());
    }

    @Test
    void testSubmitAnswerOneTry() {
        try {
            quiz.getNextQuestion();
            String feedback = quiz.submitAnswer("Earth");
            assertEquals("Correct!", feedback);
            assertEquals(4, quiz.getMarkSoFar());
            quiz.getNextQuestion();
            feedback = quiz.submitAnswer("Canada");
            assertEquals("Correct!", feedback);
            assertEquals(6, quiz.getMarkSoFar());
            assertFalse(quiz.hasMoreQuestions());
            assertEquals("It took you 2 attempts to answer 2 questions correctly.", quiz.endQuiz());
        } catch (Exception e) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    void testSubmitAnswerSomeTries() {
        try {
            quiz.getNextQuestion();
            String feedback = quiz.submitAnswer("Earth");
            assertEquals("Correct!", feedback);
            assertEquals(4, quiz.getMarkSoFar());
            quiz.getNextQuestion();
            feedback = quiz.submitAnswer("anada");
            assertEquals("Incorrect!", feedback);
        } catch (Exception e) {
            try {
                String feedback = quiz.submitAnswer("anada");
                assertEquals("Incorrect!", feedback);
            } catch (Exception r) {
                try {
                    String feedback = quiz.submitAnswer("Canada");
                    assertEquals("Correct!", feedback);
                } catch (Exception t) {
                }
            }
        }
    }


    @Test
    void testSubmitAnswerManyTries() {
        try {
            for (int i = 0; i < 4; i++) {
                String feedback = quiz.submitAnswer("AV");
                assertEquals("Incorrect", feedback);
                assertEquals(0, quiz.getMarkSoFar());
            }
        } catch (Exception a) {
                    try {
                        String feedback = quiz.submitAnswer("Earth");
                        assertEquals("Correct!", feedback);
                        assertEquals(4, quiz.getMarkSoFar());
                        quiz.getNextQuestion();
                        feedback = quiz.submitAnswer("AAX");
                        assertEquals("Incorrect", feedback);
                        assertEquals(4, quiz.getMarkSoFar());
                    } catch (Exception c) {
                        try {
                            for (int n = 0; n < 7; n++) {
                                String feedback = quiz.submitAnswer("AAX");
                                assertEquals("Incorrect", feedback);
                                assertEquals(4, quiz.getMarkSoFar());
                            }
                        } catch (Exception t) {
                                try {
                                    String feedback = quiz.submitAnswer("Canada");
                                    assertEquals("Correct!", feedback);
                                    assertEquals(6, quiz.getMarkSoFar());
                                    assertFalse(quiz.hasMoreQuestions());
                                    assertEquals("It took you 13 attempts to answer 2 questions correctly.", quiz.endQuiz());
                                } catch (Exception ignored) {
                                }
                            }
                        }
                    }
                }
    }
