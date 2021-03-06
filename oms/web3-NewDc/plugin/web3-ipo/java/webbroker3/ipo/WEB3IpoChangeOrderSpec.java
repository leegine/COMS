head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        :  wü\àe(WEB3IpoChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 mûa VKì¬
Revesion History : 2005/01/11 âã(SRA)G[R[hÌC³
*/
package webbroker3.ipo;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

/**
 * ( wü\àe)<BR>
 * 
 * @@author mûa
 * @@version 1.0
 */
public class WEB3IpoChangeOrderSpec extends ChangeOrderSpec {

	/**
	 * æøÒ
	 */
	private Trader trader;

	/**
	 * wü\Ê
	 */
	private double applicationQuantity;

	/**
	 * Åæª
	 */
	private TaxTypeEnum taxType;

	/**
	 * (OoÍ[eBeBB)<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IpoChangeOrderSpec.class);

	/**
	 * (wü\àe)<BR>
	 * RXgN^B<BR>
	 * wü\àeð¶¬·éB<BR>
	 * <BR>
	 * Pj@@X[p[NXÌRXgN^isuperjðR[ACX^Xð¶¬·éB<BR><BR>
	 * [superÉwè·éø]<BR>
	 * ¶hcF@@IPO\hc<BR>
	 * <BR>
	 * Qj@@vpeBÉøÌæøÒAwü\ÊAÅæªðZbg·éB<BR>
	 * @@param l_trader - (æøÒ)<BR>
	 * æøÒiµÒjIuWFNg
	 * 
	 * @@param l_lngIpoOrderId - IPO\hc
	 * @@param l_dblApplicationQuantity - wü\Ê
	 * @@param l_taxType - Åæª
	 * @@return webbroker3.ipo.WEB3IpoChangeOrderSpec
	 * @@roseuid 40DBC4520270
	 */
	public WEB3IpoChangeOrderSpec(
		Trader l_trader,
		long l_lngIpoOrderId,
		double l_dblApplicationQuantity,
		TaxTypeEnum l_taxType) {
		super(l_lngIpoOrderId);
		trader = l_trader;
		applicationQuantity = l_dblApplicationQuantity;
		taxType = l_taxType;
	}

	/**
	 * (getæøÒ)<BR>
	 * igetTraderj<BR>
	 * <BR>
	 * this.æøÒðÔp·éB
	 * @@return Trader
	 * @@roseuid 40DBC53A02BB
	 */
	public Trader getTrader() {
		return trader;
	}

	/**
	 * (getwü\Ê)<BR>
	 * wü\Êðæ¾·éB
	 * @@return double
	 * @@roseuid 40DBC4520260
	 */
	public double getApplicationQuantity() {
		return applicationQuantity;
	}

	/**
	 * (getÅæª)<BR>
	 * Åæªðæ¾·éB
	 * @@return TaxTypeEnum
	 * @@roseuid 40DBC51802FA
	 */
	// 2004/12/06 áQÇ[No.U00496 Åæªª[Áè]ÌêAÚqIuWFNg.ÅæªðÔp·éæ¤ÉC³Bîì@@SRA START
	public TaxTypeEnum getTaxType() throws WEB3BusinessLayerException {

		final String STR_METHOD_NAME = "getTaxType( )";
		log.entering(STR_METHOD_NAME);

//		if (this.taxType.equals(TaxTypeEnum.NORMAL)) {
//			log.exiting(STR_METHOD_NAME);
			return this.taxType;
//		}
//
//		long l_lngAccountId;
//
//		//ZLeBT[rXðæ¾
//		OpLoginSecurityService l_opLoginSec =
//			(OpLoginSecurityService) Services.getService(
//				OpLoginSecurityService.class);
//		//ZLeBT[rXæèûÀR[hðæ¾ 
//		l_lngAccountId = l_opLoginSec.getAccountId();
//
//		//FinAppT[rX
//		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//		AccountManager l_accMgr = l_finApp.getAccountManager();
//		WEB3GentradeMainAccount l_mainAccount = null;
//
//		try {
//			l_mainAccount =
//				(WEB3GentradeMainAccount) l_accMgr.getMainAccount(
//					l_lngAccountId);
//		} catch (NotFoundException l_nfe) {
//			log.error(
//				"__getTaxTypeÅûÀæ¾ÅG[__",
//				new WEB3BusinessLayerException(
//					WEB3ErrorCatalog.BUSINESS_ERROR_00064,
//					this.getClass().getName() + "." + STR_METHOD_NAME));
//			log.exiting(STR_METHOD_NAME);
//			throw new WEB3BusinessLayerException(
//				WEB3ErrorCatalog.BUSINESS_ERROR_00064,
//				this.getClass().getName() + "." + STR_METHOD_NAME);
//		}
//
//		MainAccountRow l_mainAccountRow =
//			(MainAccountRow) l_mainAccount.getDataSourceObject();
//		TaxTypeEnum l_taxTypeRow = l_mainAccountRow.getTaxType();
//
////		if (l_taxTypeRow.equals(TaxTypeEnum.SPECIAL)
////        
////			|| l_taxTypeRow.equals(TaxTypeEnum.SPECIAL_WITHHOLD)) {
//////			log.exiting(STR_METHOD_NAME);
//			return l_taxTypeRow;
////		} else {
////			log.error(
////				"__getTaxTypeÅæªæ¾ÅG[__",
////				new WEB3BusinessLayerException(
////					WEB3ErrorCatalog.BUSINESS_ERROR_00064,
////					getClass().getName() + "." + STR_METHOD_NAME));
////			log.exiting(STR_METHOD_NAME);
////			throw new WEB3BusinessLayerException(
////				WEB3ErrorCatalog.BUSINESS_ERROR_00064,
////				getClass().getName() + "." + STR_METHOD_NAME);
////		}
//	}
//	//	public TaxTypeEnum getTaxType(){
//	//		return taxType
//	//	}
//	// 2004/12/06 áQÇ[No.U00496 Åæªª[Áè]ÌêAÚqIuWFNg.ÅæªðÔp·éæ¤ÉC³Bîì@@SRA START
    }
}
@
