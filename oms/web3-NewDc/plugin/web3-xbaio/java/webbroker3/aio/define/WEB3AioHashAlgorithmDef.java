head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioHashAlgorithmDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioHashAlgorithmDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/28 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * FX電文処理サービスImpl のハッシュ値の生成　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioHashAlgorithmDef
{
    /**
     * MD2: The MD2 message digest algorithm as defined in RFC 1319.
     */
    public static final String MD2 = "MD2";

    /**
     * MD5: The MD5 message digest algorithm as defined in RFC 1321.
     */
    public static final String MD5 = "MD5";

    /**
     * SHA-1: The Secure Hash Algorithm, as defined in Secure Hash Standard, NIST FIPS 180-1. 
     */
    public static final String SHA_1 = "SHA-1";
}
@
