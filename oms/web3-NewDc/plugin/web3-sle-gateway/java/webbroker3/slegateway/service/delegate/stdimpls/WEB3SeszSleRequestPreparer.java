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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : HKSleRequestPreparer�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/10/24 �� �V�K�쐬
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
 * �s��֑��M����O�œd���ϊ��������s���N���X.(�V���Z���s��)
 * 
 */
public class WEB3SeszSleRequestPreparer implements WEB3SleRequestPreparer
{
	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static final WEB3LogUtility m_log =
		WEB3LogUtility.getInstance(WEB3SeszSleRequestPreparer.class);
	
	/**
	 * DB�v���Z�b�T
	 */
	private static WEB3SleProcessors m_wsp;
	
	
	/**
	 * preparer�N���X��singlton�C���X�^���X
	 */
	private static WEB3SeszSleRequestPreparer m_preparer = null;
	
	/**
	 * �R���X�g���N�^
	 */
	public WEB3SeszSleRequestPreparer(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * �R���X�g���N�^
	 */
	private WEB3SeszSleRequestPreparer() {
		;
	}
	
	/**
	 * Singleton�C���X�^���X�擾����
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
	 * �s��֑��M����O�ł̏�������
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return GlSleRequest
	 * @@throws RuntimeException ���������̏ꍇ(�O���ȊO�̒���)�X���[�����
	 */
	public GlSleRequest prepare(SleSendQRow l_sendqRow)
	{

		m_log.entering("prepare(SleSendQRow)");
		final SleSendqOpTypeEnum opType = l_sendqRow.getOpType();
		GlData l_glData = null;
		if (SleSendqOpTypeEnum.NEW_ORDER.equals(opType))
		{
			//�V�K����
			l_glData = prepareNewOrder(l_sendqRow);
		}
		else if (SleSendqOpTypeEnum.CANCEL_ORDER.equals(opType))
		{
			//���������
			l_glData = prepareCancelOrder(l_sendqRow);
		}
		else if (SleSendqOpTypeEnum.CHANGE_ORDER.equals(opType)) {
			//�ύX����
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
	 * �V�K�������N�G�X�g�𑗐M����O�ł̏�������
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return GlData
	 */
	private GlData prepareNewOrder(SleSendQRow l_sendqRow) {

		final GlData l_glData = new GlData(SleConstants.GLDATACOMMENT.ORDER_NEW);

		//�R�}���h
		l_glData.putBigDecimal(
			SleConstants.Order2000MsgItem.F_COMMAND,
			new BigDecimal(SleConstants.OrderCommand.NEW));

		//�����敪
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);

		//��������
		l_glData.putBigDecimal(
			SleConstants.Order2000MsgItem.F_QUANTITY,
			new BigDecimal(
			//�V�K�̏ꍇ�A���ςݐ��ʂ��l�����Ȃ��悤�ɉ��C 2006/9/25 FTL��
				l_sendqRow.getQuantity()));

		//�l�i����
//		final String modality = getModality(l_sendqRow);�@@//2006/10/6 ���@@�C��
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		//�w�l�����̉��i
		if (!GtlUtils.Double.isZero(l_sendqRow.getLimitPrice())) {
			l_glData.putBigDecimal(
				SleConstants.Order2000MsgItem.F_PRICE,
				new BigDecimal(l_sendqRow.getLimitPrice()).setScale(4,BigDecimal.ROUND_HALF_UP));
		}
        
        //�����L������   //2006/10/6 �� �ǉ�
        l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
        
		//�����،��Ǘ����郆�[�U����ID    //2006/10/6 �� �ǉ�  ���@@NUM�^�։��C �܂��́@@���ʍ��ڂ���ڂ� 2006/10/11 ��FLT
//		l_glData.putString(SleConstants.Order2000MsgItem.F_CUSTOMER_ACCOUNT, Long.toString(l_sendqRow.getAccountId()));

		return l_glData;
	}

	
	/**
	 * ����������N�G�X�g�𑗐M����O�ł̏�������
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return GlData
	 */
	private GlData prepareCancelOrder(SleSendQRow l_sendqRow)
	{
		final GlData l_glData =
			new GlData(SleConstants.GLDATACOMMENT.ORDER_CANCEL);
		//�R�}���h
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_COMMAND, 
			new BigDecimal(SleConstants.OrderCommand.CANCEL));

		//�s��Ǘ�����ԍ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_EXCHANGE_REFERENCE, 
			getExchangeReference(l_sendqRow));
		
		//�����敪  ��2006/10/16�@@�ǉ�
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);
			
		//�l�i����  ��2006/10/16�@@�ǉ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		
		//�����L������   ��2006/10/16 �ǉ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
		 
