head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityProcessResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理結果区分定数定義インタフェイス(WEB3AdminEquityProcessResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/13 劉剣（中訊）新規作成
*/

package webbroker3.eqtypeadmin.define;

/**
 * 処理結果区分定数定義インタフェイス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminEquityProcessResultDivDef
{
    /**
     * "データ更新済"<BR>
     */
    public final static String DATA_UPDATE = "データ更新済";

    /**
     * "メール送信済"<BR>
     */
    public final static String MAIL_SENDED = "メール送信済";

    /**
     * "処理エラー"<BR>
     */
    public final static String DEAL_ERROR = "処理エラー";
}
@
