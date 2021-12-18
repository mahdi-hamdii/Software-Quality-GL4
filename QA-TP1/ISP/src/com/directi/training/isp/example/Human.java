public class Human implements Worker
{
    @Override
    public String work()
    {
        return "Human works";
    }

    @Override
    public String eat()
    {
        return "Human eats";
    }
}
