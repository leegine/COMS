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
filename	WEB3IfoReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3IfoReferenceTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  ZouRui(sinocom)　@新規作成
*/
package webbroker3.ifo.define;

/**
 * 照会区分
 *                                                                     
 * @@author Zou Rui
 * @@version 1.0
 */
public interface WEB3IfoReferenceTypeDef
{
    /**
     * 0：注文約定照会
     */
    public static final String ORDER_PROMISE = "0";

    /**
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）
     */
    public static final String CORRECTION_LIST = "1";

}@
