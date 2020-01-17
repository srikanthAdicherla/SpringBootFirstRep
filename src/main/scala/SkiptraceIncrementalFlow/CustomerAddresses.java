package SkiptraceIncrementalFlow;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerAddresses {

    private String fullAddress;
    private String modifiedDate;

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fullAddress", fullAddress)
                .toString();
    }
}
