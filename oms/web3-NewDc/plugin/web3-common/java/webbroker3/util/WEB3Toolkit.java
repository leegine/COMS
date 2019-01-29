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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�e�B���e�B�N���X(WEB3Toolkit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ����� (���u) �V�K�쐬
*/

package webbroker3.util;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * ���[�e�B���e�B�N���X�B<BR>
 * <BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3Toolkit
{
    /**
     * ��̔z�����̔z��ɍ������āA�z��ɕԋp����
     *
     * @@param l_array1 �z��1
     * @@param l_array2 �z��2
     * @@return ���������z��ɕԋp����
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
     * �w�肳��Ă���z�񒆂̏d���̌��f����菜���A���ꂩ���̐V�����z��ɕԋp����A<BR>
     * ���̔z�񒆂̂��ꂼ��̌��f�͗B��̂��̂ł��B
     *
     * @@param l_array �z��
     * @@return �d�����f����菜�����z��ɕԋp����
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
     * ����f�͈�z��ɑ��݂��邩�ǂ����𔻒f����
     *
     * @@param l_obj ����
     * @@param l_array �z��
     * @@return ���݂���΁Atrue�ɕԋp�A���݂��Ȃ���΁Afalse�ɕԋp����
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
     * ����f�͈���X�g�ɑ��݂��邩�ǂ����𔻒f����
     *
     * @@param l_obj ����
     * @@param l_lst ���X�g
     * @@return ���݂���΁Atrue�ɕԋp�A���݂��Ȃ���΁Afalse�ɕԋp����
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
     * ���String�͓��������ǂ����𔻒f���܂��A�������ꍇ�A
     * true���ԋp����A�������Ȃ��ꍇ�Afalse���ԋp����܂��B
     * �����ӁF���String�͑S��null�������ꍇ�Atrue���ԋp����܂��B
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
