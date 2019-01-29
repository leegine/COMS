head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : Affinity�v���O�C���N���X(WEB3AffinityPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 �� �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import webbroker3.system.tune.affinity.handler.*;
import webbroker3.system.tune.affinity.message.*;
import webbroker3.util.*;

/**
 * Affinity�v���O�C���N���X
 *
 * @@author ��
 * @@version 1.0
 */
public final class WEB3AffinityPlugin
    extends Plugin
{
    /**
     * ���O
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AffinityPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AffinityPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        plug(WEB3AffinityPlugin.class);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        KernelPlugin.plug();
        WEB3ServerConnectorPlugin.plug();

        //���b�Z�[�W�N���X
        regClass(WEB3AffinityDescendRequest.class);

        //�n���h���N���X
        regHandler(
            WEB3AffinityDescendRequest.class,
            WEB3AffinityDescendHandler.class,
            "handleWEB3AffinityDescendRequest");

        //�T�[�r�X�N���X
        Services.registerService(WEB3OrderReqNumberHead2ManageService.class,
                                 new WEB3OrderReqNumberHead2ManageServiceImpl());

        Services.registerService(WEB3DescendRacCtxService.class,
                                 new WEB3DescendRacCtxServiceImpl());

        //�J�X�^�������̓o�^
        try
        {
            MultiPoolJndiNameLookupService service = (MultiPoolJndiNameLookupService)
                Services.getService(
                MultiPoolJndiNameLookupService.class);
            if (service != null)
            {
                Services.overrideService(MultiPoolJndiNameLookupService.class,
                                         new WEB3MultiPoolJndiNameLookupServiceImpl());
            }
        }
        catch (Exception e)
        {
            Services.registerService(MultiPoolJndiNameLookupService.class,
                                     new WEB3MultiPoolJndiNameLookupServiceImpl());
        }

        // RacPlugin �̐���ɂ��K��TransactionalInterceptor �ň͂ޕK�v������܂��B
        Services.addInterceptor(MultiPoolJndiNameLookupService.class,
                                new TransactionalInterceptor(TransactionalInterceptor.
            TX_CREATE_NEW));

    }

}
@
