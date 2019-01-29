head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTranRefPerIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 指数別証拠金推移明細クラス(WEB3IfoDepositTranRefPerIndexUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/03 有山　@祥子(SRA) 新規作成
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (指数別証拠金推移明細)<BR>
 * 指数別証拠金推移明細クラス。<BR>
 * 
 * @@author Shoko Ariyama (SRA)
 */
public class WEB3IfoDepositTranRefPerIndexUnit extends Message
{

    /**
     * (原資産銘柄コード)<BR>
     * 原資産銘柄コード。<BR>
     */
    public String targetProductCode;

    /**
     * (先物買建またはOPプット売建可能数量)<BR>
     * 先物買建またはOPプット売建可能数量。<BR>
     */
    public String bullQuantity;

    /**
     * (先物売建またはOPコール売建可能数量)<BR>
     * 先物売建またはOPコール売建可能数量。<BR>
     */
    public String bearQuantity;

    /**
     * (買ポジション建玉)<BR>
     * 買ポジション建玉。<BR>
     */
    public String longPositionContract;

    /**
     * (買ポジション建玉(内注文中))<BR>
     * 注文中買ポジション建玉。<BR>
     */
    public String partLongPositionContract;

    /**
     * (売ポジション建玉)<BR>
     * 売ポジション建玉。<BR>
     */
    public String shortPositionContract;

    /**
     * (売ポジション建玉(内注文中))<BR>
     * 注文中売ポジション建玉。<BR>
     */
    public String partShortPositionContract;

    /**
     * コンストラクタ。
     */
    public WEB3IfoDepositTranRefPerIndexUnit()
    {

    }
}
@
