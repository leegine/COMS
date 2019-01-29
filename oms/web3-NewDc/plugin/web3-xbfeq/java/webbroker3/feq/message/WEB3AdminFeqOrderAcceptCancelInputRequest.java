head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�ؓ��̓��N�G�X�g(WEB3AdminFeqOrderAcceptCancelInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
Revesion History : 2009/08/03 �Ԑi(���u)   ���f���@@No.506�Ή�
*/
package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO������������t����F�ؓ��̓��N�G�X�g)<BR>
 * �Ǘ��ҊO������������t����F�ؓ��̓��N�G�X�g�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelInputRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelInputRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public String accountCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public Date orderBizDate;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public String managementCode;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public String marketCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public String productCode;
    
    /**
     * (��t�敪)<BR>
     * ��t�敪<BR>
     * <BR>
     * 0�F������t��<BR>
     * 1�F���������<BR>
     * 2�F������t��<BR>
     * <BR>
     * �����w��̏ꍇ�́Anull�B
     */
    public String[] acceptDiv;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE39FB033C
     */
    public WEB3AdminFeqOrderAcceptCancelInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N <BR>
     * �@@�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�|�P�j�@@���p3byte�̐����łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N <BR>
     * �@@�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B <BR>
     * �@@�Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01043<BR>
     * �@@�Q�|�R�j�@@���X�R�[�h�ɓ��͂��Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00833<BR>
     * �i�ڋq�R�[�h�w��<BR>���̂݁A���X�R�[�h�K�{�j<BR>
     * <BR>
     * �R�j�@@�^�p�R�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B <BR>
     * �@@�R�|�P�j�@@5���̔��p�����łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_03163<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00090<BR>
     * �@@�S�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00091<BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00092<BR>
     * �@@�T�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * �U�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00231<BR>
     * �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00232<BR>
     * �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     *  �@@�@@��O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �@@�@@�@@�@@ �O��������������.�^�p�R�[�h <BR>
     * �@@�@@�@@�@@ �O��������������.�����ԍ�<BR>
     * �@@�@@�@@�@@ �O��������������.���X�R�[�h<BR>
     * �@@�@@�@@�@@ �O��������������.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@ �O��������������.��������敪<BR>
     * �@@�@@�@@�@@ �O��������������.��������<BR>
     * �@@�@@�@@�@@ �O��������������.���ϋ敪<BR>
     * �@@�@@�@@�@@ �O��������������.�s��R�[�h<BR>
     * �@@�@@�@@�@@ �O��������������.�����R�[�h<BR>
     * �@@�@@�@@�@@ �O��������������.�����敪<BR>
     *          �O��������������.������
     * @@throws WEB3BaseException
     * @@roseuid 42A5382D0227
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N  
        // �����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B          
        if (!WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            //�P�|�P�j�@@���p3byte�̐����łȂ��ꍇ�A��O���X���[����B
            int l_intCnt = WEB3StringTypeUtility.getByteLength(this.branchCode);
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.branchCode);
            if (l_intCnt != 3 || !l_blnFlag)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł�:" + this.branchCode); 
            }
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N  
        //�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            //�Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̃T�C�Y���s���ł�:" + this.accountCode); 
            }
            
            //�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̒l�������ȊO�̒l�ł�:" + this.accountCode); 
            }
            
            //�Q�|�R�j�@@���X�R�[�h�ɓ��͂��Ȃ��ꍇ�A��O���X���[����B
            //�i�ڋq�R�[�h�w�莞�̂݁A���X�R�[�h�K�{�j
            if (WEB3StringTypeUtility.isEmpty(this.branchCode)) 
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h�����w��ł��B" ); 
            }            
        }
        
        //�R�j�@@�^�p�R�[�h�̃`�F�b�N 
        //�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(this.managementCode))
        {
            //�R�|�P�j�@@5���̔��p�����łȂ��ꍇ�A��O���X���[����B
            int l_intCnt = this.managementCode.length();
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.managementCode);
            if (l_intCnt != 5 || !l_blnFlag)
            {
                log.debug("�^�p�R�[�h��5���̔��p�����ł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�^�p�R�[�h��5���̔��p�����ł͂���܂���B" + this.managementCode); 
            }
        }

        //�S�j�@@�v���y�[�W�ԍ��`�F�b�N  
        //�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł�:" + this.pageIndex); 
        }

        //�S�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł�:" + this.pageIndex); 
        }
        
        //�T�j�@@�y�[�W���\���s���`�F�b�N  
        //�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B"); 
        }
        
        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł�:" + this.pageSize); 
        }
        
        //�T�|�R�j�@@0�ȉ��̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł�:" + this.pageSize); 
        } 

        //�U�j�@@�\�[�g�L�[�̃`�F�b�N  
        //�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B"); 
        }
        
        //�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B"); 
        }
        
        //�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i ++)
        {
            WEB3ForeignSortKey l_key = sortKeys[i];
            if (l_key != null)
            {
                //�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
                l_key.validate();
                
                //�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
                //      �O��������������.�^�p�R�[�h  
                //      �O��������������.�����ԍ� 
                //      �O��������������.���X�R�[�h 
                //      �O��������������.�ڋq�R�[�h 
                //      �O��������������.��������敪 
                //      �O��������������.�������� 
                //      �O��������������.���ϋ敪 
                //      �O��������������.�s��R�[�h 
                //      �O��������������.�����R�[�h 
                //      �O��������������.�����敪 
                //      �O��������������.������
                if (!(WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_NO.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(l_key.keyItem)))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł�:" + l_key.keyItem); 
                }
            }
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
        return new WEB3AdminFeqOrderAcceptCancelInputResponse(this);
    }
}
@
