head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���������ꗗ���N�G�X�g(WEB3AdminAccInfoInsiderInfoListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���C�g (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҂��q�l���������ꗗ���N�G�X�g)<BR>
 * �Ǘ��҂��q�l�������ҏ��ꗗ���N�G�X�g<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListRequest.class);
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
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
     * ���q�l���\�[�g�L�[<BR>
     * �Ώۍ��ځF<BR>
     *     ���X�R�[�h�A<BR>
     *     �ڋq�R�[�h�A<BR>
     *     �����R�[�h<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    
    /**
     * (validate)<BR>
     *���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     *<BR>
     *�P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     *�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *�@@�P�|�Q�j�@@�v�f����0�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     *�@@�P�|�R�j�@@�e�v�f�ɂ��āA�ȉ��̃`�F�b�N���s���B<BR>
     *�@@�@@�P�|�R�|�P�j�@@������3�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *�@@�@@�P�|�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     *<BR>
     *�Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     *�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *�@@�Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *�@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     *<BR>
     *�R�j�@@�����R�[�h�̃`�F�b�N<BR>
     *�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *�@@�R�|�P�j�@@������5�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     *�@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00815<BR>
     *<BR>
     *�S�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     *�@@�S�|�P�j�@@�\�[�g�L�[�������͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *�@@�S�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     *�@@�S�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B<BR>  
     *�@@�@@�@@�S�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B<BR> 
     *�@@�@@�@@�S�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.���X�R�[�h<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�ڋq�R�[�h<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�����R�[�h<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�֌W�R�[�h<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.������<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.��E��<BR>
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�o�^�󋵋敪<BR>
     *<BR>
     *�T�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     *�@@�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     *�@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     *�@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     *<BR>
     *�U�j�@@�y�[�W���\���s���`�F�b�N <BR>
     *�@@�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *�@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     *�@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //���X�R�[�h�����͂̏ꍇ�A��O���X���[����B
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h������");  
        }
        
        //�P�|�Q�j�@@�v�f����0�̏ꍇ�A��O���X���[����B<BR>
        if (branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, 
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�v�f����0�̏ꍇ");
        }
        
        //*�@@�P�|�R�j�@@�e�v�f�ɂ��āA�ȉ��̃`�F�b�N���s���B<BR>
        for (int i = 0; i < branchCode.length; i++)
        {
            if (WEB3StringTypeUtility.getByteLength(branchCode[i]) != 3)
            {
                
                //*�@@�@@�P�|�R�|�P�j�@@������3�łȂ��ꍇ�A��O���X���[����B<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h������3�łȂ�");
            }
            
            //�P�|�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(branchCode[i]))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h�����ȊO�̕���");
            }
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
        //     �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
        //     �Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
        if (accountCode != null && WEB3StringTypeUtility.getByteLength(accountCode) != 6)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����͂łȂ��ꍇ,������6�łȂ��ꍇ");
        }
        
        //*�@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
        if (accountCode != null && !WEB3StringTypeUtility.isDigit(accountCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����͂łȂ��ꍇ,�����ȊO�̕������܂܂��ꍇ");
        }
        
        //�R�j�@@�����R�[�h�̃`�F�b�N<BR>
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
        //�@@�R�|�P�j�@@������5�łȂ��ꍇ�A��O���X���[����B<BR>
        if (productCode != null && WEB3StringTypeUtility.getByteLength(productCode) != 5)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00439, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�����͂łȂ��ꍇ,������5�łȂ��ꍇ");
        }
        
        //�@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
        if (productCode != null && !WEB3StringTypeUtility.isDigit(productCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00815, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�����͂łȂ��ꍇ,�����ȊO�̕������܂܂��ꍇ");
        }
        
        //�S�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
        //�@@�S�|�P�j�@@�\�[�g�L�[�������͂̏ꍇ�A��O���X���[����B<BR>
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�������͂̏ꍇ");
        }
        
        //�@@�S�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
        if (sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ");
        }
        
        //�@@�S�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B<BR>  
        //�@@�@@�@@�S�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B<BR> 
        //�@@�@@�@@�S�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.���X�R�[�h<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.�ڋq�R�[�h<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.�����R�[�h<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.�֌W�R�[�h<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.������<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.��E��<BR>
        //�@@�@@�@@�@@ �����ҏ��ꗗUnit.�o�^�󋵋敪<BR>
        for (int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            
            if(!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.PRODUCT_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.RELATION_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.OFFICER_NAME.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.POST_NAME.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.REGIST_DIV.equals(sortKeys[i].keyItem)
                )
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ");
            }
        }
        
        //�T�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        //�@@�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
            log.debug("�v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //�@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR> 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������ȊO�̕������܂܂��");
        }
        
        //�@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��}�C�i�X�l");
        }
        
        //�U�j�@@�y�[�W���\���s���`�F�b�N <BR>
        //�@@�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s��������");
        }
        
        //�@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s�������ȊO�̕������܂܂��");
        }
        
        //�@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���}�C�i�X�l");
        }
        
    }

    /* (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoInsiderInfoListResponse(this);
    }
}
@
