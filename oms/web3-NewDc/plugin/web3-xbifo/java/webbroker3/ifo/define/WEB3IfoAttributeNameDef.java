head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAttributeNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ThreadLocalSystemAttributesRegistry(設定キー)　@定数定義インタフェイス (WEB3IfoAttributeNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/25 有山 祥子(SRA) 新規作成
*/
package webbroker3.ifo.define;

/**
 * ThreadLocalSystemAttributesRegistry(設定キー)　@定数定義インタフェイス
 *                                                                     
 * @@author 有山 祥子　@
 * @@version 1.0
 */
public interface WEB3IfoAttributeNameDef
{
    /**
     * CURRENT_PRICE(返済一覧、個別返済一覧、建玉照会にて使用)
     */
    public final static String CURRENT_PRICE = "CURRENT_PRICE";

    /**
     * CURRENT_PRICE_INFO(残高照会にて使用)
     */
    public final static String CURRENT_PRICE_INFO = "CURRENT_PRICE_INFO";
    
    /**
     * ACCOUNT_ID(管理者照会にて使用)
     */
    public final static String ACCOUNT_ID = "ACCOUNT_ID";
    
    /**
     * REAL_TIMESTAMP(出来通知にて使用)
     */
    public final static String REAL_TIMESTAMP = "REAL_TIMESTAMP";
}
@
