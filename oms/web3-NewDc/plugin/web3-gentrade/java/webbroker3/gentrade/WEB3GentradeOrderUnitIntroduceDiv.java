head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOrderUnitIntroduceDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文単位紹介区分(WEB3GentradeOrderUnitIntroduceDiv)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 孟東 (中訊) 新規作成
Revesion History : 2006/11/21 松本 (SRA) 仕様変更（モデル）：218
**/
package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.OrderUnitIntroduceDivDao;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 注文単位紹介区分<BR>
 * <BR>
 */
public class WEB3GentradeOrderUnitIntroduceDiv implements BusinessObject
{

    /**
     * 注文単位紹介区分Row <BR>
     */
    private OrderUnitIntroduceDivParams orderUnitIntroduceDivParams;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderUnitIntroduceDiv.class);

    /**
	 * (saveNew注文単位紹介区分)<BR>
	 * this.注文単位紹介区分行の内容をデータベースに反映させる。(Insert)<BR>
	 * <BR>
	 * 1) this.注文単位紹介区分行オブジェクトに以下の値をセットする。<BR>
	 * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
	 * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
	 * <BR>
	 * 2) this.注文単位紹介区分行オブジェクトの内容で、<BR>
	 * 　@注文単位紹介区分テーブルを更新（Insert）する。<BR>
	 * <BR>
	 * @@roseuid 40640F0102AE
     * @@throws WEB3SystemLayerException
	 */
    public void saveNewOrderUnitIntroduceDivRow()
        throws WEB3SystemLayerException
	{
        final String STR_METHOD_NAME = 
            "saveNewOrderUnitIntroduceDivRow()";
        log.entering(STR_METHOD_NAME);
        
        Timestamp l_tsSystemTime = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
        //作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.orderUnitIntroduceDivParams.setCreatedTimestamp(l_tsSystemTime);
        //更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.orderUnitIntroduceDivParams.setLastUpdatedTimestamp(l_tsSystemTime);

        // 注文単位紹介区分テーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_processer = Processors.getDefaultProcessor();
            l_processer.doInsertQuery(this.orderUnitIntroduceDivParams); 
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);        
	}

    /**
     * (注文単位紹介区分) <BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 引数の条件に一致する注文単位紹介区分オブジェクトを返却する。 <BR>
     * <BR>
     * １）　@DB検索 <BR>
     *　@引数の値をキーとして注文単位紹介区分テーブルを検索する。 <BR>
     * 　@[条件]<BR>
     * 　@　@　@　@注文単位ID = 引数.注文単位ID<BR>
     * 　@かつ　@銘柄タイプ = 引数.銘柄タイプ<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（注文単位紹介区分Row）を<BR>
     * this.注文単位紹介区分行にセットする。<BR>
     * <BR>
     * @@param l_lngOrderUnitId 注文単位ID
     * @@param l_productType 銘柄タイプ
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeOrderUnitIntroduceDiv(
        long l_lngOrderUnitId,
        ProductTypeEnum l_productType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeOrderUnitIntroduceDiv(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@DB検索
            OrderUnitIntroduceDivRow l_row = 
                OrderUnitIntroduceDivDao.findRowByPk(
                    l_lngOrderUnitId, l_productType);
            //２）　@行オブジェクトセット 
            this.orderUnitIntroduceDivParams = 
                new OrderUnitIntroduceDivParams(l_row);
        }
        catch (DataFindException l_de)
        {
            log.debug(STR_METHOD_NAME+" not found: " + l_lngOrderUnitId + "," + l_productType);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
	 * (set注文単位ID)<BR>
	 * 注文単位IDの設定を行う。<BR>
	 * <BR>
	 * 1) this.注文単位紹介区分行.set注文単位ID( )をコールする。<BR>
	 * [引数]<BR>
	 * 　@注文単位ID=引数.注文単位ID<BR>
     * @@param l_lngOrderUnitId 注文単位ID
     * @@roseuid 40640F0102AE
	 */
    public void setOrderUnitId(long l_lngOrderUnitId)
	{
        final String STR_METHOD_NAME = "setOrderUnitId(long)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setOrderUnitId(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get注文単位ID)<BR>
     * 注文単位IDを返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get注文単位ID()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40640F0102AE
     */
    public long getOrderUnitId()
    {
        return this.orderUnitIntroduceDivParams.getOrderUnitId();
    }

    /**
     * (set口座ID)<BR>
     * 口座IDの設定を行う。<BR>
     * <BR>
     * 1) this.注文単位紹介区分行.set口座ID( )をコールする。<BR>
     * [引数]<BR>
     * 　@口座ID=引数.口座ID<BR>
     * @@param l_lngAccountId 口座ID
     * @@roseuid 40640F0102AE
     */
    public void setAccountId(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = 
            "setAccountId(long)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setAccountId(l_lngAccountId);
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get口座ID)<BR>
     * 注文単位IDを返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get口座ID()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40640F0102AE
     */
    public long getAccountId()
    {
        return this.orderUnitIntroduceDivParams.getAccountId();
    }

    /**
     * (set銘柄タイプ)<BR>
     * 銘柄タイプの設定を行う。<BR>
     * <BR>
     * 1) this.注文単位紹介区分行.set銘柄タイプ( )をコールする。<BR>
     * [引数]<BR>
     * 　@銘柄タイプ=引数.銘柄タイプ<BR>
     * @@param l_productType 銘柄タイプ
     * @@roseuid 40640F0102AE
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = 
            "setProductType(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setProductType(l_productType);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get銘柄タイプ()の戻り値を返す。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 40640F0102AE
     */
    public ProductTypeEnum getProductType()
    {
        return this.orderUnitIntroduceDivParams.getProductType();
    }

    /**
     * (set紹介区分)<BR>
     * 紹介区分の設定を行う。<BR>
     * <BR>
     * 1) this.注文単位紹介区分行.set紹介区分( )をコールする。<BR>
     * [引数]<BR>
     * 　@紹介区分=引数.紹介区分<BR>
     * @@param l_strProductType 紹介区分
     * @@roseuid 40640F0102AE
     */
    public void setIntroduceBranchDiv(String l_strProductType)
    {
        final String STR_METHOD_NAME = 
            "setIntroduceBranchDiv(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setIntroduceBranchDiv(l_strProductType);
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get紹介区分)<BR>
     * 紹介区分を返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get紹介区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getIntroduceBranchDiv()
    {
        return this.orderUnitIntroduceDivParams.getIntroduceBranchDiv();
    }

    /**
     * (set紹介店コード)<BR>
     * 紹介店コードの設定を行う。<BR>
     * <BR>
     * 1) this.注文単位紹介区分行.set紹介店コード( )をコールする。<BR>
     * [引数]<BR>
     * 　@紹介店コード=引数.紹介店コード<BR>
     * @@param l_strIntroduceBranchCode 紹介店コード
     * @@roseuid 40640F0102AE
     */
    public void setIntroduceBranchCode(String l_strIntroduceBranchCode)
    {
        final String STR_METHOD_NAME = 
            "setIntroduceBranchCode(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setIntroduceBranchCode(l_strIntroduceBranchCode);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get紹介店コード)<BR>
     * 紹介店コードを返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get紹介店コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getIntroduceBranchCode()
    {
        return this.orderUnitIntroduceDivParams.getIntroduceBranchCode();
    }

    /**
     * (set更新者コード<BR>
     * 更新者コードの設定を行う。<BR>
     * <BR>
     * 1) this.注文単位紹介区分行.set更新者コード( )をコールする。<BR>
     * [引数]<BR>
     * 　@更新者コード=引数.更新者コード<BR>
     * @@param l_strLastUpdater 更新者コード
     * @@roseuid 40640F0102AE
     */
    public void setLastUpdater(String l_strLastUpdater)
    {
        final String STR_METHOD_NAME = 
            "setLastUpdater(String)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitIntroduceDivParams.setLastUpdater(l_strLastUpdater);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get更新者コード)<BR>
     * 更新者コードを返す。<BR>
     * <BR>
     * this.注文単位紹介区分行.get更新者コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40640F0102AE
     */
    public String getLastUpdater()
    {
        return this.orderUnitIntroduceDivParams.getLastUpdater();
    }

    /**
     * (注文単位紹介区分)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeOrderUnitIntroduceDiv()
    {
        this.orderUnitIntroduceDivParams = new OrderUnitIntroduceDivParams();
    }

	/* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject#getDataSourceObject()
	 */
	public Object getDataSourceObject() 
    {
		return this.orderUnitIntroduceDivParams;
	}
}
@
