public class Clock {

    private int h;
    private int m;

    private void validate(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            throw new IllegalArgumentException();
        }
    }

    public Clock(int h, int m) {
        validate(h, m);
        this.h = h;
        this.m = m;
    }

    public Clock(String s) {

        if (s == null) {
            throw new IllegalArgumentException();
        }

        if (!s.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException();
        }

        String[] digits = s.split(":");
        if (digits.length != 2) {
            throw new IllegalArgumentException();
        }
        int h = Integer.parseInt(digits[0]);
        int m = Integer.parseInt(digits[1]);

        validate(h, m);

        this.h = h;
        this.m = m;

    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", this.h, this.m);
    }

    public boolean isEarlierThan(Clock that) {
        if (this.h < that.h) {
            return true;
        }
        else return this.h == that.h && this.m < that.m;
    }

    public void tic() {
        if (this.m == 59) {
            this.m = 0;
            if (this.h == 23) {
                this.h = 0;
            }
            else this.h++;
        }
        else this.m++;
    }

    public void toc(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException();
        }

        this.h += delta / 60;
        this.m += delta % 60;

        if (this.m >= 60) {
            this.h += this.m/60;
            this.m %= 60;
        }
        this.h %= 24;
    }

    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        Clock clock = new Clock(h, m);
        System.out.println(clock);
    }
}
