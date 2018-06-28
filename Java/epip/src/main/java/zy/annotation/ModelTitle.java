package zy.annotation;

/**
 * 　　　　　　　　 ┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻━┓ + +
 * 　　　　　　 ┃　　　　　　　  ┃
 * 　　　　　　┃　　　━　　　  ┃ ++ + + +
 * 　　　　　████━████ ┃+
 * 　　　　　┃　　　　　　　  ┃ +
 * 　　　　 ┃　　　┻　　　  ┃
 * 　　　　┃　　　　　　　  ┃ + +
 * 　　　 ┗━┓　　　┏━━┛
 * 　　　　  ┃　　　┃
 * 　　　　 ┃　　　┃ + + + +
 * 　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　 ┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　┃　　　┃
 * 　   ┃　　　┃　　+
 * 　　┃　 　 ┗━━━━ ┓ + +
 * 　 ┃ 　　　　　　　  ┣┓
 * 　┃ 　　　　　　　┏┛
 * ┗ ┓┓┏━┳┓┏┛ + + + +
 * 　 ┃┫┫　┃┫┫
 * 　┗┻┛　┗┻┛+ + + +
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author Huang Yukun
 * @
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModelTitle {
    String name();
}
