head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEqtypeChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����\�񒍕��������e(WEB3ToSuccEqtypeChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7 �s�p (���u) �V�K�쐬 
*/

package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����\�񒍕��������e)<BR>
 * �����\�񒍕��������e�̓��͂�\������B <BR>
 * <BR>
 * xTrade��EqTypeChangeOrderSpec���g�������N���X�B<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccEqtypeChangeOrderSpec extends EqTypeChangeOrderSpec 
{
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     */
    public WEB3ToSuccEqtypeChangeOrderSpec(long arg0, long arg1, double arg2, double arg3)
    {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccEqtypeChangeOrderSpec.class);

    /**
     * (������T�Z��n���)<BR>
     * ������T�Z��n����B<BR>
     */
    private double modifiedEstimatedPrice;
    
    /**
     * (������v�Z�P��)<BR>
     * ������v�Z�P���B<BR>
     */
    private double modifiedCalcUnitPrice;
    
    /**
     * (�����㒍��������)<BR>
     * �������͂̒����������B<BR>
     * �i�o����܂Œ����̏ꍇ�̂݁j<BR>
     */
    private Date modifiedExpirationDate;
    
    /**
     * (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B <BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     */
    private boolean modifiedIsCarriedOrder;
    
    /**
     * (����)<BR>
     * �㗝���͈��ҁB<BR>
     */
    private WEB3GentradeTrader trader;
    
    /**
     * (������P�������l)<BR>
     * ������P�������l�B<BR>
     * �i�P�������l�w�莞�̂݃Z�b�g�B�ȊO�Anull�j<BR>
     */
    private Double modifiedPriceAdjustValue;

    /**
     * (create�����\�񒍕��������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �����\�񒍕��������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�A�������jcreate�����\�񒍕��������e�v���Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dblModifiedQuantity - (�����㊔��)<BR>
     * �������͂̊����B<BR>
     * @@param l_dblModifiedLimitPrice - (������w�l)<BR>
     * �������͂̒P���B<BR>
     * @@param l_dblModifiedEstimatedPrice - (������T�Z��n���)<BR>
     * ������T�Z��n����B<BR>
     * @@param l_dblModifiedCalcUnitPrice - (������v�Z�P��)<BR>
     * ������v�Z�P���B<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒���������<BR>
     * 
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@param l_modifiedPriceAdjustValue - (������P�������l)<BR>
     * ������P�������l�B<BR>
     * �i�P�������l�w��Ȃ�����null���Z�b�g�j<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec
     * @@roseuid 433B92D70269
     */
    public static WEB3ToSuccEqtypeChangeOrderSpec createEqtypeChangeOrderSpec(
        long l_lngOrderId, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        double l_dblModifiedEstimatedPrice, 
        double l_dblModifiedCalcUnitPrice, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder, 
        WEB3GentradeTrader l_trader, 
        Double l_modifiedPriceAdjustValue) 
    {
        final String STR_METHOD_NAME = " createEqtypeChangeOrderSpec(long, double, " + 
            "double, double, double, Date, boolean, WEB3GentradeTrader, Double) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:EqTypeChangeOrderSpec(����ID : long, �����P��ID : long, �����㊔�� : double, ������w�l : double)
        WEB3ToSuccEqtypeChangeOrderSpec l_eqtypeChangeOrderSpec = new WEB3ToSuccEqtypeChangeOrderSpec(
            l_lngOrderId,
            -1,
            l_dblModifiedQuantity,
            l_dblModifiedLimitPrice);
        
        //1.2:set������T�Z��n���(double)
        l_eqtypeChangeOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);
        
        //1.3:set������v�Z�P��(double)
        l_eqtypeChangeOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);
        
        //1.4:set�����㒍��������(Date)
        l_eqtypeChangeOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //1.5:set������is�o����܂Œ���(boolean)
        l_eqtypeChangeOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        //1.6:set����(����)
        l_eqtypeChangeOrderSpec.setTrader(l_trader);
        
        //1.7:set������P�������l(Double)
        l_eqtypeChangeOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);
        
        log.exiting(STR_METHOD_NAME);        
        return l_eqtypeChangeOrderSpec;
    }
    
    /**
     * (get�����\�񒍕��������e�ڍ�)<BR>
     * �igetChangeOrderUnitEntry�j<BR>
     * <BR>
     * �����\�񒍕��������e�ڍׂ��擾����B<BR>
     * <BR>
     * getChangeOrderUnitEntries( )�ɂĊ����\�񒍕��������e�ڍ׈ꗗ���擾�A<BR>
     * �߂�lList��0�Ԗڂ̗v�f��ԋp����B<BR>
     * @@return EqTypeChangeOrderUnitEntry
     * @@roseuid 433B92D70298
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnitEntry() ";
        log.entering(STR_METHOD_NAME);
        
        EqTypeChangeOrderUnitEntry[] l_eqTypeChangeOrderUnitEntry = super.getChangeOrderUnitEntries();
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeChangeOrderUnitEntry[0];
    }
    
    /**
     * (get������v�Z�P��)<BR>
     * ������v�Z�P�����擾����B<BR>
     * @@return double
     * @@roseuid 433B92D702B7
     */
    public double getModifiedCalcUnitPrice() 
    {
        return this.modifiedCalcUnitPrice;
    }
    
    /**
     * (set������v�Z�P��)<BR>
     * ������v�Z�P�����Z�b�g����B<BR>
     * @@param l_dblModifiedCalcUnitPrice - (������v�Z�P��)<BR>
     * �������͂̒����P�����Z�o�����v�Z�P��<BR>
     * @@roseuid 433B92D702D7
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get�����㒍��������)<BR>
     * �����㒍�����������擾����B<BR>
     * @@return Date
     * @@roseuid 433B92D702E6
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }
    
    /**
     * (set�����㒍��������)<BR>
     * �����㒍�����������Z�b�g����B<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒���������<BR>
     * @@roseuid 433B92D70306
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get������T�Z��n���)<BR>
     * ������T�Z��n������擾����B<BR>
     * @@return double
     * @@roseuid 433B92D70354
     */
    public double getModifiedEstimatedPrice() 
    {
        return modifiedEstimatedPrice;
    }
    
    /**
     * (set������T�Z��n���)<BR>
     * ������T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@roseuid 433B92D70373
     */
    public void setModifiedEstimatedPrice(double l_dblEstimatedPrice) 
    {
        this.modifiedEstimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (set������is�o����܂Œ���)<BR>
     * ������is�o����܂Œ������Z�b�g����B<BR>
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@roseuid 433B92D70392
     */
    public void setModifiedIsCarriedOrder(boolean l_blnModifiedIsCarriedOrder) 
    {
        this.modifiedIsCarriedOrder = l_blnModifiedIsCarriedOrder;
    }
    
    /**
     * (is������o����܂Œ���)<BR>
     * �����オ�u�o����܂Œ����v�ł��邩�ǂ�����Ԃ��B<BR>
     * this.������is�o����܂Œ����̒l��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 433B92D703B1
     */
    public boolean isModifiedCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (get����)<BR>
     * ���҂��擾����B<BR>
     * @@return WEB3GentradeTrader
     * @@roseuid 433B92D703D1
     */
    public WEB3GentradeTrader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (set����)<BR>
     * ���҂��Z�b�g����B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@roseuid 433B92D80008
     */
    public void setTrader(WEB3GentradeTrader l_trader) 
    {
        this.trader = l_trader;
    }
    
    /**
     * (get������P�������l)<BR>
     * ������P�������l���擾����B<BR>
     * @@return Double
     * @@roseuid 433BAA650298
     */
    public Double getModifiedPriceAdjustValue() 
    {
        return this.modifiedPriceAdjustValue;
    }
    
    /**
     * (set������P�������l)<BR>
     * ������P�������l���Z�b�g����B<BR>
     * @@param l_modifiedPriceAdjustValue - (������P�������l)<BR>
     * ������P�������l�B<BR>
     * @@roseuid 433BAA6502A8
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue) 
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }
}
@
