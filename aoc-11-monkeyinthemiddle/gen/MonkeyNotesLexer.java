// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MonkeyNotesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MONKEY_LABEL=1, STARTING_ITEMS_LABEL=2, OPERATION_LABEL=3, TEST_LABEL=4, 
		TEST_TRUE_LABEL=5, TEST_FALSE_LABEL=6, INTEGER_LITERAL=7, DIVISIBLE_BY_TEST=8, 
		THROW_ACTION=9, COLON=10, COMMA=11, ASSIGN=12, PLUS=13, MINUS=14, MUL=15, 
		DIV=16, NEW=17, OLD=18, WS=19, UNMATCHED=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"MONKEY_LABEL", "STARTING_ITEMS_LABEL", "OPERATION_LABEL", "TEST_LABEL", 
			"TEST_TRUE_LABEL", "TEST_FALSE_LABEL", "INTEGER_LITERAL", "DIVISIBLE_BY_TEST", 
			"THROW_ACTION", "COLON", "COMMA", "ASSIGN", "PLUS", "MINUS", "MUL", "DIV", 
			"NEW", "OLD", "WS", "UNMATCHED", "Digits"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Monkey'", "'Starting items'", "'Operation'", "'Test'", "'If true'", 
			"'If false'", null, "'divisible by'", "'throw to monkey'", "':'", "','", 
			"'='", "'+'", "'-'", "'*'", "'/'", "'new'", "'old'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MONKEY_LABEL", "STARTING_ITEMS_LABEL", "OPERATION_LABEL", "TEST_LABEL", 
			"TEST_TRUE_LABEL", "TEST_FALSE_LABEL", "INTEGER_LITERAL", "DIVISIBLE_BY_TEST", 
			"THROW_ACTION", "COLON", "COMMA", "ASSIGN", "PLUS", "MINUS", "MUL", "DIV", 
			"NEW", "OLD", "WS", "UNMATCHED"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MonkeyNotesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MonkeyNotesLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0014\u00a7\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u0098\b\u0012\u000b"+
		"\u0012\f\u0012\u0099\u0001\u0012\u0001\u0012\u0001\u0013\u0004\u0013\u009f"+
		"\b\u0013\u000b\u0013\f\u0013\u00a0\u0001\u0014\u0004\u0014\u00a4\b\u0014"+
		"\u000b\u0014\f\u0014\u00a5\u0001\u00a0\u0000\u0015\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0000\u0001\u0000\u0002\u0003\u0000\t\n\r\r  "+
		"\u0001\u000009\u00a8\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0001+\u0001"+
		"\u0000\u0000\u0000\u00032\u0001\u0000\u0000\u0000\u0005A\u0001\u0000\u0000"+
		"\u0000\u0007K\u0001\u0000\u0000\u0000\tP\u0001\u0000\u0000\u0000\u000b"+
		"X\u0001\u0000\u0000\u0000\ra\u0001\u0000\u0000\u0000\u000fc\u0001\u0000"+
		"\u0000\u0000\u0011p\u0001\u0000\u0000\u0000\u0013\u0080\u0001\u0000\u0000"+
		"\u0000\u0015\u0082\u0001\u0000\u0000\u0000\u0017\u0084\u0001\u0000\u0000"+
		"\u0000\u0019\u0086\u0001\u0000\u0000\u0000\u001b\u0088\u0001\u0000\u0000"+
		"\u0000\u001d\u008a\u0001\u0000\u0000\u0000\u001f\u008c\u0001\u0000\u0000"+
		"\u0000!\u008e\u0001\u0000\u0000\u0000#\u0092\u0001\u0000\u0000\u0000%"+
		"\u0097\u0001\u0000\u0000\u0000\'\u009e\u0001\u0000\u0000\u0000)\u00a3"+
		"\u0001\u0000\u0000\u0000+,\u0005M\u0000\u0000,-\u0005o\u0000\u0000-.\u0005"+
		"n\u0000\u0000./\u0005k\u0000\u0000/0\u0005e\u0000\u000001\u0005y\u0000"+
		"\u00001\u0002\u0001\u0000\u0000\u000023\u0005S\u0000\u000034\u0005t\u0000"+
		"\u000045\u0005a\u0000\u000056\u0005r\u0000\u000067\u0005t\u0000\u0000"+
		"78\u0005i\u0000\u000089\u0005n\u0000\u00009:\u0005g\u0000\u0000:;\u0005"+
		" \u0000\u0000;<\u0005i\u0000\u0000<=\u0005t\u0000\u0000=>\u0005e\u0000"+
		"\u0000>?\u0005m\u0000\u0000?@\u0005s\u0000\u0000@\u0004\u0001\u0000\u0000"+
		"\u0000AB\u0005O\u0000\u0000BC\u0005p\u0000\u0000CD\u0005e\u0000\u0000"+
		"DE\u0005r\u0000\u0000EF\u0005a\u0000\u0000FG\u0005t\u0000\u0000GH\u0005"+
		"i\u0000\u0000HI\u0005o\u0000\u0000IJ\u0005n\u0000\u0000J\u0006\u0001\u0000"+
		"\u0000\u0000KL\u0005T\u0000\u0000LM\u0005e\u0000\u0000MN\u0005s\u0000"+
		"\u0000NO\u0005t\u0000\u0000O\b\u0001\u0000\u0000\u0000PQ\u0005I\u0000"+
		"\u0000QR\u0005f\u0000\u0000RS\u0005 \u0000\u0000ST\u0005t\u0000\u0000"+
		"TU\u0005r\u0000\u0000UV\u0005u\u0000\u0000VW\u0005e\u0000\u0000W\n\u0001"+
		"\u0000\u0000\u0000XY\u0005I\u0000\u0000YZ\u0005f\u0000\u0000Z[\u0005 "+
		"\u0000\u0000[\\\u0005f\u0000\u0000\\]\u0005a\u0000\u0000]^\u0005l\u0000"+
		"\u0000^_\u0005s\u0000\u0000_`\u0005e\u0000\u0000`\f\u0001\u0000\u0000"+
		"\u0000ab\u0003)\u0014\u0000b\u000e\u0001\u0000\u0000\u0000cd\u0005d\u0000"+
		"\u0000de\u0005i\u0000\u0000ef\u0005v\u0000\u0000fg\u0005i\u0000\u0000"+
		"gh\u0005s\u0000\u0000hi\u0005i\u0000\u0000ij\u0005b\u0000\u0000jk\u0005"+
		"l\u0000\u0000kl\u0005e\u0000\u0000lm\u0005 \u0000\u0000mn\u0005b\u0000"+
		"\u0000no\u0005y\u0000\u0000o\u0010\u0001\u0000\u0000\u0000pq\u0005t\u0000"+
		"\u0000qr\u0005h\u0000\u0000rs\u0005r\u0000\u0000st\u0005o\u0000\u0000"+
		"tu\u0005w\u0000\u0000uv\u0005 \u0000\u0000vw\u0005t\u0000\u0000wx\u0005"+
		"o\u0000\u0000xy\u0005 \u0000\u0000yz\u0005m\u0000\u0000z{\u0005o\u0000"+
		"\u0000{|\u0005n\u0000\u0000|}\u0005k\u0000\u0000}~\u0005e\u0000\u0000"+
		"~\u007f\u0005y\u0000\u0000\u007f\u0012\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0005:\u0000\u0000\u0081\u0014\u0001\u0000\u0000\u0000\u0082\u0083\u0005"+
		",\u0000\u0000\u0083\u0016\u0001\u0000\u0000\u0000\u0084\u0085\u0005=\u0000"+
		"\u0000\u0085\u0018\u0001\u0000\u0000\u0000\u0086\u0087\u0005+\u0000\u0000"+
		"\u0087\u001a\u0001\u0000\u0000\u0000\u0088\u0089\u0005-\u0000\u0000\u0089"+
		"\u001c\u0001\u0000\u0000\u0000\u008a\u008b\u0005*\u0000\u0000\u008b\u001e"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\u0005/\u0000\u0000\u008d \u0001\u0000"+
		"\u0000\u0000\u008e\u008f\u0005n\u0000\u0000\u008f\u0090\u0005e\u0000\u0000"+
		"\u0090\u0091\u0005w\u0000\u0000\u0091\"\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005o\u0000\u0000\u0093\u0094\u0005l\u0000\u0000\u0094\u0095\u0005"+
		"d\u0000\u0000\u0095$\u0001\u0000\u0000\u0000\u0096\u0098\u0007\u0000\u0000"+
		"\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000"+
		"\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000"+
		"\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0006\u0012\u0000"+
		"\u0000\u009c&\u0001\u0000\u0000\u0000\u009d\u009f\t\u0000\u0000\u0000"+
		"\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000"+
		"\u00a1(\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0001\u0000\u0000\u00a3"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"*\u0001\u0000\u0000\u0000\u0004\u0000\u0099\u00a0\u00a5\u0001\u0000\u0001"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}