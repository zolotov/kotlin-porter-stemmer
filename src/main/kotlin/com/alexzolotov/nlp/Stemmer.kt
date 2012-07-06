package com.alexzolotov.nlp

import java.util.regex.Pattern

public class Stemmer {

    private val VOWEL = "(?:[aeiou]|(?<![aeiou])y)"
    private val CONSONANT = "(?:[bcdfghjklmnpqrstvwxz]|(?<=[aeiou])y|^y)"
    private val CONSONANT_CVC = "[bcdfghjklmnpqrstvz]"
    private val REDUCED_CONSONANT = "(?:[bcdfghjkmnpqrtvwx]|(?<=[aeiou])y|^y)"


    private val VOWELS_REGEX = Pattern.compile("${VOWEL}+")
    private val CONSONANT_REGEX = Pattern.compile("${CONSONANT}+")
    private val M_REGEX = Pattern.compile("(${VOWEL}+${CONSONANT}+)")

    public fun stem(var word: String): String {
        if(word.length < 3) {
            return word
        }

        word = step1(word)
        word = step2(word)
        word = step3(word)
        word = step4(word)
        return step5(word)
    }

    fun step1(word: String): String {
        return step1c(step1b(step1a(word)));
    }

    fun step2(word: String): String {
        return when {
            word.ensurePrefixM("ational", 0) -> word.replaceFirst("ational$", "ate")
            word.ensurePrefixM("tional", 0) -> word.replaceFirst("tional$", "tion")
            word.ensurePrefixM("enci", 0) -> word.replaceFirst("enci$", "ence")
            word.ensurePrefixM("anci", 0) -> word.replaceFirst("anci$", "ance")
            word.ensurePrefixM("izer", 0) -> word.replaceFirst("izer$", "ize")
            word.ensurePrefixM("abli", 0) -> word.replaceFirst("abli$", "able")
            word.ensurePrefixM("alli", 0) -> word.replaceFirst("alli$", "al")
            word.ensurePrefixM("entli", 0) -> word.replaceFirst("entli$", "ent")
            word.ensurePrefixM("eli", 0) -> word.replaceFirst("eli$", "e")
            word.ensurePrefixM("ousli", 0) -> word.replaceFirst("ousli$", "ous")
            word.ensurePrefixM("ization", 0) -> word.replaceFirst("ization$", "ize")
            word.ensurePrefixM("ation", 0) -> word.replaceFirst("ation$", "ate")
            word.ensurePrefixM("ator", 0) -> word.replaceFirst("ator$", "ate")
            word.ensurePrefixM("alism", 0) -> word.replaceFirst("alism$", "al")
            word.ensurePrefixM("iveness", 0) -> word.replaceFirst("iveness$", "ive")
            word.ensurePrefixM("fulness", 0) -> word.replaceFirst("fulness$", "ful")
            word.ensurePrefixM("ousness", 0) -> word.replaceFirst("ousness$", "ous")
            word.ensurePrefixM("aliti", 0) -> word.replaceFirst("aliti$", "al")
            word.ensurePrefixM("iviti", 0) -> word.replaceFirst("iviti$", "ive")
            word.ensurePrefixM("biliti", 0) -> word.replaceFirst("biliti$", "ble")
            else -> word
        }
    }

    fun step3(word: String): String {
        return when {
            word.ensurePrefixM("icate", 0) -> word.replaceFirst("icate$", "ic")
            word.ensurePrefixM("ative", 0) -> word.withoutPostfix("ative")
            word.ensurePrefixM("alize", 0) -> word.replaceFirst("alize", "al")
            word.ensurePrefixM("iciti", 0) -> word.replaceFirst("iciti", "ic")
            word.ensurePrefixM("ical", 0) -> word.replaceFirst("ical$", "ic")
            word.ensurePrefixM("ful", 0) -> word.withoutPostfix("ful")
            word.ensurePrefixM("ness", 0) -> word.withoutPostfix("ness")
            else -> word
        }
    }

