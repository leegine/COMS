head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄ(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/09/11 車進　@ (中訊) 仕様変更管理No.123

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
 * (管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082141L;
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class);


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
     * (専用振込先口座削除フラグ)<BR>
     * 専用振込先口座削除フラグ<BR>
     * <BR>
     * true：　@削除<BR>
     * false：　@削除でない<BR>
     */
    public boolean exclusiveTransferAccountDeleteFlag;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (変更後情報)<BR>
     * 変更後情報<BR>
     */
    public WEB3AccInfoAccountInfo changedAccountInfo;
    
    /**
     * (自動採番フラグ)<BR>
     * 口座番号自動採番フラグ。<BR>
     * <BR>
     * true：　@口座番号の自動採番を行う。<BR>
     * false：　@口座番号の自動採番を行わない。 <BR>
     */
    public boolean codeAutoFlag;    

    /**
     * @@roseuid 418F3866035B
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse(this);
    }

    /**
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
     * ３）　@変更後情報，専用振込先口座削除フラグのチェック<BR>
     * 　@３－１）　@（専用振込先口座削除フラグ == false）の場合、<BR>
     * 変更後情報が未入力であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01162<BR>
     * 　@３－２）　@（専用振込先口座削除フラグ == true）の場合、<BR>
     * 変更後情報に入力があれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01163<BR>
     * <BR>
     * ４）　@変更後情報のチェック<BR>
     * 　@変更後情報が入力されている場合、変更後情報.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CC936000D
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
        
        // ２）　@顧客コードのチェック<BR>
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
        
        //３）　@変更後情報，専用振込先口座削除フラグのチェック<BR>
        //３－１）　@（専用振込先口座削除フラグ == false）の場合、変更後情報が未入力であれば例外をスローする。
        if(this.exclusiveTransferAccountDeleteFlag == false
            && this.changedAccountInfo == null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01162,
                this.getClass().getName(),
                " （専用振込先口座削除フラグ == false）の場合、変更後情報が未入力です");
        }
        
        //３－２）　@（専用振込先口座削除フラグ == true）の場合、変更後情報に入力があれば例外をスローする
        if(this.exclusiveTransferAccountDeleteFlag == true
            && this.changedAccountInfo != null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01163,
                this.getClass().getName(),
                " （専用振込先口座削除フラグ == true）の場合、変更後情報に入力です");
        }
        

        
        //４）　@変更後情報のチェック
        //変更後情報が入力されている場合、変更後情報.validate()をコールする。
        if(this.changedAccountInfo != null)
        {
            this.changedAccountInfo.validate();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
