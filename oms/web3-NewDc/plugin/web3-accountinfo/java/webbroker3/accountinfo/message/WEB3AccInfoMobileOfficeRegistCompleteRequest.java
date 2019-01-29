head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込完了リクエスト(WEB3AccinfoMobileOfficeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報携帯番号・勤務先情報変更申込完了リクエスト)<BR>
 * お客様情報携帯番号・勤務先情報変更申込完了リクエスト<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082164L;
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistCompleteRequest.class);


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
     * @@roseuid 418F39F502DE
     */
    public WEB3AccInfoMobileOfficeRegistCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMobileOfficeRegistCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@変更後情報のチェック<BR>
     * 　@this.変更後情報.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 414027BC020F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        if(this.changedMobileOfficeInfo != null)
        {
            
            //１）　@変更後情報のチェック<BR>
            //this.変更後情報.validate()をコールする
            this.changedMobileOfficeInfo.validate();            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
