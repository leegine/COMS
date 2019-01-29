head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携共通(WEB3SrvRegiStreamCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 武波 (中訊) 新規作成 モデルNo.368,No.374,No.380,No.381,No.383,No.384,No.385,No.390,No.391
Revision History : 2008/06/19 車進 (中訊) モデルNo.392
Revision History : 2008/06/20 武波 (中訊) モデルNo.393
Revision History : 2008/06/20 武波 (中訊) モデルNo.395
Revision History : 2008/06/27 武波 (中訊) モデルNo.396
Revision History : 2008/07/18 馮海濤 (中訊) モデルNo.398
*/
package webbroker3.srvregi;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductPK;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalHowToDivValueDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * サービス利用債券連携共通<BR>
 * (債券連携共通に関する処理を行うメソッドを集めたクラス)<BR>
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3SrvRegiStreamCommon
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamCommon.class);

    /**
     * サービス利用債券連携共通<BR>
     */
    public WEB3SrvRegiStreamCommon()
    {

    }

    /**
     * (get債券残高リスト)<BR>
     * 「保有資産テーブル」と「債券銘柄マスタテーブル」を検索し、債券残高リストを生成して返却する。<BR>
     * <BR>
     * １）以下の条件で「保有資産テーブル」を検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@　@口座ID = 引数.補助口座.口座ID and<BR>
     * 　@　@銘柄タイプ = 2(債券)<BR>
     * <BR>
     * ２）戻り値の生成<BR>
     * 　@2-1）「保有資産テーブル」の検索結果が0件の場合、nullを返却する。<BR>
     * <BR>
     * 　@2-2）「保有資産テーブル」の検索結果が1件以上ある場合、以下の処理を行う。<BR>
     * <BR>
     * ３）銘柄コード、売付可能残高数量、取得金額を取得し、債券残高リストを生成する。<BR>
     * 　@3-1）「保有資産テーブル」より取得した件数分、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@3-2）債券銘柄マスタテーブルのレコードを取得。<BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@債券銘柄マスタテーブル.銘柄ID = 取得保有資産テーブル.銘柄ID<BR>
     * <BR>
     * 　@3-3）取得した「債券銘柄マスタテーブル」のレコードの種別コードが<BR>
     * 　@　@「転換社債・ワラント債(1043)」以外の場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@①@銘柄コード = 債券銘柄マスタテーブル.銘柄コード <BR>
     * <BR>
     * 　@　@○国内債券の場合（①@銘柄コードの先頭2桁目が数値）<BR>
     * 　@　@　@②売付可能残高数量 = 保有資産テーブル.数量 × 1000<BR>
     * <BR>
     * 　@　@　@③取得金額 = 保有資産テーブル.簿価（簿価単価計算用） × 1000<BR>
     * <BR>
     * 　@　@○外国債券（円価建）の場合（①@銘柄コードの先頭2桁目が "T0"）<BR>
     * 　@　@　@②売付可能残高数量 = 保有資産テーブル.数量 <BR>
     * <BR>
     * 　@　@　@③取得金額 = 保有資産テーブル.簿価（簿価単価計算用）<BR>
     * <BR>
     * 　@　@○外国債券（外貨建）（①@銘柄コードの先頭2桁目が "T0"以外の英字）<BR>
     * 　@　@　@②売付可能残高数量 = 保有資産テーブル.数量<BR>
     * <BR>
     * 　@　@　@③取得金額 = 保有資産テーブル.数量（簿価単価計算用） × 保有資産テーブル.入力簿価単価<BR>
     * <BR>
     * <BR>
     * 　@3-4）債券残高リスト作成<BR>
     * 　@　@　@銘柄コード、売付可能残高数量、取得金額の順で(*)区切りでセットする。<BR>
     * 　@　@　@複数の場合コロン（:)で区切る。<BR>
     * 　@　@　@※取得金額は小数点以下を切り捨ててセットする。<BR>
     * <BR>
     * 　@　@　@例）以下２つの残高の場合<BR>
     * 　@　@　@銘柄コード0123456、数量2000、取得金額3000000<BR>
     * 　@　@　@銘柄コード0987654、数量5000、取得金額5000000<BR>
     * <BR>
     * 　@　@　@[ 0123456*2000*3000000:0987654*5000*5000000 ]<BR>
     * <BR>
     * ４）生成した債券残高リストを返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBondBalanceList(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondBalanceList(SubAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strBondBalanceList = "";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //１）以下の条件で「保有資産テーブル」を検索する。
            //口座ID = 引数.補助口座.口座ID and
            //銘柄タイプ = 2(債券)
            String l_strAssetQuery = "account_id=? and product_type=?";

            List l_lisAssetQuerys = new ArrayList();
            l_lisAssetQuerys.add(new Long(l_subAccount.getAccountId()));
            l_lisAssetQuerys.add(ProductTypeEnum.BOND);

            Object[] l_objAssetQuerys = new Object[l_lisAssetQuerys.size()];
            l_lisAssetQuerys.toArray(l_objAssetQuerys);

            List l_lisAssets = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                l_strAssetQuery,
                l_objAssetQuerys);

            //２）戻り値の生成
            //2-1）「保有資産テーブル」の検索結果が0件の場合、nullを返却する。
            if (l_lisAssets.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //2-2）「保有資産テーブル」の検索結果が1件以上ある場合、以下の処理を行う。
            else
            {
                //３）銘柄コード、売付可能残高数量、取得金額を取得し、債券残高リストを生成する。
                int l_intAssetRow = l_lisAssets.size();
                for (int i = 0; i < l_intAssetRow; i++)
                {
                    //3-1）「保有資産テーブル」より取得した件数分、以下の処理を繰り返す。
                    AssetRow l_assetRow = (AssetRow)l_lisAssets.get(i);

                    //3-2）債券銘柄マスタテーブルのレコードを取得。
                    //[検索条件]
                    //債券銘柄マスタテーブル.銘柄ID = 取得保有資産テーブル.銘柄ID
                    long l_lngProductId = l_assetRow.getProductId();
                    BondProductPK l_bondProductPK = new BondProductPK(l_lngProductId);
                    BondProductRow l_bondProductRow =
                        BondProductDao.findRowByPk(l_bondProductPK);

                    //3-3）取得した「債券銘柄マスタテーブル」のレコードの種別コードが
                    //「転換社債・ワラント債(1043)」以外の場合、以下の処理を行う。
                    if (!WEB3BondCategCodeDef.CONVERSION_COMPANY_BOND_WARRANT_BOND.equals(
                        l_bondProductRow.getBondCategCode()))
                    {
                        //①@銘柄コード = 債券銘柄マスタテーブル.銘柄コード
                        String l_strProductCode = l_bondProductRow.getProductCode();
                        String l_strProductCodeSubstring = l_strProductCode.substring(0, 2);
                        //○国内債券の場合（①@銘柄コードの先頭2桁目が数値）
                        //②売付可能残高数量 = (保有資産テーブル.数量 + 保有資産テーブル.売付不能数量) × 1000
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));        
                        BigDecimal l_bdQuantityCannotSell = new BigDecimal(String.valueOf(l_assetRow.getQuantityCannotSell()));        
                        //売付可能残高数量 = 保有資産テーブル.数量 + 保有資産テーブル.売付不能数量
                        BigDecimal l_bdSellPossBalanceQuantity = l_bdQuantity.add(l_bdQuantityCannotSell);
                        //保有資産テーブル.数量（簿価単価計算用)
                        BigDecimal l_bdQuantityForBookValue = new BigDecimal(l_assetRow.getQuantityForBookValue());
                        
                        String l_strSellPossBalanceQuantity = "";
                        String l_strQuantityMultiplyInputBookValue = "";
                        //○国内債券の場合（①@銘柄コードの先頭2桁目が数値）
                        if (WEB3StringTypeUtility.isNumber(l_strProductCodeSubstring))
                        {
                            //②売付可能残高数量 = (保有資産テーブル.数量 + 保有資産テーブル.売付不能数量) × 1000
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdSellPossBalanceQuantity.multiply(new BigDecimal(1000)).doubleValue());

                            //③取得金額 = 保有資産テーブル.簿価（簿価単価計算用） × 1000
                            BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetRow.getBookValue()));
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_bdBookValue.multiply(new BigDecimal(1000)).doubleValue());
                        }
                        //○外国債券（円価建）の場合（①@銘柄コードの先頭2桁目が "T0"）
                        else if (WEB3CurrencyCodeDef.T0.equals(l_strProductCodeSubstring))
                        {
                            //②売付可能残高数量 = 保有資産テーブル.数量 + 保有資産テーブル.売付不能数量
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(l_bdSellPossBalanceQuantity.doubleValue());

                            //③取得金額 = 保有資産テーブル.簿価（簿価単価計算用）
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_assetRow.getBookValue());
                        }
                        //○外国債券（外貨建）（①@銘柄コードの先頭2桁目が "T0"以外の英字）
                        else if (!WEB3CurrencyCodeDef.T0.equals(l_strProductCodeSubstring))
                        {
                            //②売付可能残高数量 = 保有資産テーブル.数量 + 保有資産テーブル.売付不能数量
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(l_bdSellPossBalanceQuantity.doubleValue());

                            //③取得金額 = 保有資産テーブル.数量（簿価単価計算用） × 保有資産テーブル.入力簿価単価 
                            BigDecimal l_bdInputBookValue = new BigDecimal(String.valueOf(l_assetRow.getInputBookValue()));
                            BigDecimal l_bdQuantityMultiplyInputBookValue = l_bdQuantityForBookValue.multiply(l_bdInputBookValue);
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_bdQuantityMultiplyInputBookValue.doubleValue());
                        }

                        l_strBondBalanceList += l_strProductCode;

                        l_strBondBalanceList += "*" + l_strSellPossBalanceQuantity;

                        //取得金額は小数点以下を切り捨ててセットする
                        l_strQuantityMultiplyInputBookValue = l_strQuantityMultiplyInputBookValue.split("\\.")[0];
                        l_strBondBalanceList += "*" + l_strQuantityMultiplyInputBookValue;

                        if (i != l_intAssetRow - 1)
                        {
                            l_strBondBalanceList += ":";
                        }
                    }
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if ((!WEB3StringTypeUtility.isEmptyOrBlank(l_strBondBalanceList))
            && l_strBondBalanceList.lastIndexOf(":") == (l_strBondBalanceList.length() - 1))
        {
            l_strBondBalanceList =
                l_strBondBalanceList.substring(0, l_strBondBalanceList.length() - 1);
        }
        //5）生成した債券残高リストを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strBondBalanceList;
    }

    /**
     * (get余力残高リスト)<BR>
     * T+0～T+5の余力残高データを取得し、返却する。<BR>
     * <BR>
     * 1）取引時間モデル.営業日計算を生成<BR>
     * 　@　@[引数]<BR>
     * 　@　@業務日付：GtlUtils.getTradingSystem().getBizDate()の戻り値（Timestamp型に変換）<BR>
     * <BR>
     * 2）以下の処理をループし、T+0～T+5のデータを取得<BR>
     * 　@　@・取引余力サービス.get出金可能額～0補正無し～()をコール<BR>
     * 　@　@[get出金可能額()に渡す引数]<BR>
     * 　@　@補助口座:引数.補助口座<BR>
     * 　@　@受渡日:営業日計算.roll(）の戻り値<BR>
     * 　@　@[roll(）に渡す引数]<BR>
     * 　@　@例）Ｔ + 0の場合： 0<BR>
     * 　@　@　@　@　@　@　@<BR>
     * 3）戻り値の生成<BR>
     * 　@Ｔ＋０からＴ＋５までをセット。区切り文字はコロン(:)<BR>
     * <BR>
     * 　@例）T+0 = 0, T+1 = 1000, T+2 = 2000, T+3 = 3000, T+4 = 4000, T+5 = 2500の場合<BR>
     * 　@[0:1000:2000:3000:4000:2500]<BR>
     * <BR>
     * 4）3）で生成した余力残高データを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_tsSystemTimestamp - (現在日付)<BR>
     * 現在日付<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTradingPowerBalanceList(
        SubAccount l_subAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingPowerBalanceList(SubAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strTradingPowerBalanceList = "";
        
        //1）取引時間モデル.営業日計算を生成
        //業務日付:GtlUtils.getTradingSystem().getBizDate()の戻り値（Timestamp型に変換）
        Timestamp l_businessdate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_businessdate);

        //2）以下の処理をループし、T+0～T+5のデータを取得
        //　@・取引余力サービス.get出金可能額～0補正無し～()をコール
        //補助口座:引数.補助口座
        //受渡日:営業日計算.roll(）の戻り値
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        for (int i = 0; i < 6; i++)
        {
            double l_dblPaymentTradingPowerForCheck =
                l_service.getPaymentTradingPowerForCheck(
                    (WEB3GentradeSubAccount)l_subAccount, l_gentradeBizDate.roll(i));

            //3）戻り値の生成
            //Ｔ＋０からＴ＋５までをセット。区切り文字はコロン(:)
            l_strTradingPowerBalanceList +=
                WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPowerForCheck);

            if (i != 5)
            {
                l_strTradingPowerBalanceList += ":";
            }
        }

        //4）3）で生成した余力残高データを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strTradingPowerBalanceList;
    }

    /**
     * (get情報サービスリスト)<BR>
     * 現在有効なサービスを取得し、返却する。<BR>
     * <BR>
     * 1）以下の条件で「サービス申込登録テーブル」を検索する。<BR>
     * 　@　@[引数] <BR>
     * 　@　@証券会社コード：引数.証券会社コード <BR>
     * 　@　@部店コード：引数.部店コード <BR>
     * 　@　@口座コード：引数.口座コード <BR>
     * 　@　@適用終了日≧引数.現在日時<BR>
     * 　@　@取消区分：0<BR>
     * 　@　@有効区分：0<BR>
     * <BR>
     * 2）戻り値の生成<BR>
     * 　@2-1）取得できない場合、nullを返却。<BR>
     * <BR>
     * 　@2-2）１件以上取得出来た場合、以下の処理を行う。<BR>
     * 　@　@コロン(:)区切りでリストに追加する。<BR>
     * 　@　@※一つのサービス区分に対してレコードが複数取得出来た場合、<BR>
     * 　@　@　@リストに追加するのは１件のみ<BR>
     * <BR>
     * 　@　@例）02、02、03、04、08、10、12、12、36の場合。<BR>
     * 　@　@　@　@[02:03:04:08:10:12:36]<BR>
     * 　@　@<BR>
     * 　@　@<BR>
     * 3）2)で生成した情報サービスリストを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_tsSystemTimestamp - (現在日付)<BR>
     * 現在日付<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getInfoServiceList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        Timestamp l_tsSystemTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInfoServiceList(String, String, String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        String l_strInfoServiceList = null;
        //1）以下の条件で「サービス申込登録テーブル」を検索する。
        //証券会社コード：引数.証券会社コード
        //部店コード：引数.部店コード
        //口座コード：引数.口座コード
        //適用終了日≧引数.現在日時
        //取消区分：0
        //有効区分：0
        String l_strQuery = " institution_code = ? and branch_code = ? and account_code = ?"
            + " and appli_end_date >= ? and cancel_div = ? and effective_div = ?";

        String l_strSystemTimestamp = WEB3DateUtility.formatDate(
            l_tsSystemTimestamp,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Object[] l_objQuerys =  {
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            WEB3DateUtility.getDate(
                l_strSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
            WEB3EffectiveDivDef.EFFECTIVE};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisSrvRegiApplicationRows = l_queryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_strQuery,
                l_objQuerys);

            //2）戻り値の生成
            //2-1）取得できない場合、nullを返却。
            if (l_lisSrvRegiApplicationRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //2-2）１件以上取得出来た場合、以下の処理を行う。
            //コロン(:)区切りでリストに追加する。
            //※一つのサービス区分に対してレコードが複数取得出来た場合、リストに追加するのは１件のみ
            //例）02、02、03、04、08、10、12、12、36の場合。
            //[02:03:04:08:10:12:36]
            int l_intSrvRegiApplicationRowsSize = l_lisSrvRegiApplicationRows.size();
            LinkedHashSet l_linkedHashSetSrvDivs = new LinkedHashSet();
            for (int i = 0; i < l_intSrvRegiApplicationRowsSize; i++)
            {
                SrvRegiApplicationRow l_srvRegiApplicationRow = (SrvRegiApplicationRow)l_lisSrvRegiApplicationRows.get(i);
                l_linkedHashSetSrvDivs.add(l_srvRegiApplicationRow.getSrvDiv());
            }

            Iterator l_itSrvDivs = l_linkedHashSetSrvDivs.iterator();
            if (l_itSrvDivs.hasNext())
            {
                l_strInfoServiceList = (String)l_itSrvDivs.next();
            }

            while (l_itSrvDivs.hasNext())
            {
                l_strInfoServiceList += ":" + (String)l_itSrvDivs.next();
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3）2)で生成した情報サービスリストを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strInfoServiceList;
    }

    /**
     * (get債券連携用暗号化パスワード)<BR>
     * 債券連携用の暗号化パスワードを取得する。<BR>
     * <BR>
     * １）引数.扱者コード = nullの場合、以下の処理を行う。<BR>
     * 　@　@①@Web3パスワードの取得<BR>
     * 　@　@　@　@1-1）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@　@[get顧客()に指定する引数]<BR>
     * 　@　@　@　@　@証券会社コード：this.証券会社コード<BR>
     * 　@　@　@　@　@部店コード：this.部店コード<BR>
     * 　@　@　@　@　@口座コード：this.顧客コード<BR>
     * <BR>
     * 　@　@　@　@1-2）OpLoginSecurityServiceより、ログインタイプ属性を取得する。<BR>
     * <BR>
     * 　@　@　@　@1-3）ログインタイプ属性.属性名 = 取引パスワード設定<BR>
     * 　@　@　@　@　@　@（TRADING_PWD_ENV）の属性値により以下の処理を分岐<BR>
     * <BR>
     * 　@　@　@　@　@○属性値が”0：DEFAULT（取引パスワード項目を使用しない）”の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・顧客.getログインＩＤ()をコールし、顧客のログインＩＤを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・OpLoginAdminServiceImpl.getLoginAttributes()をコールし、<BR>
     * 　@　@　@　@　@　@　@　@　@　@ログイン属性を取得する。<BR>
     * 　@　@　@　@　@　@　@　@[getLoginAttributes()に指定する引数]<BR>
     * 　@　@　@　@　@　@　@　@arg0：ログインＩＤ：getログインＩＤ()の戻り値<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@上記が取得出来ない場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・ログイン属性.get()をコールし、ログイン属性より復号化可能パスワードを取得する。<BR>
     * 　@　@　@　@　@　@　@　@[get()に指定する引数]<BR>
     * 　@　@　@　@　@　@　@　@ログイン属性名.復号化可能パスワード（WEB3_ENCRYPTED_PASSWORD）<BR>
     * <BR>
     * 　@　@　@　@　@○属性値が ”1：取引パスワード使用”の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・顧客オブジェクトより、取引パスワードを取得する。<BR>
     * <BR>
     * 　@　@　@②パスワードの復号化<BR>
     * 　@　@　@WEB3Crypt.decrypt()をコールし、パスワードの復号化を行う。<BR>
     * 　@　@　@[decrypt()に指定する引数]<BR>
     * 　@　@　@String l_str: 1-3)にて取得したパスワード<BR>
     * <BR>
     * 　@　@　@③サービス利用起動情報サービスImpl.get制御日付()をコールし、<BR>
     * 　@　@　@　@現在日付に独自の修正を加える。<BR>
     * 　@　@　@[get制御日付()に指定する引数]<BR>
     * 　@　@　@現在日付：引数.現在日付<BR>
     * <BR>
     * 　@　@　@④サービス情報管理.getサービスマスタ()をコールし、<BR>
     * 　@　@　@　@サービスマスターオブジェクトを取得する。<BR>
     * 　@　@　@[getサービスマスタ()に指定する引数]<BR>
     * 　@　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@　@サービス区分：引数.サービス区分<BR>
     * 　@　@　@is行ロック：false<BR>
     * <BR>
     * 　@　@　@⑤サービスマスターオブジェクト.getハッシュ値一覧()をコールする。<BR>
     * <BR>
     * 　@　@　@⑥文字列の配列に以下の順でセットする。<BR>
     * 　@　@　@WEB3Crypt.decrypt()の戻り値<BR>
     * 　@　@　@サービス利用起動情報サービスImpl.get制御日付()の戻り値（'YYYYMMDD'に変換）<BR>
     * 　@　@　@サービスマスターオブジェクト.getハッシュ値一覧()戻り値<BR>
     * 　@　@　@　@　@.getサービス利用キー（）の戻り値（配列の数分セットする。）<BR>
     * <BR>
     * 　@　@　@⑦ハッシュ計算を行う。<BR>
     * 　@　@　@WEB3StringTypeUtility.createHashValue()をコールする。<BR>
     * 　@　@　@[createHashValue()に指定する引数]<BR>
     * 　@　@　@ハッシュ計算方式：SHA-1<BR>
     * 　@　@　@計算対象：⑥にて生成した文字列の配列。<BR>
     * <BR>
     * ２）戻り値の返却<BR>
     * 　@2-1)引数.扱者コード != nullの場合、nullを返却する。<BR>
     * 　@2-2)引数.扱者コード = nullの場合、ハッシュ計算したパスワードを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 口座コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_tsSystemTimestamp - (現在日付)<BR>
     * 現在日付<BR>
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBondOrgUsedCryptPassword(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsSystemTimestamp,
        String l_strTraderCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBondOrgUsedCryptPassword(String, String, String, String, Timestamp, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strTraderCode != null)
        {
            //2-1)引数.扱者コード != nullの場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１）引数.扱者コード = nullの場合、以下の処理を行う。
        //①@Web3パスワードの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //1-1）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_accMgr.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);

        String l_strBondOrgUsedCryptPassword = null;

        //1-2）OpLoginSecurityServiceより、ログインタイプ属性を取得する。
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService)Services.getService(
            OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

        String l_strPassword = null;
        WEB3Crypt l_crypt = new WEB3Crypt();
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //○属性値が”0：DEFAULT（取引パスワード項目を使用しない）”の場合
            //・顧客.getログインＩＤ()をコールし、顧客のログインＩＤを取得する。
            long l_lngLoginId = l_gentradeMainAccount.getLoginId();

            OpLoginAdminService l_opLoginAdminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            Map l_mapLoginAttributes = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);

            //上記が取得出来ない場合、例外をスローする。
            if (l_mapLoginAttributes == null
                || l_mapLoginAttributes.isEmpty())
            {
                log.debug("ログイン属性が取得できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03094,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ログイン属性が取得できません。");
            }

            l_strPassword = (String)l_mapLoginAttributes.get(WEB3LoginAttributeKeyDef.PASSWORD);
        }
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            //○属性値が ”1：取引パスワード使用”の場合
            //顧客オブジェクトより、取引パスワードを取得する。
            l_strPassword = l_gentradeMainAccount.getTradingPassword();
        }

        //②パスワードの復号化
        //WEB3Crypt.decrypt()をコールし、パスワードの復号化を行う。
        //String l_str: 1-3)にて取得したパスワード
        String l_strDecryptPassword = l_crypt.decrypt(l_strPassword);

        //③サービス利用起動情報サービスImpl.get制御日付()をコールし、現在日付に独自の修正を加える。
        //現在日付：引数.現在日付
        WEB3SrvRegiStartInfoService l_srvRegiStartInfoService =
            (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        Date l_datControlTimestamp = l_srvRegiStartInfoService.getControlTimestamp(l_tsSystemTimestamp);

        //④サービス情報管理.getサービスマスタ()をコールし、サービスマスターオブジェクトを取得する。
        //証券会社コード：引数.証券会社コード
        //サービス区分：引数.サービス区分
        //is行ロック：false
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        //⑤サービスマスターオブジェクト.getハッシュ値一覧()をコールする。
        WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKeys =
            l_srvRegiServiceMaster.getHashList();

        //⑥文字列の配列に以下の順でセットする。
        //WEB3Crypt.decrypt()の戻り値
        //サービス利用起動情報サービスImpl.get制御日付()の戻り値（'YYYYMMDD'に変換）
        //サービスマスターオブジェクト.getハッシュ値一覧()戻り値.getサービス利用キー（）の戻り値（配列の数分セットする。）
        List l_lisCreateHashValues = new ArrayList();
        l_lisCreateHashValues.add(l_strDecryptPassword);
        l_lisCreateHashValues.add(
            WEB3DateUtility.formatDate(
                l_datControlTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        int l_intSrvRegiServiceUseKeyLength = l_srvRegiServiceUseKeys.length;

        for (int i = 0; i < l_intSrvRegiServiceUseKeyLength; i++)
        {
            l_lisCreateHashValues.add(l_srvRegiServiceUseKeys[i].getSrvUseKey());
        }

        String[] l_strCreateHashValues = new String[l_lisCreateHashValues.size()];
        l_lisCreateHashValues.toArray(l_strCreateHashValues);

        //⑦ハッシュ計算を行う。
        //WEB3StringTypeUtility.createHashValue()をコールする。
        //ハッシュ計算方式：SHA-1
        //計算対象：6）にて生成した文字列。
        l_strBondOrgUsedCryptPassword =
            WEB3StringTypeUtility.createHashValue(
                WEB3SrvRegiHashCalHowToDivValueDef.SHA_1, l_strCreateHashValues);

        //2-2)引数.扱者コード = nullの場合、ハッシュ計算したパスワードを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strBondOrgUsedCryptPassword;
    }

    /**
     * (validate債券連携余力チェック)<BR>
     * 取引余力残高が十分あるかを判定する。<BR>
     * <BR>
     * シーケンス図「（サービス利用）validate債券連携余力チェック」参照<BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_01187 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * 利用料金<BR>
     * @@param l_tsDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_tsOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException
     */
    public void validateBondOrgTradingPowerCheck(
        WEB3GentradeSubAccount l_subAccount,
        Trader l_trader,
        double l_dblUseAmt,
        Timestamp l_tsDeliveryDate,
        Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBondOrgTradingPowerCheck(WEB3GentradeSubAccount, Trader, double, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //拡張入出金注文マネージャ取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //get商品ID(証券会社 : Institution)
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //入出金注文内容
        //代理入力者 ：引数.代理入力者オブジェクト
        //注文種別 ：1001（出金注文）
        //振替タイプ ：2（出金）
        //商品ID ：get商品ID()の戻り値
        //金額 ：引数.利用料金
        //記述 ：null
        //振替予定日 ：引数.受渡日
        //決済機@関ID ：null
        //注文ID ：null
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblUseAmt,
                null,
                l_tsDeliveryDate,
                null,
                null);

        //サービス利用新規注文更新インタセプタ
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor =
            new WEB3SrvRegiNewOrderUpdateInterceptor();

        //set受渡日
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsDeliveryDate);

        //set発注日
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_tsOrderBizDate);

        //validate取引余力(補助口座 : 補助口座, 
        //注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult = l_service.validateTradingPower(
            l_subAccount,
            new Object[]{l_srvRegiNewOrderUpdateInterceptor},
            new Object[]{l_aioNewOrderSpec},
            OrderTypeEnum.CASH_OUT,
            false);

        //is判定フラグ( )
        boolean l_blnResultFlg = l_tradingPowerResult.isResultFlg();

        //is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする。
        if (!l_blnResultFlg)
        {
            log.debug("余力残高エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + "." + STR_METHOD_NAME,
                "余力残高エラー。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate債券銘柄コード)<BR>
     * 「債券銘柄マスタテーブル」を検索し、<BR>
     * 債券連携レスポンスの銘柄コードが存在するかチェックする。<BR>
     * <BR>
     * １）以下の条件で「債券銘柄マスタテーブル」を検索する。<BR>
     * 　@doFindAllQuery()をコールする。<BR>
     * 　@[引数] <BR>
     * 　@　@Rowタイプ：BondProductRow.TYPE <BR>
     * 　@　@Where："institution_code = ? and product_code = ?"<BR>
     * 　@　@リスト：以下の項目のリスト<BR>
     * 　@　@引数.証券会社コード<BR>
     * 　@　@引数.銘柄コード<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社 = 引数.証券会社コード and<BR>
     * 　@　@銘柄コード = 引数.銘柄コード<BR>
     * <BR>
     * <BR>
     * ２）戻り値の生成<BR>
     * 　@2-1）「債券銘柄マスタテーブル」の検索結果が0件の場合、falseを返却する。<BR>
     * <BR>
     * 　@2-2）「債券銘柄マスタテーブル」の検索結果が1件以上ある場合、trueを返却する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException
     */
    public boolean validateBondProductCode(
        String l_strProductCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBondProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        String l_strQuery = " institution_code = ? and product_code = ? ";

        Object[] l_objQuerys = {l_strInstitutionCode, l_strProductCode};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisBondProductRows = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQuery,
                l_objQuerys);

            if (l_lisBondProductRows.isEmpty())
            {
                //「債券銘柄マスタテーブル」の検索結果が0件の場合、falseを返却する。
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //「債券銘柄マスタテーブル」の検索結果が1件以上ある場合、trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}

@
