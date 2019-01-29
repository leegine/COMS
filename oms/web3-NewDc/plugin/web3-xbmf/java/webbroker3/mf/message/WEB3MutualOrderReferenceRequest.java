head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������Ɖ�N�G�X�g�N���X(WEB3MutualOrderReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3ReferenceDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�������Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualOrderReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121115L;
    
    /**
     * �Ɖ�敪<BR>
     * <BR>
     * 0:�����Ɖ�@@1:����ꗗ<BR>
     */
    public String referenceType;
    
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
     * �Ώۍ���:�h�����h�A�h�����h�A�h���������h<BR>
     */
    public WEB3MutualSortKey[]  sortKeys;

    /**
     * (���M�E�O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪 <BR>
     * <BR>
     * 0:���M�̂� <BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceRequest.class);
    
    /**
     * (���M�����Ɖ�N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A5AA0376
     */
    public WEB3MutualOrderReferenceRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�����Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A5B702AB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualOrderReferenceResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�P�|�P)�@@this.�Ɖ�敪��null�̏ꍇ�A��O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00081 <BR>
     * �@@�P�|�Q)�@@this.�Ɖ�敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h�����Ɖ�h<BR>
     * �@@�@@�@@�@@�E�h����ꗗ�h<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00082 <BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00089 <BR>
     * �@@�Q�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00091 <BR>
     * �@@�R�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * �S)�@@���M�\�[�g�L�[�`�F�b�N<BR>
     * �@@�S�|�P)�@@this.���M�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00231 <BR>
     * �@@�S�|�Q)�@@this.���M�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00232 <BR>
     * �@@�Q�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�@@�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00085 <BR>
     * �@@�@@�Q�|�R�|�Q�j�@@�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�h���������h<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00086 <BR>
     * @@roseuid 40A9A5E001D0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
       
        //�P)�@@�Ɖ�敪�`�F�b�N 
        
        //�P�|�P)�@@this.�Ɖ�敪��null�̏ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("�Ɖ�敪�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�����w��ł��B");
        }
        
        //�P�|�Q)�@@this.�Ɖ�敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B
         //�����Ɖ�.����ꗗ
        if (!((WEB3ReferenceDivDef.ORDER_REFERENCE).equals(
                this.referenceType) || 
            (WEB3ReferenceDivDef.CANCEL_REFERENCE).equals(
                this.referenceType)))
        {
            log.debug("�Ɖ�敪���g0:�����Ɖ�h�A" +
                "�g1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪���g0:�����Ɖ�h�A�g" +
                "1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���B");
        }
        
        //�Q�j�@@�v���y�[�W�ԍ��`�F�b�N 
       
        //�Q�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�Q�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
         //������̕�����𔻒f����@@�\���������郆�[�e�B���e�B�E
          //�N���X(WEB3StringTypeUtility.java)�B
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
       
        //�@@�R�j�@@�y�[�W���\���s���`�F�b�N
   
        // �R�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        // �@@�R�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
      
        //�S)�@@���M�\�[�g�L�[�`�F�b�N 
        
        // �@@�S�|�P)�@@this.���M�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");    
        }
        
        // �@@�S�|�Q)�@@this.���M�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");    
        }
        
        // �@@�Q�|�R)�@@this.���M�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            // �Q�|�R�|�P�j�@@�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
            WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
            if (l_mutualSortKey == null || 
                WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B"); 
            }
        
            // �Q�|�R�|�Q�j�@@�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B
             //�����E����.�E��������
            if (!((l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.TAX_TYPE)  || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.MUTUAL_DEALING_TYPE)  || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.ORDER_DATE) ||
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.SELL_BUY_DIV)))

            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂ��h�����h�A�h�����h�A�h���������h�ȊO�̏ꍇ");     
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
}@
