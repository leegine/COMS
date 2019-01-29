head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金ポジションマネージャ(WEB3AioPositionManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建  (中訊) 新規作成
                   2004/10/27 屈陽 (中訊) レビュー
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioPositionManagerImpl;


/**
 * (入出金ポジションマネージャ)<BR>
 * 入出金ポジションマネージャクラス<BR>
 * （AioPositionManagerの拡張クラス）<BR>
 * 
 * @@author 黄建(中訊)<BR>
 * @@version 1.0 
 */

public class WEB3AioPositionManager extends AioPositionManagerImpl 
{
    
    /**
     * (入出金ポジションマネージャ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * m_tradingType = ProductTypeEnum.AIO<BR>
     * m_helper = new 入出金ポジションヘルパー<BR>
     * <BR>
     * をセットする。<BR>
     * <BR>
     * @@roseuid 413EDA6C0317
     */
    public WEB3AioPositionManager() 
    {
        super.m_helper = 
            new WEB3AioPositionManagerHelper(ProductTypeEnum.AIO);
    }
}
@
