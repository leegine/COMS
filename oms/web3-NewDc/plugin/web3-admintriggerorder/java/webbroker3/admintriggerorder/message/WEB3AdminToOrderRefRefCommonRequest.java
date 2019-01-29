head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToOrderRefRefCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・注文照会共通リクエスト(WEB3AdminToOrderRefRefCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
                 : 2006/08/23　@肖志偉(中訊) 仕様変更モデルNo.066,071
                 : 2006/10/18  唐性峰(中訊) 仕様変更モデルNo.094
                 : 2006/11/30 周捷(中訊) 仕様変更モデルNo.117
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・注文照会共通リクエスト)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToOrderRefRefCommonRequest extends WEB3GenRequest
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToOrderRefRefCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_order_ref_ref_common";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
        
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId = null;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode = null;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode = null;
    
    /**
     * (商品区分)<BR>
     * 商品区分 <BR>
     * <BR>
     * 1：　@現物株式 <BR>
     * 2：　@信用取引 <BR>
     * 3：　@先物 <BR>
     * 4：　@オプション <BR>
     */
    public String productDiv = null;
    
    /**
     * (条件注文種別)<BR>
     * 条件注文種別<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     */
    public String triggerOrderType;
    
    /**
     * (発注状況区分)<BR>
     * 発注状況区分<BR>
     * <BR>
     * 1：　@待機@中<BR>
     * 2：　@発注中 <BR>
     * 3：　@発注完了<BR>
     * 8：　@発注審査エラー<BR>
     * 9：　@発注遅延エラー<BR>
     * 13：　@ストップ注文失効<BR>
     */
    public String triggerOrderState = null;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate = null;
    
    /**
     * (時価情報受信時間From)<BR>
     * 時価情報受信時間From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String currentPriceInfoAcceptTimeFrom = null;
    
    /**
     * (時価情報受信時間To)<BR>
     * 時価情報受信時間To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String currentPriceInfoAcceptTimeTo = null;
    
    /**
     * (トリガー起動時間From)<BR>
     * トリガー起動時間From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String triggerStartTimeFrom = null;
    
    /**
     * (トリガー起動時間To)<BR>
     * トリガー起動時間To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String triggerStartTimeTo = null;
    
    /**
     * (発注完了時間From)<BR>
     * 発注完了時間From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String orderCompleteTimeFrom = null;
    
    /**
     * (発注完了時間To)<BR>
     * 発注完了時間To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String orderCompleteTimeTo = null;
    
    /**
     * (み値照合時間From)<BR>
     * 歩み値照合時間From<BR> 
     * (YYYYMMDDhhmmss)<BR>
     */
    public String tickMatchTimeFrom = null;
    
    /**
     * (歩み値照合時間To)<BR>
     * 歩み値照合時間To <BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String tickMatchTimeTo = null;

    /**
     * (歩み値照合区分)<BR>
     * 歩み値照合区分 <BR>
     * <BR>
     * 1：　@未発注疑い <BR>
     * 2：　@不正発注疑い<BR>
     * 3：　@発注遅延疑い<BR>
     * 9：　@全てのエラー <BR>
     * <BR>
     * ※「0：　@正常」は設定されない<BR>
     */
    public String tickMatchDiv = null;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号 <BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数 <BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     */
    public WEB3AdminToOrderRefSortKey[] sortKeys;
    
    /**
     * (乖離時間一覧)<BR>
     */
    public WEB3AdminToDifferentTimeUnit[] differentTimeList;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C70399
     */
    public WEB3AdminToOrderRefRefCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@注文IDチェック <BR>
     * 　@this.注文ID≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１－１）this.注文ID≠数字の場合、 <BR>
     * 　@　@　@　@　@「注文IDが数字以外」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01476<BR>
     * <BR>
     * ２）　@部店コードチェック <BR>
     * 　@２－１）this.部店コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@２－２）this.部店コード.length＝0の場合、 <BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02175<BR>
     * <BR>
     * 　@２－３）this.部店コードの要素数分以下の処理を行う。 <BR>
     * 　@　@２－３－１）this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@　@・部店コード≠数字 <BR>
     * 　@　@　@　@　@　@　@・部店コード.length≠3 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ３）市場コードチェック<BR>
     * 　@this.市場コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.市場コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・"東京"<BR>
     * 　@　@　@　@・"大阪"<BR>
     * 　@　@　@　@・"名古屋"<BR>
     * 　@　@　@　@・"福岡"<BR>
     * 　@　@　@　@・"札幌"<BR>
     * 　@　@　@　@・"NNM"<BR>
     * 　@　@　@　@・"JASDAQ" <BR>
     * <BR>
     * ４）　@顧客コードチェック <BR>
     * 　@this.顧客コード≠nullの場合、以下のチェックを行う。 <BR>
     * 　@４－１）this.顧客コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「顧客コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・顧客コード≠数字 <BR>
     * 　@　@　@　@・顧客コード.length≠6 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00780<BR>
     * <BR>
     * ５）　@商品区分チェック <BR>
     * 　@this.商品区分≠nullの場合、以下のチェックを行う。 <BR>
     * 　@５－１）this.商品区分に下記の項目以外が設定されていたら、 <BR>
     * 　@　@　@　@「商品区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"現物株式" <BR>
     * 　@　@　@　@・"信用取引" <BR>
     * 　@　@　@　@・"先物" <BR>
     * 　@　@　@　@・"オプション" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01068<BR>
     * <BR>
     * ６）　@条件注文種別チェック<BR>
     * 　@６－１） this.条件注文種別＝nullの場合、<BR>
     * 　@　@　@　@「条件注文種別がnull」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02396<BR>
     * <BR>
     * 　@６－２）this.条件注文種別に下記の項目以外が設定されていた場合、<BR>
     * 　@　@　@　@「条件注文種別が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"連続注文" <BR>
     * 　@　@　@　@・"OCO注文"<BR>
     * 　@　@　@　@・"IFD注文"<BR>
     * 　@　@　@　@・"逆指値注文" <BR>
     * 　@　@　@　@・"W指値注文"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02397<BR>
     * <BR>
     * ７）　@発注状況区分チェック<BR>
     * 　@this.発注状況区分≠nullの場合、以下のチェックを行う。 <BR>
     * 　@７－１）this.発注状況に下記の項目以外が設定されていたら、 <BR>
     * 　@　@　@　@「発注状況が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"待機@中" <BR>
     * 　@　@　@　@・"発注中" <BR>　@
     * 　@　@　@　@・"発注完了" <BR>
     * 　@　@　@　@・"発注審査エラー"<BR>
     * 　@　@　@　@・"発注遅延エラー"<BR>
     * 　@　@　@　@・"ストップ注文失効"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02352<BR>
     * <BR>
     * 　@７－２）this.発注状況区分＝"ストップ注文失効" かつ<BR> 
     * 　@　@　@　@　@this.条件注文種別≠"W指値注文"の場合、<BR> 
     * 　@　@　@「発注状況指定が不正」の例外をスローする。<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02626<BR>
     * <BR>
     * 　@７－３）this.発注状況区分＝"発注審査エラー" かつ <BR>
     * 　@　@　@　@　@this.条件注文種別＝"W指値注文"の場合、 <BR>
     * 　@　@　@「W指値注文は発注状況区分：発注審査エラー指定不可」の <BR>
     * 　@　@　@例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02701<BR>
     * <BR>
     * ８）　@時価情報受信時間Fromチェック <BR>
     * 　@this.時価情報受信時間From≠nullの場合、以下のチェックを行う。 <BR>
     * 　@８－１）this.時価情報受信時間FromをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(時価情報受信時間From)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02354<BR>
     * <BR>
     * ９）　@時価情報受信時間Toチェック <BR>
     * 　@this.時価情報受信時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@９－１）this.時価情報受信時間ToをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(時価情報受信時間To)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02355<BR>
     * <BR>
     * １０）　@時価情報受信時間From/To整合性チェック <BR>
     * 　@this.時価情報受信時間From≠null かつ <BR>
     * 　@this.時価情報受信時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１０－１）this.時価情報受信時間From > this.時価情報受信時間Toの場合、 <BR>
     * 　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * １１）　@トリガー起動時間Fromチェック <BR>
     * 　@this.トリガー起動時間From≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１１－１）this.トリガー起動時間FromをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(トリガー起動時間From)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02356<BR>
     * <BR>
     * １２）　@トリガー起動時間Toチェック <BR>
     * 　@this.トリガー起動時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１２－１）this.トリガー起動時間ToをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(トリガー起動時間To)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02357<BR>
     * <BR>
     * １３）　@トリガー起動時間From/To整合性チェック <BR>
     * 　@this.トリガー起動時間From≠null かつ <BR>
     * 　@this.トリガー起動時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１３－１）this.トリガー起動時間From > this.トリガー起動時間Toの場合、 <BR>
     * 　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * １４）　@発注完了時間Fromチェック <BR>
     * 　@this.発注完了時間From≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１４－１）this.発注完了時間FromをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(発注完了時間From)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02358<BR>
     * <BR>
     * １５）　@発注完了時間Toチェック <BR>
     * 　@this.発注完了時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１５－１）this.発注完了時間ToをDate型に変換できなかった場合、 <BR>
     * 　@　@　@　@　@「入力時間エラー(発注完了時間To)」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02359<BR>
     * <BR>
     * １６）　@発注完了時間From/To整合性チェック <BR>
     * 　@this.発注完了時間From≠null かつ <BR>
     * 　@this.発注完了時間To≠nullの場合、以下のチェックを行う。 <BR>
     * 　@１６－１）this.発注完了時間From > this.発注完了時間Toの場合、 <BR>
     * 　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * １７）　@歩み値照合時間Fromチェック<BR>  
     * 　@this.歩み値照合時間From≠nullの場合、以下のチェックを行う。<BR>  
     * 　@１７－１）this.歩み値照合時間FromをDate型に変換できなかった場合、<BR>  
     * 　@　@　@　@　@「入力時間エラー(歩み値照合時間From)」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02627<BR>
     * <BR>
     * １８）　@歩み値照合時間Toチェック  <BR>
     * 　@this.歩み値照合時間To≠nullの場合、以下のチェックを行う。<BR>  
     * 　@１８－１）this.歩み値照合時間ToをDate型に変換できなかった場合、<BR>  
     * 　@　@　@　@　@「入力時間エラー(歩み値照合時間To)」の例外をスローする。<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02628<BR> 
     * <BR>
     * １９）　@歩み値照合時間From/To整合性チェック  <BR>
     * 　@this.歩み値照合時間From≠null かつ  <BR>
     * 　@this.歩み値照合時間To≠nullの場合、以下のチェックを行う。<BR>  
     * 　@１９－１）this.歩み値照合時間From > this.歩み値照合時間Toの場合、<BR>  
     * 　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>  
     * <BR>
     * ２０）　@歩み値照合区分チェック <BR>
     * 　@this.歩み値照合区分≠nullの場合、以下のチェックを行う。<BR>  
     * 　@２０－１）this.歩み値照合区分に下記の項目以外が設定されていたら、<BR>  
     * 　@　@　@　@「歩み値照合区分が未定義の値」の例外をスローする。<BR>  
     * 　@　@　@　@・"不正発注疑い" <BR>
     * 　@　@　@　@・"未発注疑い"  <BR>
     * 　@　@　@　@・"発注遅延疑い" <BR>
     * 　@　@　@　@・"全てのエラー" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02629<BR>
     * <BR>
     * ２１）　@乖離時間一覧チェック<BR>
     * 　@this.乖離時間一覧≠nullの場合、<BR>
     * 　@乖離時間一覧の全要素に対して下記のチェックを行う。 <BR>
     * 　@　@２１－１）乖離時間.validate()メソッドをコールする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02627<BR>
     * <BR>
     * ２２）　@ソートキーチェック <BR>
     * 　@２２－１）this.ソートキー＝nullであった場合 <BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２２－２）this.ソートキー.length＝0だった場合 <BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@２２－３）this.ソートキーの全要素に対して <BR>
     * 　@　@　@　@下記のチェックを行う。 <BR>
     * 　@　@２２－３－１）ソートキー.validate()をコールする。 <BR>
     * <BR>
     * ２３）要求ページ番号チェック <BR>
     * 　@２３－１）this.要求ページ番号＝nullであった場合、 <BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@２３－２）this.要求ページ番号≠数字の場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@２３－３）this.要求ページ番号 <= 0であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ２４）ページ内表示行数チェック <BR>
     * 　@２４－１）this.ページ内表示行数＝nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@２４－２）this.ページ内表示行数≠数字の場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00092<BR>
     * 　@ <BR>
     * 　@２４－３）this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF145E0370
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文IDチェック
        //　@this.注文ID≠nullの場合、以下のチェックを行う。
        if (this.orderId != null)
        {
            //　@１－１）this.注文ID≠数字の場合、
            //　@　@　@　@　@「注文IDが数字以外」の例外をスローする。
            if (!WEB3StringTypeUtility.isInteger(this.orderId))
            {
                log.debug("注文IDが数字以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文IDが数字以外です。");
            }
        }
        
        //２）　@部店コードチェック
        //　@２－１）this.部店コード＝nullの場合、
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。 
        if (this.branchCode == null)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        //　@２－２）this.部店コード.length＝0の場合、
        //　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。
        if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が0です。");
        }
        
        //　@２－３）this.部店コードの要素数分以下の処理を行う。
        //　@　@２－３－１）this.部店コードが以下の条件に該当する場合、
        //　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。
        //　@　@　@　@　@　@　@・部店コード≠数字
        //　@　@　@　@　@　@　@・部店コード.length≠3 
        int l_intLen = this.branchCode.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!WEB3StringTypeUtility.isInteger(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }

        //　@３）市場コードチェック  
        //　@　@this.市場コード != nullの場合、以下のチェックを行う。  
        //　@　@３－１）this.市場コードに下記の項目以外が設定されていたら、  
        //　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。  
        //　@　@　@　@　@・"東京"  
        //　@　@　@　@　@・"大阪"  
        //　@　@　@　@　@・"名古屋"  
        //　@　@　@　@　@・"福岡"  
        //　@　@　@　@　@・"札幌"  
        //　@　@　@　@　@・"NNM"  
        //　@　@　@　@　@・"JASDAQ" 
        if (this.marketCode != null
            && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
            && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
        {
            log.debug("市場コードが存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場コードが存在しないコード値です。");
        }

        //４）　@顧客コードチェック
        //　@this.顧客コード≠nullの場合、以下のチェックを行う。
        if (this.accountCode != null)
        {
            //４－１）this.顧客コードが以下の条件に該当する場合、
            //　@　@　@「顧客コードエラー」の例外をスローする。
            //　@　@　@・顧客コード≠数字
            //　@　@　@・顧客コード.length≠6
            if (!WEB3StringTypeUtility.isInteger(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("顧客コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }
        
        //５）　@商品区分チェック
        //　@this.商品区分≠nullの場合、以下のチェックを行う。
        if (this.productDiv != null)
        {
            //５－１）this.商品区分に下記の項目以外が設定されていたら、
            //　@　@　@「商品区分が未定義の値」の例外をスローする。
            //　@　@　@・"現物株式"
            //　@　@　@・"信用取引"
            //　@　@　@・"先物"
            //　@　@　@・"オプション"
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDiv)
                || WEB3CommodityDivDef.MARGIN.equals(this.productDiv)
                || WEB3CommodityDivDef.FUTURE.equals(this.productDiv)
                || WEB3CommodityDivDef.OPTION.equals(this.productDiv)))
            {
                log.debug("商品区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "商品区分が存在しないコード値です。");
            }
        }
        
        //６）　@条件注文種別チェック
        //６－１） this.条件注文種別＝nullの場合、
        //　@　@　@「条件注文種別がnull」の例外をスローする。
        if (this.triggerOrderType == null)
        {
            log.debug("条件注文種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未指定です。");
        }
        
        //６－２）this.条件注文種別に下記の項目以外が設定されていた場合、
        //　@　@　@「条件注文種別が未定義の値」の例外をスローする。
        //　@　@　@・"連続注文" 
        //　@　@　@・"OCO注文"
        //　@　@　@・"IFD注文"
        //　@　@　@・"逆指値注文"
        //　@　@　@・"W指値注文"
        if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType)))
        {
            log.debug("条件注文種別が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未定義の値です。");
        }
        
        //７）　@発注状況区分チェック
        //　@this.発注状況区分≠nullの場合、以下のチェックを行う。
        if (this.triggerOrderState != null)
        {
            //７－１）this.発注状況に下記の項目以外が設定されていたら、
            //　@　@　@「発注状況が未定義の値」の例外をスローする。 
            //　@　@　@・"待機@中" 
            //　@　@　@・"発注中" 
            //　@　@　@・"発注完了" 
            //　@　@　@・"発注審査エラー"
            //　@　@　@・"発注遅延エラー"
            //　@　@　@・"ストップ注文失効" 
            if (!(WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDERING.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(this.triggerOrderState)))
            {
                log.debug("発注状況の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02352,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注状況の値が存在しないコード値です。");
            }
            
            //　@７－２）this.発注状況区分＝"ストップ注文失効" かつ 
            //　@　@　@　@　@this.条件注文種別≠"W指値注文"の場合、 
            //　@　@　@「発注状況指定が不正」の例外をスローする。
            if (WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(this.triggerOrderState) 
                && !WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType))
            {
                log.debug("発注状況指定が不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02626,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注状況指定が不正。");
            }
            
            //７－３）this.発注状況区分＝"発注審査エラー" かつ 
            //   this.条件注文種別＝"W指値注文"の場合、 
            // 「W指値注文は発注状況区分：発注審査エラー指定不可」の 
            // 例外をスローする。 
            if (WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(this.triggerOrderState) 
                && WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType))
            {
                log.debug("W指値注文は発注状況区分：発注審査エラー指定不可");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02701,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "W指値注文は発注状況区分：発注審査エラー指定不可");
            }
        }
        
        //８）　@時価情報受信時間Fromチェック
        //　@this.時価情報受信時間From≠nullの場合、以下のチェックを行う。
        if (this.currentPriceInfoAcceptTimeFrom != null)
        {
            //８－１）this.時価情報受信時間FromをDate型に変換できなかった場合、 
            //　@　@　@　@「入力時間エラー(時価情報受信時間From)」の例外をスローする。
            if (!WEB3StringTypeUtility.isDateStr(this.currentPriceInfoAcceptTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(時価情報受信時間From)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02354,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(時価情報受信時間From)。");
            }
        }

        //９）　@時価情報受信時間Toチェック 
        //　@this.時価情報受信時間To≠nullの場合、以下のチェックを行う。 
        if (this.currentPriceInfoAcceptTimeTo != null)
        {
            //９－１）this.時価情報受信時間ToをDate型に変換できなかった場合、 
            //　@　@　@　@「入力時間エラー(時価情報受信時間To)」の例外をスローする。 
            if (!WEB3StringTypeUtility.isDateStr(this.currentPriceInfoAcceptTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(時価情報受信時間To)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02355,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(時価情報受信時間To)。");
            }
        }
        
        //１０）　@時価情報受信時間From/To整合性チェック 
        //　@this.時価情報受信時間From≠null かつ 
        //　@this.時価情報受信時間To≠nullの場合、以下のチェックを行う。 
        if (this.currentPriceInfoAcceptTimeFrom != null && this.currentPriceInfoAcceptTimeTo != null)
        {
            //１０－１）this.時価情報受信時間From > this.時価情報受信時間Toの場合、
            //　@　@　@　@「入力時間整合性エラー」の例外をスローする。 
            if (this.currentPriceInfoAcceptTimeFrom.compareTo(
                this.currentPriceInfoAcceptTimeTo) > 0)
            {
                log.debug("入力時間整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間整合性エラー。");
            }
        }
        
        //１１）　@トリガー起動時間Fromチェック
        //　@this.トリガー起動時間From≠nullの場合、以下のチェックを行う。
        if (this.triggerStartTimeFrom != null)
        {
            //　@１１－１）this.トリガー起動時間FromをDate型に変換できなかった場合、
            //　@　@　@　@　@「入力時間エラー(トリガー起動時間From)」の例外をスローする。
            if (!WEB3StringTypeUtility.isDateStr(this.triggerStartTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(トリガー起動時間From)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02356,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(トリガー起動時間From)。");
            }
        }
        
        //１２）　@トリガー起動時間Toチェック
        //　@this.トリガー起動時間To≠nullの場合、以下のチェックを行う。 
        if (this.triggerStartTimeTo != null)
        {
            //１２－１）this.トリガー起動時間ToをDate型に変換できなかった場合、
            //　@　@　@　@「入力時間エラー(トリガー起動時間To)」の例外をスローする。
            if (!WEB3StringTypeUtility.isDateStr(this.triggerStartTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(トリガー起動時間To)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02357,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(トリガー起動時間To)。");
            }
        }
        
        //１３）　@トリガー起動時間From/To整合性チェック
        //　@this.トリガー起動時間From≠null かつ
        //　@this.トリガー起動時間To≠nullの場合、以下のチェックを行う。
        if (this.triggerStartTimeFrom != null && this.triggerStartTimeTo != null)
        {
            //１３－１）this.トリガー起動時間From > this.トリガー起動時間Toの場合、
            //　@　@　@　@「入力時間整合性エラー」の例外をスローする。
            if (this.triggerStartTimeFrom.compareTo(this.triggerStartTimeTo) > 0)
            {
                log.debug("入力時間整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間整合性エラー。");
            }
        }

        //１４）　@発注完了時間Fromチェック>
        //　@this.発注完了時間From≠nullの場合、以下のチェックを行う。
        if (this.orderCompleteTimeFrom != null)
        {
            //１４－１）this.発注完了時間FromをDate型に変換できなかった場合、
            //　@　@　@　@「入力時間エラー(発注完了時間From)」の例外をスローする。   
            if (!WEB3StringTypeUtility.isDateStr(this.orderCompleteTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(発注完了時間From)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02358,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(発注完了時間From)。");
            }
        }
        
        //１５）　@発注完了時間Toチェック
        //　@this.発注完了時間To≠nullの場合、以下のチェックを行う。
        if (this.orderCompleteTimeTo != null)
        {
            //１５－１）this.発注完了時間ToをDate型に変換できなかった場合、
            //　@　@　@　@「入力時間エラー(発注完了時間To)」の例外をスローする。
            if (!WEB3StringTypeUtility.isDateStr(this.orderCompleteTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("入力時間エラー(発注完了時間To)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02359,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間エラー(発注完了時間To)。");
            }
        }

        //１６）　@発注完了時間From/To整合性チェック
        //　@this.発注完了時間From≠null かつ
        //　@this.発注完了時間To≠nullの場合、以下のチェックを行う。
        if (this.orderCompleteTimeFrom != null && this.orderCompleteTimeTo != null)
        {
            //１６－１）this.発注完了時間From > this.発注完了時間Toの場合、 
            //　@　@　@　@「入力時間整合性エラー」の例外をスローする。
            if (this.orderCompleteTimeFrom.compareTo(this.orderCompleteTimeTo) > 0)
            {
                log.debug("入力時間整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間整合性エラー。");
            }
        }

        //１７）　@歩み値照合時間Fromチェック 
        //　@this.歩み値照合時間From≠nullの場合、以下のチェックを行う。  
        //　@１７－１）this.歩み値照合時間FromをDate型に変換できなかった場合、  
        //　@　@　@　@　@「入力時間エラー(歩み値照合時間From)」の例外をスローする。  
        if (this.tickMatchTimeFrom != null && 
            !WEB3StringTypeUtility.isDateStr(this.tickMatchTimeFrom, "yyyyMMddHHmmss"))
        {
            log.debug("入力時間エラー(歩み値照合時間From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02627,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力時間エラー(歩み値照合時間From)");
        }
        
        //１８）　@歩み値照合時間Toチェック  
        //　@this.歩み値照合時間To≠nullの場合、以下のチェックを行う。  
        //　@１８－１）this.歩み値照合時間ToをDate型に変換できなかった場合、  
        //　@　@　@　@　@「入力時間エラー(歩み値照合時間To)」の例外をスローする。  
        if (this.tickMatchTimeTo != null 
            && !WEB3StringTypeUtility.isDateStr(this.tickMatchTimeTo, "yyyyMMddHHmmss"))
        {
            log.debug("入力時間エラー(歩み値照合時間To)。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02628,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力時間エラー(歩み値照合時間To)。");
        }
        
        //１９）　@歩み値照合時間From/To整合性チェック  
        //　@this.歩み値照合時間From≠null かつ  
        //　@this.歩み値照合時間To≠nullの場合、以下のチェックを行う。  
        //　@１９－１）this.歩み値照合時間From > this.歩み値照合時間Toの場合、  
        //　@　@　@　@　@「入力時間整合性エラー」の例外をスローする。  
        if (this.tickMatchTimeFrom != null && this.tickMatchTimeTo != null) 
        {
            if (this.tickMatchTimeFrom.compareTo(this.tickMatchTimeTo) > 0)
            {
                log.debug("入力時間整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力時間整合性エラー。");
            }
        }
        
        //２０）　@歩み値照合区分チェック 
        //　@this.歩み値照合区分≠nullの場合、以下のチェックを行う。  
        //　@２０－１）this.歩み値照合区分に下記の項目以外が設定されていたら、  
        //　@　@　@　@「歩み値照合区分が未定義の値」の例外をスローする。  
        //　@　@　@　@・"不正発注疑い" 　@　@ 
        //　@　@　@　@・"未発注疑い" 
        //        ・"発注遅延疑い"
        //　@　@　@　@・"全てのエラー"      
        if (this.tickMatchDiv != null)
        {
            if (!WEB3AdminToTickMatchDivDef.ERROR_ORDER_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.NOT_ORDER_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.ORDER_DELAY_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(this.tickMatchDiv))
            {
                log.debug("歩み値照合区分が未定義の値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02629,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "歩み値照合区分が未定義の値。");
            }
        }
        
        //２１）　@乖離時間一覧チェック
        //　@this.乖離時間一覧≠nullの場合、
        //　@乖離時間一覧の全要素に対して下記のチェックを行う。
        //　@　@２１－１）乖離時間.validate()メソッドをコールする。
        if (this.differentTimeList != null)
        {
            l_intLen = this.differentTimeList.length;
            for (int i = 0; i < l_intLen; i++)
            {
                this.differentTimeList[i].validate();
            }
        }

        //２２）　@ソートキーチェック
        //２２－１）this.ソートキー＝nullであった場合
        //　@　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //２２－２）this.ソートキー.length＝0だった場合
        //　@　@　@「ソートキー.要素数が0」の例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //２２－３）this.ソートキーの全要素に対して
        //　@　@　@下記のチェックを行う。
        //　@２２－３－１）ソートキー.validate()をコールする。
        l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            this.sortKeys[i].validate();
        }
        
        //２３）要求ページ番号チェック
        //２３－１）this.要求ページ番号＝nullであった場合、
        //　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //２３－２）this.要求ページ番号≠数字の場合、
        //　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //２３－３）this.要求ページ番号 <= 0であった場合、
        //　@　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        
        //２４）ページ内表示行数チェック
        //２４－１）this.ページ内表示行数＝nullであった場合、
        //　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
        //２４－２）this.ページ内表示行数≠数字の場合、
        //　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //２４－３）this.ページ内表示行数 <= 0であった場合、
        //　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponseの実装)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
