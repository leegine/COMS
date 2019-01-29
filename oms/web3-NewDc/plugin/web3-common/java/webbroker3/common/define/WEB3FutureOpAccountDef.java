head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FutureOpAccountDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP口座開設区分(顧客マスター) 定数定義インタフェイス(WEB3FutureOpAccountDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2004/10/10 鄒 政(sinocom)　@JavaDocを修正
*/
package webbroker3.common.define;

/**
 * 先物OP口座開設区分(顧客マスター)　@定数定義インタフェイス <BR>
 *   先物OP口座開設区分（東証） <BR>
 *   先物OP口座開設区分（大証） <BR>
 *   先物OP口座開設区分（名証） <BR>
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3FutureOpAccountDef
{
    /**
     * 0 : DEFAULT（口座なし）
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : OP口座開設
     */
    public static final String OP_ACCOUNT_ESTABLISH = "1";

    /**
     * 2 : 先物口座開設
     */
    public static final String FUTURE_ACCOUNT_ESTABLISH = "2";

    /**
     * 3 : 先物OP口座開
     */
    public static final String FUTURE_OP_ACCOUNT_ESTABLISH = "3";

}
@
