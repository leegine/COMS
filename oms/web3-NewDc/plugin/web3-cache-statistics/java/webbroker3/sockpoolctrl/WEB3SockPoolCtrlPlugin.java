head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolCtrlPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SockPoolCtrlPlugin�v���O�C���̃v���O�C���N���X(WEB3SockPoolCtrlPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/08/18 �� (FLJ)�V�K�쐬
                  : 2008/10/24 �� (FTL) Invalidation�G���[�Ή�
 */
package webbroker3.sockpoolctrl;

import java.lang.reflect.Method;

import com.fitechlabs.dbind.impl.InvHeartbeats;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.sockpoolctrl.handler.*;
import webbroker3.sockpoolctrl.message.*;
import webbroker3.sockpoolctrl.service.delegate.*;
import webbroker3.sockpoolctrl.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3SockPoolCtrlPlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3SockPoolCtrlPlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SockPoolCtrlPlugin.class);

    private static boolean isPlugged = false;

    /**
     * �C���o���f�[�V�����I���ҋ@@����
     */
    private final static long DEFAULT_SLEEP_MILLISECS = 5000;

    /**
     * �C���o���f�[�V�����I���ҋ@@���ԃv���t�@@�����X�L�[
     */
    private final static String INVALIDATION_SAFE_STOP_SLEEP_KEY = "invalidation.safe.stop.sleep.millisecs";

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3SockPoolCtrlPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3SockPoolCtrlPlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        regClass(WEB3SockPoolCtrlRequest.class);
        regClass(WEB3SockPoolCtrlResponse.class);

        regHandler(
            WEB3SockPoolCtrlPlugin.class,
            WEB3SockPoolCtrlRequest.class,
            WEB3SockPoolCtrlHandler.class,
            "handleWEB3SockPoolCtrlRequest");

        Services.registerService(
            WEB3SockPoolControlService.class,
            new WEB3SockPoolControlServiceImpl());

        isPlugged = true;

        log.info("WEB3SockPoolCtrlPlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

    public static void onUnplug() throws Exception
    {

        try
        {
            log.info("on unplugging WEB3SockPoolCtrlPlugin...");

            long l_lngSleepTime = DEFAULT_SLEEP_MILLISECS;
            String l_strSleepTime = GtlUtils.getTradingSystem().getPreference(
                    INVALIDATION_SAFE_STOP_SLEEP_KEY);
            if (l_strSleepTime != null)
            {
                try
                {
                    l_lngSleepTime = Long.parseLong(l_strSleepTime);
                }
                catch (NumberFormatException e)
                {
                    log.error(e.getMessage(), e);
                }
            }

            synchronized (com.fitechlabs.dbind.impl.InvServer.class)
            {

                log.error("stopping InvHeartbeats ...");
                Class heartbeats = InvHeartbeats.class;
                Method method = heartbeats.getDeclaredMethod("stopAll", new Class[0]);
                method.setAccessible(true);
                method.invoke(heartbeats, new Object[0]);
                log.error("stopped  InvHeartbeats successfully.");

                log.info("sleep millisecs=" + l_lngSleepTime);
                try
                {
                    Thread.sleep(l_lngSleepTime);
                }
                catch (InterruptedException e)
                {
                    log.error(e.getMessage(), e);
                }
            }

            isPlugged = false;
            
            log.info("WEB3SockPoolCtrlPlugin was unplugged.");
        }
        catch(Throwable t)
        {
            log.error("error on unplugging WEB3SockPoolCtrlPlugin. "  + t.getMessage(), t);
        }
    }

}
@
