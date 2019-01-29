head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M�����}�l�[�W���N���X(WEB3MutualFundProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ������ (���u) �V�K�쐬
Revesion History : 2004/08/23 ����� (���u) ���r���[
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/03/14 ��� (SRA) �d�l�ύX���f��No.404�Ή� 
Revesion History : 2006/06/28 �юu�� (���u) �d�l�ύX���f��No.461�Ή�  
Revesion History : 2007/01/04 �����q (���u) �d�l�ύX���f��No.517�Ή�
Revesion History : 2007/02/05 ������ (���u) �d�l�ύX���f��No.529�Ή�
Revesion History : 2007/04/09 �����F (���u) �d�l�ύX���f��No.547�Ή�
Revesion History : 2007/08/30 �����F (���u) �d�l�ύX���f��No.571�Ή�
Revesion History : 2008/04/29 ���u�� (���u) �d�l�ύX���f��No.598�Ή�
Revesion History : 2008/07/10 ���u�� (���u) �d�l�ύX���f��No.609,618�Ή�
Revesion History : 2009/05/13 ���@@�g (���u) �d�l�ύX���f��No.641�Ή�
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3FixedBuyPossibleDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SpecDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.data.MutualFund2ndProductSonarDao;
import webbroker3.mf.data.MutualFund2ndProductSonarRow;
import webbroker3.mf.data.MutualFundInstCondSonarDao;
import webbroker3.mf.data.MutualFundInstCondSonarRow;
import webbroker3.mf.data.MutualFundProductCategoryDao;
import webbroker3.mf.data.MutualFundProductCategoryPK;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �g�����M�����}�l�[�W���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualFundProductManager extends MutualFundProductManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductManager.class);

    /**
     * (�g�����M�����}�l�[�W��)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 40AD90090261
     */
    public WEB3MutualFundProductManager()
    {

    }

	/**
	 * (get���M����)<BR>
	 * �igetMutualFundProduct�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * �g�����M�������擾����B<BR>
	 * <BR>
	 * �P�j�@@���M�����I�u�W�F�N�g���擾����B<BR>
	 * �@@super.getMutualFundProduct()���R�[�����āA���M�����I�u�W�F�N�g��<BR>
	 * �@@�擾����B<BR>
	 * �@@[getMutualFundProduct�ɓn���p�����^]<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
	 * �@@�@@�񍆃R�[�h�F ����.�񍆃R�[�h<BR>
	 * <BR>
	 * �Q�j�@@�g�����M�����I�u�W�F�N�g���擾����B<BR>
	 * �@@this.to����()���R�[�����āA�g�����M�����I�u�W�F�N�g���擾����B<BR>
	 * �@@[to�����ɓn���p�����^�n<BR>
	 * �@@�@@Row�I�u�W�F�N�g�F <BR>
	 *        �擾�������M�����I�u�W�F�N�g.getDataSourceObject()�̖߂�l<BR>
	 * <BR>
	 * �R�j�@@�擾�����g�����M�����I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@param l_strProductIssueCode - �񍆃R�[�h
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD90090270
	 */
	public MutualFundProduct getMutualFundProduct(
		Institution l_institution,
		String l_strProductCode,
		String l_strProductIssueCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "String l_strProductIssueCode)";
                
		log.entering(STR_METHOD_NAME);
        
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		//�P�j�@@���M�����I�u�W�F�N�g���擾����
		MutualFundProduct l_mutualFundProduct =
			super.getMutualFundProduct(
				l_institution,
				l_strProductCode,
				l_strProductIssueCode);

		if (l_mutualFundProduct == null)
		{
			String l_strmsg =
				"�g�����M�����擾�ł��Ȃ��ꍇ�G���[ for "
					+ "institution code = "
					+ l_institution.getInstitutionCode()
					+ ", ProductCode = "
					+ l_strProductCode
					+ ", ProductIssueCode = "
					+ l_strProductIssueCode;
                    
			log.debug(l_strmsg);
			log.exiting(STR_METHOD_NAME);
			throw new NotFoundException(l_strmsg);
		}

		//�Q�j�@@�g�����M�����I�u�W�F�N�g���擾����
		WEB3MutualFundProduct l_web3MutualFundProduct =
			(WEB3MutualFundProduct) this.toProduct(
				(MutualFundProductRow) l_mutualFundProduct.getDataSourceObject());

		//�R�j�@@�擾�����g�����M�����I�u�W�F�N�g��Ԃ�
		log.exiting(STR_METHOD_NAME);
		return l_web3MutualFundProduct;
	}

	/**
	 * (get���M�������)<BR>
	 * �igetMutualFundTradedProduct�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * �g�����M����������擾����B<BR>
	 * <BR>
	 * �P�j�@@���M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@super.getMutualFundTradedProduct()���R�[�����āA<BR>
	 * ���M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@[getMutualFundTradedProduct�ɓn���p�����^]<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
	 * �@@�@@�񍆃R�[�h�F ����.�񍆃R�[�h<BR>
	 * �@@�@@�s��R�[�h�F ����.�s��R�[�h<BR>
	 * <BR>
	 * �Q�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@this.to�������()���R�[�����āA�g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@[to��������ɓn���p�����^�n<BR>
	 * �@@�@@Row�I�u�W�F�N�g�F <BR>
	 *       �擾�������M��������I�u�W�F�N.getDataSourceObject()�̖߂�l<BR>
	 * <BR>
	 * �R�j�@@�擾�����g�����M��������I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@param l_strProductIssueCode - �񍆃R�[�h
	 * @@param l_strMarketCode - �s��R�[�h
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD90090280
	 */
	public MutualFundTradedProduct getMutualFundTradedProduct(
		Institution l_institution,
		String l_strProductCode,
		String l_strProductIssueCode,
		String l_strMarketCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "String l_strProductIssueCode,"
				+ "String l_strMarketCode)";
		log.entering(STR_METHOD_NAME);

		//�P�j�@@���M��������I�u�W�F�N�g���擾����
		MutualFundTradedProduct l_mutualFundTradedProduct =
			super.getMutualFundTradedProduct(
				l_institution,
				l_strProductCode,
				l_strProductIssueCode,
				l_strMarketCode);

		if (l_mutualFundTradedProduct == null)
		{
			String l_strmsg =
				"�g�����M��������擾�ł��Ȃ��ꍇ�G���[ for "
					+ "l_institution = "
					+ l_institution
					+ ", l_strProductCode = "
					+ l_strProductCode
					+ ", l_strProductIssueCode = "
					+ l_strProductIssueCode
					+ ", l_strMarketCode = "
					+ l_strMarketCode;
			log.debug(l_strmsg);
			log.exiting(STR_METHOD_NAME);
			throw new NotFoundException(l_strmsg);
		}

		//�Q�j�@@�g�����M��������I�u�W�F�N�g���擾����
		WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
			(WEB3MutualFundTradedProduct) this.toTradedProduct(
				(MutualFundTradedProductRow) l_mutualFundTradedProduct.getDataSourceObject());

		log.exiting(STR_METHOD_NAME);

		//�R�j�@@�擾�����g�����M��������I�u�W�F�N�g��Ԃ�
		return l_web3MutualFundTradedProduct;
	}

	/**
	 * (to����)<BR>
	 * �itoProduct�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * �w���Row�I�u�W�F�N�g����g�����M�����I�u�W�F�N�g���쐬���ĕԂ��B<BR>
	 * <BR>
	 * �P�j�@@����.Row�I�u�W�F�N�g��ProductRow�N���X�̃C���X�^���X�łȂ��A<BR>
	 * �@@����MutualFundProductRow�N���X�̃C���X�^���X�łȂ��ꍇ�́A<BR>
	 * �@@IllegalArgumentException���X���[����<BR>
	 * <BR>
	 * �Q�j�@@�g�����M�����I�u�W�F�N�g�𐶐�����<BR>
	 * �@@�|����.Row�I�u�W�F�N�g��ProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
	 * �@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
	 * �@@�@@�@@����Row�F ����.Row�I�u�W�F�N�g��ProductRow�N���X�ɃL���X�g��������<BR>
	 * �@@�|����.Row�I�u�W�F�N�g��MutualFundProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
	 * �@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
	 * �@@�@@�@@���M����Row�F ����.Row�I�u�W�F�N�g��MutualFundProductRow�N���X�ɃL���X�g��������<BR>
	 * <BR>
	 * �R�j�@@���������g�����M�����I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * @@param l_rowObject - (Row�I�u�W�F�N�g)<BR>
	 * ����Row�I�u�W�F�N�g<BR>
	 * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Product
	 * @@roseuid 40AD90090290
	 */
	public Product toProduct(Row l_rowObject)
	{
		final String STR_METHOD_NAME = "toProduct(Row l_rowObject)";
		log.entering(STR_METHOD_NAME);

		if (l_rowObject == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		WEB3MutualFundProduct l_web3MutualFundProduct = null;
		try
		{
			//�P�j�@@����.Row�I�u�W�F�N�g��ProductRow�N���X�̃C���X�^���X�łȂ��A
			//�@@����MutualFundProductRow�N���X�̃C���X�^���X�łȂ��ꍇ
			if (!(l_rowObject instanceof ProductRow)
				&& !(l_rowObject instanceof MutualFundProductRow))
			{
				log.debug("__an IllegalArgumentException__");
				log.exiting(STR_METHOD_NAME);
				String l_strmsg = this.getClass().getName() + "." + STR_METHOD_NAME 
						+ ": The expect type is ProductRow or MutualFundProductRow,"
						+ " but in fact it is " + l_rowObject.getClass();
				throw new IllegalArgumentException(l_strmsg);
			}
            
			//�Q�j�@@����.Row��ProductRow�N���X�̃C���X�^���X�ł���ꍇ
			if (l_rowObject instanceof ProductRow)
			{
				l_web3MutualFundProduct =
					new WEB3MutualFundProduct((ProductRow) l_rowObject);
			}
            
			//�Q�j����.Row��MutualFundProductRow�N���X�̃C���X�^���X�ł���ꍇ
			if (l_rowObject instanceof MutualFundProductRow)
			{
				l_web3MutualFundProduct =
					new WEB3MutualFundProduct(
						(MutualFundProductRow) l_rowObject);
			}
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when new WEB3MutualFundProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when new WEB3MutualFundProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//�R�j�@@���������g�����M�����I�u�W�F�N�g��Ԃ�
		return l_web3MutualFundProduct;
	}

	/**
	 * (to�������)<BR>
	 * �itoTradedProduct�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * �w���Row�I�u�W�F�N�g����g�����M��������I�u�W�F�N�g���쐬���ĕԂ��B<BR>
	 * <BR>
	 * �P�j�@@����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�łȂ��A<BR>
	 * �@@����MutualFundTradedProductRow�N���X�̃C���X�^���X�łȂ��ꍇ�́A<BR>
	 * �@@IllegalArgumentException���X���[����<BR>
	 * <BR>
	 * �Q�j�@@�g�����M��������I�u�W�F�N�g�𐶐�����<BR>
	 * �@@�|����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
	 * �@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
	 * �@@�@@�@@�������Row�F ����.Row�I�u�W�F�N�g��TradedProductRow�N���X�ɃL���X�g��������<BR>
	 * �@@�|����.Row�I�u�W�F�N�g��MutualFundTradedProductRow�N���X�̃C���X�^���X�̏ꍇ<BR>
	 * �@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
	 * �@@�@@�@@���M�������Row�F <BR>
	 * ����.Row�I�u�W�F�N�g��MutualFundTradedProductRow�N���X�ɃL���X�g��������<BR>
	 * <BR>
	 * �R�j�@@���������g�����M��������I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * @@param l_rowObject - �������Row�I�u�W�F�N�g
	 * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
	 * @@roseuid 40AD9009029F
	 */
	public TradedProduct toTradedProduct(Row l_rowObject)
	{
		final String STR_METHOD_NAME = "toTradedProduct(Row l_rowObject)";
		log.entering(STR_METHOD_NAME);

		if (l_rowObject == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct = null;
		try
		{
			//�P�j�@@����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�łȂ��A
			// �@@����MutualFundTradedProductRow�N���X�̃C���X�^���X�łȂ��ꍇ�́A
			if (!(l_rowObject instanceof TradedProductRow)
				&& !(l_rowObject instanceof MutualFundTradedProductRow))
			{
				log.debug("__an IllegalArgumentException__");
				log.exiting(STR_METHOD_NAME);
				String l_strmsg = this.getClass().getName() + "." + STR_METHOD_NAME 
						+ ": The expect type is TradedProductRow or"
						+ " MutualFundTradedProductRow,"
						+ " but in fact it is " + l_rowObject.getClass();
				throw new IllegalArgumentException(l_strmsg);
			}

			//�Q�j�@@����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�ł���ꍇ
			if (l_rowObject instanceof TradedProductRow)
			{
				l_web3MutualFundTradedProduct =
					new WEB3MutualFundTradedProduct(
						(TradedProductRow) l_rowObject);
			}

			//�Q�j�@@����.Row�I�u�W�F�N�g��TradedProductRow�N���X�̃C���X�^���X�ł���ꍇ
			if (l_rowObject instanceof MutualFundTradedProductRow)
			{
				l_web3MutualFundTradedProduct =
					new WEB3MutualFundTradedProduct(
						(MutualFundTradedProductRow) l_rowObject);
			}

		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when new WEB3MutualFundTradedProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when new WEB3MutualFundTradedProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//�R�j�@@���������g�����M��������I�u�W�F�N�g��Ԃ�
		return l_web3MutualFundTradedProduct;
	}

	/**
	 * (get���M����)<BR>
	 * �igetMutualFundProduct�̃I�[�o�[���[�h�j<BR>
	 * <BR>
	 * �g�����M�������擾����B<BR>
	 * <BR>
	 * �P�j�@@this.get�g�����M����()���R�[�����A���̖߂�l��Ԃ��B<BR>
	 * �@@�mget�g�����M�����ɓn���p�����^�n<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ���������R�[�h<BR>
	 * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
	 * <BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD9019003E
	 */
	public MutualFundProduct getMutualFundProduct(
		Institution l_institution,
		String l_strProductCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        
		//�P�j�@@this.get�g�����M����()���R�[�����A���̖߂�l��Ԃ�
		MutualFundProduct l_mutualFundProduct =
			this.getMutualFundProduct(l_institution, l_strProductCode, "0");
		log.exiting(STR_METHOD_NAME);
		return l_mutualFundProduct;
	}

	/**
	 * (get���M�������)<BR>
	 * �igetMutualFundTradedProduct�̃I�[�o�[���[�h�j<BR>
	 * <BR>
	 * �g�����M����������擾����B<BR>
	 * <BR>
	 * �P�j�@@this.get�g�����M�������()���R�[�����A���̖߂�l��Ԃ��B<BR>
	 * �@@�mget�g�����M��������ɓn���p�����^�n<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ���������R�[�h<BR>
	 * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
	 * �@@�@@�s��R�[�h�F �h0�FDEFAULT�h<BR>
	 * <BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD9019005D
	 */
	public MutualFundTradedProduct getMutualFundTradedProduct(
		Institution l_institution,
		String l_strProductCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
       
		//�P�j�@@this.get�g�����M�������()���R�[�����A���̖߂�l��Ԃ�
		MutualFundTradedProduct l_mutualFundTradedProduct =
			this.getMutualFundTradedProduct(
				l_institution,
				l_strProductCode,
				"0",
				WEB3MarketCodeDef.DEFAULT);
		log.exiting(STR_METHOD_NAME);                
		return l_mutualFundTradedProduct;
	}

	/**
	 * (get���M�����J�e�S���[���X�g)<BR>
	 * ���M�����J�e�S���[Params�̃��X�g��Ԃ��B<BR>
	 * <BR>
	 * �P�j�@@���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params<BR>
	 * �@@�@@�I�u�W�F�N�g��List���擾���ĕԂ��B<BR>
	 * �@@�@@�m���������n<BR>
	 * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
	 * �@@�@@�m�\�[�g�����n<BR>
	 * �@@�@@�@@�e�J�e�S���[�R�[�h nulls first<BR>
	 * �@@�@@�@@�J�e�S���[�R�[�h<BR>
	 * <BR>
	 * �@@�|�擾�������M�����J�e�S���[Params�I�u�W�F�N�g��List��Ԃ��B<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - �،���ЃR�[�h
	 * @@return List
	 * @@roseuid 40ADB77E0203
	 */
	public List getMutualFundProductCategoryList(String l_strInstitutionCode) 
			throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProductCategoryList(String l_strInstitutionCode)";
		log.entering(STR_METHOD_NAME);

		if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		List l_rtnList = new Vector();

		//�����̏���
		String l_whereClause = " institution_code = ? ";
		String l_strSortCond =
			" parent_category_code nulls first , category_code ";
		Object l_bindVars[] = { l_strInstitutionCode };

		try
		{
			//�P�j�@@���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params��List���擾��
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_rtnList =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE,
					l_whereClause,
					l_strSortCond,
					null,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���  when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//�擾�������M�����J�e�S���[Params�I�u�W�F�N�g��List��Ԃ�
		return l_rtnList;
	}

	/**
	 * (get���ʓ��M�����J�e�S���[���X�g)<BR>
	 * �w�肳�ꂽ�J�e�S���[�R�[�h�ɕR�t�����ʃJ�e�S���[��<BR>
	 * ���M�����J�e�S���[Params�̃��X�g��Ԃ��B<BR>
	 * <BR>
	 * �P�j�@@���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params<BR>
	 * �@@�@@�I�u�W�F�N�g��List���擾���ĕԂ��B<BR>
	 * <BR>
	 * �@@�@@�m��������������n<BR>
	 * �@@�@@�@@ "�J�e�S���[�R�[�h != ?<BR>
	 * �@@�@@�@@�@@connect by prior �J�e�S���[�R�[�h = �e�J�e�S���[�R�[�h<BR>
	 * �@@�@@�@@�@@and �،���ЃR�[�h = ?<BR>
	 * �@@�@@�@@�@@start with �J�e�S���[�R�[�h = ? and �،���ЃR�[�h = ?"<BR>
	 * <BR>
	 * �@@�@@�m���������f�[�^�R���e�i�n<BR>
	 * �@@�@@�@@����.�J�e�S���[�R�[�h<BR>
	 * �@@�@@�@@����.�،���ЃR�[�h<BR>
	 * �@@�@@�@@����.�J�e�S���[�R�[�h<BR>
	 * �@@�@@�@@����.�،���ЃR�[�h<BR>
	 * <BR>
	 * �@@�@@�m�\�[�g�����n<BR>
	 * �@@�@@�@@�e�J�e�S���[�R�[�h nulls first<BR>
	 * �@@�@@�@@�J�e�S���[�R�[�h<BR>
	 * <BR>
	 * �@@�|�擾�������M�����J�e�S���[Params�I�u�W�F�N�g��List��Ԃ��B<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - �،���ЃR�[�h
	 * @@param l_strCategoryCode - �����J�e�S���[�R�[�h
	 * @@return List
	 * @@roseuid 40DBCE500193
	 */
	public List getLowMutualFundProductCategoryList(
		String l_strInstitutionCode,
		String l_strCategoryCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getLowMutualFundProductCategoryList("
				+ "String l_strInstitutionCode,"
				+ "String l_strCategoryCode)";
		log.entering(STR_METHOD_NAME);

		List l_rtnList = new Vector();

		// �����̏���
		//�m��������������n
		String l_whereClause = " category_code != ? ";
		String l_strConditions = " connect by prior "
				+ " category_code = parent_category_code "
				+ " and institution_code = ? "
				+ " start with category_code = ? and institution_code = ? ";

		//�m�\�[�g�����n
		String l_strSortCond =
			" order by parent_category_code nulls first , category_code ";

		//�m���������f�[�^�R���e�i�n
		Object l_bindVars[] =
			{
				l_strCategoryCode,
				l_strInstitutionCode,
				l_strCategoryCode,
				l_strInstitutionCode };

		try
		{
			//�P�j�@@���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[ParamsList���擾���ĕԂ�            
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

			l_rtnList =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE,
					l_whereClause,
					null,
					l_strConditions + l_strSortCond,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}
		//�擾�������M�����J�e�S���[Params�I�u�W�F�N�g��List��Ԃ�        
		return l_rtnList;
	}
    
	/**
     * (get���M�����J�e�S���[)<BR>
	 * �w�肳�ꂽ���M�����J�e�S���[���擾���A�ԋp����B<BR> 
	 * �P�j�ȉ��̏����ŁA���M�����J�e�S���[�e�[�u������������B<BR> 
	 *[��������] <BR>
�@@	 * �،���ЃR�[�h=����.�،���ЃR�[�h and <BR>
�@@	 * �J�e�S���[�R�[�h=����.���M�����J�e�S���[�R�[�h <BR>
	 *<BR>
	 *�Q�j��������==0���̏ꍇ�Anull��ԋp����B<BR> 
	 *<BR>
	 *�R�j��������==1���̏ꍇ�A���M�����J�e�S���[�̃R���X�g���N�^���R�[�����A<BR> 
�@@	 *  �����������M�����J�e�S���[�I�u�W�F�N�g��ԋp����B<BR> 
	 * [����] <BR>
	 *  ���M�����J�e�S���[�s=�������ʂł��铊�M�����J�e�S���[Params�I�u�W�F�N�g<BR>
	 * 
	 * @@param l_strInstitutionCode - �،���ЃR�[�h
	 * @@param l_strCategoryCode - ���M�����J�e�S���[�R�[�h
	 * @@throws WEB3BaseException
	 * @@roseuid 40DBCE500193
	 */   
	public WEB3MutualProductCategory getMutualFundProductCategory(
		String l_strInstitutionCode, 
		String l_strCategoryCode) throws WEB3BaseException
	{   
		final String STR_METHOD_NAME =
			"getMutualFundProductCategory(String l_strInstitutionCode, " +
			"String l_strCategoryCode)";
		log.entering(STR_METHOD_NAME);

		//�m�\�[�g�����n
		String l_strWhere = "institution_code = ? and category_code = ?";
		//�m���������f�[�^�R���e�i�n
		Object[] l_bindVars = {l_strInstitutionCode, l_strCategoryCode};
        
		//�P�j�ȉ��̏����ŁA���M�����J�e�S���[�e�[�u������������B 
		//  [��������] 
		//�@@ �،���ЃR�[�h=����.�،���ЃR�[�h and 
		//�@@ �J�e�S���[�R�[�h=����.���M�����J�e�S���[�R�[�h 
		List l_lisMFProductCategoryRows = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisMFProductCategoryRows =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE, 
					l_strWhere, 
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
        
		int l_intSize = l_lisMFProductCategoryRows.size();
        
		//�Q�j��������==0���̏ꍇ�Anull��ԋp����B 
		if (l_intSize == 0)
		{
			log.exiting(STR_METHOD_NAME);
			return null;
		}
        
		//�R�j��������==1���̏ꍇ�A���M�����J�e�S���[�̃R���X�g���N�^���R�[�����A 
		//  �����������M�����J�e�S���[�I�u�W�F�N�g��ԋp����B 
		//  [����] 
		//�@@���M�����J�e�S���[�s=�������ʂł��铊�M�����J�e�S���[Params�I�u�W�F�N�g
		WEB3MutualProductCategory l_mutualProductCategory  = null;
		if (l_intSize == 1)
		{
			MutualFundProductCategoryRow 
				l_mutualFundProductCategoryRow  = 
					(MutualFundProductCategoryRow) l_lisMFProductCategoryRows.get(0);
			l_mutualProductCategory = 
				new WEB3MutualProductCategory(l_mutualFundProductCategoryRow);
		}
		log.exiting(STR_METHOD_NAME);
		return l_mutualProductCategory;
	} 
    
	/**
	 * (validate���M�����J�e�S���[�K�w)<BR>
	 * �w�肳�ꂽ�e�J�e�S���[�R�[�h�̊K�w���A<BR>
	 * �R�K�w���[���Ȃ��Ă��Ȃ����`�F�b�N����B<BR>
	 *�i�w�肳�ꂽ�e�J�e�S���[���R�K�w�i���j�ł������ꍇ�A��O���X���[����B�j<BR> 
	 * �V�[�P���X�}�u�i���M�jvalidate���M�����J�e�S���[�K�w�v�Q��<BR>
	 * 
	 * @@param l_strInstitutionCode - �،���ЃR�[�h
	 * @@param l_strCategoryCode - ���M�����J�e�S���[�R�[�h
	 * @@param l_strParentCategoryCode - �e�J�e�S���[�R�[�h
	 * @@throws WEB3BaseException
	 * @@roseuid 40DBCE500193
	 */ 
	public void validateProductCategorystorey(
		String l_strInstitutionCode, 
		String l_strCategoryCode,
		String l_strParentCategoryCode) throws WEB3BaseException 
	{
		final String STR_METHOD_NAME =
			"validateProductCategorystorey(String l_strInstitutionCode, " +
			"String l_strCategoryCode) ";
		log.entering(STR_METHOD_NAME);

		//1.1)get���M�����J�e�S���[(String, String)
		//����.���M�����J�e�S���[�̓��M�����J�e�S���[�I�u�W�F�N�g���擾����B 
		//[get���M�����J�e�S���[�ɓn������] 
		//  �،���ЃR�[�h=����.�،���ЃR�[�h 
		// ���M�����J�e�S���[�R�[�h=����.�e�J�e�S���[�R�[�h
		WEB3MutualProductCategory 
			l_mutualProductCategory1 =
				this.getMutualFundProductCategory(
					l_strInstitutionCode, 
					l_strParentCategoryCode);
        
		boolean l_blnFlag = false;
		if (l_mutualProductCategory1 != null)
		{
			//1.1.1)get�e�J�e�S���[�R�[�h( )
			String l_strParentCategoryCode1 = 
				l_mutualProductCategory1.getParentCategoryCode();
				
			if (l_strParentCategoryCode1 != null)
			{
                
		  		//1.2)get���M�����J�e�S���[(String, String)
		  		//����.���M�����J�e�S���[�̐e�J�e�S���[�ƂȂ铊�M�����J�e�S���[�I�u�W�F�N�g���擾����B 
		  		//[get���M�����J�e�S���[�ɓn������] 
		  		//  �،���ЃR�[�h=����.�،���ЃR�[�h 
		  		//  ���M�����J�e�S���[�R�[�h=�擾�����e�J�e�S���[�R�[�h
		  		WEB3MutualProductCategory 
			  		l_mutualProductCategory2 =
				  		this.getMutualFundProductCategory(
					  		l_strInstitutionCode, 
					  		l_strParentCategoryCode1);

		  		if (l_mutualProductCategory2 != null)
		  		{
			  		//1.2.1)get�e�J�e�S���[�R�[�h( )
			  		String l_strParentCategoryCode2 = 
				  		l_mutualProductCategory2.getParentCategoryCode();
			  		//�ő�K�w�I�[�o�[�G���[
            
					l_blnFlag = true;     
			  		//1.3)get�e�J�e�S���[�R�[�h( )�̖߂�l!=null�̏ꍇ
			  		if (l_strParentCategoryCode2 != null)
			  		{
						log.debug("�ő�K�w�I�[�o�[�G���[�B");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01293,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"�ő�K�w�I�[�o�[�G���[�B");
					}
			    }            
		    }            
	    }
        
	   if (l_strCategoryCode != null)
		{		
			//1.4)get���ʓ��M�����J�e�S���[���X�g(String, String)
			//  �w�肳�ꂽ�J�e�S���[�R�[�h���u�e�J�e�S���[�R�[�h�v�Ɏ����M�����J�e�S���[�̈ꗗ�����o����B 
			//  [get���ʓ��M�����J�e�S���[���X�g�ɓn������] 
			//      �،���ЃR�[�h=����.�،���ЃR�[�h 
			//      �J�e�S���[�R�[�h=����.�J�e�S���[�R�[�h
			List l_lisLowMutualFundProductList =
				this.getLowMutualFundProductCategoryList(
					l_strInstitutionCode, 
					l_strCategoryCode);
        
			//1.5)��get���ʓ��M�����J�e�S���[���X�g( )�̖߂�l��!=null�ł���A��������0���̏ꍇ
			if (l_lisLowMutualFundProductList != null && 
				!l_lisLowMutualFundProductList.isEmpty())
			{
				//1.5.1)��ʊK�w�̃`�F�b�N�ŁA�`�F�b�N�Q��ڂ����{���Ă���ꍇ
				if (l_blnFlag)
				{
					log.debug("�ő�K�w�I�[�o�[�G���[�B");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01293,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"�ő�K�w�I�[�o�[�G���[�B");
				}
        
				//1.5.2)�J��Ԃ�����
				MutualFundProductCategoryParams l_mutualFundProductCategoryParams = null;
				for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
				{
					//1.5.2.1)get���ʓ��M�����J�e�S���[���X�g(String, String)     
					//�u�e�J�e�S���[�R�[�h�v�Ɏ����M�����J�e�S���[�̈ꗗ�����o����B 
					//[get���ʓ��M�����J�e�S���[���X�g�ɓn������] 
					//�،���ЃR�[�h=����.�،���ЃR�[�h 
					//�J�e�S���[�R�[�h=<�ꎟ����>�Ŏ擾�����������ʂ� 
					//���M�����J�e�S���[Params�I�u�W�F�N�g.get�J�e�S���[�R�[�h( )
					l_mutualFundProductCategoryParams =
						(MutualFundProductCategoryParams) l_lisLowMutualFundProductList.get(i);
                
					List l_lisLowMutualFundProductCategory2 =
						this.getLowMutualFundProductCategoryList(
							l_strInstitutionCode,
							l_mutualFundProductCategoryParams.getCategoryCode());
        
					//1.5.2.2��get���ʓ��M�����J�e�S���[���X�g( )�̖߂�l��!=null�ł���A��������0���̏ꍇ
					if (l_lisLowMutualFundProductCategory2 != null && 
						!l_lisLowMutualFundProductCategory2.isEmpty())
					{
						log.debug("�ő�K�w�I�[�o�[�G���[�B");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01293,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"�ő�K�w�I�[�o�[�G���[�B");
					}
				}
			}
		}
		log.exiting(STR_METHOD_NAME);
	}
    
	/**
	 * (create���M�����J�e�S���[�ꗗ)<BR>
	 * ���M�����J�e�S���[�e�[�u���̌������ʂ��烌�X�|���X�Ɉ����n���ׁA<BR>
	 * �c���[�\���̔z����쐬����B<BR>
	 * <BR>
	 * �P)�@@����:�����J�e�S���[�ꗗ�̑S�Ă̗v�f���A<BR>
	 * �����\�b�h�̖߂�l�i�ȉ��A"�߂�l�I�u�W�F�N�g"�ƕ\�L)<BR>
	 * �@@�ƂȂ�I�u�W�F�N�g�Ɉڂ��ς���܂ňȉ����J��Ԃ��B<BR>
	 * �@@�P�|�P)�@@���M�����J�e�S���[�R�[�h���̃I�u�W�F�N�g�𐶐�����B<BR>
	 * �@@�@@�@@�@@[����]<BR>
	 * �@@�@@�@@�@@�@@�����J�e�S���[Params.get�J�e�S���[�R�[�h( )<BR>
	 * �@@�@@�@@�@@�@@�����J�e�S���[Params.get�J�e�S���[����( )<BR>
	 * <BR>
	 * �@@�P�|�Q)�@@�����J�e�S���[Params.get�e�J�e�S���[�R�[�h( )���R�[�����āA<BR>
	 * �e�J�e�S���[�R�[�h���擾����B<BR>
	 * �@@�@@�P�|�Q�|�P)�@@�e�J�e�S���[�R�[�h��null�̏ꍇ<BR>
	 * �@@�@@�@@�@@�@@�@@�@@�߂�l�I�u�W�F�N�g�g�ɂ��̂܂ܒǉ�����B<BR>
	 * <BR>
	 * �@@�@@�P�|�Q�|�Q)�@@�P�|�Q)�̖߂�l��null�ȊO�̏ꍇ<BR>
	 * �@@�@@�@@�P�|�Q�|�Q�|�P)�@@�߂�l�I�u�W�F�N�g�̒�����A<BR>
	 * �e�J�e�S���[�R�[�h�Ɠ����J�e�S���[�R�[�h�����I�u�W�F�N�g�����݂���ꍇ<BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���̃I�u�W�F�N�g.����J�e�S���[�R�[�h���̂ɒǉ�����B<BR>
	 * (���ɑ��݂���ꍇ�́A�ǉ����Ȃ�)<BR>
	 * <BR>
	 * �@@�@@�@@�P�|�Q�|�Q�|�Q)�@@�߂�l�I�u�W�F�N�g�̒�����A<BR>
	 * �e�J�e�S���[�R�[�h�Ɠ����J�e�S���[�R�[�h�����I�u�W�F�N�g�����݂��Ȃ��ꍇ<BR>
	 * �@@�@@�@@�@@�P�|�Q�|�Q�|�Q�|�P)�@@����:�����J�e�S���[�ꗗ����<BR>
	 * �e�J�e�S���[�R�[�h�Ɠ����J�e�S���[�R�[�h�����I�u�W�F�N�g��T���o���A<BR>
	 * ���̃I�u�W�F�N�g��߂�l�I�u�W�F�N�g�ɒǉ�����B<BR>
	 * <BR>
	 * �Q)�@@�쐬�����߂�l�I�u�W�F�N�g�����^�[������B<BR>
	 * <BR>
	 * @@param l_lisProductCategoryList - �����J�e�S���[�ꗗ
	 * @@return WEB3MutualProductCategoryUnit[ ]
	 * @@roseuid 40E4E09602B1
	 */
	public WEB3MutualProductCategoryUnit[] createMutualFundProductCategoryList(List l_lisProductCategoryList)
	{
		final String STR_METHOD_NAME =
			"createMutualFundProductCategoryList(List l_lisProductCategoryList)";
		log.entering(STR_METHOD_NAME);

		WEB3MutualProductCategoryUnit[] l_arrayWEB3MFProductCategoryUnit = null;
        
		int l_listLength = l_lisProductCategoryList.size();
		List l_lisMutualProductCategoryUnit = new Vector();
		Map l_mapIndex = new Hashtable();
		Map l_mapRelation = new Hashtable();

		for (int i = 0; i < l_listLength; i++)
		{
			log.debug(" i = " + i);
			// �����J�e�S���[Params���擾����
			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			// �P�|�P)�@@���M�����J�e�S���[�R�[�h���̃I�u�W�F�N�g�𐶐�����        
			WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
				new WEB3MutualProductCategoryUnit();

			// �����J�e�S���[Params.get�J�e�S���[�R�[�h()
			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();
			l_web3MutualProductCategoryUnit.categoryCode = l_strCategoryCode;

			// �����J�e�S���[Params.get�J�e�S���[����()
			l_web3MutualProductCategoryUnit.categoryName =
				l_productCategoryParams.getCategoryName();

			l_mapIndex.put(l_strCategoryCode, l_web3MutualProductCategoryUnit);
			log.debug(" Node_" + i + "= " + l_strCategoryCode);            
			l_mapRelation.put(l_strCategoryCode, new Vector());
		}

		for (int i = 0; i < l_listLength; i++)
		{

			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();
			String l_strParentCode = l_productCategoryParams.getParentCategoryCode();
            
			log.debug(" Node_" + i + ".l_strCategoryCode= " + l_strCategoryCode);
			log.debug(" Node_" + i + ".l_strParentCode= " + l_strParentCode);

			//�@@���M�����J�e�S���[�R�[�h���̃I�u�W�F�N�g�𐶐�����        
			WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
				(WEB3MutualProductCategoryUnit)l_mapIndex.get(l_strCategoryCode);
                
			if (l_strParentCode != null && l_mapRelation.containsKey(l_strParentCode))
			{
				((List)l_mapRelation.get(l_strParentCode)).add(l_web3MutualProductCategoryUnit);
                
				log.debug(" Node_" + l_strParentCode + ".child = " + l_productCategoryParams.category_code);
			}
			else
			{
				l_lisMutualProductCategoryUnit.add(l_web3MutualProductCategoryUnit);
				log.debug(" ParentNode " + l_productCategoryParams.category_code);
			}
		}

		int l_intLisCategoryUnitLength = l_lisMutualProductCategoryUnit.size();
		l_arrayWEB3MFProductCategoryUnit = new WEB3MutualProductCategoryUnit[l_intLisCategoryUnitLength];
		l_lisMutualProductCategoryUnit.toArray(l_arrayWEB3MFProductCategoryUnit);

		for (int i = 0; i < l_listLength; i++)
		{
			// �����J�e�S���[Params���擾����
			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();

			List childList = (List) l_mapRelation.get(l_strCategoryCode);

			if (childList != null && childList.size() != 0)
			{
				WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
					(WEB3MutualProductCategoryUnit) l_mapIndex.get(l_strCategoryCode);

				l_web3MutualProductCategoryUnit.childCategory =
					new WEB3MutualProductCategoryUnit[childList.size()];

				childList.toArray(
					l_web3MutualProductCategoryUnit.childCategory);
				log.debug(" ParentNode_" + l_web3MutualProductCategoryUnit.categoryCode 
							 + ".ChildenLength = " 
							 + l_web3MutualProductCategoryUnit.childCategory.length);
			}
		}

		log.exiting(STR_METHOD_NAME);
		return l_arrayWEB3MFProductCategoryUnit;
	}
    
	/**
	 * (get���M�������X�g)<BR>
	 * ���M����Params�̃��X�g��Ԃ��B<BR>
	 * <BR>
	 * �P)�@@���������␳<BR>
	 * �@@�P�|�P)�@@����:��������������null�ȊO�̏ꍇ�A<BR>
	 * �擪��" and "��ǉ�����B<BR>
	 * <BR>
	 * �@@�P�|�Q)�@@����:��������������̐擪�Ɉȉ���ǉ�����B<BR>
	 * �@@�@@�@@�@@"�،���ЃR�[�h=?"<BR>
	 * �@@�@@�@@�@@�i����:��������������null�Ŗ����ꍇ�A<BR>
	 * �ǉ����镶����̌��" and "��t����j<BR>
	 * <BR>
	 * �@@�P�|�R)�@@����:���������f�[�^�R���e�i�̐擪�Ɉȉ���ǉ�����B<BR>
	 * �@@�@@�@@�@@����:�،���ЃR�[�h<BR>
	 * <BR>
	 * �Q�j�@@���M�����}�X�^�e�[�u�����������A<BR>
	 * ���M����Params�I�u�W�F�N�g��List���擾���ĕԂ��B<BR>
	 * �@@�@@�m���������n<BR>
	 * �@@�@@�@@�P�|�Q)�ō쐬������������������<BR>
	 * �@@�@@�@@�P�|�R)�ō쐬�������������f�[�^�R���e�i<BR>
	 * �@@�@@ [���ёւ��n<BR>
	 * �E����.�\�[�g�����敪���h���t�ꗗ�Ɖ�h�̏ꍇ  <BR>
	 * �|�\�����ʂŏ����A�����R�[�h�ŏ���  <BR>
	 * �E����.�\�[�g�����敪���h�Ǘ��ғ��M���������o�^�Ɖ�h�̏ꍇ  <BR>
	 * �|�����R�[�h�ŏ���  <BR>
	 * <BR>
	 * �@@�|�擾�������M����Params�I�u�W�F�N�g��List��Ԃ��B<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - �،���ЃR�[�h
	 * @@param l_strSearchCondCharacterString - ��������������
	 * @@param l_strSearchCondDataContainer - ���������f�[�^�R���e�i
	 * @@param l_strSearchCondDef - �\�[�g�����敪
	 * @@return List
	 * @@roseuid 40ADB8A601E4
	 */
	public List getMutualFundProductList(
		String l_strInstitutionCode,
		String l_strSearchCondCharacterString,
		String[] l_strSearchCondDataContainer,
		String l_strSearchCondDef) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProductList("
				+ "String l_strInstitutionCode,"
				+ "String l_strSearchCondCharacterString,"
				+ "String[] l_strSearchCondDataContainer)";
		log.entering(STR_METHOD_NAME);

		if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		List l_rtnList = null;

		//�P)�@@���������␳
		//�P�|�P)�@@����:��������������null�ȊO�̏ꍇ
		if (l_strSearchCondCharacterString != null
			&& !"".equals(l_strSearchCondCharacterString))
		{
			l_strSearchCondCharacterString =
				" and " + l_strSearchCondCharacterString;
		}

		//�P�|�Q)�@@����:��������������̐擪�Ɉȉ���ǉ�����
		if(l_strSearchCondCharacterString != null)
		{
			l_strSearchCondCharacterString =
				" institution_code = ? " + l_strSearchCondCharacterString;
		}
		else
		{
			l_strSearchCondCharacterString =
				" institution_code = ? ";
		}

		//�P�|�R)�@@����:���������f�[�^�R���e�i�̐擪�Ɉȉ���ǉ�����
		String[] l_bindVars = null;

		if (l_strSearchCondDataContainer != null
			&& l_strSearchCondDataContainer.length != 0)
		{
			l_bindVars =
				new String[l_strSearchCondDataContainer.length + 1];
			l_bindVars[0] = l_strInstitutionCode;
            
			for (int i = 1; i < l_strSearchCondDataContainer.length + 1; i++)
			{
				l_bindVars[i] = l_strSearchCondDataContainer[i - 1];
			}
		}
		else
		{
			l_bindVars =
				new String[1];
			l_bindVars[0] = l_strInstitutionCode;
		}

		//[���ёւ��n
		String l_strSort = " indication_ranking asc";
		if(WEBMFSortConditionDivDef.MUTUAL_BUY_LIST.equals(l_strSearchCondDef))
		{
			// �E����.�\�[�g�����敪���h���t�ꗗ�Ɖ�h�̏ꍇ 
			// �|�\�����ʂŏ����A�����R�[�h�ŏ���  
			l_strSort = " indication_ranking asc, product_code asc";
		}
		else
		{
			if(WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF.equals(
					l_strSearchCondDef))
			{
				// �E����.�\�[�g�����敪���h�Ǘ��ғ��M���������o�^�Ɖ�h�̏ꍇ 
				// �|�����R�[�h�ŏ���  
				l_strSort = " product_code asc ";
			}
		}

		// Test log
		log.debug("���M�����}�X�^�e�[�u���������� l_strSearchCondCharacterString = " + l_strSearchCondCharacterString);
		for (int i = 0; i < l_bindVars.length; i ++)
		{
			log.debug("���M�����}�X�^�e�[�u���������� l_bindVars " + i + "  = " + l_bindVars[i]);
		}

		try
		{
			//�Q�j�@@���M�����}�X�^�e�[�u�����������A���M����Params��List���擾���ĕԂ�
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_rtnList =
				l_QueryProcessor.doFindAllQuery(
					MutualFundProductRow.TYPE,
					l_strSearchCondCharacterString,
					l_strSort,
					null,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProduct");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProduct");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//�擾�������M����Params�I�u�W�F�N�g��List��Ԃ�
		return l_rtnList;
	}

	/**
	 * (get��n��)<BR>
	 * ��n����Ԃ��B<BR>
	 * <BR>
	 * �P�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@this.get���M�������()���R�[�����āA<BR>
	 * �g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@�mget���M��������ɓn���p�����^�n<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
	 * <BR>
	 * �Q�j�@@��n�����擾����B<BR>
	 * �@@�g�����M�������.get��n��()���R�[�����āA��n�����擾����B<BR>
	 * �@@�mget��n���ɓn���p�����^�n<BR>
	 * �@@�@@is���t�F ����.is���t<BR>
	 * <BR>
	 * �R�j  �g�����M�������擾����B<BR>
     * �@@�@@�@@this�Dget���M����()<BR>
     * �@@�@@�@@[get���M����()�̈���]<BR>
     * �@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�����R�[�h�F����.�����R�[�h<BR>
     * <BR>
     * �S�j  �g�����M����.is�O��MMF = true �̏ꍇ<BR>
     * �@@�@@�@@�i������ԊǗ�.get���M������()�Ɠ������A�C�O���������擾����j<BR>
     * <BR>
     * �@@�@@�S-�P�j�@@�擾������n���������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�S-�P-�P�j�@@is�x��() = true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������v�Z��p���ė��c�Ɠ����擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�@@�擾�������c�Ɠ��������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�B�@@is�x��() = true�̏ꍇ�A�@@�֖߂�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�C�@@is�x��() = false�̏ꍇ�A�擾�������c�Ɠ���Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�S-�P-�Q�j�@@is�x��() = false�̏ꍇ�A�擾������n����Ԃ��B<BR>
     * <BR>
     * �T�j  �g�����M����.is�O��MMF = false �̏ꍇ�A�擾������n����Ԃ��B<BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@param l_blnIsAcquired - (is���t)<BR>
	 * ���t�̏ꍇ��true���A���̏ꍇ��false���w�肷��<BR>
	 * @@return Timestamp
	 * @@roseuid 40B44D83037A
	 */
	public Timestamp getDeliveryDate(
		Institution l_institution,
		String l_strProductCode,
		boolean l_blnIsAcquired) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getDeliveryDate("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "boolean l_blnIsAcquired)";
		log.entering(STR_METHOD_NAME);

		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		Timestamp l_deliveryDate = null;
		Date l_datDeliveryDate = null;
		try
		{
			//�P�j�@@�g�����M��������I�u�W�F�N�g���擾����
			WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
				(WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
					l_institution,
					l_strProductCode);

			//�Q�j�@@��n�����擾����
			l_datDeliveryDate =
				l_web3MutualFundTradedProduct.getDeliveryDate(l_blnIsAcquired);
			l_deliveryDate = new Timestamp(l_datDeliveryDate.getTime());

            //�R�j  �g�����M�������擾����B
            //      this�Dget���M����()
            //      [get���M����()�̈���]
            //      �،���ЁF����.�،����
            //      �����R�[�h�F����.�����R�[�h
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)this.getMutualFundProduct(
                    l_institution,
                    l_strProductCode);

            //�S�j  �g�����M����.is�O��MMF = true �̏ꍇ
            //�@@�@@�@@�i������ԊǗ�.get���M������()�Ɠ������A�C�O���������擾����j
            if (l_mutualFundProduct.isFrgnMmf())
            {
                //�@@�@@�S-�P�j�@@�擾������n���������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                    l_institution.getInstitutionCode(),
                    l_strProductCode,
                    l_deliveryDate);

                //�S-�P-�P�j�@@is�x��() = true�̏ꍇ
                //�@@�@@�@@�@@�@@�������v�Z��p���ė��c�Ɠ����擾����B
                //�@@�@@�@@�A�@@�擾�������c�Ɠ��������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B
                //�@@�@@�@@�B�@@is�x��() = true�̏ꍇ�A�@@�֖߂�
                //�@@�@@�@@�C�@@is�x��() = false�̏ꍇ�A�擾�������c�Ɠ���Ԃ��B
                if (l_blnIsHoliday)
                {
                    while (l_blnIsHoliday)
                    {
                        l_deliveryDate =
                            new WEB3GentradeBizDate(l_deliveryDate).roll(1);
                        l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                            l_institution.getInstitutionCode(),
                            l_strProductCode,
                            l_deliveryDate);
                    }
                }

                //�S-�P-�Q�j�@@is�x��() = false�̏ꍇ�A�擾������n����Ԃ��B
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_deliveryDate;
                }
            }

            //�T�j  �g�����M����.is�O��MMF = false �̏ꍇ�A�擾������n����Ԃ��B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_deliveryDate;
            }
		}
		catch (NotFoundException l_ex)
		{
			log.error("�g�����M��������I�u�W�F�N�g���擾����ł��Ȃ� for�F"
					  + "this.getMutualFundTradedProduct() "
					  + "l_institution = " + l_institution.getInstitutionId()
					  + "l_strProductCode = " + l_strProductCode);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		return l_deliveryDate;
	}

	/**
	 * (get����)<BR>
	 * ������Ԃ��B<BR>
	 * <BR>
	 * �P�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@this.get���M�������()���R�[�����āA<BR>
	 * �g�����M��������I�u�W�F�N�g���擾����B<BR>
	 * �@@�mget���M��������ɓn���p�����^�n<BR>
	 * �@@�@@�،���ЁF ����.�،����<BR>
	 * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
	 * <BR>
	 * �Q�j�@@�������擾����B<BR>
	 * �@@�g�����M�������.get����()���R�[�����āA�������擾����B<BR>
	 * <BR>
	 * �R�j�@@�擾����������Ԃ��B<BR>
	 * <BR>
	 * @@param l_institution - �،����
	 * @@param l_strProductCode - �����R�[�h
	 * @@return Timestamp
	 * @@roseuid 40B44DE700DA
	 */
	public Timestamp getExecutedDate(
		Institution l_institution,
		String l_strProductCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getExecutedDate("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);

		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		Timestamp l_executedDate = null;
		Date l_datExecutedDate = null;
		try
		{
			//�P�j�@@�g�����M��������I�u�W�F�N�g���擾����
			WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
				(WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
					l_institution,
					l_strProductCode);

			//�Q�j�@@�������擾����
			l_datExecutedDate = l_web3MutualFundTradedProduct.getExecutedDate();
			l_executedDate = new Timestamp(l_datExecutedDate.getTime());
		}
		catch (NotFoundException l_ex)
		{
			log.error("�g�����M��������I�u�W�F�N�g���擾����ł��Ȃ� for: "
					  + "this.getMutualFundTradedProduct() "
					  + "l_institution = " + l_institution.getInstitutionCode()
					  + "l_strProductCode = " + l_strProductCode);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//�R�j�@@�擾����������Ԃ�
		return l_executedDate;
	}

    /**
     * �ivalidate��������()�j<BR>
     * �V�[�P���X�}�u�i���M�j���������o�^�R���v�Q��<BR>
     * --------------------------------------------------<BR>
     * ���������̓o�^�R�����s���B<BR>
     * <BR>
     * 1) ���M�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     * 2) "���t�J�n��"�̃`�F�b�N<BR>
     * �@@����.�����������e.get���t�J�n��()��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i���t�J�n���G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00335 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get���t�J�n��()�����M�����I�u�W�F�N�g.get�ݒ��() - 1�c�Ɠ�<BR>
     * <BR>
     * 3) "���t�I����"�̃`�F�b�N <BR>
     * �@@(��1)���t�I����/���抷�I�����`�F�b�N���Q��<BR>
     * <BR>
     * 4) "���抷�J�n��"�̃`�F�b�N<BR>
     * �@@����.�����������e.get���抷�J�n��()��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i���^�抷�J�n���G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00337 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get���抷�J�n��()�����M�����I�u�W�F�N�g.get�����֓�()<BR>
     * <BR>
     * 5) "���抷�I����"�̃`�F�b�N <BR>
     * �@@(��1)���t�I����/���抷�I�����`�F�b�N���Q��<BR>
     * <BR>
     * 6) "���搿���J�n��"�̃`�F�b�N<BR>
     * �@@����.�����������e.get���搿���J�n��()��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i���搿���J�n���G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00339 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get���搿���J�n��()��<BR>
     * ���M�����I�u�W�F�N�g.get�ݒ��()<BR>
     * <BR>
     * 7) "���搿���I����"�̃`�F�b�N<BR>
     * �@@����.�����������e.get���搿���I����()��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i���搿���I�����G���[�j�̗�O���X���[�B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00340 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get���搿���I����()��<BR>
     * ���M�����I�u�W�F�N�g.get���ғ�()<BR>
     * <BR>
     * <BR>
     * 8) ���M��2�����}�X�^Dao.findRowByProductCode()���R�[�����A<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g���擾�B<BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�����R�[�h<BR>
     *  <BR>
     * -- ���t <BR>
     *  <BR>
     * 9) �w����@@�i���t�j�̃`�F�b�N <BR>
     *  9-1) ����.�����������e.get�w����@@�i���t�j��"����"�ł���A <BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get���t�����w��敪()��<BR>
     *    "�s��"�̏ꍇ�A <BR>
     * �@@�@@��O���X���[����B <BR>
     *  <BR>
     *  9-2) ����.�����������e.get�w����@@�i���t�j��"����"�ł���A <BR>
     * �@@����.�����������e.get�w����@@�i���t�j()��"����"�ł���A<BR>
     *     ���ȉ����S��null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�E����.�����������e.get�Œ�����i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʌ����i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�Œ�����i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʌ����i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j�̖߂�l <BR>
     *  <BR>
     *  9-3) ����.�����������e.get�w����@@�i���t�j��"���z"�ł���A <BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get���t���z�w��敪()��<BR>
     *    "�s��"�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B <BR>
     *  <BR>
     *  9-4) ����.�����������e.get�w����@@�i���t�j��"���z"�ł���A <BR>
     * �@@����.�����������e.get�w����@@�i���t�j()��"���z"�ł���A<BR>
     *     ���ȉ����S��null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�E����.�����������e.get�Œ���z�i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʋ��z�i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�Œ���z�i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʋ��z�i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i�V�K���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i�ǉ����t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i���t�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j�̖߂�l <BR>
     * <BR>
     * -- �V�K���t<BR>
     * <BR>
     * 10) �Œ�����i�V�K���t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ�����i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ�����i�V�K���t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00341 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ�����i�V�K���t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i���t�j()<BR>
     * <BR>
     * 11) �P�ʌ����i�V�K���t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʌ����i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʌ����i�V�K���t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00342 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�V�K���t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�V�K���t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * 12) �Œ���z�i�V�K���t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ���z�i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ���z�i�V�K���t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00343 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ���z�i�V�K���t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i���t�j()<BR>
     * <BR>
     * 13) �P�ʋ��z�i�V�K���t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʋ��z�i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʋ��z�i�V�K���t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00344 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�V�K���t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�V�K���t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * -- �ǉ����t<BR>
     * <BR>
     * 14) �Œ�����i�ǉ����t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ�����i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ�����i�ǉ����t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00345 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ�����i�ǉ����t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i���t�j()<BR>
     * <BR>
     * 15) �P�ʌ����i�ǉ����t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʌ����i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʌ����i�ǉ����t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00346 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�ǉ����t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�ǉ����t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * 16) �Œ���z�i�ǉ����t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ���z�i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ���z�i�ǉ����t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00347 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ���z�i�ǉ����t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i���t�j()<BR>
     * <BR>
     * 17) �P�ʋ��z�i�ǉ����t�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʋ��z�i�ǉ����t�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00348 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * -- ���<BR>
     * <BR>
     * 18) �w����@@�i���j�̃`�F�b�N<BR>
     *  18-1) ����.�����������e.get�w����@@�i���j��"����"�ł���A<BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�������w��敪()��<BR>
     *    "�s��"�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     *  18-2) ����.�����������e.get�w����@@�i���j��"����"�ł���A<BR>
     * �@@����.�����������e.get�w����@@�i���j()��"����"�ł���A<BR>
     *     ���ȉ����S��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E����.�����������e.get�Œ�����i���j�̖߂�l<BR>
     * �@@�@@�E����.�����������e.get�P�ʌ����i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j�̖߂�l<BR>
     * <BR>
     *  18-3) ����.�����������e.get�w����@@�i���j��"���z"�ł���A<BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�����z�w��敪()��<BR>
     *    "�s��"�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     *  18-4) ����.�����������e.get�w����@@�i���j��"���z"�ł���A<BR>
     * �@@����.�����������e.get�w����@@�i���j()��"���z"�ł���A<BR>
     *   ���ȉ����S��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E����.�����������e.get�Œ���z�i���j�̖߂�l<BR>
     * �@@�@@�E����.�����������e.get�P�ʋ��z�i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i���j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j�̖߂�l<BR>
     * <BR>
     * 19) �Œ�����i���j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ�����i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ�����i���j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00349 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ�����i���j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i���j()<BR>
     * <BR>
     * 20) �P�ʌ����i���j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʌ����i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʌ����i���j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00350 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i���j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i���j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * 21) �Œ���z�i���j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ���z�i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ���z�i���j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00351 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ���z�i���j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i���j()<BR>
     * <BR>
     * 22) �P�ʋ��z�i���j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʋ��z�i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʋ��z�i���j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00352 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i���j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i���j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * -- �抷<BR>
     * <BR>
     * 23) �w����@@�i�抷�j�̃`�F�b�N<BR>
     *  23-1) ����.�����������e.get�w����@@�i�抷�j��"����"�ł���A<BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�抷�����w��敪()��<BR>
     *    "�s��"�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * 
     *  23-2) ����.�����������e.get�w����@@�i�抷�j��"����"�ł���A<BR>
     * �@@����.�����������e.get�w����@@�i�抷�j()��"����"�ł���A<BR>
     *    ���ȉ����S��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E����.�����������e.get�Œ�����i�抷�j�̖߂�l<BR>
     * �@@�@@�E����.�����������e.get�P�ʌ����i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j�̖߂�l<BR>
     * <BR>
     *  23-4) ����.�����������e.get�w����@@�i�抷�j��"���z"�ł���A<BR>
     * �@@�@@�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�抷���z�w��敪()��<BR>
     *    "�s��"�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     *  23-4) ����.�����������e.get�w����@@�i�抷�j��"���z"�ł���A<BR>
     * �@@����.�����������e.get�w����@@�i�抷�j()��"���z"�ł���A<BR>
     *    ���ȉ����S��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E����.�����������e.get�Œ���z�i�抷�j�̖߂�l<BR>
     * �@@�@@�E����.�����������e.get�P�ʋ��z�i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i�抷�j�̖߂�l<BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j�̖߂�l<BR>
     * <BR>
     * 24) �Œ�����i�抷�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ�����i�抷�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ�����i�抷�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00353 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ�����i�抷�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i�抷�j()<BR>
     * <BR>
     * 25) �P�ʌ����i�抷�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʌ����i�抷�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʌ����i�抷�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00354 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�抷�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i�抷�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * 26) �Œ���z�i�抷�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�Œ���z�i�抷�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�Œ���z�i�抷�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00355 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@����.�����������e.get�Œ���z�i�抷�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i�抷�j()<BR>
     * <BR>
     * 27) �P�ʋ��z�i�抷�j�̃`�F�b�N<BR>
     * �@@����.�����������e.get�P�ʋ��z�i�抷�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A<BR>
     * �@@�i�P�ʋ��z�i�抷�j�G���[�j�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00356 <BR>
     * �@@[����]<BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�抷�j��<BR>
     * ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j()<BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i�抷�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * -- ��W <BR>
     * <BR>
     * 28) ��W�J�n���̃`�F�b�N <BR>
     * �@@����.�����������e.get��W�J�n��()��null�ȊO�ł���A<BR>
     *  ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i��W�J�n���G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get��W�J�n��()�� <BR>
     *       �擾�������M�����I�u�W�F�N�g.get��W�J�n��(SONAR)() <BR>
     * <BR>
     *      *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02272 <BR>
     * <BR>
     * 29) ��W�I�����̃`�F�b�N <BR>
     * �@@����.�����������e.get��W�I����()��null�ȊO�ł���A<BR>
     *      ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i��W�I�����G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get��W�I����()�� <BR>
     *      �擾�������M�����I�u�W�F�N�g.get��W�I����(SONAR)() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02273 <BR>
     * <BR>
     * 30) �w����@@�i��W�j�̃`�F�b�N <BR>
     *  30-1) ����.�����������e.get�w����@@�i��W�j��"����"�ł���A <BR>
     * �@@�@@���擾�������M��2�����}�X�^Row�I�u�W�F�N�g. <BR>
     *      get��W�����w��敪()��"�s��"�̏ꍇ�A <BR>
     * �@@�@@��O���X���[����B <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02281 <BR>
     * <BR>
     *  30-2) ����.�����������e.get�w����@@�i��W�j��"����"�ł���A<BR>
     *      ���ȉ����S��null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�E����.�����������e.get�Œ�����i��W�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʌ����i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j�̖߂�l <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02282 <BR>
     * <BR>
     *  30-3) ����.�����������e.get�w����@@�i��W�j��"���z"�ł���A <BR>
     * �@@�@@���擾�������M��2�����}�X�^Row�I�u�W�F�N�g. <BR>
     *      get��W���z�w��敪()��"�s��"�̏ꍇ�A <BR>
     * �@@�@@��O���X���[����B <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02283 <BR>
     * <BR>
     *  30-4) ����.�����������e.get�w����@@�i��W�j��"���z"�ł���A<BR>
     *      ���ȉ����S��null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�E����.�����������e.get�Œ���z�i��W�j�̖߂�l <BR>
     * �@@�@@�E����.�����������e.get�P�ʋ��z�i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i��W�j�̖߂�l <BR>
     * �@@�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j�̖߂�l <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02284 <BR>
     * <BR>
     * 31) �Œ�����i��W�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�Œ�����i��W�j��null�ȊO�ł���A<BR>
     *      ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�Œ�����i��W�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get�Œ�����i��W�j�� <BR>
     *      ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i��W�j() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02274 <BR>
     * <BR>
     * 32) �P�ʌ����i��W�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�P�ʌ����i��W�j��null�ȊO�ł���A<BR>
     *      ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�P�ʌ����i��W�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i��W�j�� <BR>
     *      ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j() <BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʌ����i��W�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02275 <BR>
     * <BR>
     * 33) �Œ���z�i��W�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�Œ���z�i��W�j��null�ȊO�ł���A<BR>
     *      ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�Œ���z�i��W�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get�Œ���z�i��W�j�� <BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i��W�j() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02276 <BR>
     * <BR>
     * 34) �P�ʋ��z�i��W�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�P�ʋ��z�i��W�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�P�ʋ��z�i��W�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i��W�j�� <BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j() <BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�P�ʋ��z�i��W�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02277 <BR>
     * <BR> 
     * 35) �O�ݍŒ���z�i�V�K���t�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݍŒ���z�i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݍŒ���z�i�V�K���t�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get�O�ݍŒ���z�i�V�K���t�j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���t�j() <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02745 <BR>
     * <BR>
     * 36) �O�ݒP�ʋ��z�i�V�K���t�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݒP�ʋ��z�i�V�K���t�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() <BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02746 <BR>
     * <BR>
     * 37) �O�ݍŒ���z�i�ǉ����t�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݍŒ���z�i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݍŒ���z�i�ǉ����t�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get�O�ݍŒ���z�i�ǉ����t�j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���t�j() <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02747 <BR>
     * <BR>
     * 38) �O�ݒP�ʋ��z�i�ǉ����t�j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݒP�ʋ��z�i�ǉ����t�j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0�ȊO���A<BR>
     * �@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() <BR>
     * �@@�@@�̗]�肪0�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0���A<BR>
     * �@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02748 <BR>
     * <BR>
     * 39) �O�ݍŒ���z�i���j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݍŒ���z�i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݍŒ���z�i���j�G���[�j�̗�O���X���[����B <BR>
     * �@@[����] <BR>
     * �@@�@@�@@����.�����������e.get�O�ݍŒ���z�i���j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���j() <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02749 <BR>
     * <BR>
     * 40) �O�ݒP�ʋ��z�i���j�̃`�F�b�N <BR>
     * �@@����.�����������e.get�O�ݒP�ʋ��z�i���j��null�ȊO�ł���A<BR>
     * ���ȉ��̏��������������ꍇ�A <BR>
     * �@@�i�O�ݒP�ʋ��z�i���j�G���[�j�̗�O���X���[����B <BR>
     *�@@[����] <BR>
     *�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j() ��0�ȊO���A<BR>
     *�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i���j��<BR>
     *          ���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j() <BR>
     *�@@�@@�̗]�肪0�ȊO�̏ꍇ<BR>
     * <BR>
     *�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j() ��0���A<BR>
     *�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i���j��0�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02750 <BR>
     * <BR>
     * - ������t���؎��Ԃ̃`�F�b�N <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j���������o�^�R���v�̒������؎��Ԃ̃`�F�b�N���Q�� <BR>
     * ���A�C�e����`�̋L�q�͍폜���܂���<BR>
     * <BR>
     * <BR>
     * (��1)���t�I����/���抷�I�����`�F�b�N <BR>
     * �@@�����t�I�����F <BR>
     * �@@�@@�@@�@@���t�I����!=null���A�ȉ��̏����𖞂����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�i���t�I�����G���[�j�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@���t�I���� > ���ғ� - ( �����ړ����� + 1 )�c�Ɠ� <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00336 <BR>
     * <BR>
     * �@@�����抷�I�����F <BR>
     * �@@�@@�@@�@@���抷�I����!=null���A�ȉ��̏����𖞂����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�i���^�抷�I�����G���[�j�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@���抷�I���� > ���ғ� - ( �����ړ����� + 1 )�c�Ɠ� <BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00338 <BR>
     * <BR>
     * <BR>
     * �@@�@@�� ���t�I����       �F ����.�����������e.get���t�I����() <BR>
     * �@@�@@ �� ���抷�I���� �F ����.�����������e.get���抷�I����() <BR>
     * �@@�@@�� ���ғ�             �F ���M�����I�u�W�F�N�g.get���ғ�() <BR>
     * �@@�@@�� �����ړ����� �F this.get���M������� (�،����,�����R�[�h).get�����ړ�����() <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[get���M��������̈���] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЁF����.�،���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h�F����.�����������e.get�����R�[�h <BR>
     * @@param l_institution - �،����
     * @@param l_productCondSpec - �����������e
     * @@throws WEB3BaseException
     * @@roseuid 40E9357A0369
     */
    public void validateProductCond(
        Institution l_institution,
        WEB3MutualFundProductCondSpec l_productCondSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
              "validateProductCond(" 
              + "Institution l_institution," 
              + "WEB3MutualFundProductCondSpec l_productCondSpec)";
            
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_productCondSpec == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //1) ���M�����I�u�W�F�N�g�̎擾
            MutualFundProduct l_mutualFundProduct =
                (MutualFundProduct) this.getMutualFundProduct(
                    l_institution,
                    l_productCondSpec.getMutualProductCode());
            MutualFundProductParams l_mutualFundProductParams =
                new MutualFundProductParams((MutualFundProductRow) l_mutualFundProduct.getDataSourceObject());

            //���ғ� �F ���M�����I�u�W�F�N�g.get���ғ�()
            Timestamp l_tmsRedemptionDate = l_mutualFundProductParams.getRedemptionDate();

            //�����ړ������Fthis.get���M������� (�،����,�����R�[�h).get�����ړ�����()
            MutualFundTradedProduct l_mutualFundTradedProduct =
                this.getMutualFundTradedProduct(l_institution,
                    l_productCondSpec.getMutualProductCode());
            MutualFundTradedProductParams l_mutualFundTradedProductParams =
                new MutualFundTradedProductParams(
                    (MutualFundTradedProductRow)l_mutualFundTradedProduct.getDataSourceObject());
            int l_intExecDateShiftdays = l_mutualFundTradedProductParams.getExecDateShiftdays();

            //���ғ� - ( �����ړ����� + 1 )�c�Ɠ�
            Date l_datBizDate = new WEB3GentradeBizDate(l_tmsRedemptionDate).roll(-(l_intExecDateShiftdays + 1));

            //2) "���t�J�n��"�̃`�F�b�N
            Timestamp l_tmsSettingDate = l_mutualFundProductParams.getSettingDate();
            if (l_productCondSpec.getBuyStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyStartDate(),
                    new WEB3GentradeBizDate(l_tmsSettingDate).roll(-1))
                    < 0)
            {
                log.debug("���t�J�n���G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00335,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t�J�n���G���[");
            }

            //3) "���t�I����"�̃`�F�b�N
            if (l_productCondSpec.getBuyEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyEndDate(),
                    l_datBizDate)
                    > 0)
            {
                log.debug("���t�I�����G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00336,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t�I�����G���[");
            }

            //4) "���抷�J�n��"�̃`�F�b�N
            if (l_productCondSpec.getSellSwitchingStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getSellSwitchingStartDate(),
                    l_mutualFundProductParams.getSellBanDate())
                    < 0)
            {
                log.debug("���^�抷�J�n���G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00337,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���^�抷�J�n���G���[");
            }

            //5) "���抷�I����"�̃`�F�b�N
            if (l_productCondSpec.getSellSwitchingEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getSellSwitchingEndDate(),
                    l_datBizDate)
                    > 0)
            {
                log.debug("���^�抷�I�����G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00338,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���^�抷�I�����G���[");
            }

            //6) "���搿���J�n��"�̃`�F�b�N
            if (l_productCondSpec.getBuyClaimStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyClaimStartDate(),
                    l_mutualFundProductParams.getSettingDate())
                    <= 0)
            {
                log.debug("���搿���J�n���G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���搿���J�n���G���[");
            }

            //7) "���搿���I����"�̃`�F�b�N
            if (l_productCondSpec.getBuyClaimEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyClaimEndDate(),
                    l_mutualFundProductParams.getRedemptionDate())
                    > 0)
            {
                log.debug("���搿���I�����G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00340,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���搿���I�����G���[");
            }

            //8) MutualFund2ndProductSonarRow���擾    
            MutualFund2ndProductSonarRow l_mutualFundProductSonar2Row =
                MutualFund2ndProductSonarDao.findRowByProductCodeInstitutionCode(
                    l_productCondSpec.getMutualProductCode(),
                    l_institution.getInstitutionCode());

            //9) �w����@@�i���t�j�̃`�F�b�N
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getBuySelectable()) 
                && WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(l_mutualFundProductSonar2Row.getBuyQtySpecDiv()))
            {
                log.debug("�w����@@�i���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���t�j�G���[");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getBuySelectable()) 
                      && l_productCondSpec.getNewBuyMinQty() == null
                      && l_productCondSpec.getNewBuyUnitQty() == null 
                      && l_productCondSpec.getAddBuyMinQty() == null
                      && l_productCondSpec.getAddBuyUnitQty() == null
                      && l_mutualFundProductParams.getNewBuyMinQtyIsNull()
                      && l_mutualFundProductParams.getNewBuyUnitQtyIsNull()
                      && l_mutualFundProductParams.getAddBuyMinQtyIsNull()
                      && l_mutualFundProductParams.getAddBuyUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getBuyMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getBuyUnitQtyIsNull())
            {
                log.debug("�w����@@�i���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���t�j�G���["); 
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getBuySelectable()) 
                && WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(l_mutualFundProductSonar2Row.getBuyAmtSpecDiv()))
            {
                log.debug("�w����@@�i���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���t�j�G���[");             
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getBuySelectable()) 
                      && l_productCondSpec.getNewBuyMinAmt() == null
                      && l_productCondSpec.getNewBuyUnitAmt() == null 
                      && l_productCondSpec.getAddBuyMinAmt() == null
                      && l_productCondSpec.getAddBuyUnitAmt() == null
                      && l_mutualFundProductParams.getNewBuyMinAmtIsNull()
                      && l_mutualFundProductParams.getNewBuyUnitAmtIsNull()
                      && l_mutualFundProductParams.getAddBuyMinAmtIsNull()
                      && l_mutualFundProductParams.getAddBuyUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getBuyMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getBuyUnitAmtIsNull())
            {
                log.debug("�w����@@�i���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���t�j�G���["); 
            }           

            // 10) �Œ�����i�V�K���t�j�̃`�F�b�N
            if (l_productCondSpec.getNewBuyMinQty() != null
                &&(Integer.parseInt(l_productCondSpec.getNewBuyMinQty()))
                    < l_mutualFundProductSonar2Row.getBuyMinQty())
            {
                log.debug("�Œ�����i�V�K���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�����i�V�K���t�j�G���[");
            }

            // 11) �P�ʌ����i�V�K���t�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʌ����i�V�K���t�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʌ����i�V�K���t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�V�K���t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�V�K���t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getNewBuyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getNewBuyUnitQty())
                    % l_mutualFundProductSonar2Row.getBuyUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getNewBuyUnitQty()) != 0)))
            {
                log.debug("�P�ʌ����i�V�K���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00342,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʌ����i�V�K���t�j�G���[");
            }

            // 12) �Œ���z�i�V�K���t�j�̃`�F�b�N    
            if (l_productCondSpec.getNewBuyMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getNewBuyMinAmt())
                    < l_mutualFundProductSonar2Row.getBuyMinAmt())
            {
                log.debug("�Œ���z�i�V�K���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00343,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ���z�i�V�K���t�j�G���[");
            }

            // 13) �P�ʋ��z�i�V�K���t�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʋ��z�i�V�K���t�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʋ��z�i�V�K���t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�V�K���t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�V�K���t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getNewBuyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getNewBuyUnitAmt())
                    % l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getNewBuyUnitAmt()) != 0)))
            {
                log.debug("�P�ʋ��z�i�V�K���t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00344,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʋ��z�i�V�K���t�j�G���[");
            }

            // 14) �Œ�����i�ǉ����t�j�̃`�F�b�N   
            if (l_productCondSpec.getAddBuyMinQty() != null
                && Integer.parseInt(l_productCondSpec.getAddBuyMinQty())
                    < l_mutualFundProductSonar2Row.getBuyMinQty())
            {
                log.debug("�Œ�����i�ǉ����t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00345,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�����i�ǉ����t�j�G���[");
            }

            // 15) �P�ʌ����i�ǉ����t�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʌ����i�ǉ����t�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʌ����i�ǉ����t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�ǉ����t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�ǉ����t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getAddBuyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getAddBuyUnitQty())
                    % l_mutualFundProductSonar2Row.getBuyUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getAddBuyUnitQty()) != 0)))
            {
                log.debug("�P�ʌ����i�ǉ����t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00346,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʌ����i�ǉ����t�j�G���[");
            }

            // 16 �Œ���z�i�ǉ����t�j�̃`�F�b�N  
            if (l_productCondSpec.getAddBuyMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getAddBuyMinAmt())
                    < l_mutualFundProductSonar2Row.getBuyMinAmt())
            {
                log.debug("�Œ���z�i�ǉ����t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00347,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ���z�i�ǉ����t�j�G���[");
            }

            // 17) �P�ʋ��z�i�ǉ����t�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʋ��z�i�ǉ����t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�ǉ����t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getAddBuyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getAddBuyUnitAmt())
                    % l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getAddBuyUnitAmt()) != 0)))
            {
                log.debug("�P�ʋ��z�i�ǉ����t�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00348,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʋ��z�i�ǉ����t�j�G���[");
            }

            //18) �w����@@�i���j�̃`�F�b�N
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSellSelectable()) 
                && WEB3SellPossibleDivDef.NOT_SELL.equals(l_mutualFundProductSonar2Row.getSellQtySpecDiv()))
            {
                log.debug("�w����@@�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���j�G���[");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSellSelectable()) 
                      && l_productCondSpec.getSellMinQty() == null
                      && l_productCondSpec.getSellUnitQty() == null 
                      && l_mutualFundProductParams.getSellMinQtyIsNull()
                      && l_mutualFundProductParams.getSellUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSellMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSellUnitQtyIsNull())
            {
                log.debug("�w����@@�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���j�G���[");     
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSellSelectable()) 
                && WEB3SellPossibleDivDef.NOT_SELL.equals(l_mutualFundProductSonar2Row.getSellAmtSpecDiv()))
            {
                log.debug("�w����@@�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���j�G���[");             
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSellSelectable()) 
                      && l_productCondSpec.getSellMinAmt() == null
                      && l_productCondSpec.getSellUnitAmt() == null 
                      && l_mutualFundProductParams.getSellMinAmtIsNull()
                      && l_mutualFundProductParams.getSellUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSellMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSellUnitAmtIsNull())
            {
                log.debug("�w����@@�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i���j�G���["); 
            }           

            // 19) �Œ�����i���j�̃`�F�b�N
            if (l_productCondSpec.getSellMinQty() != null
                && Integer.parseInt(l_productCondSpec.getSellMinQty())
                    < l_mutualFundProductSonar2Row.getSellMinQty())
            {
                log.debug("�Œ�����i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00349,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�����i���j�G���[");
            }

            // 20) �P�ʌ����i���j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʌ����i���j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʌ����i���j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i���j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i���j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i���j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getSellUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getSellUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getSellUnitQty())
                    % l_mutualFundProductSonar2Row.getSellUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getSellUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getSellUnitQty()) != 0)))
            {
                log.debug("�P�ʌ����i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00350,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʌ����i���j�G���[");
            }

            // 21) �Œ���z�i���j�̃`�F�b�N 
            if (l_productCondSpec.getSellMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getSellMinAmt())
                    < l_mutualFundProductSonar2Row.getSellMinAmt())
            {
                log.debug("�Œ���z�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ���z�i���j�G���[");
            }

            // 22) �P�ʋ��z�i���j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʋ��z�i���j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʋ��z�i���j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i���j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i���j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i���j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getSellUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getSellUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSellUnitAmt())
                    % l_mutualFundProductSonar2Row.getSellUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getSellUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSellUnitAmt()) != 0)))
            {
                log.debug("�P�ʋ��z�i���j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00352,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʋ��z�i���j�G���[");
            }

            //23) �w����@@�i�抷�j�̃`�F�b�N
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSwitchingSelectable()) 
                && WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(l_mutualFundProductSonar2Row.getSwtQtySpecDiv()))
            {
                log.debug("�w����@@�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i�抷�j�G���[");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSwitchingSelectable()) 
                      && l_productCondSpec.getSwitchingMinQty() == null
                      && l_productCondSpec.getSwitchingUnitQty() == null 
                      && l_mutualFundProductParams.getSwtMinQtyIsNull()
                      && l_mutualFundProductParams.getSwtUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSwtMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSwtUnitQtyIsNull())
            {
                log.debug("�w����@@�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i�抷�j�G���[");     
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSwitchingSelectable()) 
                && WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(l_mutualFundProductSonar2Row.getSwtAmtSpecDiv()))
            {
                log.debug("�w����@@�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i�抷�j�G���[");         
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSwitchingSelectable()) 
                      && l_productCondSpec.getSwitchingMinAmt() == null
                      && l_productCondSpec.getSwitchingUnitAmt() == null 
                      && l_mutualFundProductParams.getSwtMinAmtIsNull()
                      && l_mutualFundProductParams.getSwtUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSwtMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSwtUnitAmtIsNull())
            {
                log.debug("�w����@@�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i�抷�j�G���[");
            }           

            // 24) �Œ�����i�抷�j�̃`�F�b�N
            if (l_productCondSpec.getSwitchingMinQty() != null
                && Integer.parseInt(l_productCondSpec.getSwitchingMinQty())
                    < l_mutualFundProductSonar2Row.getSwtMinQty())
            {
                log.debug("�Œ�����i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00353,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�����i�抷�j�G���[");
            }

            // 25) �P�ʌ����i�抷�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʌ����i�抷�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʌ����i�抷�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�抷�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i�抷�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i�抷�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getSwitchingUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getSwtUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getSwitchingUnitQty())
                    % l_mutualFundProductSonar2Row.getSwtUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getSwtUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getSwitchingUnitQty()) != 0)))
            {
                log.debug("�P�ʌ����i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00354,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʌ����i�抷�j�G���[");
            }

            // 26) �Œ���z�i�抷�j�̃`�F�b�N 
            if (l_productCondSpec.getSwitchingMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getSwitchingMinAmt())
                    < l_mutualFundProductSonar2Row.getSwtMinAmt())
            {
                log.debug("�Œ���z�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00355,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ���z�i�抷�j�G���[");
            }

            // 27) �P�ʋ��z�i�抷�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʋ��z�i�抷�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʋ��z�i�抷�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�抷�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i�抷�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i�抷�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getSwitchingUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getSwtUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSwitchingUnitAmt())
                    % l_mutualFundProductSonar2Row.getSwtUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getSwtUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSwitchingUnitAmt()) != 0)))
            {
                log.debug("�P�ʋ��z�i�抷�j�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00356,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʋ��z�i�抷�j�G���[");
            }
            
            //1.3 ��W�֘A���ڂ̃`�F�b�N
            //�i�ڍׂ́Avalidate��������()�̃A�C�e����`���Q�Ɓj
            
            //-- ��W 
            //28) ��W�J�n���̃`�F�b�N 
            //  ����.�����������e.get��W�J�n��()��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A 
            //�i��W�J�n���G���[�j�̗�O���X���[����B           
            //  [����] 
            //�@@�@@����.�����������e.get��W�J�n��()��
            //          �擾�������M�����I�u�W�F�N�g.get��W�J�n���iSONAR�j() 
            if (l_productCondSpec.getApplyAbleStartDate() != null &&
                WEB3DateUtility.compareToDay(l_productCondSpec.getApplyAbleStartDate(), 
                   l_mutualFundProductParams.getRecruitStartDateSonar()) < 0)                
            {
                log.debug("��W�J�n���G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��W�J�n���G���[�B");
            }
            
            //29) ��W�I�����̃`�F�b�N 
            //�@@����.�����������e.get��W�I����()��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A 
            //�i��W�I�����G���[�j�̗�O���X���[����B 
            // [����] 
            //  ����.�����������e.get��W�I����()��
            //        �擾�������M�����I�u�W�F�N�g.get��W�I�����iSONAR�j()�̑O�c�Ɠ� 
            
            WEB3GentradeBizDate l_gentradeBizDate = null;
            Timestamp l_tsRecruitEndDate = null;
            if(l_mutualFundProductParams.getRecruitEndDateSonar() != null)
            { 
                 l_gentradeBizDate = 
                     new WEB3GentradeBizDate(l_mutualFundProductParams.getRecruitEndDateSonar());
                 l_tsRecruitEndDate= l_gentradeBizDate.roll(-1);
            }
            
            if (l_productCondSpec.getApplyAbleEndDate() != null &&
                WEB3DateUtility.compareToDay(l_productCondSpec.getApplyAbleEndDate(), 
                    l_tsRecruitEndDate) > 0)                
            {
                log.debug("��W�I�����G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02273,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "��W�I�����G���[�B");
            }
            
            //30) �w����@@�i��W�j�̃`�F�b�N 
            //30-1) ����.�����������e.get�w����@@�i��W�j��"����"�ł���A 
            //�@@���擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get��W�����w��敪()��"�s��"�̏ꍇ�A 
            //�@@��O���X���[����B 
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getApplySelectable()) && 
                WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    l_mutualFundProductSonar2Row.getRecruitQtySpecDiv()))
            {
                log.debug("�w����@@�i��W�j�G���[(�h�����h���w��s��)�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02281,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w����@@�i��W�j�G���[(�h�����h���w��s��)�B");
            }
            //30-2) ����.�����������e.get�w����@@�i��W�j��"����"�ł���A
            //      ���ȉ����S��null�̏ꍇ�A��O���X���[����B 
            //�@@�E����.�����������e.get�Œ�����i��W�j�̖߂�l 
            //�@@�E����.�����������e.get�P�ʌ����i��W�j�̖߂�l 
            //�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ�����i��W�j�̖߂�l 
            //�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʌ����i��W�j�̖߂�l 
            //�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i��W�j�̖߂�l 
            //�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j�̖߂�l 
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getApplySelectable()) && 
                l_productCondSpec.getApplyMinQty() == null && 
                l_productCondSpec.getApplyUnitQty() == null && 
                l_mutualFundProductParams.getRecruitMinQtyIsNull() && 
                l_mutualFundProductParams.getRecruitUnitQtyIsNull() && 
                l_mutualFundProductSonar2Row.getRecruitMinQtyIsNull()&&
                l_mutualFundProductSonar2Row.getRecruitUnitQtyIsNull())
            {
                log.debug("�w����@@�i��W�j�G���[�i�h�����h���w��̏ꍇ�A" +
                        "�Œ�����ƒP�ʌ������擾�ł��܂���B�j");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02282,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i��W�j�G���[�i�h�����h���w��̏ꍇ�A" +
                    "�Œ�����ƒP�ʌ������擾�ł��܂���B�j");
            }
            
            //30-3) ����.�����������e.get�w����@@�i��W�j��"���z"�ł���A 
            //�@@���擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get��W���z�w��敪()��"�s��"�̏ꍇ�A 
            //�@@��O���X���[����B 
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getApplySelectable()) &&
                WEB3SpecDivDef.NOT_POSSIBLE.equals(
                    l_mutualFundProductSonar2Row.getRecruitAmtSpecDiv()))
            {
                log.debug("�w����@@�i��W�j�G���[(�h���z�h���w��s��)�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02283,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i��W�j�G���[(�h���z�h���w��s��)�B");
            }
            
            // 30-4) ����.�����������e.get�w����@@�i��W�j��"���z"�ł���A
            //        ���ȉ����S��null�̏ꍇ�A��O���X���[����B 
            //�@@�E����.�����������e.get�Œ���z�i��W�j�̖߂�l 
            //�@@�E����.�����������e.get�P�ʋ��z�i��W�j�̖߂�l 
            //�@@�E�擾�������M�����I�u�W�F�N�g.get�Œ���z�i��W�j�̖߂�l 
            //�@@�E�擾�������M�����I�u�W�F�N�g.get�P�ʋ��z�i��W�j�̖߂�l 
            //�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i��W�j�̖߂�l 
            //�@@�E�擾�������M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j�̖߂�l 
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getApplySelectable()) && 
                l_productCondSpec.getApplyMinAmt() == null && 
                l_productCondSpec.getApplyUnitAmt() == null && 
                l_mutualFundProductParams.getRecruitMinAmtIsNull() && 
                l_mutualFundProductParams.getRecruitUnitAmtIsNull() && 
                l_mutualFundProductSonar2Row.getRecruitMinAmtIsNull()&&
                l_mutualFundProductSonar2Row.getRecruitUnitAmtIsNull())
            {
                log.debug("�w����@@�i��W�j�G���[�i�h���z�h���w��̏ꍇ�A" +
                        "�Œ���z�ƒP�ʋ��z���擾�ł��܂���B�j");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02284,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@�i��W�j�G���[�i�h���z�h���w��̏ꍇ�A" +
                    "�Œ���z�ƒP�ʋ��z���擾�ł��܂���B�j");
            }

            //31) �Œ�����i��W�j�̃`�F�b�N 
            //����.�����������e.get�Œ�����i��W�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A 
            //�i�Œ�����i��W�j�G���[�j�̗�O���X���[����B 
            //�@@[����] 
            //�@@����.�����������e.get�Œ�����i��W�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ�����i��W�j() 
            if (l_productCondSpec.getApplyMinQty() != null &&
                (Integer.parseInt(l_productCondSpec.getApplyMinQty()) < 
                    l_mutualFundProductSonar2Row.getRecruitMinQty()))
            {
                log.debug("�Œ�����i��W�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�����i��W�j�G���[�B");
            }
            
            //32) �P�ʌ����i��W�j�̃`�F�b�N 
            //�@@����.�����������e.get�P�ʌ����i��W�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʌ����i��W�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i��W�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʌ����i��W�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʌ����i��W�j��0�ȊO�̏ꍇ 
            if (l_productCondSpec.getApplyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getRecruitUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getApplyUnitQty())
                    % l_mutualFundProductSonar2Row.getRecruitUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getRecruitUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getApplyUnitQty()) != 0)))
            {
                log.debug("�P�ʌ����i��W�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02275,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʌ����i��W�j�G���[�B");
            }
            
            //33) �Œ���z�i��W�j�̃`�F�b�N 
            //�@@����.�����������e.get�Œ���z�i��W�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A 
            //�@@�i�Œ���z�i��W�j�G���[�j�̗�O���X���[����B 
            //�@@[����] 
            //�@@�@@�@@����.�����������e.get�Œ���z�i��W�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�Œ���z�i��W�j() 
            if (l_productCondSpec.getApplyMinAmt() != null &&
                (Integer.parseInt(l_productCondSpec.getApplyMinAmt()) < 
                    l_mutualFundProductSonar2Row.getRecruitMinAmt()))
            {
                log.debug("�Œ���z�i��W�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02276,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ���z�i��W�j�G���[�B");
            }

            //34) �P�ʋ��z�i��W�j�̃`�F�b�N
            //�@@����.�����������e.get�P�ʋ��z�i��W�j��null�ȊO�ł���A���ȉ��̏��������������ꍇ�A
            //�@@�i�P�ʋ��z�i��W�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i��W�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�P�ʋ��z�i��W�j() ��0���A
            //�@@�@@�@@����.�����������e.get�P�ʋ��z�i��W�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getApplyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getRecruitUnitAmt() != 0
                        && Integer.parseInt(l_productCondSpec.getApplyUnitAmt())
                        % l_mutualFundProductSonar2Row.getRecruitUnitAmt() != 0)
                        || (l_mutualFundProductSonar2Row.getRecruitUnitAmt() == 0
                            && Integer.parseInt(l_productCondSpec.getApplyUnitAmt()) != 0)))
            {
                log.debug("�P�ʋ��z�i��W�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02277,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�ʋ��z�i��W�j�G���[�B");
            }

            //35) �O�ݍŒ���z�i�V�K���t�j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݍŒ���z�i�V�K���t�j��null�ȊO�ł���A
            // ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݍŒ���z�i�V�K���t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@����.�����������e.get�O�ݍŒ���z�i�V�K���t�j
            //         �����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���t�j()
            if (l_productCondSpec.getBuyFrgnMinAmtBuy() != null
                && (Integer.parseInt(l_productCondSpec.getBuyFrgnMinAmtBuy()) <
                    l_mutualFundProductSonar2Row.getFrgnBuyMinAmt()))
            {
                log.debug("�O�ݍŒ���z�i�V�K���t�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02745,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݍŒ���z�i�V�K���t�j�G���[�B");
            }

            //36) �O�ݒP�ʋ��z�i�V�K���t�j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j��null�ȊO�ł���A
            // ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݒP�ʋ��z�i�V�K���t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�V�K���t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getBuyFrgnUnitAmtBuy() != null
                && ((l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtBuy())
                    % l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtBuy()) != 0)))
            {
                log.debug("�O�ݒP�ʋ��z�i�V�K���t�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02746,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݒP�ʋ��z�i�V�K���t�j�G���[�B");
            }

            //37) �O�ݍŒ���z�i�ǉ����t�j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݍŒ���z�i�ǉ����t�j��null�ȊO�ł���A
            // ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݍŒ���z�i�ǉ����t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@����.�����������e.get�O�ݍŒ���z�i�ǉ����t�j
            //  �����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���t�j()
            if (l_productCondSpec.getBuyFrgnMinAmtAdd() != null
                && (Integer.parseInt(l_productCondSpec.getBuyFrgnMinAmtAdd()) <
                    l_mutualFundProductSonar2Row.getFrgnBuyMinAmt()))
            {
                log.debug("�O�ݍŒ���z�i�ǉ����t�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02747,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݍŒ���z�i�ǉ����t�j�G���[�B");
            }

            //38) �O�ݒP�ʋ��z�i�ǉ����t�j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j��null�ȊO�ł���A
            //    ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݒP�ʋ��z�i�ǉ����t�j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���t�j() ��0���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i�ǉ����t�j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getBuyFrgnUnitAmtAdd() != null
                && ((l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtAdd())
                    % l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtAdd()) != 0)))
            {
                log.debug("�O�ݒP�ʋ��z�i�ǉ����t�j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02748,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݒP�ʋ��z�i�ǉ����t�j�G���[�B");
            }

            //39) �O�ݍŒ���z�i���j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݍŒ���z�i���j��null�ȊO�ł���A
            // ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݍŒ���z�i���j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@����.�����������e.get�O�ݍŒ���z�i���j
            //          �����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݍŒ���z�i���j()
            if (l_productCondSpec.getSellFrgnMinAmtSell() != null
                && (Integer.parseInt(l_productCondSpec.getSellFrgnMinAmtSell()) <
                    l_mutualFundProductSonar2Row.getFrgnSellMinAmt()))
            {
                log.debug("�O�ݍŒ���z�i���j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02749,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݍŒ���z�i���j�G���[�B");
            }

            //40) �O�ݒP�ʋ��z�i���j�̃`�F�b�N
            //�@@����.�����������e.get�O�ݒP�ʋ��z�i���j��null�ȊO�ł���A
            //  ���ȉ��̏��������������ꍇ�A
            //�@@�i�O�ݒP�ʋ��z�i���j�G���[�j�̗�O���X���[����B
            //�@@[����]
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j() ��0�ȊO���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i���j�����M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j()
            //�@@�@@�̗]�肪0�ȊO�̏ꍇ
            //
            //�@@�@@�@@���M��2�����}�X�^Row�I�u�W�F�N�g.get�O�ݒP�ʋ��z�i���j() ��0���A
            //�@@�@@�@@����.�����������e.get�O�ݒP�ʋ��z�i���j��0�ȊO�̏ꍇ
            if (l_productCondSpec.getSellFrgnUnitAmtSell() != null
                && ((l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSellFrgnUnitAmtSell())
                    % l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSellFrgnUnitAmtSell()) != 0)))
            {
                log.debug("�O�ݒP�ʋ��z�i���j�G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02750,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�ݒP�ʋ��z�i���j�G���[�B");
            }
            
            // 1.14 ������t���؎��Ԃ̃`�F�b�N 
            // ����.�����������e�̈ȉ��̍��ڂ̂����ꂩ��null�ł͂Ȃ��ꍇ�A 
            // �@@�ȉ������{����B 
            // �@@�E�������؊J�n���ԁi�����j�@@�@@�E�������؏I�����ԁi�����j 
            // �@@�E�������؊J�n���ԁi�����j�@@�@@�E�������؏I�����ԁi�����j 
            if(l_productCondSpec.getOrderCloseStartTimeFull() != null
                    || l_productCondSpec.getOrderCloseEndTimeFull() != null
                    || l_productCondSpec.getOrderCloseStartTimeHalf() != null
                    || l_productCondSpec.getOrderCloseEndTimeHalf() != null)
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //  1.14.1) �u���M��Еʏ����e�[�u���iSONAR�j�v����������B  
                // �@@[��������]  
                // �@@�@@�،���ЃR�[�h������.�،����.getInstitutionCode() and 
                // �@@�@@�����R�[�h������.�����������e.get�����R�[�h()
                MutualFundInstCondSonarRow l_mfInstCondSonarRow = 
                    MutualFundInstCondSonarDao.findRowByPk(
                        l_institution.getInstitutionCode(),
                        l_productCondSpec.getMutualProductCode());
                
                // 1.14.2 get�������؊J�n���ԁi�����j
                String l_strCondSpecOrderCloseStartTimeFull = 
                    l_productCondSpec.getOrderCloseStartTimeFull();
                
                // 1.14.3 get�������؏I�����ԁi�����j
                String l_strCondSpecOrderCloseEndTimeFull = 
                    l_productCondSpec.getOrderCloseEndTimeFull();
                
                //  ----- �������؊J�n���ԁi�����j�̃`�F�b�N

                //1.14.4 ����.�����������e.get�������؊J�n���ԁi�����j!=null 
                //      �܂��� ����.�����������e.get�������؏I�����ԁi�����j!=null�̏ꍇ
                if(l_strCondSpecOrderCloseStartTimeFull != null || l_strCondSpecOrderCloseEndTimeFull != null)
                {
                    // 1.14.4.1 ������ԃe�[�u����������
                    // �@@�@@�������ʂ̍ŏ��̈ꌏ�ڂ��擾���āu������ԃe�[�u��Params�v�ŃL���X�g����B 
                    //  �@@[��������]  
                    //  �@@�@@�،���ЃR�[�h������.�،����.getInstitutionCode() and  
                    //  �@@�@@�s��R�[�h���hDEFAULT�h and 
                    //  �@@�@@��t���ԋ敪���h�����M���h and 
                    //  �@@�@@���i�R�[�h������.�����������e.�����R�[�h and  
                    //  �@@�@@�c�Ɠ��敪���h�I���c�Ɠ��h and  
                    //  �@@�@@�s��g���K���s���hSONAR��MQ�g���K�����{����h and  
                    //  �@@�@@��t�\���h��t�\�h and  
                    //  �@@�@@�������v�Z���h�����h 
                    String l_strWhere = " institution_code = ? and " +
                        " market_code = ? and " +
                        " trading_time_type = ? and " +
                        " product_code = ? and " +
                        " biz_date_type = ? and " +
                        " submit_market_trigger = ? and " +
                        " enable_order = ? and " +
                        " bizdate_calc_parameter = ? ";
            
                    Object[] l_objWhere = {
                        l_institution.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT,
                        WEB3TradingTimeTypeDef.MUTUAL_FUND,
                        l_productCondSpec.getMutualProductCode(),
                        WEB3BizDateTypeDef.BIZ_DATE,
                        WEB3SubmitMarketTriggerDef.TRIGGER,
                        WEB3EnableOrderDef.ENABLE,
                        WEB3BizDateCalcParameterDef.DAY_BIZ_DATE
                        };
                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_strWhere,
                            null,
                            l_objWhere);
                    
                    if(l_lisSearchResult != null && l_lisSearchResult.size() > 0)
                    {
                        TradingTimeRow l_tradingTimeRow = 
                            (TradingTimeRow)l_lisSearchResult.get(0);   
                        
                        if(l_tradingTimeRow.getStartTime() == null 
                                || l_tradingTimeRow.getStartTime().length() < 6 
                                || l_tradingTimeRow.getEndTime() == null 
                                || l_tradingTimeRow.getEndTime().length() < 6)
                        {
                            log.debug("������ԃe�[�u��Params�I�u�W�F�N�g.�J�n���ԁ@@�܂��́@@" +
                                    "������ԃe�[�u��Params�I�u�W�F�N�g.�I�����ԕs��");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }

                        long l_lngTradingTimeRowStartTime = 
                            Long.parseLong(l_tradingTimeRow.getStartTime().substring(0, 6));

                        // 1.14.4.2 get�������؊J�n���ԁi�����j!=null �̏ꍇ�A����J�n���ԂƂ̐������`�F�b�N�����{����B
                        if (l_strCondSpecOrderCloseStartTimeFull != null)
                        {
                            if(l_strCondSpecOrderCloseStartTimeFull.length() < 4)
                            {
                                log.debug("�����������e.get�������؊J�n���ԁi�����j�s���B");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            
                            //format �����������e.get�������؊J�n���ԁi�����j with hhmmss
                            long l_lngCondSpecCloseStartTime = 
                                Long.parseLong(l_strCondSpecOrderCloseStartTimeFull + "00");

                            //�擾�����u������ԃe�[�u��Params�I�u�W�F�N�g�v.get�J�n����() 
                            //�� ����.�����������e.get�������؊J�n���ԁi�����j() 
                            //�̏ꍇ�A��O���X���[����B
                            if (l_lngTradingTimeRowStartTime >= l_lngCondSpecCloseStartTime)
                            {
                                log.debug("������t��~�J�n���ԁi�����j�̎w��\���Ԃ͈͓̔��ł͂���܂���B");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01701,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "������t��~�J�n���ԁi�����j�̎w��\���Ԃ͈͓̔��ł͂���܂���B");
                            }
                            
                            
                            if(l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual() == null
                                    || l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual().length() < 4)
                            {
                                log.debug("���M��Еʃe�[�u���iSONAR�j.�������؎��ԁi�����j�s���B");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            // �擾�������M��Еʃe�[�u���iSONAR�j.�������؎��ԁi����)
                            // format ���M��Еʃe�[�u���iSONAR�j.�������؎��ԁi����) with hhmmss
                            long l_lngOrderAcceptLimitTimeUsual = 
                                Long.parseLong(l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual() + "00");

                            // 1.14.4.2.1 �擾�������M��Еʃe�[�u���iSONAR�j.�������؎��ԁi�����j
                            //       �� ����.�������؊J�n���ԁi�����j�̏ꍇ�A��O���X���[�B
                            if(l_lngOrderAcceptLimitTimeUsual < l_lngCondSpecCloseStartTime)
                            {
                                log.debug(" Remain_Test ----------------------------> case3");
                                log.debug("������t��~�J�n���ԁi�����j�̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01702,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "������t��~�J�n���ԁi�����j�̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B");
                            }
                        }
                        
                        // 1.14.4.3 get�������؊J�n���ԁi�����j=null�܂��́Aget�������؏I�����ԁi����()=null�̏ꍇ
                        if(l_strCondSpecOrderCloseStartTimeFull == null || l_strCondSpecOrderCloseEndTimeFull == null)
                        {
                            // 1.14.4.3.1 ������ԃe�[�u�����u�����~���ԑсv���擾���錟������
                            // �ȉ��̏����ŁA�u������ԃe�[�u���v���猻�݂́u�����~���ԑсv����������B 
                            //[��������] 
                            // �،���ЃR�[�h������.�،����.getInstitutionCode() and 
                            //�s��R�[�h���hDEFAULT�h and 
                            //��t���ԋ敪���h�����M���h and 
                            //���i�R�[�h������.�����������e.�����R�[�h and 
                            //�c�Ɠ��敪���h�I���c�Ɠ��h and 
                            //�s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h and 
                            //��t�\���h��t�s�h and 
                            //�������v�Z���h���c�Ɠ��h
                            String l_strTradingStopWhere = " institution_code = ? and "
                                + " market_code = ? and "
                                + " trading_time_type = ? and "
                                + " product_code = ? and "
                                + " biz_date_type = ? and "
                                + " submit_market_trigger = ? and "
                                + " enable_order = ? and "
                                + " bizdate_calc_parameter = ? ";

                            Object[] l_objTradingStopWhereValue =
                            { l_institution.getInstitutionCode(),
                                WEB3MarketCodeDef.DEFAULT,
                                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                                l_productCondSpec.getMutualProductCode(),
                                WEB3BizDateTypeDef.BIZ_DATE,
                                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                                WEB3EnableOrderDef.DISABLED,
                                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
                            
                            List l_lisTradingStopTimeResult = 
                                l_queryProcessor.doFindAllQuery(
                                    TradingTimeRow.TYPE,l_strTradingStopWhere, 
                                    null, l_objTradingStopWhereValue);
                        
                            TradingTimeRow l_tradingStopTimeRow = null;
                            if(l_lisTradingStopTimeResult != null && l_lisTradingStopTimeResult.size() > 0)
                            {
                                l_tradingStopTimeRow = 
                                    (TradingTimeRow)l_lisTradingStopTimeResult.get(0); 
                                
                                // 1.14.4.3.2 ����.�����������e.get�������؊J�n���ԁi�����j() = null �̏ꍇ
                                if (l_strCondSpecOrderCloseStartTimeFull == null)
                                {
                                    // �擾�����u�����~���ԑсv�̊J�n����
                                    long l_lngTradingStopTimeStartTime = 
                                        Long.parseLong(l_tradingStopTimeRow.getStartTime().substring(0, 6));

                                    // ����.�����������e.get�������؏I�����ԁi�����j
                                    long l_lngOrderCloseEndTimeFull = 
                                        Long.parseLong(l_strCondSpecOrderCloseEndTimeFull + "00");
                                    
                                    // 1.14.4.3.2.1  �擾�����u�����~���ԑсv�̊J�n����
                                    // ������.�����������e.get�������؏I�����ԁi�����j()�̏ꍇ�A��O���X���[
                                    if(l_lngTradingStopTimeStartTime > l_lngOrderCloseEndTimeFull)
                                    {
                                        log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                    }
                                }
                                // 1.14.4.3.3 ����.�����������e.get�������؏I�����ԁi�����j()= null�̏ꍇ
                                if (l_strCondSpecOrderCloseEndTimeFull == null)
                                {
                                    // ����.�����������e.get�������؊J�n���ԁi�����j
                                    long l_lngOrderCloseStartTimeFull = 
                                        Long.parseLong(l_strCondSpecOrderCloseStartTimeFull + "00");

                                    // �擾�����u�����~���ԑсv�̏I������
									Date l_datTradingStopTimeEndTime = 
										WEB3DateUtility.getDate(l_tradingStopTimeRow.getEndTime(), "HHmmss");
									String l_strTradingStopTimeEndTime =
										WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
											l_datTradingStopTimeEndTime,1L), "HHmmss");
									long l_lngTradingStopTimeEndTime = 
										Long.parseLong(l_strTradingStopTimeEndTime.substring(0, 6));

                                    // 1.14.4.3.3.1 ����.�����������e.get�������؊J�n���ԁi�����j()
                                    // ���擾�����u�����~���ԑсv�̏I�����Ԃ̏ꍇ�A��O���X���[
                                    if(l_lngOrderCloseStartTimeFull > l_lngTradingStopTimeEndTime)
                                    {
                                        log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                    }
                                }
                            }
                        }
                    }
                }
                
                // 1.14.5  get�������؊J�n���ԁi�����j
                String l_strCondSpecOrderCloseStartTimeHalf = 
                    l_productCondSpec.getOrderCloseStartTimeHalf();
                
                // 1.14.6 get�������؏I�����ԁi�����j
                String l_strCondSpecOrderCloseEndTimeHalf = 
                    l_productCondSpec.getOrderCloseEndTimeHalf();
                
                //  ----- �������؊J�n���ԁi�����j�̃`�F�b�N

                //1.14.7 ����.�����������e.get�������؊J�n���ԁi�����j!=null 
                //      �܂��� ����.�����������e.get�������؏I�����ԁi�����j!=null�̏ꍇ
                if(l_strCondSpecOrderCloseStartTimeHalf != null || l_strCondSpecOrderCloseEndTimeHalf != null)
                {
                    // 1.14.7.1 ������ԃe�[�u����������
                    // �@@�@@�������ʂ̍ŏ��̈ꌏ�ڂ��擾���āu������ԃe�[�u��Params�v�ŃL���X�g����B 
                    //  �@@[��������]  
                    //  �@@�@@�،���ЃR�[�h������.�،����.getInstitutionCode() and  
                    //  �@@�@@�s��R�[�h���hDEFAULT�h and 
                    //  �@@�@@��t���ԋ敪���h�����M���h and 
                    //  �@@�@@���i�R�[�h������.�����������e.�����R�[�h and  
                    //  �@@�@@�c�Ɠ��敪�������i�ߑO�̂݁j�h and  
                    //  �@@�@@�s��g���K���s���hSONAR��MQ�g���K�����{����h and  
                    //  �@@�@@��t�\���h��t�\�h and  
                    //  �@@�@@�������v�Z���h�����h 
                    String l_strWhere = " institution_code = ? and " +
                        " market_code = ? and " +
                        " trading_time_type = ? and " +
                        " product_code = ? and " +
                        " biz_date_type = ? and " +
                        " submit_market_trigger = ? and " +
                        " enable_order = ? and " +
                        " bizdate_calc_parameter = ? ";
            
                    Object[] l_objWhere = {
                        l_institution.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT,
                        WEB3TradingTimeTypeDef.MUTUAL_FUND,
                        l_productCondSpec.getMutualProductCode(),
                        WEB3BizDateTypeDef.BIZ_DATE_AM,
                        WEB3SubmitMarketTriggerDef.TRIGGER,
                        WEB3EnableOrderDef.ENABLE,
                        WEB3BizDateCalcParameterDef.DAY_BIZ_DATE
                        };
                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_strWhere,
                            null,
                            l_objWhere);
                    
                    if(l_lisSearchResult != null && l_lisSearchResult.size() > 0)
                    {
                        TradingTimeRow l_tradingTimeRow = 
                            (TradingTimeRow)l_lisSearchResult.get(0);   
                        
                        if(l_tradingTimeRow.getStartTime() == null 
                                || l_tradingTimeRow.getStartTime().length() < 6 
                                || l_tradingTimeRow.getEndTime() == null 
                                || l_tradingTimeRow.getEndTime().length() < 6)
                        {
                            log.debug("������ԃe�[�u��Params�I�u�W�F�N�g.�J�n���ԁ@@�܂��́@@" +
                                    "������ԃe�[�u��Params�I�u�W�F�N�g.�I�����ԕs��");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }

                        long l_lngTradingTimeRowStartTime = 
                            Long.parseLong(l_tradingTimeRow.getStartTime().substring(0, 6));

                        // 1.14.7.2 get�������؊J�n���ԁi�����j!=null �̏ꍇ�A����J�n���ԂƂ̐������`�F�b�N�����{����B
                        if (l_strCondSpecOrderCloseStartTimeHalf != null)
                        {
                            if(l_strCondSpecOrderCloseStartTimeHalf.length() < 4)
                            {
                                log.debug("�����������e.get�������؊J�n���ԁi�����j�s���B");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            //format �����������e.get�������؊J�n���ԁi�����j with hhmmss
                            long l_lngCondSpecCloseStartTimeHalf = 
                                Long.parseLong(l_strCondSpecOrderCloseStartTimeHalf + "00");
                            
                            //�擾�����u������ԃe�[�u��Params�I�u�W�F�N�g�v.get�J�n����()
                            //�� ����.�����������e.get�������؊J�n���ԁi�����j() 
                            //�̏ꍇ�A��O���X���[����B
                            if (l_lngTradingTimeRowStartTime >= l_lngCondSpecCloseStartTimeHalf)
                            {
                                log.debug("������t��~�J�n���Ԃ̎w��\���Ԃ͈͓̔��ł͂���܂���B");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01701,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "������t��~�J�n���Ԃ̎w��\���Ԃ͈͓̔��ł͂���܂���B");
                            }
                            
                            if(l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf() == null
                                    || l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf().length() < 4)
                            {
                                log.debug("���M��Еʃe�[�u���iSONAR�j.�������؎��ԁi�����j�s���B");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            // �擾�������M��Еʃe�[�u���iSONAR�j.�������؎��ԁi����)
                            //format ���M��Еʃe�[�u���iSONAR�j.�������؎��ԁi����) with hhmmss
                            long l_lngOrderAcceptLimitTimeHalf = 
                                Long.parseLong(l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf() + "00");

                            // 1.14.7.2.1 �擾�������M��Еʃe�[�u���iSONAR�j.�������؎��ԁi�����j
                            //       �� ����.�������؊J�n���ԁi�����j�̏ꍇ�A��O���X���[�B
                            if(l_lngOrderAcceptLimitTimeHalf < l_lngCondSpecCloseStartTimeHalf)
                            {
                                log.debug("������t��~�J�n���Ԃ̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01702,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "������t��~�J�n���Ԃ̎w�肪HOST�o�^���Ԃ���̎��Ԃ��w�肳��Ă��܂��B");
                            }
                        }
                        
                        // 1.14.7.3 get�������؊J�n���ԁi�����j=null�܂��́Aget�������؏I�����ԁi����()=null�̏ꍇ
                        if(l_strCondSpecOrderCloseStartTimeHalf == null || l_strCondSpecOrderCloseEndTimeHalf == null)
                        {
                            // 1.14.7.3.1 ������ԃe�[�u�����u�����~���ԑсv���擾���錟������
                            // �ȉ��̏����ŁA�u������ԃe�[�u���v���猻�݂́u�����~���ԑсv����������B 
                            //[��������]  
                            // �،���ЃR�[�h������.�،����.getInstitutionCode() and 
                            //�s��R�[�h���hDEFAULT�h and 
                            //��t���ԋ敪���h�����M���h and 
                            //���i�R�[�h������.�����������e.�����R�[�h and 
                            //�c�Ɠ��敪���h�����i�ߑO�̂݁j�h and 
                            //�s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h and 
                            //��t�\���h��t�s�h and 
                            //�������v�Z���h���c�Ɠ��h
                            String l_strTradingStopWhere = " institution_code = ? and "
                                + " market_code = ? and "
                                + " trading_time_type = ? and "
                                + " product_code = ? and "
                                + " biz_date_type = ? and "
                                + " submit_market_trigger = ? and "
                                + " enable_order = ? and "
                                + " bizdate_calc_parameter = ? ";

                            Object[] l_objTradingStopWhereValue =
                            { l_institution.getInstitutionCode(),
                                WEB3MarketCodeDef.DEFAULT,
                                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                                l_productCondSpec.getMutualProductCode(),
                                WEB3BizDateTypeDef.BIZ_DATE_AM,
                                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                                WEB3EnableOrderDef.DISABLED,
                                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
                            
                            List l_lisTradingStopTimeResult = 
                                l_queryProcessor.doFindAllQuery(
                                    TradingTimeRow.TYPE,l_strTradingStopWhere, 
                                    null, l_objTradingStopWhereValue);
                        
                            TradingTimeRow l_tradingStopTimeRow = null;
                            if(l_lisTradingStopTimeResult != null && l_lisTradingStopTimeResult.size() > 0)
                            {
                                l_tradingStopTimeRow = 
                                    (TradingTimeRow)l_lisTradingStopTimeResult.get(0); 
                                
                                // 1.14.7.3.2 ����.�����������e.get�������؊J�n���ԁi�����j() = null �̏ꍇ
                                if (l_strCondSpecOrderCloseStartTimeHalf == null)
                                {
                                    // �擾�����u�����~���ԑсv�̊J�n����
                                    long l_lngTradingStopTimeStartTime = 
                                        Long.parseLong(l_tradingStopTimeRow.getStartTime().substring(0, 6));

                                    // ����.�����������e.get�������؏I�����ԁi�����j
                                    long l_lngOrderCloseEndTimeHalf = 
                                        Long.parseLong(l_strCondSpecOrderCloseEndTimeHalf + "00");
                                    
                                    // 1.14.7.3.2.1  �擾�����u�����~���ԑсv�̊J�n����
                                    // ������.�����������e.get�������؏I�����ԁi�����j()�̏ꍇ�A��O���X���[
                                    if(l_lngTradingStopTimeStartTime > l_lngOrderCloseEndTimeHalf)
                                    {
                                        log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                    }
                                }
                                // 1.14.7.3.3 ����.�����������e.get�������؏I�����ԁi�����j()= null�̏ꍇ
                                if (l_strCondSpecOrderCloseEndTimeHalf == null)
                                {
                                    // ����.�����������e.get�������؊J�n���ԁi�����j
                                    long l_lngOrderCloseStartTimeHalf = 
                                        Long.parseLong(l_strCondSpecOrderCloseStartTimeHalf + "00");

                                    // �擾�����u�����~���ԑсv�̏I������
									Date l_datTradingStopTimeEndTime = 
										WEB3DateUtility.getDate(l_tradingStopTimeRow.getEndTime(), "HHmmss");
									String l_strTradingStopTimeEndTime =
										WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
											l_datTradingStopTimeEndTime,1L), "HHmmss");
                                    long l_lngTradingStopTimeEndTime = 
                                        Long.parseLong(l_strTradingStopTimeEndTime.substring(0, 6));

                                    // 1.14.7.3.3.1 ����.�����������e.get�������؊J�n���ԁi�����j()
                                    // ���擾�����u�����~���ԑсv�̏I�����Ԃ̏ꍇ�A��O���X���[
                                    if(l_lngOrderCloseStartTimeHalf > l_lngTradingStopTimeEndTime)
                                    {
                                        log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�Y���f�[�^�Ȃ�", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                    "���M�����I�u�W�F�N�g�̎擾�ł��Ȃ� with "
                    + "this.getMutualFundProduct()  �،����= "
                    + l_institution.getInstitutionCode()
                    + "�����R�[�h = "
                    + l_productCondSpec.getMutualProductCode());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (get�X�V�p���M����)<BR>
	 * �igetMutualFundProduct�̃I�[�o�[���[�h�j <BR>
	 * <BR>
	 *Select For Update�Ŋg�����M�������擾����B  <BR>
	 *
	 *�P�j�@@�ȉ��̏����Łu���M�����}�X�^�[�e�[�u���v����������B  <BR>
	 *�@@�،���ЃR�[�h=����.�،���ЃR�[�h and  <BR>
	 *�@@�����R�[�h=����.�����R�[�h  <BR>
	 *�@@�񍆃R�[�h=�h0�FDEFAULT�h  <BR>
	 * <BR>
	 *�Q�j�@@�P�j�̌������ʂ̃N���[�����쐬���A���̃N���[���������ɁA  <BR>
	 *�@@this.to����( )���R�[�����Ď擾�����g�����M�����I�u�W�F�N�g��ԋp����B <BR>
	 * <BR>
	 * @@param l_institutionCode - �،���ЃR�[�h
	 * @@param l_strProductCode - �����R�[�h
	 * @@return WEB3MutualFundProduct
	 * @@throws WEB3BaseException
	 * @@roseuid 40AD9019005D
	 */
	public WEB3MutualFundProduct getUpdateMutualFundTradedProduct(
		String l_institutionCode,
		String l_strProductCode)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getUpdateMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
       
		// �P�j�@@�ȉ��̏����Łu���M�����}�X�^�[�e�[�u���v����������B 
		//   �،���ЃR�[�h=����.�،���ЃR�[�h and  
		//   �����R�[�h=����.�����R�[�h 
		//   �񍆃R�[�h=�h0�FDEFAULT�h 
		MutualFundProductRow l_mfProductRow = null;
		try
		{
			String l_strWhere = "institution_code = ? and " +
					" product_code = ? and " +
					" product_issue_code = ? ";
            
			String l_strCondition = " for update ";

			Object[] l_objWhere = {
					l_institutionCode,
					l_strProductCode,
					"0"};
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			List l_lisSearchResult =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductRow.TYPE,
					l_strWhere,
					null,
					l_strCondition,
					l_objWhere);
            
			l_mfProductRow = 
				(MutualFundProductRow)l_lisSearchResult.get(0);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���  when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
        
		// �Q�j�@@�P�j�̌������ʂ̃N���[�����쐬���A���̃N���[���������ɁA 
		//   this.to����()���R�[�����Ď擾�����g�����M�����I�u�W�F�N�g��ԋp����B
		WEB3MutualFundProduct l_mfProduct =
			(WEB3MutualFundProduct) this.toProduct(l_mfProductRow);
        
		log.exiting(STR_METHOD_NAME);                
		return l_mfProduct;
	}
    
    /**
     * (get�抷����)<BR>
     * �抷�����̖�����Ԃ��B <BR>
     * <BR>
     * �P�j���������擾����B<BR>
     * �@@�@@���M������ԊǗ�.get�抷�������i�j���R�[������B<BR>
     *     [����]
     *     �抷�������R�[�h�F ����.�抷�������R�[�h
     *     �抷������R�[�h�F ����.�抷������R�[�h
     * <BR>
     * �Q�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B <BR>
     * <BR>
     * �Q�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �����R�[�h�F ����.�抷������R�[�h <BR>
     * <BR>
     * �Q�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    ������t�g�����U�N�V�����F �h���t�h <BR>
     * <BR>
     * �Q�|�R�j���M������ԊǗ�.setTimestamp()���R�[������B<BR> 
     * <BR>
     * �R�j�抷������̖������擾����B <BR>
     * <BR>
     *    this.get����()���R�[������B <BR>
     * <BR>
     *    �m�����n <BR>
     *    �،���ЁF ����.�،���� <BR>
     *    �����R�[�h�F ����.�抷������R�[�h <BR>
     *    �������F �擾�����抷������ <BR>
     * <BR>
     * �S�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B <BR>
     * <BR>
     * �S�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �����R�[�h�F ����.�抷�������R�[�h <BR>
     * <BR>
     * �S�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    ������t�g�����U�N�V�����F �h���t�h <BR>
     * <BR>
     * �S�|�R�j���M������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �T�j�抷�������̖������擾����B <BR>
     * <BR>
     *    this.get����()���R�[������B <BR>
     * <BR>
     *    �m�����n <BR>
     *    �،���ЁF ����.�،���� <BR>
     *    �����R�[�h�F ����.�抷�������R�[�h <BR>
     *    �������F �擾�����抷������ <BR>
     * <BR>
     * �U�j���������肵�ԋp����B <BR>
     * <BR>
     *    �抷�������̖��� < �抷������̖����̏ꍇ�A<BR>
     * �抷������̖�����ԋp����B <BR>
     *    �抷�������̖��� >= �抷������̖����̏ꍇ�A<BR>
     * �抷�������̖�����ԋp����B <BR>
     * <BR>
     * @@param l_institution - �،����
     * @@param l_strSwtOriginProductCode - �抷�������R�[�h
     * @@param l_strSwtPointProductCode - �抷������R�[�h
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD9019005D
     */
    public Timestamp getSwtExecutedDate(
        Institution l_institution, 
        String l_strProductCode, 
        String l_strSwtProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwtExecutedDate(Institution, String, String)";                
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���������擾����B
        //   ���M������ԊǗ�.get�抷�������i�j���R�[������B
        //   [����]
        //   �抷�������R�[�h�F ����.�抷�������R�[�h
        //   �抷������R�[�h�F ����.�抷������R�[�h
        Date l_datCurrentBizDate = WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_strProductCode, l_strSwtProductCode);
        
        //�Q�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B 
        //�Q�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B 
        //   [����] 
        //   �����R�[�h�F ����.�抷������R�[�h        
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strSwtProductCode);

        //�Q�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //   [����] 
        //   ������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //�Q�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B         
		WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //�R�j�抷������̖������擾����B
        //   this.get����()���R�[������B
        //�m�����n 
        //�،���ЁF ����.�،���� 
        //�����R�[�h�F ����.�抷������R�[�h 
        //�������F �擾�����抷������
        Date l_datSwtExecutedDate = this.getExecutedDate(
            l_institution, l_strSwtProductCode, l_datCurrentBizDate);
        
        //�S�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B 
        //�S�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B 
        //   [����] 
        //   �����R�[�h�F ����.�抷�������R�[�h 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strProductCode);

        //�S�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //   [����] 
        //   ������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //�S�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B 
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //�T�j�抷�������̖������擾����B
        //    this.get����()���R�[������B
        //�m�����n 
        //�،���ЁF ����.�،���� 
        //�����R�[�h�F ����.�抷�������R�[�h 
        //�������F �擾�����抷������
        Date l_datExecutedDate = this.getExecutedDate(
            l_institution, l_strProductCode, l_datCurrentBizDate);

        log.debug("�抷�������̖��� = " + l_datExecutedDate);
        log.debug("�抷������̖��� = " + l_datSwtExecutedDate);
        
        //�U�j���������肵�ԋp����B 
        //�抷�������̖��� < �抷������̖����̏ꍇ�A�抷������̖�����ԋp����B 
        //�抷�������̖��� >= �抷������̖����̏ꍇ�A�抷�������̖�����ԋp����B 
        if (WEB3DateUtility.compareToDay(l_datExecutedDate, l_datSwtExecutedDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�抷���� = " + l_datSwtExecutedDate);
            return new Timestamp(l_datSwtExecutedDate.getTime());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�抷���� = " + l_datExecutedDate);
            return new Timestamp(l_datExecutedDate.getTime());
        }
    }
    
    /**
     * (get�抷��n��)<BR>
     * �抷�����̎�n����Ԃ��B <BR>
     * <BR>
     * �P�j�������擾����B<BR>
     * �@@�@@this.get�抷�����i�j���R�[������B<BR>
     *     [����]<BR>
     *     �،���ЁF ����.�،����<BR>
     *     �抷�������R�[�h�F ����.�抷�������R�[�h<BR>
     *     �抷������R�[�h�F ����.�抷������R�[�h<BR>
     * <BR>
     * �Q�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B <BR>
     * <BR>
     * �Q�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �����R�[�h�F ����.�抷������R�[�h <BR>
     * <BR>
     * �Q�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    ������t�g�����U�N�V�����F �h���t�h <BR>
     * <BR>
     * �Q�|�R�j���M������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�抷������̎�n�����擾����B <BR>
     * <BR>
     *    this.get��n��()���R�[������B <BR>
     * <BR>
     *    �m�����n <BR>
     *    �،���ЁF ����.�،���� <BR>
     *    �����R�[�h�F ����.�抷������R�[�h <BR>
     *    is���t�F true <BR>
     *    �����F �擾�������� <BR>
     * <BR>
     * �S�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B <BR>
     * <BR>
     * �S�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �����R�[�h�F ����.�抷�������R�[�h <BR>
     * <BR>
     * �S�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    ������t�g�����U�N�V�����F �h���t�h <BR>
     * <BR>
     * �S�|�R�j���M������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �T�j�抷�������̎�n�����擾����B <BR>
     * <BR>
     *    this.get��n��()���R�[������B <BR>
     * <BR>
     *    �m�����n <BR>
     *    �،���ЁF ����.�،���� <BR>
     *    �����R�[�h�F ����.�抷�������R�[�h <BR>
     *    is���t�F false <BR>
     *    �����F�@@�擾��������
     * <BR>
     * �U�j��n�������肵�ԋp����B <BR>
     * <BR>
     *    �抷�������̎�n�� < �抷������̎�n���̏ꍇ�A�抷�������̎�n����ԋp����B <BR>
     *    �抷�������̎�n�� >= �抷������̎�n���̏ꍇ�A�抷������̎�n����ԋp����B <BR>
     * <BR>
     * @@param l_institution - �،����
     * @@param l_strSwtOriginProductCode - �抷�������R�[�h
     * @@param l_strSwtPointProductCode - �抷������R�[�h
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD9019005D
     */
    public Timestamp getSwtDeliveryDate(
        Institution l_institution, 
        String l_strProductCode, 
        String l_strSwtProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwtDeliveryDate(Institution, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�������擾����B
        //   this.get�抷�����i�j���R�[������B
        //   [����]
        //   �،���ЁF ����.�،����
        //   �抷�������R�[�h�F ����.�抷�������R�[�h
        //   �抷������R�[�h�F ����.�抷������R�[�h
        Date l_datSwtExecutedDate = this.getSwtExecutedDate(
            l_institution, l_strProductCode, l_strSwtProductCode);
        
        //�Q�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B 
        //�Q�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B 
        //   [����] 
        //   �����R�[�h�F ����.�抷������R�[�h         
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strSwtProductCode);

        //�Q�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //   [����] 
        //   ������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //�Q�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B         
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //�R�j�抷������̎�n�����擾����B 
        //�@@�@@this.get��n��()���R�[������B 
        //�@@ �m�����n 
        //�@@�@@�،���ЁF ����.�،���� 
        //�@@�@@�����R�[�h�F ����.�抷������R�[�h 
        //�@@�@@is���t�F true 
        //�@@�@@�����F�擾��������
        Date l_datSwtDeliveryDate = 
            this.getDeliveryDate(l_institution, l_strSwtProductCode, true, l_datSwtExecutedDate);
        
        //�S�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B 
        //�S�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B 
        //   [����] 
        //   �����R�[�h�F ����.�抷�������R�[�h 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strProductCode);

        //�S�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //   [����] 
        //   ������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //�S�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B 
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //�T�j�抷�������̎�n�����擾����B 
        //   this.get��n��()���R�[������B
        //  �m�����n 
        //   �،���ЁF ����.�،���� 
        //   �����R�[�h�F ����.�抷�������R�[�h 
        //   is���t�F false 
        //   �����F�擾��������
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_institution, l_strProductCode, false, l_datSwtExecutedDate);
        
        log.debug("�抷�������̎�n�� = " + l_datDeliveryDate);
        log.debug("�抷������̎�n�� = " + l_datSwtDeliveryDate);

        //�U�j��n�������肵�ԋp����B 
        //�抷�������̎�n�� < �抷������̎�n���̏ꍇ�A�抷�������̎�n����ԋp����B 
        //�抷�������̎�n�� >= �抷������̎�n���̏ꍇ�A�抷������̎�n����ԋp����B  
        
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datSwtDeliveryDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�抷��n�� = " + l_datDeliveryDate);
            return new Timestamp(l_datDeliveryDate.getTime());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�抷��n�� = " + l_datSwtDeliveryDate);
            return new Timestamp(l_datSwtDeliveryDate.getTime());
        }
    }
    
    /**
     * (get����)<BR>
     * ����.�������ɑ΂��������Ԃ��B<BR>
     * <BR>
     * �P�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@this.get���M�������()���R�[�����āA<BR>
     * �g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@�mget���M��������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �Q�j�@@�������擾����B<BR>
     * �@@�g�����M�������.get����()���R�[�����āA�������擾����B<BR>
     *   [����]
     *   �������F����.������
     * <BR>
     * �R�j�@@�擾����������Ԃ��B<BR>
     * <BR>
     * @@param l_institution - �،����
     * @@param l_strProductCode - �����R�[�h
     * @@param l_datCurrentBizDate - ������
     * @@return Timestamp
     */
    public Timestamp getExecutedDate(
        Institution l_institution,
        String l_strProductCode,
        Date l_datCurrentBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getExecutedDate("
                + "Institution l_institution,"
                + "String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Timestamp l_executedDate = null;
        Date l_datExecutedDate = null;
        try
        {
            //�P�j�g�����M��������I�u�W�F�N�g���擾����B
            WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
                    l_institution,
                    l_strProductCode);

            //�Q�j�������擾����B
            //  �g�����M�������.get����()���R�[�����āA�������擾����B
            //  [����]
            //  �������F����.������
            l_datExecutedDate = l_web3MutualFundTradedProduct.getExecutedDate(
                l_datCurrentBizDate);
            l_executedDate = new Timestamp(l_datExecutedDate.getTime());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�g�����M��������I�u�W�F�N�g���擾����ł��Ȃ� for: "
                      + "this.getMutualFundTradedProduct() "
                      + "l_institution = " + l_institution.getInstitutionCode()
                      + "l_strProductCode = " + l_strProductCode);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        //�R�j�@@�擾����������Ԃ�
        return l_executedDate;
    }
    
    /**
     * (get��n��)<BR>
     * ����.�����ɑ΂����n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@this.get���M�������()���R�[�����āA<BR>
     * �g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@�mget���M��������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �Q�j�@@��n�����擾����B<BR>
     * �@@�g�����M�������.get��n��()���R�[�����āA��n�����擾����B<BR>
     * �@@�mget��n���ɓn���p�����^�n<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     *     �����F ����.����<BR>
     * <BR>
     * �R�j  �g�����M�������擾����B<BR>
     * �@@�@@�@@this�Dget���M����()<BR>
     * �@@�@@�@@[get���M����()�̈���]<BR>
     * �@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�����R�[�h�F����.�����R�[�h<BR>
     * <BR>
     * �S�j  �g�����M����.is�O��MMF = true �̏ꍇ<BR>
     * �@@�@@�@@�i������ԊǗ�.get���M������()�Ɠ������A�C�O���������擾����j<BR>
     * <BR>
     * �@@�@@�S-�P�j�@@�擾������n���������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�S-�P-�P�j�@@is�x��() = true�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������v�Z��p���ė��c�Ɠ����擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�@@�擾�������c�Ɠ��������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�B�@@is�x��() = true�̏ꍇ�A�@@�֖߂�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�C�@@is�x��() = false�̏ꍇ�A�擾�������c�Ɠ���Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�S-�P-�Q�j�@@is�x��() = false�̏ꍇ�A�擾������n����Ԃ��B<BR>
     * <BR>
     * �T�j  �g�����M����.is�O��MMF = false �̏ꍇ�A�擾������n����Ԃ��B<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_blnIsAcquired - (is���t)<BR>
     * @@param l_datSwtExecutedDate - ����<BR>
     * ���t�̏ꍇ��true���A���̏ꍇ��false���w�肷��<BR>
     * @@return Timestamp
     * @@roseuid 40B44D83037A
     */
    public Timestamp getDeliveryDate(
        Institution l_institution,
        String l_strProductCode,
        boolean l_blnIsAcquired,
        Date l_datSwtExecutedDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate("
                + "Institution l_institution,"
                + "String l_strProductCode,"
                + "boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Timestamp l_deliveryDate = null;
        Date l_datDeliveryDate = null;
        try
        {
            //�P�j�@@�g�����M��������I�u�W�F�N�g���擾����
            WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
                    l_institution,
                    l_strProductCode);

            //�Q�j�@@��n�����擾����
            l_datDeliveryDate =
                l_web3MutualFundTradedProduct.getDeliveryDate(
                    l_blnIsAcquired, l_datSwtExecutedDate);
            l_deliveryDate = new Timestamp(l_datDeliveryDate.getTime());

            //�R�j  �g�����M�������擾����B   
            //      this�Dget���M����() 
            //      [get���M����()�̈���] 
            //      �،���ЁF����.�،���� 
            //      �����R�[�h�F����.�����R�[�h 
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)this.getMutualFundProduct(l_institution,l_strProductCode);

            //�S�j  �g�����M����.is�O��MMF = true �̏ꍇ
            //�@@�@@�@@�i������ԊǗ�.get���M������()�Ɠ������A�C�O���������擾����j
            if (l_mutualFundProduct.isFrgnMmf())
            {
                //�@@�@@�S-�P�j�@@�擾������n���������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                    l_institution.getInstitutionCode(),
                    l_strProductCode,
                    l_deliveryDate);

                //�S-�P-�P�j�@@is�x��() = true�̏ꍇ
                //�@@�@@�@@�@@�@@�@@�������v�Z��p���ė��c�Ɠ����擾����B
                //�@@�@@�@@�@@�A�@@�擾�������c�Ɠ��������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B
                //�@@�@@�@@�@@�B�@@is�x��() = true�̏ꍇ�A�@@�֖߂�
                //�@@�@@�@@�@@�C�@@is�x��() = false�̏ꍇ�A�擾�������c�Ɠ���Ԃ��B
                if (l_blnIsHoliday)
                {
                    while (l_blnIsHoliday)
                    {
                        l_deliveryDate =
                            new WEB3GentradeBizDate(l_deliveryDate).roll(1);
                        l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                            l_institution.getInstitutionCode(),
                            l_strProductCode,
                            l_deliveryDate);
                    }
                }

                //�S-�P-�Q�j�@@is�x��() = false�̏ꍇ�A�擾������n����Ԃ��B
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_deliveryDate;
                }
            }

            //�T�j  �g�����M����.is�O��MMF = false �̏ꍇ�A�擾������n����Ԃ��B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_deliveryDate;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�g�����M��������I�u�W�F�N�g���擾����ł��Ȃ� for�F"
                      + "this.getMutualFundTradedProduct() "
                      + "l_institution = " + l_institution.getInstitutionId()
                      + "l_strProductCode = " + l_strProductCode);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        return l_deliveryDate;
    }
    
    /**
     * (get�莞��z���t�\�������X�g)<BR>
     * �莞��z���t�\�Ȗ����̃��X�g��Ԃ��B<BR> 
     * <BR>
     * �P�j�@@���M�����}�X�^�[�e�[�u�����������A�莞��z���t�\�Ȗ����̓��M����Params <BR>
     * �@@�@@�I�u�W�F�N�g��List���擾����B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�莞��z���t�\�敪 = �@@1�F�莞��z���t�@@and<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     * �@@�@@�@@���t�J�n��(YYYYMMDDHH24MISS)�@@<= ���ݓ���(YYYYMMDDHH24MISS)�@@and<BR>
     * �@@�@@�@@���ݓ���(YYYYMMDD)�@@<= ���t�I����(YYYYMMDD)<BR>
     * <BR>
     * �����ݓ�����ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B<BR>
     * <BR>
     * <BR>
     * �@@�@@�m�\�[�g�����n<BR> 
     * �@@�@@�@@�\�������̏������J�e�S���[�R�[�h�̏����������R�[�h�̏���<BR> 
     * �@@ <BR>
     * �Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s���B<BR> 
     * �@@�@@�|�g�����M�����I�u�W�F�N�g�𐶐�����B <BR>
     * �@@�@@�@@�m�R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@����Row�F ���M����Params <BR>
     * �@@�@@�|���������g�����M�������A�g�����M������List�ɒǉ�����B<BR> 
     * <BR>
     * �R�j�@@�g�����M������List�����^�[������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getFixedBuyPossibleProductList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMutualFixedBuyPossibleDivProductList(String)";
        log.entering(STR_METHOD_NAME); 
        
        //�P�j�@@���M�����}�X�^�[�e�[�u�����������A�莞��z���t�\�Ȗ����̓��M����Params
        //�I�u�W�F�N�g��List���擾����B
        List l_lisRows = null;

        //���ݓ��t�̎擾
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
        String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

        //�莞��z���t�\�敪 = �@@1�F�莞��z���t�@@and
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���t�J�n��(YYYYMMDDHH24MISS)�@@<= ���ݓ���(YYYYMMDDHH24MISS)�@@and
        //���ݓ���(YYYYMMDD)�@@<= ���t�I����(YYYYMMDD)
        String l_strQueryString = "fixed_buy_possible_div = ? and institution_code = ? and " +
            "(to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and to_char(buy_end_date, 'YYYYMMDD') >= ?)";

        Object[] l_objQueryDataContainer =
            {WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE,
            l_strInstitutionCode,
            l_strFormatTime,
            l_strFormatDate};

        //�\�������̏������J�e�S���[�R�[�h�̏����������R�[�h�̏���
        String l_strSortCond = "indication_ranking, category_code, product_code";
        try 
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRows =
                l_QueryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDataContainer);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s���B
        List l_lisMutualFundProducts = new ArrayList();
        
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            int l_intLength = l_lisRows.size();
            try 
            {
                for (int i = 0; i < l_intLength; i++) 
                {
                    WEB3MutualFundProduct l_mutualFundProduct = 
                        new WEB3MutualFundProduct((MutualFundProductRow)l_lisRows.get(i));
                    
                    //���������g�����M�������A�g�����M������List�ɒǉ�����
                    l_lisMutualFundProducts.add(l_mutualFundProduct);
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //�R�j�@@�g�����M������List�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_lisMutualFundProducts;
    }

    /**
     * (get���M����)<BR>
     * ���M�������擾����B<BR>
     * <BR>
     * 1�j���M�����}�X�^�[�e�[�u�����������A���M����Params�I�u�W�F�N�g��Ԃ��B<BR>
     * [��������]<BR>
     * �،���ЃR�[�h�������D�،���ЃR�[�h�@@   and<BR>
     * �J�e�S���[�R�[�h�������D���M�����J�e�S���[�R�[�h<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�Anull��Ԃ��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strCategoryCode - (�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMutualFundProduct(
        String l_strInstitutionCode,
        String l_strCategoryCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualFundProduct(String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRows = null;
        //���M�����}�X�^�[�e�[�u�����������A���M����Params�I�u�W�F�N�g��Ԃ��B
        //[��������]
        //�،���ЃR�[�h�������D�،���ЃR�[�h�@@   and
        //�J�e�S���[�R�[�h�������D���M�����J�e�S���[�R�[�h
        String l_strQueryString = " institution_code = ? and category_code = ? ";

        Object[] l_objQueryDataContainers = {l_strInstitutionCode, l_strCategoryCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                MutualFundProductRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���������ʂ�0���̏ꍇ�Anull��Ԃ��B
        if (l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }

    /**
     * (get��ʓ��M�����J�e�S���[�R�[�h)<BR>
     * �w�肳�ꂽ�J�e�S���[�R�[�h����A��ʃJ�e�S���[�R�[�h���擾����B<BR>
     * <BR>
     * 1�j���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params���擾����B<BR>
     * �@@�m�����n<BR>
     * �@@�،���ЃR�[�h = �����D�،���ЃR�[�h<BR>
     * �@@�J�e�S���[�R�[�h = �����D�J�e�S���[�R�[�h<BR>
     * <BR>
     * 2)�擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�̏ꍇ<BR>
     * �@@2-1�j�擾�������M�����J�e�S���[Params�Dget�J�e�S���[�R�[�h�����^�[������B<BR>
     * <BR>
     * 3�j���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params���擾����B<BR>
     * �@@�m�����n<BR>
     * �@@�،���ЃR�[�h = �����D�،���ЃR�[�h<BR>
     * �@@�J�e�S���[�R�[�h = �擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h<BR>
     * <BR>
     * 4�j) 3�j�Ŏ擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�̏ꍇ<BR>
     * �@@4�j-1) get�J�e�S���R�[�h�����^�[������B<BR>
     * <BR>
     * 5�j) 3�j�Ŏ擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�ȊO�̏ꍇ<BR>
     * �@@5�j-1) get�e�J�e�S���R�[�h�����^�[������B<BR>
     * @@param l_strCategoryCode - (�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h<BR>
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getUpMutualFundProductCategoryCode(
        String l_strCategoryCode,
        String l_strInstitutionCode)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUpMutualFundProductCategoryCode(String, String)";
        log.entering(STR_METHOD_NAME);

        //1�j���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params���擾����B
        //�m�����n
        //�،���ЃR�[�h = �����D�،���ЃR�[�h
        //�J�e�S���[�R�[�h = �����D�J�e�S���[�R�[�h
        MutualFundProductCategoryParams l_mutualFundProductCategoryParams = null;
        MutualFundProductCategoryRow l_mutualFundProductCategoryRow = null;
        try
        {
            MutualFundProductCategoryPK l_mutualFundProductCategoryPK =
                new MutualFundProductCategoryPK(l_strInstitutionCode, l_strCategoryCode);
            l_mutualFundProductCategoryRow =
                MutualFundProductCategoryDao.findRowByPk(l_mutualFundProductCategoryPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_mutualFundProductCategoryParams =
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

        //2)�擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�̏ꍇ
        String l_strParentCategoryCode =
            l_mutualFundProductCategoryParams.getParentCategoryCode();
        if (WEB3StringTypeUtility.isEmpty(l_strParentCategoryCode))
        {
            //2-1�j�擾�������M�����J�e�S���[Params�Dget�J�e�S���[�R�[�h�����^�[������B
            log.exiting(STR_METHOD_NAME);
            return l_mutualFundProductCategoryParams.getCategoryCode();
        }

        //3�j���M�����J�e�S���[�e�[�u�����������A���M�����J�e�S���[Params���擾����B
        //�m�����n
        //�،���ЃR�[�h = �����D�،���ЃR�[�h
        //�J�e�S���[�R�[�h = �擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h
        try
        {
            MutualFundProductCategoryPK l_mutualFundProductCategoryPK =
                new MutualFundProductCategoryPK(l_strInstitutionCode, l_strParentCategoryCode);
            l_mutualFundProductCategoryRow =
                MutualFundProductCategoryDao.findRowByPk(l_mutualFundProductCategoryPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_mutualFundProductCategoryParams =
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

        //4�j) 3�j�Ŏ擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�̏ꍇ
        l_strParentCategoryCode =
            l_mutualFundProductCategoryParams.getParentCategoryCode();
        if (WEB3StringTypeUtility.isEmpty(l_strParentCategoryCode))
        {
            //4�j-1) get�J�e�S���R�[�h�����^�[������B
            log.exiting(STR_METHOD_NAME);
            return l_mutualFundProductCategoryParams.getCategoryCode();
        }
        //5�j) 3�j�Ŏ擾�������M�����J�e�S���[Params�Dget�e�J�e�S���[�R�[�h��null�ȊO�̏ꍇ
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strParentCategoryCode;
        }
    }

    /**
     * (get�莞��z���t�\�������X�g)<BR>
     * �莞��z���t�\�Ȗ����̃��X�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@���M�����}�X�^�[�e�[�u�����������A�莞��z���t�\�Ȗ����̓��M����Params<BR>
     * �@@�@@�I�u�W�F�N�g��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�莞��z���t�\�敪 = �@@1�F�莞��z���t�@@and<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     * �@@�@@�@@���t�J�n��(YYYYMMDDHH24MISS)�@@<= ���ݓ���(YYYYMMDDHH24MISS)�@@and<BR>
     * �@@�@@�@@���ݓ���(YYYYMMDD)�@@<= ���t�I����(YYYYMMDD) and<BR>
     * �@@�@@�@@�쐬���������R�[�h������ and(���P)<BR>
     * �@@�@@�@@�쐬�����J�e�S���R�[�h������(���Q)<BR>
     * <BR>
     * �����ݓ�����ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A<BR>
     * �@@�@@���ݓ������擾����B<BR>
     * <BR>
     * �@@�@@�m�\�[�g�����n<BR>
     * �@@�@@�@@�\�������̏������J�e�S���[�R�[�h�̏����������R�[�h�̏���<BR>
     * <BR>
     * ���P�D����.�����R�[�h�ꗗ==null�ȊO�̏ꍇ�A<BR>
     * �@@�@@�����R�[�h�ꗗ�Ń��[�v���u�����R�[�h in (�����R�[�h�̒l,�����R�[�h�̒l) and�v�̕�������쐬����B<BR>
     * ���Q�D����.�J�e�S���[���X�g==null�ȊO�̏ꍇ�A<BR>
     * �@@�@@�J�e�S���[���X�g�Ń��[�v���u�J�e�S���R�[�h in (�J�e�S���R�[�h�̒l,�J�e�S���R�[�h�̒l)�v<BR>
     * �@@�@@�̕�������쐬����B<BR>
     * <BR>
     * �Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s���B<BR>
     * �@@�@@�|�g�����M�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@����Row�F ���M����Params<BR>
     * �@@�@@�|���������g�����M�������A�g�����M������List�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�g�����M������List�����^�[������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProductCodes - (�����R�[�h�ꗗ)<BR>
     * �����R�[�h�ꗗ<BR>
     * @@param l_lisCategoryList - (�J�e�S���[���X�g)<BR>
     * �J�e�S���[���X�g<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getFixedBuyPossibleProductList(
        String l_strInstitutionCode,
        String[] l_strProductCodes,
        List l_lisCategoryList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyPossibleProductList"
            +"(String, String[], List)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���M�����}�X�^�[�e�[�u�����������A�莞��z���t�\�Ȗ����̓��M����Params
        //�I�u�W�F�N�g��List���擾����B
        List l_lisRows = null;

        //�m���������n
        //�莞��z���t�\�敪 = �@@1�F�莞��z���t�@@and
        //�،���ЃR�[�h = ����.�،���ЃR�[�h and
        //���t�J�n��(YYYYMMDDHH24MISS)�@@<= ���ݓ���(YYYYMMDDHH24MISS)�@@and
        //���ݓ���(YYYYMMDD)�@@<= ���t�I����(YYYYMMDD) and
        String l_strQueryString = " fixed_buy_possible_div = ? "
            + "and institution_code = ? "
            + "and (to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? "
            + "and to_char(buy_end_date, 'YYYYMMDD') >= ?) ";

        //�쐬���������R�[�h������ and(���P)
        //���P�D����.�����R�[�h�ꗗ==null�ȊO�̏ꍇ�A�����R�[�h�ꗗ�Ń��[�v���u�����R�[�h in
        //(�����R�[�h�̒l,�����R�[�h�̒l) and�v�̕�������쐬����B
        if (l_strProductCodes != null && l_strProductCodes.length > 0)
        {
            l_strQueryString = l_strQueryString + " and product_code in ( ";
            int l_intLength = l_strProductCodes.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strQueryString = l_strQueryString + l_strProductCodes[i];
                if (i != (l_intLength - 1))
                {
                    l_strQueryString = l_strQueryString + ",";
                }
            }
            l_strQueryString = l_strQueryString + ") ";
        }
        //�쐬�����J�e�S���R�[�h������ (���Q)
        //���Q�D����.�J�e�S���[���X�g==null�ȊO�̏ꍇ�A�J�e�S���[���X�g�Ń��[�v���u�J�e�S���R�[�h in
        //(�J�e�S���R�[�h�̒l,�J�e�S���R�[�h�̒l)�v�̕�������쐬����B
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            l_strQueryString = l_strQueryString + " and category_code in ( ";
            int l_intSize = l_lisCategoryList.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //��Q3098
                MutualFundProductCategoryRow l_mutualFundProductCategoryRow =
                    (MutualFundProductCategoryRow)l_lisCategoryList.get(i);
                l_strQueryString = l_strQueryString + l_mutualFundProductCategoryRow.getCategoryCode();
                if (i != (l_intSize - 1))
                {
                    l_strQueryString = l_strQueryString + ",";
                }
            }
            l_strQueryString = l_strQueryString + ") ";
        }

        //�����ݓ�����ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
        String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

        //�m�\�[�g�����n
        //�\�������̏������J�e�S���[�R�[�h�̏����������R�[�h�̏���
        String l_strSortCond = " indication_ranking, category_code, product_code ";

        Object[] l_objQueryDataContainer =
        {WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE,
        l_strInstitutionCode,
        l_strFormatTime,
        l_strFormatDate};

        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRows =
                l_QueryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDataContainer);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s���B
        //�|�g�����M�����I�u�W�F�N�g�𐶐�����B
        //�m�R���X�g���N�^�ɓn���p�����^�n
        //����Row�F ���M����Params
        //�|���������g�����M�������A�g�����M������List�ɒǉ�����B
        List l_lisMutualFundProducts = new ArrayList();

        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            int l_intLength = l_lisRows.size();
            try
            {
                for (int i = 0; i < l_intLength; i++)
                {
                    WEB3MutualFundProduct l_mutualFundProduct =
                        new WEB3MutualFundProduct((MutualFundProductRow)l_lisRows.get(i));

                    l_lisMutualFundProducts.add(l_mutualFundProduct);
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�R�j�@@�g�����M������List�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_lisMutualFundProducts;
    }
}
@
