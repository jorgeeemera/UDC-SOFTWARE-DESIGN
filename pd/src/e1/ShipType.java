package e1;

public enum ShipType {
    DE("Destructor de Escolta", "Ultraligero"),
    DD("Destructor", "Ultraligero"),
    CL("Crucero Ligero", "Ligero"),
    AV("Portahidros", "Ligero"),
    CA("Crucero Pesado", "Pesado"),
    CV("Portaaviones", "Pesado"),
    BB("Acorazado", "Ultrapesado");

    private final String fullName;
    private final String weightClass;

    ShipType(String fullName, String weightClass) {
        this.fullName = fullName;
        this.weightClass = weightClass;
    }

    public String getFullName() {
        return fullName;
    }

    public String getWeightClass() {
        return weightClass;
    }
}