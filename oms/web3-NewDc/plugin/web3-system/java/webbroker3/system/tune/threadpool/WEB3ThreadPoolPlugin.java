head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ThreadPoolPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �X���b�h�v�[���v���O�C���N���X(WEB3ThreadPoolPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/07 �� �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �X���b�h�v�[���v���O�C���N���X
 *
 * @@author ��
 * @@version 1.0
 */
public final class WEB3ThreadPoolPlugin
    extends Plugin
{

    /**
     * �X���b�h �v�[��
     */
    private static ThreadPool pool;

    /**
     * �X���b�h �v�[����DEFAULT�T�C�Y
     */
    private final static int DEFAULT_THREAD_POOL_SIZE = 20;

    /**
     * �X���b�h �v�[���T�C�Y�̐ݒ�L�[
     */
    private final static String STR_THREAD_POOL_SIZE_KEY = "system.thread.pool.size";

    static
    {
        //�X���b�h �v�[��������
        int l_intThreadSize = DEFAULT_THREAD_POOL_SIZE;
        String l_strThreadSize = GtlUtils.getTradingSystem().getPreference(
            STR_THREAD_POOL_SIZE_KEY);
        if (l_strThreadSize != null)
        {
            try
            {
                l_intThreadSize = Integer.parseInt(l_strThreadSize);
            }
            catch (Exception e)
            {}
        }
        pool = new ThreadPool(l_intThreadSize);
    }

    /**
     * �R���X�g���N�^�B
     */
    public WEB3ThreadPoolPlugin()
    {
    }

    public static ThreadPool getThreadPool()
    {
        return pool;
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        plug(WEB3ThreadPoolPlugin.class);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        Services.registerService(WEB3AsynExecuteService.class,
                                 new WEB3AsynExecuteServiceImpl());

    }

    /**
     * �v���O�C���I�������B
     */
    public static void onUnplug() throws Exception
    {
        pool.shutdownNow();
    }

}
@
