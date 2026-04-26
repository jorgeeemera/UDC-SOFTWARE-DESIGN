package e3;

import java.util.Objects;

public record Property(PropertyType propertyType, String catastro, String direccion, String cp, int metros, int room, int bath) {

    public Property{

        Objects.requireNonNull(catastro, "El catastro no puede ser nulo");
        Objects.requireNonNull(direccion, "La direccion no puede ser nula");
        Objects.requireNonNull(cp, "El código postal no puede ser nulo");

        if (metros<0){
            throw new IllegalArgumentException("El número de metros no puede ser negativo");
        }
        if (room<0){
            throw new IllegalArgumentException("El número de habitaciones no puede ser negativo");
        }
        if (bath<0){
            throw new IllegalArgumentException("El número de baños no puede ser negativo");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Property property = (Property) obj;
        return catastro.equals(property.catastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catastro);
    }

    @Override
    public String toString() {
        return propertyType + "\n" + catastro + "\n" + direccion + "\n" + cp + "\n"
                + metros + " meters, " + room + " rooms, " + bath + " bathrooms\n";
    }
}
