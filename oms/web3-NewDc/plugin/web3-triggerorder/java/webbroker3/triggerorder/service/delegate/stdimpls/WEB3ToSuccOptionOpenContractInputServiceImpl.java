head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP新規建入力サービスImpl(WEB3ToSuccOptionOpenContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 于瀟(中訊) 新規作成モデル266
Revision History : 2008/04/23 于瀟(中訊) 仕様変更モデル338
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）OP新規建入力サービスImpl)<BR>
 * （連続）OP新規建入力サービス実装クラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccOptionOpenContractInputServiceImpl extends WEB3OptionOpenContractInputServiceImpl
    implements WEB3ToSuccOptionOpenContractInputService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 47FDBE4000FA
     */
    public WEB3ToSuccOptionOpenContractInputServiceImpl()
    {

    }

    /**
     * （連続）OP新規建入力サービス処理を実施する。<BR>
     * <BR>
     * this.create入力画面をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A91B0200F4
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

        //this.create入力画面をコールする
        //引数
        //リクエスト
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccOptionsOpenInputRequest)
        {
            l_response = this.createInput((WEB3SuccOptionsOpenInputRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create入力画面)<BR>
     * （連続）OP新規建入力画面を表示する。<BR>
     * <BR>
     * 「（（連続）OP新規建入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A91B020104
     */
    protected WEB3SuccOptionsOpenInputResponse createInput(WEB3SuccOptionsOpenInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInput(WEB3SuccOptionsOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //親注文の注文単位オブジェクトを取得する
        //引数は以下の通りにセットする
        //（親注文）注文ID：　@リクエスト.連続注文共通情報の同項目
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(Long.parseLong(l_request.succCommonInfo.parentOrderId));

        //補助口座を取得する
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)getSubAccount();

        //連続注文共通のチェックを行う
        //引数は以下の通りにセットする
        //補助口座：　@this.get補助口座()
        //銘柄タイプ：　@"先物オプション"
        //先物／オプション区分：　@"オプション"
        //連続注文取引区分：　@リクエスト.連続注文共通情報.連続注文取引区分
        //親注文の注文単位：　@取得した親注文の注文単位オブジェクト
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //連続注文最大設定数を超過しないかどうかをチェックする
        //親注文の注文単位：　@取得した親注文の注文単位オブジェクト
        l_orderManager.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //superクラスの入力画面表示メソッドをコールする
        //引数は以下の通りにセットする
        //リクエストデータ：引数のリクエストデータ
        WEB3SuccOptionsOpenInputResponse l_response =
            (WEB3SuccOptionsOpenInputResponse)super.createInputScreen(l_request);

        //反対売買取引かどうかを判定する
        //引数は以下の通りにセットする
        //連続注文取引区分：　@リクエスト.連続注文共通情報の同項目
        //親注文の注文単位：　@取得した親注文の注文単位オブジェクト
        boolean l_blnReversingTrade = l_orderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //注文数量：　@
        //　@○連続注文マネージャ.is反対売買取引()==trueの場合
        //　@　@　@（親注文の注文単位.注文数量）をセット。
        //　@○連続注文マネージャ.is反対売買取引()==falseの場合
        //　@　@　@nullをセット。
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
        }
        else
        {
            l_response.orderQuantity = null;
        }

        //執行条件一覧：　@"無条件"のみをセット。
        //発注条件区分一覧：　@"指定なし"のみをセット。
        //Ｗ指値用執行条件一覧：　@nullをセット。
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        l_response.wlimitExecCondList = null;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
