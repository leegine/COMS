head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金計算(WEB3IfoDepositCalc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 nakazato(ACT) 新規作成
Revesion History : 2007/07/06 hijikata(SRA) 夕場対応　@
                      モデルNo.060,No.063,No.065,No.072,No.075,No.078,No.079,No.088,No.092,No.93         
                      計算式書No.022,No.023,No.025,No.027,No.028,No.029,No.032
Revesion History : 2007/08/02 hijikata(SRA) 夕場対応　@モデルNo.095, No.101, No.103, No.106
Revesion History : 2007/08/07 k.yamashita(SRA) 夕場対応　@U03038, U03039, U03040, U03042
Revesion History : 2007/08/10 k.yamashita(SRA) 夕場対応　@計算式書 No.039
Revision History : 2007/08/13 k.yamashita(SRA) 夕場対応 U03048,U03049
Revision History : 2007/10/18 k.yamashita(SRA)  未取込要件No.014,021
*/
package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSpanConnectService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTotalContractSpec;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.ifodeposit.define.WEB3IfoDepositBranchPrefNameDef;
import webbroker3.ifodeposit.define.WEB3IfoDepositPriceScanMultiplyDef;
import webbroker3.ifodeposit.define.WEB3IfoDepositUnderlyingProductCodeDef;
import webbroker3.ifodeposit.define.WEB3IfoPositionBalanceTypeDef;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証拠金計算)
 * 証拠金計算を行うクラス。
 */
