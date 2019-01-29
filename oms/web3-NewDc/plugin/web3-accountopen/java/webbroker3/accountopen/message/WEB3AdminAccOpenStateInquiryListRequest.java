head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ一覧リクエスト(WEB3AdminAccOpenStateInquiryListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 郭英 (中訊) 新規作成
Revesion History : 2009/08/13 武波 (中訊) 仕様変更モデルNo.163
Revesion History : 2010/02/10 武波 (中訊) 仕様変更モデルNo.216
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.accountopen.define.WEB3AccOpenDeleteFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenForeignerFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenInsiderDivDef;
import webbroker3.accountopen.define.WEB3AccOpenReceiveFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenTaxTypeDivDef;
import webbroker3.accountopen.define.WEB3AccountOpenKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者口座開設状況問合せ一覧リクエスト)<BR>
 * 管理者口座開設状況問合せ一覧リクエスト<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
 
public class WEB3AdminAccOpenStateInquiryListRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryListRequest.class);
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081605L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客コード（自）)<BR>
     * 顧客コード（自）<BR>
     */
    public String accountCodeFrom;

    /**
     * (顧客コード（至）)<BR>
     * 顧客コード（至）<BR>
     */
    public String accountCodeTo;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     */
    public String accountType;

    /**
     * (顧客姓（カナ）)<BR>
     * 顧客姓（カナ）<BR>
     */
    public String accountFamilyNameKana;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * (資料請求日（自）)<BR>
     * 資料請求日（自）<BR>
     */
    public Date infoClaimDateFrom;

    /**
     * (資料請求日（至）)<BR>
     * 資料請求日（至）<BR>
     */
    public Date infoClaimDateTo;

    /**
     * (SONAR送信日（自）)<BR>
     * SONAR送信日（自）<BR>
     */
    public Date sonarSendDateFrom;

    /**
     * (SONAR送信日（至）)<BR>
     * SONAR送信日（至）<BR>
     */
    public Date sonarSendDateTo;

    /**
     * (口座開設日（自）)<BR>
     * 口座開設日（自）<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (口座開設日（至）)<BR>
     * 口座開設日（至）<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (口座開設状況)<BR>
     * 口座開設状況<BR>
     * <BR>
     * 0：　@DEFAULT（未開設）<BR>
     * 1：　@開設中<BR>
     * 2：　@エラー発生<BR>
     * 3：　@開設済<BR>
     */
    public String accountOpenStateDiv;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (削除フラグ)<BR>
     * 削除フラグ<BR>
     * <BR>
     * 1：TRUE/無効（削除）<BR> 
     * 0：FALSE/有効（DEFAULT）<BR>
     */
    public String deleteFlag;

    /**
     * (印刷フラグ)<BR>
     * 印刷フラグ<BR>
     * <BR>
     * 0：印刷可<BR>
     * 1：印刷済<BR>
     * 3：未処理（DEFAULT）<BR>
     */
    public String printFlag;

    /**
     * (受領フラグ)<BR>
     * 受領フラグ<BR>
     * <BR>
     * 1：TRUE/受領済<BR>
     * 0：FALSE/未受領（DEFAULT）<BR>
     */
    public String receiveFlag;

    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般口座<BR>
     * 1：特定口座<BR>
     */
    public String taxTypeDiv;

    /**
     * (外国人フラグ)<BR>
     * 外国人フラグ<BR>
     * <BR>
     * 1：TRUE/日本以外<BR>
     * 0：FALSE/日本（DEFAULT）<BR>
     */
    public String foreignerFlag;

    /**
     * (内部者登録区分)<BR>
     * 内部者登録区分<BR>
     * <BR>
     * 0：登録なし<BR>
     * 1：登録あり<BR>
     */
    public String insiderDiv;

    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AccOpenSortKey[] sortKeys;

    /**
     * @@roseuid 41B45E7600DA
     */
    public WEB3AdminAccOpenStateInquiryListRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenStateInquiryListResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@資料請求日（自），資料請求日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@１－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01326<BR>
     * <BR>
     * ２）　@SONAR送信日（自），SONAR送信日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@２－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01327<BR>
     * <BR>
     * ３）　@口座開設日（自），口座開設日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@３－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01328<BR>
     * <BR>
     * ４）　@顧客コード（自），顧客コード（至）のチェック<BR>
     * 　@４－１）　@（自） > （至）であれば、例外をスローする。※（自），<BR>
     * （至）の両方に入力がある場合のみ<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00795<BR>
     * 　@４－２）　@顧客コード（自）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01330<BR>
     * 　@４－３）　@顧客コード（至）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01331<BR>
     * <BR>
     * ５）　@要求ページ番号チェック <BR>
     * 　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ６）　@ページ内表示行数チェック <BR>
     * 　@６－１）　@未入力の場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * 　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * 　@６－３）　@マイナス値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * ７）　@ソートキーのチェック <BR>
     * 　@７－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * 　@７－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * 　@７－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@７－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@７－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 <BR>
     * 例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@　@ 口座開設状況.部店コード <BR>
     * 　@　@　@　@ 口座開設状況.顧客コード <BR>
     * 　@　@　@　@ 口座開設状況.識別コード <BR>
     * 　@　@　@　@ 口座開設状況.資料請求日<BR>
     * 　@　@　@　@ 口座開設状況.口座開設日<BR>
     * <BR>
     * ８）　@削除フラグのチェック<BR>
     * 　@８－１）　@削除フラグ != null かつ 削除フラグが下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03173<BR>
     * 　@　@　@1：TRUE/無効（削除）<BR>
     * 　@　@　@0：FALSE/有効（DEFAULT）<BR>
     * <BR>
     * ９）　@印刷フラグのチェック<BR>
     * 　@９－１）　@印刷フラグ != null かつ 印刷フラグが下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03174<BR>
     * 　@　@　@0：印刷可<BR>
     * 　@　@　@1：印刷済<BR>
     * 　@　@　@3：未処理（DEFAULT）<BR>
     * <BR>
     * １０）　@受領フラグのチェック<BR>
     * 　@１０－１）　@受領フラグ != null かつ 受領フラグが下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03175<BR>
     * 　@　@　@1：TRUE/受領済<BR>
     * 　@　@　@0：FALSE/未受領（DEFAULT）<BR>
     * <BR>
     * １１）　@特定口座区分のチェック<BR>
     * 　@１１－１）　@特定口座区分 != null かつ 特定口座区分が下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02114<BR>
     * 　@　@　@0：一般口座<BR>
     * 　@　@　@1：特定口座<BR>
     * <BR>
     * １２）　@外国人フラグのチェック<BR>
     * 　@１２－１）　@外国人フラグ != null かつ 外国人フラグが下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03176<BR>
     * 　@　@　@1：TRUE/日本以外<BR>
     * 　@　@　@0：FALSE/日本（DEFAULT）<BR>
     * <BR>
     * １３）　@内部者登録区分のチェック<BR>
     * 　@１３－１）　@内部者登録区分 != null かつ 内部者登録区分が下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03186<BR>
     * 　@　@　@1：TRUE/登録あり<BR>
     * 　@　@　@0：FALSE/登録なし（DEFAULT）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C631D035E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@資料請求日（自），資料請求日（至）のチェック 
        //  ※（自），（至）の両方に入力がある場合のみ 
        if (this.infoClaimDateFrom != null && this.infoClaimDateTo != null) 
        {
            //１－１）　@（自） > （至）であれば、例外をスローする。
            int l_intFlag = WEB3DateUtility.compareToSecond(this.infoClaimDateFrom, this.infoClaimDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01326,
                    getClass().getName() + STR_METHOD_NAME,
                    " 資料請求日（自）は資料請求日（至）より大きいです。" + 
                    this.infoClaimDateFrom.toString() + ", " + 
                    this.infoClaimDateTo.toString()); 
            }
        }

        //２）　@SONAR送信日（自），SONAR送信日（至）のチェック 
        //  ※（自），（至）の両方に入力がある場合のみ 
        if (this.sonarSendDateFrom != null && this.sonarSendDateTo != null) 
        {
            //２－１）　@（自） > （至）であれば、例外をスローする。
            int l_intFlag = WEB3DateUtility.compareToSecond(this.sonarSendDateFrom, this.sonarSendDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01327,
                    getClass().getName() + STR_METHOD_NAME,
                    " SONAR送信日（自）はSONAR送信日（至）より大きいです。" + 
                    this.sonarSendDateFrom.toString() + ", " + 
                    this.sonarSendDateTo.toString()); 
            }
        }
         

        //３）　@口座開設日（自），口座開設日（至）のチェック 
        //  ※（自），（至）の両方に入力がある場合のみ 
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null) 
        {
            //３－１）　@（自） > （至）であれば、例外をスローする。
            int l_intFlag = WEB3DateUtility.compareToSecond(this.accountOpenDateFrom, this.accountOpenDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + STR_METHOD_NAME,
                    " 口座開設日（自）は口座開設日（至）より大きいです。" + 
                    this.accountOpenDateFrom.toString() + ", " + 
                    this.accountOpenDateTo); 
            }
        }
         
        //４）　@顧客コード（自），顧客コード（至）のチェック 
        //４－２）　@顧客コード（自）に半角数字以外の文字が含まれる場合、例外をスローする。
        if (this.accountCodeFrom != null && !WEB3StringTypeUtility.isNumber(this.accountCodeFrom))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01330,
                getClass().getName() + STR_METHOD_NAME,
                " 顧客コード（自）の値が数字以外の値です。" + 
                this.accountCodeFrom); 
        }
        
        //４－３）　@顧客コード（至）に半角数字以外の文字が含まれる場合、例外をスローする。
        if (this.accountCodeTo != null && !WEB3StringTypeUtility.isNumber(this.accountCodeTo))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                getClass().getName() + STR_METHOD_NAME,
                " 顧客コード（至）の値が数字以外の値です。" + 
                this.accountCodeTo); 
        }
        
        //４－１）　@（自） > （至）であれば、例外をスローする。※（自），（至）の両方に入力がある場合のみ 
        if (this.accountCodeFrom != null && this.accountCodeTo != null)
        {
            int l_intFrom = (int)Double.parseDouble(this.accountCodeFrom);
            int l_intTo = (int)Double.parseDouble(this.accountCodeTo);
            if (l_intFrom > l_intTo)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                    getClass().getName() + STR_METHOD_NAME,
                    " 顧客コード（自）は顧客コード（至）より大きいです。" + 
                    this.accountCodeFrom + ", " + this.accountCodeTo); 
            }
        }

        //５）　@要求ページ番号チェック 
        //５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 
        if (this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            this.pageIndex  = "1";
        }
        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号が数字以外の値である。" + 
                this.pageIndex); 
        }
        //５－３）　@マイナス値の場合、例外をスローする。
        if ((int)Double.parseDouble(this.pageIndex) < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号の値が0以下です。" + 
                this.pageIndex); 
        }

        //６）　@ページ内表示行数チェック 
        //６－１）　@未入力の場合、例外をスローする。
        if (this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数が０、または未指定の場合。" + 
                this.pageIndex); 
        }
        //６－２）　@数字以外の文字が含まれる場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数は数字以外の値です。" + 
                this.pageSize);
        }
        //６－３）　@マイナス値の場合、例外をスローする。         
        if ((int)Double.parseDouble(this.pageSize) < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数の値が0以下です。" + 
                this.pageSize);
        }
        
        //７）　@ソートキーのチェック 
        //７－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                " ソートキーがnullの値である。" + 
                this.sortKeys);
        }
        //７－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                " ソートキーの要素数が０である。" + 
                this.sortKeys.length);
        }
        //７－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            //７－３－１）　@ソートキー.validate()をコールする。
            l_accOpenSortKey.validate();
            //７－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            //口座開設状況.部店コード 
            //口座開設状況.顧客コード 
            //口座開設状況.識別コード 
            //口座開設状況.資料請求日 
            //口座開設状況.口座開設日
            if (!(WEB3AccountOpenKeyItemDef.BRANCH_CODE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.ACCOUNT_CODE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.INFO_CLAIM_DATE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.ACCOUNT_OPEN_DATE.equals(l_accOpenSortKey.keyItem))) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    " キー項目に銘柄名、売買区分、注文時間の項目名以外の値が存在しています。" + 
                    l_accOpenSortKey.keyItem);         
            }
        }

        //８）　@削除フラグのチェック
        //８－１）　@削除フラグ != null かつ 削除フラグが下記の項目以外の場合、 例外をスローする。
        //1：TRUE/無効（削除）
        //0：FALSE/有効（DEFAULT）
        if (this.deleteFlag != null
            && !(WEB3AccOpenDeleteFlagDef.DEFAULT.equals(this.deleteFlag)
            || WEB3AccOpenDeleteFlagDef.DELETE.equals(this.deleteFlag)))
        {
            log.debug("削除フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03173,
                getClass().getName() + STR_METHOD_NAME,
                "削除フラグが存在しないコード値です。");
        }

        //９）　@印刷フラグのチェック
        //９－１）　@印刷フラグ != null かつ 印刷フラグが下記の項目以外の場合、 例外をスローする。
        //0：印刷可
        //1：印刷済
        //3：未処理（DEFAULT）
        if (this.printFlag != null
            && !(WEB3PrintFlagDef.ENABLE_PRINT.equals(this.printFlag)
            || WEB3PrintFlagDef.PRINT_COMPLETE.equals(this.printFlag)
            || WEB3PrintFlagDef.DEFAULT.equals(this.printFlag)))
        {
            log.debug("印刷フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03174,
                getClass().getName() + STR_METHOD_NAME,
                "印刷フラグが存在しないコード値です。");
        }

        //１０）　@受領フラグのチェック
        //１０－１）　@受領フラグ != null かつ 受領フラグが下記の項目以外の場合、 例外をスローする。
        //1：TRUE/受領済
        //0：FALSE/未受領（DEFAULT）
        if (this.receiveFlag != null
            && !(WEB3AccOpenReceiveFlagDef.RECEIVED.equals(this.receiveFlag)
            || WEB3AccOpenReceiveFlagDef.DEFAULT.equals(this.receiveFlag)))
        {
            log.debug("受領フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03175,
                getClass().getName() + STR_METHOD_NAME,
                "受領フラグが存在しないコード値です。");
        }

        //１１）　@特定口座区分のチェック
        //１１－１）　@特定口座区分 != null かつ 特定口座区分が下記の項目以外の場合、 例外をスローする。
        //0：一般口座
        //1：特定口座
        if (this.taxTypeDiv != null
            && !(WEB3AccOpenTaxTypeDivDef.NORMAL.equals(this.taxTypeDiv)
            || WEB3AccOpenTaxTypeDivDef.SPECIAL.equals(this.taxTypeDiv)))
        {
            log.debug("特定口座区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02114,
                getClass().getName() + STR_METHOD_NAME,
                "特定口座区分が未定義の値です。");
        }

        //１２）　@外国人フラグのチェック
        //１２－１）　@外国人フラグ != null かつ 外国人フラグが下記の項目以外の場合、 例外をスローする。
        //1：TRUE/日本以外
        //0：FALSE/日本（DEFAULT）
        if (this.foreignerFlag != null
            && !(WEB3AccOpenForeignerFlagDef.DEFAULT.equals(this.foreignerFlag)
            || WEB3AccOpenForeignerFlagDef.FOREIGNER.equals(this.foreignerFlag)))
        {
            log.debug("外国人フラグ存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03176,
                getClass().getName() + STR_METHOD_NAME,
                "外国人フラグ存在しないコード値です。");
        }
        log.exiting(STR_METHOD_NAME);

        //１３）　@内部者登録区分のチェック
        //内部者登録区分 != null かつ 内部者登録区分が下記の項目以外の場合、 例外をスローする。
        if (this.insiderDiv != null
            && !(WEB3AccOpenInsiderDivDef.TRUE.equals(this.insiderDiv)
                || WEB3AccOpenInsiderDivDef.DEFAULT.equals(this.insiderDiv)))
        {
            log.debug("内部者登録区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03186,
                getClass().getName() + STR_METHOD_NAME,
                "内部者登録区分が存在しないコード値です。");
        }
    }

}
@
