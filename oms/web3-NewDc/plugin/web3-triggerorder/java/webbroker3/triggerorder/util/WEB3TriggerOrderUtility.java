head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.06.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3TriggerOrderUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������̃��[�e�B���e�B(WEB3TriggerOrderUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 �Г� (���u) �V�K�쐬
*/
package webbroker3.triggerorder.util;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.util.WEB3LogUtility;

/**
 * �A�������̃��[�e�B���e�B�N���X�B<BR>
 * <BR>
 * 
 * @@author �Г�(���u)
 * @@version 1.0
 */
public class WEB3TriggerOrderUtility
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B)�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TriggerOrderUtility.class);

    /**
     * (create�L�[)<BR>
     * ����ID + �����^�C�v��hashmap�̃L�[���쐬 <BR>
     * <BR>
     * @@param l_lngParentOrderId �e�����̒���ID  
     * @@param l_productType �����^�C�v
     * @@return String
     */
    public static String createKey(
        long l_lngParentOrderId, 
        ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "getProduct(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        String l_strKey = "";
        
        l_strKey = 
            new Long(l_lngParentOrderId).toString() + l_productType.stringValue();
        
        log.exiting(STR_METHOD_NAME);
        return l_strKey;
    }
}
@
