head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������o�^�Ɖ�N�G�X�g(WEB3AdminMutualConditionsReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2007/04/09 �����F (���u) ���f��548
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����������o�^�Ɖ�N�G�X�g<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsReferenceRequest 
    extends WEB3GenRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131230L;
    
    /**
     * (�����R�[�h)<BR>
     * ���M�����R�[�h<BR>
     * (null�̏ꍇ�A�w��i�V�Ƃ���)<BR>
     */
    public String mutualProductCode;
    
    /**
     * ���M��������R�[�h<BR>
     * (null�̏ꍇ�A�w��i�V�Ƃ���)<BR>
     */
    public String mutualAssocProductCode;
    
    /**
     * ���M�����J�e�S���[�R�[�h<BR>
     * (null�̏ꍇ�A�w��i�V�Ƃ���)<BR>
     */
    public String categoryCode;
    
    /**
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (���M�E�O��MMF�\���敪)<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * 0:���M�̂�  <BR>
     * 1:�O��MMF�̂�  <BR>
     * 2:����<BR>
     * <BR>
     * ��null�̏ꍇ�A�u2:�����v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * �y�[�W���\���s��<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceRequest.class);    
    
    /**
     * (���M���������o�^�Ɖ�N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40DF7657015E
     */
    public WEB3AdminMutualConditionsReferenceRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���������o�^�Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF76EB03C0
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsReferenceResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * 1) �v���y�[�W�ԍ��`�F�b�N<BR>
     *  1-1) this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00089 <BR>
     *  1-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * 2) �y�[�W���\���s���`�F�b�N<BR>
     *  2-1) this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00091 <BR>
     *  2-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00092 <BR>
     * @@roseuid 40DF76EB03DF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) �v���y�[�W�ԍ��`�F�b�N
        
        //1-1) this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");    
        }
        
        //1-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B     
         //������̕�����𔻒f����@@�\���������郆�[�e�B���e�B
          //�E�N���X(WEB3StringTypeUtility.java)�B
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł���B");
        }
       
        //2) �y�[�W���\���s���`�F�b�N 
        
        //2-1) this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }        
        
        // 2-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
     }
 }
@
