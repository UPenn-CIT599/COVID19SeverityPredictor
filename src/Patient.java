/**
 * Patient class represents individuals who tested positive for COVID19; 
 * an object of this class is one unique patient.
 * 
 * @author cbusc
 *
 */
public class Patient {


	private double age;
	private boolean gender;
	private boolean comorbidity;
	private boolean healthcareRelatedExposure;
	private boolean currentSmoker;
	private boolean respiratoryRateGreaterThan24;
	private boolean temperatureGreaterThan37;
	private boolean groundGlassOpacity;
	private double wbc;
	private double lymphocyteCount;
	private double platelets;
	private double albumin;
	private double lactateDehydrogenase;
	private double troponinI;
	private double dDimer;
	private double ferritin;
	private double interleukin6;
	
	private String outcome;
	
	public Patient(boolean gender, double age, boolean comorbid, boolean healthcareRelatedExposure, 
			String outcome, boolean currentSmoker, boolean respiratoryRateGreaterThan24, boolean temperatureGreaterThan37, 
			double wbc, double lymphocyteCount, double platelets, double albumin, double lactateDehydrogenase, double troponinI, 
			double dDimer, double ferritin, double interleukin6, boolean groundGlassOpacity)
	{
		this.gender = gender;
		this.age = age;
		this.comorbidity = comorbid;
		this.healthcareRelatedExposure = healthcareRelatedExposure;
		this.outcome = outcome;
		this.currentSmoker = currentSmoker;
		this.respiratoryRateGreaterThan24 = respiratoryRateGreaterThan24;
		this.temperatureGreaterThan37 = temperatureGreaterThan37;
		this.wbc = wbc;
		this.lymphocyteCount = lymphocyteCount;
		this.platelets = platelets;
		this.albumin = albumin;
		this.lactateDehydrogenase = lactateDehydrogenase;
		this.troponinI = troponinI;
		this.dDimer = dDimer;
		this.ferritin = ferritin;
		this.interleukin6 = interleukin6;
		this.groundGlassOpacity = groundGlassOpacity;
	}
	/**
	 * Gets gender
	 * @return gender
	 */
	public boolean getGender() {
		return gender;
	}
	/**
	 * Gets age
	 * @return age
	 */
	public double getAge() {
		return age;
	}
	/**
	 * Gets whether or not has co-morbid diseases
	 * @return comorbid
	 */
	public boolean isComorbid() {
		return comorbidity;
	}
	/**
	 * Gets whether or not exposure was healthcare related
	 * @return healthcareRelatedExposure
	 */
	public boolean isHealthcareRelatedExposure() {
		return healthcareRelatedExposure;
	}
	/**
	 * Gets state
	 * @return state
	 */
	public String getOutcome() {
		return outcome;
	}
	public boolean isCurrentSmoker() {
		return currentSmoker;
	}
	public boolean isRespiratoryRateGreaterThan24() {
		return respiratoryRateGreaterThan24;
	}
	public boolean isTemperatureGreaterThan37() {
		return temperatureGreaterThan37;
	}
	public double getWbc() {
		return wbc;
	}
	public double getLymphocyteCount()
	{
		return lymphocyteCount;
	}
	public double getPlatelets() {
		return platelets;
	}
	public double getAlbumin() {
		return albumin;
	}
	public double getLactateDehydrogenase() {
		return lactateDehydrogenase;
	}
	public double getTroponinI() {
		return troponinI;
	}
	public double getdDimer() {
		return dDimer;
	}
	public double getFerritin() {
		return ferritin;
	}
	public double getInterleukin6() {
		return interleukin6;
	}
	public boolean isGroundGlassOpacity() {
		return groundGlassOpacity;
	}
	
}
