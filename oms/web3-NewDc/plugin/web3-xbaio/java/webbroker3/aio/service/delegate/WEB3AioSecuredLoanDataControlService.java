head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecuredLoanDataControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローンデータ制御サービス(WEB3AioSecuredLoanDataControlService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 柴双紅 (中訊) 新規作成 仕様変更 モデルNo.755、No.761、No.776、No.788、No.796
*/

package webbroker3.aio.service.delegate;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.StockSecuredLoanParams;

/**
 * (証券担保ローンデータ制御サービス)<BR>
 * 証券担保ローンデータ制御サービスインターフェイス。<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AioSecuredLoanDataControlService extends Service
{
    /**
     * (insert株券担保ローン)<BR>
     * 株券担保ローン口座テーブルにinsert処理を行う。<BR>
     * <BR>
     * @@param l_strStockLoanAccNumber - (ストックローン口座番号)<BR>
     * ストックローン口座番号<BR>
     * @@param l_mainAccountParams - (顧客Params)<BR>
     * 顧客Params<BR>
     * @@throws WEB3BaseException
     */
    public void insertStockSecuredLoan(String l_strStockLoanAccNumber, MainAccountParams l_mainAccountParams)
        throws WEB3BaseException;

    /**
     * (get株券担保ローン顧客情報)<BR>
     * 株券担保ローン口座オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_lngAccId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_tsDate - (日付)<BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getStockSecuredLoanAccInfo(long l_lngAccId, Timestamp l_tsDate) throws WEB3BaseException;

    /**
     * (get株券担保ローン一覧)<BR>
     * 指定された条件に合致する株券担保ローン口座テーブルを検索し、<BR>
     * その結果を株券担保ローンParamsオブジェクトの配列にして返却する。<BR>
     * <BR>
     * @@param l_stockSecuredLoans - (株券担保ローン配列[])<BR>
     * 株券担保ローン配列[]<BR>
     * @@return StockSecuredLoanParams[]
     * @@throws WEB3BaseException
     */
    public StockSecuredLoanParams[] getStockSecuredLoanList(Object[] l_stockSecuredLoans) throws WEB3BaseException;

    /**
     * (update採番テーブル)<BR>
     * 採番テーブルにupdate処理を行う<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSerialNumberName - (採番項目名)<BR>
     * 採番項目名<BR>
     * @@param l_strSerialNumber - (シリアルナンバー)<BR>
     * シリアルナンバー<BR>
     * @@throws WEB3BaseException
     */
    public void updateCommSerialNumbers(
        String l_strInstitutionCode,
        String l_strSerialNumberName,
        String l_strSerialNumber) throws WEB3BaseException;
}
@
