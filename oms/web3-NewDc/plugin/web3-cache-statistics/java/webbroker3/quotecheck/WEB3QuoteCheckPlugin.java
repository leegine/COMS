head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteCheckPlugin�v���O�C���̃v���O�C���N���X(WEB3QuoteCheckPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2009/02/03 �� (FLJ)�V�K�쐬
 */
package webbroker3.quotecheck;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.quotecheck.data.WEB3QuoteCheckSessionDatabaseExtensions;
import webbroker3.quotecheck.handler.WEB3QuoteCheckHandler;
import webbroker3.quotecheck.message.WEB3QuoteCheckRequest;
import webbroker3.quotecheck.message.WEB3QuoteCheckResponse;
import webbroker3.quotecheck.service.delegate.WEB3QuoteCheckService;
import webbroker3.quotecheck.service.delegate.stdimpls.WEB3QuoteCheckServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * ��������_�`�F�b�N�̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3QuoteCheckPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteCheckPlugin.class);

    private static boolean isPlugged = false;

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3QuoteCheckPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3QuoteCheckPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();
        
        WEB3QuoteCheckSessionDatabaseExtensions.plug();

        regClass(WEB3QuoteCheckRequest.class);
        regClass(WEB3QuoteCheckResponse.class);

        regHandler(
            WEB3QuoteCheckPlugin.class,
            WEB3QuoteCheckRequest.class,
            WEB3QuoteCheckHandler.class,
            "handleWEB3QuoteCheckRequest");

        Services.registerService(
                WEB3QuoteCheckService.class,
                new WEB3QuoteCheckServiceImpl());

        isPlugged = true;

        log.info("WEB3QuoteCheckPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

}
@
