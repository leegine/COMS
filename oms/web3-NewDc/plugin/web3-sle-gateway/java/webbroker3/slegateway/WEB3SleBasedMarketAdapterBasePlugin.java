head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleBasedMarketAdapterBasePlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleBasedMarketAdapterBasePlugin�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 �� �V�K�쐬
                    2006/05/29 �� ���J�o���[������ǉ� 
 */
package webbroker3.slegateway;

import webbroker3.slebase.data.SleMarketAdapterSessionDatabaseExtensions;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleOpenStatusEnum;
import webbroker3.slegateway.handler.WEB3SleBasedMarketAdapterPluginMessagesHandler;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryResponse;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;
import webbroker3.slegateway.message.WEB3ProcessSleSendqResponse;
import webbroker3.slegateway.service.delegate.WEB3SleRecoveryProcessorManagerService;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorManagerService;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorService;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleConnectorManager;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleRecoveryProcessorManagerServiceImpl;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleSendqProcessorManagerServiceImpl;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleSendqProcessorServiceImpl;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;

/**
 * SLE Connector���g�����}�[�P�b�g�E�A�_�v�^�[�v���O�C���ł��B
 */
public final class WEB3SleBasedMarketAdapterBasePlugin extends Plugin {

  private static final WEB3LogUtility m_log          = WEB3LogUtility
                                                         .getInstance(WEB3SleBasedMarketAdapterBasePlugin.class);


  public static boolean               isPluggingDone = false;


  private WEB3SleBasedMarketAdapterBasePlugin() {
    ;
  }

  /**
   * �v���O�C�����v���O���܂��B
   */
  public static void plug() throws Exception {
    plug(WEB3SleBasedMarketAdapterBasePlugin.class);
  }

  /**
   * �v���O����鎞�̃R�[���o�b�N���\�b�h�ł��B
   */
  public static void onPlug() throws Exception {

    m_log.entering("onPlug()");
    if (isPluggingDone) {
      m_log.info("Already plugged in. Ignoring the call.");
      return;
    }
    m_log.info("Plugging in SLE based MarketAdapterBase.");


    com.fitechlabs.xtrade.kernel.boot.KernelPlugin.plug();
    com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.plug();

	//base��DB�G�N�X�e���V������o�^����
    SleMarketAdapterSessionDatabaseExtensions.plug();

    // �T�[�r�X�̓o�^
    m_log.info("Installing necessary services...");
    registerServices();

    m_log.info("Adding enums ");
    //base�̃G�i���[�o�^����
    addEnums();

    m_log.info("registering messages and handlers");
    registerMessagesAndHandlers();
    
    //���C���^�Z�v�^�ǉ�(2006/9/21)
    m_log.info("Adding interceptor");
    //���M�T�[�r�X�C���^�Z�v�^�o�^����
    addInterceptors();


    //final Class[] preloadClasses = { WEB3SleMarketAdapterErrorMessageDef.class };//ErrorCatog�N���X���폜 2006/10/23


    m_log.exiting("onPlug()");
    // plugging done
    isPluggingDone = true;
  }

  /**
   * �萔�̒ǉ�
   */
  private static void addEnums() throws Exception {
    final Class[] enums = { 
    	SleSendqOpTypeEnum.class,//�I�y���[�^�^�C�v
        SleSendqProcStatusEnum.class,//���M�X�e�[�^�X
		SleRcvdqProcStatusEnum.class,//����L���[�����敪
        SleConnectionStatusEnum.class,//SLE�R�l�N�^��SLE�����G���W���Ԃ̐ڑ����
        SleOpenStatusEnum.class,//SLE�ڑ���ԊǗ����R�[�h�̃I�[�v�����
    };

    for (int i = 0; i < enums.length; i++) {
      EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
    }
  }

  /**
   * �n���h���̓o�^
   */
  private static void registerMessagesAndHandlers() throws Exception {


    final Class[] messages = {
            WEB3ProcessSleSendqRequest.class, WEB3ProcessSleSendqResponse.class,
            WEB3ProcessSleRecoveryRequest.class,WEB3ProcessSleRecoveryResponse.class//reg class .2006/05/29 ���@@�ǉ�
            };
    for (int i = 0; i < messages.length; i++) {
      regClass(messages[i]);
    }


    regHandler(WEB3SleBasedMarketAdapterBasePlugin.class, WEB3ProcessSleSendqRequest.class,
            WEB3SleBasedMarketAdapterPluginMessagesHandler.class, "handleSendQ");
    //reg handler .2006/05/29 ���@@�ǉ�
    regHandler(WEB3SleBasedMarketAdapterBasePlugin.class, WEB3ProcessSleRecoveryRequest.class,
            WEB3SleBasedMarketAdapterPluginMessagesHandler.class, "handleRecovery");
    
  }

  /**�T�[�r�X�̓o�^*/
  private static void registerServices() throws Exception {
	
	// send_q manager service.2006/9/21 �� �ǉ�
    Services.registerService(WEB3SleSendqProcessorManagerService.class,new WEB3SleSendqProcessorManagerServiceImpl());

    //recovery service.2006/05/29 ���@@�ǉ�
    Services.registerService(WEB3SleRecoveryProcessorManagerService.class, new WEB3SleRecoveryProcessorManagerServiceImpl());
    
    //send_q service.2006/9/21 ���ǉ�
	Services.registerService(WEB3SleSendqProcessorService.class, 
							 new WEB3SleSendqProcessorServiceImpl(
							 )); 
  }
  
  /**
   * �T�[�r�X�C���^�Z�v�^�̒ǉ�
   */
   private static void addInterceptors() throws Exception {
   	
		// Service�C���^�Z�v�^�̐ݒ�
		// 1)send_q���b�Z�[�W1�����M�T�[�r�X�C���^�Z�v�^
		Services.addInterceptor(
			WEB3SleSendqProcessorService.class,
		  	new WEB3SLEGatewayInterceptor());
	
		// RAC-CTX�C���^�Z�v�^�̐ݒ�
		// 1)send_q���b�Z�[�W1�����M�T�[�r�XRAC-CTX�C���^�Z�v�^ �ǉ� 2006/11/7
		Services.addInterceptor(
			WEB3SleSendqProcessorService.class,
			new  WEB3SleSenderRacCtxInterceptor());
   }

  /**
   * �A���v���O���ɂ͂��ׂĂ�SLE�R�l�N�^����т��̑��̃��\�[�X���N���[�Y���܂��B
   */
  public static void onUnplug() throws Exception {

      WEB3SleConnectorManager.close();
  }
}@
