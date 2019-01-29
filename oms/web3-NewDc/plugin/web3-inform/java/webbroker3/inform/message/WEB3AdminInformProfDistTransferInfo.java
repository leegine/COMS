head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistTransferInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先情報クラス(WEB3AdminInformProfDistSellTransSrcInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
Revision History    : 2007/06/12 謝旋(中訊) 仕様変更 モデルNo.079
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.DirectDebitRow;


/**
 * (利金・分配金・売却代金振込先情報クラス)<BR>
 * 利金・分配金・売却代金振込先情報クラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistTransferInfo extends Message
{
    /**
     * (登録年月日)<BR>
     * 登録年月日
     */
    public Date registDate;

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
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）
     */
    public String accountName;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）
     */
    public String accountNameKana;

    /**
     * (扱者コード)<BR>
     * 扱者コード
     */
    public String traderCode;

    /**
     * (指定区分)<BR>
     * 指定区分
     */
    public String specifyDiv;

    /**
     * (商品)<BR>
     * 商品
     */
    public String product;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;

    /**
     * (振替区分)<BR>
     * 振替区分
     */
    public String transferDiv;

    /**
     * (銀行コード)<BR>
     * 銀行コード
     */
    public String financialInstitutionCode;

    /**
     * (銀行名)<BR>
     * 銀行名
     */
    public String financialInstitutionName;

    /**
     * (支店コード／通帳記号)<BR>
     * 支店コード／通帳記号
     */
    public String financialBranchCode;

    /**
     * (支店名)<BR>
     * 支店名
     */
    public String financialBranchName;

    /**
     * (預金区分)<BR>
     * 預金区分
     */
    public String financialAccountDiv;

    /**
     * (口座番号／通帳番号)<BR>
     * 口座番号／通帳番号
     */
    public String financialAccountCode;

    /**
     * (振込先区分)<BR>
     * 振込先区分
     */
    public String transferAccountDiv;

    /**
     * (取扱区分)<BR>
     * 取扱区分
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 4655937501F2
     */
    public WEB3AdminInformProfDistTransferInfo()
    {

    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 各項目に、引数.自動振替登録マスタRowの同項目の値をセットする。<BR>
     * ※対応する項目がない場合は、nullを設定する。<BR>
     * ※顧客コードは、引数.自動振替登録マスタRow.顧客コードの頭6桁をセットする。<BR>
     * @@param l_directDebitRow - (自動振替登録マスタRow)<BR>
     * 自動振替登録マスタRow<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4643C4F0030A
     */
    public WEB3AdminInformProfDistTransferInfo(DirectDebitRow l_directDebitRow)
        throws WEB3BaseException
    {
        // 登録年月日
        this.registDate = l_directDebitRow.getSonarCreatedTimestamp();

        // 部店コード
        this.branchCode = l_directDebitRow.getBranchCode();

        // 顧客コード
        this.accountCode = l_directDebitRow.getAccountCode().substring(0,6);

        // 顧客名（漢字）
        this.accountName = null;

        // 顧客名（カナ）
        this.accountNameKana = null;

        // 扱者コード
        this.traderCode = l_directDebitRow.getTraderCode();

        // 指定区分
        this.specifyDiv = l_directDebitRow.getDesignateDiv();

        // 商品
        this.product = l_directDebitRow.getComodity();

        // 銘柄コード
        this.productCode = l_directDebitRow.getFundCode();

        // 銘柄名
        this.productName = null;

        // 振替区分
        this.transferDiv = l_directDebitRow.getTransferDiv();

        // 銀行コード
        this.financialInstitutionCode = l_directDebitRow.getFinInstitutionCode();

        // 銀行名
        this.financialInstitutionName = l_directDebitRow.getFinInstitutionName();

        // 支店コード／通帳記号
        this.financialBranchCode = l_directDebitRow.getFinBranchCode();

        // 支店名
        this.financialBranchName = l_directDebitRow.getFinBranchName();

        // 預金区分
        this.financialAccountDiv = l_directDebitRow.getFinSaveDiv();

        // 口座番号／通帳番号
        this.financialAccountCode = l_directDebitRow.getFinAccountNo();

        // 振込先区分
        this.transferAccountDiv = l_directDebitRow.getTransCommission();

        // 取扱区分
        this.tradeHandleDiv = l_directDebitRow.getTransDealDiv();
    }
}
@
