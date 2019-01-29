head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ(WEB3PvInfoDataManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/04 王暁傑(中訊) 新規作成
Revesion History : 2006/05/23 肖志偉(中訊) 仕様変更 モデル063
Revesion History : 2006/09/12 張騰宇(中訊) 仕様変更モデル070
Revesion History : 2007/02/26 金傑(中訊) 仕様変更モデル073
Revesion History : 2007/03/16 金傑(中訊) 仕様変更モデル076
Revision History : 2007/07/13 謝旋(中訊) 仕様変更モデル083
Revision History : 2007/12/07 孟亞南(中訊) 仕様変更モデル095,096
Revision History : 2008/10/06 柴双紅(中訊) 仕様変更モデル106
*/
package webbroker3.pvinfo;

import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;


/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝのDB I/Oなどを管理するクラス。(インタフェイス)<BR>
 * @@author 王暁傑
 * @@version 1.0
 */
public interface WEB3PvInfoDataManager extends Service
{
   
   /**
    * (get注文件数)<BR>
    * 引数の商品区分の注文件数を取得する。<BR>
    * @@param l_strProductDiv - 商品区分<BR>
    * <BR>
    * 0：　@現物 <BR>
    * 1：　@信用 <BR>
    * 2：　@先物 <BR>
    * 3：　@オプション <BR>
    * 
    * @@param l_isTodayOrder - (is当日注文)<BR>
    * 当日注文を取得するかどうかのフラグ<BR>
    * <BR>
    * false：　@翌日注文<BR>
    * true：　@当日注文<BR>
    * @@param l_strQueryString - 検索条件文字列
    * @@param l_strQueryDataContainers - 検索条件データコンテナ
    * @@return int
    * @@roseuid 4141331503A3
    */
   public int getOrderCnt(String l_strProductDiv, boolean l_isTodayOrder, String l_strQueryString, String[] l_strQueryDataContainers) 
       throws WEB3BaseException;
   
   /**
    * (get約定件数)<BR>
    * 引数の商品区分の当日約定件数を取得する。<BR>
    * @@param l_mainAccount - (顧客)<BR>
    * 顧客オブジェクト<BR>
    * @@param l_strProductDiv - 商品区分<BR>
    * <BR>
    * 0：　@現物 <BR>
    * 1：　@信用 <BR>
    * 2：　@先物 <BR>
    * 3：　@オプション <BR>
    * 
    * @@return int
    * @@roseuid 414152F00384
    */
   public int getExecuteCnt(WEB3GentradeMainAccount l_mainAccount, String l_strProductDiv)
       throws WEB3BaseException;
   
   /**
    * (get表示内容Params)<BR>
    * 引数の表示内容IDに該当する表示内容Paramsを<BR>
    * 表示内容テーブルから取得する。<BR>
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@return DisplayContentsParams
    * @@roseuid 4147A21F0069
    */
   public DisplayContentsParams getDisplayContentsParams(long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get表示内容Params一覧)<BR>
    * 条件に該当する表示内容Paramsの一覧を<BR>
    * 表示内容テーブルから取得する。<BR>
    * @@param l_strQueryString - 検索条件文字列
    * @@param l_strQueryDataContainers - 検索条件データコンテナ
    * @@param l_strSortCond - ソート条件
    * @@return List
    * @@roseuid 4145092300D9
    */
   public List getDisplayContentsParamsList(String l_strQueryString, String[] l_strQueryDataContainers, String l_strSortCond)
       throws WEB3BaseException;
   
   /**
    * (insert表示内容)<BR>
    * 表示内容テーブルに新規データを一行登録する。<BR>
    * @@param l_administrator - (管理者)<BR>
    * 管理者オブジェクト<BR>
    * @@param l_displayContentsInfo - (表示内容情報)<BR>
    * 表示内容情報オブジェクト<BR>
    * @@roseuid 415BF49B0047
    */
   public void insertDisplayContents(WEB3Administrator l_administrator, WEB3PvInfoDisplayContentsUnit l_displayContentsInfo)
       throws WEB3BaseException;
   
   /**
    * (update表示内容)<BR>
    * 引数の表示内容Paramsで表示内容テーブルを更新する。<BR>
    * @@param l_displayContentsParams - (表示内容Params)<BR>
    * 表示内容Paramsオブジェクト<BR>
    * @@roseuid 415BF49B0076
    */
   public void updateDisplayContents(DisplayContentsParams l_displayContentsParams)
       throws WEB3BaseException;
   
   /**
    * (delete表示内容)<BR>
    * 引数の表示内容IDに該当する表示内容テーブルのデータを削除する。<BR>
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@roseuid 415D3043036B
    */
   public void deleteDisplayContents(long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get新規表示内容ID)<BR>
    * 表示内容IDを新規採番し、返却する。<BR>
    * @@return long
    * @@roseuid 415C0F010281
    */
   public long getNewDisplayContentsId()
       throws WEB3BaseException;
   
   /**
    * (get表示条件設定Params一覧)<BR>
    * 引数に該当する表示条件設定Paramsの一覧を取得する。<BR>
    * @@param l_strInstitutionCode - 証券会社コード
    * @@param l_strBranchCode - 部店コード
    * @@return List
    * @@roseuid 415BE56D02E2
    */
   public List getDisplayConditionParamsList(String l_strInstitutionCode, String l_strBranchCode)
       throws WEB3BaseException;
   
   /**
    * (create表示条件情報一覧)<BR>
    * ダイレクト指定を除く、表示条件情報の一覧を作成する。<BR>
    * @@param l_administrator - 管理者オブジェクト
    * @@return WEB3PvInfoDisplayConditionUnit[]
    * @@roseuid 415BFC23009C
    */
   public WEB3PvInfoDisplayConditionUnit[] createDisplayConditionList(WEB3Administrator l_administrator)
       throws WEB3BaseException;
   
   /**
    * (get閲覧履歴Params)<BR>
    * 閲覧履歴Paramsを取得する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@return BrowseHistoryParams
    * @@roseuid 414523FA032B
    */
   public BrowseHistoryParams getBrowseHistoryParams(WEB3GentradeMainAccount l_mainAccount, long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get閲覧履歴Params一覧)<BR>
    * 条件に該当する閲覧履歴Paramsの一覧を<BR>
    * 表示内容テーブルから取得する。<BR>
    * @@param l_strQueryString - 検索条件文字列
    * @@param l_strQueryDataContainers - 検索条件データコンテナ
    * @@param l_strSortCond - ソート条件
    * @@return List
    * @@roseuid 415CBDF403C1
    */
   public List getBrowseHistoryParamsList(String l_strQueryString, String[] l_strQueryDataContainers, String l_strSortCond)
       throws WEB3BaseException;
   
   /**
    * (insert閲覧履歴)<BR>
    * 閲覧履歴テーブルにデータを一行登録する。<BR>
    * @@param l_strInstitutionCode - 証券会社コード
    * @@param l_strBranchCode - 部店コード
    * @@param l_strAccountCode - 部店コード
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@param l_isRead - 未読既読フラグを既読で登録するかどうかのフラグ<BR>
    * <BR>
    * false：　@未読で登録<BR>
    * true：　@既読で登録<BR>
    * @@roseuid 4160D8C4017D
    */
   public void insertBrowseHistory(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId, boolean l_isRead)
       throws WEB3BaseException;
   
   /**
    * (update閲覧履歴)<BR>
    * 引数の閲覧履歴Paramsで閲覧履歴テーブルを更新する。<BR>
    * @@param l_browseHistoryParams - 閲覧履歴Paramsオブジェクト
    * @@roseuid 4145236D005C
    */
   public void updateBrowseHistory(BrowseHistoryParams l_browseHistoryParams)
       throws WEB3BaseException;
   
   /**
    * (delete閲覧履歴)<BR>
    * 引数の表示内容IDに該当する閲覧履歴テーブルのデータを削除する。<BR>
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@roseuid 4160E9200090
    */
   public void deleteBrowseHistory(long l_lngDisplayContentsId)
   throws WEB3BaseException;
   
   /**
    * (delete閲覧履歴)<BR>
    * 引数の顧客情報、表示内容IDに該当する閲覧履歴テーブルのデータを削除する。<BR>
    * @@param l_strInstitutionCode - 証券会社コード
    * @@param l_strBranchCode - 部店コード
    * @@param l_strAccountCode - 顧客コード
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@roseuid 41610CD7028C
    */
   public void deleteBrowseHistory(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId)
   throws WEB3BaseException;
   
   /**
    * (getIPO申告Params)<BR>
    * 当選したIPO申告Paramsを取得する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@return List
    * @@roseuid 41454EEC000E
    */
   public List getIpoOrderParams(WEB3GentradeMainAccount l_mainAccount)
       throws WEB3BaseException;
   
   /**
    * (getIPO銘柄Params)<BR>
    * 引数のIPO銘柄IDに該当する購入申込締切前の<BR>
    * IPO銘柄Paramsを取得する。<BR>
    * @@param l_lngIpoProductId - IPO銘柄ID
    * @@param l_isAdvancedElection - is繰上げ当選
    * @@return IpoProductParams
    * @@roseuid 41455DCC02AE
    */
   public IpoProductParams getIpoProductParams(long l_lngIpoProductId, boolean l_isAdvancedElection)
       throws WEB3BaseException;
   
   /**
    * (get決済期限間近建玉一覧)<BR>
    * 引数の顧客の保持する決済期限間近の建玉を取得する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@param l_isSettleBeforeOneWeek - (is決済一週間前)<BR>
    * 決済期限が一週間前かどうかのフラグ<BR>
    * <BR>
    * false：　@決済一ヶ月前<BR>
    * true：　@決済一週間前<BR>
    * @@return List
    * @@roseuid 414567D50221
    */
   public List getSettleContractList(WEB3GentradeMainAccount l_mainAccount, boolean l_isSettleBeforeOneWeek)
       throws WEB3BaseException;
   
   /**
    * (get証拠金不足一覧)<BR>
    * 引数の顧客に該当するデータを証拠金テーブルから取得する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@return List
    * @@roseuid 4145913501B4
    */
   public List getIfoDepositShortageList(WEB3GentradeMainAccount l_mainAccount)
       throws WEB3BaseException;
   
   /**
    * (get株式銘柄Params)<BR>
    * 引数の銘柄IDに該当する株式銘柄Paramsを<BR>
    * 株式銘柄テーブルより取得する。<BR>
    * @@param l_lngProductId - 銘柄ID
    * @@return EqtypeProductParams
    * @@roseuid 4147AB310115
    */
   public EqtypeProductParams getEqtypeProductParams(long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (get先物OP銘柄Params)<BR>
    * 引数の銘柄IDに該当する先物OP銘柄Paramsを<BR>
    * 先物OP銘柄テーブルより取得する。<BR>
    * @@param l_lngProductId - 銘柄ID
    * @@return IfoProductParams
    * @@roseuid 41593981026F
    */
   public IfoProductParams getIfoProductParams(long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (clone閲覧履歴Params)<BR>
    * 引数の閲覧履歴Paramsをコピーして、<BR>
    * 同じ内容の別インスタンスを作成し、返却する。<BR>
    * @@param l_browseHistoryParams - 閲覧履歴Paramsオブジェクト
    * @@return BrowseHistoryParams
    * @@roseuid 4147C8E403AD
    */
   public BrowseHistoryParams cloneBrowseHistoryParams(BrowseHistoryParams l_browseHistoryParams);
   
   /**
    * (clone表示内容Params)<BR>
    * 引数の表示内容Paramsをコピーして、<BR>
    * 同じ内容の別インスタンスを作成し、返却する。<BR>
    * @@param l_displayContentsParams - 表示内容Paramsオブジェクト
    * @@return DisplayContentsParams
    * @@roseuid 4147C96201B9
    */
   public DisplayContentsParams cloneDisplayContentsParams(DisplayContentsParams l_displayContentsParams);
   
   /**
    * (create表示内容Params)<BR>
    * 引数の表示内容情報から<BR>
    * 表示内容Paramsを作成し、返却する。<BR>
    * @@param l_displayContentsUnit - 表示内容情報オブジェクト
    * @@return DisplayContentsParams
    * @@roseuid 415D227A02CF
    */
   public DisplayContentsParams createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
       throws WEB3BaseException;
   
   /**
    * (is資産保有)<BR>
    * 引数の銘柄タイプに該当する資産を保有しているか判別する。<BR>
    * <BR>
    * 保有している場合true、以外falseを返却する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@param l_productType - 銘柄タイプ<BR>
    * (ProductTypeEnumにて定義)<BR>
    * @@param l_isMiniStock - (isミニ株)<BR>
    * 取得対象保有資産が、ミニ株かどうかのフラグ<BR>
    * <BR>
    * true：　@ミニ株<BR>
    * false：　@ミニ株でない<BR>
    * @@return boolean
    * @@roseuid 41590F7E0221
    */
   public boolean isAssetHas(WEB3GentradeMainAccount l_mainAccount, ProductTypeEnum l_productType, boolean l_isMiniStock)
       throws WEB3BaseException;
   
   /**
    * (is建玉保有)<BR>
    * 引数の銘柄タイプ、先物／オプション区分に該当する建玉を<BR>
    * 保有しているか判別する。<BR>
    * <BR>
    * 保有している場合true、以外falseを返却する。<BR>
    * @@param l_mainAccount - 顧客オブジェクト
    * @@param l_productType - 銘柄タイプ<BR>
    * (ProductTypeEnumにて定義)<BR>
    * @@param l_strFutureOptionDiv - 先物／オプション区分<BR>
    * <BR>
    * 0：　@DEFAULT<BR>
    * 1：　@先物<BR>
    * 2：　@オプション<BR>
    * @@return boolean
    * @@roseuid 4159148800B9
    */
   public boolean isContractHas(WEB3GentradeMainAccount l_mainAccount, ProductTypeEnum l_productType, String l_strFutureOptionDiv)
       throws WEB3BaseException;
   
   /**
    * (get閲覧履歴Params一覧)<BR>
    * 引数の条件に該当する閲覧履歴Paramsの一覧を取得する。<BR>
    * @@param l_strInstitutionCode - 証券会社コード
    * @@param l_strBranchCode - 部店コードの配列
    * @@param l_strAccountCode - 顧客コード
    * @@param l_lngDisplayContentsId - 表示内容ID
    * @@return List
    * @@roseuid 4214199602DB
    */
   public List getBrowseHistoryParamsList(String l_strInstitutionCode, String[] l_strBranchCode, String l_strAccountCode, long l_lngDisplayContentsId)
       throws WEB3BaseException;
   
   /**
    * (get営業日一覧)<BR>
    * 営業日の一覧を返却する。<BR>
    * @@return Date[]
    * @@roseuid 4214360C0274
    */
   public Date[] getBizDateList()
       throws WEB3BaseException;
   
   /**
    * (get売買代金)<BR>
    * 引数の商品区分の売買代金合計を取得する。<BR>
    * <BR>
    * ※売買区分を問わず単価 * 数量の絶対値の集計を行う。<BR>
    * 　@手数料、消費税は含まれない。<BR>
    * 　@現引・現渡の代金は加算されない。<BR>
    * @@param l_strProductDiv - 商品区分<BR>
    * <BR>
    * 0：　@現物 <BR>
    * 1：　@信用 <BR>
    * 2：　@先物 <BR>
    * 3：　@オプション <BR>
    * 
    * @@param l_strQueryString - 検索条件文字列
    * @@param l_strQueryDataContainers - 検索条件データコンテナ
    * @@return double
    * @@roseuid 421451A202C5
    */
   public double getTradePrice(String l_strProductDiv, String l_strQueryString, String[] l_strQueryDataContainers)
       throws WEB3BaseException;
   
   /**
    * (getトランザクション一覧)<BR>
    * トランザクションの一覧を取得する。<BR>
    * @@param rowType - RowType
    * @@param l_lngOrderId - 注文ID
    * @@param l_lngOrderUnitId - 注文単位ID
    * @@return List
    * @@roseuid 4214692F0120
    */
   public List getFinTransactionList(RowType rowType, long l_lngOrderId, long l_lngOrderUnitId)
       throws WEB3BaseException;
   
   /**
    * (get指数乗数)<BR>
    * 指数乗数を取得し返却する。<BR>
    * @@param l_lngBranchId - 部店ID
    * @@param l_lngMarketId - 市場ID
    * @@param l_lngProductId - 銘柄ID
    * @@return double
    * @@roseuid 42146A8F013B
    */
   public double getUnitSize(long l_lngBranchId, long l_lngMarketId, long l_lngProductId)
       throws WEB3BaseException;
   
   /**
    * (get資産余力情報)<BR>
    * 引数の補助口座に該当する資産余力情報を返却する。<BR>
    * <BR>
    * 戻り値：　@資産余力情報<現物顧客> or<BR>
    * 　@　@　@　@　@　@資産余力情報<信用顧客><BR>
    * <BR>
    * ※本メソッドを使用する場合は、<BR>
    * 使用するサービスのサービスインタセプタ.onReturn()、onThrowable()内にて、<BR>
    * "TRADING_POWER_INFO"の設定キーにて設定されているデータをクリアすること。<BR>
    * @@param l_subAccount - 補助口座オブジェクト
    * @@return Object
    * @@roseuid 4215A73A00FF
    */
   public Object getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (get入金請求発生日)<BR>
    * 入金請求発生日を返却する。<BR>
    * @@param l_subAccount - 補助口座オブジェクト
    * @@return Date
    * @@roseuid 4215AAE60238
    */
   public Date getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (get立替金発生日)<BR>
    * 立替金発生日を返却する。<BR>
    * @@param l_subAccount - 補助口座オブジェクト
    * @@return Date
    * @@roseuid 4215B4A5016D
    */
   public Date getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount)
       throws WEB3BaseException;
   
   /**
    * (is建玉強制処分)<BR>
    * 強制処分の対象となる建玉を<BR>  
    * 保有しているか判別する。<BR>  
    * <BR>  
    * 保有している場合true、以外falseを返却する。<BR>
    * <BR>
    * @@param l_mainAccount - (顧客)
    * 顧客オブジェクト<BR>
    * @@param l_strConditionNo - (表示条件番号)
    * 表示条件設定Params.表示条件番号<BR>
    * @@return boolean
    * @@throws WEB3BaseException 
    */
   public boolean  isContractEnforcedDisposal(
       WEB3GentradeMainAccount l_mainAccount, String l_strConditionNo) throws WEB3BaseException;
   
   /**
    * (get入金請求管理信用Params)<BR>
    * 入金請求管理信用Paramsを取得する。<BR> 
    * <BR>
    * @@param l_mainAccount - (顧客)
    * 顧客オブジェクト<BR>
    * @@return PaymentRequisitionMarginParams 
    * @@throws WEB3BaseException 
    */
   public PaymentRequisitionMarginParams getPaymentRequisitionMarginParams(
       WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
   
   /**
    * (is出金停止)<BR>
    *出金停止顧客か判別する。<BR>                 
    * <BR>                                        
    *出金停止顧客の場合true、以外falseを返却する。<BR> 
    *@@param l_mainAccount-(顧客)<BR>
    *顧客オブジェクト<BR>
    *@@param l_strCashoutStopDiv-出金停止区分 <BR>
    *出金停止区分<BR>
    *<BR>
    *1：全額<BR>
    *2：一部<BR>
    *@@return boolean 
    *@@throws WEB3BaseException
    */
   public boolean isCashoutStop(WEB3GentradeMainAccount l_mainAccount,
       String l_strCashoutStopDiv)throws WEB3BaseException;
   
   /**
    * (get担保不足顧客データParams)<BR>
    *条件に該当する担保不足顧客データParamsを取得する。<BR> >
    *@@param l_mainAccount-(顧客)<BR>
    *顧客オブジェクト<BR>
    *@@param l_strCashoutStopDiv-出金停止区分 <BR>
    *出金停止区分<BR>
    *<BR>
    *1：全額<BR>
    *2：一部<BR>
    *@@return 担保不足顧客データParams  
    *@@throws WEB3BaseException
    */
   public SecurityShortageAccountParams getSecurityShortageAccountParams(
       WEB3GentradeMainAccount l_mainAccount,
       String l_strCashoutStopDiv)throws WEB3BaseException;
   
   /**
	 * (is手数料割引キャンペーン)<BR>
	 * 手数料割引キャンペーン対象顧客か判別する。<BR>
	 * <BR>
	 * 手数料割引キャンペーン対象顧客の場合true、以外falseを返却する。<BR>
     * @@param l_mainAccount-(顧客)<BR>
     * 顧客オブジェクト<BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount) 
	    throws WEB3BaseException;

	/**
	 * (get割引キャンペーン顧客Params)<BR>
	 * 手数料割引キャンペーン顧客履歴Paramsを取得する。<BR>
     * @@param l_mainAccount-(顧客)<BR>
     * 顧客オブジェクト<BR> 
	 * @@return CommCampaignAccHistoryParams[]
	 * @@throws WEB3BaseException
	 */
	public CommCampaignAccHistoryParams[] getCommCampaignAccHistoryParams(
	   WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

	/**
	 * (get商品コード)<BR>
	 * 手数料割引キャンペーン商品マスタから商品コードを取得する。<BR>
	 * @@param l_lngAccInfoConditionId-(キャンペーン手数料条件ID)<BR>
	 * @@return String[]
	 * @@throws WEB3BaseException 
	 */
	public String[] getCommProductCodes(long l_lngAccInfoConditionId) throws WEB3BaseException;

    /**
     * (isモバイル専用口座開設)<BR>
     * モバイル専用口座の開設／未開設を判別する。  <BR>
     * <BR>
     * モバイル専用口座開設の場合true、以外(モバイル専用口座未開設)の場合falseを返却する。 <BR>
     * <BR>
     * @@param l_mainAccount - WEB3GentradeMainAccount
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOnlyMobileOpen(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (is書面交付日より11ヶ月経過)<BR>
     * 書面交付日より11ヶ月経過の顧客か判別する。 <BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeliveryDate(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (get書面交付管理Params)<BR>
     * 検索条件に該当する書面交付管理レコードをList型で返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDeliveryManagementParams(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get不足金発生状況)<BR>
     * 該当顧客の不足金発生状況を返す。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@不足金未発生<BR>
     * "1"：　@不足金発生（信用口座未開設）<BR>
     * "2"：　@不足金発生（信用口座開設）<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getShortfallGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get追証発生状況)<BR>
     * 該当顧客の不足金発生状況を返す。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@追証未発生<BR>
     * "1"：　@第一水準追証発生<BR>
     * "2"：　@第二水準追証発生<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdddepositGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get不足金発生情報)<BR>
     * 該当顧客の不足金発生情報を返す。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3TPShortfallOccurInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get追証発生情報)<BR>
     * 該当顧客の不足金発生情報を返す。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get余力取引停止区分)<BR>
     * 顧客余力条件テーブルの取引停止区分を取得する。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@取引可能<BR>
     * "1"：　@取引停止<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTPTradingStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (get顧客余力条件Params)<BR>
     * 検索条件に該当する顧客余力条件レコードを返却する。<BR>
     * <BR>
     * @@param l_accountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    public TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException;

    /**
     * (get追証未入金区分)<BR>
     * 顧客余力条件テーブルの追証未入金区分を取得する。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@追証未入金なし（取引可能）<BR>
     * "1"：　@追証未入金あり（取引停止）<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionalDepositStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;
}
@
