head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式銘柄コード名称(WEB3EquityProductCodeNameUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 張坤芳 (中訊) 新規作成
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式銘柄コード名称）。<BR>
 * <BR>
 * 銘柄一覧用クラス
 * @@version 1.0
 */
public class WEB3EquityProductCodeNameUnit extends Message
{

    /**
     * (銘柄コード)<BR>
     * 銘柄名に対応した銘柄コード<BR>
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     * 銘柄コードに対応した銘柄名<BR>
     */
    public String productName;

    /**
     * (銘柄コード名称)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4073BCCD0389
     */
    public WEB3EquityProductCodeNameUnit()
    {

    }
}
@
