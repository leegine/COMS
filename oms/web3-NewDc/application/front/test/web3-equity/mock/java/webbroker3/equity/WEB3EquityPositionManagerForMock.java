head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPositionManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityPositionManagerForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/16 金傑 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPositionManagerForMock extends WEB3EquityPositionManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManagerForMock.class);
    
    public Contract getContract(long l_lngContractId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityPositionManager",
                "getContract",
                new Class[]{long.class},
                new Object[]{new Long(l_lngContractId)});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityPositionManager",
                "getContract",
                new Class[]{long.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityPositionManagerForMock.getContract(long)");
            
            return (Contract)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPositionManager",
                    "getContract",
                    new Class[]{long.class}).asObject();
        }
        return super.getContract(l_lngContractId);
    }
    
    public EqTypeAsset getAsset(long l_lngAccountId, long l_lngSubAccountId, long l_lngProductId, TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityPositionManager", "getAsset",
                new Class[]
                { long.class, long.class, long.class, TaxTypeEnum.class }, new Object[]
                { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_lngProductId), l_taxType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPositionManager", "getAsset",
                new Class[]
                { long.class, long.class, long.class, TaxTypeEnum.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityPositionManagerForMock.getAsset()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityPositionManager",
                    "getAsset", new Class[]
                    { long.class, long.class, long.class, TaxTypeEnum.class }).asWEB3BaseException();
            return (EqTypeAsset) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPositionManager", "getAsset", new Class[]
                    { long.class, long.class, long.class, TaxTypeEnum.class }).asObject();
        }
        return super.getAsset(l_lngAccountId, l_lngSubAccountId, l_lngProductId, l_taxType);
    }

    public WEB3MarginCloseMarginContractUnit[] createCloseMarginContracts(
            WEB3GentradeSubAccount l_subAccount,
            boolean isLong,
            long l_lngMarketId,
            long l_lngProductId,
            TaxTypeEnum l_taxType,
            String l_strRepaymentType,
            double l_dblRepaymentNum)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityPositionManager", "createCloseMarginContracts",
            new Class[]
            {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class},
            new Object[]
            {l_subAccount, new Boolean(isLong), new Long(l_lngMarketId), new Long(l_lngProductId), l_taxType, l_strRepaymentType, new Double(l_dblRepaymentNum)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityPositionManager", "createCloseMarginContracts",
                new Class[]
                {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityPositionManagerForMock.createCloseMarginContracts()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityPositionManager",
                    "createCloseMarginContracts", new Class[]
                    {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class}).asWEB3BaseException();
            return (WEB3MarginCloseMarginContractUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPositionManager", "createCloseMarginContracts", new Class[]
                    {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class}).asObject();
        }
        return super.createCloseMarginContracts(l_subAccount, isLong, l_lngMarketId, l_lngProductId, l_taxType, l_strRepaymentType, l_dblRepaymentNum);
    }
}
@
