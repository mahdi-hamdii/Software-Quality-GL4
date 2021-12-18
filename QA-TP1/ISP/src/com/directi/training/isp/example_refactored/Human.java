public class Human implements IWorker, IEater
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
