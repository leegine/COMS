head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerSettlementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ώ���]�͎擾�T�[�r�XImpl(WEB3TPTradingPowerSettlementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/22 nakazato(ACT) �V�K�쐬
Revesion History : 2007/09/28 �g�E�N�|�i���u�j���f��No.189
Revesion History : 2007/10/10 �g�E�N�|�i���u�j���f��No.205
Revesion History : 2007/10/22 �И���i���u�j�����̖��No.005
Revesion History : 2008/03/14 �����Q (���u) ���f��No.263�ANo.264
*/
package webbroker3.tradingpower;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPExcludeExceptSettlementBuyAmountCheckDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlementReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

/**
 * (�������ώ���]�̓T�[�r�XImpl)
 */
public class WEB3TPTradingPowerSettlementServiceImpl implements WEB3TPTradingPowerSettlementService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerSettlementServiceImpl.class);

    /**
     * (�R���X�g���N�^)
     */
    public WEB3TPTradingPowerSettlementServiceImpl()
    {
    }

    /**
     * �iget�������ϔ��t�\�z�j<BR>
     * <BR>
     * �V�[�P���X�}�u(�������ώ���]�̓T�[�r�X)get�������ϔ��t�\�z�v�Q��
     * <BR>
     * @@param l_subAccount - �i�⏕�����j
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_blnTodayRepFund - �i���������Ώۖ����t���O�j
     * �@@�E�w����������������Ώۖ����̏ꍇ��true
     * �@@�E�w����������������Ώۖ����łȂ��ꍇ��false
     * @@return double
     */
    public double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        boolean l_blnTodayRepFund)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getBuyOrderPossibleAmount(WEB3GentradeSubAccount, Date, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //����ID���擾
            long l_lngAccountId = l_subAccount.getAccountId();

            //�ڋq�I�u�W�F�N�g���擾
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            //�M�p�����J�݋敪���擾
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�]�͌v�Z�����I�u�W�F�N�g�𐶐�
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            //�g�p�\�������
            WEB3TPActualAccountBalanceInfo l_actualBalInfo = null;

            //����.�w�����null�̏ꍇ
            if(l_datSpecifiedDate != null)
            {
                //�������ϔ��t�\�z���v�Z
                double l_dblSettlementBuyAmt =
                    l_tpUpd.settlement.getBuyOrderPossibleAmount(l_datSpecifiedDate, l_lngOrderFundId);

                //����.�w�����int�^�ɕϊ�����B
                int l_intSpecifiedDate = l_calcCond.calcSpecifiedPoint(l_datSpecifiedDate);
                //int�^�ɕϊ������w������A�]�͌v�Z���<�������t/�M�p����>�ɃZ�b�g
                l_calcCond.setEquityBasePoint(l_intSpecifiedDate);

                //�������ϔ��t�\�z��-1�̏ꍇ
                if(l_dblSettlementBuyAmt != -1)
                {
                    /*
                     * �g�p�\�������I�u�W�F�N�g�𐶐�
                     */
                    l_actualBalInfo = new WEB3TPActualAccountBalanceInfo();
                    l_actualBalInfo.settlementBuyAmount = l_dblSettlementBuyAmt;
                    l_actualBalInfo.specifiedPoint = l_intSpecifiedDate;
                }
            }

            //�]�͌v�Z����
            WEB3TPCalcResult l_calcResult = null;

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                /*
                 * �]�͌v�Z����(List)���擾
                 */
                List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();

                //���Y�]�͏��I�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerCalcEquity l_calcEquity =
                    new WEB3TPTradingPowerCalcEquity(l_updResults, l_actualBalInfo, l_calcCond);

                //����.���������Ώۖ����t���O==true�̏ꍇ
                if (l_blnTodayRepFund == true)
                {
                    //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                    l_calcResult = l_calcEquity.calcAppliedEquityTradingPowerTodayDepFund();
                }
                //�ȊO(����.���������Ώۖ����t���O==false)�̏ꍇ
                else
                {
                    //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                    l_calcResult = l_calcEquity.calcAppliedEquityTradingPower();
                }
            }
            //�ȊO(�M�p�ڋq)�̏ꍇ
            else
            {
                /*
                 * �]�͌v�Z����(List)���擾
                 */
                List l_updResults =
                    l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

                //���Y�]�͏��I�u�W�F�N�g�𐶐�
                WEB3TPTradingPowerCalcMargin l_calcMargin =
                    new WEB3TPTradingPowerCalcMargin(l_updResults, l_actualBalInfo, l_calcCond);

                //����.���������Ώۖ����t���O==true�̏ꍇ
                if (l_blnTodayRepFund == true)
                {
                    //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                    l_calcResult = l_calcMargin.calcAppliedEquityTradingPowerTodayDepFund();
                }
                //�ȊO(����.���������Ώۖ����t���O==false)�̏ꍇ
                else
                {
                    //�]�͌v�Z���ʃI�u�W�F�N�g���擾
                    l_calcResult = l_calcMargin.calcAppliedEquityTradingPower();
                }
            }

            //0�␳
            double l_dblTradingPower = Math.max(0, l_calcResult.tradingPower);
            log.debug("�������ώ���\�z = " + Double.toString(l_dblTradingPower));

            //�������ϔ��t�\�z(=�]�͌v�Z����.����\�z)��ԋp
            log.exiting(STR_METHOD_NAME);
            return l_dblTradingPower;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * �iget�������ϔ��t�\���ʁj<BR>
     * <BR>
     * �V�[�P���X�}�u(�������ώ���]�̓T�[�r�X)get�������ϔ��t�\���ʁv�Q��<BR>
     * <BR>
     * @@param l_subAccount - �i�⏕�����j
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_lngMarketId - �i�s��ID�j
     * @@param l_dblLimitPrice - �i�w�l�j
     * @@param l_dblLotSize - �i�����P�ʁj
     * @@return double
     */
    public double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        long l_lngMarketId,
        double l_dblLimitPrice,
        double l_dblLotSize) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSellOrderPossibleQuantity(WEB3GentradeSubAccount, Date, long, long, double, double)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕�����܂��́A����.�w�����null�̏ꍇ
        if(l_subAccount == null || l_datSpecifiedDate == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass()
                .getName()
                + "."
                + STR_METHOD_NAME);
        }

        try
        {
            //����ID
            long l_lngAccountId = l_subAccount.getAccountId();
            //�ڋq�I�u�W�F�N�g
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            //�M�p�����J�݋敪
            boolean l_blnMargin = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�]�͌v�Z����
            WEB3TPCalcCondition l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            //�w���(:int�^)
            int l_intSpecifiedDate = l_intSpecifiedDate = l_calcCond.calcSpecifiedPoint(l_datSpecifiedDate);

            /*
             * (�����O)���o�\�������擾
             */
            //(�����O)���t���]�͍X�V
            WEB3TPTradingPowerUpdAfterSell l_tpUpdBefore = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                null,
                l_lngOrderFundId);

            //(�����O)���o�\����
            double l_dblActPayBalBefore;

            //�����ڋq�̏ꍇ
            WEB3TPTradingPowerCalcEquity l_calcEquityBefore = null;
            if(l_blnMargin == false)
            {
                l_calcEquityBefore = new WEB3TPTradingPowerCalcEquity(
                    l_tpUpdBefore.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                    l_calcCond);
                l_dblActPayBalBefore = l_calcEquityBefore.calcActualPaymentBalance(l_intSpecifiedDate);
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                WEB3TPTradingPowerCalcMargin l_calcMarginBefore = new WEB3TPTradingPowerCalcMargin(
                    l_tpUpdBefore.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                    l_calcCond);
                l_dblActPayBalBefore = l_calcMarginBefore.calcActualPaymentBalance(l_intSpecifiedDate);
            }

            /*
             * �����P�����擾
             */
            //�����P��
            double l_dblPrice;

            //����.�w�l = 0�̏ꍇ
            if(l_dblLimitPrice == 0)
            {
                //�������擾
                l_dblPrice = l_calcCond.getEqtypeQuote(l_lngOrderFundId, l_lngMarketId);

                //���� = 0�̏ꍇ
                if(l_dblPrice == 0)
                {
                    //T+0���擾
                    Date l_datT0 = l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0);

                    //�I�l���擾
                    l_dblPrice = l_calcCond.getClosingPrice(
                        l_lngOrderFundId,
                        l_datT0,
                        l_lngMarketId,
                        false);

                    //�I�l = 0 �̏ꍇ
                    if(l_dblPrice == 0)
                    {
                        /*
                         * �O���I�l���擾
                         */
                        EqtypeTradedProductRow l_row = EqtypeTradedProductDao.findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate(
                            l_mainAccount.getInstitution().getInstitutionCode(),
                            l_lngOrderFundId,
                            l_lngMarketId,
                            WEB3DateUtility.formatDate(l_datT0, "yyyyMMdd"));

                        if(l_row != null)
                        {
                            l_dblPrice = l_row.getLastClosingPrice();
                        }
                    }
                }
            }
            //�ȊO�̏ꍇ
            else
            {
                l_dblPrice = l_dblLimitPrice;
            }

            /*
             * (�ő�/�ŏ�)�������ʂ��擾
             *              
             * ���j�����P�ʂŊۂ߂�
             */
            //�������Ǝ�����
            WEB3TPSettlementReflector l_orderReflector = l_tpUpdBefore.getSettlement()
                .getSettlementReflector(l_datSpecifiedDate, l_lngOrderFundId);

            //�������� = �ő唄�t�\����( = �w����O���ۗL���� + ���t���ʁ@@- ���t���ʁ@@- ����蔄�t����)
            double l_dblOrderQty = l_orderReflector.getExistQuantity()
                + l_orderReflector.getBuyQuantity()
                - l_orderReflector.getSellQuantity()
                - l_orderReflector.getUnexecutedSellQuantity();
            //�����P�ʂŊۂ߂�
            l_dblOrderQty = Math.floor(l_dblOrderQty / l_dblLotSize) * l_dblLotSize;
            
            //�ŏ����t�\���� = MAX( (�w����O���ۗL���� - ���t���ʁ@@- ����蔄�t����), 0)
            double l_dblMinOrderQty = l_orderReflector.getExistQuantity()
                - l_orderReflector.getSellQuantity()
                - l_orderReflector.getUnexecutedSellQuantity();
            l_dblMinOrderQty = Math.max(l_dblMinOrderQty, 0);
            //�����P�ʂŊۂ߂�
            l_dblMinOrderQty = Math.floor(l_dblMinOrderQty / l_dblLotSize) * l_dblLotSize;
            
            /*
             * ���������e�𐶐�
             */
            WEB3TPNewOrderSpec l_newOrderSpec = new WEB3TPNewOrderSpec();
            //�⏕����ID
            l_newOrderSpec.setSubAccountId(l_subAccount.getSubAccountId());
            //�⏕�����^�C�v
            l_newOrderSpec.setSubAccountType(l_subAccount.getSubAccountType());
            //����ID
            l_newOrderSpec.setOrderId(WEB3TPNewOrderSpec.DEFAULT_NEW_ID);
            //�����P��ID
            l_newOrderSpec.setOrderUnitId(WEB3TPNewOrderSpec.DEFAULT_NEW_ID);
            //����ID
            l_newOrderSpec.setProductId(l_lngOrderFundId);
            //�����^�C�v
            l_newOrderSpec.setProductType(ProductTypeEnum.EQUITY);
            //�s��ID
            l_newOrderSpec.setMarketId(l_lngMarketId);
            //�����J�e�S��
            l_newOrderSpec.setOrderCategory(OrderCategEnum.ASSET);
            //�����^�C�v
            l_newOrderSpec.setOrderType(OrderTypeEnum.EQUITY_SELL);
            //����
            l_newOrderSpec.setQuantity(l_dblOrderQty);
            //�P��
            l_newOrderSpec.setPrice(l_dblPrice);
            //�w�l
            l_newOrderSpec.setLimitPrice(l_dblLimitPrice);
            //�T�Z���
            l_newOrderSpec.setEstimatedPrice(l_dblPrice * l_dblOrderQty);
            //������
            l_newOrderSpec.setOrderBizDate(l_calcCond.getBizDate(WEB3TPSpecifiedPointDef.T_0));
            //��n��
            l_newOrderSpec.setDeliveryDate(l_datSpecifiedDate);
            //�ԍώw����
            l_newOrderSpec.setContractSettleSpecify(null);
            //���n���v
            l_newOrderSpec.setCapitaGain(0);
            //�ŋ敪
            l_newOrderSpec.setTaxType(TaxTypeEnum.NORMAL);
            //�o���\���敪
            l_newOrderSpec.setPaymentApplicationDiv(null);
            //��t����
            l_newOrderSpec.setReceivedDateTime(null);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = {l_newOrderSpec};

            /*
             * (������)���t���]�͍X�V
             */
            WEB3TPTradingPowerUpdAfterSell l_tpUpdAfter = new WEB3TPTradingPowerUpdAfterSell(
                l_lngAccountId,
                l_blnMargin,
                l_calcCond,
                l_newOrderSpecs,
                l_lngOrderFundId);

            /*
             * �ő�LOOP�񐔂��擾
             */
            String l_strMaxLoopCount = GtlUtils.getTradingSystem().getPreference(
                "system.tradingpower.posssellorder.loopcount");
            int l_intMaxLoopCount = Integer.MAX_VALUE;
            if(l_strMaxLoopCount != null)
            {
                l_intMaxLoopCount = Integer.parseInt(l_strMaxLoopCount);
            }

            //(������)���o�\����
            double l_dblActPayBalAfter = 0;

            for(int l_intLoopCnt = 0; l_intLoopCnt < l_intMaxLoopCount; l_intLoopCnt++)
            {
                //LOOP�����񐔂�0�ȊO�̏ꍇ
                if(l_intLoopCnt != 0)
                {
                    //�������� = �������� - �����P��
                    l_dblOrderQty = l_dblOrderQty - l_dblLotSize;

                    /*
                     * (*)����t���[�F�������ʂ��ŏ����t�\���ʈȉ��ł���ꍇ
                     */
                    if(l_dblOrderQty <= l_dblMinOrderQty)
                    {
                        log.exiting(STR_METHOD_NAME);
                        return l_dblMinOrderQty;
                    }

                    //���񔄕t������������̓��e�����Z�b�g����B
                    l_tpUpdAfter.reSetWEB3TPTransactionReflectorNewSellOrder(l_dblOrderQty);
                }

                /*
                 * (������)���o�\�������擾
                 */
                //�����ڋq�̏ꍇ
                WEB3TPTradingPowerCalcEquity l_calcEquityAfter = null;
                if(l_blnMargin == false)
                {
                    l_calcEquityAfter = new WEB3TPTradingPowerCalcEquity(
                        l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(),
                        l_calcCond);
                    l_dblActPayBalAfter = l_calcEquityAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                }
                //�M�p�ڋq�̏ꍇ
                else
                {
                    WEB3TPTradingPowerCalcMargin l_calcMarginAfter = new WEB3TPTradingPowerCalcMargin(
                        l_tpUpdAfter.calcTradingpowerUpdResultMarginIncUnexecSellOrder(),
                        l_calcCond);
                    l_dblActPayBalAfter = l_calcMarginAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                }

                //�������ϑ����O���t�����l����(������)���o�\����
                double l_dblActualPaymentBalance = 0;

                //(*)����t���[
                //�����ڋq�i�ڋq.is�M�p�����J��() == false�j�A���A
                // (������)���o�\���� < 0�A���A
                // (�����O)���o�\���� <= (������)���o�\�����̏ꍇ
                if (!l_blnMargin && l_dblActPayBalAfter < 0 && l_dblActPayBalBefore <= l_dblActPayBalAfter)
                {
                    //get��Е��X�ʗ]�͌v�Z����(String)
                    String l_strExcludeExceptSettlementBuyAmountCheck =
                        l_calcCond.getInstBranCalcCondition(
                            WEB3TPCalcCondition.EXCLUDE_EXCEPT_SETTLEMENT_BUY_AMOUNT_CHECK);

                    //is�a��،��]����( )
                    boolean l_blnIsAssetEvalDiv = l_calcCond.isAssetEvalDiv();

                    //get���v��S����(int)
                    //�i�����O�j���v��S�������擾����B
                    //[����]
                    //��n��:�]�͌v�Z����.calc�w���()�̖߂�l
                    double l_dblDayTradeRestraintBefore = l_calcEquityBefore.getDayTradeRestraint(l_intSpecifiedDate);

                    //get���v��S����(int)
                    //�i������j���v��S�������擾����B
                    //[����]
                    //��n��:�]�͌v�Z����.calc�w���()�̖߂�l
                    double l_dblDayTradeRestraintAfter = l_calcEquityAfter.getDayTradeRestraint(l_intSpecifiedDate);

                    // (*)����t���[
                    // �������ϑ����O���t�����l���̍������σ`�F�b�N����(get��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F���{����)���A
                    // is�a��،��]����()�̖߂�l == true ���A
                    // (�����O)get���v��S����(T+��n��) < (������)get���v��S����(T+��n��) �̏ꍇ
                    if (WEB3TPExcludeExceptSettlementBuyAmountCheckDef.EXECUTE.equals(
                        l_strExcludeExceptSettlementBuyAmountCheck)
                        && l_blnIsAssetEvalDiv && l_dblDayTradeRestraintBefore < l_dblDayTradeRestraintAfter)
                    {
                        //calc�]�͍X�V���e<�����ڋq>�`����蔄�t�����l���`(boolean)
                        List l_lisOrders = l_tpUpdAfter.calcTradingpowerUpdResultEquityIncUnexecSellOrder(true);

                        //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
                        l_calcEquityAfter =
                            new WEB3TPTradingPowerCalcEquity(l_lisOrders, l_calcCond);

                        //calc���o�\����(int)
                        l_dblActualPaymentBalance =
                            l_calcEquityAfter.calcActualPaymentBalance(l_intSpecifiedDate);
                    }
                }

                // (*)����t���[�F�������ʂ��g�����h�ł���ꍇ
                // �������ϑ����O���t�����l����(������)���o�\���� >= 0 ���A
                // (�u(������)���o�\���� >= 0�v �܂���
                // �u(������)���o�\���� >= (�����O)���o�\�����v)�̏ꍇ
                if(l_dblActualPaymentBalance >= 0 && (l_dblActPayBalAfter >= 0
                    || l_dblActPayBalAfter >= l_dblActPayBalBefore))
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_dblOrderQty;
                }
            }

            /*
             * ���t�\���ʂ��v�Z����B
             * [�v�Z��]
             * �@@���t�\���� = MAX( �������� - ABS( (������)���o�\���� / ���ϒP��(*) ), �ŏ����t�\����))
             * 
             * (*)���ϒP�� = ���t��� / ���t����
             * 
             * ���j�����P�ʂŊۂ߂�
             * 
             */
            //���ϒP��
            double l_dblAve = l_orderReflector.getBuyAmount() / l_orderReflector.getBuyQuantity();

            //���t�\����
            double l_dblPossSell = Math.max(l_dblOrderQty
                - Math.abs(l_dblActPayBalAfter / l_dblAve), l_dblMinOrderQty);
            //�����P�ʂŊۂ߂�
            l_dblPossSell = Math.floor(l_dblPossSell / l_dblLotSize) * l_dblLotSize;
            
            //���t�\���ʂ�ԋp
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblPossSell, 0);
        }
        catch(WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }
}
@
