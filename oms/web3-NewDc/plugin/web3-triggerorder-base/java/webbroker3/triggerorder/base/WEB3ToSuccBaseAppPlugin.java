head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccBaseAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-TriggerOrder-Base �v���O�C���N���X(WEB3ToSuccBaseAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 �s�p (���u) �V�K�쐬 
Revesion History : 2008/03/25 ��іQ (���u) ���f��No.254
*/
package webbroker3.triggerorder.base;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.triggerorder.base.data.WEB3TriggerOrderAccountDatabaseExtensions;
import webbroker3.triggerorder.base.data.WEB3TriggerOrderMasterDatabaseExtensions;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-TriggerOrder �v���O�C���N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccBaseAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccBaseAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3ToSuccBaseAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3ToSuccBaseAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception 
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        //���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        //install the system plugins that we need
        KernelPlugin.plug();
        
        //DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3TriggerOrderAccountDatabaseExtensions.plug();
        WEB3TriggerOrderMasterDatabaseExtensions.plug();
        
        //Service �̓o�^
        //�����\�񒍕��X�V�T�[�r�X 
        Services.registerService(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl());

        //�敨OP�\�񒍕��X�V�T�[�r�X
        Services.registerService(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl());

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        // �����\�񒍕��X�V�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�\�񒍕��X�V�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        Services.addInterceptor(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨OP�\�񒍕��X�V�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        log.exiting(METHOD_NAME);
    }
}
@