		return l_glData;
	}

	/**
	 * �ύX�������N�G�X�g�𑗐M����O�ł̏�������
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return GlData
	 */
	private GlData prepareChangeOrder(SleSendQRow l_sendqRow) {
		final GlData l_glData = new GlData(SleConstants.GLDATACOMMENT.ORDER_CHG);

		//�R�}���h
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_COMMAND, 
			new BigDecimal(SleConstants.OrderCommand.MODIFY));

		//�����敪     //2006/10/6 ���@@�폜 �� 2006/10/15 ����
		final BigDecimal side = getSleSide(l_sendqRow);
		l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_SIDE, side);

		//��������
		if ( l_sendqRow.getAlreadyExecdQuantity() == 0){
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_QUANTITY, 
						new BigDecimal(l_sendqRow.getQuantity()));
		}else{
		
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_QUANTITY, 
			new BigDecimal(l_sendqRow.getQuantity() - l_sendqRow.getAlreadyExecdQuantity()));
		}
		
		//�w�l�����̉��i
		if (!GtlUtils.Double.isZero(l_sendqRow.getLimitPrice())) {
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_PRICE, 
				new BigDecimal(l_sendqRow.getLimitPrice()).setScale(4,BigDecimal.ROUND_HALF_UP));
		}
		

		//�s��Ǘ�����ԍ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_EXCHANGE_REFERENCE, 
			getExchangeReference(l_sendqRow));

		//�c����������
		if ( l_sendqRow.getAlreadyExecdQuantity() == 0) {
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_REMAINING_QUANTITY,
			new BigDecimal(l_sendqRow.getChangeQuantity() ));//NUM�֕ύX 2006/10/16		
		}else{
		
			l_glData.putBigDecimal(SleConstants.Order2000MsgItem.F_REMAINING_QUANTITY,
			new BigDecimal(l_sendqRow.getChangeQuantity() - l_sendqRow.getAlreadyExecdQuantity()));
		}
		
		//�����،��Ǘ����郆�[�U����ID    //2006/10/6 �� �ǉ�  ���@@NUM�^�։��C �܂��́@@���ʍ��ڂ���ڂ� 2006/10/11 ��FLT
		l_glData.putString(SleConstants.Order2000MsgItem.F_CUSTOMER_ACCOUNT, Long.toString(l_sendqRow.getAccountId()));
		

		//�l�i����  ��2006/10/16�@@�ǉ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_MODALITY, SleConstants.Markets.SESZ.Modality.LIMIT_PRICE);

		
		//�����L������   ��2006/10/16 �ǉ�
		l_glData.putString(SleConstants.Order2000MsgItem.F_VALIDITY, SleConstants.Markets.SESZ.Validity.ALL_DAY);
		 
		return l_glData;
	}
	
	/**
	 * �S�ẴI�y���[�^�^�C�v�ɋ��ʂ���d���t�B�[���h��ݒ肷��
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W�̃R���e���c
	 * @@param glData
	 *          �d���f�[�^
	 */
	private void setCommonFieldValues(SleSendQRow l_sendqRow, GlData l_glData) {

		
        //���[�U�[�i���o�[   2006/10/6 �� �ǉ�
//       l_glData.putString(
//                SleConstants.Order2000SZ.F_USER_NUMBER,
 //               SleConstants.Markets.SZ.DIR_SZ_GL_NO);
// 		l_glData.putBigDecimal(
// 				  SleConstants.Order2000SZ.F_USER_NUMBER,
//				  new BigDecimal(SleConstants.Markets.SZ.DIR_SZ_GL_NO));        
        //�e�X�g�p"101"�ɐݒ肵�Ă���
        
        //�d�l���ǂ̂��߁A�ǉ� �� 2006/10/18 
        //����SLE�R�l�N�^�T�[�o�Ŏ����w��\�Ȃ̂ŁA�����Ŏw�肷��K�v������ 2006/11/1
//		l_glData.putBigDecimal(
//				  SleConstants.Order2000SZ.F_USER_NUMBER,
//				  new BigDecimal(getUserNo()));         
        
        //�����J�e�S��
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_ORDER_CATEGORY,
			SleConstants.Markets.SESZ.OrderCategory.SINGLE);

		//�����R�[�h
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_STOCK_CODE,
			l_sendqRow.getProductCode());

		//GLID
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_GLID,
			SleConstants.Markets.SESZ.GLID);
		//  "000800000000"

		//REFERENCE(����ID��ێ�����)
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_INTERNAL_REFERENCE,
			"" + l_sendqRow.getOrderId());

		//memo(�L���[ID��ێ�����)
		l_glData.putString(
			SleConstants.Order2000MsgItem.F_MEMO,
			"" + l_sendqRow.getQueueId());

		//�^�p�R�[�h �ǉ� 2006/11/15
		l_glData.putString(
//			SleConstants.Order2000MsgItem.F_LONG_NAME,
			//GL�Ή��s�\���߁AROUTING_REFERENCE�ɕύX 2006/11/21
			SleConstants.Order2000MsgItem.ROUTING_REFERENCE,
			"" + l_sendqRow.getOrderEmpCode());		
	}

	/**
	 * SLE�d���̎s�����ԍ����擾
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return �s�����ԍ�
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
	 * SLE�d���� 'modality' �t�B�[���h���擾.
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return modality. ���ۂɖ��g�p(2006/10/18)
	 */

	private String getModality(SleSendQRow l_sendqRow)
	{

		if (GtlUtils.Double.isZero(l_sendqRow.getLimitPrice()))
		{
			//����s��
			return SleConstants.Markets.SESZ.Modality.ANY_PRICE;
		}
		else{
			//�w�l(���ʁA'�w�l'�̂ݎw��\)
			return SleConstants.Markets.SESZ.Modality.LIMIT_PRICE;
		}
	}

	/**
	 * SLE�d���̔����敪�t�B�[���h���擾
	 * @@param sendqRow
	 *          SEND_Q���b�Z�[�W
	 * @@return �����敪
	 */
	private BigDecimal getSleSide(SleSendQRow l_sendqRow)
	{

		m_log.entering("getSleSide(SleSendQRow)");
		switch (l_sendqRow.getOrderType().intValue()) {

			//�O����
			case OrderTypeEnum.IntValues.FEQ_BUY:
				m_log.exiting("getSleSide(SleSendQRow)");
				return new BigDecimal(SleConstants.Markets.SESZ.Side.BUY);

				//�O����
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
	 * SLE�d���̃��[�UNo���擾.
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
