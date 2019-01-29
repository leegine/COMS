head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RichPushPlugin�v���O�C���̃v���O�C���N���X(WEB3RichPushPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 �� (FLJ)�V�K�쐬
                  : 2009/06/03 �� (FTL) ���Ή�
 */
package webbroker3.rcp;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.rcp.data.qtp.WEB3QtpRcpSessionDatabaseExtensions;
import webbroker3.rcp.handler.WEB3QtpRichPushMainHandler;
import webbroker3.rcp.message.WEB3QtpRichPushMainRequest;
import webbroker3.rcp.message.WEB3QtpRichPushMainResponse;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushMainService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushEquityMarginOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpChangeCancelUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpContUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpLapseUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushFuOpOrderAcceptUnitServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushGateWayServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushMainServiceImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushPersistentDataManagerImpl;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpRichPushSwapOrderAcceptUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * WEB3RichPushPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3QtpRichPushPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushPlugin.class);

    private static boolean isPlugged = false;

    /** ���ő�v�b�V�����b�Z�[�W�� */
    private static final int DEFAULT_MAX_PUSH_MESSAGES_SIZE = 100;

    /** ���ő�v�b�V�����b�Z�[�W���ݒ�L�[ */
    private final static String STR_MAX_PUSH_MESSAGES_SIZE_KEY =
        "qtp.rich.push.max.messages.size";

    /** ���ő�v�b�V�����b�Z�[�W�� */
    private static int max_push_messages_size = DEFAULT_MAX_PUSH_MESSAGES_SIZE;

    /** �v�b�V���I�u�W�F�N�g�\�[�g�p�R���e�N�X�g�L�[*/
    public static final String PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.qtp.rcp.pushobjectcontext";

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3QtpRichPushPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3QtpRichPushPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        WEB3QtpRcpSessionDatabaseExtensions.plug();

        // QTP���b�`�N���C�A���g�v�b�V�����C�����b�Z�[�W�̓o�^
        regClass(WEB3QtpRichPushMainRequest.class);
        regClass(WEB3QtpRichPushMainResponse.class);

        // QTP���b�`�N���C�A���g�v�b�V�����C���n���h���[�̓o�^
        regHandler(WEB3QtpRichPushPlugin.class,
                   WEB3QtpRichPushMainRequest.class,
                   WEB3QtpRichPushMainHandler.class,
                   "handleQtpRichPushMainRequest");

        // QTP���b�`�N���C�A���g�v�b�V�����C���T�[�r�X
        Services.registerService(
            WEB3QtpRichPushMainService.class,
            new WEB3QtpRichPushMainServiceImpl());

        // Type<00> QTP���b�`�N���C�A���g�v�b�V������������t�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class,
            new WEB3QtpRichPushEquityMarginOrderAcceptUnitServiceImpl());

        // Type<01> QTP���b�`�N���C�A���g�v�b�V�������M�p�������n��t�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushSwapOrderAcceptUnitService.class,
            new WEB3QtpRichPushSwapOrderAcceptUnitServiceImpl());

        // Type<02> QTP���b�`�N���C�A���g�v�b�V��������������ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushEquityMarginChangeCancelUnitService.class,
            new WEB3QtpRichPushEquityMarginChangeCancelUnitServiceImpl());

        // Type<03> QTP���b�`�N���C�A���g�v�b�V�������o���ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushEquityMarginContUnitService.class,
            new WEB3QtpRichPushEquityMarginContUnitServiceImpl());

        // Type<04> QTP���b�`�N���C�A���g�v�b�V�����������ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushEquityMarginLapseUnitService.class,
            new WEB3QtpRichPushEquityMarginLapseUnitServiceImpl());

        // Type<10> QTP���b�`�N���C�A���g�v�b�V���敨OP������t�ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushFuOpOrderAcceptUnitService.class,
            new WEB3QtpRichPushFuOpOrderAcceptUnitServiceImpl());

        // Type<12> QTP���b�`�N���C�A���g�v�b�V���敨OP��������ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushFuOpChangeCancelUnitService.class,
            new WEB3QtpRichPushFuOpChangeCancelUnitServiceImpl());

        // Type<13> QTP���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushFuOpContUnitService.class,
            new WEB3QtpRichPushFuOpContUnitServiceImpl());

        // Type<14> QTP���b�`�N���C�A���g�v�b�V���敨OP�����ʒm�@@�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushFuOpLapseUnitService.class,
            new WEB3QtpRichPushFuOpLapseUnitServiceImpl());

        // QTP���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�[�T�[�r�X
        Services.registerService(
            WEB3QtpRichPushGateWayService.class,
            new WEB3QtpRichPushGateWayServiceImpl());

        // QTP���b�`�N���C�A���g�f�[�^�v�b�V���f�[�^�x�[�X�A�N�Z�X�Ǘ��T�[�r�X
        Services.registerService(
            WEB3QtpRichPushPersistentDataManager.class,
            new WEB3QtpRichPushPersistentDataManagerImpl());

        max_push_messages_size = getPushMaxMessageSizePreference();

        isPlugged = true;

        log.info("WEB3QtpRichPushPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

    public static int getPushMaxMessageSize()
    {
        return max_push_messages_size;
    }

    private static int getPushMaxMessageSizePreference()
    {

        final String STR_METHOD_NAME = "getPushMaxMessageSizePreference()";
        log.entering(STR_METHOD_NAME);

        int l_intPushMaxMessageSize = DEFAULT_MAX_PUSH_MESSAGES_SIZE;
        String l_strPushMaxMessageSize = GtlUtils.getTradingSystem().getPreference(
            STR_MAX_PUSH_MESSAGES_SIZE_KEY);
        if (l_strPushMaxMessageSize != null)
        {
            try
            {
                l_intPushMaxMessageSize = Integer.parseInt(l_strPushMaxMessageSize);
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intPushMaxMessageSize;

    }

}
@
