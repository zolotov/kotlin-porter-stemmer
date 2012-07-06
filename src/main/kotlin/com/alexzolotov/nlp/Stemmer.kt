package com.alexzolotov.nlp

import java.util.regex.Pattern

public class Stemmer {

    private val VOWEL = "(?:[aeiou]|(?<![aeiou])y)"
    private val CONSONANT = "(?:[bcdfghjklmnpqrstvwxz]|(?<=[aeiou])y)"
    private val CONSONANT_CVC = "[bcdfghjklmnpqrstvz]"
    private val REDUCED_CONSONANT = "(?:[bcdfghjkmnpqrtvwx]|(?<=[aeiou])y)"


    private val VOWELS_REGEX = Pattern.compile("${VOWEL}+")
    private val CONSONANT_REGEX = Pattern.compile("${CONSONANT}+")
    private val M_REGEX = Pattern.compile("(${VOWEL}+${CONSONANT}+)")

    public fun stem(var word : String): String {
        word = step1(word)
        word = step2(word)
        word = step3(word)
        word = step4(word)
        return step5(word)
    }

    fun step1(word : String) : String {
        return step1c(step1b(step1a(word)));
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

    fun step1b(var word : String) : String {
        val m = m(word)
        if (word.endsWith("eed")) {
            if(m > 0) word = word.substring(0, word.length - 1)
        } else {
            word = when {
                word.endsWith("ed") && word.containsVowel(2) -> word.replaceFirst("ed$", "")
                word.endsWith("ing") && word.containsVowel(3) -> word.replaceFirst("ing$", "")
                else -> return word
            }

            word = when {
                word.endsWith("at") -> "${word}e"
                word.endsWith("bl") -> "${word}e"
                word.endsWith("iz") -> "${word}e"
                word.endsWithPattern("${REDUCED_CONSONANT}{2}") -> word.substring(0, word.length - 1)
                word.endsWithCvc() && m(word) == 1 -> "${word}e"
                else -> word
            }
        }

        return word
    }

    fun step1c(word : String) : String {
        return if(word.endsWith("y") && word.containsVowel(1)) word.replaceFirst("y$", "i") else word
    }

    fun m(word : String) : Int {
        var result = 0
        val matcher = M_REGEX!!.matcher(word)
        while(matcher!!.find()) {
            result++
        }
        word.iterator()
        return result
    }

    fun String.endsWithPattern(pattern : String) : Boolean = Pattern.compile("${pattern}$")!!.matcher(this)!!.find()
    fun String.endsWithCvc() : Boolean = this.endsWithPattern("${CONSONANT}${VOWEL}${CONSONANT_CVC}")
    fun String.containsVowel() : Boolean = VOWELS_REGEX!!.matcher(this)!!.find()
    fun String.containsVowel(postfixCount: Int) : Boolean = this.substring(0, this.length - postfixCount).containsVowel()
    fun String.containsConsonant() : Boolean = CONSONANT_REGEX!!.matcher(this)!!.find()
}