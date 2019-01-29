head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOrderManagerUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccOrderManagerForTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/26 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.util;

import webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock;

public class WEB3ToSuccOrderManagerUtility
{
    public static void changeOrderManager()
    {
        WEB3TriggerOrderTradingModel.orderManager = new WEB3ToSuccOrderManagerImplForMock();
    }
}
@
