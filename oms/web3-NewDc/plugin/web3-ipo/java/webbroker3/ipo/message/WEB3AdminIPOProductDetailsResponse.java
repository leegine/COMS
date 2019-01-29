head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄詳細レスポンス(WEB3AdminIPOProductDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.101修正
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄詳細レスポンス)<BR>
 * IPO銘柄詳細レスポンスクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDetails";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161039L;
    
    /**
     * 操作コード一覧<BR>
     * <BR>
     * 01：　@訂正<BR>
     * 02：　@削除<BR>
     * 11：　@ブックビルディング状況ダウンロード<BR>
     * 12：　@抽選結果／購入申込状況ダウンロード<BR>
     * 21：　@抽選結果アップロード<BR>
     * 31：　@IPO募集停止／再開<BR>
     * 32：　@IPO中止<BR>
     * 41：　@抽選割当入力<BR>
     * 42：　@抽選割当結果確認<BR>
     * 43：　@抽選割当結果完了<BR>
     * <BR>
     */
    public String[] controlCodeList;
    
    /**
     * 銘柄情報
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D02D1
     */
    public WEB3AdminIPOProductDetailsResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄詳細レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D141F30025
     */
    public WEB3AdminIPOProductDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
