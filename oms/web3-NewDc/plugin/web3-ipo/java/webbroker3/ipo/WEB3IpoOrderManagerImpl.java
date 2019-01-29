head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告マネージャ(WEB3IpoOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
              001: 2004/10/14 張威 (中訊) 対応名称  コードチェック指摘事項(IPOV1.0-20040928ベース)
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>039,040
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>050
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>070
Revision History : 2005/08/19 沢田(SRA) 未取込案件IPO-No.76（パフォーマンス改善）
Revision History : 2006/09/07 徐宏偉(中訊) モデル  No.155
Revision History : 2007/07/19 趙林鵬(中訊) モデル  No.174,176
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResultHolder;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ActionSendStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3IpoLimitQuantityCheckDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3SendMailStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO申告マネージャクラス。
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IpoOrderManagerImpl implements OrderManager
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderManagerImpl.class);

    /**
     * (注文チェック)
     */
    public WEB3IpoOrderValidator orderValidator = new WEB3IpoOrderValidator();

    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_lngArg0 - (arg0)
     * @@return Order
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0129
     */
    public Order getOrder(long l_lngArg0) throws NotFoundException
    {
        return null;
    }

    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_arg0 - (arg0)
     * @@return Order
     * @@roseuid 40BFFDBC012B
     */
    public Order toOrder(Row l_arg0)
    {
        return null;
    }

    /**
     * (getIPO申告)<BR>
     * （getOrderUnitの実装）<BR>
     * <BR><BR>
     * 指定したIPO申告ＩＤに該当する行をIPO申告テーブルより検索する。
     * 検索結果のIPO申告行オブジェクトを引数に指定して、IPO申告オブジェクトを<BR>生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * IPO申告行オブジェクト
     * @@param l_lngIpoOrderId - IPO申告ＩＤ
     * @@return OrderUnit
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC012D
     */
    public OrderUnit getOrderUnit(long l_lngIpoOrderId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getOrderUnit(long)";
        
        log.entering(STR_METHOD_NAME);
        
        log.debug("IpoOrder Id: " + l_lngIpoOrderId);
        
        OrderUnit l_orderUnit = null;
        try
        {
            //指定したIPO申告ＩＤに該当する行をIPO申告テーブルより検索する。
            IpoOrderRow l_orderRow;

            l_orderRow = IpoOrderDao.findRowByIpoOrderId(l_lngIpoOrderId);
            //findRowByPk(l_lngIpoOrderId);
            if(l_orderRow == null)
            {
                log.error(getClass().getName() + STR_METHOD_NAME);
                throw new NotFoundException("該当するIPO銘柄行が存在しない");
            }

            log.debug("IpoOrderRow: " + l_orderRow);
            
            //検索結果のIPO申告行オブジェクトを引数に指定して、IPO申告オブジェクトを生成する。
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            l_orderUnit = l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().toOrderUnit(l_orderRow);
            
        }
//        catch (DataFindException l_ex)
//        {
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new NotFoundException("該当するIPO銘柄行が存在しない");
//        }
        catch (DataQueryException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        log.exiting(STR_METHOD_NAME);
        
        //生成したオブジェクトを返却する。
        return l_orderUnit;
    }

    /**
     * (getIPO申告)<BR>
     * （getOrderUnits）<BR>
     * <BR>
     * 引数の補助口座／IPO登録区分に該当するIPO銘柄を持つIPO申告オブジェクトを<BR>取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@公開日の１ヶ月前取得<BR>
     * 　@現在日時(*1)の１ヶ月前のTimestampを取得する。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()にて取得する。<BR>
     * <BR>
     * ２）　@IPO銘柄ＩＤ取得<BR>
     * 　@以下の条件で、IPO銘柄テーブルを検索し、IPO銘柄行を取得する。<BR>
     * <BR>
     *   [検索条件]<BR>
     *   （IPO銘柄テーブル.IPO登録区分 = 引数のIPO登録区分 And <BR>
     *     IPO銘柄テーブル.削除フラグ = BooleanEnum.FALSE And <BR>
     *   （IPO銘柄テーブル.公開日 > １）で取得したTimestamp Or IPO銘柄テーブル.公開日 = null））<BR> 
     * <BR>
     *   [取得順]<BR>
     *    公開日　@降順（：desc），<BR>
     *  　@銘柄コード　@昇順（：asc）<BR> 
     * <BR>
     * ３）　@IPO申告行検索<BR>
     * 　@返却値格納用にArrayListを生成する（new ArrayList()）。<BR>
     * 　@２）で取得したIPO銘柄行毎に、以下の処理を実施する。<BR>
     * <BR>
     * 　@--- LOOP ---<BR>
     * 　@３－１）　@IPO申告取得<BR>
     * 　@　@以下の条件にて、IPO申告行を取得する。<BR>
     * 　@行が取得できなかった場合は、以降の処理をスキップする。（continue; ）<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@IPO申告.部店ＩＤ = 補助口座.get取引店().getBranchId()<BR>
     * 　@　@IPO申告.口座ＩＤ = 補助口座.getAccountId()<BR>
     * 　@　@IPO申告.補助口座ＩＤ = 補助口座.getSubAccountId()<BR>
     * 　@　@IPO申告.IPO銘柄ＩＤ = IPO銘柄行.IPO銘柄ＩＤ[index]<BR>
     * <BR>
     * 　@３－２）　@返却値格納用ArrayListに追加<BR>
     * 　@　@this.toIPO申告()メソッドを利用し、IPO申告オブジェクトを作成し、<BR>ArrayListに追加する。<BR>
     * <BR>
     * 　@　@[toIPO申告()に指定する引数]<BR>
     * 　@　@３－１）で取得したIPO申告行<BR>
     * <BR>
     * 　@--- LOOP ---<BR>
     * <BR>
     * ４）　@返却値生成<BR>
     * 　@３）で作成した返却値格納用ArrayListを、toArray()にて配列に変換し返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_strIpoRegDiv - IPO登録区分<BR>
     * <BR>
     * 　@1：新規上場<BR>
     * 　@2：既上場<BR>
     * 　@※ DBレイアウト「IPO銘柄テーブル」参照。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40DBF7BF026E
     */
    public WEB3IpoOrderImpl[] getOrderUnits(SubAccount l_subAccount, String l_strIpoRegDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(SubAccount, String)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //１）　@公開日の１ヶ月前取得
            //(*1) 現在日時
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            
            Timestamp l_tsSystemTs = l_tradingSystem.getSystemTimestamp();

            //２）　@IPO銘柄ＩＤ取得
            String l_strWhere = " ipo_regist_div = ?" 
                                    + " and delete_flag = ?" 
                                    + " and (public_offering_date > add_months(to_date(?" 
                                    + ", 'yyyy/mm/dd'), ?) "
                                    + " or public_offering_date is null) ";
            Object[] l_objBinds = new Object[]{
                    l_strIpoRegDiv,
                    new Integer(BooleanEnum.IntValues.FALSE),
                    WEB3DateUtility.formatDate(l_tsSystemTs, "yyyy/MM/dd"),
                    new Integer(-1)
                };              
            log.debug("OneMonthAgo: " + "add_months(to_date('" + WEB3DateUtility.formatDate(l_tsSystemTs, "yyyy/MM/dd") + "', 'yyyy/mm/dd'), -1)");

            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //DataQueryException            
            List l_lisProductRows =
                    l_queryProcessor.doFindAllQuery(IpoProductRow.TYPE, 
                                                    l_strWhere,
                                                    l_strOrderBy,
                                                    null,
                                                    l_objBinds);
                                                                    
            //List to Array
            int l_intProductRowsCnt = l_lisProductRows.size();
            
            log.debug("Found " + l_intProductRowsCnt + " Product Rows");
            
            IpoProductRow[] l_ipoProductRows = new IpoProductRow[l_intProductRowsCnt];
            
            l_lisProductRows.toArray(l_ipoProductRows);

//            //Comparator[]：com[0] = new IPO銘柄.公開日Comparatorr(降順（：desc）)     
//            Comparator[] l_comparator = new Comparator[1];
//            l_comparator[0] = new WEB3IpoProductPublicOfferingDateComparator(WEB3AscDescDef.DESC);
//            
//            //[sortに指定する引数]
//            WEB3ArraysUtility.sort(l_ipoProductRows, l_comparator);

            //３）　@IPO申告行検索
            List l_lisOrderRows = new ArrayList();

            for (int i = 0; i < l_intProductRowsCnt; i++)
            {
                log.debug("IpoProductRow " + (i + 1) + ": " + l_ipoProductRows[i]);
                
                //DataNetworkException, DataFindException, DataQueryException
                IpoOrderRow l_ipoOrderRow =
                    IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                        l_ipoProductRows[i].getIpoProductId(),
                        ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                        l_subAccount.getAccountId(),
                        l_subAccount.getSubAccountId());
                
                log.debug("IpoOrderRow " + (i + 1) + ": " + l_ipoOrderRow);
                if(l_ipoOrderRow != null)
                {
                    l_lisOrderRows.add(this.toOrderUnit(l_ipoOrderRow));
                }
            }

            //４）　@返却値生成
            //  ３）で作成した返却値格納用ArrayListを、toArray()にて配列に変換し返却する。
            l_ipoOrder = new WEB3IpoOrderImpl[l_lisOrderRows.size()];
            
            l_lisOrderRows.toArray(l_ipoOrder);
        }
