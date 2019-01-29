head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3SrvRegiAllTests.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/11 齊珂 (中訊) 新規作成
*/

package webbroker3.srvregi;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdListHandlerTest;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest;
import webbroker3.srvregi.handler.WEB3SrvRegiStreamHandlerTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdCommonRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCommonRequestTest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCommonRequestTest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequestTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataDownloadServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountRegistServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdListServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeInputServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceDetailServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiApplicationInputServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiOtherOrgServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceListInquiryServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceStartServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceUseApplicationServiceImplTest_submitUseAppli;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStartInfoServiceImplTest;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStreamServiceImplTest;

/**
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3SrvRegiAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.srvregi");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3SrvRegiStreamCommonTest.class);
        suite.addTestSuite(WEB3SrvRegiStreamServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataDownloadCsvTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadServiceImplTest_validateDetailsLine.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadCsvTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceInterceptorTest.class);
        suite.addTestSuite(WEB3SrvRegiApplicationRequiredServiceTest.class);
        suite.addTestSuite(WEB3SrvRegiOtherOrgInfoAdminTest.class);
        suite.addTestSuite(WEB3SrvRegiServiceInfoManagementTest.class);
        suite.addTestSuite(WEB3SrvRegiStreamServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdListHandlerTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadHandlerTest.class);
        suite.addTestSuite(WEB3SrvRegiStreamHandlerTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadInputRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiServiceChangeCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiServiceRegistCommonRequestTest.class);
        suite.addTestSuite(WEB3SrvRegiStreamRequestTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataDownloadServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_insertSrvApplyAttribute.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest_updateSrvApplyAttribute.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiAccountRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiServiceChangeInputServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiServiceChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminSrvRegiServiceDetailServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiApplicationInputServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiOtherOrgServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiRegistServiceImplTest_calcAppliEndDate.class);
        suite.addTestSuite(WEB3SrvRegiRegistServiceImplTest_getServiceAttributeLists.class);
        suite.addTestSuite(WEB3SrvRegiServiceListInquiryServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiServiceStartServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist.class);
        suite.addTestSuite(WEB3SrvRegiServiceUseApplicationServiceImplTest_submitUseAppli.class);
        suite.addTestSuite(WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli.class);
        suite.addTestSuite(WEB3SrvRegiStartInfoServiceImplTest.class);
        suite.addTestSuite(WEB3SrvRegiServiceMasterTest.class);
        suite.addTestSuite(WEB3SrvRegiReservedWordChangeTest.class);
        //$JUnit-END$
        return suite;
    }
}
@
