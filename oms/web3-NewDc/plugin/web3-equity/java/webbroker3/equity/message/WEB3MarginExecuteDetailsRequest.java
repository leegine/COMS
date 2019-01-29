head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定詳細リクエストクラス(WEB3MarginExecuteDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文約定詳細リクエスト）。<br>
 * <br>
 * 信用取引注文約定詳細リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginExecuteDetailsRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginExecuteDetailsRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_executeDetails";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101804L;       
    /**
     * (注文ID)<BR>
     */
    public String id;
    
    /**
     * @@roseuid 4140485A01E3
     */
    public WEB3MarginExecuteDetailsRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤ＝nullであれば「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084D7BA01D2<BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        if (id==null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }  
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140485A0201
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginExecuteDetailsResponse(this);
    }
}
@
