head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	IfoMarketAdapterImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IfoMarketAdapterImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 金傑 (中訊) 新規作成
*/
package com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;

/**
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class IfoMarketAdapterImplForMock extends IfoMarketAdapterImpl
{
	 public MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService()
	{
		return new IfoMarketResponseReceiverCallbackServiceImplForMock();
	}
}
@
