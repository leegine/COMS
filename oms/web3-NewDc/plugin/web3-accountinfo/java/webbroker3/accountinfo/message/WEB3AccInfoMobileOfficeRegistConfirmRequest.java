head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込確認リクエスト(WEB3AccinfoMobileOfficeRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/10/30 徐大方 (中訊) 仕様変更モデルNo.139
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報携帯番号・勤務先情報変更申込確認リクエスト)<BR>
 * お客様情報携帯番号・勤務先情報変更申込確認リクエスト<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistConfirmRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082154L;
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistConfirmRequest.class);


    /**
     * (変更後情報)<BR>
     * 変更後情報<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;
    
    /**
     * (変更前情報)<BR>
     * 変更前情報<BR>
     */
    public WEB3AccInfoMobileOfficeInfo preMobileOfficeInfo;

    /**
     * @@roseuid 418F39F6009C
     */
    public WEB3AccInfoMobileOfficeRegistConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMobileOfficeRegistConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * this.変更後情報.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4140279500F6
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        if(this.changedMobileOfficeInfo != null)
        {
            
            //this.変更後情報.validate()をコールする
            this.changedMobileOfficeInfo.validate();
        }
                
        log.exiting(STR_METHOD_NAME);

    }
}
@
