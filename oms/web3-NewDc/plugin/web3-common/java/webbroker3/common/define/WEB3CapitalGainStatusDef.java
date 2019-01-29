head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CapitalGainStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 譲渡益有効状態 定数定義インタフェイス(WEB3CapitalGainStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/15 栄イ(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 譲渡益有効状態 定数定義インタフェイス
 *
 * @@author 栄イ
 * @@version 1.0
 */
public interface WEB3CapitalGainStatusDef {

	/**
     * 0:無効
     */
    public final static String INVALIDITY = "0";

    /**
     * 1:有効
     */
    public final static String VALIDITY = "1";
}
@
