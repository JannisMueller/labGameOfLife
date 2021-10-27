package se.mueller

enum StatusOfCell {
    ALIVE("1"),
    DEAD("0")


    public final String label;

    private StatusOfCell(String label) {
        this.label = label;
    }

    String getLabel() {
        return label
    }
}