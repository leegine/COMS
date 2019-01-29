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
filename	WEB3EquityReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ReferenceTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 li-yunfeng(sinocom)　@新規作成
*/

package webbroker3.equity.define;
/**
 * 照会区分　@定数定義インタフェイス
 *                                                                     
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3EquityReferenceTypeDef
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
