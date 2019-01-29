head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOpenAtOrderDLDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 寄付注文DLファ@イル用 定数定義インタフェイス(WEB3FeqOpenAtOrderDLDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/25 孟東(中訊) 新規作成
*/

package webbroker3.feq.define;
/**
 * 寄付注文DLファ@イル用 定数定義インタフェイス
 *                                                                     
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqOpenAtOrderDLDef
{
    /**
     * B：買
     */
    public final static String BUY = "B";
    
    /**
     * S：売
     */
    public final static String SELL = "S";

    /**
     * L：約定方法@
     */
    public final static String EXECUTE_METHOD = "L";
}
@
