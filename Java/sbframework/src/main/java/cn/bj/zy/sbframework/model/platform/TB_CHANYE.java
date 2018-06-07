package cn.bj.zy.sbframework.model.platform;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tb_chanye`")
public class TB_CHANYE implements Serializable {
    @Id
    private String id;

    /**
     * 生产单位
     */
    private String production_unit;

    /**
     * 产品
     */
    private String production;

    /**
     * 产业性质
     */
    private String chanyexingzhi;

    /**
     * 产业现状
     */
    private String chanyexianzhuang;

    /**
     * 总投资
     */
    private String zongtouzi;

    /**
     * 达产规模
     */
    private String dachanguimo;

    /**
     * 达产产值
     */
    private String dachanchanzhi;

    /**
     * 行业领域
     */
    private String hangyelingyu;

    /**
     * 所在地
     */
    private String suozaidi;

    private String create_user;

    private Date create_time;

    private String update_user;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取生产单位
     *
     * @return production_unit - 生产单位
     */
    public String getProduction_unit() {
        return production_unit;
    }

    /**
     * 设置生产单位
     *
     * @param production_unit 生产单位
     */
    public void setProduction_unit(String production_unit) {
        this.production_unit = production_unit;
    }

    /**
     * 获取产品
     *
     * @return production - 产品
     */
    public String getProduction() {
        return production;
    }

    /**
     * 设置产品
     *
     * @param production 产品
     */
    public void setProduction(String production) {
        this.production = production;
    }

    /**
     * 获取产业性质
     *
     * @return chanyexingzhi - 产业性质
     */
    public String getChanyexingzhi() {
        return chanyexingzhi;
    }

    /**
     * 设置产业性质
     *
     * @param chanyexingzhi 产业性质
     */
    public void setChanyexingzhi(String chanyexingzhi) {
        this.chanyexingzhi = chanyexingzhi;
    }

    /**
     * 获取产业现状
     *
     * @return chanyexianzhuang - 产业现状
     */
    public String getChanyexianzhuang() {
        return chanyexianzhuang;
    }

    /**
     * 设置产业现状
     *
     * @param chanyexianzhuang 产业现状
     */
    public void setChanyexianzhuang(String chanyexianzhuang) {
        this.chanyexianzhuang = chanyexianzhuang;
    }

    /**
     * 获取总投资
     *
     * @return zongtouzi - 总投资
     */
    public String getZongtouzi() {
        return zongtouzi;
    }

    /**
     * 设置总投资
     *
     * @param zongtouzi 总投资
     */
    public void setZongtouzi(String zongtouzi) {
        this.zongtouzi = zongtouzi;
    }

    /**
     * 获取达产规模
     *
     * @return dachanguimo - 达产规模
     */
    public String getDachanguimo() {
        return dachanguimo;
    }

    /**
     * 设置达产规模
     *
     * @param dachanguimo 达产规模
     */
    public void setDachanguimo(String dachanguimo) {
        this.dachanguimo = dachanguimo;
    }

    /**
     * 获取达产产值
     *
     * @return dachanchanzhi - 达产产值
     */
    public String getDachanchanzhi() {
        return dachanchanzhi;
    }

    /**
     * 设置达产产值
     *
     * @param dachanchanzhi 达产产值
     */
    public void setDachanchanzhi(String dachanchanzhi) {
        this.dachanchanzhi = dachanchanzhi;
    }

    /**
     * 获取行业领域
     *
     * @return hangyelingyu - 行业领域
     */
    public String getHangyelingyu() {
        return hangyelingyu;
    }

    /**
     * 设置行业领域
     *
     * @param hangyelingyu 行业领域
     */
    public void setHangyelingyu(String hangyelingyu) {
        this.hangyelingyu = hangyelingyu;
    }

    /**
     * 获取所在地
     *
     * @return suozaidi - 所在地
     */
    public String getSuozaidi() {
        return suozaidi;
    }

    /**
     * 设置所在地
     *
     * @param suozaidi 所在地
     */
    public void setSuozaidi(String suozaidi) {
        this.suozaidi = suozaidi;
    }

    /**
     * @return create_user
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * @param create_user
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    /**
     * @return create_time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * @param create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * @return update_user
     */
    public String getUpdate_user() {
        return update_user;
    }

    /**
     * @param update_user
     */
    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    /**
     * @return update_time
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * @param update_time
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", production_unit=").append(production_unit);
        sb.append(", production=").append(production);
        sb.append(", chanyexingzhi=").append(chanyexingzhi);
        sb.append(", chanyexianzhuang=").append(chanyexianzhuang);
        sb.append(", zongtouzi=").append(zongtouzi);
        sb.append(", dachanguimo=").append(dachanguimo);
        sb.append(", dachanchanzhi=").append(dachanchanzhi);
        sb.append(", hangyelingyu=").append(hangyelingyu);
        sb.append(", suozaidi=").append(suozaidi);
        sb.append(", create_user=").append(create_user);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_user=").append(update_user);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}