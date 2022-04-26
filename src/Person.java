public class Person {
    private String personName;
    private String personID;    //a random 32-character string assigned on creation
    private PersonAvailabilityWeek availabilityWeek;
    //constructor used to create a brand-new person. to re-use from file, will add another constructor
    Person(String nameToSet) {
        availabilityWeek = new PersonAvailabilityWeek();
        personName = nameToSet;
        personID = "";
        String choices = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        for (int i = 0; i < 32; i++) {
            int rndChoice = (int) ( Math.random() * 61 );
            personID += choices.charAt(rndChoice);
        }

        System.out.println("Added person.");
        System.out.println("Name: " + personName);
        System.out.println("ID: " + personID + '\n');
    }

    public String GetName() {
        return personName;
    }
    public void SetName(String nameToSet) {
        personName = nameToSet;
    }

    public String GetID() {
        return personID;
    }

    public boolean CompareID(Person toCompareWith) {
        //a function to compare "this" to the parameter
        return (this.personID.equals(toCompareWith.GetID()) || this.personName.equals(toCompareWith.GetName()));
    }

    //function which checks if the calling object has the same name as the parameter
    //example: foo.is("bar")
    public boolean is(String name) {
        return (personName.equals(name));
    }
}
