package djamelfel.gala;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by djamel on 03/11/15.
 */
public class User implements Serializable, Parcelable{
    private String id_user;
    private ArrayList<Billet> billets = null;


    public User(String id_user, String id_billet, int quantite){
        this.id_user = id_user;
        this.billets = new ArrayList<Billet>();
        this.billets.add(new Billet(id_billet, quantite));
    }

    public User(String save) {
        String[] str = save.split("-");
        this.id_user = str[0];
        this.billets = new ArrayList<Billet>();
        this.billets.add((new Billet(str[1])));
    }

    protected User(Parcel in) {
        this.id_user = in.readString();
        this.billets = (ArrayList<Billet>)in.readSerializable();
    }

    public String getId_user() {
        return this.id_user;
    }

    public boolean isChecked(String id_billet, int quantite) {
        if(this.billets.isEmpty()) {
            this.billets.add(new Billet(id_billet, quantite));
            return true;
        }
        Iterator<Billet> itr = this.billets.iterator();
        while(itr.hasNext()) {
            Billet billet = itr.next();
            if(billet.getId_billet().equals(id_billet)) {
                if (billet.isClickable()) {
                    billet.validatePlace();
                    return true;
                }
                else
                    return false;
            }
            else {
                this.billets.add(new Billet(id_billet, quantite));
                return true;
            }
        }
        return false;
    }

    public String writeToFile() {
        String str = "";
        Iterator<Billet> itr = billets.iterator();
        while(itr.hasNext()) {
            Billet billet = itr.next();
            str = str + "-" + billet.writeToFile();
        }

        return id_user + str;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_user);
        dest.writeSerializable(this.billets);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
