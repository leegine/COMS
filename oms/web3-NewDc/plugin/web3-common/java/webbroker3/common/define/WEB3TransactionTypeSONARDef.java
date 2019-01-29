head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionTypeSONARDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引コード（ＳＯＮＡＲ）　@定数定義インタフェイス(WEB3TransactionTypeSONARDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引コード（ＳＯＮＡＲ）　@定数定義インタフェイス
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionTypeSONARDef
{

    /**
     * 11：普通株式
     */
    public static final String MARKET_TRADING = "11";
    
    /**
     * 16：立会外分売
     */
    public static final String SALES_OUTSIDE_MARKET = "16";

    /**
     * 51：信用建
     */
    public static final String OPEN_CONTRACT = "51";
    
    /**
     * 52：信用返済
     */
    public static final String SETTLE_CONTRACT = "52";
    
    /**
     * 53：現引現渡
     */
    public static final String SWAP_CONTRACT = "53";
    
    /**
     * 91：売付
     */
    public static final String SELL = "91";
}
@
