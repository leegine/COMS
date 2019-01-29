head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAssetDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ��ۗL���Y���׃I�u�W�F�N�g(WEB3RuitoAssetDetail)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/08  ���u�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
/**
 * �ݓ��ۗL���Y���׃I�u�W�F�N�g<BR>
 */
public class WEB3RuitoAssetDetail
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoAssetDetail.class);	
		
	/**
	 * ����ID<BR>
	 */
	private long productID;
	/**
	 * �ݓ��c������<BR>
	 */
	private double balance;
	/**
	 * �ݓ��ɂ���30�����o�߂����c��<BR>
	 */
	private double countAfterPenalty;
	/**
	 * �ݓ���30�����o�߂��Ă��Ȃ����y�i���e�B�Ώێc��<BR>
	 */
	private double countBeforePenalty;
	/**
	 * �M�����Y���ۊz�@@���@@30�����o�ߎc�� �~ 0.001<BR>
	 */
	private double trustFortunePenaltyPrice;
	/**
	 * ���\�c�� �� �c�� �| �M�����Y���ۊz �| ��񒆒�������<BR>
	 */
	private double sellPossibleBalance;
	/**
	 * ��񒍕�����<BR>
	 */
	private WEB3RuitoSellOrderDetail[] sellOrderDetail;
	/**
	 * �ݓ��^�C�v<BR>
	 * �iRuitoTypeEnum�ɂĒ�`�j<BR>
	 * <BR>
	 * �R�F�������t�@@���h<BR>
	 * �S�FMMF<BR>
	 */
	private RuitoTypeEnum ruitoType;
	/**
	 * ��񒍕��̍��v��ݒ肷��<BR>
	 */
	private double sellOrderTotal;
	/**
	 * �ݓ��ۗL���Y���׃I�u�W�F�N�g<BR>
	 */
	public WEB3RuitoSellOrderDetail theWEB3RuitoSellOrderDetail[];

	/**
	 * �R���X�g���N�^<BR>
	 * �P�j�@@�����̒l���e�����ɐݒ肷��B<BR> 
	�@@  *     �|����.����ID��this.����ID�ɐݒ肷��B<BR> 
	�@@  *     �|����.�c����this.�c���ɐݒ肷��B <BR>
	�@@  *     �|����.30�����o�ߎc����this.30�����o�ߎc���ɐݒ肷��B<BR> 
	�@@  *     �|����.�ݓ��^�C�v��this.�ݓ��^�C�v�ɐݒ肷��B <BR>
	�@@  *     �|�ȉ��̌v�Z���ʂ�this.30���o�ߎc���ɐݒ肷��B <BR>
	�@@�@@*�@@     ����.�c�� - ����.30�����o�ߎc��<BR>
	 * @@roseuid 40BC3716036C<BR>
	 */
	public WEB3RuitoAssetDetail(
		long l_lngProductId,
		double l_dblBalance,
		double l_countBeforePenalty,
		RuitoTypeEnum l_ruitoType)
	{
		final String STR_METHOD_NAME = "WEB3RuitoAssetDetail(long l_lngProductId," 
		    + " double l_dblBalance, double l_countBeforePenalty, RuitoTypeEnum l_ruitoType)";
		log.entering(STR_METHOD_NAME);

		productID = l_lngProductId;
		balance = l_dblBalance;
		countBeforePenalty = l_countBeforePenalty;
		ruitoType = l_ruitoType;
        countAfterPenalty = l_dblBalance - l_countBeforePenalty;
        
        log.debug("productID = " + productID);
        log.debug("balance = " + balance);
        log.debug("countBeforePenalty = " + countBeforePenalty);
        log.debug("ruitoType = " + ruitoType);
        log.debug("countAfterPenalty = " + countAfterPenalty);
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * �c���̐ݒ���s���B<BR>
	 * @@param l_dblBalance
	 * @@roseuid 40765A2102E7
	 */
	public void setBalance(double l_dblBalance)
	{
		log.debug("l_dblBalance = " + l_dblBalance);
		balance = l_dblBalance;
	}
	/**
	 * this.�c����ԋp����<BR>
	 * @@return double
	 * @@roseuid 40765A8A0307
	 */
	public double getBalance()
	{
		log.debug("balance = " + balance);
		return balance;
	}
	/**
	 * 30���o�ߎc���̐ݒ���s���B<BR>
	 * @@param l_dblCount
	 * @@roseuid 40765A2B026A
	 */
	public void setCountAfterPenalty(double l_dblCount)
	{
		log.debug("l_dblCount = " + l_dblCount);
		countAfterPenalty = l_dblCount;
	}
	/**
	 * this.30���o�ߎc����ԋp����B<BR>
	 * @@return double
	 * @@roseuid 40765A970190
	 */
	public double getCountAfterPenalty()
	{
		log.debug("countAfterPenalty = " + countAfterPenalty);
		return countAfterPenalty;
	}
	/**
	 * 30�����o�ߎc���̐ݒ���s���B<BR>
	 * @@param l_dblCount
	 * @@roseuid 40765A3B03D2
	 */
	public void setCountBeforePenalty(double l_dblCount)
	{
		log.debug("l_dblCount = " + l_dblCount);
		countBeforePenalty = l_dblCount;
	}
	/**
	 * this.30�����o�ߎc����ԋp����<BR>
	 * @@return double
	 * @@roseuid 40765AA80019
	 */
	public double getCountBeforePenalty()
	{
		log.debug("countBeforePenalty = " + countBeforePenalty);
		return countBeforePenalty;
	}
	/**
	 * �M�����Y���ۊz�̐ݒ���s���B<BR>
	 * @@param l_dblPrice
	 * @@roseuid 40765A4C0067
	 */
	public void setTrustFortunePenaltyPrice(double l_dblPrice)
	{
		log.debug("l_dblPrice = " + l_dblPrice);
		trustFortunePenaltyPrice = l_dblPrice;
	}
	/**
	 * this.�M�����Y���ۊz��ԋp����B<BR>
	 * @@return double
	 * @@roseuid 40765AB50384
	 */
	public double getTrustFortunePenaltyPrice()
	{
		log.debug("trustFortunePenaltyPrice = " + trustFortunePenaltyPrice);
		return trustFortunePenaltyPrice;
	}
	/**
	 * ���\�c���̐ݒ���s���B<BR>
	 * @@param l_dblBalance
	 * @@roseuid 40765A6200C4
	 */
	public void setSellPossibleBalance(double l_dblBalance)
	{
		log.debug("l_dblBalance = " + l_dblBalance);
		sellPossibleBalance = l_dblBalance;
	}
	/**
	 * this.���\�c����ԋp����B<BR>
	 * @@return double
	 * @@roseuid 40765AC40086
	 */
	public double getSellPossibleBalance()
	{
		log.debug("sellPossibleBalance = " + sellPossibleBalance);
		return sellPossibleBalance;
	}
	/**
	 * �ݓ��^�C�v��ݒ肷��B<BR>
	 * @@param l_ruitoType
	 * @@roseuid 407671A30190
	 */
	public void setRuitoType(RuitoTypeEnum l_ruitoType)
	{
		log.debug("l_ruitoType = " + l_ruitoType);
		ruitoType = l_ruitoType;
	}
	/**
	 * this.�ݓ��^�C�v���擾����<BR>
	 * @@return RuitoTypeEnum
	 * @@roseuid 407671C50384
	 */
	public RuitoTypeEnum getRuitoType()
	{
		log.debug("ruitoType = " + ruitoType);
		return ruitoType;
	}
	/**
	 * ����ID��ݒ肷��B<BR>
	 * @@param l_lngProductID
	 * @@roseuid 40767D9D0364
	 */
	public void setProductID(long l_lngProductID)
	{
		log.debug("l_lngProductID = " + l_lngProductID);
		productID = l_lngProductID;
	}	
	/**
	 * this.����ID���擾����<BR>
	 * @@return long
	 * @@roseuid 407671C50384
	 */
	public long getProductID()
	{
		log.debug("productID = " + productID);
		return productID;
	}
	/**
	 * ��񒍕����v��ݒ肷��B<BR>
	 * @@param l_dblSellOrderTatal
	 * @@roseuid 4079F8A90236
	 */
	public void setSellOrderTotal(double l_dblSellOrderTatal)
	{
		log.debug("l_dblSellOrderTatal = " + l_dblSellOrderTatal);
		sellOrderTotal = l_dblSellOrderTatal;
	}
	/**
	 * this.��񒍕����v��ԋp����<BR>
	 * @@return double
	 * @@roseuid 4079F8C00294
	 */
	public double getSellOrderTotal()
	{
		log.debug("sellOrderTotal = " + sellOrderTotal);
		return sellOrderTotal;
	}
	/**
	 * ��񒍕����׃I�u�W�F�N�g��ݒ肷��B<BR>
	 * @@param l_sellOrderDetail
	 * @@roseuid 407E3705029E
	 */
	public void setSellOrderDetail(WEB3RuitoSellOrderDetail[] l_sellOrderDetail)
	{
		log.debug("l_sellOrderDetail = " + l_sellOrderDetail);
		sellOrderDetail = l_sellOrderDetail;
	}
	/**
	 * this.��񒍕����׃I�u�W�F�N�g���擾����<BR>
	 * @@return webbroker3.xbruito.WEB3RuitoSellOrderDetail
	 * @@roseuid 407E36F10389
	 */
	public WEB3RuitoSellOrderDetail[] getSellOrderDetail()
	{
		log.debug("sellOrderDetail = " + sellOrderDetail);
		return sellOrderDetail;
	}
}
@
