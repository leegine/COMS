head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginIndividualCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引個別決済一覧リクエスト(WEB3MarginIndividualCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引個別決済一覧リクエスト）。<br>
 * <br>
 * 信用取引個別決済一覧リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginIndividualCloseMarginListRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginIndividualCloseMarginListRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_individualCloseMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    
    /**
     * (建株id)<BR>
     */
    public String[] id;
    
    /**
     * @@roseuid 414047490371
     */
    public WEB3MarginIndividualCloseMarginListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@１－１）this.ＩＤ＝nullであった場合「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00081<BR>
     * 　@１－２）this.ＩＤ.要素数＝０であった場合「IDの要素数が0」の<BR>
     * 例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00282<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084945402AD
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "●●● WEB3MarginIndividualCloseMarginListRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@ＩＤチェック<BR>
        log.debug("this.ＩＤ＝nullであった場合");
        // 　@１－１）this.ＩＤ＝nullであった場合「IDがnull」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00081<BR>
        if (this.id == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00081,
            this.getClass().getName() + "validate");
        }

        log.debug("this.ＩＤ.要素数＝０であった場合");        
        // 　@１－２）this.ＩＤ.要素数＝０であった場合「IDの要素数が0」の<BR>
        // 例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00282<BR>
        // @@throws WEB3BaseException
        // @@roseuid 4084945402AD
        int l_intIDLength = this.id.length;
        if (l_intIDLength == 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414047490385
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginIndividualCloseMarginListResponse(this);
    }
}
@
