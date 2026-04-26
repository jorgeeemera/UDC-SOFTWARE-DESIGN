package e2;

public class KeyPad {
    public static Boolean isValidKeyPad(char[][] keyPad) {
        if (keyPad == null || keyPad.length == 0) {
            return false;
        }

        int rows = keyPad.length;
        int cols = keyPad[0].length;

        for (int i = 1; i < rows; i++) {
            if (keyPad[i] == null || keyPad[i].length != cols) {
                return false;
            }
        }

        String validSequence = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (isValidByRows(keyPad, validSequence, rows, cols)) {
            return true;
        }
        if (isValidByColumns(keyPad, validSequence, rows, cols)) {
            return true;
        }
        return false;
    }

    private static boolean isValidByRows(char[][] keyPad, String validSequence, int filas, int columnas) {
        int aux = 0;
        int i; int j;

        for (i = 0; i < filas; i++) {
            for (j = 0; j < columnas; j++) {
                if (keyPad[i][j] != validSequence.charAt(aux)) {
                    return false;
                }
                aux++;
            }
        }
        return true;
    }

    private static boolean isValidByColumns(char[][] keyPad, String validSequence, int filas, int columnas) {
        int aux = 0;
        int i; int j;

        for (j = 0; j < columnas; j++) {
            for (i = 0; i < filas; i++) {
                if (keyPad[i][j] != validSequence.charAt(aux)) {
                    return false;
                }
                aux++;
            }
        }
        return true;
    }

    public static Boolean areValidMovements(String[] movements) {
        if (movements == null) {
            return false;
        }

        for (String move : movements) {
            if (move == null || !move.matches("[UDLR]+")) {
                return false;
            }
        }
        return true;
    }

    public static String obtainCode(char[][] keyPad, String[] movements) {
        if (!isValidKeyPad(keyPad)) {
            throw new IllegalArgumentException("Teclado inválido");
        }

        if (!areValidMovements(movements)) {
            throw new IllegalArgumentException("Secuencia de movimientos inválida");
        }

        StringBuilder code = new StringBuilder();
        int filas = keyPad.length;
        int columnas = keyPad[0].length;
        int currentRow = 0, currentCol = 0;

        for (String move : movements) {
            for (char direction : move.toCharArray()) {
                switch (direction) {
                    case 'U':
                        if (currentRow > 0) currentRow--;
                        break;
                    case 'D':
                        if (currentRow < filas - 1) currentRow++;
                        break;
                    case 'L':
                        if (currentCol > 0) currentCol--;
                        break;
                    case 'R':
                        if (currentCol < columnas - 1) currentCol++;
                        break;
                }
            }
            code.append(keyPad[currentRow][currentCol]);
        }
        return code.toString();
    }
}

