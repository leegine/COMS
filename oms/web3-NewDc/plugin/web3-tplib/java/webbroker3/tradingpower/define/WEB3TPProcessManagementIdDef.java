head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPProcessManagementIdDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 ネットトレードシステム開発部
 File Name        : 「プロセス管理テーブルプロセスＩＤ」の定数定義インターフェース(WEB3TPProcessManagementIdDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/07/24 nakazato(DIR-ST) 新規作成
 Revesion History : 2008/04/02 崔遠鵬 (中訊) モデルNo.272
 Revesion History : 2008/10/23 孟亞南 (中訊) モデルNo.308
 */
package webbroker3.tradingpower.define;

/**
 * (「プロセス管理テーブルプロセスＩＤ」の定数定義インターフェース)
 */
public interface WEB3TPProcessManagementIdDef
{
    /**
     * (「プロセスID(0006:余力再計算基準時間)」の定数定義。)<BR>
     */
    public final static String TP_DATUM_TIME = "0006";
    
    /**
     * (「プロセスID(0009:翌日注文受付開始時間)」の定数定義。)<BR>
     */
    public final static String EQUITY_NEXTDAYORDER_STARTTIME = "0009";

    /**
     * (「プロセスID(0010:PTS出来終了)」の定数定義。)<BR>
     */
    public final static String PTS_ORDER_EXECUTION_END_TYPE = "0010";

    /**
     * (「プロセスID(”0005”（保証金自動振替終了）)」の定数定義。)<BR>
     */
    public final static String DEPOSIT_AUTO_TRANSFER_STOP = "0005";
}
@
