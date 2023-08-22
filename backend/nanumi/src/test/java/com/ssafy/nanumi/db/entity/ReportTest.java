package com.ssafy.nanumi.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReportTest {

    private Report report;
    private User reporter;
    private User reported;

    @BeforeEach
    void setUp() {
        reporter = User.builder()
                 .id(1L)
//                 .name("Reporter")
                .build();

        reported = User.builder()
                .id(2L)
                .build();

        report = Report.builder()
                .id(1L)
                .content("MacBook")
                .status(false)
                .stopDate(0)
                .reporter(reporter)
                .reported(reported)
                .build();
    }

    @Test
    void testReportBuilder() {
        assertEquals(1L, report.getId());
        assertEquals("MacBook", report.getContent());
//        assertFalse(report.getStatus());
        assertEquals(0, report.getStopDate());
        assertEquals(reporter, report.getReporter());
        assertEquals(reported, report.getReported());
    }

    @Test
    void testUpdateStatus() {
        report.updateStatus(10);
//        assertEquals(true, report.getStatus());
        assertEquals(10, report.getStopDate());
    }
}
