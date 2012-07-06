package com.alexzolotov.nlp

import kotlin.test.assertEquals
import org.testng.annotations.Test as test

class StemmerTest {
    val stemmer = Stemmer()

    test fun step1a() {
        assertEquals("caress", stemmer.step1a("caresses"))
        assertEquals("poni", stemmer.step1a("ponies"))
        assertEquals("ti", stemmer.step1a("ties"))
        assertEquals("caress", stemmer.step1a("caress"))
        assertEquals("cat", stemmer.step1a("cats"))
    }

    test fun step1b() {
        assertEquals("agree", stemmer.step1b("agreed"))
        assertEquals("plaster", stemmer.step1b("plastered"))
        assertEquals("bled", stemmer.step1b("bled"))
        assertEquals("motor", stemmer.step1b("motoring"))
        assertEquals("sing", stemmer.step1b("sing"))

        assertEquals("conflate", stemmer.step1b("conflated"))
        assertEquals("trouble", stemmer.step1b("troubled"))
        assertEquals("size", stemmer.step1b("sized"))
        assertEquals("hop", stemmer.step1b("hopping"))
        assertEquals("tan", stemmer.step1b("tanned"))
        assertEquals("hiss", stemmer.step1b("hissing"))
        assertEquals("fizz", stemmer.step1b("fizzed"))
        assertEquals("fail", stemmer.step1b("failed"))
        assertEquals("file", stemmer.step1b("filing"))
    }

    test fun step1c() {
        assertEquals("happi", stemmer.step1c("happy"))
        assertEquals("sky", stemmer.step1c("sky"))
    }

    test fun step1() {
        assertEquals("caress", stemmer.step1("caresses"))
        assertEquals("poni", stemmer.step1("ponies"))
        assertEquals("ti", stemmer.step1("ties"))
        assertEquals("caress", stemmer.step1("caress"))
        assertEquals("cat", stemmer.step1("cats"))
        assertEquals("agree", stemmer.step1("agreed"))
        assertEquals("plaster", stemmer.step1("plastered"))
        assertEquals("bled", stemmer.step1("bled"))
        assertEquals("motor", stemmer.step1("motoring"))
        assertEquals("sing", stemmer.step1("sing"))
        assertEquals("conflate", stemmer.step1("conflated"))
        assertEquals("trouble", stemmer.step1("troubled"))
        assertEquals("size", stemmer.step1("sized"))
        assertEquals("hop", stemmer.step1("hopping"))
        assertEquals("tan", stemmer.step1("tanned"))
        assertEquals("hiss", stemmer.step1("hissing"))
        assertEquals("fizz", stemmer.step1("fizzed"))
        assertEquals("fail", stemmer.step1("failed"))
        assertEquals("file", stemmer.step1("filing"))
        assertEquals("happi", stemmer.step1("happy"))
        assertEquals("sky", stemmer.step1("sky"))
    }

    test fun m() {
        assertEquals(0, stemmer.m("tr"))
        assertEquals(0, stemmer.m("ee"))
        assertEquals(0, stemmer.m("tree"))
        assertEquals(0, stemmer.m("y"))
        assertEquals(0, stemmer.m("by"))

        assertEquals(1, stemmer.m("trouble"))
        assertEquals(1, stemmer.m("oats"))
        assertEquals(1, stemmer.m("trees"))
        assertEquals(1, stemmer.m("ivy"))

        assertEquals(2, stemmer.m("troubles"))
        assertEquals(2, stemmer.m("private"))
        assertEquals(2, stemmer.m("oaten"))
        assertEquals(2, stemmer.m("orrery"))
    }
}