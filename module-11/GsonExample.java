import com.google.gson.Gson;

class Student {
    String name;
    int age;
}

public class GsonExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Student student = new Student();
        student.name = "Alex";
        student.age = 21;

        String json = gson.toJson(student);
        System.out.println(json);

        Student parsedStudent = gson.fromJson(json, Student.class);
        System.out.println(parsedStudent.name + " - " + parsedStudent.age);
    }
}
