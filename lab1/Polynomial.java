public class Polynomial {
    private FractionSet factor;

    Polynomial() {
        this.factor = new FractionSet();
    }

    Polynomial(FractionSet set) {
        this.factor = set;
    }
    public Fraction get(int i) {
        return this.factor.get(i);
    }

    private void add(Fraction x) {
        this.factor.add(x);
    }

    public Polynomial sum(Polynomial x) {
        Polynomial newPol = new Polynomial();
        int begin = Math.min(this.factor.size(), x.factor.size());
        int end = Math.max(this.factor.size(), x.factor.size());

        int i;
        for(i = 0; i < begin; ++i) {
            newPol.add(this.get(i).sum(x.get(i)));
        }

        if (begin == this.factor.size()){
            for(i = begin; i < end; ++i) {
                newPol.add(x.get(i));
            }
        }else{
            for(i = begin; i < end; ++i) {
                newPol.add(this.get(i));
            }
        }

        return newPol;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.factor.size(); i++) {
            if (i == 0) {
                sb.append(this.factor.get(i));
            } else {
                if (this.factor.get(i).getNum() > 0) {
                    sb.append("+");
                }

                sb.append(this.factor.get(i)).append("*x^").append(i);
            }
        }
        return sb.toString();
    }
}
