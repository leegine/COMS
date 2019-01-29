head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������Ɖ�N�G�X�g(WEB3FeqExecuteReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[ 
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.464
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqReferenceTypeDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������������Ɖ�N�G�X�g)<BR>
 * �O�������������Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqExecuteReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceRequest.class);
    
    /**
     * (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * <BR>
     * 0�F�������Ɖ�<BR>
     * 1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j<BR>
     */
    public String referenceType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * �� �w��Ȃ��̏ꍇ�́Anull<BR>
     */
    public String productCode;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * �� �w��Ȃ��̏ꍇ�́Anull<BR>
     */
    public String marketCode;
    
    /**
     * (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * 3�F��菈����(�ꕔ����)<BR>
     * 4�F��菈����(�S������)<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ�́Anull<BR>
     */
    public String execType;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ�́Anull<BR>
     */
    public Date orderBizDate;
    
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
     * �O�������\�[�g�L�[�I�u�W�F�N�g�̔z��<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE3A08037A
     */
    public WEB3FeqExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�Ɖ�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�Ɖ�敪 == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�j�@@this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E"�������Ɖ�"<BR>
     * �@@�@@�E"��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j"<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * �Q�j�@@����ԋ敪�̃`�F�b�N<BR>
     * �@@this.����ԋ敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@this.����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E"�����"<BR>
     * �@@�@@�E"�ꕔ����"<BR>
     * �@@�@@�E"�S������"<BR>
     * �@@�@@�E"��菈����(�ꕔ����)"<BR>
     * �@@�@@�E"��菈����(�S������)"<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00626<BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�\�[�g�L�[ == null�̏ꍇ�A��O���X���[����<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.�\�[�g�L�[�̗v�f�� == 0�̏ꍇ�A��O���X���[���� <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�R�|�R�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�R�|�R�|�P�j�@@�\�[�g�L�[.validate()���\�b�h���R�[������B<BR>
     * �@@�@@�R�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@�E�O��������������.�����R�[�h<BR>
     * �@@�@@�@@�E�O��������������.��������敪<BR>
     * �@@�@@�@@�E�O��������������.�s��R�[�h<BR>
     * �@@�@@�@@�E�O��������������.�����敪<BR>
     * �@@�@@�@@�E�O��������������.���ϋ敪<BR>
     * �@@�@@�@@�E�O��������������.���s����<BR>
     * �@@�@@�@@�E�O��������������.��������<BR>
     * �@@�@@�@@�E�O��������������.��������<BR>
     * �@@�@@�@@�E�O��������������.������<BR>
     * �@@�@@�@@�E�O��������������.�����L������<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.�v���y�[�W�ԍ����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� == null<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00089<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� != ���l<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00090<BR>
     * �@@�@@�Ethis.�v���y�[�W�ԍ� <= 0<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.�y�[�W���\���s�����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� == null<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00091<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� != ���l<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00092<BR>
     * �@@�@@�Ethis.�y�[�W���\���s�� <= 0<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00617<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 429EC6F7020F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�Ɖ�敪�̃`�F�b�N
        //�P�|�P�j�@@this.�Ɖ�敪 == null�̏ꍇ�A��O���X���[����B
        if (this.referenceType == null)
        {
            log.debug("�Ɖ�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ɖ�敪�����w��ł��B" + this.referenceType);
        }
        
        //�P�|�Q�j�@@this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�E"�������Ɖ�"
        //�E"��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j"
        if (!(WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType)
            || WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType)))
        {
            log.debug("�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + this.referenceType);
        }
         
        //�Q�j�@@����ԋ敪�̃`�F�b�N
        //this.����ԋ敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.execType != null)
        {
            //�Q�|�P�j�@@this.����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            //�E"�����"�E"�ꕔ����"�E"�S������"�E"��菈����(�ꕔ����)"�E"��菈����(�S������)"
            if (!(WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(this.execType)))
            {
                log.debug("����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + 
                    this.referenceType);
            }
        }
        
        //�R�j�@@�\�[�g�L�[�̃`�F�b�N 
        //�R�|�P�j�@@this.�\�[�g�L�[ == null�̏ꍇ�A��O���X���[���� 
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        // this.�\�[�g�L�[�̗v�f�� == 0 or
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }  
        
        //�R�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        for (int i=0; i<this.sortKeys.length; i++)
        {
            //�R�|�Q�|�P�j�@@�\�[�g�L�[.validate()���\�b�h���R�[������B
            this.sortKeys[i].validate();
            //�R�|�Q�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
            //�ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O���X���[����B 
            //�E�O��������������.�����R�[�h 
            //�E�O��������������.��������敪 
            //�E�O��������������.�s��R�[�h 
            //�E�O��������������.�����敪 
            //�E�O��������������.���ϋ敪 
            //�E�O��������������.���s���� 
            //�E�O��������������.�������� 
            //�E�O��������������.�������� 
            //�E�O��������������.������ 
            //�E�O��������������.�����L������ 
            if (!(WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)))
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

        //�S�j�@@�v���y�[�W�ԍ��̃`�F�b�N 
        //�S�|�P�j�@@this.�v���y�[�W�ԍ����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A 
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
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
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

        //�T�j�@@�y�[�W���\���s���̃`�F�b�N 
        //�T�|�P�j�@@this.�y�[�W���\���s�����ȉ��̏����̂����ꂩ�ɊY������ꍇ�A
        
        //�Ethis.�y�[�W���\���s�� == null 
        //��O���X���[����B 
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
        return new WEB3FeqExecuteReferenceResponse(this);
    }
}
@
