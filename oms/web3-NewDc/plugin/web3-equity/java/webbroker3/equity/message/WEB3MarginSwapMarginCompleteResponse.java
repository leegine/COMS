head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文完了レスポンス(WEB3MarginSwapMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引現引現渡注文完了レスポンス）。<br>
 * <br>
 * 信用取引現引現渡注文完了レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (更新時間)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * <BR>
     * 注文ＩＤ<BR>
     */
    public String orderActionId;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要　@　@　@false：警告表示不要
	 */
	public boolean insiderWarningFlag;
    
    /**
     * @@roseuid 414042550010
     */
    public WEB3MarginSwapMarginCompleteResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginSwapMarginCompleteResponse(WEB3MarginSwapMarginCompleteRequest l_request)
    {
        super(l_request);
    }    
}
@
