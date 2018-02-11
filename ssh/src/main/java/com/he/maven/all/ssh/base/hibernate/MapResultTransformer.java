package com.he.maven.all.ssh.base.hibernate;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyanjing on 2018/2/10 10:32.
 */
public class MapResultTransformer extends AliasedTupleSubsetResultTransformer {
    public static final MapResultTransformer INSTANCE = new MapResultTransformer();

    private MapResultTransformer() {
    }

    @Override
    public boolean isTransformedValueATupleElement(String[] strings, int i) {
        return false;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map result = new HashMap(tuple.length);

        for (int i = 0; i < tuple.length; ++i) {
            String alias = aliases[i].toLowerCase();
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }

        return result;
    }
}
