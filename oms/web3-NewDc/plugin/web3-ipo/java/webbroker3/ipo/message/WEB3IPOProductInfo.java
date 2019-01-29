head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO銘柄情報メッセージデータ(WEB3IPOProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
                 : 2006/11/22 何文敏 (中訊) モデル No.166
Revesion History : 2008/08/22 王志葵 (中訊) モデル No.178
Revesion History : 2010/09/23 車進 (中訊) モデル No.181
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.common.define.WEB3EnableMarketOrderDef;
import webbroker3.common.define.WEB3IpoRegistDetailDivDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3IpoUnitDivDef;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * IPO銘柄情報メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOProductInfo extends Message
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOProductInfo.class);
        
    /**
     * IPO登録区分<BR>
     * <BR>
     * １：新規上場<BR>
     * ２：既上場<BR>
     */
    public String ipoRegistDiv;
    
    /**
     * IPO登録区分詳細<BR>
     * <BR>
     * １：募集<BR>
     * ２：売出し<BR>
     * ３：私募<BR>
     * ４：募集・売出し<BR>
     * ５：募集の取扱い<BR>
     * ６：売出しの取扱い<BR>
     * ７：私募の取扱い<BR>
     * ８：募集・売出しの取扱い<BR>
     */
    public String ipoRegistDetailDiv;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * 未定決定区分<BR>
     * <BR>
     * ０： スケジュール未定<BR>
     * １： スケジュール決定
     */
    public String undecideDecideDiv;
    
    /**
     * 公開日
     */
    public WEB3IPOTermUnit publicOfferingDate;
    
    /**
     * 公開市場コード<BR>
     * <BR>
     * 10：　@東証　@<BR>
     * 11：　@東証一部　@<BR>
     * 12：　@東証二部　@<BR>
     * 13：　@マザーズ　@<BR>
     * 20：　@大証　@<BR>
     * 21：　@大証一部　@<BR>
     * 22：　@大証二部　@<BR>
     * 30：　@名証　@<BR>
     * 31：　@名証一部　@<BR>
     * 32：　@名証二部　@<BR>
     * 33：　@セントレックス<BR>
     * 40：　@福証　@<BR>
     * 41：　@Q-Board<BR>
     * 50：　@札証　@<BR>
     * 51：　@アンビシャス<BR>
     * 90：　@JASDAQ（スタンダード）
     * 91：　@JASDAQ（グロース）
     */
    public String publicOfferingMarketCode;
    
    /**
     * 仮条件区分<BR>
     * <BR>
     * １：実価格（円）<BR>
     * ２：ディスカウント率（％）
     */
    public String temporaryConditionDiv;
    
    /**
     * 仮条件下限値
     */
    public String temporaryConditionLower;
    
    /**
     * 仮条件上限値
     */
    public String temporaryConditionUpper;
    
    /**
     * 仮条件提示日
     */
    public Date temporaryConditionPresentationDate;
    
    /**
     * 公募数量
     */
    public String issuedQuantity;
    
    /**
     * 売出数量
     */
    public String offeringQuantity;
    
    /**
     * 当社取扱数量
     */
    public String handlingQuantity;
    
    /**
     * 購入申込単位
     */
    public String offerUnit;
    
    /**
     * 刻み
     */
    public String tickValue;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）
     */
    public String displayUnitDiv;
    
    /**
     * 成行可能<BR>
     * <BR>
     * ０： 成行可能（指値／成行）<BR>
     * １： 成行不可（指値のみ）
     */
    public String marketOrderFlag;
    
    /**
     * 主幹事証券会社名
     */
    public String leadManagingUnderwriter;
    
    /**
     * ブックビルディング開始日時
     */
    public Date bookBuildingStartDate;
    
    /**
     * ブックビルディング終了日時
     */
    public Date bookBuildingEndDate;
    
    /**
     * 公開価格決定日
     */
    public WEB3IPOTermUnit publicOfferingPriceDetermDate;
    
    /**
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 公開価格ディスカウント率
     */
    public String publicOfferingDiscountRate;
    
    /**
     * 抽選日
     */
    public WEB3IPOTermUnit lotDate;
    
    /**
     * 購入申込期間（目論見書）
     */
    public WEB3IPOTermUnit prospectusOfferTerm;
    
    /**
     * 購入申込期間（当社指定）
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * 発行会社ロゴファ@イルURL
     */
    public String issuerLogoFileURL;
    
    /**
     * 発行会社ウェブサイトURL
     */
    public String issuerWebSiteURL;
    
    /**
     * 発行会社概要
     */
    public String issuerCorporateOutline;
    
    /**
     * 備考
     */
    public String note;
    
    /**
     * (目論見書閲覧区分)<BR>
     * 目論見書閲覧区分 <BR>
     * <BR>
     * 0：閲覧要<BR>
     * 1：需要申告時閲覧不要<BR>
     */
    public String prospectusReadDiv;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C6723801F0
     */
    public WEB3IPOProductInfo() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@this.IPO登録区分のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00434<BR>
     * 　@１－２）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00436<BR>
     * <BR>
     * ２）　@this.IPO登録区分詳細のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00435<BR>
     * 　@２－２）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00437<BR>
     * <BR>
     * ３）　@this.銘柄コードのチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00438<BR>
     * 　@３－２）　@サイズが5byte以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * ４）　@this.銘柄名のチェック<BR>
     * 　@４－１）<BR>
     * 　@　@○　@this.IPO登録区分 == ”新規上場”の場合<BR>
     * 　@　@　@未入力の場合であれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00440<BR>
     * <BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@４－２）　@サイズが50byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00441<BR>
     * <BR>
     * ５）　@this.公開日のチェック<BR>
     * 　@※ this.未定決定区分 == ”決定”の場合、以下のチェックを行う。<BR>
     * 　@５－１）　@this.公開日.開始日時が未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00442<BR>
     * 　@５－２）　@this.公開日.validate()をコールする。<BR>
     * <BR>
     * ６）　@this.市場コードのチェック<BR>
     * 　@６－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * ７）　@this.仮条件区分のチェック<BR>
     * 　@７－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00444<BR>
     * 　@７－２）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00445<BR>
     * <BR>
     * ８）　@this.仮条件下限値のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@８－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00446<BR>
     * 　@８－２）　@桁数チェック<BR>
     * 　@　@○ this.仮条件区分 == ”実価格（円）”の場合<BR>
     * 　@　@　@－サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00447<BR>
     * 　@　@　@－整数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00488<BR>
     * 　@　@○ this.仮条件区分 == ”ディスカウント率（％）”の場合<BR>
     * 　@　@　@－サイズが整数部2桁／少数部2桁以内のレンジでない場合、例外を<BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00448<BR>
     * <BR>
     * ９）　@this.仮条件上限値のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@９－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00449<BR>
     * 　@９－２）　@桁数チェック<BR>
     * 　@　@○ this.仮条件区分 == ”実価格（円）”の場合<BR>
     * 　@　@　@－サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00450<BR>
     * 　@　@　@－整数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00489<BR>
     * 　@　@○ this.仮条件区分 == ”ディスカウント率（％）”の場合<BR>
     * 　@　@　@－サイズが整数部2桁／少数部2桁以内の範囲外の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00451<BR>
     * <BR>
     * １０）　@this.仮条件提示日のチェック<BR>
     * 　@１０－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00452<BR>
     * <BR>
     * １１）　@this.公募数量のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１１－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00453<BR>
     * 　@１１－２）　@サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00454<BR>
     * <BR>
     * １２）　@this.売出数量のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１２－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00455<BR>
     * 　@１２－２）　@サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00456<BR>
     * <BR>
     * １３）　@this.当社取扱数量のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１３－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00457<BR>
     * 　@１３－２）　@サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00458<BR>
     * <BR>
     * １４）　@this.購入申込単位のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１４－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00459<BR>
     * 　@１４－２）　@サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00460<BR>
     * 　@１４－３）　@<BR>
     * 　@　@○ this.当社取扱数量、this.購入申込単位の両方に入力がある場合<BR>
     * 　@　@　@－（this.購入申込単位 > this.当社取扱数量）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00461<BR>
     * 　@　@　@－（this.当社取扱数量 % this.購入申込単位 != 0）の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00462<BR>
     * 　@　@○ this.売出数量、this.購入申込単位の両方に入力がある場合<BR>
     * 　@　@　@－（this.売出数量 % this.購入申込単位 != 0）の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00463<BR>
     * 　@　@○ this.公募数量、this.購入申込単位の両方に入力がある場合<BR>
     * 　@　@　@－（this.公募数量 % this.購入申込単位 != 0）の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00464<BR>
     * <BR>
     * １５）　@this.刻みのチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１５－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00465<BR>
     * 　@１５－２）　@桁数チェック。<BR>
     * 　@　@○ this.仮条件区分 == ”実価格（円）”の場合<BR>
     * 　@　@　@－サイズが6byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00466<BR>
     * 　@　@　@－整数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00467<BR>
     * 　@　@○ this.仮条件区分 == ”ディスカウント率（％）”の場合<BR>
     * 　@　@　@－サイズが整数部2桁／少数部2桁以内の範囲外の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00468<BR>
     * 　@１５－４）　@ゼロチェック。
     * 　@　@　@－ゼロが入力されたら、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00465<BR>
     * <BR>
     * １６）　@this.表示用単位区分のチェック<BR>
     * 　@１６－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00469<BR>
     * 　@１６－２）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00470<BR>
     * <BR>
     * １７）　@this.主幹事証券会社名のチェック<BR>
     * 　@１７－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00471<BR>
     * 　@１７－２）　@サイズが80byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00472<BR>
     * <BR>
     * １８）　@this.ブックビルディング開始日時のチェック<BR>
     * 　@１８－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00473<BR>
     * <BR>
     * １９）　@this.ブックビルディング終了日時のチェック<BR>
     * 　@１９－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00474<BR>
     * <BR>
     * ２０）　@this.公開価格決定日のチェック<BR>
     * 　@※ this.未定決定区分 == ”決定”の場合、以下のチェックを行う。<BR>
     * 　@２０－１）　@this.公開価格決定日.開始日時が未入力の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00475<BR>
     * 　@２０－２）　@this.公開価格決定日.validate()をコールする。<BR>
     * <BR>
     * ２１）　@this.公開価格のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２１－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00476<BR>
     * 　@２１－２）　@サイズが9byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00477<BR>
     * <BR>
     * ２２）　@this.公開価格ディスカウント率のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２２－１）　@サイズが整数部2桁／少数部2桁以内の範囲外の場合、例外を
     * <BR>
     * スローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00478<BR>
     * <BR>
     * ２３）　@this.抽選日のチェック<BR>
     * 　@※ this.未定決定区分 == ”決定”の場合、以下のチェックを行う。<BR>
     * 　@２３－１）　@this.抽選日.開始日時が未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00479<BR>
     * 　@２３－２）　@this.抽選日.validate()をコールする。<BR>
     * <BR>
     * ２４）　@this.購入申込期間（目論見書）のチェック<BR>
     * 　@※ this.未定決定区分 == ”決定”の場合、以下のチェックを行う。<BR>
     * 　@２４－１）　@this.購入申込期間（目論見書）.開始日時が未入力の場合、
     * <BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00480<BR>
     * 　@２４－２）　@this.購入申込期間（目論見書）.終了日時が未入力の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00481<BR>
     * 　@２４－３）　@this.購入申込期間（目論見書）.validate()をコールする。<BR>
     * <BR>
     * ２５）　@this.購入申込期間（当社指定）のチェック<BR>
     * 　@※ this.未定決定区分 == ”決定”の場合、以下のチェックを行う。<BR>
     * 　@２５－１）　@this.購入申込期間（当社指定）.開始日時が未入力の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00482<BR>
     * 
     * 　@２５－２）　@this.購入申込期間（当社指定）.終了日時が未入力の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00483<BR>
     * 
     * 　@２５－３）　@this.購入申込期間（当社指定）.validate()をコールする。<BR>
     * <BR>
     * ２６）　@this.発行会社ロゴファ@イルURLのチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２６－１）　@サイズが80byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00484<BR>
     * 
     * <BR>
     * ２７）　@this.発行会社ウェブサイトURLのチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２７－１）　@サイズが80byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00485<BR>
     * 
     * <BR>
     * ２８）　@this.発行会社概要のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２８－１）　@サイズが400byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00486<BR>
     * 
     * <BR>
     * ２９）　@this.備考のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@２９－１）　@サイズが400byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00487<BR>
     * <BR>
     * ３０）　@this.成行可能のチェック <BR>
     * ３０－１）　@this.成行可能が未入力の場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00591<BR>
