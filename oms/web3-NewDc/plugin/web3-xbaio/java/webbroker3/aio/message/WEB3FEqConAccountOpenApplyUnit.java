head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenApplyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設申込明細(WEB3FEqConAccountOpenApplyUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
                   2006/02/08 黄建(中訊) 仕様変更・モデル481
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (外株口座開設申込明細)<BR>
 * 外株口座開設申込明細クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenApplyUnit extends Message 
{
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * (申込日時)<BR>
     * YYYYMMDDHHMMSS
     */
    public Date applyDate;
    
    /**
     * (開設日時)<BR>
     * YYYYMMDDHHMMSS
     */
    public Date openDate;
    
    /**
     * (ステータス区分)<BR>
     * 0：口座開設中<BR>
     * 1：口座開設完了<BR>
     * 2：口座開設エラー<BR>
     * 9：削除
     */
    public String statusDiv;
    
    /**
     * (送受信区分)<BR>
     * 0：送信未済<BR>
     * 1：送信済
     */
    public String sendRcvDiv;
    
    /**
     * (名前（姓）)<BR>
     * 外国株式取引用の名前（姓）
     */
    public String familyName;
    
    /**
     * (名前（名）)<BR>
     * 外国株式取引用の名前（名）
     */
    public String name;
    
    /**
     * (外株口座番号)<BR>
     * 外国株式取引用の口座番号<BR>
     * <BR>
     * 口座開設状況区分＝0(口座開設中)または2(口座開設エラー）の場合はnull
     */
    public String fstkAccountCode;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;
    
    /**
     * (備考)<BR>
     * 10000000：口座開設受付済 <BR>
     * 20000000：口座開設中 <BR>
     * 90000000：取消済 <BR>
     * 99999999：システムエラー <BR>
     * 00000000：口座開設完了 <BR>
     * 90000009:口座抹消
     */
    public String biko;
    
    /**
     * (質問情報一覧)<BR>
     * 外株口座開設質問情報の一覧
     */
    public WEB3FEqConAccountOpenQuestionInfo[] questionInfoList;
    
    /**
     * (更新可能フラグ)<BR>
     * 更新可能フラグ<BR>
     * <BR>
     * 更新可能： true<BR>
     * 更新不可： false
     */
    public boolean updateFlag;
    
    /**
     * (外株口座開設申込明細)<BR>
     * コンストラクタ
     * @@roseuid 41CFACF201DC
     */
    public WEB3FEqConAccountOpenApplyUnit() 
    {
     
    }
}
@
