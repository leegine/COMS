head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleBasedMarketAdapterBasePlugin.java;


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
 */

package webbroker3.slebase;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import webbroker3.slebase.data.SleMarketAdapterSessionDatabaseExtensions;
import webbroker3.slebase.enums.*;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * �x�[�X�v���O�C���ł��B
 * </p>
 */
public final class SleBasedMarketAdapterBasePlugin extends Plugin {

  private static final WEB3LogUtility m_log          = WEB3LogUtility
                                                         .getInstance(SleBasedMarketAdapterBasePlugin.class);

  public static boolean               isPluggingDone = false;

  /**
   * singleton constructor
   */
  private SleBasedMarketAdapterBasePlugin() {
    ;
  }

  /**
   * �v���O�C�����v���O���܂��B
   */
  public static void plug() throws Exception {
    plug(SleBasedMarketAdapterBasePlugin.class);
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


    SleMarketAdapterSessionDatabaseExtensions.plug();


    m_log.info("Adding enums ");
    addEnums();


    m_log.exiting("onPlug()");

    isPluggingDone = true;
  }

  /**
   * enums�̒ǉ�
   */
  private static void addEnums() throws Exception {
    final Class[] enums = {
        SleSendqProcStatusEnum.class,//���M�L���[�����敪
		SleRcvdqProcStatusEnum.class,//����L���[�����敪
		SleConnectionStatusEnum.class,//SLE�R�l�N�^�X�e�[�^�X
		SleOpenStatusEnum.class,//�I�[�v���X�e�[�^�X 
		SleSendqOpTypeEnum.class,//�I�y���[�^�^�C�v
    };

    for (int i = 0; i < enums.length; i++) {
      EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
    }
  }

  /**
   * �A���v���O���ɂ͂��ׂĂ�SLE�R�l�N�^����т��̑��̃��\�[�X���N���[�Y���܂��B
   */
  public static void onUnplug() throws Exception {
    ;
  }
}@
