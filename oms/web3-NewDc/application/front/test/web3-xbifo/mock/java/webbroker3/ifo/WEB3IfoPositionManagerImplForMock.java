head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoPositionManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoPositionManagerImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/19 金傑 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoPositionManagerImplForMock extends WEB3IfoPositionManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoPositionManagerImplForMock.class);
    
    public WEB3FuturesOptionsCloseMarginContractUnit[] createSettleContracts(WEB3GentradeSubAccount l_subAccount,
            ContractTypeEnum l_contractTypeEnum, long l_lngMarketId, long l_lngProductId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createSettleContracts", new Class[]
                { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class }, new Object[]
                { l_subAccount, l_contractTypeEnum, new Long(l_lngMarketId), new Long(l_lngProductId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createSettleContracts", new Class[]
                { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createSettleContracts()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createSettleContracts", new Class[]
                    { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class })
                    .asWEB3BaseException();
            return (WEB3FuturesOptionsCloseMarginContractUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "createSettleContracts", new Class[]
                    { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class }).asObject();
        }
        return super.createSettleContracts(l_subAccount, l_contractTypeEnum, l_lngMarketId, l_lngProductId);
    }
    
    public void createOptionUnSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails,
            WEB3IfoContractImpl l_ifocontractImpl) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createOptionUnSettledContractInquiryDetails", new Class[]
                { ArrayList.class, WEB3IfoContractImpl.class }, new Object[]
                { l_lisContractInquiryDetails, l_ifocontractImpl });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createOptionUnSettledContractInquiryDetails", new Class[]
                { ArrayList.class, WEB3IfoContractImpl.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createOptionUnSettledContractInquiryDetails()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createOptionUnSettledContractInquiryDetails", new Class[]
                    { ArrayList.class, WEB3IfoContractImpl.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createOptionUnSettledContractInquiryDetails", new Class[]
                    { ArrayList.class, WEB3IfoContractImpl.class }).asVoid();
            return;
        }
        super.createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifocontractImpl);
    }
    
    public WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeNameFromContract(
            WEB3GentradeSubAccount l_subAccount, boolean l_blnIsUnSettlement, String l_strFuturesOptionDivision)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class }, new Object[]
                { l_subAccount, new Boolean(l_blnIsUnSettlement), l_strFuturesOptionDivision });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createProductCodeNameFromContract()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createProductCodeNameFromContract", new Class[]
                    { WEB3GentradeSubAccount.class, boolean.class, String.class }).asWEB3BaseException();
            return (WEB3FuturesOptionsProductCodeNameUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "createProductCodeNameFromContract", new Class[]
                    { WEB3GentradeSubAccount.class, boolean.class, String.class }).asObject();
        }
        return super.createProductCodeNameFromContract(l_subAccount, l_blnIsUnSettlement, l_strFuturesOptionDivision);
    }

    public WEB3FuturesContractReferenceUnit[] createFuturesContractInquiryDetails(
            WEB3GentradeSubAccount l_subAccount,
            String l_strFuturesOptionDivision,
            String l_strDesSettlementStatus,
            String l_strSearchConditionString,
            String[] l_strSearchConditionDataContainer)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createFuturesContractInquiryDetails", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }, new Object[]
                { l_subAccount, l_strFuturesOptionDivision, l_strDesSettlementStatus, l_strSearchConditionString, l_strSearchConditionDataContainer });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createFuturesContractInquiryDetails", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createFuturesContractInquiryDetails()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createFuturesContractInquiryDetails", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }).asWEB3BaseException();
            return (WEB3FuturesContractReferenceUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "createFuturesContractInquiryDetails", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }).asObject();
        }
        return super.createFuturesContractInquiryDetails(
                l_subAccount,
                l_strFuturesOptionDivision,
                l_strDesSettlementStatus,
                l_strSearchConditionString,
                l_strSearchConditionDataContainer);
    }
    
    public WEB3OptionsContractReferenceUnit[] createOptionsContractReferenceUnits(WEB3GentradeSubAccount l_subAccount,
            String l_strFuturesOptionDivision, String l_strDesSettlementStatus, String l_strSearchCondition,
            String[] l_strSearchConditionDataContainer) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createOptionsContractReferenceUnits", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                new Object[]
                { l_subAccount, l_strFuturesOptionDivision, l_strDesSettlementStatus, l_strSearchCondition,
                        l_strSearchConditionDataContainer });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createOptionsContractReferenceUnits", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createOptionsContractReferenceUnits()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createOptionsContractReferenceUnits", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class })
                    .asWEB3BaseException();
            return (WEB3OptionsContractReferenceUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "createOptionsContractReferenceUnits", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class })
                    .asObject();
        }
        return super.createOptionsContractReferenceUnits(l_subAccount, l_strFuturesOptionDivision,
                l_strDesSettlementStatus, l_strSearchCondition, l_strSearchConditionDataContainer);
    }

    public List getContractListByOrderUnit(long l_lngOrderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit", new Class[]
                { long.class},
                new Object[]
                {new Long(l_lngOrderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit", new Class[]
                { long.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.getContractListByOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "getContractListByOrderUnit", new Class[]
                    { long.class})
                    .asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "getContractListByOrderUnit", new Class[]
                    { long.class})
                    .asObject();
        }
        return super.getContractListByOrderUnit(l_lngOrderId);
    }

    public List getTransactionsListByOrderUnitPlusContract(long l_orderUnitId, long l_lngContractId)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getTransactionsListByOrderUnitPlusContract", new Class[]
                { long.class, long.class},
                new Object[]
                {new Long(l_orderUnitId), new Long(l_lngContractId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getTransactionsListByOrderUnitPlusContract", new Class[]
                { long.class, long.class}))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.getTransactionsListByOrderUnitPlusContract()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "getTransactionsListByOrderUnitPlusContract", new Class[]
                    { long.class, long.class})
                    .asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "getTransactionsListByOrderUnitPlusContract", new Class[]
                    { long.class, long.class})
                    .asObject();
        }
        return super.getTransactionsListByOrderUnitPlusContract(l_orderUnitId, l_lngContractId);
    }
    
    public WEB3FuturesOptionsDetailUnit[] createIfoBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount,
            String l_strFuOpDiv, String l_strSettlementStatus, String l_strQueryString,
            String[] l_strQueryContainers) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                new Object[]
                { l_subAccount, l_strFuOpDiv, l_strSettlementStatus, l_strQueryString, l_strQueryContainers });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit", new Class[]
                { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoPositionManagerImplForMock.createOptionsContractReferenceUnits()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoPositionManagerImpl",
                    "createIfoBalanceReferenceDetailUnit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class })
                    .asWEB3BaseException();
            return (WEB3FuturesOptionsDetailUnit[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", "createIfoBalanceReferenceDetailUnit", new Class[]
                    { WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class })
                    .asObject();
        }
        return super.createIfoBalanceReferenceDetailUnit(l_subAccount, l_strFuOpDiv,
                l_strSettlementStatus, l_strQueryString, l_strQueryContainers);
    }
}
@
