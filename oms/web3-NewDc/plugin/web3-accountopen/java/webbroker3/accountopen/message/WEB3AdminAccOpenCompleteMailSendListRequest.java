head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設完了メール送信一覧リクエスト(WEB3AdminAccOpenCompleteMailSendListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 張学剛 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者口座開設完了メール送信一覧リクエスト)<BR>
 * 管理者口座開設完了メール送信一覧リクエスト<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendListRequest extends WEB3GenRequest
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSendList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081609L;

    /**
     * (表示行数)<BR>
     * 表示行数<BR>
     */
    public String dispSize;

    /**
     * @@roseuid 41B45E7502EE
     */
    public WEB3AdminAccOpenCompleteMailSendListRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenCompleteMailSendListResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@表示行数チェック <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01323<BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01324<BR>
     * 　@２－３）　@マイナス値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01325<BR>
     * @@throws WEB3BaseException
     * @@roseuid 418EF71903A8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）表示行数チェック
        //２－１）　@未入力の場合、例外をスローする。
        if (dispSize == null || "".equals(dispSize.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01323,
                getClass().getName() + STR_METHOD_NAME,
                "表示行数未入力の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //２－２）数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(dispSize))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01324,
                getClass().getName() + STR_METHOD_NAME,
                "表示行数数字以外の文字が含まれる場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //２－３)マイナス値の場合、例外をスローする。
        double l_dblDispSize = Double.valueOf(dispSize).doubleValue();       
        if (l_dblDispSize < 0)
        {            
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01325,
                getClass().getName() + STR_METHOD_NAME,
                "マイナス値の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
