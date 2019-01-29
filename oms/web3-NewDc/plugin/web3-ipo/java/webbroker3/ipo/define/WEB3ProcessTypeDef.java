head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3ProcessTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO処理内容区分(WEB3ProcessTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 鄭海良(sinocom) 新規作成
*/
package webbroker3.ipo.define;

/**
 * IPO処理内容区分
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3ProcessTypeDef
{

    /**
     * 1：   申告
     */
    public final static String ORDER = "1";

    /**
     * 2：   訂正
     */
    public final static String MODIFY = "2";

    /**
     * 3：   取消
     */
    public final static String CANCEL = "3";
    
}
 @
