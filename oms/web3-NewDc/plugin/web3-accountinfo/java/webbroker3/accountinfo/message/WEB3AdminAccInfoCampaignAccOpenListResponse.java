head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾚｽﾎﾟﾝｽ
                       (WEB3AdminAccInfoCampaignAccOpenListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾚｽﾎﾟﾝｽ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312036L;
    
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
     * 口座開設条件指定情報
     */
    public WEB3AccInfoCampaignInfo accopenConditionInfo[];
    
    /**
     * @@roseuid 45C087600266
     */
    public WEB3AdminAccInfoCampaignAccOpenListResponse() 
    {
     
    }
    
    /**
     * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignAccOpenListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
