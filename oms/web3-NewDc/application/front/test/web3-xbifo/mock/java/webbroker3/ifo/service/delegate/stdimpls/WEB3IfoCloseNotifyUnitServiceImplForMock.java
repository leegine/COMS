head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCloseNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoCloseNotifyUnitServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 金傑 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCloseNotifyUnitServiceImplForMock extends WEB3IfoCloseNotifyUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    		WEB3IfoCloseNotifyUnitServiceImplForMock.class);
    
	public String notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                "notifyClose",
                new Class[] 
                          {OrderUnit.class,
                		double.class,
                		String.class,
                		String.class
                		},
                new Object[]{l_orderUnit,new Double(l_dblExecutionQuantity),l_strCloseReasonCode,l_strCloseNotifyType});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                "notifyClose",
                new Class[] 
                          {OrderUnit.class,
                		double.class,
                		String.class,
                		String.class
                		}))
        {
        	log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImplFoMock.notifyClose(OrderUnit,double,String,String)");
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                              {OrderUnit.class,
                    		double.class,
                    		String.class,
                    		String.class
                    		}).asWEB3BaseException();
        	
        	return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                              {OrderUnit.class,
                    		double.class,
                    		String.class,
                    		String.class
                    		}).asObject();
        }
        
    	return super.notifyClose(l_orderUnit,l_dblExecutionQuantity,l_strCloseReasonCode,l_strCloseNotifyType);
    }
}
@
