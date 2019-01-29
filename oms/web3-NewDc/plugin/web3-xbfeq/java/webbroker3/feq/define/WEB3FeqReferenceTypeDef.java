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
filename	WEB3FeqReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 照会区分 定数定義インタフェイス(WEB3FeqReferenceTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東(中訊) 新規作成
*/

package webbroker3.feq.define;
/**
 * 照会区分　@定数定義インタフェイス
 *                                                                     
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqReferenceTypeDef
{
    /**
     * 0：注文約定照会
     */
    public final static String REFERENCE_TYPE_VIEW = "0";
    
    /**
     * 1：訂正取消一覧
     */
    public final static String REFERENCE_TYPE_UPDATE = "1";
}
@
