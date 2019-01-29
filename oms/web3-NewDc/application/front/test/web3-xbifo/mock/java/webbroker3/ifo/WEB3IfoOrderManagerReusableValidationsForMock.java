head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderManagerReusableValidationsForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�����R���ʃ`�F�b�NForMock(WEB3IfoOrderManagerReusableValidationsForMock.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/01 ���G�� (���u) �V�K�쐬
 */
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨OP�����R���ʃ`�F�b�NForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoOrderManagerReusableValidationsForMock extends WEB3IfoOrderManagerReusableValidations
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoOrderManagerReusableValidationsForMock.class);

    /**
     * (validate�敨OP�����J��(Mock))<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strFuturesOptionDivision - �敨�^�I�v�V�����敪<BR>
     * 1�F�敨 2�F�I�v�V����<BR>
     * @@throws WEB3BaseException
     */
    public void validateFuturesOptionAccountOpen(SubAccount l_subAccount, String l_strFuturesOptionDivision)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateFuturesOptionAccountOpen(SubAccount, String)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen", new Class[]
                { SubAccount.class, String.class }, new Object[]
                { l_subAccount, l_strFuturesOptionDivision });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen", new Class[]
                { SubAccount.class, String.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateFuturesOptionAccountOpen", new Class[]
                    { SubAccount.class, String.class }).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateFuturesOptionAccountOpen", new Class[]
                    { SubAccount.class, String.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateFuturesOptionAccountOpen(l_subAccount, l_strFuturesOptionDivision);

    }

    /**
     * (validate�������(Mock))<BR>
     * �������ʂ�������ʂ𒴂��ĂȂ����`�F�b�N���s���B<BR>
     * @@param l_dblMaxQuantity - �������<BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validateMaxQuantity(double l_dblMaxQuantity, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateMaxQuantity(double, double)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMaxQuantity", new Class[]
                { double.class, double.class }, new Object[]
                { new Double(l_dblMaxQuantity), new Double(l_dblQuantity) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMaxQuantity", new Class[]
                { double.class, double.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMaxQuantity", new Class[]
                    { double.class, double.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateMaxQuantity", new Class[]
                    { double.class, double.class }).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateMaxQuantity(l_dblMaxQuantity, l_dblQuantity);

    }

    /**
     * (validate�������(Mock))<BR>
     * ��������̃`�F�b�N���s���A<BR>
     * �敨OP��������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * <BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoTradedProductImpl validateTradedProduct(WEB3IfoProductImpl l_ifoProduct, WEB3GentradeMarket l_market,
            boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(l_ifoProduct, l_market, l_blnIsBuyToOpenOrder, l_blnIsOpenContract)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateTradedProduct", new Class[]
                { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }, new Object[]
                { l_ifoProduct, l_market, new Boolean(l_blnIsBuyToOpenOrder), new Boolean(l_blnIsOpenContract) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateTradedProduct", new Class[]
                { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class })
                    .asWEB3BaseException();

            //3)MockFor --�r asBoolean
            return (WEB3IfoTradedProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateTradedProduct", new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateTradedProduct(l_ifoProduct, l_market, l_blnIsBuyToOpenOrder, l_blnIsOpenContract);
    }

    /**
     * (validate����(Mock))<BR>
     * ���ʂ̃`�F�b�N���s���B<BR>
     * �@@�|���ʂ��O�܂��̓}�C�i�X�l�łȂ����ƁB <BR>
     * �@@�|���ʂ�����P�ʂ𒴂��Ă��Ȃ����ƁB <BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * <BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     * @@throws WEB3BaseException
     */
    public void validateQuantity(WEB3GentradeSubAccount l_subAccount, WEB3IfoTradedProductImpl l_ifoTradedProduct,
            double l_dblQuantity, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_subAccount, l_ifoTradedProduct, l_dblQuantity, l_blnIsBuyToOpenOrder,"
                + "l_blnIsOpenContract) -->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateQuantity", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                        boolean.class }, new Object[]
                { l_subAccount, l_ifoTradedProduct, new Double(l_dblQuantity), new Boolean(l_blnIsBuyToOpenOrder),
                        new Boolean(l_blnIsOpenContract) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateQuantity", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                        boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                            boolean.class }).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                            boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateQuantity(l_subAccount, l_ifoTradedProduct, l_dblQuantity, l_blnIsBuyToOpenOrder,
                l_blnIsOpenContract);
    }

    /**
     * (validate�����P��(Mock))<BR>
     * �w�l�̃`�F�b�N���s���B<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@param l_ifoTradedProduct - �敨OP��������I�u�W�F�N�g
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateOrderUnitPrice(double l_dblLimitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct,
            SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderUnitPrice(l_dblLimitPrice,l_ifoTradedProduct,l_subAccount)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderUnitPrice", new Class[]
                { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }, new Object[]
                { new Double(l_dblLimitPrice), l_ifoTradedProduct, l_subAccount });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderUnitPrice", new Class[]
                { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderUnitPrice", new Class[]
                    { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderUnitPrice", new Class[]
                    { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProduct, l_subAccount);

    }

    /**
     * (validate���������\���(Mock))<BR>
     * �����̒������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B <BR>
     * <BR>
     * @@param l_order - (����)
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * Skip�x���󋵃`�F�b�N <BR>
     * <BR>
     * true�F�x���󋵃`�F�b�N���X�L�b�v����B�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�j <BR>
     * false�F�x���󋵃`�F�b�N���X�L�b�v���Ȃ��B<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayStatusCheck)
            throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class, boolean.class }, new Object[]
                { l_order, new Boolean(l_blnIsSkipDelayStatusCheck) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            //            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
            //                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            //                "validateOrderForChangeability",
            //                new Class[] {Order.class, boolean.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class, boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

    }

    /**
     * (validate�����h�c(Mock))<BR>
     * �����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lngProductID - �����h�c<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoProductImpl validateProductID(long l_lngProductID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductID(long)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateProductID", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngProductID) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateProductID", new Class[]
                { long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID", new Class[]
                    { long.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            return (WEB3IfoProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateProductID", new Class[]
                    { long.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateProductID(l_lngProductID);
    }

    /**
     * (validate�戵�\�w��(Mock))<BR>
     * ���X�Ŏ戵�\�Ȏw�����𔻒肷��B<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_ifoTradedProduct - �敨OP�������
     * @@throws WEB3BaseException
     */
    public void validateHandlingIndex(String l_strBranchCode, WEB3IfoTradedProductImpl l_ifoTradedProduct)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingIndex(String, WEB3IfoTradedProductImpl)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex", new Class[]
                { String.class, WEB3IfoTradedProductImpl.class }, new Object[]
                { l_strBranchCode, l_ifoTradedProduct });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex", new Class[]
                { String.class, WEB3IfoTradedProductImpl.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingIndex", new Class[]
                    { String.class, WEB3IfoTradedProductImpl.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingIndex", new Class[]
                    { String.class, WEB3IfoTradedProductImpl.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateHandlingIndex(l_strBranchCode, l_ifoTradedProduct);

    }

    /**
     * (validate��������(Mock))<BR>
     * ���������̃`�F�b�N���s���B<BR>
     * @@param l_datOrderBizDate - ������������<BR>
     * @@param l_datExpirationDate - ����������<BR>
     * @@param l_strOrderCond - ��������<BR>
     * �@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l<BR>
     * @@param l_executionConditionType - ���s����<BR>
     * @@param l_strExpirationDateType - ���������敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateOrderCond(WEB3GentradeSubAccount l_subAccount, long l_lngOrderUnitId,
            boolean l_blnIsMarketOrder, WEB3IfoTradedProductImpl l_ifoTradedProduct, boolean l_blnIsOpenContract,
            boolean l_blnIsBuyToOpenOrder, Date l_datOrderBizDate, Date l_datExpirationDate, String l_strOrderCond,
            IfoOrderExecutionConditionType l_executionConditionType, String l_strExpirationDateType,
            Long l_firstOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderCond(WEB3GentradeSubAccount,long,boolean,"
                + "WEB3IfoTradedProductImpl,boolean,boolean,Date,Date,"
                + "String,IfoOrderExecutionConditionType,String)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderCond", new Class[]
                { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class, Date.class, Date.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, Long.class }, new Object[]
                { l_subAccount, new Long(l_lngOrderUnitId), new Boolean(l_blnIsMarketOrder), l_ifoTradedProduct,
                        new Boolean(l_blnIsOpenContract), new Boolean(l_blnIsBuyToOpenOrder), l_datOrderBizDate,
                        l_datExpirationDate, l_strOrderCond, l_executionConditionType, l_strExpirationDateType,
                        l_firstOrderUnitId });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderCond", new Class[]
                { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class, Date.class, Date.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, Long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderCond",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class, Date.class, Date.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, Long.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderCond",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class, Date.class, Date.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, Long.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderCond(l_subAccount, l_lngOrderUnitId, l_blnIsMarketOrder, l_ifoTradedProduct,
                l_blnIsOpenContract, l_blnIsBuyToOpenOrder, l_datOrderBizDate, l_datExpirationDate, l_strOrderCond,
                l_executionConditionType, l_strExpirationDateType, l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateW�w�l����(Mock))<BR>
     * ���������Ƃ���W�w�l���w�肳�ꂽ�����ɂ��āA <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)<BR>
     * �����P�ʂh�c<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_strOrderCondition - (��������)<BR>
     * ��������<BR>
     * @@param l_dblOrderCondPrice - (���������P��)<BR>
     * ��������<BR>
     * @@param l_strWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * @@param l_wLimitExecCondType - (�iW�w�l�j���s����)<BR>
     * �iW�w�l�j���s����<BR>
     * @@param l_strWlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     * <BR>
     * ���������̂ݎg�p�B�V�K�����o�^���́A <BR>
     * �@@null�Œ�B<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP��������I�u�W�F�N�g<BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * �iisOpenContract�j <BR>
     * �V�K��������ǂ����̔���B <BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B <BR>
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * �iisBuyToOpenOrder�j <BR>
     * ����������ǂ����̔���B <BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B <BR>
     * @@throws WEB3BaseException 
     */
    public void validateWLimitPriceOrder(WEB3GentradeSubAccount l_subAccount, long l_lngOrderUnitId,
            double l_dblLimitPrice, String l_strOrderCondition, double l_dblOrderCondPrice, String l_strWLimitPrice,
            IfoOrderExecutionConditionType l_wLimitExecCondType, String l_strWlimitEnableStatusDiv,
            WEB3IfoTradedProductImpl l_ifoTradedProduct, boolean l_blnIsOpenContract, boolean l_blnIsBuyToOpenOrder)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
                + "double, String, IfoOrderExecutionConditionType, boolean, "
                + "WEB3IfoTradedProductImpl, boolean, boolean)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class }, new Object[]
                { l_subAccount, new Long(l_lngOrderUnitId), new Double(l_dblLimitPrice), l_strOrderCondition,
                        new Double(l_dblOrderCondPrice), l_strWLimitPrice, l_wLimitExecCondType,
                        l_strWlimitEnableStatusDiv, l_ifoTradedProduct, new Boolean(l_blnIsOpenContract),
                        new Boolean(l_blnIsBuyToOpenOrder) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateWLimitPriceOrder(l_subAccount, l_lngOrderUnitId, l_dblLimitPrice, l_strOrderCondition,
                l_dblOrderCondPrice, l_strWLimitPrice, l_wLimitExecCondType, l_strWlimitEnableStatusDiv,
                l_ifoTradedProduct, l_blnIsOpenContract, l_blnIsBuyToOpenOrder);

    }

    /**
     * (validate�������e(Mock))<BR>
     * �������͒l���Ó��ł��邩���`�F�b�N����B<BR>
     * @@param l_orderUnit - �����P��<BR>
     * �������i�����������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_dblQuantityAfterChange - ��������
     * @@param l_dblLimitPrice - �����w�l
     * @@param l_executionConditionType - �������s����
     * @@param l_strOrderConditionType - ������������
     * @@param l_strOrderCondOperator - ���������������Z�q
     * @@param l_strStopPriceType - �����t�w�l��l�^�C�v
     * @@param l_dblStopPrice - �����t�w�l��l
     * @@param l_dblWStopPrice - �����iW�w�l�j�����w�l
     * @@param l_wLimitExecCondType - �����iW�w�l�j���s����
     * @@param l_datExpriationDate - ��������������
     * @@param l_strExpirationDateType - �������������敪
     * @@param l_modifiedSettleContractEntries - �����ԍό��ʃG���g��
     * @@throws WEB3BaseException
     */
    public void validateOrderChangeSpec(OrderUnit l_orderUnit, double l_dblQuantityAfterChange, double l_dblLimitPrice,
            IfoOrderExecutionConditionType l_executionConditionType, String l_strOrderConditionType,
            String l_strOrderCondOperator, String l_strStopPriceType, double l_dblStopPrice, double l_dblWStopPrice,
            IfoOrderExecutionConditionType l_wLimitExecCondType, Date l_datExpriationDate,
            String l_strExpirationDateType, SettleContractEntry[] l_modifiedSettleContractEntries)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangeSpec(ForMock)";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderChangeSpec", new Class[]
                { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                        Date.class, String.class, SettleContractEntry[].class }, new Object[]
                { l_orderUnit, new Double(l_dblQuantityAfterChange), new Double(l_dblLimitPrice),
                        l_executionConditionType, l_strOrderConditionType, l_strOrderCondOperator, l_strStopPriceType,
                        new Double(l_dblStopPrice), new Double(l_dblWStopPrice), l_wLimitExecCondType,
                        l_datExpriationDate, l_strExpirationDateType, l_modifiedSettleContractEntries });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderChangeSpec", new Class[]
                { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                        Date.class, String.class, SettleContractEntry[].class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                            "validateOrderChangeSpec",
                            new Class[]
                            { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                                    String.class, String.class, String.class, double.class, double.class,
                                    IfoOrderExecutionConditionType.class, Date.class, String.class,
                                    SettleContractEntry[].class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                            "validateOrderChangeSpec",
                            new Class[]
                            { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                                    String.class, String.class, String.class, double.class, double.class,
                                    IfoOrderExecutionConditionType.class, Date.class, String.class,
                                    SettleContractEntry[].class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderChangeSpec(l_orderUnit, l_dblQuantityAfterChange, l_dblLimitPrice, l_executionConditionType,
                l_strOrderConditionType, l_strOrderCondOperator, l_strStopPriceType, l_dblStopPrice, l_dblWStopPrice,
                l_wLimitExecCondType, l_datExpriationDate, l_strExpirationDateType, l_modifiedSettleContractEntries);
    }

    /**
     * (validate����������Rev���(Mock))<BR>
     * �������̒���Rev������𒴂��Ȃ����ǂ������`�F�b�N����B <BR>
     * <BR>
     * @@param l_ifoOrderUnit - �����P��
     * �����O�̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_dblPriceChange - (�����w�l)<BR>
     * �����w�l<BR>
     * @@param l_changeExecCondType - (�������s����)<BR>
     * �������s����<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevLimit(IfoOrderUnit l_ifoOrderUnit, double l_dblQuantity, double l_dblPriceChange,
            IfoOrderExecutionConditionType l_changeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrderRevLimit("
                + "IfoOrderUnit, double, double, IfoOrderExecutionConditionType)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateChangeOrderRevLimit", new Class[]
                { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }, new Object[]
                { l_ifoOrderUnit, new Double(l_dblQuantity), new Double(l_dblPriceChange), l_changeExecCondType });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateChangeOrderRevLimit", new Class[]
                { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateChangeOrderRevLimit", new Class[]
                    { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class })
                    .asWEB3BaseException();

            //3)MockFor --�r asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateChangeOrderRevLimit", new Class[]
                    { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateChangeOrderRevLimit(l_ifoOrderUnit, l_dblQuantity, l_dblPriceChange, l_changeExecCondType);
    }

    /**
     * (validate�s��h�c)<BR>
     * �s��̃`�F�b�N�����{����B<BR>
     * @@param l_lngMarketID - �s��h�c<BR>
     * @@return Market
     * @@throws WEB3BaseException
     */
    public Market validateMarketID(long l_lngMarketID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarketID(l_lngMarketID)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMarketID", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngMarketID) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMarketID", new Class[]
                { long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID", new Class[]
                    { long.class }).asWEB3BaseException();

            //3)MockFor --�r asBoolean
            return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateMarketID", new Class[]
                    { long.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateMarketID(l_lngMarketID);
    }

    public void validateHandlingOpenContractOrder(SubAccount l_subAccount, boolean l_blnIsBuy) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingOpenContractOrder", new Class[]
                { SubAccount.class, boolean.class }, new Object[]
                { l_subAccount, new Boolean(l_blnIsBuy) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingOpenContractOrder", new Class[]
                { SubAccount.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateHandlingOpenContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", new Class[]
                    { SubAccount.class, boolean.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", new Class[]
                    { SubAccount.class, boolean.class }).asVoid();
            return;
        }
        super.validateHandlingOpenContractOrder(l_subAccount, l_blnIsBuy);
    }

    public void validateOrderForChangeability(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateOrderForChangeability()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validateOrderForChangeability(l_order);
    }

    public void validateOrderForCancellation(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForCancellation", new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForCancellation", new Class[]
                { Order.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateOrderForCancellation()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForCancellation", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validateOrderForCancellation(l_order);
    }

    /**
     * �i�X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B�j�B<BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        log.debug("�v���O�C�����������ɃR�[�������register");
        super.setInstance(this);
    }
}
@
