head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍϊ������N�G�X�g(WEB3OptionsCloseMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
              001: 2004/08/05 ���Ō�  �Ή��o�O WEB3_IFO_UT-000116
Revesion History : 2008/03/12 ����(���u) ���f�� 826
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V���������ԍϊ������N�G�X�g)<BR>
 * �����w���I�v�V���������ԍϊ������N�G�X�g�N���X<BR>
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginChangeCompleteRequest extends WEB3OptionsCommonRequest 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="options_closeMarginChangeComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406291134L;
      
    /**
     * (�����h�c)<BR>
     */
    public String id;
   
    /**
     * (�ԍό���)<BR>
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;
   
    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;
   
    /**
     * (�m�F���P��)<BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public String checkPrice;
   
    /**
     * (�m�F��������)<BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public Date checkDate;
   
    /**
     * @@roseuid 40C0A8EA0213
     */
    public WEB3OptionsCloseMarginChangeCompleteRequest() 
    {
    
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */    
    public WEB3GenResponse createResponse()
    {
        log.debug("createResponse.....START>>>>>");
        return new WEB3OptionsCloseMarginChangeCompleteResponse(this);
        
    }
   
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@�ԍό��ʃ`�F�b�N<BR>
     * �@@this.�ԍό��ʂ�null�̒l�܂��́A�v�f�����O��<BR>
     * �@@����Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * �S�j�@@�������ʃ`�F�b�N<BR>
     * �@@�S�|�P�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * �@@�S�|�Q�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ��O�ȉ��̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * �T�j�@@�ԍό��ʗv�f�`�F�b�N<BR>
     *    �ԍό��ʂ̗v�f�����ȉ��̃`�F�b�N���J��Ԃ��čs���B<BR>
     *    �T�|�P�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �U�j�@@�ԍό��ʑ����ʃ`�F�b�N<BR>
     *�@@�@@this.�������� = null and<BR>
     *  �i���ׂĂ̕ԍό��ʂ̐���=0 or null�j �̏ꍇ�A<BR>
     *  �@@�@@��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00285<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 406978800278
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " WEB3OptionsCloseMarginChangeCompleteRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        //�Q�j�h�c�`�F�b�N
        if(WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }

        //�R�j�ԍό��ʃ`�F�b�N
        if(this.closeMarginContractUnits == null 
            || this.closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                getClass().getName() + "validate",
                "�ԍό��ʂ�null�̒l�܂��́A�v�f�����O�ł���ꍇ�̃G���[");
        }

        //�������ʃ`�F�b�N(�S�|�P)
        if(WEB3StringTypeUtility.isNotEmpty(this.opOrderQuantity) 
            && !WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�������ʃ`�F�b�N(�S�|�Q)
        if(WEB3StringTypeUtility.isNotEmpty(this.opOrderQuantity) 
            && Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }       

        // �U�j�@@�ԍό��ʑ����ʃ`�F�b�N<BR>
        //�@@�@@this.�������� = null and<BR>
        //�@@ �i���ׂĂ̕ԍό��ʂ̐���=0 or null�j �̏ꍇ�A<BR>
        // �@@�@@��O���X���[����B<BR>
        //     class:WEB3BusinessLayerException<BR>
        //     tag:BUSINESS_ERROR_00285<BR>
        int l_intContractUnitsLength = closeMarginContractUnits.length;
        int l_intQuantityCnt = 0;
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                //�T�|�P�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B
                closeMarginContractUnits[i].validate();
                
                if (closeMarginContractUnits[i].contractOrderQuantity == null
                    || Double.parseDouble(closeMarginContractUnits[i].contractOrderQuantity) == 0)
                {
                    l_intQuantityCnt += 1;
                }
            }
        }
        
        // ���ׂĂ̕ԍό��ʂ̐���=0 or null�̏ꍇ�A��O���X���[����B
        if (l_intQuantityCnt == l_intContractUnitsLength)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00285,
                this.getClass().getName() + "validate",
                "���ׂĂ̕ԍό��ʂ̐��ʂ��O�ł���B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT���Ύ��) <BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c=null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * �R�j�@@�ԍό��ʃ`�F�b�N<BR>
     * �@@�R�|�P�jthis.�ԍό���=null �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00178<BR>
     * �@@�R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * �S�j�@@�m�F���P���`�F�b�N<BR>
     * �@@this.�m�F���P����null�̏ꍇ�A<BR>
     *�@@�u�m�F���P����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * �T�j�@@�m�F���������`�F�b�N<BR>
     * �@@this.�m�F����������null�̏ꍇ�A<BR>
     * �u�m�F����������null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00078<BR>
     * �U�j�@@�A�������E���������`�F�b�N<BR>
     * �@@�X�[�p�[�N���X��validate�A���������\�b�h���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateATReserveOrder()throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateATReserveOrder()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();
        
        // �Q�j�@@�h�c�`�F�b�N
        // this.�h�c=null �̏ꍇ�A��O���X���[����B
        if (this.id == null)
        {
            // ��O���X���[����B
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        // �R�j�@@�ԍό��ʃ`�F�b�N
        // �R�|�P�jthis.�ԍό���=null �̏ꍇ�A
        if (this.closeMarginContractUnits == null)
        {
            // ��O���X���[����B
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }
        // �R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A
        if (this.closeMarginContractUnits.length == 0)
        {
            // ��O���X���[����B
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }
        
        // �S�j�@@�m�F���P���`�F�b�N
        // this.�m�F���P����null�ł������ꍇ�A�u�m�F���P����null�v�̗�O���X���[����B
        if (this.checkPrice == null)
        {
            // �u�m�F���P����null�v�̗�O���X���[����B
            log.debug("�m�F���P�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F���P�������w��ł��B");
        }
        
        // �T�j�@@�m�F���������`�F�b�N 
        // this.�m�F����������null�ł������ꍇ�A�u�m�F����������null�v�̗�O���X���[����B
        if (this.checkDate == null)
        {
            // �u�m�F���P����null�v�̗�O���X���[����B
            log.debug("�m�F�������������͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�������������͂���Ă��܂���B");
        }

        // �U�j�@@�A�������E���������`�F�b�N
        // �X�[�p�[�N���X��validate�A���������\�b�h���R�[������B
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@