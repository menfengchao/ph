package org.men.frameworkcommon.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Spring JPA the tool of update any object
 *
 * @author Leon
 * @version 2018/8/20 11:49
 */
public final class UpdateUtils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertiesName(target));
    }

    /**
     * Return the array of properties whose's value is not null
     *
     * @param source The object of source
     * @return
     */
    private static String[] getNullPropertiesName(Object source) {
        final BeanWrapper bw = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = bw.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        Stream.of(pds).forEach(pd -> {
            Object scrValue = bw.getPropertyValue(pd.getName());
            if (scrValue != null) {
                emptyNames.add(pd.getName());
            }
        });
        return emptyNames.toArray(new String[emptyNames.size()]);
    }

}
