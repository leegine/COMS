head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HashCalHowToDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ハッシュ計算方式区分(WEB3HashCalHowToDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * ハッシュ計算方式区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3HashCalHowToDivDef
{
    /**
     * 0：指定無　@
     */
    public final static String DEFAULT = "0";
    
    /**
     * 1：MD2　@
     */
    public final static String MD2 = "1";
    
    /**
     * 2：MD5　@
     */
    public final static String MD5 = "2";
    
    /**
     * 3：SHA-1　@
     */
    public final static String SHA_1 = "3";
    
    /**
     * 4：SHA-256　@
     */
    public final static String SHA_256 = "4";
    
    /**
     * 5：SHA-384　@
     */
    public final static String SHA_384 = "5";
    
    /**
     * 6：SHA-512　@
     */
    public final static String SHA_512 = "6";
}
@
