head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�S�����ϓ�)<BR>
 * �S�����ϓ���\������B
 */
public abstract class WEB3TPRestraintReflector
    extends WEB3TPAssetReflector
{

    /**
     * (�S����)<BR>
     */
    private double amount;

    /**
      * (get�S����)<BR>
      *  �S������Ԃ��B<BR>
      * @@return double 
      * @@roseuid 40D64CBA02AA
      */
     public double getAmount()
     {
         return amount;
     }

     /**
      * (set�S����)<BR>
      * ������ �S�����z�ɃZ�b�g����B<BR>
      * @@param l_dblAmount - (�S����)
      * @@roseuid 40D64CB6024C
      */
     public void setAmount(double l_dblAmount)
     {
         amount = l_dblAmount;
     }   

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("amount", getAmount())
            .appendSuper(super.toString())
            .toString();
    }
      
}
@
