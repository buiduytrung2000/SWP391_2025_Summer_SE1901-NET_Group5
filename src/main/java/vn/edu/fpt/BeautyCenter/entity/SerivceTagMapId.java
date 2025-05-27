package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SerivceTagMapId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -1603217748815681257L;
    @Size(max = 36)
    @NotNull
    @Column(name = "service_id", nullable = false, length = 36)
    private String serviceId;

    @NotNull
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SerivceTagMapId entity = (SerivceTagMapId) o;
        return Objects.equals(this.tagId, entity.tagId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, serviceId);
    }

}