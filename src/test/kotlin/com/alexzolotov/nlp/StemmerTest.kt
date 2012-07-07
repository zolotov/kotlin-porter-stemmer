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
        assertEquals("wrong", stemmer.step1b("wronging"))
        assertEquals("wrong", stemmer.step1b("wronged"))
        assertEquals("speed", stemmer.step1b("speed"))
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
        assertEquals("wrong", stemmer.step1("wronging"))
        assertEquals("wrong", stemmer.step1("wronged"))
        assertEquals("speed", stemmer.step1("speed"))
    }

    test fun step2() {
        assertEquals("relate", stemmer.step2("relational"))
        assertEquals("rational", stemmer.step2("rational"))
        assertEquals("condition", stemmer.step2("conditional"))
        assertEquals("valence", stemmer.step2("valenci"))
        assertEquals("hesitance", stemmer.step2("hesitanci"))
        assertEquals("digitize", stemmer.step2("digitizer"))
        assertEquals("conformable", stemmer.step2("conformabli"))
        assertEquals("radical", stemmer.step2("radicalli"))
        assertEquals("different", stemmer.step2("differentli"))
        assertEquals("vile", stemmer.step2("vileli"))
        assertEquals("analogous", stemmer.step2("analogousli"))
        assertEquals("vietnamize", stemmer.step2("vietnamization"))
        assertEquals("predicate", stemmer.step2("predication"))
        assertEquals("operate", stemmer.step2("operator"))
        assertEquals("feudal", stemmer.step2("feudalism"))
        assertEquals("decisive", stemmer.step2("decisiveness"))
        assertEquals("hopeful", stemmer.step2("hopefulness"))
        assertEquals("callous", stemmer.step2("callousness"))
        assertEquals("formal", stemmer.step2("formaliti"))
        assertEquals("sensitive", stemmer.step2("sensitiviti"))
        assertEquals("sensible", stemmer.step2("sensibiliti"))
        assertEquals("visible", stemmer.step2("visibli"))
    }

    test fun step3() {
        assertEquals("triplic", stemmer.step3("triplicate"))
        assertEquals("form", stemmer.step3("formative"))
        assertEquals("formal", stemmer.step3("formalize"))
        assertEquals("electric", stemmer.step3("electriciti"))
        assertEquals("electric", stemmer.step3("electrical"))
        assertEquals("hope", stemmer.step3("hopeful"))
        assertEquals("good", stemmer.step3("goodness"))
    }
    
    test fun step4() {
        assertEquals("reviv", stemmer.step4("revival"))
        assertEquals("allow", stemmer.step4("allowance"))
        assertEquals("infer", stemmer.step4("inference"))
        assertEquals("airlin", stemmer.step4("airliner"))
        assertEquals("gyroscop", stemmer.step4("gyroscopic"))
        assertEquals("adjust", stemmer.step4("adjustable"))
        assertEquals("defens", stemmer.step4("defensible"))
        assertEquals("irrit", stemmer.step4("irritant"))        
        assertEquals("replac", stemmer.step4("replacement"))
        assertEquals("adjust", stemmer.step4("adjustment"))
        assertEquals("depend", stemmer.step4("dependent"))
        assertEquals("adopt", stemmer.step4("adoption"))
        assertEquals("homolog", stemmer.step4("homologou"))
        assertEquals("commun", stemmer.step4("communism"))
        assertEquals("activ", stemmer.step4("activate"))
        assertEquals("angular", stemmer.step4("angulariti"))
        assertEquals("homolog", stemmer.step4("homologous"))
        assertEquals("effect", stemmer.step4("effective"))
        assertEquals("bowdler", stemmer.step4("bowdlerize"))
        assertEquals("oblivion", stemmer.step4("oblivion"))
        assertEquals("vizament", stemmer.step4("vizament"))
    }

    test fun step5() {
        assertEquals("probat", stemmer.step5("probate"))
        assertEquals("rate", stemmer.step5("rate"))
        assertEquals("ceas", stemmer.step5("cease"))
        assertEquals("control", stemmer.step5("controll"))
        assertEquals("roll", stemmer.step5("roll"))
        assertEquals("worser", stemmer.step5("worser"))
    }

    test fun step5a() {
        assertEquals("probat", stemmer.step5a("probate"))
        assertEquals("rate", stemmer.step5a("rate"))
        assertEquals("ceas", stemmer.step5a("cease"))
        assertEquals("worser", stemmer.step5a("worser"))
        assertEquals("yoke", stemmer.step5a("yoke"))
    }

    test fun step5b() {
        assertEquals("control", stemmer.step5b("controll"))
        assertEquals("roll", stemmer.step5b("roll"))
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