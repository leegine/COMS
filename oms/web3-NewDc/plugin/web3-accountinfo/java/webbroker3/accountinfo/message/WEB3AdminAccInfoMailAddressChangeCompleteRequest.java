head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更完了リクエスト(WEB3AdminAccInfoMailAddressChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                   2005/12/23 鄭徳懇 (中訊) 仕様変更No.072
Revesion History : 2007/08/28 武波 (中訊) 仕様変更・モデルNo.217
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報メールアドレス変更完了リクエスト)<BR>
 * 管理者お客様情報メールアドレス変更完了リクエスト<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082118L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (変更後メールアドレス)<BR>
     * 変更後メールアドレス<BR>
     */
    public String changedMailAddress;

    /**
     * (メールアドレス削除フラグ)<BR>
     * メールアドレス削除フラグ<BR>
     * <BR>
     * true：　@削除<BR>
     * false：　@削除でない<BR>
     */
    public boolean mailAddressDelFlag;
    
    /**
     * (口座開設完了メール送信フラグ)<BR>
     * 口座開設完了メール送信フラグ<BR>
     * <BR>
     * true：　@送信<BR>
     * false：　@送信しない<BR>
     */
    public boolean accountOpenMailFlag;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (案内メール送信フラグ)<BR>
     * 案内メール送信フラグ<BR>
     * <BR>
     * true：　@要<BR>
     * false：　@不要<BR>
     */
    public boolean guideMailFlag;

    /**
     * (複数アドレス情報)<BR>
     * 複数アドレス情報<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (メールアドレス情報)<BR>
     * メールアドレス情報<BR>
     */
    public WEB3AccInfoMailAddressInfoUnit[] mailAddressInfoList;

    /**
     * @@roseuid 418F385800FA
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressChangeCompleteResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@変更後メールアドレス，メールアドレス削除フラグのチェック<BR>
     * 　@３－１）　@（メールアドレス削除フラグ == true）の場合、<BR>
     * 変更後メールアドレスに入力があれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01154<BR>
     * <BR>
     * ４）　@メールアドレス削除フラグ，口座開設完了メール送信フラグのチェック<BR>
　@   *   ４－１）　@（口座開設完了メール送信フラグ == true） &&<BR>
　@　@ *     （メールアドレス削除フラグ == true）であれば、例外をスローする。<BR>
　@   *      ※ 削除と口座開設完了メールは同時に指定できない。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01280<BR>
     * ５）　@メールアドレス削除フラグ，案内ﾒｰﾙ送信フラグのチェック<BR> 
     * 　@ ５－１）　@（案内ﾒｰﾙ送信フラグ == true） &&<BR> 
     * 　@ （メールアドレス削除フラグ == true）であれば、例外をスローする。<BR> 
     * 　@ ※ 削除と案内ﾒｰﾙ送信（要）同時に指定できない。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02296<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147F8F0002A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("部店コード未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, getClass().getName() + STR_METHOD_NAME, "部店コード未入力");
        }
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            log.error("顧客コード未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, getClass().getName() + STR_METHOD_NAME, "顧客コード未入力");
        }
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            log.error("顧客コード桁数が6でない場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, getClass().getName() + STR_METHOD_NAME, "桁数が6でない場合");
        }
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.error("顧客コード数字以外の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, getClass().getName() + STR_METHOD_NAME, "顧客コード数字以外の文字");
        }
        if (this.mailAddressDelFlag && (this.changedMailAddress != null && (!"".equals(this.changedMailAddress))))
        {
            log.error("（メールアドレス削除フラグ == true）の場合、変更後メールアドレスが入力であれば例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01154, getClass().getName() + STR_METHOD_NAME, "(メールアドレス削除フラグ == true）の場合、変更後メールアドレスが入力");
        }
        if (this.accountOpenMailFlag && this.mailAddressDelFlag)
        {
            log.error("(口座開設完了メール送信フラグ == true） &&（メールアドレス削除フラグ == true）であれば、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01280, getClass().getName() + STR_METHOD_NAME, "(口座開設完了メール送信フラグ == true） &&（メールアドレス削除フラグ == true)");
        }        

        // ５）　@メールアドレス削除フラグ，案内ﾒｰﾙ送信フラグのチェック<BR> 
        // 　@ ５－１）　@（案内ﾒｰﾙ送信フラグ == true） &&<BR> 
        // 　@ （メールアドレス削除フラグ == true）であれば、例外をスローする。<BR> 
        // 　@ ※ 削除と案内ﾒｰﾙ送信（要）同時に指定できない。<BR>
        if (this.guideMailFlag && this.mailAddressDelFlag)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02296, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "削除と案内ﾒｰﾙ送信（要）同時に指定できない。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
