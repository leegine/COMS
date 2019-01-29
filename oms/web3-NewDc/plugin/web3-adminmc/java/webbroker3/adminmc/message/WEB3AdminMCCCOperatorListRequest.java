head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : �Ǘ��҃��j���[����CC���ڰ��ꗗظ���(WEB3AdminMCCCOperatorListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 �͌d�� (���u) �V�K�쐬
*/


package webbroker3.adminmc.message;

import webbroker3.adminmc.define.WEB3AdminMCCCOperatorRegistUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��ꗗظ���)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��ꗗظ���<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h<BR>
     */
    public String operatorCode;
    
    /**
     * (�I�y���[�^��)<BR>
     * �I�y���[�^��<BR>
     */
    public String operatorName;
    
    /**
     * (��s�����\�敪)<BR>
     * ��s�����\�敪<BR>
     * <BR>
     * 0�F�@@�s��<BR>
     * 1�F�@@�\<BR>
     */
    public String accountOrderDiv;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String departmentCode;
    
    /**
     * (�G���[��)<BR>
     * �G���[��<BR>
     * <BR>
     * �����O�C���G���[��<BR>
     * <BR>
     */
    public String errorCount;
    
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
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41986426034B
     */
    public WEB3AdminMCCCOperatorListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00834           <BR>
     * <BR>
     * �Q�j�@@�I�y���[�^���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�o�C�g����40byte���傫���ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01194              <BR>
     * <BR>
     * �R�j�@@�G���[�񐔂̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01211           <BR>
     * �@@�R�|�Q�j�@@�L���l�� 0�`9 �͈͓̔��łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01212             <BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092             <BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * �U�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B <BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.���X�R�[�h <BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�I�y���[�^�R�[�h <BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.��s�����敪<BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�����R�[�h<BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�G���[��<BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�X�V����<BR>
     * �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�X�V�҃R�[�h<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_00086             <BR>
     * <BR>
     * @@roseuid 417E22190037
     */
    public void validate() throws  WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        // �@@�P�|�P�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>

        if ((this.branchCode != null) && (!"".equals(this.branchCode)) && WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error("�u���X�R�[�h�̕�������3byte�łȂ��v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �Q�j�@@�I�y���[�^���̃`�F�b�N<BR>
        // �@@�Q�|�P�j�@@�o�C�g����40byte���傫���ꍇ�A��O���X���[����B<BR>
        if ((this.operatorName != null) && (!"".equals(this.operatorName)) && WEB3StringTypeUtility.getByteLength(this.operatorName) > 40)
        {
            log.error("�u�I�y���[�^���̃o�C�g����40byte���傫���v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01194,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

        // �R�j�@@�G���[�񐔂̃`�F�b�N<BR>
        // �@@�R�|�P�j�@@�����łȂ��ꍇ�A��O���X���[����B<BR>
        if ((this.errorCount != null) && (!"".equals(this.errorCount)) && !WEB3StringTypeUtility.isNumber(this.errorCount))
        {
            log.error("�u�G���[�񐔂������łȂ��v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01211,
                this.getClass().getName() + STR_METHOD_NAME);        
        }
        
        // �@@�R�|�Q�j�@@�L���l�� 0�`9 �͈͓̔��łȂ��ꍇ�A��O���X���[����B<BR>
        if ((this.errorCount != null) && (!"".equals(this.errorCount)) && ((Double.parseDouble(this.errorCount) < 0) || (Double.parseDouble(this.errorCount) > 9)))
        {
            log.error("�u�G���[�񐔂̗L���l�� 0�`9 �͈͓̔��łȂ��v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01212,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
        
        // �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        // �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        // �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" �v���y�[�W�ԍ��������ȊO �B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }        
        // �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.error(" �v���y�[�W�ԍ����}�C�i�X�l�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }         

        // �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
        // �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error(" �y�[�W���\���s���������� �B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME);          
        }                 
        // �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" �y�[�W���\���s���������ȊO�̕������܂܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }         
        // �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
        if (Long.parseLong(this.pageSize) <= 0)
        {
             //��O
             log.error("�y�[�W���\���s�����}�C�i�X�l�B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                 this.getClass().getName() + STR_METHOD_NAME);         
        }        

        // �U�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
        // �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
        if (this.sortKeys == null)
        {
            //��O
            log.error(" �\�[�g�L�[�������͂ł��� �B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME);               
        }

        // �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
        if (this.sortKeys.length == 0)
        {
            log.error(" �\�[�g�L�[�̗v�f����0 ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME);
         
        }        
        // �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
        // �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
        // �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B <BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.���X�R�[�h <BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�I�y���[�^�R�[�h <BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.��s�����敪<BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�����R�[�h<BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�G���[��<BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�X�V����<BR>
        // �@@�@@�@@�@@ CC�I�y���[�^�o�^���.�X�V�҃R�[�h<BR>
        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            this.sortKeys[i].validate();
            if (!WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(this.sortKeys[i].keyItem))
            {
                log.error(" �\�[�g�L�[.�L�[���ڂ�����`�̒l�ł��� ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
        }

        log.exiting(STR_METHOD_NAME);    
    }

    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41986426038A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorListResponse(this);
    }
}
@
