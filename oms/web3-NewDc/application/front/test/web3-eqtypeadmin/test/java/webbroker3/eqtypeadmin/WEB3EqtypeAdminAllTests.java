head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EqtypeAdminAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3TPTradingPowerServiceImplTest1.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/20 �Ӑ� (���u) �V�K�쐬
*/
package webbroker3.eqtypeadmin;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.util.DeleteAllTable;

import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoNotifyHandlerTest;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderDLHandlerTest;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSCancelExecHandlerTest;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSInputExecHandlerTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefSortKeyTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityLapseTargetOrderConditionTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequestTest_20070913;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnitTest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequestTest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontSortKeyTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleReferenceServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontOrderCommonServiceImplTest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest;



/**
 * XXXXXX�N���X//TODO
 *
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */
public class WEB3EqtypeAdminAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite((new Date()).toLocaleString() +": Test for webbroker3.eqtypeadmin");

        suite.addTestSuite(DeleteAllTable.class);
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3EqtypeAdminAppPluginTest.class);

        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest.class);
        
        // �������ω������X�V�C���^�Z�v�^
        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest.class);//error
        
        // �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X�����N���X
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderApproveMainServiceImplTest.class);
        
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminForcedSettleApproveConfirmRequestTest.class);
        
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminForcedSettleApproveRunRequestTest.class);
        
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminForcedSettleApproveStatusRequestTest.class);
        
        //�Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminForcedSettleRefInputRequestTest.class);
        
        //�������ω������쐬���N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateRequestTest.class);
        
        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�N���X
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderApproveMainRequestTest.class);
        
        suite.addTestSuite(WEB3AdminForcedSettleReferenceRequestTest_20070913.class);
        
        suite.addTestSuite(WEB3AdminFrontNoticeHistoryReferenceRequestTest.class);
        
        suite.addTestSuite(WEB3AdminFrontSortKeyTest.class);
        
        //�������σ\�[�g�L�[�N���X
        suite.addTestSuite(WEB3AdminForcedSettleSortKeyUnitTest.class);
        
        suite.addTestSuite(WEB3AdminFrontNoticeHistoryServiceImplTest_xiexuan.class);
        
        suite.addTestSuite(WEB3AdminPMEquityDataManagerTest_20070608.class);
        
        suite.addTestSuite(WEB3AdminPMEquityDataManagerTest_xhw2007.class);
        
        suite.addTestSuite(WEB3AdminPMEquityDataManagerTest.class);
        
        suite.addTestSuite(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImplTest.class);
        
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplTest.class);

        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608.class);
        
        suite.addTestSuite(WEB3AdminFrontOrderCommonServiceImplTest.class);
        
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderApproveServiceImplTest.class);//
        suite.addTestSuite(WEB3AdminEquityForcedSettleReferenceServiceImplTest.class);//
        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest.class);//
        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608.class);//
        suite.addTestSuite(WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest.class);//
        
        //���������Ώے��������̃e�X�g
        suite.addTestSuite(WEB3AdminEquityLapseTargetOrderConditionTest.class);

        //�Ǘ��ҁE�����蓮�����T�[�r�XImpl�̃e�X�g
        suite.addTestSuite(WEB3AdminEquityManualExpireServiceImplTest.class);
//        
        suite.addTestSuite(WEB3AdminEquityPTSCancelExecHandlerTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSCancelExecInputRequestTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSCancelExecServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSCancelExecServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSInputExecHandlerTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSInputExecInputRequestTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSInputCancelExecCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSInputExecServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminEquityPTSInputExecServiceImplTest.class);

        //��������2
        suite.addTestSuite(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderDLHandlerTest.class);
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderDLServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderDLCsvTest.class);
        suite.addTestSuite(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptorTest.class);
        suite.addTestSuite(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptorTest.class);

        suite.addTestSuite(WEB3AdminEquityProductCondSettingServiceImplTest.class);

        //���؎�����Ή��i�񎟔����j
        suite.addTestSuite(WEB3AdminEquityAttentionInfoNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEquityAttentionInfoNotifyHandlerTest.class);
        suite.addTestSuite(WEB3AdminEquityAttentionInfoReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AdminEqAttentionInfoRefRefRequestTest.class);
        suite.addTestSuite(WEB3AdminEqAttentionInfoRefSortKeyTest.class);

        //$JUnit-END$
        return suite;
    }

}
@
