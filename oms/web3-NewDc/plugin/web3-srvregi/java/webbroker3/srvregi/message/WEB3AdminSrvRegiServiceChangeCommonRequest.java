head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更共通リクエスト(WEB3AdminSrvRegiServiceChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2007/06/06 肖志偉 仕様変更モデル251
Revesion History : 2007/06/13 金傑 仕様変更モデル262
Revesion History : 2007/06/29 崔遠鵬 仕様変更モデル278
Revesion History : 2007/12/03 于瀟 仕様変更モデル306、307
Revesion History : 2007/12/04 金傑 仕様変更モデル308
Revesion History : 2009/05/27 柴双紅(中訊) 仕様変更モデル423
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUsePeriodDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者サービス変更共通リクエスト)<BR>
 * サービス利用管理者サービス変更共通リクエストクラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeCommonRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceChangeCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151451L;

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
     * (ステータス)<BR>
     * 0:停止中　@1:提供中（申込不可）　@2:提供中<BR>
     */
    public String serviceStatus;

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
     * (抽選区分)<BR>
     * 0:無　@1:有<BR>
     */
    public String lotteryDiv;

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
     * (メール区分（契約期限メール）)<BR>
     * 0:送信しない　@1:送信する<BR>
     */
    public String noticeMailDiv;

    /**
     * (メール送信日（契約期限メール）)
     */
    public String noticeMailDate;

    /**
     * (募集期間情報)
     */
    public WEB3SrvRegiLotteryInfo[] applyInfo;

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
     * (適用手数料条件)<BR>
     * 適用している手数料条件の一覧<BR>
     */
    public WEB3SrvRegiApplyCommissionCondition[] applyCommissionConditions;

    /**
     * (第２URL)
     */
    public String url2;

    /**
     * (ハッシュ計算方式区分)<BR>
     * 0:指定無 <BR>
     * 1:MD2 <BR>
     * 2:MD5 <BR>
     * 3:SHA-1 <BR>
     * 4:SHA-256 <BR>
     * 5:SHA-384 <BR>
     * 6:SHA-512<BR>
     */
    public String hashCalHowToDiv;

    /**
     * (ハッシュ計算手順区分)<BR>
     * 0:指定無 <BR>
     * 1:電子鳩 <BR>
     * 2:通常計算（１） <BR>
     * 3:通常計算（２）<BR>
     * 4:２段階計算<BR>
     * 5:ログイン認証<BR>
     * 7:シングルサインオン連携<BR>
     */
    public String hashCalOrderDiv;

    /**
     * (送信方法@区分)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST <BR>
     * 3:特殊（１）－リテラクレア証券 MULTEX 専用 <BR>
     * 4:特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     */
    public String sendHowToDiv;

    /**
     * (送信パラメータ区分)<BR>
     * 0:無 <BR>
     * 1:有<BR>
     */
    public String sendParamDiv;

    /**
     * (暗号化顧客コード区分)<BR>
     * 0:無 <BR>
     * 1:有<BR>
     */
    public String cryptAccountCodeDiv;

    /**
     * (ハッシュ値一覧)
     */
    public WEB3SrvRegiExecKey[] hashList;

    /**
     * (送信パラメータ一覧)
     */
    public WEB3SrvRegiExecKey[] paramList;

    /**
     * (無料対象期間)<BR>
     * 無料対象期間
     */
    public String freeTargetPeriod;

    /**
     * (サービス利用管理者サービス変更共通リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F25765033B
     */
    public WEB3AdminSrvRegiServiceChangeCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.申込区分=="要"であり、this.サービス区分の桁数!=2桁の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00880<BR>
     *  1-3) this.申込区分=="不要"であり、this.サービス区分の桁数!=4桁の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00881<BR>
     *  1-4) this.サービス区分に数値以外が格納されている場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00882<BR>
     * <BR>
     * 2) サービス名称のチェック<BR>
     *  2-1) this.サービス名称==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00883<BR>
     *  2-2) 1Byte≦this.サービス名称の桁数≦100Byteではない場合、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00884<BR>
     * <BR>
     * 3) 摘要のチェック<BR>
     *  3-1) this.申込区分=="要"であり、かつthis.摘要==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00885<BR>
     *  3-2) this.申込区分=="不要"であり、かつthis.摘要!=nullの場合、<BR>
     *  例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00886<BR>
     *  3-3) this.摘要に半角カナ以外がセットされている場合、例外をスローする。(*1)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00887<BR>
     *  3-4) かつthis.摘要の桁数＞25文字の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00888<BR>
     * <BR>
     * 4) ステータスのチェック<BR>
     *  4-1) this.ステータス==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00889<BR>
     *  4-2) this.ステータスが以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"停止中"<BR>
     * 　@　@　@"提供中（申込不可）"<BR>
     * 　@　@　@"提供中"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * 5) 申込区分のチェック<BR>
     *  5-1) this.申込区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00891<BR>
     *  5-2) this.申込区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"不要"<BR>
     * 　@　@　@"要"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00892<BR>
     * <BR>
     * 6) 抽選区分のチェック<BR>
     *  6-1) this.申込区分=="要"であり、かつthis.抽選区分==nullの場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00895<BR>
     *  6-2) this.申込区分=="要"であり、かつthis.抽選区分が以下の値以外の場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@"無"<BR>
     * 　@　@　@"有"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00896<BR>
     * <BR>
     * 7) 利用期間料金情報のチェック<BR>
     * 　@this.利用期間料金情報の件数分、以下をチェックする。<BR>
     * 7-1) this.ステータス!="停止中"であり、this.抽選区分="無"であり、<BR>
     * 　@　@this.利用期間料金情報=nullまたは要素数=0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01827<BR>
     * <BR>
     * 　@（nullの場合、チェックしない）<BR>
     *  7-2) this.利用期間料金情報.利用期間単位区分==nullの場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00897<BR>
     *  7-3) this.利用期間料金情報.利用期間==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00898<BR>
     *  7-4) this.利用期間料金情報.利用期間の桁数==0の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00899<BR>
     *  7-5) this.利用期間料金情報.利用期間の桁数＞2桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00905<BR>
     *  7-6) this.利用期間料金情報.利用料金==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00906<BR>
     *  7-7) this.利用期間料金情報.利用料金の桁数＞9桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00907<BR>
     * <BR>
     * 8) 試用期間単位区分、試用期間のチェック<BR>
     *  8-1) this.抽選区分!="無"であり、かつthis.試用期間!=nullの場合であり<BR>
     * 　@　@かつthis.試用期間単位区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00910<BR>
     *  8-2) this.抽選区分=="無"であり、this.試用期間!=nullであり、かつ試用期間に数値以外が設定されている場合、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00912<BR>
     *  8-3) this.抽選区分=="無"であり、this.試用期間≦0の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00913<BR>
     *  8-4) this.抽選区分=="無"であり、this.試用期間＞2桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00914<BR>
     *  8-5) this.抽選区分=="無"であり、this.試用期間単位区分!=nullかつ<BR>
     * 　@　@this.試用期間!=nullであり、かつthis.試用期間単位区分に以下の値以外がセットされている場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@"年"<BR>
     * 　@　@　@"月"<BR>
     * 　@　@　@"日"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00915<BR>
     * <BR>
     * 9) 申込可能期間（自-至）のチェック<BR>
     *  9-1) this.抽選区分!="無"であり、かつthis.申込可能期間（自）==nullである場合、<BR>
     * 　@　@かつthis.申込可能期間（至）!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00916<BR>
     *  9-2) this.抽選区分!="無"であり、かつthis.申込可能期間（自）!=nullである場合、<BR>
     * 　@　@かつthis.申込可能期間（至）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00916<BR>
     *  9-3) this.申込可能期間（自）!=nullであり、かつ申込可能期間（至）!=nullであり、<BR>
     * 　@　@かつthis.申込可能期間（自）＞申込可能期間（至）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00918<BR>
     *  9-4) this.申込可能期間（自）==null以外であり、かつ値が数値以外の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00919<BR>
     *  9-5) this.申込可能期間（至）==null以外であり、かつ値が数値以外の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00920<BR>
     *  9-6) this.申込可能期間（自）==null以外であり、かつ値＞0ではない場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00921<BR>
     *  9-7) this.申込可能期間（至）==null以外であり、かつ値＞0ではない場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00922<BR>
     * <BR>
     * 10) 無料対象期間のチェック <BR>
     * 10-1) 無料対象期間!=nullの場合、以下のチェックを行う。 <BR>
     *  10-1-1) this.無料対象期間に数値以外が格納されている場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02802<BR>
     *  10-1-2) this.無料対象期間＞2桁の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02803<BR>
     *  10-1-3) this.無料対象期間＜'1' の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02822<BR>
     *  <BR>
     * 10-2) 10-2) this.提供形式="無料提供のみ(ウツミ屋)"）又は、this.提供形式="有料／無料提供(ウツミ屋)"の場合、
     * 以下のチェックを行う。<BR> 
     *  10-2-1) 無料対象期間 ==null の場合、例外をスローする。<BR>
     *  <BR>
     * 11) 同意書文言のチェック<BR>
     * 11-1) this.申込区分=="要" && this.ステータス!="停止中"であり、<BR>
     *       this.同意書文言=nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01860<BR>
     * 11-2) this.申込区分=="不要"であり、this.同意書文言!=nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00923<BR>
     * 12) サービス内容のチェック<BR>
     * 　@this.申込区分=="不要"であり、this.サービス内容!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00924<BR>
     * <BR>
     * 13) サービス説明（URL）のチェック<BR>
     * 　@this.申込区分=="不要"であり、this.サービス説明（URL）!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00925<BR>
     * <BR>
     * 14) メール区分（確認メール）のチェック<BR>
     *  14-1) this.申込区分=="要"であり、this.メール区分（確認メール）==nullの場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00926<BR>
     *  14-2) this.メール区分（確認メール）が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"送信しない"<BR>
     * 　@　@　@"送信する"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00927<BR>
     * <BR>
     * 15) メール区分（契約期限メール）のチェック<BR>
     *  15-1) this.申込区分=="要"であり、this.メール区分（契約期限メール）==nullの場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00928<BR>
     *  15-2) this.メール区分（契約期限メール）が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"送信しない"<BR>
     * 　@　@　@"送信する"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00929<BR>
     * <BR>
     * 16) メール送信日（契約期限メール）のチェック<BR>
     * 　@this.メール区分（契約期限メール）=="送信する"であり、かつ<BR>
     * 　@this.メール送信日（契約期限メール）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00930<BR>
     * <BR>
     * 17) 募集期間情報のチェック<BR>
     * 　@this.募集期間情報の件数分、以下のチェックを行う。<BR>
     * 17-1) this.ステータス!="停止中"であり、this.抽選区分="有"であり、<BR>
     *　@　@this.募集期間情報=nullまたは要素数=0件の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01829<BR>
     *　@<BR>
     * 　@（this.ステータス!="停止中"の場合、またはnullの場合、行わない）<BR>
     *  17-2) this.抽選区分=="無"であり、this.募集期間情報!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00931<BR>
     *  17-3) this.運用区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00932<BR>
     *  17-4) this.運用区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"無条件当選"<BR>
     * 　@　@　@"通常運用（抽選有）"<BR>
     * 　@　@　@"通常運用（抽選有オークション）"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00933<BR>
     *  17-5) this.募集期間情報の申込期間（自）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00934<BR>
     *  17-6) this.募集期間情報の申込期間（至）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00935<BR>
     *  17-7) this.募集期間情報の適用開始日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00936<BR>
     *  17-8) this.募集期間情報の適用終了日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00937<BR>
     *  17-9) this.募集期間情報の出金日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00938<BR>
     *  17-10) this.募集期間情報.募集枠==null以外であり、かつ数値以外がセットされている<BR>
     * 　@　@場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00939<BR>
     * <BR>
     *  17-11) this.募集期間情報.募集枠の桁数＞7桁の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01861<BR>
     *  17-12) this.募集期間情報.運用区分が"無条件当選"または"通常運用（抽選有）"<BR>
     * 　@　@であり、かつthis.募集期間情報.募集枠!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00940<BR>
     * <BR>
     *  17-13) this.募集期間情報.運用区分!="無条件当選" &&、<BR>
     * 　@　@this.募集期間情報.募集枠==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00941<BR>
     * <BR>
     *  17-14) this.抽選区分=="有"であり、かつthis.募集期間情報の利用料金==nullの<BR>
     * 　@　@場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00942<BR>
     * <BR>
     *  17-15) this.募集期間情報.運用区分=="無条件当選"<BR>
     * 　@　@であり、かつthis.募集期間情報.入札単位==null以外の場合、例外をスローする。<BR>
     * 【サービス利用】仕様変更管理台帳 No:107
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00943<BR>
     * <BR>
     *  17-16) this.募集期間情報.運用区分=="通常運用（抽選有オークション）"であり、<BR>
     * 　@　@かつthis.募集期間情報.入札単位==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00944<BR>
     * <BR>
     *  17-17) this.募集期間情報の出金日≦this.募集期間情報の抽選日の場合、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00947<BR>
     * <BR>
     *  17-18) this.募集期間情報の申込期間（自）＞this.募集期間情報の申込期間（至）の場合、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00948<BR>
     * <BR>
     *  17-19) this.募集期間情報の適用開始日＞this.募集期間情報の適用終了日の場合、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00949<BR>
     * <BR>
     *  17-20) this.募集期間情報の抽選日がnullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00950<BR>
     *  17-21) this.募集期間情報の申込期間（至）≧this.募集期間情報.抽選日の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00951<BR>
     *  17-22) this.募集期間情報の適用開始日＜this.募集期間情報.抽選日の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00952<BR>
     * 18) 提供形式のチェック <BR>
     *　@this.提供形式!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     *　@　@　@"通常提供" <BR>
     *　@　@　@"無料提供" <BR>
     *　@　@　@"無料提供のみ(ウツミ屋)"<BR>
     *　@　@　@"有料／無料提供(ウツミ屋)"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01180<BR>
     *<BR>
     *  19) 手数料条件基準合計額のチェック<BR>
     *　@19-1) this.手数料条件基準合計額!=nullであり、<BR>
     *　@　@　@かつ値が数値以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01181<BR>
     *　@19-2) this.提供形式!=nullであり、かつthis.手数料条件基準合計額=nullの場合、<BR>
     *　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01182<BR>
     *　@19-3) this.提供形式!=nullであり、かつthis.手数料条件基準合計額≦0の場合、<BR>
     *　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01183<BR>
     *<BR>
     *  20) 適用手数料条件のチェック<BR>
     *  20-1) this.ステータス!="停止中"であり、かつthis.提供形式!=nullであり、<BR>
     *        かつthis.適用手数料条件=nullまたは要素数=0の場合、<BR>
     *        またはthis.ステータス!="停止中"であり、かつthis.提供形式!=nullであり、<BR>
     *        this.適用手数料条件の無効区分が全て"無効"の場合、<BR>
     *     例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01184<BR>
     *　@20-2) this.ステータス!="停止中"であり、かつthis.提供形式=nullであり、<BR>
     *　@　@    かつthis.適用手数料条件!=nullかつ要素数>0であり、<BR>
     *　@　@    かつthis.適用手数料条件の無効区分が全て"無効"ではない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01185<BR>
     *　@20-3) this.適用手数料条件の件数分、以下のチェックを行う。<BR>
     *　@ 20-3-1) this.適用手数料条件.商品分類区分=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01186<BR>
     * 21) ステータス、利用期間料金情報、募集期間情報のチェック <BR>
     *　@21-1) this.ステータス!="停止中" && this.抽選区分="無" &&<BR> 
     * 　@　@　@(this.提供形式!="無料提供のみ" 又は、<BR>
     * 　@　@　@this.提供形式!="無料提供のみ(ウツミ屋)"）の場合<BR>
     *　@ 21-1-1) this.利用期間料金情報==0件またはnullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01287<BR>
     *　@ 21-1-2) this.利用期間料金情報の全ての無効区分=="無効"の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01288<BR>
     * 21-2) this.ステータス!="停止中" && <BR>
     * 　@　@　@this.抽選区分="無" && （this.提供形式=="無料提供のみ" 又は、<BR>
     * 　@　@　@this.提供形式=="無料提供のみ(ウツミ屋)"）の場合<BR>
     *　@ 21-2-1) (this.利用期間料金情報>0件 or nullではない) &&<BR>
     * 　@　@　@　@　@(this.利用期間料金情報の無効区分が１つでも"有効")の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01906<BR>
     * 21-3) this.ステータス!="停止中" && this.抽選区分="有"の場合<BR>
     * 　@21-3-1) this.募集期間情報==0件 or nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01289<BR>
     * 　@21-3-2) this.募集期間情報の全ての無効区分=="無効"の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01290<BR>
     * 22) ハッシュ計算方式区分のチェック <BR>
     * 22-1) this.ハッシュ計算方式区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01841<BR>
     * 22-2) this.ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・指定無 <BR>
     * 　@　@・MD2 <BR>
     * 　@　@・MD5 <BR>
     * 　@　@・SHA-1 <BR>
     * 　@　@・SHA-256 <BR>
     * 　@　@・SHA-384 <BR>
     * 　@　@・SHA-512<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01842<BR>
     * <BR>
     * 23) ハッシュ計算手順区分のチェック <BR>
     * 23-1) this.ハッシュ計算手順区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01843<BR>
     * 23-2) this.ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・指定無 <BR>
     * 　@　@・電子鳩 <BR>
     * 　@　@・通常計算（１） <BR>
     * 　@　@・通常計算（２）<BR>
     * 　@　@・２段階計算<BR>
     * 　@　@・ログイン認証<BR>
     * 　@　@・シングルサインオン連携<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01844<BR>
     * <BR>
     * 24) 送信方法@区分のチェック <BR>
     * 24-1) this.送信方法@区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01845<BR>
     * 24-2) this.送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・GET <BR>
     * 　@　@・POST <BR>
     * 　@　@・HTTP-REQUEST <BR>
     * 　@　@・特殊（１）－リテラクレア証券 MULTEX 専用 <BR>
     * 　@　@・特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01846<BR>
     * <BR>
     * 25) 送信パラメータ区分のチェック <BR>
     * 25-1) this.送信パラメータ区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01847<BR>
     * 25-2) this.送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・無 <BR>
     * 　@　@・有<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01848<BR>
     * <BR>
     * 26) 暗号化顧客コード区分のチェック <BR>
     * 26-1) this.暗号化顧客コード区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01849<BR>
     * 26-2) this.暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・無 <BR>
     * 　@　@・有<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01850<BR>
     * <BR>
     * 27) ハッシュ値一覧のチェック <BR>
     * 　@ハッシュ値一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR>
     * 27-1) ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01851<BR>
     * 27-2) ハッシュ値一覧.利用キー==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01852<BR>
     * <BR>
     * 28) 送信パラメータ一覧のチェック <BR>
     * 　@送信パラメータ一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR>
     * 28-1) 送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01853<BR>
     * 28-2) 送信パラメータ一覧.利用キー==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01854<BR>
     * <BR>
     * 29) ハッシュ計算方式区分、ハッシュ計算手順区分のチェック <BR>
     * 29-1) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ計算手順区分="指定無"<BR>
     * 　@　@の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * 29-2) this.ハッシュ計算方式区分="指定無"であり、かつthis.ハッシュ計算手順区分!="指定無"<BR>
     * 　@　@の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * <BR>
     * 30) ステータス、ハッシュ計算方式区分、ハッシュ値一覧のチェック <BR>
     * 　@this.ステータス!="停止中"の場合にのみ、以下のチェックを実施する。 <BR>
     * 30-1) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ値一覧=null<BR>
     * 　@　@または要素数==0件の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01856<BR>
     * 30-2) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ値一覧の全ての<BR>
     * 　@　@無効区分="無効"の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01857<BR>
     * 30-3) this.送信パラメータ区分="有"であり、かつthis.送信パラメータ一覧=null<BR>
     * 　@　@または要素数==0件の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01858<BR>
     * 30-4) this.送信パラメータ区分="有"であり、かつthis.送信パラメータ一覧の全ての<BR>
     * 　@　@無効区分="無効"の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01859<BR>
     * <BR>
     * 31) 提供形式、利用期間料金情報、試用期間のチェック<BR>
     *
     * 31-1) this.ステータス!="停止中" && <BR>
     *     (this.提供形式="無料提供のみ" 又は、this.提供形式="無料提供のみ(ウツミ屋)") &&<BR>
     *     this.利用期間料金情報!=null && <BR>
     *     利用期間料金情報の要素数＞0件 &&<BR>
     *     this.利用期間料金情報の全ての無効区分が"無効"ではなかった場合、<BR>
     *     例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01906<BR>
     * 31-2)  this.ステータス!="停止中" &&<BR>
     * 　@　@　@(this.提供形式=="無料提供のみ" 又は、<BR>
     * 　@　@　@this.提供形式=="無料提供のみ(ウツミ屋)"）&&<BR>
     * 　@　@　@(this.試用期間単位区分!=null or this.試用期間!=null)<BR>
     * 　@　@　@の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01908<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01909<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4DC640008
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) サービス区分のチェック
        // 1-1) this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1) ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 1-2) this.申込区分=="要"であり、this.サービス区分の桁数!=2桁の場合
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1-3) this.申込区分=="不要"であり、this.サービス区分の桁数!=4桁の場合、
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 4)
        {
            log.debug("1-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1-4) this.サービス区分に数値以外が格納されている場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.serviceDiv))
        {
            log.debug("1-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + STR_METHOD_NAME);
        }

        //2) サービス名称のチェック
        //2-1) this.サービス名称==nullの場合、例外をスローする。
        if (this.serviceName == null || "".equals(serviceName.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00883,
                getClass().getName() + STR_METHOD_NAME);
        }

        //2-2) 1Byte≦this.サービス名称の桁数≦100Byteではない場合、例外をスローする
        if (this.serviceName.length() < 1 ||
            WEB3StringTypeUtility.getByteLength(this.serviceName) > 100)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00884,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3) 摘要のチェック
        //3-1) this.申込区分=="要"であり、かつthis.摘要==nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.summary == null || "".equals(summary.trim())))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00885,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-2) this.申込区分=="不要"であり、かつthis.摘要!=nullの場合、
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            this.summary != null && summary.trim().length() != 0)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00886,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-3) this.摘要に半角カナ以外がセットされている場合、例外をスローする。
        if (this.summary != null && summary.trim().length() != 0 && !WEB3StringTypeUtility.is1byteKanaString(this.summary))
        {
            log.debug("3-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00887,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-4) かつthis.摘要の桁数＞25文字の場合、例外をスローする。
        if (this.summary != null && this.summary.length() > 25)
        {
            log.debug("3-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00888,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) ステータスのチェック
        //4-1) this.ステータス==nullの場合、例外をスローする。
        if (this.serviceStatus == null || "".equals(serviceStatus.trim()))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4-2) this.ステータスが以下の値以外の場合、例外をスローする。
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            !WEB3SrvStatusDef.PROVIDING_APPLI_IMPOSSIBLE.equals(this.serviceStatus) &&
            !WEB3SrvStatusDef.PROVIDING.equals(this.serviceStatus))
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 5) 申込区分のチェック
        //5-1) this.申込区分==nullの場合、例外をスローする。
        if (this.applyDiv == null || "".equals(applyDiv.trim()))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00891,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5-2) this.申込区分が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv))
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00892,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) 抽選区分のチェック
        //6-1) this.申込区分=="要"であり、かつthis.抽選区分==nullの場合、
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.lotteryDiv == null || "".equals(lotteryDiv.trim())))
        {
            log.debug("6-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00895,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6-2) this.申込区分=="要"であり、かつthis.抽選区分が以下の値以外の場合、
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            log.debug("6-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                getClass().getName() + STR_METHOD_NAME);
        }

        //7) 利用期間料金情報のチェック
        //this.利用期間料金情報の件数分、以下をチェックする。
        //((this.利用期間料金情報=null or 要素数=0)の場合はチェックしない）
        if(this.chargeInfo != null && this.chargeInfo.length != 0)
        {
            int l_intLength = chargeInfo.length;
            for (int i = 0; i < l_intLength; i++)
            {
                log.debug("利用期間料金情報のチェック");
                //仕様変更 NO_197
                //7-0) this.利用期間料金情報.無効区分=="無効"の場合は、以下の処理をスッキプしfor文を実行する。
                if (chargeInfo[i].invalidDiv)
                {
					log.debug("7-0) chargeInfo["+i+"] 無効区分＝無効　@以下の処理をスキップ");
                	continue;
                }
                
                //7-1) this.利用期間料金情報.利用期間単位区分==nullの場合、
                if (chargeInfo[i].chargeDiv == null || "".equals(chargeInfo[i].chargeDiv.trim()))
                {
                    log.debug("7-1)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00897,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-2) this.利用期間料金情報.利用期間==nullの場合、例外をスローする
                if (chargeInfo[i].chargePeriod == null || "".equals(chargeInfo[i].chargePeriod.trim()))
                {
                    log.debug("7-2)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00898,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-3) this.利用期間料金情報.利用期間の桁数==0の場合、例外をスローする。
                if (chargeInfo[i].chargePeriod.length() == 0)
                {
                    log.debug("7-3)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00899,
                        getClass().getName() + STR_METHOD_NAME);
                }
                
                //障害対応 NO_01994
				//7-3-1) this.利用期間料金情報.利用期間＜１の場合、例外をスローする。
				if (!WEB3StringTypeUtility.isNumber(chargeInfo[i].chargePeriod) ||
					 Integer.parseInt(chargeInfo[i].chargePeriod) < 1)
				{
					log.debug("7-3-1)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00897,
						getClass().getName() + STR_METHOD_NAME);
				}

                //7-4) this.利用期間料金情報.利用期間の桁数＞2桁の場合、例外をスローする。
                if (chargeInfo[i].chargePeriod.length() > 2)
                {
                    log.debug("7-4)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00905,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-5) this.利用期間料金情報.利用料金==nullの場合、例外をスローする。
                if (chargeInfo[i].chargeAmt == null || "".equals(chargeInfo[i].chargeAmt.trim()))
                {
                    log.debug("7-5)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00906,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-6) this.利用期間料金情報.利用料金の桁数＞9桁の場合、例外をスローする
                if (chargeInfo[i].chargeAmt.length() > 9)
                {
                    log.debug("7-6)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00907,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //8) 試用期間単位区分、試用期間のチェック<BR>
        //8-1) this.抽選区分=="無"であり、かつthis.試用期間!=nullの場合であり
        //かつthis.試用期間単位区分==nullの場合、例外をスローする
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.trialPeriod != null && trialPeriod.trim().length() != 0) &&
            (this.trialDiv == null || "".equals(trialDiv.trim())))
        {
            log.debug("8-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00910,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-2) this.抽選区分=="無"であり、this.試用期間!=nullであり、かつthis.試用期間に数値以外が設定されている場合、
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && !WEB3StringTypeUtility.isNumber(this.trialPeriod))
        {
            log.debug("8-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00912,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-3) this.抽選区分=="無"であり、this.試用期間≦0の場合、例外をスローする
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && Integer.parseInt(this.trialPeriod) <= 0)
        {
            log.debug("8-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00913,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-4) this.抽選区分=="無"であり、this.試用期間＞2桁の場合、例外をスローする。
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && this.trialPeriod.length() > 2)
        {
            log.debug("8-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00914,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-5) this.抽選区分=="無"であり、this.試用期間単位区分!=nullかつ<
        //this.試用期間!=nullであり、かつthis.試用期間単位区分に以下の値以外がセットされている場合
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.trialDiv != null && trialDiv.trim().length() != 0) &&
            (this.trialPeriod != null && trialPeriod.trim().length() != 0) &&
            !WEB3SrvUsePeriodDivDef.YEAR.equals(this.trialDiv) &&
            !WEB3SrvUsePeriodDivDef.MONTH.equals(this.trialDiv) &&
            !WEB3SrvUsePeriodDivDef.DATE.equals(this.trialDiv))
        {
            log.debug("8-5)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00910,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9) 申込可能期間（自-至）のチェック
        //9-1) this.抽選区分!="無"であり、かつthis.申込可能期間（自）==nullである場合、
        //かつthis.申込可能期間（至）!=nullの場合、例外をスローする。
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.applyAbleStartDate == null || "".equals(applyAbleStartDate.trim())) &&
            (this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0))
        {
            log.debug("9-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-2) this.抽選区分!="無"であり、かつthis.申込可能期間（自）!=nullである場合、
        //かつthis.申込可能期間（至）==nullの場合、例外をスローする。<BR>
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            (this.applyAbleEndDate == null || "".equals(applyAbleEndDate.trim())))
        {
            log.debug("9-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-3) this.申込可能期間（自）!=nullであり、かつ申込可能期間（至）!=nullであり、
        //かつthis.申込可能期間（自）＞申込可能期間（至）の場合、例外をスローする。
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            (this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleStartDate) > Integer.parseInt(this.applyAbleEndDate))
        {
            log.debug("9-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-4) this.申込可能期間（自）==null以外であり、かつ値が数値以外の場合、
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.applyAbleStartDate))
        {
            log.debug("9-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00919,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-5) this.申込可能期間（至）==null以外であり、かつ値が数値以外の場合、
        if ((this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.applyAbleEndDate))
        {
            log.debug("9-5)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00920,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-6) this.申込可能期間（自）==null以外であり、かつ値＞0ではない場合、
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleStartDate) <= 0)
        {
            log.debug("9-6)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00921,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-7) this.申込可能期間（至）==null以外であり、かつ値＞0ではない場合、
        if ((this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleEndDate) <= 0)
        {
            log.debug("9-7)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00922,
                getClass().getName() + STR_METHOD_NAME);
        }

        //10) 無料対象期間のチェック
        //10-1) 無料対象期間!=nullの場合、以下のチェックを行う。 
        if (this.freeTargetPeriod != null)
        {
            // 10-1-1) this.無料対象期間に数値以外が格納されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.freeTargetPeriod))
            {
                log.debug("無料対象期間が数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02802,
                    getClass().getName() + STR_METHOD_NAME);
            }

            // 10-1-2) this.無料対象期間＞2桁の場合、例外をスローする。
            if (this.freeTargetPeriod.length() > 2)
            {
                log.debug("無料対象期間のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02803,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            // 10-1-3) this.無料対象期間＜'1' の場合、例外をスローする。
            if (Integer.parseInt(this.freeTargetPeriod) < 1)
            {
                log.debug("無料対象期間が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02822,
                    getClass().getName() + STR_METHOD_NAME); 
            }
        }
        
        //10-2) this.提供形式="無料提供のみ(ウツミ屋)"）又は、this.提供形式="有料／無料提供(ウツミ屋)"
		//の場合、以下のチェックを行う。 
		if (WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType) || 
			WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA.equals(this.offerType))
		{
			//10-2-1) 無料対象期間 ==null の場合、例外をスローする。
			if (this.freeTargetPeriod == null)
			{
				log.debug("無料対象期間を入力してください。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02802,
					getClass().getName() + STR_METHOD_NAME);

			}
		}

        //10) 同意書文言のチェック<BR>
        //10-1) this.申込区分=="要" && this.ステータス!="停止中"であり、this.同意書文言=nullの場合、例外をスローする
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            this.consentSentence == null)
        {
            log.debug("10)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01860,
                getClass().getName() + STR_METHOD_NAME,
                "ステータス!=停止中であり、this.同意書文言=nullの場合");
        }

		//10-2)
        //this.申込区分=="不要"であり、this.同意書文言!=nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.consentSentence != null && consentSentence.trim().length() != 0))
        {
            log.debug("10)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00923,
                getClass().getName() + STR_METHOD_NAME);
        }

        //11-1) サービス内容のチェック
        //this.申込区分=="不要"であり、this.サービス内容!=nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.serviceContent != null && serviceContent.trim().length() != 0))
        {
            log.debug("11)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00924,
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//仕様変更 NO_196
        //11-2) 
		//this.サービス内容!=nullであり、サービス内容のサイズが＞４０００の場合、例外をスローする。
		if (this.serviceContent != null && this.serviceContent.trim().length() != 0 &&
			WEB3StringTypeUtility.getByteLength(this.serviceContent) > 4000)
		{
			log.debug("11-2) 【例外発生！！】サービス内容 ＞ 4000byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        //12-1) サービス説明（URL）のチェック
        //this.申込区分=="不要"であり、this.サービス説明（URL）!=nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.explainURL != null && explainURL.trim().length() != 0))
        {
            log.debug("12)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00925,
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//仕様変更 NO_196
		//12-2) 
		//this.サービス説明(URL)!=nullであり、サービス説明(URL).length()が＞256の場合、例外をスローする。
		if (this.explainURL != null && this.explainURL.trim().length() != 0 &&
			WEB3StringTypeUtility.getByteLength(this.explainURL) > 256)
		{
			log.debug("12-2) 【例外発生！！】サービス説明(URL) ＞ 256byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        //13) メール区分（確認メール）のチェック<BR>
        //13-1) this.申込区分=="要"であり、this.メール区分（確認メール）==nullの場合
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.confirmMailDiv == null || "".equals(confirmMailDiv.trim())))
        {
            log.debug("13-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00926,
                getClass().getName() + STR_METHOD_NAME);
        }

        //13-2) this.メール区分（確認メール）が以下の値以外の場合、例外をスローする。
        if (this.confirmMailDiv != null &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.confirmMailDiv) &&
            !WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.confirmMailDiv))
        {
            log.debug("13-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00927,
                getClass().getName() + STR_METHOD_NAME);
        }

        //14) メール区分（契約期限メール）のチェック
        //14-1) this.申込区分=="要"であり、this.メール区分（契約期限メール）==nullの場合、
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.noticeMailDiv == null || "".equals(noticeMailDiv.trim())))
        {
            log.debug("14-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00928,
                getClass().getName() + STR_METHOD_NAME);
        }

        //14-2) this.メール区分（契約期限メール）が以下の値以外の場合、例外をスローする。
        if (this.noticeMailDiv != null &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.noticeMailDiv) &&
            !WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv))
        {
            log.debug("14-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00929,
                getClass().getName() + STR_METHOD_NAME);
        }

        //15) メール送信日（契約期限メール）のチェック
        //this.メール区分（契約期限メール）=="送信する"であり、かつ
        //this.メール送信日（契約期限メール）==nullの場合、例外をスローする。
        if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv) &&
            (this.noticeMailDate == null || "".equals(noticeMailDate.trim())))
        {
            log.debug("15)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00930,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 16) 募集期間情報のチェック
        //this.募集期間情報の件数分、以下のチェックを行う
        //(this.ステータス!="停止中" or this.ステータス != null)であり、かつ
        //募集期間情報 == null 以外の場合に行う。
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (this.serviceStatus != null && serviceStatus.trim().length() != 0) &&
             (applyInfo != null && applyInfo.length != 0))
        {
			log.debug("募集期間情報のチェック");
            int l_intLength = this.applyInfo.length;
            for (int i = 0; i < l_intLength; i++)
            {
				//仕様変更 NO_197
            	//16-0) this.無効区分=="無効"の場合、以下の処理をスキップさせてfor文を実行する。
            	if (applyInfo[i].invalidDiv)
            	{
					log.debug("16-0) applyInfo["+i+"] 無効区分＝無効　@以下の処理をスキップ");
            		continue;
            	}
            	
				//16-1) this.抽選区分=="無"であり、this.募集期間情報!=nullの場合、例外をスローする。
				if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
					this.applyInfo != null)
				{
					log.debug("16-1)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00931,
						getClass().getName() + STR_METHOD_NAME);
				}
            	
                //16-2) this.運用区分==nullの場合、例外をスローする。
                if (this.applyInfo[i].useDiv == null || "".equals(applyInfo[i].useDiv.trim()))
                {
                    log.debug("16-2)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00932,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-3) this.運用区分が以下の値以外の場合、例外をスローする。
                if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    !WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(this.applyInfo[i].useDiv) &&
                    !WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.applyInfo[i].useDiv))
                {
                    log.debug("16-3)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00933,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-4) this.募集期間情報の申込期間（自）==nullの場合、例外をスローする。
                if (this.applyInfo[i].applyStartDate == null)
                {
                    log.debug("16-4)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-5) this.募集期間情報の申込期間（至）==nullの場合、例外をスローする。
                if (this.applyInfo[i].applyEndDate == null)
                {
                    log.debug("16-5)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-6) this.募集期間情報の適用開始日==nullの場合、例外をスローする。
                if (this.applyInfo[i].trialStartDate == null)
                {
                    log.debug("16-6)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00936,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-7) this.募集期間情報の適用終了日==nullの場合、例外をスローする。
                if (this.applyInfo[i].trialEndDate == null)
                {
                    log.debug("16-7)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00937,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-8) this.募集期間情報.募集枠==null以外であり、かつ数値以外がセットされている場合
                if ((this.applyInfo[i].applyMax != null && applyInfo[i].applyMax.trim().length() != 0) &&
                    !WEB3StringTypeUtility.isNumber(this.applyInfo[i].applyMax))
                {
                    log.debug("16-8)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00939,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-9) this.募集期間情報.募集枠の桁数＞7桁の場合、例外をスローする。 <BR>
                if (this.applyInfo[i].applyMax != null && this.applyInfo[i].applyMax.length() > 7)
                {
                    log.debug("16-9)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01861,
                        getClass().getName() + STR_METHOD_NAME,
                        "募集期間情報.募集枠の桁数＞7桁の場合");
                }

                //16-10) this.募集期間情報.運用区分が"無条件当選"
                //であり、かつthis.募集期間情報.募集枠!=nullの場合、例外をスローする。
                if(WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].applyMax != null && applyInfo[i].applyMax.trim().length() != 0))
                {
                    log.debug("16-10)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00940,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-11) this.募集期間情報.運用区分!="無条件当選" &&
                //  this.募集期間情報.募集枠==nullの場合、例外をスローする。
                if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].applyMax == null || "".equals(applyInfo[i].applyMax.trim())))
                {
                    log.debug("16-11)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00941,
                        getClass().getName() + STR_METHOD_NAME);
                }
                
				//16-12) this.募集期間情報.運用区分!="無条件当選" && 
				//		 this.募集期間情報の抽選日がnullの場合、例外をスローする。
				//U00871 start
				//障害対応  NO_U01708
				if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
				   this.applyInfo[i].lotteryDate == null)
				{
					log.debug("16-12)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00950,
						getClass().getName() + STR_METHOD_NAME);
				}
				//U00871 end

                //16-13) this.抽選区分=="有"であり、かつthis.募集期間情報の利用料金==nullの場合
                //U00871 start
                if ((this.applyInfo[i].chargeAmt == null || "".equals(applyInfo[i].chargeAmt.trim())))
                {
                    log.debug("16-13)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                        getClass().getName() + STR_METHOD_NAME);
                }
                //U00871 end

                //16-14) this.募集期間情報.運用区分=="無条件当選" or "通常運用（抽選有）"
                //であり、かつthis.募集期間情報.入札単位==null以外の場合、例外をスローする。
                //サービス利用】仕様変更管理台帳 No:107
                if ((WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) ||
                    WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(this.applyInfo[i].useDiv)) &&
                    this.applyInfo[i].biddingPriceUnit != null && applyInfo[i].biddingPriceUnit.trim().length() != 0)
                {
                    log.debug("16-14)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00943,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-15) this.募集期間情報.運用区分=="通常運用（抽選有オークション）"であり、
                //かつthis.募集期間情報.入札単位==nullの場合、例外をスローする。
                if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].biddingPriceUnit == null || "".equals(applyInfo[i].biddingPriceUnit.trim())))
                {
                    log.debug("16-15)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00944,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-16) this.募集期間情報の出金日≦this.募集期間情報の抽選日の場合、
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].paymentDate, this.applyInfo[i].lotteryDate) <= 0)
                {
                    log.debug("16-16)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00947,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-17) this.募集期間情報の申込期間（自）＞this.募集期間情報の申込期間（至）の場合、
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].applyStartDate, this.applyInfo[i].applyEndDate) > 0)
                {
                    log.debug("16-17)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-18) this.募集期間情報の適用開始日＞this.募集期間情報の適用終了日の場合
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].trialStartDate, this.applyInfo[i].trialEndDate) > 0)
                {
                    log.debug("16-18)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00949,
                        getClass().getName() + STR_METHOD_NAME);
                }
				
				//障害対応 NO_U02019
				//障害対応 05/27無条件当選時の対応
				if (!(WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv)))
				{
					//16-19)this.募集期間情報の申込期間（至）>this.募集期間情報.抽選日の場合
					if (WEB3DateUtility.compareToSecond(this.applyInfo[i].applyEndDate, this.applyInfo[i].lotteryDate) > 0)
					{
						log.debug("16-19)");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00951,
							getClass().getName() + STR_METHOD_NAME);
					}

					//16-20) this.募集期間情報の適用開始日＜this.募集期間情報.抽選日の場合
					if (WEB3DateUtility.compareToSecond(this.applyInfo[i].trialStartDate, this.applyInfo[i].lotteryDate) < 0)
					{
						log.debug("16-20)");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00952,
							getClass().getName() + STR_METHOD_NAME);
					}
				}
            }
        }
        //* 17) 提供形式のチェック
        //this.提供形式!=nullであり、かつ以下の値以外の場合、例外をスローする。
        //  ・無料提供のみ  
        //　@・有料／無料提供  
        //　@・無料提供のみ(ウツミ屋) 
        //　@・有料／無料提供(ウツミ屋) 
        if ((this.offerType != null && offerType.trim().length() != 0) &&
            !WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA.equals(this.offerType))
        {
            log.debug("17)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01180,
                getClass().getName() + STR_METHOD_NAME);
        }

        //18) 手数料条件基準合計額のチェック
        //18-1) this.手数料条件基準合計額!=nullであり、かつ値が数値以外の場合、例外をスローする。
        if ((this.commissionAttainTotal != null && commissionAttainTotal.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.commissionAttainTotal))
        {
            log.debug("18-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01181,
                getClass().getName() + STR_METHOD_NAME);
        }
        //18-2) this.提供形式!=nullであり、かつthis.手数料条件基準合計額=nullの場合、
        if ((this.offerType != null && offerType.trim().length() != 0) && this.commissionAttainTotal == null)
        {
            log.debug("18-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01182,
                getClass().getName() + STR_METHOD_NAME);
        }
        //18-3) this.提供形式!=nullであり、かつthis.手数料条件基準合計額≦0の場合、
        if ((this.offerType != null && offerType.trim().length() != 0) && Integer.parseInt(this.commissionAttainTotal) <= 0)
        {
            log.debug("18-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01183,
                getClass().getName() + STR_METHOD_NAME);
        }

        //19) 適用手数料条件のチェック
        // 19-1) this.ステータス!="停止中"であり、かつthis.提供形式!=nullであり、
        //かつthis.適用手数料条件=nullまたは要素数=0の場合、
        //またはthis.ステータス!="停止中"であり、かつthis.提供形式!=nullであり、
        //this.適用手数料条件の無効区分が全て"無効"の場合、
        //例外をスローする。

        boolean l_blnIsInvaild = false;
        if (this.applyCommissionConditions != null)
        {
            int l_intCnt = this.applyCommissionConditions.length;
            if (l_intCnt > 0)
            {
                int l_intFlag = 0;
                for (int i = 0; i < l_intCnt; i++)
                {
                    if (this.applyCommissionConditions[i].invalidDiv)
                    {
                        l_intFlag++;
                    }
                }
                if (l_intFlag == l_intCnt)
                {
                    l_blnIsInvaild = true;
                }
            }
        }
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            (this.offerType != null && offerType.trim().length() != 0) &&
            ((this.applyCommissionConditions == null ||
            this.applyCommissionConditions.length == 0) ||
            l_blnIsInvaild))
        {
            log.debug("19-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01184,
                getClass().getName() + STR_METHOD_NAME);
        }
        //19-2) this.ステータス!="停止中"であり、かつthis.提供形式=nullであり、
        // かつthis.適用手数料条件!=nullかつ要素数>0であり、
        // かつthis.適用手数料条件の無効区分が全て"無効"ではない場合、例外をスローする。
        //WEB3-SRVREGI-A-CD-0131
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            (this.offerType == null || this.offerType.trim().length() == 0) &&
            (this.applyCommissionConditions != null && this.applyCommissionConditions.length > 0) &&
            !l_blnIsInvaild)
        {
            log.debug("19-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01185,
                getClass().getName() + STR_METHOD_NAME);
        }
        //19-3) this.適用手数料条件の件数分、以下のチェックを行う。
        //19-3-1) this.適用手数料条件.商品分類区分=nullの場合、例外をスローする。
        if (applyCommissionConditions != null)
        {
            int l_intLength = this.applyCommissionConditions.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (applyCommissionConditions[i].productKindDiv == null ||
                    applyCommissionConditions[i].productKindDiv.trim().length() == 0)
                {
                    log.debug("19-3-1)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01186,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //20) ステータス、利用期間料金情報、募集期間情報のチェック
        //20-1) this.ステータス!="停止中"であり、かつthis.抽選区分="無" && (this.提供形式!="無料提供のみ"
        //      又は、this.提供形式!="無料提供のみ(ウツミ屋)"）の場合 
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (!WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)))
        {
            //20-1-1) this.利用期間料金情報==0件またはnullの場合、例外をスローする。
            if (this.chargeInfo == null || this.chargeInfo.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01287,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //20-1-2) this.利用期間料金情報の全ての無効区分=="無効"の場合、例外をスローする。
            int l_int = this.chargeInfo.length;
            int l_intCnt = 0;
            for (int i = 0; i < l_int; i++)
            {
                if (this.chargeInfo[i].invalidDiv)
                {
                    l_intCnt = l_intCnt + 1;
                }
            }
            if (l_intCnt == l_int)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01288,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
        }
        //20-2) this.ステータス!="停止中"であり、かつthis.抽選区分="無" && (this.提供形式="無料提供のみ"
        //又は、this.提供形式="無料提供のみ(ウツミ屋)"）の場合
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)))
        {
			//20-2-1) this.利用期間料金情報>0件、かつnull以外の場合で、
			//this.利用期間料金情報の無効区分が一つでも有効の場合に例外をスローする。
			int l_intCnt = 0;
            if(this.chargeInfo != null){
                
                int l_int = this.chargeInfo.length;
                for (int i = 0; i < l_int; i++)
                {
                    if (!this.chargeInfo[i].invalidDiv)
                    {
                        l_intCnt = l_intCnt + 1;
                    }
                }
            }
            
			if ((this.chargeInfo != null && this.chargeInfo.length > 0) && l_intCnt != 0)
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01906,
					getClass().getName() + STR_METHOD_NAME);
			}
        }
        
        //20-3) this.ステータス!="停止中"であり、かつthis.抽選区分="有"の場合
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            //20-3-1) this.募集期間情報==0件またはnullの場合、例外をスローする。
            if (this.applyInfo == null || this.applyInfo.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01289,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //20-3-2) this.募集期間情報の全ての無効区分=="無効"の場合、例外をスローする。
            int l_int = this.applyInfo.length;
            int l_intCnt = 0;
            for (int i = 0; i < l_int; i++)
            {
                if (this.applyInfo[i].invalidDiv)
                {
                    l_intCnt = l_intCnt + 1;
                }
            }
            if (l_intCnt == l_int)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01290,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //* 21) ハッシュ計算方式区分のチェック <BR>
		//障害対応 NO_U02018
		
        //* 21-1) this.ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        //* 　@　@・指定無 <BR>
        //* 　@　@・MD2 <BR>
        //* 　@　@・MD5 <BR>
        //* 　@　@・SHA-1 <BR>
        //* 　@　@・SHA-256 <BR>
        //* 　@　@・SHA-384 <BR>
        //* 　@　@・SHA-512<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD2.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD5.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_1.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_256.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_384.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_512.equals(this.hashCalHowToDiv))
        {
            log.debug("ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01842,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算方式区分の値が不正");
        }

        //* 22) ハッシュ計算手順区分のチェック <BR>
		//障害対応 NO_U02018
		
        //22-1) this.ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        // * 　@　@・指定無 <BR>
        //* 　@　@・電子鳩 <BR>
        //* 　@　@・通常計算（１） <BR>
        //* 　@　@・通常計算（２）<BR>
        //* 　@　@・２段階計算<BR>
        //* 　@　@・ログイン認証 <BR>
        //*     ・シングルサインオン連携 <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(this.hashCalOrderDiv))
        {
            log.debug("ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01844,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算手順区分の値が不正");
        }

        //* 23) 送信方法@区分のチェック <BR>
		//障害対応 NO_U02018

        //* 23-1) this.送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        //* 　@　@・GET <BR>
        //* 　@　@・POST <BR>
        //* 　@　@・HTTP-REQUEST <BR>
        //* 　@　@・特殊（１）－リテラクレア証券 MULTEX 専用 <BR>
        //* 　@　@・特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.GET.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.POST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.HTTP_REQUEST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL2.equals(this.sendHowToDiv))
        {
            log.debug("送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01846,
                getClass().getName() + STR_METHOD_NAME,
                "送信方法@区分の値が不正");
        }

        //* 24) 送信パラメータ区分のチェック <BR>
		//障害対応 NO_U02018

        //* 24-1) this.送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        //* 　@　@・無 <BR>
        //* 　@　@・有<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
        {
            log.debug("送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01848,
                getClass().getName() + STR_METHOD_NAME,
                "送信パラメータ区分の値が不正");
        }

        //* 25) 暗号化顧客コード区分のチェック <BR>
		//障害対応 NO_U02018
		

        //* 25-1) this.暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        //* 　@　@・無 <BR>
        //* 　@　@・有<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.cryptAccountCodeDiv))
        {
            log.debug("暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01850,
                getClass().getName() + STR_METHOD_NAME,
                "暗号化顧客コード区分の値が不正");
        }

        //* 26) ハッシュ値一覧のチェック <BR>
        //* 　@ハッシュ値一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR>
        if (this.hashList != null && this.hashList.length > 0)
        {
            int l_int = this.hashList.length;
            for (int i = 0; i < l_int; i++)
            {
                //26-1) ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする。<BR>
                if (hashList[i].keyKindDiv == null)
                {
                    log.debug("ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01851,
                        getClass().getName() + STR_METHOD_NAME,
                        "ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする");
                }

                //26-2) ハッシュ値一覧.利用キー==nullの場合、例外をスローする。 <BR>
                if (hashList[i].key == null)
                {
                    log.debug("ハッシュ値一覧.利用キー==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01852,
                        getClass().getName() + STR_METHOD_NAME,
                        "ハッシュ値一覧.利用キー==nullの場合、例外をスローする");
                }
                
				//仕様変更 NO_196
				//26-3)ハッシュ値一覧.利用キーのバイト数が256バイト数以上の場合、例外をスローする。<BR>
				if ((hashList[i].key != null && hashList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(hashList[i].key) > 256)
				{
					log.debug("26-3) ハッシュ値一覧.利用キー＞256byte以上");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01831,
						getClass().getName() + STR_METHOD_NAME);
				}
            }

        }

        //* 27) 送信パラメータ一覧のチェック <BR>
        //* 　@送信パラメータ一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR>
        if (this.paramList != null && this.paramList.length > 0)
        {
            int l_int = this.paramList.length;
            for (int i = 0; i < l_int; i++)
            {
                //27-1) 送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする。 <BR>
                if (paramList[i].keyKindDiv == null)
                {
                    log.debug("送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01853,
                        getClass().getName() + STR_METHOD_NAME,
                        "送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする");
                }

                //27-2) 送信パラメータ一覧.利用キー==nullの場合、例外をスローする。<BR>
                if (paramList[i].key == null)
                {
                    log.debug("送信パラメータ一覧.利用キー==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01854,
                        getClass().getName() + STR_METHOD_NAME,
                        "送信パラメータ一覧.利用キー==nullの場合、例外をスローする");
                }
                
				//仕様変更 NO_196
				//27-3)送信パラメータ一覧.利用キーのバイト数が256バイト数以上の場合、例外をスローする。<BR>
				if ((paramList[i].key != null && paramList[i].key.trim().length() != 0) && 
					 WEB3StringTypeUtility.getByteLength(paramList[i].key) > 256)
				{
					log.debug("27-3) 送信パラメータ一覧.利用キー＞256byte以上");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01859,
						getClass().getName() + STR_METHOD_NAME);
				}
            }
        }

		//障害対応 NO_2018
        //28) ハッシュ計算方式区分、ハッシュ計算手順区分のチェック <BR>
        //	(this.ハッシュ計算方式区分="null" and this.ハッシュ計算手順区分="null")である場合に以下のチェックを行わない。
        if(!(this.hashCalHowToDiv == null && this.hashCalOrderDiv == null))
        {
	        //* 28-1)this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ計算手順区分="指定無"<BR>
	        if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	            WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
	        {
	            log.debug("ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ計算手順区分=指定無");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
	                getClass().getName() + STR_METHOD_NAME,
	                "ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ計算手順区分=指定無");
	        }
	
	        //* 28-2) this.ハッシュ計算方式区分="指定無"であり、かつthis.ハッシュ計算手順区分!="指定無"<BR>
	        if (WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
	        {
	            log.debug("ハッシュ計算方式区分=指定無であり、かつthis.ハッシュ計算手順区分!=指定無");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
	                getClass().getName() + STR_METHOD_NAME,
	                "ハッシュ計算方式区分=指定無であり、かつthis.ハッシュ計算手順区分!=指定無");
	        }
        }

		//障害対応 NO_2018
        //* 29) ステータス、ハッシュ計算方式区分、ハッシュ値一覧のチェック <BR>
		//	(this.ハッシュ計算方式区分="null" and this.ハッシュ値一覧="null")である場合に以下のチェックを行わない。
		if(!(this.hashCalHowToDiv == null && this.hashList == null))
		{
	        //* 　@this.ステータス!="停止中"の場合にのみ、以下のチェックを実施する。 <BR>
	        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus))
	        {
	            //* 29-1) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ値一覧=null<BR>
	            //* 　@　@または要素数==0件の場合、例外をスローする。 <BR>
	            if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	                (this.hashList == null || this.hashList.length == 0))
	            {
	                log.debug("ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ値一覧=null");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01856,
	                    getClass().getName() + STR_METHOD_NAME,
	                    "ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ値一覧=null");
	            }
	            //* 29-2) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ値一覧の全ての<BR>
	            //* 　@　@無効区分="無効"の場合、例外をスローする。 <BR>
	            if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv))
	            {
	                int l_int = hashList.length;
	                int l_intCnt = 0;
	                for (int i = 0; i < l_int; i++)
	                {
	                    if (!hashList[i].invalidDiv)
	                    {
	                        l_intCnt = l_intCnt + 1;
	                    }
	                }
	                if (l_intCnt == 0)
	                {
	                    log.debug("ハッシュ値一覧の全ての無効区分=無効の場合");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3BusinessLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01857,
	                        getClass().getName() + STR_METHOD_NAME,
	                        "ハッシュ値一覧の全ての無効区分=無効の場合");
	                }
	            }
	            //* 29-3) this.送信パラメータ区分="有"であり、かつthis.送信パラメータ一覧=null<BR>
	            //* 　@　@または要素数==0件の場合、例外をスローする。 <BR>
	            if (WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv) &&
	                (this.paramList == null || this.paramList.length == 0))
	            {
	                log.debug("送信パラメータ区分=有であり、かつthis.送信パラメータ一覧=null");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01858,
	                    getClass().getName() + STR_METHOD_NAME,
	                    "送信パラメータ区分=有であり、かつthis.送信パラメータ一覧=null");
	            }
	            //* 29-4) this.送信パラメータ区分="有"であり、かつthis.送信パラメータ一覧の全ての<BR>
	            //* 　@　@無効区分="無効"の場合、例外をスローする。<BR>
	            if (WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
	            {
	                int l_int = this.paramList.length;
	                int l_intCnt = 0;
	                for (int i = 0; i < l_int; i++)
	                {
	                    if (!paramList[i].invalidDiv)
	                    {
	                        l_intCnt = l_intCnt + 1;
	                    }
	                }
	                if (l_intCnt == 0)
	                {
	                    log.debug("送信パラメータ一覧の全ての無効区分=無効の場合");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3BusinessLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01859,
	                        getClass().getName() + STR_METHOD_NAME,
	                        "送信パラメータ一覧の全ての無効区分=無効の場合");
	                }
	            }
	        }
        }

        //30) 提供形式、利用期間料金情報、試用期間のチェック
		//障害対応 NO_U01997 30-1を削除

        // 30-1) this.ステータス!="停止中" && （this.提供形式="無料提供のみ" 又は、
        // this.提供形式="無料提供のみ(ウツミ屋)"） &&
        // this.利用期間料金情報!=null && 利用期間料金情報の要素数＞0件 &&
        // this.利用期間料金情報の全ての無効区分が"無効"ではなかった場合、例外をスローする。
        if(!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.chargeInfo != null && this.chargeInfo.length > 0)
        {
            int l_intInvalidDivCnt = this.chargeInfo.length;
            boolean l_blnInvalidDivFlag = true;
            for (int i = 0; i < l_intInvalidDivCnt; i++)
            {
               if (this.chargeInfo[i].invalidDiv)
               {
                   l_blnInvalidDivFlag = false;
               }
            }
            if (l_blnInvalidDivFlag)
            {
                log.debug("提供形式が無料提供の場合は、利用期間料金情報が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01906,
                    getClass().getName() + STR_METHOD_NAME,
                    "提供形式が無料提供の場合は、利用期間料金情報が指定不可です。");
            }
        }
        
        //30-2) this.ステータス!="停止中"であり、(this.提供形式="無料提供のみ"
        //      又は、this.提供形式="無料提供のみ(ウツミ屋)"） &&
        //    (this.試用期間単位区分!=nullまたはthis.試用期間!=null)の場合、例外をスローする。
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.trialDiv != null)
        {
            log.debug("30-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01908,
                getClass().getName() + STR_METHOD_NAME,
                "提供形式が無料提供の場合は、試用期間単位区分が指定不可です。");
        }

        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.trialPeriod != null)
        {
            log.debug("30-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01909,
                getClass().getName() + STR_METHOD_NAME,
                "提供形式が無料提供の場合は、試用期間が指定不可です。");
        }
        
		//仕様変更 NO_196
		//31-1)this.URLのバイト数が256バイト数以上の場合、例外をスローする。<BR>
		if ((url != null && url.trim().length() != 0) &&
			 WEB3StringTypeUtility.getByteLength(url) > 256)
		{
			log.debug("31-1) URL ＞ 256byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
		
		//仕様変更 NO_196
		//32-1)this.URL2のバイト数が256バイト数以上の場合、例外をスローする。<BR>
		if ((url2 != null && url2.trim().length() != 0) &&
			 WEB3StringTypeUtility.getByteLength(url2) > 256)
		{
			log.debug("32-1) URL2 ＞ 256byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F49810280
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
