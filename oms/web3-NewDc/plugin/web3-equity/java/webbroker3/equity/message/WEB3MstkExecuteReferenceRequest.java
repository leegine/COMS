head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�N�G�X�g(WEB3MstkExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityOrderTypeDivisionDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�����������Ɖ�N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�N�G�X�g�N���X
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3MstkExecuteReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MstkExecuteReferenceRequest.class);
    
    /**
     * �iPTYPE�j�B
     */
    public final static  String PTYPE = "mstk_executeReference";
        
    /**
     * �iserialVersionUID�j�B
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * �i�Ɖ�敪�j�B<BR>
     * 0�F�������Ɖ�i�f�t�H���g�j <BR>
     * 1�F����ꗗ�i����\�Ȃ��̂̂ݕ\���j
     */
    public String referenceType;
    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
    /**
     * �i�����󋵋敪�j�B<BR>
     * <BR>
     * ��ʏ���\�����Ȃǃf�t�H���g�́u�w��Ȃ��v<BR>
     * null�F �w�薳��<BR>
     * 0�F�������@@1�F���ρ@@2�F�����ρ@@3�F�����
     */
    public String miOrderState;
    
    /**
     * �i�\�[�g�L�[�j�B<BR>
     * <BR>
     * �����~�j�����\�[�g�L�[�̈ꗗ<BR>
     * �Ώۍ��ځF�@@�����R�[�h�A�s��R�[�h�A�����敪�A��������
     */
    public WEB3MstkSortKey[] sortKeys;
    
    /**
     * �i�v���y�[�W�ԍ��j�B<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * �i�y�[�W���\���s���j�B<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s��
     */
    public String pageSize;
    
    /**
     * �i�����~�j�����������Ɖ�N�G�X�g�j�B<BR>
     * <BR>
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3MstkExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�Ɖ�敪��null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�Ɖ�敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00081<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�Ɖ�敪�����L�̒l�ȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�Ɖ�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�u0�F�������Ɖ�v<BR>
     * �@@�@@�@@�@@�E�u1�F����ꗗ(����\�Ȃ��̂̂ݕ\��)�v<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * �Q�j�@@�@@�����󋵋敪�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����󋵋敪��null���A<BR>
     * �@@�@@�@@�@@this.�����󋵋敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����󋵋敪������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * �@@�@@�@@�@@0�F�������@@1�F���ρ@@2�F�����ρ@@3�F�����<BR>
     * <BR>
     * �R�j�@@�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�@@�R�|�P�jthis.�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�@@�R�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�@@�R�|�R�jthis.�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�@@�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�����~�j�����������Ɖ���P��.�����R�[�h<BR>
     * �@@�@@�@@�����~�j�����������Ɖ���P��.�s��R�[�h<BR>
     * �@@�@@�@@�����~�j�����������Ɖ���P��.�����敪<BR>
     * �@@�@@�@@�����~�j�����������Ɖ���P��.��������<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �S�j�@@�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�@@�@@�@@this�D�v���y�[�W�ԍ����A���w��̏ꍇ�A<BR>
     * �@@�@@�@@�@@this�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�v���y�[�W�ԍ����A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ���null�@@�@@�@@�@@<BR>
     * �@@(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ��������@@�@@<BR>
     * �@@�@@(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ����O�@@�@@ �@@�@@�@@ <BR>
     * (�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�@@�@@�@@this.�y�[�W���\���s�����A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s����null<BR>
     * �@@(�u�y�[�W���\���s����null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s���������@@<BR>
     * �@@�@@�@@(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s�����O<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     *  (�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.exiting(STR_METHOD_NAME);
        
        //�P�j�@@�Ɖ�敪�`�F�b�N
        if (referenceType== null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "validate");
            
        }
        
        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(referenceType) 
            && !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(referenceType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "validate");
        }
        
        // �Q�j�@@�@@�����󋵋敪�`�F�b�N
         if (this.miOrderState != null
                && !WEB3EquityOrderTypeDivisionDef.ORDRING.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.EXECUTED.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.ORDERED.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.CANCELED.equals(this.miOrderState))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00816,
                this.getClass().getName() + "validate");
        }   
        
        // �R�j�@@�@@�\�[�g�L�[�`�F�b�N
        if (this.sortKeys == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "validate");
            
        }    
        
        if (sortKeys.length == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "validate");
            
        } 
        
        for (int i = 0;i < sortKeys.length;i++)
        {
           sortKeys[i].validate();
           if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.DEALINGTYPE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.ORDER_TIME.equals(this.sortKeys[i].keyItem))                   
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "validate");
            }
 
        
        }
        // �S�j�@@�@@�v���y�[�W�ԍ��`�F�b�N
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "validate");
        }        
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "validate");        
        }       
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "validate");        
        }       
        // �T�j�@@�@@�y�[�W���\���s���`�F�b�N
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "validate");
        
        }
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "validate");
        
        }
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "validate");
        
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * @@return WEB3GenResponse �����~�j�����������Ɖ�X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkExecuteReferenceResponse(this);
    }
}
@
