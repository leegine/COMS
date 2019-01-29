head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccountTaxTypeKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客税区分Key定数定義(WEB3TPAccountTaxTypeIndexDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/03 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 顧客税区分Key定数定義
 */
public interface WEB3TPAccountTaxTypeKeyDef 
{
    /**
     * 税区分
     */
    public static String TAX_TYPE = "tax_type";

    /**
     * 税区分（次年）
     */
    public static String TAX_TYPE_NEXT = "tax_type_next";

    /**
     * 信用取引税区分
     */
    public static String MARGIN_TAX_TYPE = "margin_tax_type";

    /**
     * 信用取引税区分（次年）
     */
	public static String MARGIN_TAX_TYPE_NEXT = "margin_tax_type_next";
	
}
@