//        catch (DataFindException l_ex)
//        {
//            //DBアクセスが失敗の場合
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //例外をスローする
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (getIPO申告)<BR>
     * （getOrderUnits）<BR>
     * <BR>
     * 引数のIPO銘柄を持つIPO申告オブジェクトで、ブックビルディング期間中に<BR>
     * 新規申告されたものを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO申告行オブジェクト取得<BR>
     * 　@以下の条件でIPO申告行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ＩＤ = IPO銘柄.IPO銘柄行.IPO銘柄ＩＤ<BR>
     * 　@IPO申告.作成日時 < IPO銘柄.IPO銘柄行.ブックビルディング終了日時<BR>
     * <BR>
     * ２）　@IPO申告オブジェクト生成<BR>
     * 　@１）で取得した各IPO申告行（IPO申告Params）について、this.toIPO申告()<BR>
     * メソッドを利用し、IPO申告オブジェクトを作成し、ArrayListに追加する。<BR>
     * <BR>
     * 　@[toIPO申告()に指定する引数]<BR>
     * 　@１）で取得したIPO申告行オブジェクト<BR>
     * <BR>
     * ３）　@配列作成<BR>
     * 　@２）のArrayListをtoArray()にて配列に変換し返却する。<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40E366C30173
     */
    public WEB3IpoOrderImpl[] getOrderUnits(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3IpoProductImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //１）　@IPO申告行オブジェクト取得
            StringBuffer l_sbWhere = new StringBuffer();

            //[条件]<BR>
            l_sbWhere.append(" ipo_product_id = ? and created_timestamp < ? ");

            log.debug("IpoProductId: " + l_ipoProduct.getProductId());
            log.debug("BookbuildingEndDatetime: " + ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime());
            
            Object[] l_ipoOrderWhere =
                {
                    Long.toString(l_ipoProduct.getProductId()),
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime() };

            //DataNetworkException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //IPO申告行を検索
            //DataQueryException, DataNetworkException
            List l_lisOrderRowList =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE, 
                    l_sbWhere.toString(),
                    " bookbuilding_created_timestamp asc ",
                    null, 
                    l_ipoOrderWhere
                    );

            List l_lisArrayList = new ArrayList();

            int l_intOrderRowCnt = l_lisOrderRowList.size();

            log.debug("Found " + l_intOrderRowCnt + " Rows");
            
            //該当行が存在しない場合はnullを返却する。
            if (l_intOrderRowCnt == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            //２）　@IPO申告オブジェクト生成
            for (int i = 0; i < l_intOrderRowCnt; i++)
            {
                log.debug("IpoOrderRow " + (l_intOrderRowCnt + 1) + ": " + (IpoOrderRow)l_lisOrderRowList.get(i));
                //１）で取得した各IPO申告行（IPO申告Params）について、this.toIPO申告()
                OrderUnit l_orderUnit = this.toOrderUnit((IpoOrderRow)l_lisOrderRowList.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            int l_intArrayListCnt = l_lisArrayList.size();
            
            //３）　@配列作成
            l_ipoOrder = new WEB3IpoOrderImpl[l_intArrayListCnt];
            
            l_lisArrayList.toArray(l_ipoOrder);

        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (getIPO申告)<BR>
     * （getOrderUnits）<BR>
     * <BR>
     * 引数のIPO銘柄を持つIPO申告オブジェクトで、ブックビルディング期間中に<BR>
     * 新規申告されたもの且つ、引数の部店コード、顧客コードfrom－to、新規申告日時from－to<BR>
     * の範囲内のIPO申告オブジェクトを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO申告行オブジェクト取得<BR>
     * 　@以下の条件でIPO申告行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ID = 引数.IPO銘柄.getProductId()<BR>
     * 　@IPO申告.作成日時 < 引数.IPO銘柄.ブックビルディング終了日時<BR>
     * 　@IPO申告.部店ID = 引数.IPO銘柄に紐づく部店の部店ID<BR>
     * 　@引数.顧客コードfrom <= IPO申告.口座ID.顧客コード <= 引数.顧客コードto<BR>
     * 　@引数.新規申告日時from <= IPO申告.新規申告日時 < 引数.新規申告日時to<BR>
     * <BR>
     * ２）　@IPO申告オブジェクト生成<BR>
     * 　@１）で取得した各IPO申告行（IPO申告Params）について、this.toIPO申告()<BR>
     * メソッドを利用し、IPO申告オブジェクトを作成し、ArrayListに追加する。<BR>
     * <BR>
     * 　@[toIPO申告()に指定する引数]<BR>
     * 　@１）で取得したIPO申告行オブジェクト<BR>
     * <BR>
     * ３）　@配列作成<BR>
     * 　@２）のArrayListをtoArray()にて配列に変換し返却する。<BR>
     * <BR>
     * @@param l_ipoProduct         - (IPO銘柄)<BR>
     * @@param l_strBranchCodes     - (部店[])<BR>
     * @@param l_strAccountCodeFrom - (顧客コードfrom)<BR>
     * @@param l_strAccountCodeTo   - (顧客コードto)<BR>
     * @@param l_dteBbCreatedTimestampFrom - (新規申告日時from)<BR>
     * @@param l_dteBbCreatedTimestampTo   - (新規申告日時to)<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     */
    public WEB3IpoOrderImpl[] getOrderUnits(
        WEB3IpoProductImpl l_ipoProduct,
        String[] l_strBranchCodes,
        String l_strAccountCodeFrom,
        String l_strAccountCodeTo,
        Date l_dteBbCreatedTimestampFrom,
        Date l_dteBbCreatedTimestampTo
    ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3IpoProductImpl, String[], String, String, String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_strBranchCodes == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }

        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            // IPO申告行オブジェクト取得
            // 条件文字列設定
            StringBuffer l_sbWhere = new StringBuffer();

            // 銘柄ID＆作成日時
            l_sbWhere.append(" ipo_product_id = ? and created_timestamp < ? ");

            // 部店ID  
            if(l_strBranchCodes.length > 0)
            {
                // 引数.部店コード.length() == 1 の場合 
                // " and branch_id=?"をの文字列に追加する。
                l_sbWhere.append(" and ( branch_id = ? ");

                // 引数.部店コード.length() > 1 の場合 
                // " and (branch_id=? or branch_id=? or ... or branch_id=?)"
                // を文字列に追加する。 
                // ※"branch_id=?"の数は、部店コードの要素数と同じ
                for(int i = 1; i < l_strBranchCodes.length; i++)
                {
                    l_sbWhere.append(" or branch_id = ? ");
                }

                l_sbWhere.append(" ) ");
            }

            // 顧客コードfrom 
            // 引数.顧客コードfrom != null の場合 
            // " and substr(account_id, 9, 6) >=?"を文字列に追加する。
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeFrom))
            {
                l_sbWhere.append(" and substr(account_id, 9, 6) >= ? ");
            }

            // 顧客コードto 
            // 引数.顧客コードto != null の場合 
            // " and substr(account_id, 9, 6) <=?"を文字列に追加する。
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeTo))
            {
                l_sbWhere.append(" and substr(account_id, 9, 6) <= ? ");
            }

            // 新規申告日時from 
            // 引数.新規申告日時from != null の場合 
            // " and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') >=?"を文字列に追加する。
            if(l_dteBbCreatedTimestampFrom != null)
            {
                l_sbWhere.append(" and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') >= ? ");
            }

            // 新規申告日時to 
            // 引数.新規申告日時to != null の場合 
            // " and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') <?"を文字列に追加する。
            if(l_dteBbCreatedTimestampTo != null)
            {
                l_sbWhere.append(" and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') < ? ");
            }

            log.debug("************** the StringWhere = " + l_sbWhere);

            // 条件データコンテナ設定
            List l_ipoOrderWhere = new Vector();

            // 銘柄ID＆ブックビルディング終了日時  
            l_ipoOrderWhere.add(Long.toString(l_ipoProduct.getProductId()));
            l_ipoOrderWhere.add(((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime());

            // 部店ID
            // 引数.部店コードの各要素を部店IDに変換後、Listに追加する。
            for(int i = 0; i < l_strBranchCodes.length; i++)
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                Institution l_institution = l_ipoProduct.getInstitution();
                try
                {
                    Branch l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCodes[i]);
                    l_ipoOrderWhere.add(Long.toString(l_branch.getBranchId()));   
                }
                catch(NotFoundException l_ex)
                {
                    // 引数.部店コードに該当するレコードが存在しない場合
                    log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                    // 例外をスローする
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                    );
                }
            }

            // 顧客コードfrom
            // 引数.顧客コードfrom != null の場合
            // 引数.顧客コードfromをListに追加する。
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeFrom))
            {
                l_ipoOrderWhere.add(l_strAccountCodeFrom);   
            }

            // 顧客コードto
            // 引数.顧客コードto != null の場合
            // 引数.顧客コードtoをListに追加する。
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeTo))
            {
                l_ipoOrderWhere.add(l_strAccountCodeTo);   
            }

            // 新規申告日時from
            // 引数.新規申告日時from != null の場合
            // 引数.新規申告日時fromをListに追加する。
            if(l_dteBbCreatedTimestampFrom != null)
            {
                l_ipoOrderWhere.add(WEB3DateUtility.formatDate(l_dteBbCreatedTimestampFrom, "yyyyMMddHHmmss"));   
            }

            // 新規申告日時to
            // 引数.新規申告日時to != null の場合
            // 引数.新規申告日時toをListに追加する。
            if(l_dteBbCreatedTimestampTo != null)
            {
                l_ipoOrderWhere.add(WEB3DateUtility.formatDate(l_dteBbCreatedTimestampTo, "yyyyMMddHHmmss"));
            }

            //log for test
            for (int i = 0; i < l_ipoOrderWhere.size(); i++)
            {
                log.debug("************* Value[" + i + "]= " + l_ipoOrderWhere.get(i));
            }

            //DataNetworkException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // IPO申告行を検索
            //DataQueryException, DataNetworkException
            List l_lisOrderRowList =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE, 
                    l_sbWhere.toString(),
                    " bookbuilding_created_timestamp asc ",
                    null, 
                    l_ipoOrderWhere.toArray()
                );

            List l_lisArrayList = new ArrayList();

            int l_intOrderRowCnt = l_lisOrderRowList.size();

            log.debug("Found " + l_intOrderRowCnt + " Rows");

            // 該当行が存在しない場合はnullを返却する。
            if (l_intOrderRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);

                return null;
            }

            // IPO申告オブジェクト生成
            for (int i = 0; i < l_intOrderRowCnt; i++)
            {
                log.debug("IpoOrderRow " + (l_intOrderRowCnt + 1) + ": " + (IpoOrderRow)l_lisOrderRowList.get(i));
                // 取得した各IPO申告行（IPO申告Params）について、this.toIPO申告()
                OrderUnit l_orderUnit = this.toOrderUnit((IpoOrderRow)l_lisOrderRowList.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            int l_intArrayListCnt = l_lisArrayList.size();

            // 配列作成
            l_ipoOrder = new WEB3IpoOrderImpl[l_intArrayListCnt];

            l_lisArrayList.toArray(l_ipoOrder);

        }
        catch (DataQueryException l_ex)
        {
            // DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            // 例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            // DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            // 例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_ipoOrder;
    }

    /**
     * (getIPO申告)<BR>
     * （getOrderUnit）<BR>
     * <BR>
     * 引数の補助口座、IPO銘柄に該当するIPO申告オブジェクトを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO申告行オブジェクト取得<BR>
     * 　@以下の条件でIPO申告行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ＩＤ = IPO銘柄ＩＤ<BR>
     * 　@IPO申告.部店ＩＤ = 補助口座.get取引店().getBranchId()<BR>
     * 　@IPO申告.口座ＩＤ = 補助口座.getAccountId()<BR>
     * 　@IPO申告.補助口座ＩＤ = 補助口座.getSubAccountId()<BR>
     * <BR>
     * ２）　@IPO申告オブジェクト生成<BR>
     * 　@this.toIPO申告()メソッドにて、IPO申告オブジェクトを作成し返却する。<BR>
     * <BR>
     * 　@[toIPO申告()に指定する引数]<BR>
     * 　@１）で取得したIPO申告行オブジェクト<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_lngIpoProductId - IPO銘柄ＩＤ
     * @@return webbroker3.ipo.WEB3IpoOrderImpl
     * @@roseuid 40D2C54501CC
     */
    public WEB3IpoOrderImpl getOrderUnit(SubAccount l_subAccount, long l_lngIpoProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnit(SubAccount, long)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl l_ipoOrder = null;

        try
        {
            //１）　@IPO申告行オブジェクト取得
            //DataNetworkException, DataFindException, DataQueryException
            IpoOrderRow l_orderRow =
                IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                    l_lngIpoProductId,
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                    l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId());

            log.debug("IpoOrderRow: " + l_orderRow);
            
            //行が取得できなかった場合はnullを返却し、処理を終了する。
            if (l_orderRow == null)
            {
                return null;
            }

            //２）　@IPO申告オブジェクト生成
            l_ipoOrder = (WEB3IpoOrderImpl)this.toOrderUnit(l_orderRow);
        }
//        catch (DataFindException l_ex)
//        {
//            //DBアクセスが失敗の場合
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //例外をスローする
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //IPO申告オブジェクトを作成し返却する。
        return l_ipoOrder;
    }

    /**
     * (get有効IPO申告For抽選)<BR>
     * （getOpenOrderUnits）<BR>
     * <BR>
     * 引数のIPO銘柄に該当するIPO申告オブジェクトで、有効なデータを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO銘柄オブジェクト取得<BR>
     * 　@IPO銘柄ＩＤに該当するIPO銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@[IPO銘柄コンストラクタの引数]<BR>
     * 　@IPO銘柄ＩＤ：　@IPO銘柄ＩＤ<BR>
     * <BR>
     * ２）　@IPO申告行オブジェクト取得<BR>
     * 　@以下の条件でIPO申告行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ＩＤ = IPO銘柄ＩＤ And<BR>
     * 　@IPO申告.IPO新規申告日時 < IPO銘柄.IPO銘柄行.ブックビルディング終了日<BR>時 And<BR>
     * 　@IPO申告.ブックビルディング申告状態 != OrderStatusEnum.CANCELLED<BR>
     * （取消）<BR>
     * <BR>
     * ３）　@IPO申告オブジェクト生成<BR>
     * 　@取得した各行オブジェクトについて、<BR>
     * 　@this.toIPO申告()メソッドにて、IPO申告オブジェクトを作成し配列にて返却する。<BR>
     * <BR>
     * 　@[toIPO申告()に指定する引数]<BR>
     * 　@２）で取得したIPO申告行オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO銘柄ＩＤ
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40EB5E8A021A
     */
    public WEB3IpoOrderImpl[] getOpenOrderUnits(long l_lngIpoProductId, String l_strOrderBy, boolean l_blnNewLot) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenOrderUnits(long,String,boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //１）　@IPO銘柄オブジェクト取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);

            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[条件]
            List l_lisOrderRow = new ArrayList();
            StringBuffer l_sbWhere = new StringBuffer();     
            if(l_blnNewLot)
            {                
                l_sbWhere.append(" ipo_product_id = ? and ");
                l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
                l_sbWhere.append(" order_status != ? ");

                Object[] l_ipoOrderWhere =
                    {   Long.toString(l_lngIpoProductId),
                        ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                        OrderStatusEnum.CANCELLED };
                        
                //２）　@IPO申告行オブジェクト取得
                //DataQueryException
                l_lisOrderRow =
                    l_queryProcessor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_ipoOrderWhere
                        );

            }
            else
            {
                l_sbWhere.append(" ipo_product_id = ? and ");
                l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
                l_sbWhere.append(" order_status != ? and ");   
                l_sbWhere.append(" lot_result = ? and ") ;
                l_sbWhere.append(" lot_result_retry = ? ") ;
                
                Object[] l_ipoOrderWhere =
                    {   Long.toString(l_lngIpoProductId),
                        ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                        OrderStatusEnum.CANCELLED,
                        WEB3LotResultDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.DEFAULT}; 
                        
                //２）　@IPO申告行オブジェクト取得
                //DataQueryException
                l_lisOrderRow =
                    l_queryProcessor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_ipoOrderWhere
                        );
            }



            int l_intOrderRowSize = l_lisOrderRow.size();

            log.debug("Found " + l_intOrderRowSize + " IpoOrderRows");
            //行が取得できなかった場合はnullを返却し、処理を終了する。
            if (l_intOrderRowSize == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            List l_lisArrayList = new ArrayList();

            //３）　@IPO申告オブジェクト生成
            for (int i = 0; i < l_intOrderRowSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (IpoOrderRow)l_lisOrderRow.get(i));
                
                OrderUnit l_orderUnit =
                    this.toOrderUnit((IpoOrderRow)l_lisOrderRow.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            l_ipoOrder = new WEB3IpoOrderImpl[l_intOrderRowSize];
            
            l_lisArrayList.toArray(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
            //オプション約定データが見つかりません。
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }



    /**
     * (get有効IPO申告)<BR>
     * （getOpenOrderUnits）<BR>
     * <BR>
     * 引数のIPO銘柄に該当するIPO申告オブジェクトで、有効なデータを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO銘柄オブジェクト取得<BR>
     * 　@IPO銘柄ＩＤに該当するIPO銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@[IPO銘柄コンストラクタの引数]<BR>
     * 　@IPO銘柄ＩＤ：　@IPO銘柄ＩＤ<BR>
     * <BR>
     * ２）　@IPO申告行オブジェクト取得<BR>
     * 　@以下の条件でIPO申告行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ＩＤ = IPO銘柄ＩＤ And<BR>
     * 　@IPO申告.IPO新規申告日時 < IPO銘柄.IPO銘柄行.ブックビルディング終了日<BR>時 And<BR>
     * 　@IPO申告.ブックビルディング申告状態 != OrderStatusEnum.CANCELLED<BR>
     * （取消）<BR>
     * <BR>
     * ３）　@IPO申告オブジェクト生成<BR>
     * 　@取得した各行オブジェクトについて、<BR>
     * 　@this.toIPO申告()メソッドにて、IPO申告オブジェクトを作成し配列にて返却する。<BR>
     * <BR>
     * 　@[toIPO申告()に指定する引数]<BR>
     * 　@２）で取得したIPO申告行オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO銘柄ＩＤ
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40EB5E8A021A
     */
    public WEB3IpoOrderImpl[] getOpenOrderUnits(long l_lngIpoProductId, String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenOrderUnits(long)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //１）　@IPO銘柄オブジェクト取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);
            
            //[条件]
            StringBuffer l_sbWhere = new StringBuffer();

            l_sbWhere.append(" ipo_product_id = ? and ");
            l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
            l_sbWhere.append(" order_status != ? ");

            Object[] l_ipoOrderWhere =
                {
                    Long.toString(l_lngIpoProductId),
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                    OrderStatusEnum.CANCELLED };

            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //２）　@IPO申告行オブジェクト取得
            //DataQueryException
            List l_lisOrderRow =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_ipoOrderWhere
                    );

            int l_intOrderRowSize = l_lisOrderRow.size();

            log.debug("Found " + l_intOrderRowSize + " IpoOrderRows");
            //行が取得できなかった場合はnullを返却し、処理を終了する。
            if (l_intOrderRowSize == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            List l_lisArrayList = new ArrayList();

            //３）　@IPO申告オブジェクト生成
            for (int i = 0; i < l_intOrderRowSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (IpoOrderRow)l_lisOrderRow.get(i));
                
                OrderUnit l_orderUnit =
                    this.toOrderUnit((IpoOrderRow)l_lisOrderRow.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            l_ipoOrder = new WEB3IpoOrderImpl[l_intOrderRowSize];
            
            l_lisArrayList.toArray(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
            //オプション約定データが見つかりません。
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }


    /**
     * (get無効ブックビルディング申告履歴)<BR>
     * （getCloseOrderActions）<BR>
     * <BR>
     * 引数のIPO銘柄に該当するブックビルディング申告履歴オブジェクトで、無効な<BR>データを取得する。<BR>
     * 該当行が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@IPO銘柄オブジェクト取得<BR>
     * 　@IPO銘柄ＩＤに該当するIPO銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@[IPO銘柄コンストラクタの引数]<BR>
     * 　@IPO銘柄ＩＤ：　@IPO銘柄ＩＤ<BR>
     * <BR>
     * ２）　@ブックビルディング申告履歴行オブジェクト取得<BR>
     * 　@以下の条件でブックビルディング申告履歴行を検索する。<BR>
     * 行が取得できなかった場合はnullを返却し、処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@ブックビルディング申告履歴.IPO銘柄ＩＤ = IPO銘柄ＩＤ<BR>
     * 　@ブックビルディング申告履歴.削除フラグ = BooleanEnum.TRUE（”削除”）<BR>
     * <BR>
     * ３）　@ブックビルディング申告履歴オブジェクト生成<BR>
     * 　@取得した各行オブジェクトについて、<BR>
     * 　@this.toブックビルディング申告履歴()メソッドにて、ブックビルディング申告履歴<BR>オブジェクトを作成し配列にて返却する。<
     * BR>
     * <BR>
     * 　@[toブックビルディング申告履歴()に指定する引数]<BR>
     * 　@２）で取得したブックビルディング申告履歴行オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO銘柄ＩＤ
     * @@return WEB3IpoBookbuildingOrderAction[]
     * @@roseuid 40EE77C5026B
     */
    public WEB3IpoBookbuildingOrderActionImpl[] getInvalidOrderActions(long l_lngIpoProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInvalidOrderActions(long)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoBookbuildingOrderActionImpl[] l_orderAction = null;
        
        try
        {
            //??１）　@IPO銘柄オブジェクト取得?? Unused            
            //FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            //WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);
            
            //２）　@ブックビルディング申告履歴行オブジェクト取得
            String l_strOrderActionWhere = " ipo_product_id = ? and delete_flag = ? ";

            Object[] l_orderActionWhere = { Long.toString(l_lngIpoProductId), BooleanEnum.TRUE };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisOrderAction =
                l_queryProcessor.doFindAllQuery(
                    IpoBookbuildingOrderActionRow.TYPE,
                    l_strOrderActionWhere,
                    " substr(branch_id, 3, 3) asc, substr(account_id, 9, 6) asc, created_timestamp asc",
                    null, 
                    l_orderActionWhere);

            List l_lisToOrderAction = new ArrayList();

            int l_intSize = l_lisOrderAction.size();


            log.debug("Found " + l_intSize + " IpoBbOrderActionRows");
            
            if(l_intSize == 0)
            {
                return null;    
            }
            
            for (int i = 0; i < l_intSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (Row)l_lisOrderAction.get(i));
                
                OrderAction l_loopOrderAction = this.toOrderAction((Row)l_lisOrderAction.get(i));
                l_lisToOrderAction.add(l_loopOrderAction);
            }

            l_orderAction = new WEB3IpoBookbuildingOrderActionImpl[l_intSize];
            
            l_lisToOrderAction.toArray(l_orderAction);
        }
//        catch (NotFoundException l_ex)
//        {
//            //オプション約定データが見つかりません。
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //例外をスローする
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_orderAction;
    }

    /**
     * (toIPO申告)<BR>
     * （toOrderUnitの実装）<BR>
     * <BR>
     * 指定のIPO申告行オブジェクトからIPO申告オブジェクトを生成する。<BR>
     * <BR>
     * IPO申告行オブジェクトを引数に指定して、IPO申告オブジェクトを生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * IPO申告行オブジェクト<BR>
     * @@param l_ipoOrderParams - (IPO申告Params)<BR>
     * IPO申告行オブジェクト<BR>
     * <BR>
     * ※ IPO申告ParamsクラスはDDLより自動生成する。
     * 
     * @@return OrderUnit
     * @@roseuid 40BFFDBC012F
     */
    public OrderUnit toOrderUnit(Row l_ipoOrderParams)
    {
        WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(l_ipoOrderParams);

        return l_ipoOrder;
    }

    /**
     * (getブックビルディング申告履歴)<BR>
     * （getOrderActionの実装）<BR>
     * <BR>
     * １）　@ブックビルディング履歴行取得<BR>
     * 　@指定したブックビルディング申告履歴ＩＤに該当する行をブックビルディング<BR>
     * 申告履歴テーブルより検索する。<BR>
     * <BR>
     * ２）　@ブックビルディング申告履歴オブジェクト作成<BR>
     * 　@１）で取得した各ブックビルディング申告履歴行（ブックビルディング<BR>
     * 申告履歴Params）について、this.toブックビルディング申告履歴()メソッドを<BR>
     * 利用し、ブックビルディング申告履歴オブジェクト<BR>
     * <BR>
     * を作成する。<BR>
     * <BR>
     * 　@[toブックビルディング申告履歴()に指定する引数]<BR>
     * 　@１）で取得したブックビルディング申告履歴行オブジェクト<BR>
     * <BR>
     * ３）　@返却値作成<BR>
     * 　@作成したブックビルディング申告履歴を返却する。<BR>
     * @@param l_lngBookbuildingOrderActionId - ブックビルディング申告履歴ＩＤ
     * @@return OrderAction
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0131
     */
    public OrderAction getOrderAction(long l_lngBookbuildingOrderActionId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getOrderAction(long)";
        
        log.entering(STR_METHOD_NAME);
        
        OrderAction l_orderAction = null;
        
        try
        {
            //１）　@ブックビルディング履歴行取得
            //DataFindException, DataQueryException, DataNetworkException
            IpoBookbuildingOrderActionRow l_orderActionRow =
                IpoBookbuildingOrderActionDao.findRowByPk(l_lngBookbuildingOrderActionId);

            //２）　@ブックビルディング申告履歴オブジェクト作成
            l_orderAction = this.toOrderAction(l_orderActionRow);
        }
//        catch (DataFindException l_ex)
//        {
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new NotFoundException("該当するIPO銘柄行が存在しない");
//        }
        catch (DataQueryException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new NotFoundException("該当するIPO銘柄行が存在しない");
        }
        catch (DataNetworkException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new NotFoundException("該当するIPO銘柄行が存在しない");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //３）　@返却値作成
        return l_orderAction;
    }

    /**
     * (toブックビルディング申告履歴)<BR>
     * （toOrderActionの実装）<BR>
     * <BR>
     * 指定のブックビルディング申告履歴行オブジェクトからブックビルディング<BR>
     * 申告履歴オブジェクトを生成する。<BR>
     * <BR>
     * ブックビルディング申告履歴行オブジェクトを引数に指定して、ブックビルディング<BR>
     * 申告履歴オブジェクトを生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * ブックビルディング申告履歴行オブジェクト<BR>
     * @@param l_bookbuildingOrderActionParams - (ブックビルディング申告履歴Params)<BR>
     * ブックビルディング申告履歴行オブジェクト<BR>
     * <BR>
     * ※ ブックビルディング申告履歴ParamsクラスはDDLより自動生成する。
     * 
     * @@return OrderAction
     * @@roseuid 40BFFDBC0133
     */
    public OrderAction toOrderAction(Row l_bookbuildingOrderActionParams)
    {
        WEB3IpoBookbuildingOrderActionImpl l_orderAction =
            new WEB3IpoBookbuildingOrderActionImpl(l_bookbuildingOrderActionParams);

        return l_orderAction;
    }

    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_lngArg0 - (arg0)
     * @@return OrderExecution
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0139
     */
    public OrderExecution getOrderExecution(long l_lngArg0) throws NotFoundException
    {
        return null;
    }

    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_arg0 - (arg0)
     * @@return OrderExecution
     * @@roseuid 40BFFDBC013B
     */
    public OrderExecution toOrderExecution(Row l_arg0)
    {
        return null;
    }

    /**
     * （未使用）
     * @@param l_arg0 - (arg0)
     * @@roseuid 40BFFDBC0178
     */
    public void overrideOrderValidator(OrderValidator l_arg0)
    {

    }

    /**
     * （getOrderValidatorの実装）<BR>
     * <BR>
     * this.注文チェックを返却する。
     * @@return OrderValidator
     * @@roseuid 40BFFDBC0177
     */
    public OrderValidator getOrderValidator()
    {
        return this.orderValidator;
    }

    /**
     * (createNewIPO申告)<BR>
     * 引数の内容より、新規申告状態のIPO申告オブジェクトを作成する。<BR>
     * <BR>
     * １）　@IPO申告行を取得する。<BR>
     * 　@this.getIPO申告(long)にて、IPO申告の既存行を取得する。<BR>
     * <BR>
     * 　@[getIPO申告()の引数]<BR>
     * 　@IPO申告ＩＤ：　@引数のIPO申告ＩＤ<BR>
     * <BR>
     * 　@－行が取得できなかった場合、１－１）～１－３）の手続きにてIPO申告行を<BR>新規作成する。<BR>
     * 　@－行が取得できた場合、取得したIPO申告.getDataSourceObject()にて<BR>IPO申告行を取得する。<BR>
     * <BR>
     * 　@１－１）　@IPO申告行を生成する。<BR>
     * 　@IPO申告Paramsオブジェクトを生成する。<BR>
     * 　@※IPO申告ParamsクラスはDDLより自動生成。<BR>
     * <BR>
     * 　@１－２）　@IPO申告行の各項目に初期値をセットする。<BR>
     * 　@　@－日付型（Date）、文字列型（String）の初期値 ： null<BR>
     * 　@　@－数値型（Double,double,Long,long,Integer,int）の初期値 ： 0<BR>
     * Q&A WEB3-IPO-A-ＦＴ-0037<BR>
     * 　@　@－数値型（Double,double,Long,long,Integer,int）で、NotNull項目の初期値 ： 0<BR>
     * 　@　@－数値型（Double,double,Long,long,Integer,int）で、NotNull項目以外の初期値 ： null<BR>
     * <BR>
     * 　@１－３）　@以下の項目に値をセットする。<BR>
     * <BR>
     * 　@　@IPO申告行.IPO申告ＩＤ = 引数のIPO申告ＩＤ<BR>
     * 　@　@IPO申告行.IPO銘柄ＩＤ = ブックビルディング申告内容.get銘柄ＩＤ()<BR>
     * 　@　@IPO申告行.部店ＩＤ = 補助口座.get取引店.getBranchId()<BR>
     * 　@　@IPO申告行.口座ＩＤ = 補助口座.getAccountId()<BR>
     * 　@　@IPO申告行.補助口座ＩＤ = 補助口座.getSubAccountId()<BR>
     * 　@　@IPO申告行.申告履歴最終番号 = 0<BR>
     * 　@　@IPO申告行.作成日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * ２）　@行オブジェクトにプロパティをセットする。<BR>
     * 　@１）で取得したIPO申告行オブジェクトのプロパティに、以下の通りセットを行う。<BR>
     * <BR>
     * 　@IPO申告行.申告履歴最終番号 = （IPO申告行.申告履歴最終番号＋１）<BR>
     * 　@IPO申告行.数量 = ブックビルディング申告内容.get数量()<BR>
     * 　@IPO申告行.指値 = ブックビルディング申告内容.get指値()<BR>
     * 　@IPO申告行.ブックビルディング申告状態 = OrderStatusEnum.ORDERED<BR>　@（新規）<BR>
     * 　@IPO申告行.IPO申告有効状態 = OrderOpenStatusEnum.OPEN　@<BR>
     * （有効）<BR>
     * 　@IPO申告行.新規申告日時 = TradingSystem.getSystemTimestamp()<BR>
     * 　@IPO申告行.抽選結果 = ”0：DEFAULT（未抽選）”<BR>
     * 　@IPO申告行.抽選結果（繰上） = ”0：DEFAULT（未抽選）”<BR>
     * 　@IPO申告行.電子メイル送信ステイタス = ”0：DEFAULT（初期値）”<BR>
     * 
     *   仕様変更管理台帳-モデル032<BR>
     * 　@IPO申告行.購入申込区分 = ”0：DEFAULT（初期値）”<BR>
     *
     * 　@IPO申告行.受付状態 = ”0：DEFAULT（初期値）”<BR>
     *   IPO申告行.取引者ID = ブックビルディング申告内容.getTrader().getTraderId() <BR>
     *   IPO申告行.注文経路区分 = (*1) 
     * 　@IPO申告行.更新者コード = (*2)<BR>
     * 　@IPO申告行.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * 　@<BR>
     *   (*1)　@注文経路区分 <BR>
     *   セッションプロパティよりログインチャネルを取得する。<BR>
     *   @@see OpLoginSecurityService.getSessionProperty(LOGIN_CHANNEL) <BR> 
     *   <BR>
     * 　@(*2)　@更新者コード<BR>
     * 　@（ブックビルディング申告内容.getTrader() == null）の場合<BR>
     * 　@　@－補助口座.getMainAccount().getAccountCode()<BR>
     * 　@以外の場合<BR>
     * 　@　@－ブックビルディング申告内容.getTrader().getTraderCode()<BR>
     * <BR>
     * ４）　@IPO申告オブジェクト生成<BR>
     * 　@１）～３）で作成したIPO申告行オブジェクトにて、IPO申告オブジェクトを生成し<BR>返却する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@IPO申告行オブジェクト：　@IPO申告行オブジェクト<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_bookbuidingNewOrderSpec - (ブックビルディング申告内容)<BR>
     * ブックビルディング申告内容オブジェクト
     * @@param l_lngIpoOrderId - IPO申告ＩＤ<BR>
     * <BR>
     * 新規採番した値を指定する。
     * 
     * @@return WEB3IpoOrderImpl
     * @@roseuid 40D8E4B702B7
     */
    protected WEB3IpoOrderImpl createNewIpoOrder(
        SubAccount l_subAccount,
        NewOrderSpec l_bookbuidingNewOrderSpec,
        long l_lngIpoOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewIpoOrder(SubAccount, NewOrderSpec, long)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuidingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl l_ipoOrder = null;

//        try
//        {
            //ブックビルディング申告内容オブジェクト
            WEB3IpoBookbuildingNewOrderSpec l_bbOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuidingNewOrderSpec;
                
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            
            //１）　@IPO申告行を取得する。
            //NotFoundException
            OrderUnit l_orderUnit;
            
            try
            {
                l_orderUnit = this.getOrderUnit(l_lngIpoOrderId);
            }
            catch (NotFoundException l_ex)
            {
                l_orderUnit = null;
            }
            
            IpoOrderParams l_orderParams = null;

            if (l_orderUnit == null)
            {
                //－行が取得できなかった場合、１－１）～１－３）の手続きにてIPO申告行を<BR>新規作成する。

                log.debug("Not Existing OrderUnit for IpoOrderId: " + l_lngIpoOrderId + " IPO申告行を生成する");
                
                //１－１）　@IPO申告行を生成する。
                l_orderParams = new IpoOrderParams();

                //１－２）　@IPO申告行の各項目に初期値をセットする。
                
                //Q&A WEB3-IPO-A-ＦＴ-0037
                //  －数値型（Double,double,Long,long,Integer,int）で、NotNull項目の初期値 ： 0
                //  －数値型（Double,double,Long,long,Integer,int）で、NotNull項目以外の初期値 ： null
                
                l_orderParams.setIpoOrderId(0);               //IPO申告ＩＤ
                l_orderParams.setIpoProductId(0);             //IPO銘柄ＩＤ
                l_orderParams.setBranchId(0);                 //部店ＩＤ
                l_orderParams.setAccountId(0);                //口座ＩＤ
                l_orderParams.setSubAccountId(0);             //補助口座ＩＤ
                l_orderParams.setLastOrderActionSerialNo(0);  //申告履歴最終番号
                l_orderParams.setQuantity(0);                 //数量
                l_orderParams.setLimitPrice(0);               //指値
                l_orderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);     //ブックビルディング申告状態
                l_orderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);   //IPO申告有効状態
                
                l_orderParams.setPrice(null);                //計算単価
                l_orderParams.setCurrentPrice(null);         //基準値（時価）
                l_orderParams.setBookbuildingPrice(null);    //申告相当額
                l_orderParams.setBookbuildingCreatedTimestamp(null);  //新規申告日時
                l_orderParams.setElectedQuantity(null);      //当選数量
                l_orderParams.setLotResult(null);            //抽選結果
                l_orderParams.setLotResultRetry(null);       //抽選結果（繰上）
                l_orderParams.setSubstitutePriority(null);   //優先順位
                l_orderParams.setSendMailStatus(null);       //電子メイル送信ステイタス
                l_orderParams.setOfferingDiv(null);          //購入申込区分
                l_orderParams.setAcceptStatus(null);         //受付状態
                l_orderParams.setApplicationQuantity(null);  //購入申込数量
                l_orderParams.setPayAmount(null);            //購入申込代金
                l_orderParams.setTaxType(null);   //税区分
                l_orderParams.setOfferingTimestamp(null);    //購入申込／辞退日時
                l_orderParams.setTraderId(null);             //取引者ＩＤ
                l_orderParams.setOrderRootDiv(null);         //注文経路区分
                l_orderParams.setLastUpdater(null);          //更新者コード
                l_orderParams.setCreatedTimestamp(null);     //作成日時
                l_orderParams.setLastUpdatedTimestamp(null); //更新日時

                //１－３）　@項目に値をセットする。
                l_orderParams.setIpoOrderId(l_lngIpoOrderId);
                l_orderParams.setIpoProductId(l_bbOrderSpec.getProductId());
                l_orderParams.setBranchId(
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId());
                l_orderParams.setAccountId(l_subAccount.getAccountId());
                l_orderParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderParams.setLastOrderActionSerialNo(0);
                l_orderParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
                
                // 抽選番号を取得
                String strLotNumber = this.getLotNumber();
                
                // 抽選番号をセット
                l_orderParams.setLotNumber(strLotNumber);

            }
            else
            {
                log.debug("Found OrderUnit for IpoOrderId: " + l_lngIpoOrderId  + " IPO申告行を取得する");
                //－行が取得できた場合、取得したIPO申告.getDataSourceObject()にて<BR>IPO申告行を取得する。
                IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_orderUnit.getDataSourceObject();
                
                l_orderParams = new IpoOrderParams(l_ipoOrderRow);
            }

            //２）　@行オブジェクトにプロパティをセットする。
            l_orderParams.setLastOrderActionSerialNo(l_orderParams.last_order_action_serial_no + 1);
            l_orderParams.setQuantity(l_bbOrderSpec.getQuantity());
            l_orderParams.setLimitPrice(l_bbOrderSpec.getLimitPrice());
            l_orderParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_orderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            l_orderParams.setBookbuildingCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
            l_orderParams.setLotResult(WEB3LotResultDef.DEFAULT);
            l_orderParams.setLotResultRetry(WEB3LotResultRetryDef.DEFAULT);
            l_orderParams.setSendMailStatus(WEB3SendMailStatusDef.DEFAULT);
            
            // 仕様変更管理台帳-モデル032<BR>
            // IPO申告行.購入申込区分 = ”0：DEFAULT（初期値）”<BR>
            l_orderParams.setOfferingDiv(WEB3OfferingDivDef.DEFAULT);
            
            l_orderParams.setAcceptStatus(WEB3IpoOrderAcceptStatusDef.DEFAULT);
                        
            // 取扱者IDのセット
            if (l_bbOrderSpec.getTrader() != null)
            {
                l_orderParams.setTraderId(l_bbOrderSpec.getTrader().getTraderId() );
            }


            OpLoginSecurityService l_opLoginSec =
                        (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_orderParams.setOrderRootDiv(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV) );

            //(*1)　@更新者コード
            if (l_bbOrderSpec.getTrader() == null)
            {
                log.debug("LastUpdater is a Customer");
                l_orderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LasterUpdater is a Trader");
                l_orderParams.setLastUpdater(l_bbOrderSpec.getTrader().getTraderCode());
            }

            l_orderParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

            //４）　@IPO申告オブジェクト生成
            //NotFoundException
            //コードチェック指摘事項(IPOV1.0-20040928ベース) No. 3
            l_ipoOrder =
                (WEB3IpoOrderImpl)this.toOrderUnit(l_orderParams);
            
//        }
//        catch (NotFoundException l_ex)
//        {
//            //オプション約定データが見つかりません。
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //例外をスローする
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (saveIPO申告)<BR>
     * １） IPO申告行オブジェクト取得<BR>
     * 　@IPO申告.getDataSourceObject()にてIPO申告行を取得する。<BR>
     * <BR>
     * ２） 更新情報をセットする。<BR>
     * 　@IPO申告行の以下の項目に、値をセットする。<BR>
     * <BR>
     *   IPO申告行.取引者ID = (*1)
     *   IPO申告行.注文経路区分 = (*2) 
     * 　@IPO申告行.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     *   (*1)　@取引者ID
     *   セッション情報よりログインIDを取得し、該当する扱者の扱者IDをセットする。<BR>
     *   ログインIDに該当する扱者が取得できない場合、nullをセットする。<BR>
     *   @@see OpLoginSecurityService.getLoginId() <BR>
     *   @@see FinObjectManager.getTraderByLoginId()<BR>
     *   (*2)　@注文経路区分<BR>
     *   セッションプロパティよりログインチャネルを取得する。<BR>
     *   @@see OpLoginSecurityService.getSessionProperty(LOGIN_CHANNEL) <BR>
     * <BR>
     * ３） DB更新<BR>
     * 　@IPO申告行オブジェクトの内容で、IPO申告テーブルを更新（update）する。
     *   @@throws WEB3BaseException
     *   @@param l_ipoOrder - (IPO申告)<BR>
     *   IPO申告オブジェクト
     *   @@roseuid 40CEAFC70336
     */
    public void saveIpoOrder(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveIpoOrder(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //１） IPO申告行オブジェクト取得
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();

            //２） 更新情報をセットする。
                
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            
            try
            {
                Trader l_trdTrader = l_finApp.getFinObjectManager().getTraderByLoginId(l_opLoginSec.getLoginId());
                l_ipoOrderParams.setTraderId(l_trdTrader.getTraderId());
            }
            catch(NotFoundException nf_ex)
            {
                l_ipoOrderParams.setTraderId(null);
            }
            
            
            l_ipoOrderParams.setOrderRootDiv(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV) );            
 
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                        
            l_ipoOrderParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

            log.debug("SystemTimestamp: " + l_tradingSystem.getSystemTimestamp());
            
            //３） DB更新
            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //DataQueryException
            l_queryProcessor.doUpdateQuery(l_ipoOrderParams);  
                  
            log.exiting(STR_METHOD_NAME);
        
        }
        catch (DataFindException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (saveNewブックビルディング申告履歴)<BR>
     * IPO申告の内容より、ブックビルディング申告履歴を作成、DBにinsertを行う。<BR>
     * <BR>
     * １） IPO申告行オブジェクト取得<BR>
     * 　@IPO申告.getDataSourceObject()にてIPO申告行を取得する。<BR>
     * <BR>
     * ２） 更新情報をセットする。<BR>
     * 　@ブックビルディング申告履歴行の項目に、値をセットする。<BR>
     * <BR>
     * 　@セットする内容は、<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「ﾌﾞｯｸﾋﾞﾙ共通_ブックビルディング申告履歴テーブル仕様.xls」参照。<BR>
     * <BR>
     * ３） DB更新<BR>
     * 　@ブックビルディング申告履歴行オブジェクトの内容で、ブックビルディング<BR>
     * 申告履歴テーブルを更新（insert）する。<BR>
     * @@throws WEB3BaseException
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@roseuid 40CEB11803C3
     */
    public void saveNewIpoBookbuildingOrderAction(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewIpoBookbuildingOrderAction(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //１） IPO申告行オブジェクト取得
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            IpoBookbuildingOrderActionParams l_bbOrderActionParams =
                new IpoBookbuildingOrderActionParams();

            //２） 更新情報をセットする。
            //DataNetworkException, DataQueryException
            l_bbOrderActionParams.setBookbuildingOrderActionId(IpoBookbuildingOrderActionDao.newPkValue());         //ブックビルディング申告履歴ＩＤ
            l_bbOrderActionParams.setIpoOrderId(l_ipoOrderParams.getIpoOrderId());                  //IPO申告ＩＤ
            l_bbOrderActionParams.setIpoProductId(l_ipoOrderParams.getIpoProductId());              //IPO銘柄ＩＤ
            l_bbOrderActionParams.setBranchId(l_ipoOrderParams.getBranchId());                      //部店ＩＤ
            l_bbOrderActionParams.setAccountId(l_ipoOrderParams.getAccountId());                    //口座ＩＤ
            l_bbOrderActionParams.setSubAccountId(l_ipoOrderParams.getSubAccountId());              //補助口座ＩＤ
            l_bbOrderActionParams.setOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo());  //申告履歴番号
            l_bbOrderActionParams.setQuantity(l_ipoOrderParams.getQuantity());                      //数量
            l_bbOrderActionParams.setLimitPrice(l_ipoOrderParams.getLimitPrice());                  //指値
            l_bbOrderActionParams.setOrderStatus(l_ipoOrderParams.getOrderStatus());                //ブックビルディング申告状態
            l_bbOrderActionParams.setOrderOpenStatus(l_ipoOrderParams.getOrderOpenStatus());        //IPO申告有効状態
            
            if(l_ipoOrderParams.getPriceIsNull())
            {
                l_bbOrderActionParams.setPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setPrice(l_ipoOrderParams.getPrice());                            //計算単価
            }
            
            if(l_ipoOrderParams.getCurrentPriceIsNull())
            {
                l_bbOrderActionParams.setCurrentPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setCurrentPrice(l_ipoOrderParams.getCurrentPrice());              //基準値（時価）
            }

            if(l_ipoOrderParams.getBookbuildingPriceIsNull())
            {
                l_bbOrderActionParams.setBookbuildingPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setBookbuildingPrice(l_ipoOrderParams.getBookbuildingPrice());    //申告相当額
            }

            l_bbOrderActionParams.setBookbuildingCreatedTimestamp(l_ipoOrderParams.getBookbuildingCreatedTimestamp());  //新規申告日時
            if(l_ipoOrderParams.getTraderIdIsNull())
            {
                l_bbOrderActionParams.setTraderId(null);
            }
            else
            {
                l_bbOrderActionParams.setTraderId(l_ipoOrderParams.getTraderId());//取引者ID           
            }
            
            l_bbOrderActionParams.setOrderRootDiv(l_ipoOrderParams.getOrderRootDiv());//注文経路区分            
            l_bbOrderActionParams.setLastUpdater(l_ipoOrderParams.getLastUpdater());                //更新者コード
            l_bbOrderActionParams.setDeleteFlag(BooleanEnum.FALSE);                                 //削除フラグ
            l_bbOrderActionParams.setCreatedTimestamp(l_ipoOrderParams.getLastUpdatedTimestamp());      //作成日時
            l_bbOrderActionParams.setLastUpdatedTimestamp(l_ipoOrderParams.getLastUpdatedTimestamp());//更新日時
            l_bbOrderActionParams.setActionSendStatus(WEB3ActionSendStatusDef.DEFAULT);
            log.debug("New BbOrderActionId is " + l_bbOrderActionParams.getBookbuildingOrderActionId());
            //３） DB更新
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //DataQueryException, DataNetworkException
            l_queryProcessor.doInsertQuery(l_bbOrderActionParams); 
                   
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (saveNewIPO申告)<BR>
     * 指定されたIPO申告オブジェクトの内容で、IPO申告テーブルを更新（insert）する。<BR>
     * <BR>
     * １）　@行オブジェクト取得<BR>
     * 　@引数.IPO申告.getDataSourceObject()にて行オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@DB更新<BR>
     * 　@○　@既存取消済申告が存在しない場合（IPO申告行.作成日時 ==<BR>
     *  IPO申告行.新規申告日時）、<BR>
     * 　@　@　@　@－取得した行オブジェクトの内容でIPO銘柄テーブルに行を挿入（insert）<BR>する。<BR>
     * <BR>
     * 　@○　@既存取消済申告が存在する場合（IPO申告行.作成日時 != <BR>
     * IPO申告行.新規申告日時）、<BR>
     * 　@　@　@　@－this.saveIPO申告()にて既存行を更新する。<BR>
     * <BR>
     * 　@　@　@　@[saveIPO申告()に指定する引数]<BR>
     * 　@　@　@　@IPO申告：　@IPO申告<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@roseuid 40D9043003A1
     */
    public void saveNewIpoOrder(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewIpoOrder(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //１）　@行オブジェクト取得
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            //２）　@DB更新

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); //DataNetworkException, DataFindException

            log.debug("CreatedTimestamp: " + l_ipoOrderParams.getCreatedTimestamp());
            log.debug("BookbuildingCreatedTimestamp: " + l_ipoOrderParams.getBookbuildingCreatedTimestamp());
            
            if (l_ipoOrderParams.getCreatedTimestamp().equals(l_ipoOrderParams.getBookbuildingCreatedTimestamp()))
            {
                log.debug("InsertOrder");
                l_queryProcessor.doInsertQuery(l_ipoOrderParams); //DataQueryException, DataNetworkException
            }
            else if (!l_ipoOrderParams.getCreatedTimestamp().equals(l_ipoOrderParams.getBookbuildingCreatedTimestamp()))
            {
                log.debug("SaveOrder");
                this.saveIpoOrder(l_ipoOrder);
            }
        }
        catch (DataFindException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getNewIPO申告ＩＤ)<BR>
     * IPO申告ＩＤを取得する。<BR>
     * <BR>
     * １）　@既存ＩＤの取得<BR>
     * 　@以下の条件にて、IPO申告テーブルを検索する。<BR>
     * 行が取得できた場合は、該当行のIPO申告ＩＤを返却する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO申告.IPO銘柄ＩＤ =　@IPO銘柄.getIPO銘柄ＩＤ() And<BR>
     * 　@IPO申告.部店ＩＤ =　@補助口座.get取引店().getBranchId() And<BR>
     * 　@IPO申告.口座ＩＤ =　@補助口座.getAccountId() And<BR>
     * 　@IPO申告.補助口座ＩＤ =　@補助口座.getSubAccountId()<BR>
     * <BR>
     * ２）　@IPO申告ＩＤ新規採番<BR>
     * 　@１）で行が取得できなかった場合、<BR>
     * IPO申告ＩＤを新規採番(*1)し返却する。<BR>
     * <BR>
     * (*1) IPO申告ＩＤの新規採番<BR>
     * 　@IPO申告DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@※ IPO申告DAOクラスはDDLより自動生成する。<BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * 
     * @@return long
     * @@roseuid 40D7C6E402E9
     */
    protected long getNewIpoOrderId(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewIpoOrderId(SubAccount, WEB3IpoProductImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        long l_lngOrderId = 0;
        
        try
        {
            //１）　@既存ＩＤの取得
            //DataNetworkException, DataFindException, DataQueryException
            IpoOrderRow l_orderRow =
                IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                    l_ipoProduct.getProductId(),
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                    l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId());
            
            if(l_orderRow != null)
            {
                log.debug("Found a existing OrderId: " + l_orderRow.getIpoOrderId());
                return l_orderRow.getIpoOrderId();
            }
            else
            {
                //２）　@IPO申告ＩＤ新規採番
                l_lngOrderId = IpoOrderDao.newPkValue();
                log.debug("A New OrderId Created Which is " + l_lngOrderId);
            }
        }
        catch (DataFindException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lngOrderId;
    }

    /**
     * (calc申告相当額)<BR>
     * 申告相当額を計算する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO注文）calc申告相当額」参照。
     * @@throws WEB3BaseException
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@param l_dblLimitPrice - 指値<BR>
     * ※ 成行の場合0
     * 
     * @@param l_dblQuantity - 数量
     * @@param l_dblCurrentPrice - 基準値（時価）
     * @@return WEB3IpoBookbuildingPriceCalcResult
     * @@roseuid 40D7D6640068
     */
    public WEB3IpoBookbuildingPriceCalcResult calcBookbuildingPrice(
        MainAccount l_mainAccount,
        WEB3IpoProductImpl l_ipoProduct,
        double l_dblLimitPrice,
        double l_dblQuantity,
        double l_dblCurrentPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcBookbuildingPrice(MainAccount, WEB3IpoProductImpl, double, double, double)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("Parameters: START");
        log.debug("指値: " + l_dblLimitPrice);
        log.debug("数量: " + l_dblQuantity);
        log.debug("基準値（時価）: " + l_dblCurrentPrice);
        log.debug("Parameters: END");
        
        if(Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }        
        if(Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }        
        if(Double.isNaN(l_dblCurrentPrice))
        {
            l_dblCurrentPrice = 0D;
        }
        
        //1.1.計算結果をインスタンス化する。
        WEB3IpoBookbuildingPriceCalcResult l_equivalentPriceCalcResult = new WEB3IpoBookbuildingPriceCalcResult();
        
        //1.2.isディスカウント扱い( )
        boolean l_blnIsDiscountHandling = l_ipoProduct.isDiscountHandling();
        
        if(l_blnIsDiscountHandling) //1.3.(*1) ディスカウント扱いの場合
        {
            if(l_dblCurrentPrice == 0) //1.3.1.基準値未指定
            {
                log.debug("1.3.1.基準値未指定");
                
                log.debug("mainaccount: " + l_mainAccount.getAccountId());
                log.debug("product id: " + l_ipoProduct.getProductId());
                l_dblCurrentPrice = l_ipoProduct.getCurrentPrice(l_mainAccount, l_ipoProduct); //1.3.1.1.get時価

                log.debug("1.3.1.1.get時価: " + l_dblCurrentPrice);
                
                if(Double.isNaN(l_dblCurrentPrice))
                {
                    l_dblCurrentPrice = 0D;
                }
                //1.3.2.set基準値（時価)
                l_equivalentPriceCalcResult.setCurrentPrice(l_dblCurrentPrice);
            }
            else
            {
                log.debug("1.3.2.基準値指定");
                
                l_equivalentPriceCalcResult.setCurrentPrice(l_dblCurrentPrice);
            }
            
            //1.3.3.set計算単価
            //1.3.4.set計算単価（実価格）
            if(l_dblLimitPrice == 0)
            {
                log.debug("成行の場合: (l_dblLimitPrice == 0)");
                
                double l_dblProvisionalMinValue =
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMinValue(); //仮条件下限値
                
                log.debug("仮条件下限値: " + l_dblProvisionalMinValue);
                
                if(Double.isNaN(l_dblProvisionalMinValue))
                {
                    l_dblProvisionalMinValue = 0D;
                }
                
                log.debug("1.3.3.set計算単価: " + l_dblProvisionalMinValue);
                
                l_equivalentPriceCalcResult.setPrice(l_dblProvisionalMinValue);
                
                log.debug("1.3.4.set計算単価（実価格）: " + (l_dblCurrentPrice * (100 - l_dblProvisionalMinValue) / 100));
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(
                    l_dblCurrentPrice * (100 - l_dblProvisionalMinValue) / 100);
            }
            else
            {
                log.debug("成行の場合: (l_dblLimitPrice != 0)");
                l_equivalentPriceCalcResult.setPrice(l_dblLimitPrice);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(
                    l_dblCurrentPrice * (100 - l_dblLimitPrice) / 100);
            }
            
        }
        else if(!l_blnIsDiscountHandling) //1.4.実価格の場合
        {
            //1.4.1.set計算単価
            //1.4.2.set計算単価（実価格）
            if(l_dblLimitPrice == 0)
            {
                double l_dblProvisionalMaxValue =
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMaxValue(); //仮条件上限値
                
                log.debug("仮条件上限値: " + l_dblProvisionalMaxValue);
                if(Double.isNaN(l_dblProvisionalMaxValue))
                {
                    l_dblProvisionalMaxValue = 0D;
                }
                
                l_equivalentPriceCalcResult.setPrice(l_dblProvisionalMaxValue);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(l_dblProvisionalMaxValue);
            }
            else
            {
                log.debug("LimitPrice: " + l_dblLimitPrice);
                l_equivalentPriceCalcResult.setPrice(l_dblLimitPrice);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(l_dblLimitPrice);
            }
            
            //Q&A:WEB3-IPO-A-FT-0055
            l_equivalentPriceCalcResult.setCurrentPrice(Double.NaN);
        }
        
        //1.5.get計算単価（実価格）
        double l_dblCalcUnitPriceTruePrice = l_equivalentPriceCalcResult.getCalcUnitPriceReal();
        
        log.debug("1.5.get計算単価（実価格）: " + l_dblCalcUnitPriceTruePrice);
        
        if(Double.isNaN(l_dblCalcUnitPriceTruePrice))
        {
            l_dblCalcUnitPriceTruePrice = 0D;
        }
        
        //1.6.set申告相当額
        log.debug("1.6.set申告相当額: " + (l_dblQuantity * l_dblCalcUnitPriceTruePrice));
        l_equivalentPriceCalcResult.setBookbuildingPrice(l_dblQuantity * l_dblCalcUnitPriceTruePrice);
        
        //1.7 validate申告相当額
        l_equivalentPriceCalcResult.validateBookbuildingPrice(); //WEB3BaseException
        
        log.exiting(STR_METHOD_NAME);
        
        return l_equivalentPriceCalcResult;
    }

    /**
     * (validateブックビルディング申告)<BR>
     * （validateNewOrderの実装）<BR>
     * <BR>
     * ブックビルディング申告入力審査を実施する。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）validateブックビルディング申告」参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 助口座オブジェクト
     * @@param l_productType - 銘柄タイプ
     * @@param l_bookbuildingNewOrderSpec - (ブックビルディング申告内容)<BR>
     * ブックビルディング申告内容オブジェクト
     * @@return NewOrderValidationResult
     * @@roseuid 40BFFDBC013D
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_bookbuildingNewOrderSpec)
    {
        final String STR_METHOD_NAME = " validateNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        NewOrderValidationResult l_newOrderValidationResult = null;
        
        try
        {
            //ブックビルディング申告内容オブジェクト
            WEB3IpoBookbuildingNewOrderSpec l_ipoBookbuildingOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuildingNewOrderSpec;
            
            //1.1.getOrderValidator( )
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.validate取引可能顧客
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);

            log.debug("OrderValidationResult: " + l_orderValidationResult.getProcessingResult().toString());
            
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                return new NewOrderValidationResult(l_orderValidationResult.getProcessingResult());
            }
            
            //1.3.get銘柄ＩＤ
            long l_lngProductId = l_ipoBookbuildingOrderSpec.getProductId();

            log.debug("1.3.get銘柄ＩＤ: " + l_lngProductId);
            
            //1.4.IPO銘柄
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct =
                (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngProductId);
            
            //1.5.validateブックビルディング銘柄
            //WEB3BaseException
            log.debug("1.5.validateブックビルディング銘柄");
            l_orderValidator.validateBookbuildingProduct(l_ipoProduct);
            
            //1.6.validate二重申告
            log.debug("1.6.validate二重申告");
            l_orderValidator.validateDuplicateOrder(l_subAccount, l_ipoProduct);
            
            //1.7.get数量
            double l_dblQuantity = l_ipoBookbuildingOrderSpec.getQuantity();
            
            log.debug("1.7.get数量: " + l_dblQuantity);
            
            //1.8.validate数量
            l_orderValidator.validateQuantity(l_ipoProduct, l_dblQuantity);
            
            //1.9.get指値
            double l_dblLimitPrice = l_ipoBookbuildingOrderSpec.getLimitPrice();
            
            log.debug("1.9.get指値量: " + l_dblLimitPrice);
            
            //1.10.validate単価
            log.debug("1.10.validate単価");
            l_orderValidator.validatePrice(l_ipoProduct, l_dblLimitPrice);
            
            //1.11.is上限申告株数チェック
            //上限申告株数チェックを実施するかを判定する。 
            //[引数] 
            //補助口座 : 補助口座オブジェクト
            log.debug("1.11.is上限申告株数チェック");
            boolean l_blnReturn = this.isMaxDemandProductCountCheck(l_subAccount);
            
            //1.12.is上限申告株数チェック（）の戻り値 == true の場合、以下の処理を実行
            if (l_blnReturn)
            {
                //1.12.1validate上限申告株数(IPO銘柄, double)
                log.debug("1.12.1validate上限申告株数(IPO銘柄, double)");
                l_orderValidator.validateMaxDemandProductCount(l_ipoProduct, l_dblQuantity);
            }
            
            //1.13.getNewIPO申告ＩＤ
            long l_lngNewIpoOrderId = this.getNewIpoOrderId(l_subAccount, l_ipoProduct);
            
            log.debug("1.13.getNewIPO申告ＩＤ: " + l_lngNewIpoOrderId);
            
            //1.14.NewOrderValidationResult
            l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_orderValidationResult.getProcessingResult(),
                    l_lngNewIpoOrderId);
        }
        catch (NotFoundException l_ex)
        {
        	//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME
//            );
        }

        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * (submitブックビルディング申告)<BR>
     * （submitNewOrderの実装）<BR>
     * <BR>
     * ブックビルディング申告を登録する。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）submitブックビルディング申告」参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - 銘柄タイプ
     * @@param l_bookbuildingNewOrderSpec - (ブックビルディング申告内容)<BR>
     * ブックビルディング申告内容オブジェクト
     * @@param l_lngIpoOrderId - IPO申告ＩＤ<BR>
     * <BR>
     * 新規採番した値を指定する。
     * 
     * @@param l_strTradedPassword - 取引パスワード
     * @@param l_blnIsSkipOrderSpecValidation - (isSkip申告内容審査)<BR>
     * 申告内容審査（validate）をスキップするかを判定するフラグ。<BR>
     * <BR>
     * スキップする場合true、審査を実施する場合falseを指定する。
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC0141
     */
    public OrderSubmissionResult submitNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_bookbuildingNewOrderSpec,
        long l_lngIpoOrderId,
        String l_strTradedPassword,
        boolean l_blnIsSkipOrderSpecValidation)
    {
        final String STR_METHOD_NAME = " submitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec, long, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_orderValidationResult = null;
        
        try
        {
            WEB3IpoBookbuildingNewOrderSpec l_ipoBbOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuildingNewOrderSpec;

            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.getTrader
            Trader l_trader = l_bookbuildingNewOrderSpec.getTrader();

            //1.3.validate取引パスワード
            log.debug("1.3.validate取引パスワード");
            
            l_orderValidationResult =
                l_orderValidator.validateTradingPassword(
                    l_trader,
                    l_subAccount,
                    l_strTradedPassword);
            
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate取引パスワード Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
            }

            //1.4.isSkip申告内容審査
            log.debug("1.4.isSkip申告内容審査: " + !l_blnIsSkipOrderSpecValidation);
            
            if (!l_blnIsSkipOrderSpecValidation)
            {
                //1.4.1.validateブックビルディング申告
                log.debug("1.4.1.validateブックビルディング申告");
                
                l_orderValidationResult =
                    this.validateNewOrder(l_subAccount, l_productType, l_bookbuildingNewOrderSpec);
                    
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validateブックビルディング申告 Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }

            //1.5.createNewIPO申告
            WEB3IpoOrderImpl l_ipoOrder =
                this.createNewIpoOrder(l_subAccount, l_bookbuildingNewOrderSpec, l_lngIpoOrderId);
            log.debug("NewIpoOrderId: " + l_ipoOrder.getOrderId());

            //1.5.1.IPO申告
            //IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();

            //1.6.get指値
            double l_dblLimitPrice = l_ipoBbOrderSpec.getLimitPrice();
            log.debug("1.6.get指値: " + l_dblLimitPrice);

            //1.7.get数量
            double l_dblQuantity = l_ipoBbOrderSpec.getQuantity();
            log.debug("1.7.get数量: " + l_dblQuantity);

            //1.8.get銘柄ＩＤ
            long l_lngProductId = l_ipoBbOrderSpec.getProductId();
            log.debug("1.8.get銘柄ＩＤ: " + l_lngProductId);

            //1.9.get基準値（時価）
            double l_dblBasePrice = l_ipoBbOrderSpec.getCurrentPrice();
            log.debug("1.9.get基準値（時価）: " + l_dblBasePrice);

            //1.10.IPO銘柄
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct =
                (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngProductId);
            
            //1.11.calc申告相当額
            log.debug("1.11.calc申告相当額");
            //WEB3BaseException
            WEB3IpoBookbuildingPriceCalcResult l_calcResult = this.calcBookbuildingPrice(
                l_subAccount.getMainAccount(),
                l_ipoProduct,
                l_dblLimitPrice,
                l_dblQuantity,
                l_dblBasePrice);
            
            //1.12.set申告相当額計算結果
            l_ipoOrder.setBookbuildingPriceCalcResult(l_calcResult);
            
            //1.13.saveNewIPO申告
            log.debug("1.13.saveNewIPO申告");
            this.saveNewIpoOrder(l_ipoOrder);
            
            //1.14.saveNewブックビルディング申告履歴
            log.debug("1.14.saveNewブックビルディング申告履歴");
            this.saveNewIpoBookbuildingOrderAction(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));

        }
        
        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
    }

    /**
     * (validateブックビルディング訂正)<BR>
     * （validateChangeOrderの実装）<BR>
     * <BR>
     * ブックビルディング訂正入力審査を実施する。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）validateブックビルディング訂正」参照。<BR>
     * <BR>
     * ========================================================<BR>
     *  シーケンス図:（IPO注文）validateブックビルディング訂正  <BR>
     *  1.13.訂正内容チェック<BR>
     * 　@数量、単価に訂正がない場合※は、例外をスローする。<BR>
     *   <BR>
　@   *  ※　@訂正がない場合の判定<BR>
　@   *（ブックビルディング訂正内容.get数量() == IPO申告.get数量() &&<BR>
　@　@ * ブックビルディング訂正内容.get指値() == IPO申告.get指値()）<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00595<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_bookbuildingChangeOrderSpec - (ブックビルディング訂正内容)<BR>
     * ブックビルディング訂正内容オブジェクト
     * @@return OrderValidationResult
     * @@roseuid 40BFFDBC0148
     */
    public OrderValidationResult validateChangeOrder(
        SubAccount l_subAccount,
        ChangeOrderSpec l_bookbuildingChangeOrderSpec)
    {
        final String STR_METHOD_NAME = " validateChangeOrder(SubAccount, ChangeOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingChangeOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_resultHolder = null;
        
        try
        {
            //ブックビルディング訂正内容オブジェクト
            WEB3IpoBookbuildingChangeOrderSpec l_ipoBbChangeSpec = (WEB3IpoBookbuildingChangeOrderSpec)l_bookbuildingChangeOrderSpec;
            
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate取引可能顧客
            l_resultHolder = l_orderValidator.validateSubAccountForTrading(l_subAccount);
            
            if(l_resultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate取引可能顧客 Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_resultHolder.getProcessingResult());
            }
        
            //1.3.getOrderId
            long l_lngOrderId = l_bookbuildingChangeOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.getIPO銘柄
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.6.validateブックビルディング銘柄
            //WEB3BaseException
            log.debug("1.6.validateブックビルディング銘柄");
            l_orderValidator.validateBookbuildingProduct(l_ipoProduct);
            
            //1.7.get数量
            double l_dblQuantity = l_ipoBbChangeSpec.getQuantity();
            log.debug("1.7.get数量" + l_dblQuantity);
            
            //1.8.validate数量
            log.debug("1.8.validate数量");
            l_orderValidator.validateQuantity(l_ipoProduct, l_dblQuantity);
            
            //1.9.is上限申告株数チェック
            //上限申告株数チェックを実施するかを判定する。 
            //[引数] 
            //補助口座 : 補助口座オブジェクト
            log.debug("1.9.is上限申告株数チェック");
            boolean l_blnReturn = this.isMaxDemandProductCountCheck(l_subAccount);
            
            //1.10.is上限申告株数チェック（）の戻り値 == true の場合、以下の処理を実行
            if (l_blnReturn)
            {
                //1.10.1validate上限申告株数(IPO銘柄, double)
                log.debug("1.12.1validate上限申告株数(IPO銘柄, double)");
                l_orderValidator.validateMaxDemandProductCount(l_ipoProduct, l_dblQuantity);
            }
            
            //1.11.get指値
            double l_dblLimitPrice = l_ipoBbChangeSpec.getLimitPrice();
            log.debug("1.9.get指値" + l_dblLimitPrice);
            
            //1.12.validate単価
            log.debug("1.10.validate単価");
            l_orderValidator.validatePrice(l_ipoProduct, l_dblLimitPrice);
            
            //1.13.get数量
            double l_dblOrderQuantity = l_ipoOrder.getQuantity();
            log.debug("1.11.get数量" + l_dblOrderQuantity);
            
            //1.14.get指値
            double l_dblOrderLimitPrice = l_ipoOrder.getLimitPrice();
            log.debug("1.12.get指値" + l_dblOrderLimitPrice);
            
            //1.15.訂正内容チェック
            log.debug("1.13.訂正内容チェック");
            if(l_dblQuantity == l_dblOrderQuantity && l_dblLimitPrice == l_dblOrderLimitPrice)
            {
                log.debug("1.13.訂正内容チェック Failed");
                log.error(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00595,
                    getClass().getName() + "validateChangeOrder");
            }
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        //1.16.OrderValidationResult
        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_resultHolder.getProcessingResult());
    }

    /**
     * (submitブックビルディング訂正)<BR>
     * （submitChangeOrderの実装）<BR>
     * <BR>
     * ブックビルディング訂正を登録する。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）submitブックビルディング訂正」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_bookbuildingChangeOrderSpec - (ブックビルディング訂正内容)<BR>
     * ブックビルディング訂正内容オブジェクト
     * @@param l_strTradedPassword - 取引パスワード
     * @@param l_blnIsSkipChangeSpecValidation - (isSkip訂正内容審査)<BR>
     * 訂正内容審査（validate）をスキップするかを判定するフラグ。<BR>
     * <BR>
     * スキップする場合true、審査を実施する場合falseを指定する。
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC014B
     */
    public OrderSubmissionResult submitChangeOrder(
        SubAccount l_subAccount,
        ChangeOrderSpec l_bookbuildingChangeOrderSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipChangeSpecValidation)
    {
        final String STR_METHOD_NAME = " submitChangeOrder(SubAccount, ChangeOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingChangeOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_orderValidationResult = null;

        try
        {
            //ブックビルディング訂正内容オブジェクト
            WEB3IpoBookbuildingChangeOrderSpec l_bbChangeSpec =
                (WEB3IpoBookbuildingChangeOrderSpec)l_bookbuildingChangeOrderSpec;

            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get取引者
            Trader l_trader = l_bbChangeSpec.getTrader();

            //1.3.validate取引パスワード
            l_orderValidationResult =
                l_orderValidator.validateTradingPassword(
                    l_trader,
                    l_subAccount,
                    l_strTradedPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate取引パスワード Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
            }

            //1.4.isSkip申告内容審査 == false
            if (!l_blnIsSkipChangeSpecValidation)
            {
                //1.4.1.validateブックビルディング訂正
                log.debug("1.4.1.validateブックビルディング訂正");
                l_orderValidationResult = 
                    this.validateChangeOrder(l_subAccount, l_bookbuildingChangeOrderSpec);
                
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validateブックビルディング訂正 Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_bbChangeSpec.getOrderId();
            log.debug("1.5.getOrderId" + l_lngOrderId);

            //1.6.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            l_ipoOrder.createForUpdateParams();
            
            //1.7.getDataSourceObject
//            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();
            
            //1.8.get取引者
            //l_trader = l_bbChangeSpec.getTrader();
            
            //1.9.get数量
            double l_dblQuantity = l_bbChangeSpec.getQuantity();
            log.debug("1.9.get数量" + l_dblQuantity);
            
            //1.10.get指値
            double l_dblLimitPrice = l_bbChangeSpec.getLimitPrice();
            log.debug("1.10.get指値" + l_dblLimitPrice);
            
            //1.11.get基準値（時価)
            double l_dblBasePrice = l_bbChangeSpec.getCurrentPrice();
            log.debug("1.11.get基準値（時価)" + l_dblBasePrice);
            
            //更新用の行オブジェクトを生成する。
//            l_ipoOrder.createForUpdateParams();
                        
            //1.12.IPO申告行（IPO申告Params）にプロパティセット
            l_ipoOrderParams.setLastOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo() + 1);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            l_ipoOrderParams.setQuantity(l_dblQuantity);
            l_ipoOrderParams.setLimitPrice(l_dblLimitPrice);
            if(l_trader == null)
            {
                log.debug("LastUpdate is a customer");
                l_ipoOrderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LastUpdate is a trader");
                l_ipoOrderParams.setLastUpdater(l_trader.getTraderCode());
            }
            
            //1.13.calc申告相当額
            //WEB3BaseException
            WEB3IpoBookbuildingPriceCalcResult l_calcResult =
                this.calcBookbuildingPrice(
                    l_subAccount.getMainAccount(),
                    (WEB3IpoProductImpl)l_ipoOrder.getProduct(),
                    l_dblLimitPrice,
                    l_dblQuantity,
                    l_dblBasePrice);
            
            //1.14.set申告相当額計算結果
            l_ipoOrder.setBookbuildingPriceCalcResult(l_calcResult);
            
            //1.15.saveIPO申告
            //WEB3BaseException
            this.saveIpoOrder(l_ipoOrder);
            
            //1.16.saveNewブックビルディング申告履歴
            //WEB3BaseException
            this.saveNewIpoBookbuildingOrderAction(l_ipoOrder);
                       
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
     
        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
    }

    /**
     * (validateブックビルディング取消)<BR>
     * （validateCancelOrderの実装）<BR>
     * <BR>
     * ブックビルディング取消審査を行う。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）validateブックビルディング取消」参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_bookbuildingCancelOrderSpec - (ブックビルディング取消内容)<BR>
     * ブックビルディング取消内容オブジェクト<BR>
     * @@return OrderValidationResult
     * @@roseuid 40BFFDBC0150
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_bookbuildingCancelOrderSpec)
    {
        final String STR_METHOD_NAME = " validateCancelOrder(SubAccount, CancelOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingCancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_resultHolder = null;
        
        try
        {
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate取引可能顧客
            l_resultHolder = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
            if(l_resultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate取引可能顧客 Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_resultHolder.getProcessingResult());
            }
            //1.3.getOrderId
            long l_lngOrderId = l_bookbuildingCancelOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.getIPO銘柄
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //??1.6.getIPO銘柄ＩＤ ?? Unused
            //long l_lngProductId = l_product.getProductId();
            
            //1.7.validateブックビルディング銘柄
            //WEB3BaseException
            log.debug("1.7.validateブックビルディング銘柄");
            l_orderValidator.validateBookbuildingProduct(l_product);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        
        //1.8.OrderValidationResult
        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_resultHolder.getProcessingResult());
    }

    /**
     * (submitブックビルディング取消)<BR>
     * （submitCancelOrderの実装）<BR>
     * <BR>
     * ブックビルディング取消を登録する。<BR>
     * シーケンス図<BR>
     * 「（IPO注文）submitブックビルディング取消」参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_bookbuildingCancelOrderSpec - (ブックビルディング取消内容)<BR>
     * ブックビルディング取消内容オブジェクト
     * @@param l_strTradedPassword - 取引パスワード
     * 
     * @@param l_blnIsSkipCancelSpecValidation - (isSkip取消内容審査)<BR>
     * 取消内容審査（validate）をスキップするかを判定するフラグ。<BR>
     * <BR>
     * スキップする場合true、審査を実施する場合falseを指定する。
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC0153
     */
    public OrderSubmissionResult submitCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_bookbuildingCancelOrderSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipCancelSpecValidation)
    {
        final String STR_METHOD_NAME = " submitCancelOrder(SubAccount, CancelOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingCancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            //ブックビルディング取消内容オブジェクト
            WEB3IpoBookbuildingCancelOrderSpec l_bbCancelOrderSpec =
                (WEB3IpoBookbuildingCancelOrderSpec)l_bookbuildingCancelOrderSpec;
        
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.get取引者
            Trader l_trader = l_bbCancelOrderSpec.getTrader();
        
            //1.3.validate取引パスワード
            l_processingResultHolder =
                l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);
                
            if(l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate取引パスワード Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }
        
            //1.4.isSkip申告内容審査 == false
            if (!l_blnIsSkipCancelSpecValidation)
            {
                //1.4.1.validateブックビルディング取消
                log.debug("1.4.1.validateブックビルディング取消");
                OrderValidationResult l_orderValidationResult = this.validateCancelOrder(l_subAccount, l_bbCancelOrderSpec);
                
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validateブックビルディング取消 Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }
        
            //1.5.getOrderId
            long l_lngOrderId = l_bbCancelOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);
        
            //1.6.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            l_ipoOrder.createForUpdateParams();
            
            //1.7.getDataSourceObject
//            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
//            
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);  
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();  
            
            //1.8.get取引者
            //l_trader = l_bbCancelOrderSpec.getTrader();
            
            //更新用の行オブジェクトを生成する。
