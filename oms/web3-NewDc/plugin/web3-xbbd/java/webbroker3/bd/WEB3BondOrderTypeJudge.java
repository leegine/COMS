head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderTypeJudge.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ʔ���(WEB3BondOrderTypeJudge.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
Revesion History : 2007/07/11 ���n�m (���u) �d�l�ύX�E���f��196
Revesion History : 2007/07/25 ������(���u) �d�l�ύX���f��NO.241
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

/**
 * (��������ʔ���)<BR>
 * ��������ʔ���N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3BondOrderTypeJudge 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderTypeJudge.class);
   
    
    /**
     * (�������)<BR>
     * �������<BR>
     */
    private OrderTypeEnum orderType;
    
    /**
     * (���)<BR>
     * ���<BR>
     */
    private String trading;
    
    /**
     * @@roseuid 44E33623004E
     */
    public WEB3BondOrderTypeJudge() 
    {
     
    }
    
    /**
     * (��������ʔ���)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j������ʁA����̃v���p�e�B��ݒ肷��<BR>
     * <BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@param l_strTrading - (���)<BR>
     * ���<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CAEA2D029A
     */
    public WEB3BondOrderTypeJudge(OrderTypeEnum l_orderType, String l_strTrading) 
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "WEB3BondOrderTypeJudge(OrderTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j������ʁA����̃v���p�e�B��ݒ肷��
        this.orderType = l_orderType;
        this.trading = l_strTrading;

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�������)<BR>
     * get�������<BR>
     * <BR>
     * �@@������ʂ�Ԃ�<BR>
     * @@return OrderTypeEnum
     * @@roseuid 44CAE5EA0098
     */
    public OrderTypeEnum getOrderType() 
    {
        return this.orderType;
    }
    
    /**
     * (get���)<BR>
     * get���<BR>
     * <BR>
     * �@@�����Ԃ�<BR>
     * @@return String
     * @@roseuid 44CAE5FA023E
     */
    public String getTrading() 
    {
        return this.trading;
    }
    
    /**
     * (is���咍��)<BR>
     * is���咍��<BR>
     * <BR>
     * �P�jthis.get���() == ��W���<BR>
     * �@@�@@����<BR>
     * �@@�@@this.get�������() == �����t<BR>
     * �@@�@@�̏ꍇ�Atrue��Ԃ�<BR>
     * <BR>
     * �Q�jthis.get�������() == ���������咍��<BR>
     * �@@�@@�̏ꍇ�Atrue��Ԃ�<BR>
     * <BR>
     * �R�j��L�ȊO�̏ꍇ�Afalse��Ԃ�<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 44CAE6080192
     */
    public boolean isRecruitOrder()
    {
        //�P�jthis.get���() == ��W���
        //�@@�@@����
        //�@@�@@this.get�������() == �����t
        //�@@�@@�̏ꍇ�Atrue��Ԃ�
        if (WEB3DealTypeDef.RECRUIT_TRADING.equals(this.getTrading())
            && OrderTypeEnum.BOND_BUY.equals(this.getOrderType()))
        {
            return true;
        }
        else if (OrderTypeEnum.DOMESTIC_BOND_RECRUIT.equals(this.getOrderType()))
        {
            //�Q�jthis.get�������() == ���������咍��
            //�@@�̏ꍇ�Atrue��Ԃ�
            return true;
        }
        else
        {
            //�R�j��L�ȊO�̏ꍇ�Afalse��Ԃ�
            return false;
        }
    }
    
    /**
     * (is���t����)<BR>
     * is���t����<BR>
     * <BR>
     * this.get���()���������d�؎��<BR>
     * ����<BR>
     * this.get�������()���������t<BR>
     * �̏ꍇ�Atrue��Ԃ�<BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�Afalse��Ԃ�<BR>
     * @@return boolean
     * @@roseuid 44CAE7E601E6
     */
    public boolean isBuyOrder() 
    {
        if (WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.getTrading()) &&
            OrderTypeEnum.BOND_BUY.equals(this.getOrderType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (is���p����)<BR>
     * is���p����<BR>
     * <BR>
     * this.get���()���������d�؎��<BR>
     * ����<BR>
     * this.get�������()���������p<BR>
     * �̏ꍇ�Atrue��Ԃ�<BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�Afalse��Ԃ�<BR>
     * @@return boolean
     * @@roseuid 44CAE82902E1
     */
    public boolean isSellOrder() 
    {
        if (WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.getTrading()) && 
            OrderTypeEnum.BOND_SELL.equals(this.getOrderType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
