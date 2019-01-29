head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾘｸｴｽﾄ(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/12/14 周捷 (中訊) モデルNo.153
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾘｸｴｽﾄ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccount";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082112L;
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (開始日)<BR>
     * 開始日（自）<BR>
     */
    public Date startDate;

    /**
     * (終了日)<BR>
     * 終了日（自）<BR>
     */
    public Date endDate;

    /**
     * (申込状況区分)<BR>
     * 申込状況区分<BR>
     * <BR>
     * ※　@指定なしの場合はnull<BR>
     * <BR>
     * 0：　@判定待ち<BR>
     * 1：　@判定待ち（確認中）<BR>
     * 2：　@判定済み<BR>
     */
    public String searchApplyStateDiv;

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
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：個人口座<BR>
     * 1：法@人口座<BR>
     */
    public String accountType;

    /**
     * (ソートキー)<BR>
     * お客様情報ソートキー[] <BR>
     * 対象項目：申込日(applyDate)、部店コード、顧客コード、判定日時(judgementDate)<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    /**
     * @@roseuid 418F385A0138
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegistAccountResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@※入力がある場合のみ以下のチェックを行う。<BR>
     * 　@２－１）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@検索条件項目のチェック<BR>
     * 　@３－１）　@顧客コード，開始日，終了日のすべてが未入力の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01159<BR>
     * 　@３－２）　@申込状況区分に入力があり、<BR>
     * 不正なコード値の場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * 　@３－３）　@開始日，終了日のどちらか一方のみ入力されていた場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01160<BR>
     * 　@３－４）　@開始日，終了日に両方入力があり、<BR>
     * （開始日 > 終了日）であれば、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01151<BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * ６）　@ソートキーのチェック  <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BBUSINESS_ERROR_00231<BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR> 
     * 　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。<BR>
     * 　@　@  ・申込日時<BR>
     * 　@　@  ・部店コード<BR>
     * 　@　@  ・顧客コード<BR>
     * 　@　@  ・判定日時<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ７）　@口座区分のﾁｪｯｸ <BR>
     * 　@口座区分 != null の場合、以下のﾁｪｯｸを行う。 <BR>
     * 　@７－１）　@不正なコード値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01303<BR>
     * @@throws WEB3BaseException
     * @@roseuid 414974A201A2
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        //１）　@部店コードのチェック<BR>
        //１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || "".equals(this.branchCode))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName(),
                " 部店コード==nullである");
        }
        
        //２）　@顧客コードのチェック<BR>
        //※入力がある場合のみ以下のチェックを行う。<BR>
        //２－１）　@桁数が6でない場合、例外をスローする。
        if (this.accountCode != null && !"".equals(this.accountCode))
        {
            
            if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName(),
                    " 桁数が6でない場合");
            }
            //２－２）　@数字以外の文字が含まれる場合、例外をスローする。
            else if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName(),
                    " 顧客コードに数字以外の文字があるの場合エラーである");
            }
        }
        
        //３）　@検索条件項目のチェック<BR>
        //３－１）　@顧客コード，開始日，終了日のすべてが未入力の場合、例外をスローする。
        if ((this.accountCode == null || "".equals(this.accountCode))
            && this.startDate == null 
            && this.endDate == null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01159,
                this.getClass().getName(),
                " 顧客コード，開始日，終了日のすべてが未入力です");
        }
        
        //３－２）　@申込状況区分に入力があり、不正なコード値の場合は例外をスローする。
        if(this.searchApplyStateDiv != null && !"".equals(this.searchApplyStateDiv))
        {
            
            if(!WEB3RegistStateDivDef.WAIT_DECISION.equals(this.searchApplyStateDiv)
                && !WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(this.searchApplyStateDiv)
                && !WEB3RegistStateDivDef.DECISION_COMPLETE.equals(this.searchApplyStateDiv))
            {
             
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                    this.getClass().getName(),
                    " 判定結果区分のコード値が不正です");
            }
        }

        //３－３）　@開始日，終了日のどちらか一方のみ入力されていた場合、例外をスローする。
        if (this.startDate == null && this.endDate != null
            || this.endDate == null && this.startDate != null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01160,
                this.getClass().getName(),
                " 開始日，終了日のどちらか一方のみ入力です");
        }

        //３－４）　@開始日，終了日に両方入力があり （開始日 > 終了日）であれば、例外をスローする。
        if (this.startDate != null 
            && this.endDate != null)
        {
            if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01151,
                    this.getClass().getName(),
                    " 開始日，終了日に両方入力があり、（開始日 > 終了日）です");
            }
        }

        //４）　@要求ページ番号チェック <BR>
        //４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //４－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName(),
                " 要求ページ番号が数字以外の値である");
        }

        //４－３）　@マイナス値の場合、例外をスローする。
        if (Double.parseDouble(this.pageIndex) <= 0)
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName(),
                " 要求ページ番号の値が0以下です");
        }

        //５）　@ページ内表示行数チェック <BR>
        //５－１）　@未入力の場合、例外をスローする。
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName(),
                " ページ内表示行数が０、または未指定の場合");
        }

        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName(),
                " ページ内表示行数が数字以外の値です");
        }

        //５－３）　@マイナス値の場合、例外をスローする。
        if (Double.parseDouble(this.pageSize) <= 0)
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName(),
                " ページ内表示行数の値が0以下です");
        }
        
        //６）　@ソートキーのチェック  
		//　@６－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231 ,
                    this.getClass().getName(),
                    " ソートキーが未入力");
        }
		//　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName(),
                    " ソートキーの要素数 == 0");
        }
		//　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        int l_len = this.sortKeys.length;
        for (int i = 0; i < l_len; i++)
        {
    		//　@　@　@６－３－１）　@ソートキー.validate()をコールする。
        	this.sortKeys[i].validate();
    		//　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
    		//　@　@　@　@　@　@　@・申込日時
    		//　@　@　@　@　@　@　@・部店コード
    		//　@　@　@　@　@　@　@・顧客コード
    		//　@　@　@　@　@　@　@・判定日時
        	String l_strKeyItem = this.sortKeys[i].keyItem;
        	if (!WEB3AccInfoKeyItemDef.APPLY_DATE.equals(l_strKeyItem)
        			&& !WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_strKeyItem)
					&& !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem)
					&& !WEB3AccInfoKeyItemDef.JUDGEMENT_DATE.equals(l_strKeyItem))
        	{
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName(),
                        " ソートキー.キー項目が下記の項目名以外の場合");
        	}
        	
        }

        //７）　@口座区分のﾁｪｯｸ
        //口座区分 != null の場合、以下のﾁｪｯｸを行う。
        //７－１）　@不正なコード値の場合、例外をスローする。
        if (this.accountType != null)
        {
            if (!WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(this.accountType)
               && !WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(this.accountType))
            {
                log.debug("[口座区分] = " + accountType);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "口座区分の不正なコード値の場合");
            }
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
