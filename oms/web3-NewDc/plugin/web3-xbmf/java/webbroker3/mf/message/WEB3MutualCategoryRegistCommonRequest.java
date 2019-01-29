head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCategoryRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録共通リクエスト(WEB3MutualCategoryRegistCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成 
Revesion History : 2008/04/29 武波(中訊)　@仕様変更モデル596
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3ProcessAddChangeDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信管理者カテゴリー登録共通リクエスト)<BR>
 * 投資信託管理者カテゴリー登録共通リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualCategoryRegistCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412011610L;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCategoryRegistCommonRequest.class);
    
    /**
     * (投信銘柄カテゴリーコード)<BR>
     * 投信銘柄カテゴリー名称に対応した投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * (投信銘柄カテゴリー名称)<BR>
     * 投信銘柄カテゴリーコードに対応した投信銘柄カテゴリー名称
     */
    public String categoryName;
    
    /**
     * (親カテゴリーコード)<BR>
     *  親カテゴリーコード<BR>
     *  指定しない場合… つまりルートにする場合は null を送る
     */
    public String parentCategoryCode;
    
    /**
     * (処理区分)<BR>
     * 処理区分（追加／変更／削除）<BR>
     * 0:追加　@1:変更　@2:削除<BR>
     */
    public String procedureDiv;
     
    /**
     * (validate)<BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）投信銘柄カテゴリーコードのチェック<BR>
     * 　@１－１）this.投信銘柄カテゴリーコード＝nullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:BUSINESS_ERROR_01243<BR>
     * 　@１－２）this.投信銘柄カテゴリーコードの値＞2Byteの場合、<BR>
     *           例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:BUSINESS_ERROR_01244<BR>
     * <BR>
     * ２）投信銘柄カテゴリー名称のチェック<BR>
     * 　@２－１）this.投信銘柄カテゴリー名称＝nullの場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:BUSINESS_ERROR_01245<BR>
     * 　@２－２）this.投信銘柄カテゴリー名称の値に、<BR>
     *           半角カナ文字が存在した場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01246<BR>
     * 　@２－３）this.投信銘柄カテゴリー名称の値＞100Byteの場合、<BR>
     *           例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01247<BR>
     * <BR>
     * ３）親カテゴリーコードのチェック<BR>
     * 　@３－１）this.親カテゴリーコード!=nullであり、<BR>
     *           かつ値＞2Byteの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01248<BR>
     * 　@３－２）this.親カテゴリーコード!=nullであり、かつ<BR>
     * 　@　@　@　@  this.親カテゴリーコード＝this.投信銘柄カテゴリーコードの場合、<BR>
     *           例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01281<BR>
     * <BR>
     * ４）処理区分のチェック<BR>
     * 　@４－１）this.処理区分＝nullの場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tagBUSINESS_ERROR_01249<BR>
     * 　@４－２）this.処理区分の値が以下のいずれかではない場合、<BR>
     *           例外をスローする。<BR>
     * 　@　@　@    ”追加”<BR>
     * 　@　@　@    ”変更”<BR>
     * 　@　@　@    ”削除”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01250
     * @@throws WEB3BaseException
     * @@roseuid 4153B64902D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）投信銘柄カテゴリーコードのチェック 
        //  １－１）this.投信銘柄カテゴリーコード＝nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.categoryCode))
        {
            log.debug("投信銘柄カテゴリーコードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01243,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリーコードが未指定です。");
        }
        
        //１－２）this.投信銘柄カテゴリーコードの値＞2Byteの場合、例外をスローする。
        if (this.categoryCode.getBytes().length > 2)
        {
            log.debug("投信銘柄カテゴリーコードの値が上限値を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01244,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリーコードの値が上限値を超えています。");
        }
        
        //２）投信銘柄カテゴリー名称のチェック 
        //  ２－１）this.投信銘柄カテゴリー名称＝nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.categoryName))
        {
            log.debug("投信銘柄カテゴリー名称が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリー名称が未指定です。");
        }
        
        //２－２）this.投信銘柄カテゴリー名称の値に、
        //  半角カナ文字が存在した場合、例外をスローする。   
        if (WEB3StringTypeUtility.has1byteKana(this.categoryName))
        {
            log.debug("投信銘柄カテゴリー名の値に半角カナ文字が含まれている。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01246,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリー名の値に半角カナ文字が含まれている。");
        }
        
        //２－３）this.投信銘柄カテゴリー名称の値＞100Byteの場合、例外をスローする。 
        if (this.categoryName.getBytes().length > 100)
        {
            log.debug("投信銘柄カテゴリー名称の値が上限値を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01247,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリー名称の値が上限値を超えています。");
        }
        
        //３）親カテゴリーコードのチェック 
        //  ３－１）this.親カテゴリーコード!=nullであり、
        //      かつ値＞2Byteの場合、例外をスローする。
        if (this.parentCategoryCode != null
            && this.parentCategoryCode.getBytes().length > 2)
        {
            log.debug("親カテゴリーコードの値が上限値を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親カテゴリーコードの値が上限値を超えています。");
        }
        
        //３－２）this.親カテゴリーコード!=nullであり、かつ 
        //  this.親カテゴリーコード＝this.投信銘柄カテゴリーコードの場合、例外をスローする。 
        if (this.parentCategoryCode != null
            && this.categoryCode.equals(this.parentCategoryCode))
        {
            log.debug("親カテゴリーコードと投信銘柄カテゴリーコードは同じです。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01281,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親カテゴリーコードと投信銘柄カテゴリーコードは同じです。");
        }
        
        //４）処理区分のチェック 
        //  ４－１）this.処理区分＝nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.procedureDiv))
        {
            log.debug("処理区分が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分が未指定です。");
        }
        
        //４－２）this.処理区分の値が以下のいずれかではない場合、例外をスローする。 
        //  ”追加” 
        //  ”変更”
        //  ”削除”
        if (!(WEB3ProcessAddChangeDivDef.ADD.equals(this.procedureDiv)
            || WEB3ProcessAddChangeDivDef.CHANGE.equals(this.procedureDiv)
            || WEB3ProcessAddChangeDivDef.DELETE.equals(this.procedureDiv)))
        {
            log.debug("処理区分の値は規定枚挙値の範囲ではないです。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分の値は規定枚挙値の範囲ではないです。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
