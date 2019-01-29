head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-ifo-MarketAdaptor �v���O�C���N���X(WEB3IfoMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 �����@@�ǘa(SRA) �V�K�쐬
                   2004/06/17 ����(���u)  �C��
*/
package webbroker3.ifo.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-ifo-MarketAdaptor �v���O�C���N���X�B<br>
 *<br>
 * 
 * @@author ����
 * @@version 1.0
 */
public final class WEB3IfoMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoMarketAdaptorAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3IfoMarketAdaptorAppPlugin ()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3IfoMarketAdaptorAppPlugin.class);
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

        // DatabaseExtensions �̃v���O�C������ ----------------------
        // WEB3MarketSessionDatabaseExtensions ���v���O�C��
        //WEB3MarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        // Adapter�̓o�^ --------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        log.debug("XBIFO: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3IfoMarketRequestSenderServiceImpl());

        log.exiting(METHOD_NAME);

    }
}
@
