head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorDeleteCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄削除サービス (WEB3AdminOffFloorDeleteCommonRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄削除共通リクエスト)<BR>
 * <BR>
 * 管理者立会外分売銘柄削除サービス（確認／完了）リクエストデータのスーパークラス。
 * <BR>
 * <BR>
 * -----<English>-------------<BR>
 * <BR>
 * WEB3AdminOffFloorDeleteCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminOffFloorDeleteService(validate/submit) request data<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public abstract class WEB3AdminOffFloorDeleteCommonRequest extends WEB3GenRequest
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorDeleteCommonRequest.class);

    /**
     * (立会外分売銘柄キー)<BR>
     * <BR>
     * 立会外分売銘柄キー。<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductKey productKey;
    /**
     * @@roseuid 421AE3BF0226
     */
    public WEB3AdminOffFloorDeleteCommonRequest()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@立会外分売銘柄キーチェック <BR>
     * <BR>
     * 　@１－１）　@立会外分売銘柄キー.validate( )をコールする。<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)off floor productKey check<BR>
     * <BR>
     * 　@1-1)Call off floor productKey.validate( )<BR>
     * <BR>
     * @@roseuid 41B7D5FD0185
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 Call productKey.validate()
        productKey.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /** (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
