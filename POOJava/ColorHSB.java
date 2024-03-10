public class ColorHSB {

    private final int hue;
    private final int saturation;
    private final int brightness;

    public ColorHSB(int h, int s, int b) {
        if ((0 > h || h > 359) || (0 > s || s > 100) || (0 > b || b > 100)) {
            throw new IllegalArgumentException();
        }
        this.hue = h;
        this.saturation = s;
        this.brightness = b;
    }

    @Override
    public String toString() {
        return "(" + this.hue + "," + this.saturation + "," + this.brightness + ")";
    }

    public boolean isGrayscale() {
        if (saturation == 0 || brightness == 0) {
            return true;
        }
        return false;
    }

    public int distanceSquaredTo(ColorHSB that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }

        int min = Math.min(Math.abs(this.hue - that.hue), 360 - Math.abs(this.hue - that.hue));
        int sat = this.saturation - that.saturation;
        int brit = this.brightness - that.brightness;

        return (min*min) + (sat*sat) + (brit*brit);
    }

    public static void main(String[] args) {
        int stdHue = Integer.parseInt(args[0]);
        int stdSaturation = Integer.parseInt(args[1]);
        int stdBrightness = Integer.parseInt(args[2]);
        ColorHSB stdColor = new ColorHSB(stdHue, stdSaturation, stdBrightness);

        ColorHSB closestColor = null;
        String closestColorName = "";
        int closestDistance = Integer.MAX_VALUE;

        while (!StdIn.isEmpty()) {
            String colorName = StdIn.readString();
            int h = StdIn.readInt();
            int s = StdIn.readInt();
            int b = StdIn.readInt();
            ColorHSB color = new ColorHSB(h, s, b);

            int distance = stdColor.distanceSquaredTo(color);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = color;
                closestColorName = colorName;
            }
        }

        if (closestColor != null) {
            System.out.println(closestColorName + " " + closestColor);
        }
    }
}
