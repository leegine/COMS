head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DocadminAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : (WEB3DocadminAllTests.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/10/10 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.001
*/

package webbroker3.docadmin;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.docadmin.handler.WEB3AdminFPTDeleteHandlerTest;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentListReferenceHandlerTest;
import webbroker3.docadmin.handler.WEB3AdminFPTDocumentUpdateHandlerTest;
import webbroker3.docadmin.handler.WEB3AdminFPTListReferenceHandlerTest;
import webbroker3.docadmin.handler.WEB3AdminFPTRegistHandlerTest;
import webbroker3.docadmin.handler.WEB3AdminFPTUploadHandlerTest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentCommonRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTUpdateCommonRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequestTest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequestTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDeleteServiceImplTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImplTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImplTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTListReferenceServiceImplTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTRegistServiceImplTest;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTUploadServiceImplTest;

public class WEB3DocadminAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.docadmin");

        suite.addTestSuite(WEB3AdminFPTBatoProductCodeManagementTest.class);
        suite.addTestSuite(WEB3AdminFPTCommonTest.class);
        suite.addTestSuite(WEB3AdminFPTDocCategoryManagementTest.class);
        suite.addTestSuite(WEB3AdminFPTDocDeliveryManagementHistoryTest.class);
        suite.addTestSuite(WEB3AdminFPTDocDeliveryManagementTest.class);
        suite.addTestSuite(WEB3AdminFPTDocDivManagementTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadInterceptorTest.class);
        suite.addTestSuite(WEB3FPTUploadCsvTest.class);
        suite.addTestSuite(WEB3AdminFPTDeleteHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentListReferenceHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentUpdateHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTListReferenceHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTRegistHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadHandlerTest.class);
        suite.addTestSuite(WEB3AdminFPTDeleteCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDeleteConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentListReferenceRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentUpdateCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentUpdateConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTListReferenceRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTRegistCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTRegistConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTUpdateCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadCancelRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminFPTDeleteServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentListReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFPTDocumentUpdateServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFPTListReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFPTRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFPTUploadServiceImplTest.class);

        return suite;
    }

}
@
