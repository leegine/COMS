head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLProductInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄検索情報クラス(WEB3SLProductInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760
Revision History : 2007/11/08 トウ鋒鋼 (中訊) モデル822
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (担保銘柄登録情報)<BR>
 * 担保銘柄登録情報<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3SLProductInfoUnit extends Message
{
    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public long productId;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債券<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * 7：累積投資<BR>
     */
    public String productType;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (適格区分)<BR>
     * 適格区分<BR>
     * <BR>
     * 0：不適格<BR>
     * 1：適格<BR>
     */
    public String qualifiedDiv;

    /**
     * (掛目)<BR>
     * 掛目<BR>
     */
    public String weight;

    /**
     * (適用期間From)<BR>
     * 適用期間From<BR>
     */
    public Date targetPeriodFrom;

    /**
     * (適用期間To)<BR>
     * 適用期間To<BR>
     */
    public Date targetPeriodTo;

    /**
     * (理由)<BR>
     * 理由<BR>
     */
    public String reason;

    /**
     * (担保銘柄登録情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46DBBB800130
     */
    public WEB3SLProductInfoUnit()
    {

    }
}
@