　@   * ３０－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00592<BR>
     *<BR>
     * ３１）　@this.未定決定区分のチェック <BR>
     * ３１－１）　@this.未定決定区分が未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00593<BR>
     * ３１－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00594<BR>
     * <BR>
     * ３２）　@this.目論見書閲覧区分のチェック<BR>
     * 　@３２－１）　@this.目論見書閲覧区分 != null の場合、以下のﾁｪｯｸを行う。<BR>
     * <BR>
     * 　@　@３２－１－１）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02694<BR>
     * <BR>
     * @@roseuid 40C4103B00BB
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validate()";
        
        log.entering(STR_METHOD_NAME);
        
        //IPO登録区分のチェック
        if(this.ipoRegistDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00434,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        else if(!WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoRegistDiv) && !WEB3IpoRegistDivDef.LISTED.equals(this.ipoRegistDiv))
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00436,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //this.IPO登録区分詳細のチェック
        if(this.ipoRegistDetailDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00435,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        else if(!WEB3IpoRegistDetailDivDef.RECRUIT.equals(this.ipoRegistDetailDiv) 
            && !WEB3IpoRegistDetailDivDef.SALES.equals(this.ipoRegistDetailDiv) 
            && !WEB3IpoRegistDetailDivDef.PRIVATE_RECRUIT.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_AND_SALES.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.SALES_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.PRIVATE_RECRUIT_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_AND_SALES_HANDING.equals(this.ipoRegistDetailDiv))
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00437,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }        
        
        //３）　@this.銘柄コードのチェック
        
        if(this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        
        if(l_intLength != 5)
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }  
              
        // ４）　@this.銘柄名のチェック
        int l_intNameLength = WEB3StringTypeUtility.getByteLength(this.productName);
        if(WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoRegistDiv))
        {
            if(this.productName == null || "".equals(this.productName))
            {
                throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00440,
                            this.getClass().getName() + STR_METHOD_NAME);
            }            
        }
        
        if(this.productName != null || !"".equals(this.productName)) //this.銘柄名入力がある場合
        {
            if(l_intNameLength > 50)
            {
                //４－２）　@サイズが50byteより大きい場合
                throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                            this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        //５）　@this.公開日のチェック
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.publicOfferingDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00442,
                                                this.getClass().getName() + STR_METHOD_NAME);
            }
            
            this.publicOfferingDate.validate();
        }
        
        //     * ６）　@this.市場コードのチェック<BR>

        if(this.publicOfferingMarketCode == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        // ７）　@this.仮条件区分のチェック<BR>

        if(this.temporaryConditionDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00444,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv) && !WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00445,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //     * ８）　@this.仮条件下限値のチェック<BR>
        if( !(this.temporaryConditionLower == null) )
        {        
            int l_intLowerLength = WEB3StringTypeUtility.getByteLength(this.temporaryConditionLower);
            if( !(WEB3StringTypeUtility.isNumber(this.temporaryConditionLower)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00446,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intLowerLength > 9)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00447,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.temporaryConditionLower)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00488,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.temporaryConditionLower) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.temporaryConditionLower) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00448,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_temporaryConditionLowerD = Double.parseDouble(this.temporaryConditionLower);
			if (l_temporaryConditionLowerD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00446,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }
        //９）　@this.仮条件上限値のチェック<BR>
        if( !(this.temporaryConditionUpper == null) )
        {
            int l_intUpperLength = WEB3StringTypeUtility.getByteLength(this.temporaryConditionUpper);
            
            if( !(WEB3StringTypeUtility.isNumber(this.temporaryConditionUpper)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00449,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intUpperLength > 9)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00450,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.temporaryConditionUpper)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00489,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.temporaryConditionUpper) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.temporaryConditionUpper) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00451,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_temporaryConditionUpperD = Double.parseDouble(this.temporaryConditionUpper);
			if (l_temporaryConditionUpperD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00449,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }

        //１０）　@this.仮条件提示日のチェック<BR>
        
        if(this.temporaryConditionPresentationDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00452,
                                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        //１１）　@this.公募数量のチェック<BR>
        if( !(this.issuedQuantity == null) )
        {
            int l_intQuantityLength = WEB3StringTypeUtility.getByteLength(this.issuedQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.issuedQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00453,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intQuantityLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00454,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }
        //１２）　@this.売出数量のチェック<BR>
        if( !(this.offeringQuantity == null) )
        {
            int l_intOfferingLength = WEB3StringTypeUtility.getByteLength(this.offeringQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.offeringQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00455,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intOfferingLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00456,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        //１３）　@this.当社取扱数量のチェック<BR>
        if( !(this.handlingQuantity == null) )
        {
            int l_intHandlingLength = WEB3StringTypeUtility.getByteLength(this.handlingQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.handlingQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00457,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intHandlingLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00458,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }          
        
        //１４）　@this.購入申込単位のチェック<BR>
        
        if(this.offerUnit != null)
        {
            int l_intOfferLength = WEB3StringTypeUtility.getByteLength(this.offerUnit);
            if( !(WEB3StringTypeUtility.isNumber(this.offerUnit)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00459,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            
            if(l_intOfferLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00460,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            double l_dblUnit = Double.parseDouble(this.offerUnit); 
			if (l_dblUnit == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00459,
								this.getClass().getName() + STR_METHOD_NAME);  
			}
            if(this.handlingQuantity != null)
            {
                double l_dblQuantity = Double.parseDouble(this.handlingQuantity);
                if(l_dblUnit > l_dblQuantity)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00461,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                
                else if( (l_dblQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00462,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }
            
            if(this.offeringQuantity != null)
            {
                double l_dblOfferingQuantity = Double.parseDouble(this.offeringQuantity);
                if( (l_dblOfferingQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00463,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }
            
            if(this.issuedQuantity != null)
            {
                double l_dblIssuedQuantity = Double.parseDouble(this.issuedQuantity);
                if( (l_dblIssuedQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00464,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }         
        }  
        
        //１５）　@this.刻みのチェック<BR>
        
        if( !(this.tickValue == null) )
        {
            int l_intTickValue = WEB3StringTypeUtility.getByteLength(this.tickValue);
            if( !(WEB3StringTypeUtility.isNumber(this.tickValue)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00465,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intTickValue > 6)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00466,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.tickValue)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00467,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.tickValue) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.tickValue) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00468,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_intTickD = Double.parseDouble(this.tickValue);
			if (l_intTickD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00465,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }

        //１６）　@this.表示用単位区分のチェック<BR>
        
        if(this.displayUnitDiv == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00469,
                                            this.getClass().getName() + STR_METHOD_NAME);                        
        }
        else if(!WEB3IpoUnitDivDef.STOCK_QUANTITY.equals(this.displayUnitDiv) && !WEB3IpoUnitDivDef.QUANTITY.equals(this.displayUnitDiv))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00470,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        //１７）　@this.主幹事証券会社名のチェック<BR>

        if(this.leadManagingUnderwriter == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00471,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }

        int l_intLeadLength = WEB3StringTypeUtility.getByteLength(this.leadManagingUnderwriter);
        if(l_intLeadLength > 80)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00472,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        //１８）　@this.ブックビルディング開始日時のチェック<BR>

        if(this.bookBuildingStartDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00473,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //１９）　@this.ブックビルディング終了日時のチェック<BR>

        if(this.bookBuildingEndDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00474,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //２０）　@this.公開価格決定日のチェック<BR>

        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.publicOfferingPriceDetermDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00475,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            this.publicOfferingPriceDetermDate.validate();
        }
        //２１）　@this.公開価格のチェック<BR>

        if(this.publicOfferingPrice != null)
        {
            int l_intPriceLength = WEB3StringTypeUtility.getByteLength(this.publicOfferingPrice);
            if( !(WEB3StringTypeUtility.isNumber(this.publicOfferingPrice)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00476,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intPriceLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00477,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        
        // ２２）　@this.公開価格ディスカウント率のチェック<BR>
         
        if( !(this.publicOfferingDiscountRate == null))
        {
            if( (WEB3StringTypeUtility.getIntegerDigits(this.publicOfferingDiscountRate) > 2) 
                || WEB3StringTypeUtility.getFractionDigits(this.publicOfferingDiscountRate) > 2)
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00478,
                                this.getClass().getName() + STR_METHOD_NAME);                     
            }
        }
        
        //２３）　@this.抽選日のチェック<BR>
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.lotDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00479,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            this.lotDate.validate();
        }
        // ２４）　@this.購入申込期間（目論見書）のチェック<BR>
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.prospectusOfferTerm.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00480,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            else if(this.prospectusOfferTerm.endDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00480,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            this.prospectusOfferTerm.validate();
        }
        //２５）　@this.購入申込期間（当社指定）のチェック<BR>

        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.appointOfferTerm.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00482,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            else if(this.appointOfferTerm.endDate ==null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00482,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            this.appointOfferTerm.validate();
        }        
        
        //２６）　@this.発行会社ロゴファ@イルURLのチェック<BR>

        if(this.issuerLogoFileURL != null)
        {
            int l_intLogoLength = WEB3StringTypeUtility.getByteLength(this.issuerLogoFileURL);
            if(l_intLogoLength > 80)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00484,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        // ２７）　@this.発行会社ウェブサイトURLのチェック<BR>

        int l_intWebLength = WEB3StringTypeUtility.getByteLength(this.issuerWebSiteURL);
        if(this.issuerWebSiteURL != null)
        {
            if(l_intWebLength > 80)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00485,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        //２８）　@this.発行会社概要のチェック<BR>

        int l_intOutlineLength = WEB3StringTypeUtility.getByteLength(this.issuerCorporateOutline);
        if( !(this.issuerCorporateOutline == null) )
        {
            if(l_intOutlineLength > 400)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00486,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        // ２９）　@this.備考のチェック<BR>

        int l_intNoteLength = WEB3StringTypeUtility.getByteLength(this.note);
        if( !(this.note == null) )
        {
            if(l_intNoteLength > 400)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        //３０）　@this.成行可能のチェック <BR>

        if(this.marketOrderFlag == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00591,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        else if(!WEB3EnableMarketOrderDef.ENABLE_MARKET_ORDER.equals(this.marketOrderFlag) 
                            && !WEB3EnableMarketOrderDef.DISABLE_MARKET_ORDER.equals(this.marketOrderFlag))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00592,
                                            this.getClass().getName() + STR_METHOD_NAME);                                
        }
        
        // ３１）　@this.未定決定区分のチェック <BR>

        if(this.undecideDecideDiv == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00593,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        else if(!WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv) 
                            && !WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED.equals(this.undecideDecideDiv))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00594,
                                            this.getClass().getName() + STR_METHOD_NAME);                                
        }
        
        // ３２）　@this.目論見書閲覧区分のチェック
        // 　@３２－１）　@this.目論見書閲覧区分 != null の場合、以下のﾁｪｯｸを行う。
        // 　@　@３２－１－１）　@不正なコード値の場合、例外をスローする。
        if (this.prospectusReadDiv != null)
        {
            if (!WEB3DocReadingDivDef.DEFAULT.equals(this.prospectusReadDiv)
                && !WEB3DocReadingDivDef.NO_READ.equals(this.prospectusReadDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02694,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "目論見書閲覧区分が不正なコード値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
