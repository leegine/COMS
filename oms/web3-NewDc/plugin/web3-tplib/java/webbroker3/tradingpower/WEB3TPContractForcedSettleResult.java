head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractForcedSettleResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉強制決済結果(WEB3TPContractForcedSettleResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/25 肖志偉 (中訊) 新規作成
Revesion History : 2008/01/29 張騰宇 (中訊) モデル251
*/
package webbroker3.tradingpower;

import java.util.Date;

/**
 * 建玉強制決済結果
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3TPContractForcedSettleResult
{
    /**
     * (判定フラグ)<BR>
     * <BR>
     * 強制決済対象顧客である場合、trueをセット、<BR>
     * そうでない場合、falseをセット<BR>
     */
    public boolean resultFlg;

    /**
     * (強制決済理由)<BR>
     *<BR>
     * [設定値]<BR>
     * 　@"1"：オンライン開始前（重度）<BR>
     * 　@"2"：オンライン開始前（軽度）<BR>
     * 　@"3"：場間<BR>
     * 　@"4"：オンライン開始前（法@定） <BR>
     * <BR>
     * ※オンライン開始前（重度）、オンライン開始前（軽度）、<BR>
     * 　@いずれの条件に該当した場合、朝（重度）を強制決済理由とする。<BR>
     * ※判定フラグ==falseの場合、nullをセット<BR>
     */
    public String forcedSettleReason;

    /**
     * (追証発生日) <BR>
     * <BR>
     * ※判定フラグ==falseの場合、nullをセット<BR>
     */
    public Date additionalMarginDate;

    /**
     * (追証発生日からの経過日数) <BR>
     * <BR>
     * ※判定フラグ==falseの場合、nullをセット<BR>
     */
    public Integer additionalMarginAccruedDays;

    /**
     * (保証金預託率) <BR>
     * <BR>
     * ※判定フラグ==falseの場合、nullをセット<BR>
     */
    public Double marginMaintenanceRate;
}
@
