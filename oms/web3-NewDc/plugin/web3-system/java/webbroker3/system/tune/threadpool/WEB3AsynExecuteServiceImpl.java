head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AsynExecuteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �񓯊����s�����N���X(WEB3AsynExecuteServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/07 �� �V�K�쐬
 */

package webbroker3.system.tune.threadpool;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.util.*;

/**
 * �񓯊����s�����N���X
 *
 * @@author ��
 * @@version 1.0
 */
public class WEB3AsynExecuteServiceImpl
    implements WEB3AsynExecuteService
{
    /**
     * �񓯊����s�̐ݒ�L�[
     */
    private final static String STR_ASYN_EXECUTE_KEY = "system.asyn.execute";

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynExecuteServiceImpl.class);

    public void execute(Runnable l_runnable)
    {
        try
        {
            String l_strIsAsynExecute = GtlUtils.getTradingSystem().getPreference(
                STR_ASYN_EXECUTE_KEY);
            if ("false".equals(l_strIsAsynExecute))
            {
                if (log.ison())
                {
                    log.debug("asyn.execute=false >>" + l_runnable.getClass());
                }
                l_runnable.run();
            }
            else
            {
                if (log.ison())
                {
                    log.debug("asyn.execute=true >>" + l_runnable.getClass());
                }
                WEB3ThreadPoolPlugin.getThreadPool().execute(l_runnable);
            }
        }
        catch (InterruptedException ex)
        {
        }
    }

}
@
