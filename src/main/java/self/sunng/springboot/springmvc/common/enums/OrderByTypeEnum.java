package self.sunng.springboot.springmvc.common.enums;

/**
 * Created by sunxiaodong on 2016/12/27.
 */
public enum OrderByTypeEnum {
    ASC("asc"), DESC("desc");

    private String orderByType;

    OrderByTypeEnum(String orderByType) {
        this.orderByType = orderByType;
    }

    public String value() {
        return this.orderByType;
    }

    // public static OrderByTypeEnum valueOf(String orderByType) {
    //     switch (orderByType) {
    //         case "asc":
    //             return ASC;
    //         case "desc":
    //             return DESC;
    //         default:
    //             return null;
    //     }
    // }
}
