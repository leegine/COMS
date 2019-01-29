head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferReportUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替レポート明細(WEB3FEqConTransferReportUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
                   2006/02/08 黄建(中訊) 仕様変更・モデル481
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (外株振替レポート明細)<BR>
 * 外株振替レポート明細クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferReportUnit extends Message 
{
    
    /**
     * (振替区分)<BR>
     * 振替区分<BR>
     * <BR>
     * 1：入金<BR>
     * 2：出金
     */
    public String transferDiv;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (名前（姓）)<BR>
     * 顧客名(姓)
     */
    public String familyName;
    
    /**
     * (名前（名）)<BR>
     * 顧客名(名)
     */
    public String name;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * (受付日時)<BR>
     * 受付日時
     */
    public Date receptionDate;
    
    /**
     * (振替日)<BR>
     * 振替日
     */
    public String transferDate;
    
    /**
     * (振替金額)<BR>
     * 振替金額
     */
    public String changeAmt;
    
    /**
     * (外株口座番号)<BR>
     * 外株口座番号
     */
    public String fstkAccountCode;
    
    /**
     * (UWG受付日時)<BR>
     * UWG受付日時
     */
    public Date uwgReceptionDate;
    
    /**
     * (ステータス区分)<BR>
     * 0：決済中<BR>
     * 1：決済完了<BR>
     * 2：決済エラー<BR>
     * 3：取消
     */
    public String statusDiv;
    
    /**
     * (メッセージ)<BR>
     * 10000000：受付済 <BR> 
     * 20000000：決済中 <BR> 
     * 90000000：取消済 <BR> 
     * 99999999：決済失敗（システムエラー） <BR> 
     * 00000000：決済完了<BR> 
     * 90000009:口座抹消
     */
    public String message;
    
    /**
     * (外株振替レポート明細)<BR>
     * コンストラクタ
     * @@roseuid 41D0BA6200ED
     */
    public WEB3FEqConTransferReportUnit() 
    {
     
    }
}
@
