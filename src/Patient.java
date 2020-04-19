/**
 * Patient class represents individuals who tested positive for COVID19; 
 * an object of this class is one unique patient.
 * 
 * @author cbusc
 *
 */
public class Patient {

	private String gender;
	private int age;
	String ageAsDecade;
	private boolean comorbid;
	private boolean healthcareRelatedExposure;
	private String state;
	
	public Patient(String gender, int age, String ageAsDecade, boolean comorbid, boolean healthcareRelatedExposure, String state)
	{
		this.gender = gender;
		this.age = age;
		this.ageAsDecade = ageAsDecade;
		this.comorbid = comorbid;
		this.healthcareRelatedExposure = healthcareRelatedExposure;
		this.state = state;
	}
	/**
	 * Gets gender
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Gets age
	 * @return age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * Gets ageAsDecade
	 * @return ageAsDecade
	 */
	public String getAgeAsDecade() {
		return ageAsDecade;
	}
	/**
	 * Gets whether or not has co-morbid diseases
	 * @return comorbid
	 */
	public boolean getComorbid() {
		return comorbid;
	}
	/**
	 * Gets whether or not exposure was healthcare related
	 * @return healthcareRelatedExposure
	 */
	public boolean getHealthcareRelatedExposure() {
		return healthcareRelatedExposure;
	}
	/**
	 * Gets state
	 * @return state
	 */
	public String getState() {
		return state;
	}
}
