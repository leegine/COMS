head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TpInfoMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPShortfallGenerationHandler.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 �����i���u�j�V�K�쐬
*/

package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImplForMock;

public class WEB3TpInfoMockAppPlugin extends Plugin
{
    public WEB3TpInfoMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void plug()
    throws Exception
    {
        plug(WEB3TpInfoMockAppPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        //WEB3TPAssetTradingPowerServiceImplForMock
        Services.overrideService(WEB3TPAssetTradingPowerService.class,
            new WEB3TPAssetTradingPowerServiceImplForMock());
    }
}@
