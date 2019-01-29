head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�]��(WEB3TPAssetValuation.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
 *                  2004/08/04 �� ((FLJ)) �����C��
 */
package webbroker3.tradingpower.updtpower;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (���Y�]��) <BR>
 * ���Y�]����\������B
 */
public abstract class WEB3TPAssetValuation
{

    /**
     * �ڋq���� <BR>
     */
    private WEB3TPAccountInfo accountInfo;

    /**
     * �v�Z���� <BR>
     */
    private WEB3TPCalcCondition calcCondition;

    /**
     * �]�͌v�Z���g�p���錻�������e <BR>
     */
    private webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec[] newOrderSpecs;

    /**
     * �]�͌v�Z���g�p���铖�������ꗗ <BR>
     */
    private java.util.List todaysEquityOrders;
    
    /**
     * @@roseuid 4136A96003C8
     */
    public WEB3TPAssetValuation()
    {

    }

    /**
     * (get�]�͌v�Z����)<BR>
     * <BR>
     * �]�͌v�Z�������擾����B<BR>
     * @@return WEB3TPCalcCondition
     * @@roseuid 40BA8F6C0276
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * (set�]�͌v�Z����)<BR>
     * <BR>
     * �]�͌v�Z�������Z�b�g����B<BR>
     * @@param l_calcCondition - �]�͌v�Z����
     * @@roseuid 40BA8F79012E
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        this.calcCondition = l_calcCondition;
    }

    /**
     * (get�ڋq����)<BR>
     * <BR>
     * �ڋq�������擾����B<BR>
     * @@return WEB3TPAccountInfo
     * @@roseuid 40BA8F9001F9
     */
    public WEB3TPAccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }

    /**
     * (set�ڋq����)<BR>
     * <BR>
     * �ڋq�������Z�b�g����B<BR>
     * @@param l_accountInfo- �ڋq����
     * @@param l_accountInfo
     * @@roseuid 40BA8F9F015D
     */
    public void setAccountInfo(WEB3TPAccountInfo l_accountInfo)
    {
        this.accountInfo = l_accountInfo;
    }

    /**
     * (get���������e)<BR>
     * <BR>
     * ���������e���擾����B<BR>
     * @@return  WEB3TPNewOrderSpec[]
     * @@roseuid 40F3C6950371
     */
    public WEB3TPNewOrderSpec[] getNewOrderSpecs()
    {
        return this.newOrderSpecs;
    }

    /**
     * (set���������e)<BR>
     * <BR>
     * ���������e���Z�b�g����B<BR>
     * @@param l_newOrderSpecs - ���������e�z��
     * @@roseuid 40F3C6A00343
     */
    public void setNewOrderSpecs(WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        this.newOrderSpecs = l_newOrderSpecs;
    }

    /**
     * (get���������ꗗ)<BR>
     * <BR>
     * ���������ꗗ���擾����B<BR>
     * @@return List
     */
    public java.util.List getTodaysEquityOrders()
    {
        return this.todaysEquityOrders;
    }

    /**
     * (set���������ꗗ)<BR>
     * <BR>
     * ���������ꗗ���Z�b�g����B<BR>
     * @@param l_todaysEquityOrders - ���������ꗗ
     */
    public void setTodaysEquityOrders(java.util.List l_todaysEquityOrders)
    {
        this.todaysEquityOrders = l_todaysEquityOrders;
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("accountInfo", accountInfo)
            .append("calcCondition", calcCondition)
            .append("newOrderSpecs", newOrderSpecs)
            .toString();
    }

}
@
