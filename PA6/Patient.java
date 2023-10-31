public class Patient {

    public String name;
    public int priority;

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

		public String toString() {
			return name + " (" + priority + ")";
		}

}
