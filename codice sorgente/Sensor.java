public class Sensor {
    private String type;
    private Boolean state;

    public Sensor(String type, boolean state)
    {
        this.type = type;
        this.state = state;
    }

    public String toString () {
        return "Sensor"+type+" : "+ state.toString();
    }

    public String getType()
    {
        return type;
    }

    public boolean getState()
    {
        return state;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public boolean isEqual(String type)
    {
        return type == this.type;
    }
}
