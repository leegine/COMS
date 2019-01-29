head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������c���Ɖ�N�G�X�g(WEB3FeqBalanceReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬   
                 : 2005/08/01 �s�p(���u) ���r���[  
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O�������c���Ɖ�N�G�X�g)<BR>
 * �O�������c���Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBalanceReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_balanceReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceRequest.class);

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[�̔z��<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
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
     * @@roseuid 42CE3A0401F4
     */
    public WEB3FeqBalanceReferenceRequest() 
    {
     
    }
    
    /**
     * �G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     *   �P�|�Q�j�\�[�g�L�[�̗v�f�� == 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�j�\�[�g�L�[�̔z��̌����A<BR>
     * �@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B<BR>
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ�<BR>
     * �@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.�����R�[�h<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.���n�����R�[�h<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.�s��R�[�h<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.��������敪<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@�@@�@@�@@�E�O�������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�v���y�[�W�ԍ����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� == null<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00089<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� != ���l<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00090<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� <= 0<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�y�[�W���\���s�����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� == null<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00091<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� != ���l<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00092<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� <= 0<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00617<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42A7FF8B01E1
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g�L�[�̃`�F�b�N 
        //�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A��O���X���[����B 
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
                
        //�P�|2�jthis.�\�[�g�L�[�̗v�f�� == 0 or
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }        
        
        //�P�|3�j�\�[�g�L�[�̔z��̌����A 
        //�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B 
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //�P�|3�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B
            this.sortKeys[i].validate();

           //�P�|3�|�Q�j�\�[�g�L�[.�L�[���ڂ� 
            //�ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O���X���[����B 
            //�E�O�������c���Ɖ��.�����R�[�h 
            //�E�O�������c���Ɖ��.���n�����R�[�h 
            //�E�O�������c���Ɖ��.�s��R�[�h 
            //�E�O�������c���Ɖ��.��������敪 
            //�E�O�������c���Ɖ��.�T�Z�]���z(�c������) 
            //�E�O�������c���Ɖ��.�T�Z�]�����v(�c������) 
            if (!(WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B" + 
                    this.sortKeys[i].keyItem);
            }
        }
 
        //�Q�j�@@�v���y�[�W�ԍ��̃`�F�b�N 
        //�Q�|�P�j�@@this.�v���y�[�W�ԍ����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A 
        //��O���X���[����B 
        //�Ethis.�v���y�[�W�ԍ� == null 
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B" + this.pageIndex);
        }
        //�Ethis.�v���y�[�W�ԍ� != ���l 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B" + this.pageIndex);
        }
        
        //�Ethis.�v���y�[�W�ԍ� <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B" + this.pageIndex);
        }

        //�R�j�@@�y�[�W���\���s���̃`�F�b�N 
        //�R�|�P�j�@@this.�y�[�W���\���s�����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A 
        //��O���X���[����B 
        //�Ethis.�y�[�W���\���s�� == null 
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B" + this.pageSize);
        }
        
        //�Ethis.�y�[�W���\���s�� != ���l 
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B" + this.pageSize);
        }
        
        //�Ethis.�y�[�W���\���s�� <= 0 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B" + this.pageSize);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBalanceReferenceResponse(this);
    }

}
@
