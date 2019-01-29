head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SeszSleRequestPreparer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : HKSleRequestPreparerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/10/24 孫 新規作成
*/
package webbroker3.slegateway.service.delegate.stdimpls;

import java.math.BigDecimal;
import com.fitechlabs.xbconnector.glbase.gldata.GlData;
import com.fitechlabs.xbconnector.glbase.gldata.GlSleRequest;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.slebase.data.SleExchangeOrderKeyMngPK;
import webbroker3.slebase.data.SleExchangeOrderKeyMngRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.utils.SleConstants;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.WEB3SleProcessorsImpl;

/**
 * 市場へ送信する前で電文変換処理を行うクラス.(シンセン市場)
 * 
 */
public class WEB3SeszSleRequestPreparer implements WEB3SleRequestPreparer
{
	/**
	 * ログ出力ユーティリティ
	 */
	private static final WEB3LogUtility m_log =
		WEB3LogUtility.getInstance(WEB3SeszSleRequestPreparer.class);
	
	/**
	 * DBプロセッサ
	 */
	private static WEB3SleProcessors m_wsp;
	
	
	/**
	 * preparerクラスのsingltonインスタンス
	 */
	private static WEB3SeszSleRequestPreparer m_preparer = null;
	
	/**
	 * コンストラクタ
	 */
	public WEB3SeszSleRequestPreparer(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * コンストラクタ
	 */
	private WEB3SeszSleRequestPreparer() {
		;
	}
	
	/**
	 * Singletonインスタンス取得する
	 */
	public static WEB3SeszSleRequestPreparer getInstance()
	{
		
		if(m_preparer == null)
		{
				m_preparer =  new WEB3SeszSleRequestPreparer(new WEB3SleProcessorsImpl());
		} 
		return m_preparer;
	}
	
	/**
	 * 市場へ送信する前での準備処理
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return GlSleRequest
	 * @@throws RuntimeException 無効注文の場合(外株以外の注文)スローされる
	 */
	public GlSleRequest prepare(SleSendQRow l_sendqRow)
	{

		m_log.entering("prepare(SleSendQRow)");
		final SleSendqOpTypeEnum opType = l_sendqRow.getOpType();
		GlData l_glData = null;
		if (SleSendqOpTypeEnum.NEW_ORDER.equals(opType))
		{
			//新規注文
			l_glData = prepareNewOrder(l_sendqRow);
		}
		else if (SleSendqOpTypeEnum.CANCEL_ORDER.equals(opType))
		{
			//注文取消し
			l_glData = prepareCancelOrder(l_sendqRow);
		}
		else if (SleSendqOpTypeEnum.CHANGE_ORDER.equals(opType)) {
			//変更注文
			l_glData = prepareChangeOrder(l_sendqRow);
		}
		else
		{

			final String errMsg =
				"Invalid op_type in sle_send_q row. Row Values:" + l_sendqRow;
			m_log.error(errMsg);

			m_log.exiting("prepare(SleSendQRow sendqRow)");
			throw new IllegalArgumentException(errMsg);
		}
		final GlSleRequest sleReq =
			new GlSleRequest(SleConstants.Markets.SESZ.F_GL_REQ_ID, l_glData);

		setCommonFieldValues(l_sendqRow, l_glData);

		m_log.exiting("prepare(SleSendQRow)");
		return sleReq;
	}
	
	/**
	 * 新規注文リクエストを送信する前での準備処理
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return GlData
	 */
	private GlData prepareNewOrder(SleSendQRow l_sendqRow) {

		final GlData l_glData = new GlData(SleConstants.GLDATACOMMENT.ORDER_NEW);

		//コマンド
		l_glData.putBigDecimal(
			SleConstants.Order2000MsgItem.F_COMMAND,
			new BigDecimal(SleConstants.OrderCommand.NEW));

		//買売区分
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);

		//注文数量
		l_glData.putBigDecimal(
			SleConstants.Order2000MsgItem.F_QUANTITY,
			new BigDecimal(
			//新規の場合、約定済み数量を考慮しないように改修 2006/9/25 FTL李
				l_sendqRow.getQuantity()));

		//値段条件
//		final String modality = getModality(l_sendqRow);　@//2006/10/6 孫　@修正
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		//指値注文の価格
		if (!GtlUtils.Double.isZero(l_sendqRow.getLimitPrice())) {
			l_glData.putBigDecimal(
				SleConstants.Order2000MsgItem.F_PRICE,
				new BigDecimal(l_sendqRow.getLimitPrice()).setScale(4,BigDecimal.ROUND_HALF_UP));
		}
        
        //注文有効期限   //2006/10/6 孫 追加
        l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
        
		//内藤証券管理するユーザ口座ID    //2006/10/6 孫 追加  ←　@NUM型へ改修 または　@共通項目から移す 2006/10/11 李FLT
//		l_glData.putString(SleConstants.Order2000MsgItem.F_CUSTOMER_ACCOUNT, Long.toString(l_sendqRow.getAccountId()));

		return l_glData;
	}

	
	/**
	 * 取消注文リクエストを送信する前での準備処理
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return GlData
	 */
	private GlData prepareCancelOrder(SleSendQRow l_sendqRow)
	{
		final GlData l_glData =
			new GlData(SleConstants.GLDATACOMMENT.ORDER_CANCEL);
		//コマンド
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_COMMAND, 
			new BigDecimal(SleConstants.OrderCommand.CANCEL));

