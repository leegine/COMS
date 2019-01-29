head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾚｽﾎﾟﾝｽ
                       (WEB3AdminAccInfoCampaignIndiviListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾚｽﾎﾟﾝｽ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312044L;
    
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
     * 手数料割引キャンペーン条件情報
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo[];
    
    /**
     * @@roseuid 45C087620091
     */
    public WEB3AdminAccInfoCampaignIndiviListResponse() 
    {
     
    }
    
    /**
     * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListResponsee
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoCampaignIndiviListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
