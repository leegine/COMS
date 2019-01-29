head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ登録顧客照会ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCampaignRegistAccListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ登録顧客照会ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報 手数料割引キャンペーン登録顧客照会レスポンス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignRegistAccList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312047L;
    
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
     * 手数料割引キャンペーン登録顧客情報
     */
    public WEB3AccInfoCampaignRegistAccountInfo registAccountInfo[];
    
    /**
     * @@roseuid 45C087620294
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse() 
    {
     
    }
    
    /**
     * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
