head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������(WEB3TPSettlement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 nakazato(ACT) �V�K�쐬
                   2006/09/15 �Ԑi�@@  (���u)���f��No.45
Revesion History : 2007/09/28 �И���@@(���u)���f��No.177,No.191,No.192
Revesion History : 2007/10/22 �И���@@(���u)���f��No.215
Revesion History : 2007/11/12 �И���@@(���u)���f��No.230
Revesion History : 2008/05/27 �����F�@@(���u)���f��No.283,No.284
Revesion History : 2008/06/10 �И���@@(���u)���f��No.288,No.289,No.290
Revesion History : 2010/01/11 ���g�@@  (���u)���f��No.419
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationPerProduct;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������ρj
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPSettlement
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSettlement.class);

    /**
     * �i�������j
     */
    protected WEB3TPCashValuation cashValuation;

    /**
     * �i�،��]���z�j
     */
    protected WEB3TPSecurityValuation securityValuation;

    /**
     * �i�ڋq���萄�ځj
     */
    protected WEB3TPAccountTransition accountTransition;

    /**
     * �i�]�͌v�Z�����j
     */
    protected WEB3TPCalcCondition calcCondtion;

    /**
     * �i�������ρj<BR>
     * 
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�p�����[�^���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�ڋq���萄�ڂ̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�Q�j�Ő��������ڋq���萄�ڂ��v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_cashValuation - �i�������j
     * @@param l_securityValuation - �i�،��]���z�j
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     * @@roseuid 40F73A1700E9
     */
    public WEB3TPSettlement(
        WEB3TPCashValuation l_cashValuation,
        WEB3TPSecurityValuation l_securityValuation,
        WEB3TPCalcCondition l_calcCondition)
    {
        this.cashValuation = l_cashValuation;
        this.securityValuation = l_securityValuation;
        this.calcCondtion = l_calcCondition;

        this.accountTransition = new WEB3TPAccountTransition();
    }

    /**
     * (get���v��S�����`����蔄�t�����l���`)<BR>
     * <BR>
     * �p�����[�^.�w����̖���蔄�t�������l������<BR>
     * ���v�S�������擾���ԋp����B<BR>
     * <BR>
     * �P�jthis.get���v��S�����`����蔄�t�����l���`()���R�[������B <BR>
     * <BR>
     *    [����]<BR>
     * �@@�@@�@@�@@�w����F����.�w���<BR>
     * �@@�@@�@@�@@�������ϑ����O���t�����l���t���O�Ffalse<BR>
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@return double
     */
    public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate)
    {
        //�P�jthis.get���v��S�����`����蔄�t�����l���`()���R�[������B
        return this.getDayTradeRestraintIncUnexecSellOrder(l_datSpecifiedDate, false);
    }

    /**
     * �iget���v��S�����j<BR>
     * 
     * �p�����[�^.�w����̓��v�S�������擾���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(��������)get���v��S�����v�Q��<BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@return double
     * @@roseuid 40F73A1F0399
     */
    public double getDayTradeRestraint(Date l_datSpecifiedDate)
    {
        //do�w����̌ڋq���萄�ڃ��[�h()���R�[������B
        this.loadAccountTransition(l_datSpecifiedDate, false);
        
        //���v��S�������v�Z����B
        double l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraint();

        //���v��S������ԋp����B
        return l_dblDayTradeRestraint;
    }

    
    /**
     * �iget�������ϔ��t�\���ʁj<BR>
     * 
     * �p�����[�^.�w����A�p�����[�^����������<BR>
     * �������ϔ��t�\���ʂ��擾���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(��������)get�������ϔ��t�\���ʁv�Q��<BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_dblOrderQuantity - �i�������ʁj
     * @@param l_dblLotSize - �i�����P�ʁj
     * @@return WEB3TPSellOrderPossibleQuantityResult
     */
    public WEB3TPSellOrderPossibleQuantityResult getSellOrderPossibleQuantity(
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        double l_dblOrderQuantity,
        double l_dblLotSize)
    {
        //do�w����̌ڋq���萄�ڃ��[�h()���R�[������B
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //�Ώۖ������擾����B
        WEB3TPSecurityValuationProduct l_valuationProduct =
            this.securityValuation.getProduct(l_lngOrderFundId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //�������Ə،��]���z���擾����B
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
            this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

        //�����ۗL����<�m��>���擾����B
        double l_dblExistQuantity;
        if(l_valuationPerProduct != null) 
        {
            l_dblExistQuantity = l_valuationPerProduct.calcAssetQuantity(
                    l_datSpecifiedDate, WEB3TPDepositTypeDef.TRUST);
        }
        else
        {
            l_dblExistQuantity = 0.0d;
        }                                                       

        //�������ϔ��t�\���ʂ��v�Z����B
        WEB3TPSellOrderPossibleQuantityResult l_sellOrderPossQtyResult = this.accountTransition
            .calcSellOrderPossibleQuantity(
                    l_lngOrderFundId, l_dblOrderQuantity, l_dblLotSize,
                    l_dblExistQuantity);

        //�������ϔ��t�\���ʂ�ԋp����B
        return l_sellOrderPossQtyResult;
    }

    /**
     * �iget�������ϔ��t�\�z�j<BR>
     * <BR>
     * ����.�w����A����.��������ID��<BR>
     * �������ϔ��t�\�z���擾���ԋp����B<BR>
     * <BR>
     * ����.��������ID���A���v��Ώۖ����łȂ����A<BR>
     * ����.�������t�\�z���ԋp�����B<BR>
     * <BR>
     * �V�[�P���X�}�u(��������)get�������ϔ��t�\�z�v�Q��<BR>
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@return double
     */
    public double getBuyOrderPossibleAmount(Date l_datSpecifiedDate, long l_lngOrderFundId)
    {
        //do�w����̌ڋq���萄�ڃ��[�h()���R�[������B
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //�������ϔ��t�\�z���v�Z����B
        double l_dblBuyOrderPossibleAmount =
            this.accountTransition.calcBuyOrderPossibleAmount(l_lngOrderFundId);

        //�������ϔ��t�\�z��ԋp����B
        return l_dblBuyOrderPossibleAmount;
    }

    /**
     * �iget�������Ǝ�����j<BR>
     * <BR>
     * �V�[�P���X�}�u(��������)get�������Ǝ�����v�Q��
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@return WEB3TPSettlementReflector
     */
    public WEB3TPSettlementReflector getSettlementReflector(
        Date l_datSpecifiedDate,
        long l_lngOrderFundId)
    {
        final String STR_METHOD_NAME = "getSettlementReflector(Date, long)";
        log.entering(STR_METHOD_NAME);

        //do�w����̌ڋq���萄�ڃ��[�h()���R�[������B
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //�������Ǝ�����ꗗ���擾
        List l_list = this.accountTransition.getLisSettlementReflectors();

        //LOOP�����F�������Ǝ�����ꗗ�̗v�f����
        for(Iterator l_iter = l_list.iterator(); l_iter.hasNext();)
        {
            //�v�f�I�u�W�F�N�g���擾
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector) l_iter.next();

            //�v�f�I�u�W�F�N�g�̖���ID == ����.����ID �̏ꍇ 
            if(l_element.getFundId() == l_lngOrderFundId)
            {
                //�������Ǝ�����I�u�W�F�N�g��ԋp
                log.exiting(STR_METHOD_NAME);
                return l_element;
            }
        }

        /*
         * �ȉ��A�w������̖������Ǝ�����I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�̏���
         */
        //�Ώۖ������擾����B
        WEB3TPSecurityValuationProduct l_valuationProduct = this.securityValuation.getProduct(
            l_lngOrderFundId,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //�������Ə،��]���z���擾����B
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct = this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

        //�����ۗL����<�m��>���擾����B
        double l_dblExistQuantity;
        if(l_valuationPerProduct != null)
        {
            //�p�����[�^.�w����̑O�c�Ɠ����擾����B
            Date l_datPrevDate = this.calcCondtion.rollBizDate(l_datSpecifiedDate, -1);
            
            //�w����O���̊����ۗL����<�m��>���擾����B
            double l_dblAssetQuantity = l_valuationPerProduct
                .calcAssetQuantity(
                        l_datPrevDate, WEB3TPDepositTypeDef.TRUST);

            //�w����O���̊m��������<�����ȍ~>���擾����B
            double l_dblExecutedTransactionQuantity = l_valuationPerProduct
                .calcExecutedTransactionQuantity(l_datPrevDate);

            //�����ۗL����<�m��> = �w����O���̊����ۗL����<�m��> + �w����O���̊m��������<�����ȍ~>
            l_dblExistQuantity = l_dblAssetQuantity
                    + l_dblExecutedTransactionQuantity;
        }
        else
        {
            l_dblExistQuantity = 0.0d;
        }

        /*
         * �������Ǝ�����I�u�W�F�N�g�𐶐�
         */
        WEB3TPSettlementReflector l_reflector = new WEB3TPSettlementReflector();
        //����ID
        l_reflector.setFundId(l_lngOrderFundId);
        //�w����O���ۗL����
        l_reflector.setExistQuantity(l_dblExistQuantity);

        //�������Ǝ�����I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_reflector;
    }
    
    /**
     * �ido�w����̌ڋq���萄�ڃ��[�h�j<BR>
     * �w����̎�������擾���A�ڋq���萄�ڂ�\������B<BR>
     * <BR>
     * �P�jthis.do�w����̌ڋq���萄�ڃ��[�h()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�w����F����.�w��� <BR>
     * �@@�@@�@@����蔄�t�������[�h�t���O�F����.����蔄�t�������[�h�t���O <BR>
     * �@@�@@�@@�������ϑ����O���t�����l���t���O�Ffalse<BR>
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_blnUnexecSellOrder - �i����蔄�t�������[�h�t���O�j
     */
    protected void loadAccountTransition(Date l_datSpecifiedDate, boolean l_blnUnexecSellOrder)
    {
    	this.loadAccountTransition(l_datSpecifiedDate, l_blnUnexecSellOrder, false);
    }

    /**
     * (get���v��S�����`����蔄�t�����l���`)<BR>
     * <BR>
     * �p�����[�^.�w����̖���蔄�t�������l������<BR>
     * ���v�S�������擾���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(��������)get���v��S�����`����蔄�t�����l���`�v�Q�Ɓiget���v��S�����j<BR>
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_blnIsSettlement - (�������ϑ����O���t�����l���t���O)
     * @@return double
     */
    public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate, boolean l_blnIsSettlement)
    {
        //do�w����̌ڋq���萄�ڃ��[�h()���R�[������B
        this.loadAccountTransition(l_datSpecifiedDate, true, l_blnIsSettlement);
        //���v��S����
        double l_dblDayTradeRestraint = 0D;

        if (l_blnIsSettlement)
        {
            //calc���v��S�����`0�␳�����`
            l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraintForCheck();
        }
        else
        {
            //calc���v��S����
            l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraint();
        }

        //���v��S������ԋp����B
        return l_dblDayTradeRestraint;
    }

    /**
     * �ido�w����̌ڋq���萄�ڃ��[�h�j<BR>
     * �w����̎�������擾���A�ڋq���萄�ڂ�\������B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ڋq���萄��)do�w����̌ڋq���萄�ڃ��[�h�j�v�Q��<BR>
     * <BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_blnUnexecSellOrder - �i����蔄�t�������[�h�t���O�j
     * @@param l_blnIsSettlement - (�������ϑ����O���t�����l���t���O)
     */
    protected void loadAccountTransition(Date l_datSpecifiedDate,
        boolean l_blnUnexecSellOrder,
        boolean l_blnIsSettlement)
    {
        //�p�����[�^.�w�����int�^�ɕϊ�
        int l_intSpecifiedDate = this.calcCondtion.calcSpecifiedPoint(l_datSpecifiedDate);

        //�w���(int)��this.�ڋq���萄�ڂɃZ�b�g����B
        this.accountTransition.setSpecifiedDate(l_intSpecifiedDate);

        //�p�����[�^.�w����̑O�c�Ɠ����擾����B
        Date l_datPrevDate = this.calcCondtion.rollBizDate(l_datSpecifiedDate, -1);

        //�a����c���i�w����j���擾����B
        double l_dblSpecifiedCashBalance = this.cashValuation.calcCashBalance(l_datSpecifiedDate);

        //�������ϑ���i�w����j���擾����B
        double l_dblSpecifiedTodaysExecutedTotal =
            this.cashValuation.calcTodaysExecutedTotal(l_datSpecifiedDate);

        //�������ϑ���i�w���-1�j���擾����B
        double l_dblPrevTodaysExecutedTotal =
            this.cashValuation.calcTodaysExecutedTotal(l_datPrevDate);

        //������������i�w����j���擾����B
        double l_dblSpecifiedTodaysUnexecutedTotal =
            this.cashValuation.calcTodaysUnexecutedTotal(l_datSpecifiedDate);

        //������������i�w���-1�j���擾����B
        double l_dblPrevTodaysUnexecutedTotal =
            this.cashValuation.calcTodaysUnexecutedTotal(l_datPrevDate);

        //���̑��S�����i�w����j���擾����
        double l_dblSpecifiedOtherRestraintsTotal =
            this.cashValuation.calcOtherRestraintsTotal(l_datSpecifiedDate);

        //���̑��S�����i�w���-1�j���擾����
        double l_dblPrevOtherRestraintsTotal =
            this.cashValuation.calcOtherRestraintsTotal(l_datPrevDate);

        //�������������S�����i�w���-1�j���擾����
        double l_dblPrevTodayDepFundRestraint =
            this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datPrevDate);

        //�������������S�����i�w����j���擾����
        double l_dblSpecifiedTodayDepFundRestraint =
            this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datSpecifiedDate);

        //IPO�S�����i�w���-1�j���擾����
        double l_dblPrevIPORestraint =
            this.cashValuation.getRestraint().calcIPORestraint(l_datPrevDate);

        //IPO�S�����i�w����j���擾����
        double l_dblSpecifiedIPORestraint =
            this.cashValuation.getRestraint().calcIPORestraint(l_datSpecifiedDate);

        //�T�[�r�X���p�S�����i�w���-1�j���擾����
        double l_dblPrevServiceRestraint =
            this.cashValuation.getRestraint().calcServiceChargeRestraint(l_datPrevDate);

        //�T�[�r�X���p�i�w����j���擾����
        double l_dblSpecifiedServiceRestraint =
            this.cashValuation.getRestraint().calcServiceChargeRestraint(l_datSpecifiedDate);       

        //�����M���O��S�����i�w���-1�j���擾����
        double l_dblPrevMutualFundAdvanceRestraint =
            this.cashValuation.getRestraint().calcMutualFundAdvanceRestraint(l_datPrevDate);

        //�����M���O��S�����i�w����j���擾����
        double l_dblSpecifiedMutualFundAdvanceRestraint =
            this.cashValuation.getRestraint().calcMutualFundAdvanceRestraint(l_datSpecifiedDate);       
        
        //�X�g�b�N�I�v�V�������t����S�����i�w����j���擾����
        double l_dblSpecifiedStockOptionSellAmountRestraint = 
            this.cashValuation.getRestraint().calcStockOptionSellAmountRestraint(l_datSpecifiedDate);
        
        //�X�g�b�N�I�v�V�������t����S�����i�w���-1�j���擾����
        double l_dbPrevlStockOptionSellAmountRestraint = 
            this.cashValuation.getRestraint().calcStockOptionSellAmountRestraint(l_datPrevDate);

        //get�ڋq����c�����f�ΏۊO�S������ʈꗗ
        String[] l_accountCashBalanceReflectObjectRestraintDivList = 
            this.cashValuation.getRestraint().getAccountCashBalanceReflectObjectRestraintDivList();

        //calc���S����(Date, String[])
        //[����]
        //Date = ����.�w���
        //String[] = �S����.get�ڋq����c�����f�ΏۊO�S������ʈꗗ()�̖߂�l
        double l_dbTempRestraintSpecified =
            this.cashValuation.getRestraint().calcTempRestraint(
                l_datSpecifiedDate, l_accountCashBalanceReflectObjectRestraintDivList);

        //calc���S����(Date, String[])
        //[����]
        //Date = roll�c�Ɠ�()�̖߂�l
        //String[] = �S����.get�ڋq����c�����f�ΏۊO�S������ʈꗗ()�̖߂�l
        double l_dbTempRestraintPrev =
            this.cashValuation.getRestraint().calcTempRestraint(
                l_datPrevDate, l_accountCashBalanceReflectObjectRestraintDivList);

        //�o���v�f�W�v<�m��>�i�w����j
        double l_dblSpecifiedFixedMinusTotal =
            this.cashValuation.getTransactionAmount().getFixedMinusTotal(l_datSpecifiedDate);

        //�����v�f�W�v<�m��>�i�w����j
        double l_dblSpecifiedFixedPlusTotal =
            this.cashValuation.getTransactionAmount().getFixedPlusTotal(l_datSpecifiedDate);

        /*
         * �O��S�����W�v<���v��S�����v�㕪>
         */
        double l_dblAdRestOffset = 0.0d;

        //�w������A���̔������ȑO�̏ꍇ
        if(l_intSpecifiedDate <= this.calcCondtion.getEquityBizDate())
        {
            //�O��S�����W�v<���v��S�����v�㕪>���擾
            l_dblAdRestOffset = this.cashValuation.getRestraint().calcAdvanceRestraintOffset(
                l_datSpecifiedDate);
        }

        //���v�Z1
        //
        //���̑��S�����o���v�f(�w���-1)
        //=
        //���̑��S����(�w���-1)
        //-�@@�������������S����(�w���-1)
        //-�@@IPO�S����(�w���-1)
        //-�@@�T�[�r�X���p�S����(�w���-1)
        //-�@@�����M���O��S����(�w���-1)
        //-�@@�X�g�b�N�I�v�V�������t����S����(�w���-1)
        //-�@@���S����(�w���-1)(*)
        //
        //(*)
        //���S����(�w���-1) = calc���S����(�w���-1�A�S������ʈꗗ)()�̖߂�l
        double l_dblPrevOtherRestraintsElement =
            l_dblPrevOtherRestraintsTotal
                - l_dblPrevTodayDepFundRestraint
                - l_dblPrevIPORestraint
                - l_dblPrevServiceRestraint
                - l_dblPrevMutualFundAdvanceRestraint
                - l_dbPrevlStockOptionSellAmountRestraint
                - l_dbTempRestraintPrev;

        //"�w����O������̌ڋq����c��"
        //��
        //�a����c��(�w���)�@@
        //-�@@�������ϑ��(�w���-1)�@@
        //-�@@�����������(�w���-1)
        //-�@@���̑��S�����o���v�f(�w���-1)�@@���v�Z1
        //+�@@�o���v�f�W�v<�m��>
        //-�@@�����v�f�W�v<�m��>
        //
        //���j
        //�O��S�����͉������Ȃ��B
        double l_dblPrevDateBalance =
            l_dblSpecifiedCashBalance
                - l_dblPrevTodaysExecutedTotal
                - l_dblPrevTodaysUnexecutedTotal
                - l_dblPrevOtherRestraintsElement
                + l_dblSpecifiedFixedMinusTotal
                - l_dblSpecifiedFixedPlusTotal;

        //���v�Z2
        //
        //���̑��S�����o���v�f(�w���)
        //=
        //���̑��S����(�w���)
        //-�@@���������Ώۖ����S����(�w���)
        //-�@@IPO�S����(�w���)
        //-�@@�T�[�r�X���p�S����(�w���)
        //-�@@�����M���O��S����(�w���)
        //-�@@�X�g�b�N�I�v�V�������t����S����(�w���)
        //-�@@���S����(�w���)(*)
        //
        //(*)
        //���S����(�w���) = calc���S����(�w����A�S������ʈꗗ)()�̖߂�l
        double l_dblSpecifiedOtherRestraintsElement =
            l_dblSpecifiedOtherRestraintsTotal
                - l_dblSpecifiedTodayDepFundRestraint
                - l_dblSpecifiedIPORestraint
                - l_dblSpecifiedServiceRestraint
                - l_dblSpecifiedMutualFundAdvanceRestraint
                - l_dblSpecifiedStockOptionSellAmountRestraint
                - l_dbTempRestraintSpecified;

        //"�w����̌ڋq����c��"
        //��
        //�a����c��(�w���)�@@
        //-�@@�������ϑ��(�w���)�@@
        //-�@@�����������(�w���)
        //-�@@���̑��S�����o���v�f(�w���)�@@���v�Z2
        //-�@@�O��S�����W�v<���v��S�����v�㕪>(�w���)
        //���j
        //�O��S�����͉������Ȃ��B
        //�������A�������ɂ��Ă͓��v��S�����Ƒ��E�Ƃ���B
        double l_dblSpecifiedDateBalance =
            l_dblSpecifiedCashBalance
                - l_dblSpecifiedTodaysExecutedTotal
                - l_dblSpecifiedTodaysUnexecutedTotal
                - l_dblSpecifiedOtherRestraintsElement
                - l_dblAdRestOffset;

        //�w����̌J�z�ڋq����c�����ڋq���萄�ڂɃZ�b�g����B
        this.accountTransition.setPrevDateBalance(l_dblPrevDateBalance);

        //�w����̌ڋq����c�����ڋq���萄�ڂɃZ�b�g����B
        this.accountTransition.setSpecifiedDateBalance(l_dblSpecifiedDateBalance);

        //�o���v�f�W�v
        double l_dblSpecifiedMinusTotal =
            this.cashValuation.getTransactionAmount().getMinusTotal(l_datSpecifiedDate);

        //MAX(���̑��S�����o���v�f(�w���)�@@���v�Z2
        //-�@@���̑��S�����o���v�f(�w���-1)�@@���v�Z1 , 0)
        double l_dblMaxOtherRestraints =
            Math.max(l_dblSpecifiedOtherRestraintsElement - l_dblPrevOtherRestraintsElement, 0);

        //"�o���v�f���v"
        //��
        //�o���v�f�W�v(�w���)
        //+ MAX(���̑��S�����o���v�f(�w���)�@@���v�Z2
        //-�@@���̑��S�����o���v�f(�w���-1)�@@���v�Z1 , 0)
        //+�@@�O��S�����W�v<���v��S�����v�㕪>(�w���)
        //���j
        //�O��S�����͉������Ȃ��B
        //�������A�������ɂ��Ă͓��v��S�����Ƒ��E�Ƃ���B
        this.accountTransition.setTotalPaymentAmount(
            l_dblSpecifiedMinusTotal
                + l_dblMaxOtherRestraints
                + l_dblAdRestOffset);

        //�����v�f���v���ڋq���萄�ڂɃZ�b�g����B
        this.accountTransition.setTotalReceiptAmount(
            this.cashValuation.getTransactionAmount().getPlusTotal(l_datSpecifiedDate));

        //�擾�������g�����U�N�V�����^�C�v�z����쐬����B
        //�������t�A�������t�A�M�p�����A�M�p���n�A�O�������A�O������
        FinTransactionType[] l_finTransactionTypes =
            {
                FinTransactionType.EQTYPE_EQUITY_BUY,
                FinTransactionType.EQTYPE_EQUITY_SELL,
                FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
                FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT,
                FinTransactionType.EQTYPE_FEQ_BUY,
                FinTransactionType.EQTYPE_FEQ_SELL
            };

        //������������擾����B
        WEB3TPTransactionReflector[] l_transactionReflectors =
            this.cashValuation.getTransactionAmount().getEquityTransactions(
                l_finTransactionTypes,
                l_datSpecifiedDate);

        //�������Ǝ�����̃��X�g
        List l_lisSettlementReflectors = new ArrayList();

        //������̔z��̗v�f�����擾����B
        int l_intLength = l_transactionReflectors.length;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intLength; index++)
        {
            //������(index)���擾����B
            WEB3TPTransactionReflector l_bufTransactionReflector = l_transactionReflectors[index];

            //�ŋ敪���擾����B 
            TaxTypeEnum l_taxType = l_bufTransactionReflector.getTaxType();
            //�X�g�b�N�I�v�V�����c���ɑ΂������̏ꍇ
            //(get�ŋ敪() == 5�F�X�g�b�N�I�v�V����)
            if (TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
            {
                //�ȍ~�̏������s�킸�Ɏ���LOOP��
                continue;
            }
            
            //����ID(index)���擾����
            long l_lngBufProductId = l_bufTransactionReflector.getProductId();
            //�g�����U�N�V�����^�C�v(index)���擾����
            FinTransactionType l_bufFinTransactionType =
                l_bufTransactionReflector.getFinTransactionType();
            //���ϐ���(index)���擾����
            double l_dblBufExecutedQuantity = l_bufTransactionReflector.getExecutedQuantity();
            //���ϑ��(index)���擾����
            double l_dblBufExecutedAmount = Math.abs(l_bufTransactionReflector.getExecutedAmount());
            //����萔��(index)���擾����
            double l_dblBufUnexecutedQuantity = l_bufTransactionReflector.getUnexecutedQuantity();
            //�������(index)���擾����
            double l_dblBufUnexecutedAmount = Math.abs(l_bufTransactionReflector.getUnexecutedAmount());

            //���X�g���Ɏ�����(index).����ID�Ɠ�������ID�����������Ǝ����񂪑��݂��邩�ǂ����̃t���O
            boolean l_isFlg = false;
            //�����q���擾����B
            Iterator l_iterator = l_lisSettlementReflectors.iterator();
            //�v�f�����݂��A�t���O���ufalse�v�̊ԁA�J��Ԃ�����
            while (l_iterator.hasNext() && l_isFlg == false)
            {
                //�������Ǝ�����(�J�����g)���擾����B 
                WEB3TPSettlementReflector l_curSettlementReflector =
                    (WEB3TPSettlementReflector)l_iterator.next();
                //�������Ǝ�����(�J�����g).����ID���擾����B
                long l_lngCurFundId = l_curSettlementReflector.getFundId();

                //������(index).����ID�Ǝ擾��������ID����������
                if (l_lngBufProductId == l_lngCurFundId)
                {
                	WEB3TPSettlementReflector l_newSettlementReflector = null;
                	//�������ϑ����O���t�����l���t���O = true
                	if (l_blnIsSettlement)
                	{
                		//�������Ǝ�����<�������ϑ����O���t�����l��>
                		WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount l_settlementRef =
                			new WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount();
                		l_newSettlementReflector = l_settlementRef;
                	}
                	else
                	{
                        //�V�����������Ǝ�������쐬����B
                        l_newSettlementReflector =
                            new WEB3TPSettlementReflector();
                	}
                    //�V�����������Ǝ�����Ɏ�������Z�b�g����B
                    l_newSettlementReflector = l_curSettlementReflector;

                    //�������t�܂��͐M�p���� ������"�O������"�̎�
                    if (l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_BUY)
                        == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
                            == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_BUY))
                    {
                        //���t���ʁi= ���ϐ���(index) + ����萔��(index) + �������Ǝ�����.���t���ʁj���v�Z����B
                        double l_dblBuyQuantity =
                            l_dblBufExecutedQuantity
                                + l_dblBufUnexecutedQuantity
                                + l_curSettlementReflector.getBuyQuantity();

                        //���t����i= ���ϑ��(index) + �������(index) + �������Ǝ�����.���t����j���v�Z����B
                        double l_dblBuyAmount =
                            l_dblBufExecutedAmount
                                + l_dblBufUnexecutedAmount
                                + l_curSettlementReflector.getBuyAmount();

                        //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����B
                        l_newSettlementReflector.setBuyQuantity(l_dblBuyQuantity);
                        //�V�����������Ǝ�����ɔ��t������Z�b�g����B
                        l_newSettlementReflector.setBuyAmount(l_dblBuyAmount);

                    }
                    //�������t ������"�O������"�̎�
                    else if (
                        l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_SELL)
                            == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_SELL))
                    {
                        //���t���ʁi= ���ϐ���(index) + �������Ǝ�����.���t���ʁj���v�Z����B
                        double l_dblSellQuantity =
                            l_dblBufExecutedQuantity + l_curSettlementReflector.getSellQuantity();

                        //���t����i= ���ϑ��(index) + �������Ǝ�����.���t����j���v�Z����B
                        double l_dblSellAmount =
                            l_dblBufExecutedAmount + l_curSettlementReflector.getSellAmount();

                        /*
                         * ����蔄�t���ʁi= ����萔��(index) + �������Ǝ�����.����蔄�t���ʁj���v�Z����B
                         */
                        double l_dblUnexecutedSellQuantity;

                        //����蔄�t�������[�h�t���O == true�̏ꍇ
                        if(l_blnUnexecSellOrder == true)
                        {
                            //����蔄�t���� = ����萔��(index) + �������Ǝ�����.����蔄�t����
                            l_dblUnexecutedSellQuantity = l_dblBufUnexecutedQuantity
                                    + l_curSettlementReflector
                                        .getUnexecutedSellQuantity();
                        }
                        //�ȊO�i����蔄�t�������[�h�t���O == false�j�̏ꍇ
                        else
                        {
                            //����蔄�t���� = 0
                            l_dblUnexecutedSellQuantity = 0.0d;
                        }
                           
                        //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����
                        l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                        //�V�����������Ǝ�����ɔ��t������Z�b�g����
                        l_newSettlementReflector.setSellAmount(l_dblSellAmount);
                        //�V�����������Ǝ�����ɖ���蔄�t���ʂ��Z�b�g����
                        l_newSettlementReflector.setUnexecutedSellQuantity(
                            l_dblUnexecutedSellQuantity);

                    }
                    //�M�p���n�̎�
                    else if (
                        l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT)
                            == true)
                    {
                        //���t���ʁi= ���ϐ���(index) + ����蔄�t����(index) + �������Ǝ�����.���t���ʁj���v�Z����B
                        double l_dblSellQuantity =
                            l_dblBufExecutedQuantity
                                + l_dblBufUnexecutedQuantity
                                + l_curSettlementReflector.getSellQuantity();

                        //���t����i= ���ϑ��(index) + ����蔄�t���(index) + �������Ǝ�����.���t����j���v�Z����B
                        double l_dblSellAmount =
                            l_dblBufExecutedAmount
                                + l_dblBufUnexecutedAmount
                                + l_curSettlementReflector.getSellAmount();

                        //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����
                        l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                        //�V�����������Ǝ�����ɔ��t������Z�b�g����
                        l_newSettlementReflector.setSellAmount(l_dblSellAmount);

                    }

                    //�Â��������Ǝ���������X�g���폜����B
                    l_iterator.remove();
                    //�V�����������Ǝ���������X�g�ɒǉ�����B
                    l_lisSettlementReflectors.add(l_newSettlementReflector);
                    //�t���O��TRUE��������B
                    l_isFlg = true;

                }
            }

            if (l_isFlg == false)
            {
            	WEB3TPSettlementReflector l_newSettlementReflector = null;
            	//�������ϑ����O���t�����l���t���O = true
            	if (l_blnIsSettlement)
            	{
            		//�������Ǝ�����<�������ϑ����O���t�����l��>
            		WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount l_settlementRef =
            			new WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount();
            		l_newSettlementReflector = l_settlementRef;
            	}
            	else
            	{
                    //�V�����������Ǝ�������쐬����B
                    l_newSettlementReflector =
                        new WEB3TPSettlementReflector();
            	}

                /*
                 * �����ۗL����<�m��>���擾
                 */
                //�Ώۖ������擾����B
                WEB3TPSecurityValuationProduct l_valuationProduct =
                    this.securityValuation.getProduct(l_lngBufProductId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //�������Ə،��]���z���擾����B
                WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
                    this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

                //�����ۗL����<�m��>
                double l_dblExistQuantity;
                if(l_valuationPerProduct != null)
                {
                    //�w����O���̊����ۗL����<�m��>���擾����B
                    double l_dblAssetQuantity = l_valuationPerProduct
                        .calcAssetQuantity(
                                l_datPrevDate, WEB3TPDepositTypeDef.TRUST);

                    //�w����O���̊m��������<�����ȍ~>���擾����B
                    double l_dblExecutedTransactionQuantity = l_valuationPerProduct
                        .calcExecutedTransactionQuantity(l_datPrevDate);

                    //�����ۗL����<�m��> = �w����O���̊����ۗL����<�m��> + �w����O���̊m��������<�����ȍ~>
                    l_dblExistQuantity = l_dblAssetQuantity
                            + l_dblExecutedTransactionQuantity;
                }
                else
                {
                    l_dblExistQuantity = 0.0d;
                }
                
                /*
                 * �V�����������Ǝ�����̃v���p�e�B�ɒl���Z�b�g
                 */
                //�V�����������Ǝ�����ɖ���ID���Z�b�g����B
                l_newSettlementReflector.setFundId(l_lngBufProductId);
                //�V�����������Ǝ�����Ɏw����O���ۗL���ʂ��Z�b�g����B
                l_newSettlementReflector.setExistQuantity(l_dblExistQuantity);

                //�������t�܂��͐M�p���� ������"�O������"�̎�
                if (l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_BUY) == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
                        == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_BUY))
                {
                    //���t���ʁi= ���ϐ���(index) + ����萔��(index)�j���v�Z����B
                    double l_dblBuyQuantity = l_dblBufExecutedQuantity + l_dblBufUnexecutedQuantity;

                    //���t����i= ���ϑ��(index) + �������(index)�j���v�Z����B
                    double l_dblBuyAmount = l_dblBufExecutedAmount + l_dblBufUnexecutedAmount;

                    //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����B
                    l_newSettlementReflector.setBuyQuantity(l_dblBuyQuantity);
                    //�V�����������Ǝ�����ɔ��t������Z�b�g����B
                    l_newSettlementReflector.setBuyAmount(l_dblBuyAmount);

                }
                //�������t  ������"�O������"�̎�
                else if (
                    l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_SELL) == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_SELL))
                {
                    //���t���ʁi= ���ϐ���(index)�j���v�Z����B
                    double l_dblSellQuantity = l_dblBufExecutedQuantity;

                    //���t����i= ���ϑ��(index)�j���v�Z����B
                    double l_dblSellAmount = l_dblBufExecutedAmount;

                    /*
                     * ����蔄�t���ʁi= ����萔��(index)�j���v�Z����B
                     */
                    double l_dblUnexecutedSellQuantity;

                    //����蔄�t�������[�h�t���O == true�̏ꍇ
                    if(l_blnUnexecSellOrder == true)
                    {
                        //����蔄�t���ʁ@@= ����萔��(index)
                        l_dblUnexecutedSellQuantity = l_dblBufUnexecutedQuantity;
                    }
                    //�ȊO�i����蔄�t�������[�h�t���O == false�j�̏ꍇ
                    else
                    {
                        //����蔄�t���� = 0
                        l_dblUnexecutedSellQuantity = 0.0d;
                    }

                    //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����
                    l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                    //�V�����������Ǝ�����ɔ��t������Z�b�g����
                    l_newSettlementReflector.setSellAmount(l_dblSellAmount);
                    //�V�����������Ǝ�����ɖ���蔄�t���ʂ��Z�b�g����
                    l_newSettlementReflector.setUnexecutedSellQuantity(l_dblUnexecutedSellQuantity);

                }
                //�M�p���n�̎�
                else if (
                    l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT)
                        == true)
                {
                    //���t���ʁi= ���ϐ���(index) + ����蔄�t����(index)�j���v�Z����B
                    double l_dblSellQuantity =
                        l_dblBufExecutedQuantity + l_dblBufUnexecutedQuantity;

                    //���t����i= ���ϑ��(index) + ����蔄�t���(index)�j���v�Z����B
                    double l_dblSellAmount = l_dblBufExecutedAmount + l_dblBufUnexecutedAmount;

                    //�V�����������Ǝ�����ɔ��t���ʂ��Z�b�g����
                    l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                    //�V�����������Ǝ�����ɔ��t������Z�b�g����
                    l_newSettlementReflector.setSellAmount(l_dblSellAmount);

                }

                /*
                 * �V�����������Ǝ���������X�g�ɒǉ�����B
                 */
                l_lisSettlementReflectors.add(l_newSettlementReflector);

            }
        }

        //�������Ǝ�����̃��X�g���ڋq���萄�ڂɃZ�b�g����B
        this.accountTransition.setLisSettlementReflectors(l_lisSettlementReflectors);
    }
}
@
