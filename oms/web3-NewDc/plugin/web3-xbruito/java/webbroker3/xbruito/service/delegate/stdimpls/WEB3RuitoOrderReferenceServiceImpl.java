head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文照会サービス実装クラス(WEB3RuitoOrderReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.define.WEB3OrderStatusDivDef;
import webbroker3.xbruito.message.WEB3RuitoOrderGroup;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoSortKey;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.WEB3RuitoProduct;


/**
 * 累投注文照会サービス実装クラス
 */
public class WEB3RuitoOrderReferenceServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoOrderReferenceService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceServiceImpl.class);

    /**
     * @@roseuid 40A299DF003E
     */

    /**
     * 累積投資の注文照会サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「（累投）注文照会」参照。<BR>
     * <BR>
     * 
     * １)　@累投注文照会リクエスト.validate( )をコールし例外が<BR>
     *      返された場合、例外をスローする。<BR>
     * <BR>
     * ２)　@顧客別取引停止属性チェック<BR>
     * 　@２－１)　@this.get補助口座( )をコールし、<BR>
     *             補助口座オブジェクトを取得する。<BR>
     * 　@２－２)　@注文チェック.validate取引可能顧客<BR>
     *            (補助口座オブジェクト : SubAccount)を<BR>
     * 　@           コールし例外が返された場合、例外をスローする。<BR>
     * <BR>
     * ３)　@累積投資取引口座チェック<BR>
     * 　@３－１)　@累投発注審査個別チェック.getインスタンス( )を<BR>
     *             コールする。<BR>
     * 　@３－２)　@累投発注審査個別チェック.累投取引口座開設<BR>
     *            (補助口座オブジェクト : SubAccount)<BR>
     * 　@　@　@　@  をコールし例外が返された場合、例外をスローする。<BR>
     * <BR>
     * ４)　@受付時間チェック<BR>
     * ＝(シーケンス図「(累投)注文照会」-１参照)<BR>
     * 　@４－１)中期国債ファ@ンド買付の受付時間チェック<BR>
     * 　@　@４－１－１)　@ThreadLocalSystemAttributesRegistryの<BR>
     *                 取引カレンダコンテキストの内容を<BR>
     * 　@　@　@　@　@　@　@以下のように変更する。<BR>
     * 　@　@　@　@取引カレンダコンテキスト.受付時間区分 = <BR>
     *             WEB3TradingTimeTypeDef.中国F<BR>
     * 　@　@　@　@取引カレンダコンテキスト.注文受付トランザクション =<BR>
     *              "01:買付(新規注文)"<BR>
     
     * 　@　@４－１－２)　@取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * 　@　@４－１－３)　@取引時間管理.validate注文受付可能( )を<BR>
     *                      コールする。<BR>
     * 　@　@４－１－３－１)　@validate注文受付可能から例外が<BR>
     *           返されなかった場合<BR>
     * 　@　@　@　@is中国F買付受付可能(内部エリア)に"true"をセット。<BR>
     * 　@　@４－１－３－２)　@validate注文受付可能から例外が<BR>
     *          返された場合<BR>
     * 　@　@　@　@is中国F買付受付可能(内部エリア)に"false"をセット。<BR>
     * <BR>
     
     
     * 　@４－２)中期国債ファ@ンド解約の受付時間チェック<BR>
     * 　@　@４－２－１)　@ThreadLocalSystemAttributesRegistryの<BR            
     * 取引カレンダコンテキストの内容を<BR>
     * 　@　@   以下のように変更する。<BR>
     * 　@　@ 　@取引カレンダコンテキスト.受付時間区分 = <BR>
     *               WEB3TradingTimeTypeDef.中国F<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *              "02:売付(新規注文)"<BR>
     * 　@　@４－２－２)　@取引時間管理.setTimestamp()を<BR>
     *         コールする。<BR>
     * <BR>
     * 　@　@４－２－３)　@取引時間管理.validate注文受付可能( )を<BR>
     *         コールする。<BR>
     * 　@　@４－２－３－１)　@validate注文受付可能から例外が<BR>
     *          返されなかった場合<BR>
     * 　@　@　@　@ is中国F解約受付可能(内部エリア)に"true"をセット。<BR>
     * 　@　@４－２－３－２)　@validate注文受付可能から例外が<BR>
     *          返された場合<BR>
     * 　@　@　@  is中国F解約受付可能(内部エリア)に"false"をセット。<BR>
     * <BR>
     * 　@４－３)　@MMF買付の受付時間チェック<BR>
     * 　@　@４－３－１)　@ThreadLocalSystemAttributesRegistryの<BR>
     *       取引カレンダコンテキストの内容を以下のように変更する。<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = <BR>
     *             WEB3TradingTimeTypeDef.MMF(設定)<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *               "01:買付(新規注文)"<BR>
     * 　@　@４－３－２)　@取引時間管理.setTimestamp()を<BR>
     *        コールする。<BR>
     * <BR>
     * 　@　@４－３－３)　@取引時間管理.validate注文受付可能( )を<BR>
     *         コールする。<BR>
     * 　@　@４－３－３－１)　@validate注文受付可能から例外が<BR>
     *           返されなかった場合<BR>
     * 　@　@　@　@isMMF買付受付可能(内部エリア)に"true"をセット。<BR>
     * 　@　@４－３－３－２)　@validate注文受付可能から例外が<BR>
     *           返された場合<BR>
     * 　@　@　@　@ isMMF買付受付可能(内部エリア)に"false"をセット。<BR>
     * <BR>
     * 　@４－４)　@MMF解約の受付時間チェック<BR>
     * 　@　@４－４－１)　@ThreadLocalSystemAttributesRegistryの<BR>
     *           取引カレンダコンテキストの内容を<BR>
     * 　@　@　@　@以下のように変更する。<BR>
     * 　@　@　@　@取引カレンダコンテキスト.受付時間区分 = <BR>
     *               WEB3TradingTimeTypeDef.MMF(設定解約)<BR>
     * 　@　@　@　@取引カレンダコンテキスト.注文受付トランザクション = <BR>
     *               "02:売付(新規注文)"<BR>
     * 　@　@４－３－２)　@取引時間管理.setTimestamp()を<BR>
     *           コールする。<BR>
     * <BR>
     * 　@　@４－４－３)　@取引時間管理.validate注文受付可能( )を<BR>
     *           コールする。<BR>
     * 　@　@４－４－３－１)　@validate注文受付可能から例外が<BR>
     *           返されなかった場合<BR>
     * 　@　@　@　@isMMF解約受付可能(内部エリア)に"true"をセット。<BR>
     * 　@　@４－４－３－２)　@validate注文受付可能から例外が<BR>
     *          返された場合<BR>
     * 　@　@　@　@isMMF解約受付可能(内部エリア)に"false"をセット。<BR>
     * <BR>
     * 　@４－５)　@リクエストデータ.照会区分=="1:取消一覧"の場合<BR>
     * 　@　@　@　@以下の条件が満たされた場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@○is中国F買付受付可能==false and <BR>
     *             is中国F解約受付可能==false and <BR>
     * 　@　@　@　@　@ isMMF買付受付可能==false and　@<BR>
     *             isMMF解約受付可能==false  <BR>
     * 　@　@　@　@(中国F買付、中国F解約、MMF設定、<BR>
     *           MMF設定解約が受付出来ない状態)<BR>
     *          class    : WEB3BusinessLayerException<BR>
     *          tag      : BUSINESS_ERROR_00199<BR>
     * ５)　@createソート条件(リクエストデータ.ソートキー : <BR>
     *      累投ソートキー)をコールし、<BR>
     * 　@   リクエストデータにて渡されたソート条件を取得する。<BR>
     * <BR>
     * ６)　@検索条件に合致する全ての累投注文単位オブジェクトを<BR>
     *      取得する。<BR>
     * ＝(シーケンス図「(累投)注文照会」-２参照)<BR>
     * 　@　@拡張累投注文マネージャ.get累投注文単位一覧をコールし、<BR>
     *     検索条件に合致する<BR>
     * 　@　@　@　@全ての累投注文単位オブジェクトを取得する。<BR>
     * 　@　@[get累投注文単位一覧の引数]<BR>
     * 　@　@　@　@補助口座オブジェクト : 補助口座<BR>
     * 　@　@　@　@createソート条件メソッド戻り値 : String)<BR>
     * <BR>
     * ７)　@６)のget累投注文単位一覧メソッド戻り値の全てに対して、<BR>
     *      注文それぞれに取消が可能か<BR>
     * 　@　@どうかの判定を行い、その判定結果をもとに、<BR>
     *     照会区分に該当するレスポンス明細データ<BR>
     * 　@　@(累投注文照会注文単位クラス)を生成する。<BR>
     * ＝(シーケンス図「(累投)注文照会」-３参照)<BR>
     * <BR>
     * 　@７－１－１)　@this.validate取消可否メソッドをコールし、<BR>
     *          取消処理が可能な注文かどうかを判定する。<BR>
     * 　@　@　@　@[validate取消可否の引数]<BR>
     * 　@　@　@　@　@累投注文単位オブジェクト<BR>
     * 　@　@　@　@　@is中国F買付受付可能<BR>
     * 　@　@　@　@　@is中国F解約受付可能<BR>
     * 　@　@　@　@　@isMMF買付受付可能<BR>
     * 　@　@　@　@　@isMMF解約受付可能<BR>
     * <BR>
     * 　@７－１－２)　@注文照会の場合は検索結果の全て、<BR>
     *          取消一覧の場合は取消可能な注文のみを<BR>
     * 　@　@　@　@対象に、レスポンス明細データを作成する。<BR>
     * <BR>
     * 　@　@　@○リクエストデータ.照会区分=="0:注文照会"の場合、または<BR>
     * 　@　@　@○リクエストデータ.照会区分=="1:取消一覧"であり、<BR>
     * 　@　@　@　@７－１－２)のthis.validate取消可否メソッド戻り値が<BR>
     *          "true"の場合<BR>
     * <BR>
     * 　@　@７－１－３－１)　@レスポンス明細データのインスタンスを<BR>
     *          生成する。<BR>
     * <BR>
     * 　@　@７－１－３－２)　@売買区分の取得<BR>
     * 　@　@　@　@　@－累投注文単位Params.getOrderType()をコールし、<BR>
     *               注文種別を取得する。<BR>
     * 　@　@　@　@　@－累投注文単位.注文種別<BR>
     *                               ==OrderTypeEnum.RUITO_BUY<BR>
     *               の場合<BR>
     * 　@　@　@　@　@　@　@"1:買付"をセットする。<BR>
     * 　@　@　@　@　@－注文種別 == OrderTypeEnum.RUITO_SELLの場合<BR>
     * 　@　@　@　@　@　@　@累投注文単位Params.getGpSellDiv()をコールし、<BR>
     *                 累投解約区分をセットする。<BR>
     * <BR>
     * 　@　@７－１－３－３)　@レスポンス明細データに以下のように値をセットする。<BR>
     * 　@　@　@　@レスポンス明細データ.ID=累投注文単位.注文ID<BR>
     * 　@　@　@　@レスポンス明細データ.銘柄コード=累投注文単位.getProduct( )<BR>
     *          の戻り値.銘柄コード<BR>
     * 　@　@　@　@レスポンス明細データ.銘柄名=累投注文単位.getProduct( )<BR>
     *           の戻り値.銘柄名<BR>
     * 　@　@　@　@レスポンス明細データ.売買区分(累投)=７－１－３－３)で<BR>
     *           取得した売買区分<BR>
     * 　@　@　@　@レスポンス明細データ.注文日時=累投注文単位.受注日時<BR>
     * 　@　@　@　@レスポンス明細データ.注文数量区分=(*)<BR>
     * 　@　@　@　@レスポンス明細データ.注文数量=累投注文単位.注文数量<BR>
     * 　@　@　@　@レスポンス明細データ.注文状態区分=<BR>
     * 　@　@　@ 　@　@　@this.get注文状態区分(累投注文単位)メソッド戻り値<BR>
     * 　@　@　@　@レスポンス明細データ.取消可能フラグ=<BR>
     * 　@　@　@　@　@　@７－１－２)のthis.validate取消可否( )メソッド戻り値<BR>
     *              をセット<BR>
     * 　@ (*1)レスポンス明細データ.注文チャネル=<BR>
     *                  累投注文単位.初回注文の注文チャネル<BR>
     * 　@ (*1)レスポンス明細データ.注文経路区分=<BR>
     *                  累投注文単位.注文経路区分<BR>
     * 　@ (*1)レスポンス明細データ.オペレータコード=<BR>
     *                  累投注文単位.取引者ID≠nullの場合のみ、<BR>
     * 　@　@　@　@　@　@　@拡張金融オブジェクトマネージャ.getTrader(累投注文<BR>
     *                  単位.取引者ID).扱者コードをセット。<BR>
     * <BR>
     *   *)　@累投注文単位.注文数量タイプ=<BR>
     *             "1:口数"の場合、注文数量区分に"4:口数"をセット<BR>
     * 　@　@   累投注文単位.注文数量タイプ=<BR>
     *            "2:金額"の場合、注文数量区分に"3:金額"をセット<BR>
     * (*1) <BR>
     *  this.get代理入力者()≠null の場合のみセットを行う。 <BR>
     * <BR>
     * ８)　@７)で作成したレスポンス明細データの一覧を元に、以下を設定する。<BR>
     * ＝(シーケンス図「(累投)注文照会」-４参照)<BR>
     * <BR>
     * 　@８－１)　@リクエストデータ.createResponse( )メソッドをコールし、<BR>
     * 　@　@　@　@レスポンスデータ(累投注文照会レスポンスクラス)を生成する。<BR>
     * <BR>
     * 　@８－２)　@レスポンスの以下の項目を設定する。<BR>
     * 　@　@　@レスポンス.総ページ数:　@７)で確定した明細の要素数÷<BR>
     *        リクエスト.ページ内表示行数<BR>
     * 　@　@　@　@　@※余りが出る場合は、＋１した値を設定。<BR>
     * 　@　@　@　@　@※７)で確定した明細の要素数＝0<BR>
     *               (表示対象データなし)の場合、 0をセット。<BR>
     *               レスポンス.総レコード数:　@７)で確定した明細の要素数<BR>
     * 　@　@　@　@　@　@レスポンス.表示ページ番号(表示が何ページ目にあたるか):<BR>
     * 　@　@　@　@　@　@７)で確定した明細の要素数 > <BR>
     *             (リクエスト.ページ内表示行数×<BR>
     *                      (リクエスト.要求ページ番号-1) )<BR>
     * 　@　@　@　@　@　@であれば、リクエスト.要求ページ番号。<BR>
     * 　@　@　@　@　@　@上記以外の場合は、レスポンス.総ページ数 をそのまま設定。<BR>
     * 　@　@　@　@　@※検索結果がPR層からの要求ページ番号に満たない場合は、<BR>
     *               最終ページに<BR>
     * 　@　@　@　@　@該当するデータをレスポンスに設定する。<BR>
     * <BR>
     * 　@８－３)　@設定後、レスポンス.総ページ数＝0 の場合は、<BR>
     *              レスポンス.注文情報一覧<BR>
     * 　@　@　@(累投注文照会注文単位[ ])にnullをセットし例外をスローする。<BR>
     *          class    : WEB3BusinessLayerException<BR>
     *          tag      : BUSINESS_ERROR_00200<BR>
     * <BR>
     * ９)　@８)で確定した明細のうち、レスポンスデータに設定する明細を決める。<BR>
     * ＝(シーケンス図「(累投)注文照会」-５参照)<BR>
     * <BR>
     * 　@９－１)　@(リクエスト.ページ内表示行数×<BR>
     *                      (レスポンス.表示ページ番号－1)数分、<BR>
     * 　@　@　@　@８)で確定したレスポンス明細データ一覧の<BR>
     * 　@　@　@　@要素をスキップする。<BR>
     * <BR>
     * 　@９－２)　@上記で決定したレスポンス明細データ一覧の要素番号～<BR>
     * 　@　@　@　@(レスポンス明細データ一覧の要素番号＋<BR>
     *                                 リクエスト.ページ内表示行数)<BR>
     * 　@　@　@　@までに該当するレスポンス明細データを、<BR>
     *          レスポンスデータ.注文情報一覧<BR>
     * 　@　@　@　@としてセットする。<BR>
     * <BR>
     * 　@９－３)　@レスポンスをリターンする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405A4BD20127
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }        
        
        WEB3RuitoOrderReferenceRequest l_orderReferRequest =
            (WEB3RuitoOrderReferenceRequest) l_request;
        l_orderReferRequest.validate();
        log.debug("累投注文照会リクエスト.validate( )をコールし");
        
        //=====================================================================
        // 補助口座オブジェクトを取得する。
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //注文チェック.validate取引可能顧客
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   

        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME);
        }       
     
        // 累投発注審査個別チェックのインスタンスを取得する。
        WEB3RuitoOrderManagerReusableValidationsCheck l_orderMgrResVal =
            (WEB3RuitoOrderManagerReusableValidationsCheck) 
            WEB3RuitoOrderManagerReusableValidationsCheck.getInstance();
        
        //累積投資取引口座チェック 
        try
        {
            l_orderMgrResVal.validateRuitoTradedAccountEstablish(l_subAccount);
            log.debug("累積投資取引口座チェック");
        }
        catch(OrderValidationException e)
        {
            log.error("累積投資取引口座チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "累積投資取引口座チェックエラー");
        }            
        //is中国F買付受付可能(内部エリア)
        boolean l_blnMidFBuyAccept = false;
        //is中国F解約受付可能(内部エリア)
        boolean l_blnMidFSellAccept = false;
        //isMMF買付受付可能(内部エリア)
        boolean l_blnMMFBuy = false;
        //isMMF解約受付可能(内部エリア)
        boolean l_blnMMFSell = false;
        
        //ThreadLocalSystemAttributesRegistryより、
        //取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) 
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        try
        {
            
            //取引カレンダコンテキスト.受付時間区分 = ”
            //WEB3TradingTimeTypeDef.中国F”
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
    
            //取引カレンダコンテキスト.注文受付トランザクション = 
            //”01:買付(新規注文)”
            l_context.setOrderAcceptTransaction(
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
            WEB3GentradeTradingTimeManagement.setTimestamp();    
            WEB3GentradeTradingTimeManagement.validateOrderAccept();          
            l_blnMidFBuyAccept = true;
            log.debug("l_blnMidFBuyAccept = true");
        }

        catch(WEB3BaseException l_ex)
        {
            l_blnMidFBuyAccept = false;
            log.debug("l_blnMidFBuyAccept = false");
        }
        try
        {
            //４－２)中期国債ファ@ンド解約の受付時間チェック
            // 取引カレンダコンテキスト.受付時間区分 = ”WEB3TradingTimeTypeDef.中国F”
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);

            //取引カレンダコンテキスト.注文受付トランザクション = ”02:売付(新規注文)”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);

            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            l_blnMidFSellAccept = true;
            log.debug("l_blnMidFSellAccept = true");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMidFSellAccept = false;
            log.debug("l_blnMidFSellAccept = false");
        }
        
        //４－３)　@MMF買付の受付時間チェック
        try
        {            
            // 取引カレンダコンテキスト.受付時間区分 = ”WEB3TradingTimeTypeDef.MMF(設定)”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);
    
            //取引カレンダコンテキスト.注文受付トランザクション = ”01:買付(新規注文)”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);                
            WEB3GentradeTradingTimeManagement.setTimestamp();
    
            l_blnMMFBuy = true;
    
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("l_blnMMFBuy = true");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMMFBuy = false;
            log.debug("l_blnMMFBuy = false");
        }
        
        //４－４)　@MMF解約の受付時間チェック<BR>     
        try
        {            
            // 取引カレンダコンテキスト.受付時間区分 = WEB3TradingTimeTypeDef.MMF(設定解約)
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET_CANCEL);

            //取引カレンダコンテキスト.注文受付トランザクション = ”02:売付(新規注文)”
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);              
    
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            l_blnMMFSell = true;
            log.debug("l_blnMMFSell = true;");
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnMMFSell = false;
            log.debug("l_blnMMFSell = false");
        }      

        //４－５)　@リクエストデータ.照会区分=="1:取消一覧"の場合以下の
        //条件が満たされた場合、例外をスローする。
        if (WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType))
        {
            if (l_blnMidFBuyAccept == false && l_blnMidFSellAccept == false &&
                l_blnMMFBuy == false && l_blnMMFSell == false)
            {
                log.debug("__リクエストデータ.照会区分==1:取消一覧の場合__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00199, STR_METHOD_NAME,  
                    "リクエストデータ.照会区分=='1:取消一覧'の場合以下の条件が満たされた場合");
            }
        }

        //５)　@createソート条件
        WEB3RuitoSortKey l_sortKey[] = l_orderReferRequest.ruitoSortKeys;

        String l_strSort = createSortCondType(l_sortKey);

        /* ６)　@検索条件に合致する全ての累投注文単位オブジェクトを<BR>
         *      取得する。<BR>
         * ＝(シーケンス図「(累投)注文照会」-２参照)<BR>
         * 　@　@拡張累投注文マネージャ.get累投注文単位一覧をコールし、<BR>
         *     検索条件に合致する<BR>
         * 　@　@　@　@全ての累投注文単位オブジェクトを取得する。<BR>
         * 　@　@[get累投注文単位一覧の引数]<BR>
         * 　@　@　@　@補助口座オブジェクト : 補助口座<BR>
         * 　@　@　@　@createソート条件メソッド戻り値 : String)<BR>
         * <BR>
         * ７)　@６)のget累投注文単位一覧メソッド戻り値の全てに対して、<BR>
         *      注文それぞれに取消が可能か<BR>
         * 　@　@どうかの判定を行い、その判定結果をもとに、<BR>
         *     照会区分に該当するレスポンス明細データ<BR>
         * 　@　@(累投注文照会注文単位クラス)を生成する。<BR>
         * ＝(シーケンス図「(累投)注文照会」-３参照)<BR>*/
     
        //６)　@検索条件に合致する全ての累投注文単位オブジェクトを取得する。
        WEB3RuitoOrderManager l_ruitoOrderManager =
           (WEB3RuitoOrderManager) l_finApp
               .getTradingModule(ProductTypeEnum.RUITO)
               .getOrderManager();   
        //******need add＝(シーケンス図「(累投)注文照会」-２参照)
        List l_lisRuitoOrderUnit = new Vector();
        try
        {        
            l_lisRuitoOrderUnit =
                    l_ruitoOrderManager.getOrderUnits(l_subAccount, l_strSort);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //７)//******need add＝(シーケンス図「(累投)注文照会」-３参照)        
        
        /* 　@７－１－１)　@this.validate取消可否メソッドをコールし、<BR>
        *          取消処理が可能な注文かどうかを判定する。<BR>
        * 　@　@　@　@[validate取消可否の引数]<BR>
        * 　@　@　@　@　@累投注文単位オブジェクト<BR>
        * 　@　@　@　@　@is中国F買付受付可能<BR>
        * 　@　@　@　@　@is中国F解約受付可能<BR>
        * 　@　@　@　@　@isMMF買付受付可能<BR>
        * 　@　@　@　@　@isMMF解約受付可能<BR>
        * <BR>
        * 　@７－１－２)　@注文照会の場合は検索結果の全て、<BR>
        *          取消一覧の場合は取消可能な注文のみを<BR>
        * 　@　@　@　@対象に、レスポンス明細データを作成する。<BR>
        * <BR>
        * 　@　@　@○リクエストデータ.照会区分=="0:注文照会"の場合、または<BR>
        * 　@　@　@○リクエストデータ.照会区分=="1:取消一覧"であり、<BR>
        * 　@　@　@　@７－１－1)のthis.validate取消可否メソッド戻り値が<BR>
        *          "true"の場合<BR>
        * <BR>
        * 　@　@７－１－３－１)　@レスポンス明細データのインスタンスを<BR>
        *          生成する。<BR>*/
        //７－１－１)
        RuitoOrderUnit l_ruitoOrderUnit = null;
        RuitoOrderUnitRow l_ruitoOrderUnitRow = null;

        List l_lisRuitoOrderGroups = new Vector();

        for (int i = 0; i < l_lisRuitoOrderUnit.size(); i++)
        {
            l_ruitoOrderUnit = (RuitoOrderUnit) l_lisRuitoOrderUnit.get(i);
            l_ruitoOrderUnitRow = 
                (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();   
               
            boolean l_blnValidResult = this.validateCancelAbleUnable(
                l_ruitoOrderUnit,l_blnMidFBuyAccept,
                l_blnMidFSellAccept,l_blnMMFBuy,l_blnMMFSell);
            log.debug("l_blnValidResult="+new Boolean(
                l_blnValidResult).toString());        
            //７－１－２)
            
            if ( (!WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType))|| 
                    ((WEB3CancelDivDef.CANCEL.equals(l_orderReferRequest.referenceType) && l_blnValidResult)))
            { 
                 log.debug("レスポンス明細データ設定");
                /* 　@　@７－１－３－２)　@売買区分の取得<BR>
                 * 　@　@　@　@　@－累投注文単位Params.getOrderType()をコールし、<BR>
                 *               注文種別を取得する。<BR>
                 * 　@　@　@　@　@－累投注文単位.注文種別<BR>
                 *                               ==OrderTypeEnum.RUITO_BUY<BR>
                 *               の場合<BR>
                 * 　@　@　@　@　@　@　@"1:買付"をセットする。<BR>
                 * 　@　@　@　@　@－注文種別 == OrderTypeEnum.RUITO_SELLの場合<BR>
                 * 　@　@　@　@　@　@　@累投注文単位Params.getGpSellDiv()をコールし、<BR>
                 *                 累投解約区分をセットする。<BR>*/
                 
                //７－１－３－２)　@売買区分の取得
                RuitoOrderUnitParams l_params = 
                    (RuitoOrderUnitParams) 
                        l_ruitoOrderUnit.getDataSourceObject();
                String l_strTradedDiv = "";
                OrderTypeEnum l_orderType = l_params.getOrderType();
                if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
                {
                    l_strTradedDiv = WEB3BuySellTypeDef.BUY;
                    log.debug("l_orderType="+l_orderType.stringValue());
                    log.debug("l_strTradedDiv="+l_strTradedDiv);
                    
                }
                if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
                {
                    l_strTradedDiv = l_params.getGpSellDiv();
                    log.debug("l_orderType="+l_orderType.stringValue());
                    log.debug("l_strTradedDiv="+l_strTradedDiv);
                }
        
                //レスポンス明細データに以下のように値をセットする。
                //レスポンス明細データ.ID=累投注文単位.注文ID
            
                /*)　@累投注文単位.注文数量タイプ="1:口数"の場合、
                     注文数量区分に"4:口数"をセット 
        　@　@         累投注文単位.注文数量タイプ="2:金額"の場合、
                     注文数量区分に"3:金額"をセット */
                String l_strQuantityType = "";
                if (QuantityTypeEnum.QUANTITY.equals(
                     l_ruitoOrderUnit.getQuantityType()))
                {
                    l_strQuantityType = "4";
                    log.debug("l_strQuantityType =" +l_strQuantityType);
                }     
                if (QuantityTypeEnum.AMOUNT.equals(
                     l_ruitoOrderUnit.getQuantityType()))
                {
                    l_strQuantityType = "3";
                    log.debug("l_strQuantityType =" +l_strQuantityType);
                }              
                
         
                WEB3RuitoProduct l_ruitoProduct = 
                    (WEB3RuitoProduct) l_ruitoOrderUnit.getProduct();
                WEB3RuitoOrderGroup l_ruitoOrderGroup = new WEB3RuitoOrderGroup();
                //レスポンス明細データ.ID = 累投注文単位.注文ID
                l_ruitoOrderGroup.id = l_ruitoOrderUnit.getOrderId() + "";
                log.debug("レスポンス明細データ.ID=" + l_ruitoOrderGroup.id);
    
                //レスポンス明細データ.銘柄コード=
                //累投注文単位.getProduct( )の戻り値.銘柄コード
                l_ruitoOrderGroup.ruitoProductCode = 
                    l_ruitoProduct.getProductCode();
                log.debug("レスポンス明細データ.銘柄コード="+
                        l_ruitoOrderGroup.ruitoProductCode);
    
                //レスポンス明細データ.銘柄名=
                //累投注文単位.getProduct( )の戻り値.銘柄名
                l_ruitoOrderGroup.ruitoProductName = 
                    l_ruitoProduct.getProductName();
                log.debug("レスポンス明細データ.銘柄名="+
                        l_ruitoOrderGroup.ruitoProductName);
                    
                //レスポンス明細データ.売買区分(累投)=
                //７－１－３－３)で取得した売買区分
                l_ruitoOrderGroup.ruitoDealingType = l_strTradedDiv;
                log.debug("レスポンス明細データ.売買区分(累投)="+
                        l_ruitoOrderGroup.ruitoDealingType);
    
                //レスポンス明細データ.注文日時=累投注文単位.受注日時
                l_ruitoOrderGroup.orderDate = 
                    l_ruitoOrderUnitRow.getReceivedDateTime();
                log.debug("レスポンス明細データ.注文日時 = "+
                        l_ruitoOrderGroup.orderDate);
                    
                //レスポンス明細データ.注文数量区分=(*)
                l_ruitoOrderGroup.ruitoOrderQuantityType = l_strQuantityType;
                log.debug("レスポンス明細データ.注文数量区分 = "+
                        l_ruitoOrderGroup.ruitoOrderQuantityType);
    
                //レスポンス明細データ.注文数量=累投注文単位.注文数量
                l_ruitoOrderGroup.ruitoOrderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_ruitoOrderUnit.getQuantity());
                log.debug("レスポンス明細データ.注文数量 = "+
                        l_ruitoOrderGroup.ruitoOrderQuantity);                
                 
                //レスポンス明細データ.注文状態区分= 
                //this.get注文状態区分(累投注文単位)メソッド戻り値
                l_ruitoOrderGroup.orderState = 
                    this.getOrderStatusType(l_ruitoOrderUnit);
                log.debug("レスポンス明細データ.注文状態区分="+
                        l_ruitoOrderGroup.orderState);    
    
                //レスポンス明細データ.取消可能フラグ= ７－１－２)
                //のthis.validate取消可否( )メソッド戻り値
                l_ruitoOrderGroup.cancelFlag = l_blnValidResult;
                log.debug("レスポンス明細データ.取消可能フラグ="+
                        l_ruitoOrderGroup.cancelFlag);
                
                //this.get代理入力者()≠null の場合のみセットを行う。 
                if (this.getTrader() != null)
                {
                    log.debug("this.get代理入力者()≠null の場合");
                    //レスポンス明細データ.注文チャネル = 
                    //累投注文単位.初回注文の注文チャネル<BR>
                    l_ruitoOrderGroup.orderChannel = l_ruitoOrderUnitRow.getOrderChanel();
                    log.debug("レスポンス明細データ.注文チャネル="+
                            l_ruitoOrderGroup.orderChannel);
        
                    //レスポンス明細データ.注文経路区分=累投注文単位.注文経路区分<BR>
                    l_ruitoOrderGroup.orderRootDiv = l_ruitoOrderUnitRow.getOrderRootDiv();
                    log.debug("レスポンス明細データ.注文経路区分="+
                            l_ruitoOrderGroup.orderRootDiv);
                    
                    //レスポンス明細データ.オペレータコード
                    //=累投注文単位.取引者ID≠nullの場合のみ、 
                    //拡張金融オブジェクトマネージャ.getTrader
                    //(累投注文単位.取引者ID).扱者コードをセット。
                    if(l_ruitoOrderUnit.getTraderId() > 0)
                    {
                        //拡張金融オブジェクトマネージャ.getTrade()        
                        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    
                        Trader l_trader = null;
                        try
                        {
                            l_trader = l_gentradeFinObjectManager.getTrader(
                                    l_ruitoOrderUnit.getTraderId());
                  
                        }
                        catch (NotFoundException l_ex)
                        {           
                            log.error("__NotFoundException__", l_ex);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                "searchExecuteReference");
                        }        
                        l_ruitoOrderGroup.operatorCode = 
                            new Long(l_trader.getTraderId()).toString();
                        log.debug("レスポンス明細データ.オペレータコード="+
                                l_ruitoOrderGroup.operatorCode); 
                    }
                }
                l_lisRuitoOrderGroups.add(l_ruitoOrderGroup);
            }

        }
        WEB3RuitoOrderGroup[] l_ruitoOrderGroups = new WEB3RuitoOrderGroup[l_lisRuitoOrderGroups.size()];
        l_lisRuitoOrderGroups.toArray(l_ruitoOrderGroups);
        
        WEB3RuitoOrderReferenceResponse l_ruitoOrderReferenceResponse =
            (WEB3RuitoOrderReferenceResponse) l_orderReferRequest.createResponse();
        
        //８－２)　@レスポンスの以下の項目を設定する。 
        // --------------Start-------------------- TotalPages
        int l_intTotalPages =
            l_ruitoOrderGroups.length / Integer.parseInt(l_orderReferRequest.pageSize);
        
        if (l_ruitoOrderGroups.length == 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = "0";
        }
        else if (l_ruitoOrderGroups.length % Integer.parseInt(l_orderReferRequest.pageSize) == 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages);
        }
        else if (l_ruitoOrderGroups.length % Integer.parseInt(l_orderReferRequest.pageSize) > 0)
        {
            l_ruitoOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages + 1);
        }
        log.debug("totalPages=" + l_ruitoOrderReferenceResponse.totalPages);
        //--------------- End --------------- TotalPages

        // --------------- Start ---------------- TotalRecords
        //レスポンス.総レコード数:        
        //レスポンス.総レコード数:　@７)で確定した明細の要素数 
        l_ruitoOrderReferenceResponse.totalRecords = 
            WEB3StringTypeUtility.formatNumber(l_ruitoOrderGroups.length);

        log.debug("totalRecords="+l_ruitoOrderReferenceResponse.totalRecords);
        // -------------- End -------------------TotalRecords
        
        /*レスポンス.表示ページ番号(表示が何ページ目にあたるか): 
　@　@　@　@　@   ７)で確定した明細の要素数 > (リクエスト.ページ内表示行数×
        //   (リクエスト.要求ページ番号-1) ) 
　@　@　@　@　@　@  であれば、リクエスト.要求ページ番号。 
　@　@　@　@　@　@  上記以外の場合は、レスポンス.総ページ数 をそのまま設定。*/ 
        // ------------- Start ---------------- PageIndex
        if (l_ruitoOrderGroups.length > 
            Integer.parseInt(l_orderReferRequest.pageSize) * (Integer.parseInt(l_orderReferRequest.pageIndex) - 1))
        {
            l_ruitoOrderReferenceResponse.pageIndex = l_orderReferRequest.pageIndex;
        }
        else
        {
            l_ruitoOrderReferenceResponse.pageIndex = l_ruitoOrderReferenceResponse.totalPages;
        }
        // ------------ End ------------------- PageIndex
        if ("0".equals(l_ruitoOrderReferenceResponse.totalPages))
        {
            l_ruitoOrderReferenceResponse.ruitoOrderGroups = null;
            log.debug("投信注文照会レスポンス.総ページ数 = 0");
			return l_ruitoOrderReferenceResponse;
        }
        /* 　@９－１)　@(リクエスト.ページ内表示行数×<BR>
             *                      (レスポンス.表示ページ番号－1)数分、<BR>
             * 　@　@　@　@８)で確定したレスポンス明細データ一覧の<BR>
             * 　@　@　@　@要素をスキップする。<BR>
             * <BR>
       */
        // ------------ Start ---------------- RecordBegin & RecordEnd
        int l_intRecordBegin =
            Integer.parseInt(l_orderReferRequest.pageSize)
                * (Integer.parseInt(l_ruitoOrderReferenceResponse.pageIndex) - 1);
        int l_intRecordEnd = 0;
        if (l_ruitoOrderReferenceResponse.pageIndex.equals(l_ruitoOrderReferenceResponse.totalPages))
        {
            l_intRecordEnd = l_ruitoOrderGroups.length;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_orderReferRequest.pageSize);
        }
        // ------------ End ------------- RecordBegin & RecordEnd
           
       /* 
             * 　@９－２)　@上記で決定したレスポンス明細データ一覧の要素番号～<BR>
             * 　@　@　@　@(レスポンス明細データ一覧の要素番号＋<BR>
             *                                 リクエスト.ページ内表示行数)<BR>
             * 　@　@　@　@までに該当するレスポンス明細データを、<BR>
             *          レスポンスデータ.注文情報一覧<BR>
             * 　@　@　@　@としてセットする。<BR>
        */
        // -----------------Start-------------- ReturnValue RuitoOrderGroups
        List l_lisReturnRuitoOrderGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            l_lisReturnRuitoOrderGroups.add(l_ruitoOrderGroups[i]);
        }
        WEB3RuitoOrderGroup[] l_returnRuitoOrderGroups = 
            new WEB3RuitoOrderGroup[l_lisReturnRuitoOrderGroups.size()];
        l_lisReturnRuitoOrderGroups.toArray(l_returnRuitoOrderGroups);
        // -----------------End --------------- ReturnValue RuitoOrderGroups

        l_ruitoOrderReferenceResponse.ruitoOrderGroups = l_returnRuitoOrderGroups;
        log.exiting(STR_METHOD_NAME);    
        return l_ruitoOrderReferenceResponse;
    }

    /**
     * (createソート条件)<BR>
     * <BR>
     * 引数:ソートキーより、QueryProcessorのパラメータとして用いる<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * ソート条件文字列を作成し返す。<BR>
     * <BR>
     * １)　@引数のソートキー.キー項目の数分、<BR>
     *       対応するテーブルの列物理名を<BR>
     * 　@　@　@昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@・銘柄　@　@　@　@　@:累投注文単位テーブル.銘柄ID<BR>
     * 　@　@　@　@・売買　@　@　@　@　@:累投注文単位テーブル.累投解約区分<BR>
     * 　@　@　@　@・注文日時　@　@:累投注文単位テーブル.受注日時<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順<BR>
     *         指定に従い設定する。<BR>
     * <BR>
     * 　@　@○売買・昇順が指定された場合、昇順／降順<BR>
     *         指定の後に" nulls first"を付加する。<BR>
     * 　@　@○売買・降順が指定された場合、昇順／降順指定<BR>
     *         の後に" nulls last"を付加する。<BR>
     * 　@　@　@※定義上、"買付"が最小値を持っているようなソートが<BR>
     *           行われるべきだが、<BR>
     * 　@　@　@　@累投注文単位テーブル.累投解約区分に"買付"として<BR>
     *          の定義は、コードではなく<BR>
     * 　@　@　@　@nullである為、上記パラメータが必要となる。<BR>
     * <BR>
     * ２)　@作成したソート条件文字列を返す。<BR>
     * @@param l_sortKey - 累投ソートキーの配列<BR>
     * @@return String
     * @@roseuid 408659760394
     */
    //For Test Change private to public
