head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.36.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Aio-MarketAdaptor �v���O�C���N���X(WEB3AioMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 ����� (���u)  �V�K�쐬
                   
*/
package webbroker3.aio.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-Aio-MarketAdaptor �v���O�C���N���X�B<br>
 *<br> 
 * 
 * @@author ����� (���u)
 * @@version 1.0
 */
public final class WEB3AioMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketAdaptorAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AioMarketAdaptorAppPlugin ()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String l_strMETHOD_NAME = "plug()";
        log.entering(l_strMETHOD_NAME);
        plug(WEB3AioMarketAdaptorAppPlugin.class);
        log.exiting(l_strMETHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String l_strMETHOD_NAME = "onPlug()";
        log.entering(l_strMETHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);

        // Adapter�̓o�^ --------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        log.info("XBAIO: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��........");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3AioMarketRequestSenderServiceImpl());
        log.info("XBAIO: �s�ꃊ�N�G�X�g���M�T�[�r�X���C���X�g�[��........OK");
        log.exiting(l_strMETHOD_NAME);

    }
}
@