		//市場管理取引番号
		l_glData.putString(SleConstants.Order2000MsgItem.F_EXCHANGE_REFERENCE, 
			getExchangeReference(l_sendqRow));
		
		//買売区分  ⇒2006/10/16　@追加
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);
			
		//値段条件  ⇒2006/10/16　@追加
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		
		//注文有効期限   ⇒2006/10/16 追加
		l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
		 
		return l_glData;
	}

	/**
	 * 変更注文リクエストを送信する前での準備処理
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return GlData
	 */
	private GlData prepareChangeOrder(SleSendQRow l_sendqRow) {
		final GlData l_glData = new GlData(SleConstants.GLDATACOMMENT.ORDER_CHG);

		//コマンド
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_COMMAND, 
			new BigDecimal(SleConstants.OrderCommand.MODIFY));

		//買売区分     //2006/10/6 孫　@削除 ⇒ 2006/10/15 復元
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);

		//注文数量
		if ( l_sendqRow.getAlreadyExecdQuantity() == 0){
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_QUANTITY, 
						new BigDecimal(l_sendqRow.getQuantity()));
		}else{
		
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_QUANTITY, 
			new BigDecimal(l_sendqRow.getQuantity() - l_sendqRow.getAlreadyExecdQuantity()));
		}
		
		//指値注文の価格
		if (!GtlUtils.Double.isZero(l_sendqRow.getLimitPrice())) {
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_PRICE, 
				new BigDecimal(l_sendqRow.getLimitPrice()).setScale(4,BigDecimal.ROUND_HALF_UP));
		}
		

		//市場管理取引番号
		l_glData.putString(SleConstants.Order2000MsgItem.F_EXCHANGE_REFERENCE, 
			getExchangeReference(l_sendqRow));

		//残った注文数
		if ( l_sendqRow.getAlreadyExecdQuantity() == 0) {
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_REMAINING_QUANTITY,
			new BigDecimal(l_sendqRow.getChangeQuantity() ));//NUMへ変更 2006/10/16		
		}else{
		
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_REMAINING_QUANTITY,
			new BigDecimal(l_sendqRow.getChangeQuantity() - l_sendqRow.getAlreadyExecdQuantity()));
		}
		
		//内藤証券管理するユーザ口座ID    //2006/10/6 孫 追加  ←　@NUM型へ改修 または　@共通項目から移す 2006/10/11 李FLT
		l_glData.putString(SleConstants.Order2000MsgItem.F_CUSTOMER_ACCOUNT, Long.toString(l_sendqRow.getAccountId()));
		

		//値段条件  ⇒2006/10/16　@追加
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		
		//注文有効期限   ⇒2006/10/16 追加
		l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
		 
		return l_glData;
	}
	
	/**
	 * 全てのオペレータタイプに共通する電文フィールドを設定する
	 * @@param sendqRow
	 *          SEND_Qメッセージのコンテンツ
	 * @@param glData
	 *          電文データ
	 */
	private void setCommonFieldValues(SleSendQRow l_sendqRow, GlData l_glData) {

		
        //ユーザーナンバー   2006/10/6 孫 追加
//       l_glData.putString(
//                SleConstants.Order2000SZ.F_USER_NUMBER,
 //               SleConstants.Markets.SZ.DIR_SZ_GL_NO);
// 		l_glData.putBigDecimal(
// 				  SleConstants.Order2000SZ.F_USER_NUMBER,
//				  new BigDecimal(SleConstants.Markets.SZ.DIR_SZ_GL_NO));        
        //テスト用"101"に設定している
        
        //仕様改良のため、追加 ⇒ 2006/10/18 
        //↑⇒SLEコネクタサーバで自動指定可能なので、ここで指定する必要が無し 2006/11/1
//		l_glData.putBigDecimal(
//				  SleConstants.Order2000SZ.F_USER_NUMBER,
//				  new BigDecimal(getUserNo()));         
        
        //注文カテゴリ
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_ORDER_CATEGORY,
			SleConstants.Markets.SESZ.OrderCategory.SINGLE);

		//銘柄コード
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_STOCK_CODE,
			l_sendqRow.getProductCode());

		//GLID
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_GLID,
			SleConstants.Markets.SESZ.GLID);
		//  "000800000000"

		//REFERENCE(注文IDを保持する)
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_INTERNAL_REFERENCE,
			"" + l_sendqRow.getOrderId());

		//memo(キューIDを保持する)
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_MEMO,
			"" + l_sendqRow.getQueueId());

		//運用コード 追加 2006/11/15
		l_glData.putString(
//			SleConstants.Order2000MsgItem.F_LONG_NAME,
			//GL対応不可能ため、ROUTING_REFERENCEに変更 2006/11/21
			SleConstants.Order2000MsgItem.ROUTING_REFERENCE,
			"" + l_sendqRow.getOrderEmpCode());		
	}

	/**
	 * SLE電文の市場取引番号を取得
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return 市場取引番号
	 */
	private String getExchangeReference(SleSendQRow l_sendqRow)
	{
		m_log.entering("getExchangeReference(SleSendQRow)");

		final QueryProcessor qp;
		final SleExchangeOrderKeyMngRow row;

		try {
			qp = m_wsp.getDefaultProcessor();
			row =
				(SleExchangeOrderKeyMngRow) qp.doFindByPrimaryKeyQuery(
					new SleExchangeOrderKeyMngPK(
						l_sendqRow.getProductType().intValue(),
						l_sendqRow.getOrderUnitId()));
			return row.getExchangeOrderKey();
		} catch (DataFindException dfe) {
			;
			return null;
		} catch (DataException ex) {
			final String msg =
				"Exception while fetching sle_mkt_order_key_map with order_unit_id :"
					+ l_sendqRow.getOrderUnitId();
			m_log.error(msg, ex);
			m_log.exiting("getExchangeReference(SleSendQRow)");
			throw new RuntimeException(msg, ex);
		}

	}

	/**
	 * SLE電文の 'modality' フィールドを取得.
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return modality. 実際に未使用(2006/10/18)
	 */

	private String getModality(SleSendQRow l_sendqRow)
	{

		if (GtlUtils.Double.isZero(l_sendqRow.getLimitPrice()))
		{
			//成り行き
			return SleConstants.Markets.SESZ.Modality.ANY_PRICE;
		}
		else{
			//指値(当面、'指値'のみ指定可能)
			return SleConstants.Markets.SESZ.Modality.LIMIT_PRICE;
		}
	}

	/**
	 * SLE電文の買売区分フィールドを取得
	 * @@param sendqRow
	 *          SEND_Qメッセージ
	 * @@return 買売区分
	 */
	private BigDecimal getSleSide(SleSendQRow l_sendqRow)
	{

		m_log.entering("getSleSide(SleSendQRow)");
		switch (l_sendqRow.getOrderType().intValue()) {

			//外株買
			case OrderTypeEnum.IntValues.FEQ_BUY:
				m_log.exiting("getSleSide(SleSendQRow)");
				return new BigDecimal(SleConstants.Markets.SESZ.Side.BUY);

				//外株売
			case OrderTypeEnum.IntValues.FEQ_SELL:
				m_log.exiting("getSleSide(SleSendQRow)");
				return new BigDecimal(SleConstants.Markets.SESZ.Side.SELL);

			default:
				final String errMsg =
					"Invalid order_type in sle_send_q table with queue_id:"
						+ l_sendqRow.getQueueId();
				m_log.error(errMsg);
				m_log.exiting("getSleSide(SleSendQRow)");
				return null;
		}

	}
	
	/**
	 * SLE電文のユーザNoを取得.
	 * 
	 * @@return int UserNo.
	 */

	private int getUserNo()
	{

		m_log.entering("getUserNo()");
		
		final String l_strName = "sle.user.number";
		final int defaultValue = 101;

		final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
	
		if (l_strValue == null)
		{
			final String msg =
				"sle connector sle user number not found in the SYSTEM_PREFERENCES with  name:"
					+ l_strName;
			m_log.warn(msg);
			m_log.exiting("getUserNo()");
			return defaultValue;
		}
		
		m_log.exiting("getUserNo");
	
		return Integer.valueOf(l_strValue).intValue();
	}
}@
