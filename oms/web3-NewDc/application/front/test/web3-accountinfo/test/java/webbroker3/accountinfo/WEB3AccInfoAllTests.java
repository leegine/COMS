head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AccInfoAllTests.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 齊珂 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenListHandlerTest;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviChangeHandlerTest;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviListHandlerTest;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignRegistAccListHandlerTest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequestTest;
import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfoTest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteRequestTest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListRequestTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoAccountBaseInfoCreatedServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoAccountBaseInfoCreatedServiceImplTest_createAccInfoCampaignInfo;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoEquityCommissionCourseRegistServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMailAddressChangeServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenListServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviListServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeChangeServiceImplTest;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest;

public class WEB3AccInfoAllTests
{    
    public WEB3AccInfoAllTests()
    {
        super();
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.accountinfo");

        suite.addTestSuite(WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignCommonTest_cyp.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignCommonTest_isChangeInfo.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignCommonTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignSearchConditionTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenChangeHandlerTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenListHandlerTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviChangeHandlerTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviListHandlerTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignRegistAccListHandlerTest.class);
        suite.addTestSuite(WEB3AccInfoMultiMailAddressInfoTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenInputRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenListRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviInputRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviListRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignRegistAccListRequestTest.class);
        suite.addTestSuite(WEB3AccInfoAccountBaseInfoCreatedServiceImplTest_createAccInfoCampaignInfo.class);
        suite.addTestSuite(WEB3AccInfoAccountBaseInfoCreatedServiceImplTest.class);
        suite.addTestSuite(WEB3AccInfoEquityCommissionCourseRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AccInfoMailAddressChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignAccOpenListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignIndiviListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCampaignRegistAccListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoMailAddressChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoMobileOfficeChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest.class);
        suite.addTestSuite(WEB3AccInfoExecMailDistributionChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCommissionChangeAccountDownloadRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoInsiderInfoChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoInsiderInfoListRequestTest.class);
        suite.addTestSuite(WEB3AccInfoPasswordChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCommissionChangeAccountInquiryRequestTest.class);
        suite.addTestSuite(WEB3AccInfoCommissionCourseMasterTest.class);
        suite.addTestSuite(WEB3AccInfoEquityCommissionCourseChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AccInfoEquityCommissionCourseChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequestTest.class);
        suite.addTestSuite(WEB3AccInfoAccopenConditionRegAcceptVoucherTest.class);
        //$JUnit-END$
        return suite;
    }
}
@
