head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPMockPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3TradeManagementMockAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 ���z(���u) �V�K�쐬
*/
package webbroker3.tradingpoweradmin;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock;

public class WEB3AdminTPMockPlugin extends Plugin
{

    public WEB3AdminTPMockPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void plug()
    throws Exception
    {
        plug(WEB3AdminTPMockPlugin.class);
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
    public static void onPlug()
        throws Exception
    {
        // WEB3AdminTMLoginRejectIPManagementServiceImplForMock
        Services.overrideService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
            new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock());

    }
}
@
