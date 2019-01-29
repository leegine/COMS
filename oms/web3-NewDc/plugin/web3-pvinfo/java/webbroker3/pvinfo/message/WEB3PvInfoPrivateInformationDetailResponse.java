head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細レスポンス(WEB3PvInfoPrivateInformationDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李丁銀(中訊) 作成
Revesion History : 2006/09/11 張騰宇(中訊) 仕様変更モデル070
Revesion History : 2007/02/26 関博(中訊) 仕様変更モデル073
Revesion History : 2007/03/16 関博(中訊) 仕様変更モデル076
Revesion History : 2008/10/08 劉仁和(中訊) 仕様変更モデル109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細レスポンス)<BR>
 * プライベートインフォメーション詳細レスポンスクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationDetailResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_privateInformationDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (表示内容ID)<BR>
     * 表示内容ID<BR>
     */
    public String displayContentsId;
    
    /**
     * (表示タイトル)<BR>
     * 表示タイトル<BR>
     */
    public String displayTitle;
    
    /**
     * (最終更新日時)<BR>
     * 最終更新日時<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (URLリンク)<BR>
     * URLリンク<BR>
     */
    public String urlLink;
    
    /**
     * (表示文章)<BR>
     * 表示文章<BR>
     */
    public String displayMessage;
    
    /**
     * (立替金情報)<BR>
     */
    public WEB3PvInfoAdvanceUnit[] advanceUnits;
    
    /**
     * (決済期限間近の建玉情報[])<BR>
     */
    public WEB3PvInfoSettleContractUnit[] settleContractUnits;
    
    /**
     * (出金停止情報)<BR>
     * 出金停止情報<BR>
     */
    public WEB3PvInfoCashoutStopUnit cashoutStopUnit;

    /**
     * (手数料割引キャンペーン情報)<BR>
     * 手数料割引キャンペーン情報<BR>
     */
    public WEB3PvInfoCommissionCampaignUnit[] commissionCampaignUnits;

    /**
     * (不足金発生表示情報)<BR>
     * 不足金発生表示情報<BR>
     */
    public WEB3PvInfoShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (第一水準追証表示情報)<BR>
     * 第一水準追証表示情報<BR>
     */
    public WEB3PvInfoFirstAdditionalInfo firstAdditionalInfo;

    /**
     * (第二水準追証表示情報 )<BR>
     * 第二水準追証表示情報 <BR>
     */
    public WEB3PvInfoSecondAdditionalInfo secondAdditionalInfo;
    
    /**
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3PvInfoPrivateInformationDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
