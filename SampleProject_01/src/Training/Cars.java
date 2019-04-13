package Training;

public class Cars {
	private String name;
	private Integer power;

	public Cars (String _name, Integer _power)
	{
		name = _name;
		power = _power;
	}
	public String getName() { return name; }
	public void setName(String _name) { name = _name; }
	public Integer getPower() { return power; }
	public void setPower(Integer _power) { power = _power; }

	@Override
	public String toString() { return getName() + " pwr: " + power; }
}
