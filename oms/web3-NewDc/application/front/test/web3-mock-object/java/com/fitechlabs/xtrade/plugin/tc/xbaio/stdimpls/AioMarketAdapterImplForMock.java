head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	AioMarketAdapterImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;

public class AioMarketAdapterImplForMock extends AioMarketAdapterImpl
{

     public MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService()
        {
            return new AioMarketResponseReceiverCallbackServiceImplForMock();
        }
}
@
