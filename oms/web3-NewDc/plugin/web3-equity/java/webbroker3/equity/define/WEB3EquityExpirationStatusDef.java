head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 失効区分 定数定義インタフェイス(WEB3EquityExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 中尾寿彦(SRA) 新規作成
*/

package webbroker3.equity.define;

/**
 * 失効区分　@定数定義インタフェイス
 * 
 * @@author 中尾寿彦
 * @@version 1.0
 */
public interface WEB3EquityExpirationStatusDef
{
    /**
     * 失効区分:
     * 0:失効なし
     */
    public final static String EXPIRATION_TYPE_NOT_PROMISE = "0";
    
    /**
     * 失効区分:
     * 1:一部失効
     */
    public final static String EXPIRATION_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 失効区分:
     * 2:全部失効
     */
    public final static String EXPIRATION_TYPE_ALL_COMPLETE = "2";
}
@
