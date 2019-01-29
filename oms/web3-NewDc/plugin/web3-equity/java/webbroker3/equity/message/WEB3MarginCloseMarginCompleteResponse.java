head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引返済注文完了レスポンスクラス(WEB3MarginCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1167
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引返済注文完了レスポンス）。<br>
 * <br>
 * 信用取引返済注文完了レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101643L;      
    /**
     * (更新時間)<BR>
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
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 414047D802C2
     */
    public WEB3MarginCloseMarginCompleteResponse() 
    {
  
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCloseMarginCompleteResponse(WEB3MarginCloseMarginCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
