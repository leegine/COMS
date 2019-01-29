head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3InformAllTests.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/03/15　@唐性峰(中訊) 新規作成
 */

package webbroker3.inform;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.inform.handler.WEB3AdminInformPTSAccOpenStateChangeHandlerTest;
import webbroker3.inform.handler.WEB3AdminInformPTSAccountListHandlerTest;
import webbroker3.inform.handler.WEB3AdminInformProfDistRegistVoucherMakeHandlerTest;
import webbroker3.inform.handler.WEB3AdminInformProfDistSellTransSrcListHandlerTest;
import webbroker3.inform.handler.WEB3AdminInformSwElecDeliApplyHandlerTest;
import webbroker3.inform.handler.WEB3InformAccSwElecDeliApplyHandlerTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequestTest;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfoTest;
import webbroker3.inform.message.WEB3AdminInformCommonRequestTest;
import webbroker3.inform.message.WEB3AdminInformDetailRequestTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequestTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCommonRequestTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequestTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqConditionTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqSortKeyTest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcConditionTest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcSortKeyTest;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfoTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequestTest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherUpdateCommonRequestTest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequestTest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequestTest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequestTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccOpenStateChangeServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccountListServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistSellTransSrcListServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformReferenceServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplTest;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImplTest;

public class WEB3InformAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(WEB3InformTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan.class);

        suite.addTestSuite(WEB3AdminInformDownLoadCSVTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test.class);
        suite.addTestSuite(WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xhw.class);
        suite.addTestSuite(WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistRegistVoucherMakeHandlerTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistTransferInfoTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistSellTransSrcSortKeyTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistSellTransSrcConditionTest.class);
        
        suite.addTestSuite(WEB3InformTest070608Wubo.class);
        
        suite.addTestSuite(WEB3AdminInformTransferApplyPostVoucherTest.class);

        suite.addTestSuite(WEB3AdminInformReferenceServiceImplTest.class);

        suite.addTestSuite(WEB3AdminInformCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminInformDetailRequestTest.class);

        suite.addTestSuite(WEB3AdminInformProfDistSellTransSrcListHandlerTest.class);

        suite.addTestSuite(WEB3AdminInformProfDistSellTransSrcListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistSellTransSrcListServiceInterceptorTest.class);

        suite.addTestSuite(WEB3AdminInformTransferApplyFinancialInstitutionVoucher_wubo070607Test.class);
        suite.addTestSuite(WEB3InformItemMasterTest_070607.class);

        suite.addTestSuite(WEB3AdminInformProfDistVoucherMakeInpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherMakeCnfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherMakeCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherInfoRefRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherChgCnfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherChgCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherCancCnfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherCancCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformProfDistVoucherUpdateCommonRequestTest.class);

        suite.addTestSuite(WEB3InformAccSwElecDeliApplyCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwitchElecDeliApplyInfoTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliApplyInpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliApplyConfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliApplyCmpRequestTest.class);

        // WEB3InformAccSwElecDeliApplyServiceImplTest
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyServiceImplTest.class);
        // WEB3InformAccSwElecDeliApplyCommonServiceImplTest
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyCommonServiceImplTest.class);
        
        // WEB3AdminInformSwElecDeliApplyServiceImplTest
        suite.addTestSuite(WEB3AdminInformSwElecDeliApplyServiceImplTest.class);
        
        // WEB3InformAccSwElecDeliApplyHandlerTest
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyHandlerTest.class);
        
        // WEB3InformAccSwElecDeliApplyServiceInterceptorTest
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyServiceInterceptorTest.class);
        
        // WEB3AdminInformSwElecDeliApplyHandlerTest
        suite.addTestSuite(WEB3AdminInformSwElecDeliApplyHandlerTest.class);
        
        // WEB3AdminInformSwElecDeliApplyServiceInterceptorTest
        suite.addTestSuite(WEB3AdminInformSwElecDeliApplyServiceInterceptorTest.class);
        
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliDeleteConfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliDeleteCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformAccSwElecDeliApplyRefRequestTest.class);
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyInpRequestTest.class);
        suite.addTestSuite(WEB3InformAccSwElecDeliApplyConfRequestTest.class);
        // WEB3InformConditionRegVoucherTest
        suite.addTestSuite(WEB3InformConditionRegVoucherTest.class);
        
        suite.addTestSuite(WEB3AdminInformPTSAccountListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccountListHandlerTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccountListInqConditionTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccountListInqSortKeyTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccountListResultRequestTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccountListServiceImplTest.class);
        
        suite.addTestSuite(WEB3InformPTSAccOpenApplyServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeHandlerTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeCnfRequestTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminInformPTSAccOpenStateChangeInpRequestTest.class);

        return suite;
    }
}@
