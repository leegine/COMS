head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物返済入力サービスImpl（WEB3ToSuccFuturesSettleContractInputServiceImpl.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/17 孟亞南(中訊) 新規作成モデルNo.255 No.298 No.320
 Revision History : 2008/04/19 孟亞南(中訊) 仕様変更モデルNo.335 No.337
 Revision History : 2008/05/19 劉剣(中訊) 仕様変更モデルNo.318
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）先物返済入力サービスImpl)<BR>
 * （連続）先物返済入力サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractInputServiceImpl
    extends WEB3FuturesSettleContractInputServiceImpl
    implements WEB3ToSuccFuturesSettleContractInputService
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractInputServiceImpl.class);

    /**
     * @@roseuid 47D659350138
     */
    public WEB3ToSuccFuturesSettleContractInputServiceImpl()
    {

    }

    /**
     * （連続）株価指数先物返済入力画面表示処理を実施する。<BR>
     * <BR>
     * this.get返済入力画面()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94B8701C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (!(l_request instanceof WEB3SuccFuturesCloseInputRequest))
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        WEB3SuccFuturesCloseInputRequest l_inputRequest =
            (WEB3SuccFuturesCloseInputRequest)l_request;

        WEB3SuccFuturesCloseInputResponse l_response =
            this.getSettleContractInputScreen(l_inputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get返済入力画面)<BR>
     * （連続）株価指数先物返済入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）先物返済入力）get返済入力画面」参照<BR>
     * ========================================================<BR>
     * シーケンス図 ：(（（連続）先物返済入力）get返済入力画面) <BR>
     * 具体位置：(reset銘柄コード(銘柄コード : String))<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_02248<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccFuturesCloseInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94B8701E6
     */
    protected WEB3SuccFuturesCloseInputResponse getSettleContractInputScreen(
        WEB3SuccFuturesCloseInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get補助口座
        SubAccount l_subAccount = this.getSubAccount();
        //is反対売買取引
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(l_request.succCommonInfo.succTradingType, l_ifoOrderUnit);

        String l_strProductCode = null;
        //連続注文マネージャ.is反対売買取引()==trueの場合
        if (l_blnReversingTrade)
        {
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();
            //銘柄コード：　@親注文の注文単位.先物OP銘柄.原資産銘柄コード
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //銘柄コード：　@建玉オブジェクト.先物OP銘柄.原資産銘柄コード(*1)
            //(*1)先物OPポジションマネージャ.getContract(リクエストデータ.ID[0])から建玉オブジェクトを生成
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

            //リクエストデータ.ID==nullの場合
            //「連続注文取扱不可です。」の例外をスローする。
            if (l_request.id == null)
            {
                log.debug("連続注文取扱不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02248,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "連続注文取扱不可です。");
            }
            //建玉オブジェクトを取得する。
            try
            {
                WEB3IfoContractImpl l_ifoContractImpl =
                    (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(
                        Long.parseLong(l_request.id[0]));
                WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
                l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //reset銘柄コード
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate連続注文
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate連続注文最大設定数
        l_orderManager.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //get返済入力画面(リクエストデータ : 株価指数先物返済入力画面リクエスト)
        WEB3SuccFuturesCloseInputResponse l_response =
            (WEB3SuccFuturesCloseInputResponse)super.getColseMarginInput(l_request);
        //連続注文マネージャ.is反対売買取引()==trueの場合
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
        }
        //執行条件一覧
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //発注条件区分一覧
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        //Ｗ指値用執行条件一覧
        l_response.wlimitExecCondList = null;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get建玉)<BR>
     * 建玉を取得し返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）株価指数先物返済入力画面リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get先物OP親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、<BR>
     * 　@連続注文マネージャImpl.create建玉()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[指定する引数]<BR>
     * 　@　@注文単位：　@２）で取得した親注文の注文単位<BR>
     * <BR>
     * ４）　@既存残に対する返済（３）以外）の場合、<BR>
     * 　@super.get建玉()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@[指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。<BR>
     * 　@[指定する引数]<BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 返済注文入力リクエストオブジェクト。<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     * @@roseuid 47B13D8100C5
     */
    protected WEB3IfoContractImpl getContract(WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //連続注文マネージャImpl.is反対売買取引()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        WEB3IfoContractImpl l_ifoContract = null;
        if (l_blnReversingTrade)
        {
            //create建玉()
            l_ifoContract = l_orderManager.createIfoContract(l_ifoOrderUnit);
        }
        else
        {
            //super.get建玉()
            l_ifoContract = super.getContract(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }

    /**
     * (create建玉照会明細一覧)<BR>
     * リクエストデータより建玉照会明細の一覧を作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）株価指数先物返済入力画面リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get先物OP親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、以下の手順にて建玉照会明細を作成する。<BR>
     * <BR>
     * 　@３－１）　@連続注文マネージャImpl.create建玉照会明細()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[指定する引数]<BR>
     * 　@　@　@注文単位：　@２）で取得した親注文の注文単位<BR>
     * <BR>
     * 　@３－２）　@プロパティセットしたインスタンスのみを要素とする<BR>
     * 　@　@配列を生成し、返却する。<BR>
     * <BR>
     * ４）　@既存残に対する返済（３）以外）の場合、<BR>
     * 　@super.create建玉照会明細一覧()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。<BR>
     * 　@[指定する引数]<BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 返済注文入力リクエストデータオブジェクト。<BR>
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47B1710C00A0
     */
    protected  WEB3FuturesContractReferenceUnit[] createContractReferenceUnitList(
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitList(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //連続注文マネージャImpl.is反対売買取引()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        WEB3FuturesContractReferenceUnit[] l_referenceUnits = null;
        if (l_blnReversingTrade)
        {
            //this.create建玉照会明細()
            WEB3FuturesContractReferenceUnit l_referenceUnit =
                this.createContractReferenceUnit(l_ifoOrderUnit);
            l_referenceUnits = new WEB3FuturesContractReferenceUnit[]{l_referenceUnit};
        }
        else
        {
            //super.create建玉照会明細一覧()
            l_referenceUnits = super.createContractReferenceUnitList(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_referenceUnits;
    }

    /**
     * (sort建玉明細一覧)<BR>
     * 引数の建玉明細一覧をソートする。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを <BR>
     * 　@（連続）株価指数先物返済入力画面リクエストにキャストする。 <BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。 <BR>
     * 　@連続注文マネージャImpl.get先物OP親注文の注文単位()を <BR>
     * 　@コールする。 <BR>
     * <BR>
     * 　@[指定する引数] <BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目 <BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、 <BR>
     * 　@個別返済のみでソート不要である為、 <BR>
     * 　@処理を終了する。 <BR>
     * <BR>
     * ４）　@既存残に対する返済（３）以外）の場合、 <BR>
     * 　@super.sort建玉明細一覧()をコールする。 <BR>
     * <BR>
     * 　@[指定する引数] <BR>
     * 　@　@本メソッドの引数をそのまま設定。 <BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。 <BR>
     * 　@[指定する引数] <BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目 <BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_contractUnits - (建玉明細一覧)<BR>
     * 建玉明細の配列。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 返済注文入力リクエストオブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B242EC0166
     */
    protected void sortContractUnitList(
        WEB3FuturesOptionsContractUnit[] l_contractUnits,
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sortContractUnitList(WEB3FuturesOptionsContractUnit[], WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP親注文の注文単位(long)
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //連続注文マネージャImpl.is反対売買取引()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        //連続注文マネージャImpl.is反対売買取引() == true
        //個別返済のみでソート不要である為、処理を終了する。
        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //super.sort建玉明細一覧()
            super.sortContractUnitList(l_contractUnits, l_request);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create建玉照会明細)<BR>
     * 引数の注文単位より株価指数先物建玉照会明細を作成する。 <BR>
     * <BR>
     * ※反対売買時に使用する。 <BR>
     * <BR>
     * １）　@以下の手順にて株価指数先物建玉照会明細を作成する。 <BR>
     * 　@１－１）　@株価指数先物建玉照会明細インスタンスを生成する。 <BR>
     * <BR>
     * 　@１－２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * 　@　@　@　@　@　@※以下の項目以外はnullをセットする。 <BR>
     * <BR>
     * 　@　@指数種別 = 注文単位.先物OP銘柄.原資産銘柄コード <BR>
     * 　@　@限月 = 注文単位.先物OP銘柄.限月 <BR>
     * 　@　@取引市場 = 注文単位.市場コード（SONAR） <BR>
     * 　@　@建区分 = 注文単位.getSide() == "買"の場合、"買建" <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、"売建" <BR>
     * 　@　@建日 = 注文単位.発注日 <BR>
     * 　@　@建数量 = 注文単位.注文数量 <BR>
     * <BR>
     * 　@１－３）　@プロパティセットしたインスタンスを返却する。<BR>
     * @@param l_ifoOrderUnit  - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return WEB3FuturesContractReferenceUnit
     * @@throws WEB3BaseException
     */
    public WEB3FuturesContractReferenceUnit createContractReferenceUnit(
        IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnit(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //株価指数先物建玉照会明細インスタンスを生成する
        WEB3FuturesContractReferenceUnit l_futuresContractReferenceUnit =
            new WEB3FuturesContractReferenceUnit();

        //指数種別 = 注文単位.先物OP銘柄.原資産銘柄コード
        IfoProduct l_ifoProduct = (IfoProduct)l_ifoOrderUnit.getProduct();
        l_futuresContractReferenceUnit.targetProductCode =
            l_ifoProduct.getUnderlyingProductCode();

        //限月 = 注文単位.先物OP銘柄.限月
        l_futuresContractReferenceUnit.delivaryMonth = l_ifoProduct.getMonthOfDelivery();

        //取引市場 = 注文単位.市場コード（SONAR）
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        l_futuresContractReferenceUnit.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

        //建区分
        if (SideEnum.BUY.equals(l_ifoOrderUnit.getSide()))
        {
            //注文単位.getSide() == "買"の場合、"買建"
            l_futuresContractReferenceUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else
        {
            //以外、"売建"
            l_futuresContractReferenceUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        //建日 = 注文単位.発注日
        l_futuresContractReferenceUnit.openDate =
            WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //建数量 = 注文単位.注文数量
        l_futuresContractReferenceUnit.contractOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());

        log.exiting(STR_METHOD_NAME);
        return l_futuresContractReferenceUnit;
    }
}
@
