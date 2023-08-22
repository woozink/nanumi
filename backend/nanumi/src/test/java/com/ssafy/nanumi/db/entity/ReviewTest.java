package com.ssafy.nanumi.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ReviewTest {

    private Review review;
    private User writer;
    private User receiver;
    private Match match;

    @BeforeEach
    public void setUp() {
        writer = mock(User.class);
        receiver = mock(User.class);
        match = mock(Match.class);

        review = Review.builder()
                .id(1L)
                .content("최고!")
                .starPoint(5)
                .rating(10)
                .writer(writer)
                .receiver(receiver)
                .match(match)
                .build();
    }

    @Test
    public void testReviewCreation() {
        assertEquals(1L, review.getId());
        assertEquals("최고!", review.getContent());
        assertEquals(5, review.getStarPoint());
        assertEquals(10, review.getRating());
        assertEquals(writer, review.getWriter());
        assertEquals(receiver, review.getReceiver());
        assertEquals(match, review.getMatch());
    }
}
