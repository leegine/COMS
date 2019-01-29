head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Ruito-MarketAdaptor �v���O�C���N���X(WEB3RuitoMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/05 ���u�� �V�K�쐬
                   
*/
package webbroker3.xbruito.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-Ruito-MarketAdaptor �v���O�C���N���X�B<br>
 *<br>
 * 
 * @@author ���u��
 * @@version 1.0
 */
public final class WEB3RuitoMarketAdaptorAppPlugin extends Plugin
{
	/**
	 * ���O���[�e�B���e�B�B
	 */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMarketAdaptorAppPlugin.class);

	/**
	 * �R���X�g���N�^�B
	 */
	public WEB3RuitoMarketAdaptorAppPlugin ()
	{
	}

	/**
	 * �v���O�C���G���g���[�|�C���g�B
	 */
    public static void plug() throws Exception
    {
    	String METHOD_NAME = "plug()";
		log.entering(METHOD_NAME);
        plug(WEB3RuitoMarketAdaptorAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

	/**
	 * �v���O�C�������B
	 */
    public static void onPlug() throws Exception
    {
		String METHOD_NAME = "onPlug()";
		log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
		// install the system plugins that we need
		KernelPlugin.plug();

		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

		// Adapter�̓o�^ --------------------
		// �s�ꃊ�N�G�X�g���M�T�[�r�X
        log.debug("XBRUITO: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��");
		l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
			new WEB3RuitoMarketRequestSubmitServiceImpl());

		log.exiting(METHOD_NAME);

    }
}
@
