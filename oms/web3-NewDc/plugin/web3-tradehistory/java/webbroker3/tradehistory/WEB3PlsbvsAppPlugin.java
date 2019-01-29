head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PlsbvsAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Plsbvs�v���O�C��(WEB3PlsbvsAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 �Ɍ��t (���u) �V�K�쐬
                 : 2006/11/01 ����(���u) ���f��056
*/

package webbroker3.tradehistory;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.tradehistory.data.WEB3TradeHistorySessionDatabaseExtensions;
import webbroker3.tradehistory.handler.WEB3BVSBookValueSpecsHandler;
import webbroker3.tradehistory.handler.WEB3PLSProfitLossSpecsHandler;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsRequest;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.service.delegate.WEB3BVSBookValueSpecsService;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3BVSBookValueSpecsServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3PLSProfitLossSpecsServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Plsbvs�v���O�C���N���X�B
 *                                                                
 * @@author �Ɍ��t
 * @@version 1.0
 */
public final class WEB3PlsbvsAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PlsbvsAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3PlsbvsAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PlsbvsAppPlugin()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3PlsbvsAppPlugin.class);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String STR_METHOD_NAME = " onPlug()";
        log.entering(STR_METHOD_NAME);
        
        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();
        
        // DatabaseExtensions �̃v���O�C������ ----------------------
		// ���u�� Codegen�̑Ή� start
//		  WEB3TradeHistoryMasterDatabaseExtensions.plug();
        
		WEB3TradeHistorySessionDatabaseExtensions.plug();
		// ���u�� Codegen�̑Ή� end
		
        // Service �̓o�^���� ----------------------
        
        //�뉿�P�����׏Ɖ�
        Services.registerService(WEB3BVSBookValueSpecsService.class, new WEB3BVSBookValueSpecsServiceImpl());

        //���v���׏Ɖ�
        Services.registerService(WEB3PLSProfitLossSpecsService.class, new WEB3PLSProfitLossSpecsServiceImpl());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //�뉿�P�����׏Ɖ�
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new WEB3BVSBookValueSpecsServiceInterceptor());
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���v���׏Ɖ�
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new WEB3PLSProfitLossSpecsServiceInterceptor());
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message �̓o�^���� ----------------------

        //�뉿�P�����׏Ɖ�N�G�X�g
        regClass(WEB3BVSBookValueSpecsRequest.class);
        //�뉿�P�����׏Ɖ�X�|���X
        regClass(WEB3BVSBookValueSpecsResponse.class);

        //���v���׏Ɖ�N�G�X�g
        regClass(WEB3PLSProfitLossSpecsRequest.class);
        //���v���׏Ɖ�X�|���X
        regClass(WEB3PLSProfitLossSpecsResponse.class);
        
        //���v���׃t�@@�C���_�E�����[�h���N�G�X�g
        regClass(WEB3PLSProfitLossDownloadRequest.class);
        //���v���׃t�@@�C���_�E�����[�h���X�|���X
        regClass(WEB3PLSProfitLossDownloadResponse.class);

        //Handler �̓o�^���� ----------------------

        //�뉿�P�����׏Ɖ� �p�n���h���[�̓o�^
        regHandler(WEB3PlsbvsAppPlugin.class, WEB3BVSBookValueSpecsRequest.class, WEB3BVSBookValueSpecsHandler.class, "getBookValueSpecs");

        //���v���׏Ɖ� �p�n���h���[�̓o�^
        regHandler(WEB3PlsbvsAppPlugin.class, WEB3PLSProfitLossSpecsRequest.class, WEB3PLSProfitLossSpecsHandler.class, "getProfitLossSpecs");

        regHandler(WEB3PlsbvsAppPlugin.class, WEB3PLSProfitLossDownloadRequest.class, WEB3PLSProfitLossSpecsHandler.class, "getProfitLossDownload");
        log.exiting(STR_METHOD_NAME);
    }
}@