public class WEB3IfoDepositCalc
{

    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalc.class);

    /**
     * (補助口座)
     */
    private WEB3GentradeSubAccount subAccount;

    /**
     * (証拠金計算条件)
     */
    private WEB3IfoDepositCalcCondition ifoDepositCalcCondition;

    /**
     * (銘柄建単価別先物OP建玉集計一覧)
     */
    private WEB3IfoSummaryContractPerProductContractPrice ifoSummaryContractPerProductContractPriceList[];

    /**
     * (銘柄別先物OP建玉集計一覧)
     */
    private WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[];

    /**
     * (指数別先物OP建玉集計一覧)
     */
    private WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[];

    /**
     * (先物OP顧客移動明細)
     */
    private WEB3IfoCustomerTransfer ifoCustomerTransfer;

    /**
     * (コンストラクタ)
     */
    public WEB3IfoDepositCalc()
    {

    }

    /**
     * (証拠金計算)<BR>
     * 
     * 新規建余力チェック時に使用するコンストラクタ。<BR>
     * シーケンス図<BR>
     * 「（証拠金計算）コンストラクタ（新規建余力チェック時）」参照。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_ifoDepositCalcCondition - (証拠金計算条件)<BR>
     * 証拠金計算条件オブジェクト。<BR>
     * @@param l_ifoNewOrderSpec - 先物OP現注文内容。
     */
    public WEB3IfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException, WEB3BaseException
    {
        //this.補助口座に、引数.補助口座をセット
        this.subAccount = l_subAccount;

        //this,.証拠金計算条件に、引数.証拠金計算条件をセット
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;

        //this.create先物OP保有建玉情報一覧()をコール
        WEB3IfoContract[] l_ifoContract = this.createIfoContractList();

        //this.create先物OP当日新規建注文情報一覧()をコール
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrder =
            this.createIfoTodayOpenOrderList(l_ifoNewOrderSpec);

        //this.create先物OP顧客移動明細()をコール
        this.createIfoCustomerTransfer(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create取引銘柄Params一覧()をコール
        IfoTradedProductParams[] l_ifoTradedProductParams =
            this.createTradedProductParamsList(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create先物OP銘柄情報一覧()をコール
        WEB3IfoProduct[] l_ifoProduct = this.createIfoProductList(l_ifoTradedProductParams);

        //this.create銘柄建単価別先物OP建玉集計一覧()をコール
        this.createIfoSummaryContractPerProductContractPriceList(l_ifoContract, l_ifoProduct);

        //this.create銘柄別先物OP建玉集計一覧()をコール
        this.createIfoSummaryContractPerProductList(
            l_ifoContract,
            l_ifoTodayOpenOrder,
            l_ifoProduct);

        //this.create指数別先物OP建玉集計一覧()をコール()
        this.createIfoSummaryContractPerIndexList(l_ifoContract, l_ifoTodayOpenOrder, l_ifoProduct);

        //ログ出力
        this.printLog();
        //---------------Debug Log Add Start
        if (l_ifoContract == null) {
            log.debug("ifoContract == null");
        } else {
            for (int i = 0; i < l_ifoContract.length; i++){
                log.debug("ifoContract[" + i +"]" + l_ifoContract[i].toString());      	
            }
        }
        if (l_ifoProduct == null) {
            log.debug("ifoProduct == null");
        } else {
            for (int i = 0; i < l_ifoProduct.length; i++){
                log.debug("ifoProduct[" + i +"]" + l_ifoProduct[i].toString());      	
            }
        }
        if (l_ifoTodayOpenOrder == null) {
            log.debug("ifoTodayOpenOrder == null");
        } else {
            for (int i = 0; i < l_ifoTodayOpenOrder.length; i++){
                log.debug("ifoTodayOpenOrder[" + i +"]" + l_ifoTodayOpenOrder[i].toString());      	
            }
        }
        //---------------Debug Log Add End
    }

    /**
     * (証拠金計算)<BR>
     * コンストラクタ。<BR>
     * シーケンス図<BR>
     * 「（証拠金計算）コンストラクタ」参照。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param ifoDepositCalcCondition - (証拠金計算条件)<BR>
     * 証拠金計算条件オブジェクト。<BR>
     */
    public WEB3IfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
        throws WEB3SystemLayerException, WEB3BaseException
    {
        //this.補助口座に、引数.補助口座をセット
        this.subAccount = l_subAccount;

        //this,.証拠金計算条件に、引数.証拠金計算条件をセット
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;

        //this.create先物OP保有建玉情報一覧()をコール
        WEB3IfoContract[] l_ifoContract = this.createIfoContractList();

        //this.create先物OP当日新規建注文情報一覧()をコール
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrder = this.createIfoTodayOpenOrderList();

        //this.create先物OP顧客移動明細()をコール
        this.createIfoCustomerTransfer(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create取引銘柄Params一覧()をコール
        IfoTradedProductParams[] l_ifoTradedProductParams =
            this.createTradedProductParamsList(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create先物OP銘柄情報一覧()をコール
        WEB3IfoProduct[] l_ifoProduct = this.createIfoProductList(l_ifoTradedProductParams);

        //this.create銘柄建単価別先物OP建玉集計一覧()をコール
        this.createIfoSummaryContractPerProductContractPriceList(l_ifoContract, l_ifoProduct);

        //this.create銘柄別先物OP建玉集計一覧()をコール
        this.createIfoSummaryContractPerProductList(
            l_ifoContract,
            l_ifoTodayOpenOrder,
            l_ifoProduct);

        //this.create指数別先物OP建玉集計一覧()をコール()
        this.createIfoSummaryContractPerIndexList(l_ifoContract, l_ifoTodayOpenOrder, l_ifoProduct);

        //ログ出力
        this.printLog();
        //---------------Debug Log Add Start
        if (l_ifoContract == null) {
            log.debug("ifoContract == null");
        } else {
            for (int i = 0; i < l_ifoContract.length; i++){
                log.debug("ifoContract[" + i +"]" + l_ifoContract[i].toString());      	
            }
        }
        if (l_ifoProduct == null) {
            log.debug("ifoProduct == null");
        } else {
            for (int i = 0; i < l_ifoProduct.length; i++){
                log.debug("ifoProduct[" + i +"]" + l_ifoProduct[i].toString());      	
            }
        }
        if (l_ifoTodayOpenOrder == null) {
            log.debug("ifoTodayOpenOrder == null");
        } else {
            for (int i = 0; i < l_ifoTodayOpenOrder.length; i++){
                log.debug("ifoTodayOpenOrder[" + i +"]" + l_ifoTodayOpenOrder[i].toString());      	
            }
        }
        //---------------Debug Log Add End
    }

    /**
     * (create先物OP当日新規建注文情報一覧)<BR>
     * 
     * 先物OP当日新規建注文情報の一覧を作成する。<BR>
     * ※該当の先物OP当日新規建注文が存在しない場合は、nullを返却する。<BR>
     * シーケンス図<BR>
     * 「（証拠金計算）create先物OP当日新規建注文情報一覧」参照。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder[]
     */
    private WEB3IfoTodayOpenOrder[] createIfoTodayOpenOrderList()
    {
        //先物OP当日新規建注文情報を格納するArrayListを生成する。
        ArrayList l_ifoTodayOpenOrders = new ArrayList();

        //口座IDを取得する
        long l_lngAccountId = this.subAccount.getAccountId();
        //補助口座IDを取得する
        long l_lngSubAccountId = this.subAccount.getSubAccountId();
        //営業日[T+0]を取得する。
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //当日新規建注文単位Params一覧を取得する
        IfoOrderUnitParams[] l_ifoOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //取得した当日新規建注文単位Params == nullの時
        if (l_ifoOrderUnitParams == null)
        {
            //nullを返却する
            return null;
        }

        //取得した当日新規建注文単位Params一覧の要素数回LOOP処理
        for (int index = 0; index < l_ifoOrderUnitParams.length; index++)
        {
            //取得した当日新規建注文単位Params一覧より先物OP当日新規建情報を生成する
            WEB3IfoTodayOpenOrder l_ifoCurTodayOpenOrder =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams[index]);

            //生成した先物OP当日新規建情報をArrayListに格納する
            l_ifoTodayOpenOrders.add(l_ifoCurTodayOpenOrder);
        }

        //先物OP当日新規建情報のArrayListを配列に変換して返却する
        WEB3IfoTodayOpenOrder[] l_returns = new WEB3IfoTodayOpenOrder[l_ifoTodayOpenOrders.size()];
        l_returns = (WEB3IfoTodayOpenOrder[])l_ifoTodayOpenOrders.toArray(l_returns);
        return l_returns;
    }

    /**
     * (create先物OP当日新規建注文情報一覧)<BR>
     * 
     * 今回注文(引数.先物OP現注文内容)を含んだ先物OP当日新規建注文情報の一覧を作成する<BR>
     * 
     * （新規建余力チェック時に使用）<BR>
     * シーケンス図<BR>
     * 「（証拠金計算）create先物OP当日新規建注文情報一覧（新規建余力チェック時）」参照。<BR>
     * 
     * @@param l_ifoNewOrderSpec - 先物OP現注文内容。
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder[]
     */
    private WEB3IfoTodayOpenOrder[] createIfoTodayOpenOrderList(WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
    {
        //先物OP当日新規建注文情報を格納するHashMapを生成する
        HashMap l_ifoTodayOpenOrders = new HashMap();

        //口座IDを取得する
        long l_lngAccountId = this.subAccount.getAccountId();
        //補助口座IDを取得する
        long l_lngSubAccountId = this.subAccount.getSubAccountId();
        //営業日[T+0]を取得する。
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //当日新規建注文単位Params一覧を取得する。
        IfoOrderUnitParams[] l_ifoOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //取得した当日新規建注文単位Params一覧 != nullの時
        if (l_ifoOrderUnitParams != null)
        {
            //取得した当日新規建注文単位Params一覧の要素数回LOOP処理
            for (int index = 0; index < l_ifoOrderUnitParams.length; index++)
            {
                //取得した当日新規建注文単位Params一覧より先物OP当日新規建情報を生成する
                WEB3IfoTodayOpenOrder l_ifoCurTodayOpenOrder =
                    WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams[index]);

                //生成した先物OP当日新規建情報をHashMapに格納する
                l_ifoTodayOpenOrders.put(
                    Long.toString(l_ifoOrderUnitParams[index].getOrderUnitId()),
                    l_ifoCurTodayOpenOrder);
            }
        }

        //先物OP現注文内容から先物OP当日新規建注文情報を作成する。 
        WEB3IfoTodayOpenOrder l_ifoTodayOpenOrder =
            WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoNewOrderSpec);

        //HashMapに生成した先物OP当日新規建注文情報を追加する。
        l_ifoTodayOpenOrders.put(Long.toString(l_ifoNewOrderSpec.orderUnitId), l_ifoTodayOpenOrder);

        //先物OP当日新規建情報のArrayListを配列に変換して返却する
        WEB3IfoTodayOpenOrder[] l_returns = new WEB3IfoTodayOpenOrder[l_ifoTodayOpenOrders.size()];
        l_returns = (WEB3IfoTodayOpenOrder[])l_ifoTodayOpenOrders.values().toArray(l_returns);
        return l_returns;
    }

    /**
     * (create先物OP保有建玉情報一覧)<BR>
     * 
     * 先物OP保有建玉情報の一覧を作成する。<BR>
     * ※該当の先物OP建玉が存在しない場合は、nullを返却する。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create先物OP保有建玉情報一覧」参照。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoContract[]
     */
    private WEB3IfoContract[] createIfoContractList()
    {
        //先物OP保有建玉情報を格納するHashMapを生成する。
        HashMap l_ifoContracts = new HashMap();

        //口座IDを取得する
        long l_lngAccountId = this.subAccount.getAccountId();
        //補助口座を取得する
        long l_lngSubAccountId = this.subAccount.getSubAccountId();

        //建玉Params一覧を取得する
        IfoContractParams[] l_ifoContractParams =
            WEB3IfoDepositPersistentDataManager.getContractParamsList(
                l_lngAccountId,
                l_lngSubAccountId);

        //取得した建玉Params一覧==nullの時
        if (l_ifoContractParams == null)
        {
            //nullを返却する
            return null;
        }

        //get営業日[T+0]
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //取得した建玉Params一覧の要素数回LOOP処理
        for (int index = 0; index < l_ifoContractParams.length; index++)
        {
            //建玉Params一覧より先物OP保有建玉情報を生成する
            WEB3IfoContract l_ifoCurContract =
                WEB3IfoContract.getIfoContract(l_ifoContractParams[index], l_datCurrentBizDate);

            //生成した先物OP保有建玉情報をHashMapに格納する 
            l_ifoContracts.put(
                Long.toString(l_ifoContractParams[index].getContractId()),
                l_ifoCurContract);
        }

        //トランザクションParams一覧を取得する。
        IfoFinTransactionParams[] l_finTransactionParams =
            WEB3IfoDepositPersistentDataManager.getFinTransactionParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //取得したトランザクションParams != nullの場合
        if (l_finTransactionParams != null)
        {
            //(カーソル)トランザクションParams
            IfoFinTransactionParams l_curFinTransaction = null;
            //取得したトランザクションParams一覧の要素数回LOOP処理
            for (int index = 0; index < l_finTransactionParams.length; index++)
            {
                //(カーソル)トランザクションParamsを取得する。
                l_curFinTransaction = l_finTransactionParams[index];

                //HashMapより、先物OP保有建玉情報を取り出す
                WEB3IfoContract l_ifoContract =
                    (WEB3IfoContract)l_ifoContracts.get(
                        Long.toString(l_curFinTransaction.getContractId()));

                //注文単位IDにひもづく注文単位を取得する。 
                IfoOrderUnitParams l_ifoOrderUnitParams = 
                WEB3IfoDepositPersistentDataManager.getIfoOrderUnitParams(
                    l_curFinTransaction.getOrderUnitId());

                //先物OP保有建玉情報.建玉数量より約定数量を減算する
                l_ifoContract.subtractQuantity(
                    l_curFinTransaction.getFinTransactionCateg(),
                    l_curFinTransaction.getQuantity(),
                    l_ifoOrderUnitParams.getSessionType());

                //先物OP保有建玉情報に、トランザクションParamsを追加する        
                l_ifoContract.addIfoFinTransaction(l_curFinTransaction);

                //HashMapに、先物OP保有建玉情報を格納する
                l_ifoContracts.put(
                    Long.toString(l_curFinTransaction.getContractId()),
                    l_ifoContract);
            }
        }

        //先物OP保有建玉情報のHashMapを配列に変換して返却する
        WEB3IfoContract[] l_returns = new WEB3IfoContract[l_ifoContracts.size()];
        l_returns = (WEB3IfoContract[])l_ifoContracts.values().toArray(l_returns);       
        return l_returns;
    }

    /**
     * (create先物OP顧客移動明細)<BR>
     * 
     * 先物OP顧客異動明細の作成、プロパティの設定を行い、
     * this.先物OP顧客移動明細にセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create先物OP顧客移動明細」」参照。<BR>
     * 
     * @@param l_ifoContractList - (先物OP保有建玉情報一覧)<BR>
     * 先物OP保有建玉情報の一覧。<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (先物OP当日新規建注文情報一覧)<BR>
     * 先物OP当日新規建注文情報の一覧。<BR>
     * 先物OP当日新規建注文が存在しない場合はnull。<BR>
     */
    private void createIfoCustomerTransfer(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList)
    {
        //先物OP顧客移動明細オブジェクトを生成する
        WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();

        /*
         * 確定残高[T+0..2]を取得する
         */
        //確定残高[T+0..2]
        double[] l_cashBalance = new double[3];

        //顧客勘定残高マスタ情報Paramsを取得する
        TpCashBalanceParams l_cashBalanceParams =
            WEB3IfoDepositPersistentDataManager.getTpCashBalanceParams(
                subAccount.getAccountId(),
                subAccount.getSubAccountId());

        //顧客勘定残高マスタ情報Paramsの戻り値がNULLの場合
        if(l_cashBalanceParams == null)
        {
            //確定残高[T+0..2]に値(=0)をセットする            
            l_cashBalance[0] = 0;
            l_cashBalance[1] = 0;
            l_cashBalance[2] = 0;
        }
        //以外の場合
        else
        {
            //確定残高[T+0..2]に値をセットする            
            l_cashBalance[0] = l_cashBalanceParams.getCashBalance0();
            l_cashBalance[1] = l_cashBalanceParams.getCashBalance1();
            l_cashBalance[2] = l_cashBalanceParams.getCashBalance2();
        }

        //残高[T+0..2]を、先物OP顧客移動明細にセットする。
        l_ifoCustomerTransfer.setBalances(l_cashBalance);

        //営業日[T+0]を取得する
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //営業日[T+1]を取得する
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();
        //営業日[T+2]を取得する
        Date l_datBizDate2 = this.ifoDepositCalcCondition.getNext2BizDate();

        //当日振替注文単位Params一覧を取得する
        AioOrderUnitParams[] l_aioOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayAioOrderUnitParamsList(
                subAccount.getAccountId(),
                subAccount.getSubAccountId(),
                l_datBizDate0);

        //当日振替注文単位Params一覧≠nullの時
        if (l_aioOrderUnitParams != null)
        {
            //当日振替注文単位Params一覧の要素数回LOOP処理
            for (int index = 0; index < l_aioOrderUnitParams.length; index++)
            {
                //先物OP顧客移動明細に入金額を加算する
                l_ifoCustomerTransfer.addCashinAmount(
                    l_aioOrderUnitParams[index].getOrderType(),
                    l_aioOrderUnitParams[index].getQuantity(),
                    l_aioOrderUnitParams[index].getDeliveryDate(),
                    l_datBizDate0);

                //先物OP顧客移動明細に出金額を加算する
                l_ifoCustomerTransfer.addCashoutAmount(
                    l_aioOrderUnitParams[index].getOrderType(),
                    l_aioOrderUnitParams[index].getQuantity(),
                    l_aioOrderUnitParams[index].getDeliveryDate(),
                    l_datBizDate0);
            }
            //振替額をセットする
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(
                l_ifoCustomerTransfer.getCurrentBizDateCashinAmount() - 
                l_ifoCustomerTransfer.getCurrentBizDateCashoutAmount());
                
            l_ifoCustomerTransfer.setNextBizDateTransferAmount(
                l_ifoCustomerTransfer.getNextBizDateCashinAmount() - 
                l_ifoCustomerTransfer.getNextBizDateCashoutAmount());
        }

        //引数.先物OP保有建玉情報一覧≠nullの時
        if (l_ifoContractList != null)
        {
            //先物OP保有建玉情報一覧の要素数回LOOP処理
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //先物決済損益[T+1]を取得する
                double l_dblFuturesCloseProfitLoss1 =
                    l_ifoContractList[index].getNextBizDateFuturesCloseProfitLoss(l_datBizDate1);
                //オプション受渡代金[T+1]を取得する
                double l_dblOptionNetAmount1 =
                    l_ifoContractList[index].getNextBizDateOptionNetAmount(l_datBizDate1);

                //先物OP顧客移動明細に、先物決済損益[T+1]を加算する
                l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(
                    l_dblFuturesCloseProfitLoss1);
                //先物OP顧客移動明細に、オプション受渡代金[T+1]を加算する
                l_ifoCustomerTransfer.addNextBizDateOptionNetAmount(l_dblOptionNetAmount1);

                //先物決済損益[T+2]を取得する
                double l_dblFuturesCloseProfitLoss2 =
                    l_ifoContractList[index].getNext2BizDateFuturesCloseProfitLoss(l_datBizDate2);
                //オプション受渡代金[T+2]を取得する
                double l_dblOptionNetAmount2 =
                    l_ifoContractList[index].getNext2BizDateOptionNetAmount(l_datBizDate2);

                //先物OP顧客移動明細に、先物決済損益[T+2]を加算する
                l_ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(
                    l_dblFuturesCloseProfitLoss2);
                //先物OP顧客移動明細に、オプション受渡代金[T+2]を加算する
                l_ifoCustomerTransfer.addNext2BizDateOptionNetAmount(l_dblOptionNetAmount2);
            }
        }

        //引数.先物OP当日新規建注文情報一覧≠nullの時
        if (l_ifoTodayOpenOrderList != null)
        {
            //先物OP当日新規建注文情報一覧の要素数回LOOP処理
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //オプションの時
                if (l_ifoTodayOpenOrderList[index].isFutures() == false)
                {
                    //当日受渡代金を取得する
                    double l_dblCurrentBizDateNetAmount =
                        this.getCurrentBizDateNetAmount(
                            l_ifoTodayOpenOrderList[index].getOrderUnitId(),
                            l_ifoContractList,
                            l_datBizDate0);

                    //先物OP当日新規建注文情報より、当日受渡代金を減算する
                    l_ifoTodayOpenOrderList[index].subtractOptionEstimatedNetAmount(
                        l_dblCurrentBizDateNetAmount);
                }

                //オプション買建概算受渡代金[T+1]を取得する 
                double l_dblEstimatedNetAmount1 =
                    l_ifoTodayOpenOrderList[index].getOptionBuyEstimatedNetAmount(l_datBizDate1);
                //オプション買建概算受渡代金[T+2]を取得する 
                double l_dblEstimatedNetAmount2 =
                    l_ifoTodayOpenOrderList[index].getOptionBuyEstimatedNetAmount(l_datBizDate2);

                //先物OP顧客移動明細に、オプション買建概算受渡代金[T+1]を加算する
                l_ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(
                    l_dblEstimatedNetAmount1);
                //先物OP顧客移動明細に、オプション買建概算受渡代金[T+2]を加算する
                l_ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(
                    l_dblEstimatedNetAmount2);
            }
        }

        //作成した先物OP顧客移動明細をプロパティにセットする。
        this.setIfoCustomerTransfer(l_ifoCustomerTransfer);
    }

    /**
     * (create取引銘柄Params一覧)<BR>
     * 
     * 該当顧客の保有/注文している先物OP取引銘柄Paramsの配列を作成する。<BR>
     * 
     * １）　@保有/注文している先物OP銘柄が存在しない場合<BR>
     * 　@引数.先物OP保有建玉情報一覧 == null、かつ、<BR>
     * 　@引数.先物OP当日新規建注文情報一覧 == nullの場合、nullを返却して終了する。<BR>
     * 
     * ２）　@取引銘柄Params一覧を格納するHashMapを生成する。<BR>
     * 
     * ３）　@引数.先物OP保有建玉情報一覧 != nullの場合のみ、先物OP保有建玉情報一覧ごとのLoop処理を行う。<BR>
     * 
     * 　@３－１）　@銘柄IDと市場IDの組み合わせがHashMapに追加済(*)の場合、次の要素に処理を移行する(continue;)<BR>
     * 　@　@　@　@　@　@(*)HashMap.containsKey(key)　@== true<BR>
     * 　@　@　@　@　@　@　@　@key：　@先物OP保有建玉情報.銘柄IDと先物OP保有建玉情報.市場IDの文字列連結値<BR>
     * 
     * 　@３－２）　@証拠金計算データソースアクセス管理.get取引銘柄Params()により取引銘柄Paramsを取得する。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@銘柄ID：　@先物OP保有建玉情報.銘柄ID<BR>
     * 　@　@　@　@　@　@市場ID：　@先物OP保有建玉情報.市場ID<BR>
     * 
     * 　@３－３）　@HashMap.put( )にて取引銘柄Paramsを追加する。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@key：　@先物OP保有建玉情報.銘柄IDと先物OP保有建玉情報.市場IDの文字列連結値<BR>
     * 　@　@　@　@　@　@value：　@取得した取引銘柄Params<BR>
     * 
     * ４）　@引数.先物OP当日新規建注文情報一覧 != nullの場合のみ、先物OP新規建注文情報一覧ごとのLoop処理を行う。<BR>
     * 
     * 　@４－１）　@銘柄IDと市場IDの組み合わせがHashMapに追加済(*)の場合、次の要素に処理を移行する(continue;)<BR>
     * 
     * 　@　@　@　@　@　@(*)HashMap.containsKey(key)　@== true<BR>
     * 　@　@　@　@　@　@　@　@key：　@先物OP当日新規建注文情報.銘柄IDと先物OP当日新規建注文情報.市場IDの文字列連結値<BR>
     * 
     * 　@４－２）　@証拠金計算データソースアクセス管理.get取引銘柄Params()により取引銘柄Paramsを取得する。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@銘柄ID：　@先物OP当日新規建注文情報.銘柄ID<BR>
     * 　@　@　@　@　@　@市場ID：　@先物OP当日新規建注文情報.市場ID<BR>
     * 
     * 　@４－３）　@HashMap.put( )にて取引銘柄Paramsを追加する。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@key：　@先物OP当日新規建注文情報.銘柄IDと先物OP当日新規建注文情報.市場IDの文字列連結値<BR>
     * 　@　@　@　@　@　@value：　@取得した取引銘柄Params<BR>
     * 
     * ５）　@HashMapを配列化して先物OP取引銘柄Paramsの配列を返却する。<BR>
     * 　@
     * @@param l_ifoContractList - (先物OP保有建玉情報の一覧)<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - 先物OP当日新規建注文情報の一覧。(BR>
     * 該当する先物OP当日新規建注文が存在しない場合はnull。(BR>
     * 
     * @@return IfoTradedProductParams[]
     */
    private IfoTradedProductParams[] createTradedProductParamsList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList)
    {

        //引数.先物OP保有建玉情報一覧 == null、かつ、引数.先物OP当日新規建注文情報一覧 == nullの時
        if (l_ifoContractList == null && l_ifoTodayOpenOrderList == null)
        {
            //nullを返却する。 
            return null;
        }

        //取引銘柄Params一覧を格納するHashMapを生成する。
        HashMap l_ifoTradedProducts = new HashMap();

        //引数.先物OP保有建玉情報一覧 != nullの時
        if (l_ifoContractList != null)
        {
            //(カーソル)先物OP保有建玉情報
            WEB3IfoContract l_curIfoContract = null;

            //引数.先物OP保有建玉情報一覧の要素数回LOOP処理
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(カーソル)先物OP保有建玉情報を取得する
                l_curIfoContract = l_ifoContractList[index];

                //HashMapのKey(銘柄ID+市場ID)を作成する。
                String l_strCurKey =
                    Long.toString(l_curIfoContract.productId)
                        + Long.toString(l_curIfoContract.marketId);
                //KeyがHashMapに追加未済みの時
                if (l_ifoTradedProducts.containsKey(l_strCurKey) == false)
                {
                    //取引銘柄Paramsを取得する
                    IfoTradedProductParams l_ifoTradedProductParams =
                        WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                            l_curIfoContract.productId,
                            l_curIfoContract.marketId);

                    //HashMapに、取引銘柄Paramsを追加する。 
                    l_ifoTradedProducts.put(l_strCurKey, l_ifoTradedProductParams);
                }
                
            }
        }

        //引数.先物OP当日新規建注文情報一覧 != nullの時
        if (l_ifoTodayOpenOrderList != null)
        {
            //(カーソル)先物OP新規建注文情報を取得する
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;

            //引数.先物OP新規建注文情報一覧の要素数回LOOP処理
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //(カーソル)先物OP新規建注文情報を取得する
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];

                //HashMapのKey(銘柄ID+市場ID)を作成する。
                String l_strCurKey =
                    Long.toString(l_curIfoTodayOpenOrder.productId)
                        + Long.toString(l_curIfoTodayOpenOrder.marketId);

                //KeyがHashMapに追加未済の時
                if (l_ifoTradedProducts.containsKey(l_strCurKey) == false)
                {
                    //取引銘柄Paramsを取得する。 
                    IfoTradedProductParams l_ifoTradedProductParams =
                        WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                            l_curIfoTodayOpenOrder.productId,
                            l_curIfoTodayOpenOrder.marketId);

                    //HashMapに取引銘柄Paramsを追加する 
                    l_ifoTradedProducts.put(l_strCurKey, l_ifoTradedProductParams);
                }
            }
        }

        //HashMapを配列化して先物OP取引銘柄Paramsの配列を返却する
        IfoTradedProductParams[] l_returns = new IfoTradedProductParams[l_ifoTradedProducts.size()];
        l_returns = (IfoTradedProductParams[])l_ifoTradedProducts.values().toArray(l_returns);
        return l_returns;
    }

    /**
     * (create先物OP銘柄情報一覧)<BR>
     * 
     * 先物OP銘柄情報の一覧を作成する。<BR>
     * ※保有/注文している先物OP銘柄が存在しない場合は、nullを返却する。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create先物OP銘柄情報一覧」参照。<BR>
     * 
     * @@param l_ifoTradedProductParams - (取引銘柄Params一覧)<BR>
     * 取引銘柄Paramsの配列。<BR>
     * ※先物OP建玉を保有していない、かつ、当日新規建注文も存在しない場合は、null。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoProduct[]
     */
    private WEB3IfoProduct[] createIfoProductList(IfoTradedProductParams[] l_ifoTradedProductParams)
        throws WEB3SystemLayerException
    {
        //引数.取引銘柄Params一覧==nullの時
        if (l_ifoTradedProductParams == null)
        {
            //nullを返却する
            return null;
        }

        //先物OP銘柄情報を格納するArrayListを生成する
        ArrayList l_ifoProducts = new ArrayList();
        //リアル時価証拠金計算実施フラグを取得する
        boolean l_flgRealPrice = this.ifoDepositCalcCondition.isRealPriceIfoDepositCalc();

        //取引カレンダコンテキストををスレッドローカルから取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //受付時間区分を取得する
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //銘柄コードを取得する
        String l_strProductCode = l_clendarContext.getProductCode();

        //(カーソル)取引銘柄Params
        IfoTradedProductParams l_curIfoTradedProductParams = null;
        //(カーソル)銘柄Params
        IfoProductParams l_curIfoProductParams = null;

        //引数.取引銘柄Params一覧の要素数回LOOP処理
        for (int index = 0; index < l_ifoTradedProductParams.length; index++)
        {
            //(カーソル)取引銘柄Paramsを取得する
            l_curIfoTradedProductParams = l_ifoTradedProductParams[index];
            //(カーソル)銘柄Paramsを取得する
            l_curIfoProductParams =
                WEB3IfoDepositPersistentDataManager.getProductParams(
                    l_curIfoTradedProductParams.getProductId());

            //取引カレンダコンテキストに原資産銘柄コードをセットする
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_curIfoProductParams.getUnderlyingProductCode());
            //取引カレンダコンテキストに受付時間区分をセットする。
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
                
            //立会時間帯フラグを取得する。
            boolean l_blnSession = WEB3GentradeTradingTimeManagement.isSessionTimeZone();           
            //夕場時間帯フラグを取得する。
            boolean l_blnEvening = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();


            //市場開局時間帯(日中)の時
            if (l_blnSession && !l_blnEvening)
            {
                //先物OP銘柄情報を生成する。
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOnSessionIfoProduct(
                        l_curIfoProductParams,
                        l_curIfoTradedProductParams,
                        l_flgRealPrice);
                //生成した先物OP銘柄情報をArrayListに追加する
                l_ifoProducts.add(l_ifoProduct);
            }
             //市場開局時間帯(夕場)の時
            else if (l_blnSession && l_blnEvening)
            {

                //翌営業日を取得
                Date l_datOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();              
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderDate .getTime()));
                l_datOrderDate = WEB3DateUtility.toDay(l_gentradeBizDate.roll(1));

                //取引銘柄一時Paramsを取得する
                IfoTradedProductUpdqParams l_tradedProductUpdqParams =
                    WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                        l_curIfoProductParams.getProductId(),
                        l_curIfoTradedProductParams.getMarketId(),
                        l_curIfoProductParams.getInstitutionCode(),
                        l_datOrderDate);
               
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOnEveningSessionIfoProduct(
                        l_curIfoProductParams,
                        l_tradedProductUpdqParams,
                        l_flgRealPrice,
                        this.ifoDepositCalcCondition.isIfoDepositMailFlag(),
                        l_curIfoTradedProductParams);
                //生成した先物OP銘柄情報をArrayListに追加する
                l_ifoProducts.add(l_ifoProduct);
            }
             //市場開局時間帯以外
            else
            {
                //発注日(基準日を取得する)
                Date l_datOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();              
                //夕場時間帯の場合は営業日計算.calc営業日で翌営業日を取得
                if (l_blnEvening){
                    WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderDate .getTime()));
                    //翌営業日
                    l_datOrderDate = WEB3DateUtility.toDay(l_gentradeBizDate.roll(1));
                }                                      
                //先物OP銘柄情報を生成する
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOffSessionIfoProduct(
                        l_curIfoProductParams,
                        l_curIfoTradedProductParams,
                        l_datOrderDate);

                //生成した先物OP銘柄情報をArrayListに追加する
                l_ifoProducts.add(l_ifoProduct);
            }
        }

        //取引カレンダコンテキストを初期状態に戻す
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);

        //先物OP銘柄情報のArrayListを配列に変換して返却する
        WEB3IfoProduct[] l_returns = new WEB3IfoProduct[l_ifoProducts.size()];
        l_returns = (WEB3IfoProduct[])l_ifoProducts.toArray(l_returns);
        return l_returns;
    }

    /**
     * (create銘柄建単価別先物OP建玉集計一覧)<BR>
     * 
     * 銘柄建単価別先物OP建玉集計の一覧の作成、プロパティの設定を行い、
     * this.銘柄建単価別先物OP建玉集計一覧にセットする。<BR>
     * ※保有している先物OP銘柄が存在しない場合はnullをセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create銘柄建単価別先物OP建玉集計一覧」参照。<BR>
     * 
     * @@param l_ifoContractList - (先物OP保有建玉情報一覧)<BR>
     * 先物OP保有建玉情報の一覧。<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoProductList - (先物OP銘柄情報一覧)<BR>
     * 先物OP銘柄情報の一覧。<BR>
     * 保有/注文している先物OP銘柄が存在しない場合はnulｌ。<BR>
     */
    private void createIfoSummaryContractPerProductContractPriceList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //先物OP保有建玉情報が存在しない場合
        if (l_ifoContractList == null)
        {
            //this.銘柄建単価別先物OP建玉集計一覧にnullを代入
            this.setIfoSummaryContractPerProductContractPriceList(null);
            //処理終了
            return;
        }

        //銘柄建単価別先物OP建玉集計を格納するHashMapを生成する
        HashMap l_ifoSummarys = new HashMap();
        //証拠金計算基準日を取得する        
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //銘柄コードを取得して保存する
        String l_strProductCode = l_clendarContext.getProductCode();

        //(カーソル)先物OP保有建玉情報
        WEB3IfoContract l_curIfoContract = null;

        //先物OP保有建玉情報一覧の要素数回LOOP処理
        for (int index = 0; index < l_ifoContractList.length; index++)
        {
            //(カーソル)先物OP保有建玉情報を取得する
            l_curIfoContract = l_ifoContractList[index];

            //銘柄Paramsを取得する。 
            IfoProductParams l_ifoProductParams =
                WEB3IfoDepositPersistentDataManager.getProductParams(
                    l_curIfoContract.productId);

            //取引カレンダコンテキストに証拠金計算データソースアクセス管理.get銘柄Paramsの戻り値.原資産銘柄コードをセットする
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_ifoProductParams.underlying_product_code);
                 
            //HashMapのKey(銘柄ID+建単価)を作成する。
            String l_strKey =
                Long.toString(l_curIfoContract.productId)
                    + Double.toString(l_curIfoContract.contractPrice);

            //HashMapに既にKeyが存在する時
            if (l_ifoSummarys.containsKey(l_strKey) == true)
            {
                //HashMapより銘柄建単価別先物OP建玉集計オブジェクトを取得する。
                WEB3IfoSummaryContractPerProductContractPrice l_ifoSummary =
                    (WEB3IfoSummaryContractPerProductContractPrice)l_ifoSummarys.get(l_strKey);

                //建数量を加算する
                l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);                   

                //建数量＜証拠金不足仮確定＞を追加する
                l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                //当日建の場合、当日建数量を加算する
                if(l_curIfoContract.isTodayContract(this.ifoDepositCalcCondition))
                {
                    l_ifoSummary.addTodayQuantity(l_curIfoContract.isBuy(), l_curIfoContract.quantity);
                }

                //HashMapに銘柄建単価別先物OP建玉集計オブジェクトを格納する
                l_ifoSummarys.put(l_strKey, l_ifoSummary);
            }
            //HashMapにKeyが存在しない時
            else
            {
                //指定銘柄の先物OP銘柄情報一覧を取得する
                WEB3IfoProduct[] l_specifiedIfoProductList =
                    this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);

                //銘柄建単価別先物OP建玉集計を生成する
                WEB3IfoSummaryContractPerProductContractPrice l_ifoSummary =
                    WEB3IfoSummaryContractPerProductContractPrice.create();

                //銘柄IDをセットする
                l_ifoSummary.setProductId(l_curIfoContract.productId);
                //銘柄コードをセットする
                l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                //先物オプション商品区分をセットする
                l_ifoSummary.setIfoDerivativeType(
                    l_specifiedIfoProductList[0].ifoDerivativeType);
                //建単価をセットする
                l_ifoSummary.setContractPrice(l_curIfoContract.contractPrice);
                //時価をセットする
                l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                //当日清算値 セットする
                l_ifoSummary.setCurrentBizDateLiquidationPrice(l_specifiedIfoProductList[0].currentBizDateLiquidationPrice);
                //指数乗数をセットする
                l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);

                //建数量を加算する
                l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);                   

                //建数量＜証拠金不足仮確定＞を追加する
                l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);
                   
                //当日建の場合、当日建数量を加算する
                if(l_curIfoContract.isTodayContract(this.ifoDepositCalcCondition))
                {
                    l_ifoSummary.addTodayQuantity(l_curIfoContract.isBuy(), l_curIfoContract.quantity);
                }
                    
                //評価損益計算区分をセットする
                l_ifoSummary.setProfitLossCalcType(
                    this.ifoDepositCalcCondition.getCalcConditionPerBranch(
                        WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC));

                //HashMapに銘柄建単価別先物OP建玉集計オブジェクトを格納する
                l_ifoSummarys.put(l_strKey, l_ifoSummary);
            }

        }
        
        //取引カレンダコンテキストを初期状態に戻す
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //決済済建玉のみだった場合
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.銘柄建単価別先物OP建玉集計一覧にnullを代入
            this.setIfoSummaryContractPerProductContractPriceList(null);
        }
        //それ以外
        else
        {

            //HashMapを配列に変換する
            WEB3IfoSummaryContractPerProductContractPrice[] l_list =
                new WEB3IfoSummaryContractPerProductContractPrice[l_ifoSummarys.size()];
            l_list =
                (WEB3IfoSummaryContractPerProductContractPrice[])l_ifoSummarys.values().toArray(
                    l_list);

            //this.銘柄建単価別先物OP建玉集計一覧に、配列を代入する
            this.setIfoSummaryContractPerProductContractPriceList(l_list);
        }
    }

    /**
     * (create銘柄別先物OP建玉集計一覧)<BR>
     * 
     * 銘柄別先物OP建玉集計の一覧の作成、プロパティの設定を行い、<BR>
     * this.銘柄別先物OP建玉集計一覧にセットする。<BR>
     * ※保有/注文している先物OP銘柄が存在しない場合はnullをセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create銘柄別先物OP建玉集計一覧」参照。<BR>
     * 
     * @@param l_ifoContractList - (先物OP保有建玉情報一覧)<BR>
     * 先物OP保有建玉情報の一覧。<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (先物OP当日新規建注文情報一覧)<BR>
     * 該当する先物OP当日新規建注文が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoProductList - (先物OP銘柄情報の一覧。)<BR>
     * 保有/注文している先物OP銘柄が存在しない場合はnulｌ。<BR>
     */
    private void createIfoSummaryContractPerProductList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //保有/注文先物OP銘柄が存在しない場合
        if (l_ifoProductList == null)
        {
            //this.銘柄別先物OP建玉集計一覧にnullをセットする
            this.setIfoSummaryContractPerProductList(null);
            return;
        }

        //銘柄別先物OP建玉集計を格納するHashMapを生成する
        HashMap l_ifoSummarys = new HashMap();

        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //銘柄コードを取得して保存する
        String l_strProductCode = l_clendarContext.getProductCode();

        //先物OP保有建玉情報が存在する時
        if (l_ifoContractList != null)
        {
            //(カーソル)先物OP保有建玉情報
            WEB3IfoContract l_curIfoContract = null;

            //先物OP保有建玉情報の要素数回LOOP処理
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(カーソル)先物OP保有建玉情報を取得する
                l_curIfoContract = l_ifoContractList[index];

                //HashMapのKey(＝銘柄ID)
                String l_strKey = Long.toString(l_curIfoContract.productId);

                //HashMapに既にKeyが存在する時
                if (l_ifoSummarys.containsKey(l_strKey) == true)
                {
                    //HashMapより銘柄別先物OP建玉集計を取り出す
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        (WEB3IfoSummaryContractPerProduct)l_ifoSummarys.get(l_strKey);

                    //指定銘柄の先物OP銘柄情報一覧を取得する
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_ifoSummary.getProductId(), l_ifoProductList);
                         
                    //取引カレンダコンテキストに先物OP銘柄情報.原資産銘柄コードをセットする
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_specifiedIfoProductList[0].getUnderlyingProductCode());
                             
                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle)
                    {
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }

                    //建数量を追加する
                    l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);
                    //建数量＜証拠金不足仮確定＞を追加する
                    l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                    //HasMapに銘柄別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                //HashMapにKeyが存在しない時
                else
                {
                    //指定銘柄の先物OP銘柄情報一覧を取得する
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);

                    //銘柄別先物OP建玉集計を生成する
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        WEB3IfoSummaryContractPerProduct.create();
                    //銘柄IDをセット
                    l_ifoSummary.setProductId(l_curIfoContract.productId);
                    //銘柄コードをセット
                    l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                    //先物OP商品区分をセット
                    l_ifoSummary.setIfoDerivativeType(
                        l_specifiedIfoProductList[0].ifoDerivativeType);
                    //時価をセット
                    l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                    //当日清算値 セットする
                    l_ifoSummary.setCurrentBizDateLiquidationPrice(l_specifiedIfoProductList[0].currentBizDateLiquidationPrice);						
                    //指数乗数をセット
                    l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);


                    //取引カレンダコンテキストに先物OP銘柄情報.原資産銘柄コードをセットする
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_specifiedIfoProductList[0].getUnderlyingProductCode());
                             
                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                    
                    //建数量を追加する
                    l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);

                    //建数量＜証拠金不足仮確定＞を追加する
                    l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                    //HasMapに銘柄別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                
            }
        }

        //取引カレンダコンテキストを初期状態に戻す
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //営業日[T+0]
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //営業日[T+1]
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();

        //先物OP当日新規建注文情報が存在する時
        if (l_ifoTodayOpenOrderList != null)
        {
            //(カーソル)先物OP当日新規建注文情報
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;

            //先物OP当日新規建注文情報の要素数回LOOP処理
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //先物OP当日新規建注文情報を取得する
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];

                //HashMapのKey(＝銘柄ID)
                String l_strKey = Long.toString(l_curIfoTodayOpenOrder.productId);

                //HashMapにKeyが存在する時                
                if (l_ifoSummarys.containsKey(l_strKey) == true)
                {
                    //HashMapから銘柄別先物OP建玉集計を取得する
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        (WEB3IfoSummaryContractPerProduct)l_ifoSummarys.get(l_strKey);

                    //注文数量を追加する
                    l_ifoSummary.addOrderQuantity(
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMapに銘柄別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                //HashMapにKeyが存在しない時
                else
                {
                    //指定銘柄の先物OP銘柄情報一覧を取得する
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_curIfoTodayOpenOrder.productId, l_ifoProductList);

                    //銘柄別先物OP建玉集計を生成する
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        WEB3IfoSummaryContractPerProduct.create();

                    //銘柄IDをセット
                    l_ifoSummary.setProductId(l_curIfoTodayOpenOrder.productId);
                    //銘柄コードをセット
                    l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                    //先物OP商品区分をセット
                    l_ifoSummary.setIfoDerivativeType(
                        l_specifiedIfoProductList[0].ifoDerivativeType);
                    //時価をセット
                    l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                    //指数乗数をセット
                    l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);
                    //注文数量を追加する
                    l_ifoSummary.addOrderQuantity(
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMapに銘柄別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
            }
        }

        //HashMapが空の時
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.銘柄別先物OP建玉集計一覧にnullをセット
            this.setIfoSummaryContractPerProductList(null);
        }
        //以外の時
        else
        {
            //HashMapを配列に変換する
            WEB3IfoSummaryContractPerProduct[] l_list =
                new WEB3IfoSummaryContractPerProduct[l_ifoSummarys.size()];
            l_list = (WEB3IfoSummaryContractPerProduct[])l_ifoSummarys.values().toArray(l_list);

            //this.銘柄別先物OP建玉集計一覧に配列をセットする
            this.setIfoSummaryContractPerProductList(l_list);
        }
    }

    /**
     * (create指数別先物OP建玉集計一覧)<BR>
     * 
     * 指数別OP建玉集計の一覧の作成、プロパティの設定を行い、<BR>
     * this.指数別先物OP建玉集計一覧にセットする。<BR>
     * ※保有/注文している先物OP銘柄が存在しない場合はnullをセットする。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算）create指数別先物OP建玉集計一覧」参照。<BR>
     * 
     * @@param l_ifoContractList - (先物OP保有建玉情報一覧)<BR>
     * 先物OP保有建玉情報の一覧。<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (先物OP当日新規建注文情報一覧)<BR>
     * 先物OP当日新規建注文情報の一覧。<BR>
     * 該当する先物OP当日新規建注文が存在しない場合はnull。<BR>
     * 
     * @@param l_ifoProductList - (先物OP銘柄情報一覧)<BR>
     * @@throws WEB3BaseException<BR>
     * 
     * 先物OP銘柄情報の一覧。<BR>
     * 保有/注文している先物OP銘柄が存在しない場合はnulｌ。<BR>
     */
    private void createIfoSummaryContractPerIndexList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList,
        WEB3IfoProduct[] l_ifoProductList) throws WEB3BaseException
    {
        //保有/注文先物OP銘柄が存在しない時
        if (l_ifoProductList == null)
        {
            //this.指数別先物OP建玉集計一覧にnullをセットする
            this.setIfoSummaryContractPerIndexList(null);
            //処理終了
            return;
        }

        //銘柄別先物OP建玉集計を格納するHashMapを生成する
        HashMap l_ifoSummarys = new HashMap();

        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //銘柄コードを取得して保存する
        String l_strProductCode = l_clendarContext.getProductCode();

        //先物OP保有建玉情報が存在する時
        if (l_ifoContractList != null)
        {
            //(カーソル)先物OP保有建玉情報
            WEB3IfoContract l_curIfoContract = null;
            //(カーソル)指定銘柄の先物OP銘柄情報一覧
            WEB3IfoProduct[] l_curSpecifiedIfoProductList = null;
            //(カーソル)原資産銘柄コード(=Key)
            String l_strCurUnderlyingProductCode = null;

            //先物OP保有建玉情報の要素数回LOOP処理
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(カーソル)先物OP保有建玉情報を取得する
                l_curIfoContract = l_ifoContractList[index];

                //(カーソル)指定銘柄の先物OP銘柄情報一覧を取得する
                l_curSpecifiedIfoProductList =
                    this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);
                    
                // 該当銘柄が、ポジションとして有効であるか判定する。
                // 有効でない場合は、contiuneする。
                if ( !l_curSpecifiedIfoProductList[0].isPosition(ifoDepositCalcCondition) )
                {
                    continue;
                }
    
                    
                //(カーソル)原資産銘柄コード(=Key)を取得する。
                l_strCurUnderlyingProductCode =
                    l_curSpecifiedIfoProductList[0].underlyingProductCode;

                //HashMapにKeyが存在する時
                if (l_ifoSummarys.containsKey(l_strCurUnderlyingProductCode) == true)
                {
                    //HashMapより指数別先物OP建玉集計を取得する
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        (WEB3IfoSummaryContractPerIndex)l_ifoSummarys.get(
                            l_strCurUnderlyingProductCode);
                                
                    //取引カレンダコンテキストに原資産銘柄コードをセットする
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_strCurUnderlyingProductCode);

                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                             
                    //建数量を追加する
                    l_ifoSummary.addContractQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity
                        );
                    //建数量＜証拠金不足仮確定＞を追加する
                    l_ifoSummary.addContractQuantityTemp(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp
                        );

                    //HashMapに指数別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
                //HashMapにKeyが存在しない時
                else
                {
                    //指数別先物OP建玉集計を生成する
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        WEB3IfoSummaryContractPerIndex.create();

                    //証拠金計算条件より規定証拠金を取得する
                    double l_dblPerUnit =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnit(
                            l_strCurUnderlyingProductCode);
                    //証拠金計算条件より規定証拠金レッドを取得する
                    double l_dblPerUnitRed =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnitRed(
                            l_strCurUnderlyingProductCode);

                    //原資産銘柄コードをセット
                    l_ifoSummary.setTargetProductCode(l_strCurUnderlyingProductCode);
                    //規定証拠金をセット
                    l_ifoSummary.setIfoDepositPerUnit(l_dblPerUnit);
                    //規定証拠金レッドをセット
                    l_ifoSummary.setIfoDepositPerUnitRed(l_dblPerUnitRed);
                        
                    //規定証拠金＜証拠金不足仮確定＞：　@証拠金計算条件.get規定証拠金＜証拠金不足仮確定＞( )をセット
                    double l_dblDeposit = 
                         this.ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strCurUnderlyingProductCode);
                    l_ifoSummary.setIfoDepositPerUnitTemp(l_dblDeposit);
                        
                    //取引カレンダコンテキストに原資産銘柄コードをセットする
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_strCurUnderlyingProductCode);

                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                        
                    //建数量を追加する
                    l_ifoSummary.addContractQuantity(
                    l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity
                        );
                    //建数量＜証拠金不足仮確定＞を追加する
                    l_ifoSummary.addContractQuantityTemp(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp
                        );

                    //HashMapに指数別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
            }
        }

        //取引カレンダコンテキストを初期状態に戻す
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //営業日[T+0]
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //営業日[T+1]
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();

        ////先物OP当日新規建注文情報が存在する時
        if (l_ifoTodayOpenOrderList != null)
        {
            //(カーソル)先物OP保有建玉情報を取得する
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;
            //(カーソル)指定銘柄の先物OP銘柄情報一覧
            WEB3IfoProduct[] l_curSpecifiedIfoProductList = null;
            //(カーソル)原資産銘柄コード(=Key)
            String l_strCurUnderlyingProductCode = null;

            //先物OP当日新規建注文情報の要素数回LOOP処理
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //(カーソル)先物OP保有建玉情報を取得する
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];
                //(カーソル)指定銘柄の先物OP銘柄情報一覧を取得する
                l_curSpecifiedIfoProductList =
                    this.getIfoProductList(l_curIfoTodayOpenOrder.productId, l_ifoProductList);
                //(カーソル)原資産銘柄コード(=Key)を取得する。
                l_strCurUnderlyingProductCode =
                    l_curSpecifiedIfoProductList[0].underlyingProductCode;

                //HashMapにKeyが追加済みの時
                if (l_ifoSummarys.containsKey(l_strCurUnderlyingProductCode) == true)
                {
                    //HashMapより指数別先物OP建玉集計を取得する
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        (WEB3IfoSummaryContractPerIndex)l_ifoSummarys.get(
                            l_strCurUnderlyingProductCode);

                    //注文数量を加算する
                    l_ifoSummary.addOrderQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMapに指数別先物OP建玉集計を追加する
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
                //HashMapにKeyが追加未済みの時
                else
                {
                    //指数別先物OP建玉集計を生成する
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        WEB3IfoSummaryContractPerIndex.create();

                    //証拠金計算条件より規定証拠金を取得する
                    double l_dblPerUnit =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnit(
                            l_strCurUnderlyingProductCode);
                    //証拠金計算条件より規定証拠金レッドを取得する
                    double l_dblPerUnitRed =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnitRed(
                            l_strCurUnderlyingProductCode);

                    //原資産銘柄コードをセット
                    l_ifoSummary.setTargetProductCode(l_strCurUnderlyingProductCode);
                    //規定証拠金をセット
                    l_ifoSummary.setIfoDepositPerUnit(l_dblPerUnit);
                    //規定証拠金レッドをセット
                    l_ifoSummary.setIfoDepositPerUnitRed(l_dblPerUnitRed);

                    //規定証拠金＜証拠金不足仮確定＞：　@証拠金計算条件.get規定証拠金＜証拠金不足仮確定＞( )をセット
                    double l_dblDeposit = 
                         this.ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strCurUnderlyingProductCode);
                    l_ifoSummary.setIfoDepositPerUnitTemp(l_dblDeposit);

                    //注文数量を加算する
                    l_ifoSummary.addOrderQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMapに指数別先物OP建玉集計を格納する
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
            }
        }

        //HashMapが空の時
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.指数別先物OP建玉集計一覧にnullをセット
            this.setIfoSummaryContractPerIndexList(null);
        }
        //以外の時
        else
        {
            //HashMapを配列に変換する
            WEB3IfoSummaryContractPerIndex[] l_list =
                new WEB3IfoSummaryContractPerIndex[l_ifoSummarys.size()];
            l_list = (WEB3IfoSummaryContractPerIndex[])l_ifoSummarys.values().toArray(l_list);

            //this.指数別先物OP建玉集計一覧に配列をセットする
            this.setIfoSummaryContractPerIndexList(l_list);
        }
    }

    /**
     * (set補助口座)<BR>
     * 
     * 引数.補助口座をthis.補助口座にセットする。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     */
    private void setSubAccount(WEB3GentradeSubAccount l_subAccount)
    {
        this.subAccount = l_subAccount;
    }

    /**
     * (set証拠金計算条件)<BR>
     * 
     * 引数.証拠金計算条件をthis.証拠金計算条件にセットする。<BR>
     * @@param l_ifoDepositCalcCondition - (証拠金計算条件)<BR>
     * 
     * 証拠金計算条件オブジェクト。<BR>
     * @@roseuid 41254D420282
     */
    private void setIfoDepositCalcCondition(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;
    }

    /**
     * (set先物OP顧客移動明細)<BR>
     * 
     * 引数.先物OP顧客移動明細をthis.先物OP顧客移動明細にセットする。<BR>
     * 
     * @@param l_ifoCustomerTransfer - (先物OP顧客移動明細)<BR>
     */
    private void setIfoCustomerTransfer(WEB3IfoCustomerTransfer l_ifoCustomerTransfer)
    {
        this.ifoCustomerTransfer = l_ifoCustomerTransfer;
    }

    /**
     * (set銘柄建単価別先物OP建玉集計一覧)<BR>
     * 
     * 引数.銘柄建単価別先物OP建玉集計一覧をthis.銘柄建単価別先物OP建玉集計一覧にセットする。<BR>
     * 
     * @@param l_ifoSummaryontractPerProductContractPriceList - (銘柄建単価別先物OP建玉集計一覧)<BR>
     */
    private void setIfoSummaryContractPerProductContractPriceList(WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList)
    {
        this.ifoSummaryContractPerProductContractPriceList =
            l_ifoSummaryontractPerProductContractPriceList;
    }

    /**
     * (set銘柄別先物OP建玉集計一覧)<BR>
     * 
     * 引数.銘柄別先物OP建玉集計一覧をthis.銘柄別先物OP建玉集計一覧にセットする。<BR>
     * 
     * @@param l_ifoSummaryContractPerProductList - (銘柄別先物OP建玉集計一覧)<BR>
     */
    private void setIfoSummaryContractPerProductList(WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList)
    {
        this.ifoSummaryContractPerProductList = l_ifoSummaryContractPerProductList;
    }

    /**
     * (set指数別先物OP建玉集計一覧)<BR>
     * 
     * 引数.指数別先物OP建玉集計一覧をthis.指数別先物OP建玉集計一覧にセットする。<BR>
     * 
     * @@param l_ifoSummaryContractPerIndexList - (指数別先物OP建玉集計一覧)<BR>
     */
    private void setIfoSummaryContractPerIndexList(WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexList)
    {
        this.ifoSummaryContractPerIndexList = l_ifoSummaryContractPerIndexList;
    }

    /**
     * (get先物OP銘柄情報一覧)<BR>
     * 
     * 銘柄IDに対応する先物OP銘柄情報の一覧を返却する。<BR>
     * ※銘柄IDに対応する先物OP銘柄情報が存在しない場合は、nullを返却する。<BR>
     * 
     * 引数.先物OP銘柄情報一覧要素ごとのLoop処理を行い、<BR>
     * 先物OP銘柄情報一覧.銘柄ID == 引数.銘柄IDとなる先物OP銘柄情報オブジェクトの配列を返却する。<BR>
     * 
     * @@param l_lngProductId - 銘柄ID
     * @@param l_ifoProductList - 先物OP銘柄情報の一覧
     * @@return webbroker3.ifodeposit.WEB3IfoProduct[]
     */
    private WEB3IfoProduct[] getIfoProductList(
        long l_lngProductId,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //先物OP銘柄情報が存在しない時
        if (l_ifoProductList == null)
        {
            //nullを返却する
            return null;
        }

        //先物OP銘柄情報を格納するArrayListを生成する
        ArrayList l_ifoProducts = new ArrayList();

        //(カーソル)先物OP銘柄情報
        WEB3IfoProduct l_curIfoProduct = null;

        //先物OP銘柄情報の要素数回LOOP処理
        for (int index = 0; index < l_ifoProductList.length; index++)
        {
            //(カーソル)先物OP銘柄情報を取得する。
            l_curIfoProduct = l_ifoProductList[index];

            //先物OP銘柄情報.銘柄ID == 引数.銘柄IDの時
            if (l_curIfoProduct.productId == l_lngProductId)
            {
                //先物OP銘柄情報をArrayListに格納する
                l_ifoProducts.add(l_curIfoProduct);
            }
        }

        //ArrayListの要素が空の時
        if (l_ifoProducts.isEmpty() == true)
        {
            //nullを返却する
            return null;
        }
        //ArrayListの要素が空でない時
        else
        {
            //ArrayListを配列に変換する
            WEB3IfoProduct[] l_returns = new WEB3IfoProduct[l_ifoProducts.size()];
            l_returns = (WEB3IfoProduct[])l_ifoProducts.toArray(l_returns);

            //配列を返却する
            return l_returns;
        }
    }

    /**
     * (get当日受渡代金)<BR>
     * 
     * 注文に対応する当日の受渡代金を返却する。
     * (注文に対応する当日の受渡代金が存在しない場合は、0)
     * 
     * １）　@戻り値となる受渡代金を格納する変数を作成
     * 　@　@　@double 受渡代金 = 0
     * 
     * ２）　@引数.先物OP保有建玉情報一覧要素ごとのLoop処理
     * 　@　@　@※引数.先物OP保有建玉情報一覧がnullの場合は、0を返却する。
     * 
     * 　@　@　@２－１）　@先物OP保有建玉情報.get受渡代金( )により受渡代金を取得する。
     * 
     * 　@　@　@[引数の設定]
     * 　@　@　@　@・注文単位ID：　@引数.注文単位ID
     * 　@　@　@　@・建日：　@引数.建日
     * 
     * 　@　@　@２－２）　@受渡代金 = 受渡代金 + 先物OP保有建玉情報.get受渡代金( )の戻り値
     * 
     * ３）　@受渡代金を返却する。　@
     * 
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * @@param l_ifoContractList - (先物OP保有建玉情報一覧)<BR>
     * 先物OP保有建玉情報の一覧。<BR>
     * 該当する先物OP建玉が存在しない場合はnull。<BR>
     * 
     * @@param l_datOpenDate - (建日)<BR>
     * 
     * @@return double
     */
    private double getCurrentBizDateNetAmount(
        long l_lngOrderUnitId,
        WEB3IfoContract[] l_ifoContractList,
        Date l_datOpenDate)
    {
        //先物OP保有建玉情報が存在しない時
        if (l_ifoContractList == null)
        {
            //受渡代金(=0)を返却する
            return 0;
        }

        //受渡代金
        double l_dblNetAmount = 0;

        //(カーソル)先物OP保有建玉情報
        WEB3IfoContract l_curIfoContract = null;

        //先物OP保有建玉情報の要素数回LOOP処理
        for (int index = 0; index < l_ifoContractList.length; index++)
        {
            //(カーソル)先物OP保有建玉情報を取得
            l_curIfoContract = l_ifoContractList[index];

            //受渡代金 = 受渡代金 + 先物OP保有建玉情報.get受渡代金()の戻り値
            l_dblNetAmount =
                l_dblNetAmount + l_curIfoContract.getNetAmount(l_lngOrderUnitId, l_datOpenDate);
        }

        //受渡代金を返却する。
        return l_dblNetAmount;
    }

    /**
     * (get補助口座)<BR>
     * 
     * this.補助口座を返却する。<BR>
     * 
     * @@return WEB3GentradeSubAccount
     */
    public WEB3GentradeSubAccount getSubAccount()
    {
        return this.subAccount;
    }

    /**
     * (get証拠金計算条件)<BR>
     * 
     * this.証拠金計算条件を返却する。<BR>
     * 
     * @@return WEB3IfoDepositCalcCondition
     */
    public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
    {
        return this.ifoDepositCalcCondition;
    }

    /**
     * (get先物OP顧客移動明細)<BR>
     * 
     * this.先物OP顧客移動明細を返却する。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoCustomerTransfer
     */
    public WEB3IfoCustomerTransfer getIfoCustomerTransfer()
    {
        return this.ifoCustomerTransfer;
    }

    /**
     * (get銘柄建単価別先物OP建玉集計一覧)<BR>
     * 
     * this.銘柄建単価別先物OP建玉集計一覧を返却する。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProductContractPrice[]
     */
    public WEB3IfoSummaryContractPerProductContractPrice[] getIfoSummaryContractPerProductContractPriceList()
    {
        return this.ifoSummaryContractPerProductContractPriceList;
    }

    /**
     * (get銘柄別先物OP建玉集計一覧)<BR>
     * 
     * this.銘柄別先物OP建玉集計一覧を返却する。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProduct[]
     */
    public WEB3IfoSummaryContractPerProduct[] getIfoSummaryContractPerProductList()
    {
        return this.ifoSummaryContractPerProductList;
    }

    /**
     * (get指数別先物OP建玉集計一覧)<BR>
     * 
     * this.指数別先物OP建玉集計一覧を返却する。<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex[]
     */
    public WEB3IfoSummaryContractPerIndex[] getIfoSummaryContractPerIndexList()
    {
        return this.ifoSummaryContractPerIndexList;
    }

    /**
     * (getSPAN証拠金)<BR>
     * 
     * 引数で指定された指定日(=n)の、「SPAN証拠金」を返却する。<BR>
     * 
     * １）　@SPAN使用不可の場合(this.get証拠金計算条件().isSPAN使用可能() == false)、-1を返却する。<BR>
     * 
     * ２）　@指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却する。<BR>
     * 
     * ３）　@建玉/注文が存在しない場合(this..銘柄別先物OP建玉集計一覧 == null)、0を返却する。<BR>
     * 
     * ４）　@PC-SPAN使用のために必要な建玉集計一覧を格納するArrayListを作成する。<BR>
     * 
     * ５）　@this.銘柄別先物OP建玉集計一覧要素ごとのLoop処理。<BR>
     * 
     * 　@　@　@５－１）　@建玉集計Specを生成する。<BR>
     * 
     * 　@　@　@　@[コンストラクタの設定]<BR>
     * 　@　@　@　@　@・銘柄コード：　@銘柄別先物OP建玉集計一覧.get銘柄コード()<BR>
     * 　@　@　@　@　@・買建数量：　@銘柄別先物OP建玉集計一覧.calc買建数量(引数.指定日)<BR>
     * 　@　@　@　@　@・売建数量：　@銘柄別先物OP建玉集計一覧.calc売建数量(引数.指定日)<BR>
     * 
     * 　@　@　@５－２）　@生成した建玉集計SpecをArrayListに追加する。<BR>
     * 
     * ６）　@ArrayListを配列化し、建玉集計Specの配列を取得する。<BR>
     * 
     * ７）　@SPAN証拠金を取得する。<BR>
     * 
     * 　@　@　@SPAN証拠金 = SPAN接続サービスImpl.getSPAN証拠金()<BR>
     * 　@　@
     * 　@　@　@[引数の設定]<BR>
     *          ・補助口座：　@this.補助口座<BR>
     *          ・建玉集計一覧：　@取得した建玉集計Specの配列<BR>
     * 
     * ８）　@取得したSPAN証拠金を返却する。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double getSPANIfoDeposit(int l_intReservedDate)
    {
        //SPAN使用不可の場合(this.get証拠金計算条件().isSPAN使用可能() == false)
        if (this.ifoDepositCalcCondition.isSPANUsable() == false)
        {
            //-1を返却する。
            return -1;
        }

        //指定日が0、1、2でない時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //SPAN証拠金(=0)を返却する
            return 0;
        }
        //建玉が存在しない場合
        if (this.ifoSummaryContractPerProductList == null)
        {
            //SPAN証拠金(=0)を返却する
            return 0;
        }

        //SPAN証拠金
        double l_dblSPAN = 0;

        //建玉集計Specを格納するArrayListを生成する
        ArrayList l_contractSpecs = new ArrayList();

        //(カーソル)銘柄別先物OP建玉集計を取得する
        WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

        //銘柄別先物OP建玉集計の要素数回LOOP処理
        for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
        {
            //(カーソル)銘柄別先物OP建玉集計を取得する
            l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

            //銘柄コードを取得する
            String l_strCurProductCode = l_curIfoSummary.getProductCode();
            //買建数量を取得する
            double l_dblCurMarginLongNum =
                l_curIfoSummary.calcBuyContractQuantity(l_intReservedDate);
            //売建数量を取得する
            double l_dblCurMarginShortNum =
                l_curIfoSummary.calcSellContractQuantity(l_intReservedDate);

            //建玉集計Specを生成する
            WEB3GentradeTotalContractSpec l_curContractSpec =
                new WEB3GentradeTotalContractSpec(
                    l_strCurProductCode,
                    l_dblCurMarginLongNum,
                    l_dblCurMarginShortNum);

            //生成した建玉集計SpecをArrayListに追加する
            l_contractSpecs.add(l_curContractSpec);
        }

        //ArrayListを配列に変換する
        WEB3GentradeTotalContractSpec[] l_contractSpecList =
            new WEB3GentradeTotalContractSpec[l_contractSpecs.size()];

        l_contractSpecList =
            (WEB3GentradeTotalContractSpec[])l_contractSpecs.toArray(l_contractSpecList);

        // SPAN証拠金サービスオブジェクトを取得する。
        WEB3GentradeSpanConnectService l_service =
            (WEB3GentradeSpanConnectService)Services.getService(
                WEB3GentradeSpanConnectService.class);

        //SPAN証拠金を取得する
        l_dblSPAN = l_service.callSpanIfoDeposit(this.subAccount, l_contractSpecList);

        //取得したSPAN証拠金を返却する
        return l_dblSPAN;
    }

    /**
     * (get当日請求額)<BR>
     * 
     * 「当日請求額」を返却する。<BR>
     * 
     * this.get証拠金計算条件( ).get前日証拠金不足額( )の値を返却する。<BR>
     * 
     * @@return double
     */
    public double getCurrentBizDateDemandAmount()
    {
        return this.ifoDepositCalcCondition.getPreBizDateIfoDepositLackCharge();
    }

    /**
     * (calc証拠金残高)<BR>
     * 
     * 「証拠金残高」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositBalance()
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //証拠金残高(基準日)を取得する。
        double l_dblIfoDepositBalance = this.calcIfoDepositBalance(l_intBaseDate);

        //証拠金残高(基準日)を返却する
        return l_dblIfoDepositBalance;
    }

    /**
     * (calc証拠金残高)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金残高」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositBalance(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //証拠金残高(=0)を返却する
            return 0;
        }

        //証拠金残高(n) = 残高(n) + 振替額(n) + 先物決済損益(n) + オプション受渡代金(n) + オプション買建概算受渡代金(n)
        double l_dblIfoDepositBalance =
            this.ifoCustomerTransfer.getBalance(l_intReservedDate)
                + this.ifoCustomerTransfer.getTransferAmount(l_intReservedDate)
                + this.ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate)
                + this.ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate)
                + this.ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(l_intReservedDate);

        //証拠金残高を返却する
        return l_dblIfoDepositBalance;
    }


	/**
	 * (calc証拠金残高<証拠金不足仮確定> (指定日))<BR>
	 * 
	 * 引数で指定された指定日(=n)の、「証拠金残高<証拠金不足仮確定>(n)」を返却する。<BR>
	 * 
	 * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
	 * 
	 * @@param l_intReservedDate - (指定日)<BR>
	 * 0、1、2のいずれかの値。<BR>
	 * 
	 * @@return double
	 */
	public double calcIfoDepositBalanceTemp(int l_intReservedDate)
	{
		//指定日が0、1、2以外の時
		if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
			&& l_intReservedDate != WEB3IfoReservedDateDef.T_1
			&& l_intReservedDate != WEB3IfoReservedDateDef.T_2)
		{
			//証拠金残高(=0)を返却する
			return 0;
		}

		//証拠金残高(n) <証拠金不足仮確定> = 残高(n) + 振替額[T+0] + 先物決済損益(n) + オプション受渡代金(n)
		double l_dblIfoDepositBalanceTemp =
			this.ifoCustomerTransfer.getBalance(l_intReservedDate)
				+ this.ifoCustomerTransfer.getCurrentBizDateTransferAmount()
				+ this.ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate)
				+ this.ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate);

		//証拠金残高を返却する
		return l_dblIfoDepositBalanceTemp;
	}


    /**
     * (calc受入証拠金残高)<BR>
     * 
     * 「受入証拠金残高」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalance()
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //受入証拠金残高(基準日)を取得する。
        double l_dblReceiptIfoDepositBalance = this.calcReceiptIfoDepositBalance(l_intBaseDate);

        //受入証拠金残高(基準日)を返却する
        return l_dblReceiptIfoDepositBalance;
    }

    /**
     * (calc受入証拠金残高)<BR>
     * 
     * 引数で指定された指定日(=n)の、「受入証拠金残高」を返却する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalance(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //受入証拠金残高(=0)を返却する
            return 0;
        }

        //受入証拠金残高(n)　@=　@証拠金残高(n)　@+　@先物評価損益
        double l_dblReceiptIfoDepositBalance =
            this.calcIfoDepositBalance(l_intReservedDate) + this.calcFuturesAppraisalProfitLoss();

        //受入証拠金残高(n)を返却する
        return l_dblReceiptIfoDepositBalance;
    }

    /**
     * (calc証拠金所要額)<BR>
     * 
     * 「証拠金所要額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmount()
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //証拠金所要額(基準日)を取得する。
        double l_dblIfoDepositRequiredAmount = this.calcIfoDepositRequiredAmount(l_intBaseDate);

        //証拠金所要額(基準日)を返却する
        return l_dblIfoDepositRequiredAmount;
    }

    /**
     * (calc証拠金所要額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金所要額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmount(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //証拠金所要額(=0)を返却する
            return 0;
        }

        /*
         * 証拠金所要額(n)を計算する
         */

        //証拠金所要額(n)
        double l_dblIfoDepositRequiredAmount = 0;

        //SPAN証拠金採用の時
        if (this.ifoDepositCalcCondition.isSPANUsable() == true)
        {
            //証拠金所要額(n)　@=　@(SPAN証拠金(n) － ネットオプション価値総額(n)) × SPAN係数
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));
            BigDecimal l_bdSPANFactor =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactor());

            BigDecimal l_bdIfoDepositRequiredAmount =
                l_bdSPANIfoDeposit.subtract(l_bdNetOption).multiply(l_bdSPANFactor);

            //少数点以下切り上げ処理を行う
            l_bdIfoDepositRequiredAmount =
                l_bdIfoDepositRequiredAmount.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmount = l_bdIfoDepositRequiredAmount.doubleValue();
        }
        //SPAN証拠金不採用の時
        else
        {
            //証拠金所要額(n)　@=　@簡易SPAN証拠金(n) － ネットオプション価値総額(n)
            BigDecimal l_bdSimpleSPANIfoDeposit =
                new BigDecimal(this.calcSimpleSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));

            l_dblIfoDepositRequiredAmount =
                l_bdSimpleSPANIfoDeposit.subtract(l_bdNetOption).doubleValue();
        }

        //計算した証拠金所要額(n)を返却する
        return l_dblIfoDepositRequiredAmount;
    }

    /**
     * (calc証拠金所要額レッド)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金所要額レッド」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmountRed(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //証拠金所要額(=0)を返却する
            return 0;
        }

        /*
         * 証拠金所要額レッド(n)を計算する
         */

        //証拠金所要額レッド(n)
        double l_dblIfoDepositRequiredAmountRed = 0;

        //SPAN証拠金採用の時
        if (this.ifoDepositCalcCondition.isSPANUsable() == true)
        {
            //証拠金所要額レッド(n)　@=　@(SPAN証拠金(n) － ネットオプション価値総額(n)) × SPAN係数レッド
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));
            BigDecimal l_bdSPANFactorRed =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactorRed());

            BigDecimal l_bdIfoDepositRequiredAmountRed =
                l_bdSPANIfoDeposit.subtract(l_bdNetOption).multiply(l_bdSPANFactorRed);

            //少数点以下切り上げ処理を行う
            l_bdIfoDepositRequiredAmountRed =
                l_bdIfoDepositRequiredAmountRed.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmountRed = l_bdIfoDepositRequiredAmountRed.doubleValue();
        }
        //SPAN証拠金不採用の時
        else
        {
            //証拠金所要額レッド(n)　@=　@簡易SPAN証拠金レッド(n) － ネットオプション価値総額(n)
            BigDecimal l_bdSimpleSPANIfoDepositRed =
                new BigDecimal(this.calcSimpleSPANIfoDepositRed(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));

            l_dblIfoDepositRequiredAmountRed =
                l_bdSimpleSPANIfoDepositRed.subtract(l_bdNetOption).doubleValue();
        }

        //計算した証拠金所要額レッド(n)を返却する
        return l_dblIfoDepositRequiredAmountRed;
    }

    /**
     * (calc証拠金余力額)<BR>
     * 
     * 「証拠金余力額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTradingPowerAmount()
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //証拠金余力額(基準日)を取得する。
        double l_dblIfoDepositTradingPowerAmount =
            this.calcIfoDepositTradingPowerAmount(l_intBaseDate);

        //証拠金余力額(基準日)を返却する
        return l_dblIfoDepositTradingPowerAmount;
    }

    /**
     * (calc証拠金余力額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金余力額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 1、2のいずれかの値。<BR>
     * 
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     */
    public double calcIfoDepositTradingPowerAmount(int l_intReservedDate)
    {
        //指定日が1、2 以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //証拠金余力額(=0)を返却する
            return 0;
        }

        /*
         * 証拠金余力額(n)を計算する
         */
        //証拠金余力額(n)
        double l_dblTradingPowerAmount = 0;

        //受入証拠金残高(n)
        double l_dblReceiptIfoDeposit = this.calcReceiptIfoDepositBalance(l_intReservedDate);
        //必要最低保証金
        double l_dblMinIfoDeposit = this.ifoDepositCalcCondition.getMinIfoDeposit();
        //未入金額
        double l_dblNonPayAmount = this.calcNonPayAmount();

        //先物OP新規建規制顧客、または、証拠金不足顧客の場合
        if (this.ifoDepositCalcCondition.isNewOpenTradingPowerAvailable() == false
            || l_dblReceiptIfoDeposit < l_dblMinIfoDeposit
            || l_dblNonPayAmount > 0)
        {
            //証拠金余力額(n)に0を代入する
            l_dblTradingPowerAmount = 0;
        }
        //以外の場合
        else
        {
            //証拠金余力額(n)　@=　@ 受入証拠金残高(n) － 証拠金所要額(n)
            l_dblTradingPowerAmount =
                l_dblReceiptIfoDeposit - this.calcIfoDepositRequiredAmount(l_intReservedDate);
        }

        //計算した証拠金余力額(n)を返却する
        return l_dblTradingPowerAmount;
    }

    /**
     * (calc証拠金振替余力額)<BR>
     * 
     * 「証拠金振替余力額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTransferableAmount()
    {
        //未入金額を取得する
        double l_dblNonPayAmount = this.calcNonPayAmount();

        //未入金額が0より大きい時
        if (l_dblNonPayAmount > 0)
        {
            //0を返却する
            return 0;
        }

        //取引カレンダコンテキストをスレッドローカルから取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //受付時間区分を取得する
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //銘柄コードを取得する
        String l_strProductCode = l_clendarContext.getProductCode();
        
        //受付時間区分を設定する
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MARGIN_TRANSFER);
        WEB3GentradeTradingTimeManagement.resetProductCode(WEB3ProductCodeDef.DEFAULT);
       
        //証拠金振替余力額
        double l_dblTransferableAmount0 = 0;
        double l_dblTransferableAmount1 = 0;
        double l_dblTransferableAmount2 = 0;
        double l_dblTransferableAmount = 0;
        
        try
        { 
            // 発注日
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            // 営業日T+0
            Date l_datBaseDate = this.ifoDepositCalcCondition.getCurrentBizDate();
            
            l_dblTransferableAmount1 =
                this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_1);
            l_dblTransferableAmount2 =
                this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_2);
            
            //振替の発注日がT+0の場合    
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBaseDate) == 0)
            {
                //証拠金振替余力額 = Min(証拠金振替余力額(T+0), 証拠金振替余力額(T+1), (証拠金振替余力額(T+2))
                l_dblTransferableAmount0 =
                    this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_0);
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount0, l_dblTransferableAmount1);
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount, l_dblTransferableAmount2);
            }
            //振替の発注日がT+1の場合
            else
            {
                //証拠金振替余力額 = Min(証拠金振替余力額(T+1), (証拠金振替余力額(T+2))
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount1, l_dblTransferableAmount2);
            }
        }
        catch (WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(
                sle.getErrorInfo(),
                sle.getErrorMethod(),
                sle.getErrorMessage(),
                sle.getException());
        }

        //証拠金振替余力額が負であった時、証拠金振替余力額に0を代入する
        l_dblTransferableAmount = Math.max(0, l_dblTransferableAmount);
        
        //取引カレンダコンテキストを初期状態に戻す
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //計算した証拠金振替余力額を返却する
        return l_dblTransferableAmount;
    }

    /**
     * (calc証拠金振替余力額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金振替余力額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTransferableAmount(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //証拠金振替余力額(n)　@=　@ 受入証拠金残高(n) － 証拠金所要額(n) × 振替余力係数
        BigDecimal l_bdReceiptIfoDepositBalance =
            new BigDecimal(this.calcReceiptIfoDepositBalance(l_intReservedDate));
        BigDecimal l_bdRequiredAmount =
            new BigDecimal(this.calcIfoDepositRequiredAmount(l_intReservedDate));
        BigDecimal l_bdTransferPowerFactor =
            new BigDecimal(Double.toString(this.ifoDepositCalcCondition.getTransferPowerFactor()));

        BigDecimal l_bdTransferableAmount =
            l_bdReceiptIfoDepositBalance.subtract(
                l_bdRequiredAmount.multiply(l_bdTransferPowerFactor));

        //少数点以下切捨て処理を行う
        l_bdTransferableAmount = l_bdTransferableAmount.setScale(0, BigDecimal.ROUND_DOWN);
        double l_dblTransferableAmount = l_bdTransferableAmount.doubleValue();

        //計算した証拠金振替余力額(n)を返却する。
        return l_dblTransferableAmount;
    }

    /**
     * (calc買ポジション可能数量)<BR>
     * 
     * 引数で指定された原資産銘柄コードの「買ポジション可能数量」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@return double
     */
    public double calcPossibleBuyQty(String l_strUnderlyingProductCode)
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //買ポジション可能数量(基準日)を取得する。
        double l_dblBuyQty = this.calcPossibleBuyQty(l_strUnderlyingProductCode, l_intBaseDate);

        //買ポジション可能数量(基準日)を返却する
        return l_dblBuyQty;
    }

    /**
     * (calc買ポジション可能数量)<BR>
     * 
     * 引数で指定された原資産銘柄コードの指定日(=n)の「買ポジション可能数量」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@param l_intReservedDate - (指定)<BR>
     * 1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleBuyQty(String l_strUnderlyingProductCode, int l_intReservedDate)
    {
        //指定日が1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        /*
         * 買ポジション金額、売ポジション金額を計算する。
         */
        //買ポジション金額(n)
        double l_dblBuyAmt = 0;
        //売ポジション金額(n)
        double l_dblSellAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null 、または、証拠金計算.calc証拠金余力額(n) < 0 の場合
        //(建玉/注文(*)が存在しない、または、証拠金余力額がマイナスの場合)
        if (this.ifoSummaryContractPerIndexList == null
            || this.calcIfoDepositTradingPowerAmount(l_intReservedDate) < 0)
        {
            //買ポジション金額(n) =  0
            l_dblBuyAmt = 0;
            //売ポジション金額(n) =  0
            l_dblSellAmt = 0;
        }
        //以外の場合
        else
        {
            //買ポジション金額(n) =  証拠金計算.calc買ポジション金額(n)
            l_dblBuyAmt = this.calcBuyContractAmt(l_intReservedDate);
            //売ポジション金額(n) =  証拠金計算.calc売ポジション金額(n)
            l_dblSellAmt = this.calcSellContractAmt(l_intReservedDate);
        }

        /*
         * 買ポジション可能数量(n)を計算する。
         */
        //買ポジション可能数量(n)
        double l_dblPossibleBuyQty = 0;

        //買ポジション可能数量(n) =  (証拠金余力額(n) + Max(0, 売ポジション金額(n) - 買ポジション金額(n)) / 規定証拠金
        //計算結果が0以上の場合、小数点以下切り捨て、0未満の場合、小数点以下切り上げとする
        double l_dblTmp =
            this.calcIfoDepositTradingPowerAmount(l_intReservedDate)
                + Math.max(0, l_dblSellAmt - l_dblBuyAmt);
        double l_dblPerUnit =
            this.ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);

        BigDecimal l_bdTmp = new BigDecimal(Double.toString(l_dblTmp));
        BigDecimal l_bdPerUnit = new BigDecimal(Double.toString(l_dblPerUnit));

        l_dblPossibleBuyQty = l_bdTmp.divide(l_bdPerUnit, 0, BigDecimal.ROUND_FLOOR).doubleValue();

        //計算した買ポジション可能数量(n)を返却する
        return l_dblPossibleBuyQty;
    }

    /**
     * (calc売ポジション可能数量)<BR>
     * 
     * 引数で指定された原資産銘柄コードの「売ポジション可能数量」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@return double
     */
    public double calcPossibleSellQty(String l_strUnderlyingProductCode)
    {
        //基準日を取得する
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //売ポジション可能数量(基準日)を取得する。
        double l_dblSellQty = this.calcPossibleSellQty(l_strUnderlyingProductCode, l_intBaseDate);

        //売ポジション可能数量(基準日)を返却する
        return l_dblSellQty;
    }

    /**
     * (calc売ポジション可能数量)<BR>
     * 
     * 引数で指定された原資産銘柄コードの指定日(=n)の「売ポジション可能数量」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     * @@param l_intReservedDate - (指定日)<BR>
     * 1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcPossibleSellQty(String l_strUnderlyingProductCode, int l_intReservedDate)
    {
        //指定日が1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        /*
         * 買ポジション金額、売ポジション金額を計算する。
         */
        //買ポジション金額(n)
        double l_dblBuyAmt = 0;
        //売ポジション金額(n)
        double l_dblSellAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null 、または、証拠金計算.calc証拠金余力額(n) < 0 の場合
        //(建玉/注文(*)が存在しない、または、証拠金余力額がマイナスの場合)
        if (this.ifoSummaryContractPerIndexList == null
            || this.calcIfoDepositTradingPowerAmount(l_intReservedDate) < 0)
        {
            //買ポジション金額(n) =  0
            l_dblBuyAmt = 0;
            //売ポジション金額(n) =  0
            l_dblSellAmt = 0;
        }
        //以外の場合
        else
        {
            //買ポジション金額(n) =  証拠金計算.calc買ポジション金額(n)
            l_dblBuyAmt = this.calcBuyContractAmt(l_intReservedDate);
            //売ポジション金額(n) =  証拠金計算.calc売ポジション金額(n)
            l_dblSellAmt = this.calcSellContractAmt(l_intReservedDate);
        }

        /*
         * 売ポジション可能数量(n)を計算する。
         */
        //売ポジション可能数量(n)
        double l_dblPossibleSellQty = 0;

        //売ポジション可能数量(n) =  (証拠金余力額(n) + Max(0, 買ポジション金額(n) - 売ポジション金額(n)) / 規定証拠金
        //計算結果が0以上の場合、小数点以下切り捨て、0未満の場合、小数点以下切り上げとする
        double l_dblTmp =
            this.calcIfoDepositTradingPowerAmount(l_intReservedDate)
                + Math.max(0, l_dblBuyAmt - l_dblSellAmt);
        double l_dblPerUnit =
            this.ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);

        BigDecimal l_bdTmp = new BigDecimal(Double.toString(l_dblTmp));
        BigDecimal l_bdPerUnit = new BigDecimal(Double.toString(l_dblPerUnit));

        l_dblPossibleSellQty = l_bdTmp.divide(l_bdPerUnit, 0, BigDecimal.ROUND_FLOOR).doubleValue();

        //計算した売ポジション可能数量(n)を返却する
        return l_dblPossibleSellQty;
    }

    /**
     * (calc未入金額)<BR>
     * 
     * 「未入金額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcNonPayAmount()
    {
        //未入金額
        double l_dblNonPayAmount = 0;
  
        //［a．証拠金計算.証拠金計算条件.is証拠金不足額非管理　@==　@trueの場合］
        //（証拠金不足額非管理会社）
        if (this.ifoDepositCalcCondition.isLackChargeNonManagement()){
            //未入金額 =  0
            return 0;                        
        }
      
        //［ｂ．証拠金計算.証拠金計算条件.is証拠金不足額非管理　@==　@falseの場合］
        //（証拠金不足額管理会社）
        //当日の証拠金不足確定時間の場合
        // [ｂ－１. 証拠金計算.証拠金計算条件.is証拠金不足メール送信済 == true の場合]
        if(this.ifoDepositCalcCondition.isIfoDepositMailFlag() == true)
        {
           //翌日請求額
            double l_dblDemandAmount1 = this.calcNextBizDateDemandAmount();
            //入金額(T+1)
            double l_dblNextBizDateCashinAmount = this.ifoCustomerTransfer.getNextBizDateCashinAmount();
            
            //未入金額 =  Max(0, 翌日請求額 - 入金額(T+1))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblDemandAmount1 - l_dblNextBizDateCashinAmount);
        }
        //証拠金不足仮計算時間帯の場合　@
        //  [b-2.証拠金計算.証拠金計算条件.is清算値速報受信済　@== true 　@かつ　@
        //  証拠金計算.証拠金計算条件.is証拠金不足メール送信済　@==　@falseの場合]
        else if (this.ifoDepositCalcCondition.isQuickReportReceived() && 
                  !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //証拠金不足額＜仮計算＞(T+1)
            double l_dblLackChargeTemp = this.calcIfoDepositLackChargeTemp(WEB3IfoReservedDateDef.T_1) ;
            //入金額(T+1)
            double l_dblNextBizDateCashinAmount = this.ifoCustomerTransfer.getNextBizDateCashinAmount();

            //未入金額 =  Max(0, 証拠金不足額＜仮計算＞(T+1) - 入金額(T+1))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblLackChargeTemp - l_dblNextBizDateCashinAmount);
        }
        // 当日の証拠金不足仮計算前時間帯の場合
        else
        {
            //当日請求額
            double l_dblDemandAmount0 = this.getCurrentBizDateDemandAmount();
            //入金額(T+0)
            double l_dblCurrentBizDateCashinAmount = this.ifoCustomerTransfer.getCurrentBizDateCashinAmount();

            //未入金額 =  Max(0, 当日請求額 - 入金額(T+0))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblDemandAmount0 - l_dblCurrentBizDateCashinAmount);
        }

        //計算した未入金額を返却する
        return l_dblNonPayAmount;
    }

    /**
     * (calc翌日請求額)<BR>
     * 
     * 「翌日請求額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcNextBizDateDemandAmount()
    {
        //翌日請求額
        double l_dblDemandAmount = 0;

        //当日証拠金不足額
        double l_dblLackCharge0 =
            this.ifoDepositCalcCondition.getCurrentBizDateIfoDepositLackCharge();
        //証拠金残高(T+1)
        double l_dblIfoDepBalance = this.calcIfoDepositBalance(WEB3IfoReservedDateDef.T_1);
        //先物評価損益
        double l_dblFuturesAppProLos = this.calcFuturesAppraisalProfitLoss();
        //証拠金所要額(T+1)
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_1);

        //翌日証拠金不足＝(証拠金残高(T+1) + 先物評価損益) － 証拠金所要額(T+1)
        double l_dblLackCharge1 = l_dblIfoDepBalance + l_dblFuturesAppProLos - l_dblRequiredAmount;

        //[a.当日証拠金不足額 > 0 の場合]
        // (当日に確定した証拠金不足が発生している場合)
        if (l_dblLackCharge0 > 0)
        {
            //翌日請求額 = 当日不足額
            l_dblDemandAmount = l_dblLackCharge0;

        }
        //[b.証拠金計算.証拠金計算条件.is証拠金不足メール送信済　@==　@true　@かつ
        //   当日証拠金不足額 == 0の場合]
        // (当日に証拠金不足が発生していない場合)
        else if (this.ifoDepositCalcCondition.isIfoDepositMailFlag() && 
                   l_dblLackCharge0 == 0)
        {
            //翌日請求額 = 0
       	    l_dblDemandAmount = 0;
        }
        //[c.証拠金計算.証拠金計算条件.is清算値速報受信済　@== true 　@かつ　@
        //   証拠金計算.証拠金計算条件.is証拠金不足メール送信済　@==　@falseの場合]
        // (証拠金不足仮計算時間帯の場合)
        else if (this.ifoDepositCalcCondition.isQuickReportReceived() && 
                  !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //翌日請求額 = 証拠金不足額＜仮計算＞(T+1)
            l_dblDemandAmount = this.calcIfoDepositLackChargeTemp(WEB3IfoReservedDateDef.T_1);

        }
        //[d.(証拠金残高(T+1) + 先物評価損益) - 証拠金所要額(T+1) < 0 の場合]
        // (当日に計算値による証拠金不足が発生している場合)
        else if (l_dblLackCharge1 < 0)
        {
            //翌日請求額 = Abs((証拠金残高(T+1) + 先物評価損益) - 証拠金所要額(T+1))
            l_dblDemandAmount = Math.abs(l_dblLackCharge1);
        }
        //[e.(証拠金残高(T+1) + 先物評価損益) - 証拠金所要額(T+1) ≧ 0 の場合] 
        // (当日に計算値による証拠金不足が発生していない場合)
        else
        {
            //翌日請求額 = 0
            l_dblDemandAmount = 0;
        }

        //計算した翌日請求額を返却する
        return l_dblDemandAmount;
    }

    /**
     * (calc先物評価損益)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesAppraisalProfitLoss()
    {
        //先物評価損益
        double l_dblProfitLoss = 0;

        //先物評価損益　@=　@先物買建評価損益 + 先物売建評価損益
        l_dblProfitLoss =
            this.calcFuturesBuyAppraisalProfitLoss() + this.calcFuturesSellAppraisalProfitLoss();

        //計算した先物評価損益を返却する        
        return l_dblProfitLoss;
    }

    /**
     * (calc先物買建評価損益)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesBuyAppraisalProfitLoss()
    {
        //先物買建評価損益
        double l_dblBuyProfitLoss = 0;

        /*
         * 先物買建評価損益を計算する。
         */

        //建玉が存在しない時
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //先物買建評価損益に0を代入する
            l_dblBuyProfitLoss = 0;
        }
        //建玉が存在する時
        else
        {
            //(カーソル)銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(カーソル)銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //先物買建評価損益＝Sum(銘柄建単価別先物買建評価損益)
                l_dblBuyProfitLoss =
                    l_dblBuyProfitLoss + l_curIfoSummary.calcFuturesBuyContractProfitLoss();
            }
        }

        //計算した先物買建評価損益を返却する        
        return l_dblBuyProfitLoss;
    }

    /**
     * (calc先物売建評価損益)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesSellAppraisalProfitLoss()
    {
        //先物売建評価損益
        double l_dblSellProfitLoss = 0;

        /*
         * 先物売建評価損益を計算する。
         */

        //建玉が存在しない時
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //先物売建評価損益に0を代入する
            l_dblSellProfitLoss = 0;
        }
        //建玉が存在する時
        else
        {
            //(カーソル)銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(カーソル)銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //先物売建評価損益＝Sum(銘柄建単価別先物売建評価損益)
                l_dblSellProfitLoss =
                    l_dblSellProfitLoss + l_curIfoSummary.calcFuturesSellContractProfitLoss();
            }
        }

        //計算した先物売建評価損益を返却する        
        return l_dblSellProfitLoss;
    }

    /**
     * (calcオプション評価損益)<BR>
     * 
     * 引数で指定された指定日(=n)の、「オプション評価損益」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcOptionAppraisalProfitLoss()
    {
        //オプション評価損益
        double l_dblProfitLoss = 0;

        //オプション評価損益　@=　@オプション買建評価損益 + オプション売建評価損益
        l_dblProfitLoss =
            this.calcOptionBuyAppraisalProfitLoss() + this.calcOptionSellAppraisalProfitLoss();

        //計算したオプション評価損益を返却する        
        return l_dblProfitLoss;
    }

    /**
     * (calcオプション買建評価損益)<BR>
     * 
     * 引数で指定された指定日(=n)の、「オプション買建評価損益」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyAppraisalProfitLoss()
    {
        //オプション買建評価損益
        double l_dblBuyProfitLoss = 0;

        /*
         * オプション買建評価損益を計算する。
         */

        //建玉が存在しない時
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //オプション買建評価損益に0を代入する
            l_dblBuyProfitLoss = 0;
        }
        //建玉が存在する時
        else
        {
            //(カーソル)銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(カーソル)銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //オプション買建評価損益＝Sum(銘柄建単価別オプション買建評価損益)
                l_dblBuyProfitLoss =
                    l_dblBuyProfitLoss + l_curIfoSummary.calcOptionBuyContractProfitLoss();
            }
        }

        //計算したオプション買建評価損益を返却する        
        return l_dblBuyProfitLoss;
    }

    /**
     * (calcオプション売建評価損益)<BR>
     * 
     * 引数で指定された指定日(=n)の、「オプション売建評価損益」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcOptionSellAppraisalProfitLoss()
    {
        //オプション売建評価損益
        double l_dblSellProfitLoss = 0;

        /*
         * オプション売建評価損益を計算する。
         */

        //建玉が存在しない時
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //オプション売建評価損益に0を代入する
            l_dblSellProfitLoss = 0;
        }
        //建玉が存在する時
        else
        {
            //(カーソル)銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(カーソル)銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //オプション売建評価損益＝Sum(銘柄建単価別オプション売建評価損益)
                l_dblSellProfitLoss =
                    l_dblSellProfitLoss + l_curIfoSummary.calcOptionSellContractProfitLoss();
            }
        }

        //計算したオプション売建評価損益を返却する        
        return l_dblSellProfitLoss;
    }

    /**
     * (calcポジションバランス)<BR>
     * 
     * 引数で指定された指定日(=n)の、「ポジションバランス」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoPositionBalance
     */
    public WEB3IfoPositionBalance calcPositionBalance(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //nullを返却する
            return null;
        }

        //ポジションバランス
        double l_dblBalance = 0;
        BigDecimal l_bdBalance = new BigDecimal(String.valueOf(l_dblBalance));
        String l_strType = null;
        //買ポジション建玉(n)
        double l_dblBuyQty = this.calcBuyContractQty(l_intReservedDate);
        BigDecimal l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
        //売ポジション建玉(n)
        double l_dblSellQty = this.calcSellContractQty(l_intReservedDate);
        BigDecimal l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));

        //買ポジション建玉(n)＝0、売ポジション建玉(n)＝0の時
        if (l_dblBuyQty == 0 && l_dblSellQty == 0)
        {
            //nullを返却する
            return null;
        }
        //買超過の時
        else if (l_dblBuyQty > l_dblSellQty)
        {
            //ポジションバランス(n)＝買ポジション建玉(n)－売ポジション建玉(n)
            l_bdBalance = l_bdBuyQty.subtract(l_bdSellQty);
            //ポジションバランス区分(n)＝ “買超過”
            l_strType = WEB3IfoPositionBalanceTypeDef.BUY_OVER;
        }
        //売超過の時
        else if (l_dblSellQty > l_dblBuyQty)
        {
            //ポジションバランス(n)＝売ポジション建玉(n)－買ポジション建玉(n)　@
            l_bdBalance = l_bdSellQty.subtract(l_bdBuyQty);
            //ポジションバランス区分(n)＝“売超過”
            l_strType = WEB3IfoPositionBalanceTypeDef.SELL_OVER;
        }
        //以外の時(買ポジション建玉(n)＝売ポジション建玉(n))
        else
        {
            //ポジションバランス(n)＝０
            l_dblBalance = 0;
            //ポジションバランス区分(n)＝“ニュートラル”
            l_strType = WEB3IfoPositionBalanceTypeDef.NEUTRAL;
        }

        //先物OPポジションバランスを生成し返却する
        l_dblBalance = l_bdBalance.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
        return new WEB3IfoPositionBalance(l_dblBalance, l_strType);
    }

    /**
     * (calcネットオプション価値総額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「ネットオプション価値総額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcNetOptionTotalAmount(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //ネットオプション価値総額(n)＝Min(0, 買オプション価値総額(n)－売オプション価値総額(n))
        double l_dblNetOption =
            Math.min(
                0,
                this.calcOptionBuyTotalAmount(l_intReservedDate)
                    - this.calcOptionSellTotalAmount(l_intReservedDate));

        //計算したネットオプション価値総額(n)を返却する
        return l_dblNetOption;
    }

    /**
     * (calc買オプション価値総額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「買オプション価値総額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionBuyTotalAmount(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //買オプション価値総額
        double l_dblBuyNetOption = 0;

        //建玉／注文が存在しない時
        if (this.ifoSummaryContractPerProductList == null)
        {
            //買オプション価値総額に0を代入
            l_dblBuyNetOption = 0;
        }
        //建玉／注文が存在する時
        else
        {
            //(カーソル)銘柄別先物OP建玉集計
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //銘柄別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //(カーソル)銘柄別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //買オプション価値総額＝Sum(銘柄別買オプション価値(n))
                l_dblBuyNetOption =
                    l_dblBuyNetOption + l_curIfoSummary.calcBuyOptionValue(l_intReservedDate);
            }
        }

        //計算した買オプション価値総額を返却する
        return l_dblBuyNetOption;
    }

    /**
     * (calc売オプション価値総額)<BR>
     * 
     * 引数で指定された指定日(=n)の、「売オプション価値総額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellTotalAmount(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //売オプション価値総額
        double l_dblSellNetOption = 0;

        //建玉／注文が存在しない時
        if (this.ifoSummaryContractPerProductList == null)
        {
            //売オプション価値総額に0を代入
            l_dblSellNetOption = 0;
        }
        //建玉／注文が存在する時
        else
        {
            //(カーソル)銘柄別先物OP建玉集計
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //銘柄別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //(カーソル)銘柄別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //売オプション価値総額＝Sum(銘柄別売オプション価値(n))
                l_dblSellNetOption =
                    l_dblSellNetOption + l_curIfoSummary.calcSellOptionValue(l_intReservedDate);
            }
        }

        //計算した売オプション価値総額を返却する
        return l_dblSellNetOption;
    }

    /**
     * (calc簡易SPAN証拠金)<BR>
     * 
     * 引数で指定された指定日(=n)の、「簡易SPAN証拠金」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDeposit(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //簡易SPAN証拠金
        double l_dblSimpleSPAN = 0;

        //オプション買建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //簡易SPAN証拠金に0を代入
            l_dblSimpleSPAN = 0;
        }
        //オプション買建を除く、建玉／注文が存在する時
        else
        {
            //簡易SPAN証拠金＝Max(買ポジション金額(n), 売ポジション金額(n))
            l_dblSimpleSPAN =
                Math.max(
                    this.calcBuyContractAmt(l_intReservedDate),
                    this.calcSellContractAmt(l_intReservedDate));

        }

        //計算した簡易SPAN証拠金を返却する
        return l_dblSimpleSPAN;
    }

    /**
     * (calc簡易SPAN証拠金レッド)<BR>
     * 
     * 引数で指定された指定日(=n)の、「簡易SPAN証拠金レッド」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDepositRed(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //簡易SPAN証拠金レッド
        double l_dblSimpleSPANRed = 0;

        //オプション買建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //簡易SPAN証拠金レッドに0を代入
            l_dblSimpleSPANRed = 0;
        }
        //オプション買建を除く、建玉／注文が存在する時
        else
        {
            //簡易SPAN証拠金レッド(n)　@=　@Max(買ポジション金額レッド(n), 売ポジション金額レッド(n))
            l_dblSimpleSPANRed =
                Math.max(
                    this.calcBuyContractAmtRed(l_intReservedDate),
                    this.calcSellContractAmtRed(l_intReservedDate));
        }

        //計算した簡易SPAN証拠金レッドを返却する
        return l_dblSimpleSPANRed;
    }

    /**
     * (calc買ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「買ポジション建玉」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractQty(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //買ポジション建玉
        double l_dblBuyQty = 0;
        BigDecimal l_bdBuyQty;
        BigDecimal l_bdPositionQty;
        
        //オプション買建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //買ポジション建玉に0を代入
            l_dblBuyQty = 0;
        }
        //オプション買建を除く、建玉／注文が存在する時
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //買ポジション建玉＝Sum(指数別買ポジション建玉(n))
                l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcBuyContractQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdBuyQty = l_bdBuyQty.add(l_bdPositionQty);
                l_dblBuyQty = l_bdBuyQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
                
            }
        }

        //計算した買ポジション建玉を返却する
        return l_dblBuyQty;
    }

    /**
     * (calc売ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「売ポジション建玉」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractQty(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //売ポジション建玉
        double l_dblSellQty = 0;
        BigDecimal l_bdSellQty;
        BigDecimal l_bdPositionQty;

        //オプション売建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //売ポジション建玉に0を代入
            l_dblSellQty = 0;
        }
        //オプション売建を除く、建玉／注文が存在する時
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //売ポジション建玉＝Sum(指数別売ポジション建玉(n))
                l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcSellContractQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdSellQty = l_bdSellQty.add(l_bdPositionQty);
                l_dblSellQty = l_bdSellQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //計算した売ポジション建玉を返却する
        return l_dblSellQty;
    }

    /**
     * (calc注文中買ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「注文中買ポジション建玉」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyOrderQty(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //注文中買ポジション建玉
        double l_dblBuyQty = 0;
        BigDecimal l_bdBuyQty;
        BigDecimal l_bdPositionQty;

        //オプション買建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //買ポジション建玉に0を代入
            l_dblBuyQty = 0;
        }
        //オプション買建を除く、建玉／注文が存在する時
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //注文中買ポジション建玉＝Sum(指数別注文中買ポジション建玉(n))
                l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcBuyOrderQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdBuyQty = l_bdBuyQty.add(l_bdPositionQty);
                l_dblBuyQty = l_bdBuyQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //計算した注文中買ポジション建玉を返却する
        return l_dblBuyQty;
    }

    /**
     * (calc注文中売ポジション建玉)<BR>
     * 
     * 引数で指定された指定日(=n)の、「注文中売ポジション建玉」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellOrderQty(int l_intReservedDate)
    {
        //指定日が0、1、2以外の時
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する
            return 0;
        }

        //注文中売ポジション建玉
        double l_dblSellQty = 0;
        BigDecimal l_bdSellQty;
        BigDecimal l_bdPositionQty;

        //オプション注文中売建を除く、建玉／注文が存在しない時
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //注文中売ポジション建玉に0を代入
            l_dblSellQty = 0;
        }
        //オプション注文中売建を除く、建玉／注文が存在する時
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //注文中売ポジション建玉＝Sum(指数別注文中売ポジション建玉(n))
                l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcSellOrderQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdSellQty = l_bdSellQty.add(l_bdPositionQty);
                l_dblSellQty = l_bdSellQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //計算した注文中売ポジション建玉を返却する
        return l_dblSellQty;
    }

    /**
     * (calc買ポジション金額)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「買ポジション金額」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractAmt(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 買ポジション金額(n)を計算する。
         */
        double l_dblContractAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        //(建玉/注文(*)が存在しない場合)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //買ポジション金額(n)　@=　@0
            l_dblContractAmt = 0;
        }
        //以外の場合
        //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //買ポジション金額(n)　@=　@Sum(指数別買ポジション金額(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleBuyAmt(l_intReservedDate);
            }
        }

        /*
         * 計算した買ポジション金額(n)を返却する。
         */
        return l_dblContractAmt;
    }

    /**
     * (calc売ポジション金額)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「売ポジション金額」を返却する。
     *  <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractAmt(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 売ポジション金額(n)を計算する。
         */
        double l_dblContractAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        //(建玉/注文(*)が存在しない場合)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //売ポジション金額(n)　@=　@0
            l_dblContractAmt = 0;
        }
        //以外の場合
        //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //売ポジション金額(n)　@=　@Sum(指数別売ポジション金額(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleSellAmt(l_intReservedDate);
            }
        }

        /*
         * 計算した売ポジション金額(n)を返却する。
         */
        return l_dblContractAmt;
    }

    /**
     * (calc買ポジション金額レッド)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「買ポジション金額レッド」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractAmtRed(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 買ポジション金額レッド(n)を計算する。
         */
        double l_dblContractAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        //(建玉/注文(*)が存在しない場合)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //買ポジション金額レッド(n)　@=　@0
            l_dblContractAmt = 0;
        }
        //以外の場合
        //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //買ポジション金額レッド(n)　@=　@Sum(指数別買ポジション金額レッド(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleBuyAmtRed(l_intReservedDate);
            }
        }

        /*
         * 計算した買ポジション金額レッド(n)を返却する。
         */
        return l_dblContractAmt;
    }

    /**
     * (calc売ポジション金額レッド)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「売ポジション金額レッド」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractAmtRed(int l_intReservedDate)
    {
        /*
         * 引数の指定日チェックを行う。
         */
        //指定日が範囲外の場合(nが0以上2以下でない場合)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0を返却する。
            return 0;
        }

        /*
         * 売ポジション金額レッド(n)を計算する。
         */
        double l_dblContractAmt = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        //(建玉/注文(*)が存在しない場合)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //売ポジション金額レッド(n)　@=　@0
            l_dblContractAmt = 0;
        }
        //以外の場合
        //(建玉/注文(*)が存在する場合、証拠金計算.指数別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //(カーソル)指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(カーソル)指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //売ポジション金額レッド(n)　@=　@Sum(指数別売ポジション金額レッド(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleSellAmtRed(l_intReservedDate);
            }
        }

        /*
         * 計算した売ポジション金額レッド(n)を返却する。
         */
        return l_dblContractAmt;
    }
    
    /**
     * (printログ)<BR>
     * 
     * ログ出力を行う。
     * シーケンス図「（証拠金計算）printログ」」参照。
     */
    private void printLog()
    {
        if(!log.ison()) return;
        //infoログ出力メッセージ
        StringBuffer l_infoMessage = new StringBuffer();
        //debugログ出力メッセージ
        StringBuffer l_debugMessage = new StringBuffer();

        /*
         * 取得項目
         */
        //01.営業日(n)
        String l_strBizDate;
        //02.証拠金残高(n)
        String l_strDepBalance;
        //03.振替額(n)
        String l_strTransferAmt;
        //04.先物決済損益(n)        
        String l_strFuturesCloseProLos;
        //05.オプション受渡代金(n)  
        String l_strOpNetAmt;
        //06.オプション買建概算受渡代金(n)
        String l_strOpBuyEstNetAmt;
        //07.先物評価損益
        String l_strFuturesAppProLos;
        //08.先物買建評価損益    
        String l_strFuturesBuyAppProLos;
        //09.先物売建評価損益     
        String l_strFuturesSellAppProLos;
        //10.受入証拠金残高(n)      
        String l_strRecDepBalance;
        //11.買ポジション建玉(n)
        String l_strBuyContQty;
        //12.注文中買ポジション建玉(n)
        String l_strBuyOrderQty;
        //13.売ポジション建玉(n)
        String l_strSellContQty;
        //14.注文中売ポジション建玉(n)
        String l_strSellOrderQty;
        //15.ポジションバランス(n)
        String l_strPositionBalance;
        //16.ポジションバランス区分(n)
        String l_strPositionBalanceType;
        //17.SPAN証拠金(n)          
        String l_strSpanIfoDep;
        double l_dblSpanIfoDep;
        //18.簡易SPAN証拠金(n)
        String l_strSimpleSPANIfoDeposit;
        //19.ネットオプション価値総額
        String l_strNetOptionAmt;
        //20.買オプション価値総額(n)
        String l_strOpBuyTotalAmt;
        //21.売オプション価値総額(n)
        String l_strOpSellTotalAmt;
        //22.証拠金所要額(n)        
        String l_strIfoDepReqAmt;
        //23.証拠金余力額(n)        
        String l_strIfoDepTPAmt;
        //24.証拠金振替余力額(n)
        String l_strIfoDepTransferableAmtInt;
        //25.証拠金振替余力額
        String l_strIfoDepTransferableAmt;
        //26.オプション評価損益
        String l_strOpAppProLos;
        //27.オプション買建評価損益
        String l_strOpBuyAppProLos;
        //28.オプション売建評価損益
        String l_strOpSellAppProLos;
        //29.本日請求額
        String l_strCurDemandAmt;
        //30.翌日請求額
        String l_strNextDemandAmt;
        //31.未入金額
        String l_strNonPayAmt;
        //32.買ポジション金額(n)
        String l_strBuyContractAmt;
        //33.売ポジション金額(n)
        String l_strSellContractAmt;
        //34.先物評価損益＜証拠金不足仮確定＞
        String l_strFuturesProfitLossTemp;
        //35.先物買建評価損益＜証拠金不足仮確定＞
        String l_strFuturesBuyProfitLossTemp;
        //36.先物売建評価損益＜証拠金不足仮確定＞
        String l_strFuturesSellProfitLossTemp;
        //37.受入証拠金残高＜証拠金不足仮確定＞(n)
        String l_strReceiptIfoDepositBalanceTemp;      
        //38.簡易SPAN証拠金＜証拠金不足仮確定＞(n)
        String l_strSimpleSPANIfoDepositTemp;
        //39.ネットオプション価値総額＜証拠金不足仮確定＞（n）
        String l_strNetOptionTotalAmountTemp;
        //40.買オプション価値総額＜証拠金不足仮確定＞（n）
        String l_strOptionBuyTotalAmountTemp;
        //41.売オプション価値総額＜証拠金不足仮確定＞（n）
        String l_strOptionSellTotalAmountTemp;
        //42.証拠金所要額＜証拠金不足仮確定＞(n)
        String l_strIfoDepositRequiredAmountTemp;
        //43.買ポジション金額＜証拠金不足仮確定＞（n）
        String l_strBuyContractAmtTemp;
        //44.売ポジション金額＜証拠金不足仮確定＞（n）
        String l_strSellContractAmtTemp;
        //45.翌日請求額＜夕場＞
        String l_strNextDemandAmountEvening;
        //46.翌々日請求額
        String l_strNext2BizDateDemandAmount;
        //47.証拠金不足額＜仮計算＞（n）
        String l_strIfoDepositLackChargeTemp;

        /*
         * infoログ出力メッセージを作成する。 
         * （※）カンマ区切りで文字列連結
         */
        //指定日(=0～2)でLOOP処理
        for (int index = 0; index <= 2; index++)
        {
            //01.営業日(n)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            l_strBizDate = formatter.format(this.ifoDepositCalcCondition.getBizDate(index));
            //02.証拠金残高(n)
            l_strDepBalance = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositBalance(index));
            //03.振替額(n)
            l_strTransferAmt = WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getTransferAmount(index));
            //04.先物決済損益(n)
            l_strFuturesCloseProLos =
                WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getFuturesCloseProfitLoss(index));
            //05.オプション受渡代金(n)
            l_strOpNetAmt = WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getOptionNetAmount(index));
            //06.オプション買建概算受渡代金(n)
            l_strOpBuyEstNetAmt =
                WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(index));
            //07.先物評価損益
            l_strFuturesAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesAppraisalProfitLoss());
            //08.先物買建評価損益
            l_strFuturesBuyAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesBuyAppraisalProfitLoss());
            //09.先物売建評価損益
            l_strFuturesSellAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesSellAppraisalProfitLoss());
            //10.受入証拠金残高(n)
            l_strRecDepBalance = WEB3StringTypeUtility.formatNumber(this.calcReceiptIfoDepositBalance(index));
            //11.買ポジション建玉(n)
            l_strBuyContQty = WEB3StringTypeUtility.formatNumber(this.calcBuyContractQty(index));
            //12.注文中買ポジション建玉(n)
            l_strBuyOrderQty = WEB3StringTypeUtility.formatNumber(this.calcBuyOrderQty(index));
            //13.売ポジション建玉(n)
            l_strSellContQty = WEB3StringTypeUtility.formatNumber(this.calcSellContractQty(index));
            //14.注文中売ポジション建玉(n)
            l_strSellOrderQty = WEB3StringTypeUtility.formatNumber(this.calcSellOrderQty(index));
            //15.ポジションバランス(n)、16.ポジションバランス区分(n)
            WEB3IfoPositionBalance l_positionBalance = this.calcPositionBalance(index);
            if (l_positionBalance == null)
            {
                l_strPositionBalance = "-";
                l_strPositionBalanceType = "-";
            }
            else
            {
                l_strPositionBalance = WEB3StringTypeUtility.formatNumber(l_positionBalance.positionBalance);
                l_strPositionBalanceType = l_positionBalance.positionBalanceType;
            }
            //17.SPAN証拠金(n)
            l_dblSpanIfoDep = this.getSPANIfoDeposit(index);
            if (l_dblSpanIfoDep == -1)
            {
                l_strSpanIfoDep = "-";
            }
            else
            {
                l_strSpanIfoDep = WEB3StringTypeUtility.formatNumber(l_dblSpanIfoDep);
            }
            //18.簡易SPAN証拠金(n)
            l_strSimpleSPANIfoDeposit = WEB3StringTypeUtility.formatNumber(this.calcSimpleSPANIfoDeposit(index));
            //19.ネットオプション価値総額(n)
            l_strNetOptionAmt = WEB3StringTypeUtility.formatNumber(this.calcNetOptionTotalAmount(index));
            //20.買オプション価値総額(n)
            l_strOpBuyTotalAmt = WEB3StringTypeUtility.formatNumber(this.calcOptionBuyTotalAmount(index));
            //21.売オプション価値総額(n)
            l_strOpSellTotalAmt = WEB3StringTypeUtility.formatNumber(this.calcOptionSellTotalAmount(index));
            //22.証拠金所要額(n)
            l_strIfoDepReqAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositRequiredAmount(index));
            //23.証拠金余力額(n)
            l_strIfoDepTPAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTradingPowerAmount(index));
            //24.証拠金振替余力額(n)
            l_strIfoDepTransferableAmtInt =
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTransferableAmount(index));
            //25.証拠金振替余力額
            l_strIfoDepTransferableAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTransferableAmount());
            //26.オプション評価損益
            l_strOpAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionAppraisalProfitLoss());
            //27.オプション買建評価損益
            l_strOpBuyAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionBuyAppraisalProfitLoss());
            //28.オプション売建評価損益
            l_strOpSellAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionSellAppraisalProfitLoss());
            //29.本日請求額
            l_strCurDemandAmt = WEB3StringTypeUtility.formatNumber(this.getCurrentBizDateDemandAmount());
            //30.翌日請求額
            l_strNextDemandAmt = WEB3StringTypeUtility.formatNumber(this.calcNextBizDateDemandAmount());
            //31.未入金額
            l_strNonPayAmt = WEB3StringTypeUtility.formatNumber(this.calcNonPayAmount());
            //32.買ポジション金額(n)
            l_strBuyContractAmt = WEB3StringTypeUtility.formatNumber(this.calcBuyContractAmt(index));
            //33.売ポジション金額
            l_strSellContractAmt = WEB3StringTypeUtility.formatNumber(this.calcSellContractAmt(index));
            //34.先物評価損益＜証拠金不足仮確定＞
            l_strFuturesProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesAppraisalProfitLossTemp());    
            //35.先物買建評価損益＜証拠金不足仮確定＞
            l_strFuturesBuyProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesBuyAppraisalProfitLossTemp());    
            //36.先物売建評価損益＜証拠金不足仮確定＞
            l_strFuturesSellProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesSellAppraisalProfitLossTemp());    
            //37.受入証拠金残高＜証拠金不足仮確定＞(n)
            l_strReceiptIfoDepositBalanceTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcReceiptIfoDepositBalanceTemp(index));      
            //38.簡易SPAN証拠金＜証拠金不足仮確定＞(n)
            l_strSimpleSPANIfoDepositTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcSimpleSPANIfoDepositTemp(index));    
            //39.ネットオプション価値総額＜証拠金不足仮確定＞（n）
            l_strNetOptionTotalAmountTemp =
                WEB3StringTypeUtility.formatNumber(this.calcNetOptionTotalAmountTemp(index));    
            //40.買オプション価値総額＜証拠金不足仮確定＞（n）
            l_strOptionBuyTotalAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcOptionBuyTotalAmountTemp(index));    
            //41.売オプション価値総額＜証拠金不足仮確定＞（n）
            l_strOptionSellTotalAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcOptionSellTotalAmountTemp(index));    
            //42.証拠金所要額＜証拠金不足仮確定＞(n)
            l_strIfoDepositRequiredAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositRequiredAmountTemp(index));
            //43.買ポジション金額＜証拠金不足仮確定＞（n）
            l_strBuyContractAmtTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcBuyContractAmtTemp(index));    
            //44.売ポジション金額＜証拠金不足仮確定＞（n）
            l_strSellContractAmtTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcSellContractAmtTemp(index));    
            //45.翌日請求額＜夕場＞
            l_strNextDemandAmountEvening = 
                WEB3StringTypeUtility.formatNumber(this.calcNextBizDateDemandAmountEvening());    
            //46.翌々日請求額
            l_strNext2BizDateDemandAmount = 
                WEB3StringTypeUtility.formatNumber(this.calcNext2BizDateDemandAmount());    
            //47.証拠金不足額＜仮計算＞（n）
            l_strIfoDepositLackChargeTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositLackChargeTemp(index));    

            /*
             * infoログ出力メッセージの連結
             * 
             * 証拠金残高(n), 振替額(n), 先物決済損益(n), オプション受渡代金(n), 先物評価損益,
             * 先物買建評価損益, 先物売建評価損益, 受入証拠金残高(n), SPAN証拠金(n), ネットオプション価値総額
             * 証拠金所要額(n), 証拠金余力額(n),翌日請求額
             */
            l_infoMessage.append(l_strDepBalance).append(",");
            l_infoMessage.append(l_strTransferAmt).append(",");
            l_infoMessage.append(l_strFuturesCloseProLos).append(",");
            l_infoMessage.append(l_strOpNetAmt).append(",");
            l_infoMessage.append(l_strFuturesAppProLos).append(",");
            l_infoMessage.append(l_strFuturesBuyAppProLos).append(",");
            l_infoMessage.append(l_strFuturesSellAppProLos).append(",");
            l_infoMessage.append(l_strRecDepBalance).append(",");
            l_infoMessage.append(l_strSpanIfoDep).append(",");
            l_infoMessage.append(l_strNetOptionAmt).append(",");
            l_infoMessage.append(l_strIfoDepReqAmt).append(",");
            l_infoMessage.append(l_strIfoDepTPAmt).append(",");
            l_infoMessage.append(l_strNextDemandAmt);
            if (index != 2)
            {
                l_infoMessage.append(",");
            }

            /*
             * debugログ出力メッセージの連結
             * 営業日(n), 証拠金残高(n), 振替額(n), 先物決済損益(n), オプション受渡代金(n), オプション買建概算受渡代金(n),
             * 先物評価損益, 先物買建評価損益, 先物売建評価損益, 受入証拠金残高(n), 買ポジション建玉(n),
             * 注文中買ポジション建玉(n), 売ポジション建玉(n), 注文中売ポジション建玉(n), ポジションバランス(n),
             * ポジションバランス(n), SPAN証拠金(n), 簡易SPAN証拠金(n), ネットオプション価値総額,
             * 買オプション価値総額(n), 売オプション価値総額(n), 証拠金所要額(n), 証拠金余力額(n), 証拠金振替余力額(n),
             * 証拠金振替余力額,オプション評価損益,オプション買建評価損益, オプション売建評価損益, 本日請求額, 翌日請求額,未入金額,
             * 買ポジション金額（n）,売ポジション金額（n）
             * 先物評価損益＜証拠金不足仮確定＞,
             * 先物買建評価損益＜証拠金不足仮確定＞, 先物売建評価損益＜証拠金不足仮確定＞,
             * 受入証拠金残高＜証拠金不足仮確定＞(n),
             * 簡易SPAN証拠金＜証拠金不足仮確定＞(n),
             * ネットオプション価値総額＜証拠金不足仮確定＞（n）,
             * 買オプション価値総額＜証拠金不足仮確定＞（n）,
             * 売オプション価値総額＜証拠金不足仮確定＞（n）,
　@　@　@　@　@　@ * 証拠金所要額＜証拠金不足仮確定＞(n),
             * 買ポジション金額＜証拠金不足仮確定＞（n）,
             * 売ポジション金額＜証拠金不足仮確定＞（n）,
             * 翌日請求額＜夕場＞, 翌々日請求額,
             * 証拠金不足額＜仮計算＞（n)
             */
            l_debugMessage.append(l_strBizDate).append(",");
            l_debugMessage.append(l_strDepBalance).append(",");
            l_debugMessage.append(l_strTransferAmt).append(",");
            l_debugMessage.append(l_strFuturesCloseProLos).append(",");
            l_debugMessage.append(l_strOpNetAmt).append(",");
            l_debugMessage.append(l_strOpBuyEstNetAmt).append(",");
            l_debugMessage.append(l_strFuturesAppProLos).append(",");
            l_debugMessage.append(l_strFuturesBuyAppProLos).append(",");
            l_debugMessage.append(l_strFuturesSellAppProLos).append(",");
            l_debugMessage.append(l_strRecDepBalance).append(",");
            l_debugMessage.append(l_strBuyContQty).append(",");
            l_debugMessage.append(l_strBuyOrderQty).append(",");
            l_debugMessage.append(l_strSellContQty).append(",");
            l_debugMessage.append(l_strSellOrderQty).append(",");
            l_debugMessage.append(l_strPositionBalance).append(",");
            l_debugMessage.append(l_strPositionBalanceType).append(",");
            l_debugMessage.append(l_strSpanIfoDep).append(",");
            l_debugMessage.append(l_strSimpleSPANIfoDeposit).append(",");
            l_debugMessage.append(l_strNetOptionAmt).append(",");
            l_debugMessage.append(l_strOpBuyTotalAmt).append(",");
            l_debugMessage.append(l_strOpSellTotalAmt).append(",");
            l_debugMessage.append(l_strIfoDepReqAmt).append(",");
            l_debugMessage.append(l_strIfoDepTPAmt).append(",");
            l_debugMessage.append(l_strIfoDepTransferableAmtInt).append(",");
            l_debugMessage.append(l_strIfoDepTransferableAmt).append(",");
            l_debugMessage.append(l_strOpAppProLos).append(",");
            l_debugMessage.append(l_strOpBuyAppProLos).append(",");
            l_debugMessage.append(l_strOpSellAppProLos).append(",");
            l_debugMessage.append(l_strCurDemandAmt).append(",");
            l_debugMessage.append(l_strNextDemandAmt).append(",");
            l_debugMessage.append(l_strNonPayAmt).append(",");
            l_debugMessage.append(l_strBuyContractAmt).append(",");
            l_debugMessage.append(l_strSellContractAmt).append(",");
            l_debugMessage.append(l_strFuturesProfitLossTemp).append(",");
            l_debugMessage.append(l_strFuturesBuyProfitLossTemp).append(",");
            l_debugMessage.append(l_strFuturesSellProfitLossTemp).append(",");
            l_debugMessage.append(l_strReceiptIfoDepositBalanceTemp).append(",");      
            l_debugMessage.append(l_strSimpleSPANIfoDepositTemp).append(",");
            l_debugMessage.append(l_strNetOptionTotalAmountTemp).append(",");
            l_debugMessage.append(l_strOptionBuyTotalAmountTemp).append(",");
            l_debugMessage.append(l_strOptionSellTotalAmountTemp).append(",");
            l_debugMessage.append(l_strIfoDepositRequiredAmountTemp).append(",");
            l_debugMessage.append(l_strBuyContractAmtTemp).append(",");
            l_debugMessage.append(l_strSellContractAmtTemp).append(",");
            l_debugMessage.append(l_strNextDemandAmountEvening).append(",");
            l_debugMessage.append(l_strNext2BizDateDemandAmount).append(",");
            l_debugMessage.append(l_strIfoDepositLackChargeTemp);
 
            if (index != 2)
            {
                l_debugMessage.append(",");
            }
        }

        // infoログ出力
        log.info(l_infoMessage.toString());
        // debugログ出力
        log.debug(l_debugMessage.toString());

    }

    /**
     * (calcポジション数量)<BR>
     * <BR>
     * 引数の原資産銘柄コードのプライススキャンに相当するポジションを返却する。 <BR>
     * <BR>
     * 1）引数.原資産銘柄コード == ミニ日経225の場合 <BR>
     * 　@[引数.数量×　@0.1]を返却する。 <BR>
     *  <BR>
     * 2)　@1)以外の場合 <BR>
     * 　@[引数.数量]をそのまま返却する。 <BR>
     * <BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * 原資産銘柄コード<BR>
     * @@return double
     */
    private double calcPositionQuantity(double l_dblQuantity , String l_strUnderlyingProductCode)
    {
          if(l_strUnderlyingProductCode.equals(WEB3IfoDepositUnderlyingProductCodeDef.MINI_NIKKEI_225)) 
          { 
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
                BigDecimal l_bdMultiply = new BigDecimal(String.valueOf(WEB3IfoDepositPriceScanMultiplyDef.MINI));
                l_bdQuantity = l_bdQuantity.multiply(l_bdMultiply);
                l_dblQuantity = l_bdQuantity.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
          }
          return   l_dblQuantity;    
    }

    /**
     * (calc受入証拠金残高＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「受入証拠金残高＜証拠金不足仮確定＞」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalanceTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //受入証拠金残高＜証拠金不足仮確定＞(n)　@=　@
        //    証拠金計算.calc証拠金残高＜証拠金不足仮確定＞(n) + 証拠金計算.calc先物評価損益＜証拠金不足仮確定＞
        double l_dblReceiptIfoDepositBalanceTemp =
            this.calcIfoDepositBalanceTemp(l_intReservedDate) + 
            this.calcFuturesAppraisalProfitLossTemp();

        return l_dblReceiptIfoDepositBalanceTemp;
    }

    /**
     * (calc証拠金所要額＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「証拠金所要額高＜証拠金不足仮確定＞」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmountTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //証拠金所要額＜証拠金不足仮確定＞(n)
        double l_dblIfoDepositRequiredAmountTemp = 0;

        //[a.証拠金計算.証拠金計算条件.isSPAN使用可能() == true の場合]
        //（SPAN証拠金採用会社の場合）
        if (this.ifoDepositCalcCondition.isSPANUsable())
        {
            //証拠金所要額＜証拠金不足仮確定＞(n) = (SPAN証拠金(n) － ネットオプション価値総額＜証拠金不足仮確定＞(n)) × SPAN係数
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOptionTemp =
                new BigDecimal(this.calcNetOptionTotalAmountTemp(l_intReservedDate));
            BigDecimal l_bdSPANFactor =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactor());

            BigDecimal l_bdIfoDepositRequiredAmount =
                l_bdSPANIfoDeposit.subtract(l_bdNetOptionTemp).multiply(l_bdSPANFactor);

            //小数点以下切り上げとする
            l_bdIfoDepositRequiredAmount =
                l_bdIfoDepositRequiredAmount.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmountTemp = l_bdIfoDepositRequiredAmount.doubleValue();
        }
        // [b.証拠金計算.証拠金計算条件.isSPAN使用可能() == false の場合]
        //（SPAN証拠金非採用会社、または、SPAN採用会社でSPANがトラブルの場合）
        else
        {
            //証拠金所要額＜証拠金不足仮確定＞(n)　@=　@
            //   簡易SPAN証拠金＜証拠金不足仮確定＞(n) － ネットオプション価値総額＜証拠金不足仮確定＞(n)
            BigDecimal l_bdSimpleSPANIfoDeposit =
                new BigDecimal(this.calcSimpleSPANIfoDepositTemp(l_intReservedDate));
            BigDecimal l_bdNetOptionTemp =
                new BigDecimal(this.calcNetOptionTotalAmountTemp(l_intReservedDate));

            l_dblIfoDepositRequiredAmountTemp =
                l_bdSimpleSPANIfoDeposit.subtract(l_bdNetOptionTemp).doubleValue();
        }

        return l_dblIfoDepositRequiredAmountTemp;
    }

    /**
     * (calc簡易SPAN証拠金＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「簡易SPAN証拠金＜証拠金不足仮確定＞」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDepositTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //簡易SPAN証拠金＜証拠金不足仮確定＞
        double l_dblSimpleSPANTemp = 0;
        //[a.証拠金計算.指数別先物OP建玉集計一覧 == null の場合]
        //(建玉/注文(*)が存在しない場合)
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //簡易SPAN証拠金＜証拠金不足仮確定＞に0を代入
            l_dblSimpleSPANTemp = 0;
        }
        //[b. 以外の場合]
        else
        {
            //簡易SPAN証拠金＜証拠金不足仮確定＞ (n)　@=　@
            //     Max(買ポジション金額＜証拠金不足仮確定＞ (n), 売ポジション金額＜証拠金不足仮確定＞ (n))          
            l_dblSimpleSPANTemp =
                Math.max(
                    this.calcBuyContractAmtTemp(l_intReservedDate),
                    this.calcSellContractAmtTemp(l_intReservedDate));
        }
        return l_dblSimpleSPANTemp;
    }

    /**
     * (calc先物評価損益＜証拠金不足仮確定＞)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesAppraisalProfitLossTemp()
    {
        //先物評価損益＜証拠金不足仮確定＞　@=　@先物買建評価損益＜証拠金不足仮確定＞ + 先物売建評価損益＜証拠金不足仮確定＞
        double l_dblProfitLossTemp =
            this.calcFuturesBuyAppraisalProfitLossTemp() + this.calcFuturesSellAppraisalProfitLossTemp();

        //計算した先物評価損益を返却する        
        return l_dblProfitLossTemp;
    }

    /**
     * (calc先物買建評価損益＜証拠金不足仮確定＞)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesBuyAppraisalProfitLossTemp()
    {
        //先物買建評価損益＜証拠金不足仮確定＞
        double l_dblBuyProfitLossTemp = 0;

        //[a.証拠金計算.銘柄建単価別先物OP建玉集計一覧 == null の場合]
        //(建玉が存在しない場合)
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //先物買建評価損益＜証拠金不足仮確定＞に0を代入する
            l_dblBuyProfitLossTemp = 0;
        }
        //[b. 以外の場合]
        //(建玉が存在する場合、証拠金計算.銘柄建単価別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //先物買建評価損益＜証拠金不足仮確定＞＝Sum(銘柄建単価別先物買建評価損益＜証拠金不足仮確定＞)
                l_dblBuyProfitLossTemp =
                    l_dblBuyProfitLossTemp + l_curIfoSummary.calcFuturesBuyContractProfitLossTemp();
            }
        }

        return l_dblBuyProfitLossTemp;
    }

    /**
     * (calc先物売建評価損益＜証拠金不足仮確定＞)<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcFuturesSellAppraisalProfitLossTemp()
    {
        //先物売建評価損益＜証拠金不足仮確定＞
        double l_dblSellProfitLossTemp = 0;

        //[a.証拠金計算.銘柄建単価別先物OP建玉集計一覧 == null の場合]
        //(建玉が存在しない場合)
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //先物売建評価損益＜証拠金不足仮確定＞に0を代入する
            l_dblSellProfitLossTemp = 0;
        }
        //[b. 以外の場合]
        //(建玉が存在する場合、証拠金計算.銘柄建単価別先物OP建玉集計一覧要素ごとのLoop処理を行い、総和を求める)
        else
        {
            //銘柄建単価別先物OP建玉集計
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //銘柄建単価別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //銘柄建単価別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //先物売建評価損益＜証拠金不足仮確定＞＝Sum(銘柄建単価別先物売建評価損益＜証拠金不足仮確定＞)
                l_dblSellProfitLossTemp =
                    l_dblSellProfitLossTemp + l_curIfoSummary.calcFuturesSellContractProfitLossTemp();
            }
        }

        return l_dblSellProfitLossTemp;
    }

   /**
     * (calcネットオプション価値総額＜証拠金不足仮確定＞ )<BR>
     * 
     * 引数で指定された指定日(=n)の、「ネットオプション価値総額＜証拠金不足仮確定＞ 」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcNetOptionTotalAmountTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
             return 0;
        }

        //ネットオプション価値総額＜証拠金不足仮確定＞(n)＝
        //    Min(0, 買オプション価値総額＜証拠金不足仮確定＞(n)－売オプション価値総額＜証拠金不足仮確定＞(n))
        double l_dblNetOptionTemp =
            Math.min(
                0,
                this.calcOptionBuyTotalAmountTemp(l_intReservedDate)
                    - this.calcOptionSellTotalAmountTemp(l_intReservedDate));

        return l_dblNetOptionTemp;
    }

    /**
     * (calc買オプション価値総額＜証拠金不足仮確定＞ )<BR>
     * 
     * 引数で指定された指定日(=n)の、「買オプション価値総額＜証拠金不足仮確定＞ 」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionBuyTotalAmountTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }
        
        //買オプション価値総額＜証拠金不足仮確定＞ 
        double l_dblBuyNetOptionTemp = 0;

        //証拠金計算.銘柄別先物OP建玉集計一覧 == null の場合
        if (this.ifoSummaryContractPerProductList == null)
        {
            //買オプション価値総額＜証拠金不足仮確定＞ に0を代入
            l_dblBuyNetOptionTemp = 0;
        }
        else
        {
            //銘柄別先物OP建玉集計
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //銘柄別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //銘柄別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //買オプション価値総額＜証拠金不足仮確定＞ ＝Sum(銘柄別買オプション価値＜証拠金不足仮確定＞ (n))
                l_dblBuyNetOptionTemp =
                    l_dblBuyNetOptionTemp + l_curIfoSummary.calcBuyOptionValueTemp(l_intReservedDate);
            }
        }
        return l_dblBuyNetOptionTemp;
    }

    /**
     * (calc売オプション価値総額＜証拠金不足仮確定＞)<BR>
     * 
     * 引数で指定された指定日(=n)の、「売オプション価値総額＜証拠金不足仮確定＞」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcOptionSellTotalAmountTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //売オプション価値総額＜証拠金不足仮確定＞
        double l_dblSellNetOptionTemp = 0;

        //証拠金計算.銘柄別先物OP建玉集計一覧 == null の場合
        if (this.ifoSummaryContractPerProductList == null)
        {
            //売オプション価値総額＜証拠金不足仮確定＞に0を代入
            l_dblSellNetOptionTemp = 0;
        }
        else
        {
            //銘柄別先物OP建玉集計
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //銘柄別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //銘柄別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //売オプション価値総額＜証拠金不足仮確定＞＝Sum(銘柄別売オプション価値＜証拠金不足仮確定＞(n))
                l_dblSellNetOptionTemp =
                    l_dblSellNetOptionTemp + l_curIfoSummary.calcSellOptionValueTemp(l_intReservedDate);
            }
        }
        return l_dblSellNetOptionTemp;
    }

    /**
     * (calc買ポジション金額＜証拠金不足仮確定＞)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「買ポジション金額＜証拠金不足仮確定＞」を返却する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcBuyContractAmtTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
             return 0;
        }

        double l_dblContractAmtTemp = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //買ポジション金額＜証拠金不足仮確定＞(n)　@=　@0
            l_dblContractAmtTemp = 0;
        }
        else
        {
           //指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //買ポジション金額＜証拠金不足仮確定＞(n)　@=　@Sum(指数別買ポジション金額＜証拠金不足仮確定＞(n))
                l_dblContractAmtTemp = l_dblContractAmtTemp + l_curIfoSummary.calcPossibleBuyAmtTemp(l_intReservedDate);
            }
        }
        return l_dblContractAmtTemp;
    }

    /**
     * (calc売ポジション金額＜証拠金不足仮確定＞)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「売ポジション金額＜証拠金不足仮確定＞」を返却する。
     *  <BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * <BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * @@return double
     */
    public double calcSellContractAmtTemp(int l_intReservedDate)
    {

        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        double l_dblContractAmtTemp = 0;

        //証拠金計算.指数別先物OP建玉集計一覧 == null の場合
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //売ポジション金額＜証拠金不足仮確定＞(n)　@=　@0
            l_dblContractAmtTemp = 0;
        }
        else
        {
            //指数別先物OP建玉集計
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //指数別先物OP建玉集計の要素数回LOOP処理
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //指数別先物OP建玉集計を取得する
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //売ポジション金額＜証拠金不足仮確定＞(n)　@=　@Sum(指数別売ポジション金額＜証拠金不足仮確定＞(n))
                l_dblContractAmtTemp = l_dblContractAmtTemp + l_curIfoSummary.calcPossibleSellAmtTemp(l_intReservedDate);
            }
        }
        return l_dblContractAmtTemp;
    }

   /**
     * (calc証拠金不足額＜仮計算＞ )<BR>
     * 
     * 夕場実施会社の場合、清算値速報から証拠金不足額を計算する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@param l_intReservedDate - (指定日)<BR>
     * 0、1、2のいずれかの値。<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositLackChargeTemp(int l_intReservedDate)
    {
        //指定日が範囲外の場合(nが0以上2以下でない場合)、0を返却
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        // 証拠金不足額＜仮計算＞
        double l_dblIfoDepositLackChargeTemp = 0;
        // 受入証拠金残高＜証拠金不足仮確定＞(n) ・・・ 証拠金計算. calc受入証拠金残高＜証拠金不足仮確定＞(n)
        double l_dblBalanceTemp = this.calcReceiptIfoDepositBalanceTemp(l_intReservedDate);
        // 証拠金所要額＜証拠金不足仮確定＞(n) ・・・証拠金計算. calc証拠金所要額＜証拠金不足仮確定＞(n)
        double l_dblRequiredAmountTemp = this.calcIfoDepositRequiredAmountTemp(l_intReservedDate);
       
        //[a.証拠金計算.証拠金計算条件.is清算値速報受信済 == true かつ
        //    証拠金計算.証拠金計算条件.is証拠金不足メール送信済 == false の場合]
        //   　@(証拠金不足仮計算時間帯の場合)
        if ( this.ifoDepositCalcCondition.isQuickReportReceived() && 
             !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //[a-1.　@受入証拠金残高＜証拠金不足仮確定＞(n)-証拠金所要額＜証拠金不足仮確定＞(n)）＜０　@の場合］
            if((l_dblBalanceTemp - l_dblRequiredAmountTemp) < 0 )
            {
                //  証拠金不足額＜仮計算＞(n) = 
                //        Aｂｓ（（受入証拠金残高＜証拠金不足仮確定＞(n)　@-　@証拠金所要額＜証拠金不足仮確定＞(n)））
                l_dblIfoDepositLackChargeTemp  = Math.abs(l_dblBalanceTemp - l_dblRequiredAmountTemp);
            }
            else
            {
                //証拠金不足額＜仮計算＞ = ０
                l_dblIfoDepositLackChargeTemp = 0;
            }
        }
        //［b.　@a以外の場合］
        else
        {
            //証拠金不足額＜仮計算＞ = ０
            l_dblIfoDepositLackChargeTemp = 0;
        }

        return l_dblIfoDepositLackChargeTemp;
    }
    
   /**
     * (calc翌々日請求額)<BR>
     * 
     * 「翌々日請求額」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcNext2BizDateDemandAmount()
    {
        //翌々日請求額
        double l_dblDemandAmount = 0;
        //受入証拠金残高（T+2)    証拠金計算. calc受入証拠金残高（2)
        double l_dblBalance  = this.calcReceiptIfoDepositBalance(WEB3IfoReservedDateDef.T_2);
        //証拠金所要額（T+2)   証拠金計算. calc証拠金所要額（2）
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_2);

        //［a. 受入証拠金残高（T+2) - 証拠金所要額（T+2）＜０　@の場合］］
        if ((l_dblBalance - l_dblRequiredAmount) < 0 )
        {
            //翌々日請求額 =  Abs(受入証拠金残高（T+2　@- 証拠金所要額（T+2）)
            l_dblDemandAmount = Math.abs(l_dblBalance - l_dblRequiredAmount);
        }
        //［b. 以外の場合］
         else {
            //翌々日請求額 =  0
            l_dblDemandAmount = 0;
        }

        return l_dblDemandAmount;
    }

   /**
     * (calc翌日請求額＜夕場＞)<BR>
     * 
     * 「calc翌日請求額＜夕場＞」を返却する。<BR>
     * (*) 計算の詳細は、「基本設計計算式書（証拠金）.doc」参照。<BR>
     * 
     * @@return double
     */
    public double calcNextBizDateDemandAmountEvening()
    {
        //翌日請求額＜夕場＞       
        double l_dblDemandAmount = 0;
        //受入証拠金残高（T+1)    証拠金計算. calc受入証拠金残高（1)
        double l_dblBalance  = this.calcReceiptIfoDepositBalance(WEB3IfoReservedDateDef.T_1);
        //証拠金所要額（T+1)   証拠金計算. calc証拠金所要額（1）
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_1);

        //［a. 受入証拠金残高（T+1）- 証拠金所要額（T+1）＜０ の場合］
        if ((l_dblBalance - l_dblRequiredAmount) < 0 )
        {
            //翌日請求額＜夕場＞ =  Abs(受入証拠金残高（T+1）-　@証拠金所要額（T+1）)
            l_dblDemandAmount = Math.abs(l_dblBalance - l_dblRequiredAmount);
        }
         //［b. 以外の場合］
        else {
            //翌日請求額＜夕場＞ =  0
           l_dblDemandAmount = 0;
        }

        return l_dblDemandAmount;
    }


    
}
@
