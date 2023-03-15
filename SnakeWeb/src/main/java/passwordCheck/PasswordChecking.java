package passwordCheck;

public class PasswordChecking {
	int size;
	int maj;
	int min;
	boolean validSize;
	boolean validMin;
	boolean validMaj;
	public PasswordChecking(){
		this.size=0;
		this.maj=0;
		this.min=0;
		this.validSize=false;
		this.validMaj=false;
		this.validMin=false;
	}
	public boolean check(String str) {
		sizeCheck(str);
		majMinCheck(str);
		boolean globalCheck=true;
		if(this.size>=8) {
			this.validSize=true;
		}
		else {
			globalCheck=false;
		}
		if(this.min>=2) {
			this.validMin=true;
		}
		else {
			globalCheck=false;
		}
		if(this.maj>=2) {
			this.validMaj=true;
		}
		else {
			globalCheck=false;
		}
		return globalCheck;
	}
	public void sizeCheck(String str){
		this.size=str.length();
	}
	public void majMinCheck(String str){
		for(int i=0;i<str.length();i++) {
			if (Character.isUpperCase(str.charAt(i))) this.maj++;
		    if (Character.isLowerCase(str.charAt(i))) this.min++;
		}
	}
}
