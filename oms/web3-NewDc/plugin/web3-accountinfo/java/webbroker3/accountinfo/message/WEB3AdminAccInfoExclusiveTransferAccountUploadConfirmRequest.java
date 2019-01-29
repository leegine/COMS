head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082136L;
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.class);


    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル<BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] uploadFile;

    /**
     * @@roseuid 418F386900CB
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@アップロードファ@イルのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 415BC9A70341
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@アップロードファ@イルのチェック<BR>
        //１－１）　@未入力の場合、例外をスローする。
        if(this.uploadFile == null || this.uploadFile.length == 0)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName(),
                " アップロードファ@イルがnullの場合エラー");
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
