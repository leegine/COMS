head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMultiDocCheckResultUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 複数銘柄目論見書閲覧チェックUnitクラス(WEB3GentradeMultiDocCheckResultUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/17 趙林鵬(中訊) 新規作成 モデルNo.330
*/

package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (複数銘柄目論見書閲覧チェックUnitクラス)<BR>
 * 複数銘柄目論見書閲覧チェックUnitクラス<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0 
 */
public class WEB3GentradeMultiDocCheckResultUnit extends Message
{
    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String typeCode;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (チェック結果)<BR>
     * チェック結果<BR>
     * <BR>
     * 0： 閲覧済<BR>
     * 1： 閲覧未済<BR>
     */
    public String checkResult;

    /**
     * 複数銘柄目論見書閲覧チェック結果Unitクラス<BR>
     * コンストラクタ<BR>
     */
    public WEB3GentradeMultiDocCheckResultUnit() 
    {

    }
}@
