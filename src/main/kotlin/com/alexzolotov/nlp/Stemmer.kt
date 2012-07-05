package com.alexzolotov.nlp

public class Stemmer {

    public fun stem(var word : String): String {
        word = step1(word)
        word = step2(word)
        word = step3(word)
        word = step4(word)
        return step5(word)
    }

    fun step1(word : String) : String {
        return step1b(step1a(word));
    }

    fun step2(var word : String) : String {
        return word
    }

    fun step3(var word : String) : String {
        return word
    }

    fun step4(var word : String) : String {
        return word
    }

    fun step5(var word : String) : String {
        return word
    }

    fun step1a(word : String) : String {
        return when {
            word.endsWith("sses") -> word.replaceFirst("sses$", "ss")
            word.endsWith("ies") -> word.substring(0, word.length - 2)
            word.endsWith("ss") -> word
            word.endsWith("s") -> word.substring(0, word.length - 1)
            else -> word
        }
    }

    fun step1b(word : String) : String {
        return word
    }

    fun step1c(word : String) : String {
        return word
    }

    fun m(word : String) : Int {
        return 0
    }
}