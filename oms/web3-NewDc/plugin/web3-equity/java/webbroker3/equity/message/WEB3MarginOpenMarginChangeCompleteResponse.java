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
filename	WEB3MarginOpenMarginChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文訂正_新規建完了レスポンスクラス(WEB3MarginOpenMarginChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1167
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引注文訂正_新規建完了レスポンス）。<br>
 * <br>
 * 信用取引注文訂正_新規建完了レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeComplete";

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
     * 注文ＩＤ
     */
    public String orderActionId;
    
    /**
     * (インサイダー警告表示フラグ)<BR>
     * true：警告表示要　@　@　@false：警告表示不要
     */
    public boolean insiderWarningFlag;
    
    /**
     * (連続注文設定フラグ)<BR>
     * true：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140453902A9
     */
    public WEB3MarginOpenMarginChangeCompleteResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginOpenMarginChangeCompleteResponse(WEB3MarginOpenMarginChangeCompleteRequest l_request)
    {
        super(l_request);
    }
}
 @
