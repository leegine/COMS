head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DecisionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 判定結果(WEB3DecisionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 判定結果 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3DecisionDef
{

    /**
     * 0：DEFAULT（未判定）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：承認
     */
    public final static String APPROVAL = "1";

    /**
     * 2：不可
     */
    public final static String DISAPPROVAL = "2";

}@
