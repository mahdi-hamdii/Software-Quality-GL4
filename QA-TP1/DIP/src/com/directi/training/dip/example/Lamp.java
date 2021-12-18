public class Lamp implements Equipement
{
    private int _color;
    @Override
    public void turnOn()
    {
        System.out.println("Lamp turned on");
    }
    @Override
    public void turnOff()
    {
        System.out.println("Lamp turned off");
    }
    @Override
    public int getColor()
    {
        return _color;
    }
    @Override
    public void setColor(int color)
    {
        _color = color;
    }
}
