package models;

public enum CategoriaRecurso {
    LIBRO,
    REVISTA,
    AUDIOLIBRO,
    ENCICLOPEDIA,
    OTRO;

    public static void mostrarCategorias() {
        System.out.println("Categorías disponibles:");
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            System.out.println("- " + categoria.name());
        }
    }

    public static CategoriaRecurso desdeString(String input) {
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            if (categoria.name().equalsIgnoreCase(input.trim())) {
                return categoria;
            }
        }
        return OTRO;
    }

}
