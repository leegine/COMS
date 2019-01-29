head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ�N�G�X�g(WEB3BondExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���� (���u) �V�K�쐬
Revesion History : 2006/07/10 ���n�m (���u) ���f��No.191
*/
package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondExecuteReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondProductDivDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���������Ɖ�N�G�X�g)<BR>
 * ���������Ɖ�N�G�X�g
 * 									
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_execute_reference";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (�Ɖ�敪)<BR>
     * �Ɖ�敪 <BR>
     * <BR>
     * 0�F�������Ɖ� <BR>
     * 1�F����ꗗ <BR>
     */
    public String referenceType;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F���ׂĂ̖���<BR>
     * 2�F�O���������̂�<BR>
     * 3�F������(�l���������܂ށj<BR>
     * 4�F������(�l���������܂܂Ȃ��j<BR>
     * 5�F�l�������̂�<BR>
     * <BR>
     * ��null�̏ꍇ�A�u1�F���ׂĂ̖����v�Ƃ���B<BR>
     */
    public String productDiv = WEB3BondProductDivDef.ALL_PRODUCT;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3BondSortKey[] sortKeys;
    
    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@�Ɖ�敪�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@this.�Ɖ�敪 == null�̏ꍇ�A<BR> 
     * �@@�@@�u�Ɖ�敪�����w��v�̗�O���X���[����B <BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�j�@@this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�u�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�E"�������Ɖ�" <BR>
     * �@@�@@�@@�E"����ꗗ" <BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00082<BR>
     * <BR>
     * �Q�j�\�[�g�L�[�̃`�F�b�N<BR> 
     * �@@�Q�|�P�j�\�[�g�L�[ == null�̏ꍇ�A<BR> 
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�Q�|�R�j�\�[�g�L�[�̔z��̌����A <BR>
     * �@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B <BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B <BR>
     * �@@�@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ� <BR>
     * �@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A <BR>
     * �@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E���������Ɖ��.������ <BR>
     * �@@�@@�@@�@@�E���������Ɖ��.����敪 <BR>
     * �@@�@@�@@�@@�E���������Ɖ��.���ϋ敪 <BR>
     * �@@�@@�@@�@@�E���������Ɖ��.�������� <BR>
     * �@@�@@�@@�@@�E���������Ɖ��.������� <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR> 
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�R�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * �@@ <BR>
     * �@@�S�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * �T�j���i�敪�̃`�F�b�N<BR>
     * �@@this.���i�敪��null�̏ꍇ�A��`�l�̃`�F�b�N���s���B<BR>
     * �@@�@@this.���i�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�u���i�敪������`�l�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�P�F���ׂĂ̖���<BR>
     * �@@�@@�@@�@@�Q�F�O���������̂�<BR>
     * �@@�@@�@@�@@�R�F�������i�l���������܂ށj<BR>
     * �@@�@@�@@�@@�S�F�������i�l���������܂܂Ȃ��j<BR>
     * �@@�@@�@@�@@�T�F�l�������̂�<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01068<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
		//�P�j�@@�Ɖ�敪�̃`�F�b�N 
		//�P�|�P�j�@@this.�Ɖ�敪 == null�̏ꍇ�A 
		//�u�Ɖ�敪�����w��v�̗�O���X���[����B 
		//�P�|�Q�j�@@this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A 
		//�u�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�v�̗�O���X���[����B 
		//�E"�������Ɖ�" 
		//�E"����ꗗ" 
        if (referenceType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�����w��ł��B");
        }
        else if (!WEB3BondReferenceTypeDef.EXECUTE_REFERENCE.equals(referenceType) 
    		&& !WEB3BondReferenceTypeDef.CANCEL_LIST.equals(referenceType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //�Q�j�\�[�g�L�[�̃`�F�b�N 
		//�Q�|�P�j�\�[�g�L�[ == null�̏ꍇ�A 
		//�u�\�[�g�L�[��null�v�̗�O���X���[����B 
        if (sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
		//�Q�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A 
		//�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B
        else if (sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�Q�|�R�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B 
        for (int i = 0; i < sortKeys.length; i++)
    	{
        	//�Q�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B 
        	sortKeys[i].validate();
        	
			//�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ� 
			//�@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A 
			//�@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
			//�@@�@@�@@�@@�E���������Ɖ��.������ 
			//�@@�@@�@@�@@�E���������Ɖ��.����敪 
			//�@@�@@�@@�@@�E���������Ɖ��.���ϋ敪 
			//�@@�@@�@@�@@�E���������Ɖ��.�������� 
			//�@@�@@�@@�@@�E���������Ɖ��.������� 
        	if (!WEB3BondExecuteReferenceDetailUnitDef.PRODUCT_NAME.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.STATE_DIV.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.SETTLE_DIV.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.ORDER_DATE.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.EXECUTION_STATE.equals(sortKeys[i].keyItem))
        	{
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        	}       	
    	}
        
        //�R�j�v���y�[�W�ԍ��`�F�b�N 
		//�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
		//�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B 
        if (pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
		//�@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
		//�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B 
        else if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
		//�@@�R�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A 
		//�@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
        else if (Integer.parseInt(pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        //�S�j�y�[�W���\���s���`�F�b�N 
		//�@@�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A 
		//�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B 
        
        if (pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
		//�@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
		//�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B 
        else if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
		
        //�@@�S�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A 
		//�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        else if (Integer.parseInt(pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�T�j���i�敪�̃`�F�b�N
        //this.���i�敪��null�̏ꍇ�A��`�l�̃`�F�b�N���s���B
        //�@@this.���i�敪���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�u���i�敪������`�l�v�̗�O���X���[����B
        //�@@�@@�@@�P�F���ׂĂ̖���
        //�@@�@@�@@�Q�F�O���������̂�
        //�@@�@@�@@�R�F�������i�l���������܂ށj
        //�@@�@@�@@�S�F�������i�l���������܂܂Ȃ��j
        //�@@�@@�@@�T�F�l�������̂�
        if (productDiv == null)
        {
            //��null�̏ꍇ�A�u1�F���ׂĂ̖����v�Ƃ���B
            productDiv = WEB3BondProductDivDef.ALL_PRODUCT;
        }
        else
        {
            if (!WEB3BondProductDivDef.ALL_PRODUCT.equals(productDiv)
                && !WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(productDiv))
            {
                log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */ 
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondExecuteReferenceResponse(this);
    }
}
@
