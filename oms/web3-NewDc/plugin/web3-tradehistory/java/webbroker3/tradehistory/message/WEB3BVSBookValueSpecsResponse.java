head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 簿価単価明細照会レスポンス(WEB3BVSBookValueSpecsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08  賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (簿価単価明細照会レスポンス)<BR>
 * 簿価単価明細照会レスポンスクラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0  
 */
public class WEB3BVSBookValueSpecsResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_bookValueSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
        
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (現在の損益)<BR>
     * 現在の損益<BR>
     */
    public String currentProlossAmount;
    
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
     * (簿価単価明細情報一覧)<BR>
     */
    public WEB3BVSBookValueSpecsUnit[] bookValueUnits;
    
    /**
     * @@roseuid 418877BB03D8
     */
    public WEB3BVSBookValueSpecsResponse() 
    {
     
    }
    
    /**
     * デフォルトコンストラクタ<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3BVSBookValueSpecsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
