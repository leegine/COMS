head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����\��ԍϒ����������e(WEB3ToSuccMarginChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7 �s�p (���u) �V�K�쐬 
*/


package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����\��ԍϒ����������e)<BR>
 * �����\��ԍϒ����������e�̓��͂�\������B <BR>
 * <BR>
 * xTrade��EqTypeChangeSettleContractOrderSpec���g�������N���X�B<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeSettleContractOrderSpec extends EqTypeChangeSettleContractOrderSpec 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeSettleContractOrderSpec.class);
        
    /**
     * @@param arg0
     * @@param arg1
     */
    public WEB3ToSuccMarginChangeSettleContractOrderSpec(long arg0, EqTypeContractSettleChangeOrderUnitEntry[] arg1)
    {
        super(arg0, arg1);
    }

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
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
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
     * (create�����\��ԍϒ����������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �����\��ԍϒ����������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�A�������jcreate�����\��ԍϒ����������e�v���Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_eqTypeSettleContractOrderEntries - (���ό����G���g��)<BR>
     * ���ό����G���g���B<BR>
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
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@param l_modifiedPriceAdjustValue - (������P�������l)<BR>
     * ������P�������l�B<BR>
     * �i�P�������l�w��Ȃ�����null���Z�b�g�j<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccMarginChangeSettleContractOrderSpec
     * @@roseuid 43424CF90107
     */
    public static WEB3ToSuccMarginChangeSettleContractOrderSpec createMarginChangeSettleContractOrderSpec(
        long l_lngOrderId, 
        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        double l_dblModifiedEstimatedPrice, 
        double l_dblModifiedCalcUnitPrice, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder, 
        WEB3GentradeTrader l_trader, 
        Double l_modifiedPriceAdjustValue) 
    {
        final String STR_METHOD_NAME = " createMarginChangeSettleContractOrderSpec(long, " + 
            "EqTypeSettleContractOrderEntry[], double, double, double, double, Date, boolean," +
            " WEB3GentradeTrader, Double) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: EqTypeContractSettleChangeOrderUnitEntry(arg0 : long, arg1 : double, arg2 : EqTypeSettleContractOrderEntry[])
        EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry = 
            new EqTypeContractSettleChangeOrderUnitEntry(
                -1,
                l_dblModifiedLimitPrice,
                l_eqTypeSettleContractOrderEntries);
        
        //1.2:EqTypeChangeSettleContractOrderSpec(arg0 : long, arg1 : EqTypeChangeSettleContractOrderSpec[])
        EqTypeContractSettleChangeOrderUnitEntry[] l_eqTypeContractSettleChangeOrderUnitEntres = 
            {l_eqTypeContractSettleChangeOrderUnitEntry};
            
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec = 
            new WEB3ToSuccMarginChangeSettleContractOrderSpec(
                l_lngOrderId, 
                l_eqTypeContractSettleChangeOrderUnitEntres);
        
        //1.3:set������T�Z��n���(double)
        l_marginChangeSettleContractOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);
        
        //1.4:set������v�Z�P��(double)
        l_marginChangeSettleContractOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);
        
        //1.5:set�����㒍��������(Date)
        l_marginChangeSettleContractOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //1.6:set������is�o����܂Œ���(boolean)
        l_marginChangeSettleContractOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        //1.7:set����(����)
        l_marginChangeSettleContractOrderSpec.setTrader(l_trader);
        
        //1.8:set������P�������l
        l_marginChangeSettleContractOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);
        
        log.exiting(STR_METHOD_NAME);
        return l_marginChangeSettleContractOrderSpec;
    }
    
    /**
     * (get�����\��ԍϒ����������e�ڍ�)<BR>
     * �igetChangeOrderUnitEntry�j<BR>
     * <BR>
     * �����\��ԍϒ����������e�ڍׂ��擾����B<BR>
     * <BR>
     * getChangeOrderUnitEntries( )�ɂĊ����\��ԍϒ����������e�ڍ׈ꗗ���擾�A<BR>
     * �߂�lList��0�Ԗڂ̗v�f��ԋp����B<BR>
     * @@return EqTypeContractSettleChangeOrderUnitEntry
     * @@roseuid 43424CF90136
     */
    public EqTypeContractSettleChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnitEntry() ";
        log.entering(STR_METHOD_NAME);
        
        EqTypeContractSettleChangeOrderUnitEntry[] l_eqTypeContractSettleChangeOrderUnitEntry = 
            super.getChangeOrderUnitEntries();
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeContractSettleChangeOrderUnitEntry[0];
    }
    
    /**
     * (get������v�Z�P��)<BR>
     * ������v�Z�P�����擾����B<BR>
     * @@return double
     * @@roseuid 43424CF90165
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
     * @@roseuid 43424CF90193
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get�����㒍��������)<BR>
     * �����㒍�����������擾����B<BR>
     * @@return Date
     * @@roseuid 43424CF901B3
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
     * @@roseuid 43424CF90210
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get������T�Z��n���)<BR>
     * ������T�Z��n������擾����B<BR>
     * @@return double
     * @@roseuid 43424CF9023F
     */
    public double getModifiedEstimatedPrice() 
    {
        return this.modifiedEstimatedPrice;
    }
    
    /**
     * (set������T�Z��n���)<BR>
     * ������T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@roseuid 43424CF9026E
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
     * @@roseuid 43424CF9028D
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
     * @@roseuid 43424CF902BC
     */
    public boolean isModifiedCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (get����)<BR>
     * ���҂��擾����B<BR>
     * @@return WEB3GentradeTrader
     * @@roseuid 43424CF902EB
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
     * @@roseuid 43424CF9031A
     */
    public void setTrader(WEB3GentradeTrader l_trader) 
    {
        this.trader = l_trader;
    }
    
    /**
     * (get������P�������l)<BR>
     * ������P�������l���擾����B<BR>
     * @@return Double
     * @@roseuid 43424CF90349
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
     * @@roseuid 43424CF90378
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue) 
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }
}
@
