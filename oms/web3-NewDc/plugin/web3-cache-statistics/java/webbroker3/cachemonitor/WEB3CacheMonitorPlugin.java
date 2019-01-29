head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3CacheMonitorPlugin�v���O�C���̃v���O�C���N���X(WEB3CacheMonitorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/18 �� (FLJ)�V�K�쐬
 */
package webbroker3.cachemonitor;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.cachemonitor.data.*;
import webbroker3.cachemonitor.handler.*;
import webbroker3.cachemonitor.message.*;
import webbroker3.cachemonitor.service.delegate.*;
import webbroker3.cachemonitor.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3CacheMonitorPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3CacheMonitorPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CacheMonitorPlugin.class);

    private static boolean isPlugged = false;

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3CacheMonitorPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3CacheMonitorPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        WEB3CacheMonitorSessionDatabaseExtensions.plug();

        regClass(WEB3CacheMonitorRequest.class);
        regClass(WEB3CacheMonitorResponse.class);

        regHandler(
            WEB3CacheMonitorPlugin.class,
            WEB3CacheMonitorRequest.class,
            WEB3CacheMonitorHandler.class,
            "handleWEB3CacheMonitorRequest");

        Services.registerService(
            WEB3CacheMonitorService.class,
            new WEB3CacheMonitorServiceImpl());

        isPlugged = true;

        log.info("WEB3CacheMonitorPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

}
@