//            l_ipoOrder.createForUpdateParams();
            
            //1.9.IPO申告行（IPO申告Params）にプロパティセット
            l_ipoOrderParams.setLastOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo() + 1);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            if(l_trader == null)
            {
                log.debug("LastUpdater is a customer");
                l_ipoOrderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LastUpdater is a trader");
                l_ipoOrderParams.setLastUpdater(l_trader.getTraderCode());
            }
            
            //NotFoundException
            WEB3IpoOrderImpl l_newIpoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().toOrderUnit(l_ipoOrderParams);
            
            //1.10.saveIPO申告
            log.debug("1.10.saveIPO申告");
            this.saveIpoOrder(l_newIpoOrder);

            //1.11.saveNewブックビルディング申告履歴
            //WEB3BaseException
            log.debug("1.11.saveNewブックビルディング申告履歴");
            this.saveNewIpoBookbuildingOrderAction(l_newIpoOrder);
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }

    /**
     * (validate購入申込)<BR>
     * 購入申込審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO注文）validate購入申込」参照。
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_offerSpec - (購入申込内容)<BR>
     * 購入申込内容オブジェクト
     * @@return OrderValidationResult
     * @@roseuid 40DBC2B50004
     */
    public OrderValidationResult validateOffer(
        SubAccount l_subAccount,
        ChangeOrderSpec l_offerSpec)
    {
        final String STR_METHOD_NAME = " validateOffer(SubAccount, WEB3IpoChangeOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_offerSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processoringResultHolder = null;
        
        try
        {
            WEB3IpoChangeOrderSpec l_changeOrderSpec = (WEB3IpoChangeOrderSpec)l_offerSpec;
            
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate取引可能顧客
            l_processoringResultHolder =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
                
            if(l_processoringResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate取引可能顧客 Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_processoringResultHolder.getProcessingResult());
            }
                    
            //1.3.getOrderId
            long l_lngOrderId = l_changeOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.validate購入申込・辞退可能
            //WEB3BaseException
            log.debug("1.5.validate購入申込・辞退可能");
            l_orderValidator.validateOfferDeclinePossible(l_ipoOrder);
            
            //1.6.getIPO銘柄
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.7.validate購入申込可能銘柄
            log.debug("1.7.validate購入申込可能銘柄");
            l_orderValidator.validateOfferPossibleProduct(l_ipoProduct);
            
            //1.8.validate二重申込・辞退
            log.debug("1.8.validate二重申込・辞退");
            l_orderValidator.validateDuplicateAppDecline(l_ipoOrder);
            
            //1.9.get税区分
            TaxTypeEnum l_taxTypeEnum = l_changeOrderSpec.getTaxType();
            log.debug("1.9.get税区分: " + l_taxTypeEnum.toString());
            
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
            //1.10.validate特定口座
            log.debug("1.10.validate特定口座");
            l_orderValidator.validateSpecialAccount(
                l_subAccount,
                l_taxTypeEnum,
                l_ipoProductRow.getPublicOfferingDate());
            
            //1.11.get購入申込数量
            double l_dblOfferQuantity = l_changeOrderSpec.getApplicationQuantity();
            log.debug("1.11.get購入申込数量: " + l_dblOfferQuantity);
            
            //1.12.validate購入申込数量
            log.debug("1.12.validate購入申込数量");
            l_orderValidator.validateApplicationQuantity(
                l_ipoProduct,
                l_dblOfferQuantity,
                ((IpoOrderRow)l_ipoOrder.getDataSourceObject()).getElectedQuantity());

            //get部店ID
            long l_lngBranchId = l_ipoOrder.getBranchId();

            // is購入申込代金チェック(long)
            //部店ID： get部店ID()の戻り値
            boolean l_blnIsPayAmount = this.isPayAmountCheck(l_lngBranchId);

            //購入申込代金をチェックする場合
            if (l_blnIsPayAmount)
            {
                //validate購入申込代金(補助口座, IPO銘柄, IPO申告, double)
                //[引数]
                // 補助口座： 引数.補助口座
                // IPO銘柄： getIPO銘柄()の戻り値
                // IPO申告： IPO申告()の戻り値
                // 購入申込数量： get購入申込数量()の戻り値
                l_orderValidator.validatePayAmount(
                    l_subAccount,
                    l_ipoProduct,
                    l_ipoOrder,
                    l_dblOfferQuantity);
            }
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  END
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_processoringResultHolder.getProcessingResult());
    }

    /**
     * (submit購入申込)<BR>
     * IPO申告に購入申込を更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO注文）submit購入申込」参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_offerSpec - (購入申込内容)<BR>
     * 購入申込内容オブジェクト
     * @@param l_strTradedPassword - 取引パスワード
     * 
     * @@param l_blnIsSkipOfferSpecValidation - (isSkip購入申込内容審査)<BR>
     * 購入申込内容審査（validate）をスキップするかを判定するフラグ。<BR>
     * <BR>
     * スキップする場合true、審査を実施する場合falseを指定する。
     * @@throws WEB3BaseException
     * @@return OrderSubmissionResult
     * @@roseuid 40DBC2B50014
     */
    public OrderSubmissionResult submitOffer(
        SubAccount l_subAccount,
        ChangeOrderSpec l_offerSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipOfferSpecValidation)
    {
        final String STR_METHOD_NAME = " submitOffer(SubAccount, WEB3IpoChangeOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_offerSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            WEB3IpoChangeOrderSpec l_changeOrderSpec = (WEB3IpoChangeOrderSpec)l_offerSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get取引者
            Trader l_trader = l_changeOrderSpec.getTrader();

            //1.3.validate取引パスワード
            l_processingResultHolder =
                orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);

            if (l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate取引パスワード Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }

            //1.4.isSkip購入申込内容審査 == false
            if (!l_blnIsSkipOfferSpecValidation)
            {
                //1.4.1.validate購入申込
                log.debug(".4.1.validate購入申込");
                l_processingResultHolder = this.validateOffer(l_subAccount, l_changeOrderSpec);

                if (l_processingResultHolder.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validate購入申込 Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_changeOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);

            //1.6.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.7.get購入申込数量
            double l_dblOfferQuantity = l_changeOrderSpec.getApplicationQuantity();
            log.debug("1.7.get購入申込数量: " + l_dblOfferQuantity);
            
            //1.8.get税区分
            TaxTypeEnum l_taxTypeEnum = l_changeOrderSpec.getTaxType();
            log.debug("1.8.get税区分: " + l_taxTypeEnum.toString());
            
            //更新用の行オブジェクトを生成する。
            l_ipoOrder.createForUpdateParams();
            
            //1.9.購入申込
            log.debug("1.9.購入申込");
            l_ipoOrder.offer(l_trader, l_dblOfferQuantity, l_taxTypeEnum);
            
            //1.10.saveIPO申告
            log.debug("1.10.saveIPO申告");
            //WEB3BaseException
            this.saveIpoOrder(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));

        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }

    /**
     * (validate辞退)<BR>
     * 辞退審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO注文）validate辞退」参照。
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_declineSpec - (辞退内容)<BR>
     * 辞退内容オブジェクト
     * @@return OrderValidationResult
     * @@roseuid 40DBDF8F0359
     */
    public OrderValidationResult validateDecline(
        SubAccount l_subAccount,
        CancelOrderSpec l_declineSpec)
    {
        final String STR_METHOD_NAME = " validateDecline(SubAccount, WEB3IpoCancelOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_declineSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderValidationResult l_orderValidationResult = null;
        
        try
        {
            WEB3IpoCancelOrderSpec l_cancelOrderSpec = (WEB3IpoCancelOrderSpec)l_declineSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.validate取引可能顧客
            l_orderValidationResult = orderValidator.validateSubAccountForTrading(l_subAccount);
            
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate取引可能顧客 Failed");
                log.exiting(STR_METHOD_NAME);
                return l_orderValidationResult;
            }
            
            //1.3.getOrderId
            long l_lngOrderId = l_cancelOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);

            //1.4.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.validate購入申込・辞退可能
            log.debug("1.5.validate購入申込・辞退可能");
            orderValidator.validateOfferDeclinePossible(l_ipoOrder);
            
            //1.6.getIPO銘柄
            WEB3IpoProductImpl l_ipoProoduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.7.validate辞退可能銘柄
            log.debug("1.7.validate辞退可能銘柄");
            orderValidator.validateDeclinePossibleProduct(l_ipoProoduct);
            
            //1.8.validate二重申込・辞退
            log.debug("1.8.validate二重申込・辞退");
            orderValidator.validateDuplicateAppDecline(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (submit辞退)<BR>
     * IPO申告に辞退を更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO注文）submit辞退」参照。
     * @@throws WEB3BaseException
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_declineSpec - (辞退内容)<BR>
     * 辞退内容オブジェクト
     * @@param l_strTradedPassword - 取引パスワード
     * 
     * @@param l_blnIsSkipDeclineValidation - (isSkip辞退審査)<BR>
     * 辞退審査（validate）をスキップするかを判定するフラグ。<BR>
     * <BR>
     * スキップする場合true、審査を実施する場合falseを指定する。
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40DBDF8F0369
     */
    public OrderSubmissionResult submitDecline(
        SubAccount l_subAccount,
        WEB3IpoCancelOrderSpec l_declineSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipDeclineValidation)
    {
        final String STR_METHOD_NAME = " submitDecline(SubAccount, WEB3IpoCancelOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_declineSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            WEB3IpoCancelOrderSpec l_cancelOrderSpec = (WEB3IpoCancelOrderSpec)l_declineSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get取引者
            Trader l_trader = l_cancelOrderSpec.getTrader();

            //1.3.validate取引パスワード
            l_processingResultHolder = orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);

            if (l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate取引パスワード Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }

            //1.4.isSkip辞退審査 == false
            if (!l_blnIsSkipDeclineValidation)
            {
                //1.4.1.validate辞退
                log.debug("1.4.1.validate辞退");
                l_processingResultHolder = this.validateDecline(l_subAccount, l_cancelOrderSpec);

                if (l_processingResultHolder.getProcessingResult().isFailedResult())
                {     
                    log.debug("1.4.1.validate辞退 Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_cancelOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);

            //1.6.IPO申告
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            //更新用の行オブジェクトを生成する。
            l_ipoOrder.createForUpdateParams(); 
            
            //1.7.辞退
            log.debug("1.7.辞退");
            l_ipoOrder.decline(l_trader);
            
            //1.8.saveIPO申告
            log.debug("1.8.saveIPO申告");
            this.saveIpoOrder(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttionの例外処理修正　@坂上@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }
    
    /**
     * (get抽選番号)<BR>
     * IPO申告時に抽選番号（乱数）を生成し、返却する。<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     * @@return lotNumber String
     * @@roseuid 40DBDF8F0369
     */
    private String getLotNumber() {
        
        // １）抽選番号桁数生成
        int lotNumberFigure = 10;
        
        int lotNumber = 0;

        // ２）抽選番号桁数生成
        Random random = new Random();

        // ３）乱数格納配列を生成
        int[] randomNumbers = new int[lotNumberFigure];
                
        int numberCount = 0;
        
        // ４）抽選番号桁数のサイズ分、Loop処理を行う。
        while(numberCount < lotNumberFigure){
            // ４－１）乱数の生成
            int number = random.nextInt(10);
            
            boolean checkFlag = true;

            // ４－２）乱数を格納
            randomNumbers[numberCount] = number;

            numberCount++;
        }

        int i = 0;
        
        StringBuffer strLotNumber = new StringBuffer();
        // ５）乱数格納配列の要素をappendする。
        while(i < randomNumbers.length){
            
            strLotNumber.append(Integer.toString(randomNumbers[i]));

            i++;                        
        }
        // ６）生成した抽選番号をString型に変換し、返却する。
        return strLotNumber.toString();
    }
    
    
    /**
     * (is上限申告株数チェック)<BR>
     * 上限申告株数チェックを実施するかを判定する。<BR> 
     * <BR>
     * [戻り値] <BR>
     * true： チェック要 <BR>
     * false： チェック不要 <BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 <BR>
     * <BR>
     * [条件] <BR>
     * 部店ID = 引数:補助口座オブジェクトから取得した部店ID <BR>
     * プリファ@レンス名 = "ipo.limit.quantity.check" <BR>
     * プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * 取得したレコード.プリファ@レンスの値 == ”チェック実施” の場合、true を返却する。<BR> 
     * <BR>
     * それ以外の場合は、falseを返却する。<BR> 
     * ※レコードが取得できなかった場合も含む。<BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public boolean isMaxDemandProductCountCheck(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMaxDemandProductCountCheck(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        // 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 
        // [条件] 
        // 部店ID = 引数:補助口座オブジェクトから取得した部店ID 
        // プリファ@レンス名 = "ipo.limit.quantity.check" 
        // プリファ@レンス名の連番 = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            WEB3GentradeBranch l_genBranch = 
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_genBranch.getBranchId(),
                WEB3BranchPreferencesNameDef.IPO_LIMIT_QUANTITY_CHECK,
                1);
        } 
        catch (DataNetworkException l_nex) 
        {
            log.error("DBへのアクセスに失敗しました:", l_nex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        
        // 取得したレコード.プリファ@レンスの値 == ”申告株数チェック要” の場合、true を返却する。 
        if (l_branchReferencesRow != null && WEB3IpoLimitQuantityCheckDef.CHECK_QUANTITY.equals(
                l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        // それ以外の場合は、falseを返却する。 
        //※レコードが取得できなかった場合も含む。
        log.exiting(STR_METHOD_NAME);
        return false;     
    }

    /**
     * (is購入申込代金チェック)<BR>
     * 購入申込代金をチェックするか否かを判定する。<BR>
     * <BR>
     * [戻り値] <BR>
     * true： チェックする。<BR>
     * false： チェックしない。<BR>
     * <BR>
     * １）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
     * <BR>
     * [条件] <BR>
     * 　@部店ID = 引数.部店ID<BR>
     * 　@プリファ@レンス名 = "ipo.offer.tradingpower.check"（<BR>
     * 　@IPO購入申込余力チェック）<BR>
     * 　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 = 1：チェックする の場合、<BR>
     * trueを返却する。<BR>
     * <BR>
     * ３）それ以外の場合、falseを返却する。<BR>
     * 　@※レコードが取得できなかった場合も含む。<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isPayAmountCheck(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPayAmountCheck(long)";
        log.entering(STR_METHOD_NAME);

        //１）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。
        //[条件]
        //部店ID = 引数.部店ID
        //プリファ@レンス名 = "ipo.offer.tradingpower.check"（IPO購入申込余力チェック）
        //プリファ@レンス名の連番 = 1
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.IPO_OFFER_TRADINGPOWER_CHECK,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ２）取得したレコード.プリファ@レンスの値 = 1：チェックする の場合、trueを返却する。
        if (l_branchReferencesRow != null
            && WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //それ以外の場合は、falseを返却する。
        //※レコードが取得できなかった場合も含む。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
