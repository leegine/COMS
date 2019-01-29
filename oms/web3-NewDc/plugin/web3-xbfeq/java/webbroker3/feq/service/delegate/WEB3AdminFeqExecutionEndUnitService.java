head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了UnitService(WEB3AdminFeqExecutionEndUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (外国株式出来終了UnitService)<BR>
 * 外国株式出来終了UnitServiceインタフェイス<BR>
 * <BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3AdminFeqExecutionEndUnitService extends Service 
{
    
    /**
     * (exec出来終了)<BR>
     * 顧客単位の出来終了処理を実施する。<BR>
     * @@param l_account - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF7D50142
     */
    public void execExecEnd(WEB3GentradeMainAccount l_account, String l_strMarketCode, Date l_datBizDate) 
        throws WEB3BaseException;
}
@
