head	1.3;
access;
symbols;
locks; strict;
comment	@// @;


1.3
date	2011.03.28.08.19.27;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3dc4d90448e3755;
filename	WEB3GentradeAppPlugin.java;

1.2
date	2011.03.24.08.50.14;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeAppPlugin.java;

1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeAppPlugin.java;


desc
@@


1.3
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : Webbroker3-Gentrade �v���O�C���N���X(WEB3GentradeAppPlugin.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/02/19 �����@@�ǘa(SRA) �V�K�쐬
Revesion History : 2006/02/25 ������ (���u) �O���V�X�e��SOAP�ڑ��T�[�r�X��ǉ�
Revesion History : 2008/02/06 ���{�iSRA�j�����QU03065
Revesion History : 2008/02/19 �юu�� (���u) �����L�������擾�T�[�r�X��ǉ�
Revesion History : 2010/11/10 �����F (���u) �d�q���{���\����ǉ�
*/
package webbroker3.gentrade;

import webbroker3.gentrade.data.WEB3GenTradeAccountDatabaseExtensions;
import webbroker3.gentrade.data.WEB3GenTradeMasterDatabaseExtensions;
import webbroker3.gentrade.data.WEB3GenTradeSessionDatabaseExtensions;
import webbroker3.gentrade.handler.WEB3DocumentDeliverHistoryRegistHandler;
import webbroker3.gentrade.handler.WEB3ExpirationDateListHandler;
import webbroker3.gentrade.handler.WEB3GentradeBatoClientHandler;
import webbroker3.gentrade.handler.WEB3GentradePasswordConvHandler;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.message.WEB3DocumentDeliverRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverResponse;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeMenuDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayResponse;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayRequest;
import webbroker3.gentrade.message.WEB3GentradeReadDisplayResponse;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.gentrade.service.delegate.WEB3GentradePasswordConvService;
import webbroker3.gentrade.service.delegate.WEB3GentradeSystemSoapConnectService;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImpl;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImpl;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradePasswordConvServiceImpl;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeSystemSoapConnectServiceImpl;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;

/**
 * Webbroker3-Gentrade �v���O�C���N���X�B<BR>
 *<BR>
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public final class WEB3GentradeAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3GentradeAppPlugin.class);
    
    /**
     * �R���X�g���N�^�B <BR>
     */
    public WEB3GentradeAppPlugin ()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B <BR>
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3GentradeAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B<BR> 
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        // DatabaseExtensions �̃v���O�C������ ----------------------
        // WEB3GenTradeMasterDatabaseExtensions ���v���O�C��
        WEB3GenTradeMasterDatabaseExtensions.plug();
        // WEB3GenTradeAccountDatabaseExtensions ���v���O�C��
        WEB3GenTradeAccountDatabaseExtensions.plug();
        // WEB3GenTradeSessionDatabaseExtensions ���v���O�C��
        WEB3GenTradeSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        // �g���}�l�[�W���̐؂�ւ����� --------------------
        // �g���A�J�E���g�E�}�l�[�W���[
        l_finApp.overrideAccountManager(
            new WEB3GentradeAccountManager()
            );
        // �g�����Z�I�u�W�F�N�g�E�}�l�[�W���[
        l_finApp.overrideFinObjectManager(
            new WEB3GentradeFinObjectManager()
            );

        //�������ʔԍ����̔Ԃ���T�[�r�X
        Services.registerService(
            WEB3HostReqOrderNumberManageService.class,
            new WEB3HostReqOrderNumberManageServiceImpl());
        
        //�h�L�������g�V�X�e���ڑ��T�[�r�X
        // �d�q���ڑ��T�[�r�X
        // �i�T�[�r�X�C���b�Z�[�W�C�n���h���j
        Services.registerService(WEB3GentradeBatoClientService.class, 
            new WEB3GentradeBatoClientServiceImpl());

        //�O���V�X�e��SOAP�ڑ��T�[�r�X
        Services.registerService(WEB3GentradeSystemSoapConnectService.class, 
            new WEB3GentradeSystemSoapConnectServiceImpl());

        regClass(WEB3GentradeMenuDisplayRequest.class);
        regClass(WEB3GentradeMenuDisplayResponse.class);
        regClass(WEB3GentradeProspectusDisplayRequest.class);
        regClass(WEB3GentradeProspectusDisplayResponse.class);
        regClass(WEB3DocumentDeliverRequest.class);
        regClass(WEB3DocumentDeliverResponse.class);
        regClass(WEB3GentradeReadDisplayRequest.class);
        regClass(WEB3GentradeReadDisplayResponse.class);

        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3GentradeMenuDisplayRequest.class,
            WEB3GentradeBatoClientHandler.class,
            "displayMenu");
        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3GentradeProspectusDisplayRequest.class,
            WEB3GentradeBatoClientHandler.class,
            "displayProspectus");
        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3DocumentDeliverRequest.class,
            WEB3GentradeBatoClientHandler.class,
            "documentDeliver");
        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3GentradeReadDisplayRequest.class,
            WEB3GentradeBatoClientHandler.class,
            "readDisplay");
        
		// �Ïؔԍ��ϊ��T�[�r�X
		// �i�T�[�r�X�C���b�Z�[�W�C�n���h���j
		Services.registerService(WEB3GentradePasswordConvService.class, 
			new WEB3GentradePasswordConvServiceImpl());

		regClass(WEB3GentradePasswordConvMainAccountRequest.class);
		regClass(WEB3GentradePasswordConvMainAccountResponse.class);
		regClass(WEB3GentradePasswordConvAccOpenRequest.class);
		regClass(WEB3GentradePasswordConvAccOpenResponse.class);
		regClass(WEB3GentradePasswordConvWeb2TransferRequest.class);
		regClass(WEB3GentradePasswordConvWeb2TransferResponse.class);
        regClass(WEB3GentradePasswordConvExpAccOpenRequest.class);
        regClass(WEB3GentradePasswordConvExpAccOpenResponse.class);
        regClass(WEB3GentradePasswordConvSonarTraderRequest.class);
        regClass(WEB3GentradePasswordConvSonarTraderResponse.class);

		regHandler(
			WEB3GentradeAppPlugin.class,
			WEB3GentradePasswordConvMainAccountRequest.class,
			WEB3GentradePasswordConvHandler.class,
			"convMainAccountBuffer");
		regHandler(
			WEB3GentradeAppPlugin.class,
			WEB3GentradePasswordConvAccOpenRequest.class,
			WEB3GentradePasswordConvHandler.class,
			"convAccOpenBuffer");
		regHandler(
			WEB3GentradeAppPlugin.class,
			WEB3GentradePasswordConvWeb2TransferRequest.class,
			WEB3GentradePasswordConvHandler.class,
			"transfer");
        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3GentradePasswordConvExpAccOpenRequest.class,
            WEB3GentradePasswordConvHandler.class,
            "convExpAccOpenBuffer");
		regHandler(
			WEB3GentradeAppPlugin.class,
			WEB3GentradePasswordConvSonarTraderRequest.class,
			WEB3GentradePasswordConvHandler.class,
			"convSonarTrader");
        
        //SPAN�ڑ��T�[�r�X                  
       Services.registerService(                    
           WEB3GentradeSpanConnectService.class,                 
           new WEB3GentradeSpanConnectServiceImpl());

        // WEB3GentradeTradingCalendarModelImpl����
        // �g������J�����_�ڍׂɐ؂�ւ���
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
        l_finObjectManager.overrideTradingCalendarModel(
            new WEB3GentradeTradingCalendarModelImpl()
            );
        
        //override �v�Z�T�[�r�X
        l_finApp.overrideGlobalBizLogicProvider(new WEB3GentradeBizLogicProvider());
            
        l_finApp.overrideCommonOrderValidator(
            new WEB3GentradeOrderValidator());

        //���ʌ�t����o�^�T�[�r�X
        Services.registerService(WEB3DocumentDeliverHistoryRegistService.class,
            new WEB3DocumentDeliverHistoryRegistServiceImpl());
        
        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        Services.addInterceptor(WEB3DocumentDeliverHistoryRegistService.class,
        		new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        regClass(WEB3DocumentDeliverHistoryRegistRequest.class);
        regClass(WEB3DocumentDeliverHistoryRegistResponse.class);

        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3DocumentDeliverHistoryRegistRequest.class,
            WEB3DocumentDeliverHistoryRegistHandler.class,
            "documentDeliverHistoryRegist");

        //�����L�������擾�T�[�r�X
        Services.registerService(WEB3ExpirationDateListService.class,
            new WEB3ExpirationDateListServiceImpl());

        Services.addInterceptor(WEB3ExpirationDateListService.class,
            new WEB3ExpirationDateListServiceInterceptor());

        regClass(WEB3ExpirationDateListRequest.class);
        regClass(WEB3ExpirationDateListResponse.class);

        regHandler(
            WEB3GentradeAppPlugin.class,
            WEB3ExpirationDateListRequest.class,
            WEB3ExpirationDateListHandler.class,
            "expirationDateListRequest");

        log.exiting(METHOD_NAME);
    }
}
@


