head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSExecHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式(PTS)約定履歴（WEB3AdminEquityPTSExecHistory.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル172
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者・株式(PTS)約定・約定取消履歴)<BR>
 * 管理者・株式(PTS)約定・約定取消履歴　@データクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSExecHistory extends Message
{

    /**
     * (取消可能フラグ)<BR>
     * false：出来取消不可<BR>
     * true：出来取消可能<BR>
     */
    public boolean cancelFlag = false;

    /**
     * (約定日時)<BR>
     */
    public Date executionTimeStamp;

    /**
     * (約定株数)<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     */
    public String execPrice;

    /**
     * (約定・約定取消区分)<BR>
     * 0： 約定<BR>
     * 1： 全部約定<BR>
     * 2： 一部約定<BR>
     * 4： 約定取消<BR>
     */
    public String inputExecCancelExecDiv;

    /**
     * (更新者コード)<BR>
     * 取引所からの通知の場合はnull、<BR>
     * 管理者・株式（PTS）出来入力・出来取消で作成した場合は<BR>
     * 更新者(管理者)のコードがセットされる。<BR>
     */
    public String updaterCode = null;

    /**
     * (処理区分)<BR>
     * 約定・約定取消データの処理状態を表す区分<BR>
     * <BR>
     * 0：未処理<BR>
     * 1：処理済<BR>
     * 8：プログラムエラー<BR>
     * 9：データエラー<BR>
     */
    public String inputExecCancelExecProcDiv;

    /**
     * @@roseuid 4795A0F80071
     */
    public WEB3AdminEquityPTSExecHistory()
    {

    }
}
@
