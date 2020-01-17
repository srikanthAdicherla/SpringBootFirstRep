package dto;

import java.util.List;

public class ResultCustomer {
    private String id;
    private List<String> address;
    private List<Integer> mobileNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<Integer> getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(List<Integer> mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "ResultCustomer{" +
                "id='" + id + '\'' +
                ", address=" + address +
                ", mobileNo=" + mobileNo +
                '}';
    }
}
