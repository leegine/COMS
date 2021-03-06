head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®¶PÊ(WEB3FeqOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ¤ûU (u) VKì¬
                   2005/07/27 ä»@@(u) r[
Revesion History : 2007/08/13 Øk (u) f@@No.355Î
Revesion History : 2008/01/16 Äog(u) fNo.372
Revesion History : 2008/10/02 (SRA) yO®zdlÏXÇä ifjNo.466
*/

package webbroker3.feq;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.feq.data.FeqOrderChangeStatusDao;
import webbroker3.feq.data.FeqOrderChangeStatusRow;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqExpirationStatusDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (O®¶PÊ)<BR>
 * O®¶PÊ<BR>
 *
 * @@author ¤ûU(u)
 * @@version 1.0
 */
public class WEB3FeqOrderUnit extends FeqOrderUnitImpl
{
    /**
     * O[eBeBB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUnit.class);

    /**
     * RXgN^<BR>
     */
    protected WEB3FeqOrderUnit(long l_orderUnitId)
        throws DataQueryException, DataNetworkException
    {
        super(l_orderUnitId);
    }

    /**
     * RXgN^<BR>
     */
    protected WEB3FeqOrderUnit(FeqOrderUnitRow row)
    {
        super(row);
    }

    /**
     * (isHOST­)<BR>
     * HOST©çüÍ³ê½¶©ð»è·éB<BR>
     * <BR>
     * ithis.¶PÊs.¶oHæª == 9FHOSTj<BR>
     * ÌêtrueAÈOÍfalseðÔp·éB<BR>
     * @@return boolean
     * @@roseuid 428D6DE9006D
     */
    public boolean isHOSTOrder()
    {
        return (WEB3OrderRootDivDef.HOST.equals(
            this.m_row.getOrderRootDiv())) ? true : false;
    }

    /**
     * (getñèÅIÊÔ)<BR>
     * ñèÅIÊÔðæ¾·éB<BR>
     * <BR>
     * this.¶PÊs.ñèÅIÊÔ ðÔp·éB<BR>
     * @@return int
     * @@roseuid 429291AD004B
     */
    public int getLastExecutionSerialNo()
    {
        return this.m_row.getLastExecutionSerialNo();
    }

    /**
     * (getâûÀ)<BR>
     * âûÀðæ¾·éB<BR>
     * <BR>
     * this.getSubAccountId()ÉY·éâûÀðÔp·éB<BR>
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException
     * @@roseuid 4292A9190321
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                this.getAccountId(),
                this.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }

    /**
     * (getsê)<BR>
     * sêðæ¾·éB<BR>
     * <BR>
     * this.¶PÊs.sêIDÉY·ésêIuWFNgðÔp·éB<BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 4292A9650275
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(this.m_row.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (getÊÝ)<BR>
     * ÊÝðæ¾·éB<BR>
     * <BR>
     * this.getProduct().getÊÝ()ðÔp·éB<BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 4292AB7A0014
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        return ((WEB3FeqProduct)this.getProduct()).getCurrency();
    }

    /**
     * (getØïÐR[h)<BR>
     * ØïÐR[hðæ¾·éB<BR>
     * <BR>
     * this.¶PÊs.ØïÐR[hðÔp·éB<BR>
     * @@return String
     * @@roseuid 4292C54F015C
     */
    public String getInstitutionCode()
    {
        return this.m_row.getInstitutionCode();
    }

    /**
     * (getXR[h)<BR>
     * XR[hðæ¾·éB<BR>
     * <BR>
     * this.getBranchId()ÉY·éX.getBranchCode()ðÔp·éB<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4292C5F20091
     */
    public String getBranchCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(this.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_branch.getBranchCode();
    }

    /**
     * (getûÀR[h)<BR>
     * ûÀR[hðæ¾·éB<BR>
     * <BR>
     * this.getMainAccount().getAccountCode()ðÔp·éB<BR>
     * @@return String
     * @@roseuid 4292C64302B4
     */
    public String getAccountCode()
    {
        return this.getMainAccount().getAccountCode();
    }

    /**
     * (getÚq)<BR>
     * ÚqIuWFNgðæ¾·éB<BR>
     * <BR>
     * this.getMainAccount()ðÔp·éB<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 42943CF902D7
     */
    public MainAccount getMainAccount()
    {
        return (WEB3GentradeMainAccount)super.getMainAccount();
    }

    /**
     * (get^pR[h)<BR>
     * ^pR[hðæ¾·éB<BR>
     * <BR>
     * this.¶PÊs.^pR[hðÔp·éB<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getOrderEmpCode()
    {
        return this.m_row.getOrderEmpCode();
    }

    /**
     * (get­ú)<BR>
     * ­úðæ¾·éB<BR>
     * <BR>
     * this.¶PÊs.­úðÔp·éB<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getBizDate()
    {
        return this.m_row.getBizDate();
    }

    /**
     * (iswl)<BR>
     * iisLimitOrderj<BR>
     * <BR>
     * wl©ðæ¾·éB<BR>
     * <BR>
     * ithis.getLimitPrice() == 0jÌêfalseAÈOtrueðÔp·éB<BR>
     * @@return boolean
     * @@roseuid 4292AC400014
     */
    public boolean isLimitOrder()
    {
        return (this.getLimitPrice() == 0) ? false : true;
    }

    /**
     * (ist)<BR>
     * t©ð»è·éB<BR>
     * <BR>
     * this.getSide() == h¢hÌêtrueAÈOfalseðÔp·éB<BR>
     * @@return boolean
     * @@roseuid 4292BFB602C3
     */
    public boolean isBuy()
    {
        return (SideEnum.BUY.equals(this.getSide())) ? true : false;
    }

    /**
     * (is~ÝÏ)<BR>
     * ~ÝÏ©ð»è·éB<BR>
     * <BR>
     * this.¶PÊs.Ïæª == h0F~ÝÏhÌêAtrueðÔp·éB<BR>
     * ÈOAfalseðÔp·éB<BR>
     * @@return boolean
     * @@roseuid 42A57036014E
     */
    public boolean isJpySettle()
    {
        return (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
            this.m_row.getSettleDiv())) ? true : false;
    }

    /**
     * (getótæª)<BR>
     * øÌ¶PÊÌótæªðÔp·éB<BR>
     * <BR>
     * Ôpl<BR>
     * 0Fót¢Ï<BR>
     * 1FótÏ<BR>
     * 2FótG[<BR>
     * <BR>
     * Pj@@ótæª»Ê<BR>
     * @@[@@¶PÊ.sê©çmFÏÌÊ==NaNÌê]<BR>
     * @@@@[Ap[^.¶PÊ.¶óÔ=="­¸s(VK¶)"Ìê]<BR>
     * @@@@@@"ótG["ðÔp·éB<BR>
     * <BR>
     * @@@@[AÈOÌê]<BR>
     * @@@@i­É¸sµÄ¢È¢êj<BR>
     * @@@@@@"ót¢Ï"ðÔp·éB<BR>
     * <BR>
     * @@[@@ÈOÌê]<BR>
     * @@isê©çmFÏÝÌÊÉlªüÁÄ¢éêj<BR>
     * @@@@"ótÏ"ðÔp·éB<BR>
     * @@return String
     * @@roseuid 42A52F5B018B
     */
    private String getAcceptDiv()
    {
        final String STR_METHOD_NAME = "getAcceptDiv()";
        log.entering(STR_METHOD_NAME);

        // Pj@@ótæª»Ê
        String l_strReturn;
        if (this.m_row.getConfirmedQuantityIsNull())
        {
            if (OrderStatusEnum.NOT_ORDERED.equals(this.m_row.getOrderStatus()))
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR;
            }
            else
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN;
            }
        }
        else
        {
            l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN;
        }

        log.debug(STR_METHOD_NAME + ".getótæª : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (getñèóÔæª)<BR>
     * ñèóÔæªðÔ·B<BR>
     * <BR>
     * Ôpl<BR>
     * 0F¢ñè<BR>
     * 1Fê¬§<BR>
     * 2FS¬§<BR>
     * 3Fñèiê¬§j<BR>
     * 4FñèiS¬§j<BR>
     * <BR>
     * Pj@@¶PÊæè¼ñètOðæ¾·éB<BR>
     * <BR>
     * Qj@@¼ñètOªh0FDEFAULThÌê<BR>
     * <BR>
     * @@Q?Pj@@ê¬§ithis.isPartiallyExecuted( ) == truejÌêA<BR>
     * @@@@@@@@@@@@hê¬§hðÔp·éB<BR>
     * <BR>
     * @@Q?Qj@@S¬§ithis.isFullyExecuted( ) == truejÌêA<BR>
     * @@@@@@@@@@@@hS¬§hðÔp·éB<BR>
     * <BR>
     * Rj@@¼ñètOªh1F¼ñèhÌê<BR>
     * <BR>
     * @@R?Pj@@ê¬§ithis.isPartiallyExecuted( ) == truejÌêA<BR>
     * @@@@@@@@@@@@hñèiê¬§jhðÔp·éB<BR>
     * <BR>
     * @@R?Qj@@S¬§ithis.isFullyExecuted( ) == truejÌêA<BR>
     * @@@@@@@@@@@@hñèiS¬§jhðÔp·éB<BR>
     * <BR>
     * Sj@@ãLÈOÌêAh¢ñèhðÔp·éB<BR>
     * @@return String
     * @@roseuid 42A52F5B018D
     */
    public String getExecStatusDiv()
    {
        final String STR_METHOD_NAME = "getExecStatusDiv()";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = null;
        // ¼ñètOªh0FDEFAULThÌê
        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
            }            
        }
        // ¼ñètOªh1F¼ñèhÌê
        else if (WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE;
            }
        }
        
        // ãLÈOÌêAh¢ñèhðÔp·éB
        if (l_strReturn == null)
        {
            l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }
        
        log.debug(STR_METHOD_NAME + ".getñèóÔæª : " + l_strReturn); 
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;

    }

    /**
     * (get¶óÔæª)<BR>
     * ¶óÔæªðÔ·B<BR>
     * <BR>
     * ßèlÌ¶óÔæªF<BR>
     * 0F»Ì¼<BR>
     * 1FótÏiVK¶j 2F­iVK¶j <BR>
     * 3F­ÏiVK¶j 6F­¸siVK¶j<BR>
     * 7FótÏiÏX¶j 8F­iÏX¶j<BR>
     * 10F­ÏiÏX¶j 11F­¸siÏX¶j<BR>
     * 12FótÏiæÁ¶j 13F­iæÁ¶j<BR>
     * 14F­ÏiæÁ¶j 15F­¸siæÁ¶j<BR>
     * 20Fê¸ø 21FS¸ø 22F³ø<BR>
     * 50FJzÏ 51FJz¸s<BR>
     * <BR>
     * Pj@@O®¶}l[W.isJz¶PÊ(this)Ìßèl == trueA<BR>
     * @@@@@@©Â@@this.¶óÔ == ACCEPTED<BR>
     * @@@@@@iótÏiVK¶jjÌêA"JzÏ"ðÔ·B<BR>
     * <BR>
     * Qj@@O®¶}l[W.isoéÜÅ¶PÊ(this)<BR>
     * @@@@@@Ìßèl == trueA<BR>
     * @@@@@@©Â@@this.¶¸øútÆ±út<BR>
     *      iGtlUtils.getTradingSystem().getBizDate()jA<BR>
     * @@@@@@©Â@@this.¶LøóÔ ==CLOSEDiN[YjA<BR>
     * @@@@@@©Â@@this.¸øæª == EXPIREDi¸øÏjA<BR>
     * @@@@@@©Â@@this.¶G[RR[h != "0000F³í"<BR>
     * @@@@@@ÌêA"Jz¸s"ðÔ·B<BR>
     * <BR>
     * Rj@@this.isUnexecuted( ) == trueA<BR>
     * @@@@@@©Â@@this.¶LøóÔ == CLOSEDiN[YjA<BR>
     * @@@@@@©Â@@this.¸øæª == INVALIDATED_BY_MKT<BR>
     *      i}[PbgÛjÌêA<BR>
     * @@@@@@"S¸ø"ðÔ·B<BR>
     * <BR>
     * Sj@@this.isPartiallyExecuted( ) == trueA<BR>
     * @@@@@@©Â@@this.¶LøóÔ == CLOSEDiN[YjA<BR>
     * @@@@@@©Â@@this.¸øæª == INVALIDATED_BY_MKT<BR>
     *     i}[PbgÛjÌêA<BR>
     * @@@@@@"ê¸ø"ðÔ·B<BR>
     * <BR>
     * Tj@@this.¶LøóÔ == CLOSEDiN[YjA<BR>
     * @@@@@@©Â@@this.¸øæª == EXPIREDi¸øÏjÌêA"³ø"ðÔ·B<BR>
     * @@@@@@¦oI¹ÊmÅ¶ª¸øµ½ê<BR>
     * <BR>
     * Uj@@ãLÈOÌêÍAthis.¶óÔ.intValueð¶ñÅÔ·B<BR>
     * @@return String
     * @@roseuid 42A52F5B018F
     */
    public String getOrderStatusDiv()
    {
        final String STR_METHOD_NAME = "getOrderStatusDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strReturn;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        boolean l_blnIsCarryOverOrderUnit = l_orderManager.isCarryOverOrderUnit(this);
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(this);
        Timestamp l_datBizDate = new Timestamp (GtlUtils.getTradingSystem().getBizDate().getTime());
        OrderStatusEnum l_orderStsEnum = this.getOrderStatus();
        OrderOpenStatusEnum l_ordOpenStsEnum = this.getOrderOpenStatus();
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();
        
        // Pj@@O®¶}l[W.isJz¶PÊ(this)Ìßèl == trueA
        // @@@@@@©Â@@this.¶óÔ == ACCEPTEDiótÏiVK¶jjÌêA"JzÏ"ðÔ·B
        if (l_blnIsCarryOverOrderUnit &&
            OrderStatusEnum.ACCEPTED.equals(l_orderStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.TRANSFERED;
        }

        // Qj@@O®¶}l[W.isoéÜÅ¶PÊ(this)Ìßèl == trueA
        // @@@@@@©Â@@this.¶¸øútÆ±útiGtlUtils.getTradingSystem().getBizDate()jA
        // @@@@@@©Â@@this.¶LøóÔ ==CLOSEDiN[YjA
        // @@@@@@©Â@@this.¸øæª == EXPIREDi¸øÏjA
        // @@@@@@©Â@@this.¶G[RR[h != "0000F³í"ÌêA"Jz¸s"ðÔ·B
        else if (l_blnIsCarriedOrderUnit &&
            WEB3DateUtility.compareToDay(this.m_row.getExpirationDate(), l_datBizDate) >= 0 &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum) &&
            !WEB3ErrorReasonCodeDef.NORMAL.equals(this.m_row.getErrorReasonCode()))
        {
            l_strReturn = WEB3OrderStatusDef.NOT_TRANSFERED;
        }

        // Rj@@this.isUnexecuted( ) == trueA
        // @@@@@@©Â@@this.¶LøóÔ == CLOSEDiN[YjA
        // @@@@@@©Â@@this.¸øæª == INVALIDATED_BY_MKTi}[PbgÛjÌêA
        // @@@@@@"S¸ø"ðÔ·B
        else if (this.isUnexecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.FULL_INAFFECTED;
        }

        // Sj@@this.isPartiallyExecuted( ) == trueA
        // @@@@@@©Â@@this.¶LøóÔ == CLOSEDiN[YjA
        // @@@@@@©Â@@this.¸øæª == INVALIDATED_BY_MKTi}[PbgÛjÌêA
        // @@@@@@"ê¸ø"ðÔ·B
        else if (this.isPartiallyExecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.PART_INAFFECTED;
        }

        // Tj@@this.¶LøóÔ == CLOSEDiN[YjA
        // @@@@@@©Â@@this.¸øæª == EXPIREDi¸øÏjÌêA"³ø"ðÔ·B
        // @@@@@@¦oI¹ÊmÅ¶ª¸øµ½ê
        else if (OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.CLOSED;
        }

        // Uj@@ãLÈOÌêÍAthis.¶óÔ.intValueð¶ñÅÔ·B
        else
        {
            l_strReturn = new Integer(l_orderStsEnum.intValue()).toString();
        }

        log.debug(STR_METHOD_NAME + ".get¶óÔæª : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (getóµæª)<BR>
     * óµæªðÔp·éB<BR>
     * <BR>
     * ßèlÌóµæªF<BR>
     * ¦R[hlÍbZ[Wè`tH_ÈºÌ<BR>
     * uÒ¯¾°¼Þè`_O®(¤Ê).xlsvÌóµæªè`ðQÆB<BR>
     * <BR>
     * Pj@@this.getótæª()ðR[µA<BR>
     * @@ótæªðæ¾·éB<BR>
     * <BR>
     * Qj@@this.getñèóÔæª()ðR[µA<BR>
     * @@ñèæªðæ¾·éB<BR>
     * <BR>
     * Rj@@¸øæªð»è·éB<BR>
     * @@@@this.get¶óÔæª()ðR[·éB<BR>
     * @@@@@@Ethis.get¶óÔæª() == "ê¸ø"ÌêÍA"1Fê¸ø"B<BR>
     * @@@@@@Ethis.get¶óÔæª() == "S¸ø"ÌêÍA"2FS¸ø"B<BR>
     * @@@@@@EãLÈOÌêÍA"0F¸øÈµ"B<BR>
     * <BR>
     * Sj@@óµæªÔp<BR>
     * @@[PjÅæ¾µ½ótæª == "ótG["Ìê]<BR>
     * @@@@"ótG["ðæªÆµÄÔ·B<BR>
     * @@[ãLÈOÌê]<BR>
     * @@@@PjÌßèl<BR>
     * @@@@+ QjÌßèl<BR>
     * @@@@+ RjÌ»èÊ<BR>
     * @@@@+ this.¶ù³EæÁæªðÔp·éB<BR>
     * @@¦êÉ¶ÝµÈ¢gÝí¹Åà»ÌÜÜÔp·éB<BR>
     * @@return String
     * @@roseuid 42A52F5B01AA
     */
    public String getTransactionStateType()
    {
        final String STR_METHOD_NAME = "getTransactionStateType()";
        log.entering(STR_METHOD_NAME);

        // Pj@@this.getótæª()ðR[µAótæªðæ¾·éB
        String l_strAcceptDiv = this.getAcceptDiv();

        // Qj@@this.getñèóÔæª()ðR[µAñèæªðæ¾·éB
        String l_strExecStatusDiv = this.getExecStatusDiv();

        // Rj@@¸øæªð»è·éB
        // this.get¶óÔæª()ðR[·éB
        String l_strOrderStatusDiv = this.getOrderStatusDiv();
        String l_strExpStsDiv;

        //this.get¶óÔæª() == "ê¸ø"ÌêÍA"1Fê¸ø"B
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ONE_COMPLETE;
        }

        //this.get¶óÔæª() == "S¸ø"ÌêÍA"2FS¸ø"B
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ALL_COMPLETE;
        }

        //ãLÈOÌêÍA"0F¸øÈµ"B
        else
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE;
        }

        // Sj@@óµæªÔp
        // [PjÅæ¾µ½ótæª == "ótG["Ìê]
        String l_strReturn;
        if (WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptDiv))
        {
            l_strReturn =
                WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR +
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE +
                WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE +
                WEB3ModifyCancelTypeDef.INITIAL_VALUE;
        }
        
        // [ãLÈOÌê]
        else
        {
            l_strReturn =
                l_strAcceptDiv +
                l_strExecStatusDiv +
                l_strExpStsDiv +
                this.m_row.getModifyCancelType();
        }

        log.debug(STR_METHOD_NAME + ".getóµæª : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (is¶ótÂ\)<BR>
     * wèÌÏXãótæªðwèÂ\©ð»è·éB<BR>
     * <BR>
     * <BR>
     * Pj@@¸øæªæ¾<BR>
     * @@this.getExpirationStatus()ÉÄ¸øæªðæ¾·éB<BR>
     * <BR>
     * Qj@@¸øæªÌär<BR>
     * @@i¸øæª == 3F}[PbgÛj<BR>
     * ÌêA¸øÌæÁiÏXãótæª == h03F¶ótÏæÁhj<BR>
     * Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@¦i¸øæª != 3F}[PbgÛjÌêARjÈ~Ìðs¤B<BR>
     * <BR>
     * Rj@@¶óÔæ¾<BR>
     * @@this.getOrderStatus()ÉÄ¶óÔðæ¾·éB<BR>
     * <BR>
     * Sj@@óÔÌär<BR>
     * @@@@i¶óÔ == 2:­iVK¶j10:­ÏiÏX¶ ÂÇÔÑjjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@01F¶ótÏ<BR>
     * @@@@02F¶ótG[<BR>
     * @@@@31Fo¸<BR>
     * <BR>
     * @@@@i¶óÔ == 13:­iæÁ¶jjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@21FæÁÏ<BR>
     * @@@@22FæÁG[<BR>
     * @@@@31Fo¸<BR>
     * <BR>
     * @@@@i¶óÔ == 8:­iÏX¶jjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@11Fù³Ï<BR>
     * @@@@12Fù³G[<BR>
     * @@@@31Fo¸<BR>
     * <BR>
     * @@@@i¶óÔ == 3:­ÏiVK¶j Or 10:­ÏiÏX¶jjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@03F¶ótÏæÁ<BR>
     * @@@@31Fo¸<BR>
     * <BR>
     * @@@@i¶óÔ == 14:­ÏiæÁ¶jjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@03F¶ótÏæÁ<BR>
     * <BR>
     * @@@@i¶óÔ == 11:­¸siÏX¶j Or<BR>
     * @@@@ 15:­¸siæÁ¶jjÌê<BR>
     * @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueA<BR>
     * @@@@ÈOÍfalseðÔp·éB<BR>
     * <BR>
     * @@@@31Fo¸<BR>
     * <BR>
     * @@ ÈOÌêAfalseðÔp·éB<BR>
     * @@param l_strAfterChangeAcceptDiv - (ÏXãótæª)<BR>
     * ÏXãótæª<BR>
     * <BR>
     * 01F¶ótÏ<BR>
     * 02F¶ótG[<BR>
     * 03F¶ótÏæÁ<BR>
     * <BR>
     * 11Fù³Ï<BR>
     * 12Fù³G[<BR>
     * <BR>
     * 21FæÁÏ<BR>
     * 22FæÁG[<BR>
     * <BR>
     * 31Fo¸<BR>
     *
     * @@return boolean
     * @@roseuid 42A560A003B0
     */
    public boolean isOrderAcceptPoss(String l_strAfterChangeAcceptDiv)
    {
        final String STR_METHOD_NAME = "isOrderAcceptPoss(String)";
        log.entering(STR_METHOD_NAME);

        // Pj@@¸øæªæ¾
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();

        // Qj@@¸øæªÌär
        boolean l_blnReturn = false;
        // i¸øæª == 3F}[PbgÛj
        if (OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            // ¸øÌæÁiÏXãótæª == h03F¶ótÏæÁhjÅ êÎtrueAÈOÍfalseðÔp·éB
            l_blnReturn = WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);

            log.debug(STR_METHOD_NAME + ".is¶ótÂ\ : " + l_blnReturn);
            if (!l_blnReturn)
            {
                log.error(STR_METHOD_NAME + ".is¶ótÂ\ªfalseÌê");
                log.error(STR_METHOD_NAME + "¸øæª == 3F}[PbgÛÌê");
                log.error(STR_METHOD_NAME + "ótæª:" + l_strAfterChangeAcceptDiv);
            }
            log.exiting(STR_METHOD_NAME);
            return l_blnReturn;
        }

        // Rj@@¶óÔæ¾
        OrderStatusEnum l_ordStsEnum = this.getOrderStatus();
        boolean l_confirmedPrice = this.isConfirmedPriceMarketOrder();

        // Sj@@óÔÌär
        // @@i¶óÔ == 2:­iVK¶jÍ@@10:­ÏiÏX¶ ÂÇÔÑjjÌê
        if (OrderStatusEnum.ORDERING.equals(l_ordStsEnum) ||
        (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && l_confirmedPrice))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@01F¶ótÏ
            // @@@@02F¶ótG[
            // @@@@31Fo¸
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // @@i¶óÔ == 13:­iæÁ¶jjÌê
        else if (OrderStatusEnum.CANCELLING.equals(l_ordStsEnum))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@21FæÁÏ
            // @@@@22FæÁG[
            // @@@@31Fo¸
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // @@i¶óÔ == 8:­iÏX¶jjÌê
        else if (OrderStatusEnum.MODIFYING.equals(l_ordStsEnum))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@11Fù³Ï
            // @@@@12Fù³G[
            // @@@@31Fo¸
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CHANGED.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // @@@@i¶óÔ == 3:­ÏiVK¶j Or 10:­ÏiÏX¶jjÌê
        else if (OrderStatusEnum.ORDERED.equals(l_ordStsEnum) ||
            (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && !l_confirmedPrice))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@03F¶ótÏæÁ
            // @@@@31Fo¸
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // @@@@i¶óÔ == 14:­ÏiæÁ¶jjÌê
        else if (OrderStatusEnum.CANCELLED.equals(l_ordStsEnum))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@03F¶ótÏæÁ
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);
        }

        // @@i¶óÔ == 11:­¸siÏX¶j Or 15:­¸siæÁ¶jjÌê
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_ordStsEnum) ||
            OrderStatusEnum.NOT_CANCELLED.equals(l_ordStsEnum))
        {
            // @@@@ÏXãótæªªÈºÌ½ê©Å êÎtrueAÈOÍfalseðÔp·éB
            // @@@@31Fo¸
            l_blnReturn = WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        //  ÈOÌêAfalseðÔp·éB

        log.debug(STR_METHOD_NAME + ".is¶ótÂ\ : " + l_blnReturn);

        if (!l_blnReturn)
        {
            log.error(STR_METHOD_NAME + ".is¶ótÂ\ªfalseÌê");
            log.error(STR_METHOD_NAME + "¶óÔ:" + l_ordStsEnum);
            log.error(STR_METHOD_NAME + "ótæª:" + l_strAfterChangeAcceptDiv);
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (isù³¶)<BR>
     * ù³¶©ð»è·éB<BR>
     * <BR>
     * ÈºÌðÅOù³óµe[u©çR[hðæ¾·éB<BR>
     * <BR>
     *    [ð]<BR>
     *    ûÀID = this.ûÀID<BR>
     *    VK¶ID = this.¶ID<BR>
     * <BR>
     * R[hªæ¾Å«½êÍ true ðA<BR>
     * æ¾Å«È©Á½êÍ false ðÔp·éB<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A94C1A030B
     */
    public boolean isChangeOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeOrder()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderChangeStatusRow l_row = FeqOrderChangeStatusDao.findRowByAccountIdNewOrderId(
                this.getAccountId(),
                new Long(this.getOrderId()));
            

            // R[hªæ¾Å«½êÍ true ðAæ¾Å«È©Á½êÍ false ðÔp·éB
            log.exiting(STR_METHOD_NAME);
            return (l_row == null) ? false : true;
        }
        catch (DataFindException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (isoI¹)<BR>
     * oI¹ÏÝ©ð»è·éB<BR>
     * <BR>
     * i¶PÊs.oI¹ú == nulljÌêAfalse<BR>
     * i¶PÊs.oI¹ú != nulljÌêAtrue<BR>
     * ð»ê¼êÔp·éB<BR>
     * @@return boolean
     * @@roseuid 42AFDEA903A8
     */
    public boolean isExecEnd()
    {
        return (this.m_row.getExecEndTimestamp() == null) ? false : true;
    }
    
    /**
     * (getñèSEQ)<BR>
     * ñèSEQðæ¾·éB<BR>
     * <BR>
     * thisD¶PÊsDñèSEQðÔp·éB<BR>
     * <BR>
     * @@return String
     */
    public String getExecutionSeqNo()
    {
        if (this.m_row.getExecutionSeqNoIsNull())
        {
            return null;
        }

        return String.valueOf(this.m_row.getExecutionSeqNo());
    }
}
@
