head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxTransferMasterRemarkCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替条件マスタの摘要コード定数定義インタフェイス(WEB3FxTransferMasterRemarkCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/17 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * FX振替条件マスタの摘要コード定数定義インタフェイス<BR>
 * (FX振替条件マスタの摘要コードの參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3FxTransferMasterRemarkCodeDef
{
    /**
     * 71：株先証拠金（東証）
     */
    public static final String TOCK_FUTURES_MARGIN_TOKYO = "71";

    /**
     * 72：株先証拠金（大証）
     */
    public static final String TOCK_FUTURES_MARGIN_OSAKA = "72";

    /**
     * 99：その他
     */
    public static final String OTHER = "99";
}@
