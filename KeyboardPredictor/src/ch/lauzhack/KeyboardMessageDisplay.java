package ch.lauzhack;

import com.logitech.gaming.LogiLED;

public class KeyboardMessageDisplay {
	private final int PULSEDURATION = 1000;
	private final int TIMEBETWEENCHARS = 1500;
	public void displayCharacterPulse(char c){
		System.out.println("displaying " + c); 
		Thread t = new Thread(){
			public void run(){
				displayCharPulse(LetterToKeyboardValue(c), 100, 0,0, PULSEDURATION);
			}
		};
		t.start();
	}
	
	public void displayMessagePulse(String message) {
		char lastChar = '0';
		char[] characters = message.toCharArray();
		
		for(int i = 0; i < characters.length; ++i) {
			if(characters[i] == lastChar) {
				wait(PULSEDURATION);
			} else {
				wait(TIMEBETWEENCHARS);
			}
			
			displayCharacterPulse(characters[i]);
			lastChar = characters[i];
		}
		wait(PULSEDURATION);
	}
	
	private void displayCharPulse(int key, int redPercentage, int greenPercentage, int bluePercentage, int pulseDuration) {
		LogiLED.LogiLedPulseSingleKey(key, 50, 50, 50, redPercentage, greenPercentage, bluePercentage, pulseDuration/2, false);
		wait(pulseDuration/2);
		LogiLED.LogiLedPulseSingleKey(key, redPercentage, greenPercentage, bluePercentage, 50, 50, 50, pulseDuration/2, false);
		wait(pulseDuration/2);
	}
	
	public void ShowLetter(char character, int redPercentage, int greenPercentage, int bluePercentage) {
		LogiLED.LogiLedSetLightingForKeyWithKeyName(LetterToKeyboardValue(character), redPercentage, greenPercentage, bluePercentage);
	}
	
	public void showHeart(boolean show) {
		if(show) {
		LogiLED.LogiLedPulseSingleKey(LogiLED.SPACE, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.N, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.J, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.I, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.EIGHT, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.Y, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.FIVE, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.R, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.F, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.V, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);

		LogiLED.LogiLedPulseSingleKey(LogiLED.U, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.H, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.B, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.G, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);
		LogiLED.LogiLedPulseSingleKey(LogiLED.T, 0, 0, 0, 100, 0, 0, PULSEDURATION, show);		
		} else {
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.SPACE);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.N);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.J);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.I);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.EIGHT);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.Y);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.FIVE);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.R);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.F);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.V);

			LogiLED.LogiLedStopEffectsOnKey(LogiLED.U);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.H);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.B);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.G);
			LogiLED.LogiLedStopEffectsOnKey(LogiLED.T);		
		}
	}
	
	private void wait(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int LetterToKeyboardValue(char c) {
		if(c == ' ')
			return LogiLED.SPACE;
		if(c <= 90 && c >= 65) {
			c += 32;
		}
		if(c < 97 || c > 122){
			return -1;
		}
		
		switch(c){
			case 'q': return LogiLED.Q;
			case 'w': return LogiLED.W;
			case 'e': return LogiLED.E;
			case 'r': return LogiLED.R;
			case 't': return LogiLED.T;
			case 'y': return LogiLED.Y;
			case 'u': return LogiLED.U;
			case 'i': return LogiLED.I;
			case 'o': return LogiLED.O;
			case 'p': return LogiLED.P;
			
			case 'a': return LogiLED.A;
			case 's': return LogiLED.S;
			case 'd': return LogiLED.D;
			case 'f': return LogiLED.F;
			case 'g': return LogiLED.G;
			case 'h': return LogiLED.H;
			case 'j': return LogiLED.J;
			case 'k': return LogiLED.K;
			case 'l': return LogiLED.L;
			
			case 'z': return LogiLED.Z;
			case 'x': return LogiLED.X;
			case 'c': return LogiLED.C;
			case 'v': return LogiLED.V;
			case 'b': return LogiLED.B;
			case 'n': return LogiLED.N;
			case 'm': return LogiLED.M;
			default: return -1;
		}
	}
}
