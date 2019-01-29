head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文銘柄選択レスポンス(WEB3MarginProductSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引新規建注文銘柄選択レスポンス）。<br>
 * <br>
 * 信用取引新規建注文銘柄選択レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginProductSelectResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_productSelect";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (市場コードの一覧)
     */
    public String[] marketList;
    
    /**
     * (取引終了警告文言を表示する市場コードの一覧)
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 4140477E033B
     */
    public WEB3MarginProductSelectResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginProductSelectResponse(WEB3MarginProductSelectRequest l_request)
    {
        super(l_request);
    }
}
@
