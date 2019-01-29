head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M�����}�l�[�W��ForMock(WEB3MutualFundOrderManagerForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �g�����M�����}�l�[�W��ForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerForMock extends WEB3MutualFundOrderManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundOrderManagerForMock.class);

    /**
     * (get�����P��(Mock))<BR>
     * ���M�����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strRequestNumber - ���ʃR�[�h<BR>
     */
    public MutualFundOrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(String , String , String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderUnit",
            new Class[] {String.class, String.class, String.class},
            new Object[]{l_strInstitutionCode, l_strBranchCode, l_strRequestNumber});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderUnit",
            new Class[] {String.class, String.class, String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            return (MutualFundOrderUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderUnit(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);
    }

    /**
     * (validate�V�K����(Mock))<BR>
     * �ivalidateNewOrder�̃I�[�o�[���[�h�j<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_web3MutualFundProduct - �g�����M����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F���� 5�F��W <BR>
     * @@param l_strPaymentMethod - (��n���@@)<BR>
     * 1�F��s�U���݁@@2�F�،���������<BR>
     * 
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * 2�F�S���@@3�F���z�@@4�F����<BR>
     * @@param l_switchingSubject - (�抷�����)<BR>
     * @@param l_taxType - (�ŋ敪) <BR>
     * 
     * �抷��̊g�����M�����I�u�W�F�N�g<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubject,
        TaxTypeEnum l_taxType,
        String l_strsettleDiv)     
        
    {
        String STR_METHOD_NAME = "validateNewOrder()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateNewOrder",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                double.class, String.class,
                String.class, String.class,
                WEB3MutualFundProduct.class,
                TaxTypeEnum.class,
                String.class},
            new Object[]{
                l_subAccount,
                l_web3MutualFundProduct,
                new Double(l_dblOrderQuantity),
                l_strProcessDiv,
                l_strPaymentMethod,
                l_strDesignateMethod,
                l_switchingSubject,
                l_taxType,
                l_strsettleDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateNewOrder",
            new Class[] {SubAccount.class,
                    WEB3MutualFundProduct.class,
                    double.class, String.class,
                    String.class, String.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class,
                    String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --�r asVoid
            return (NewOrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "validateNewOrder",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    double.class, String.class,
                    String.class, String.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class,
                    String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateNewOrder(l_subAccount,
            l_web3MutualFundProduct,
            l_dblOrderQuantity,
            l_strProcessDiv,
            l_strPaymentMethod,
            l_strDesignateMethod,
            l_switchingSubject,
            l_taxType,
            l_strsettleDiv);
    }

    /**
     * (calc�T�Z��n���(Mock))<BR>
     * �@@(3-3) �T�Z��n����ɁA�����̒������ʂ��Z�b�g����B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_web3MutualFundProduct - (�g�����M����)<BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * <BR>
     * �P�F���t<BR>
     * �Q�F���<BR>
     * �R�F�抷<BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * <BR>
     * �����w��̏ꍇ�͒��������A���z�w��̏ꍇ�͒������z<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * <BR>
     * �R�F���z�w��<BR>
     * �S�F�����w��<BR>
     * @@param l_strSettlementMethod - ���ϕ��@@<BR>
     * �P�F�~��<BR>
     * �Q�F�O��<BR>
     * @@return webbroker3.mf.WEB3MutualFundEstimatedPrice
     */
    protected WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class, String.class,
                String.class},
            new Object[]{
                l_subAccount,
                l_web3MutualFundProduct,
                l_strProcessDiv,
                new Double(l_dblOrderQuantity),
                l_strDesignateMethod,
                l_strSettlementMethod});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class, String.class,
                String.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class, String.class,
                    String.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (WEB3MutualFundEstimatedPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class, String.class,
                    String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimateDeliveryAmount(l_subAccount,
            l_web3MutualFundProduct,
            l_strProcessDiv,
            l_dblOrderQuantity,
            l_strDesignateMethod,
            l_strSettlementMethod);
    }

    /**
     * (calc�T�Z��n���(Mock))
     * �T�Z��n����i�T�Z��������A�T�Z���������j���Z�o���A <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_swtProduct - �����i�抷��j
     * @@param l_strProcessDiv - �����敪
     * @@param l_dblOrderQuantity - ��������
     * @@param l_strDesignateMethod - �w����@@
     * @@param l_strSettlementMethod - ���ϕ��@@
     * @@param l_strRequestMethod - �������@@
     * @@param l_strAccountDiv - �����敪
     * @@param l_strOrderChannel - �����`���l��
     * @@param l_datBizDate - ������
     * @@throws WEB3BaseException
     */
    public WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundProduct l_swtProduct, 
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        String l_strOrderChannel, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount(SubAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String," +
            "double, String, String, String, String, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class},
            new Object[]{
                l_subAccount,
                l_mutualFundProduct,
                l_swtProduct, 
                l_strProcessDiv,
                new Double(l_dblOrderQuantity),
                l_strDesignateMethod,
                l_strSettlementMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_strOrderChannel, 
                l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class}))
        {
            //2�jMockFor --�r asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (WEB3MutualFundEstimatedPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimateDeliveryAmount(
            l_subAccount,
            l_mutualFundProduct,
            l_swtProduct, 
            l_strProcessDiv,
            l_dblOrderQuantity,
            l_strDesignateMethod,
            l_strSettlementMethod, 
            l_strRequestMethod, 
            l_strAccountDiv, 
            l_strOrderChannel, 
            l_datBizDate);
    }

    public OrderSubmissionResult submitNewOrder(
        SubAccount subAccount,
        ProductTypeEnum productType,
        NewOrderSpec inSpec,
        long orderId,
        String tradingPassword,
        boolean skipOrderValidation)
    {

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class},
            new Object[]{
                subAccount,
                productType,
                inSpec,
                new Long(orderId),
                tradingPassword,
                new Boolean(skipOrderValidation)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class}))
        {

            //2)MockFor --�r asObject
            return (OrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class}).asObject();
        }

        return super.submitNewOrder(
                subAccount,
                productType,
                inSpec,
                orderId,
                tradingPassword,
                skipOrderValidation);
    }

    /**
     * (validate�������(Mock))<BR>
     * �ivalidateCancelOrder�̎����j<BR>
     * @@param l_mutualCancelOrderSpec - ���M����������e<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_mutualCancelOrderSpec)
    {
        String STR_METHOD_NAME = "validateCancelOrder()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            new Object[]{l_subAccount, l_mutualCancelOrderSpec});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class}))
        {

            //2)MockFor --�r asObject
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "validateCancelOrder",
                new Class[] {SubAccount.class, CancelOrderSpec.class}).asObject();
        }

        return super.validateCancelOrder(l_subAccount, l_mutualCancelOrderSpec);
    }

    /**
     * (get�������ʋ敪(Mock))<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     */
    public String getOrderQuantityDiv(MutualFundOrderUnit l_mutualFundOrderUnit)            
    {
        String STR_METHOD_NAME = "getOrderQuantityDiv()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderQuantityDiv",
            new Class[] {MutualFundOrderUnit.class},
            new Object[]{l_mutualFundOrderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderQuantityDiv",
            new Class[] {MutualFundOrderUnit.class}))
        {

            //2)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderQuantityDiv",
                new Class[] {MutualFundOrderUnit.class}).asObject();
        }

        return super.getOrderQuantityDiv(l_mutualFundOrderUnit);
    }

    /**
     * (get�T�Z��n����ʉ݃R�[�h(Mock))<BR>
     * ���M�����P�ʂ��A���̒����̊T�Z���ʋ敪�𔻒肵�ĕԋp����B<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     */
    public String getEstimateDeliveryAmountCurrencyCode(
        MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        String STR_METHOD_NAME = "getEstimateDeliveryAmountCurrencyCode()-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getEstimateDeliveryAmountCurrencyCode",
            new Class[] {MutualFundOrderUnit.class},
            new Object[]{l_mutualFundOrderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getEstimateDeliveryAmountCurrencyCode",
            new Class[] {MutualFundOrderUnit.class}))
        {
            //3)MockFor --�r asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getEstimateDeliveryAmountCurrencyCode",
                new Class[] {MutualFundOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getEstimateDeliveryAmountCurrencyCode(l_mutualFundOrderUnit);
    }

}
@
