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
filename	WEB3IfoExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 失効区分インタフェイス(WEB3IfoExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 凌建平(中訊)　@新規作成
*/
package webbroker3.ifo.define;

/**
 * 失効区分　@定数定義インタフェイス
 * 
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoExpirationStatusDef
{
    /**
     * 0 失効なし
     */
    public static final String DEFAULT = "0";

    /**
     * 1 一部失効
     */
    public static final String PARTIALLY_CLOSE = "1";

    /**
     * 2 全部失効
     */
    public static final String ALL_CLOSE = "2";
}
@
