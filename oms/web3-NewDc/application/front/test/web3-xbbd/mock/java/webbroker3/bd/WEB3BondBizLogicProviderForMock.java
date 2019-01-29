head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3BondBizLogicProviderForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/11 金傑 (中訊) 新規作成
*/
package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券計算サービスクラスForMock
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3BondBizLogicProviderForMock extends WEB3BondBizLogicProvider
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3BondBizLogicProviderForMock.class);
    
    public WEB3BondEstimatedPriceCalcResult calcEstimatedPrice(
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
            BigDecimal l_bdQuantity, 
            BigDecimal l_bdBondOrderUnit, 
            BigDecimal l_bdFxRate,
            WEB3BondProduct l_bondProduct, 
            WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException

    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.WEB3BondBizLogicProvider",
                "calcEstimatedPrice",
                new Class[]{WEB3BondOrderTypeJudge.class,
                            BigDecimal.class,
                            BigDecimal.class,
                            BigDecimal.class,
                            WEB3BondProduct.class,
                            WEB3BondExecuteDateInfo.class},
                new Object[]{l_bondOrderTypeJudge,
                             l_bdQuantity,
                             l_bdBondOrderUnit,
                             l_bdFxRate,
                             l_bondProduct,
                             l_bondExecuteDateInfo});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.bd.WEB3BondBizLogicProvider",
                "calcEstimatedPrice",
                new Class[]{WEB3BondOrderTypeJudge.class,
                            BigDecimal.class,
                            BigDecimal.class,
                            BigDecimal.class,
                            WEB3BondProduct.class,
                            WEB3BondExecuteDateInfo.class}))
        {
            log.debug("webbroker3.bd.WEB3BondBizLogicProviderForMock.calcEstimatedPrice");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondBizLogicProvider",
                    "calcEstimatedPrice",
                    new Class[]{WEB3BondOrderTypeJudge.class,
                                BigDecimal.class,
                                BigDecimal.class,
                                BigDecimal.class,
                                WEB3BondProduct.class,
                                WEB3BondExecuteDateInfo.class}).asWEB3BaseException();
            
            return (WEB3BondEstimatedPriceCalcResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondBizLogicProvider",
                    "calcEstimatedPrice",
                    new Class[]{WEB3BondOrderTypeJudge.class,
                                BigDecimal.class,
                                BigDecimal.class,
                                BigDecimal.class,
                                WEB3BondProduct.class,
                                WEB3BondExecuteDateInfo.class}).asObject();
            
        }
        
        return super.calcEstimatedPrice(l_bondOrderTypeJudge, l_bdQuantity, l_bdBondOrderUnit, l_bdFxRate,
                l_bondProduct, l_bondExecuteDateInfo);
    }
}
@
