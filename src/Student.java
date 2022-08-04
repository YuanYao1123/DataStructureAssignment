public class Student implements Comparable<Student>{
    private String stuName;
    private int age;
    private String major;
    private String country;

    public Student() {
    }

    public Student(String stuName, int age, String major, String country) {
        this.stuName = stuName;
        this.age = age;
        this.major = major;
        this.country = country;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student stu) {
        int flag=-1;
        flag= stuName.compareTo(stu.stuName);
        return flag;
    }
}
