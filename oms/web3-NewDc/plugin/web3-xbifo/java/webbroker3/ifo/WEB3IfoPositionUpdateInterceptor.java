head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoPositionUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OPポジションマネージャー更新インタセプタ(WEB3IfoPositionUpdateInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/6/3 稲葉大雄(SRA) 新規作成
 */
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoPositionManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OPポジションマネージャ更新インタセプタ)<BR>
 * 先物OPポジションマネージャ更新インタセプタクラス<BR>
 * @@author 稲葉大雄(SRA)
 * @@version 1.0 
 */
public class WEB3IfoPositionUpdateInterceptor implements IfoPositionManagerPersistenceEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoPositionUpdateInterceptor.class);

    /**
     * (更新値設定)<BR>
     * (mutateメソッドの実装)<BR>
     *<BR>
     * 建玉Paramsの以下の項目に実時間を設定し返却する。<BR> 
     * -更新日付<BR>
     * -作成日付<BR>
     * @@param ifocontractparams - 建玉Params
     * @@return IfoContractParams
     */
    public IfoContractParams mutateBeforeInsert(IfoContractParams ifocontractparams)
    {
        Timestamp l_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3IfoAttributeNameDef.REAL_TIMESTAMP
            );

        ifocontractparams.setLastUpdatedTimestamp(l_timestamp);
        ifocontractparams.setCreatedTimestamp(l_timestamp);
        return ifocontractparams;
    }

    /**
     * (更新値設定) <BR>
     * (mutateBeforeInsertメソッドの実装）<BR> 
     * <BR>
     * １）　@建玉ＩＤ、注文単位ＩＤをキーに建玉返済指定情報テーブルを検索する。<BR> 
     * <BR>
     * ２）　@取得した建玉返済指定情報が以下の条件の場合、更新日付を実時間で更新する。<BR> 
     * 　@　@２-１)更新日付 != 作成日付　@かつ <BR>
     * 　@　@　@　@　@更新日付 == ThreadLocalSystemAttributesRegistryより取得した実時間。<BR> 
     * <BR>
     * ３）　@トランザクション(取引勘定明細)Paramsの以下の項目に実時間を設定し返却する。<BR> 
     * 　@-更新日付 <BR>
     * 　@-作成日付 <BR>
     * 　@-トランザクション発生日時<BR>
     * @@param ifofintransactionparams - トランザクション(取引勘定明細)Params
     * @@return IfoFinTransactionParams
     */
    public IfoFinTransactionParams mutateBeforeInsert(IfoFinTransactionParams ifofintransactionparams)
    {
        final String STR_METHOD_NAME = "mutateBeforeInsert(IfoFinTransactionParams)";
        Timestamp l_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3IfoAttributeNameDef.REAL_TIMESTAMP
                    );

        //建玉返済指定情報テーブルを検索する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" contract_id = ? ");
        l_sbWhere.append(" and order_unit_id = ? ");
        
        ArrayList l_lstObjClosingContractSpec = new ArrayList();
        //建玉ID
        String l_strContractId = ifofintransactionparams.contract_id.toString();
        l_lstObjClosingContractSpec.add(l_strContractId);
        //注文単位
        String l_strOrderUnitId = ifofintransactionparams.order_unit_id.toString();
        l_lstObjClosingContractSpec.add(l_strOrderUnitId);
        
        int l_intSize = l_lstObjClosingContractSpec.size();
        Object[] l_objClosingContractSpecWhere = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objClosingContractSpecWhere[i] = l_lstObjClosingContractSpec.get(i);
        }

        try
        {
            List l_lisRecords = null;
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoClosingContractSpecRow.TYPE,
                l_sbWhere.toString(),
                l_objClosingContractSpecWhere);
            int l_intRecSize = l_lisRecords.size();

            Date l_bizDate = new Date(
                        ((Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG)).getTime()
                        );
            for (int i = 0; i < l_intRecSize; i++)
            {
                IfoClosingContractSpecParams l_closContSpecParams = (IfoClosingContractSpecParams) l_lisRecords.get(i);
                Date l_datCreatedTimeStamp = new Date(l_closContSpecParams.getCreatedTimestamp().getTime());
                Date l_datUpdatedTimeStamp = new Date(l_closContSpecParams.getLastUpdatedTimestamp().getTime());
                if (WEB3DateUtility.compareToSecond(l_datCreatedTimeStamp, l_datUpdatedTimeStamp) != 0 && 
                    WEB3DateUtility.compare(l_datUpdatedTimeStamp, l_bizDate) == 0)
                {
                    HashMap l_map = new HashMap();
                    l_map.put("last_updated_timestamp", l_timestamp);
                    l_queryProcessor.doUpdateQuery(
                        new IfoClosingContractSpecPK(l_closContSpecParams.getClosingContractSpecId()),
                        l_map
                        );
                }
            }
        }
        catch (DataFindException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }
        catch (DataNetworkException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }
        catch (DataQueryException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        ifofintransactionparams.setLastUpdatedTimestamp(l_timestamp);
        ifofintransactionparams.setCreatedTimestamp(l_timestamp);
        ifofintransactionparams.setFinTransactionTimestamp(l_timestamp);
        return ifofintransactionparams;

    }
    
    /**
     * (更新値設定) <BR>
     * (mutateBeforeUpdateメソッドの実装）<BR> 
     * <BR>
     * 建玉テーブル.更新日付を実時間で更新する。<BR> 
     * <BR>
     * １）　@Mapに以下のようにセットし、返却する。<BR> 
     * 　@　@　@キー：更新日付 <BR>
     * 　@　@　@値　@：ThreadLocalSystemAttributesRegistryより取得した実時間。<BR>
     * @@param ifocontractparams - 建玉Params
     * @@param map - Map
     * @@return Map
     */

    public Map mutateBeforeUpdate(IfoContractParams ifocontractparams, Map map)
    {
        Timestamp l_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3IfoAttributeNameDef.REAL_TIMESTAMP
                    );

        ifocontractparams.setLastUpdatedTimestamp(l_timestamp);
        
        map.put("last_updated_timestamp", l_timestamp);
        return map; 
    }
    
    /**
     * (更新値設定)<BR>
     * (mutateBeforeUpdateメソッドの実装)<BR> 
     * <BR>
     * １）　@建玉ＩＤ、注文単位ＩＤをキーに建玉返済指定情報テーブルを検索する。<BR> 
     * <BR>
     * ２）　@取得した建玉返済指定情報が以下の条件の場合、更新日付を実時間で更新する。<BR> 
     * 　@　@２-１)更新日付 != 作成日付　@かつ <BR>
     * 　@　@　@　@　@更新日付 == ThreadLocalSystemAttributesRegistryより取得した実時間。<BR> 
     * <BR>
     * ３）　@トランザクション(取引勘定明細).更新日付を実時間で更新する。<BR> 
     * 　@　@　@３?１）　@Mapに以下のようにセットし、返却する。<BR> 
     * 　@　@　@　@　@　@　@　@キー：更新日付 <BR>
     * 　@　@　@　@　@　@　@　@値　@：ThreadLocalSystemAttributesRegistryより取得した実時間。<BR>
     * @@param ifofintransactionparams - トランザクション(取引勘定明細)Params
     * @@param map - Map
     * @@return Map
     */
    public Map mutateBeforeUpdate(
        IfoFinTransactionParams ifofintransactionparams, Map map
        )
    {
        final String STR_METHOD_NAME = "mutateBeforeUpdate(IfoFinTransactionParams, Map)";
        Timestamp l_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3IfoAttributeNameDef.REAL_TIMESTAMP
                    );

        //建玉返済指定情報テーブルを検索する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" contract_id = ? ");
        l_sbWhere.append(" and order_unit_id = ? ");
        
        ArrayList l_lstObjClosingContractSpec = new ArrayList();
        //建玉ID
        String l_strContractId = ifofintransactionparams.contract_id.toString();
        l_lstObjClosingContractSpec.add(l_strContractId);
        //注文単位
        String l_strOrderUnitId = ifofintransactionparams.order_unit_id.toString();
        l_lstObjClosingContractSpec.add(l_strOrderUnitId);
        
        int l_intSize = l_lstObjClosingContractSpec.size();
        Object[] l_objClosingContractSpecWhere = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objClosingContractSpecWhere[i] = l_lstObjClosingContractSpec.get(i);
        }

        try
        {
            List l_lisRecords = null;
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoClosingContractSpecRow.TYPE,
                l_sbWhere.toString(),
                l_objClosingContractSpecWhere);
            int l_intRecSize = l_lisRecords.size();

			//注文単位から発注日を取得する。
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			IfoOrderManager l_oManager = 
				(IfoOrderManager)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
			OrderUnit l_oUnit = (OrderUnit)l_oManager.getOrderUnit(
				ifofintransactionparams.getOrderUnitId()
				);
			IfoOrderUnitRow l_ifoOrderUnitRow = 
					(IfoOrderUnitRow) l_oUnit.getDataSourceObject();
			String l_bizDate = l_ifoOrderUnitRow.getBizDate();

			Timestamp l_BizDateTime = new Timestamp(
				(WEB3DateUtility.getDate(l_bizDate, "yyyyMMdd")).getTime()
				);

            for (int i = 0; i < l_intRecSize; i++)
            {
                IfoClosingContractSpecParams l_closContSpecParams = (IfoClosingContractSpecParams) l_lisRecords.get(i);
                Date l_datCreatedTimeStamp = new Date(l_closContSpecParams.getCreatedTimestamp().getTime());
                Date l_datUpdatedTimeStamp = new Date(l_closContSpecParams.getLastUpdatedTimestamp().getTime());
                if ((WEB3DateUtility.compareToSecond(l_datCreatedTimeStamp, l_datUpdatedTimeStamp) != 0 && 
                     WEB3DateUtility.compare(l_datUpdatedTimeStamp, l_BizDateTime) == 0) ||
                    (WEB3DateUtility.compare(l_timestamp, l_BizDateTime) == 0))
                    
                {
                    HashMap l_map = new HashMap();
                    l_map.put("last_updated_timestamp", l_timestamp);
                    l_queryProcessor.doUpdateQuery(
                        new IfoClosingContractSpecPK(l_closContSpecParams.getClosingContractSpecId()),
                        l_map
                        );
                }
            }
        }
        catch (DataFindException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }
        catch (DataNetworkException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }
		catch (NotFoundException e)
		{
			log.error("DBへのアクセスに失敗しました。", e);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME
				);
		}
        catch (DataQueryException e)
        {
            log.error("DBへのアクセスに失敗しました。", e);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }
        map.put("last_updated_timestamp", l_timestamp);
        return map; 
    }

}
@
