head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DirsecAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3DirsecAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/29 孟亜南 (中訊) 新規作成
*/

package webbroker3.dirsec;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.dirsec.handler.WEB3AdminDirSecAPMngForcedStartHandlerTest;
import webbroker3.dirsec.handler.WEB3AdminDirSecAccRegVoucherStatUpdHandlerTest;
import webbroker3.dirsec.handler.WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest;
import webbroker3.dirsec.handler.WEB3AdminDirSecTriggerIssueHandlerTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCommonRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartSortKeyTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueSortKeyTest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequestTest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequestTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTestcsh;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecTriggerIssueServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminFrontOrderRouteChangeServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminFrontOrderRouteChangeServiceImplTestLmz;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImplTest;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImplTest1;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImplTest_xhw;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan;

/**
 * XXXXXXクラス
 *
 * @@author 孟亜南(中訊)
 * @@version 1.0
 */  
public class WEB3DirsecAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.dirsec");
        //$JUnit-BEGIN$
            
        suite.addTestSuite(WEB3FrontOrderSwitchManagementTest.class);                                                     
        suite.addTestSuite(WEB3AdminDirSecAccRegVoucherStatUpdHandlerTest.class);                                         
        suite.addTestSuite(WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest.class);                                        
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueHandlerTest.class);                                                 
        suite.addTestSuite(WEB3AdminDirSecAccRegVoucherSearchResRequestTest.class);                                       
        suite.addTestSuite(WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.class);                                     
        suite.addTestSuite(WEB3AdminDirSecAioOrderUnitTableSearchResultRequestTest.class);                                
        suite.addTestSuite(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest.class);                              
        suite.addTestSuite(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest.class);                               
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueCompleteRequestTest.class);                                         
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueConfirmRequestTest.class);                                          
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueListRequestTest.class);                                             
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueSortKeyTest.class);                                                 
        suite.addTestSuite(WEB3AdminDirSecWorkingCompleteRequestTest.class);                                              
        suite.addTestSuite(WEB3AdminDirSecWorkingConfirmRequestTest.class);                                               
        suite.addTestSuite(WEB3AdminDirSecWorkingListRequestTest.class);                                                  
        suite.addTestSuite(WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.class);                                 
        suite.addTestSuite(WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTestcsh.class);                                  
        suite.addTestSuite(WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest.class);                                  
        suite.addTestSuite(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest.class);                                    
        suite.addTestSuite(WEB3AdminDirSecFrontOrderCommonServiceImplTest.class);                                         
        suite.addTestSuite(WEB3AdminDirSecTriggerIssueServiceImplTest.class);                                             
        suite.addTestSuite(WEB3AdminFrontOrderRouteChangeServiceImplTest.class);                                          
        suite.addTestSuite(WEB3AdminFrontOrderRouteChangeServiceImplTestLmz.class);                                       
        suite.addTestSuite(WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder.class);                                
        suite.addTestSuite(WEB3AdminSwitchOrderRouteServiceImplTest_xhw.class);                                           
        suite.addTestSuite(WEB3AdminSwitchOrderRouteServiceImplTest_xiexuan.class);                                       
        suite.addTestSuite(WEB3AdminSwitchOrderRouteServiceImplTest.class);                                               
        suite.addTestSuite(WEB3AdminSwitchOrderRouteServiceImplTest1.class);                                              
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartCnfRequestTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartSortKeyTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngListRequestTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartHandlerTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminDirSecAPMngForcedStartServiceImplTest.class);
        
        //$JUnit-END$
        return suite;
        
    }
}
@
