package jpa.study;

import jpa.study.constants.EQUIPMENT_TYPE;

import javax.persistence.*;

/**
 * Created by pillsoonpark on 2015. 10. 22..
 */
@Entity
@Table(name = "PCROOM_EQUIPMENT")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private EQUIPMENT_TYPE type;

    private long price;

    private boolean isIdle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EQUIPMENT_TYPE getType() {
        return type;
    }

    public void setType(EQUIPMENT_TYPE type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIsIdle(boolean isIdle) {
        this.isIdle = isIdle;
    }
}
