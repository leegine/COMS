head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®gUNV}l[W(WEB3FeqFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 àò(u) VKì¬
                 : 2005/07/26 ¤ûU(u) r[
Revesion History : 2007/11/20 ½¶q(u) dlÏX fNo.365
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (O®gUNV}l[W) <BR>
 * O®gUNV}l[W<BR>
 * <BR>
 * @@ author àò <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqFinTransactionManager extends FeqFinTransactionManagerImpl 
{
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqFinTransactionManager.class);
    
    /**
     * @@roseuid 42CE39E703B9
     */
    public WEB3FeqFinTransactionManager() 
    {
     
    }
    
    /**
     * (getónãàv) <BR>
     * igetNetAmountj  <BR>
     * wè¶ÉÖA·égUNVf[^æèA <BR>
     * ¶ÉÎ·évónàzðZo·éB  <BR>
     *  <BR>
     * Pj@@gUNViæø¨è¾×je[uõ  <BR>
     * @@øÅwèµ½¶PÊÉY·éOgUNVð <BR>
     *   getgUNV()ÉÄæ¾·éB  <BR>
     *  <BR>
     * [ø]  <BR>
     * ¶PÊF@@øÌ¶PÊIuWFNg  <BR>
     *  <BR>
     * Qj@@vàzvZ  <BR>
     * @@æ¾µ½·×ÄÌ®gUNVÌónãàðWvµA <BR>
     * vZÊðÔp·éB  <BR>
     * @@param l_feqOrderUnit - (¶PÊ)<BR>
     * ¶PÊ
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292AFE90302
     */
    public double getNetAmount(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmount(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@gUNViæø¨è¾×je[uõ
        //øÅwèµ½¶PÊÉY·éOgUNVð 
        //getgUNV()ÉÄæ¾·éB
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        double  l_dblNetAmount = 0.0D;
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //Qj@@vàzvZ 
                l_dblNetAmount += l_transactionRow.getNetAmount();
            }
            
        }
        log.exiting(STR_METHOD_NAME);
        //vZÊðÔp·éB
        return l_dblNetAmount;
    }
    
    /**
     * (getónãàviOÝj) <BR>
     * igetNetAmountFcj  <BR>
     * wè¶ÉÖA·égUNVf[^æèA <BR>
     * ¶ÉÎ·évónàziOÝjðZo·éB  <BR>
     *  <BR>
     * Pj@@gUNViæø¨è¾×je[uõ  <BR>
     * @@øÅwèµ½¶PÊÉY·éOgUNVð <BR>
     * @@getgUNV()ÉÄæ¾·éB  <BR>
     *  <BR>
     * [ø]  <BR>
     * ¶PÊF@@øÌ¶PÊIuWFNg  <BR>
     *  <BR>
     * Qj@@vàzvZ  <BR>
     * @@æ¾µ½·×ÄÌ®gUNVÌónãàiOÝjðWvµA <BR>
     * @@vZÊðÔp·éB  <BR>
     * @@param l_feqOrderUnit - (¶PÊ)<BR>
     * ¶PÊ
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292BA3702A4
     */
    public double getNetAmountFc(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmountFc(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        //PjgUNViæø¨è¾×je[uõ  
        //øÅwèµ½¶PÊÉY·éOgUNVð 
        //getgUNV()ÉÄæ¾·éB
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        BigDecimal l_bdNetAmountFc = new BigDecimal("0");
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //Qj@@vàzvZ 
                l_bdNetAmountFc =
                    l_bdNetAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getNetAmountFc())));
            }
        }
        log.exiting(STR_METHOD_NAME);
        //vZÊðÔp·éB
        return l_bdNetAmountFc.doubleValue();
    }
    
    /**
     * (getgUNV) <BR>
     * igetTransactionsÌI[o[[hj  <BR>
     *  <BR>
     * øÅwèµ½¶PÊÉY·égUNVðæ¾·éB  <BR>
     *  <BR>
     * Pj@@gUNVe[uõ  <BR>
     * @@ÈºÌðÅO®gUNViæø¨è¾×jðõµA <BR>
     * @@æ¾ÊðWÅ·éB<BR>
     *  <BR>
     * @@[õð]  <BR>
     * @@¶ID = ¶PÊ.¶ID  <BR>
     * @@¶PÊID = ¶PÊ.¶PÊID  <BR>
     * @@ítO = FALSE  <BR>
     * @@param l_feqOrderUnit - (¶PÊ) <BR>
     * ¶PÊ
     * @@return List
     * @@throws WEB3BaseException 
     * @@roseuid 4292B03A01C9
     */
    public List getTransactions(FeqOrderUnit l_feqOrderUnit)throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderUnit)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lstreturnTransaction = null;
        
        //f[^¸æm
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //Pj@@gUNVe[uõ
            String l_strWhere = " order_id = ? and order_unit_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_feqOrderUnit.getOrderId());
            l_strBindValue[1] = new Long(l_feqOrderUnit.getOrderUnitId());
            l_strBindValue[2] = BooleanEnum.FALSE;
                        
            l_lstreturnTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        log.exiting(STR_METHOD_NAME);        
        return l_lstreturnTransaction;
    }
    
    /**
     * (getgUNV) <BR>
     * igetTransactionÌI[o[[hj  <BR>
     *  <BR>
     * øÅwèµ½ñèÉY·égUNVðæ¾·éB  <BR>
     *  <BR>
     * Pj@@gUNVe[uõ  <BR>
     * @@ÈºÌðÅO®gUNViæø¨è¾×jðõµA <BR>
     * @@æ¾sðÔp·éB  <BR>
     *  <BR>
     * @@[õð]  <BR>
     * @@¶ID = ñè.¶ID  <BR>
     * @@¶PÊID = ñè.¶PÊID  <BR>
     * @@ñèID = ñè.ñèID  <BR>
     *  <BR>
     * @@YsªÈ¢êA<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01037<BR>
     * @@YsªQÈã éêáOðX[·éB <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01461<BR>
     * @@param l_feqOrderExecution - (ñè)<BR>
     * ñèIuWFNg
     * @@return FeqFinTransactionParams
     * @@throws WEB3BaseException 
     * @@roseuid 4294367703A2
     */
    public FeqFinTransactionParams getTransaction(FeqOrderExecution l_feqOrderExecution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderExecution)";    
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderExecution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lstTransaction = null;
        
        //f[^¸æm
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //Pj@@gUNVe[uõ
            String l_strWhere = " order_id = ? and order_unit_id = ? and order_execution_id = ? ";
            FeqOrderExecutionRow l_executionRow = 
                (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_executionRow.getOrderId());
            l_strBindValue[1] = new Long(l_executionRow.getOrderUnitId());
            l_strBindValue[2] = new Long(l_executionRow.getOrderExecutionId());

                        
            l_lstTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        
        if (l_lstTransaction == null || l_lstTransaction.isEmpty())
        {
            String l_strMessage = "ðÉY·éf[^ª¶ÝµÈ¢B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_lstTransaction.size() > 1)
        {
            String l_strMessage = "e[uÉd¡·éYf[^ª¶ÝµÜ·B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01461,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        log.exiting(STR_METHOD_NAME);        
        return new FeqFinTransactionParams((FeqFinTransactionRow)l_lstTransaction.get(0));
    }

    /**
     * (getKp×Ö[g)<BR>
     * øÅwèµ½¶PÊÉY·égUNV.Kp×Ö[gðæ¾·éB<BR>
     * @@¦¡æ¾Å«éêÍAgUNVList(0)Ì×Ö[gðæ¾·éB<BR>
     * <BR>
     * Pj@@gUNVe[uõ<BR>
     * @@ÈºÌðÅO®gUNViæø¨è¾×jðõµAæ¾Êð<BR>
     * @@WÅæ¾·éB<BR>
     * <BR>
     * @@[õð]<BR>
     * @@¶ID = ¶PÊ.¶ID<BR>
     * @@¶PÊID = ¶PÊ.¶PÊID<BR>
     * <BR>
     * @@¦@@gUNVªæ¾Å«È¢êÍAVXeG[<BR>
     * @@hYf[^ÈµhðÔp·éB<BR>
     * <BR>
     * Qj@@Kp×Ö[gæ¾<BR>
     * @@gUNVList(0)DKp×Ö[gðÔp·éB<BR>
     * <BR>
     * @@param l_feqOrderUnit - (¶PÊ)<BR>
     * ¶PÊ<BR>
     * @@return Double
     * @@throws WEB3BaseException
     */
    public Double getFxRate(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getFxRate(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnit == null)
        {
            log.debug("p[^ls³B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "p[^ls³B");
        }

        // Pj@@gUNVe[uõ
        List l_lisTransactions = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            // [õð]
            // ¶ID = ¶PÊ.¶ID
            // ¶PÊID = ¶PÊ.¶PÊID
            String l_strWhere = " order_id = ? and order_unit_id = ? ";
            Object[] l_bindValues = new Object[2];
            l_bindValues[0] = new Long(l_feqOrderUnit.getOrderId());
            l_bindValues[1] = new Long(l_feqOrderUnit.getOrderUnitId());

            l_lisTransactions = processor.doFindAllQuery(
                FeqFinTransactionRow.TYPE,
                l_strWhere,
                l_bindValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // gUNVªæ¾Å«È¢êÍAVXeG[hYf[^ÈµhðÔp·éB
        if (l_lisTransactions.isEmpty())
        {
            log.debug("e[uÉY·éf[^ª èÜ¹ñB");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "e[uÉY·éf[^ª èÜ¹ñB");
        }

        // Qj@@Kp×Ö[gæ¾
        // @@gUNVList(0)DKp×Ö[gðÔp·éB
        FeqFinTransactionRow l_feqFinTransactionRow =
            (FeqFinTransactionRow)l_lisTransactions.get(0);
        double l_dblFxRate = l_feqFinTransactionRow.getFxRate();

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblFxRate);
    }
}
@
