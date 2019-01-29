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
filename	WEB3MarginRepaymentUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引弁済クラス(WEB3MarginRepaymentUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引弁済）。<br>
 * <br>
 * 信用取引弁済クラス
 * @@version 1.0
 */
public class WEB3MarginRepaymentUnit extends Message 
{ 
   /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginRepaymentUnit.class);    

    
    /**
     * (弁済区分)<BR>
     * 1：制度信用　@2：一般信用<BR>
     */
    public String repaymentDiv;
    
    /**
     * (弁済期限値)<BR>
     * 月指定。<BR>
     * 無期限の場合”9999999”<BR>
     */
    public String repaymentTimeLimit;
    
    /**
     * (信用取引弁済)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.margin.message.WEB3MarginRepaymentUnit
     * @@roseuid 40C930D40333
     */
    public WEB3MarginRepaymentUnit() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@弁済区分チェック<BR>
     * 　@１－１）this.弁済区分＝nullであれば「弁済区分がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00628<BR>
     * <BR>
     * 　@１－２）this.弁済区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「弁済区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”1：制度信用”<BR>
     * 　@　@　@　@・”2：一般信用”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00629<BR>
     * <BR>
     * ２）　@弁済期限値チェック<BR>
     * 　@this.弁済期限値が以下のいずれかに該当する場合は、<BR>
     * 　@以下の例外をスローする。<BR>
     * <BR>
     * 　@ 　@ ・null<BR>
     * 　@　@　@　@　@「弁済期限値がnull」の例外をスロー。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00630<BR>
     * <BR>
     * 　@　@　@・数字以外<BR>
     * 　@　@　@　@　@「弁済期限値が数字以外」の例外をスロー。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00631<BR>
     * <BR>
     * 　@　@　@・this.弁済期限値 <= ０<BR>
     * 　@　@　@　@　@「弁済期限値が0以下」の例外をスロー。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00632<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 407E7FAA01A5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // １）　@弁済区分チェック<BR>
        //     * 　@１－１）this.弁済区分＝nullであれば「弁済区分がnull」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00628<BR>
        //
        if (repaymentDiv == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00628,STR_METHOD_NAME);
        }
        // 　@１－２）this.弁済区分が以下の値以外の場合、<BR>
        //    * 　@　@　@　@　@「弁済区分が未定義の値」の例外をスローする。<BR>
        //    * 　@　@　@　@・”1：制度信用”<BR>
        //    * 　@　@　@　@・”2：一般信用”<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00629<BR>
        //
        if ( !WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(repaymentDiv) && !WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(repaymentDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00629,STR_METHOD_NAME);
        }
        // ２）　@弁済期限値チェック<BR>
        //    * 　@this.弁済期限値が以下のいずれかに該当する場合は、<BR>
        //    * 　@以下の例外をスローする。<BR>
        //    * <BR>
        //    * 　@ 　@ ・null<BR>
        //    * 　@　@　@　@　@「弁済期限値がnull」の例外をスロー。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00630<BR>
        //
        if (repaymentTimeLimit == null )
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00630,STR_METHOD_NAME);
        }
        // 　@　@　@・数字以外<BR>
        //    * 　@　@　@　@　@「弁済期限値が数字以外」の例外をスロー。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00631<BR>
        //
        if (!WEB3StringTypeUtility.isNumber(repaymentTimeLimit))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00631,STR_METHOD_NAME);
        } 
        // 　@　@　@・this.弁済期限値 <= ０<BR>
        //    * 　@　@　@　@　@「弁済期限値が0以下」の例外をスロー。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00632<BR>
        //
        if (Long.parseLong(repaymentTimeLimit) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00632,STR_METHOD_NAME);
        }       
        log.exiting(STR_METHOD_NAME);
    }
}
@
