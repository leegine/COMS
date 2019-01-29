head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  照会区分定数定義インタフェイス(WEB3RuitoReferenceTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 zhou-yong(sinocom)　@新規作成
*/

package webbroker3.xbruito.define;
/**
 * 照会区分　@定数定義インタフェイス
 *                                                                     
 * @@author zhou-yong
 * @@version 1.0
 */
public interface WEB3RuitoReferenceTypeDef
{
    /**
     * 照会区分:
     * 0：注文約定照会
     */
    public final static String REFERENCE_TYPE_VIEW = "0";
    
    /**
     * 照会区分:
     * 1：訂正取消一覧
     */
    public final static String REFERENCE_TYPE_UPDATE = "1";
}
@
