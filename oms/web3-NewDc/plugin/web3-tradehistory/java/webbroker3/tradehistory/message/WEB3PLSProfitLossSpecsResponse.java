head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細照会レスポンス(WEB3PLSProfitLossSpecsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 範慧琴 (中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (損益明細照会レスポンス)<BR>
 * 損益明細照会レスポンスクラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_profitLossSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (損益明細情報一覧 )<BR>
     */
    public WEB3PLSProfitLossSpecsUnit[] profitLossUnits;
    
    /**
     * @@roseuid 418877BC0186
     */
    public WEB3PLSProfitLossSpecsResponse() 
    {
     
    }
    
    /**
     * デフォルトコンストラクタ<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3PLSProfitLossSpecsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
