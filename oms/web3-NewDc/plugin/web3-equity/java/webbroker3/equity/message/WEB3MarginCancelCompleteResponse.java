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
filename	WEB3MarginCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文取消完了レスポンスクラス(WEB3MarginCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引注文取消完了レスポンス）。<br>
 * <br>
 * 信用取引注文取消完了レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginCancelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_cancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409101622L;
    
    /**
     * (更新時間)<BR>
     * <BR>
     * 注文受付時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * <BR>
     * 注文ＩＤ<BR>
     */
    public String orderActionId;
    
    /**
     * (連続注文設定フラグ)<BR>
     * <BR>
     * rue：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;
    
    /**
     * @@roseuid 414046A703BE
     */
    public WEB3MarginCancelCompleteResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCancelCompleteResponse(WEB3MarginCancelCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
