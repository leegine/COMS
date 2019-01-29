head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTradingModuleForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityTradingModuleForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/16  金傑　@(中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketAdapterImplForMock;

public class WEB3EquityTradingModuleForMock extends WEB3EquityTradingModule
{
    public WEB3EquityTradingModuleForMock()
    {
        super();
        super.m_marketAdapter = new EqTypeMarketAdapterImplForMock();
    	super.m_finTranManager = new WEB3EquityFinTransactionManagerForMock();
    }
}
@
