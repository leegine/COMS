head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiHashCalHowToDivValueDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ハッシュ計算方式区分値(WEB3SrvRegiHashCalHowToDivValueDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * ハッシュ計算方式区分値
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiHashCalHowToDivValueDef
{
    /**
     * MD2　@
     */
    public final static String MD2 = "MD2";
    
    /**
     * MD5　@
     */
    public final static String MD5 = "MD5";
    
    /**
     * SHA-1　@
     */
    public final static String SHA_1 = "SHA-1";
    
    /**
     * SHA-256　@
     */
    public final static String SHA_256 = "SHA-256";
    
    /**
     * SHA-384　@
     */
    public final static String SHA_384 = "SHA-384";
    
    /**
     * SHA-512　@
     */
    public final static String SHA_512 = "SHA-512";
}
@
