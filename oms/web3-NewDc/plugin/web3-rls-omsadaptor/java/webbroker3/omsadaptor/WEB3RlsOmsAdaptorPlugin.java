head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsOmsAdaptorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RlsOmsAdaptorPlugin�v���O�C���̃v���O�C���N���X(WEB3RlsOmsAdaptorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.omsadaptor;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.omsadaptor.handler.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.omsadaptor.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3RlsOmsAdaptorPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsOmsAdaptorPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsOmsAdaptorPlugin.class);

    private static boolean isPlugged = false;

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3RlsOmsAdaptorPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RlsOmsAdaptorPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        //���[���G���W���ꌏ�ʒm�p���b�Z�[�W�̓o�^
        regClass(WEB3RlsCondOrderSubmitRequest.class);
        regClass(WEB3RlsCondOrderSubmitResponse.class);

        //���[���G���W���ʒm���b�Z�[�W�̓o�^
        regClass(WEB3RlsCondOrderNotifyMainRequest.class);
        regClass(WEB3RlsCondOrderNotifyMainResponse.class);

        //���[���G���W���ꌏ�ʒm�n���h���[�̓o�^
        regHandler(WEB3RlsOmsAdaptorPlugin.class,
                   WEB3RlsCondOrderSubmitRequest.class,
                   WEB3RlsCondOrderSubmitHandler.class,
                   "handleRlsCondOrderSubmitRequest");

        //���[���G���W���ʒm�n���h���[�̓o�^
        regHandler(WEB3RlsOmsAdaptorPlugin.class,
                   WEB3RlsCondOrderNotifyMainRequest.class,
                   WEB3RlsCondOrderNotifyMainHandler.class,
                   "handleRlsCondOrderNotifyMainRequest");

        // WEB3RlsCondOrderSubmitService�T�[�r�X��o�^
        Services.registerService(
            WEB3RlsCondOrderSubmitService.class,
            new WEB3RlsCondOrderSubmitServiceImpl());
        // WEB33RlsCondOrderNotifyMainService�T�[�r�X��o�^
        Services.registerService(
            WEB33RlsCondOrderNotifyMainService.class,
            new WEB3RlsCondOrderNotifyMainServiceImpl());

        // RAC-CTX�C���^�Z�v�^�̐ݒ�
        Services.addInterceptor(
            WEB3RlsCondOrderSubmitService.class,
            new WEB3RlsDescendRacCtxInterceptor());

        isPlugged = true;

        log.info("WEB3RlsOmsAdaptorPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }
}
@
