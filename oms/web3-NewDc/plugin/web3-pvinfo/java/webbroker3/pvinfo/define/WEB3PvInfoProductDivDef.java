head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品区分定義(WEB3PvInfoProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王亞洲(中訊) 新規作成
                   2005/10/07 譚漢江(中訊) 外国株追加 
*/
package webbroker3.pvinfo.define;

/**
 * 商品区分
 *
 * @@author 王亞洲
 * @@version 1.00
 */
public interface WEB3PvInfoProductDivDef
{
    /**
     * 0：   現物
     */
    public final static String PD_SPOT = "0";

    /**
     * 1：   信用
     */
    public final static String PD_CREDIT = "1";
    /**
     * 2：   先物
     */
    public final static String PD_FUTURE = "2";

    /**
     * 3：   オプション
     */
    public final static String PD_OPTION = "3";
    
    /**
     * 4：   外国株
     */
    public final static String PD_FOREIGN_EQUITY = "4";
    
}

@
