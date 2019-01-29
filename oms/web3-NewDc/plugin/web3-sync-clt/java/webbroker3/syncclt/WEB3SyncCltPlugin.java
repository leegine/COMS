head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	WEB3SyncCltPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SyncCltPlugin�v���O�C���̃v���O�C���N���X(WEB3SyncCltPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 					2007/12/21 FLJ���@@�X�V
 Revesion History : 2009/01/07 �����F (���u) ���؎�����Ή�
 */
package webbroker3.syncclt;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import webbroker3.aio.service.delegate.*;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.equity.service.delegate.*;
import webbroker3.ifo.service.delegate.*;
import webbroker3.mf.service.delegate.*;
import webbroker3.syncclt.data.*;
import webbroker3.util.*;
import webbroker3.xbruito.service.delegate.*;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;

/**
 * <p>
 * WEB3SyncCltPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3SyncCltPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SyncCltPlugin.class);

    private static boolean isPlugged = false;

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3SyncCltPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3SyncCltPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();
        WEB3SyncCltSessionDatabaseExtensions.plug();

        //�����������͒ʒm
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����������͒ʒm
        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�������n�����ʒm
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�ݐϓ���������t
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //SONAR���o��
        Services.addInterceptor(
            WEB3AioSonarCashTransService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSonarCashTransService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�U�֐����ʒm
        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��U�֋���
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //��������U��
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�U�֐����A�o�ɐ���
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //���M�����ʒm
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP�����ʒm
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨�����ʒm
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //20061227�ǉ�
        //���M������t�T�[�r�X
        //WEB3MutualOrderAcceptService
        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����M�������t�T�[�r�X
        //WEB3MutualCancelAcceptService
        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        //20070601�ǉ�
        //�O��MMF������t�T�[�r�X
		//WEB3MutualFrgnMmfOrderAcceptService
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���@@20071221�ǉ�------------------------
        //p_type = aio_cashin_accept /* ���o����t(GI80C) */
        //������t�T�[�r�X
		//WEB3AioCashinAcceptService
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));        
        
        //p_type = acc_trans_change_request_accept /* �U�֐�����t(GI80F) */
        //�U�֐�����t�T�[�r�X
		//WEB3AccTransChangeRequestAcceptService
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //p_type = aio_security_transfer_notify /* �،��U�֒ʒm(GI80G) */
        //�،��U�֒ʒm�T�[�r�X
		//WEB3AioSecurityTransferNotifyService
        Services.addInterceptor(
                WEB3AioSecurityTransferNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioSecurityTransferNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //p_type = accOpen_voucherRegAccept /* �����J�ݎ�t(GI82A) */
        //�����J�ݓ`�[�o�^��t�T�[�r�X
		//WEB3AccOpenVoucherRegAcceptService
        Services.addInterceptor(
                WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccOpenVoucherRegAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));        
        
        //p_type = accInfo_lockRegistReleaseAccept /* ���b�N�q�o�^����(GI84F),����q�o�^(GI84G) */
        //���b�N�o�^������t�T�[�r�X
		//WEB3AccInfoLockRegistReleaseAcceptService
        Services.addInterceptor(
                WEB3AccInfoLockRegistReleaseAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AccInfoLockRegistReleaseAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //���@@20071221�ǉ�------------------------
        //���@@20080402�ǉ�------------------------
        // p_type = aio_cashout_accept /* �o��������t(GI80A)*/
        //�o����t�T�[�r�X
        //WEB3AioCashoutAcceptService
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING)); 
        //���@@20080402�ǉ�------------------------       

        //���ӏ��ʒm
        Services.addInterceptor(
            WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3SyncProcStatusInterceptor());

        Services.addInterceptor(
            WEB3AdminEquityAttentionInfoNotifyService.class,
            new TransactionalInterceptor(
            TransactionalInterceptor.TX_JOIN_EXISTING));

        isPlugged = true;

        log.info("WEB3SyncCltPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@
