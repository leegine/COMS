head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSendHowToDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 送信方法@区分(WEB3SrvRegiSendHowToDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 送信方法@区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiSendHowToDivDef
{
    /**
     * 0：GET　@
     */
    public final static String GET = "0";
    
    /**
     * 1：POST　@
     */
    public final static String POST = "1";
    
    /**
     * 2：HTTP-REQUEST　@
     */
    public final static String HTTP_REQUEST = "2";
    
    /**
     * 3：特殊（１）－リテラクレア証券 MULTEX 専用　@
     */
    public final static String SPECIAL1 = "3";
    
    /**
     * 4：特殊（２）－リテラクレア証券 日経テレコン21 専用　@
     */
    public final static String SPECIAL2 = "4";
}
@
