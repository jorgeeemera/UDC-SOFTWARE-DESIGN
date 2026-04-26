package e1;

public class StringGames {
    public static String bestCharacters(String s1, String s2) {
        int i, j;

        if (s1.length() != s2.length())
            throw new IllegalArgumentException("Strings de diferente longitud");

        // Contadores para s1
        int minus1 = 0; int mayus1 = 0; int digit1 = 0;

        for (i = 0; i < s1.length(); i++) {
            if (Character.isLowerCase(s1.charAt(i))) minus1++;
            if (Character.isUpperCase(s1.charAt(i))) mayus1++;
            if (Character.isDigit(s1.charAt(i))) digit1++;
        }

        // Contadores para s2
        int minus2 = 0; int mayus2 = 0; int digit2 = 0;
        for (j = 0; j < s2.length(); j++) {
            if (Character.isLowerCase(s2.charAt(j))) minus2++;
            if (Character.isUpperCase(s2.charAt(j))) mayus2++;
            if (Character.isDigit(s2.charAt(j))) digit2++;
        }

        // Variables para almacenar las cadenas que "ganan" en cada categoría
        int s1Winner = 0;
        int s2Winner = 0;

        // Comparación de minúsculas
        if (minus1 >= minus2)
            s1Winner++;
        else
            s2Winner++;

        // Comparación de mayúsculas
        if (mayus1 >= mayus2)
            s1Winner++;
        else
            s2Winner++;

        // Comparación de dígitos
        if (digit1 >= digit2)
            s1Winner++;
        else
            s2Winner++;

        return s1Winner >= s2Winner ? s1 : s2;
    }

    public static int crossingWords(String s1, String s2) {
        int i, j;
        int count = 0;
        for (i = 0; i < s1.length(); i++) {
            for (j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static  String wackyAlphabet(String s1, String s2) {
        // Asegurarnos que no hay más de 26 elementos y que estos son letras.
        if (s2.length() != 26 || !s2.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("El segundo string debe contener exactamente 26 letras del alfabeto.");
        }

        // Asegurarnos de que no hay elementos repetidos.
        if (s2.toLowerCase().chars().distinct().count() != 26) {
            throw new IllegalArgumentException("El segundo string no debe contener letras repetidas.");
        }

        // Convertir ambos strings a minúsculas para que el reordenamiento sea insensible a mayúsculas/minúsculas.
        char[] s1Letters = s1.toLowerCase().toCharArray();
        char[] s2Letters = s2.toLowerCase().toCharArray();

        // Crear un StringBuilder para almacenar el resultado.
        StringBuilder result = new StringBuilder();

        // Recorrer el alfabeto de s2
        for (int i = 0; i < s2Letters.length; i++) {
            char currentChar = s2Letters[i];

            // Recorrer s1 buscando las letras que coincidan con la letra actual de s2
            for (int j = 0; j < s1Letters.length; j++) {
                // Comparar las letras sin importar mayúsculas o minúsculas
                if ((s1Letters[j]) == currentChar) {
                    result.append(s1Letters[j]);  // Añadir la letra de s1 al resultado
                }
            }
        }
        // Devolver el String reordenado
        return result.toString();
    }
}

