head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券プロダクトマネージャ(WEB3BondProductManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
                    2006/10/08 周捷 (中が非営業日です訊) 仕様変更・モデル107、115
                    2006/10/10 柴雙紅 (中訊) 仕様変更・モデル121
                    2006/10/16 趙林鵬 (中訊) 仕様変更・モデル124、125、129
 Revesion History : 2007/07/11 周墨洋 (中訊) 仕様変更・モデル195,198,202
 Revesion History : 2007/07/25 謝旋 (中訊) 仕様変更・モデル218
 Revesion History : 2007/08/08 謝旋 (中訊) 仕様変更・モデル251
 Revesion History : 2007/09/10 周墨洋 (中訊) 仕様変更・モデル256,モデル257
 Revesion History : 2007/10/08 武波 (中訊) 仕様変更・モデル258
 Revesion History : 2008/08/13 馮海濤 (中訊) 仕様変更・モデル260
 Revesion History : 2009/07/24 武波 (中訊) 仕様変更・モデル262,263 ＤＢ更新仕様No.044
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondProduct;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductManagerImpl;

import com.fitechlabs.dbind.Row;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3OnlineDispDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.data.BondAutoExecLimitActionRow;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.data.BondIssueCouponTypeRow;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondProductSortKeyItemDef;
import webbroker3.bd.message.WEB3AdminBondProductUpdateInfo;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;

/**
 * (債券プロダクトマネージャ)<BR>
 * 債券プロダクトマネージャクラス。 <BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondProductManager extends BondProductManagerImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondProductManager.class);

    /**
     * @@roseuid 44E3361B005D
     */
    public WEB3BondProductManager()
    {

    }

    /**
     * (get債券銘柄)<BR>
     * 債券銘柄オブジェクトを取得する。 <BR>
     * <BR>
     * １）以下の条件で債券銘柄テーブルからデータを取得する。 <BR>
     * <BR>
     * [条件] <BR>
     * 銘柄ID： 引数.銘柄ID <BR>
     * <BR>
     * ２）取得したデータから債券銘柄オブジェクトを生成し、返却する。 <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return BondProduct
     * @@throws WEB3BaseException
     * @@roseuid 42195ECE00B5
     */
    public BondProduct getBondProduct(long l_lngProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondProduct(long)";
        log.entering(STR_METHOD_NAME);

        BondProduct l_bondProduct = null;

        try
        {
            l_bondProduct = (BondProduct) this.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bondProduct;
    }

    /**
     * (to銘柄)<BR>
     * （toProductのオーバーライド）<BR>
     * <BR>
     * 引数.行オブジェクトをもとに債券銘柄オブジェクトを生成し、返却する。<BR>
     * @@param l_row - 行オブジェクト<BR>
     * @@return Product
     * @@roseuid 42195F430209
     */
    public Product toProduct(Row l_row)
    {
        final String STR_METHOD_NAME = "toProduct(Row)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_row instanceof ProductRow)
            {
                log.exiting(STR_METHOD_NAME);
                return new WEB3BondProduct((ProductRow)l_row);
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return new WEB3BondProduct((BondProductRow)l_row);
            }
        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating WEB3BondProduct for product id: " +
                ((ProductRow)l_row).getProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }
    }

    /**
     * (validate銘柄内容)<BR>
     * （validate銘柄内容()） <BR>
     * 債券銘柄更新情報の登録審査を行う。 <BR>
     * <BR>
     * １）get債券銘柄()メソッドで、債券銘柄を取得する。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@　@証券会社：証券会社オブジェクト<BR>
     * 　@　@　@　@銘柄コード（WEB3）　@：債券銘柄更新情報.銘柄コード(WEB3)<BR>
     * <BR>
     * ２）債券銘柄更新情報の「取扱開始日時」「取扱終了日時」をチェック。<BR>
     * 　@　@２－１）債券銘柄更新情報.取扱開始日時 != null　@又は　@<BR>
     * 　@　@　@　@　@　@債券銘柄更新情報.取扱終了日時 != nullの場合、<BR>
     * 　@　@　@　@　@　@以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@取扱開始日時(*1)　@＞　@取扱終了日時(*2)である場合、<BR>
     * 　@　@　@　@　@　@エラー『取扱開始日時と取扱終了日時の関係が不正です。』<BR>
     *　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@　@　@　@　@tag:   BUSINESS_ERROR_02621<BR>
     * <BR>
     * 　@　@　@　@　@(*1)以下の値を使用する。<BR>
     * 　@　@　@　@　@　@　@　@債券銘柄更新情報.取扱開始日時 != nullの場合、<BR>
     * 　@　@　@　@　@  　@　@　@　@債券銘柄更新情報.取扱開始日時。<BR>
     * 　@　@　@　@　@　@　@　@債券銘柄更新情報.取扱開始日時 == nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄.取扱開始日時。<BR>
     * <BR>
     * 　@　@　@　@　@(*2)以下の値を使用する。<BR>
     * 　@　@　@　@　@　@　@　@債券銘柄更新情報.取扱終了日時 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄更新情報.取扱終了日時。<BR>
     * 　@　@　@　@　@　@　@　@債券銘柄更新情報.取扱終了日時 == nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄.取扱終了日時。<BR>
     * (*1)の値 != null かつ (*2)の値 != nullの場合に比較をする。 <BR>
     * <BR>
     * <BR>
     * ３）債券銘柄更新情報の「応募開始日」、「応募終了日」をチェック<BR>
     * 　@　@３－１）債券銘柄更新情報.応募開始日 != null　@又は　@債券銘柄更新情報.応募終了日 != nullの場合、<BR>
     * 　@　@　@　@　@　@以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@応募開始日(*1)　@＞　@応募終了日(*2)である場合、<BR>
     * 　@　@　@　@　@　@エラー『応募開始日と応募終了日の関係が不正です。』<BR>
     *　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@　@　@　@　@tag:   BUSINESS_ERROR_02622<BR>
     * <BR>
     * 　@　@　@　@　@(*1)債券銘柄更新情報.応募開始日 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.応募開始日。<BR>
     * 　@　@　@　@　@　@　@債券銘柄更新情報.応募開始日 == nullの場合<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄.応募開始日。<BR>
     * <BR>
     * 　@　@　@　@　@(*2)債券銘柄更新情報.応募終了日 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.応募終了日。<BR>
     * 　@　@　@　@　@　@　@債券銘柄更新情報.取扱終了日時 == nullの場合<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄.応募終了日。<BR>
     * <BR>
     * (*1)の値 != null かつ (*2)の値 != nullの場合に比較をする。<BR>
     * <BR>
     * 　@　@３－２）債券銘柄.債券タイプ == 国内債券の場合、<BR>
     * 　@　@　@３－２－１）債券銘柄.応募開始日(SONAR) != null かつ、<BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄更新情報.応募開始日 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄.応募開始日(SONAR) ＞債券銘柄更新情報.応募開始日である場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラー『応募開始日(SONAR)と応募開始日の関係が不正です。』<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02623<BR>
     * <BR>
     * 　@　@　@３－２－２）債券銘柄.応募終了日(SONAR) != null かつ、<BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄更新情報.応募終了日 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@債券銘柄更新情報.応募終了日 ＞ 債券銘柄.応募終了日(SONAR)である場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラー『応募終了日(SONAR)と応募終了日の関係が不正です。』<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02624<BR>
     * <BR>
     * ４）債券銘柄更新情報の買付単価をチェック <BR>
     * 　@４－１）拡張債券注文マネージャ.validate単価を呼ぶ <BR>
     * 　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@債券銘柄：取得した債券銘柄 <BR>
     * 　@　@　@　@　@単価　@　@　@：債券銘柄更新情報.買付単価 <BR>
     * <BR>
     * ５）債券銘柄更新情報の売却単価をチェック <BR>
     * 　@５－１）拡張債券注文マネージャ.validate単価を呼ぶ <BR>
     * 　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@債券銘柄：取得した債券銘柄 <BR>
     * 　@　@　@　@　@単価　@　@　@：債券銘柄更新情報.売却単価 <BR>
     * <BR>
     * ６）債券銘柄更新情報の最高額面と最低額面をチェック<BR>
     * 　@  ６－１）債券銘柄更新情報.最低額面 != null　@又は　@債券銘柄更新情報.最高額面 != nullの場合、  <BR>
     * 　@　@　@　@以下のチェックを行なう。 <BR>
     * 　@　@　@　@６－１－１）最低額面(*1)　@＞　@最高額面(*2)である場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@エラー『最高額面と最低額面の関係が不正です。』  <BR>
     * 　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02625<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@(*1)債券銘柄更新情報.最低額面 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.最低額面。  <BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.最低額面 == nullの場合   <BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄.最低額面。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@(*2)債券銘柄更新情報.最高額面 != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.最高額面。   <BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄更新情報.最高額面 == nullの場合   <BR>
     * 　@　@　@　@　@　@　@　@　@債券銘柄.最高額面。   <BR>
     * (*1)の値 != null かつ (*2)の値 != nullの場合に比較をする。 <BR>
     * <BR>
     * ７）応募勧誘形式・応募引受け区分チェック<BR>
     * 　@　@７－１）債券銘柄更新情報.売買区分 == ”応募”の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@７－１－１）債券銘柄更新情報.応募勧誘形式 == null または、<BR>
     * 　@　@　@　@　@　@債券銘柄更新情報.応募引受け区分 == null の場合、<BR>
     * 　@　@　@　@　@　@エラー『応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。』<BR>
     * 　@　@　@　@　@　@をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_03106<BR>
     * <BR>
     * 　@　@７－２）債券銘柄更新情報.売買区分 ≠ ”応募”の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@７－２－１）債券銘柄更新情報.応募勧誘形式 ≠ null または、<BR>
     * 　@　@　@　@　@　@債券銘柄更新情報.応募引受け区分 ≠ null の場合、<BR>
     * 　@　@　@　@　@　@エラー『応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。』<BR>
     * 　@　@　@　@　@　@をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_03107<BR>
     * <BR>
     * ８）受渡日チェック<BR>
     * 　@　@８－１）債券銘柄更新情報 .受渡日 != null　@かつ<BR>
     * 　@　@　@　@債券銘柄更新情報 .受渡日 !=債券銘柄. 受渡日の場合、<BR>
     * 　@　@　@　@以下のチェックを行なう。<BR>
     * 　@　@　@　@８－１－１） 債券銘柄更新情報 .受渡日が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「受渡日が非営業日です。」をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02336<BR>
     * <BR>
     * 　@　@　@　@８－１－２）債券銘柄更新情報 .受渡日＜現在日付の場合、<BR>
     * 　@　@　@　@　@　@エラー「受渡日が不正です。」をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02865<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_productUpdateInfo - (債券銘柄更新情報)<BR>
     * 債券銘柄更新情報<BR>
     * @@param l_strProductCode - (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)<BR>
     * @@roseuid 44C44AF10311
     */
    public void validateProductSpec(Institution l_institution,
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo,
        String l_strProductCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateProductSpec(Institution, WEB3AdminBondProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_productUpdateInfo == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTradeStartDate = null ;
        Date l_datTradeEndDate = null ;
        Date l_datRecruitStartDate = null ;
        Date l_datRecruitEndDate = null ;

        // １）get債券銘柄()メソッドで、債券銘柄を取得する。
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct) this.getBondProduct
                (l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）債券銘柄更新情報の「取扱開始日時」「取扱終了日時」をチェック。
        //２－１）債券銘柄更新情報.取扱開始日時 != null　@又は　@
        //債券銘柄更新情報.取扱終了日時 != nullの場合、
        //以下のチェックを行なう。<BR>
        if ((l_productUpdateInfo.tradeStartDate != null) || (l_productUpdateInfo.tradeEndDate != null))
        {
            //(*1)以下の値を使用する。
            //債券銘柄更新情報.取扱開始日時 != nullの場合、
            //債券銘柄更新情報.取扱開始日時
            //債券銘柄更新情報.取扱開始日時 == nullの場合、
            //債券銘柄.取扱開始日時。
            if (l_productUpdateInfo.tradeStartDate != null)
            {
                l_datTradeStartDate = l_productUpdateInfo.tradeStartDate;
            }
            else
            {
                l_datTradeStartDate = l_bondProduct.getTradeStartDate();
            }

            //(*2)以下の値を使用する。
            //債券銘柄更新情報.取扱終了日時 != nullの場合、
            //債券銘柄更新情報.取扱終了日時。
            //債券銘柄更新情報.取扱終了日時 == nullの場合、
            //債券銘柄.取扱終了日時。
            if (l_productUpdateInfo.tradeEndDate != null)
            {
                l_datTradeEndDate = l_productUpdateInfo.tradeEndDate;
            }
            else
            {
                l_datTradeEndDate = l_bondProduct.getTradeEndDate();
            }

            if (l_datTradeStartDate != null && l_datTradeEndDate != null)
            {
                //取扱開始日時(*1)　@＞　@取扱終了日時(*2)である場合、
                //エラー『取扱開始日時と取扱終了日時の関係が不正です。』
                if (l_datTradeStartDate.compareTo(l_datTradeEndDate) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02621,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "取扱開始日時が取扱終了日時を超えています。");
                }
            }

        }
        //３）債券銘柄更新情報の「応募開始日」、「応募終了日」をチェック
        //３－１）債券銘柄更新情報.応募開始日 != null　@又は　@債券銘柄更新情報.応募終了日 != nullの場合、
        //以下のチェックを行なう。
        if ((l_productUpdateInfo.recruitStartDate != null) || (l_productUpdateInfo.recruitEndDate != null))
        {
            //(*1)債券銘柄更新情報.応募開始日 != nullの場合、
            //債券銘柄更新情報.応募開始日。
            //債券銘柄更新情報.応募開始日 == nullの場合
            //債券銘柄.応募開始日。
            if (l_productUpdateInfo.recruitStartDate != null)
            {
                l_datRecruitStartDate = l_productUpdateInfo.recruitStartDate;
            }
            else
            {
                l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
            }

            //(*2)債券銘柄更新情報.応募終了日 != nullの場合、
            //債券銘柄更新情報.応募終了日。
            //債券銘柄更新情報.取扱終了日時 == nullの場合
            //債券銘柄.応募終了日。
            if (l_productUpdateInfo.recruitEndDate != null)
            {
                l_datRecruitEndDate = l_productUpdateInfo.recruitEndDate;
            }
            else
            {
                l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
            }

            //応募開始日(*1)　@＞　@応募終了日(*2)である場合、
            //エラー『応募開始日と応募終了日の関係が不正です。』
            if (l_datRecruitStartDate != null && l_datRecruitEndDate != null)
            {
                if (l_datRecruitStartDate.compareTo(l_datRecruitEndDate) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02622,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "応募開始日が応募終了日を超えています。");
                }
            }

            //３－２）債券銘柄.債券タイプ == 国内債券の場合、
            if (BondTypeEnum.DOMESTIC_BOND == l_bondProduct.getBondType())
            {
                //３－２－１）債券銘柄.応募開始日(SONAR) != null かつ、
                //債券銘柄更新情報.応募開始日 != nullの場合、
                //以下のチェックを行なう
                if (l_bondProduct.getHostRecruitStartDate() != null &&
                    l_productUpdateInfo.recruitStartDate != null)
                {

                    // 債券銘柄.応募開始日(SONAR) ＞債券銘柄更新情報.応募開始日である場合、
                    // エラー『応募開始日(SONAR)と応募開始日の関係が不正です。』
                    if (l_bondProduct.getHostRecruitStartDate().compareTo(
                        l_productUpdateInfo.recruitStartDate) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02623,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "応募開始日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。");
                    }
                }

                //３－２－２）債券銘柄.応募終了日(SONAR) != null かつ、
                //債券銘柄更新情報.応募終了日 != nullの場合、
                //以下のチェックを行なう。<BR>
                if (l_bondProduct.getHostRecruitEndDate() != null &&
                    l_productUpdateInfo.recruitEndDate != null)
                {
                     //債券銘柄更新情報.応募終了日 ＞ 債券銘柄.応募終了日(SONAR)である場合、
                     //エラー『応募終了日(SONAR)と応募終了日の関係が不正です。』
                    if (l_productUpdateInfo.recruitEndDate.compareTo(
                        l_bondProduct.getHostRecruitEndDate()) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02624,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "応募終了日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。");
                    }
                  }
            }
        }
        // ４）債券銘柄更新情報の買付単価をチェック 
        // ４－１）拡張債券注文マネージャ.validate単価を呼ぶ 
        //  [引数] 
        //  債券銘柄：取得した債券銘柄 
        //  単価　@　@　@：債券銘柄更新情報.買付単価
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_productUpdateInfo.buyPrice);
      
        //５）債券銘柄更新情報の売却単価をチェック 
        //５－１）拡張債券注文マネージャ.validate単価を呼ぶ 
        // [引数] 
        // 債券銘柄：取得した債券銘柄 
        // 単価　@　@　@：債券銘柄更新情報.売却単価 
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_productUpdateInfo.sellPrice);
        
        // ６）債券銘柄更新情報の最高額面と最低額面をチェック
        //６－1）債券銘柄更新情報.最低額面 != null　@又は　@債券銘柄更新情報.最高額面 != nullの場合、
        //        以下のチェックを行なう。
        if (l_productUpdateInfo.minFaceAmount != null || l_productUpdateInfo.maxFaceAmount != null)
        {
            String l_strMinFaceAmount = null;
            String l_strMaxFaceAmount = null;

            //(*1)債券銘柄更新情報.最低額面 != nullの場合、
            //債券銘柄更新情報.最低額面。
            //債券銘柄更新情報.最低額面 == nullの場合
            //債券銘柄.最低額面。
            if (l_productUpdateInfo.minFaceAmount != null)
            {
                l_strMinFaceAmount = l_productUpdateInfo.minFaceAmount;
            }
            else
            {
                l_strMinFaceAmount =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());
            }

            //(*2)債券銘柄更新情報.最高額面 != nullの場合、
            //債券銘柄更新情報.最高額面。
            //債券銘柄更新情報.最高額面 == nullの場合
            //債券銘柄.最高額面
            if (l_productUpdateInfo.maxFaceAmount != null)
            {
                l_strMaxFaceAmount = l_productUpdateInfo.maxFaceAmount;
            }
            else
            {
                l_strMaxFaceAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProduct.getMaxFaceAmount());
            }

            //  ６－1－１）最低額面(*1)　@＞　@最高額面(*2)である場合、
            //　@　@　@　@　@エラー『最高額面と最低額面の関係が不正です。』
            if (l_strMinFaceAmount != null && l_strMaxFaceAmount != null)
            {
                BigDecimal l_bdMinFaceAmount = new BigDecimal(l_strMinFaceAmount);
                BigDecimal l_bdMaxFaceAmount = new BigDecimal(l_strMaxFaceAmount);
                if(l_bdMinFaceAmount.compareTo(l_bdMaxFaceAmount) > 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02625,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "最低額面が最高額面を超えています。");
                }
            }
        }

        //７）応募勧誘形式・応募引受け区分チェック
        //７－１）債券銘柄更新情報.売買区分 == ”応募”の場合、以下のチェックを行なう。
        if (WEB3BondTradeTypeDef.RECRUIT.equals(l_productUpdateInfo.buySellDiv))
        {
            //７－１－１）債券銘柄更新情報.応募勧誘形式 == null または、債券銘柄更新情報.応募引受け区分 == null の場合、 
            //エラー『応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。』をスローする。
            if (l_productUpdateInfo.recruitInvitationForm == null || l_productUpdateInfo.recruitAcceptDiv == null)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03106,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。");
            }
        }
        //７－２）債券銘柄更新情報.売買区分 ≠ ”応募”の場合、以下のチェックを行なう。
        else
        {
            //７－２－１）債券銘柄更新情報.応募勧誘形式 ≠ null または、債券銘柄更新情報.応募引受け区分 ≠ null の場合、 
            //エラー『応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。』をスローする。
            if (l_productUpdateInfo.recruitInvitationForm != null || l_productUpdateInfo.recruitAcceptDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03107,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。");
            }
        }

        //８）受渡日チェック
        //８－１）債券銘柄更新情報 .受渡日 != null　@かつ
        //債券銘柄更新情報 .受渡日 !=債券銘柄. 受渡日の場合、
        //以下のチェックを行なう。
        if (l_productUpdateInfo.deliveryDate != null
            && (WEB3DateUtility.compareToDay(
                l_productUpdateInfo.deliveryDate, l_bondProduct.getDeliveryDate()) != 0))
        {
            //８－１－１） 債券銘柄更新情報 .受渡日が非営業日の場合、
            //エラー「受渡日が非営業日です。」をスローする。
            Timestamp l_tsDeliveryDate =
                new Timestamp(l_productUpdateInfo.deliveryDate.getTime());
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDeliveryDate)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("受渡日が非営業日です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡日が非営業日です。");
            }

            //８－１－２）債券銘柄更新情報 .受渡日＜現在日付の場合、
            //エラー「受渡日が不正です。」をスローする。
            if (WEB3DateUtility.compareToDay(
                l_productUpdateInfo.deliveryDate,
                GtlUtils.getSystemTimestamp()) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("受渡日が不正です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受渡日が不正です。");
            }
            
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get債券銘柄)<BR>
     * 債券銘柄を取得する。 <BR>
     * <BR>
     * １）　@this.get債券銘柄(Institution, String, String)をコールする。<BR>
     * 　@　@[引数] <BR>
     * 　@　@　@Institution：引数.証券会社<BR>
     * 　@　@　@String：引数.銘柄コード(WEB3) <BR>
     * 　@　@　@String："0"<BR>
     * <BR>
     * ２）　@１）で取得した債券銘柄オブジェクトを返す。 <BR>
     * @@param l_institution - (証券会社)<BR>
     * @@param l_strProductCode - (銘柄コード(WEB3))<BR>
     * @@return BondProduct
     * @@throws NotFoundException
     * @@roseuid 44C44AF103CC
     */
    public BondProduct getBondProduct(
        Institution l_institution,
        String l_strProductCode) throws NotFoundException
    {
        return this.getBondProduct(l_institution, l_strProductCode, "0");
    }

    /**
     * (get債券自動約定枠履歴一覧)<BR>
     * 債券自動約定枠履歴一覧を返す。 <BR>
     * <BR>
     * １）　@債券自動約定枠履歴テーブルを検索し、検索結果のListを取得する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@銘柄ID = 引数.債券銘柄.銘柄ID<BR>
     * 　@　@　@オンライン表示区分 = 有効<BR>
     * 　@　@［ソート条件］ <BR>
     * 　@　@　@約定枠更新日付　@降順<BR>
     * <BR>
     * ２）　@Listを返却する。<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF20013
     */
    public List getBondAutoExecLimitHistoryList(BondProduct l_bondProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondAutoExecLimitHistoryList(BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //銘柄ID = 引数.債券銘柄.銘柄ID , オンライン表示区分 = 有効
        String l_strQueryCondition = " product_id = ? and online_disp_div = ? ";

        //約定枠更新日付　@降順
        String l_strOrderBy = " execution_update_date desc ";
        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = new Long(l_bondProduct.getProductId());
        l_objBindVars[1] = WEB3OnlineDispDivDef.VALIDITY;
        List l_lisBondAutoExecLimitHistoryList = new ArrayList();

        try
        {
            // １） QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondAutoExecLimitHistoryList = l_queryProcessor.doFindAllQuery(
                BondAutoExecLimitActionRow.TYPE,
                l_strQueryCondition,
                l_strOrderBy,
                null,
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisBondAutoExecLimitHistoryList;
    }

    /**
     * (get債券銘柄利率一覧)<BR>
     * 債券銘柄利率一覧を返す。 <BR>
     * <BR>
     * １）　@債券銘柄利率テーブルを検索し、検索結果のListを取得する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@銘柄ID = 引数・銘柄.getProductId()<BR>
     * 　@　@［ソート条件］ <BR>
     * 　@　@　@利払日　@降順<BR>
     * <BR>
     * ２）　@Listを返却する。<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF20080
     */
    public List getBondProductCouponList(BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondProductCouponList(BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //銘柄ID = 引数・銘柄.getProductId()
        String l_strQueryCondition = " product_id = ? ";

        //利払日　@降順
        String l_strOrderBy = " interest_payment_day desc ";
        Object[] l_objBindVars = new Object[1];
        l_objBindVars[0] = new Long(l_bondProduct.getProductId());
        List l_lisBondAutoExecLimitHistoryList = new ArrayList();

        try
        {
            // １）　@債券銘柄利率テーブルを検索し、検索結果のListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondAutoExecLimitHistoryList = l_queryProcessor.doFindAllQuery(
                BondProductCouponRow.TYPE,
                l_strQueryCondition,
                l_strOrderBy,
                null,
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBondAutoExecLimitHistoryList;
    }

    /**
     * (get債券銘柄リスト)<BR>
     * 債券銘柄のリストを返却する。<BR>
     * <BR>
     * １）検索条件の補正<BR>
     * <BR>
     * 　@１－1）以下の通りに検索条件文字列を作成する。<BR>
     * 　@　@　@　@　@" 証券会社コード = ? " + 引数.検索条件文字列<BR>
     * <BR>
     * 　@１－２）引数.検索条件データコンテナの先頭に以下を追加する。<BR>
     * 　@　@　@　@　@引数.証券会社コード<BR>
     * <BR>
     * ２）以下の条件で債券銘柄マスタテーブルを検索し、<BR>
     * 　@　@債券銘柄RowオブジェクトのListを取得して返却する。<BR>
     * <BR>　@　@
     *     [検索条件]<BR>
     * 　@　@　@作成した検索条件文字列<BR>
     * 　@　@　@作成した検索条件データコンテナ<BR>
     * <BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@　@引数.ソート条件文字列<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件文字列)<BR>
     * ソート条件文字列<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF200AF
     */
    public List getBondProductList(
        String l_strInstitutionCode,
        String l_strQueryString,
        Object[] l_objQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBondProductList(String, String, Object, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strInstitutionCode == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）検索条件の補正
        //   １－1）以下の通りに検索条件文字列を作成する。
        //   " 証券会社コード = ? " + 引数.検索条件文字列
        if (l_strQueryString != null)
        {
            String l_strTemp = l_strQueryString.trim();
            if ("and".equals(l_strTemp.substring(0,3)))
            {
                l_strQueryString = " institution_code = ? "  + l_strQueryString ;
            }
            else
            {
                l_strQueryString = " institution_code = ?  and "  + l_strQueryString ;
            }
        }
        else
        {
            l_strQueryString = " institution_code = ? ";

        }

        // １－２）引数.検索条件データコンテナの先頭に以下を追加する。
        // 　@ 引数.証券会社コード
        List l_lisContainer = new ArrayList();
        l_lisContainer.add(l_strInstitutionCode);

        if (l_objQueryContainers != null)
        {
            for (int i = 0; i < l_objQueryContainers.length; i++)
            {
                l_lisContainer.add(l_objQueryContainers[i]);
            }
        }

        Object[] l_objBindVars = new Object[l_lisContainer.size()];
        l_lisContainer.toArray(l_objBindVars);

        //return List
        List l_lisBondProductList = new ArrayList();

        // ２）以下の条件で債券銘柄マスタテーブルを検索し
        //　@  債券銘柄RowオブジェクトのListを取得して返却する。
        try
        {
            // １） QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondProductList = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_objBindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisBondProductList;
    }

    /**
     * (update債券銘柄内容)<BR>
     * 債券銘柄DB更新を行う。<BR>
     * <BR>
     * １）this.get債券銘柄(Institution, String)をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@Institution＝引数.証券会社<BR>
     * 　@　@String　@　@　@＝銘柄コード(WEB3)<BR>
     * <BR>
     * ２）債券銘柄Paramsオブジェクトのクローンを生成する。 <BR>
     * <BR>
     * ３）クローンにプロパティをセットする。 <BR>
     * 　@　@※セット内容は、<BR>
     * 　@　@DB更新仕様「債券銘柄登録_債券銘柄マスタテーブルDB更新仕様.xls」を参照<BR>
     * <BR>
     * ４）クローンの内容でDBを更新する。 <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)<BR>
     * @@param l_productUpdateInfo - (債券銘柄更新情報)<BR>
     * 債券銘柄更新情報オブジェクト<BR>
     * @@param l_strAdminCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44C44AF2010D
     */
    public void updateBondProductSpec(
        Institution l_institution,
        String l_strProductCode,
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo,
        String l_strAdminCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateBondProductSpec(Institution, String, WEB3AdminBondProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_productUpdateInfo == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）this.get債券銘柄(Institution, String)をコールする。
        // 　@[引数]
        // 　@　@Institution＝引数.証券会社
        // 　@　@String　@　@　@＝銘柄コード(WEB3)
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)this.getBondProduct(
                l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        BondProductRow l_bondProductRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        BondProductParams l_bondProductParams = new BondProductParams(l_bondProductRow);

        //取扱区分　@　@　@　@　@　@： 債券銘柄更新情報.取扱区分
        l_bondProductParams.setTradeHandleDiv(l_productUpdateInfo.tradeHandleDiv);

        //取扱開始日時　@　@　@　@： 債券銘柄更新情報.取扱開始日時
        l_bondProductParams.setTradeStartDate(l_productUpdateInfo.tradeStartDate);

        //取扱終了日時　@　@　@　@： 債券銘柄更新情報.取扱終了日時
        l_bondProductParams.setTradeEndDate(l_productUpdateInfo.tradeEndDate);

        //応募開始日　@　@　@　@　@： 債券銘柄更新情報.応募開始日
        l_bondProductParams.setRecruitStartDate(l_productUpdateInfo.recruitStartDate);

        //応募終了日　@　@　@　@　@： 債券銘柄更新情報.応募終了日
        l_bondProductParams.setRecruitEndDate(l_productUpdateInfo.recruitEndDate);     

        //売買区分　@　@　@　@　@　@： 債券銘柄更新情報.売買区分
        l_bondProductParams.setTradeType(l_productUpdateInfo.buySellDiv);

        //銘柄名　@　@　@　@　@： 債券銘柄更新情報.銘柄名
        l_bondProductParams.setProductName(l_productUpdateInfo.productName);
        
        //買付単価　@　@　@　@　@　@： 債券銘柄更新情報.買付単価
        if (l_productUpdateInfo.buyPrice != null)
        {
            l_bondProductParams.setBuyPrice(new Double(l_productUpdateInfo.buyPrice));
        }
        else
        {
            l_bondProductParams.setBuyPrice(null);
        }

        //売却単価　@　@　@　@　@　@： 債券銘柄更新情報.売却単価
        if (l_productUpdateInfo.sellPrice != null)
        {
            l_bondProductParams.setSellPrice(new Double(l_productUpdateInfo.sellPrice));
        }
        else
        {
            l_bondProductParams.setSellPrice(null);
        }
        
        //申込単位　@　@　@　@　@　@： 債券銘柄更新情報.申込単位
        l_bondProductParams.setTradeUnit(Double.parseDouble(l_productUpdateInfo.tradeUnit));
        
        //最低額面　@　@　@　@　@　@： 債券銘柄更新情報.最低額面
        l_bondProductParams.setMinFaceAmount(Double.parseDouble(l_productUpdateInfo.minFaceAmount));
        
        //最高額面　@　@　@　@　@　@： 債券銘柄更新情報.最高額面
        if (l_productUpdateInfo.maxFaceAmount != null)
        {
            l_bondProductParams.setMaxFaceAmount(Double.parseDouble(l_productUpdateInfo.maxFaceAmount));
        }
        else
        {
            l_bondProductParams.setMaxFaceAmount(null);
        }

        //カレンダー連携市場コード　@： 債券銘柄更新情報.カレンダー連携市場コード
        l_bondProductParams.setCalLinkedMarketCode(l_productUpdateInfo.calendarLinkedDiv);
        
        //買付受渡日移動日数　@： 債券銘柄更新情報.買付受渡日移動日数
        if (l_productUpdateInfo.buyDeliveryMove != null)
        {
            l_bondProductParams.setBuyDeliveryDateShiftdays(Integer.parseInt(l_productUpdateInfo.buyDeliveryMove));
        }
        else
        {
            l_bondProductParams.setBuyDeliveryDateShiftdays(null);
        }

        //売却受渡日移動日数　@： 債券銘柄更新情報.売却受渡日移動日数       
        if (l_productUpdateInfo.sellDeliveryMove != null)
        {
            l_bondProductParams.setSellDeliveryDateShiftdays(Integer.parseInt(l_productUpdateInfo.sellDeliveryMove));
        }
        else
        {
            l_bondProductParams.setSellDeliveryDateShiftdays(null);
        }

        //自動約定区分　@　@　@　@： 債券銘柄更新情報.自動約定区分
        l_bondProductParams.setAutoExecDiv(l_productUpdateInfo.autoExecDiv);

        //自動約定枠　@　@　@　@　@： 債券銘柄更新情報.自動約定枠
        if (l_productUpdateInfo.autoExecLimit != null)
        {
            l_bondProductParams.setAutoExecLimit(Double.parseDouble(l_productUpdateInfo.autoExecLimit));
        }
        else
        {
            l_bondProductParams.setAutoExecLimit(null);
        }

        //カストディアンコード： 債券銘柄更新情報.カストディアンコード
        l_bondProductParams.setCustodianCode(l_productUpdateInfo.custodianCode);

        //仕入時の為替レート　@： 債券銘柄更新情報.仕入時の為替レート
        if (l_productUpdateInfo.fxRateAtStock != null)
        {
            l_bondProductParams.setBuyingFxRate(Double.parseDouble(l_productUpdateInfo.fxRateAtStock));
        }
        else
        {
            l_bondProductParams.setBuyingFxRate(null);
        }

        //取引時間チェック区分： 債券銘柄更新情報.取引時間チェック区分
        l_bondProductParams.setTradingTimeCheckDiv(l_productUpdateInfo.tradeTimeCheckDiv);

        //仲介手数料率　@　@　@　@： 債券銘柄更新情報.仲介手数料率
        if (l_productUpdateInfo.mediatorCommissionRate != null)
        {
            l_bondProductParams.setMediatorCommissionRate(new Double(l_productUpdateInfo.mediatorCommissionRate));
        }
        else
        {
            l_bondProductParams.setMediatorCommissionRate(null);
        }

        //最終更新者コード　@　@： 引数.管理者コード
        l_bondProductParams.setLastUpdater(l_strAdminCode);

        //　@管理者更新日時　@　@　@： 現在日時
        l_bondProductParams.setAdminLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //　@更新日付　@　@　@　@　@　@： 現在日時
        l_bondProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //応募勧誘形式
        l_bondProductParams.setRecruitInvitationDiv(l_productUpdateInfo.recruitInvitationForm);

        //応募引受け区分
        l_bondProductParams.setRecruitAcceptDiv(l_productUpdateInfo.recruitAcceptDiv);

        //受渡日
        l_bondProductParams.setDeliveryDate(l_productUpdateInfo.deliveryDate);

        //３）クローンの内容でDBを更新する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //スレッド番号を元に生成したPrimaryKeyオブジェクト
            l_queryProcessor.doUpdateQuery(l_bondProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get取扱可能種別コード一覧)<BR>
     * 債券銘柄マスタで取扱可能な種別コードの一覧を検索する。<BR>
     * <BR>
     * １)一時保存用のListを作成。<BR>
     * <BR>
     * ２）銘柄マスタで検索を行う。<BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード＝引数の証券会社コード<BR>
     * 　@取扱区分 != 0：不可<BR>
     * 　@[ソート条件] <BR>
     * 　@種別コード　@昇順 <BR>
     * <BR>
     * ３）検索結果の件数分、下記操作を繰り返し実行<BR>
     * 　@３－１）銘柄の種別コードを取得。<BR>
     * 　@３－２）Listの中に同じ種別コードが存在するかどうかを判断する<BR>
     * 　@３－３）存在しない場合：Listに追加、存在する場合：Listに追加しない<BR>
     * <BR>
     * ４）List結果をString配列に変換し、返す。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     *
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 44C46848017A
     */
    public String[] getTradeHandlingPossibleBondCategCodeList(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeHandlingPossibleBondCategCodeList(String)";
        log.entering(STR_METHOD_NAME);

        //１)一時保存用のListを作成。
        List l_lisReturn = new ArrayList();
        List l_lisTradeHandleDivList = new ArrayList();

        //２）銘柄マスタで検索を行う。
        // 　@[検索条件]
        // 　@証券会社コード＝引数の証券会社コード
        // 　@取扱区分 != 0：不可
        //   　@[ソート条件] 
        //    　@種別コード　@昇順 
        String l_strQueryCondition = " institution_code = ? and trade_handle_div != ? ";
        String l_strSortCond = " " + WEB3BondProductSortKeyItemDef.BOND_CATEG_CODE + " asc";
        
        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = l_strInstitutionCode;
        l_objBindVars[1] = WEB3TradeHandleDivDef.DISABLED;

        try
        {
            // １） QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisTradeHandleDivList = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQueryCondition,
                l_strSortCond,
                null,
                l_objBindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        int l_intSize = 0;
        if (l_lisTradeHandleDivList != null && !l_lisTradeHandleDivList.isEmpty())
        {
            l_intSize = l_lisTradeHandleDivList.size();
        }

        //３）検索結果の件数分、下記操作を繰り返し実行
        for (int i = 0; i < l_intSize; i++)
        {
            //３－１）銘柄の種別コードを取得。
            boolean l_blnFlag = false;
            String l_strTemp = ((BondProductRow)l_lisTradeHandleDivList.get(i)).getBondCategCode();
            if (l_strTemp == null)
            {
            	continue;
            }
            for (int j = 0; j < i; j++)
            {
                //３－２）Listの中に同じ種別コードが存在するかどうかを判断する
                if (l_strTemp.equals(((BondProductRow)l_lisTradeHandleDivList.get(j)).getBondCategCode()))
                {
                    l_blnFlag = true;
                }
            }
            //３－３）存在しない場合：Listに追加、存在する場合：Listに追加しない
            if (!l_blnFlag)
            {
                l_lisReturn.add(l_strTemp);
            }
        }

        //４）List結果をString配列に変換し、返す。
        String[] l_strs = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strs);
        return l_strs;
    }

    /**
     * (get債券銘柄)<BR>
     * （getBondProduct(Institution, String, String)のオーバーライド） <BR>
     * <BR>
     * 債券銘柄を取得する。 <BR>
     * <BR>
     * １）　@債券銘柄マスタテーブル債券銘柄Paramsを取得する。 <BR>
     * －以下の条件で債券銘柄テーブルを検索し、債券銘柄Paramsを取得する。 <BR>
     * 　@　@[検索条件] <BR>
     * 　@　@　@証券会社コード：引数.証券会社.get証券会社コード() <BR>
     * 　@　@　@銘柄コード(WEB3)：引数.銘柄コード(WEB3) <BR>
     * 　@　@　@回号コード(WEB3)：引数.回号コード <BR>
     * <BR>
     * ２）　@債券銘柄オブジェクトを取得する。 <BR>
     * 　@this.to銘柄()をコールして、債券銘柄オブジェクトを取得する。<BR>
     * 　@[to銘柄に渡すパラメタ］ <BR>
     * 　@　@Paramsオブジェクト： 1)で取得した債券銘柄Paramsオブジェクト<BR>
     *<BR>
     * ３）　@取得した債券銘柄オブジェクトを返す。 <BR>
     * @@param l_institution - (証券会社)<BR>
     * @@param l_strProductCode - (銘柄コード(WEB3))<BR>
     * @@param l_strIssueCode - (回号コード)<BR>
     * 回号コード<BR>
     * @@return BondProduct
     * @@throws NotFoundException
     * @@roseuid 44D67EBE00D9
     */
    public BondProduct getBondProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strIssueCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBondProduct(Institution, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strInstitutionCode = l_institution.getInstitutionCode();
        BondProductRow l_row = null;
        try
        {
            l_row =
                BondProductDao.findRowByInstitutionCodeProductCodeProductIssueCode(
                l_strInstitutionCode,
                l_strProductCode,
                l_strIssueCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null)
        {
            String msg = "No Bond product found with institutionCode,productCode and productIssueCode :"
                + l_strInstitutionCode + "," +
                l_strProductCode + "," +
                l_strIssueCode;
            throw new NotFoundException(msg);
        }

        // ２）　@債券銘柄オブジェクトを取得する。
        //  this.to銘柄()をコールして、債券銘柄オブジェクトを取得する。
        BondProductParams l_bondProductParams = new BondProductParams(l_row);
        BondProduct l_bondProduct = (BondProduct)this.toProduct(l_bondProductParams);

        //３）　@取得した債券銘柄オブジェクトを返す。
        return l_bondProduct;
    }

    /**
     * (validate銘柄内容)<BR>
     * 国内債券銘柄更新情報のチェックを行なう。<BR>
     * <BR>
     * １）this.get債券銘柄（）より債券銘柄を取得する。<BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@　@銘柄ID　@：引数.銘柄ID <BR>
     * <BR>
     * ２）応募開始日（WEB3)チェック <BR>
     * 　@　@２－１）国内債券銘柄更新情報.応募開始日（WEB3)が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（WEB3）が非営業日です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02856<BR>
     * <BR>
     * 　@　@２－２）債券銘柄.応募開始日(SONAR） ＞<BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募開始日（WEB3)の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（SONAR)と応募開始日（WEB3)<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02857<BR>
     * <BR>
     * ３）応募終了日（WEB3)チェック <BR>
     * 　@　@３－１）国内債券銘柄更新情報.応募終了日（WEB3)が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募終了日（WEB3)が非営業日です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02858<BR>
     * <BR>
     * 　@　@３－２）債券銘柄.応募終了日（SONAR） ≦<BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募終了日（WEB3)の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募終了日（SONAR)と応募終了日（WEB3)<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02859<BR>
     * <BR>
     * 　@　@３－３）国内債券銘柄更新情報.応募開始日（WEB3) ＞<BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募終了日（WEB3)の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（WEB3)と応募終了日（WEB3）<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02860<BR>
     * <BR>
     * ４）応募開始日（インターネット）チェック <BR>
     * 　@　@４－１）国内債券銘柄更新情報.応募開始日（インターネット）が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（インターネット）が非営業日です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02861<BR>
     * <BR>
     * 　@　@４－２）国内債券銘柄更新情報.応募開始日（WEB3) ＞<BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募開始日（インターネット）の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（WEB3)と応募開始日（インターネット）<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02862<BR>
     * <BR>
     * ５）応募終了日（インターネット）チェック <BR>
     * 　@　@５－１）国内債券銘柄更新情報.応募終了日（インターネット）が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募終了日（インターネット）が非営業日です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02863<BR>
     * <BR>
     * 　@　@５－２）国内債券銘柄更新情報.応募終了日（WEB3) ＜<BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募終了日（インターネット）の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募終了日（WEB3)と応募終了日（インターネット）<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02864<BR>
     * <BR>
     * 　@　@５－３）国内債券銘柄更新情報.応募開始日（インターネット) ＞ <BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.応募終了日（インターネット）の場合、<BR>
     * 　@　@　@　@　@　@エラー「応募開始日（インターネット)と応募終了日（インターネット）<BR>
     * 　@　@　@　@　@　@の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02868<BR>
     * <BR>
     * ６）受渡日チェック <BR>
     * 　@　@６－１）国内債券銘柄更新情報.受渡日が非営業日の場合、<BR>
     * 　@　@　@　@　@　@エラー「受渡日が非営業日です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02336<BR>
     * <BR>
     * 　@　@６－２）国内債券銘柄更新情報.応募終了日（WEB3） + 1営業日 ＞ <BR>
     * 　@　@　@　@　@　@国内債券銘柄更新情報.受渡日 の場合、<BR>
     * 　@　@　@　@　@　@エラー「受渡日が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02865<BR>
     * <BR>
     * 　@　@６－３）国内債券銘柄更新情報.受渡日 ≧ 債券銘柄.発行日の場合、<BR>
     * 　@　@　@　@　@　@エラー「受渡日が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02865<BR>
     * <BR>
     * ７）申込単位チェック <BR>
     * 　@　@債券銘柄.最小発行券種 ≠ nullの場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@７－１）国内債券銘柄更新情報.申込単位が債券銘柄.最小発行券種 × 1000<BR>
     * 　@　@　@　@　@　@の整数倍でない場合、<BR>
     * 　@　@　@　@　@　@エラー「申込単位が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02866<BR>
     * <BR>
     * ８）最低額面チェック <BR>
     * 　@　@債券銘柄.最小発行券種 ≠ nullの場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@８－１）国内債券銘柄更新情報.最低額面 ＜ 債券銘柄.最小発行券種 × 1000<BR>
     * 　@　@　@　@　@　@ の場合、<BR>
     * 　@　@　@　@　@　@エラー「最低額面が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02867<BR>
     * <BR>
     * ９）最高額面チェック <BR>
     * 　@　@９－１）国内債券銘柄更新情報.最低額面 ＞ 国内債券銘柄更新情報.最高額面の場合、<BR>
     * 　@　@　@　@　@　@エラー「最低額面と最高額面の関係が不正です。」をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02625<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_bondDomesticProductUpdateInfo - (国内債券銘柄更新情報)<BR>
     * 国内債券銘柄更新情報<BR>
     * @@throws WEB3BaseException
     */
    public void validateProductSpec(
        String l_strProductId,
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateProductSpec(String, WEB3BondDomesticProductUpdateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_strProductId == null || l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）this.get債券銘柄（）より債券銘柄を取得する。
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)this.getBondProduct(Long.parseLong(l_strProductId));

        //２）応募開始日（WEB3)チェック
        //　@　@２－１）国内債券銘柄更新情報.応募開始日（WEB3)が非営業日の場合、
        //　@　@　@　@　@　@エラー「応募開始日（WEB3）が非営業日です。」をスローする。
        Timestamp l_tsRecruitStartDateWEB3 =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitStartDateWEB3.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitStartDateWEB3)))
        {
            log.debug("応募開始日（WEB3）が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02856,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（WEB3）が非営業日です。");
        }

        //　@　@２－２）債券銘柄.応募開始日(SONAR） ＞ 国内債券銘柄更新情報.応募開始日（WEB3)の場合、
        //　@　@　@　@　@　@エラー「応募開始日（SONAR)と応募開始日（WEB3)の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondProduct.getHostRecruitStartDate(),
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3) > 0)
        {
            log.debug("応募開始日（SONAR)と応募開始日（WEB3)の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02857,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（SONAR)と応募開始日（WEB3)の関係が不正です。");
        }

        //３）応募終了日（WEB3)チェック
        //　@　@３－１）国内債券銘柄更新情報.応募終了日（WEB3)が非営業日の場合、
        //　@　@　@　@　@　@エラー「応募終了日（WEB3)が非営業日です。」をスローする。
        Timestamp l_tsRecruitEndDateWEB3 =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitEndDateWEB3.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitEndDateWEB3)))
        {
            log.debug("応募終了日（WEB3)が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02858,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（WEB3)が非営業日です。");
        }

        //　@　@３－２）債券銘柄.応募終了日（SONAR） ≦ 国内債券銘柄更新情報.応募終了日（WEB3)の場合、
        //　@　@　@　@　@　@エラー「応募終了日（SONAR)と応募終了日（WEB3)の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondProduct.getHostRecruitEndDate(),
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3) <= 0)
        {
            log.debug("応募終了日（SONAR)と応募終了日（WEB3)の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02859,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（SONAR)と応募終了日（WEB3)の関係が不正です。");
        }

        //　@　@３－３）国内債券銘柄更新情報.応募開始日（WEB3) ＞ 国内債券銘柄更新情報.応募終了日（WEB3)の場合、
        //　@　@　@　@　@　@エラー「応募開始日（WEB3)と応募終了日（WEB3）の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3) > 0)
        {
            log.debug("応募開始日（WEB3)と応募終了日（WEB3）の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02860,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（WEB3)と応募終了日（WEB3）の関係が不正です。");
        }

        //４）応募開始日（インターネット）チェック
        //　@　@４－１）国内債券銘柄更新情報.応募開始日（インターネット）が非営業日の場合、
        //　@　@　@　@　@　@エラー「応募開始日（インターネット）が非営業日です。」をスローする。
        Timestamp l_tsRecruitStartDateInterNet =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitStartDateInterNet.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruitStartDateInterNet)))
        {
            log.debug("応募開始日（インターネット）が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02861,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（インターネット）が非営業日です。");
        }

        //　@　@４－２）国内債券銘柄更新情報.応募開始日（WEB3) ＞ 国内債券銘柄更新情報.応募開始日（インターネット）の場合、
        //　@　@　@　@　@　@エラー「応募開始日（WEB3)と応募開始日（インターネット）の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet) > 0)
        {
            log.debug("応募開始日（WEB3)と応募開始日（インターネット）の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02862,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（WEB3)と応募開始日（インターネット）の関係が不正です。");
        }

        //５）応募終了日（インターネット）チェック
        //　@　@５－１）国内債券銘柄更新情報.応募終了日（インターネット）が非営業日の場合、
        //　@　@　@　@　@　@エラー「応募終了日（インターネット）が非営業日です。」をスローする。
        Timestamp l_tsRecruiEndDateInterNet =
            new Timestamp(l_bondDomesticProductUpdateInfo.recruitEndDateInterNet.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsRecruiEndDateInterNet)))
        {
            log.debug("応募終了日（インターネット）が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02863,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（インターネット）が非営業日です。");
        }

        //　@　@５－２）国内債券銘柄更新情報.応募終了日（WEB3) ＜ 国内債券銘柄更新情報.応募終了日（インターネット）の場合、
        //　@　@　@　@　@　@エラー「応募終了日（WEB3)と応募終了日（インターネット）の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3,
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet) < 0)
        {
            log.debug("応募終了日（WEB3)と応募終了日（インターネット）の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02864,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募終了日（WEB3)と応募終了日（インターネット）の関係が不正です。");
        }

        //　@　@５－３）国内債券銘柄更新情報.応募開始日（インターネット) ＞ 国内債券銘柄更新情報.応募終了日（インターネット）の場合、
        //　@　@　@　@　@　@エラー「応募開始日（インターネット)と応募終了日（インターネット）の関係が不正です。」をスローする。
        if (WEB3DateUtility.compareToMinute(
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet,
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet) > 0)
        {
            log.debug("応募開始日（インターネット)と応募終了日（インターネット）の関係が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02868,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募開始日（インターネット)と応募終了日（インターネット）の関係が不正です。");
        }

        //６）受渡日チェック
        //　@　@６－１）国内債券銘柄更新情報.受渡日が非営業日の場合、
        //　@　@　@　@　@　@エラー「受渡日が非営業日です。」をスローする。
        Timestamp l_tsDeliveryDate =
            new Timestamp(l_bondDomesticProductUpdateInfo.deliveryDate.getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsDeliveryDate)))
        {
            log.debug("受渡日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が非営業日です。");
        }

        //　@　@６－２）国内債券銘柄更新情報.応募終了日（WEB3） + 1営業日 ＞ 国内債券銘柄更新情報.受渡日 の場合、
        //　@　@　@　@　@　@エラー「受渡日が不正です。」をスローする。
        Date l_datNextDeliveryDate = new WEB3GentradeBizDate(l_tsRecruitEndDateWEB3).roll(1);
        if (WEB3DateUtility.compareToDay(
            l_datNextDeliveryDate,
            l_bondDomesticProductUpdateInfo.deliveryDate) > 0)
        {
            log.debug("受渡日が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が不正です。");
        }

        //　@　@６－３）国内債券銘柄更新情報.受渡日 ≧ 債券銘柄.発行日の場合、
        //　@　@　@　@　@　@エラー「受渡日が不正です。」をスローする。
        if (WEB3DateUtility.compareToDay(
            l_bondDomesticProductUpdateInfo.deliveryDate,
            l_bondProduct.getIssueDate()) >= 0)
        {
            log.debug("受渡日が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が不正です。");
        }

        //７）申込単位チェック
        //　@　@債券銘柄.最小発行券種 ≠ nullの場合、以下のチェックを行なう。
        if (l_bondProduct.getMinIssueCouponType() != null)
        {
            //７－１）国内債券銘柄更新情報.申込単位が債券銘柄.最小発行券種 × 1000 の整数倍でない場合、
            //　@　@　@　@エラー「申込単位が不正です。」をスローする。
            long l_lngApplyUnit = Long.parseLong(l_bondDomesticProductUpdateInfo.applyUnit);
            long l_lngMinIssueCouponType =
                (new BigDecimal(l_bondProduct.getMinIssueCouponType()).multiply(
                    new BigDecimal("1000"))).longValue();
            if (l_lngApplyUnit % l_lngMinIssueCouponType != 0)
            {
                log.debug("申込単位が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02866,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "申込単位が不正です。");
            }
        }

        //国内債券銘柄更新情報.最低額面
        BigDecimal l_bdMinFaceAmount = new BigDecimal(l_bondDomesticProductUpdateInfo.minFaceAmount);

        //８）最低額面チェック
        //　@　@債券銘柄.最小発行券種 ≠ nullの場合、以下のチェックを行なう。
        if (l_bondProduct.getMinIssueCouponType() != null)
        {
            //８－１）国内債券銘柄更新情報.最低額面 ＜ 債券銘柄.最小発行券種 × 1000 の場合、
            //　@　@　@　@エラー「最低額面が不正です。」をスローする。
            BigDecimal l_bdMinIssueCouponType =
                new BigDecimal(l_bondProduct.getMinIssueCouponType()).multiply(new BigDecimal("1000"));
            if (l_bdMinFaceAmount.compareTo(l_bdMinIssueCouponType) < 0)
            {
                log.debug("最低額面が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02867,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "最低額面が不正です。");
            }
        }

        //９）最高額面チェック
        //　@　@９－１）国内債券銘柄更新情報.最低額面 ＞ 国内債券銘柄更新情報.最高額面の場合、
        //　@　@　@　@　@　@エラー「最低額面と最高額面の関係が不正です。」をスローする。
        BigDecimal l_bdMaxFaceAmount = new BigDecimal(l_bondDomesticProductUpdateInfo.maxFaceAmount);
        if (l_bdMinFaceAmount.compareTo(l_bdMaxFaceAmount) > 0)
        {
            log.debug("最低額面が最高額面を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02625,
                this.getClass().getName() + STR_METHOD_NAME,
                "最低額面が最高額面を超えています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update債券銘柄内容)<BR>
     * 国内債券銘柄更新情報より債券銘柄マスタテーブルの更新処理を行う。<BR>
     * <BR>
     * １）this.get債券銘柄()より債券銘柄を取得する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@　@銘柄ID　@：引数.銘柄ID<BR>
     * <BR>
     * ２）取得した債券銘柄Paramsオブジェクトのクローンを生成する。<BR>
     * <BR>
     * ３）クローンに以下の通り、プロパティをセットする。<BR>
     *    ※DB更新仕様参照<BR>
     * <BR>
     * ４）クローンの内容で債券銘柄マスタテーブルを更新する。<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_bondDomesticProductUpdateInfo - (国内債券銘柄更新情報)<BR>
     * 国内債券銘柄更新情報
     * @@param l_administratorCode - (管理者コード)<BR>
     * 管理者コード
     * @@throws WEB3BaseException
     */
    public void updateBondProductContent(
        String l_strProductId,
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo,
        String l_administratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateBondProductContent(String, WEB3BondDomesticProductUpdateInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strProductId == null || l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）this.get債券銘柄()より債券銘柄を取得する。
        BondProduct l_bondProduct = this.getBondProduct(Long.parseLong(l_strProductId));

        // ２）取得した債券銘柄Paramsオブジェクトのクローンを生成する。
        BondProductRow l_bondProductRow =
            (BondProductRow)l_bondProduct.getDataSourceObject();
        BondProductParams l_bondProductParams = new BondProductParams(l_bondProductRow);

        // ３）クローンに以下の通り、プロパティをセットする。
        // 取扱区分
        l_bondProductParams.setTradeHandleDiv(l_bondDomesticProductUpdateInfo.tradeHandleDiv);
        // 取扱開始日時
        // 国内債券銘柄更新情報.応募開始日（WEB3）
        l_bondProductParams.setTradeStartDate(l_bondDomesticProductUpdateInfo.recruitStartDateWEB3);
        // 取扱終了日時
        l_bondProductParams.setTradeEndDate(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3);

        // 応募開始日
        // 国内債券銘柄更新情報.応募開始日（インターネット）
        l_bondProductParams.setRecruitStartDate(
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet);
        // 応募終了日
        l_bondProductParams.setRecruitEndDate(
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet);

        // 売買区分
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setTradeType(l_bondDomesticProductUpdateInfo.dealingType);
        // 銘柄名
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setProductName(l_bondDomesticProductUpdateInfo.productNameWEB3);
        //申込単位
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setTradeUnit(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.applyUnit));
        // 最低額面
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setMinFaceAmount(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.minFaceAmount));
        // 最高額面
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setMaxFaceAmount(
            Double.parseDouble(l_bondDomesticProductUpdateInfo.maxFaceAmount));
        // 最終更新者コード
        // 管理者.管理者コード
        l_bondProductParams.setLastUpdater(l_administratorCode);
        // 管理者更新日付
        l_bondProductParams.setAdminLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        // 更新日付
        // 現在時刻
        l_bondProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        // 受渡日
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setDeliveryDate(l_bondDomesticProductUpdateInfo.deliveryDate);
        // 目論見書閲覧チェック区分
        // 国内債券銘柄更新情報の同項目
        l_bondProductParams.setProspectusCheckDiv(
            l_bondDomesticProductUpdateInfo.prospectusCheckDiv);

        // ４）クローンの内容で債券銘柄マスタテーブルを更新する。
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doUpdateQuery(l_bondProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get発行券種)<BR>
     * 発行券種を取得する。<BR>
     * <BR>
     * １）以下の検索条件で債券発行券種テーブルを検索し、Listを取得する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@　@銘柄ID = 引数.銘柄ID<BR>
     * <BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@　@　@発行券種　@昇順<BR>
     * <BR>
     * ２）String[]を生成する。<BR>
     * <BR>
     * ３）１）で取得したListの要素数分以下のLOOP処理を行なう。<BR>
     * <BR>
     * 　@　@３－１）String[]に債券発行券種.発行券種の値を追加する。<BR>
     * <BR>
     * ４）作成したString[]を返却する。<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public String[] getIssueCouponType(String l_strProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIssueCouponType(String)";
        log.entering(STR_METHOD_NAME);

        List l_lisResults = null;
        try
        {
            //１）以下の検索条件で債券発行券種テーブルを検索し、Listを取得する。
            //   [検索条件]
            //    　@銘柄ID = 引数.銘柄ID
            //   [ソート条件]
            //      発行券種　@昇順
            String l_strProductIdString = " product_id = ? ";
            String l_strIssueCouponTypeString = "issue_coupon_type asc";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    BondIssueCouponTypeRow.TYPE,
                    l_strProductIdString,
                    l_strIssueCouponTypeString,
                    null,
                    new Object[]{l_strProductId});
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intCnt = 0;
        if (!l_lisResults.isEmpty())
        {
            l_intCnt = l_lisResults.size();
        }

        //２）String[]を生成する。
        String[] l_strIssueCouponTypes = new String[l_intCnt];

        //３）１）で取得したListの要素数分以下のLOOP処理を行なう。
        BondIssueCouponTypeRow l_couponTypeRow = null;
        for (int i = 0; i < l_intCnt; i++)
        {
            //３－１）String[]に債券発行券種.発行券種の値を追加する。
            l_couponTypeRow = (BondIssueCouponTypeRow)l_lisResults.get(i);
            l_strIssueCouponTypes[i] = l_couponTypeRow.getIssueCouponType();
        }

        //４）作成したString[]を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strIssueCouponTypes;
    }

    /**
     * (get債券注文受付履歴一覧)<BR>
     * 債券注文受付履歴行オブジェクトのリストを取得する。<BR>
     *<BR>
     * １）債券注文受付履歴テーブルを検索し、債券注文受付履歴行<BR>
     * 　@　@オブジェクトのリストを取得する。<BR>
     *<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@　@銘柄ID = 引数.銘柄ID<BR>
     * 　@　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@　@部店コード = 引数.部店コード<BR>
     *<BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@　@　@注文受付日付　@　@昇順<BR>
     *<BR>
     * ２）取得した債券注文受付履歴行オブジェクトのリストを返却する。<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getBondOrderReceiveHistoryList(
        String l_strProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBondOrderReceiveHistoryList(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //債券注文受付履歴テーブルを検索し、債券注文受付履歴行オブ
        //ジェクトのリストを取得する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_sbWhere.append(" and institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");

        Object[] l_whereValues = new Object[3];
        l_whereValues[0] = l_strProductId;
        l_whereValues[1] = l_strInstitutionCode;
        l_whereValues[2] = l_strBranchCode;

        //[ソート条件]
        //注文受付日付　@　@昇順
        String l_strOrderBy = " order_accept_date asc ";

        List l_lisBondOrderAcceptAction = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderAcceptAction = l_queryProcessor.doFindAllQuery(
                BondOrderAcceptActionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_whereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得した債券注文受付履歴行オブジェクトのリストを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisBondOrderAcceptAction;
    }

    /**
     * (create国内債券部店別応募枠情報)<BR>
     * 国内債券部店別応募枠情報の配列を作成する。<BR>
     *<BR>
     * １）債券部店別応募枠Rowのリストを取得。<BR>
     *<BR>
     * 　@　@１－１）以下の検索条件で債券部店別応募枠テーブルを検索し、<BR>
     * 　@　@　@　@　@　@債券部店別応募枠Rowのリストを取得する。<BR>
     *<BR>
     * 　@　@　@　@　@　@[検索条件]<BR>
     * 　@　@　@　@　@　@　@銘柄ID　@＝　@引数.銘柄ID<BR>
     * 　@　@　@　@　@　@　@証券会社コード　@＝　@引数.証券会社コード<BR>
     * 　@　@　@　@　@　@　@(*1)部店コード　@＝　@引数.部店コード<BR>
     *<BR>
     * 　@　@　@　@　@　@[ソート条件]<BR>
     * 　@　@　@　@　@　@　@部店コード　@昇順<BR>
     *<BR>
     * 　@　@　@　@　@　@　@(*1)引数.部店コード == null の場合、<BR>
     * 　@　@　@　@　@　@　@部店コードの検索条件は作成しない。<BR>
     *<BR>
     * 　@　@１－２）債券部店別応募枠テーブルの検索結果が0件の場合、<BR>
     * 　@　@　@　@　@　@要素数0の国内債券部店別応募枠情報の配列を返却する。<BR>
     *<BR>
     * ２）国内債券部店別応募枠情報の配列を作成。<BR>
     * 　@　@取得した債券部店別応募枠Rowのリストの要素数分LOOP処理を行ない、<BR>
     * 　@　@国内債券部店別応募枠情報の配列を作成する。<BR>
     *<BR>
     * 　@　@２－１）国内債券部店別応募枠情報インスタンスを生成する。<BR>
     *<BR>
     * 　@　@２－２）注文金額合計を取得。<BR>
     * 　@　@　@　@　@　@拡張債券注文マネージャ.calc国内債券注文金額合計()をコールし、<BR>
     * 　@　@　@　@　@　@注文金額合計を取得する。<BR>
     *<BR>
     * 　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@銘柄ID　@：　@債券部店別応募枠Row.銘柄ID<BR>
     * 　@　@　@　@　@　@　@　@証券会社コード　@：　@債券部店別応募枠Row.証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@部店コード　@：　@債券部店別応募枠Row.部店コード<BR>
     *<BR>
     * 　@　@２－３）生成した国内債券部店別応募枠情報インスタンスに<BR>
     * 　@　@　@　@　@　@以下の通り、プロパティをセットする。<BR>
     *<BR>
     * 　@　@　@　@　@　@　@　@注文金額合計　@＝　@calc国内債券注文金額合計()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@WEB3応募枠　@　@＝　@債券部店別応募枠Row.応募枠<BR>
     * 　@　@　@　@　@　@　@　@部店コード　@　@　@　@＝　@債券部店別応募枠Row.部店コード<BR>
     *<BR>
     * ３）作成した国内債券部店別応募枠情報の配列を返却する。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitInfo[] - 国内債券部店別応募枠情報[]
     * @@throws WEB3BaseException
     */
    public WEB3BondDomesticBranchRecruitLimitInfo[] createAdminBondDomesticRecruitLimitInfo(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAdminBondDomesticRecruitLimitInfo(long, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）債券部店別応募枠Rowのリストを取得。
        //１－１）以下の検索条件で債券部店別応募枠テーブルを検索し、
        //　@　@　@　@債券部店別応募枠Rowのリストを取得する。
        //[検索条件]
        //  銘柄ID　@＝　@引数.銘柄ID
        //  証券会社コード　@＝　@引数.証券会社コード
        //  (*1)部店コード　@＝　@引数.部店コード
        List l_lisValues = new ArrayList();

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_lisValues.add(new Long(l_lngProductId));
        l_sbWhere.append(" AND institution_code = ? ");
        l_lisValues.add(l_strInstitutionCode);

        //(*1)引数.部店コード == null の場合、部店コードの検索条件は作成しない。
        if (l_strBranchCode != null)
        {
            l_sbWhere.append(" AND  branch_code = ? ");
            l_lisValues.add(l_strBranchCode);
        }

        Object[] l_whereValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_whereValues);

        //[ソート条件]
        //部店コード　@昇順
        String l_strOrderBy = " branch_code ASC ";

        //債券部店別応募枠Rowのリストを取得する。
        List l_lisBondBranchRecruitLimit = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondBranchRecruitLimit = l_queryProcessor.doFindAllQuery(
                BondBranchRecruitLimitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_whereValues);

            //１－２）債券部店別応募枠テーブルの検索結果が0件の場合、
            //　@　@　@　@要素数0の国内債券部店別応募枠情報の配列を返却する。
            if (l_lisBondBranchRecruitLimit.isEmpty())
            {
                WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos =
                    new WEB3BondDomesticBranchRecruitLimitInfo[]{};
                return l_bondDomesticBranchRecruitLimitInfos;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）国内債券部店別応募枠情報の配列を作成。
        //取得した債券部店別応募枠Rowのリストの要素数分LOOP処理を行ない、
        //国内債券部店別応募枠情報の配列を作成する。
        List l_lisBondDomesticBranchRecruitLimitInfos = new ArrayList();
        Iterator l_iteratorBondBranchRecruitLimit = l_lisBondBranchRecruitLimit.iterator();

        while (l_iteratorBondBranchRecruitLimit.hasNext())
        {
            BondBranchRecruitLimitRow l_bondBranchRecruitLimitRow =
                (BondBranchRecruitLimitRow)l_iteratorBondBranchRecruitLimit.next();

            //２－１）国内債券部店別応募枠情報インスタンスを生成する。
            WEB3BondDomesticBranchRecruitLimitInfo l_bondDomesticBranchRecruitLimitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo();

            //２－２）注文金額合計を取得。
            //　@[引数]
            //　@　@　@銘柄ID　@：　@債券部店別応募枠Row.銘柄ID
            //　@　@　@証券会社コード　@：　@債券部店別応募枠Row.証券会社コード
            //　@　@　@部店コード　@：　@債券部店別応募枠Row.部店コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.BOND);
            WEB3BondOrderManager l_bondOrderManager =
                (WEB3BondOrderManager)l_tradingModule.getOrderManager();
            double l_dblCalcBondDomesticOrderAmountTotal = l_bondOrderManager.calcBondDomesticOrderAmountTotal(
                l_bondBranchRecruitLimitRow.getProductId(),
                l_bondBranchRecruitLimitRow.getInstitutionCode(),
                l_bondBranchRecruitLimitRow.getBranchCode());

            //２－３）生成した国内債券部店別応募枠情報インスタンスに
            //   以下の通り、プロパティをセットする。
            //注文金額合計　@＝　@calc国内債券注文金額合計()の戻り値
            l_bondDomesticBranchRecruitLimitInfo.orderAmountTotal =
                WEB3StringTypeUtility.formatNumber(l_dblCalcBondDomesticOrderAmountTotal);
            //WEB3応募枠　@　@＝　@債券部店別応募枠Row.応募枠
            l_bondDomesticBranchRecruitLimitInfo.web3RecruitLimit =
                WEB3StringTypeUtility.formatNumber(l_bondBranchRecruitLimitRow.getRecruitLimit());
            //部店コード　@　@　@　@＝　@債券部店別応募枠Row.部店コード
            l_bondDomesticBranchRecruitLimitInfo.branchCode = l_bondBranchRecruitLimitRow.getBranchCode();

            l_lisBondDomesticBranchRecruitLimitInfos.add(l_bondDomesticBranchRecruitLimitInfo);
        }

        //３）作成した国内債券部店別応募枠情報の配列を返却する。
        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos =
            new WEB3BondDomesticBranchRecruitLimitInfo[l_lisBondDomesticBranchRecruitLimitInfos.size()];
        l_lisBondDomesticBranchRecruitLimitInfos.toArray(l_bondDomesticBranchRecruitLimitInfos);

        log.exiting(STR_METHOD_NAME);
        return l_bondDomesticBranchRecruitLimitInfos;
    }
}
@
