head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引共通リクエスト(WEB3MarginCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
                   2006/11/02 張騰宇(中訊) モデル 948,1000
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引共通リクエスト）。<br>
 * <br>
 * 信用取引共通リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCommonRequest extends WEB3GenRequest 
{

    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCommonRequest.class);
    
    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "margin_common";

    /**
     * <p>（SerialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200409101800L;
    
    /**
     * <p>（注文株数）。</p>
     * <p>注文株数</p>
     */
    public String orderQuantity;
    
    /**
     * <p>（注文単価区分）。</p>
     * <p>0：成行　@1：指値</p>
     */
    public String orderPriceDiv;
    
    /**
     * <p>（注文単価）。</p>
     * <p>注文単価<br>
     * <br>
     * 注文単価区分が「指値」の場合に設定</p>
     */
    public String limitPrice;
    
    /**
     * <p>（値段条件）。</p>
     * <p>0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消</p>
     */
    public String priceCondType;
    
    /**
     * <p>（執行条件）。</p>
     * <p>1：無条件 3：寄付 4：引け 7：不出来引け成行</p>
     */
    public String execCondType;
    
    /**
     * <p>（注文期限区分）。</p>
     * <p>1：当日限り　@2：出来るまで注文</p>
     */
    public String expirationDateType;
    
    /**
     * <p>（注文有効期限）。</p>
     * <p>注文期限区分が「出来るまで注文」の場合に設定</p>
     */
    public Date expirationDate;
    
    /**
     * <p>（発注条件区分）。</p>
     * <p>0：指定なし　@1：逆指値　@2：W指値</p>
     */
    public String orderCondType;
    
    /**
     * <p>（逆指値用発注条件単価）。</p>
     * <p>発注条件区分が、「逆指値」の場合設定される</p>
     */
    public String stopOrderCondPrice;
    
    /**
     * <p>（逆指値用発注条件演算子）。</p>
     * <p>1：以上　@2：以下<br>
     * <br>
     * 発注条件区分が、「逆指値」の場合設定される</p>
     */
    public String stopOrderCondOperator;
    
    /**
     * <p>（Ｗ指値用発注条件単価）。</p>
     * <p>発注条件区分が、「W指値」の場合設定される</p>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * <p>（Ｗ指値用発注条件演算子）。</p>
     * <p>1：以上　@2：以下<br>
     * <br>
     * 発注条件区分が、「W指値」の場合設定される</p>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * <p>（Ｗ指値用注文単価区分）。</p>
     * <p>0：成行　@1：指値<br>
     * <br>
     * 発注条件区分が、「W指値」の場合設定される</p>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * <p>（Ｗ指値用注文単価）。</p>
     * <p>Ｗ指値用注文単価区分が、「指値」の場合設定される</p>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される。<BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効 <BR>
     * 2：ストップ注文失効済 <BR>
     * 発注条件区分が、「2：W指値」の場合設定される。 <BR>
     * ※訂正時のみセット。<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * <p>（信用取引共通リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginCommonRequest() 
    {
    }
    
    /**
     * <p>（validate）。</p>
     * <p>（当リクエストデータの整合性チェックを行う。<br>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<br>
     * <br>
     * １）　@注文単価区分チェック<br>
     * 　@１－１）this.注文単価区分＝nullの場合、<br>
     * 　@　@　@　@　@「注文単価区分がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00184<br>
     * <br>
     * 　@１－２）this.注文単価区分が以下の値以外の場合、<br>
     * 　@　@　@　@　@「注文単価区分が未定義の値」の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・”0：成行”<br>
     * 　@　@　@　@・”1：指値”<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00185<br>
     * <br>
     * ２）　@注文単価チェック<br>
     * 　@２－１）this.注文単価区分＝”0：成行”でかつ、this.注文単価≠null<br>
     * 　@　@　@　@　@の場合は、例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00116<br>
     * <br>
     * 　@２－２）this.注文単価区分＝”1：指値”でかつ、<br>
     * 　@　@　@　@　@以下のいずれかに該当する場合は、以下の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・this.注文単価＝null　@(「注文単価がnull」の例外をスロー。)<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00313<br>
     * 　@　@　@　@・this.注文単価≠数字 (「注文単価が数字以外」の例外をスロー。)<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * 　@　@　@　@・this.注文単価≦０　@　@ (「注文単価が0以下」の例外をスロー。)<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * 　@　@　@　@・this.注文単価＝8桁を超える数字<br>
     * 　@　@　@　@　@　@(「注文単価の桁数が8桁を超過」の例外をスロー。)<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * ３）　@値段条件チェック<br>
     * 　@３－１）this.値段条件＝nullの場合、<br>
     * 　@　@　@　@　@「値段条件がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01344<br>
     * <br>
     * 　@３－２）this.値段条件が以下の値以外の場合、<br>
     * 　@　@　@　@　@「値段条件が未定義の値」の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・”0：指定なし”<br>
     * 　@　@　@　@・”1：現在値指値”<br>
     * 　@　@　@　@・”3：優先指値”<br>
     * 　@　@　@　@・”5：成行残数指値”<br>
     * 　@　@　@　@・”7：成行残数取消”<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * ４）　@執行条件チェック<br>
     * 　@４－１）this.執行条件＝nullの場合、<br>
     * 　@　@　@　@　@「執行条件がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00197<br>
     * <br>
     * 　@４－２）this.執行条件が以下の値以外の場合、<br>
     * 　@　@　@　@　@「執行条件が未定義の値」の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・”1：無条件”<br>
     * 　@　@　@　@・”3：寄付”<br>
     * 　@　@　@　@・”4：引け”<br>
     * 　@　@　@　@・”7：不出来引け成行”<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00127<br>
     * <br>
     * ５）　@執行条件・注文単価区分チェック<br>
     * 　@this.執行条件＝”7：不出来引け成行”でかつ、<br>
     * 　@this.値段条件≠（”1：現在値指値”、”3：優先指値”） かつ、<BR>
     * 　@this.注文単価区分≠”1：指値”の場合、<br>
     * 　@「不出来引け成行時は成行不可」の例外をスローする。<br>
     * 　@※”1：現在値指値”、”3：優先指値”注文の場合、市場到着後にすぐに値が付くので、<BR> 
     * 　@※不出来引け成行であっても”0：成行”指定可とする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00114<br>
     * <br>
     * ６）　@注文期限区分チェック<br>
     * 　@６－１）this.注文期限区分＝nullの場合、<br>
     * 　@　@　@　@　@「注文期限区分がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00208<br>
     * <br>
     * 　@６－２）this.注文期限区分が以下の値以外の場合、<br>
     * 　@　@　@　@　@「注文期限区分が未定義の値」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00209<br>
     * <br>
     * 　@　@　@　@・”1：当日限り”<br>
     * 　@　@　@　@・”2：出来るまで注文”<br>
     * <br>
     * ７）　@注文期限区分・執行条件チェック<br>
     * 　@this.注文期限区分＝”2：出来るまで注文”でかつ、<br>
     * 　@this.執行条件≠”1：無条件”の場合、<br>
     * 　@「出来るまで注文時は執行条件指定不可」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00125<br>
     * <br>
     * ８）　@注文有効期限チェック<br>
     * 　@８－１）　@this.注文期限区分＝”1：当日限り”でかつ、<br>
     * 　@this.注文有効期限≠nullの場合、<br>
     * 　@「当日注文時は注文有効期限指定不可」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00117<br>
     * <br>
     * 　@８－２）　@this.注文期限区分＝”2：出来るまで注文”でかつ、<br>
     * 　@this.注文有効期限＝nullの場合、<br>
     * 　@「注文有効期限がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00210<br>
     * <br>
     * ９）　@発注条件区分チェック<br>
     * 　@９－１）this.発注条件区分＝nullの場合、<br>
     * 　@　@　@　@　@「発注条件区分がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00211<br>
     * <br>
     * 　@９－２）this.発注条件区分が以下の値以外の場合、<br>
     * 　@　@　@　@　@「発注条件区分が未定義の値」の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・”0：指定なし”<br>
     * 　@　@　@　@・”1：逆指値”<br>
     * 　@　@　@　@・”2：W指値”<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00212<br>
     * <br>
     * １０）　@発注条件のチェック１（”0：指定なし”）<br>
     * 　@this.発注条件区分＝”0：指定なし”でかつ、　@<br>
     *   以下のいずれかに該当する場合、以下の例外をスローする。<br>
     * <br>
     * 　@　@・this.逆指値用発注条件単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「逆指値用発注条件単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01872<br>
     * 　@　@・this.逆指値用発注条件演算子≠null<br>
     * 　@　@　@　@「逆指値発注条件演算子の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01873<br>
     * 　@　@・this.W指値用発注条件単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「W指値用発注条件単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01874<br>
     * 　@　@・this.W指値用発注条件演算子≠null<br>
     * 　@　@　@　@「W指値用発注条件演算子の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01875<br>
     * 　@　@・this.W指値用注文単価区分≠null<br>
     * 　@　@　@　@「W指値用注文単価区分の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01876<br>
     * 　@　@・this.W指値用注文単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「W指値用注文単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01877<br>
     * 　@　@・this.W指値用執行条件≠null <BR>
     *　@　@　@　@「発注条件区分が“指定なし”の場合は、<BR>
     * 　@　@　@　@Ｗ指値用執行条件が指定不可」の例外をスロー。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02525<BR>
     * <br>
     * １１）　@発注条件のチェック２（”1：逆指値”）<br>
     * 　@１１－１）this.発注条件区分＝”1：逆指値”でかつ、<br>
     * 　@　@　@　@　@以下のいずれかに該当する場合、例外をスローする。<br>
     * <br>
     * 　@　@・this.W指値用発注条件単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「W指値用発注条件単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01878<br>
     * 　@　@・this.W指値用発注条件演算子≠null<br>
     * 　@　@　@　@「W指値用発注条件演算子の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01879<br>
     * 　@　@・this.W指値用注文単価区分≠null<br>
     * 　@　@　@　@「W指値用注文単価区分の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01880<br>
     * 　@　@・this.W指値用注文単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「W指値用注文単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01881<br>
     *　@　@・this.W指値用執行条件≠null <BR>
     *　@　@　@　@「発注条件区分が“逆指値”の場合は、<BR>
     * 　@　@　@　@Ｗ指値用執行条件が指定不可」の例外をスロー。<BR> 
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_02526<BR>
     * <br>
     * 　@１１－２）this.発注条件区分＝”1：逆指値”でかつ、<br>
     * 　@　@　@　@　@以下のいずれかに該当する場合、例外をスローする。<br>
     * <br>
     * 　@　@・this.逆指値用発注条件単価＝null<br>
     * 　@　@　@　@「逆指値用発注条件単価がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00215<br>
     * 　@　@・this.逆指値用発注条件単価≠数字<br>
     * 　@　@　@　@「逆指値用発注条件単価が数字以外」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00216<br>
     * 　@　@・this.逆指値用発注条件単価≦０<br>
     * 　@　@　@　@「逆指値用発注条件単価が0以下」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00216<br>
     * 　@　@・this.逆指値用発注条件単価＝8桁を超える数字<br>
     * 　@　@　@　@「逆指値用発注条件単価の桁数が8桁を超過」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00216<br>
     * 　@　@・this.逆指値用発注条件演算子＝null<br>
     * 　@　@　@　@「逆指値用発注条件演算子がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00219<br>
     * 　@　@・this.逆指値用発注条件演算子≠(”1：以上”または”2：以下”)<br>
     * 　@　@　@　@「逆指値用発注条件演算子が未定義の値」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00220<br>
     * <br>
     * １２）　@発注条件のチェック３（”2：W指値”）<br>
     * 　@１２－１）this.発注条件区分＝”2：W指値”でかつ、<br>
     * 　@　@　@　@　@以下のいずれかに該当する場合、以下の例外をスローする。<br>
     * <br>
     * 　@　@・this.逆指値用発注条件単価≠（nullまたは”０”）<br>
     * 　@　@　@　@「逆指値用発注条件単価の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01882<br>
     * 　@　@・this.逆指値用発注条件演算子≠null<br>
     * 　@　@　@　@「逆指値発注条件演算子の指定不可」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01883<br>
     * <br>
     * 　@１２－２）this.発注条件区分＝”2：W指値”の値でかつ、<br>
     * 　@　@　@　@　@以下のいずれかに該当する場合、以下の例外をスローする。<br>
     * <br>
     * 　@　@・this.Ｗ指値用発注条件単価＝null<br>
     * 　@　@　@　@「W指値用発注条件単価がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00223<br>
     * 　@　@・this.Ｗ指値用発注条件単価≠数字<br>
     * 　@　@　@　@「W指値用発注条件単価が数字以外」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00224<br>
     * 　@　@・this.Ｗ指値用発注条件単価≦０<br>
     * 　@　@　@　@「W指値用発注条件単価が0以下」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00224<br>
     * 　@　@・this.Ｗ指値用発注条件単価＝8桁を超える数字<br>
     * 　@　@　@　@「W指値用発注条件単価の桁数が8桁を超過」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00224<br>
     * 　@　@・this.Ｗ指値用発注条件演算子＝null<br>
     * 　@　@　@　@「W指値用発注条件演算子がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00227<br>
     * 　@　@・this.Ｗ指値用発注条件演算子≠（”1：以上”または”2：以下”）<br>
     * 　@　@　@　@「W指値用発注条件演算子が未定義の値」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00228<br>
     * 　@　@・this.Ｗ指値用注文単価区分＝null<br>
     * 　@　@　@　@「W指値用注文単価区分がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00229<br>
     * 　@　@・this.Ｗ指値用注文単価区分≠（”0：成行”または”1：指値”）<br>
     * 　@　@　@　@「W指値用注文単価区分が未定義の値」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00230<br>
     *　@　@・this．Ｗ指値用執行条件＝null <BR>
     *　@　@　@　@「Ｗ指値用執行条件が未指定」の例外をスロー。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02499<BR>
     *　@　@・this．Ｗ指値用執行条件≠null <BR>
     *　@　@　@かつ下記の値以外の場合、「W指値注文の執行条件は“無条件”、<BR>
     * 　@　@　@“不出来引け成行”以外指定不可。」の例外をスロー。 <BR>
     * <BR>
     *　@　@　@　@　@1：無条件 <BR>
     *　@　@　@　@　@7：不出来引け成行<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02500<BR>
     * <br>
     * １３）　@Ｗ指値用注文単価チェック<br>
     * 　@１３－１）this.発注条件区分＝”2：W指値”でかつ、<br>
     * 　@　@　@　@this.Ｗ指値用注文単価区分＝”0：成行”でかつ<br>
     * 　@　@　@　@this.Ｗ指値用注文単価≠nullの場合、<br>
     * 　@　@　@　@「W指値(成行)時はW指値用注文単価の指定不可」の<br>
     * 　@　@　@　@例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00124<br>
     * <br>
     * 　@１３－２）this.発注条件区分＝”2：W指値”でかつ、<br>
     * 　@　@　@　@this.Ｗ指値用注文単価区分＝”1：指値”でかつ、<br>
     * 　@　@　@　@以下のいずれかに該当する場合は、以下の例外をスローする。<br>
     * <br>
     * 　@　@　@　@・this.Ｗ指値用注文単価＝null<br>
     * 　@　@　@　@　@　@「W指値用注文単価がnull」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00313<br>
     * 　@　@　@　@・this.Ｗ指値用注文単価≠数字<br>
     * 　@　@　@　@　@　@「W指値用注文単価が数字以外」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * 　@　@　@　@・this.Ｗ指値用注文単価≦０<br>
     * 　@　@　@　@　@　@「W指値用注文単価が0以下」の例外スロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * 　@　@　@　@・this.Ｗ指値用注文単価＝8桁を超える数字<br>
     * 　@　@　@　@　@　@「W指値用注文単価の桁数が8桁を超過」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * １４）　@注文株数チェック<br>
     * 　@　@　@　@this.注文株数≠nullでかつ、以下のいずれかに該当する場合は、<br>
     * 　@　@　@　@以下の例外をスローする。<br>
     * <br>
     * 　@　@　@・this.注文株数≠数字<br>
     * 　@　@　@　@　@　@「注文株数が数字以外」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00075<br>
     * 　@　@　@・this.注文株数≦０<br>
     * 　@　@　@　@　@　@「注文株数が0以下」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00076<br>
     * 　@　@　@・this.注文株数＝8桁を超える数字<br>
     * 　@　@　@　@　@　@「注文株数の桁数が8桁を超過」の例外をスロー。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00077<br>
     * <br>
     * １５）　@値段条件・注文単価区分のチェック<br>
     * 　@this.値段条件≠"0：指定なし"の場合、<br>
     * 　@this.注文単価区分≠「0：成行」であれば例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01348<br>
     * <br>
     * １６）　@値段条件・執行条件のチェック<br>
     * 　@this.値段条件＝（"5：成行残数指値注文"、"7：成行残数取消注文"）のいずれかの場合、<br>
     * 　@this.執行条件≠「1：無条件」であれば例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01349<br>
     * <br>
     * １７）　@値段条件・注文期限区分のチェック<br>
     * 　@this.値段条件≠"0：指定なし"の場合、<br>
     * 　@this.注文期限区分≠「1：当日限り」であれば例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01350<br>
     * <br>
     * １８）　@値段条件・発注条件区分のチェック<br>
     * 　@this.値段条件≠"0：指定なし"の場合、<br>
     * 　@this.発注条件区分≠「0：指定なし」であれば例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01351</p>
     * <br>
     * １９）　@発注条件・執行条件のチェック <BR>
     * 　@１９－１）　@　@this.発注条件＝"1：逆指値"の場合、 <BR>
     * 　@　@this.執行条件≠「1：無条件」であれば例外をスローする。 <BR>
     *  　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@　@tag:   BUSINESS_ERROR_02473<BR>
     * <BR>
     * 　@１９－２）　@this.発注条件＝"2：W指値"の場合、 <BR>
     * 　@　@this.執行条件が以下の値以外の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@・1：無条件 <BR>
     * 　@　@　@・7：不出来引け成行 <BR>
     *  　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@　@tag:   BUSINESS_ERROR_02500<BR>
     * <BR>
     * ２０）　@Ｗ指値用執行条件・注文単価区分チェック <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件＝”7:不出来引け成行”でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分≠”1：指値”の場合、例外をスローする。<BR>
     *  　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@　@tag:   BUSINESS_ERROR_02501<BR>
     * <BR>
     * ２１）　@Ｗ指値用有効状態区分チェック <BR>
     * 　@　@　@　@this.Ｗ指値用有効状態区分≠nullであれば、 <BR>
     * 　@　@　@　@以下の処理を行う。 <BR>
     * 　@２１－１）　@this.Ｗ指値有効状態区分が以下の値以外の場合、 <BR>
     * 　@　@　@　@例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@・0：リミット注文有効 <BR>
     * 　@　@　@　@　@・1：ストップ注文有効 <BR>
     * 　@　@　@　@　@・2：ストップ注文失効済 <BR>
     *  　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@　@tag:   BUSINESS_ERROR_02502<BR>
     * <BR>
     * ２２）　@Ｗ指値用執行条件・注文有効期限チェック <BR>
     * 　@　@　@　@this.発注条件区分＝”2：W指値”でかつ、 <BR>
     * 　@　@　@　@this.注文期限区分＝”2：出来るまで注文”でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件≠”1：無条件”の場合、 <BR>
     * 　@　@　@　@例外をスローする。<BR>
     *  　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@　@tag:   BUSINESS_ERROR_02503<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginCommonRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@注文単価区分チェック
        if (this.orderPriceDiv == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00184,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
                 && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00185,
            this.getClass().getName() + "validate");
        }

        // ２）　@注文単価チェック
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv) && this.limitPrice != null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00116,
            this.getClass().getName() + "validate");
        }

		if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
		{
			if (this.limitPrice == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00187,
				this.getClass().getName() + "validate");
			}

			if (!WEB3StringTypeUtility.isNumber(this.limitPrice))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00188,
				this.getClass().getName() + "validate");
			}

			if (Long.parseLong(this.limitPrice) <= 0 )
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00189,
				this.getClass().getName() + "validate");
			}

			if (this.limitPrice.length() > 8)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00190,
				this.getClass().getName() + "validate");
			}
		}
       
        // ３）　@値段条件チェック
        if(this.priceCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01343,
            this.getClass().getName() + "validate");
        }

        if(!(WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01344,
            this.getClass().getName() + "validate");
        }

        if (this.execCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00197,
            this.getClass().getName() + "validate");
        }

        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00127,
            this.getClass().getName() + "validate");
        }

        // ５）　@執行条件・注文単価区分チェック
        // this.執行条件＝”7：不出来引け成行” かつ、 
        // this.値段条件≠（”1：現在値指値”、”3：優先指値”） かつ、 
        // this.注文単価区分≠”1：指値”の場合、 
        // 「不出来引け成行時は成行不可」の例外をスローする。 
		// ※”1：現在値指値”、”3：優先指値”注文の場合、市場到着後にすぐに値が付くので、 
		// ※不出来引け成行であっても”0：成行”指定可とする。 
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)
            && !(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
                || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00114,
            this.getClass().getName() + "validate");
        }

        // ６）　@注文期限区分チェック
        if (this.expirationDateType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00208,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
                && !WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00209,
            this.getClass().getName() + "validate");
        }

        // ７）　@注文期限区分・執行条件チェック
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
                && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00125,
            this.getClass().getName() + "validate");
        }

        // ８）　@注文有効期限チェック
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
                && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00117,
            this.getClass().getName() + "validate");
        }

        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
                && this.expirationDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00210,
            this.getClass().getName() + "validate");
        }

        // ９）　@発注条件区分チェック
        if (this.orderCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00211,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00212,
            this.getClass().getName() + "validate");
        }

        // １０）　@発注条件のチェック１（”0：指定なし”）
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice != null && !"0".equals(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondPrice != null && !"0".equals(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                this.getClass().getName() + "validate");
            }
            
            //・this.W指値用執行条件≠null 
            //　@　@「発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可」の例外をスロー。 
            if (this.wlimitExecCondType != null)
            {
                log.debug("発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02525,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可です。");
            }
        }

        // １１）　@発注条件のチェック２（”1：逆指値”）
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.wlimitOrderCondPrice != null && !"0".equals(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                this.getClass().getName() + "validate");
            }
            
            //・this.W指値用執行条件≠null 
            //　@　@「発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可」の例外をスロー。 
            if (this.wlimitExecCondType != null)
            {
                log.debug("発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
            }
        }

        // 　@１１－２）this.発注条件区分＝”1：逆指値”
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                this.getClass().getName() + "validate");
            }

            if (!WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.stopOrderCondPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
                    && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                this.getClass().getName() + "validate");
            }
        }

        // １２）　@発注条件のチェック３（”2：W指値”）
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice != null && !"0".equals(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                this.getClass().getName() + "validate");
            }
        }

        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.wlimitOrderCondPrice == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                this.getClass().getName() + "validate");
            }

            if (!WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.wlimitOrderCondPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                    && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00229,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
                    && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00230,
                this.getClass().getName() + "validate");
            }
            
            //　@　@・this．Ｗ指値用執行条件＝null 
            //　@　@　@　@「Ｗ指値用執行条件が未指定」の例外をスロー。
            if (this.wlimitExecCondType == null)
            {
                log.debug("Ｗ指値用執行条件が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02499,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "Ｗ指値用執行条件が未指定です。");
            }
            
            //　@　@・this．Ｗ指値用執行条件≠null 
            //　@　@　@かつ下記の値以外の場合、「W指値注文の執行条件は“無条件”、
            //“不出来引け成行”以外指定不可。」の例外をスロー。 
            //　@　@　@　@　@1：無条件 
            //　@　@　@　@　@7：不出来引け成行
            else if (!(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType)
                || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)))
            {
                log.debug("W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
            }
        }

		// １３）　@Ｗ指値用注文単価チェック
		if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
				&& WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
				&& this.wLimitPrice != null)
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00124,
			this.getClass().getName() + "validate");
		}

		if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
				&& WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
		{
			if (this.wLimitPrice == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00313,
				this.getClass().getName() + "validate");
			}
    
			if (!WEB3StringTypeUtility.isNumber(this.wLimitPrice))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}
    
			if (Long.parseLong(this.wLimitPrice) <= 0)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}    

			if (this.wLimitPrice.length() > 8)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}    
		}

        // １４）　@注文株数チェック
        if (this.orderQuantity != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderQuantity))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.orderQuantity) <= 0)
            {

                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "validate");
            }    

            if (this.orderQuantity.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + "validate");
            }   
        }
        
        // １５）　@値段条件・注文単価区分のチェック 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01348,
            this.getClass().getName() + "validate");
        }

        // １６）　@値段条件・執行条件のチェック 
        if(WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01349,
                this.getClass().getName() + "validate");
            }
        }

        // １７）　@値段条件・注文期限区分のチェック 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01350,
            this.getClass().getName() + "validate");
        }

        // １８）　@値段条件・発注条件区分のチェック 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01351,
            this.getClass().getName() + "validate");
        }
        
		// １９）　@発注条件・執行条件のチェック
		if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
			&& !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02473,
				this.getClass().getName() + ".validate()");
		}
        
        //　@１９－２）　@this.発注条件＝"2：W指値"の場合、 
        //　@　@this.執行条件が以下の値以外の場合、例外をスローする。 
        //　@　@　@・1：無条件 
        //　@　@　@・7：不出来引け成行 
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            log.debug("W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");
        }
        
        //２０）　@Ｗ指値用執行条件・注文単価区分チェック 
        //　@　@this.Ｗ指値用執行条件＝”7:不出来引け成行”でかつ、 
        //　@　@this.Ｗ指値用注文単価区分≠”1：指値”の場合、例外をスローする。
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            log.debug("Ｗ指値用注文単価区分が”指値”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02501,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "Ｗ指値用注文単価区分が”指値”以外の値です。");
        }
        
        //２１）　@Ｗ指値用有効状態区分チェック 
        //　@　@this.Ｗ指値用有効状態区分≠nullであれば、 
        //　@　@以下の処理を行う。
        //２１－１）　@this.Ｗ指値有効状態区分が以下の値以外の場合、
        //　@　@例外をスローする。
        //　@　@　@・0：リミット注文有効
        //　@　@　@・1：ストップ注文有効
        //　@　@　@・2：ストップ注文失効済
        if (this.wlimitEnableStatusDiv != null 
            && !(WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE.equals(this.wlimitEnableStatusDiv)))
        {
            log.debug("Ｗ指値有効状態区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02502,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "Ｗ指値有効状態区分の値が存在しないコード値です。");
        }

        // ２２）　@Ｗ指値用執行条件・注文有効期限チェック 
        //　@　@this.発注条件区分＝”2：W指値”でかつ、 
        //　@　@　@this.注文期限区分＝”2：出来るまで注文”でかつ、 
        //　@　@　@this.Ｗ指値用執行条件≠”1：無条件”の場合、 
        //　@　@　@例外をスローする。
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("Ｗ指値用執行条件が”無条件”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "Ｗ指値用執行条件が”無条件”以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate連続注文)<BR>
     * 当リクエストデータの整合性チェック（連続注文用）を行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@値段条件チェック<BR>
     * 　@値段条件≠"指定なし"の場合は、<BR>
     * 　@「連続注文は値段条件指定不可」の例外をthrowする。<BR>
     * <BR>
     * ２）　@執行条件チェック<BR>
     * 　@執行条件≠"無条件"の場合は、<BR>
     * 　@「連続注文は執行条件指定不可」の例外をthrowする。<BR>
     * <BR>
     * ３）　@発注条件チェック<BR>
     * 　@発注条件区分≠"指定なし"の場合は、<BR>
     * 　@「連続注文は発注条件指定不可」の例外をthrowする。<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateSuccOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateSuccOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02234,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02235,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02236,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * <p>（createレスポンス）。</p>
     * <p>nullを返す。</p>
     * @@return null
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
