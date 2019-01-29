head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客基本情報メッセージクラス(WEB3AccInfoAccountBaseInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
Revesion History : 2006/02/22 呉艶飛 (中訊) モデルNo.086
Revesion History : 2006/06/30 丁昭奎 (中訊) 仕様変更管理No.114
Revesion History : 2006/09/07 車進　@ (中訊) 仕様変更管理No.121
Revesion History : 2006/12/01 周捷 (中訊) 仕様変更管理No.150
Revesion History : 2007/02/26 吉麗ナ (中訊) 仕様変更管理No.200
Revesion History : 2007/03/16 吉麗ナ (中訊) 仕様変更管理No.212
Revesion History : 2007/03/08 吉麗ナ (中訊) 仕様変更管理No.208、210
Revesion History : 2007/07/13 武波 (中訊) 仕様変更管理No.219
Revesion History : 2007/09/07 武波 (中訊) 仕様変更・モデルNo.223
Revesion History : 2007/08/28 武波 (中訊) 仕様変更・モデルNo.217
Revesion History : 2007/11/01 武波 (中訊) 仕様変更・モデルNo.228
Revesion History : 2008/02/15 馮海涛 (中訊) 仕様変更・モデルNo.229
Revesion History : 2008/05/22 車進　@ (中訊) 仕様変更・モデルNo.234
Revesion History : 2008/08/01 張少傑 (中訊) 仕様変更・モデルNo.238
Revesion History : 2008/08/22 張少傑 (中訊) 仕様変更・モデルNo.247
Revesion History : 2008/09/26 武波 (中訊) 仕様変更・モデルNo.251
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.263
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (顧客基本情報)<BR>
 * 顧客基本情報メッセージクラス<BR>
 *
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfo extends Message
{
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (生年月日年号)<BR>
     * 生年月日年号<BR>
     * <BR>
     * 1：明治　@2：大正　@3：昭和　@4：平成　@9：不明<BR>
     */
    public String eraBorn;

    /**
     * (生年月日)<BR>
     * 生年月日<BR>
     * <BR>
     * 和暦（YYMMDD）：6桁<BR>
     * <BR>
     */
    public String bornDate;

    /**
     * (郵便番号)<BR>
     * 郵便番号<BR>
     */
    public String zipCode;

    /**
     * (住所１（カナ）)<BR>
     * 住所１（カナ）<BR>
     */
    public String addressKana1;

    /**
     * (住所２（カナ）)<BR>
     * 住所２（カナ）<BR>
     */
    public String addressKana2;

    /**
     * (住所３（カナ）)<BR>
     * 住所３（カナ）<BR>
     */
    public String addressKana3;

    /**
     * (住所１（漢字）)<BR>
     * 住所１（漢字）<BR>
     */
    public String address1;

    /**
     * (住所２（漢字）)<BR>
     * 住所２（漢字）<BR>
     */
    public String address2;

    /**
     * (住所３（漢字）)<BR>
     * 住所３（漢字）<BR>
     */
    public String address3;

    /**
     * (電話番号)<BR>
     * 電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String telephone;

    /**
     * (口座開設日)<BR>
     * 口座開設日<BR>
     */
    public Date accountOpenDate;

    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;

    /**
     * (振込先指定口座)<BR>
     * 振込先指定口座<BR>
     */
    public WEB3AccInfoAccountInfo transferAccount;

    /**
     * (専用振込先口座)<BR>
     * 専用振込先口座<BR>
     */
    public WEB3AccInfoAccountInfo exclusiveTransferAccount;

    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;

    /**
     * (メールアドレス更新日時)<BR>
     * メールアドレス更新日時<BR>
     */
    public Date mailAddressUpdateDate;

    /**
     * (株式約定メール送信フラグ)<BR>
     * 株式約定メール送信フラグ<BR>
     * <BR>
     * true：送信要　@false：送信不要<BR>
     */
    public boolean equityExecMailFlag;

    /**
     * (株式未約定メール送信フラグ)<BR>
     * 株式未約定メール送信フラグ<BR>
     * <BR>
     * true：送信要　@false：送信不要<BR>
     */
    public boolean equityUnExecMailFlag;

    /**
     * (先物OP約定メール送信フラグ)<BR>
     * 先物OP約定メール送信フラグ<BR>
     * <BR>
     * true：送信要　@false：送信不要<BR>
     */
    public boolean futOpExecMailFlag;

    /**
     * (先物OP未約定メール送信フラグ)<BR>
     * 先物OP未約定メール送信フラグ<BR>
     * <BR>
     * true：送信要　@false：送信不要<BR>
     */
    public boolean futOpUnExecMailFlag;

    /**
     * (案内メール送信フラグ)<BR>
     * 案内メール送信フラグ<BR>
     * <BR>
     * true：送信要　@false：送信不要<BR>
     */
    public boolean guidanceMailFlag;

    /**
     * (ログインエラー回数)<BR>
     * ログインエラー回数<BR>
     */
    public String loginErrorCount;

    /**
     * (パスワード更新日時)<BR>
     * パスワード更新日時<BR>
     */
    public Date lPasswordUpdateDate;

    /**
     * (暗証番号更新日時)<BR>
     * 暗証番号更新日時<BR>
     */
    public Date passwordUpdateDate;

    /**
     * (暗証番号変更可能フラグ)<BR>
     * 暗証番号変更可能フラグ<BR>
     * <BR>
     * 暗証番号変更ボタンを表示する場合：　@true<BR>
     * 暗証番号変更ボタンを表示しない場合：　@false<BR>
     */
    public boolean passwordUpdateAbleFlag;

    /**
     * (手数料コース)<BR>
     * 手数料コース<BR>
     * ※　@現在の手数料コース <BR>
     * 手数料コース（手数料コースコード）<BR> 
     * <BR>
     * 02：　@定率手数料（スタンダード） <BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR> 
     * 03：　@約定代金合計 <BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR> 
     * 04：　@約定回数 <BR>
     * 05：　@一日定額制 <BR>
     * 06：　@少額ボックス <BR>
     * 07：　@現物1日合計＋信用1日注文毎 <BR>
     * 08：　@現物1日注文毎＋信用1日合計 <BR>
     * 16：　@少額ボックス（キャンペーン） <BR>
     * 99：　@上記以外（リテラ・岩井のみ）<BR> 
     * <BR>
     * ※　@各コードの名称については、証券会社によって違う。<BR> 
     * 　@　@Web層にて、名称に変換する。<BR>
     */
    public String commissionCourse;

    /**
     * (有料情報フラグ)<BR>
     * 有料情報フラグ<BR>
     * <BR>
     * true：　@登録済み　@false：　@未登録<BR>
     */
    public boolean chargedInfoFlag;

    /**
     * (振込先指定金融機@関登録フラグ)<BR>
     * 振込先指定金融機@関登録フラグ<BR>
     * <BR>
     * true：　@登録済み　@false：　@未登録<BR>
     */
    public boolean transferFinancialInstitutionFlag;

    /**
     * (外貨振込先指定金融機@関登録フラグ)<BR>
     * 外貨振込先指定金融機@関登録フラグ<BR>
     * <BR>
     * true：　@登録済み　@false：　@未登録<BR>
     */
    public boolean foreignTransferFinancialInstitutionFlag;

    /**
     * (制度信用取引登録フラグ)<BR>
     * 制度信用取引登録フラグ<BR>
     * <BR>
     * true：　@登録済み　@false：　@未登録<BR>
     */
    public boolean marketMarginFlag;

    /**
     * (一般信用取引登録フラグ)<BR>
     * 一般信用取引登録フラグ<BR>
     * <BR>
     * true：　@登録済み　@false：　@未登録<BR>
     */
    public boolean institutionMarginFlag;

    /**
     * (先物OP取引登録)<BR>
     * 先物OP取引登録<BR>
     * <BR>
     * 1：　@登録済み（OP買建取引）<BR>
     * 2：　@登録済み（先物取引）<BR>
     * 3：　@登録済み（先物／OP取引）<BR>
     * <BR>
     */
    public String futOpTradeRegist;

    /**
     * (中国F・MMF登録)<BR>
     * 中国F・MMF登録<BR>
     * <BR>
     * ※ 保有しているファ@ンド名称を配列にてセットする。<BR>
     */
    public String[] ruitoRegist;

    /**
     * (現物株式口座区分)<BR>
     * 現物株式口座区分<BR>
     * <BR>
     * 1：　@未開設（一般）<BR>
     * 2：　@開設済み源泉徴収なし（特定かつ源泉徴収なし）<BR>
     * 3：　@開設済み源泉徴収あり（特定かつ源泉徴収）<BR>
     * <BR>
     */
    public String equityTaxType;

    /**
     * (現物株式口座区分（次年）)<BR>
     * 現物株式口座区分（次年）<BR>
     * <BR>
     * 1：　@未開設（一般）<BR>
     * 2：　@開設済み源泉徴収なし（特定かつ源泉徴収なし）<BR>
     * 3：　@開設済み源泉徴収あり（特定かつ源泉徴収）<BR>
     * <BR>
     */
    public String equityTaxTypeNext;

    /**
     * (現物株式特定口座開設日)<BR>
     * 現物株式特定口座開設日<BR>
     */
    public Date equityCapitalGainTaxOpenDate;

    /**
     * (信用取引口座区分)<BR>
     * 信用取引口座区分<BR>
     * <BR>
     * 1：　@未開設（一般）<BR>
     * 2：　@開設済み源泉徴収なし（特定かつ源泉徴収なし）<BR>
     * 3：　@開設済み源泉徴収あり（特定かつ源泉徴収）<BR>
     * <BR>
     */
    public String marginTaxType;

    /**
     * (信用取引口座区分（次年）)<BR>
     * 信用取引口座区分（次年）<BR>
     * <BR>
     * 1：　@未開設（一般）<BR>
     * 2：　@開設済み源泉徴収なし（特定かつ源泉徴収なし）<BR>
     * 3：　@開設済み源泉徴収あり（特定かつ源泉徴収）<BR>
     */
    public String marginTaxTypeNext;

    /**
     * (信用取引特定口座開設日)<BR>
     * 信用取引特定口座開設日<BR>
     */
    public Date marginCapitalGainTaxOpenDate;

    /**
     * (携帯番号・勤務先情報変更状態区分)<BR>
     * 携帯番号・勤務先情報変更状態区分<BR>
     * <BR>
     * 0：　@申込可<BR>
     * 1：　@申込中<BR>
     * 2：　@確認中<BR>
     */
    public String mobileOfficeChangeStateDiv;

    /**
     * (携帯番号・勤務先情報)<BR>
     * 携帯番号・勤務先情報<BR>
     */
    public WEB3AccInfoMobileOfficeInfo mobileOfficeInfo;

    /**
     * (手数料コース変更申込情報一覧)<BR>
     * 手数料コース変更申込情報一覧<BR>
     * ※　@手数料コース変更申込がない場合はnull。<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] commissionCourseChangeInfoList;

    /**
     * (内部者情報)<BR>
     * 内部者情報<BR>
     */
    public WEB3AccInfoInsiderInfo[] insiderList;

    /**
     * (停止情報)<BR>
     * 停止情報<BR>
     */
    public WEB3AccInfoStopInfo stopInfo;

    /**
     * (電子鳩状況)<BR>
     * 電子鳩状況<BR>
     */
    public WEB3AccInfoBatoInfo batoStatus;
    
    /**
     * (外国株式口座番号)<BR>
     *外国株式口座番号      
     */
    public String fstkAccountCode;
    
    /**
     * (為替保証金口座情報)<BR>
     *為替保証金口座情報<BR>
     */   
    public WEB3AccInfoFxAccountInfo[] fxAccountInfoList; 
    
    /**
     * (オリックス専用振込先口座)<BR>
     *オリックス専用振込先口座<BR>
     */
    public String orixSubAccCode;
    
    /**
     * (累投口座開設区分)<BR>
     *累投口座開設区分<BR>
     *<BR>
     * 0：　@開設<BR>
     * 1：　@未開設<BR>
     */
    public String ruitoAccountOpenDiv;

    /**
     * (外国証券口座開設区分)<BR>
     *外国証券口座開設区分<BR>
     *<BR>
     * 0：　@未開設<BR>
     * 1：　@開設<BR>
     */
    public String foreignSecAccOpenDiv;
    
    /**
     * (特定管理口座開設区分）<BR>
     *特定管理口座開設区分<BR>
     *<BR>
     * 0：　@未開設<BR>
     * 1：　@開設<BR>
     */
    public String capitalGainTaxAccOpenDiv;

    /**
     * (MRF口座開設区分)<BR>
     * MRF口座開設区分 <BR>
     * <BR>
     * 0：　@未開設 <BR>
     * 1：　@開設　@<BR>
     */
    public String mrfAccOpenDiv;
    
    /**
     * (口座タイプ)<BR>
     * 口座タイプ <BR>
     * <BR>
     * 0：その他 <BR>
     * 1：個人アカウント <BR>
     * 2：共用アカウント <BR>
     * 3：法@人アカウント <BR>
     */
    public String accountType;
    
    /**
     * (顧客情報変更区分1)<BR>
     * 顧客情報変更区分1<BR>
     */
    public String accountChangeDiv1;
    
    /**
     * (顧客情報変更区分2)<BR>
     * 顧客情報変更区分2 <BR>
     */
    public String accountChangeDiv2;
    
    /**
     * (顧客情報変更区分3)<BR>
     * 顧客情報変更区分3<BR>
     */
    public String accountChangeDiv3;
    
    /**
     * (職業)<BR>
     * 職業<BR>
     */
    public String occupationDiv;
    
    /**
     * (国籍)<BR>
     * 国籍<BR>
     */
    public String nationality;
    
    /**
     * (国籍その他)<BR>
     * 国籍その他<BR>
     */
    public String nationalityOther;
    
    /**
     * (顧客正式名称1)<BR>
     * 顧客正式名称1<BR>
     */
    public String accountRealName1;
    
    /**
     * (顧客正式名称2)<BR>
     * 顧客正式名称2<BR>
     */
    public String accountRealName2;

    /**
     * (法@人情報)<BR>
     * 法@人情報<BR>
     */    
    public WEB3AccInfoCorporationInfo corporationInfo;
    
    /**
     * (ストックオプション口座開設区分)<BR>
     * ストックオプション口座開設区分<BR>
     */    
    public String stockOptionAccOpenDiv;
    
    /**
     * (証券担保ローン区分)<BR>
     * 証券担保ローン区分<BR>
     */    
    public String comStockLoanDiv;

    /**
     * (性別)<BR>
     * 性別
     */
    public String sex;

    /**
     * (ストックオプション口座開設情報一覧)<BR>
     * ストックオプション口座開設情報一覧<BR>
     */    
    public WEB3AccInfoStockOptionInfo[] stockOptionAccOpenList;
    
    /**
     * (手数料割引キャンペーン情報)<BR>
     * 手数料割引キャンペーン情報<BR>
     */    
    public WEB3AccInfoCommissionCampaignInfo[] commissionCampaignInfoList;
    
    /**
     * (外貨振込先指定口座一覧)<BR>
     * 外貨振込先指定口座一覧<BR>
     */    
    public WEB3AccInfoAccountInfo[] foreignTransferAccountList;

    /**
     * (モバイル専用口座開設区分)<BR>
     * モバイル専用口座開設区分<BR>
     * 0：　@未開設<BR>
     * 1：　@開設<BR>
     */
    public String mobileAccountDiv;

    /**
     * (証券担保ローン口座開設情報)<BR>
     * 証券担保ローン口座開設情報<BR>
     */
    public WEB3AccInfoStockLoanAccountInfo stockLoanAccountInfo;

    /**
     * (複数アドレス情報)<BR>
     * 複数アドレス情報<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (プロアマ区分)<BR>
     * プロアマ区分<BR>
     * 0：　@アマ<BR>
     * 1：　@プロ<BR>
     */
    public String proAmDiv;

    /**
     * (PTS口座開設区分)<BR>
     * PTS口座開設区分<BR>
     * 0：　@未開設<BR>
     * 1：　@開設<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (放送法@)<BR>
     * 放送法@<BR>
     * <BR>
     * 0：該当なし<BR>
     * 1：該当あり<BR>
     */
    public String broadcastLaw;

    /**
     * (航空法@)<BR>
     * 航空法@<BR>
     * <BR>
     * 0：該当なし<BR>
     * 1：該当あり<BR>
     */
    public String aviationLaw;

    /**
     * (NTT法@)<BR>
     * NTT法@<BR>
     * <BR>
     * 0：該当なし<BR>
     * 1：該当あり<BR>
     */
    public String nttLaw;

    /**
     * (配当金振込指定区分)<BR>
     * 配当金振込指定区分<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：登録配当金受領口座<BR>
     * 2：株式数比例配分<BR>
     */
    public String dividendTransDesignate;

    /**
     * (常任代理人)<BR>
     * 常任代理人<BR>
     * <BR>
     * 0：選任なし<BR>
     * 1：選任あり<BR>
     */
    public String standingProxy;

    /**
     * (法@定代理人)<BR>
     * 法@定代理人<BR>
     * <BR>
     * 0：選任なし<BR>
     * 1：選任あり<BR>
     */
    public String statutoryAgent;

    /**
     * (加入者口座番号)<BR>
     * 加入者口座番号<BR>
     */ 
    public String affiliateAccountCode;

    /**
     * (機@構通知完了区分)<BR>
     * 機@構通知完了区分<BR>
     * <BR>
     * 0：非通知<BR>
     * 1：通知中<BR>
     * 2：通知済<BR>
     */
    public String agencyNotifyCmpDiv;

    /**
     * (CFD口座情報)<BR>
     * CFD口座情報<BR>
     */
    public WEB3AccInfoCfdAccountInfo[] cfdAccountInfoList;

    /**
     * (複数アドレスリスト)<BR>
     * 複数アドレスリスト<BR>
     */
    public WEB3AccInfoMultiMailAddressList multiMailAddressList;

    /**
     * (顧客基本情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@return WEB3AccInfoAccountBaseInfo
     * @@roseuid 4163879A012B
     */
    public WEB3AccInfoAccountBaseInfo()
    {

    }
    

    
}
@
