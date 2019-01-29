head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文訂正_新規建完了リクエストクラス(WEB3MarginOpenMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文訂正_新規建完了リクエスト）。<br>
 * <br>
 * 信用取引注文訂正_新規建完了リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeCompleteRequest extends WEB3MarginCommonRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCancelCompleteRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;    
    
    /**
     * (注文ID)
     */
    public String id;
    
    /**
     * (確認時単価)<BR>
     * <BR>
     * 確認レスポンスで送信した値。
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * <BR>
     * 確認レスポンスで送信した値。
     */
    public Date checkDate;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * @@roseuid 41404539021D
     */
    public WEB3MarginOpenMarginChangeCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@this.ＩＤ＝nullであった場合、「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@注文株数チェック<BR>
     * 　@３－１）this.注文株数＝nullであれば、「注文株数が未指定」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866CE90250
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
        //
        super.validate();
        // ２）　@ＩＤチェック<BR>
        //     * 　@this.ＩＤ＝nullであった場合、「IDがnull」の例外をスローする。<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id==null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
		// ３）　@注文株数チェック<BR>
		//     * 　@３－１）this.注文株数＝nullであれば、「注文株数が未指定」の例外をスローする。<BR>
		//     *   class: WEB3BusinessLayerException<BR>
		//     *   tag:   BUSINESS_ERROR_00126<BR>
		if (this.orderQuantity == null)
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,STR_METHOD_NAME);
		}

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140453A0084
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginChangeCompleteResponse(this);
    }    
}
@
