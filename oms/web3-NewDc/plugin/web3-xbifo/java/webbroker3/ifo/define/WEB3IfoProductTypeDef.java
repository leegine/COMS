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
filename	WEB3IfoProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物オプション商品区分インタフェイス(WEB3IfoProductTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.ifo.define;

/**
 * 先物オプション商品区分
 *                                                                     
 * @@author li yingyuan
 * @@version 1.0
 */
public interface WEB3IfoProductTypeDef
{
    /**
     * P：プットオプション
     */
    public static final String PUT_OPTIONS = "P";

    /**
     * C：コールオプション
     */
    public static final String CALL_OPTIONS = "C";
    
    /**
     * F：先物
     */
    public static final String FUTURES = "F";

}
@
