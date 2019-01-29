head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityContractForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityContractForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/16 金傑 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityContractForMock extends WEB3EquityContract
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3EquityContractForMock.class);
    
    public WEB3EquityContractForMock(EqtypeContractRow l_row)
    {
        super(l_row);
    }
    
    public double getManagementFee(double l_dblQuantity, 
            long l_lngOrderUnitId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFee",
                new Class[]{double.class,long.class},
                new Object[]{new Double(l_dblQuantity),new Long(l_lngOrderUnitId)});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFee",
                new Class[]{double.class,long.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityContractForMock.getManagementFee(double,long)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityContract",
                    "getManagementFee",
                    new Class[]{double.class,long.class}).asWEB3BaseException();
            
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityContract",
                    "getManagementFee",
                    new Class[]{double.class,long.class}).asDouble();
        }
        
        return super.getManagementFee(l_dblQuantity, l_lngOrderUnitId);
    }
    
    public double getManagementFeeTax(double l_dblQuantity, long l_lngOrderUnitId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFeeTax",
                new Class[]{double.class,long.class},
                new Object[]{new Double(l_dblQuantity),new Long(l_lngOrderUnitId)});
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFeeTax",
                new Class[]{double.class,long.class}))
        {
            log.debug("webbroker3.equity.WEB3EquityContractForMock.getManagementFeeTax(double,long)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityContract",
                    "getManagementFeeTax",
                    new Class[]{double.class,long.class}).asWEB3BaseException();
            
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityContract",
                    "getManagementFeeTax",
                    new Class[]{double.class,long.class}).asDouble();
        }
        return super.getManagementFeeTax(l_dblQuantity,l_lngOrderUnitId);
    }
}
@
