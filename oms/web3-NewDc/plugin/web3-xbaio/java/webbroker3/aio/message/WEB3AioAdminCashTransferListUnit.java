head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAdminCashTransferListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧明細(WEB3AioAdminCashTransferListUnit)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (入出金一覧明細)<BR>
 * 入出金一覧明細クラス<BR>
 * 
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioAdminCashTransferListUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * −−−−−−−−−−−−−−−−−−−−−−−−−<BR>
     * ＜入金＞<BR>
     * 101： SONAR入金<BR>
     * 102： バーチャル入金<BR>
     * 103： ネット入金<BR>
     * 104： 振替(株先証拠金から預り金)<BR>
     * 105： 為替保証金振替(為替保証金から預り金)<BR>
     * 106： その他振替(Xから預り金)<BR>
     * ＜出金＞<BR>
     * 201： 出金<BR>
     * 202： 振替(預り金から株先証拠金)<BR>
     * 203： 為替保証金振替(預り金から為替保証金)<BR>
     * 204： その他振替(預り金からX)<BR>
     * −−−−−−−−−−−−−−−−−−−−−−−−−<BR>
     */
    public String orderType;

    /**
     * (受渡日)<BR>
     * 注文の受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客氏名)<BR>
     * 顧客氏名<BR>
     */
    public String accountName;

    /**
     * (注文日時)<BR>
     * 注文日時<BR>
     */
    public Date orderDate;

    /**
     * (ステータス)<BR>
     * ステータス<BR>
     * <BR>
     * −−−−−<BR>
     * 1： 完了<BR>
     * 2： 未処理<BR>
     * 9： エラー<BR>
     * −−−−−<BR>
     */
    public String cashinoutStatus;

    /**
     * (入金金額)<BR>
     * 入金金額<BR>
     */
    public String cashinAmt;

    /**
     * (出金金額)<BR>
     * 出金金額<BR>
     */
    public String cashoutAmt;

    /**
     * (入力経路)<BR>
     * 入力経路<BR>
     */
    public String orderRoutDiv;

    /**
     * (入力者)<BR>
     * 入力者<BR>
     */
    public String operatorCode;

    /**
     * (銀行コード)<BR>
     * 銀行コード<BR>
     */
    public String financialInstitutionCode;

    /**
     * (支店コード)<BR>
     * 支店コード<BR>
     */
    public String financialBranchCode;

    /**
     * (口座種別)<BR>
     * 口座種別<BR>
     */
    public String accountTypeCode;

    /**
     * (口座番号)<BR>
     * 口座番号<BR>
     */
    public String financialAccountCode;

    /**
     * @@roseuid 45C3F15701A5
     */
    public WEB3AioAdminCashTransferListUnit() 
    {
        
    }
}
@
