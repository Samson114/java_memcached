package two;

public class Tbean implements java.io.Serializable
	{
	    private static final long serialVersionUID = 1945562032261336919L;
	    
	    private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	
}
