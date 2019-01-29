head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	EqTypeMarketAdapterImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : //TODO(EqTypeMarketAdapterImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 ™GˆÌ (’†u) V‹Kì¬
*/
package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;

/**
 * XXXXXXƒNƒ‰ƒX//TODO
 *
 * @@author ™GˆÌ(’†u)
 * @@version 1.0
 */
public class EqTypeMarketAdapterImplForMock extends EqTypeMarketAdapterImpl
{
         
     private static EqTypeMarketResponseReceiverCallbackService getInterceptorWrappedResponseCallbackService()
     {
         return new EqTypeMarketResponseReceiverCallbackServiceImplForMock();
     }
     
     public MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService()
     {
         return new EqTypeMarketResponseReceiverCallbackServiceImplForMock();
     }
}
@
