package pro3;

public abstract class Entry {
	public abstract int getSize();
	public abstract String getName();
	public Entry add(Entry entry) throws FileTreatmentException{
		return null;
		
	}
	public void printlist(){
		printList("");
	}
	protected abstract void printList(String prefix);
	
	public String toString(){
		return getName() +" ("+getSize()+")";
	}
	

}
