head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス詳細レスポンス(WEB3AdminSrvRegiServiceDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
Revesion History : 2007/06/06 張騰宇 (中訊) モデル256
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス詳細レスポンス)<BR>
 * サービス利用管理者サービス詳細レスポンスクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (摘要)
     */
    public String summary;
    
    /**
     * (申込区分)<BR>
     * 0:不要　@1:要<BR>
     */
    public String applyDiv;
    
    /**
     * (電子鳩設定区分)<BR>
     * true:設定有　@false:設定無<BR>
     */
    public boolean elePigeonDiv;
    
    /**
     * (ステータス)<BR>
     * 0:停止中　@1:提供中（申込不可）　@2:提供中<BR>
     */
    public String serviceStatus;
    
    /**
     * (利用期間料金情報)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;
    
    /**
     * (試用期間単位区分)<BR>
     * 1:年　@2:月　@3:日<BR>
     * （試用期間が無い場合、null）<BR>
     */
    public String trialDiv;
    
    /**
     * (試用期間)<BR>
     * （試用期間が無い場合、null）<BR>
     */
    public String trialPeriod;
    
    /**
     * (申込可能期間（自）)
     */
    public String applyAbleStartDate;
    
    /**
     * (申込可能期間（至）)
     */
    public String applyAbleEndDate;
    
    /**
     * (同意書文言)
     */
    public String consentSentence;
    
    /**
     * (サービス内容)
     */
    public String serviceContent;
    
    /**
     * (サービス説明URL)
     */
    public String explainURL;
    
    /**
     * (メール区分（確認メール）)<BR>
     * 0:送信しない　@1:送信する<BR>
     */
    public String confirmMailDiv;
    
    /**
     * (差出人（確認メール）)
     */
    public String confirmMailFrom;
    
    /**
     * (件名（確認メール）)
     */
    public String confirmMailSubject;
    
    /**
     * (メールヘッダー（確認メール）)
     */
    public String confirmMailHeader;
    
    /**
     * (メール本文（確認メール）)
     */
    public String confirmMailBody;
    
    /**
     * (メールフッター（確認メール）)
     */
    public String confirmMailFooter;
    
    /**
     * (メール区分（契約期限メール）)<BR>
     * 0:送信しない　@1:送信する<BR>
     */
    public String noticeMailDiv;
    
    /**
     * (メール送信日（契約期限メール）)
     */
    public String noticeMailDate;
    
    /**
     * (差出人（契約期限メール）)
     */
    public String noticeMailFrom;
    
    /**
     * (件名（契約期限メール）)
     */
    public String noticeMailSubject;
    
    /**
     * (メールヘッダー（契約期限メール）)
     */
    public String noticeMailHeader;
    
    /**
     * (メール本文（契約期限メール）)
     */
    public String noticeMailBody;
    
    /**
     * (メールフッター（契約期限メール）)
     */
    public String noticeMailFooter;
    
    /**
     * (抽選区分)<BR>
     * 0:無　@1:有<BR>
     */
    public String lotteryDiv;
    
    /**
     * (募集期間情報)
     */
    public webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo[ ] applyInfo;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (提供形式)<BR>
     * NULL：条件付けを行わない<BR> 
     * 0：無料提供のみ（条件達成時に無料提供、未達成時に未提供）<BR> 
     * 1：有料／無料提供（条件達成時に無料提供、未達成時に有料提供）<BR>
     */
    public String offerType;
    
    /**
     * (手数料条件基準合計額)
     */
    public String commissionAttainTotal;
    
    /**
     * (設定可能手数料条件)<BR>
     * 設定可能な手数料条件の一覧<BR>
     */
    public WEB3SrvRegiSetAbleCommissionCondition[] setAbleCommissionConditions;

    /**
     * (適用手数料条件)<BR>
     * 適用している手数料条件の一覧<BR>
     */
    public WEB3SrvRegiApplyCommissionCondition[] applyCommissionConditions;
    
    /**
     * (第２URL)<BR>
     */
    public String url2;
    
    /**
     * (ハッシュ計算方式区分)<BR>
     * 0:指定無    1:MD2    2:MD5    3:SHA-1    4:SHA-256<BR>
     * 5:SHA-384   6:SHA-512<BR>
     */
    public String hashCalHowToDiv;
    
    /**
     * (ハッシュ計算手順区分)<BR>
     * 0:指定無    1:電子鳩    2:通常計算（１）    3:通常計算（２）<BR>
     * 4:２段階計算 5:ログイン認証<BR>
     */
    public String hashCalOrderDiv;
    
    /**
     * (送信方法@区分)<BR>
     * 0:GET    1:POST    2:HTTP-REQUEST<BR>
     * 3:特殊（１）－リテラクレア証券 MULTEX 専用<BR>
     * 4:特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (送信パラメータ区分)<BR>
     * 0:無    1:有<BR>
     */
    public String sendParamDiv;
    
    /**
     * (暗号化顧客コード区分)<BR>
     * 0:無    1:有<BR>
     */
    public String cryptAccountCodeDiv;
    
    /**
     * (ハッシュ値一覧)<BR>
     */
    public WEB3SrvRegiExecKey[] hashList;
    
    /**
     * (送信パラメータ一覧)<BR>
     */
    public WEB3SrvRegiExecKey[] paramList;

    /**
     * (無料対象期間)<BR>
     */
    public String freeTargetPeriod;

    /**
     * (サービス利用管理者サービス詳細レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE4E110035
     */
    public WEB3AdminSrvRegiServiceDetailsResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiServiceDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
