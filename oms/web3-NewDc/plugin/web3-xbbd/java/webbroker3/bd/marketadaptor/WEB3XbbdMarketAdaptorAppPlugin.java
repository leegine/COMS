head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3XbbdMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Xbbd-MarketAdaptor �v���O�C���N���X(WEB3XbbdMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 ���� (���u)  �V�K�쐬                
*/

package webbroker3.bd.marketadaptor;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Xbbd-MarketAdaptor �v���O�C���N���X�B
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3XbbdMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3XbbdMarketAdaptorAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3XbbdMarketAdaptorAppPlugin ()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3XbbdMarketAdaptorAppPlugin.class);
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
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);

        // Adapter�̓o�^ --------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        log.debug("Xbbd: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��........");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3BondMarketRequestService());
        log.debug("Xbbd: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��........OK");
        log.exiting(METHOD_NAME);

    }
}
@
