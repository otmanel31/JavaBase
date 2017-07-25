package designPatterMoyen;

import java.nio.file.attribute.AclEntry.Builder;
import java.util.ArrayList;
import java.util.Arrays;

public class Computer {
	private String cpu;
	private int ramSizeGo;
	private String ecran;
	private boolean clavier;
	private boolean souris;
	private String carteResaux;
	private String[] disquesDur;

	public String getCpu() {return cpu;}
	public void setCpu(String cpu) {this.cpu = cpu;}
	public int getRamSizeGo() {return ramSizeGo;}
	public void setRamSizeGo(int ramSizeGo) {this.ramSizeGo = ramSizeGo;}
	public String getEcran() {return ecran;}
	public void setEcran(String ecran) {this.ecran = ecran;}
	public boolean isClavier() {return clavier;}
	public void setClavier(boolean clavier) {this.clavier = clavier;}
	public boolean isSouris() {return souris;}
	public void setSouris(boolean souris) {this.souris = souris;}
	public String getCarteResaux() {return carteResaux;}
	public void setCarteResaux(String carteResaux) {this.carteResaux = carteResaux;}
	public String[] getDisquesDur() {return disquesDur;}
	public void setDisquesDur(String[] disquesDur) {this.disquesDur = disquesDur;}


	private Computer(String cpu, int ramSize) {
		setCpu(cpu);
		setRamSizeGo(ramSize);
	}
	
	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", ramSizeGo=" + ramSizeGo + ", ecran=" + ecran + ", clavier=" + clavier
				+ ", souris=" + souris + ", carteResaux=" + carteResaux + ", disquesDur=" + Arrays.toString(disquesDur)
				+ "]";
	}

	public static class Builder{
		private String buildCpu;
		private int buildRamSizeGo;
		
		private String buildEcran;
		private boolean buildClavier;
		private boolean buildSouris;
		private String buildCarteResaux;
		private ArrayList<String> buildDisquesDur;
		
		public Builder(String cpu, int ramSizeGo) {
			this.buildCpu = cpu;
			this.buildRamSizeGo = ramSizeGo;
			this.buildDisquesDur = new ArrayList<>();
			this.buildCarteResaux = "none";
			this.buildClavier = false;
			this.buildEcran = "none";
			this.buildSouris = false;
		}
		// ajouter un ecran a un clavier qui sera construit
		public Builder addEcran(String ecran) {
			this.buildEcran  = ecran;
			return this;
		}
		public Builder addSouris() {
			this.buildSouris  = true;
			return this;
		}
		public Builder addClavier() {
			this.buildClavier  = true;
			return this;
		}
		public Builder addCarteReseaux(String carte) {
			this.buildCarteResaux  = carte;
			return this;
		}
		public Builder addDdr(String ddr) {
			this.buildDisquesDur.add(ddr);
			return this;
		}
		
		public Computer build() {
			Computer c = new Computer(this.buildCpu, this.buildRamSizeGo);
			c.carteResaux = this.buildCarteResaux;
			c.clavier = this.buildClavier;
			c.disquesDur = this.buildDisquesDur.toArray(new String[0]);
			c.souris = this.buildSouris;
			c.clavier = this.buildClavier;
			return c;
		}
	}
}
