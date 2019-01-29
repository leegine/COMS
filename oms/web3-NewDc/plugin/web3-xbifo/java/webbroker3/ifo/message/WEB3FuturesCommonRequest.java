head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物共通入力リクエスト(WEB3FuturesCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 李媛 (中訊) 新規作成
                   2004/07/26 李媛 (中訊) 変更　@Interfaceを変更されるように、ソースを変更しました。
                   2006/07/27 張騰宇(中訊)　@モデル　@454,462,470,533
                   2006/08/03 張騰宇(中訊)　@モデル 538
                   2006/08/10 郭英(中訊)　@モデル 541
Revesion History : 2007/06/11 孟亜南　@仕様変更モデルNo.639 No.644
Revesion History : 2008/03/11 張騰宇　@仕様変更モデル825
*/ 
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物共通入力リクエスト)<BR>
 * 株価指数先物共通入力リクエストクラス<BR>
 *                                                                     
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesCommonRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCommonRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407201150L;

    /**
     * (注文数量)<BR>
     * 一括返済時に「ランダムモード」の場合は設定されない<BR>
     */
    public String futOrderQuantity;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価区分が「指値」の場合に設定<BR>
     */
    public String limitPrice;

    /**
     * (執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     */
    public String execCondType;

    /**
     * (注文期限区分)<BR>
     * 1：当日限り　@2：出来るまで注文　@3：夕場まで注文<BR>
     */
    public String expirationDateType;

    /**
     * (注文有効期限)<BR>
     * 注文期限区分が「出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate;

    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
     */
    public String wLimitPrice;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効 <BR>
     * 2：ストップ注文失効済<BR>
     * <BR>
     * ※訂正時のみセット。<BR>
     * 
     */
    public String wlimitEnableStatusDiv;

    /**
     * @@roseuid 40C0A60F02DE
     */
    public WEB3FuturesCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@注文単価区分チェック<BR>
     * 　@１－１）this.注文単価区分がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00184<BR>
     * 　@１－２）this.注文単価区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”0：成行”<BR>
     * 　@　@　@　@・”1：指値”<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00185<BR>
     * <BR>
     * ２）　@注文単価チェック<BR>
     * 　@２－１）this.注文単価区分が”0：成行”でかつ、this.注文単価が<BR>
     * 　@　@　@　@null以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00116<BR>
     * 　@２－２）this.注文単価区分が”1：指値”でかつ、this.注文単価が<BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00187<BR>
     * 　@２－３）this.注文単価区分が”1：指値”でかつ、this.注文単価が<BR>
     * 　@　@　@　@数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00188<BR>
     * 　@２－４）this.注文単価区分が”1：指値”でかつ、this.注文単価が<BR>
     * 　@　@　@　@０以下の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00189<BR>
     * 　@２－５）this.注文単価区分が”1：指値”でかつ、this.注文単価が<BR>
     * 　@　@　@　@７桁を超える値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00190<BR>
     * <BR>
     * ３）　@執行条件チェック<BR>
     * 　@３－１）this.執行条件がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00197<BR>
     * 　@３－２）this.執行条件が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：無条件”<BR>
     * 　@　@　@　@・”3:寄付”<BR>
     * 　@　@　@　@・”4:引け”<BR>
     * 　@　@　@　@・”7:不出来引け成行”<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00127<BR>
     * <BR>
     * ４）　@執行条件・注文単価区分チェック<BR>
     * 　@this.執行条件が”7:不出来引け成行”でかつ、<BR>
     * 　@this.注文単価区分が”1：指値”以外の値であれば例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00198<BR>
     * <BR>
     * ５）　@注文期限区分チェック<BR>
     * 　@５－１）this.注文期限区分がnullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00208<BR>
     * 　@５－２）this.注文期限区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：当日限り”<BR>
     * 　@　@　@　@・”2：出来るまで注文”<BR>
     * 　@　@　@　@・”3：夕場まで注文”<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00209<BR>
     * <BR>
     * ６）　@注文期限区分・執行条件チェック<BR>
     * 　@    this.注文期限区分が”2：出来るまで注文”でかつ、<BR>
     * 　@    this.執行条件が”1：無条件”以外の値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00125<BR>
     * 　@６－２）this.注文期限区分が”3：夕場まで注文”でかつ、<BR>
     * 　@　@　@　@　@this.執行条件が”1：無条件”以外の値であれば例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02817<BR>
     * <BR>
     * ７）　@注文有効期限チェック<BR>
     * 　@７－１）this.注文期限区分が”1：当日限り”でかつ、<BR>
     *　@　@　@　@　@ this.注文有効期限≠nullであれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00117<BR>
     * 　@７－２）this.注文期限区分が”2：出来るまで注文”でかつ、<BR>
     * 　@this.注文有効期限がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00210<BR>
     * 　@７－３）this.注文期限区分が”3：夕場まで注文”でかつ、<BR>
     * 　@　@　@　@　@this.注文有効期限≠nullであれば例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02818<BR>
     * <BR>
     * ８）　@発注条件区分チェック<BR>
     * 　@８－１）this.発注条件区分がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00211<BR>
     * 　@８－２）this.発注条件区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”0：指定なし”<BR>
     * 　@　@　@　@・”1：逆指値”<BR>
     * 　@　@　@　@・”2：W指値”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * ９）　@逆指値用発注条件単価チェック<BR>
     * 　@９－１）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件単価がnullの値で<BR>
     * 　@　@　@　@あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00215<BR>
     * 　@９－２）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件単価が数字以外の値で<BR>
     * 　@　@　@　@あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00216<BR>
     * 　@９－３）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件単価≦０であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00216<BR>
     * 　@９－４）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件単価が７桁を超える値で<BR>
     * 　@　@　@　@あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00216<BR>
     * 　@９－５）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@　@ this.逆指値用発注条件単価≠(nullまたは0)で<BR>
     *　@　@　@　@ あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01872<BR>
     * 　@９－６）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     *　@　@　@　@ this.逆指値用発注条件単価≠(nullまたは0)で<BR>
     *　@　@　@　@ あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01882<BR>
     * <BR>
     * １０）　@逆指値用発注条件演算子チェック<BR>
     * 　@１０－１）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件演算子がnullの値で<BR>
     * 　@　@　@　@あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00219<BR>
     * 　@１０－２）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.逆指値用発注条件演算子が以下の値以外の場合<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@・”1：以上”<BR>
     * 　@　@　@　@・”2：以下”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00220<BR>
     * 　@１０－３）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@ 　@this.逆指値用発注条件演算子≠nullで<BR>
     *　@　@　@　@ あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01873<BR>
     *　@ １０－４）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     *　@　@　@　@ this.逆指値用発注条件演算子≠nullで<BR>
     *　@　@　@　@ あれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01883<BR>
     * <BR>
     * １１）　@Ｗ指値用発注条件単価チェック<BR>
     * 　@１１－１）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件単価がnullの値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00223<BR>     
     * 　@１１－２）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件単価が数字以外の値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00224<BR>      
     * 　@１１－３）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件単価≦０であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00224<BR>      
     * 　@１１－４）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件単価が７桁を超える値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00224<BR>
     * 　@１１－５）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@　@ this.Ｗ指値用発注条件単価≠(nullまたは0)であれば<BR>
     *　@　@　@　@ 例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01874<BR>
     * 　@１１－６）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     *　@　@　@　@ this.Ｗ指値用発注条件単価≠(nullまたは0)であれば<BR>
     *　@　@　@　@ 例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01878<BR>
     * <BR>
     * １２）　@Ｗ指値用発注条件演算子チェック<BR>
     * 　@１２－１）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件演算子がnullの値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00227<BR>      
     * 　@１２－２）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用発注条件演算子が以下の値以外の場合<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@・”1：以上”<BR>
     * 　@　@　@　@・”2：以下”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00228<BR>
     * 　@１２－３）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@ 　@this.Ｗ指値用発注条件演算子≠nullであれば<BR>
     *　@　@　@ 　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01875<BR>
     *　@ １２－４）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     *　@　@　@　@ this.Ｗ指値用発注条件演算子≠nullであれば<BR>
     *　@　@　@　@ 例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01879<BR>
     * <BR>
     * １３）　@Ｗ指値用注文単価区分チェック<BR>
     * 　@１３－１）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分がnullの値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00229<BR>      
     * 　@１３－２）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が以下の値以外の場合<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@・”0：成行”<BR>
     * 　@　@　@　@・”1：指値”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00230<BR>     
     * 　@１３－３）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@ 　@this.Ｗ指値用注文単価区分≠nullであれば<BR>
     *　@　@　@　@ 例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01876<BR>
     * 　@１３－４）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     *　@　@　@　@ this.Ｗ指値用注文単価区分≠nullであれば<BR>
     *　@　@　@　@ 例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01880<BR>
     * <BR>
     * １４）　@Ｗ指値用注文単価チェック<BR>
     * 　@１４－１）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”0：成行”の値でかつ<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価≠nullであれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00124<BR>      
     * 　@１４－２）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”の値でかつ<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価がnullの値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00313<BR>      
     * 　@１４－３）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”の値でかつ<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価が数字以外の値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00314<BR>      
     * 　@１４－４）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”の値でかつ<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価≦０であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00314<BR> 
     * 　@１４－５）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”の値でかつ<BR>
     * 　@　@　@　@this.Ｗ指値用注文単価が７桁を超える値であれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00314<BR>
     *   １４－６）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     *　@　@　@　@this.Ｗ指値用注文単価≠(nullまたは0)であれば<BR>
     *　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01877<BR>
     *　@ １４－７）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     *　@　@　@　@this.Ｗ指値用注文単価≠(nullまたは0)であれば<BR>
     *　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01881<BR>
     * <BR>
     * １５）　@発注条件・執行条件のチェック <BR>
     * 　@１５－１）　@this.発注条件＝"1：逆指値"の場合、 <BR>
     * 　@　@　@　@this.執行条件≠「1：無条件」であれば例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02473<BR>
     * 　@１５－２）　@this.発注条件＝"2：W指値"の場合、 <BR>
     * 　@　@　@　@this.執行条件が以下の値以外の場合、例外をスローする。 <BR>
     * 　@　@　@　@・”1：無条件” <BR>
     * 　@　@　@　@・”7:不出来引け成行”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02500<BR>
     *<BR>       
     * １６）　@Ｗ指値用執行条件チェック <BR>
     * 　@１６－１）this.発注条件区分が”2：W指値”の値でかつ、<BR> 
     * 　@　@　@　@this.Ｗ指値用執行条件がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02499<BR> 
     * 　@１６－２）this.発注条件区分が”2：W指値”の値でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件が以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@　@　@・”1：無条件” <BR>
     * 　@　@　@　@・”7:不出来引け成行” <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02500<BR>
     * <BR>
     * 　@１６－３）this.発注条件区分が”0：指定なし”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用執行条件≠nullであれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02525<BR>
     * <BR>
     * 　@１６－４）this.発注条件区分が”1：逆指値”の値でかつ、<BR>
     * 　@　@　@　@this.Ｗ指値用執行条件≠nullであれば<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02526<BR>
     * <BR>
     * １７）　@Ｗ指値用執行条件・注文単価区分チェック <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件が”7:不出来引け成行”でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”以外の値であれば<BR>
     * 　@　@　@　@例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02501<BR>
     * <BR>
     * １８）　@Ｗ指値用有効状態区分チェック <BR>
     * 　@　@　@　@this.Ｗ指値用有効状態区分≠nullであれば、<BR> 
     * 　@　@　@　@以下の処理を行う。 <BR>
     * 　@１８－１）　@this.Ｗ指値有効状態区分が以下の値以外の場合、<BR> 
     * 　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@・”0：リミット注文有効” <BR>
     * 　@　@　@　@・”1：ストップ注文有効” <BR>
     * 　@　@　@　@・”2：ストップ注文失効済” <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02502<BR>
     * <BR>
     * １９）　@Ｗ指値用執行条件・注文有効期限チェック <BR>
     * 　@１９－１）this.発注条件区分が”2：W指値”の値でかつ、<BR> 
     * 　@　@　@　@　@　@this.注文期限区分が”2：出来るまで注文”でかつ、 <BR>
     * 　@　@　@　@　@　@this.Ｗ指値用執行条件が”1：無条件”以外の値 <BR>
     * 　@　@　@　@　@　@であれば例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02503<BR>
     * 　@１９－２）this.発注条件区分が”2：W指値”の値でかつ、<BR>
     * 　@　@　@　@　@　@this.注文期限区分が”3：夕場まで注文”でかつ、<BR>
     * 　@　@　@　@　@　@this.Ｗ指値用執行条件が”1：無条件”以外の値<BR>
     * 　@　@　@　@　@　@であれば例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02503<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2120903DB
     */    
    
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単価区分チェック
        //　@１－１）this.注文単価区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                getClass().getName() + ".validate()",
                "注文単価区分がnullの値である");
        }
        
        //　@１－２）this.注文単価区分が以下の値以外の場合例外をスローする。
        // 　@　@　@　@・”0：成行”
        // 　@　@　@　@・”1：指値”
        else if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                getClass().getName() + ".validate()",
                "注文単価区分が”0：成行”まだは、”1：指値”以外である");
        }
        log.debug("１）　@注文単価区分チェック: END");

        //２）　@注文単価チェック
        //　@２－１）this.注文単価区分が”0：成行”でかつ、this.注文単価が
        //null以外の値であれば例外をスローする。
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.isNotEmpty(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                getClass().getName() + ".validate()",
                "注文単価区分が”0：成行”の場合、注文単価が入力不可です");
        }
        
        //　@２－２）this.注文単価区分が”1：指値”でかつ、this.注文単価が
        //nullの値であれば例外をスローする。
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.isEmpty(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00187,
                getClass().getName() + ".validate()",
                "指値注文の場合、指値が入力必須です");
        }
        
        //　@２－３）this.注文単価区分が”1：指値”でかつ、this.注文単価が
        //数字以外の値であれば例外をスローする。
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && !WEB3StringTypeUtility.isNumber(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00188,
                getClass().getName() + ".validate()",
                "注文単価区分が”1：指値”の場合、注文単価が数字以外である");
        }
        
        //　@２－４）this.注文単価区分が”1：指値”でかつ、this.注文単価が
        //０以下の値であれば例外をスローする。
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && Double.parseDouble(this.limitPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00189,
                getClass().getName() + ".validate()",
                "注文単価区分が”1：指値”の場合、注文単価が０以下の値である");
        }
        
        //　@２－５）this.注文単価区分が”1：指値”でかつ、this.注文単価が
        //７桁を超える値であれば例外をスローする。
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.getByteLength(this.limitPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00190,
                getClass().getName() + ".validate()",
                "注文単価区分が”1：指値”の場合、注文単価が７桁を超えました。");
        }
        log.debug("２）　@注文単価チェック: END");

        //３）　@執行条件チェック
        log.debug("３）　@執行条件チェック: ENTER");
        //　@３－１）this.執行条件がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                getClass().getName() + ".validate()",
                "執行条件を選択してください");
        }
        
        //　@３－２）this.執行条件が以下の値以外の場合例外をスローする。
        //”1：無条件”
        //”3:寄付”
        //”4:引け”
        //”7:不出来引け成行”
        else if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                getClass().getName() + ".validate()",
                "執行条件が条件なし、寄り、引け、不出来引け成行以外の場合エラー。");
        }
        log.debug("３）　@執行条件チェック: END");

        //４）　@執行条件・注文単価区分チェック
        log.debug("４）　@執行条件・注文単価区分チェック: ENTER");
        //this.執行条件が”7:不出来引け成行”でかつ、
        //this.注文単価区分が”1：指値”以外の値であれば例外をスローする。
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                getClass().getName() + ".validate()",
                "執行条件が”7:不出来引け成行”の場合、注文単価区分が”1：指値”を設定してください。");
        }
        log.debug("４）　@執行条件・注文単価区分チェック: END");

        //５）　@注文期限区分チェック
        //　@５－１）this.注文期限区分がnullの値であれば例外をスローする。
        if (this.expirationDateType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                getClass().getName() + ".validate()",
                "注文期限区分が選択してください");
        }
        //　@５－２）this.注文期限区分が以下の値以外の場合例外をスローする。
        //”1：当日限り”
        //”2：出来るまで注文”
        //”3：夕場まで注文”
        else if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && !WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                getClass().getName() + ".validate()",
                "注文期限区分が”1：当日限り”、”2：出来るまで注文”、”3：夕場まで注文”以外の場合エラー");
        }
        log.debug("５）　@注文期限区分チェック: END");

        //６）　@注文期限区分・執行条件チェック
        log.debug("６）　@注文期限区分・執行条件チェック: ENTER");
        //this.注文期限区分が”2：出来るまで注文”でかつ、
        //this.執行条件が”1：無条件”以外の値であれば例外をスローする。
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                getClass().getName() + ".validate()",
                "注文期限区分＝”2：出来るまで注文”の場合、執行条件≠「1：無条件」の場合エラー。");
        }
        log.debug("６）　@注文期限区分・執行条件チェック: END");

        //６－２）this.注文期限区分が”3：夕場まで注文”でかつ、<BR>
        //this.執行条件が”1：無条件”以外の値であれば例外をスローする。<BR>
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02817,
                getClass().getName() + ".validate()",
                "注文期限区分が“3：夕場まで注文”の場合は、執行条件に“1：無条件”を設定して下さい。");
        }

        //７）　@注文有効期限チェック
        log.debug("７）　@注文有効期限チェック: ENTER");
        //７－１）this.注文期限区分が”1：当日限り”でかつ、
        // this.注文有効期限≠nullであれば例外をスローする。
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                getClass().getName() + "validate",
                "注文期限区分が“1：当日限り”の場合は、注文有効期限指定不可です。");
        }
        
        //７－２）this.注文期限区分が”2：出来るまで注文”でかつ、
        //this.注文有効期限がnullの値であれば例外をスローする。
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && this.expirationDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00210,
                getClass().getName() + ".validate()",
                "注文期限区分が”2：出来るまで注文”の場合、注文有効期限が入力してください");
        }

        //７－３）this.注文期限区分が”3：夕場まで注文”でかつ、
        //this.注文有効期限≠nullであれば例外をスローする。
        else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02818,
                getClass().getName() + ".validate()",
                "注文期限区分が“3：夕場まで注文”の場合は、注文有効期限指定不可です。");
        }
        log.debug("７）　@注文有効期限チェック: END");

        //８）　@発注条件区分チェック
        log.debug("８）　@発注条件区分チェック: ENTER");
        //　@８－１）this.発注条件区分がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00211,
                getClass().getName() + ".validate()",
                "発注条件区分が選択してください");
        }
        
        //　@８－２）this.発注条件区分が以下の値以外の場合例外をスローする。
        //”0：指定なし”
        //”1：逆指値”
        //”2：W指値”
        else if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                getClass().getName() + ".validate()",
                "発注条件”0：指定なし”、”1：逆指値”、”2：W指値”以外の場合エラー。");
        }
        log.debug("８）　@発注条件区分チェック: END");

        //９）　@逆指値用発注条件単価チェック
        //　@９－１）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件単価がnullの値で
        //あれば例外をスローする。
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.stopOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合、逆指値用発注条件単価がnullの値である");
        }
        
        //　@９－２）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件単価が数字以外の値で
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合、逆指値用発注条件単価が数字以外の値である");
        }
        
        //　@９－３）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件単価が０以下の値で
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && Double.parseDouble(this.stopOrderCondPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合、逆指値用発注条件単価が０以下の値である");
        }
        
        //　@９－４）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件単価が７桁を超える値で
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.getByteLength(this.stopOrderCondPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合、逆指値用発注条件単価が７桁を超えました。");
        }
        
        //  ９－５）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.逆指値用発注条件単価≠(nullまたは0)で
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.stopOrderCondPrice != null
            && Double.parseDouble(this.stopOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、逆指値用発注条件単価が指定不可です。");
        }

        //　@９－６）this.発注条件区分が”2：W指値”の値でかつ、
        //this.逆指値用発注条件単価≠(nullまたは0)で
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.stopOrderCondPrice != null
            && Double.parseDouble(this.stopOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                getClass().getName() + "validate",
                "発注条件区分が“2：W指値”の場合は、逆指値用発注条件単価が指定不可です。");
        }
        log.debug("９）　@逆指値用発注条件単価チェック: END");

        //１０）　@逆指値用発注条件演算子チェック
        log.debug("１０）　@逆指値用発注条件演算子チェック: ENTER");
        //　@１０－１）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件演算子がnullの値で
        //あれば例外をスローする。
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.stopOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合、逆指値用発注条件演算子がnullの値である");
        }
        //　@１０－２）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.逆指値用発注条件演算子が以下の値以外の場合
        //例外をスローする。
        //”1：以上”
        //”2：以下”
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
            && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                getClass().getName() + ".validate()",
                "発注条件区分が”1：逆指値”の場合は、逆指値用発注条件演算子を”1：以上”又は”2：以下”を設定して下さい。");
        }
        
        //　@１０－３）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.逆指値用発注条件演算子≠nullで
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.stopOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、逆指値用発注条件演算子が指定不可です。");
        }

        //　@１０－４）this.発注条件区分が”2：W指値”の値でかつ、
        //this.逆指値用発注条件演算子≠nullで
        //あれば例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.stopOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                getClass().getName() + "validate",
                "発注条件区分が“2：W指値”の場合は、逆指値用発注条件演算子が指定不可です。");
        }
        log.debug("１０）　@逆指値用発注条件演算子チェック: END");

        //１１）　@Ｗ指値用発注条件単価チェック
        log.debug("１２）　@Ｗ指値用発注条件単価チェック: ENTER");
        //　@１１－１）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件単価がnullの値であれば
        //例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wlimitOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                getClass().getName() + ".validate()",
                "発注条件区分が”Ｗ指定”の場合は、W指値用発注条件単価が定義が必要です。");
        }
        
        //　@１１－２）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件単価が数字以外の値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の場合、Ｗ指値用発注条件単価が数字以外の値である。");
        }
        
        //　@１１－３）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件単価が０以下の値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && Double.parseDouble(this.wlimitOrderCondPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の場合、Ｗ指値用発注条件単価が０以下の値である。");
        }
        
        //　@１１－４）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件単価が７桁を超える値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.getByteLength(this.wlimitOrderCondPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の場合、Ｗ指値用発注条件単価が７桁を超えました。");
        }
        //　@１１－５）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.Ｗ指値用発注条件単価≠(nullまたは0)であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wlimitOrderCondPrice != null
            && Double.parseDouble(this.wlimitOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、W指値用発注条件単価が指定不可です。");
        }

        //　@１１－６）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.Ｗ指値用発注条件単価≠(nullまたは0)であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitOrderCondPrice != null
            && Double.parseDouble(this.wlimitOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                getClass().getName() + "validate",
                "発注条件区分が“1：逆指値”の場合は、W指値用発注条件単価が指定不可です。");
        }
        log.debug("１１）　@Ｗ指値用発注条件単価チェック: END");

        //１２）　@Ｗ指値用発注条件演算子チェック
        log.debug("１２）　@Ｗ指値用発注条件演算子チェック: ENTER");
        //　@１２－１）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件演算子がnullの値であれば
        //例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wlimitOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                getClass().getName() + ".validate()",
                "発注条件区分が”W指値”の場合は、W指値用発注条件演算子を定義が必要です。");
        }
        //　@１２－２）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用発注条件演算子が以下の値以外の場合
        //例外をスローする。
        //”1：以上”
        //”2：以下”
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
            && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                getClass().getName() + ".validate()",
                "発注条件区分が”W指値”の場合は、W指値用発注条件演算子に”1：以上”又は”2：以下”を設定して下さい。");
        }
        //　@１２－３）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.Ｗ指値用発注条件演算子≠nullであれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wlimitOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、W指値用発注条件演算子が指定不可です。");
        }

        //　@１２－４）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.Ｗ指値用発注条件演算子≠nullであれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                getClass().getName() + "validate",
                "発注条件区分が“1：逆指値”の場合は、W指値用発注条件演算子が指定不可です。");
        }
        log.debug("１２）　@Ｗ指値用発注条件演算子チェック: END");

        //１３）　@Ｗ指値用注文単価区分チェック
        log.debug("１３）　@Ｗ指値用注文単価区分チェック: ENTER");
        //　@１３－１）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分がnullの値であれば
        //例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wLimitOrderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00229,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の場合、Ｗ指値用注文単価区分がnullの値である。");
        }
        //　@１３－２）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が以下の値以外の場合
        //例外をスローする。
        //”0：成行”
        //”1：指値”
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00230,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の場合、Ｗ指値用注文単価区分が”0：成行”、”1：指値”以外の場合ｴﾗｰ。");
        }
        //　@１３－３）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.Ｗ指値用注文単価区分≠nullであれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wLimitOrderPriceDiv != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、W指値用注文単価区分が指定不可です。");
        }

        //　@１３－４）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.Ｗ指値用注文単価区分≠nullであれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wLimitOrderPriceDiv != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                getClass().getName() + "validate",
                "発注条件区分が“1：逆指値”の場合は、W指値用注文単価区分が指定不可です。");
        }
        log.debug("１３）　@Ｗ指値用注文単価区分チェック: END");

        //１４）Ｗ指値用注文単価チェック
        log.debug("１４）Ｗ指値用注文単価チェック: ENTER");
        //　@１４－１）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が”0：成行”の値でかつ
        //this.Ｗ指値用注文単価がnull以外の値であれば
        //例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
            && WEB3StringTypeUtility.isNotEmpty(this.wLimitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00124,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の値でかつ、Ｗ指値用注文単価区分が”0：成行”の値でかつＷ指値用注文単価がnull以外の値である");
        }
        
        //　@１４－２）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が”1：指値”の値でかつ
        //this.Ｗ指値用注文単価がnullの値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && WEB3StringTypeUtility.isEmpty(this.wLimitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00313,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の値でかつ、Ｗ指値用注文単価区分が”1：指値”の値でかつＷ指値用注文単価がnullの値である");
        }
        
        //　@１４－３）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が”1：指値”の値でかつ
        //this.Ｗ指値用注文単価が数字以外の値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && !WEB3StringTypeUtility.isNumber(this.wLimitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の値でかつ、Ｗ指値用注文単価区分が”1：指値”の値でかつＷ指値用注文単価が数字以外の値である");
        }
        
        //　@１４－４）this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が”1：指値”の値でかつ
        //this.Ｗ指値用注文単価が０以下の値であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && Double.parseDouble(this.wLimitPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の値でかつ、Ｗ指値用注文単価区分が”1：指値”の値でかつＷ指値用注文単価≦０である");
        }
        
        //　@１４－５) this.発注条件区分が”2：W指値”の値でかつ、
        //this.Ｗ指値用注文単価区分が”1：指値”の値でかつ
        //this.Ｗ指値用注文単価が７桁を超える値であれば
        //例外をスローする。
        else if (
            WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
                && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
                && WEB3StringTypeUtility.getByteLength(this.wLimitPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + ".validate()",
                "発注条件区分が”2：W指値”の値でかつ、Ｗ指値用注文単価区分が”1：指値”の値でかつＷ指値用注文単価が７桁を超える値である");
        }
        //  １４－６）this.発注条件区分が”0：指定なし”の値でかつ、
        //this.Ｗ指値用注文単価≠(nullまたは0)であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wLimitPrice != null
            && Double.parseDouble(this.wLimitPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                getClass().getName() + "validate",
                "発注条件区分が“0：指定なし”の場合は、W指値用注文単価が指定不可です。");
        }

        //　@１４－７）this.発注条件区分が”1：逆指値”の値でかつ、
        //this.Ｗ指値用注文単価≠(nullまたは0)であれば
        //例外をスローする。
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wLimitPrice != null
            && Double.parseDouble(this.wLimitPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                getClass().getName() + "validate",
                "発注条件区分が“1：逆指値”の場合は、W指値用注文単価が指定不可です。");
        }
        log.debug("１４）Ｗ指値用注文単価チェック: END");
        
		// １５）　@発注条件・執行条件のチェック
        //　@１５－１）　@this.発注条件＝"1：逆指値"の場合、 
        //   this.執行条件≠「1：無条件」であれば例外をスローする。
		if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
			&& !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
		{
            log.debug("Ｗ指値用執行条件が未指定です。");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02473,
				this.getClass().getName() + STR_METHOD_NAME,
                "発注条件区分が逆指値の場合は、執行条件に無条件を指定してください。");
		}
        
        //　@１５－２）　@this.発注条件＝"2：W指値"の場合、 
        //　@　@　@　@this.執行条件が以下の値以外の場合、例外をスローする。 
        //　@　@　@　@・”1：無条件” 
        //　@　@　@　@・”7:不出来引け成行”
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            log.debug("W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + STR_METHOD_NAME,
                "W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。"); 
        }
        
        //１６）　@Ｗ指値用執行条件チェック 
        //１６－１）this.発注条件区分が”2：W指値”の値でかつ、
        //      　@  this.Ｗ指値用執行条件がnullの値であれば例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitExecCondType == null)
        {
            log.debug("Ｗ指値用執行条件が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02499,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値用執行条件が未指定です。");   
        }
        
        // 　@１６－２）this.発注条件区分が”2：W指値”の値でかつ、  
        // 　@　@　@　@this.Ｗ指値用執行条件が以下の値以外の場合、例外をスローする。  
        // 　@　@　@　@・”1：無条件”  
        // 　@　@　@　@・”7:不出来引け成行”
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)))
        {
            log.debug("W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + STR_METHOD_NAME,
                "W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
        }
        
        //　@１６－３）this.発注条件区分が”0：指定なし”の値でかつ、 
        //　@　@　@　@this.Ｗ指値用執行条件≠nullであれば 
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType) &&
            this.wlimitExecCondType != null)
        {
            log.debug("発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02525,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可です。");
        }        
        
        //　@１６－４）this.発注条件区分が”1：逆指値”の値でかつ、 
        //　@　@　@　@this.Ｗ指値用執行条件≠nullであれば 
        //　@　@　@　@例外をスローする。 
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType) &&
            this.wlimitExecCondType != null)
        {
            log.debug("発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
        }
        
        // １７）　@Ｗ指値用執行条件・注文単価区分チェック 
        // 　@　@　@　@this.Ｗ指値用執行条件が”7:不出来引け成行”でかつ、 
        // 　@　@　@　@this.Ｗ指値用注文単価区分が”1：指値”以外の値であれば 
        //         例外をスローする。  
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            log.debug("Ｗ指値用注文単価区分が”指値”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02501,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値用注文単価区分が”指値”以外の値です。");
        }
        
        // １８）　@Ｗ指値用有効状態区分チェック  
        // 　@　@　@　@this.Ｗ指値用有効状態区分≠nullであれば、  
        // 　@　@　@　@以下の処理を行う。  
        // 　@１８－１）　@this.Ｗ指値有効状態区分が以下の値以外の場合、  
        // 　@　@　@　@例外をスローする。  
        // 　@　@　@　@・”0：リミット注文有効”  
        // 　@　@　@　@・”1：ストップ注文有効”  
        // 　@　@　@　@・”2：ストップ注文失効済”
        if (this.wlimitEnableStatusDiv != null 
            && !(WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE.equals(this.wlimitEnableStatusDiv)))
        {
            log.debug("Ｗ指値有効状態区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02502,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値有効状態区分の値が存在しないコード値です。");
        }
        
        //１９）　@Ｗ指値用執行条件・注文有効期限チェック 
        //　@　@this.発注条件区分が”2：W指値”の値でかつ、 
        //　@　@this.注文期限区分が”2：出来るまで注文”でかつ、 
        //　@　@this.Ｗ指値用執行条件が”1：無条件”以外の値 
        //　@　@であれば例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("Ｗ指値用執行条件が”無条件”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値用執行条件が”無条件”以外の値です。");
        }

        //１９－２）this.発注条件区分が”2：W指値”の値でかつ、
        //     this.注文期限区分が”3：夕場まで注文”でかつ、
        //     this.Ｗ指値用執行条件が”1：無条件”以外の値
        //     であれば例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("Ｗ指値用執行条件が”無条件”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値用執行条件が”無条件”以外の値です。");
            }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate連続注文)<BR>
     * 当リクエストデータの整合性チェック（連続注文用）を行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@執行条件チェック<BR>
     * 　@執行条件≠"無条件"の場合は、<BR>
     * 　@「連続注文は執行条件指定不可」の例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02235<BR>
     * <BR>
     * ２）　@発注条件チェック<BR>
     * 　@発注条件区分≠"指定なし"の場合は、<BR>
     * 　@「連続注文は発注条件指定不可」の例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02236<BR>
     * @@throws WEB3BaseException
     */
    public void validateSuccOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSuccOrder()";
        log.entering(STR_METHOD_NAME);

        //１）　@執行条件チェック
        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            log.debug("連続注文は執行条件指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02235,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文は執行条件指定不可。");
        }

        //２）　@発注条件チェック
        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            log.debug("連続注文は発注条件指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02236,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連続注文は発注条件指定不可。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCommonResponse(this);
    }
}
@
