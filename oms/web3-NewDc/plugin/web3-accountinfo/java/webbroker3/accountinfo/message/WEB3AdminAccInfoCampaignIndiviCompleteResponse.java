head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更完了ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCampaignIndiviCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.165
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更完了ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料割引キャンペーン個別顧客指定変更完了レスポンス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312038L;
    
    /**
     * (警告フラグ)<BR>
     * 警告フラグ<BR>
     * <BR>
     * 0：警告なし<BR>
     * 1：警告あり<BR>
     */
    public String alertFlag;
    
    /**
     * @@roseuid 45C087610014
     */
    public WEB3AdminAccInfoCampaignIndiviCompleteResponse() 
    {
     
    }
    
    /**
     * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignIndiviCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
