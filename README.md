# CSCI-316---Assignment-2---Syntax-Analyzer
Implementation of a recursive descent parser for the Ada Language Subset 1 (ADALS1) language.

The program takes the input file, "input.txt," and has it processed by the Lexical Analyzer.
The Lexical Analyzer Class creates an ArrayList of the Lexical Units from the input file.
Then, the program uses a Syntax Parser to recognize the language as ADALS1.
If the Syntax Praser identifies a grammatical error in the input file, it will identify and print to console the error as well as indicate the location at which the error may have occured.
If no errors were identified, the program will print to console, "The program is syntactically correct."
