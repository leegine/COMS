head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍψꗗ��ʕ\�����N�G�X�g�N���X(WEB3OptionsCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (�����w���I�v�V�����ԍψꗗ��ʕ\�����N�G�X�g)<BR>
 * �����w���I�v�V�����ԍψꗗ��ʕ\�����N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginListRequest extends WEB3GenRequest
{

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginListRequest.class);
            
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginList";

    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200406101102L;
            
	/**
	 * (�����R�[�h)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * �����R�[�h<BR>
	 * <BR>
	 *�������R�[�h�ɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String opProductCode;
    
	/**
	 * (����s��)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * 1�F����<BR>
	 * 2�F���<BR>
	 * <BR>
	 *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String marketCode;
    
	/**
	 * (�w�����)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * 0005�FTOPIX<BR>
	 * 0018�F���o225<BR>
	 * 0016�F���o300<BR>
     * 0019�F�~�j���o225<BR>
	 * <BR>
	 *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String targetProductCode;
    
	/**
	 * (����)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * ����(YYYYMM�`��)<BR>
	 * <BR>
	 *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String delivaryMonth;
    
	/**
	 * (�I�v�V�������i�敪)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * P�F�v�b�g�I�v�V����<BR>
	 * C�F�R�[���I�v�V����<BR>
	 * <BR>
	 *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String opProductType;
    
	/**
	 * (�s�g���i)<BR>
	 * <BR>
	 *(���������w�莞�Ɏg�p)<BR>
	 * <BR>
	 * null�F�w��Ȃ�<BR>
	 * �s�g���i<BR>
	 * <BR>
	 *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
	 */
	public String strikePrice;

    /**
    * �����w���敨�I�v�V�����\�[�g�L�[<BR>
    * �Ώۍ��ځF�����R�[�h�A���敪�A���v�A���v�i���o��j<BR>
    */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
    * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
    */
    public String pageIndex;

    /**
    * �y�[�W���\���s��<BR>
    * �P�y�[�W���ɕ\�����������s�����w��<BR>
    */
    public String pageSize;

    /**
    * @@roseuid 40C0A8EC0290
    */
    public WEB3OptionsCloseMarginListRequest()
    {

    }

    /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�\�[�g�L�[�`�F�b�N<BR>
    * �@@�P�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��<BR>
    * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00231<BR>
    * �@@�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
    * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00232<BR>
    * �@@�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
    * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B<BR>
    * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00085<BR>
    * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
    * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
    * �@@�@@�@@�E�����R�[�h<BR>
    * �@@�@@�@@�E���敪<BR>
    * �@@�@@�@@�E���v<BR>
    * �@@�@@�@@�E���v(���o�)<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00278<BR>
    * �@@�@@�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00087<BR>
    * �@@�@@�P�|�R�|�S�j�\�[�g�L�[.�����^�~���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
    * �@@�@@�@@�E�hA�F�����h<BR>
    * �@@�@@�@@�E�hD�F�~���h<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00088<BR>
    * <BR>
    * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
    * �@@�Q�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00089<BR>
    * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00090<BR>
    * <BR>
    * �R�j�@@�y�[�W���\���s���`�F�b�N<BR>
    * �@@�R�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00091<BR>
    * �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00092<BR>
    * <BR>
    * �S�j�@@�����ݒ�`�F�b�N<BR>
    *   �S�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
    *        �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)<BR>
    *      �E�����R�[�h<BR>
    *      �E����s��<BR>
    *      �E�w�����<BR>
    *      �E����<BR>
    *      �E�I�v�V�������i�敪<BR>
    *      �E�s�g���i<BR>
    *      class: WEB3BusinessLayerException<BR>
    *      tag:   BUSINESS_ERROR_00830<BR>
    * �@@�S�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
    *      ����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i�̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
    *      class: WEB3BusinessLayerException<BR>
    *      tag:   BUSINESS_ERROR_00830<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A1D9B031F
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�\�[�g�L�[�`�F�b�N
        //�P�|�P�j�����w���敨�I�v�V�����\�[�g�L�[��null�̒l�ł���Η�O���X���[����
        if (futOpSortKeys == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[��null�̒l�ł���B");
        }

        //�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����
        if (futOpSortKeys.length == 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł���B");
        }

        //�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        int l_intOpSortKeysLength = futOpSortKeys.length;
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            //�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂ�null�̒l�ł���B");
            }

            //�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B
            if (!WEB3IfoKeyItemDef.PRODUCTCODE.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME_COST.equals(futOpSortKeys[i].keyItem))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }

            //�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~����null�̒l�ł���B");
            }

            //�P�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
            if (!WEB3AscDescDef.ASC.equals( futOpSortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        //�Q�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�Q�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(pageIndex))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ���null�̒l�ł���B");
        }

        //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł���B");
        }

        //�R�j�@@�y�[�W���\���s���`�F�b�N
        //�R�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(pageSize))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�B");
        }

        //�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
		//�S�j �����ݒ�`�F�b�N
		//�S�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B
		//    �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)               
		if((opProductCode!=null)               
			&&(marketCode!=null)               
			&&(targetProductCode!=null)                
			&&(delivaryMonth!=null)                
			&&(opProductType!=null)                
			&&(strikePrice!=null))             
		{              
			throw new WEB3BusinessLayerException(         
				WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
				this.getClass().getName() + "." + STR_METHOD_NAME,        
				"�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă��܂��B");      
		}              
                
		// �S�|�Q�j�����I�����Ɏ���s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�̂����ꂩ�̍��ڂ�              
		// �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B             
		if((marketCode==null)
			&&(targetProductCode==null)
			&&(delivaryMonth==null)                
			&&(opProductType==null)
			&&(strikePrice==null))          
		{              
			return;            
		}
		else              
		{              
			if((marketCode==null)
				||(targetProductCode==null)
				||(delivaryMonth==null)            
				||(opProductType==null)
				||(strikePrice==null))      
			{          
				throw new WEB3BusinessLayerException(  
					WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�������荀�ڂ̂����ꂩ���ݒ肳��Ă��܂���B");
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
        return new WEB3OptionsCloseMarginListResponse(this);
    }
}
@
