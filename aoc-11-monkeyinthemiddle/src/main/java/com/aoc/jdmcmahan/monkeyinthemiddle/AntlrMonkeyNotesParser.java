package com.aoc.jdmcmahan.monkeyinthemiddle;

import com.aoc.jdmcmahan.monkeyinthemiddle.antlr.AntlrMonkeyNotesVisitor;
import com.aoc.jdmcmahan.monkeyinthemiddle.antlr.MonkeyNotesLexer;
import com.aoc.jdmcmahan.monkeyinthemiddle.antlr.MonkeyNotesParser;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.MonkeyRegistry;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

public class AntlrMonkeyNotesParser {

    public MonkeyRegistry parse(InputStream input) throws IOException {
        MonkeyNotesLexer lexer = new MonkeyNotesLexer(CharStreams.fromStream(input));
        MonkeyNotesParser parser = new MonkeyNotesParser(new CommonTokenStream(lexer));

        MonkeyNotesParser.NotesContext tree = parser.notes();

        AntlrMonkeyNotesVisitor visitor = new AntlrMonkeyNotesVisitor();
        return visitor.visitNotes(tree);
    }
}
