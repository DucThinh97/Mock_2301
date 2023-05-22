package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OderList")
public class OderList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "oderId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    @Column(name = "oderValue", nullable = false)
    private int totalPayment;

    @Column(name = "`status`", nullable = false)
    @Convert(converter = OderListStatusConvert.class)
    private Status status = Status.WAITING;

    @CreatedDate
    @Column(name = "oderDate", insertable = false, updatable = false)
    private LocalDateTime oderDate;

    public enum Status {
        WAITING("WAITING"), DELIVERING("DELIVERING"), DELIVERED("DELIVERED"), CANCELED("CANCELED");

        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status toEnum(String sqlValue) {
            for (Status type : Status.values()) {
                if (type.getValue().equals(sqlValue)) {
                    return type;
                }
            }
            return null;
        }

    }

    @OneToMany(mappedBy = "oderList")
    private List<OderDetail> oderDetails;
}