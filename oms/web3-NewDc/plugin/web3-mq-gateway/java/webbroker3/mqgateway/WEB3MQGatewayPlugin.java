head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQGateway�v���O�C���̃v���O�C���N���X(WEB3MQGatewayPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import webbroker3.mqgateway.manager.WEB3MQGatewayManagerPlugin;
import webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl;
import webbroker3.mqgateway.stdimpls.WEB3MQMessageSenderServiceImpl;
import webbroker3.mqgateway.stdimpls.data.MqGatewayMasterDatabaseExtensions;

/**
 * <p>
 * MQGateway�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * ����Plugin�̓A�v���P�[�V��������A<br>
 * MQ�փ��b�Z�[�W�𑗐M����Ƃ���Gateway�ƂȂ�T�[�r�X��񋟂���B<br>
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3MQGatewayPlugin extends Plugin
{
    
    public static final String PREF_NAME_QUEUE_ID_PREFIX =
        "webbroker3.mqgateway.queueid.";
    
    public static final String MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME = 
        "webbroker3.mqgateway.interceptor";

    public static final String MESSAGE_SPEC_ATTRIBUTE_NAME =
        "webbroker3.mqgateway.messagespec";
    
    private static final Logit log =
        Logit.getInstance(WEB3MQGatewayPlugin.class);
    
    private static boolean isPlugged = false;
    
    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3MQGatewayPlugin() {
    }
    
    public static void plug() throws Exception {
        plug(WEB3MQGatewayPlugin.class);
    }
    
    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception {
        
        KernelPlugin.plug();
        
        // �e�[�u������o�^
        MqGatewayMasterDatabaseExtensions.plug();
        
        // Gateway�T�[�r�X��o�^
        Services.registerService(
            WEB3MQGatewayService.class,
            new WEB3MQGatewayServiceImpl());
        
        // Sender�T�[�r�X��o�^
        Services.registerService(
            WEB3MQMessageSenderService.class,
            new WEB3MQMessageSenderServiceImpl());
        
//        // MQGateway�Ǘ��p�v���O�C�����N��
//        // TODO �{���ɂ����ŊǗ��p�v���O�C�����N�����邩�v����
//        WEB3MQGatewayManagerPlugin.plug();
        
        isPlugged = true;
        
        log.info("WEB3MQGatewayPlugin was plugged.");
    }
    
    public static boolean isPlugged() {
        return isPlugged;
    }
}
@
