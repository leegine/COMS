head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderVoucherCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 当日注文伝票CSV(WEB3FeqOrderVoucherCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/02/02 柴双紅(中訊) モデルNo.396
*/

package webbroker3.feq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.feq.data.FeqOrderChangeStatusRow;
import webbroker3.feq.define.WEB3FeqChangeCancelDivDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (当日注文伝票CSV)<BR>
 * 当日注文伝票CSVクラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqOrderVoucherCSV extends WEB3GentradeCsvDataModel 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOrderVoucherCSV.class);
            
    /**
     * (証券会社コードラベル)<BR>
     * 証券会社コードラベル<BR>
     */
    public String institutionCodeLabel = "証券会社コード";
    
    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public String branchCodeLabel = "部店コード";
    
    /**
     * (口座番号ラベル)<BR>
     * 口座番号ラベル<BR>
     */
    public String accountNoLabel = "口座番号";
    
    /**
     * (扱者コードラベル)<BR>
     * 扱者コードラベル<BR>
     */
    public String traderCodeLabel = "扱者コード";
    
    /**
     * (識別コードラベル)<BR>
     * 識別コードラベル<BR>
     */
    public String orderRequestNumberLabel = "識別コード";
    
    /**
     * (伝票Noラベル)<BR>
     * 伝票Noラベル<BR>
     */
    public String voucherNoLabel = "伝票No";
    
    /**
     * (注文シーケンスNoラベル)<BR>
     * 注文シーケンスNoラベル<BR>
     */
    public String orderSequenceNoLabel = "注文シーケンスNo";
    
    /**
     * (銘柄コードラベル)<BR>
     * 銘柄コードラベル<BR>
     */
    public String productCodeLabel = "銘柄コード";
    
    /**
     * (現地銘柄コードラベル)<BR>
     * 現地銘柄コードラベル<BR>
     */
    public String offshoreProductCodeLabel = "現地銘柄コード";
    
    /**
     * (銘柄名ラベル)<BR>
     * 銘柄名ラベル<BR>
     */
    public String productNameLabel = "銘柄名";
    
    /**
     * (市場コードラベル)<BR>
     * 市場コードラベル<BR>
     */
    public String marketCodeLabel = "市場コード";
    
    /**
     * (市場名ラベル)<BR>
     * 市場名ラベル<BR>
     */
    public String marketNameLabel = "市場名";
    
    /**
     * (売買ラベル)<BR>
     * 売買ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 1：売付<BR>
     * 2：買付<BR>
     */
    public String tradeLabel = "売買";
    
    /**
     * (注文株数ラベル)<BR>
     * 注文株数ラベル<BR>
     */
    public String orderQuantityLabel = "注文株数";
    
    /**
     * (指値・成行ラベル)<BR>
     * 指値・成行ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：成行<BR>
     * 1：指値<BR>
     */
    public String limitPriceOrMarketPriceLabel = "指値・成行";
    
    /**
     * (単価ラベル)<BR>
     * 単価ラベル<BR>
     */
    public String priceLabel = "単価";
    
    /**
     * (決済区分ラベル)<BR>
     * 決済区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：円貨決済<BR>
     * 1：外貨決済<BR>
     */
    public String settleDivLabel = "決済区分";
    
    /**
     * (通貨コードラベル)<BR>
     * 通貨コードラベル<BR>
     */
    public String currencyCodeLabel = "通貨コード";
    
    /**
     * (通貨名称ラベル)<BR>
     * 通貨名称ラベル<BR>
     */
    public String currencyNameLabel = "通貨名称";
    
    /**
     * (注文日時ラベル)<BR>
     * 注文日時ラベル<BR>
     */
    public String orderTimestampLabel = "注文日時";
    
    /**
     * (発注日ラベル)<BR>
     * 発注日ラベル<BR>
     */
    public String bizDateLabel = "発注日";
    
    /**
     * (執行条件ラベル)<BR>
     * 執行条件ラベル<BR>
     */
    public String execCondLabel = "執行条件";
    
    /**
     * (発注条件ラベル)<BR>
     * 発注条件ラベル<BR>
     */
    public String orderCondLabel = "発注条件";
    
    /**
     * (発注条件演算子ラベル)<BR>
     * 発注条件演算子ラベル<BR>
     */
    public String orderCondOperatorLabel = "発注条件演算子";
    
    /**
     * (発注条件単価ラベル)<BR>
     * 発注条件単価ラベル<BR>
     */
    public String orderCondPriceLabel = "発注条件単価";
    
    /**
     * (（W指値）訂正指値ラベル)<BR>
     * （W指値）訂正指値ラベル<BR>
     */
    public String wLimitPriceLabel = "（W指値）訂正指値";
    
    /**
     * (注文状態ラベル)<BR>
     * 注文状態ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：受付未済<BR>
     * 1：受付済<BR>
     * 2：受付エラー<BR>
     * 9：訂正・取消されたデータ<BR>
     */
    public String orderStatusLabel = "注文状態";
    
    /**
     * (約定区分ラベル)<BR>
     * 約定区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：未約定<BR>
     * 1：一部約定<BR>
     * 2：全部約定<BR>
     * 3：失効<BR>
     */
    public String execDivLabel = "約定区分";
    
    /**
     * (訂正取消区分ラベル)<BR>
     * 訂正取消区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：初期値<BR>
     * 1：取消中<BR>
     * 2：一部取消完了<BR>
     * 3：全部取消完了<BR>
     * 4：取消失敗<BR>
     * 5：訂正中<BR>
     * 6：一部訂正完了<BR>
     * 7：全部訂正完了<BR>
     * 8：訂正失敗<BR>
     * 9：訂正後新規注文<BR>
     */
    public String changeCancelDivLabel = "訂正取消区分";
    
    /**
     * (注文チャネルラベル)<BR>
     * 注文チャネルラベル<BR>
     */
    public String orderChanelLabel = "注文チャネル";
    
    /**
     * (ファ@クターラベル)<BR>
     * ファ@クターラベル<BR>
     */
    public String factorLabel = "ファ@クター";
    
    /**
     * (手数料Noラベル)<BR>
     * 手数料Noラベル<BR>
     */
    public String commisionNumberLabel = "手数料No";
    
    /**
     * (手数料No枝番ラベル)<BR>
     * 手数料No枝番ラベル<BR>
     */
    public String commisionBranchNumberLabel = "手数料No枝番";
    
    /**
     * (商品コードラベル)<BR>
     * 商品コードラベル<BR>
     */
    public String productNumberLabel = "商品コード";
    
    /**
     * (注文経路区分ラベル)<BR>
     * 注文経路区分ラベル<BR>
     */
    public String orderRootDivLabel = "注文経路区分";
    
    /**
     * (メール区分ラベル)<BR>
     * メール区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：未送信<BR>
     * 1：送信済<BR>
     */
    public String mailDivLabel = "メール区分";
    
    /**
     * (特定口座区分ラベル)<BR>
     * 特定口座区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String taxTypeLabel = "特定口座区分";
    
    /**
     * (運用コードラベル)<BR>
     * 運用コードラベル<BR>
     */
    public String orderEmpCodeLabel = "運用コード";
    
    /**
     * (当日注文伝票CSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * <BR>
     * １）super()をコールし、インスタンスを生成する。<BR>
     * <BR>
     * ２）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する。<BR>
     * @@roseuid 42A038C30156
     */
    public WEB3FeqOrderVoucherCSV() 
    {
        //１）super()をコールし、インスタンスを生成する。
        super();                        
        
        //２）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する。
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、setカラムヘッダ()<BR>
     * にてインスタンスにセットする。<BR>
     * <BR>
     * １）証券会社コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.証券会社コードラベル<BR>
     *    カラム番号： 0<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２）部店コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.部店コードラベル<BR>
     *    カラム番号： 1<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３）口座番号<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.口座番号ラベル<BR>
     *    カラム番号： 2<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ４）扱者コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.扱者コードラベル<BR>
     *    カラム番号： 3<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ５）識別コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.識別コードラベル<BR>
     *    カラム番号： 4<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ６）伝票No<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.伝票Noラベル<BR>
     *    カラム番号： 5<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ７）注文シーケンスNo<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文シーケンスNoラベル<BR>
     *    カラム番号： 6<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ８）銘柄コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.銘柄コードラベル<BR>
     *    カラム番号： 7<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ９）現地銘柄コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.現地銘柄コードラベル<BR>
     *    カラム番号： 8<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １０）銘柄名<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.銘柄名ラベル<BR>
     *    カラム番号： 9<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １１）市場コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.市場コードラベル<BR>
     *    カラム番号： 10<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １２）市場名<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.市場名ラベル<BR>
     *    カラム番号： 11<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １３）売買<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.売買ラベル<BR>
     *    カラム番号： 12<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １４）注文株数<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文株数ラベル<BR>
     *    カラム番号： 13<BR>
     *    項目型： CSVカラムモデル.項目型_数値（int）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １５）指値・成行<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.指値・成行ラベル<BR>
     *    カラム番号： 14<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １６）単価<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.単価ラベル<BR>
     *    カラム番号： 15<BR>
     *    項目型： CSVカラムモデル.項目型_数値（double）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １７）決済区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.決済区分ラベル<BR>
     *    カラム番号： 16<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １８）通貨コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.通貨コードラベル<BR>
     *    カラム番号： 17<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * １９）通貨名称<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.通貨名称ラベル<BR>
     *    カラム番号： 18<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２０）注文日時<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文日時ラベル<BR>
     *    カラム番号： 19<BR>
     *    項目型： CSVカラムモデル.項目型_日付時間<BR>
     *    日付フォーマット： new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * ２１）発注日<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注日ラベル<BR>
     *    カラム番号： 20<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２２）執行条件<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.執行条件ラベル<BR>
     *    カラム番号： 21<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２３）発注条件<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件ラベル<BR>
     *    カラム番号： 22<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２４）発注条件演算子<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件演算子ラベル<BR>
     *    カラム番号： 23<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２５）発注条件単価<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件単価ラベル<BR>
     *    カラム番号： 24<BR>
     *    項目型： CSVカラムモデル.項目型_数値（double）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２６）（W指値）訂正指値<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.（W指値）訂正指値ラベル<BR>
     *    カラム番号： 25<BR>
     *    項目型： CSVカラムモデル.項目型_数値（double）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２７）注文状態<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文状態ラベル<BR>
     *    カラム番号： 26<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２８）約定区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.約定区分ラベル<BR>
     *    カラム番号： 27<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２９）訂正取消区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.訂正取消区分ラベル<BR>
     *    カラム番号： 28<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３０）注文チャネル<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文チャネルラベル<BR>
     *    カラム番号： 29<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３１）ファ@クター<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.ファ@クターラベル<BR>
     *    カラム番号： 30<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３２）手数料No<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.手数料Noラベル<BR>
     *    カラム番号： 31<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３３）手数料No枝番<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.手数料No枝番ラベル<BR>
     *    カラム番号： 32<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３４）商品コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.商品コードラベル<BR>
     *    カラム番号： 33<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３５）注文経路区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文経路区分ラベル<BR>
     *    カラム番号： 34<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３６）メール区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.メール区分ラベル<BR>
     *    カラム番号： 35<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３７）特定口座区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.特定口座区分ラベル<BR>
     *    カラム番号： 36<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３８）運用コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.運用コードラベル<BR>
     *    カラム番号： 37<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * @@roseuid 42A0399403B8
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 38;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //１）証券会社コード 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.institutionCodeLabel, 
            0, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //２）部店コード 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel, 
            1, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //３）口座番号
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNoLabel, 
            2, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //４）扱者コード
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.traderCodeLabel, 
            3, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //５）識別コード
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.orderRequestNumberLabel, 
            4, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //６）伝票No 
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.voucherNoLabel, 
            5, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //７）注文シーケンスNo
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.orderSequenceNoLabel, 
            6, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //８）銘柄コード
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.productCodeLabel, 
            7, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //９）現地銘柄コード
        l_models[8] = new WEB3GentradeCsvColumnModel(
            this.offshoreProductCodeLabel, 
            8, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１０）銘柄名
        l_models[9] = new WEB3GentradeCsvColumnModel(
            this.productNameLabel, 
            9, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１１）市場コード
        l_models[10] = new WEB3GentradeCsvColumnModel(
            this.marketCodeLabel, 
            10, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１２）市場名
        l_models[11] = new WEB3GentradeCsvColumnModel(
            this.marketNameLabel, 
            11, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１３）売買
        l_models[12] = new WEB3GentradeCsvColumnModel(
            this.tradeLabel, 
            12, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１４）注文株数
        l_models[13] = new WEB3GentradeCsvColumnModel(
            this.orderQuantityLabel, 
            13, 
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null); 

        //１５）指値・成行
        l_models[14] = new WEB3GentradeCsvColumnModel(
            this.limitPriceOrMarketPriceLabel, 
            14, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１６）単価
        l_models[15] = new WEB3GentradeCsvColumnModel(
            this.priceLabel, 
            15, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //１７）決済区分
        l_models[16] = new WEB3GentradeCsvColumnModel(
            this.settleDivLabel, 
            16, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //１８）通貨コード
        l_models[17] = new WEB3GentradeCsvColumnModel(
            this.currencyCodeLabel, 
            17, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);  

        //１９）通貨名称
        l_models[18] = new WEB3GentradeCsvColumnModel(
            this.currencyNameLabel, 
            18, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２０）注文日時
        l_models[19] = new WEB3GentradeCsvColumnModel(
            this.orderTimestampLabel, 
            19, 
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")); 

        //２１）発注日
        l_models[20] = new WEB3GentradeCsvColumnModel(
            this.bizDateLabel, 
            20, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２２）執行条件
        l_models[21] = new WEB3GentradeCsvColumnModel(
            this.execCondLabel, 
            21, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２３）発注条件
        l_models[22] = new WEB3GentradeCsvColumnModel(
            this.orderCondLabel, 
            22, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２４）発注条件演算子
        l_models[23] = new WEB3GentradeCsvColumnModel(
            this.orderCondOperatorLabel, 
            23, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２５）発注条件単価
        l_models[24] = new WEB3GentradeCsvColumnModel(
            this.orderCondPriceLabel, 
            24, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //２６）（W指値）訂正指値
        l_models[25] = new WEB3GentradeCsvColumnModel(
            this.wLimitPriceLabel, 
            25, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //２７）注文状態
        l_models[26] = new WEB3GentradeCsvColumnModel(
            this.orderStatusLabel, 
            26, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);  

        //２８）約定区分
        l_models[27] = new WEB3GentradeCsvColumnModel(
            this.execDivLabel, 
            27, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //２９）訂正取消区分
        l_models[28] = new WEB3GentradeCsvColumnModel(
            this.changeCancelDivLabel, 
            28, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３０）注文チャネル
        l_models[29] = new WEB3GentradeCsvColumnModel(
            this.orderChanelLabel, 
            29, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３１）ファ@クター
        l_models[30] = new WEB3GentradeCsvColumnModel(
            this.factorLabel, 
            30, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３２）手数料No
        l_models[31] = new WEB3GentradeCsvColumnModel(
            this.commisionNumberLabel, 
            31, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３３）手数料No枝番
        l_models[32] = new WEB3GentradeCsvColumnModel(
            this.commisionBranchNumberLabel, 
            32, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３４）商品コード
        l_models[33] = new WEB3GentradeCsvColumnModel(
            this.productNumberLabel, 
            33, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３５）注文経路区分 
        l_models[34] = new WEB3GentradeCsvColumnModel(
            this.orderRootDivLabel, 
            34, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //３６）メール区分
        l_models[35] = new WEB3GentradeCsvColumnModel(
            this.mailDivLabel, 
            35, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３７）特定口座区分
        l_models[36] = new WEB3GentradeCsvColumnModel(
            this.taxTypeLabel, 
            36, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //３８）運用コード
        l_models[37] = new WEB3GentradeCsvColumnModel(
            this.orderEmpCodeLabel, 
            37, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set証券会社コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.証券会社コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.証券会社コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@roseuid 42A9724E006C
     */
    public void setInstitutionCode(int l_intLineNo, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.institutionCodeLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strInstitutionCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set部店コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.部店コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.部店IDに該当する部店.getBranchCode()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@roseuid 42A97334001D
     */
    public void setBranchCode(int l_intLineNo, long l_lngBranchId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setBranchCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.branchCodeLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            if (l_accountMgr == null)
            {
                log.debug("アカウントマネージャが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "アカウントマネージャが存在しない。");
            }
                
            Branch l_branch = l_accountMgr.getBranch(l_lngBranchId);//NotFoundException
                
            if (l_branch == null)
            {
                log.debug("部店が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "部店が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_branch.getBranchCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set口座番号)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.口座番号ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.口座IDに該当する顧客.get表示顧客コード()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@roseuid 42A9737800F8
     */
    public void setAccountNo(int l_intLineNo, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccountNo(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.accountNoLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            if (l_accountMgr == null)
            {
                log.debug("アカウントマネージャが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "アカウントマネージャが存在しない。");
            }
                
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);//NotFoundException
                
            if (l_mainAccount == null)
            {
                log.debug("顧客が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "顧客が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_mainAccount.getDisplayAccountCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set扱者コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.扱者コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.扱者IDに該当する扱者.getTraderCode()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngTraderId - (扱者ID)<BR>
     * 扱者ID
     * @@roseuid 42A973F501F2
     */
    public void setTraderCode(int l_intLineNo, long l_lngTraderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setTraderCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.traderCodeLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManagerが存在しない。");
            }
            
            if (l_lngTraderId == 0)
            {
                this.setValue(l_intLineNo, l_model, null);
            }
            else
            {
                Trader l_trader = l_finObjMgr.getTrader(l_lngTraderId);//NotFoundException
                
                if (l_trader == null)
                {
                    log.debug("扱者が存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                         "扱者が存在しない。");
                }
                
                this.setValue(l_intLineNo, l_model, l_trader.getTraderCode());
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set識別コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.識別コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.識別コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@roseuid 42A9746D00C9
     */
    public void setOrderRequestNumber(int l_intLineNo, String l_strOrderRequestNumber) 
    {
        final String STR_METHOD_NAME = " setOrderRequestNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderRequestNumberLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderRequestNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set伝票No)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.伝票Noラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.伝票No<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strVoucheNo - (伝票No)<BR>
     * 伝票No<BR>
     * @@roseuid 42A974960250
     */
    public void setVoucherNo(int l_intLineNo, String l_strVoucheNo) 
    {
        final String STR_METHOD_NAME = " setVoucherNo(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.voucherNoLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strVoucheNo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set注文シーケンスNo)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文シーケンスNoラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文シーケンスNo<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderSequenceNo - (注文シーケンスNo)<BR>
     * 注文シーケンスNo<BR>
     * @@roseuid 42A974BF02DD
     */
    public void setOrderSequenceNo(int l_intLineNo, String l_strOrderSequenceNo) 
    {
        final String STR_METHOD_NAME = " setOrderSequenceNo(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderSequenceNoLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderSequenceNo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set銘柄コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.銘柄コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.銘柄IDに該当する外国株式銘柄.getProductCode()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@roseuid 42A9751E0166
     */
    public void setProductCode(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setProductCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.productCodeLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.exiting("TradingModuleが存在しない。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModuleが存在しない。");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManagerが存在しない。");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("銘柄が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "銘柄が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getProductCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set現地銘柄コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.現地銘柄コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.銘柄IDに該当する外国株式銘柄.get現地銘柄コード()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@roseuid 42A975980379
     */
    public void setOffshoreProductCode(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setOffshoreProductCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.offshoreProductCodeLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModuleが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModuleが存在しない。");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManagerが存在しない。");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("銘柄が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "銘柄が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getOffshoreProductCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set銘柄名)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.銘柄名ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.銘柄IDに該当する外国株式銘柄.get表示銘柄名()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@roseuid 42A9763201B4
     */
    public void setProductName(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setProductName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.productNameLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModuleが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModuleが存在しない。");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManagerが存在しない。");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("銘柄が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "銘柄が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getDisplayProductName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set市場コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.市場コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.市場IDに該当する市場.getMarketCode()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngMarketId - (市場ID)<BR>
     * 市場ID<BR>
     * @@roseuid 42A9767201E3
     */
    public void setMarketCode(int l_intLineNo, long l_lngMarketId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMarketCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.marketCodeLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManagerが存在しない。");
            }
                
            Market l_market = l_finObjMgr.getMarket(l_lngMarketId);//NotFoundException
                
            if (l_market == null)
            {
                log.debug("市場が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "市場が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_market.getMarketCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set市場名)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.市場名ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.市場IDに該当する市場.getMarketName()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngMarketId - (市場ID)<BR>
     * 市場ID<BR>
     * @@roseuid 42A9772B03A8
     */
    public void setMarketName(int l_intLineNo, long l_lngMarketId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMarketName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.marketNameLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManagerが存在しない。");
            }
                
            Market l_market = l_finObjMgr.getMarket(l_lngMarketId);//NotFoundException
                
            if (l_market == null)
            {
                log.debug("市場が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "市場が存在しない。");
            }
                
            this.setValue(l_intLineNo, l_model, l_market.getMarketName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set売買)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.売買ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.is買付 == true の場合、"2"<BR>
     *          引数.is買付 == false の場合、"1"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付注文かどうかのフラグ<BR>
     * @@roseuid 42A977C00270
     */
    public void setTrade(int l_intLineNo, boolean l_blnIsBuy) 
    {
        final String STR_METHOD_NAME = " setTrade(int, boolean) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.tradeLabel);
        
        //２）　@値セット
        if (l_blnIsBuy)
        {
            this.setValue(l_intLineNo, l_model, WEB3TransactionTypeDef.BUY);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3TransactionTypeDef.SELL);
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set注文株数)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文株数ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文株数<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngOrderQuantity - (注文株数)<BR>
     * 注文株数<BR>
     * @@roseuid 42A97EE70108
     */
    public void setOrderQuantity(int l_intLineNo, long l_lngOrderQuantity) 
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, long) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderQuantityLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, new Long(l_lngOrderQuantity));       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set指値・成行)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.指値・成行ラベル<BR><BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （以下のとおり）<BR>
     *         引数.指値 == 0 の場合、”成行”<BR>
     *         引数.指値 != 0 の場合、”指値”<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@roseuid 42A97F2303C7
     */
    public void setLimitPriceOrMarketPrice(int l_intLineNo, double l_lngLimitPrice) 
    {
        final String STR_METHOD_NAME = " setLimitPriceOrMarketPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.limitPriceOrMarketPriceLabel);
        
        //２）　@値セット
        if (l_lngLimitPrice == 0)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderPriceDivDef.MARKET_PRICE);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderPriceDivDef.LIMIT_PRICE);
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set単価)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.単価ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.指値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@roseuid 42A97F5A0389
     */
    public void setPrice(int l_intLineNo, double l_lngLimitPrice) 
    {
        final String STR_METHOD_NAME = " setPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.priceLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, new Double(l_lngLimitPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set決済区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.決済区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.決済区分<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@roseuid 42A97F8E032B
     */
    public void setSettleDiv(int l_intLineNo, String l_strSettleDiv) 
    {
        final String STR_METHOD_NAME = " setSettleDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.settleDivLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strSettleDiv);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set通貨コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.通貨コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.通貨コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@roseuid 42A97FD902BE
     */
    public void setCurrencyCode(int l_intLineNo, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " setCurrencyCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.currencyCodeLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strCurrencyCode);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set通貨名称)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.通貨名称ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.銘柄IDに該当する銘柄.get通貨().get通貨名()の戻り値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@roseuid 42A97FF5007C
     */
    public void setCurrencyName(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCurrencyName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.currencyNameLabel);
            
            //２）　@値セット
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModuleが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModuleが存在しない。");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManagerが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManagerが存在しない。");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("銘柄が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "銘柄が存在しない。");
            }
                
            WEB3GentradeCurrency l_currency = l_product.getCurrency();

            this.setValue(l_intLineNo, l_model, l_currency.getCurrencyName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set注文日時)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文日時ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文日時<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_datOrderTimestamp - (注文日時)<BR>
     * 注文日時<BR>
     * @@roseuid 42A9815A03E7
     */
    public void setOrderTimestamp(int l_intLineNo, Date l_datOrderTimestamp) 
    {
        final String STR_METHOD_NAME = " setOrderTimestamp(int, Date) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderTimestampLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_datOrderTimestamp);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set発注日)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注日ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.発注日<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@roseuid 42A9817A00D9
     */
    public void setBizDate(int l_intLineNo, String l_strBizDate) 
    {
        final String STR_METHOD_NAME = " setOrderTimestamp(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.bizDateLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strBizDate);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set執行条件)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.執行条件ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.執行条件<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strExecCond - (執行条件)<BR>
     * 執行条件<BR>
     * @@roseuid 42A981A10250
     */
    public void setExecCond(int l_intLineNo, String l_strExecCond) 
    {
        final String STR_METHOD_NAME = " setExecCond(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.execCondLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strExecCond);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set発注条件)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.発注条件<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderCond - (発注条件)<BR>
     * 発注条件<BR>
     * @@roseuid 42A981CC0241
     */
    public void setOrderCond(int l_intLineNo, String l_strOrderCond) 
    {
        final String STR_METHOD_NAME = " setOrderCond(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderCond);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set発注条件演算子)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件演算子ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.発注条件演算子<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@roseuid 42A981E302AE
     */
    public void setOrderCondOperator(int l_intLineNo, String l_strOrderCondOperator) 
    {
        final String STR_METHOD_NAME = " setOrderCondOperator(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondOperatorLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderCondOperator);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set発注条件単価)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.発注条件単価ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.発注条件単価<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_orderCondPrice - (発注条件単価)<BR>
     * 発注条件単価<BR>
     * @@roseuid 42A981F90398
     */
    public void setOrderCondPrice(int l_intLineNo, double l_orderCondPrice) 
    {
        final String STR_METHOD_NAME = " setOrderCondPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondPriceLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, new Double(l_orderCondPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }

    public void setOrderCondPrice(int l_intLineNo) 
    {
        final String STR_METHOD_NAME = " setOrderCondPrice(int) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondPriceLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, null);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.（W指値）訂正指値ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.（W指値）訂正指値<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_wLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@roseuid 42A98269032B
     */
    public void setWLimitPrice(int l_intLineNo, double l_wLimitPrice) 
    {
        final String STR_METHOD_NAME = " setWLimitPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.wLimitPriceLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, new Double(l_wLimitPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }

    public void setWLimitPrice(int l_intLineNo) 
    {
        final String STR_METHOD_NAME = " setWLimitPrice(int) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.wLimitPriceLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, null);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set注文状態)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文状態ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値：（以下のとおり）<BR>
     *       引数.注文状態が以下の場合、”受付未済”をセットする。<BR>
     * <BR>
     *          受付済（新規注文）、発注中（新規注文）<BR>
     *          受付済（変更注文）、発注中（変更注文）<BR>
     *          受付済（取消注文）、発注中（取消注文）<BR>
     * <BR>
     *       引数.注文状態が以下の場合、”受付済”をセットする。<BR> 
     * <BR>
     *          発注済（新規注文）<BR> 
     * <BR>
     *       引数.注文状態が以下の場合、”受付エラー”をセットする。<BR> 
     * <BR>
     *          発注失敗（新規注文）<BR> 
     *          発注失敗（変更注文）<BR> 
     *          発注失敗（取消注文）<BR> 
     * <BR>
     *       引数.注文状態が以下の場合、”訂正・取消されたデータ”をセットする。<BR> 
     * <BR>
     *          発注済（変更注文）<BR> 
     *          発注済（取消注文）<BR> 
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderStatus - (注文状態)<BR>
     * 注文状態<BR>
     * @@roseuid 42A9829F02DD
     */
    public void setOrderStatus(int l_intLineNo, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = " setOrderStatus(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderStatusLabel);
        
        //２）　@値セット
        String l_strOrderStatusInput = null;
        
        //引数.注文状態が以下の場合、”受付未済”をセットする。
        //受付済（新規注文）、発注中（新規注文）
        //受付済（変更注文）、発注中（変更注文）
        //受付済（取消注文）、発注中（取消注文）
        if (Integer.toString(OrderStatusEnum.ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFY_ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCEL_ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.ORDERING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFYING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCELLING.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN;  
        }
        //引数.注文状態が以下の場合、”受付済”をセットする。
        //発注済（新規注文）
        else if (Integer.toString(OrderStatusEnum.ORDERED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN;  
        }
        //引数.注文状態が以下の場合、”受付エラー”をセットする。
        //発注失敗（新規注文）
        //発注失敗（変更注文）
        //発注失敗（取消注文）
        else if (Integer.toString(OrderStatusEnum.NOT_ORDERED.intValue()).equals(l_strOrderStatus) || 
            Integer.toString(OrderStatusEnum.NOT_MODIFIED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.NOT_CANCELLED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR;  
        }
        //引数.注文状態が以下の場合、”訂正・取消されたデータ”をセットする。 
        //発注済（変更注文）
        //発注済（取消注文）
        else if (Integer.toString(OrderStatusEnum.MODIFIED.intValue()).equals(l_strOrderStatus) || 
            Integer.toString(OrderStatusEnum.CANCELLED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.CNANGE_CANCELED_DATA;  
        }
        
        this.setValue(l_intLineNo, l_model, l_strOrderStatusInput);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set約定区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.約定区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （以下のとおり）<BR>
     *          引数.約定数量 == 0 の場合、”未約定”<BR>
     *          引数.約定数量 > 0 and 引数.約定数量 < 引数.注文数量 <BR>
     *          の場合、”一部約定”<BR>
     *          引数.約定数量 == 引数.注文数量 の場合、”全部約定”<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * 
     * @@param l_lngExecQuantity - (約定数量)<BR>
     * 約定数量<BR>
     * @@roseuid 42A982BA036A
     */
    public void setExecDiv(int l_intLineNo, long l_lngOrderQuantity, long l_lngExecQuantity) 
    {
        final String STR_METHOD_NAME = " setExecDiv(int, long, long) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.execDivLabel);
        
        //２）　@値セット
        if (l_lngExecQuantity == 0)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.UNEXECUTED);
        }
        else if (l_lngExecQuantity > 0 && l_lngExecQuantity < l_lngOrderQuantity)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);
        }  
        else if (l_lngOrderQuantity == l_lngExecQuantity) 
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.EXECUTED);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set訂正取消区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.訂正取消区分ラベル<BR>
     * <BR>
     * ２）”訂正後新規注文”かどうかの判定<BR>
     * <BR>
     * 以下の条件で、外株訂正状況テーブルを検索する。<BR> 
     * <BR>
     * [条件]<BR> 
     *   口座ID = 引数.口座ID <BR>
     *   新規注文ID = 引数.注文ID <BR>
     * <BR>
     *   該当レコードが存在した場合、この注文は”訂正後新規注文”とする。<BR> 
     * <BR>
     * ３）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （以下のとおり）<BR>
     *    ２）で”訂正後新規注文”と判定された場合、”訂正後新規注文”<BR>
     *    それ以外の場合、引数.訂正取消区分 <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_strChangeCancelDiv - (訂正取消区分)<BR>
     * 訂正取消区分<BR>
     * @@throws 
     * @@roseuid 42A982D401F3
     */
    public void setChangeCancelDiv(
        int l_intLineNo, 
        long l_lngAccountId, 
        long l_lngOrderId, 
        String l_strChangeCancelDiv) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setChangeCancelDiv(int, long, long, String) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１）　@カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.changeCancelDivLabel);
        
            //２）”訂正後新規注文”かどうかの判定
            //以下の条件で、外株訂正状況テーブルを検索する。
            //[条件]
            //口座ID = 引数.口座ID
            //新規注文ID = 引数.注文ID 
            String l_strWhere = " account_id = ? and new_order_id = ? ";
            Object[] l_objs = {new Long(l_lngAccountId), new Long(l_lngOrderId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisFeqOrderChangeStatusRows = l_queryProcessor.doFindAllQuery(
                FeqOrderChangeStatusRow.TYPE, 
                l_strWhere,
                l_objs);//DataNetworkException, DataQueryException
            
            boolean l_blnIsNewOrderAfterChanged = false;
            
            //該当レコードが存在した場合、この注文は”訂正後新規注文”とする。    
            if (l_lisFeqOrderChangeStatusRows != null && 
                !l_lisFeqOrderChangeStatusRows.isEmpty())
            {
                l_blnIsNewOrderAfterChanged = true;
            }
        
            //３）　@値セット
            if (l_blnIsNewOrderAfterChanged)
            {
                //２）で”訂正後新規注文”と判定された場合、”訂正後新規注文”
                this.setValue(l_intLineNo, l_model, WEB3FeqChangeCancelDivDef.CHANGED_NEW_ORDER);
            }
            else
            {
                //それ以外の場合、引数.訂正取消区分
                this.setValue(l_intLineNo, l_model, l_strChangeCancelDiv);
            }            
        
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }
    
    /**
     * (set注文チャネル)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文チャネルラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文チャネル<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderChanel - (注文チャネル)<BR>
     * 注文チャネル<BR>
     * @@roseuid 42A982EE0212
     */
    public void setOrderChanel(int l_intLineNo, String l_strOrderChanel) 
    {
        final String STR_METHOD_NAME = " setOrderChanel(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderChanelLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderChanel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setファ@クター)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.ファ@クターラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.ファ@クター<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strFactor - (ファ@クター)<BR>
     * ファ@クター<BR>
     * @@roseuid 42A9830B01B4
     */
    public void setFactor(int l_intLineNo, String l_strFactor) 
    {
        final String STR_METHOD_NAME = " setFactor(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.factorLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strFactor);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set手数料No)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.手数料Noラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.手数料No<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strCommisionNumber - (手数料No)<BR>
     * 手数料No<BR>
     * @@roseuid 42A9832401E3
     */
    public void setCommisionNumber(int l_intLineNo, String l_strCommisionNumber) 
    {
        final String STR_METHOD_NAME = " setCommisionNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.commisionNumberLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strCommisionNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set手数料No枝番)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.手数料No枝番ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.手数料No枝番<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strCommisionBranchNumber - (手数料No枝番)<BR>
     * 手数料No枝番<BR>
     * @@roseuid 42A9833F027F
     */
    public void setCommisionBranchNumber(int l_intLineNo, String l_strCommisionBranchNumber) 
    {
        final String STR_METHOD_NAME = " setCommisionBranchNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.commisionBranchNumberLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strCommisionBranchNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set商品コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.商品コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.商品コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strProductNumber - (商品コード)<BR>
     * 商品コード<BR>
     * @@roseuid 42A9835D006C
     */
    public void setProductNumber(int l_intLineNo, String l_strProductNumber) 
    {
        final String STR_METHOD_NAME = " setProductNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.productNumberLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strProductNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set注文経路区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.注文経路区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文経路区分<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     * @@roseuid 42A9837E0398
     */
    public void setOrderRootDiv(int l_intLineNumber, String l_strOrderRootDiv) 
    {
        final String STR_METHOD_NAME = " setOrderRootDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderRootDivLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strOrderRootDiv);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setメール区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.メール区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （以下のとおり）<BR>
     *         引数.注文状態 == （”発注中（新規注文）” or ”発注中（訂正注文）” or<BR>
     * 　@　@　@　@ ”発注中（取消注文）”） の場合、”未送信”<BR>
     *         それ以外の場合、”送信完了”<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderStatus - (注文状態)<BR>
     * 注文状態<BR>
     * @@roseuid 42A9839A033B
     */
    public void setMailDiv(int l_intLineNo, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = " setMailDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.mailDivLabel);
        
        //２）　@値セット
        if (Integer.toString(OrderStatusEnum.ORDERING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFYING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCELLING.intValue()).equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNo, l_model, WEB3MqStatusDef.NOT_SEND_MAIL);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3MqStatusDef.MAIL_SENDED);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set特定口座区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.特定口座区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （以下のとおり）<BR>
     *       引数.特定口座区分（注文単位.税区分） == ”一般” の場合、”一般”<BR>
     *       引数.特定口座区分（注文単位.税区分） == ”特定” の場合、”特定”<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strTaxType - (特定口座区分)<BR>
     * 特定口座区分<BR>
     * @@roseuid 42A983F40231
     */
    public void setTaxType(int l_intLineNo, String l_strTaxType) 
    {
        final String STR_METHOD_NAME = " setTaxType(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.taxTypeLabel);
        
        //２）　@値セット
        String l_strTaxTypeInput = null;
        //引数.特定口座区分（注文単位.税区分） == ”一般” の場合、”一般”
        if (Integer.toString(TaxTypeEnum.NORMAL.intValue()).equals(l_strTaxType))
        {
            l_strTaxTypeInput = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (Integer.toString(TaxTypeEnum.SPECIAL.intValue()).equals(l_strTaxType))
        {
            l_strTaxTypeInput = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        
        this.setValue(l_intLineNo, l_model, l_strTaxTypeInput);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set運用コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 当日注文伝票CSV.運用コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.運用コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@roseuid 42A98411035A
     */
    public void setOrderEmpCode(int l_intLineNo, String l_strOrderEmpCode) 
    {
        final String STR_METHOD_NAME = " setOrderEmpCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderEmpCodeLabel);
        
        //２）　@値セット
        this.setValue(l_intLineNo, l_model, l_strOrderEmpCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getCSVファ@イル行)<BR>
     * （getCSVファ@イル行のオーバーライド）<BR>
     * <BR>
     * CSVファ@イルに出力するデータを、行毎の配列にて返却する。<BR>
     * <BR>
     * １）スーパークラスのgetCSVファ@イル行()メソッドをコールする。<BR>
     * <BR>
     * ２）取得した配列から空行、ブランクのみの行を除外する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * @@return String[]
     * @@roseuid 42AFD4F5007C
     */
    public String[] getCSVFileRow() 
    {
        //１）スーパークラスのgetCSVファ@イル行()メソッドをコールする。
        String[] l_strCsvFileLines = super.getCsvFileLines();
        
        //２）取得した配列から空行、ブランクのみの行を除外する。
        List l_lisLines = new ArrayList();
        String[] l_strLines = null;
        
        if (l_strCsvFileLines != null && l_strCsvFileLines.length > 0)
        {
            int l_intLinesCnt = l_strCsvFileLines.length;
            for (int i = 0; i < l_intLinesCnt; i++)
            {
                if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strCsvFileLines[i]))
                {
                    l_lisLines.add(l_strCsvFileLines[i]);
                }
            }
            
            if (l_lisLines.size() > 0)
            {
                l_strLines = new String[l_lisLines.size()];
                l_lisLines.toArray(l_strLines);
            }
        }         
        
        //３）２）の結果を返却する。
        return l_strLines;
    }
    
    /**
     * (isカラムヘッダ行出力)<BR>
     * CSVファ@イルにカラムヘッダ行の出力要否を判定する。<BR>
     * （オーバーライドメソッド）<BR>
     * <BR>
     * falseを返却する。<BR>
     * 
     * @@return boolean 
     */
    public boolean isColumnHeadOutput()
    {
        return false;
    }
}
@
