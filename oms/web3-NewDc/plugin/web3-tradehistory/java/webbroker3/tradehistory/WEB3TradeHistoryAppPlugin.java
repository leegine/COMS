head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Tradehistory �v���O�C��(WEB3TradeHistoryAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/04 �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.tradehistory;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;

import webbroker3.tradehistory.handler.WEB3HistorySettleDetailHandler;
import webbroker3.tradehistory.handler.WEB3HistoryTradeDetailHandler;
import webbroker3.tradehistory.handler.WEB3HistoryTradeHistoryListHandler;

import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;

import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;

import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistorySettleDetailServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistoryTradeDetailServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistoryTradeHistoryListServiceImpl;

import webbroker3.tradehistory.data.WEB3TradeHistorySessionDatabaseExtensions;

import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Tradehistory �v���O�C���N���X�B
 *                                                                
 * @@author �� �� �@@
 * @@version 1.0
 */
public final class WEB3TradeHistoryAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeHistoryAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3TradeHistoryAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3TradeHistoryAppPlugin()";
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

        plug(WEB3TradeHistoryAppPlugin.class);

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
//        WEB3TradeHistoryMasterDatabaseExtensions.plug();
        
		WEB3TradeHistorySessionDatabaseExtensions.plug();
		// ���u�� Codegen�̑Ή� end

        // Service �̓o�^���� ----------------------
        
        //���ϖ���
        Services.registerService(WEB3HistorySettleDetailService.class, new WEB3HistorySettleDetailServiceImpl());

        //�������
        Services.registerService(WEB3HistoryTradeDetailService.class, new WEB3HistoryTradeDetailServiceImpl());
        
        //��������ꗗ
        Services.registerService(WEB3HistoryTradeHistoryListService.class, new WEB3HistoryTradeHistoryListServiceImpl());


        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        //���ϖ���
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new WEB3HistorySettleDetailServiceInterceptor());
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�������
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new WEB3HistoryTradeDetailServiceInterceptor());
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //��������ꗗ
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new WEB3HistoryTradeHistoryListServiceInterceptor());
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message �̓o�^���� ----------------------

        //���ϖ��׃��N�G�X�g
        regClass(WEB3HistorySettleDetailRequest.class);
        //���ϖ��׃��X�|���X
        regClass(WEB3HistorySettleDetailResponse.class);

        //������׃��N�G�X�g
        regClass(WEB3HistoryTradeDetailRequest.class);
        //������׃��X�|���X
        regClass(WEB3HistoryTradeDetailResponse.class);

        //��������ꗗ���N�G�X�g
        regClass(WEB3HistoryTradeHistoryListRequest.class);
        //��������ꗗ���X�|���X
        regClass(WEB3HistoryTradeHistoryListResponse.class);

        //��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g
        regClass(WEB3HistoryTradeHistoryDownloadRequest.class);
        //��������ꗗ�t�@@�C���_�E�����[�h���X�|���X
        regClass(WEB3HistoryTradeHistoryDownloadResponse.class);

        //Handler �̓o�^���� ----------------------

        //���ϖ��� �p�n���h���[�̓o�^
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistorySettleDetailRequest.class, WEB3HistorySettleDetailHandler.class, "getSettleDetailScreen");

        //������� �p�n���h���[�̓o�^
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeHistoryListRequest.class, WEB3HistoryTradeHistoryListHandler.class, "getTradeHistoryListScreen");

        //��������ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeDetailRequest.class, WEB3HistoryTradeDetailHandler.class, "getTradeDetailScreen");

        //��������ꗗ �p�n���h���[�̓o�^
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeHistoryDownloadRequest.class, WEB3HistoryTradeHistoryListHandler.class, "getTradeHistoryListDownload");

        log.exiting(STR_METHOD_NAME);
    }
}@