1.2
log
@*** empty log message ***
@
text
@a9 1
Revesion History : 2010/11/11 �����C (���u) �����@@���ʏ��擾�T�[�r�X��ǉ�
a17 1
import webbroker3.gentrade.handler.WEB3FPTDocumentGetHandler;
a25 2
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
a43 1
import webbroker3.gentrade.service.delegate.WEB3FPTDocumentGetService;
a48 1
import webbroker3.gentrade.service.delegate.stdimpls.WEB3FPTDocumentGetServiceImpl;
a261 16

        //�����@@���ʏ��擾�T�[�r�X
        Services.registerService(WEB3FPTDocumentGetService.class,
            new WEB3FPTDocumentGetServiceImpl());

        Services.addInterceptor(WEB3FPTDocumentGetService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        regClass(WEB3FPTDocumentGetRequest.class);
        regClass(WEB3FPTDocumentGetResponse.class);

        regHandler(
                WEB3GentradeAppPlugin.class,
                WEB3FPTDocumentGetRequest.class,
                WEB3FPTDocumentGetHandler.class,
                "fptDocumentGet");
@


1.1
log
@*** empty log message ***
@
text
@d2 1
a2 1
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
d4 1
a4 1
Author Name      : Daiwa Institute of Research
d9 2
d19 1
d28 2
d44 2
d48 1
d54 1
d151 2
d169 5
d268 17
@

