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
filename	WEB3EquityCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式共通リクエスト(WEB3EquityCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 李雲峰 (中訊) 新規作成
                   2006/11/01 張騰宇(中訊) モデル 948,1000
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;

/**
 * （現物株式共通リクエスト）。<br>
 * <br>
 * 現物株式共通要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityCommonRequest extends WEB3GenRequest
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20040521001L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_common";

    /**
     * 注文株数<BR>
     */
    public String orderQuantity;

    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0:成行　@1:指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * <BR>
     * 注文単価区分が「指値」の場合に設定<BR>
     */
    public String limitPrice;
    
    /**
     * (値段条件)<BR>
     * <BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消<BR>
     */
    public String priceCondType;

    /**
     * (執行条件)<BR>
     * <BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;

    /**
     * (注文期限区分)<BR>
     * <BR>
     * 1：当日限り　@2：出来るまで注文<BR>
     */
    public String expirationDateType;

    /**
     * (注文有効期限)<BR>
     * <BR>
     * 注文期限区分が「出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate;

    /**
     * (発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * <BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (Ｗ指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * <BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (Ｗ指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     * 仮に、どちらも選択されなかった場合、nullとされる。<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W指値用注文単価)<BR>
     * <BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
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
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 発注条件区分が、「2：W指値」の場合設定される。<BR>
     * ※訂正時のみセット。<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCommonRequest.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 40611B320200
     */
    public WEB3EquityCommonRequest()
    {

    }

    /**
     * （validate）<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@注文株数のチェック<BR>
     * 　@this．注文株数の値が以下のいずれかに該当する場合は、<BR>
     * 　@例外をスローする。<BR>
     * <BR>
     * 　@  ・null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00126<BR>
     * 　@　@・数字以外<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00901<BR>
     * 　@　@・０以下の数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00902<BR>
     * 　@　@・８桁を超える数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00903<BR>
     * <BR>
     * ２）　@注文単価区分チェック<BR>
     * 　@２－１）this.注文単価区分＝nullの場合、<BR>
     * 　@　@　@　@　@「注文単価区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00184<BR>
     * <BR>
     * 　@２－２）this.注文単価区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「注文単価区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・0：成行<BR>
     * 　@　@　@　@・1：指値<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00185<BR>
     * <BR>
     * ３）　@値段条件チェック<BR>
     * 　@３－１）this.値段条件＝nullの場合、<BR>
     * 　@　@　@　@　@「値段条件がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01343<BR>
     * <BR>
     * 　@３－２）this.値段条件≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「値段条件が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・0：指定なし<BR>
     * 　@　@　@　@・1：現在値指値注文<BR>
     * 　@　@　@　@・3：優先指値注文<BR>
     * 　@　@　@　@・5：成行残数指値注文<BR>
     * 　@　@　@　@・7：成行残数取消注文<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01344<BR>
     * <BR>
     * ４）　@執行条件チェック<BR>
     * 　@４－１）this.執行条件＝nullの場合、<BR>
     * 　@　@　@　@　@「執行条件がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00197<BR>
     * <BR>
     * 　@４－２）this.執行条件≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「執行条件が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・1：無条件<BR>
     * 　@　@　@　@・3：寄付<BR>
     * 　@　@　@　@・4：引け<BR>
     * 　@　@　@　@・7：不出来引け成行<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00150<BR>
     * <BR>
     * ５）　@注文期限区分チェック<BR>
     * 　@５－１）this.注文期限区分＝nullの場合、<BR>
     * 　@　@　@　@　@「注文期限区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00208<BR>
     * <BR>
     * 　@５－２）this.注文期限区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「注文期限区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・1：当日限り<BR>
     * 　@　@　@　@・2：出来るまで注文<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00209<BR>
     * <BR>
     * ６）　@発注条件区分チェック<BR>
     * 　@６－１）this.発注条件区分＝nullの場合、<BR>
     * 　@　@　@　@　@「発注条件区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00211<BR>
     * <BR>
     * 　@６－２）this.発注条件区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「発注条件区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・0：指定なし<BR>
     * 　@　@　@　@・1：逆指値<BR>
     * 　@　@　@　@・2：W指値<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00212<BR>
     * <BR>
     * ７）　@注文単価・執行条件のチェック<BR>
     * 　@this．執行条件＝”7：不出来引け成行”且つ、<BR>
     * 　@this．値段条件≠（”1：現在値指値”、”3：優先指値”）且つ、<BR>
     * 　@this．注文単価区分≠”1：指値”であれば例外をスローする。<BR>
     * 　@※”1：現在値指値”、”3：優先指値”注文の場合、市場到着後にすぐに値が付くので、<BR>
     * 　@※不出来引け成行であっても”0：成行”指定可とする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00114<BR>
     * <BR>
     * ８）　@注文単価区分・単価 の整合性チェック<BR>
     * 　@８－１)　@this．注文単価区分＝”1：指値”且つ、<BR>
     * 　@　@this．注文単価の値が以下のいずれかに該当する場合は、<BR>
     * 　@　@例外をスローする<BR>
     * <BR>
     * 　@　@  ・null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00187<BR>
     * 　@　@  ・数字以外<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00188<BR>
     * 　@　@  ・０以下の数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00189<BR>
     * 　@  　@・８桁を超える数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00190<BR>
     * <BR>
     * 　@８－２)　@this．注文単価区分＝”0：成行”且つ、<BR>
     * 　@　@this．注文単価≠（nullまたは”０”）であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00116<BR>
     * <BR>
     * ９）　@期限のチェック<BR>
     * 　@９－１)　@　@　@this．注文期限区分＝”1：当日限り”且つ、<BR>
     * 　@this．注文有効期限≠nullであれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00117<BR>
     * 　@９－２)　@　@this．注文期限区分＝”2：出来るまで注文”且つ、<BR> 
     * 　@this．注文有効期限＝nullであれば例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00210<BR>
     * <BR>
     * １０）　@発注条件のチェック１（”0：指定なし”）<BR>
     * 　@this．発注条件区分＝”0：指定なし”且つ、<BR>
     *   以下のいずれかに該当する場合は例外をスローする。<BR>
     * <BR>
     * 　@　@・this．逆指値用発注条件単価≠（nullまたは”０”） <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01872<BR>
     * 　@　@・this．逆指値用発注条件演算子≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01873<BR>
     * 　@　@・this．W指値用発注条件単価≠（nullまたは”０”）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01874<BR>
     * 　@　@・this．W指値用発注条件演算子≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01875<BR>
     * 　@　@・this．W指値用注文単価区分≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01876<BR>
     * 　@　@・this．W指値用注文単価≠（nullまたは”０”）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01877<BR>
     * 　@　@・this．Ｗ指値用執行条件≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02525<BR> 
     * <BR>
     * １１）　@発注条件のチェック２（”1：逆指値”）<BR>
     * 　@１１－１)　@this．発注条件区分＝”1：逆指値”且つ、<BR>
     * 　@　@以下のいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@・this．W指値用発注条件単価≠（nullまたは”０”）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01878<BR>
     * 　@　@・this．W指値用発注条件演算子≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01879<BR>
     * 　@　@・this．W指値用注文単価区分≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01880<BR>
     * 　@　@・this．W指値用注文単価≠（nullまたは”０”）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01881<BR>
     * 　@　@・this．Ｗ指値用執行条件≠null <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02526<BR>
     * <BR>
     * 　@１１－２)　@this．発注条件区分＝”1：逆指値”且つ、<BR>
     * 　@　@以下のいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@・this．逆指値用発注条件単価の値が下記のいずれかに<BR>
     * 　@　@ 該当する<BR>
     * 　@　@　@・null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00215<BR>
     * 　@　@  ・数字以外<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00216<BR>
     * 　@　@  ・０以下の数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00217<BR>
     * 　@  　@・８桁を超える数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00218<BR>
     * <BR>
     * 　@　@・this．逆指値用発注条件演算子＝null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00219<BR>
     * 　@　@・this．逆指値用発注条件演算子≠null<BR>
     * 　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00220<BR>
     * <BR>
     * １２）　@発注条件のチェック３（”2：W指値”）<BR>
     * 　@１２－１)　@this．発注条件区分＝”2：W指値”且つ、<BR>
     * 　@　@以下のいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@・this．逆指値用発注条件単価≠（nullまたは”０”）<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01882<BR>
     * 　@　@・this．逆指値用発注条件演算子≠null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01883<BR>
     * <BR>
     * 　@１２－２)　@this．発注条件区分＝”2：W指値”且つ、<BR>
     * 　@　@以下のいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@・this．W指値用発注条件演算子＝null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00227<BR>
     * 　@　@・this．W指値用発注条件演算子≠null<BR>
     * 　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@1：以上<BR>
     * 　@　@　@　@2：以下<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00228<BR>
     * 　@　@・this．W指値用発注単価区分＝null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01358<BR>
     * 　@　@・this．W指値用発注単価区分≠null<BR>
     * 　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@・0：成行<BR>
     * 　@　@　@　@・1：指値<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01359<BR>
     * 　@　@・this．W指値用発注条件単価の値が以下のいずれか<BR>
     * 　@　@　@　@　@null<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00223<BR>
     * 　@　@　@　@　@数字以外<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01361<BR>
     *  　@　@　@　@０以下の数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01360<BR>
     * 　@　@　@　@　@８桁を超える数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01362<BR>
     * 　@　@・this．Ｗ指値用執行条件＝null <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02499<BR>
     * 　@　@・this．Ｗ指値用執行条件≠null <BR>
     * 　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@1：無条件<BR>
     * 　@　@　@　@　@7：不出来引け成行<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02500<BR>
     * <BR>
     * １３）　@W指値用注文単価区分・W指値用注文単価 の整合性チェック<BR>
     * 　@１３－１)　@this．W指値用注文単価区分＝”1：指値”且つ、<BR>
     * 　@　@this．W指値用注文単価の値が以下のいずれかに該当する<BR>
     * 　@ 場合は、例外をスローする。<BR>
     * <BR>
     * 　@　@・null
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00313<BR>
     * 　@　@・数字以外<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00314<BR>
     * 　@　@・０以下の数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00315<BR>
     * 　@　@・８桁を超える数字<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00316<BR>
     * <BR>
     * 　@１３－２)　@this．W指値用注文単価区分＝”0：成行”且つ、<BR>
     * 　@　@this．W指値用注文単価≠（nullまたは”０”）であれば、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00124<BR>
     * <BR>
     * １４）　@注文期限・執行条件のチェック<BR>
     * 　@this．注文期限区分＝”2：出来るまで注文”の場合、<BR>
     * 　@this．執行条件≠「1：無条件」であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00125<BR>
     * <BR>
     * １５）　@値段条件・注文単価区分のチェック<BR>
     * 　@this.値段条件≠"0：指定なし"の場合、<BR>
     * 　@this.注文単価区分≠「0：成行」であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01348<BR>
     * <BR>
     * １６）　@値段条件・執行条件のチェック<BR>
     * 　@this.値段条件＝（"5：成行残数指値注文"、"7：成行残数取消注文"）のいずれかの場合、<BR>
     * 　@this.執行条件≠「1：無条件」であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01349<BR>
     * <BR>
     * １７）　@値段条件・注文期限区分のチェック<BR>
     * 　@this.値段条件≠"0：指定なし"の場合、<BR>
     * 　@this.注文期限区分≠「1：当日限り」であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01350<BR>
     * <BR>
     * １８）　@値段条件・発注条件区分のチェック<BR>
     * 　@this.値段条件≠"0：指定なし"の場合、<BR>
     * 　@this.発注条件区分≠「0：指定なし」であれば例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01351<BR>
     * <BR>
     * １９）　@発注条件・執行条件のチェック <BR>
     * 　@１９－１）　@this.発注条件＝"1：逆指値"の場合、 <BR>
     * 　@　@this.執行条件≠「1：無条件」であれば例外をスローする。 <BR>
     *  　@　@　@class : WEB3BusinessLayerException<BR>
     *  　@　@　@tag   : BUSINESS_ERROR_02473<BR>
     * <BR>
     * 　@１９－２）　@this.発注条件＝"2：W指値"の場合、 <BR>
     * 　@　@this.執行条件が以下の値以外の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@・1：無条件 <BR>
     * 　@　@　@・7：不出来引け成行<BR>
     *  　@　@　@class : WEB3BusinessLayerException<BR>
     *  　@　@　@tag   : BUSINESS_ERROR_02500<BR>
     * <BR>
     * ２０）　@Ｗ指値用執行条件・注文単価区分チェック <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件＝”7:不出来引け成行”でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用注文単価区分≠”1：指値”の場合、例外をスローする。<BR>
     *  　@　@　@class : WEB3BusinessLayerException<BR>
     *  　@　@　@tag   : BUSINESS_ERROR_02501<BR>
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
     *  　@　@　@class : WEB3BusinessLayerException<BR>
     *  　@　@　@tag   : BUSINESS_ERROR_02502<BR>
     * <BR>
     * ２２）　@Ｗ指値用執行条件・注文有効期限チェック <BR>
     * 　@　@　@　@this.発注条件区分＝”2：W指値”でかつ、 <BR>
     * 　@　@　@　@this.注文期限区分＝”2：出来るまで注文”でかつ、 <BR>
     * 　@　@　@　@this.Ｗ指値用執行条件≠”1：無条件”の場合、 <BR>
     * 　@　@　@　@例外をスローする。<BR>
     *  　@　@　@class : WEB3BusinessLayerException<BR>
     *  　@　@　@tag   : BUSINESS_ERROR_02503<BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 40600E47029F
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";

        log.entering(STR_METHOD_NAME);

        // １）　@注文株数のチェック
        if(this.orderQuantity == null)
        {
            // 注文株数がnullの場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + ".validate()");
        }

        try
        {
            int l_intOrderQuantity = Integer.parseInt(this.orderQuantity);
            if(l_intOrderQuantity <= 0)
            {
                // 注文株数が０以下の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                    this.getClass().getName() + ".validate()");
            }
            
            if(99999999 < l_intOrderQuantity)
            {
                // 注文株数が８桁を超える
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00903,
                    this.getClass().getName() + ".validate()");
            }
            
            
        } catch(NumberFormatException e)
        {
            // 注文株数が数値以外の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                this.getClass().getName() + ".validate()");
        }

        // ２）　@注文単価区分チェック
        if(this.orderPriceDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + ".validate()");
        }
        if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + ".validate()");
        }

        // ３）　@値段条件チェック
        if(this.priceCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01343,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01344,
                this.getClass().getName() + ".validate()");
        }

        // ４）　@執行条件チェック
        if(this.execCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + ".validate()");
        }

        // ５）　@注文期限区分チェック
        if(this.expirationDateType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            || WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                this.getClass().getName() + ".validate()");
        }
        
        // ６）　@発注条件区分チェック
        if(this.orderCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00211,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                this.getClass().getName() + ".validate()");
        }

        //  ７）　@注文単価・執行条件のチェック<BR>
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType) &&
            !WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType) &&
            !WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType) &&
            !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                this.getClass().getName() + ".validate()");
        }

        // ８）　@注文単価区分・単価 の整合性チェック
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            if(this.limitPrice == null)
            {
                // 注文単価がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00187,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intLimitPrice = Integer.parseInt(this.limitPrice);
                if(l_intLimitPrice <= 0)
                {
                    // 注文単価が０以下の場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00189,
                        this.getClass().getName() + ".validate()");
                }
                if(99999999 < l_intLimitPrice)
                {
                    // 注文単価が８桁を超える場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00190,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // 注文単価が数値以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00188,
                    this.getClass().getName() + ".validate()");
            }
        } else if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
        {
            if(this.limitPrice != null && !this.limitPrice.equals("0"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                    this.getClass().getName() + ".validate()");
            }
        }

        // ９）　@期限のチェック
        if(WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                this.getClass().getName() + ".validate()");
        }
        
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
        	&& this.expirationDate == null)
        {
        	throw new WEB3BusinessLayerException(
        		WEB3ErrorCatalog.BUSINESS_ERROR_00210,
        		this.getClass().getName() + ".validate()");
        }
        

        // １０）　@発注条件のチェック１（”0：指定なし”）
        if(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            if(!(this.stopOrderCondPrice == null || this.stopOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!(this.wlimitOrderCondPrice == null || this.wlimitOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                    this.getClass().getName() + ".validate()");
            }
            
			if(this.wLimitOrderPriceDiv != null)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01876,
					this.getClass().getName() + ".validate()");
			}
			
            if(!(this.wLimitPrice == null || this.wLimitPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                    this.getClass().getName() + ".validate()");
            }
            
            //・this．Ｗ指値用執行条件≠null 
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
        if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if(!(this.wlimitOrderCondPrice == null || this.wlimitOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                    this.getClass().getName() + ".validate()");
            }
            
			if(this.wLimitOrderPriceDiv != null)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01880,
					this.getClass().getName() + ".validate()");
			}
			
            if(!(this.wLimitPrice == null || this.wLimitPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                    this.getClass().getName() + ".validate()");
            }
            
            //・this．Ｗ指値用執行条件≠null 
            if (this.wlimitExecCondType != null)
            {
                log.debug("発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");
            }
            
            if(this.stopOrderCondPrice == null) {
                // 逆指値用発注条件単価がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                    this.getClass().getName() + ".validate()");
            }
            try
            {
                int l_intStopOrderCondPrice = Integer.parseInt(this.stopOrderCondPrice);
                
                if(l_intStopOrderCondPrice <= 0)
                {
                    // 逆指値用発注条件単価が０以下の場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intStopOrderCondPrice)
                {
                    // 逆指値用発注条件単価が８桁を超える場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                        this.getClass().getName() + ".validate()");
                }
                
            } catch(NumberFormatException e)
            {
                // 逆指値用発注条件単価が数値以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator == null)
            {
                // 逆指値用発注条件演算子がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
            {
                // 逆指値用発注条件演算子が定義値以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                    this.getClass().getName() + ".validate()");
            }
            
        }

        // １２）　@発注条件のチェック３（”2：W指値”）
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if(!(this.stopOrderCondPrice == null || this.stopOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator == null)
            {
                // W指値用発注条件演算子がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                // W指値用発注条件演算子が定義値以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wLimitOrderPriceDiv == null)
            {
                // W指値用注文単価区分がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01358,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
                && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                // W指値用注文単価区分が定義値以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01359,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondPrice == null)
            {
                // W指値用発注条件単価がnullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intWlimitOrderCondPrice = Integer.parseInt(this.wlimitOrderCondPrice);
                if(l_intWlimitOrderCondPrice <= 0)
                {
                    // W指値用発注条件単価が０以下の場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01360,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intWlimitOrderCondPrice)
                {
                    // W指値用発注条件単価が8桁を超えた場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01361,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // W指値用発注条件単価が数字以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01362,
                    this.getClass().getName() + ".validate()");
            }
            
            //・this．Ｗ指値用執行条件＝null
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
            //　@　@　@かつ下記の値以外の場合、 
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

        // １３）　@W指値用注文単価区分・W指値用注文単価 の整合性チェック
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            if(this.wLimitPrice == null)
            {
                // W指値用注文単価がNullの場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00313,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intWlimitPrice = Integer.parseInt(this.wLimitPrice);
                
                if(l_intWlimitPrice <= 0)
                {
                    // W指値用注文単価が０以下の場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intWlimitPrice)
                {
                    // W指値用注文単価が８桁を超える場合
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // W指値用注文単価が数字以外の場合
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                    this.getClass().getName() + ".validate()");
            }
        } else if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            if(this.wLimitPrice != null && !this.wLimitPrice.equals("0"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00124,
                    this.getClass().getName() + ".validate()");
            }
        }

        // １４）　@注文期限・執行条件のチェック
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                    this.getClass().getName() + ".validate()");
            }
        }

        // １５）　@値段条件・注文単価区分のチェック
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01348,
                    this.getClass().getName() + ".validate()");
            }
        }

        // １６）　@値段条件・執行条件のチェック
        if(WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01349,
                    this.getClass().getName() + ".validate()");
            }
        }

        // １７）　@値段条件・注文期限区分のチェック
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01350,
                    this.getClass().getName() + ".validate()");
            }
        }

        // １８）　@値段条件・発注条件区分のチェック
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01351,
                    this.getClass().getName() + ".validate()");
            }
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
        //　@this.執行条件が以下の値以外の場合、例外をスローする。
        //　@・1：無条件 
        //　@・7：不出来引け成行
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
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 409EFF3C0270
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
