public class Coche {

	public String marca;
	public String modelo;
	public int maxVelocidad = 120;
	public String tipocombustible;
	public int velocimetro;
	public int tacometro;
	public String marcha;
	public boolean marchaAtras = false;
	public int gradosFinales = 0;

	public Coche(String marca, String modelo, String tipocombustible) {
		this.marca = marca;
		this.modelo = modelo;
		this.tipocombustible = tipocombustible;
	}

	public void arrancar() {
		if (this.tacometro == 0) {
			this.tacometro = 1000;
			this.marcha = "N";
			System.out.println("El coche está recién encendido.");
		} else {
			System.out.println("El vehiculo está encendido desde hace un rato");
		}
	}

	public boolean revisarMarchaAtras() {
		return this.marchaAtras;
	}

	public void acelerar() {

		if (this.velocimetro >= this.maxVelocidad) {
			System.out.println("Velocidad máxima alcanzada\n");
		} else {
			if (this.revisarMarchaAtras() && (this.velocimetro >= 25)) {
				System.out.println("Máxima velocidad marcha atrás.\n");
			} else {
				this.velocimetro += 10;
				if (!this.revisarMarchaAtras()) {
					calculateTachometer();
				}
			}
		}
	}

	private void calculateTachometer() {

		double gearVel = (float) this.velocimetro / 25;
		double gearVelInt = Math.floor(gearVel);
		this.marcha = Integer.toString((int) (gearVelInt) + 1);
		double fract = gearVel - gearVelInt;
		this.tacometro = (int) ((fract * 100 * 1500) / 100) + 1000;
	}

	public void frenar() {

		if (this.velocimetro == 0) {
			System.out.println("El vehículo está detenido\n");
		} else {
			this.velocimetro -= 10;
			if ((this.velocimetro != 0) && !this.revisarMarchaAtras()) {
				calculateTachometer();
			} else {
				if (!this.revisarMarchaAtras()) {
					this.tacometro = 1000;
					this.marcha = "N";
				}
			}
		}
	}

	public void giroRueda(int grados) {

		if ((grados + this.gradosFinales) <= 45 && (grados + this.gradosFinales) >= -45) {
			this.gradosFinales += grados;
		} else {
			System.out.println("No se puede girar más \n");
		}

//		OTRA MANERA DE HACER ESTO
//
//		if ((grados + this.gradosFinales) <= 45) {
//			if ((grados + this.gradosFinales) >= -45) {
//				this.gradosFinales += grados;
//			} else {
//				System.out.println("No se puede girar más");
//		} else {
//			System.out.println("No se puede girar más");
//		}

//        OTRA MANERA DE HACERLO

//	public int giroRueda (int grados) {
//
//		if (this.gradosFinales + grados == 45) {
//			System.out.println("máximos grados de giro hacia la derecha");
//		} else if (this.gradosFinales + grados == -45) {
//			System.out.println("máximos grados de giro hacia la izquierda");
//		} else {
//			if (this.gradosFinales + grados <= 45 && this.gradosFinales >= -45) {
//				this.gradosFinales = grados + this.gradosFinales;
//			} else {
//				System.out.println("grados máximos de giro alcanzados");
//			}
//		}
//		return grados;
	}

	public String mostrargiro() {

		String Giro = "";

		if (this.gradosFinales == 0) {
			Giro = "Recto";
		} else {
			if (this.gradosFinales > 0) {
				Giro = "Derecha";
			} else {
				Giro = "Izquierda";
			}
		}
		return Giro;
	}

	public void cambiarMarchaAtras(boolean marchasActuales) {

		if (this.velocimetro == 0) {
			if (this.revisarMarchaAtras() == marchasActuales) {
				System.out.println("El vehículo ya tiene esa marcha\n");
			} else {
				this.marchaAtras = marchasActuales;
				if (this.marchaAtras == true) {

					this.marcha = "R";
					System.out.println("Está la R puesta\n");
				} else {
					this.marcha = "N";
					System.out.println("Está la N puesta\n");
				}
			}
		}

	}

	public void mostrardetalles() {
		System.out.println("\n***DETALLES DEL VEHÍCULO***");
		arrancar();
		System.out.println("La marca del coche es " + this.marca + ".");
		System.out.println("El modelo del coche es " + this.modelo + ".");
		System.out.println("El sistema de alimentación del coche es: " + tipocombustible);
		System.out.println("La velocidad del coche es " + this.velocimetro + ".");
		System.out.println("Las revoluciones del coche son " + this.tacometro + ".");
		System.out.println("El coche tiene puesta la " + this.marcha + " marcha.");
		System.out.println("El coche va " + this.mostrargiro());
		System.out.println("Los grados de las ruedas son " + this.gradosFinales);
		System.out.println("La velocidad máxima predefinida del coche por normativa española es " + this.maxVelocidad + " km/h.\n");
	}
}
