head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更完了リクエスト(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報携帯番号・勤務先情報変更完了リクエスト)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更完了リクエスト<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082111L;
    /**
     * ログ出力ユーティリティ。<BR>
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class);


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
     * (判定結果区分)<BR>
     * 判定結果区分<BR>
     * <BR>
     * 1：　@承認<BR>
     * 2：　@不可<BR>
     */
    public String judgmentResultDiv;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (変更後情報)<BR>
     * 変更後情報<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;

    /**
     * @@roseuid 418F385901D4
     */
    public WEB3AdminAccInfoMobileOfficeRegistCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegistCompleteResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性をチェックする。<BR>
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
     * ３）　@判定結果区分のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01157<BR>
     * 　@３－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * <BR>
     * ４）　@変更後情報のチェック<BR>
     * 　@this.変更後情報.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 414178AF01AA
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック<BR>
        //１－１）　@未入力の場合、例外をスローする。
        if(this.branchCode == null || "".equals(this.branchCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName(),
                " 部店コード==nullである");
        }
        
        //２）　@顧客コードのチェック<BR>
        //２－１）　@未入力の場合、例外をスローする。
        if(this.accountCode == null || "".equals(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName(),
                " 顧客コードがnullである");
        }
        
        //２－２）　@桁数が6でない場合、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName(),
                " 桁数が6でない場合");
        }
        
        //２－３）　@数字以外の文字が含まれる場合、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName(),
                " 顧客コードに数字以外の文字があるの場合エラーである");
        }
        
        //３）　@判定結果区分のチェック<BR>
        //３－１）　@未入力の場合、例外をスローする。
        if(this.judgmentResultDiv == null || "".equals(this.judgmentResultDiv))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01157,
                this.getClass().getName(),
                " 判定結果区分を入力しません");
            
        }
        
        //３－２）　@不正なコード値の場合、例外をスローする。
        if(!WEB3JudgmentResultDivDef.CONSENT.equals(this.judgmentResultDiv)
            && !WEB3JudgmentResultDivDef.IMPOSSIBILITY.equals(this.judgmentResultDiv))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                this.getClass().getName(),
                " 判定結果区分のコード値が不正です");
        }        
        
        // ４）　@変更後情報のチェック<BR>
        //this.変更後情報.validate()をコールする。
        this.changedMobileOfficeInfo.validate();
        

        log.exiting(STR_METHOD_NAME);

    }
}
@
