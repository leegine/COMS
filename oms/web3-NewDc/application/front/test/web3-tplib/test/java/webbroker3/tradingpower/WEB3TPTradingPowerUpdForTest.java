head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerUpdForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;

public class WEB3TPTradingPowerUpdForTest extends WEB3TPTradingPowerUpd {

    public WEB3TPTradingPowerUpdForTest(
        WEB3TPCalcCondition calcCondition, 
        WEB3TPAccountInfo accountInfo,
        WEB3TPCashValuation l_WEB3TPCashValuation, 
        WEB3TPSettlement l_WEB3TPSettlement,
        WEB3TPContractInfo l_WEB3TPContractInfo,
        WEB3TPSecurityValuation securityValuation)
    {
        this.calcCondition = calcCondition;
        this.accountInfo = accountInfo;
        this.cashValuation = l_WEB3TPCashValuation;
        this.settlement = l_WEB3TPSettlement;
        this.contractInfo = l_WEB3TPContractInfo;
        this.securityValuation = securityValuation;
    }

}
@
