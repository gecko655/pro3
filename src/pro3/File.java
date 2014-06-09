package pro3;

public class File extends Entry {

	private String name;
	private int size;

	public File(String name, int size){
		this.name=name;
		this.size=size;
	}
	@Override
	protected void printList(String prefix) {
		System.out.println(prefix+"/"+this);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getName() {
		return name;
	}

}
