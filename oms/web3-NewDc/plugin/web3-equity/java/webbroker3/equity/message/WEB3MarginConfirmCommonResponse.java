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
filename	WEB3MarginConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引確認共通レスポンス(WEB3MarginConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引確認共通レスポンス）。<br>
 * <br>
 * 信用取引確認共通レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginConfirmCommonResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_confirmCommon";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101754L;    
    /**
     * (確認時発注日)<BR>
     * <BR>
     * 完了リクエストで送信する値<BR>
     */
    public Date checkDate;
    
    /**
     * (概算受渡代金)<BR>
     * <BR>
     * 新規建：　@概算建代金を設定<BR>
     * 返済：　@概算決済損益代金を設定<BR>
     * 現引現渡：　@概算受渡代金を設定<BR>
     */
    public String estimatedPrice;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * <BR>
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 41403F6F0118
     */
    public WEB3MarginConfirmCommonResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginConfirmCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
