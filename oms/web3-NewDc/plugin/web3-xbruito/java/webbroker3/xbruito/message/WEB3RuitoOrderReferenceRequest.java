head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������Ɖ�N�G�X�g�N���X(WEB3RuitoOrderReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.define.WEB3RuitoReferenceTypeDef;

/**
 * �ݓ������Ɖ�N�G�X�g<BR>
 */
public class WEB3RuitoOrderReferenceRequest extends WEB3GenRequest
{

	/**
	* ���O�o�̓��[�e�B���e�B
	*/
   private static WEB3LogUtility log =
	   WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceRequest.class);
	   	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      
	   
    /**
     * �Ɖ�敪<BR>
     * 0�F�����Ɖ�A1�F����ꗗ(����\�Ȃ��̂̂ݕ\��)<BR>
     */
    public String referenceType;

    /**
     * �\�[�g�L�[<BR>
     * �Ώۍ��ځF�t�@@���h��(������)�A�����敪�A��������<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoSortKey[] ruitoSortKeys;

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
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C8D005D
     */
    public WEB3RuitoOrderReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�Ɖ�敪��null�̒l�ł���ꍇ�A��O���X���[����B<BR>
     *              class    : WEB3BusinessLayerException<BR>
     *              tag      : BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�j�@@this.�Ɖ�敪���g0:�����Ɖ�h�A<BR>
     *               �g1:����ꗗ(����\�Ȃ��̂̂�<BR>
     * �@@�@@�@@�@@      �\��)�h�ȊO�ł���ꍇ�A��O���X���[����B<BR>
     *              class    : WEB3BusinessLayerException<BR>
     *              tag      : BUSINESS_ERROR_00082<BR>
     * �Q�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�ݓ��\�[�g�L�[��null�̒l�ł���Η�O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00231<BR>
     * �@@�Q�|�Q�jthis.�ݓ��\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00232<BR>
     * �@@�Q�|�R�jthis.�ݓ��\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00085<BR>
     * �@@�@@�Q�|�R�|�Q�j�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@          �E������<BR>
     * �@@�@@          �E�����敪<BR>
     * �@@          �@@�E��������<BR>
     *                class    : WEB3BusinessLayerException<BR>
     *                tag      : BUSINESS_ERROR_00086<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�R�j�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00087<BR>
     * �@@�@@�Q�|�R�|�S�j�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@           �E�hA�F�����h<BR>
     * �@@�@@           �@@�E�hD�F�~���h<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00088<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�R�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00089<BR>
     * �@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł����
     *             ��O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00090<BR>
     * �S�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�S�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00091<BR>
     * �@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł����
     *             ��O���X���[����B<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073678C00A8
     */
    public void validate() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
		 	
        //�Ɖ�敪�`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("�Ɖ�敪�����w��ł��B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ɖ�敪�����w��ł��B");
        }
        
        if (
            !(WEB3RuitoReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType)
                || WEB3RuitoReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType)))
        {
            //�Ɖ�敪���g0:�����Ɖ�h�A�g1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���ꍇ�A��O���X���[����B
            log.debug("�Ɖ�敪���g0:�����Ɖ�h�A�g1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ɖ�敪���g0:�����Ɖ�h�A�g1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���B");
        }

        //�\�[�g�L�[�`�F�b�N
        if (this.ruitoSortKeys == null)
        {
            log.debug("�\�[�g�L�[��null�̒l�ł���B");              
            //�ݓ��\�[�g�L�[��null�̒l�ł���Η�O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�\�[�g�L�[��null�̒l�ł���B");
        }
        
        //�ݓ��\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����
        if (this.ruitoSortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł���B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�\�[�g�L�[�̗v�f�����O�ł���B");
        }
        
        //�L�[���ڂ�null�̒l�ł���Η�O���X���[����
 
        for (int i = 0; i < this.ruitoSortKeys.length; i++)
        {
            if (this.ruitoSortKeys[i].keyItem == null)
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            }
        }

        //�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����
        for (int i = 0; i < ruitoSortKeys.length; i++)
        {
            if (!(this.ruitoSortKeys[i].keyItem.equals("ruitoProductCode")
                || this.ruitoSortKeys[i].keyItem.equals("ruitoDealingType")
                || this.ruitoSortKeys[i].keyItem.equals("orderDate")))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //�����^�~����null�̒l�ł���Η�O���X���[����
        for (int j = 0; j < this.ruitoSortKeys.length; j++)
        {
            if (this.ruitoSortKeys[j].ascDesc == null)
            {
                log.debug("�����^�~�������w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�����^�~�������w��ł��B");
            }
        }

        //�����^�~�����ȁhA�F����",�hD�F�~���h�l�ȊO�̏ꍇ��O���X���[����
        for (int k = 0; k < this.ruitoSortKeys.length; k++)
        {
            if (!("A".equals(this.ruitoSortKeys[k].ascDesc)
                || "D".equals(this.ruitoSortKeys[k].ascDesc)))
            {
                log.debug("�����^�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        //�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����
        try
        {
            double l_dblTemp = Double.parseDouble(this.pageIndex);
        }
        catch (NumberFormatException l_ex)
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "�v���y�[�W�ԍ��������ȊO�̒l�ł���B");
        }

        //�y�[�W���\���s����null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s�������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�y�[�W���\���s�������w��ł��B");
        }

        //�y�[�W���\���s���������ȊO�̒l�ł����
        try
        {
            double l_dblTemp = Double.parseDouble(this.pageSize);
        }
        catch (NumberFormatException l_ex)
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B�B");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ������Ɖ�X�|���X���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40922D7B03E7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoOrderReferenceResponse(this);
    }

}
@
