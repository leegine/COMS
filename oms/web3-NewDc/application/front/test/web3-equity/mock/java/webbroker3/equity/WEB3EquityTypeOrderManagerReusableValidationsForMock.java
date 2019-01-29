head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTypeOrderManagerReusableValidationsForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3EquityTypeOrderManagerReusableValidationsForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/30 ���G�� (���u) �V�K�쐬
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3EquityTypeOrderManagerReusableValidationsForMock 
    extends WEB3EquityTypeOrderManagerReusableValidations
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTypeOrderManagerReusableValidationsForMock.class);
    /**
     * �ivalidate���ϑ�������(Mock)�j<BR>
     * <BR>
     */
     public void validateSettleContractTotalQuantity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_equityTradedProduct,
        TaxTypeEnum l_taxTypeEnum,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        double l_dblQuantity,
        ContractTypeEnum l_contractType) throws WEB3BaseException
     {
        final String STR_METHOD_NAME =
            "validateSettleContractTotalQuantity(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, TaxTypeEnum, String, double, double, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                "validateSettleContractTotalQuantity",
                new Class[] 
                  {
                    WEB3GentradeSubAccount.class,
                    long.class,
                    WEB3EquityTradedProduct.class,
                    TaxTypeEnum.class,
                    String.class,
                    double.class,
                    double.class,
                    ContractTypeEnum.class
                   },
                new Object[]{
                    l_subAccount, 
                    new Long(l_lngOrderUnitId),
                    l_equityTradedProduct,
                    l_taxTypeEnum, 
                    l_strRepaymentType, 
                    new Double(l_dblRepaymentNum), 
                    new Double(l_dblQuantity),
                    l_contractType});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                "validateSettleContractTotalQuantity",
                new Class[] 
                  {
                    WEB3GentradeSubAccount.class,
                    long.class,
                    WEB3EquityTradedProduct.class,
                    TaxTypeEnum.class,
                    String.class,
                    double.class,
                    double.class,
                    ContractTypeEnum.class
                   }))
            {
                //2�jMockFor --�r asWEB3BaseException
                log.exiting(STR_METHOD_NAME);
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                    "validateSettleContractTotalQuantity",
                    new Class[] 
                      {
                        WEB3GentradeSubAccount.class,
                        long.class,
                        WEB3EquityTradedProduct.class,
                        TaxTypeEnum.class,
                        String.class,
                        double.class,
                        double.class,
                        ContractTypeEnum.class
                       }).asWEB3BaseException();

                //3)MockFor --�r asVoid
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations",
                    "validateSettleContractTotalQuantity",
                    new Class[] 
                          {
                            WEB3GentradeSubAccount.class,
                            long.class,
                            WEB3EquityTradedProduct.class,
                            TaxTypeEnum.class,
                            String.class,
                            double.class,
                            double.class,
                            ContractTypeEnum.class
                           }).asVoid();
                
                return;
            }

            log.exiting(STR_METHOD_NAME);
            super.validateSettleContractTotalQuantity(
                l_subAccount, 
                l_lngOrderUnitId,
                l_equityTradedProduct,
                l_taxTypeEnum, 
                l_strRepaymentType, 
                l_dblRepaymentNum, 
                l_dblQuantity,
                l_contractType);
     }
     
     public void validateOrderCondition(WEB3GentradeSubAccount l_subAccount, long l_lngProductId,
            WEB3EquityTradedProduct l_tradedProduct, Date l_datOrderBizDate, Date l_datExpirationDate,
            String l_strOrderConditionType, EqTypeExecutionConditionType l_executionCondition,
            boolean l_isCarriedOrder, String l_strMarginTradeType, String l_strPriceConditionType,
            String l_strMarketCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderCondition", new Class[]
                { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                        String.class, EqTypeExecutionConditionType.class, boolean.class, String.class, String.class,
                        String.class }, new Object[]
                { l_subAccount, new Long(l_lngProductId), l_tradedProduct, l_datOrderBizDate, l_datExpirationDate,
                        l_strOrderConditionType, l_executionCondition, new Boolean(l_isCarriedOrder),
                        l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateOrderCondition", new Class[]
                { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                        String.class, EqTypeExecutionConditionType.class, boolean.class, String.class, String.class,
                        String.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidationsForMock.validateOrderCondition()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class }).asVoid();
            return;
        }
        super.validateOrderCondition(l_subAccount, l_lngProductId, l_tradedProduct, l_datOrderBizDate,
                l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
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
