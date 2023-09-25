package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {
    public final String remark;

    public Remark(String remark) {
        requireNonNull(remark);
        this.remark = remark;
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Remark)
                && remark.equals(((Remark) other).remark);
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
