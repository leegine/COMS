head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-MarketAdaptor �v���O�C���N���X(WEB3MarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 �����@@�ǘa(SRA) �V�K�쐬
                   2005/01/05 �����@@�a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.marketadaptor;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3LogUtility;

/**
 * �iWebbroker3-MarketAdaptor �v���O�C���N���X�j�B<br>
 *<br>
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public final class WEB3MarketAdaptorAppPlugin extends Plugin
{
    /**
     * (���O���[�e�B���e�B�B)
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MarketAdaptorAppPlugin.class);
    
    /**
     * �i�R���X�g���N�^�j�B 
     */
    public WEB3MarketAdaptorAppPlugin ()
    {
    }

    /**
     * �i�v���O�C���G���g���[�|�C���g�j�B 
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3MarketAdaptorAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * �i�v���O�C�������j�B 
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
        // WEB3MarketSessionDatabaseExtensions.plug();
        //WEB3EquityMarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");

        // Adapter�̓o�^ --------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3EquityMarketRequestSenderServiceImpl());

        log.exiting(METHOD_NAME);
        
    }
}
@