    fun step4(word: String): String {
        //            (m>1 and (*S or *T)) ION ->     adoption       ->  adopt

        return when {
            word.ensurePrefixM("al", 1) -> word.withoutPostfix("al")
            word.ensurePrefixM("ance", 1) -> word.withoutPostfix("ance")
            word.ensurePrefixM("ence", 1) -> word.withoutPostfix("ence")
            word.ensurePrefixM("er", 1) -> word.withoutPostfix("er")
            word.ensurePrefixM("ic", 1) -> word.withoutPostfix("ic")
            word.ensurePrefixM("able", 1) -> word.withoutPostfix("able")
            word.ensurePrefixM("ible", 1) -> word.withoutPostfix("ible")
            word.ensurePrefixM("ant", 1) -> word.withoutPostfix("ant")
            word.ensurePrefixM("ement", 1) -> word.withoutPostfix("ement")
            word.ensurePrefixM("ment", 1) -> word.withoutPostfix("ment")
            word.ensurePrefixM("ent", 1) -> word.withoutPostfix("ent")
            word.ensurePrefixM("ion", 1) -> word.withoutPostfix("ion")
            word.ensurePrefixM("ou", 1) -> word.withoutPostfix("ou")
            word.ensurePrefixM("ism", 1) -> word.withoutPostfix("ism")
            word.ensurePrefixM("ate", 1) -> word.withoutPostfix("ate")
            word.ensurePrefixM("iti", 1) -> word.withoutPostfix("iti")
            word.ensurePrefixM("ous", 1) -> word.withoutPostfix("ous")
            word.ensurePrefixM("ive", 1) -> word.withoutPostfix("ive")
            word.ensurePrefixM("ize", 1) -> word.withoutPostfix("ize")
            else -> word
        }
    }

    fun step5(word: String): String {
        return step5b(step5a(word))
    }

    fun step1a(word: String): String {
        return when {
            word.endsWith("sses") -> word.replaceFirst("sses$", "ss")
            word.endsWith("ies") -> word.withoutPostfix(2)
            word.endsWith("ss") -> word
            word.endsWith("s") -> word.withoutPostfix(1)
            else -> word
        }
    }

    fun step1b(var word: String): String {
        if (word.ensurePrefixM("eed", 0)) {
            word = word.withoutPostfix(1)
        } else {
            word = when {
                word.endsWith("ed") && word.containsVowel("ed") -> word.withoutPostfix("ed")
                word.endsWith("ing") && word.containsVowel("ing") -> word.withoutPostfix("ing")
                else -> return word
            }

            word = when {
                word.endsWith("at") -> "${word}e"
                word.endsWith("bl") -> "${word}e"
                word.endsWith("iz") -> "${word}e"
                word.endsWithDoubleChars() && word.endsWithPattern("${REDUCED_CONSONANT}") -> word.withoutPostfix(1)
                word.endsWithCvc() && m(word) == 1 -> "${word}e"
                else -> word
            }
        }

        return word
    }

    fun step1c(word: String): String {
        return if(word.endsWith("y") && word.containsVowel("y")) word.replaceFirst("y$", "i") else word
    }

    fun step5a(word: String): String {
        if(word.endsWith("e")) {
            val wordWithoutE = word.withoutPostfix("e")
            val m = m(wordWithoutE)
            if (m > 1 || (m == 1 && !wordWithoutE.endsWithCvc())) {
                 return wordWithoutE
            }
        }
        return word
    }

    fun step5b(word: String): String {
        return if (word.endsWith("ll") && m(word) > 1) word.withoutPostfix("l") else word
    }

    fun m(word: String): Int {
        var result = 0
        val matcher = M_REGEX!!.matcher(word)
        while(matcher!!.find()) {
            result++
        }
        word.iterator()
        return result
    }

    fun String.ensurePrefixM(postfix: String, requiredM: Int): Boolean {
        return this.length >= postfix.length && this.endsWith(postfix) &&
            m(this.withoutPostfix(postfix)) > requiredM
    }

    fun String.withoutPostfix(postfix: String): String = this.withoutPostfix(postfix.length)
    fun String.withoutPostfix(postfixLength: Int): String = this.substring(0, this.length - postfixLength)
    fun String.endsWithDoubleChars(): Boolean = this.length > 1 && this.charAt(this.length - 1) == this.charAt(this.length - 2)
    fun String.endsWithPattern(pattern: String): Boolean = Pattern.compile("${pattern}$")!!.matcher(this)!!.find()
    fun String.endsWithCvc(): Boolean = this.endsWithPattern("${CONSONANT}${VOWEL}${CONSONANT_CVC}")
    fun String.containsVowel(): Boolean = VOWELS_REGEX!!.matcher(this)!!.find()
    fun String.containsVowel(postfix: String): Boolean = this.substring(0, this.length - postfix.length).containsVowel()
    fun String.containsConsonant(): Boolean = CONSONANT_REGEX!!.matcher(this)!!.find()
}