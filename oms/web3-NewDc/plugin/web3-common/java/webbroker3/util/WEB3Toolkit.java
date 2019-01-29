head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Toolkit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ユーティリティクラス(WEB3Toolkit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.util;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * ユーティリティクラス。<BR>
 * <BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3Toolkit
{
    /**
     * 二つの配列を一つの配列に合併して、配列に返却する
     *
     * @@param l_array1 配列1
     * @@param l_array2 配列2
     * @@return 合併した配列に返却する
     */
    public static Object[] merge(Object[] l_array1, Object[] l_array2)
    {
        if (l_array1 == null && l_array2 == null)
        {
            return null;
        }

        if (l_array1 == null && l_array2 != null)
        {
            return l_array2;
        }

        if (l_array1 != null && l_array2 == null)
        {
            return l_array1;
        }

        Object[] l_mergeObjects = new Object[l_array1.length + l_array2.length];
        System.arraycopy(l_array1, 0, l_mergeObjects, 0, l_array1.length);
        System.arraycopy(l_array2, 0, l_mergeObjects, l_array1.length, l_array2.length);

        return l_mergeObjects;
    }

    /**
     * 指定されている配列中の重複の元素を取り除く、それから一つの新しい配列に返却する、<BR>
     * この配列中のそれぞれの元素は唯一のものです。
     *
     * @@param l_array 配列
     * @@return 重複元素を取り除いた配列に返却する
     */
    public static Object[] toUnique(Object[] l_array)
    {
        if (l_array == null)
        {
            return null;
        }

        if (l_array.length < 2)
        {
            return l_array;
        }

        List l_lstUnique = new Vector();
        Map l_uniqueMap = new Hashtable();

        for (int i = 0; i < l_array.length; i++)
        {
            Object l_obj = l_array[i];
            if (l_uniqueMap.containsKey(l_obj))
            {
                continue;
            }
            else
            {
                l_lstUnique.add(l_obj);
                l_uniqueMap.put(l_obj, "1");
            }
        }

        return l_lstUnique.toArray();
    }

    /**
     * 一つ元素は一つ配列に存在するかどうかを判断する
     *
     * @@param l_obj 成員
     * @@param l_array 配列
     * @@return 存在すれば、trueに返却、存在しなければ、falseに返却する
     */
    public static boolean contain(Object[] l_array, Object l_obj)
    {
        if (l_array == null || l_array.length == 0)
        {
            return false;
        }

        for (int i = 0; i < l_array.length; i++)
        {
            if (l_obj == null)
            {
                if (l_array[i] == null)
                {
                    return true;
                }
            }
            else
            {
                if (l_obj.equals(l_array[i]))
                {
                    return true;
                }
            }
        }

        return false;

    }
    
    /**
     * 一つ元素は一つリストに存在するかどうかを判断する
     *
     * @@param l_obj 成員
     * @@param l_lst リスト
     * @@return 存在すれば、trueに返却、存在しなければ、falseに返却する
     */
    public static boolean contain(List l_lst, Object l_obj)
    {
        if (l_lst == null || l_lst.size() == 0)
        {
            return false;
        }

        for (int i = 0; i < l_lst.size(); i++)
        {
            Object l_tmpObj = l_lst.get(i);
            if (l_obj == null)
            {
                if (l_tmpObj == null)
                {
                    return true;
                }
            }
            else
            {
                if (l_obj.equals(l_tmpObj))
                {
                    return true;
                }
            }
        }

        return false;

    }
    
    /**
     * 二つのStringは等しいかどうかを判断します、等しい場合、
     * trueが返却され、等しくない場合、falseが返却されます。
     * ※注意：二つのStringは全部nullだった場合、trueが返却されます。
     *
     * @@param l_str1
     * @@param l_str2
     * @@return 
     */
    public static boolean isEquals(String l_str1, String l_str2)
    {
        if (l_str1 == null && l_str2 == null)
        {
            return true;
        }
        
        if (l_str1 == null || l_str2 == null)
        {
            return false;
        }
        
        return l_str1.equals(l_str2);
    }
}
@
