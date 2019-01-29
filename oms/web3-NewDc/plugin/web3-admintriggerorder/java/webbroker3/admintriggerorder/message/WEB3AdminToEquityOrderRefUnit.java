head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会Unit (WEB3AdminToEquityOrderRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03　@魏新(中訊) 新規作成
                 : 2006/10/23  唐性峰(中訊)モデルNo.085
*/

package webbroker3.admintriggerorder.message;

/**
 * (トリガー注文管理者・株式注文照会Unit )<BR>
 * 
 * @@author 魏新<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefUnit extends WEB3AdminToOrderRefCommonUnit
{
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     * 5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (弁済区分)<BR>
     * 弁済区分<BR>
     * <BR>
     * 1：制度信用
     * 2：一般信用<BR>
     */
    public String repaymentDiv;

    /**
     * (値段条件)<BR>
     * 値段条件<BR>
     * <BR>
     * 0:指定なし<BR>
     * 1:現在値指値<BR>
     * 3:優先指値<BR>
     * 5:成行残数指値<BR>
     * 7:成行残数取消<BR>
     */
    public String priceCondType;

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C9007D
     */
    public WEB3AdminToEquityOrderRefUnit() 
    {
     
    }

}
@
