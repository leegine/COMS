head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 失効区分定数定義インタフェイス(WWEB3FeqExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 失効区分定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqExpirationStatusDef
{
    /**
     * 0:失効なし
     */
    public final static String EXPIRATION_TYPE_NOT_PROMISE = "0";
    
    /**
     * 1:一部失効
     */
    public final static String EXPIRATION_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 2:全部失効
     */
    public final static String EXPIRATION_TYPE_ALL_COMPLETE = "2";
}
@
