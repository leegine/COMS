head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductInfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO銘柄情報作成サービスインタフェイス(WEB3IpoProductInfoService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.message.WEB3IPOProductInfo;

/**
 * IPO銘柄情報作成サービスインタフェイス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3IpoProductInfoService extends Service 
{
    
    /**
     * (createIPO銘柄情報)<BR>
     * 引数のIPO銘柄IDに該当するIPO銘柄の内容で、<BR>
     * IPO銘柄情報メッセージデータインスタンスを作成する。<BR>
     * @@param l_lngProductId - IPO銘柄ＩＤ
     * @@return webbroker3.ipo.message.WEB3IpoProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 40C66AFE03D5
     */
    public WEB3IPOProductInfo createIpoProductInfo(long l_lngProductId) throws WEB3BaseException;
    
    /**
     * (createIPO銘柄)<BR>
     * 画面入力値よりIPO銘柄情報を作成する。<BR>
     * 
     * @@param l_ipoProductParams - IPO銘柄行オブジェクト
     * @@param l_productInfoInputMsg - IPO銘柄情報入力メッセージ
     * @@param l_admin - 管理者オブジェクト
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40CE7934029A
     */
    public WEB3IpoProductImpl createIpoProduct(IpoProductParams l_ipoProductParams, WEB3IPOProductInfo l_productInfoInputMsg, WEB3Administrator l_admin);
}
@
