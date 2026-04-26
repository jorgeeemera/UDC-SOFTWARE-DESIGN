package e3;

public class Ad {

    private final String agencia;
    private final AdType adType;
    private final Property property;
    private final int precio;

    //Constructor
    public Ad(String agencia, Property property, AdType adType, int precio) {

        if (agencia == null || property == null || adType == null) {
            throw new NullPointerException("La agencia, la propiedad y el tipo de anuncio no pueden ser nulos");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.agencia = agencia;
        this.adType = adType;
        this.precio = precio;
        this.property = property;
    }

    //Constructor de copia
    public Ad(Ad copia) {

        if (copia == null) {
            throw new NullPointerException("No se puede copiar un anuncio nulo");
        }
        this.agencia = copia.agencia;
        this.adType = copia.adType;
        this.precio = copia.precio;
        this.property = copia.property;
    }

    //Metodo que compara si dos anuncios corresponden a propiedades iguales
    public boolean isPropertyEqual(Ad copia) {
        return this.property.equals(copia.property);
    }

    //Metodo que calcula si el precio está en un rango normal
    /*public boolean isPriceNormal() {
        //return precio;
    }

    public double priceMetersEuros() {
        //return precio / 100.0;
    }

    public void dropPrice(int precio) {
        //return;
    }

    public int getPriceInEuros() {
        //return precio;
    }*/
}
