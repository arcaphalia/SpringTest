package cn.bj.zy.sbframework.model.platform;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`tb_tuanti`")
public class TB_TUANTI implements Serializable {
    /**
     * 团体id
     */
    @Id
    @Column(name = "`id`")
    private String id;

    /**
     * 团体名称
     */
    @Column(name = "`name`")
    private String name;

    @Column(name = "`area`")
    private String area;

    private static final long serialVersionUID = 1L;

    /**
     * 获取团体id
     *
     * @return id - 团体id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置团体id
     *
     * @param id 团体id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取团体名称
     *
     * @return name - 团体名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置团体名称
     *
     * @param name 团体名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", area=").append(area);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}