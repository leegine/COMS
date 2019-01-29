head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������c���Ɖ�N�G�X�g(WEB3EquityBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
				   2006/08/29 ���r�@@(���u)�@@�d�l�ύX ���f��No.971
Revesion History : 2008/03/27 �k�v�u(���u)�@@�d�l�ύX ���f��No.1309
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �i���������c���Ɖ�N�G�X�g�j�B<BR>
 * <BR>
 * ���������c���Ɖ�N�G�X�g�N���X<BR>
 */
public class WEB3EquityBalanceReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceRequest.class);
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_balance_reference";
    
    /**
     * (�����R�[�h)<BR>
     *<BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     */
    public String productCode = null;
    
    /**
     * (�s��R�[�h)<BR>
     *<BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     */
    public String marketCode = null;
    
    /**
     * (�����敪�ꗗ)
     * <BR>
     * (�����敪�̔z��)<BR>
     * <BR>
     */
    public String[] taxTypeList;
    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3EquitySortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     *<BR>
     * �\�����������y�[�W�ʒu���w��@@<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     *<BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 4206C8AA00A8<BR>
     */
    public WEB3EquityBalanceReferenceRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�����R�[�h�̃`�F�b�N<BR>
     * �@@�����R�[�h �� null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�j�ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�����R�[�h �� ���l<BR>
     * �@@�@@�@@�E�����R�[�h.length != 5<BR>
     * <BR>
     * �Q�j�s��R�[�h�̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�i �s��R�[�h�̓`�F�b�N���Ȃ��B�j<BR>
     * <BR>
     * �R�j�����敪�ꗗ�̃`�F�b�N <BR>
     * �@@�R�|�P�j�����敪�ꗗ���v�f����0�̏ꍇ�A <BR>
     * �@@�@@�u�����敪�ꗗ�̗v�f�����O�ł��B�v�̗�O���X���[����B <BR>
     * �@@�R�|�Q�j�����敪�ꗗ�̔z��̌����A�J��Ԃ��Ĉȉ���PR�R�[�h�l�`�F�b�N���s���B<BR> 
     * �@@�@@�����敪�ꗗ���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A <BR>
     * �@@�@@�@@�u�����敪�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@�E��ʌ��� <BR>
     * �@@�@@�@@�@@�E������� <BR>
     * �@@�@@�@@�@@�E�X�g�b�N�I�v�V�������� <BR>
     * <BR>
     * 
     * �S�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�S�|�P�j�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�S�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�S�|�R�j�\�[�g�L�[�̔z��̌����A<BR>
     * �@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�S�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B<BR>
     * �@@�@@�S�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ�<BR>
     * �@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A<BR>
     * �@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E���������c���Ɖ��.�����R�[�h<BR>
     * �@@�@@�@@�@@�E���������c���Ɖ��.�����敪<BR>
     * �@@�@@�@@�@@�E���������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@�@@�@@�@@�E���������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     * <BR>
     * �T�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�T�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�T�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     * �U�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�U�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�U�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@<BR>
     * �@@�U�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * @@throws<BR>
     *  WEB3BaseException<BR>
     * @@roseuid 41B5701501F1<BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�����R�[�h�̃`�F�b�N");
        if (this.productCode != null)
        {
	        // ���l�`�F�b�N
            if (!WEB3StringTypeUtility.isNumber(this.productCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                    this.getClass().getName() + ".validate()");
            }
            
            // �����`�F�b�N
            if (this.productCode.length() != 5)
            {
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_00439,
	                this.getClass().getName() + ".validate()");
            }
        }
	    
	    log.debug("�����敪�ꗗ�̃`�F�b�N");
	    //�����敪�ꗗ���v�f����0�̏ꍇ�A 
	    //�u�����敪�ꗗ�̗v�f�����O�ł��B�v�̗�O���X���[����B
	    if(this.taxTypeList != null)
	    {
	        if (this.taxTypeList.length == 0)
	        {
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02630,
	                this.getClass().getName() + ".validate()");
	        }    
	        
		    //�����敪�ꗗ�̔z��̌����A�J��Ԃ��Ĉȉ���PR�R�[�h�l�`�F�b�N���s���B 
		    //�@@�����敪�ꗗ���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A 
		    //�u�����敪�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B 
		    //�@@�E��ʌ��� 
		    //�@@�E������� 
		    //�@@�E�X�g�b�N�I�v�V�������� 
		    for(int i = 0; i < this.taxTypeList.length; i++)
		    {
		        if (!(WEB3TaxTypeDef.NORMAL.equals(this.taxTypeList[i])
		            ||WEB3TaxTypeDef.SPECIAL.equals(this.taxTypeList[i])
		            ||WEB3TaxTypeDef.STOCK_OPTION.equals(this.taxTypeList[i])))
		            throw new WEB3BusinessLayerException(
		                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
		                this.getClass().getName() + ".validate()");
		    }
	    }
        log.debug("�\�[�g�L�[�̃`�F�b�N");
        
        // null�`�F�b�N
        if(this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        // �v�f���`�F�b�N
        int sortKeysCount = this.sortKeys.length;
        if(sortKeysCount == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        for(int i = 0 ; i < sortKeysCount ; i++)
        {
            // �\�[�g�L�[.validate()���R�[��
            sortKeys[i].validate();
            
            // �L�[���ڃ`�F�b�N
            if(!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.debug("�v���y�[�W�ԍ��`�F�b�N");
        // �v���y�[�W�ԍ���null�̏ꍇ
        if(this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageIndex= Integer.parseInt(this.pageIndex);
            if(l_intPageIndex <= 0)
            {
                // �v���y�[�W�ԍ����O�ȉ��̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // �v���y�[�W�ԍ��������ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + ".validate()");
        }

        log.debug("�y�[�W���\���s���`�F�b�N");
        // �y�[�W���\���s���`�F�b�N
        if(this.pageSize == null)
        {
            // �y�[�W���\���s����null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageSize= Integer.parseInt(this.pageSize);
            if(l_intPageSize <= 0)
            {
                // �y�[�W���\���s�����O�ȉ��̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + ".validate()");
            }
        }catch(NumberFormatException e)
        {
            // �y�[�W���\���s���������ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBalanceReferenceResponse(this);
    }
}
@
