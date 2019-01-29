head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済建株明細(WEB3MarginCloseMarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引決済建株明細）。<br>
 * <br>
 * 信用取引決済建株明細クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractUnit extends Message 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCloseMarginContractUnit.class);
 
    /**
     * (ID)<BR>
     * 建株ＩＤ
     */
    public String id;
    
    /**
     * (注文株数)
     */
    public String orderQuantity;
    
    /**
     * (決済順位)
     */
    public String settlePriority;
    
    /**
     * @@roseuid 414032D00375
     */
    public WEB3MarginCloseMarginContractUnit() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@１－１）this.ＩＤ＝nullの場合、「IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ２）　@決済順位チェック<BR>
     * 　@２－１）this.決済順位≠nullかつ、this.決済順位≠数字の場合、<BR>
     * 　@　@　@　@　@「決済順位が数字以外」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00329<BR>
     * <BR>
     * 　@２－２）this.決済順位≠nullかつ、this.決済順位＜０の場合、<BR>
     * 　@　@　@　@　@「決済順位が0未満」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00246<BR>
     * <BR>
     * ３）　@株数チェック<BR>
     * 　@３－１）this.注文株数≠nullかつ、this.注文株数≠数字の場合、<BR>
     * 　@　@　@　@　@「注文株数が数字以外」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@３－２）this.注文株数≠nullかつ、this.注文株数＜０の場合、<BR>
     * 　@　@　@　@　@「注文株数が0未満」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * ４）　@決済順位・株数チェック <BR>
     * 　@４－１）this.決済順位≠nullかつ、this.株数＝nullの場合、 <BR>
     * 　@　@　@　@　@「決済順位指定時は株数指定必須」の例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084B4D502DB
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("信用取引決済建株明細のチェック：BEGIN");
        log.debug("ＩＤチェック:");
        // １）　@ＩＤチェック<BR>
        // 　@１－１）this.ＩＤ＝nullの場合、「IDがnull」の例外をスローする。<BR>
        if (this.id == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,
            this.getClass().getName() + "validate");
        }

        log.debug("決済順位チェック:");
        // ２）　@決済順位チェック<BR>
        // 　@２－１）this.決済順位≠nullかつ、this.決済順位≠数字の場合、<BR>
        // 　@　@　@　@　@「決済順位が数字以外」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00329<BR>
        if (this.settlePriority != null && !WEB3StringTypeUtility.isNumber(this.settlePriority))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00329,
            this.getClass().getName() + "validate");
        }

        // 　@２－２）this.決済順位≠nullかつ、this.決済順位＜０の場合、<BR>
        // 　@　@　@　@　@「決済順位が0未満」の例外をスローする。<BR>
        if (this.settlePriority != null && Long.parseLong(this.settlePriority) < 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00246,
            this.getClass().getName() + "validate");
        }

        log.debug("株数チェック:");
        // ３）　@株数チェック<BR>
        // 　@３－１）this.注文株数≠nullかつ、this.注文株数≠数字の場合、<BR>
        // 　@　@　@　@　@「注文株数が数字以外」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00901<BR>
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "validate");
        }

        // 　@３－２）this.注文株数≠nullかつ、this.注文株数＜０の場合、<BR>
        // 　@　@　@　@　@「注文株数が0未満」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00902<BR>
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) < 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "validate");
        }
        
        // ４）　@決済順位・株数チェック
        // 　@４－１）this.決済順位≠nullかつ、this.株数＝nullの場合、
        // 　@　@　@　@　@「決済順位指定時は株数指定必須」の例外をスローする。
        if (this.settlePriority != null && this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.debug("信用取引決済建株明細のチェック：END");
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