//    private String createSortCondType(WEB3RuitoSortKey[] l_sortKey)
    public String createSortCondType(WEB3RuitoSortKey[] l_sortKey)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
                  "createSortCondType(WEB3RuitoSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);          
        if (l_sortKey == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        log.entering(STR_METHOD_NAME);
        //返還の値の設定
        String l_strReturn = "";
        for (int i = 0; i < l_sortKey.length; i++)
        {
            
            if(l_sortKey[i].keyItem.equals("ruitoProductCode") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "product_id";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc ";
            }
            
            if(l_sortKey[i].keyItem.equals("ruitoProductCode") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "product_id";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc ";
            }
            
            
            if(l_sortKey[i].keyItem.equals("ruitoDealingType") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "gp_sell_div";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc nulls first ";
            }
            
            if(l_sortKey[i].keyItem.equals("ruitoDealingType") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "gp_sell_div";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc nulls last ";
            }
            
            if(l_sortKey[i].keyItem.equals("orderDate") &&
                l_sortKey[i].ascDesc.endsWith("A"))
            {
                l_strReturn = l_strReturn + "received_date_time";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " asc ";
            }
            
            if(l_sortKey[i].keyItem.equals("orderDate") &&
                l_sortKey[i].ascDesc.endsWith("D"))
            {
                l_strReturn = l_strReturn + "received_date_time";
                l_strReturn = l_strReturn + " ";
                l_strReturn = l_strReturn + " desc ";
            }

            if (i >= 0 && i < l_sortKey.length - 1)
            {
                l_strReturn = l_strReturn + ", ";
            }
        }
        log.debug("order by l_strReturn = " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (validate取消可否)<BR>
     * 累投注文単位より、その注文が取消可能な注文かどうかを判定し、<BR>
     * 取消可能な場合"true"を、取消不可の場合は"false"を返却する。<BR>
     * <BR>
     * １)以下のようにThreadLocalSystemAttributesRegistryの<BR>
     * 　@   取引カレンダコンテキストの内容を変更し、<BR>
     *      取引時間管理.setTimestamp( )をコールする。<BR>
     * <BR>
     * 　@○引数:累投注文単位.累投タイプが<BR>
     *      RuitoTypeEnum.中期国債ファ@ンドの場合<BR>
     * 　@　@取引カレンダコンテキスト.受付時間区分 =<BR>
     *            WEB3TradingTimeTypeDef.中国F<BR>
     * <BR>
     * 　@○引数:累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、<BR>
     * 　@　@引数:累投注文単位.注文種別がOrderTypeEnum.RUITO_BUYの<BR>
     *     時、取引カレンダコンテキスト.受付時間区分 = <BR>
     *                 WEB3TradingTimeTypeDef.MMF(設定)<BR>
     * <BR>
     * 　@○引数:累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、<BR>
     * 　@　@引数:累投注文単位.注文種別がOrderTypeEnum.RUITO_SELL<BR>
     *      の時、取引カレンダコンテキスト.受付時間区分 =<BR>
     *              WEB3TradingTimeTypeDef.MMF(設定解約)<BR>
     * <BR>
     * 　@１－１)　@累投発注審査個別チェック.getインスタンス( )を<BR>
     *               コールする。<BR>
     * 　@１－２)　@累投発注審査個別チェック.validate取消可能( )を<BR>
     *              コールする。<BR>
     * 　@　@　@    　@[validate取消可能の引数]<BR>
     * 　@　@　@　@　@　@　@引数:累投注文単位<BR>
     *  例外が発生した場合、falseを返却する。<BR>
     * <BR>
     * ２)　@累投解約区分チェック<BR>
     * 　@２－１)　@戻り値が"true"であり、かつ引数:<BR>
     *      累投注文単位.累投タイプがRuitoTypeEnum.中期国債ファ@ンドで、<BR>
     * 　@　@かつ引数:<BR>
     *      累投注文単位.注文種別がOrderTypeEnum.RUITO_BUYの場合<BR>
     * 　@　@２－１－１)　@戻り値に引数:is中国F買付受付可能をセットする。<BR>
     * <BR>
     * 　@２－２)　@戻り値が"true"であり、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *        累投注文単位.累投タイプがRuitoTypeEnum.中期国債ファ@ンドで、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *        累投注文単位.注文種別がOrderTypeEnum.RUITO_SELLの場合<BR>
     * 　@　@２－２－１)　@戻り値に引数:is中国F解約受付可能をセットする。<BR>
     * <BR>
     * 　@２－３)　@戻り値が"true"であり、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *          累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *          累投注文単位.注文種別がOrderTypeEnum.RUITO_BUYの場合<BR>
     * 　@　@２－３－１)　@戻り値に引数:isMMF買付受付可能をセットする。<BR>
     * <BR>
     * 　@２－４)　@戻り値が"true"であり、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *        累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、<BR>
     * 　@　@　@　@かつ引数:<BR>
     *        累投注文単位.注文種別がOrderTypeEnum.RUITO_SELLの場合<BR>
     * 　@　@２－４－１)　@戻り値に引数:isMMF解約受付可能をセットする。<BR>
     * <BR>
     * ３)　@戻り値をリターンする。<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位オブジェクト<BR>
     * @@param isMidTermNationalDebtFoundBuyAcceptPossible - 
     * <BR>(is中国F買付受付可能)<BR>
     * true:受付可／false:受付不可<BR>
     * @@param isMidTermNationalDebtFoundSellAcceptPossible - 
     * <BR>(is中国F解約受付可能)<BR>
     * true:受付可／false:受付不可<BR>
     * 
     * @@param isMMFBuyAcceptPossible - isMMF買付受付可能<BR>
     * true:受付可／false:受付不可<BR>
     * 
     * @@param isMMSellAcceptPossible - (isMM解約受付可能)<BR>
     * true:受付可／false:受付不可<BR>
     * @@return boolean
     * @@roseuid 408659BF023C
     */
    // For Test Change private to public
    //private boolean validateCancelAbleUnable(
    public boolean validateCancelAbleUnable(
        RuitoOrderUnit l_ruitoOrderUnit,
        boolean isMidTermNationalDebtFoundBuyAcceptPossible,
        boolean isMidTermNationalDebtFoundSellAcceptPossible,
        boolean isMMFBuyAcceptPossible,
        boolean isMMSellAcceptPossible) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateCancelAbleUnable()";
        log.entering(STR_METHOD_NAME);
        if (l_ruitoOrderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) 
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        int  l_RuitoType;
        l_RuitoType = l_ruitoOrderUnitRow.getRuitoType().intValue();    
        log.debug("l_RuitoType = "+new Integer(l_RuitoType).toString());
        //引数:累投注文単位.累投タイプがRuitoTypeEnum.中期国債ファ@ンドの場合
        if (RuitoTypeEnum.CHUUKOKU_FUND.intValue() == l_RuitoType)
        {
            log.debug(
              "引数:累投注文単位.累投タイプがRuitoTypeEnum.中期国債ファ@ンドの場合");
            l_context.setTradingTimeType(
               WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);              
        }
        
        //引数:累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、
        //引数:累投注文単位.注文種別がOrderTypeEnum.RUITO_BUYの時
        if (RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(l_ruitoOrderUnit.getOrderType()))
        {
            log.debug("引数:累投注文単位.注文種別がOrderTypeEnum.RUITO_BUYの時");
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);
        }
        
        //引数:累投注文単位.累投タイプがRuitoTypeEnum.MMFであり、
        //引数:累投注文単位.注文種別がOrderTypeEnum.RUITO_SELLの時、
        if (RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnit.getOrderType()))
        {
            log.debug("累投注文単位.注文種別がOrderTypeEnum.RUITO_SELLの時");
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
        }      
       
        boolean l_blnValidateCheck = true;
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                 l_context); 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        try
        {   
  
            //１－１)　@累投発注審査個別チェック.getインスタンス( )をコールする。
            WEB3RuitoOrderManagerReusableValidationsCheck l_validationCheck =
                (WEB3RuitoOrderManagerReusableValidationsCheck) 
                WEB3RuitoOrderManagerReusableValidationsCheck
                .getInstance();

            //１－２)　@累投発注審査個別チェック.validate取消可能( )をコールする
            l_validationCheck.validateCancelPossible(l_ruitoOrderUnit);
            

        }        
        catch(OrderValidationException l_ex)
        {
            l_blnValidateCheck = false;
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnValidateCheck = false;
        }
        

        boolean l_blnReturn = false;
        //２)　@累投解約区分チェック
        
        if (l_blnValidateCheck && RuitoTypeEnum.CHUUKOKU_FUND.intValue() 
            == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //戻り値に引数:is中国F買付受付可能をセットする。
            log.debug("戻り値に引数:is中国F買付受付可能をセットする。");
            l_blnReturn = isMidTermNationalDebtFoundBuyAcceptPossible;
        }
        //２－２)
        if (l_blnValidateCheck && RuitoTypeEnum.CHUUKOKU_FUND.intValue() 
            == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //戻り値に引数:is中国F解約受付可能をセットする。
            log.debug("戻り値に引数:is中国F解約受付可能をセットする。");
            l_blnReturn = isMidTermNationalDebtFoundSellAcceptPossible;
        }
        //２－３)
        if (l_blnValidateCheck && RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //戻り値に引数:isMMF買付受付可能をセットする。
            log.debug("戻り値に引数:isMMF買付受付可能をセットする");
			l_blnReturn = isMMFBuyAcceptPossible;
        }
        //２－４)
        if (l_blnValidateCheck && RuitoTypeEnum.MMF.intValue() == l_RuitoType
            && OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnit.getOrderType()))
        {
            //戻り値に引数:isMMF解約受付可能をセットする。
			log.debug("戻り値に引数:isMMF解約受付可能をセットする");
			l_blnReturn = isMMSellAcceptPossible;
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * 指定された累投注文単位の注文状態区分を返す。<BR>
     * <BR>
     * 戻り値の注文状態区分:<BR>
     *  1:受付済(新規注文)　@      3:発注済(新規注文)　@ <BR>
     * 6:発注失敗(新規注文)     12:受付済(取消注文)　@<BR>
     * 14:発注済(取消注文)　@　@  15:発注失敗(取消注文)<BR>
     * 30:MRF取消エラー　@　@       31:受付済(MRF解約有り)<BR>
     * 32:注文済(MRF解約有り)<BR>
     * <BR>
     * １)　@注文状態の判定<BR>
     * 　@　@　@　@引数:注文情報.MRF注文識別コード!=nullの場合<BR>
     * <BR>
     * 　@１－１)　@累投注文単位テーブルを検索し、<BR>
     *              MRF情報としての累投注文単位オブジェクトを取得する。<BR>
     * 　@　@　@　@[検索条件]<BR>
     * 　@　@　@　@　@　@累投注文単位.識別コード = <BR>
     *                  引数:注文情報.MRF注文識別コード<BR>
     * 　@　@　@　@　@　@累投注文単位.累投タイプ = RuitoTypeEnum.MRF<BR>
     * <BR>
     * 　@　@１－１－１)　@検索結果が１件ではない場合、<BR>
     *                      データ不整合として例外をスローする。<BR>
     *                      class    : WEB3BusinessLayerException<BR>
     *                      tag      : BUSINESS_ERROR_00201<BR>
     * <BR>
     * 　@１－２)　@引数:注文情報.注文状態=<BR>
     *               OrderStatusEnum.発注失敗（取消注文）(15)で、かつ<BR>
     * 　@　@　@　@    MRF情報.注文状態=<BR>
     *               OrderStatusEnum.発注失敗（取消注文）(15)の場合<BR>
     * 　@　@　@　@　@　@32．発注失敗（ＭＲＦ解約取消注文）をリターンする。<BR>
     * <BR>
     * 　@１－３)　@引数:注文情報.注文状態=<BR>
     *               OrderStatusEnum.受付済(新規注文)で、かつ<BR>
     * 　@　@　@　@    MRF情報.注文状態=<BR>
     *              OrderStatusEnum.受付済(新規注文)の場合<BR>
     * 　@　@　@　@　@　@30．受付済（MRF解約あり）をリターンする。<BR>
     * <BR>
     * 　@１－４)　@引数:注文情報.注文状態=<BR>
     *              OrderStatusEnum.受付済(新規注文)で、かつ<BR>
     * 　@　@　@    　@MRF情報.注文状態=<BR>
     *              OrderStatusEnum.発注済(新規注文)の場合<BR>
     * 　@　@　@　@　@　@31．発注済（MRF解約あり）をリターンする。<BR>
     * <BR>
     * 　@１－５)　@引数:注文状態=OrderStatusEnum.発注済(新規注文)で、<BR>
     *              かつMRF情報.注文状態=<BR>
     *              OrderStatusEnum.発注済(新規注文)の場合<BR>
     * 　@　@　@　@　@　@31．発注済（MRF解約あり）をリターンする。<BR>
     * <BR>
     * 　@１－６)　@上記に合致しない場合、<BR>
     *               引数:注文情報.注文状態をリターンする。<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位オブジェクト
     * @@return String
     * @@roseuid 40865A7D0087
     */
    // For Test Change private to public
    //private String getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit) throws WEB3BaseException
    public String getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getOrderStatusType(RuitoOrderUnit l_ruitoOrderUnit)";
        if (l_ruitoOrderUnit == null)
        { 
            log.debug("引数=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        String l_strMrfOrderRequestNumber = null;
        l_strMrfOrderRequestNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();
        
		// SONARで受付けた注文の場合、            
		if (WEB3OrderRootDivDef.HOST.equals(l_ruitoOrderUnitRow.getOrderRootDiv()))
		{
			// 引数:注文情報.注文状態=
			// OrderStatusEnum.発注済（取消注文）(14)の場合、
			// 14：発注済（取消注文）をリターンする。
			if(OrderStatusEnum.CANCELLED.equals(
					l_ruitoOrderUnit.getOrderStatus()))
			{
				return WEB3OrderStatusDivDef.ORDER_CANCELED;
			}
			// それ以外の場合、56：取消不可をリターンする。
			else
			{
				return WEB3OrderStatusDivDef.CANCEL_DISABLE;
			}
		}
		
        //注文情報.MRF注文識別コード!=nullの場合    
        if (l_strMrfOrderRequestNumber != null)
        {
            log.debug("注文情報.MRF注文識別コード!=nullの場合");
            try
            {
                String l_strWhere = "order_request_number = ? and ruito_type = ?";

                QueryProcessor l_qp = Processors.getDefaultProcessor();
                Object l_bindVars[] = { l_strMrfOrderRequestNumber, 
                                        RuitoTypeEnum.MRF };
                List l_lisRows =
                    l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE, l_strWhere, l_bindVars);

                if (l_lisRows != null && l_lisRows.size() != 1)
                {
                    log.debug("データ不整合として");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00201,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                RuitoOrderUnitParams l_RuitoProductParams = 
                            (RuitoOrderUnitParams)l_lisRows.get(0);
                
                //１－２)　@引数:注文情報.注文状態=
                //        OrderStatusEnum.発注失敗（取消注文）(15)で、かつ
                //　@　@　@    MRF情報.注文状態=<BR>
                //        OrderStatusEnum.発注失敗（取消注文）(15)の場合
                //        32．発注失敗（ＭＲＦ解約取消注文）をリターンする。
                if (OrderStatusEnum.NOT_CANCELLED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.NOT_CANCELLED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.CANCEL_MRF_SELL_FAILED;
                }
                // １－３)　@引数:注文情報.注文状態=
                //        OrderStatusEnum.受付済(新規注文)で、かつ
                //  　@　@    MRF情報.注文状態=
                //         OrderStatusEnum.受付済(新規注文)の場合
                //         30．受付済（MRF解約あり）をリターンする。
                if (OrderStatusEnum.ACCEPTED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ACCEPTED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDERING_MRF_SELL;
                }
                //１－４)　@引数:注文情報.注文状態=
                //        OrderStatusEnum.受付済(新規注文)で、かつ
                //      　@MRF情報.注文状態=
                //        OrderStatusEnum.発注済(新規注文)の場合
                //  　@　@　@　@31．発注済（MRF解約あり）をリターンする。
                if (OrderStatusEnum.ACCEPTED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ORDERED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDER_MRF_SELL;
                }
                //１－５)　@引数:注文状態=OrderStatusEnum.発注済(新規注文)で、
                //        かつMRF情報.注文状態=
                //        OrderStatusEnum.発注済(新規注文)の場合
                //   　@　@　@31．発注済（MRF解約あり）をリターンする。
                if (OrderStatusEnum.ORDERED.equals(
                        l_ruitoOrderUnit.getOrderStatus())
                    && OrderStatusEnum.ORDERED.equals(
                        l_RuitoProductParams.getOrderStatus()))
                {
                    return WEB3OrderStatusDivDef.ORDER_MRF_SELL;
                }


            }
            catch (DataNetworkException l_ex)
            {
                log.debug("__DataNetworkException__");
                log.error(
                    "__an unexpected error__",
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex));
            }
            catch (DataQueryException l_ex)
            {
                log.debug("__DataQueryException__");
                log.error(
                    "__an unexpected error__",
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex));
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_ruitoOrderUnit.getOrderStatus().intValue() + "";
    }

}@
