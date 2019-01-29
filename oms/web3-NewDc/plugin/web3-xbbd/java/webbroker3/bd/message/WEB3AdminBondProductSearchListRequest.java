head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ�������N�G�X�g(WEB3AdminBondProductSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondProductSearchKeyItemDivDef;


/**
 * (�Ǘ��ҍ������ꗗ�������N�G�X�g)<BR>
 * �Ǘ��ҍ������ꗗ�������N�G�X�g
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchListRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductSearchListRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_list";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
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
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     */
    public WEB3BondSortKey[] sortKeys;
    
    /**
     * (��������)<BR>
     * �����ꗗ�����̏���
     */
    public WEB3AdminBondProductListConditionInfo conditionInfo;
    
    /**
     * @@roseuid 44E3363E008C
     */
    public WEB3AdminBondProductSearchListRequest() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N (�f�t�H���g�͔��s���̏���)<BR>
     * �@@�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A<BR> 
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�j�\�[�g�L�[.�L�[���ڂɔ��s���A�����R�[�h(WEB3)�A���ғ��@@�ȊO�����݂����ꍇ�A 
     * <BR>
     * �@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�R�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617�@@
     * 
     * @@throws WEB3BaseException�@@�@@
     * @@roseuid 44BC67E203A5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g�L�[�̃`�F�b�N (�f�t�H���g�͔��s���̏���)
        //�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A
        //�u�\�[�g�L�[��null�v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A 
        //�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�P�|�R�j�\�[�g�L�[.�L�[���ڂɔ��s���A�����R�[�h(WEB3)�A���ғ��@@�ȊO�����݂����ꍇ�A 
        //�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00086
        int l_intSortKeyLength = this.sortKeys.length;
        for(int i = 0; i< l_intSortKeyLength; i++)
        {
            if (!WEB3BondProductSearchKeyItemDivDef.ISSUE_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3BondProductSearchKeyItemDivDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3BondProductSearchKeyItemDivDef.MATURITY_DATE.equals(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
            
        //�Q�j�v���y�[�W�ԍ��`�F�b�N
        //�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
        //�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00089
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
        //�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�Q�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A 
        //�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�R�j�y�[�W���\���s���`�F�b�N
        //�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A 
        //�u�y�[�W���\���s����null�v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_02224
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
        //�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
        //�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B 
        //�@@class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�R�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A 
        //�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (create���X�|���X)<BR>
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     * @@roseuid 44BC67C702F8
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondProductSearchListResponse(this);
    }
    
}
@
