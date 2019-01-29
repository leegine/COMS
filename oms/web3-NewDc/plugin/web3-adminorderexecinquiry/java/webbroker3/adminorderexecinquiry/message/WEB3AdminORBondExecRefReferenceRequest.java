head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ���\�����N�G�X�g(WEB3AdminBondExecRefReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬    
Revesion History : 2007/07/9 ������(���u) �d�l�ύX���f��No.100
Revesion History : 2007/09/26 ���g(���u) ���f��No.108
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3BondExecRefUnitKeyItemDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���Ǘ��Ғ������Ɖ���\�����N�G�X�g)<BR>
 * ���Ǘ��Ғ������Ɖ���\�����N�G�X�g�N���X
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefReferenceRequest extends WEB3GenRequest 
{
    
    /**
    * PTYPE<BR>
    */
   public static final String PTYPE = "admin_or_bond_exec_ref_reference";

   /**
    * SerialVersionUID<BR>
    */
   public static final long serialVersionUID = 200608171156L;
   
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3AdminORBondExecRefReferenceRequest.class);
   
   /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     * ���ʂɍő�100���i�e�Аݒ�\�j
     */
    public String pageSize;
    
    /**
     * (���Ǘ��Ғ������Ɖ���������)<BR>
     * ���Ǘ��Ғ������Ɖ���������
     */
    public WEB3AdminORBondExecRefConditionInfo conditionInfo;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     */
    public WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys;
    
    
    /**
     * (���Ǘ��Ғ������Ɖ���\�����N�G�X�g)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 44B734170365
     */
    public  WEB3AdminORBondExecRefReferenceRequest() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N(�f�t�H���g�͔������̍~��)<BR>
     *�@@�@@�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A<BR> 
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231 <BR>
     * <BR>
     *�@@�@@�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A<BR> 
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_0023�Q <BR>
     * <BR>
     *�@@�@@�P�|�R�j�\�[�g�L�[�̔z��̌����A<BR> 
     * �@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B<BR> 
     *�@@�@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B<BR> 
     *�@@�@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ� <BR>
     * �@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A<BR> 
     * �@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@�E�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@�E�����R�[�h�iWEB3�j<BR>
     * �@@�@@�@@�@@�E�������<BR>
     * �@@�@@�@@�@@�E���ϋ敪<BR>
     * �@@�@@�@@�@@�E�󒍓���<BR>
     * �@@�@@�@@�@@�E������<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E���n����<BR>
     * �@@�@@�@@�@@�E��n��<BR>
     * �@@�@@�@@�@@�E���n��n��<BR>
     * �@@�@@�@@�@@�E���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086 <BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N<BR> 
     *�@@�@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00089 <BR> 
     * <BR>
     *�@@�@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     *�@@�@@�Q�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR> 
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N<BR> 
     *�@@�@@�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR> 
     * <BR>
     *�@@�@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>�@@ 
     * <BR>�@@ 
     *�@@�@@�R�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617
     * @@roseuid 44B734DB0265
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g�L�[�̃`�F�b�N(�f�t�H���g�͔������̍~��)
        //�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A
        //�u�\�[�g�L�[��null�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00231
        if(this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A
        //�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00232
        if(this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�P�|�R�j�\�[�g�L�[�̔z��̌����A 
        //�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B
        // �P�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B 
        // �P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ� 
        // �@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A
        // �@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        //   . �ڋq�R�[�h
        // �@@. �����R�[�h�iWEB3�j
        // �@@. �������
        // �@@. ���ϋ敪
        // �@@. �󒍓���
        // �@@. ������
        // �@@. ����
        // �@@. ���n����
        // �@@. ��n��
        // �@@. ���n��n��
        //   . ���X�R�[�h 
        // �@@�@@�@@class:�@@WEB3BusinessLayerException
        // �@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086     
        int l_intSortKeysLength = 0;

        l_intSortKeysLength = this.sortKeys.length;


        for(int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            if((!WEB3BondExecRefUnitKeyItemDivDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.SETTLE_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.ACCEPT_ORDER_TIMESTAMP.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_EXECUTION_DATE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_DELIVERY_DATE.equals(this.sortKeys[i].keyItem))
                &&!WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_EXECUTION_DATE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_DELIVERY_DATE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                &&!WEB3BondExecRefUnitKeyItemDivDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�Q�j�v���y�[�W�ԍ��`�F�b�N 
        //�@@�@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
        //   �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00089
        if(this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //this.�v���y�[�W�ԍ� != ���� 
        //�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //this.�v���y�[�W�ԍ� <= 0  
        //�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�y�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�R�j�y�[�W���\���s��
        //this.�y�[�W���\���s�� == null   
        //�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B 
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00091
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        //this.�y�[�W���\���s�� != ����
        //�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //this.�y�[�W���\���s�� <= 0
        //�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        //class: WEB3BusinessLayerException 
        //tag:   BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        
    }
    
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���Ǘ��Ғ������Ɖ���\�����X�|���X�𐶐����Ԃ�
     * @@return WEB3GenResponse
     * @@roseuid 44DA82A3000C
     */
    public WEB3GenResponse createResponse() 
    {
    	return new WEB3AdminORBondExecRefReferenceResponse(this);
    }
}
@
