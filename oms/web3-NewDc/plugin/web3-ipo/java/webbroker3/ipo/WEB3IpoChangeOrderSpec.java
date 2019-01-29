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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  購入申込内容(WEB3IpoChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 杜珉 新規作成
Revesion History : 2005/01/11 坂上(SRA)エラーコードの修正
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
 * ( 購入申込内容)<BR>
 * 
 * @@author 杜珉
 * @@version 1.0
 */
public class WEB3IpoChangeOrderSpec extends ChangeOrderSpec {

	/**
	 * 取引者
	 */
	private Trader trader;

	/**
	 * 購入申込数量
	 */
	private double applicationQuantity;

	/**
	 * 税区分
	 */
	private TaxTypeEnum taxType;

	/**
	 * (ログ出力ユーティリティ。)<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IpoChangeOrderSpec.class);

	/**
	 * (購入申込内容)<BR>
	 * コンストラクタ。<BR>
	 * 購入申込内容を生成する。<BR>
	 * <BR>
	 * １）　@スーパークラスのコンストラクタ（super）をコール、インスタンスを生成する。<BR><BR>
	 * [superに指定する引数]<BR>
	 * 注文ＩＤ：　@IPO申告ＩＤ<BR>
	 * <BR>
	 * ２）　@プロパティに引数の取引者、購入申込数量、税区分をセットする。<BR>
	 * @@param l_trader - (取引者)<BR>
	 * 取引者（扱者）オブジェクト
	 * 
	 * @@param l_lngIpoOrderId - IPO申告ＩＤ
	 * @@param l_dblApplicationQuantity - 購入申込数量
	 * @@param l_taxType - 税区分
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
	 * (get取引者)<BR>
	 * （getTrader）<BR>
	 * <BR>
	 * this.取引者を返却する。
	 * @@return Trader
	 * @@roseuid 40DBC53A02BB
	 */
	public Trader getTrader() {
		return trader;
	}

	/**
	 * (get購入申込数量)<BR>
	 * 購入申込数量を取得する。
	 * @@return double
	 * @@roseuid 40DBC4520260
	 */
	public double getApplicationQuantity() {
		return applicationQuantity;
	}

	/**
	 * (get税区分)<BR>
	 * 税区分を取得する。
	 * @@return TaxTypeEnum
	 * @@roseuid 40DBC51802FA
	 */
	// 2004/12/06 障害管理票No.U00496 税区分が[特定]の場合、顧客オブジェクト.税区分を返却するように修正。情野@@SRA START
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
//		//セキュリティサービスを取得
//		OpLoginSecurityService l_opLoginSec =
//			(OpLoginSecurityService) Services.getService(
//				OpLoginSecurityService.class);
//		//セキュリティサービスより口座コードを取得 
//		l_lngAccountId = l_opLoginSec.getAccountId();
//
//		//FinAppサービス
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
//				"__getTaxTypeで口座取得でエラー__",
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
////				"__getTaxType税区分取得でエラー__",
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
//	// 2004/12/06 障害管理票No.U00496 税区分が[特定]の場合、顧客オブジェクト.税区分を返却するように修正。情野@@SRA START
    }
}
@
