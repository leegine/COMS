head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GenRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 上り処理用リクエストの基底クラス(WEB3GenRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
Revesion History : 2006/10/27 栄イ (中訊) 仕様変更 モデル212を対応
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * 上り処理用リクエストの基底クラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.message.Request
 */
public abstract class WEB3GenRequest extends Request
{
    /**
     * コンプライアンス情報
     */
    public WEB3ComplianceInfoUnit complianceInfo = null;

    /**
     * コンストラクタ。
     */
    public WEB3GenRequest()
    {
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public abstract WEB3GenResponse createResponse();
}
@
