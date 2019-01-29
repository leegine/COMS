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
filename	WEB3FeqErrorCancelTargetDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : エラー解除対象機@能区分 定数定義インタフェイス(WEB3FeqErrorCancelTargetDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * エラー解除対象機@能区分 定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqErrorCancelTargetDivDef
{
    /**
     * 1：　@注文受付
     */
    public static final String ORDER_ACCEPT = "1";
    
    /**
     * 2：　@約定
     */
    public static final String EXECUTED = "2";
}
@
