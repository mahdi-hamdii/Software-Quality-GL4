public class Button
{
    private Equipement _equipement;
    private boolean _state;

    public Button(Equipement equipement)
    {
        _equipement = equipement;
    }

    public void toggle()
    {
        _state = !_state;
        boolean buttonOn = _state;
        if (buttonOn) {
            _equipement.turnOn();
        } else {
            _equipement.turnOff();
        }
    }

}
