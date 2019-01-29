head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 部店(WEB3GentradeBranch.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
Revesion History : 2004/06/23 李強(中訊) get市場警告文表示 先物オプションを追加
Revesion History : 2004/11/22 鄒政 (中訊) is金販法@実施()メソッドを削除
Revesion History : 2005/07/08 孟東 (中訊) get市場警告文表示()に外株のロジックを追加
Revesion History : 2005/08/22 孟東 (中訊) validate年齢下限値()を追加
Revesion History : 2005/08/22 孟東 (中訊) validate年齢上限値()を追加
Revesion History : 2005/09/26 孟東 (中訊) is投信手数料計算()を追加
Revesion History : 2005/09/26 孟東 (中訊) is投信乗換先買付最低金額チェック実施()を追加
Revesion History : 2005/09/26 孟東 (中訊) get投信源泉徴収拘束率()を追加
Revesion History : 2005/10/17 孟東 (中訊) is投信解約時出金注文生成()を追加
Revesion History : 2006/01/11 凌建平 (中訊)【共通】仕様変更管理モデルNo.171
Revesion History : 2006/01/17 凌建平 (中訊)【共通】仕様変更管理モデルNo.172
Revesion History : 2006/03/28 凌建平 (中訊)【共通】仕様変更管理モデルNo.182
Revesion History : 2006/06/26 栄イ (中訊)【共通】仕様変更管理モデルNo.191
Revesion History : 2006/11/17 栄イ (中訊)【共通】仕様変更管理モデルNo.215、217
Revesion History : 2007/06/08 キョウ再平 (中訊)【共通】仕様変更管理モデルNo.242
Revesion History : 2006/06/16 栄イ (中訊)【共通】仕様変更管理モデルNo.245
Revesion History : 2006/07/04 栄イ (中訊)【共通】仕様変更管理モデルNo.260
Revesion History : 2006/07/05 栄イ (中訊)【共通】仕様変更管理モデルNo.262
Revesion History : 2007/11/22 栄イ (中訊)【共通】仕様変更管理モデルNo.290、291
Revesion History : 2008/03/12 栄イ (中訊)【共通】仕様変更管理モデルNo.320
Revesion History : 2009/02/13 趙林鵬 (中訊)【共通】仕様変更管理モデルNo.338
Revesion History : 2009/03/18 趙林鵬 (中訊)【共通】仕様変更管理モデルNo.340
Revesion History : 2010/02/23 趙林鵬 (中訊)【共通】仕様変更管理モデルNo.351
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BatoFlagDef;
import webbroker3.common.define.WEB3BranchNameSerialNoDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuyingMinimumCheckDef;
import webbroker3.common.define.WEB3CommissionCalcDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3EqtypeFinalDayWithRightDef;
import webbroker3.common.define.WEB3EveningSessionDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3FxMrfAccountCheckDef;
import webbroker3.common.define.WEB3IfodepositLackchargeNonManagementDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MartCanDealtDef;
import webbroker3.common.define.WEB3PaymentOrderCreateDef;
import webbroker3.common.define.WEB3TriggerorderSucorderCarryoverDef;
import webbroker3.gentrade.data.BranchMarketDealtCondDao;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (部店)<BR>
 * <BR> 
 * 証券会社の部署（部店）を表現する。<BR>
 * xTrade提供の項目の他に、会社、部店で異なる情報（取扱可能な市場、取扱可能な執行条
 * <BR>
 * 件など）を拡張する。<BR>
 * xTradeのBranchを拡張したクラス。<BR>
 * <BR> 
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl
 */
public class WEB3GentradeBranch
    extends BranchImpl
    implements WEB3EnforcementDef, WEB3MartCanDealtDef, WEB3ListTypeDef
{

    /**
     * 証券会社コード <BR>
     */
    private String institutionCode;

    /**
     * 部店コード <BR>
     */
    private String branchCode;

    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranch.class);

    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_institution （証券）会社オブジェクト<BR>
     * @@param l_strBranchCode 部店コード<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException 
     * 該当するデータが見つからない場合<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの通信に失敗した場合<BR>
     * @@roseuid 403496E800C4
     */
    public WEB3GentradeBranch(
        Institution l_institution,
        String l_strBranchCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institution, l_strBranchCode);
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * @@param l_lngBranchID 部店ID<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataFindException 
     * 該当するデータが見つからない場合<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQLの実行に失敗した場合<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DBサーバとの通信に失敗した場合<BR>
     * @@roseuid 403496E702F6
     */
    public WEB3GentradeBranch(long l_lngBranchID)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_lngBranchID);
    }

    /**
     * 部店市場取扱条件テーブルを検索し、該当するRowオブジェクトを返す。<BR>
     * 証券会社コード、部店コード、引数の市場コードで検索する。<BR>
     * <BR> 
     * @@param l_strMarketCode 市場コード
     * @@return BranchMarketDealtCondRowオブジェクト
     * @@throws WEB3BaseException 
     * 部店市場取扱条件テーブルのアクセスに失敗した場合
     * @@roseuid 403496E80392
     */
    private BranchMarketDealtCondRow getBranchMarketDealtCondRow(String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketDealtCondRow(String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row = null; //部店市場取扱条件Row
        try
        {
            l_row =
                BranchMarketDealtCondDao.findRowByPk(
                    getInstitution().getInstitutionCode(),
                    getBranchCode(),
                    l_strMarketCode);
        }
        catch (DataFindException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_row;
    }

    /**
     * (get売買限度単位)<BR>
     *<BR>
     * 部店毎に設定する売買限度単位を取得する。<BR>
     *<BR> 
     * １）　@部店市場取扱条件テーブル検索<BR>
     * 　@本オブジェクトの証券会社コード、部店コード、引数の市場コードにて<BR>
     * 　@「部店市場取扱条件テーブル」を検索する。<BR>
     *<BR> 
     * ２）　@限度単位取得<BR>
     * 　@以下の通り判定し、限度単位値を返却する。<BR>
     * 
     * 　@－引数の上場区分が一部上場の場合は”売買限度単位（一部上場）”を返却する。<BR>
     * 
     * 　@－引数の上場区分が二部上場の場合は”売買限度単位（二部上場）”を返却する。<BR>
     * 
     * 　@－引数の上場区分が上記以外の場合は”売買限度単位（一部上場）”を返却する。<BR>
     * <BR>
     * @@param l_strMarketCode - 市場コード <BR>
     * （市場コードは市場種類インタフェイスにてコード定義） <BR>
     * 
     * @@param l_strListingDivision - (上場区分) <BR>
     * 株式取引銘柄マスタにて定義されている上場区分。 <BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4017687F00D3
     */
    public double getDealingLimitUnit(
        String l_strMarketCode,
        String l_strListingDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDealingLimitUnit(String,String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row =
            getBranchMarketDealtCondRow(l_strMarketCode);

        if (FIRST_SECTION.equals(l_strListingDivision))
        {
            // 上場区分が一部上場の場合
            log.info("上場区分が一部上場の場合");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit1stSec();
        }
        else if (SECOND_SECTION.equals(l_strListingDivision))
        {
            // 上場区分が二部上場の場合
            log.info("上場区分が二部上場の場合");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit2ndSec();
        }
        else
        {
            // 上場区分が上記以外の場合
            log.info("上場区分が上記以外の場合");
            log.exiting(STR_METHOD_NAME);
            return l_row.getLimitedUnit1stSec();
        }
    }
    
    /**
     * (get売買限度単位)<BR>
     *<BR>
     *部店＋市場＋弁済毎に設定する売買限度単位を取得する。<BR>
     *※this.get売買単位(市場コード, 上場区分)の、 <BR>
     *   「（部店市場弁済別）取扱条件」クラス使用版。 <BR>
     *  <BR>
     * １）　@（部店市場弁済別）取扱オブジェクト取得 <BR>
     *  （部店市場弁済別）取扱条件オブジェクトを生成する。<BR>
     *  [コンストラクタの引数] <BR>
     *  証券会社コード： 本オブジェクトの証券会社コード <BR>
     *  部店コード： 本オブジェクトの部店コード <BR>
     *  市場コード： 引数.市場コード <BR>
     *  弁済区分：　@引数.弁済区分 <BR>
     *  弁済期限値：　@引数.弁済期限値 <BR>
     *  <BR>
     * ２）　@限度単位取得 <BR>
     *  （部店市場弁済別）取扱条件オブジェクト.get売買限度 <BR>
     *   単位()を返却する。<BR>
     *  [get売買限度単位()の引数] <BR>
     *  引数.上場区分 <BR>
     *  引数.is新規建 <BR>
     *  引数.is買建 <BR>
     *  <BR>
     * @@param l_strMarketCode - 市場コード <BR>
     * @@param l_strListingDivision - 上場区分 <BR>
     * @@param l_strRepaymentDiv - 弁済区分 <BR>
     * @@param l_dbRepaymentNum - 弁済期限値 <BR> 
     * @@param l_blnIsOpenContract - is新規建 <BR> 
     * @@param l_blnIsBuyOrder - is買建 <BR> 
     * @@return double <BR> 
     * @@throws WEB3SystemLayerException <BR> 
     */
    public double getDealingLimitUnit(
        String l_strMarketCode,
        String l_strListingDivision,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyOrder) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getDealingLimitUnit(String,String,String,double,boolean,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）（部店市場弁済別）取扱オブジェクト取得
        WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
            new WEB3GentradeBranchMarketRepayDealtCond(
                this.getInstitution().getInstitutionCode(),
                this.getBranchCode(),
                l_strMarketCode,
                l_strRepaymentDiv,
                l_dbRepaymentNum
            );
        
        //２）　@限度単位取得
        double l_dblLimitUnit = 
            l_branchMarketRepayDealtCond.getDealingLimitUnit(l_strListingDivision,l_blnIsOpenContract,l_blnIsBuyOrder);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblLimitUnit;
    }
    
    
    /**
     * (is取扱可能市場) <BR>
     * 指定市場が当該部店の取扱可能市場かを判定する。 <BR>
     * <BR>
     * １）　@（部店市場別）取扱オブジェクト取得 <BR>
     * 　@（部店市場別）取扱条件オブジェクトを生成する。 <BR>
     * <BR>
     * [コンストラクタの引数] <BR>
     * 証券会社コード： 本オブジェクトの証券会社コード <BR>
     * 部店コード： 本オブジェクトの部店コード <BR>
     * 市場： 引数.市場コード <BR>
     * <BR>
     * ２）　@取扱可能判定 <BR>
     * 　@（部店市場別）取扱条件.is取扱可能()を返却する。 <BR>
     * <BR>
     * [is取扱可能 引数] <BR>
     * 銘柄タイプ： 引数.銘柄タイプ <BR>
     * <BR>
     * @@param l_strMarketCode - 市場コード <BR>
     * @@param l_productTypeEnum - 銘柄タイプ <BR>
     * <BR>
     * 0：その他　@1：株式　@2：債券　@3：投資信託　@<BR>
     * 4：外国株　@5：現金 6：先物オプション <BR>
     * （ProductTypeEnumにて定義） <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 405E652C012D
     */
    public boolean isHandlingPossibleMarket(
        String l_strMarketCode,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isHandlingPossibleMarket(String,ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //取得（部店市場別）取扱オブジェクト
        WEB3GentradeBranchMarketDealtCond l_genBranchMarketDealtCond =
            new WEB3GentradeBranchMarketDealtCond(
                getInstitution().getInstitutionCode(),
                getBranchCode(),
                l_strMarketCode);

        //取扱可能判定
        boolean l_isHandingPossibleMarket =
            l_genBranchMarketDealtCond.isHandlingPossible(
                l_productTypeEnum);

        log.exiting(STR_METHOD_NAME);
        return l_isHandingPossibleMarket;
    }
    
    /**
     * (is信用取扱実施) <BR>
     *  <BR>
     * 指定の信用取引が、当該部店で取扱可能かを判定する。<BR>
     *  <BR>
     * ○引数の弁済区分＝”指定なし”の場合<BR>
     *   －this.制度信用実施区分＝”実施”、または this.一般 <BR>
     *      信用実施区分＝”実施”の場合のみ、trueを返す。<BR>
     *   －以外、falseを返す。<BR>
     *  <BR>
     * ○引数の弁済区分＝”制度信用”の場合 <BR>
     *   －this.制度信用実施区分＝”実施”の場合、trueを返す。<BR>
     *   －以外、falseを返す。<BR>
     *  <BR>
     * ○引数の弁済区分＝”一般信用”の場合 <BR>
     *   －this.一般信用実施区分＝”実施”の場合、trueを返す。 <BR>
     *   －以外、falseを返す。 <BR>
     *  <BR>
     * ○引数の弁済区分が上記以外の場合 <BR>
     *   －例外をthrowする。 <BR>
     *       class    : WEB3BaseRuntimeException<BR>
     *       tag      : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * @@param l_strRepaymentDiv - 弁済区分 <BR>
     *   (WEB3GentradeRepaymentDivDefにて定義) <BR>
     * @@return boolean
     */
    public boolean isMarginTradeEnforcement(String l_strRepaymentDiv) 
    {
        final String STR_METHOD_NAME =
            "isMarginTradeEnforcement(String)";
        log.entering(STR_METHOD_NAME);
        
        BranchRow l_branchRow = (BranchRow)getDataSourceObject();
        boolean l_isMarginTradeEnforcement = false;
        //○引数の弁済区分＝指定なしの場合
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
        {
            //this.制度信用実施区分＝”実施”、または 
            //this.一般信用実施区分＝”実施”の場合のみ、trueを返す
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
               ||WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //○引数の弁済区分＝”制度信用”の場合
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentDiv))
        {
            //this.制度信用実施区分＝”実施”の場合、trueを返す。
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //引数の弁済区分＝”一般信用”の場合
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentDiv))
        {
            //this.一般信用実施区分＝”実施”の場合、trueを返す
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_isMarginTradeEnforcement = true;
            }
            else
            {
                l_isMarginTradeEnforcement = false;
            }
        }
        //○引数の弁済区分が上記以外の場合例外をthrowする。 
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数の弁済区分 = " + l_strRepaymentDiv);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isMarginTradeEnforcement;
    }
    
    /**
     * (get市場警告文表示) <BR>
     * 引数の銘柄タイプ、信用取引区分、 <BR>
     * 先物／オプション区分に該当する <BR>
     * 市場警告文表示を取得する。 <BR>
     * <BR>
     * ○　@株式（銘柄タイプ == ProductTypeEnum.株式）<BR>
     * 本オブジェクトが保持する行オブジェクトの<BR>
     * 「市場閉局警告文表示（株式・信用）」を返却する。<BR>
     * <BR>
     * ○　@先物オプション（銘柄タイプ == ProductTypeEnum.先物オプション）<BR>
     * －先物（先物／オプション区分 == ”先物）”の場合：<BR>
     *    本オブジェクトが保持する行オブジェクトの <BR>
     *    「市場閉局警告文表示（先物）」を返却する。<BR>
     * －先物（先物／オプション区分 == ”オプション）”の場合：<BR>
     *    本オブジェクトが保持する行オブジェクトの <BR>
     *    「市場閉局警告文表示（OP）」を返却する。<BR>
     * <BR>
     * ○　@外国株式（銘柄タイプ == ProductTypeEnum.外国株式）<BR>
     * 　@－本オブジェクトが保持する行オブジェクトの<BR>
     * 　@「市場閉局警告文表示（外株）」を返却する。<BR>
     * <BR>
     * @@param l_productTypeEnum 銘柄タイプ
     * @@param l_strMarginTradeDiv 信用取引区分 <BR>
     * 0：DEFAULT（信用取引以外）　@1：制度信用　@2：一般信用<BR>
     * @@param l_strFutureOptionDiv (先物／オプション区分) <BR>
     * 　@0：DEFAULT（先物オプション以外） 1：先物 2：オプション <BR>
     * @@return long
     * @@roseuid 4064134A0157
     */
    public long getMarketMessageSuspension(
        ProductTypeEnum l_productTypeEnum,
        String l_strMarginTradeDiv,
        String l_strFutureOptionDiv)
    {
        final String STR_METHOD_NAME =
            "getMarketMessageSuspension("
                + "ProductTypeEnum ,"
                + "String, "
                + "String)";
        log.entering(STR_METHOD_NAME);

        long l_lngMarketMessageSuspension = 0;
        BranchRow l_branchRow = (BranchRow)getDataSourceObject();
        
        //株式（銘柄タイプ == ProductTypeEnum.株式）
        if (l_productTypeEnum.equals(ProductTypeEnum.EQUITY)) 
        {
            //「市場閉局警告文表示（株式・信用）」を返却する
            l_lngMarketMessageSuspension = l_branchRow.getCloseWorngEquityMargin();
            
        }
        //先物オプション（銘柄タイプ == ProductTypeEnum.先物オプション）
        else if (l_productTypeEnum.equals(ProductTypeEnum.IFO)) 
        {
            //先物（先物／オプション区分 == ”先物）”の場合
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
            {
                //「市場閉局警告文表示（先物）」を返却する
                l_lngMarketMessageSuspension = l_branchRow.getCloseWorngSysFuture();
            }
            //先物（先物／オプション区分 == ”オプション）”の場合
            else if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv)) 
            {
                //「市場閉局警告文表示（OP）」を返却する
                l_lngMarketMessageSuspension = l_branchRow.getCloseWorngOption();
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "先物／オプション区分 = " + l_strFutureOptionDiv);
            }
        }
        //外国株式（銘柄タイプ == ProductTypeEnum.外国株式）
        else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum)) 
        {
            
            //「市場閉局警告文表示（外株）」を返却する。
            l_lngMarketMessageSuspension = l_branchRow.getCloseWorngFeq();
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngMarketMessageSuspension;

    }
    
    /**
     * (is電子鳩実施) <BR>
     *  <BR>
     * 当該部店が電子鳩サービスを実施しているかどうかを判定する。<BR>
     * １）「会社部店商品テーブル」を以下の条件で検索する。<BR>
     *   部店ID＝this.getBranchId( )の戻り値　@and <BR>
     *   手数料商品コード＝引数.手数料商品コード　@and <BR>
     *   電子鳩実施FLAG＝電子鳩実施する <BR>
     *  <BR>
     * ２）検索結果＝0件の場合、falseを返却。<BR>
     * 検索結果＞0件の場合、trueを返却する。<BR>
     *  <BR>
     * @@param l_strCommProductCode - (手数料商品コード)
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public boolean isBatoEnforcemented(String l_strCommProductCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isBatoEnforcemented(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）「会社部店商品テーブル」を検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ? ");
        l_sbWhere.append(" and commission_product_code = ? ");
        l_sbWhere.append(" and bato_flag = ? ");
        Object[] l_objWhere =
            {new Long(this.getBranchId()),
             l_strCommProductCode,
             WEB3BatoFlagDef.ENFORCEMENT };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                InstBranchProductRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）検索結果＝0件の場合、falseを返却。
        //検索結果＞0件の場合、trueを返却する
        if(l_lstRecords.size() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (getプリファ@レンスの値) <BR>
     * <BR>
     * 以下の条件で「部店別プリファ@レンステーブル」を検索し、<BR>
     *     ”プリファ@レンスの値”を取得する。<BR>
     *     [検索条件]<BR>
     *     引数.補助口座.get取引店().部店ID<BR>
     *     引数.プリファ@レンス名<BR>
     *     引数.プリファ@レンス名の連番<BR>
     * <BR>
     * @@param l_subAccount 補助口座
     * @@param l_strName プリファ@レンス名
     * @@param l_lngNameSerialNo プリファ@レンスの連番
     * @@throws WEB3BaseException
     */
    protected String getValue(
        WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValue(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //   以下の条件で「部店別プリファ@レンステーブル」を検索し、
        //     ”プリファ@レンスの値”を取得する。
        // 　@　@[検索条件]
        //      引数.補助口座.get取引店().部店ID
        //      引数.プリファ@レンス名
        // 　@　@ 引数.プリファ@レンス名の連番
        BranchPreferencesRow l_bpRow = null;
        try
        {
            l_bpRow = BranchPreferencesDao.findRowByPk(
                l_subAccount.getWeb3GenBranch().getBranchId(),
                l_strName,
                (int)l_lngNameSerialNo);
        }
        catch (DataFindException e)
        {
            //取得できない（該当データがない）場合は、何もせずにreturnする。
            log.debug("部店別プリファ@レンステーブルに" 
                + "部店ID = " + l_subAccount.getWeb3GenBranch().getBranchId()
                + " プリファ@レンス名 = " + l_strName
                + " プリファ@レンス名の連番 = " + l_lngNameSerialNo
                + " のレコードが無い");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bpRow.getValue();
    }
    
    /**
     * (validate年齢下限値) <BR>
     *  <BR>
     * 顧客の年齢が該当機@能、会社、部店が指定する年齢下限条件を<BR>
     * 満たすかチェックを行う。<BR>
     * <BR>
     * １）口座タイプの取得<BR>
     * 　@（１）引数の引数の補助口座より、顧客オブジェクトを取得し、<BR>
     *     顧客オブジェクトの口座タイプを取得する。<BR>
     * <BR>
     *   （２）（１）で取得した顧客.口座タイプ=”法@人アカウント”の場合は、<BR>
     *     何もせずにreturnする。<BR>
     * <BR>
     * ２）年齢下限値の取得<BR>
     *   （１）以下の条件で「部店別プリファ@レンステーブル」を検索し、<BR>
     *     ”プリファ@レンスの値”を取得する。<BR>
     * 　@　@[検索条件]<BR>
     *      引数.補助口座.get取引店().部店ID<BR>
     *      引数.プリファ@レンス名<BR>
     * 　@　@ 引数.プリファ@レンス名の連番<BR>
     * <BR>
     *   （２）取得できない（該当データがない）場合は、何もせずにreturnする。<BR>
     * <BR>
     * ３）年齢下限条件チェック<BR>
     *   （１）生年月日年号を取得する。<BR>
     *     取得した値が、nullの場合は、例外をスローする。<BR>
     *     生年月日年号 = 引数.補助口座.getMainAccount().生年月日年号<BR>
     * <BR>
     * （２）生年月日を取得する（和暦）。<BR>
     *     取得した値が、nullの場合は、例外をスローする。<BR>
     *     生年月日 = 引数.補助口座.getMainAccount().生年月日<BR>
     * <BR>
     *   （３）生年月日年号、和暦生年月日を西暦の日付型に変換する。<BR>
     *     西暦生年月日 = 年号.toDate(生年月日年号, 生年月日)<BR>
     * <BR>
     *   （４）現在日付を取得する。<BR>
     * <BR>
     *   （５）現在日付と西暦生年月日より顧客の年齢を計算する。<BR>
     * <BR>
     *   （６）顧客の年齢が１）で取得した”プリファ@レンスの値”以上<BR>
     *     （顧客の年齢　@>= ”プリファ@レンスの値”）の場合、returnする。<BR>
     *      上記の条件でない場合は、追加情報として「プリファ@レンスの値」を<BR>
     *      付加し、例外をスローする。<BR>
     * <BR>
     *    ※以下の処理でエラーが発生した場合は「BUSINESS_ERROR_02200」例外をスローする。 <BR>
     *       （３）生年月日年号、和暦生年月日を西暦の日付型に変換する。 <BR>
     *       （４）現在日付を取得する。 <BR>
     *       （５）現在日付と西暦生年月日より顧客の年齢を計算する。<BR>
     * <BR>
     * 　@　@　@(ただし、引数.プリファ@レンス名 = sl_lowlimit.age.check　@の場合、<BR>
     * 以下のエラーメッセージを出力する。） <BR>
     * 　@　@　@ 年齢が年齢下限値に達していません。<BR>
     * <BR>
     * @@param l_subAccount 補助口座
     * @@param l_strName プリファ@レンス名
     * @@param l_lngNameSerialNo プリファ@レンスの連番
     * @@throws WEB3BaseException
     */
    public void validateAgeLowLimit(
        WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateAgeLowLimit(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //１）口座タイプの取得
        // 　@（１）引数の引数の補助口座より、顧客オブジェクトを取得し、
        //     顧客オブジェクトの口座タイプを取得する。
        WEB3GentradeMainAccount l_mainAccount = null;
        l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

        //  （２）（１）で取得した顧客.口座タイプ=”法@人アカウント”の場合は、
        //     何もせずにreturnする。
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT == l_accountType)
        {
            log.debug("顧客.口座タイプ=”法@人アカウント”");
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        //２）年齢下限値の取得
        String l_strAgeLimit = 
            this.getValue(l_subAccount, l_strName, l_lngNameSerialNo);
        if (l_strAgeLimit == null)
        {
            //（２）取得できない（該当データがない）場合は、何もせずにreturnする。
            log.exiting(STR_METHOD_NAME);
            return ;    
        }

        //３）年齢下限条件チェック
        //（１）生年月日年号を取得する。
        String l_strEraBorn = l_mainAccountRow.getEraBorn();
        if (WEB3StringTypeUtility.isEmpty(l_strEraBorn))
        {
            //取得した値が、nullの場合は、例外をスローする。
            log.debug("生年月日年号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02198,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //（２）生年月日を取得する（和暦）
        String l_strBornDate = l_mainAccountRow.getBornDate();
        if (WEB3StringTypeUtility.isEmpty(l_strBornDate))
        {
            //取得した値が、nullの場合は、例外をスローする。
            log.debug("生年月日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02199,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        long l_lngValue = 0L;
        long l_lngAge = 0L;
        try
        {
            //（３）生年月日年号、和暦生年月日を西暦の日付型に変換する。
            Date l_datBirthday =
                WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
            //（４）現在日付を取得する。
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();

            String l_strBirthday =
                WEB3DateUtility.formatDate(l_datBirthday, "yyyyMMdd");
            String l_strSystemDate =
                WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMdd");
            long l_lngBirthDay = new Long(l_strBirthday).longValue();
            long l_lngSystemDate = new Long(l_strSystemDate).longValue();
            l_lngValue = new Long(l_strAgeLimit).longValue();
            l_lngAge = (l_lngSystemDate - l_lngBirthDay) / 10000;
        }
        catch (Exception l_ex)
        {
            if (WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.error("年齢が年齢下限値に達していません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02955,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME
                );
            }
            log.error("顧客の年齢がプリファ@レンスの値より小さいです。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02200,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME
            );
        }

        if (l_lngAge >= l_lngValue)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }
        else
        {
            if (WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02955,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                        " プリファ@レンスの値 = " + l_strAgeLimit
                );
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02200,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    " プリファ@レンスの値 = " + l_strAgeLimit
            );
        }
    }

    /**
     * (validate年齢上限値) <BR>
     *  <BR>
     * 顧客の年齢が該当機@能、会社、部店が指定する年齢上限条件を<BR>
     * 満たすかチェックを行う。<BR>
     * <BR>
     * １）口座タイプの取得<BR>
     *   （１）引数の引数の補助口座より、顧客オブジェクトを取得し、<BR>
     *     顧客オブジェクトの口座タイプを取得する。<BR>
     * <BR>
     *   （２）（１）で取得した顧客.口座タイプ=”法@人アカウント”の場合は、<BR>
     *     何もせずにreturnする。<BR>
     * <BR>
     * ２）年齢上限値の取得<BR>
     *   （１）以下の条件で「部店別プリファ@レンステーブル」を検索し、<BR>
     *    ”プリファ@レンスの値”を取得する。<BR>
     *    [検索条件]<BR>
     *      引数.補助口座.get取引店().部店ID<BR>
     *      引数.プリファ@レンス名<BR>
     *      引数.プリファ@レンス名の連番<BR>
     * <BR>
     *   （２）取得できない（該当データがない）場合は、何もせずにreturnする。<BR>
     * <BR>
     * ３）年齢上限条件チェック<BR>
     *   （１）生年月日年号を取得する。<BR>
     *     取得した値が、nullの場合は、例外をスローする。<BR>
     *     生年月日年号 = 引数.補助口座.getMainAccount().生年月日年号<BR>
     * <BR>
     *   （２）生年月日を取得する（和暦）。<BR>
     *     取得した値が、nullの場合は、例外をスローする。<BR>
     *     生年月日 = 引数.補助口座.getMainAccount().生年月日<BR>
     *   （３）生年月日年号、和暦生年月日を西暦の日付型に変換する。<BR>
     *     西暦生年月日 = 年号.toDate(生年月日年号, 生年月日)<BR>
     * <BR>
     *   （４）現在日付を取得する。<BR>
     * <BR>
     *   （５）現在日付と西暦生年月日より顧客の年齢を計算する。<BR>
     * <BR>
     *   （６）顧客の年齢が１）で取得した”プリファ@レンスの値”より小さい<BR>
     *     （ 顧客の年齢　@< ”プリファ@レンスの値” ）場合、returnする。<BR>
     *    上記の条件でない場合は、追加情報として「プリファ@レンスの値」を<BR>
     *    付加し、例外をスローする。<BR>
     * <BR>
     * 　@　@　@(ただし、引数.プリファ@レンス名 = sl_highlimit.age.checkの場合、<BR>
     * 以下のエラーメッセージを出力する。）  <BR>
     * 　@　@　@ 年齢が年齢上限値を超えています。<BR>
     * <BR>
     * @@param l_subAccount 補助口座
     * @@param l_strName プリファ@レンス名
     * @@param l_lngNameSerialNo プリファ@レンスの連番
     * @@throws WEB3BaseException
     */
    public void validateAgeHighLimit(
    WEB3GentradeSubAccount l_subAccount,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateAgeHighLimit(WEB3GentradeSubAccount, String, long)";
        log.entering(STR_METHOD_NAME);

        //１）口座タイプの取得
        // 　@（１）引数の引数の補助口座より、顧客オブジェクトを取得し、
        //     顧客オブジェクトの口座タイプを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

        //  （２）（１）で取得した顧客.口座タイプ=”法@人アカウント”の場合は、
        //     何もせずにreturnする。
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT == l_accountType)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        //２）年齢上限値の取得
        String l_strAgeLimit = 
            this.getValue(l_subAccount, l_strName, l_lngNameSerialNo);
        if (l_strAgeLimit == null)
        {
            //（２）取得できない（該当データがない）場合は、何もせずにreturnする。
            log.exiting(STR_METHOD_NAME);
            return ;    
        }

        //３）年齢上限条件チェック
        //（１）生年月日年号を取得する。
        String l_strEraBorn = l_mainAccountRow.getEraBorn();
        if (WEB3StringTypeUtility.isEmpty(l_strEraBorn))
        {
            //取得した値が、nullの場合は、例外をスローする。
            log.debug("生年月日年号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02198,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //（２）生年月日を取得する（和暦）
        String l_strBornDate = l_mainAccountRow.getBornDate();
        if (WEB3StringTypeUtility.isEmpty(l_strBornDate))
        {
            //取得した値が、nullの場合は、例外をスローする。
            log.debug("生年月日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02199,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }
        //（３）生年月日年号、和暦生年月日を西暦の日付型に変換する。
        Date l_datBirthday = 
            WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
        //（４）現在日付を取得する。
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();

        String l_strBirthday =
            WEB3DateUtility.formatDate(l_datBirthday, "yyyyMMdd");
        String l_strSystemDate =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMdd");
        long l_lngBirthDay = new Long(l_strBirthday).longValue();
        long l_lngSystemDate = new Long(l_strSystemDate).longValue();
        long l_lngValue = new Long(l_strAgeLimit).longValue();
        long l_lngAge = (l_lngSystemDate - l_lngBirthDay) / 10000; 
        if ( l_lngAge < l_lngValue)
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }
        else
        {
            if (WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK.equals(l_strName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02956,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                        " プリファ@レンスの値 = " + l_strAgeLimit
                );
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02201,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    "プリファ@レンスの値 = " + l_strAgeLimit
            );
        }
    }

    /**
     * (getプリファ@レンスの値) <BR>
     * <BR>
     * 以下の条件で「部店別プリファ@レンステーブル」を検索し、<BR>
     *     ”プリファ@レンスの値”を取得する。<BR>
     *     [検索条件]<BR>
     *     引数.部店ID<BR>
     *     引数.プリファ@レンス名<BR>
     *     引数.プリファ@レンス名の連番<BR>
     * <BR>
     * @@param l_lngBranchId 部店ID
     * @@param l_strName プリファ@レンス名
     * @@param l_lngNameSerialNo プリファ@レンスの連番
     * @@throws WEB3BaseException
     */
    protected String getValue(
        long l_lngBranchId,
        String l_strName,
        long l_lngNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValue(long, String, long)";
        log.entering(STR_METHOD_NAME);

        //   以下の条件で「部店別プリファ@レンステーブル」を検索し、
        //     ”プリファ@レンスの値”を取得する。
        // 　@　@[検索条件]
        //      引数.部店ID
        //      引数.プリファ@レンス名
        // 　@　@ 引数.プリファ@レンス名の連番
        BranchPreferencesRow l_bpRow = null;
        try
        {
            l_bpRow = BranchPreferencesDao.findRowByPk(
                l_lngBranchId,
                l_strName,
                (int)l_lngNameSerialNo);
        }
        catch (DataFindException e)
        {
            //取得できない（該当データがない）場合は、何もせずにreturnする。
            log.debug("部店別プリファ@レンステーブルに" 
                + "部店ID = " + l_lngBranchId
                + " プリファ@レンス名 = " + l_strName
                + " プリファ@レンス名の連番 = " + l_lngNameSerialNo
                + " のレコードが無い");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bpRow.getValue();
    }

    /**
     * (is投信手数料計算) <BR>
     * <BR>
     * 部店が投信信託取引にて手数料計算をするかどうかを判別する。<BR>
     * <BR>
     * [戻り値]<BR>
     * true： 手数料計算要<BR>
     * false： 手数料計算不要<BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 部店ID = this.getBranchId()の戻り値<BR>
     * プリファ@レンス名 = "mf.commission.calc"<BR>
     * プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”手数料計算要” の場合、<BR>
     * true を返却する。 <BR>
     * <BR>
     * ３）それ以外の場合は、falseを返却する。<BR>
     * ※レコードが取得できなかった場合も含む。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCommissionCalc() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCommissionCalc()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.COMMISSION_CALC,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値 == ”手数料計算要” の場合
        if (WEB3CommissionCalcDef.COMMISSION_CALC.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //３）それ以外の場合は、falseを返却する。
        //※レコードが取得できなかった場合も含む。
        return false;
    }

    /**
     * (is投信乗換先買付最低金額チェック実施) <BR>
     * <BR>
     * 部店が投信信託取引にて乗換先買付最低金額チェックをするかどうかを判別する。<BR>
     * <BR>
     * [戻り値]<BR>
     * true： 買付最低金額チェック要<BR>
     * false： 買付最低金額チェック不要<BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 部店ID = this.getBranchId()の戻り値<BR>
     * プリファ@レンス名 = "mf.switch.buying.minimum.amount.check"<BR>
     * プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”買付最低金額チェック要” の場合、<BR>
     * true を返却する。<BR>
     * <BR>
     * ３）それ以外の場合は、falseを返却する。<BR>
     * ※レコードが取得できなかった場合も含む。<BR> 
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBuyingMinimumAmountCheck() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBuyingMinimumAmountCheck()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.SWITCH_BUYING_MINIMUM_AMOUNT_CHECK,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値 == ”買付最低金額チェック要” の場合
        if (WEB3BuyingMinimumCheckDef.AMOUNT_CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //３）それ以外の場合は、falseを返却する。
        //※レコードが取得できなかった場合も含む。
        return false;
    }

    /**
     * (get投信源泉徴収拘束率) <BR>
     * <BR>
     * 投信信託の乗換注文にて使用する源泉徴収拘束率を取得する。<BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 部店ID = this.getBranchId()の戻り値<BR>
     * プリファ@レンス名 = "mf.switch.withholdingtax.restriction.rate"<BR>
     * プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値を返却する。<BR>
     * ※レコードが取得できなかった場合は、Double.NaNを返却する。<BR> 
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getWithholdingtaxRestrictionRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWithholdingtaxRestrictionRate()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.SWITCH_WITHHOLDINGTAX_RESTRICTION_RATE,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値を返却する
        //※レコードが取得できなかった場合は、Double.NaNを返却する。
        if (l_strValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblRate = new Double(l_strValue).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblRate;
    }

    /**
     * (is投信解約時出金注文生成) <BR>
     * <BR>
     * 部店が投信信託解約取引にて出金注文を生成するかどうかを判別する。<BR>
     * <BR>
     * [戻り値]<BR>
     * true： 出金注文生成要<BR>
     * false： 出金注文生成不要<BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 部店ID = this.getBranchId()の戻り値<BR>
     * プリファ@レンス名 = "mf.dissolution.create.payment.order"<BR>
     * プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”出金注文生成要” の場合、<BR>
     * true を返却する。<BR>
     * <BR>
     * ３）それ以外の場合は、falseを返却する。<BR>
     * ※レコードが取得できなかった場合も含む。<BR> 
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPaymentOrderCreate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPaymentOrderCreate()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;

        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.DISSOLUTION_CREATE_PAYMENT_ORDER,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値 == ”出金注文生成要” の場合
        if (WEB3PaymentOrderCreateDef.CREATE_ORDER.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        //３）それ以外の場合は、falseを返却する。
        //※レコードが取得できなかった場合も含む。
        return false;
    }

    /**
     * (isFX_MRF口座開設チェック実施) <BR>
     * <BR>
     * 部店が為替保証金取引にてMRF口座開設チェックをするかどうかを判別する。<BR>
     * <BR>
     * [戻り値]<BR>
     * true： チェック要<BR>
     * false： チェック不要<BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     *    [条件]<BR>
     *    部店ID = this.getBranchId()の戻り値<BR>
     *    プリファ@レンス名 = "fx.mrfaccount.check"<BR>
     *    プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”チェック不要” の場合、false を返却する。<BR>
     * <BR>
     * ３）それ以外の場合は、trueを返却する。<BR>
     *    ※レコードが取得できなかった場合も含む。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isFxMrfAccountOpenCheck() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isFxMrfAccountOpenCheck()";
        log.entering(STR_METHOD_NAME);

        final int NAME_SERIAL_NO = 1;

        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.FX_MRFACCOUNT_CHECK,
                NAME_SERIAL_NO);

        //２）取得したレコード.プリファ@レンスの値 == ”チェック不要” の場合、false を返却する。
        if (WEB3FxMrfAccountCheckDef.NO_CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３）それ以外の場合は、trueを返却する。
        //※レコードが取得できなかった場合も含む。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get投信定時定額買付最低金額) <BR>
     * <BR>
     * アイテムの定義<BR>
     * 投信信託の定時定額買付にて使用する定時定額買付最低金額を取得する。<BR> 
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR> 
     * <BR>
     *    [条件] <BR>
     *    部店ID = this.getBranchId()の戻り値 <BR>
     *    プリファ@レンス名 = "mf.fixed.buying.min.amount" <BR>
     *    プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値を返却する。<BR>
     *    ※レコードが取得できなかった場合は、Double.NaNを返却する。<BR>
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double  getMfFixedBuyingMinAmount() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getMfFixedBuyingMinAmount()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.MF_FIXED_BUYING_MIN_AMOUNT,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値を返却する
        //※レコードが取得できなかった場合は、Double.NaNを返却する。
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblMinAmount = new Double(l_strValue.trim()).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblMinAmount;
    }
    
    /**
     * (get投信定時定額買付単位金額) <BR>
     * <BR>
     * アイテムの定義<BR>
     * 投信信託の定時定額買付にて使用する定時定額買付単位金額を取得する。<BR> 
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR> 
     * <BR>
     *    [条件] <BR>
     *    部店ID = this.getBranchId()の戻り値 <BR>
     *    プリファ@レンス名 = "mf.fixed.buying.unit.amount" <BR>
     *    プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値を返却する。<BR> 
     *    ※レコードが取得できなかった場合は、Double.NaNを返却する。 <BR>
     * <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double  getMfFixedBuyingUnitAmount() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getMfFixedBuyingUnitAmount()";
        log.entering(STR_METHOD_NAME);
        final int NAME_SERIAL_NO = 1;
        
        //プリファ@レンスの値の取得
        String l_strValue = 
            this.getValue(
                this.getBranchId(),
                WEB3BranchPreferencesNameDef.MF_FIXED_BUYING_UNIT_AMOUNT,
                NAME_SERIAL_NO);
        //２）取得したレコード.プリファ@レンスの値を返却する
        //※レコードが取得できなかった場合は、Double.NaNを返却する。
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return Double.NaN;
        }

        double l_dblUnitAmount = new Double(l_strValue.trim()).doubleValue();
         
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitAmount;
    }

    /**
     * (is権利付き最終日チェック) <BR>
     * <BR>
     * 権利付き最終日チェックを実施するかどうかを判定し、 <BR>
     * 実施する場合はtrueを、しない場合はfalseを返却する。 <BR>
     * <BR>
     * １）　@部店用プリファ@レンステーブルより、権利付き最終日チェックを取得する。 <BR>
     * 　@　@　@　@[検索条件] <BR>
     * 　@　@　@　@　@部店ID =  this.getBranchId()の戻り値 <BR>
     * 　@　@　@　@　@AND　@プリファ@レンス名 = プリファ@レンス名.権利付き最終日チェック <BR>
     * 　@　@　@　@　@AND　@プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * 　@　@　@　@※上記条件でレコードが取得出来ない場合、"false：実施しない"を返却する。 <BR>
     * <BR>
     * ２）　@権利付き最終日チェックが"チェックする"の場合trueを返却する。 <BR>
     * 　@　@　@以外の場合、falseを返却する。 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isEqtypeFinalDayWithRight() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEqtypeFinalDayWithRight()";
        log.entering(STR_METHOD_NAME);

        //プリファ@レンス名の連番を定義
        final int NAME_SERIAL_NO = 1;

        //部店用プリファ@レンステーブルより、権利付き最終日チェックを取得する。
        // 部店ID = this.getBranchId()の戻り値
        // プリファ@レンス名 = プリファ@レンス名.権利付き最終日チェック
        // プリファ@レンス名の連番 = 1
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.EQTYPE_FINAL_DAY_WITH_RIGHT,
            NAME_SERIAL_NO);

        //権利付き最終日チェックが"チェックする"の場合trueを返却する。
        if (WEB3EqtypeFinalDayWithRightDef.EXECUTE.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is夕場実施) <BR>
     * <BR>
     * アイテムの定義<BR>
     * 夕場実施を行っているかを判定する。<BR>
     * <BR>
     * 　@１）引数.銘柄タイプに対応するプリファ@レンス名の連番を取得する。<BR>
     * <BR>
     * 　@　@　@１－１）引数.銘柄タイプ = "先物オプション"の場合<BR>
     * 　@　@　@　@　@　@　@　@プリファ@レンス名の連番 = "2：先物オプション"<BR>
     * <BR>
     * 　@　@　@１－２）上記以外の場合<BR>
     * 　@　@　@　@　@　@　@　@false（実施しない）を返却する。<BR>
     * <BR>
     * 　@２）部店プリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
     * <BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@　@部店ID = this.getBranchId()の戻り値<BR>
     * 　@　@　@　@AND　@プリファ@レンス名 = プリファ@レンス名.夕場実施区分<BR>
     * 　@　@　@　@AND　@プリファ@レンス名の連番 = １）で取得したプリファ@レンス名の連番<BR>
     * <BR>
     * 　@　@　@　@※上記条件でレコードが取得出来ない場合、false（実施しない）を返却する。<BR>
     * <BR>
     * 　@３）取得レコード.プリファ@レンスの値 = "実施する"の場合、 trueを返却する。<BR>
     * 　@　@　@以外の場合は、 falseを返却する。<BR>
     * <BR>
     * @@param l_productType 銘柄タイプ
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isEveningSessionEnforcemented(ProductTypeEnum l_productType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEveningSessionEnforcemented(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）引数.銘柄タイプに対応するプリファ@レンス名の連番を取得
        String l_strNameSerialNo;
        if (l_productType.equals(ProductTypeEnum.IFO))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.FUTURE_OPTION;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）部店プリファ@レンステーブルから以下の条件でレコードを取得
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.EVENING_SESSION_DIV,
            Integer.parseInt(l_strNameSerialNo));

        //３）取得レコード.プリファ@レンスの値 = "実施する"の場合、 trueを返却する
        if (WEB3EveningSessionDivDef.EXECUTE.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is証拠金不足額非管理) <BR>
     * <BR>
     * 証拠金不足額管理を行っているかを判定する。 <BR>
     * <BR>
     * <BR>
     * １）部店プリファ@レンステーブルから以下の条件でレコードを取得する。  <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@部店ID = this.getBranchId()の戻り値 <BR>
     * 　@　@　@　@AND　@プリファ@レンス名 = プリファ@レンス名.証拠金不足額非管理 <BR>
     * 　@　@　@　@AND　@プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * 　@　@　@　@※上記条件でレコードが取得出来ない場合、false（管理する）を返却する。 <BR>
     * <BR>
     * ２）取得レコード.プリファ@レンスの値 = "管理しない"の場合、 trueを返却する。 <BR>
     * 　@　@　@以外の場合は、 falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isIfodepositLackchargeNonManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isIfodepositLackchargeNonManagement()";
        log.entering(STR_METHOD_NAME);

        //プリファ@レンス名の連番を定義
        final int NAME_SERIAL_NO = 1;

        //部店プリファ@レンステーブルから以下の条件でレコードを取得する。
        //部店ID = this.getBranchId()の戻り値
        //プリファ@レンス名 = プリファ@レンス名.証拠金不足額非管理
        //プリファ@レンス名の連番 = 1
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.IFODEPOSIT_LACKCHARGE_NON_MANAGEMENT,
            NAME_SERIAL_NO);

        //プリファ@レンスの値 = "管理しない"の場合、 trueを返却する。
        if (WEB3IfodepositLackchargeNonManagementDef.NON_MANAGEMENT.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is連続注文繰越実施) <BR>
     * <BR>
     * 連続注文の繰越を行っているかを判定する。 <BR>
     * <BR>
     * 　@１）引数.銘柄タイプに対応するプリファ@レンス名の連番を取得する。 <BR>
     * <BR>
     * 　@　@　@１－１）引数.銘柄タイプ = "株式"の場合 <BR>
     * 　@　@　@　@　@　@　@　@プリファ@レンス名の連番 = "1：株式・信用" <BR>
     * <BR>
     * 　@　@　@１－２）引数.銘柄タイプ = "先物オプション"の場合 <BR>
     * 　@　@　@　@　@　@　@　@プリファ@レンス名の連番 = "2：先物オプション" <BR>
     * <BR>
     * 　@２）部店プリファ@レンステーブルから以下の条件でレコードを取得する。  <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@部店ID = this.getBranchId()の戻り値 <BR>
     * 　@　@　@　@AND　@プリファ@レンス名 = プリファ@レンス名.連続注文繰越実施区分 <BR>
     * 　@　@　@　@AND　@プリファ@レンス名の連番 = １）で取得したプリファ@レンス名の連番 <BR>
     * <BR>
     * 　@　@　@　@※上記条件でレコードが取得出来ない場合、false（実施しない）を返却する。 <BR>
     * <BR>
     * 　@３）取得レコード.プリファ@レンスの値 = "実施する"の場合、 trueを返却する。 <BR>
     * 　@　@　@以外の場合は、 falseを返却する。<BR>
     * <BR>
     * @@param l_productType 銘柄タイプ
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isSuccOrderCarryoverEnforcemented(ProductTypeEnum l_productType)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSuccOrderCarryoverEnforcemented(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）引数.銘柄タイプに対応するプリファ@レンス名の連番を取得
        String l_strNameSerialNo = null;
        if (l_productType.equals(ProductTypeEnum.EQUITY))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.EQUITY_MARGIN;
        }
        else if (l_productType.equals(ProductTypeEnum.IFO))
        {
            l_strNameSerialNo = WEB3BranchNameSerialNoDef.FUTURE_OPTION;
        }

        //２）部店プリファ@レンステーブルから以下の条件でレコードを取得
        String l_strValue = this.getValue(
            this.getBranchId(),
            WEB3BranchPreferencesNameDef.TRIGGERORDER_SUCORDER_CARRYOVER,
            Integer.parseInt(l_strNameSerialNo));

        //上記条件でレコードが取得出来ない場合、false（実施しない）を返却する
        if (l_strValue == null || "".equals(l_strValue.trim()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３）取得レコード.プリファ@レンスの値 = "実施する"の場合、 trueを返却する
        if (WEB3TriggerorderSucorderCarryoverDef.EXECUTE.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get複数メールアドレス対応実施)<BR>
     * 部店プリファ@レンスを参照し、取得したプリファ@レンスの値を返却する。<BR>
     * <BR>
     * １）部店テーブルを以下の条件で検索し、部店IDを取得する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@部店コード = 引数.部店コード<BR>
     * 　@　@証券コード = 引数.証券コード<BR>
     * <BR>
     * ２）部店プリファ@レンステーブルを以下の条件で検索する。<BR>
     *  <BR>
     * 　@　@[条件]<BR>
     * 　@　@部店ID = １）で取得した部店ID<BR>
     * 　@　@プリファ@レンス名 = （引数）プリファ@レンス名<BR>
     * 　@　@プリファ@レンス名の連番 = （引数）プリファ@レンス名の連番<BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     *  <BR>
     * ３）検索結果.プリファ@レンスの値を返却する。<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strName - (プリファ@レンス名)<BR>
     * プリファ@レンス名<BR>
     * @@param l_intNameSerialNo - (プリファ@レンス名の連番)<BR>
     * プリファ@レンス名の連番<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMultiMailAddressEnforcement(
        String l_strBranchCode, String l_strInstitutionCode, String l_strName, int l_intNameSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMultiMailAddressEnforcement(String, String, String, int)";
        log.entering(STR_METHOD_NAME);

        long l_lngBranchId = 0;
        BranchPreferencesRow l_branchPreferencesRow = null;

        try
        {
            //１）部店テーブルを以下の条件で検索し、部店IDを取得する。
            BranchRow l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode,
                l_strBranchCode);

            //部店ID = １）で取得した部店ID
            if (l_branchRow != null)
            {
                l_lngBranchId = l_branchRow.getBranchId();
            }

            //２）部店プリファ@レンステーブルを以下の条件で検索する。 
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    l_strName,
                    l_intNameSerialNo);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果が取得できなかった場合、nullを返却する。
        if (l_branchPreferencesRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //３）検索結果.プリファ@レンスの値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_branchPreferencesRow.getValue();
    }
}
@
