package TreeObjects;

public class snames {
    String[] data;

    public  String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getRandomSname() {
        int random = (int) (Math.random() * data.length);
        String sname = data[random];
        return sname;
    }
}
