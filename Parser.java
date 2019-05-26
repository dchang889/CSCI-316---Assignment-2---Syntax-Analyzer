public class Parser {
	private LexicalAnalyzer la;
	private Error e;
	private Symbol nextTokenSym;

	public Parser (LexicalAnalyzer laObj) {
		la = laObj;
		e = new Error(la);
	}

	public void Start() {
		Token t = la.getToken();
		if (t == null) 
			e.error(1);

		nextTokenSym = t.getSym();

		if(nextTokenSym == Symbol.PROCSYM) {
			updateSym();
			if(nextTokenSym == Symbol.IDENT) {
				updateSym();
				if(nextTokenSym == Symbol.ISSYM) {
					updateSym();
					decpart();
					if(nextTokenSym == Symbol.BEGINSYM) {
						updateSym();
						seqofstmt();
						if (nextTokenSym == Symbol.ENDSYM) {
							updateSym();
							if (nextTokenSym == Symbol.SEMICOLON) {
								updateSym();
								if (nextTokenSym == Symbol.EOI) {
									System.out.println("The program is syntactically correct.");
								}else e.error(7);
							}else e.error(6);
						}else e.error(5);
					}else e.error(4);
				}else e.error(3);
			}else e.error( 2);
		}else e.error(1);
	}

	private void seqofstmt() {
		statement();
		while(nextTokenSym == Symbol.NULLSYM || nextTokenSym == Symbol.IDENT || nextTokenSym == Symbol.IFSYM || nextTokenSym == Symbol.WHILESYM || nextTokenSym == Symbol.GETSYM || nextTokenSym==Symbol.PUTSYM || nextTokenSym == Symbol.NEWLINE ) {
			statement();
		}
	}

	private void decpart() {
		objectdec();
		while (nextTokenSym == Symbol.IDENT) {
			objectdec();
		}
	}

	private void objectdec() {

		identlist();

		if (nextTokenSym == Symbol.COLON) {
			updateSym();
			if(nextTokenSym == Symbol.BOOLSYM || nextTokenSym == Symbol.INTSYM) {
				updateSym();
				if (nextTokenSym == Symbol.SEMICOLON) {
					updateSym();
				}else e.error(6);
			}else e.error(9);
		}else e.error(8);
	}
	

	private void identlist() {
		if (nextTokenSym == Symbol.IDENT) {
			updateSym();
			while (nextTokenSym == Symbol.COMMA) {
				updateSym();
				identlist();
			}
			if(nextTokenSym == Symbol.IDENT) e.error(19);
		}else e.error(2);
	}

	private void statement() {

		switch (nextTokenSym) {

		case NULLSYM:
			updateSym();
			if (nextTokenSym == Symbol.SEMICOLON) {
				updateSym();
			}else e.error(6);
			break;

		case IDENT:
			updateSym();
			if (nextTokenSym == Symbol.BECOMES) {
				updateSym();
				expression();
				if (nextTokenSym == Symbol.SEMICOLON) {
					updateSym();
				}else e.error(6);
			}else e.error(10);
			break;

		case IFSYM:
			updateSym();
			condition();
			if (nextTokenSym == Symbol.THENSYM) {
				updateSym();
				seqofstmt();
				if (nextTokenSym == Symbol.ELSESYM) {
					updateSym();
					seqofstmt();
				}
				if (nextTokenSym == Symbol.ENDSYM) {
					updateSym();
					if(nextTokenSym == Symbol.IFSYM) {
						updateSym();
						if (nextTokenSym == Symbol.SEMICOLON) {
							updateSym();
						}else e.error(6);
					}else e.error(12);
				}else e.error(5);
			}else e.error(11);
			break;

		case WHILESYM:
			updateSym();
			condition();
			if (nextTokenSym == Symbol.LOOPSYM) {
				updateSym();
				seqofstmt();
				if (nextTokenSym == Symbol.ENDSYM) {
					updateSym();
					if (nextTokenSym == Symbol.LOOPSYM) {
						updateSym();
						if(nextTokenSym == Symbol.SEMICOLON) {
							updateSym();
						}else e.error(6);
					}else e.error(13);
				}else e.error(5);
			}else e.error(13);	
			break;

		case GETSYM:
		case PUTSYM:
			updateSym();
			if (nextTokenSym == Symbol.LPAREN) {
				updateSym();	
				identlist();
				if (nextTokenSym == Symbol.RPAREN) {
					updateSym();
					if (nextTokenSym == Symbol.SEMICOLON) {
						updateSym();
					}else e.error(6);
				}else e.error(15);
			}else e.error(14);
			break;

		case NEWLINE:
			updateSym();
			if (nextTokenSym == Symbol.SEMICOLON) {
				updateSym();
			}else e.error(6);
			break;

		default:
			e.error(17);
			break;
		}
	}

	
	private void condition() {
		expression();
	}
	

	private void expression() {
		simplexpr();

		if( nextTokenSym == Symbol.EQL || nextTokenSym == Symbol.NEQ || nextTokenSym == Symbol.LSS || nextTokenSym == Symbol.LEQ || nextTokenSym == Symbol.GTR || nextTokenSym == Symbol.GEQ) {
			updateSym();
			simplexpr();
		}

	}

	private void simplexpr() {
		term();
		while (nextTokenSym == Symbol.PLUS || nextTokenSym == Symbol.MINUS) {
			updateSym();
			term();
		}
	}

	private void term() {
		primary();
		while (nextTokenSym == Symbol.TIMES || nextTokenSym == Symbol.SLASH || nextTokenSym == Symbol.REMSYM) {
			updateSym();
			primary();
		}
	}

	private void primary() {

		switch (nextTokenSym) {

		case LPAREN:
			updateSym();
			expression();
			if(nextTokenSym == Symbol.RPAREN) {
				updateSym();	
			}else e.error(15);
			break;

		case IDENT:
		case NUMLIT:
		case TRUESYM:
		case FALSESYM:
			updateSym();
			break;

		default:
			e.error(18);
			break;
		}
	}
	
	private void updateSym() {
		nextTokenSym = la.getToken().getSym();
	}

}
