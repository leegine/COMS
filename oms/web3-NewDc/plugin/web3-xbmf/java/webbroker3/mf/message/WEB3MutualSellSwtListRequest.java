head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����抷�ꗗ�Ɖ�N�G�X�g�N���X(WEB3MutualSellSwtListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[ 
                   2004/12/07 ������ (���u) �c�Ή�
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����抷�ꗗ�Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellSwtListRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_swt_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111830L;
    
    /**
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * �y�[�W���\���s��<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * ���M�\�[�g�L�[<BR>
     * <BR>
     * �Ώۍ���:�h�����h�A�h�]���z�h�A�h�]�����v�h�A�h������t���؎��ԁh<BR>
     */
    public WEB3MutualSortKey[ ] sortKeys;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellSwtListRequest.class);
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A887920269
     */
    public WEB3MutualSellSwtListRequest() 
    {
     
    }
 
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���抷�ꗗ�Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8879D023A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualSellSwtListResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * <BR>
     * �P�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00089 <BR>
     * �@@�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �Q�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00091 <BR>
     * �@@�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * �R)�@@���M�\�[�g�L�[�`�F�b�N<BR>
     * �@@�R�|�P)�@@this.���M�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00231 <BR>
     * �@@�R�|�Q)�@@this.���M�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00232 <BR>
     * �@@�R�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�R�|�R�|�P�j�@@�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00085 <BR>
     * �@@�@@�R�|�R�|�Q�j�@@�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�]���z�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�]�����v�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h������t���؎��ԁh<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00086 <BR>
     * �@@�@@�R�|�R�|�R�j�@@�����^�~��==null�̏ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00087 <BR>
     * �@@�@@�R�|�R�|�S�j�@@�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�����h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�~���h <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00088 <BR>
     * <BR>
     * @@roseuid 40A8878B02D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�v���y�[�W�ԍ��`�F�b�N
        
        //�P�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B"); 
        }
        
        //�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        // ������̕�����𔻒f����@@�\���������郆�[�e�B���e�B�E
         //�N���X(WEB3StringTypeUtility.java)
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�B"); 
        }
        
        // �Q�j�@@�y�[�W���\���s���`�F�b�N
        
        //�Q�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B"); 
        }
        
        //�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B"); 
        }
       
        // �R)�@@���M�\�[�g�L�[�`�F�b�N
        
        //�R�|�P)�@@this.���M�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B"); 
        }
        
        //�R�|�Q)�@@this.���M�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�R�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
         // int l_length = this.sortKeys.length;
        for (int i=0; i < this.sortKeys.length; i++)
        {
           WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
           
            //�R�|�R�|�P�j�@@�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
            if (l_mutualSortKey == null || WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B"); 
            }
            
            //�R�|�R�|�Q�j�@@�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B
             //�����E�]���z�E�]�����v�E������t���؎��ԁh
            if (!((l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.TAX_TYPE) || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.MARKET_VALUE) || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) ||
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂ��h�����h�A�h�]���z�h�A�h�]�����v�h�A�h������t���؎��ԁh" +
                    "�ȊO�̒l�ł���");     
            }
            
            // �@@�@@�R�|�R�|�R�j�@@�����^�~��==null�̏ꍇ�A��O���X���[����B
            if(WEB3StringTypeUtility.isEmpty(l_mutualSortKey.ascDesc))
            {
                log.debug("�����^�~�������w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�������w��ł��B");  
            }
            
            // �@@�@@�R�|�R�|�S�j�@@�����^�~�����ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B
            // �@@�@@�@@�@@�E�h�����h 
            // �@@�@@�@@�@@�E�h�~���h 
            if((!WEB3AscDescDef.ASC.equals(l_mutualSortKey.ascDesc))
                    && (!WEB3AscDescDef.DESC.equals(l_mutualSortKey.ascDesc)))
            {
                log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B"); 
            }
       }
       log.exiting(STR_METHOD_NAME);
    }
}
@
