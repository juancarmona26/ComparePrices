package co.mobilemakers.compareprice;

import android.os.Parcel;
import android.os.Parcelable;

public class Market implements Parcelable {

    private int id;
    private String name;
    private String location;

    public static Creator<Market> CREATOR = new Creator<Market>() {
        @Override
        public Market createFromParcel(Parcel source) {
            return new Market(source);
        }

        @Override
        public Market[] newArray(int size) {
            return new Market[size];
        }
    };

    public Market() {

    }

    public Market(Parcel in){
        id = in.readInt();
        name = in.readString();
        location = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(location);
    }
}
