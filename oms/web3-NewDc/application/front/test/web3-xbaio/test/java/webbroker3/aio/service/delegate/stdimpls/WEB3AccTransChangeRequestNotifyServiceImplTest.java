head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AccTransChangeRequestNotifyServiceImplTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/01 車進 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;


import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

public class WEB3AccTransChangeRequestNotifyServiceImplTest extends TestBaseForMock {
 
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestNotifyServiceImplTest.class);
    
    public WEB3AccTransChangeRequestNotifyServiceImplTest(String arg0)
    {
        super(arg0);
    }
    
    WEB3AccTransChangeRequestNotifyServiceImpl l_impl = null;
    WEB3AccTransChangeRequestNotifyServiceImpl.WEB3AccTransChangeRequestNotifyTransactionCallback l_callBack = null;
    
    String l_strParam1 = "";
    String l_strParam2 = "";
    
    protected void setUp() throws Exception
    {
        l_impl = new WEB3AccTransChangeRequestNotifyServiceImpl();
        l_callBack = l_impl.new WEB3AccTransChangeRequestNotifyTransactionCallback();
    }

    protected void tearDown() throws Exception
    {
        l_impl = null;
        l_callBack = null;
    }
    
    /*
     * 引数.預かり区分 = 01（預り金） and  引数.摘要コード = 01（信用保証金） 
     * 1006を返却する。 
     */    
    public void testGetOrderTypeCase001()
    {
        String l_strMethodName = "testGetOrderTypeCase001";
        log.entering(l_strMethodName);
        
        l_strParam1 = "01";
        l_strParam2 = "01";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1006,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 = 01（預り金） and 引数.摘要コード = 72（株先証拠金） 
     * 1008を返却する。 
     */
    public void testGetOrderTypeCase002()
    {
        String l_strMethodName = "testGetOrderTypeCase002";
        log.entering(l_strMethodName);
        
        l_strParam1 = "01";
        l_strParam2 = "72";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1008,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 = 01（預り金） and 引数.摘要コード = 86（為替保証金） 
     * 1012を返却する。 
     */
    public void testGetOrderTypeCase003()
    {
        String l_strMethodName = "testGetOrderTypeCase003";
        log.entering(l_strMethodName);
        
        l_strParam1 = "01";
        l_strParam2 = "86";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1012,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    
    /*
     * 引数.預かり区分 = 04（保証金）
     * 1005を返却する。
     */
    public void testGetOrderTypeCase004()
    {
        String l_strMethodName = "testGetOrderTypeCase004";
        log.entering(l_strMethodName);
        
        l_strParam1 = "04";
        l_strParam2 = "";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1005,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 = 05（株先証拠金） 
     * 1007を返却する。
     */
    public void testGetOrderTypeCase005()
    {
        String l_strMethodName = "testGetOrderTypeCase005";
        log.entering(l_strMethodName);
        
        l_strParam1 = "05";
        l_strParam2 = "";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1007,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 != 01（預り金） and 引数.摘要コード = 86（為替保証金） 
     * 1011を返却する。
     */
    public void testGetOrderTypeCase006()
    {
        String l_strMethodName = "testGetOrderTypeCase006";
        log.entering(l_strMethodName);
        
        l_strParam1 = "07";
        l_strParam2 = "86";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1011,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 = 01（預り金） and 
     * 引数.摘要コード = (*)のいずれか  
     * 1018を返却する。
     */
    public void testGetOrderTypeCase007()
    {
        String l_strMethodName = "testGetOrderTypeCase007";
        log.entering(l_strMethodName);
        
        l_strParam1 = "01";
        l_strParam2 = "97";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1018,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
    /*
     * 引数.預かり区分 != 01（預り金） and 
     * 引数.摘要コード = (*)のいずれか 
     * 1017を返却する。
     */
    public void testGetOrderTypeCase008()
    {
        String l_strMethodName = "testGetOrderTypeCase008";
        log.entering(l_strMethodName);
        
        l_strParam1 = "07";
        l_strParam2 = "97";
        try{
            OrderTypeEnum l_result = l_callBack.getOrderType(l_strParam1, l_strParam2);
            assertEquals(1017,l_result.intValue());  
        }catch(Exception e){
            log.error("Error!");
            log.exiting(l_strMethodName);
            fail();
        }   
    }
    
}
@
