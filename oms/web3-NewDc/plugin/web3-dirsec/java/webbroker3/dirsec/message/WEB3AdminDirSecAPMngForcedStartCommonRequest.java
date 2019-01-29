head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・下り処理強制起動共通リクエスト(WEB3AdminDirSecAPMngForcedStartCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
Revision History : 2008/07/24 劉剣(中訊) モデル 135
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・下り処理強制起動共通リクエスト)<BR>
 * 管理者・下り処理強制起動共通リクエストクラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCommonRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartCommonRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211705L;

    /**
     * PTYPE。<BR>
     */
    public String pType;

    /**
     * @@roseuid 488437FE0121
     */
    public WEB3AdminDirSecAPMngForcedStartCommonRequest() 
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）PTYPEチェック<BR>
     * 　@　@this.PTYPE == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03105<BR>
     * @@throws WEB3BaseException
     * @@roseuid 487D58320078
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）PTYPEチェック
        //this.PTYPE == nullの場合、例外をスローする。
        if (this.pType == null)
        {
            log.debug("PTYPEが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03105,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTYPEが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
     public WEB3GenResponse createResponse()
     {
         return null;
     }
}
@
