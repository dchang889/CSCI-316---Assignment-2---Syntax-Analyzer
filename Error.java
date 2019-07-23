public class Error {

	private LexicalAnalyzer la;
	
	public Error(LexicalAnalyzer laObj) {
		la = laObj;
	}
	
	public void error(int n) {
		int i = la.getCurrCharPosNum();
		String errorLine = "Error: ";
		String errorWS="";
		int j = 0;
		while(j<i) {
			errorWS+=" ";
			j++;
		}

		switch (n) {
		case 1:
			errorLine += "expecting procedure.";
			break;
		case 2: 
			errorLine += "expecting ident.";
			break;
		case 3: 
			errorLine += "expecting is.";
			break;
		case 4: 
			errorLine += "expecting begin.";
			break;
		case 5: 
			errorLine += "expecting end.";
			break;
		case 6: 
			errorLine += "expecting semi-colon.";
			break;
		case 7: 
			errorLine += "expecting EOI.";
			break;
		case 8: 
			errorLine += "expecting colon.";
			break;
		case 9: 
			errorLine += "expecting boolean or integer.";
			break;
		case 10: 
			errorLine += "expecting becomes.";
			break;
		case 11: 
			errorLine += "expecting then.";
			break;
		case 12: 
			errorLine += "expecting if.";
			break;
		case 13: 
			errorLine += "expecting loop.";
			break;
		case 14: 
			errorLine += "expecting (.";
			break;
		case 15: 
			errorLine += "expecting ).";
			break;
		case 16: 
			errorLine += "expecting newline.";
			break;
		case 17: 
			errorLine += "expecting null, ident, if, while, get, put, or newline.";
			break;
		case 18: 
			errorLine += "expecting (, ident, numlit, true, or false.";
			break;	
		case 19:
			errorLine += "expecting comma.";
			break;
		default:
			break;
		}
		System.out.println(errorWS+"^");
		System.out.println(errorLine);
		System.exit(0);
	}



}
