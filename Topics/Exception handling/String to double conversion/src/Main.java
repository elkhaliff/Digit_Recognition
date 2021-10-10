class Converter {

    /**
     * It returns a double value or 0 if an exception occurred
     */
    public static double convertStringToDouble(String input) {
        double out;
        try {
            out = Double.parseDouble(input);
        } catch (Exception e) {
            out = 0.0;
        }
        return out;
    }
}