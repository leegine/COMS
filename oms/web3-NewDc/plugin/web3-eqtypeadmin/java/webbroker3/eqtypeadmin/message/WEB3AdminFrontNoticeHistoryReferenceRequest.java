head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�N���X (WEB3AdminFrontNoticeHistoryReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.119
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.121
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�N���X<BR>
 */
public class WEB3AdminFrontNoticeHistoryReferenceRequest extends WEB3GenRequest 
{
   
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_front_Notice_History_Reference";

	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryReferenceRequest.class);

   /**
    * �s��R�[�h<BR>
    */
   public String convertMarketCode;
   
   /**
    * �f�[�^��ʃR�[�h<BR>
    */
   public String dataClassCode;
   
   /**
    * ���X�R�[�h<BR>
    */
   public String branchCode;
   
   /**
    * ���z�T�[�oNo<BR>
    */
   public String virtualServerNumber;
   
   /**
    * ����(�ڋq)�R�[�h<BR>
    */
   public String accountCode;
   
   /**
    * �����R�[�h<BR>
    */
   public String productCode;
   
   /**
    * �ʒm��M���t<BR>
    */
   public String createdTimestamp;
   
   /**
    * �ʒm��M����From<BR>
    * �iHHMMSS�j<BR>
    */
   public String createdTimestampFrom;
   
   /**
    * �ʒm��M����To<BR>
    * �iHHMMSS�j<BR>
    */
   public String createdTimestampTo;
   
   /**
    * �y�[�W���\���s��<BR>
    */
   public String pageSize;
   
   /**
    * �\���y�[�W�ԍ�<BR>
    */
   public String pageIndex;

   /**
    * �\�[�g�L�[<BR>
    */
   public WEB3AdminFrontSortKey sortKeys[];
   
   /**
    * �����^�C�v<BR>
    */
   public String productType;
   
   /**
    * @@roseuid 42FB2C1400E9
    */
   public WEB3AdminFrontNoticeHistoryReferenceRequest() 
   {
    
   }
   
   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�s��R�[�h�`�F�b�N<BR>
    * �@@�P�|�P�jthis.�s��R�[�h == <BR>
    * null�̏ꍇ�A�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
    * <BR>
    * �Q�j�����^�C�v�`�F�b�N<BR>
    * �@@�@@�Q�|�P�jthis.�����^�C�v == null�̏ꍇ�A�u�����^�C�v��null�v�̗�O���X���[����B<BR>
    *  �@@�@@class:WEB3BusinessLayerException<BR>
    *�@@�@@  tag:BUSINESS_ERROR_01109<BR>
    * �@@�@@�Q�|�Q�jthis.�����^�C�v != 1:�����A���Athis.�����^�C�v != 6:�敨�I�v�V�����̏ꍇ�B<BR>
    * �@@�@@�@@�@@�u�����^�C�v�G���[�v�̗�O���X���[����<BR>
    *  �@@�@@class:WEB3BusinessLayerException<BR>
    *�@@�@@  tag:BUSINESS_ERROR_02395<BR>
    * <BR>
    * �R�j���X�R�[�h�ꗗ�`�F�b�N<BR>
    *    this.���X�R�[�h�ꗗ != null�̏ꍇ�A�v�f�����A�ȉ��̃`�F�b�N���s���B<BR>
    * �@@�@@�R�|1�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
    * �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
    * �@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
    * �@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
    * <BR>
    * �S�j�ڋq�R�[�h�`�F�b�N<BR>
    * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
    * �@@�S�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
    * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6<BR>
    * <BR>
    * �T�j�����R�[�h�`�F�b�N<BR>
    * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
    * �@@�T�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
    * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�����^�C�v��1:�����̏ꍇ�Athis.�����R�[�h.length != 5<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�����^�C�v��6:�敨�I�v�V�����̏ꍇ�A<BR>
    * �@@�@@�@@�@@�@@�@@�@@�@@�@@this.�����R�[�h.length != 9<BR>
    * <BR>
    * �U�j�ʒm��M���t�`�F�b�N<BR>
    * �@@�U�|�P�jthis.�ʒm��M���t == <BR>
    * null�̏ꍇ�A�u�ʒm��M���t��null�v�̗�O���X���[����B<BR>
    * �@@<BR>
    * �V�j�ʒm��M����From�`�F�b�N<BR>
    * �@@this.�ʒm��M����From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
    * �@@�V�|�P�jthis.�ʒm��M����From���ȉ��̏����ɊY������ꍇ�A<BR>
    * �@@�@@�@@�@@�@@�u�ʒm��M����From�G���[�v�̗�O���X���[����B<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ʒm��M����From != ���l<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ʒm��M����From.length != 6<BR>
    * <BR>
    * �W�j�ʒm��M����To�`�F�b�N<BR>
    * �@@this.�ʒm��M����To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
    * �@@�W�|�P�jthis.�ʒm��M����To���ȉ��̏����ɊY������ꍇ�A<BR>
    * �@@�@@�@@�@@�@@�u�ʒm��M����To�G���[�v�̗�O���X���[����B<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ʒm��M����To != ���l<BR>
    * �@@�@@�@@�@@�@@�@@�Ethis.�ʒm��M����To.length != 6<BR>
    * <BR>
    * �X�j�\�[�g�L�[�`�F�b�N <BR>
    * �@@�X�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
    * �@@�@@�X�|�P�|�P�j�\�[�g�L�[.validate()���R�[������B <BR>
    * <BR>
    * �P�O�j�v���y�[�W�ԍ��`�F�b�N <BR>
    * �@@�P�O�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>*
    * <BR>
    * �@@�P�O�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
    * <BR>
    * �@@�P�O�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
    * <BR>
    * �P�P�j�y�[�W���\���s���`�F�b�N <BR>
    * �@@�P�P�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
    * <BR>
    * �@@�P�P�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
    * �@@ <BR>
    * �@@�P�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
    * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
    * @@roseuid 42D1F2DD00EE
    */

   public void validate() throws WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);

		final int length_check0 = 0;
		final int length_check3 = 3;
		final int length_check5 = 5;
		final int length_check6 = 6;
        final int length_check9 = 9;

		int branchCode_length = WEB3StringTypeUtility.getByteLength(this.branchCode);
		int accountCode_length = WEB3StringTypeUtility.getByteLength(this.accountCode);
		int productCode_length = WEB3StringTypeUtility.getByteLength(this.productCode);
		int createdTimestampFrom_length = WEB3StringTypeUtility.getByteLength(this.createdTimestampFrom);
		int createdTimestampTo_length = WEB3StringTypeUtility.getByteLength(this.createdTimestampTo);
		int pageIndex_length = WEB3StringTypeUtility.getByteLength(this.pageIndex);
		int pageSize_length = WEB3StringTypeUtility.getByteLength(this.pageSize);
        int pageIndex_math = 0;
        int pageSize_math = 0;
        int l_intSortkeysLength  = 0;
       
		// �P�j�u�s��R�[�h��null�v�̗�O���X���[����
		if (this.convertMarketCode == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00443,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        // �Q�j�����^�C�v�`�F�b�N�B
        // �Q�|�P�jthis.�����^�C�v == null�̏ꍇ�A�u�����^�C�v��null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.productType))
        {
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        //�Q�|�Q�jthis.�����^�C�v != 1:�����A���Athis.�����^�C�v != 6:�敨�I�v�V�����̏ꍇ�A
        //�u�����^�C�v�G���[�v�̗�O���X���[����B
        if (!(Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            &&!(Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType)))
        {
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_02395,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		// �R�j�u���X�R�[�h��null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.branchCode != null)
		{
			// �u���X�R�[�h�G���[�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(branchCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01729,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			// �u���X�R�[�h�G���[�v�̗�O���X���[����(�����ᔽ)
			if (branchCode_length != length_check3)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00834,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// �S�j�u����(�ڋq)�R�[�h��null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.accountCode != null)
		{
			// �u����(�ڋq)�R�[�h�G���[�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(accountCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01043,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
				
			// �u����(�ڋq)�R�[�h�G���[�v�̗�O���X���[����(�����ᔽ)
			if (accountCode_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00836,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// �T�j�u�����R�[�h��null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.productCode != null)
		{
			// �u�����R�[�h�G���[�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(productCode))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00815,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
					
			// �u�����R�[�h�G���[�v�̗�O���X���[����(�����ᔽ)
            //  this.�����^�C�v��1:�����̏ꍇ�Athis.�����R�[�h.length != 5
            if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            {
                if (productCode_length != length_check5)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            //this.�����^�C�v��6:�敨�I�v�V�����̏ꍇ�Athis.�����R�[�h.length != 9
            if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType))
            {
                if (productCode_length != length_check9)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
		
		}

		// �U�j�u�ʒm��M���t��null�v�̗�O���X���[����
		if (this.createdTimestamp == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02217,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		// �V�j�u�ʒm��M����From��null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.createdTimestampFrom != null)
		{
			// �u�ʒm��M����From�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(createdTimestampFrom))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02218,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
						
			// �u�ʒm��M����From�v�̗�O���X���[����(�����ᔽ)
			if (createdTimestampFrom_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02218,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

		// �W�j�u�ʒm��M����To��null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.createdTimestampTo != null)
		{
			// �u�ʒm��M����To�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(createdTimestampTo))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02219,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
							
			// �u�ʒm��M����To�v�̗�O���X���[����(�����ᔽ)
			if (createdTimestampTo_length != length_check6)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02219,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}

        //�X�j�\�[�g�L�[�`�F�b�N  
        //�@@�X�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B  
        //�@@�@@�X�|�P�|�P�j�\�[�g�L�[.validate()���R�[������B
        if (this.sortKeys != null)
        {
            for (int i = 0; i < sortKeys.length; i++)
            {
                WEB3AdminFrontSortKey l_adminFrontSortKey = sortKeys[i];
                l_adminFrontSortKey.validate();
            }
        }

		// �P�O�j�u�v���y�[�W�ԍ���null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.pageIndex != null)
		{
			// �u�v���y�[�W�ԍ��G���[�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(pageIndex))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00090,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            // int�^�ɕϊ�
            pageIndex_math = Integer.parseInt(pageIndex);
				
			// �u�v���y�[�W�ԍ��G���[�v�̗�O���X���[����(����0�ȉ��̎�)
			if (pageIndex_math <= length_check0)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00616,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
        // null�G���[
        else{
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

		// �P�P�j�u�y�[�W���\���s����null�v�ȊO�̂Ƃ��`�F�b�N
		if (this.pageSize != null)
		{
			// �u�y�[�W���\���s���G���[�v�̗�O���X���[����(���l�ȊO)
			if (!WEB3StringTypeUtility.isNumber(pageSize))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00092,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            // int�^�ɕϊ�
            pageSize_math = Integer.parseInt(pageSize);                    

			// �u�y�[�W���\���s���G���[�v�̗�O���X���[����(����0�ȉ��̎�)
			if (pageSize_math <= length_check0)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00617,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
       // null�G���[
       else{
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

		log.exiting(STR_METHOD_NAME);
    }

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse() {
       // TODO �����������ꂽ���\�b�h�E�X�^�u
       return new WEB3AdminFrontNoticeHistoryReferenceResponse(this);
    }
}
@
