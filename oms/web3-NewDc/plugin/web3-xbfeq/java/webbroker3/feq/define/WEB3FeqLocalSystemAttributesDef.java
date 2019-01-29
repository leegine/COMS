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
filename	WEB3FeqLocalSystemAttributesDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定キーインタフェイス(WEB3FeqLocalSystemAttributesDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 孟東 (中訊) 新規作成
Revesion History : 2005/07/15 孟東 (中訊) 外国株式約定経路区分を追加
*/
package webbroker3.feq.define;

/**
 * 設定キーインタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqLocalSystemAttributesDef
{
    /**
     * ACCOUNT_ID：口座ID
     */
    public static final String ACCOUNT_ID = "ACCOUNT_ID";
 
    /**
     * FEQ_ORDER_EXEC_ROUTE_DIV：外国株式約定経路区分
     */
    public static final String FEQ_ORDER_EXEC_ROUTE_DIV 
        = "FEQ_ORDER_EXEC_ROUTE_DIV";
}
@
