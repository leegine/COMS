head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositUnderlyingProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 原資産銘柄コード定義クラス(WEB3IfoDepositUnderlyingProductCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/22 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.ifodeposit.define;

/**
 * 原資産銘柄コード定義クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositUnderlyingProductCodeDef
{
    /**
     * 日経225
     */
    public static final String NIKKEI_225 = "0018";
    
    /**
     * 日経300
     */
    public static final String NIKKEI_300 = "0016";
    
    /**
     * TOPIX
     */
    public static final String TOPIX = "0005";    

    /**
     * ミニ日経225
     */
    public static final String MINI_NIKKEI_225 = "0019";
    
}
@
